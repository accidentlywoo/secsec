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
	public Product selectByNo(String no) throws FindException{return null;}
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
				"    prod_namem\r\n" + 
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
				throw new FindException("상품이 없습니다.");
			//return list; //return 직전에 finally 구문 수행
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
		return list;
	}
}
