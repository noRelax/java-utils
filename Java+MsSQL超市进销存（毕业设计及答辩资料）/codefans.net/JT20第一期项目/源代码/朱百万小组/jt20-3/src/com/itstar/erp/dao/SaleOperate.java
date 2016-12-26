package com.itstar.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public  class SaleOperate implements SaleDao {

	public void addSaleRecord(SaleRecord record) {
		Connection conn=null;
		PreparedStatement ps_1=null;
		PreparedStatement ps_2=null;
        
        try {
		conn=Init.getConnection();
		String str_1="insert into OutStock(OGId,OGnumber,OUserName) values (?,?,?)";
		ps_1=conn.prepareStatement(str_1);
		ps_1.setLong(1, record.OGId);
		ps_1.setLong(2,record.getOGNumber());
		ps_1.setString(3, record.getOUserName());
		ps_1.executeUpdate();
		
		String str_2="insert into record(gid,gname,gnum,salename) values (?,?,?,?)"; 
		ps_2=conn.prepareStatement(str_2);
		ps_2.setLong(1,record.getOGId());
		ps_2.setString(2,record.getGoodName());
		ps_2.setLong(3, record.getOGNumber());
		ps_2.setString(4,record.getOUserName());
		ps_2.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}
	}

	
	
	public void querySaleRecord_2(SaleRecord record) {
		 Connection conn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 try {
		 conn=Init.getConnection();
		 String sql="select id,gid,gname,gnum,salename,time from record where salename=?";
	     ps=conn.prepareStatement(sql);
		 ps.setString(1, record.getOUserName());
	     rs=ps.executeQuery();
	     while(rs.next()){
	    	 record.setOId(rs.getInt("id"));
	    	 record.setOGId(rs.getInt("gid"));
	    	 record.setGoodName(rs.getString("gname"));
	    	 record.setOGNumber(rs.getInt("gnum"));
	    	 record.setOUserName(rs.getString("salename"));
	    	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    	 record.setOTime(sdf.format(rs.getDate("time")));
	    	 }
	     }catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Init.free(rs, ps, conn);
		}
	  }
	public void querySaleRecord_3(SaleRecord record) {
		 Connection conn=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 try {
		 conn=Init.getConnection();
		 String sql="select id,gid,gname,gnum,salename,time from record where time=?";
	     ps=conn.prepareStatement(sql);
		 ps.setString(1, record.getOTime());
	     rs=ps.executeQuery();
	     while(rs.next()){
	    	 record.setOId(rs.getInt("id"));
	    	 record.setOGId(rs.getInt("gid"));
	    	 record.setGoodName(rs.getString("gname"));
	    	 record.setOGNumber(rs.getInt("gnum"));
	    	 record.setOUserName(rs.getString("salename"));
	    	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    	 record.setOTime(sdf.format(rs.getDate("time")));
	    	 }
	     }catch (SQLException e) {
			e.printStackTrace();
		}finally{
			Init.free(rs, ps, conn);
		}
	  }

}
