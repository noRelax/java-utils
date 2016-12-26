package com.itstar.in;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.util.*;

public class InDao {
	PreparedStatement pps = null;

	Connection con = null;

	public InDao() {
		if (con == null)
			con = JdbcConnection.getConnection();
	}

	// 得到商品的类型
	public List getType() {
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		String strsql = "select distinct gtype from goods";
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
	//得到该商品的类型
	public String getType(String gid){
		ResultSet rs = null;
		String type = null;
		String strsql = "select  gtype from goods where gid=?";
		try {
			pps = con.prepareStatement(strsql);
			pps.setString(1, gid);
			rs = pps.executeQuery();
			while (rs.next()) {
				type=rs.getString(1);
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
		return type;
	}

	// 得到商品的名称
	public List getName(String gtype) {
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		String strsql = "select distinct gname from goods where gtype=?";
		try {
			pps = con.prepareStatement(strsql);
			pps.setString(1, gtype);
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
		String strsql = "select Gsever from Goods where gname=?";
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

	// 查询商品的详细信息
	public List querygoods(String sname) {
		
		ResultSet rs = null;
		List<InBean> list = new ArrayList<InBean>();
//		String strNum="select snumber from severs";
		String strsql = "select gid,gname,gproduce,gtype,gunit,gsever from goods where gname=? and gflag=1";
		try {
			pps = con.prepareStatement(strsql);
			pps.setString(1, sname);
			rs = pps.executeQuery();
			while (rs.next()) {
				InBean sb = new InBean();
				sb.setGid(rs.getString(1));
				sb.setGname(rs.getString(2));
				sb.setGproduce(rs.getString(3));
				sb.setGtype(rs.getString(4));
				sb.setGunit(rs.getString(5));
				sb.setGsever(rs.getString(6));
				list.add(sb);
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
	public String querynumber(String gid) {
		ResultSet rs = null;
		String str = null;
		String strsql = "select  Snumber from storage  where Sgid = ?";
		try {
			pps = con.prepareStatement(strsql);
			pps.setString(1, gid);
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

	// 查询商品编号
	public String querygid(String gname,String ssname) {
		ResultSet rs = null;
		String str = null;
		String strsql = "select  gid from goods where Gname=? and Gsever=?";
		try {
			pps = con.prepareStatement(strsql);
			pps.setString(1, gname);
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

	// 入库单 进行 插入数据 并且更新 库存量
	public boolean insertUpdate(String did, String dtotal, String dsname,
			String ddate, String dsquare, String pgid,String pprice,String pnumber,String dvariety,
			String gname,String sname,String Stype) {
		boolean bool = false;
//		PreparedStatement pps1 = null;
//		PreparedStatement pps2 = null;
		
//		入库
//		Did	入库编号
//		Dtotal	总计金额
//		Dsname	供应商名称
//		Ddate	入库时间
//		Dsquare	结算方式
//
//		入库明细
//		Pid	流水号
//		Pdid	入库编号
//		Pgid	商品编号
//		Pprice	进货单价
//		Pnumber	数量

//		库存表相关		
//		dvariety    现有库存量
		
		try {
			con.setAutoCommit(false);
			
			con.setTransactionIsolation(con.TRANSACTION_READ_COMMITTED);
			String sql = "insert into Depot(Did,Dtotal,Dsname,Ddate,Dsquare) values(?,?,?,?,?)";
			pps = con.prepareStatement(sql);
			pps.setString(1, did);
			pps.setString(2, dtotal);
			pps.setString(3, dsname);
			pps.setString(4, ddate);
			pps.setString(5, dsquare);
			pps.executeUpdate();
			String sql1 = "insert into Purchase(Pdid,Pgid,Pprice,Pnumber) values(?,?,?,?)";
			pps = con.prepareStatement(sql1);
			pps.setString(1, did);
			pps.setString(2, pgid);
			pps.setString(3, pprice);
			pps.setString(4, pnumber);
			pps.executeUpdate();
			if(!dvariety.equals("0")){	
			String sql2 = "update storage set Snumber=Snumber+? Sstotal=Sstotal+? where sgid=?";
			pps = con.prepareStatement(sql2);
			pps.setString(1, pnumber);
			pps.setString(2, dtotal);
			pps.setString(3, pgid);
			pps.executeUpdate();
			}else{
			String sql2 = "insert into storage(Sgname,Ssname,Sstotal,Stype,Snumber,Sgid) values(?,?,?,?,?,?)";	
			pps = con.prepareStatement(sql2);	
			pps.setString(1, gname);	
			pps.setString(2, sname);
			pps.setString(3, dtotal);
			pps.setString(4, Stype);
			pps.setString(5, pnumber);
			pps.setString(6, pgid);	
			pps.executeUpdate();
			}
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
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return bool;
	}

//	public static void main(String args[]){
//		InDao sd=new InDao();
//		System.out.println(sd.querynumber("asdfs"));
//	}
	

}
