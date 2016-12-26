package com.itstar;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Find {
	ResultSet rs = null;

	PreparedStatement pst = null;

	Connection conn;

	public Find() {

	}

	public  boolean lianjie(String user, String password) {

		boolean fff = false;
		try {
			conn = JdbcConnection.getConnection();
			String sql = ("select  * from login where users=? and password=? ");
			pst = conn.prepareStatement(sql);

			pst.setString(1, user);
			pst.setString(2, password);
			rs = pst.executeQuery();

			if (rs.next())
				fff = true;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pst != null)
					pst.close();
				pst = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return fff;
	}

}
