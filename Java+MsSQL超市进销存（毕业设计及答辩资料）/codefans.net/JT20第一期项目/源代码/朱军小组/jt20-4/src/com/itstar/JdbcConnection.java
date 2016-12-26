package com.itstar;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;



/** �������ʾ SQL �� PreparedStatement ���÷� */
public class JdbcConnection {

	private static Connection con;
//oracle����
//	private static String url="jdbc:oracle:thin:@localhost:1521:loushang";
//	private static String username="itstar";
//	private static String password="itstar";
//sqlserver2000����
//	private static String url="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=pubs";
//	private static String username="sa";
//	private static String password="sa";
	private static String url;
	private static String username;
	private static String password;
	private static String driverclass;
	private static ResourceBundle rb;

	public static Connection getConnection() {

//		try {
//			pro= new Properties();
//			pro.load(new FileInputStream("src\\database.properties"));
			rb=ResourceBundle.getBundle("database");
			
			
//		} catch (FileNotFoundException e) {
//			// TODO �Զ����� catch ��
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO �Զ����� catch ��
//			e.printStackTrace();
//		}
//		url=pro.getProperty("url");
//		username=pro.getProperty (" username");
//		password=pro.getProperty ("  password");
//		driverclass=pro.getProperty (driverclass");
		
		url=rb.getString("url");
		username=rb.getString("username");
		password=rb.getString("password");
		driverclass=rb.getString("driverclass");
	
			try {
				//sqlserver2000��������
				Class.forName(driverclass);
				//oracle��������
				//Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("��������ʧ��!");
			}
			try {	
			if(con==null)
			con = DriverManager.getConnection(url, username,
					password);
			if (con != null) {
				System.out.println("���ӳɹ�!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getConnection() �ڵĴ������:" + e.getMessage());
		}
		return con;
	}
}
