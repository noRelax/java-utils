/**
 * Created on 2009-8-20
 */
package com.itstar.erp.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import com.itstar.erp.dao.GetConnection;

/**
 * 出库表封装类
 * 
 * @author Zhang Li
 */
public class OutStock {
	private static int OId;
	private static int OGId;
	private static int OGNumber;
	private static String OUserName;
	private static Time OTime;

	private static Connection conn;

	
	
	public static int getOId() {
		return OId;
	}

	public static void setOId(int id) {
		OId = id;
	}

	public static int getOGId() {
		return OGId;
	}

	public static void setOGId(int id) {
		OGId = id;
	}

	public static int getOGNumber() {
		return OGNumber;
	}

	public static void setOGNumber(int number) {
		OGNumber = number;
	}

	public static String getOUserName() {
		return OUserName;
	}

	public static void setOUserName(String userName) {
		OUserName = userName;
	}

	public static Time getOTime() {
		return OTime;
	}

	public static void setOTime(Time time) {
		OTime = time;
	}
	
    /**
     * SQL语句增加
     * 
     * @param 
     * @return String
     * @throws 
     */	
	public static String addOutStock() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "insert into OutStock(OGId,OGNumber,OUserName,OTime) values(?,?,?,getdate())";

		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setInt(1, OGId);
			pdst.setInt(2, OGNumber);
			pdst.setString(3, OUserName);

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
	public static String delOutStock() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "delete from OutStock where OId = ?";

		int set = 0;

		try {
			pdst = conn
					.prepareStatement("select count(*) from Goods where GId = ?");
			pdst.setInt(1, OGId);
			set = pdst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		if (set <= 0) {
			set = 0;
			try {
				pdst = conn.prepareStatement(sql);
				pdst.setInt(1, OId);
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
	
	
	
	
	

}
