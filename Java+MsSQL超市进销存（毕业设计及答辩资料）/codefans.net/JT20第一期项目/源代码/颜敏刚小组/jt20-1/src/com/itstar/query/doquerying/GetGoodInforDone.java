/**
 * 
 */
package com.itstar.query.doquerying;

import com.itstar.query.inter.GetGoodInfor;
import com.itstar.query.item.GoodsQueryItem;

import com.itstar.query.util.GetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Administrator
 * 
 */
public class GetGoodInforDone implements GetGoodInfor {
	private List<GoodsQueryItem> goods = new ArrayList<GoodsQueryItem>();

	@SuppressWarnings("unchecked")
	public List getGoodInfor(String str){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		try {

			conn = new GetConnection().getConnection();
			String query = "select * from Goods where Gname=? and Gflag=1";
			ps = conn.prepareStatement(query);
			ps.setString(1, str);
			rs = ps.executeQuery();
			while (rs.next()) {
				GoodsQueryItem goodItem = new GoodsQueryItem();
				goodItem.setGid(rs.getString("Gid"));
				goodItem.setGname(rs.getString("Gname"));
				goodItem.setGnumber(rs.getString("Gnumber"));
				goodItem.setGproduce(rs.getString("Gproduce"));
				goodItem.setGtype(rs.getString("Gtype"));
				goodItem.setGunit(rs.getString("Gunit"));
				goodItem.setGannotate(rs.getString("Gannotate"));
				goodItem.setGsever(rs.getString("Gsever"));

				goods.add(goodItem);

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
		return goods;
		
	}
	@SuppressWarnings("unchecked")
	public List getAllGoodInfor() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		try {

			conn = new GetConnection().getConnection();
			String query = "select * from Goods where Gflag=1";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				GoodsQueryItem goodItem = new GoodsQueryItem();
				goodItem.setGid(rs.getString("Gid"));
				goodItem.setGname(rs.getString("Gname"));
				goodItem.setGnumber(rs.getString("Gnumber"));
				goodItem.setGproduce(rs.getString("Gproduce"));
				goodItem.setGtype(rs.getString("Gtype"));
				goodItem.setGunit(rs.getString("Gunit"));
				goodItem.setGannotate(rs.getString("Gannotate"));
				goodItem.setGsever(rs.getString("Gsever"));

				goods.add(goodItem);

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
		return goods;

	}
 
}
