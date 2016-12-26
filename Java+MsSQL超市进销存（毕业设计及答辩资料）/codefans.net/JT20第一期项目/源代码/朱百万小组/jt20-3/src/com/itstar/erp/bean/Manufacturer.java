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
 * ��Ӧ�̱��װ��
 * 
 * @author Zhang Li
 */
public class Manufacturer {

	private static int MId;
	private static String MName;
	private static String MAddress;
	private static String MPhone;
	private static String MAccount;
	private static String MEmail;

	private static Connection conn;

	public static int getMId() {
		return MId;
	}

	public static void setMId(int id) {
		MId = id;
	}

	public static String getMName() {
		return MName;
	}

	public static void setMName(String name) {
		MName = name;
	}

	public static String getMAddress() {
		return MAddress;
	}

	public static void setMAddress(String address) {
		MAddress = address;
	}

	public static String getMPhone() {
		return MPhone;
	}

	public static void setMPhone(String phone) {
		MPhone = phone;
	}

	public static String getMAccount() {
		return MAccount;
	}

	public static void setMAccount(String account) {
		MAccount = account;
	}

	public static String getMEmail() {
		return MEmail;
	}

	public static void setMEmail(String email) {
		MEmail = email;
	}

	/**
	 * SQL�������
	 * 
	 * @param
	 * @return String
	 * @throws
	 */
	public static String addManufacturer() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "insert into Manufacturer(MName,MAddress,MPhone,MAccount,MEmail) values(?,?,?,?,?)";

		int set = 0;

		try {
			pdst = conn.prepareStatement(sql);
			pdst.setString(1, MName);
			pdst.setString(2, MAddress);
			pdst.setString(3, MPhone);
			pdst.setString(4, MAccount);
			pdst.setString(5, MEmail);

			set = pdst.executeUpdate();

			pdst.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (set <= 0) {
			return "����ͻ�ʧ��";
		} else {
			return "����ͻ��ɹ�";
		}

	}

	/**
	 * SQL���ɾ��
	 * 
	 * @param
	 * @return String
	 * @throws
	 */
	public static String delManufacturer() {

		conn = GetConnection.getConn();
		PreparedStatement pdst = null;
		String sql = "delete from Manufacturer where MName = ?";

		int set = 0;

		try {
			pdst = conn
					.prepareStatement("select count(*) from Goods where GMId = ?");
			pdst.setInt(1, MId);
			set = pdst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (set <= 0) {
			set = 0;
			try {
				pdst = conn.prepareStatement(sql);
				pdst.setString(1, MName);
				set = pdst.executeUpdate();

				pdst.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
			if (set <= 0) {
				return "ɾ���ͻ�ʧ��";
			} else {
				return "ɾ���ͻ��ɹ�";
			}

		} else {
			return "�ù���������Ʒ����������ϵ";
		}

	}

	/**
	 * SQL����ѯ
	 * 
	 * @param
	 * @return int
	 * @throws
	 */
	public static int count() {
		conn = GetConnection.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select count(*) from Manufacturer";

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
	 * SQL����ѯ
	 * 
	 * @param
	 * @return Object[][]
	 * @throws
	 */
	public static Object[][] selectManufacturer() {
		Object[][] showAll = new Object[count() + 1][5];
		conn = GetConnection.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from Manufacturer";
		int i = 0;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// System.out.println(rs.getString("CName") +
				// rs.getString("CAddress") + rs.getString("CPhone") +
				// rs.getString("CAcount") + rs.getString("CEmail"));
				showAll[i][0] = rs.getString("MName");
				showAll[i][1] = rs.getString("MAddress");
				showAll[i][2] = rs.getString("MPhone");
				showAll[i][3] = rs.getString("MAccount");
				showAll[i][4] = rs.getString("MEmail");
				i = i + 1;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return showAll;

	}

	/**
	 * SQL����ѯ
	 * 
	 * @param String
	 *            ��Ӧ����
	 * @return Object[][]
	 * @throws
	 */
	public static Object[][] selectManufacturer(String mName) {
		Object[][] showAll = new Object[count() + 1][5];
		conn = GetConnection.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from Manufacturer where MName = '" + mName + "'";
		int i = 0;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// System.out.println(rs.getString("CName") +
				// rs.getString("CAddress") + rs.getString("CPhone") +
				// rs.getString("CAcount") + rs.getString("CEmail"));
				showAll[i][0] = rs.getString("MName");
				showAll[i][1] = rs.getString("MAddress");
				showAll[i][2] = rs.getString("MPhone");
				showAll[i][3] = rs.getString("MAccount");
				showAll[i][4] = rs.getString("MEmail");
				i = i + 1;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return showAll;

	}

	public List queryAll() {
		List list = new ArrayList();
		String sql = "select * from Manufacturer";
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

	public static String update() {
		conn = GetConnection.getConn();
		String sql = "UPDATE Manufacturer SET MId=?,MName=?,MAddress=?,MPhone=?,MAccount=?,MEmail=? where MId=?";
		PreparedStatement st = null;
		int set = 0;
		try {
			st = conn.prepareStatement(sql);

			st.setInt(1, MId);
			st.setString(2, MName);
			st.setString(3, MAddress);
			st.setString(4, MPhone);
			st.setString(5, MAccount);
			st.setString(6, MEmail);
			set = st.executeUpdate();
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		if (set <= 0) {
			return "ʧ��";
		} else {
			return "�ɹ�";
		}

	}

	public List query() {
		conn = GetConnection.getConn();
		String sql = "select MId from Manufacturer";
		List list = new ArrayList();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				list.add(rs.getInt("MId"));

			}
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return list;
	}
}
