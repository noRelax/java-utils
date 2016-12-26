package com.itstar.erp.dao.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.util.DBConnection;

public class SellSearchDaoImpl implements SellSearchDaoI {
	
	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;
	
	String getYwyName(int ywyid){


		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select ywyName from tb_yewuyuan_info where ywyID="+ywyid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String ywyName= null;
		try {
			if(rs.next()){
				ywyName=rs.getString(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ywyName;
	}
	
	
	String getKehuName(int kehuid){


		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select kehuName from tb_kehu_info where kehuID="+kehuid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String kehuName= null;
		try {
			if(rs.next()){
				kehuName=rs.getString(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kehuName;
	
		
	
		
		
	}
}
