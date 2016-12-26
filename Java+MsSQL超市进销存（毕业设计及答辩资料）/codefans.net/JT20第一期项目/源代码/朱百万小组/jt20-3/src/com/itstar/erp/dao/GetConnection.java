
/**
 * Created on 2009-8-20
 */
package com.itstar.erp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 数据库连接类
 * 
 * @author Zhang Li
 */
public class GetConnection {

	private static Connection conn;
	private static String drivername = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	private static String url = "jdbc:microsoft:sqlserver://192.168.1.47:1433;DatabaseName=JT20_03";
	private static String sqlusername = "root1";
	private static String sqlpswd = "root";

	public GetConnection() {

	}

	
    /**
     * 获取连接
     * @param 
     * @return Connection
     * @throws 
     */	
	
	public static Connection getConn() {
		if (conn == null) {
			try {
				Class.forName(drivername);
				conn = DriverManager.getConnection(url, sqlusername, sqlpswd);
System.out.println("数据库连接成功！！");
				return conn;
			} catch (ClassNotFoundException e) {
				
				System.out.println("无法加载驱动！！！");
				
			} catch (SQLException e) {
				
				System.out.println("数据库连接出现错误！！！");
				e.printStackTrace();
				
			} finally {
				
				if (conn == null) {
					System.exit(-1);
				}
				
			}
		} 
		
		return conn;

	}
	
	public static void closeConn () {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
System.out.println("调用GetConnection.closeConn()后conn的值为： " + conn);
	}
	
//	public static void main(String[] args){
//		new GetConnection();
//		System.out.println(GetConnection.getConn());
//		GetConnection.closeConn();
//	}

}
