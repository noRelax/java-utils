package com.itstar.erp.dao.tuiku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.util.DBConnection;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.vo.product.ProBean;
import com.itstar.erp.vo.ruku.RuKuBean;
import com.itstar.erp.vo.supply.SupplyBean;
import com.itstar.erp.vo.tuiku.TuiKuBean;

public class TuiKuDaoImpl implements TuiKuDaoI {
	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;
	
	public RuKuBean getRuKuBean(int rukuid) {

		RuKuBean bean=new RuKuBean();
		ResultSet rs=new GetRS().getResultSet("select * from tb_ruku_info where rukuID="+rukuid);
		try {
			if(rs.next()){
				bean.setRukuID(rs.getInt(1));
				bean.setRukuDateTime(rs.getString(2));
			
				bean.setRukuAcount(rs.getInt(3));
				bean.setProID(rs.getInt(4));
				bean.setYwyID(rs.getInt(5));
				bean.setRukuRemark(rs.getString(6));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;

	}
	
	public ProBean getProBean(int proid) {

		ProBean bean=new ProBean();
		ResultSet rs=new GetRS().getResultSet("select * from tb_product_info where proID="+proid);
		try {
			if(rs.next()){
				bean.setProID(rs.getInt(1));
				bean.setProTypeID(rs.getInt(2));
			
				bean.setProName(rs.getString(3));
				bean.setProPrice(rs.getDouble(4));
				bean.setProCreateTime(rs.getString(5));
				bean.setYwyID(rs.getInt(6));
				bean.setSpID(rs.getInt(7));
				bean.setProRemark(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;

	}
	public SupplyBean getSpBean(int spid) {

		SupplyBean bean=new SupplyBean();
		ResultSet rs=new GetRS().getResultSet("select * from tb_supply_info where spID="+spid);
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;

	}

	
	public void insert(TuiKuBean bean) {
		try {
			p=conn.prepareStatement("insert into tb_tuiku_info values(?,?,?,?,?)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			p.setInt(1, bean.getRukuID());
			p.setString(2, bean.getTkTime());
			p.setInt(3,bean.getTkAcount());
			p.setString(4, bean.getTkRemark());
			p.setInt(5, bean.getYwyID());
			p.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	public void update(int rukuid, int rukuleft) {
		try {
			s=conn.createStatement();
			s.executeUpdate("update tb_ruku_info set rukuAcount="+rukuleft+" where rukuID="+rukuid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}


