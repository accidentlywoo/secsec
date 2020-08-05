package com.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	private final static String className = "oracle.jdbc.driver.OracleDriver";
	private final static String url = "jdbc:oracle:thin:@192.168.0.114:1521:xe";;
	private final static String user ="test";
	private final static String password = "test";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection con = null;
		con = DriverManager.getConnection(url, user, password);
		Class.forName(className);
		return con;
	}
	public static void close(Connection connection)  {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void close(Statement statement, Connection connection) {
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close(connection);
	}
	public static void close(ResultSet resultSet, Statement statement, Connection connection) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close(statement, connection);
	}
}
