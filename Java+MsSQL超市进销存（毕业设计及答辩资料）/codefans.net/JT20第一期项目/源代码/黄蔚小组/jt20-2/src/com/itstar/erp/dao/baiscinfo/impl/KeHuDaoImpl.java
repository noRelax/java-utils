package com.itstar.erp.dao.baiscinfo.impl;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.dao.baiscinfo.dao.KeHuDao;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.vo.kehu.KeHuBean;

public class KeHuDaoImpl implements KeHuDao {
	
	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;
	public void insert(KeHuBean bean) {
		try {
			p=conn.prepareStatement("insert into tb_kehu_info values(?,?,?,?,?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		p.setString(1, bean.getKehuName());
		p.setString(2, bean.getKehuAddress());
		p.setString(3,bean.getKehuPhone());
		p.setString(4, bean.getKehuZip());
		p.setString(5, bean.getKehuConn());
		p.setString(6, bean.getKehuConnPhone());
		p.setString(7, bean.getKehuEmail());
		p.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public KeHuBean Query(String value) {
		KeHuBean bean=new KeHuBean();
		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select * from tb_kehu_info where kehuName='"+value+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			if(rs.next()){
				bean.setKehuID(rs.getInt(1));
				bean.setKehuName(rs.getString(2));
		        bean.setKehuAddress(rs.getString(3));
		        bean.setKehuPhone(rs.getString(4));
		        bean.setKehuZip(rs.getString(5));
		        bean.setKehuConn(rs.getString(6));
		        bean.setKehuConnPhone(rs.getString(7));
		        bean.setKehuEmail(rs.getString(8));
		}
			}catch(Exception e){
			e.printStackTrace();
		}
		return bean;
	}
	
	public void update(KeHuBean bean, String value) {

		try {
			p=conn.prepareStatement("update tb_kehu_info set kehuName=?,kehuAddress=?,kehuPhone=?,kehuZip=?,kehuConn=?,kehuConnPhone=?,kehuEmail=? where kehuName='"+value+"'");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try{
		p.setString(1, bean.getKehuName());
		p.setString(2, bean.getKehuAddress());
		p.setString(3, bean.getKehuConnPhone());
		p.setString(4, bean.getKehuZip());
		p.setString(5, bean.getKehuConn());
		p.setString(6, bean.getKehuConnPhone());
		p.setString(7, bean.getKehuEmail());
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
			s.executeUpdate("delete from tb_kehu_info where kehuName='"+value+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
	}

}
