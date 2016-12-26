
/**
 * Created on 2009-8-20
 */
package com.itstar.erp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * ���ݿ�������
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
     * ��ȡ����
     * @param 
     * @return Connection
     * @throws 
     */	
	
	public static Connection getConn() {
		if (conn == null) {
			try {
				Class.forName(drivername);
				conn = DriverManager.getConnection(url, sqlusername, sqlpswd);
System.out.println("���ݿ����ӳɹ�����");
				return conn;
			} catch (ClassNotFoundException e) {
				
				System.out.println("�޷���������������");
				
			} catch (SQLException e) {
				
				System.out.println("���ݿ����ӳ��ִ��󣡣���");
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
System.out.println("����GetConnection.closeConn()��conn��ֵΪ�� " + conn);
	}
	
//	public static void main(String[] args){
//		new GetConnection();
//		System.out.println(GetConnection.getConn());
//		GetConnection.closeConn();
//	}

}
