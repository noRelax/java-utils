package com.itstar.erp.dao.baiscinfo.impl;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.dao.baiscinfo.dao.ProDao;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.vo.product.ProBean;

public  class ProDaoImpl implements ProDao {
	
	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;
	public void insert(ProBean bean) {

		try {
			p=conn.prepareStatement("insert into tb_product_info values(?,?,?,?,?,?,?)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
		p.setInt(1, bean.getProTypeID());
		p.setString(2, bean.getProName());
		p.setDouble(3,bean.getProPrice());
		p.setString(4, bean.getProCreateTime());
		p.setInt(5, bean.getYwyID());
		p.setInt(6, bean.getSpID());
		p.setString(7, bean.getProRemark());
		p.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
	}
	
	public ProBean Query(String value) {
		
			ProBean bean=new ProBean();
			try {
				s=conn.createStatement();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			try {
				rs=s.executeQuery("select * from tb_product_info where proName='"+value+"'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try{
				while(rs.next()){
					bean.setProID(rs.getInt(1));
					bean.setProTypeID(rs.getInt(2));
			        bean.setProName(rs.getString(3));
			        bean.setProPrice(rs.getDouble(4));
			        bean.setProCreateTime(rs.getString(5));
			        bean.setYwyID(rs.getInt(6));
			        bean.setSpID(rs.getInt(7));
			        bean.setProRemark(rs.getString(8));
			}
				}catch(Exception e){
				e.printStackTrace();
			}
			return bean;
		}

	
	public void update(int ywyid,String value) {

		try {
			s=conn.createStatement();
			s.executeUpdate("update tb_product_info set ywyID="+ywyid+" where proName='"+value+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
	
		
	}

	
	public void update(int ywyid, double d,String value) {
		try {
			s=conn.createStatement();
			s.executeUpdate("update tb_product_info set ywyID="+ywyid+",proPrice="+d+" where proName='"+value+"'");
		} catch (SQLException e) {
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
			s.executeUpdate("delete from tb_product_info where proName='"+value+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	}


