/**
 * 
 */
package com.itstar.query.doquerying;
import java.util.List;
import java.util.ArrayList;

 
import com.itstar.query.item.SalesDetail;
import com.itstar.query.util.GetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 

/**
 * @author Administrator
 *
 */
public class GetSalesInfor {
@SuppressWarnings("unchecked")
public List getSalesInforAll(){
	List<SalesDetail>salesInforAll=new ArrayList<SalesDetail>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;

	try {

		try {
			conn = new GetConnection().getConnection();
		} catch (InstantiationException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		String query = "select Sale.Sid,Detail.gid,Gname,dprice,dnumber,total,gperson,saledate,Dsquare from  Sale,Detail,Goods  where Sale.Sid=Detail.Sid and detail.gid=goods.gid";
		ps = conn.prepareStatement(query);
	 
		rs = ps.executeQuery();
		while (rs.next()) {
			SalesDetail sale=new SalesDetail();
			sale.setSid(rs.getString(1));
			sale.setGid(rs.getString(2));
			sale.setGname(rs.getString(3));
			sale.setDprice(rs.getString(4));
			sale.setDnumber(rs.getString(5));
			sale.setTotal(rs.getString(6));
			sale.setTperson(rs.getString(7));
			sale.setSaledate(rs.getString(8));
			sale.setDsquare(rs.getString(9));
		
			salesInforAll.add(sale);
	 

		}	
	}
	 catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		}
	return salesInforAll;
	
 
}
@SuppressWarnings({ "unchecked" })
public List getSalesInforID(String str){
	List<SalesDetail>salesInfor=new ArrayList<SalesDetail>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;

	try {

		conn = new GetConnection().getConnection();
		String query = "select Sale.Sid,Detail.gid,Gname,dprice,dnumber,total,gperson,saledate,Dsquare from  Sale,Detail,Goods  where Sale.Sid=Detail.Sid and detail.gid=goods.gid and Sale.sid=?";
		
		ps = conn.prepareStatement(query);
		ps.setString(1, str);
	 
		rs = ps.executeQuery();
		while (rs.next()) {
			SalesDetail sale=new SalesDetail();
			sale.setSid(rs.getString(1));
			sale.setGid(rs.getString(2));
			sale.setGname(rs.getString(3));
			sale.setDprice(rs.getString(4));
			sale.setDnumber(rs.getString(5));
			sale.setTotal(rs.getString(6));
			sale.setTperson(rs.getString(7));
			sale.setSaledate(rs.getString(8));
			sale.setDsquare(rs.getString(9));
		
			salesInfor.add(sale);
	 

		}

		// 关闭连接和声明
		ps.close();
		conn.close();

	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return salesInfor;
}
@SuppressWarnings("unchecked")
public List getSalesInforGuestName(String str){
	List<SalesDetail>salesInfor=new ArrayList<SalesDetail>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;

	try {

		conn = new GetConnection().getConnection();
		String query = "select Sale.Sid,Detail.gid,Gname,dprice,dnumber,total,gperson,saledate,Dsquare from  Sale,Detail,Goods  where Sale.Sid=Detail.Sid and detail.gid=goods.gid and Sale.Gperson=?";
		
		ps = conn.prepareStatement(query);
		ps.setString(1, str);
	 
		rs = ps.executeQuery();
		while (rs.next()) {
			SalesDetail sale=new SalesDetail();
			sale.setSid(rs.getString(1));
			sale.setGid(rs.getString(2));
			sale.setGname(rs.getString(3));
			sale.setDprice(rs.getString(4));
			sale.setDnumber(rs.getString(5));
			sale.setTotal(rs.getString(6));
			sale.setTperson(rs.getString(7));
			sale.setSaledate(rs.getString(8));
			sale.setDsquare(rs.getString(9));
		
			salesInfor.add(sale);
	 

		}

		// 关闭连接和声明
		ps.close();
		conn.close();

	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return salesInfor;
}
@SuppressWarnings("unchecked")
public List getSalesInforDate(String strFrom,String strTo){
	List<SalesDetail>salesInfor=new ArrayList<SalesDetail>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;

	try {

		conn = new GetConnection().getConnection();
		String query = "select Sale.Sid,Detail.gid,Gname,dprice,dnumber,total,gperson,saledate,Dsquare from  Sale,Detail,Goods  where Sale.Sid=Detail.Sid and detail.gid=goods.gid and Sale.Saledate>=? and Sale.Saledate<=?";
		
		ps = conn.prepareStatement(query);
		ps.setString(1, strFrom);
		ps.setString(2, strTo);
	 
		rs = ps.executeQuery();
		while (rs.next()) {
			SalesDetail sale=new SalesDetail();
			sale.setSid(rs.getString(1));
			sale.setGid(rs.getString(2));
			sale.setGname(rs.getString(3));
			sale.setDprice(rs.getString(4));
			sale.setDnumber(rs.getString(5));
			sale.setTotal(rs.getString(6));
			sale.setTperson(rs.getString(7));
			sale.setSaledate(rs.getString(8));
			sale.setDsquare(rs.getString(9));
		
			salesInfor.add(sale);
	 

		}

		// 关闭连接和声明
		ps.close();
		conn.close();

	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return salesInfor;
}
@SuppressWarnings("unchecked")
public List getSalesInforGoodsName(String str){
	List<SalesDetail>salesInfor=new ArrayList<SalesDetail>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;

	try {

		conn = new GetConnection().getConnection();
		String query = "select Sale.Sid,Detail.gid,Gname,dprice,dnumber,total,gperson,saledate,Dsquare from  Sale,Detail,Goods  where Sale.Sid=Detail.Sid and detail.gid=goods.gid and Goods.Gname=?";
		
		ps = conn.prepareStatement(query);
		ps.setString(1, str);
	 
		rs = ps.executeQuery();
		while (rs.next()) {
			SalesDetail sale=new SalesDetail();
			sale.setSid(rs.getString(1));
			sale.setGid(rs.getString(2));
			sale.setGname(rs.getString(3));
			sale.setDprice(rs.getString(4));
			sale.setDnumber(rs.getString(5));
			sale.setTotal(rs.getString(6));
			sale.setTperson(rs.getString(7));
			sale.setSaledate(rs.getString(8));
			sale.setDsquare(rs.getString(9));
		
			salesInfor.add(sale);
	 

		}

		// 关闭连接和声明
		ps.close();
		conn.close();

	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return salesInfor;
}
@SuppressWarnings("unchecked")
public List getSalesInforDate(String str){
	List<SalesDetail>salesInfor=new ArrayList<SalesDetail>();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;

	try {

		conn = new GetConnection().getConnection();
		String query = "select Sale.Sid,Detail.gid,Gname,dprice,dnumber,total,gperson,saledate,Dsquare from  Sale,Detail,Goods  where Sale.Sid=Detail.Sid and detail.gid=goods.gid and Sale.Saledate=?";
		
		ps = conn.prepareStatement(query);
		ps.setString(1,  str);
		 
	 
		rs = ps.executeQuery();
		while (rs.next()) {
			SalesDetail sale=new SalesDetail();
			sale.setSid(rs.getString(1));
			sale.setGid(rs.getString(2));
			sale.setGname(rs.getString(3));
			sale.setDprice(rs.getString(4));
			sale.setDnumber(rs.getString(5));
			sale.setTotal(rs.getString(6));
			sale.setTperson(rs.getString(7));
			sale.setSaledate(rs.getString(8));
			sale.setDsquare(rs.getString(9));
		
			salesInfor.add(sale);
	 

		}

		// 关闭连接和声明
		ps.close();
		conn.close();

	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return salesInfor;
}

}
