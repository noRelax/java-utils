package com.itstar.info.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import com.itstar.info.bean.GoodsBean;
import com.itstar.info.dao.GoodsInfoDao;



public class DoGoodsInfo {
	static GoodsInfoDao goodsdao=new GoodsInfoDao();
	
	
	public void dogoodsinsert(GoodsBean goods){
		if("".equals(goods.getGid())&&"".equals(goods.getGname())&&"".equals(goods.getGsever())){
			JOptionPane.showMessageDialog(null, "商品编号、商品名和供应商都不能为空！");
		}else{
			String str1=goods.getGid();
			String str2=goods.getGname();
			String str3=goods.getGsever();
//			DoGoodsInfo dogoods=new DoGoodsInfo();
			//GoodsInfoDao goodsdao=new GoodsInfoDao();
			goodsdao.getgoodsrs(str1, str2, str3,goods);
			
		}
	}
	
	public void doalter(GoodsBean goods){
		if("".equals(goods.getGname())){
			JOptionPane.showMessageDialog(null, "请选择商品!");
		}else{
			try {
				goodsdao.altergoods(goods);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void dodelete(GoodsBean goods){
		if("".equals(goods.getGname())){
			JOptionPane.showMessageDialog(null, "请选择商品!");
		}else{
			try {
				goodsdao.deletegoods(goods);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public static List docom(){
		
		List<String>list=new GoodsInfoDao().getgoodscom();
		return list;
	}
	
	
	public GoodsBean setgoodscom(String value){
		GoodsBean goods=new GoodsBean();

			goods=goodsdao.setgoodcom(value);
		return goods;
	}
	
	
	public static List dogoodssevercom( String gname){
		
		List<String>list=new GoodsInfoDao().getgoodssevercom(gname);
		return list;
	}
	
	public GoodsBean setgoodssevercom(String gname,String value){
		GoodsBean goods=new GoodsBean();
		//String gname=comvalue;
		System.out.println("-----gname-----"+gname);
		try {
			goods=goodsdao.setgoodssevercom(gname, value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
	
	public static List doallsever(){
		List<String>list=new GoodsInfoDao().getallsever();
		return list;
	}

}
