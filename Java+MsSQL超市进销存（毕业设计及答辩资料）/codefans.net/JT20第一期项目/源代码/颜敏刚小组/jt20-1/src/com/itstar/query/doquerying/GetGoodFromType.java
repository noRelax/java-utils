/**
 * 
 */
package com.itstar.query.doquerying;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itstar.query.util.GetConnection;

import com.itstar.query.item.GoodsQueryItem;
/**
 * @author Administrator
 *
 */
public class GetGoodFromType {
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	 
@SuppressWarnings("unchecked")
public List getGoodFromType(String str){
	List<GoodsQueryItem>goods=new ArrayList<GoodsQueryItem>();
	try {
		

		conn = new GetConnection().getConnection();
		String query = "select * from Goods where Gtype=? and Gflag=1";
		ps = conn.prepareStatement(query);
		ps.setString(1, str);
		rs = ps.executeQuery();
		while (rs.next()) {
			GoodsQueryItem good=new GoodsQueryItem();
			good.setGid(rs.getString(1));
			good.setGname(rs.getString(2));
			good.setGnumber(rs.getString(3));
			good.setGproduce(rs.getString(4));
			good.setGtype(rs.getString(5));
			good.setGunit(rs.getString(6));
			good.setGannotate(rs.getString(7));
			good.setGsever(rs.getString(8));
			goods.add(good); 

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
