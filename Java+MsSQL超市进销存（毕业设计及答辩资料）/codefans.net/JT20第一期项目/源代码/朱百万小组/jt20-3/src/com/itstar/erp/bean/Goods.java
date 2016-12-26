
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
 * 商品表封装类
 * 
 * @author Zhang Li
 */
public class Goods {

	public static int GId;
	public static String GName;
	public static String GAddress;
	public static int GMId;
	public static float GInPrice;
	public static float GOutPrice;

	private static Connection conn;

	public static int getGId() {
		return GId;
	}

	public static void setGId(int id) {
		GId = id;
	}

	public static String getGName() {
		return GName;
	}

	public static void setGName(String name) {
		GName = name;
	}

	public static String getGAddress() {
		return GAddress;
	}

	public static void setGAddress(String address) {
		GAddress = address;
	}

	public static int getGMId() {
		return GMId;
	}

	public static void setGMId(int id) {
		GMId = id;
	}

	public static float getGInPrice() {
		return GInPrice;
	}

	public static void setGInPrice(float inPrice) {
		GInPrice = inPrice;
	}

	public static float getGOutPrice() {
		return GOutPrice;
	}

	public static void setGOutPrice(float outPrice) {
		GOutPrice = outPrice;
	}

	
    /**
     * SQL语句增加
     * 
     * @param 
     * @return String
     * @throws 
     */	
	public static String addGoods() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "insert into Goods(GName,GAddress,GMId,GInPrice,GOutPrice,) values(?,?,?,?,?)";

		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setString(1, GName);
			pdst.setString(2, GAddress);
			pdst.setInt(3, GMId);
			pdst.setFloat(4, GInPrice);
			pdst.setFloat(5, GOutPrice);

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
	public static String delGoods() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "delete from Goods where GName = ?";

		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setString(1, GName);
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
		String sql = "select count(*) from Goods";

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
	public static Object[][] selectGoods() {
		Object[][] showAll = new Object[count() + 1][5];
		conn = GetConnection.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from Goods";
		int i = 0;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// System.out.println(rs.getString("CName") +
				// rs.getString("CAddress") + rs.getString("CPhone") +
				// rs.getString("CAcount") + rs.getString("CEmail"));
				showAll[i][0] = rs.getString("GName");
				showAll[i][1] = rs.getString("GAddress");
				showAll[i][2] = rs.getInt("GMId");
				showAll[i][3] = rs.getFloat("GInPrice");
				showAll[i][4] = rs.getFloat("GOutPrice");
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
     * @param String 商品名
     * @return Object[][]
     * @throws 
     */
	public static Object[][] selectGoods(String gName) {
		Object[][] showAll = new Object[count() + 1][5];
		conn = GetConnection.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from Goods where GName = '" + gName + "'";
		int i = 0;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// System.out.println(rs.getString("CName") +
				// rs.getString("CAddress") + rs.getString("CPhone") +
				// rs.getString("CAcount") + rs.getString("CEmail"));
				showAll[i][0] = rs.getString("GName");
				showAll[i][1] = rs.getString("GAddress");
				showAll[i][2] = rs.getInt("GMId");
				showAll[i][3] = rs.getFloat("GInPrice");
				showAll[i][4] = rs.getFloat("GOutPrice");
				i = i + 1;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return showAll;

	}
	
	public List queryAll() {
		List list = new ArrayList();
		String sql = "select * from Goods";
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

	
	public String delet(){
		conn = GetConnection.getConn();
		String sql = "delete Goods  where GId = ?";
		PreparedStatement st = null;
		int set = 0;
		try{
			st = conn.prepareStatement(sql);
			st.setInt(1, GId);
			set = st.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	public static String addManufacturer() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "insert into Goods(GName,GAddress,GMId,GInPrice,GOutPrice) values(?,?,?,?,?)";

		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setString(1, GName);
			pdst.setString(2, GAddress);
			pdst.setInt(3, GMId);
			pdst.setFloat(4, GInPrice);
			pdst.setFloat(5, GOutPrice);

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

	
	
	public static String update(){
		conn = GetConnection.getConn();
		String sql = "UPDATE Goods SET GName=?,GAddress=?,GMId=?,GInPrice=?,GoutPrice=? where GId=?";
		PreparedStatement st = null;
		int set = 0;
		try {
				st = conn.prepareStatement(sql);
				
				st.setString(1, GName);
				st.setString(2,GAddress);
				st.setInt(3, GMId);
				st.setFloat(4, GInPrice);
				st.setFloat(5,GOutPrice);
				st.setInt(6, GId);
				set=st.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if (set <= 0) {
			return "失败";
		} else {
			return "成功";
		}

	}
	
	
	

}
