package com.itstar.erp.dao.tuihuo;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itstar.erp.util.DBConnection;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.vo.sell.SellBean;
import com.itstar.erp.vo.tuihuo.TuiHuoBean;
public class TuiHuoDaoImpl implements TuiHuoDaoI{

	Connection conn=new DBConnection().getConnection();
	PreparedStatement p;
	Statement s;
	ResultSet rs;

	public List getStringList(String field, String table) {
		List list=new ArrayList();
		ResultSet rs=new GetRS().getResultSet("select "+field+" from "+table );;
		String s;
		try {
			while(rs.next()){
				s=rs.getString(1);
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List getIntList(String field, String table) {
		List list=new ArrayList();
		ResultSet rs=new GetRS().getResultSet("select "+field+" from "+table );;
		int s;
		try {
			while(rs.next()){
				s=rs.getInt(1);
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	public SellBean getSellBean(int sellid) {
		SellBean bean=new SellBean();
		ResultSet rs=new GetRS().getResultSet("select * from tb_sell_info where sellID="+sellid);
		try {
			if(rs.next()){
				bean.setSellID(rs.getInt(1));
				bean.setSellDateTime(rs.getString(2));
				bean.setProID(rs.getInt(3));
				bean.setSellAcount(rs.getInt(4));
				bean.setSellPrice(rs.getDouble(5));
				bean.setYwyID(rs.getInt(6));
				bean.setSellRemark(rs.getString(7));
				bean.setKehuID(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	
	public String getkehuName(int kehuid) {
		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select kehuName from tb_kehu_info where kehuID="+kehuid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String kehuName= null;
		try {
			if(rs.next()){
				kehuName=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kehuName;
	}

	
	public String getywyName(int ywyid) {
		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select ywyName from tb_yewuyuan_info where ywyID="+ywyid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String ywyName= null;
		try {
			if(rs.next()){
				ywyName=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ywyName;
	}
	
	public void insert(TuiHuoBean bean){
		try {
			p=conn.prepareStatement("insert into tb_tuihuo_info values(?,?,?,?,?)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			p.setInt(1, bean.getSellID());
			p.setString(2, bean.getThTime());
			p.setString(3,bean.getThRemark());
			p.setInt(4, bean.getYwyID());
			p.setInt(5, bean.getThAcount());
			p.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public int getkucun(int proid) {
		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select kucunAcount from tb_kucun_info where proID="+proid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int kucun= 0;
		try {
			if(rs.next()){
				kucun=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kucun;
	}
	
	public int getywyID(String ywyname) {
		try {
			s=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=s.executeQuery("select ywyID from tb_yewuyuan_info where ywyName='"+ywyname+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int ywyid= 0;
		try {
			if(rs.next()){
				ywyid=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ywyid;
	}
	
	}
	



