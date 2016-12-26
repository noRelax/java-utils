package com.itstar.info.dao;
//download by http://www.codefans.net
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.itstar.info.bean.GoodsBean;
import com.itstar.info.ui.JdbcDbConnection;

public class GoodsInfoDao {
	Connection con=JdbcDbConnection.getConnection();
	PreparedStatement prs = null;
	
	public void goodsinsert(GoodsBean goods) throws SQLException{
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("insert into Goods (Gid,Gname,Gproduce,Gtype,Gunit,Gsever,Gannotate)values(?,?,?,?,?,?,?)");
		int line=0;
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, goods.getGid());
			prs.setString(2, goods.getGname());
			prs.setString(3, goods.getGproduce());
			prs.setString(4, goods.getGtype());
			prs.setString(5, goods.getGunit());
			prs.setString(6, goods.getGsever());
			prs.setString(7, goods.getGannotate());
//			System.out.println(prs.toString());
			line=prs.executeUpdate();
			if(line!=0){
				JOptionPane.showMessageDialog(null, "添加成功！");
			}else{
				JOptionPane.showMessageDialog(null, "添加失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		//rs.close();
		prs.close();
		//con.close();
		}
	}
	
	
	public void altergoods(GoodsBean goods) throws SQLException{
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("update Goods set Gid=?,Gproduce=?,Gtype=?,Gunit=?,Gannotate=? where Gname=? and Gsever=?");
		int flag=0;
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, goods.getGid());
			prs.setString(2, goods.getGproduce());
			prs.setString(3, goods.getGtype());
			prs.setString(4, goods.getGunit());		
			prs.setString(5, goods.getGannotate());
			prs.setString(6, goods.getGname());
			prs.setString(7, goods.getGsever());
			
			System.out.println(prs.toString());
			flag=prs.executeUpdate();
			if(flag!=0){
				JOptionPane.showMessageDialog(null, "修改成功！");
			}else{
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		//rs.close();
		prs.close();
		//con.close();
		}
	}
	
	public void deletegoods(GoodsBean goods) throws SQLException{
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("update Goods set Gflag=? where Gname=? and Gsever=?");
		int flag=0;
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "0");
			prs.setString(2, goods.getGname());
			prs.setString(3, goods.getGsever());
			System.out.println(prs.toString());
			flag=prs.executeUpdate();
			if(flag!=0){
				JOptionPane.showMessageDialog(null, "删除成功！");
			}else{
				JOptionPane.showMessageDialog(null, "删除失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		//rs.close();
		prs.close();
		//con.close();
		}
	}
	
	public List getgoodscom() {
		PreparedStatement prs = null;
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select distinct Gname from Goods where Gflag=?");
		List<String> list=new ArrayList<String>();
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			System.out.println("prs--------------"+prs.toString());
			rs=prs.executeQuery();
			System.out.println("rs-------------"+rs);
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
	
		//con.close();
		}
		return list;
	}
	
	public GoodsBean setgoodcom(String value){
		GoodsBean goods=new GoodsBean();
		PreparedStatement prs = null;
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select Gid,Gname,Gproduce,Gtype,Gunit,Gsever,Gannotate from Goods where Gflag=? and Gname=?");
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			prs.setString(2, value);
			System.out.println("prs--------------"+prs.toString());
			rs=prs.executeQuery();
			System.out.println("rs-------------"+rs);	
			while(rs.next()){
				goods.setGid(rs.getString(1));
				goods.setGname(rs.getString(2));
				goods.setGproduce(rs.getString(3));
				goods.setGtype(rs.getString(4));
				goods.setGunit(rs.getString(5));
				goods.setGsever(rs.getString(6));
				goods.setGannotate(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		try {
			rs.close();
			prs.close();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		//con.close();
		}
		return goods;
	}
	
	
	public List getgoodssevercom(String gname){
		PreparedStatement prs = null;
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select distinct Gsever from Goods where Gflag=? and Gname=?");
		List<String> list=new ArrayList<String>();
		//String [] str=new String [100];
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			prs.setString(2, gname);
			System.out.println("prs--------------"+prs.toString());
			rs=prs.executeQuery();
			System.out.println("rs-------------"+rs);
			int i=0;
			
			while(rs.next()){
				list.add(rs.getString(1));
//				str[i]=rs.getString(1);
//				System.out.println(str[i]);
//				i++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		try {
			rs.close();
			prs.close();
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return list;
	}
	
	public GoodsBean setgoodssevercom( String gname,String value) throws SQLException{
		GoodsBean goods=new GoodsBean();
		PreparedStatement prs = null;
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select Gid,Gname,Gproduce,Gtype,Gunit,Gsever,Gannotate from Goods where Gflag=? and Gname=? and Gsever=?");
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			prs.setString(2, gname);
			prs.setString(3, value);
			System.out.println("prs--------------"+prs.toString());
			rs=prs.executeQuery();
			System.out.println("rs-------------"+rs);	
			while(rs.next()){
				goods.setGid(rs.getString(1));
				goods.setGname(rs.getString(2));
				goods.setGproduce(rs.getString(3));
				goods.setGtype(rs.getString(4));
				goods.setGunit(rs.getString(5));
				goods.setGsever(rs.getString(6));
				goods.setGannotate(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		rs.close();
		prs.close();
		//con.close();
		}
		return goods;
	}
	
	public List getallsever(){
		PreparedStatement prs = null;
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select distinct Sname from Severs where Sflag=?");
		List<String> list=new ArrayList<String>();
		//String [] str=new String [100];
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			rs=prs.executeQuery();
			int i=0;
			
			while(rs.next()){
				list.add(rs.getString(1));
//				str[i]=rs.getString(1);
//				System.out.println(str[i]);
//				i++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		try {
			rs.close();
			prs.close();
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return list;
	}
	
	
	
	public boolean updategoodsFlag(String flag,String gid,String gname,String gsever ){
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("update Guest set Gflag=? where Gname=? and Gsever=?");
		int line=0;
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, "1");
			prs.setString(2,gname);
			prs.setString(3, gsever);
			line=prs.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			System.out.println("出错了");
		}
		if(line!=0)
			return true;
		else
			return false;
	}
	
	
	public void getgoodsrs(String gid, String gname,String gsever,GoodsBean goods) {
		ResultSet rs=null;
		StringBuffer strSQL = new StringBuffer();
		strSQL.append("select Gid,Gflag from Goods where Gname=? and Gsever=?");
		try {
			prs=con.prepareStatement(strSQL.toString());
			prs.setString(1, gname);
			prs.setString(2, gsever);
			rs=prs.executeQuery();
				if(rs.next()){
					if(rs.getString(2).equals("0")){
						this.updategoodsFlag("1",gid,gname,gsever);
						JOptionPane.showMessageDialog(null, "添加成功！");
					}else{
						JOptionPane.showMessageDialog(null, "该商品已经存在!添加失败！");
					}
				}else{
					this.goodsinsert(goods);				
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				prs.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			
		}
	}


}
