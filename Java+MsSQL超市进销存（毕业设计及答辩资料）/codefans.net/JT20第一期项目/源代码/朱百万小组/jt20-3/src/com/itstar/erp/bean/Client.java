/**
 * Created on 2009-8-20
 */
package com.itstar.erp.bean;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itstar.erp.dao.GetConnection;

/**
 * 客户表封装类
 * 
 * @author Zhang Li
 */
public class Client {

	private static int CId;
	private static String CName;
	private static String CAddress;
	private static String CPhone;
	private static String CAcount;
	private static String CEmail;

	public int getCId() {
		return CId;
	}

	public void setCId(int id) {
		CId = id;
	}

	public String getCName() {
		return CName;
	}

	public void setCName(String name) {
		CName = name;
	}

	public String getCAddress() {
		return CAddress;
	}

	public void setCAddress(String address) {
		CAddress = address;
	}

	public String getCPhone() {
		return CPhone;
	}

	public void setCPhone(String phone) {
		CPhone = phone;
	}

	public String getCAcount() {
		return CAcount;
	}

	public void setCAcount(String acount) {
		CAcount = acount;
	}

	public String getCEmail() {
		return CEmail;
	}

	public void setCEmail(String email) {
		CEmail = email;
	}

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		Client.conn = conn;
	}

	private static Connection conn;

	
    /**
     * SQL语句增加
     * 
     * @param 
     * @return String
     * @throws 
     */
	public static String addClient() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "insert into Client(CName,CAddress,CPhone,CAcount,CEmail) values(?,?,?,?,?)";

		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setString(1, CName);
			pdst.setString(2, CAddress);
			pdst.setString(3, CPhone);
			pdst.setString(4, CAcount);
			pdst.setString(5, CEmail);

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
	public static String delClient() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "delete from Client where CName = ?";

		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setString(1, CName);
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
    /**
     * SQL语句查询
     * 
     * @param 
     * @return int
     * @throws 
     */
	public static int count() {
		conn = GetConnection.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select count(*) from Client";

		int set = 0;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				set = rs.getInt(1);
			}
			
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (st != null) {
				st.close();
				st = null;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			
			if (set == 0) {
				return 1;
			}

		}
		return set;
	}
    /**
     * SQL语句查询
     * 
     * @param 
     * @return Object[][]
     * @throws 
     */
	public static Object[][] selectClient() {
		Object[][] showAll = new Object[count() + 1][5];
		conn = GetConnection.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from Client";
		int i = 0;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
//				System.out.println(rs.getString("CName") + rs.getString("CAddress") + rs.getString("CPhone") + rs.getString("CAcount") + rs.getString("CEmail"));
				showAll[i][0] = rs.getString("CName");
				showAll[i][1] = rs.getString("CAddress");
				showAll[i][2] = rs.getString("CPhone");
				showAll[i][3] = rs.getString("CAcount");
				showAll[i][4] = rs.getString("CEmail");
				i = i + 1;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (st != null) {
				st.close();
				st = null;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return showAll;

	}
	
    /**
     * SQL语句查询
     * 
     * @param String 客户名
     * @return Object[][]
     * @throws 
     */
	public static Object[][] selectClient(String cName) {
		Object[][] showAll = new Object[count() + 1][5];
		conn = GetConnection.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from Client where CName = '" + cName + "'";
		int i = 0;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("CName") + rs.getString("CAddress") + rs.getString("CPhone") + rs.getString("CAcount") + rs.getString("CEmail"));
				showAll[i][0] = rs.getString("CName");
				showAll[i][1] = rs.getString("CAddress");
				showAll[i][2] = rs.getString("CPhone");
				showAll[i][3] = rs.getString("CAcount");
				showAll[i][4] = rs.getString("CEmail");
				i = i + 1;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (st != null) {
				st.close();
				st = null;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return showAll;
}
	public static String addCli() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "insert into Client(CName,CAddress,CPhone,CAcount,CEmail) values(?,?,?,?,?,?)";

		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setString(2, CName);
			pdst.setString(3, CAddress);
			pdst.setString(4, CPhone);
			pdst.setString(5, CAcount);
			pdst.setString(6,CEmail);

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
	
	public String delet(){
		conn = GetConnection.getConn();
		String sql = "delete Client where CId = ?";
		PreparedStatement st = null;
	    int set=0;
		try{
			st = conn.prepareStatement(sql);
			st.setInt(1, CId);
			set = st.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
		
	}
	public String update(){
		conn = GetConnection.getConn();
		String sql = "UPDATE Client SET CId=?,CName=?,CAddress=?,CPhone=?,CAcount=?,CEmail=? where CId=?";
		PreparedStatement st = null;
		int set = 0;
		try {
				st = conn.prepareStatement(sql);
				
				st.setInt(1, CId);
				st.setString(2,CName);
				st.setString(3, CAddress);
				st.setString(4, CPhone);
				st.setString(5,CAcount);
				st.setString(6, CEmail);
				set=st.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(set <= 0){
			return "失败";
		}else{
			return "成功";
		}
		
		
		
	}
	
	public List queryAll() {
		 List  list = new ArrayList();
		String sql = "select * from Client";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
	
		conn = GetConnection.getConn();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
		} catch (SQLException e1) {
	
			e1.printStackTrace();
		}
		try {
			while (rs.next()) {
	
				Object[] row = new Object[6];
				row[0] = rs.getInt(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				row[5] = rs.getString(6);
				list.add(row);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stat != null) {
					stat.close();
				}
				// if (conn != null) {
				// conn.close();
				// }
			} catch (SQLException e) {
	
				e.printStackTrace();
			}
		}
		return list;
	}
	
  public static void main(String [] args){
	  new Client().queryAll();
  }
}
