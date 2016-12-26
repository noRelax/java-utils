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

public class DaoGys {
	Connection con = null;
	ResultSet ret = null;
	PreparedStatement pst = null;

	public DaoGys() {
		if (con == null)
			con = JdbcConnection.getConnection();
	}

	// ��ӹ�Ӧ����Ϣ�ķ���
	public boolean insert(GysBean g) {

		String sql = "insert into gysgl values(? , ? , ? , ?)";
		int count = 0;

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, g.getAllcall());
			pst.setString(2, g.getAddress());
			pst.setString(3, g.getPnumber());
			pst.setString(4, g.getEmail());
			count = pst.executeUpdate();

		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		if (count != 0)
			return true;
		else
			return false;
	}

	// ������Ĳ�ѯ������
	/*public String[] cXxlak() {
		ResultSet ret = null;
		String[] s1 = new String[100];
		String sql = "select allcall from gysgl";
		try {
			pst = con.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		}
		try {
			ret = pst.executeQuery();
			for (int i = 0; ret.next(); i++) {

				s1[i] = ret.getString(1);

			}
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();

		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}

		return s1;

	}
*/
	// ������ ��ֵһ��ͳ���
	public GysBean getInfos(String gysmc) {
		ResultSet ret = null;
		GysBean gys = new GysBean();
		String sql = "select * from gysgl where allcall=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, gysmc);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		try {
			ret = pst.executeQuery();
			while (ret.next()) {
				gys.setAllcall(ret.getString(2));
				gys.setAddress(ret.getString(3));
				gys.setPnumber(ret.getString(4));
				gys.setEmail(ret.getString(5));
			}

		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		return gys;

	}

	// ��ѯ��Ӧ������
	public boolean gyscz(String s4) {
		boolean bb = false;
		ResultSet ret = null;
		String sql = "select *from gysgl where allcall=?";

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, s4);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		try {
			ret = pst.executeQuery();

			if (ret.next()) {
				bb = true;

			}

		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}

		return bb;

	}

	public boolean updatexiugai(GysBean gys6) {
		String sql = "update gysgl SET address=?,pnumber=?,email=? where allcall=? ";
		int i = 0;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, gys6.getAddress());
			pst.setString(2, gys6.getPnumber());
			pst.setString(3, gys6.getEmail());
			pst.setString(4, gys6.getAllcall());
			i = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				pst.close();
			} catch (SQLException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		if (i != 0) {
			return true;
		} else
			return false;

	}

	public boolean updatedelete(GysBean gys7) {
		String sql = "delete from gysgl where allcall=? ";
		int i = 0;
		try {
			pst = con.prepareStatement(sql);

			pst.setString(1, gys7.getAllcall());
			i = pst.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				pst.close();
			} catch (SQLException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		if (i != 0) {
			return true;
		} else
			return false;

	}

	public List gysxlkchaxun() {
		
		List<String> list = new ArrayList();
		String sql = "select allcall from gysgl";
		try {
			pst = con.prepareStatement(sql);
			ret = pst.executeQuery();
			while (ret.next()) {
				list.add(ret.getString(1));

		} }catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}finally{
			try {
				ret.close();
			} catch (SQLException e) {
				// TODO �Զ����� catch ��e.printStackTrace();
			}
		}try {
			pst.close();
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return list;

	}

}
