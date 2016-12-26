
	package com.itstar.query.gysinfo;

	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.util.Properties;



	/** 这个类演示 SQL 中 PreparedStatement 的用法 */
	public class Jdbc{

		private static Connection con;
//	oracle参数
//		private static String url="jdbc:oracle:thin:@localhost:1521:loushang";
//		private static String username="itstar";
//		private static String password="itstar";
//	sqlserver2000参数
//		private static String url="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=pubs";
//		private static String username="sa";
//		private static String password="sa";
		private static String url;
		private static String username;
		private static String password;
		private static String driverclass;
		private static Properties pro;
		public static Connection getConnection() {

			try {
				pro= new Properties();
				pro.load(new FileInputStream("src\\database.properties"));
			} catch (FileNotFoundException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			url=pro.getProperty("url");
			username=pro.getProperty("username");
			password=pro.getProperty("password");
			driverclass=pro.getProperty("driverclass");
		
				try {
					//sqlserver2000加载驱动
					Class.forName(driverclass);
					//oracle加载驱动
					//Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("加载驱动失败!");
				}
				try {	
				if(con==null)
				con = DriverManager.getConnection(url, username,
						password);
				if (con != null) {
					System.out.println("连接成功!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("getConnection() 内的错误跟踪:" + e.getMessage());
			}
			return con;
		}
	}



