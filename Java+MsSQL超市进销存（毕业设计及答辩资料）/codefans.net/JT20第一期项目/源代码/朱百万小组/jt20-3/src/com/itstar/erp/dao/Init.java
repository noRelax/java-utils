package com.itstar.erp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 功能描述：建立数据库连结的公共方法
 * 
 * @author Administrator
 * @version 2.0 2009-08-25
 */

public class Init {
	private static String url = "jdbc:microsoft:sqlserver://192.168.1.123:1433;DatabaseName=JT20_03";
	private static String user = "sa";
	private static String password = "";
	private static Connection conn = null;

	Init() {
	}

	// 数据库连结执行一次，以后便不可执行
	static {
		try {
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	// 连结数据库
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 释放连结
	public static void free(ResultSet rs,Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}

		}
	}
	
	public static void main(String[] args){
		new Init();
		System.out.println(Init.getConnection());
	}
}
