/**
 * 
 */
package com.itstar.query.doquerying;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itstar.query.util.GetConnection;

/**
 * @author Administrator
 *
 */
public class GetGoodTypeDone {
	List<String> list = new ArrayList<String>();
	@SuppressWarnings("unchecked")
	public List getGoodType(){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		try {

			conn = new GetConnection().getConnection();
			String query = "select  distinct Gtype from Goods where Gflag=1";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {

				String type = rs.getString(1);
				list.add(type);
			 

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
		return list;
	}
}
