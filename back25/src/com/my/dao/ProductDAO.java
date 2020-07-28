package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.DuplicatedException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;
import com.my.vo.Product;

public class ProductDAO {
	public void insert(Product product) throws AddException, DuplicatedException{}
	public Product selectByNo(String no) throws FindException{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			connection = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		String selectBtProdNo = 
				"SELECT \r\n" + 
				"    prod_no,\r\n" + 
				"    prod_name,\r\n" + 
				"    prod_price\r\n" + 
				"FROM product\r\n" + 
				"WHERE prod_no = ?";
		
		try {
			pstmt = connection.prepareStatement(selectBtProdNo);
			pstmt.setString(1, no);
			result = pstmt.executeQuery();
			if(result.next()) {
				Product product = new Product();
				product.setProd_no(result.getString("prod_no"));
				product.setProd_name(result.getString("prod_name"));
				product.setProd_price(result.getInt("prod_price"));
				return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				MyConnection.close(result, pstmt, connection);
			}
		}
		
		return null;
	}
	public List<Product> selectByName(String word) throws FindException{return null;}
	public List<Product> selectAll(int page) throws FindException{return null;}
	
	public List<Product> selectAll() throws FindException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<Product>();
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String selectAll = "SELECT \r\n" + 
				"    prod_name\r\n" + 
				"    ,prod_no\r\n" + 
				"    ,prod_price\r\n" + 
				"FROM product\r\n";
		try {
			pstmt = con.prepareStatement(selectAll);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product(rs.getString("prod_no"), rs.getString("prod_name"), rs.getInt("prod_price"));
				list.add(product);
			}
			if(list.size() == 0)
				throw new FindException("�긽�뭹�씠 �뾾�뒿�땲�떎.");
			//return list; //return 吏곸쟾�뿉 finally 援щЦ �닔�뻾
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
		return list;
	}
}
