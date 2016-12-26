package org.jdesktop.databuffer;

import org.jdesktop.databuffer.provider.sql.JDBCDataConnection;

public class DBLwsk extends DBHelper {
	
	private JDBCDataConnection Conn;
	
	public DBLwsk()
	{
		 String driver = "oracle.jdbc.driver.OracleDriver";
	     String url = "jdbc:oracle:thin:@136.48.16.248:1521:SZDS";
	     String user = "lwskxmgl07";
	     String password = "lwskxmgl07";        
	     Conn = new  JDBCDataConnection(driver,url,user,password);
	}
	
	protected JDBCDataConnection getConn()
	{
	     return Conn;
	}

}
