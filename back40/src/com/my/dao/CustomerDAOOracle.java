package com.my.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.DuplicatedException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.sql.MyConnection;
import com.my.vo.Customer;
import com.my.vo.Postal;

public class CustomerDAOOracle implements CustomerDAO{

	@Override
	public void insert(Customer customer) throws AddException, DuplicatedException, FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
		
		String insertSQL = 
				"INSERT INTO customer(id, pwd, name, buildingno, addr) "
				+ "VALUES (?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, customer.getId());
			pstmt.setString(2, customer.getPwd());
			pstmt.setString(3, customer.getName());
			pstmt.setString(4, customer.getPostal().getBuildingno());
			pstmt.setString(5, customer.getAddr());
			pstmt.executeUpdate(); //Java는 Auto Commit
		} catch (SQLException e) {// SQLException 에만 있는 메소드가 있다.
			e.printStackTrace();
			if(e.getErrorCode() == 1) { // SQLException만 있는 getErrorCode()
				// ErrorCode = 1 -> PK중복 에러
				throw new DuplicatedException("이미 존재하는 ID입니다.");
			}
			throw new AddException(e.getMessage());
		}finally {
			MyConnection.close(pstmt, con);
		}
	}

	@Override
	public List<Customer> selectAll() throws FindException {
		return null;
	}

	@Override
	public Customer selectById(String id) throws FindException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = MyConnection.getConnection();
		}catch (ClassNotFoundException | SQLException e) {
			throw new FindException(e.getMessage());
		}
		
		String selectByIdSQL =
				"SELECT \r\n" + 
				"     c.id\r\n" + 
				"    ,c.pwd\r\n" + 
				"    ,c.name\r\n" + 
				"    ,p.buildingno\r\n" + 
				"    ,sido ||' '|| NVL(p.sigungu, ' ') || ' '|| NVL(p.eupmyun, ' ') city\r\n" + 
				"    ,p.doro || ' ' || DECODE(building2, '0', building1, building1|| '-' ||building2) doro\r\n" + 
				"    ,p.building\r\n"
				+ ",p.zipcode " + 
				"    ,c.addr\r\n" + 
				"FROM customer c\r\n" + 
				"LEFT OUTER JOIN postal p\r\n" + 
				"    ON (c.buildingNo = p.buildingno)\r\n" + 
				"WHERE 1=1\r\n" + 
				"AND id =?";
		try {
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) { // BOF 상태 Begin of File 
				Customer customer = new Customer();
				Postal postal = new Postal(rs.getString("zipcode"), rs.getString("buildingno"), rs.getString("city"), rs.getString("doro"),rs.getString("building"));
				customer.setId(id);
				customer.setPwd(rs.getString("pwd"));
				customer.setName(rs.getString("name"));
				customer.setPostal(postal);
				customer.setAddr(rs.getString("city"));
				return customer;
			}
			throw new FindException("selectById : 찾을 수 없는 아이디 입니다.");
		} catch (SQLException e) {
			throw new FindException("selectById : "+e.getMessage());
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<Customer> selectByName(String word) throws FindException {
		return null;
	}

	@Override
	public void update(Customer customer) throws ModifyException, FindException {
		
	}

	@Override
	public void delete(String id) throws RemoveException, FindException {
		
	}
	public static void main(String[] args) throws FindException, IOException {
		CustomerDAOOracle control = null;
		control = new CustomerDAOOracle();
		System.out.println(control.selectById("id1").toString());
		
		Customer customer = new Customer("id2","pwd2","name2");
		try {
			control.insert(customer);
		} catch (AddException | FindException e) {
			e.printStackTrace();
		}
	}

}
