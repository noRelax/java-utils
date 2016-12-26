/**
 * Created on 2009-8-20
 */
package com.itstar.erp.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import com.itstar.erp.dao.GetConnection;

/**
 * 库存表封装类
 * 
 * @author Zhang Li
 */
public class Stock {
	
	
	private static int SGId;
	private static float SSum;
	private static Time STime;
	
	private static Connection conn;

	
	
	public static int getSGId() {
		return SGId;
	}

	public static void setSGId(int id) {
		SGId = id;
	}

	public static float getSSum() {
		return SSum;
	}

	public static void setSSum(float sum) {
		SSum = sum;
	}

	public static Time getSTime() {
		return STime;
	}

	public static void setSTime(Time time) {
		STime = time;
	}
	
    /**
     * SQL语句增加
     * 
     * @param 
     * @return String
     * @throws 
     */	
	public static String addStock() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "insert into Stock(SGId,SSum,STime) values(?,?,getdate())";

		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setInt(1, SGId);
			pdst.setFloat(2, SSum);

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
	public static String delStock() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "delete from Stock where SGId = ?";

		int set = 0;

		try {
			pdst = conn
					.prepareStatement("select count(*) from Goods where GId = ?");
			pdst.setInt(1, SGId);
			set = pdst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		if (set <= 0) {
			set = 0;
			try {
				pdst = conn.prepareStatement(sql);
				pdst.setInt(1, SGId);
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

		} else {
			return "该供货商与商品存在依赖关系";
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
		String sql = "select count(*) from Stock";

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
	public static Object[][] selectStock() {
		Object[][] showAll = new Object[count() + 1][3];
		conn = GetConnection.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from Stock";
		int i = 0;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
//				System.out.println(rs.getString("CName") + rs.getString("CAddress") + rs.getString("CPhone") + rs.getString("CAcount") + rs.getString("CEmail"));
				showAll[i][0] = rs.getInt("SGId");
				showAll[i][1] = rs.getString("SSum");
				showAll[i][2] = rs.getString("STime");

				i = i + 1;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return showAll;

	}
	
    /**
     * SQL语句查询
     * 
     * @param String 商品号
     * @return Object[][]
     * @throws 
     */
	public static Object[][] selectStock(String sGId) {
		Object[][] showAll = new Object[count() + 1][3];
		conn = GetConnection.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from Stock where SGId = '" + sGId + "'";
		int i = 0;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
//				System.out.println(rs.getString("CName") + rs.getString("CAddress") + rs.getString("CPhone") + rs.getString("CAcount") + rs.getString("CEmail"));
				showAll[i][0] = rs.getInt("SGId");
				showAll[i][1] = rs.getString("SSum");
				showAll[i][2] = rs.getString("STime");

				i = i + 1;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return showAll;

	}
	
	
	
	
}
