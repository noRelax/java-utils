/*
 * RecordSet.java v1.0 2007-09-25 
 * Copyright 2007  �����пƼ�
 */

package org.jdesktop.databuffer;

import org.jdesktop.databuffer.provider.sql.JDBCDataConnection;
import java.sql.PreparedStatement;

/**
 * DBHelper���װ����������,���ڼ򻯶����ݿ��¼�Ĳ��롢�޸ġ�ɾ��������<br>
 * 1��executeUpdate��������ִ��Insert��Update���.<br>
 * 2��executeQuery��������ִ�����ݿ��ѯ��������һ����¼������<br>
 * DBHelper��һ��������࣬������̳�ʱҪʵ��getConn()������ȷ��ȷ�е����ݿ����Ӷ���
 * ����ʾ�У�<br><br>
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

	
	//��ȡ���ݿ����Ӷ��󣬳��󷽷�����Ҫ��������ʵ��
	protected abstract JDBCDataConnection getConn();
	
	
	/**
	 * �÷�������ִ�����ݿ��¼�Ĳ���Insert���޸�Update��� 
	 * @param pSql �� ��ִ�е�Insert��Update��䡣
	 */
	public  int executeUpdate(String pSql) throws Exception {		  
		this.getConn().setConnected(true);  
		PreparedStatement ps = this.getConn().prepareStatement(pSql);
		this.getConn().setConnected(false);
		return ps.executeUpdate();				  		
	}

	/**
	 * �÷�������ִ�����ݿ��¼�Ĳ�ѯ��� 
	 * @param pSql �� ��ִ�е�Select��䡣
	 * @return ��Ų�ѯ�����RecordSet��¼��
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
