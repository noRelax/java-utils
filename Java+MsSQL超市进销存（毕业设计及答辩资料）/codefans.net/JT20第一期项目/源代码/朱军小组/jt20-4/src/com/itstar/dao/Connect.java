package com.itstar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	public static Connection conn = null;
	public static Statement stmt = null;
	
	public static boolean getConnect(){
		try{
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			String url = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=Erp";
			//conn = DriverManager.getConnection(url,"sa","");
			conn = DriverManager.getConnection(url,"sa","");
			stmt = conn.createStatement();
			conn.setAutoCommit(false);
			
		}catch(ClassNotFoundException e){
			System.out.println("找不到驱动类");
			return false;
		}catch(SQLException e){
			e.printStackTrace();
			System.exit(-1);
			return false;
		}
		return true;
	}
	
	public static void disConnect(){
		try{
			if(stmt !=null){
				stmt.close();
				stmt = null;
			}
			if(conn != null){
				conn.setAutoCommit(true);
				conn.close();
				conn = null;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args){
		System.out.println(getConnect());
	}*/

}
