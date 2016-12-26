package com.itstar.erp.dao.baiscinfo.impl;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.dao.baiscinfo.dao.ProTypeDao;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.vo.producttype.ProTypeBean;

public class ProTypeDaoImpl implements ProTypeDao {
	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;
	
		
		
		public void insert(ProTypeBean bean) {
			try {
				p=conn.prepareStatement("insert into tb_productType_info values(?,?,?,?,?)");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try{
			p.setString(1, bean.getProTypeName());
			p.setString(2, bean.getProTypeDanwei());
			p.setString(3,bean.getProTypeCreateTime());
			p.setInt(4, bean.getYwyID());
			p.setString(5, bean.getProTypeRemark());
			p.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			
		}
			
		}



		
		public ProTypeBean Query(String value) {
			ProTypeBean bean=new ProTypeBean();
			try {
				s=conn.createStatement();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			try {
				rs=s.executeQuery("select * from tb_productType_info where proTypeName='"+value+"'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try{
				if(rs.next()){
					bean.setProTypeID(rs.getInt(1));
					bean.setProTypeName(rs.getString(2));
					bean.setProTypeDanwei(rs.getString(3));
			        bean.setProTypeCreateTime(rs.getString(4));
			        bean.setYwyID(rs.getInt(5));
			        bean.setProTypeRemark(rs.getString(6));
			}
				}catch(Exception e){
				e.printStackTrace();
			}
			return bean;
		}



		
		public void update(ProTypeBean bean, String value) {

			try {
				p=conn.prepareStatement("update tb_productType_info set proTypeDanwei=?, ywyID=?,proTypeRemark=? where proTypeName='"+value+"'");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			try{
			p.setString(1, bean.getProTypeDanwei());
			p.setInt(2, bean.getYwyID());
			p.setString(3, bean.getProTypeRemark());
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
				s.executeUpdate("delete from tb_productType_info where proTypeName='"+value+"'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			
		
			
		}




		public void update(String danwei, int ywid, String remark, String value) {
			try {
				s=conn.createStatement();
				s.executeUpdate("update tb_productType_info set proTypeDanwei='"+danwei+"',ywyID="+ywid+",proTypeRemark='"+remark+"' where proTypeName='"+value+"'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
			
		}




		

}
