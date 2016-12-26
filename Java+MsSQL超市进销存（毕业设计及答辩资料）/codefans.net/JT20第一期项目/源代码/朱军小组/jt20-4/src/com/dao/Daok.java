package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.itstar.JdbcConnection;
import com.itstar.KehuBean;

public class Daok {
	Connection con = null;

	PreparedStatement pst = null;

	ResultSet ret = null;

	public Daok() {
		if (con == null)
			con = JdbcConnection.getConnection();
	}

	// 添加供应商信息的方法
	public boolean insertk(KehuBean k) {

		String sql = "insert into kehugl values(?,? , ? , ? , ?)";
		int count = 0;

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, k.getId());
			pst.setString(2, k.getKallcall());
			pst.setString(3, k.getKaddress());
			pst.setString(4, k.getKpnumber());
			pst.setString(5, k.getKemail());

			count = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		if (count != 0)
			return true;
		else
			return false;
	}

	// 下拉框的查询方法；
	/*public String[] kcXxlak() {
		ResultSet ret = null;
		String[] s1 = new String[100];
		String sql = "select kallcall from kehugl";
		try {
			pst = con.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		try {
			ret = pst.executeQuery();
			for (int i = 0; ret.next(); i++) {

				s1[i] = ret.getString(1);

			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();

		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}

		return s1;

	}*/

	// 下拉框 各值一点就出现
	public KehuBean getkInfos(String gysmc) {
		ResultSet ret = null;
		KehuBean keh2 = new KehuBean();
		String sql = "select * from kehugl where kallcall=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, gysmc);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		try {
			ret = pst.executeQuery();
			while (ret.next()) {
				keh2.setId(ret.getString(2));
				keh2.setKallcall(ret.getString(3));
				keh2.setKaddress(ret.getString(4));
				keh2.setKpnumber(ret.getString(5));
				keh2.setKemail(ret.getString(6));
			}

		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return keh2;

	}

	// 查询供应商重名
	public boolean kehucz(String s4) {
		boolean bb = false;
		ResultSet ret = null;
		String sql = "select *from kehugl where kallcall=?";

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, s4);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		try {
			ret = pst.executeQuery();

			if (ret.next()) {
				bb = true;

			}

		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}

		return bb;

	}

	public boolean updatekehuxiugai(KehuBean kehu3) {
		String sql = "update  kehugl  SET id=?,kaddress=? ,kpnumber=?,kemail=? where kallcall=? ";
		int i = 0;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, kehu3.getId());
			pst.setString(2, kehu3.getKaddress());
			pst.setString(3, kehu3.getKpnumber());
			pst.setString(4, kehu3.getKemail());
			pst.setString(5, kehu3.getKallcall());
			i = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				pst.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		if (i != 0) {
			return true;
		} else
			return false;

	}

	public boolean updatedeletekehu(KehuBean kehu6) {
		String sql = "delete from kehugl where kallcall=? ";
		int i = 0;
		try {
			pst = con.prepareStatement(sql);

			pst.setString(1, kehu6.getKallcall());
			i = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				pst.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		if (i != 0) {
			return true;
		} else
			return false;

	}

	public List kehuxlkchaxun() {

		List<String> list1 = new ArrayList();
		String sql = "select kallcall from kehugl";
		try {
			pst = con.prepareStatement(sql);
			ret = pst.executeQuery();
			while (ret.next()) {
				list1.add(ret.getString(1));

			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				ret.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块e.printStackTrace();
			}
		}
		try {
			pst.close();
			
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list1;
	}

}
