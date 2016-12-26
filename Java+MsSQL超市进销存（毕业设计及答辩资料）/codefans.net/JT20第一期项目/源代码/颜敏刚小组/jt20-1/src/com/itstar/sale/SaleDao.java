package com.itstar.sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.util.*;

public class SaleDao {
	PreparedStatement pps = null;

	Connection con = null;

	public SaleDao() {
		if (con == null)
			con = JdbcConnection.getConnection();
	}

	// 得到商品的类型
	public List getType() {
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		String strsql = "select distinct stype from storage";
		try {
			pps = con.prepareStatement(strsql);
			rs = pps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}

		}
		return list;
	}

	// 得到商品的名称
	public List getName(String stype) {
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		String strsql = "select distinct sgname from storage where stype=?";
		try {
			pps = con.prepareStatement(strsql);
			pps.setString(1, stype);
			rs = pps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}

		}

		return list;
	}

	// 查询供应商的名称
	public List querySever(String gname) {
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		String strsql = "select Ssname from storage where Sgname=?";
		try {
			pps = con.prepareStatement(strsql);
			pps.setString(1, gname);
			rs = pps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}

		}
		return list;
	}

	// 查询供应商的详细信息
	public ServerBean queryserver(String sname) {
		ServerBean sb = new ServerBean();
		ResultSet rs = null;
		List<ServerBean> list = new ArrayList<ServerBean>();
		String strsql = "select sname,saddress,semail,sphone,sperson,spostcode,sbank,snumber from severs where sname=?";
		try {
			pps = con.prepareStatement(strsql);
			pps.setString(1, sname);
			rs = pps.executeQuery();
			while (rs.next()) {
				sb.setSname(rs.getString(1));
				sb.setSaddress(rs.getString(2));
				sb.setSemail(rs.getString(3));
				sb.setSphone(rs.getString(4));
				sb.setSperson(rs.getString(5));
				sb.setSpostcode(rs.getString(6));
				sb.setSbank(rs.getString(7));
				sb.setSnumber(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}

		}
		return sb;

	}

	// 查询客户名称
	public List queryGuest() {
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		String strsql = "select gname from guest";
		try {
			pps = con.prepareStatement(strsql);
			rs = pps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}

		}
		return list;
	}

	// 查询存库量
	public String[] querynumber(String sgname, String ssname) {
		ResultSet rs = null;
		String[] str = new String[2];
		String strsql = "select  Snumber,Sstotal from storage where sgname=? and ssname=?";
		try {
			pps = con.prepareStatement(strsql);
			pps.setString(1, sgname);
			pps.setString(2, ssname);
			rs = pps.executeQuery();
			while (rs.next()) {
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}

		}

		return str;
	}

	// 查询库存里的商品编号
	public String querySid(String sgname, String ssname) {
		ResultSet rs = null;
		String str = null;
		String strsql = "select  Sgid from storage where sgname=? and ssname=?";
		try {
			pps = con.prepareStatement(strsql);
			pps.setString(1, sgname);
			pps.setString(2, ssname);
			rs = pps.executeQuery();
			while (rs.next()) {
				str = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}

		}

		return str;
	}

	// 销售单 进行 插入数据 并且更新 库存量
	public boolean 
	insertUpdate(String sid, String guestname, String saledate,
			String dsquare, String gid, String saleprice, String salenumber,
			long storagenum) {
		boolean bool = false;
		double price = new Double(saleprice);
		long num = new Long(salenumber);
		String total = new Double(price * num).toString();
		String snumber = new Long(storagenum - num).toString();
		PreparedStatement pps1 = null;
		PreparedStatement pps2 = null;
		try {
			con.setAutoCommit(false);
			con.setTransactionIsolation(con.TRANSACTION_READ_COMMITTED);
			String sql = "insert into Sale(Sid,total,Gperson,Saledate,Dsquare) values(?,?,?,?,?)";
			pps = con.prepareStatement(sql);
			pps.setString(1, sid);
			pps.setString(2, total);
			pps.setString(3, guestname);
			pps.setString(4, saledate);
			pps.setString(5, dsquare);
			pps.executeUpdate();
			String sql1 = "insert into Detail(Sid,Gid,Dprice,Dnumber) values(?,?,?,?)";
			pps1 = con.prepareStatement(sql1);
			pps1.setString(1, sid);
			pps1.setString(2, gid);
			pps1.setString(3, saleprice);
			pps1.setString(4, salenumber);
			pps1.executeUpdate();
			String sql2 = "update storage set Snumber=? where Sgid=?";
			pps2 = con.prepareStatement(sql2);
			pps2.setString(1, snumber);
			pps2.setString(2, gid);
			pps2.executeUpdate();
			con.commit();
			bool = true;
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(true);
				pps.close();
				pps1.close();
				pps2.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return bool;
	}

	// public void transactionTest(){
	// try {
	// con.setAutoCommit(false);
	// pps=con.prepareStatement("insert xxx");
	//				
	// PreparedStatement pps2=con.prepareStatement("update xxx");
	//				
	//				
	// pps.executeUpdate();
	// pps2.executeUpdate();
	//				
	// con.commit();
	//				
	//				
	// } catch (SQLException e) {
	// //con.rollback();
	// e.printStackTrace();
	// }finally{
	// try {
	// con.setAutoCommit(true);
	// pps.close();
	// } catch (SQLException e) {
	// // TODO 自动生成 catch 块
	// e.printStackTrace();
	// }
	//				
	// }
	// }
	// public static void main(String args[]){
	// SaleDao sd=new SaleDao();
	// List<String> list=sd.getType();
	// for(String st:list){
	// System.out.println(st);
	// }

	// Date js=new Date();
	// java.sql.Date date= new java.sql.Date(js.getTime());
	// SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
	//		
	// System.out.println(sd.format(date));
	// }

}
