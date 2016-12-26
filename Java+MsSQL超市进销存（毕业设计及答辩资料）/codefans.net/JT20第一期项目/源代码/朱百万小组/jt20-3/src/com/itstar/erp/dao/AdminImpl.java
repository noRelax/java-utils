package com.itstar.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminImpl implements AdminDao {

	public void addAdmin(Admin admin) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Init.getConnection();
			String sql = "Insert into Admin(userid,psd,Asex,AEmail,APhone) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, admin.getUserid());
			ps.setString(2, admin.getPsd());
			ps.setInt(3, admin.getSex());
			ps.setString(4, admin.getAEmail());
			ps.setString(5, admin.getAPhone());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Init.free(rs, ps, conn);
		}
	}

	public void delAdmin(Admin admin) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = Init.getConnection();
			st = conn.createStatement();
			String sql = "delete from Admin where userid='" + admin.getUserid()+ "'";
			st.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Init.free(rs, st, conn);
		}
	}

	public void modifyAdmin(Admin admin) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Init.getConnection();
			String sql = "update Admin set psd=? where userid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,admin.getPsd());
			ps.setString(2,admin.getUserid());
			ps.executeUpdate();
		} catch (Exception e) {
				e.printStackTrace();
			}finally{
			Init.free(rs, ps, conn);
		}
     }
	
	public boolean findLogin(Admin admin) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
		conn=Init.getConnection();
		String sql="select userid,psd from Admin where userid=? and psd=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1, admin.getUserid());
		ps.setString(2, admin.getPsd());
		rs=ps.executeQuery();
		while(rs.next()) { return true; }
	}catch(Exception e){
		e.printStackTrace();
	} finally {
		Init.free(rs, ps, conn);
	}
	return false;
	}

	public boolean findAdmin(Admin admin) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try{
		conn=Init.getConnection();
		st=conn.createStatement();
		String sql="select userid from Admin where userid='"+admin.getUserid()+"'";
		rs=st.executeQuery(sql);
		while(rs.next()){
			return true;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			Init.free(rs, st, conn);
		}
		return false;
	}
}
