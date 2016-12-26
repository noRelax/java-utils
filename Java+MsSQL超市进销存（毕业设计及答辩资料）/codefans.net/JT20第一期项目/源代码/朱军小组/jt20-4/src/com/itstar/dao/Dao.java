package com.itstar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {

	
	private static String url;
	private static String serverName;
	private static String portNumber;
	private static String databaseName;
	private static String userName;
	private static String password;
	public static Connection con;
	public static PreparedStatement pst;
	public static ResultSet rs;
	//private String sql;
	public Dao(){
		url="jdbc:microsoft:sqlserver://";
		serverName="localhost";
		portNumber="1433";
		databaseName="Erp";
		userName="sa";
		password="";
	}
	private static String getConnectionUrl(){
		return url+serverName+":"+portNumber+";databaseName="+databaseName+";";
	}
	public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		con=DriverManager.getConnection(getConnectionUrl(),userName,password);
		
		return con;
	}
	public static ResultSet query(String sql) throws SQLException{
		   pst=con.prepareStatement(sql);
		    rs=pst.executeQuery();
			return rs;
	}
	public static boolean updata(String sql) throws SQLException{
		
		pst=con.prepareStatement(sql);
		pst.executeUpdate();
		return true;
	}
	/*public static void main(String args[]) throws ClassNotFoundException, SQLException{
		new Dao().getConnection();
	}*/
	
}
