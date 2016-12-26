package com.itstar.system.daoimple;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.itstar.system.bean.*;
import com.itstar.system.dao.*;
import com.util.*;

public class UserDaoimple implements UserDao {
	PreparedStatement pps = null;
	Connection con = null;
	public UserDaoimple(){
		super();
		if(con==null)
			con = JdbcConnection.getConnection();
	}
	
	public List queryall() {
		ResultSet rs = null;
		List<UserBean> list = new ArrayList<UserBean>();
		String strsql = "select * from Users";
		
		try {
			pps = con.prepareStatement(strsql);
			rs = pps.executeQuery();
			while (rs.next()) {
				UserBean userBean = new UserBean();
				userBean.setUid(rs.getInt(1));
				userBean.setUsername(rs.getString(2));
				userBean.setUpassword(rs.getString(3));
				userBean.setUstate(rs.getString(4));
				list.add(userBean);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块s
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}

		}
		return list;
	}

	public boolean insert(String username, String upassword) {
		int updateline = 0;
		String strsql = "insert into Users values(?,?,default)";
		try {
			pps = con.prepareStatement(strsql);
			pps.setString(1, username);
			pps.setString(2, upassword);
			updateline = pps.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		if (updateline != 0)
			return true;
		else
			return false;
	}
	
	public boolean delete(String username){
		int line=0;
		String strsql="delete from Users where username=?";
		try {
			pps=con.prepareStatement(strsql);
			pps.setString(1, username);
			line=pps.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}finally {
			try {
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		if (line != 0)
			return true;
		else
			return false;
		
	}
	
	public boolean update(String username,String upassword){
		int line=0;
		String strsql="update bbsusers set upassword=?  where username=?";
		try {
			pps=con.prepareStatement(strsql);
			pps.setString(1, upassword);
			pps.setString(2, username);
			line=pps.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(line!=0)
		return true;
		else return false;
	}
}
