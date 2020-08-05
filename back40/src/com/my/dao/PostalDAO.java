package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.FindException;
import com.my.sql.MyConnection;
import com.my.vo.Postal;

public class PostalDAO {
	public List<Postal> selectByDoro(String doro) throws FindException{
		List<Postal> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = MyConnection.getConnection();
			
			String selectByDoro = 
					"SELECT \r\n" + 
					"    zipcode\r\n" + 
					"    ,buildingno\r\n" + 
					"    ,sido city\r\n" + 
					"    ,doro\r\n" + 
					"    ,building\r\n" + 
					"FROM postal\r\n" + 
					"WHERE building LIKE ? " + 
					"OR doro || ' ' || DECODE(building2, '0', building1, building1 ||'-'|| building2) LIKE ?";
			pstmt = con.prepareStatement(selectByDoro);
			pstmt.setString(1, "%"+doro+"%");
			pstmt.setString(2, "%"+doro+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String zipcode = rs.getString("zipcode");
				String buildingno = rs.getString("buildingno");
				String city = rs.getString("city");
				String doro1 = rs.getString("doro");
				String building = rs.getString("building");
				Postal postal = new Postal(zipcode, buildingno, city, doro1, building);
				list.add(postal);
			}
			if(list.size()==0)
				throw new FindException("검색 결과가 없습니다.");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		PostalDAO postalDAO = new PostalDAO();
		try {
			List<Postal> list = postalDAO.selectByDoro("홍익길");
			for(Postal item : list)
				System.out.println(item.toString());
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
}
