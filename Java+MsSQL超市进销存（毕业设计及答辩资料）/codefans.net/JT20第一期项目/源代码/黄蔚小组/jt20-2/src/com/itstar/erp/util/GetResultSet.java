package com.itstar.erp.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetResultSet {
	public ResultSet getResultSet(String table){
		Connection conn=new DBConnection().getConnection();
		Statement s;
		ResultSet rs = null;
		try {
			s=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=s.executeQuery("select * from "+ table);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
		return rs;
		
	}
}
