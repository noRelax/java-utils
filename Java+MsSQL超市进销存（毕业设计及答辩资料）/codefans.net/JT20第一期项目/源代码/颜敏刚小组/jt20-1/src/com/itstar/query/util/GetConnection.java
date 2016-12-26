/**
 * 
 */
package com.itstar.query.util;
import java.sql.Connection;
import java.sql.DriverManager;
 
 
import java.sql.SQLException;

/**
 * @author Administrator
 *
 */
public class GetConnection {
	  Connection conn=null;
	public   Connection  getConnection() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException{
		String url = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=jxc";
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
		try{
			conn = DriverManager.getConnection(url, "sa", "sa");
			return conn;
		}
		finally{
		 //	if(conn!=null)conn.close();
		}
	}
	
	

}
