/*
 * RecordSet.java v1.0 2007-09-25 
 * Copyright 2007  齐智行科技
 */

package org.jdesktop.databuffer;

import org.jdesktop.databuffer.provider.sql.JDBCDataConnection;
import java.sql.PreparedStatement;

/**
 * DBHelper类封装了两个方法,用于简化对数据库记录的插入、修改、删除操作。<br>
 * 1、executeUpdate方法用于执行Insert、Update语句.<br>
 * 2、executeQuery方法用于执行数据库查询，并返回一个记录集对象。<br>
 * DBHelper是一个抽象的类，其子类继承时要实现getConn()方法，确定确切的数据库连接对象
 * 子类示列：<br><br>
   import org.jdesktop.databuffer.provider.sql.JDBCDataConnection;<br>
   <br>
   public class DBLwsk extends DBHelper {<br>
   private JDBCDataConnection Conn;<br><br>
   public DBLwsk(){<br>
		 String driver = "oracle.jdbc.driver.OracleDriver";<br>
	     String url = "jdbc:oracle:thin:@136.48.16.248:1521:SZDS";<br>
	     String user = "lwskxmgl07";<br>
	     String password = "lwskxmgl07";<br>        
	     Conn = new  JDBCDataConnection(driver,url,user,password);<br>
	}<br>	<br>
	protected JDBCDataConnection getConn()<br>
	{<br>
	     return Conn;<br>
	}<br>
}
* @author zlz
*/

public abstract class DBHelper {

	
	//获取数据库连接对象，抽象方法，需要在子类中实现
	protected abstract JDBCDataConnection getConn();
	
	
	/**
	 * 该方法用于执行数据库记录的插入Insert、修改Update语句 
	 * @param pSql — 待执行的Insert、Update语句。
	 */
	public  int executeUpdate(String pSql) throws Exception {		  
		this.getConn().setConnected(true);  
		PreparedStatement ps = this.getConn().prepareStatement(pSql);
		this.getConn().setConnected(false);
		return ps.executeUpdate();				  		
	}

	/**
	 * 该方法用于执行数据库记录的查询语句 
	 * @param pSql — 待执行的Select语句。
	 * @return 存放查询结果的RecordSet记录集
	 */
	public  RecordSet executeQuery(String pSql)
			throws Exception {
		RecordSet Rs = new RecordSet();
		this.getConn().setConnected(true);		
		Rs.fillBySQL(pSql, this.getConn());
		this.getConn().setConnected(false);
		return Rs;
	}
}
