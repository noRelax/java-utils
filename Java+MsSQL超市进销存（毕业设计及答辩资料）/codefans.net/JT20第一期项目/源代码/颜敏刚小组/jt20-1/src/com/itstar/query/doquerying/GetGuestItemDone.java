package com.itstar.query.doquerying;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itstar.query.util.GetConnection;

public class GetGuestItemDone {
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	Object[][] object = new Object[1][9];

	public Object[][] getGuestItemDone(String str) {
		try {
			

			conn = new GetConnection().getConnection();
			String query = "select * from Guest where Gname=? and Gflag=1";
			ps = conn.prepareStatement(query);
			ps.setString(1, str);
			rs = ps.executeQuery();
			while (rs.next()) {
				object[0][0] = rs.getInt(1);
				object[0][1] = rs.getString(2);
				object[0][2] = rs.getString(3);
				object[0][3] = rs.getString(4);
				object[0][4] = rs.getString(5);
				object[0][5] = rs.getString(6);
				object[0][6] = rs.getString(7);
				object[0][7] = rs.getString(8);
				object[0][8] = rs.getString(9);

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
		return object;
	}
}
