package com.itstar.erp.ui.searchswing.tuihuosearch;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.dao.tuihuo.TuiHuoDaoImpl;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.vo.product.ProBean;
import com.itstar.erp.vo.sell.SellBean;

public class TuiHuoLiangSearch {
		
	public static void main(String[] args) {
		
		new TuiHuoLiangSearch().init();
		
	}
	
	public  void init(){
		Object[][] cells;
		String tb="tb_tuihuo_Info";
		ResultSet rs=new GetResultSet().getResultSet(tb);
		String[] colnames={"商品编号","商品名称","退货数量"}; 
		
       Map<Integer,Integer> tuihuoliangmap=new HashMap<Integer,Integer>();
     
		try {
			while(rs.next()){
				int sellid=rs.getInt(2);                  //销售编号
				
				
				
				int thacount=rs.getInt(6);                //退货数量
				
				
				
				SellBean sellbean=new TuiHuoDaoImpl().getSellBean(sellid);
				int proid=sellbean.getProID();                                                  //商品编号
			
				
				ProBean probean=new KuCunDaoImpl().getProBean(proid);                     
				String proname=probean.getProName();                                                //商品名称
			
				
			
				
				
				
				
				
				
				
			   
			    if(!tuihuoliangmap.containsKey(proid)){
			    	tuihuoliangmap.put(proid, thacount);
			    	
			    }else{ 
			    	int d=tuihuoliangmap.get(proid);      //商品编号对应的销售额
 			    	int z=d+thacount;                  //增加后的销售额
 			    	
			    	
			    	
			    	tuihuoliangmap.put(proid, z);
			    }
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
			System.out.println(tuihuoliangmap);

		int size=tuihuoliangmap.size();
		cells=new Object[size][3];
		
		int i=0;
		
		Set<Integer> k=tuihuoliangmap.keySet();
		for(Integer key:k){
			
			cells[i][0]=("准字"+(1000+key)).toString().trim();
			
			System.out.println(cells[i][0]+"----------------");
			
			ProBean bean=new KuCunDaoImpl().getProBean(key);                     
			String name=bean.getProName();    
			
			cells[i][1]=name;
			
			System.out.println(cells[i][1]+"----------------");
			
			cells[i][2]=tuihuoliangmap.get(key).toString().trim();
			
			System.out.println(cells[i][2]+"----------------");
			
			i++;
		}
        JFrame jf=new JFrame("退货数量统计");
		JTable jt=new JTable(cells,colnames);
		jt.setBackground(Color.white);
		jt.setRowHeight (30);
		jt.setEnabled(false);
		JScrollPane js=new JScrollPane(jt);
		jf.add(js);
		jf.setVisible(true);
		jf.setSize(800,600);
		jf.setLocationRelativeTo(null);
      
	}
	
 }
