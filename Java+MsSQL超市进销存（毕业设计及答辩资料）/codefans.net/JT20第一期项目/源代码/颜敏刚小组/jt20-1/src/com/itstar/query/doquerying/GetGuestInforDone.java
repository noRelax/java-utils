package com.itstar.query.doquerying;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; //import java.util.ArrayList;

import com.itstar.query.inter.GetGuestInfor;
import com.itstar.query.util.GetConnection;

import com.itstar.query.item.GuestQueryItem;

public class GetGuestInforDone implements GetGuestInfor {

	private List<GuestQueryItem> guest=new ArrayList<GuestQueryItem>();

	@SuppressWarnings("unchecked")
	public List getGuestInfor() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		try {

			conn = new GetConnection().getConnection();
			String query = "select * from Guest where Gflag=1";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				GuestQueryItem guestItem=new GuestQueryItem();
				guestItem.setGid(rs.getInt("Gid"));
				guestItem.setGname(rs.getString("Gname"));
				guestItem.setGaddress(rs.getString("Gaddress"));
				guestItem.setGemail(rs.getString("Gemail"));
				guestItem.setGphone(rs.getString("Gphone"));
				guestItem.setGperson(rs.getString("Gperson"));
				guestItem.setGpostCode(rs.getString("GpostCode"));
				guestItem.setGbank(rs.getString("Gbank"));
				guestItem.setGnumber(rs.getString("Gnumbers"));
				guestItem.setGflag(rs.getString("Gflag"));
				guest.add(guestItem);
				 
				 
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
		return guest;
		 

	}
}
