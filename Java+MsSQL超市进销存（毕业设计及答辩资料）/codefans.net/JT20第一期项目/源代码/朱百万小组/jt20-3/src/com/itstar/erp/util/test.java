package com.itstar.erp.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.dao.Init;


public class test {
	public static void  main(String[] args) throws Exception{
		 Connection conn=Init.getConnection();
         Statement st=null;
         ResultSet rs=null;
         try {
			st=conn.createStatement();
		} catch (SQLException e) {
		}
         String sql="select gname from Goods where gid='"+3+"'";
         rs=st.executeQuery(sql);
         while(rs.next()){
      	   System.out.println(rs.getString("gname"));
      	   }
}
}