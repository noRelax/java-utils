package com.itstar.erp.dao.tianjiayonghu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itstar.erp.util.DBConnection;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.vo.kehu.KeHuBean;
import com.itstar.erp.vo.user.UserBean;

public class TianJiaUserDaoImpl implements TianJiaUserDaoI {

	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;

	public boolean test(String user, String pass) {
		System.out.print("fgdfgdfgdfgg");
		boolean flag;
		ResultSet rs=new GetRS().getResultSet("select * from tb_user_info where userName='"+user+"' and userPassWord='"+pass+"'");
		System.out.println("select * from tb_user_info where userName='"+user+"' and userPassWord='"+pass+"'");
		
//		try {
//			rs.beforeFirst();                       //“∆∂Ø”Œ±Í
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
		
		int i = 0;
		try {
			while(rs.next()){
				i=rs.getInt(1);
				
				System.out.print(i);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		if(i==0){
			flag=false;
		}else{
			flag=true;
		}
		return flag;
	}

	public void insert(UserBean bean) {
		try {
			p=conn.prepareStatement("insert into tb_user_info values(?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		p.setString(1, bean.getUserName());
		p.setString(2, bean.getUserPassWord());
		p.setInt(3, bean.getUserPower());

		p.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void delete(int i) {
		
			try {
				s=conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				s.executeUpdate("delete from tb_user_info where userID="+i);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			
		}
		
		
	
}
