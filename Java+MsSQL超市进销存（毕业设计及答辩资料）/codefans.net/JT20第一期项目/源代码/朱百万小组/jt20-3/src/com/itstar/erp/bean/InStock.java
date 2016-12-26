
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
import java.util.ArrayList;
import java.util.List;

import com.itstar.erp.dao.GetConnection;


/**
 * 入库表封装类
 * 
 * @author Zhang Li
 */
public class InStock {
	
	private static int IId;
	private static int IGId;
	private static int IGNumber;
	private static String IName;
	private static Time ITime;
	private static Connection conn;
	
	
	public static int getIId() {
		return IId;
	}
	public static void setIId(int id) {
		IId = id;
	}
	public static int getIGId() {
		return IGId;
	}
	public static void setIGId(int id) {
		IGId = id;
	}
	public static int getIGNumber() {
		return IGNumber;
	}
	public static void setIGNumber(int number) {
		IGNumber = number;
	}
	public static String getIName() {
		return IName;
	}
	public static void setIName(String name) {
		IName = name;
	}
	public static Time getITime() {
		return ITime;
	}
	public static void setITime(Time time) {
		ITime = time;
	}
	
	public static void setNull () {
		IId = 0;
		IGId = 0;
		IGNumber = 0;
		IName = null;
		ITime = null;
	}
	
	
    /**
     * SQL语句增加
     * 
     * @param 
     * @return String
     * @throws 
     */	
	public static String addEntryStock() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "insert into InStock(IGId,IGNumber,IName,ITime) values(?,?,?,getdate())";

		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setInt(1, IGId);
			pdst.setInt(2, IGNumber);
			pdst.setString(3, IName);

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
	public static String delInStock() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "delete from InStock where IId = ?";

		int set = 0;

		try {
			pdst = conn
					.prepareStatement("select count(*) from Goods where GId = ?");
			pdst.setInt(1, IGId);
			set = pdst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		if (set <= 0) {
			set = 0;
			try {
				pdst = conn.prepareStatement(sql);
				pdst.setInt(1, IId);
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
	
	@SuppressWarnings("unchecked")
	public List queryAll() {
		List list = new ArrayList();
		String sql = "select * from InStock";
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
	
				Object[] row = new Object[5];
				row[0] = rs.getInt(1);
				row[1] = rs.getInt(2);
				row[2] = rs.getInt(3);
				row[3] = rs.getString(4);
				row[4] = rs.getTime(5);
				list.add(row);
//				System.out.println(rs.getInt(1));
//				System.out.println(rs.getInt(2));
//				System.out.println(rs.getInt(3));
//				System.out.println(rs.getString(4));
//				System.out.println(rs.getTime(5));
				
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
	
	/**
	查询单个数值
	*
	*/
	public static  List query() {
		conn = GetConnection.getConn();
		String sql = "select GId from Goods";
	    List list = new ArrayList();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				list.add(rs.getInt("GId"));
//				System.out.println(rs.getInt("GId"));
				
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		   return list;
	    }
	
	/**
	查询商品名称
	*
	*/
	
	public List run(int s){
		conn = GetConnection.getConn();
		String sql = "select * from Goods where GId=? ";
		List list = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,s);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
//				list.add(rs.getInt("GId"));
				list.add(rs.getString("GName"));
				
//				System.out.println(rs.getString("GName"));
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;
		
	}
	
	//删除单行数据
	public static String delistk() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "delete from InStock where IGId = ?";
		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setInt(1, IId);
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
	
	
	//修改进货单
	
	public static boolean update(){
		conn = GetConnection.getConn();
		String sql = "UPDATE InStock SET IGId=?,IGNumber=?,IName = ?,ITime=? where IId=?";
		PreparedStatement st = null;
		int set = 0;
		try {
				st = conn.prepareStatement(sql);
				
				st.setInt(1, IGId);
				st.setInt(2,IGNumber);
				st.setString(3,IName );
				st.setTime(4, ITime);
				set=st.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		if(set <= 0){
			return false;
		}else{
			return true;
		}
	
	}
	public static void main(String[] args){
		InStock is = new InStock();
		is.queryAll();
//		System.out.println(is.queryAll());
	}
}
