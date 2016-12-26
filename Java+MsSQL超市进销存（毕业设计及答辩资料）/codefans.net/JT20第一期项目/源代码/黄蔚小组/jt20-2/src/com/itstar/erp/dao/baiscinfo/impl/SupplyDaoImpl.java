package com.itstar.erp.dao.baiscinfo.impl;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.dao.baiscinfo.dao.SupplyDao;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.vo.supply.SupplyBean;



public class SupplyDaoImpl implements SupplyDao  {
	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;
	public void insert(SupplyBean bean) {
		try {
			p=conn.prepareStatement("insert into tb_supply_info values(?,?,?,?,?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		p.setString(1, bean.getSpName());
		p.setString(2, bean.getSpAddress());
		p.setString(3,bean.getSpPhone());
		p.setString(4, bean.getSpZip());
		p.setString(5, bean.getSpConn());
		p.setString(6, bean.getSpConnPhone());
		p.setString(7, bean.getSpEmail());
		p.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void update(SupplyBean bean,String value) {
		try {
			p=conn.prepareStatement("update tb_supply_info set spName=?,spAddress=?,spPhone=?,spZip=?,spConn=?,spConnPhone=?,spEmail=? where spName='"+value+"'");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try{
		p.setString(1, bean.getSpName());
		p.setString(2, bean.getSpAddress());
		p.setString(3, bean.getSpPhone());
		p.setString(4, bean.getSpZip());
		p.setString(5, bean.getSpConn());
		p.setString(6, bean.getSpConnPhone());
		p.setString(7, bean.getSpEmail());
		p.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public SupplyBean Query(String value) {
		SupplyBean bean=new SupplyBean();
		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select * from tb_supply_info where spName='"+value+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			if(rs.next()){
				bean.setSpID(rs.getInt(1));
				bean.setSpName(rs.getString(2));
				
		bean.setSpAddress(rs.getString(3));
		bean.setSpPhone(rs.getString(4));
		bean.setSpZip(rs.getString(5));
		bean.setSpConn(rs.getString(6));
		bean.setSpConnPhone(rs.getString(7));
		bean.setSpEmail(rs.getString(8));
		
		
		
		
		}
			}catch(Exception e){
			e.printStackTrace();
		}
		return bean;
		
	}

	public void Query(SupplyBean bean) {
		// TODO Auto-generated method stub
		
	}

	public void delete(String value) {
		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			s.executeUpdate("delete from tb_supply_info where spName='"+value+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	

}
