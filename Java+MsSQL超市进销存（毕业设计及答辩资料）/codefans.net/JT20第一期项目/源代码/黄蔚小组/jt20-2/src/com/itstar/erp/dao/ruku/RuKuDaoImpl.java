package com.itstar.erp.dao.ruku;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.util.DBConnection;
import com.itstar.erp.vo.ruku.RuKuBean;

public class RuKuDaoImpl implements RuKuDaoI {
	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;
	public RuKuBean Query(String value) {
		return null;
		}
	public int getproID(String proName){
		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select proID from tb_product_info where proName='"+proName+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int proID = 0;
		try {
			if(rs.next()){
				proID=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proID;
	}
	public void insert(RuKuBean bean) {

		try {
			p=conn.prepareStatement("insert into tb_ruku_info values(?,?,?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		p.setString(1, bean.getRukuDateTime());
		p.setInt(2, bean.getRukuAcount());
		p.setInt(3,bean.getProID());
		p.setInt(4, bean.getYwyID());
		p.setString(5, bean.getRukuRemark());
		p.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
	}

}
