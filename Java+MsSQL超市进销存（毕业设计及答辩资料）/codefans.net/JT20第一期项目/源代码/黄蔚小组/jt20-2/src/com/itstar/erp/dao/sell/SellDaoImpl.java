package com.itstar.erp.dao.sell;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.util.DBConnection;
import com.itstar.erp.vo.sell.SellBean;

public class SellDaoImpl implements SellDaoI{
	
	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;
	
	public String getproName(int proID) {

		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select proName from tb_product_info where proID="+proID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String proName= null;
		try {
			if(rs.next()){
				proName=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proName;
	
		
	}


	public void insert(SellBean bean) {


		try {
			p=conn.prepareStatement("insert into tb_sell_info values(?,?,?,?,?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		p.setString(1, bean.getSellDateTime());
		p.setInt(2, bean.getProID());
		p.setInt(3,bean.getSellAcount());
		p.setDouble(4, bean.getSellPrice());
		p.setInt(5, bean.getYwyID());
		p.setString(6, bean.getSellRemark());
		p.setInt(7,bean.getKehuID());
		p.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
	
		
	}


	
	public void update(int sellid, int sellleft) {
		try {
			s=conn.createStatement();
			s.executeUpdate("update tb_sell_info set sellAcount="+sellleft+" where sellID="+sellid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	

	
}
