package com.itstar.erp.dao.baiscinfo.impl;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.dao.baiscinfo.dao.YwyDao;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.vo.yewuyuan.YwyBean;

public class YwyDaoImpl implements YwyDao {
	
	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;
	
	public void insert(YwyBean bean) {
		try {
			p=conn.prepareStatement("insert into tb_yewuyuan_info values(?,?,?)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
		p.setString(1, bean.getYwyName());
		p.setString(2, bean.getYwyAddress());
		p.setString(3,bean.getYwyPhone());
		p.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public YwyBean Query(String value) {
		
		YwyBean bean=new YwyBean();
		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select * from tb_yewuyuan_info where ywyName='"+value+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			if(rs.next()){
				bean.setYwyID(rs.getInt(1));
				bean.setYwyName(rs.getString(2));
		        bean.setYwyAddress(rs.getString(3));
		        bean.setYwyPhone(rs.getString(4));
		}
			}catch(Exception e){
			e.printStackTrace();
		}
		return bean;
	}

	
	public void update(YwyBean bean, String value) {
		try {
			p=conn.prepareStatement("update tb_yewuyuan_info set ywyName=?, ywyAddress=?,ywyPhone=? where ywyName='"+value+"'");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try{
		p.setString(1, bean.getYwyName());
		p.setString(2, bean.getYwyAddress());
		p.setString(3, bean.getYwyPhone());
		p.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
	}

	
	public void delete(String value) {
	

			try {
				s=conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				s.executeUpdate("delete from tb_yewuyuan_info where ywyName='"+value+"'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			
		}
		
	}



