package com.dao;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itstar.GysBean;
import com.itstar.JdbcConnection;
import com.itstar.SpBean;

public class Daosp {

	Connection con = null;

	PreparedStatement pst = null;

	public Daosp() {
		if (con == null)
			con = JdbcConnection.getConnection();
	}

	// 添加供应商信息的方法
	public boolean insertsp(SpBean sp1) {

		String sql = "insert into spgl values(? ,? ,? ,?,?,?,?,?,?)";
		int count = 0;

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, sp1.getId());
			pst.setString(2, sp1.getSpsname());
			pst.setString(3, sp1.getSpjc());
			pst.setString(4, sp1.getMadein());
			pst.setString(5, sp1.getUnit());
			pst.setString(6, sp1.getGuige());
			pst.setString(7, sp1.getPizhunwenhao());
			pst.setString(8, sp1.getGysmc());
			pst.setString(9, sp1.getBeizhu());
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
	public String[] spcXxlak() {
		ResultSet ret = null;
		String[] s1 = new String[100];
		String sql = "select spname from spgl";
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

	}

	// 下拉框 各值一点就出现
	public SpBean getspInfos(String gysmc) {
		ResultSet ret = null;
		SpBean sp3 = new SpBean();
		String sql = "select * from spgl where spname=?";
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
				sp3.setId(ret.getString(2));
				sp3.setSpsname(ret.getString(3));
				sp3.setSpjc(ret.getString(4));
				sp3.setMadein(ret.getString(5));
				sp3.setUnit(ret.getString(6));
				sp3.setGuige(ret.getString(7));
				sp3.setPizhunwenhao(ret.getString(8));
				sp3.setGysmc(ret.getString(9));
				sp3.setBeizhu(ret.getString(10));
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
		return sp3;

	}

	// 查询供应商重名
	public boolean spnamecz(String s4) {
		boolean bb = false;
		ResultSet ret = null;
		String sql = "select *from spgl where spname=?";

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

	public boolean updatexiugaisp(SpBean sp6) {
		String sql = "update spgl SET id=?,spjianchen=? ,madein=?,unit=?,guige=?,pizhunwenhao=?,gysname=?,beizhu=? where spname=? ";
		int i = 0;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, sp6.getId());
			pst.setString(2, sp6.getSpjc());
			pst.setString(3, sp6.getMadein());
			pst.setString(4, sp6.getUnit());
			pst.setString(5, sp6.getGuige());
			pst.setString(6, sp6.getPizhunwenhao());
			pst.setString(7, sp6.getGysmc());
			pst.setString(8, sp6.getBeizhu());
			pst.setString(9, sp6.getSpsname());

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

	public boolean updatedeletesp(SpBean sp8) {
		String sql = "delete from spgl where spname=? ";
		int i = 0;
		try {
			pst = con.prepareStatement(sql);

			pst.setString(1, sp8.getSpsname());
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

	public List spxlkchaxun() {
		List list2 = new ArrayList();
		String sql = "select spname from spgl";
		ResultSet ret = null;
		try {
			pst = con.prepareStatement(sql);
			ret = pst.executeQuery();
			while (ret.next()) {
				list2.add(ret.getString(1));

			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}try {
			pst.close();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return list2;
	}

}
