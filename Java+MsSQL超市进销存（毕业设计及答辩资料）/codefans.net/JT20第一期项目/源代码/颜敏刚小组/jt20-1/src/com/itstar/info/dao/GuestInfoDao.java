package com.itstar.info.dao;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.itstar.info.bean.*;
import com.itstar.info.ui.*;

public class GuestInfoDao {
	PreparedStatement prs = null;
	Connection con=JdbcDbConnection.getConnection();;
	public void insert(GuestBean guinfo) throws SQLException{
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("insert into Guest (Gname,Gaddress,Gnumbers,Gpostcode,Gperson,Gphone,Gemail,Gbank)values(?,?,?,?,?,?,?,?)");
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, guinfo.getGname());
			prs.setString(2, guinfo.getGaddress());
			prs.setString(3, guinfo.getGnumber());
			prs.setString(4, guinfo.getGpostcode());
			prs.setString(5, guinfo.getGperson());
			prs.setString(6, guinfo.getGphone());
			prs.setString(7, guinfo.getGemail());
			prs.setString(8, guinfo.getGbank());
//			System.out.println(prs.toString());
			prs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		//rs.close();
		prs.close();
		//con.close();
		}
	}
	
	public void alter(GuestBean guinfo) throws SQLException{
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("update Guest set Gaddress=?,Gnumbers=?,Gpostcode=?,Gperson=?,Gphone=?,Gemail=?,Gbank=? where Gname=?");
		int flag=0;
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, guinfo.getGaddress());
			prs.setString(2, guinfo.getGnumber());
			prs.setString(3, guinfo.getGpostcode());
			prs.setString(4, guinfo.getGperson());
			prs.setString(5, guinfo.getGphone());
			prs.setString(6, guinfo.getGemail());
			prs.setString(7, guinfo.getGbank());
			prs.setString(8, guinfo.getGname());
			System.out.println(prs.toString());
			flag=prs.executeUpdate();
			if(flag!=0){
				JOptionPane.showMessageDialog(null, "ÐÞ¸Ä³É¹¦£¡");
			}else{
				JOptionPane.showMessageDialog(null, "ÐÞ¸ÄÊ§°Ü£¡");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		//rs.close();
		prs.close();
		//con.close();
		}
		
	}
	
	public void delete(GuestBean guinfo) throws SQLException{
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("update Guest set Gflag=? where Gname=?");
		int flag=0;
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "0");
			prs.setString(2, guinfo.getGname());
			System.out.println(prs.toString());
			flag=prs.executeUpdate();
			if(flag!=0){
				JOptionPane.showMessageDialog(null, "É¾³ý³É¹¦£¡");
			}else{
				JOptionPane.showMessageDialog(null, "É¾³ýÊ§°Ü£¡");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		//rs.close();
		prs.close();
		//con.close();
		}
	}
	
	public  List getcom(){
		PreparedStatement prs = null;
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select Gname from Guest where Gflag=?");
		List<String> list=new ArrayList();
		//String [] str=new String [100];
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			System.out.println("prs--------------"+prs.toString());
			rs=prs.executeQuery();
			System.out.println("rs-------------"+rs);
			int i=0;
			
			while(rs.next()){
				list.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		try {
			rs.close();
			prs.close();
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		return list;
	}
	public GuestBean setcombo(String value) throws SQLException{
		GuestBean guest =new GuestBean();
		PreparedStatement prs = null;
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select Gname,Gaddress,Gnumbers,Gpostcode,Gperson,Gphone,Gemail,Gbank from Guest where Gflag=? and Gname=?");
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			prs.setString(2, value);
			System.out.println("prs--------------"+prs.toString());
			rs=prs.executeQuery();
			System.out.println("rs-------------"+rs);	
			while(rs.next()){
				guest.setGname(rs.getString(1));
				guest.setGaddress(rs.getString(2));
				guest.setGnumber(rs.getString(3));
				guest.setGpostcode(rs.getString(4));
				guest.setGperson(rs.getString(5));
				guest.setGphone(rs.getString(6));
				guest.setGemail(rs.getString(7));
				guest.setGbank(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		rs.close();
		prs.close();
		//con.close();
		}
		return guest;
	}
	
	
	public boolean updateFlag(String flag,String gname ){
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("update Guest set Gflag=? where Gname=?");
		int line=0;
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			prs.setString(2,gname);
			line=prs.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("³ö´íÁË");
		}
		if(line!=0)
			return true;
		else
			return false;
	}
	
	public ResultSet getrs(String gname) throws SQLException{
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select Gname,Gflag from Guest where Gname=?");
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, gname);
			rs=prs.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			//rs.close();
			//prs.close();
		}
		return rs;
	}
	
	
}
