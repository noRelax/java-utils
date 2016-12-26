package com.itstar.erp.util;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	static Connection conn;
	static {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:odbc:test");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {

		
		return conn;
	}
}
