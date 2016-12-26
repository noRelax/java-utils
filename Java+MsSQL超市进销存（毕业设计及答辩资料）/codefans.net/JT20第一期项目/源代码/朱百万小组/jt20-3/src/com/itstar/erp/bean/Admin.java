/**
 * Created on 2009-8-20
 */
package com.itstar.erp.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.itstar.erp.dao.GetConnection;


/**
 * 管理员表封装类
 * 
 * @author Zhang Li
 */

public class Admin {
	
	private static String Admin; 
	private static String APswd;
	
	
	private static Connection conn;
		
	public static String getAdmin() {
		return Admin;
	}


	public static void setAdmin(String admin) {
		Admin = admin;
	}


	public static String getAPswd() {
		return APswd;
	}


	public static void setAPswd(String pswd) {
		APswd = pswd;
	}

    /**
     * SQL语句增加
     * 
     * @param 
     * @return String
     * @throws 
     */
	public static String addAdmin () {
		
		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql ="insert into Admin(Admin,APswd) values(?,?)";
		
		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setString(1,Admin);
			pdst.setString(2,APswd);

			set = pdst.executeUpdate();

			pdst.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if (set <= 0) {
			return "插入客户失败";
		} else {
			return "插入客户成功";
		}
		
	}
	
    /**
     * SQL语句删除
     * 
     * @param 
     * @return String
     * @throws 
     */
public static String delAdmin () {
		
		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "delete from Admin where Admin = ?";
		
		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setString(1,Admin);
			set = pdst.executeUpdate();

			pdst.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if (set <= 0) {
			return "删除客户失败";
		} else {
			return "删除客户成功";
		}
		
	}
	

//	public static void main(String[] args) {
//		Admin admin = new Admin();
//		admin.setAdmin("admin1");
//		admin.setAPswd("pswd");
//		System.out.println(admin.addAdmin());
//	}
	

}
