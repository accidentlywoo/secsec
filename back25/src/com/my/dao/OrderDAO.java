package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;

public class OrderDAO {
	public void insert(OrderInfo info) throws AddException{ //Transaction
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			con.setAutoCommit(false); // Default true -> 자동커밋 해재
			insertInfo(con, info);
			insertLines(con, info.getLines());
			con.commit();
		} catch (Exception e) { //무슨 문제발생하든
			e.printStackTrace();
			if(con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			throw new AddException(e.getMessage());
		}finally {
			MyConnection.close(con);
		}
		
	}
	private void insertInfo(Connection connection, OrderInfo info) throws AddException{
		//주문기본정보 추가
		PreparedStatement pstmt = null;
		String insertInfoSQL =
				"INSERT INTO order_info(order_no, order_id, order_dt) "
				+ "VALUES(order_seq.NEXTVAL, ?, SYSDATE)";
		try {
			pstmt = connection.prepareStatement(insertInfoSQL);
			pstmt.setString(1, info.getOrder_c().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException();
		}finally {
			//MyConnection.close(connection); -> insertLines에서도 connection을 사용해야 하기 때문에 커넥션을 close하면 안된다.
			MyConnection.close(pstmt, null);
		}
	}
	private void insertLines(Connection connection, List<OrderLine> lines) throws AddException{
		//주문상세정보들 추가
		PreparedStatement pstmt = null;
		String insertLineSQL =
				"INSERT INTO order_line(order_no, order_prod_no, order_quantity) " //값이오는 위치에만 ?:바인드변수 사용가능
				+ "VALUES(order_seq.CURRVAL, ?, ?)";
		try {
			pstmt = connection.prepareStatement(insertLineSQL); 
			// SQL문을 준비한다 ->DB에 구문을 작성해 둔다. 실행 시 필요값('?':바인드변수)만 보낸다
			// -> 반복문에서 성능이 더 좋다.
			// Statement는 구문을 보낼때마다, 매번 DB에 SQL구문을 보냄.
			for(OrderLine item : lines) {
				pstmt.setString(1, item.getOrder_p().getProd_no());
				pstmt.setInt(2, item.getOrder_quantity());
//				pstmt.executeUpdate();
				pstmt.addBatch(); // 일괄처리에 추가
			}
			pstmt.executeBatch(); // 일괄처리작업 수행
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			//MyConnection.close(connection); -> insertLines에서도 connection을 사용해야 하기 때문에 커넥션을 close하면 안된다.
			MyConnection.close(pstmt, null);
		}
	}
	public List<OrderInfo> selectById(String id) throws FindException{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderInfo> infos = new ArrayList<OrderInfo>();
		try {
			connection = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		String selectByIdSQL = 
				"SELECT \r\n" + 
				"      oi.order_no\r\n" + 
				"    , oi.order_id\r\n" + 
				"    , oi.order_dt\r\n" + 
				"    , ol.order_prod_no\r\n" + 
				"    , ol.order_quantity \r\n" + 
				"    , p.prod_name\r\n" + 
				"    , p.prod_no\r\n" + 
				"    , p.prod_price\r\n" + 
				"    , p.prod_price * ol.order_quantity as total\r\n" + 
				"FROM order_info oi\r\n" + 
				"    JOIN order_line ol\r\n" + 
				"    ON(oi.order_no = ol.order_no)\r\n" + 
				"    JOIN product p\r\n" + 
				"    ON(ol.order_prod_no = p.prod_no)\r\n" + 
				"WHERE oi.order_id = ?\r\n" + 
				"ORDER BY oi.order_no desc";
		try {
			pstmt = connection.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			OrderInfo info = new OrderInfo();
			Customer customer = new Customer();
			OrderLine line = new OrderLine();
			Product product = new Product();
			int order_no = 0;
			int temp_order_no = 0;
			List<OrderLine> lines = new ArrayList<OrderLine>();
			while(rs.next()) {
				temp_order_no = order_no; // 초기 0, 이전 order_no
				order_no = rs.getInt("order_no"); // 현재 order_no
				if(temp_order_no == 0) {
					customer.setId(rs.getString("order_id"));
					info.setOrder_no(order_no);
					info.setOrder_c(customer);
					info.setOrder_dt(rs.getString("order_dt"));
					
					product.setProd_no(rs.getString("prod_no"));
					product.setProd_name(rs.getString("prod_name"));
					product.setProd_price(rs.getInt("prod_price"));
					
					line.setOrder_no(order_no);
					line.setOrder_p(product);
					line.setOrder_quantity(rs.getInt("prod_price"));
					lines.add(line);
					info.setLines(lines);
					infos.add(info);
				}else if(temp_order_no != order_no) {
					info.setLines(lines);
					infos.add(info);
					
					lines = new ArrayList<OrderLine>();
					info = new OrderInfo();
					
					customer.setId(rs.getString("order_id"));
					info.setOrder_no(order_no);
					info.setOrder_c(customer);
					info.setOrder_dt(rs.getString("order_dt"));
					
					product.setProd_no(rs.getString("prod_no"));
					product.setProd_name(rs.getString("prod_name"));
					product.setProd_price(rs.getInt("prod_price"));
					
					line.setOrder_no(order_no);
					line.setOrder_p(product);
					line.setOrder_quantity(rs.getInt("prod_price"));
					lines.add(line);
					info.setLines(lines);
					infos.add(info);
				}else{
					product.setProd_no(rs.getString("prod_no"));
					product.setProd_name(rs.getString("prod_name"));
					product.setProd_price(rs.getInt("prod_price"));
					
					line.setOrder_no(order_no);
					line.setOrder_p(product);
					line.setOrder_quantity(rs.getInt("total"));
					lines.add(line);
				}
			}
			if(infos.size() == 0 ) {
				throw new FindException("주문 정보가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return infos;
	}
}
