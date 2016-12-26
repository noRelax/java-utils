package com.itstar.query.ingood;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class chaxun {
	
	public List queryLike(String gname){

		Connection conn =Jdbc.getConnection();
		PreparedStatement pps = null;
		String sql="select Did,Pgid,Gname,Gtype,Pprice,Pnumber,Gunit,Dtotal,Dsname,Ddate,Dsquare from Goods,Depot,Purchase where Depot.did=Purchase.pdid and Purchase.Pgid=Goods.Gid and  gname  like '%"+gname+"%' ";
		System.out.println("-------sql-----"+sql.toString());
		ResultSet set = null;
		List<Gysbean> list = new ArrayList<Gysbean>();
		try {
			pps=conn.prepareStatement(sql);
	//	pps.setString(1, sname);                           
			set=pps.executeQuery();
			while(set.next()){
				Gysbean gb=new Gysbean();
				gb.setDid(set.getString(1));
				gb.setPgid(set.getString(2));
				gb.setGname(set.getString(3));
				gb.setGtype(set.getString(4));
				gb.setPprice(set.getString(5));
				gb.setPnumber(set.getString(6));
				gb.setGunit(set.getString(7));
				gb.setDtotal(set.getString(8));
				gb.setDsname(set.getString(9));
				gb.setDdate(set.getString(10));
				gb.setDsquare(set.getString(11));
				list.add(gb);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}finally{
			try {
				set.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			
		}	
		return list;	
		
	}
	
	public List query(String Pgid){

		Connection conn =Jdbc.getConnection();
		PreparedStatement pps = null;
		String sql="select Did,Pgid,Gname,Gtype,Pprice,Pnumber,Gunit,Dtotal,Dsname,Ddate,Dsquare from Goods,Depot,Purchase where Depot.did=Purchase.pdid and Purchase.Pgid=Goods.Gid  and Pgid  like '%"+Pgid+"%' ";

		
		System.out.println("-------sql-----"+sql.toString());
		ResultSet set = null;
		List<Gysbean> list = new ArrayList<Gysbean>();
		try {
			pps=conn.prepareStatement(sql);
	//	pps.setString(1, sname);                           
			set=pps.executeQuery();
			while(set.next()){
				Gysbean gb=new Gysbean();
				gb.setDid(set.getString(1));
				gb.setPgid(set.getString(2));
				gb.setGname(set.getString(3));
				gb.setGtype(set.getString(4));
				gb.setPprice(set.getString(5));
				gb.setPnumber(set.getString(6));
				gb.setGunit(set.getString(7));
				gb.setDtotal(set.getString(8));
				gb.setDsname(set.getString(9));
				gb.setDdate(set.getString(10));
				gb.setDsquare(set.getString(11));
				list.add(gb);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}finally{
			try {
				set.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			
		}	
		return list;	
		}
	public List querytype(String Gtype){

		Connection conn =Jdbc.getConnection();
		PreparedStatement pps = null;
		String sql="select Did,Pgid,Gname,Gtype,Pprice,Pnumber,Gunit,Dtotal,Dsname,Ddate,Dsquare from Goods,Depot,Purchase where Depot.did=Purchase.pdid and Purchase.Pgid=Goods.Gid and Gtype  like '%"+Gtype+"%' ";
		System.out.println("-------sql-----"+sql.toString());
		ResultSet set = null;
		List<Gysbean> list = new ArrayList<Gysbean>();
		try {
			pps=conn.prepareStatement(sql);
	//	pps.setString(1, sname);                           
			set=pps.executeQuery();
			while(set.next()){
				Gysbean gb=new Gysbean();
				gb.setDid(set.getString(1));
				gb.setPgid(set.getString(2));
				gb.setGname(set.getString(3));
				gb.setGtype(set.getString(4));
				gb.setPprice(set.getString(5));
				gb.setPnumber(set.getString(6));
				gb.setGunit(set.getString(7));
				gb.setDtotal(set.getString(8));
				gb.setDsname(set.getString(9));
				gb.setDdate(set.getString(10));
				gb.setDsquare(set.getString(11));
				list.add(gb);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}finally{
			try {
				set.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			
		}	
		return list;	
		
	}
	public List queryall(){

		Connection conn =Jdbc.getConnection();
		PreparedStatement pps = null;
		String sql="select Did,Pgid,Gname,Gtype,Pprice,Pnumber,Gunit,Dtotal,Dsname,Ddate,Dsquare from Goods,Depot,Purchase where Depot.did=Purchase.pdid and Purchase.Pgid=Goods.Gid";
		System.out.println("-------sql-----"+sql.toString());
		ResultSet set = null;
		List<Gysbean> list = new ArrayList<Gysbean>();
		try {
			pps=conn.prepareStatement(sql);
	//	pps.setString(1, sname);                           
			set=pps.executeQuery();
			while(set.next()){
				Gysbean gb=new Gysbean();
				gb.setDid(set.getString(1));
				gb.setPgid(set.getString(2));
				gb.setGname(set.getString(3));
				gb.setGtype(set.getString(4));
				gb.setPprice(set.getString(5));
				gb.setPnumber(set.getString(6));
				gb.setGunit(set.getString(7));
				gb.setDtotal(set.getString(8));
				gb.setDsname(set.getString(9));
				gb.setDdate(set.getString(10));
				gb.setDsquare(set.getString(11));
				list.add(gb);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}finally{
			try {
				set.close();
				pps.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			
		}	
		return list;	
		
	}
}
