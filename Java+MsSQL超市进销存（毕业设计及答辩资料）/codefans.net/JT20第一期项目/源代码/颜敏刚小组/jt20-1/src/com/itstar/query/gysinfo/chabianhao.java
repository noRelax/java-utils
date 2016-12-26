package com.itstar.query.gysinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class chabianhao {
	
	public List queryLike(int sid){

		Connection conn =Jdbc.getConnection();
		PreparedStatement pps = null;
		String sql="select * from severs where sid  like '"+sid+"%' ";
		System.out.println("-------sql-----"+sql.toString());
		ResultSet set = null;
		List<GysBean> list = new ArrayList<GysBean>();
		try {
			pps=conn.prepareStatement(sql);
//			pps.setString(1, sname);                           
			set=pps.executeQuery();
			while(set.next()){
				GysBean gb=new GysBean();
				gb.setSid(set.getInt(1));
				gb.setSname(set.getString(2));
				gb.setSaddress(set.getString(3));
				gb.setSemail(set.getString(4));
				gb.setSphone(set.getString(5));
				gb.setSperson(set.getString(6));
				gb.setSpostCode(set.getString(7));
				gb.setSbank(set.getString(8));
				gb.setSnumber(set.getString(9));
				gb.setSflag(set.getString(10));
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

