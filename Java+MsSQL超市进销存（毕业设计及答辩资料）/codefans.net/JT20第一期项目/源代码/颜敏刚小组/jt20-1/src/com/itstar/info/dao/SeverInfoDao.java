package com.itstar.info.dao;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.itstar.info.bean.SeversBean;
import com.itstar.info.ui.JdbcDbConnection;

public class SeverInfoDao {
	Connection con=JdbcDbConnection.getConnection();
	PreparedStatement prs=null;
	ResultSet rs=null;
	public void insert(SeversBean sever) throws SQLException{
		StringBuffer strSQL=new StringBuffer();
		strSQL.append("insert into Severs (Sname,Saddress,Snumber,SpostCode,Sperson,Sphone,Semail,Sbank) values(?,?,?,?,?,?,?,?)");
		try{
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1,sever.getSname());
			prs.setString(2, sever.getSaddress());
			prs.setString(3,sever.getSnumber());
			prs.setString(4,sever.getSpostCode());
			prs.setString(5,sever.getSperson());
			prs.setString(6,sever.getSphone());
			prs.setString(7, sever.getSemail());
			prs.setString(8,sever.getSbank());
			prs.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			prs.close();
		}
	}
	
	public List query(String sname) throws SQLException{
		List list=new ArrayList();
		ResultSet rs=null;
		StringBuffer strSQL=new StringBuffer();
		strSQL.append("select Sname from Severs where Sname=?");
		try{
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1,sname);
			rs=prs.executeQuery();
			while(rs.next()){
				SeversBean vo=new SeversBean();
				vo.setSname(rs.getString(1));
				list.add(vo);
				System.out.println("--------LIST-----"+list.size());
			}
			rs.close();
			prs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			rs.close();
			prs.close();
		}
		return list;
	}
	
	public void altersever(SeversBean sever) throws SQLException{
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("update Severs set Saddress=?,Snumber=?,Spostcode=?,Sperson=?,Sphone=?,Semail=?,Sbank=? where Sname=?");
		int flag=0;
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, sever.getSaddress());
			prs.setString(2, sever.getSnumber());
			prs.setString(3, sever.getSpostCode());
			prs.setString(4, sever.getSperson());
			prs.setString(5, sever.getSphone());
			prs.setString(6, sever.getSemail());
			prs.setString(7, sever.getSbank());
			prs.setString(8, sever.getSname());
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
	
	public void deletesever(SeversBean sever) throws SQLException{
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("update Severs set Sflag=? where Sname=?");
		PreparedStatement pps=null;
		int flag=0;
		try {
			con.setAutoCommit(false);
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "0");
			prs.setString(2, sever.getSname());
			String sql="update Goods set gflag=0 where Gsever=?";
			pps=con.prepareStatement(sql);
			pps.setString(1, sever.getSname());
			pps.executeUpdate();
			flag=prs.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		con.setAutoCommit(true);
		pps.close();
		prs.close();
		}
		if(flag!=0){
			JOptionPane.showMessageDialog(null, "É¾³ý³É¹¦£¡");
		}else{
			JOptionPane.showMessageDialog(null, "É¾³ýÊ§°Ü£¡");
		}
	}
	
	public List getsevercom(){
		PreparedStatement prs = null;
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select Sname from Severs where Sflag=?");
		List<String> list=new ArrayList();
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			rs=prs.executeQuery();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		return list;
	}
	
	public SeversBean setsevercom(String value) throws SQLException{
		SeversBean sever=new SeversBean();
		PreparedStatement prs = null;
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select Sname,Saddress,Snumber,Spostcode,Sperson,Sphone,Semail,Sbank from Severs where Sflag=? and Sname=?");
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			prs.setString(2, value);
			rs=prs.executeQuery();
			while(rs.next()){
				sever.setSname(rs.getString(1));
				sever.setSaddress(rs.getString(2));
				sever.setSnumber(rs.getString(3));
				sever.setSpostCode(rs.getString(4));
				sever.setSperson(rs.getString(5));
				sever.setSphone(rs.getString(6));
				sever.setSemail(rs.getString(7));
				sever.setSbank(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		rs.close();
		prs.close();
		}
		return sever;
	}
	
	
	public boolean updateFlag(String flag,String sname ){
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("update Severs set Sflag=? where Sname=?");
		int line=0;
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			prs.setString(2,sname);
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
	
	public ResultSet getrs(String sname) throws SQLException{
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select Sname,Sflag from Severs where Sname=?");
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, sname);
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
