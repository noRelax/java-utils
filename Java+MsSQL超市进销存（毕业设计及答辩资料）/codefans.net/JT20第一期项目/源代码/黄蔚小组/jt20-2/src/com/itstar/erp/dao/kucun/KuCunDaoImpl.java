package com.itstar.erp.dao.kucun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.util.DBConnection;
import com.itstar.erp.vo.product.ProBean;

public class KuCunDaoImpl implements KuCunDaoI {
	
	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;
	boolean has;
	int i;
	public int getkucunAcount(int proID){
		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select kucunAcount from tb_kucun_info where proID="+proID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int kucunAcount = 0;
		try {
			while(rs.next()){
				kucunAcount=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kucunAcount;
	}
	
	
	
	public void update(int proid,int total) {
	

			try {
				s=conn.createStatement();
				s.executeUpdate("update tb_kucun_info set kucunAcount="+total+" where proID="+proid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
			
		
			
		}
	
	public boolean test(int proID){

		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select proID from tb_kucun_info where proID="+proID);
			if(rs.next()){
				i=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(i==0)
		    return false;
		else
			return true; 
	}
	
	public void insert(int proid, int total) {


		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			s.executeUpdate("insert into tb_kucun_info values("+proid+","+total+")");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	public ProBean getProBean(int proid) {

		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select * from tb_product_info where proID="+proid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ProBean bean=new ProBean();
		try {
			while(rs.next()){
				bean.setProName(rs.getString(3));
				bean.setProPrice(rs.getDouble(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	
	}



	
	public int getproID(String proName) {
		// TODO Auto-generated method stub
		return 0;
	}
		
	}
	

