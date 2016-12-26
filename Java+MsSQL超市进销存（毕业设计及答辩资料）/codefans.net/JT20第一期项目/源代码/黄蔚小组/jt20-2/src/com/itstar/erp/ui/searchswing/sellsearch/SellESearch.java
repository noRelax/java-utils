package com.itstar.erp.ui.searchswing.sellsearch;

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
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.vo.product.ProBean;

public class SellESearch {
		
	public static void main(String[] args) {
		
		init();
	}
	
	public static void init(){
		Object[][] cells;
		String tb="tb_sell_Info";
		ResultSet rs=new GetResultSet().getResultSet(tb);
		String[] colnames={"商品编号","商品名称","销售额"}; 
		
       Map<Integer,Double> sellemap=new HashMap<Integer,Double>();
       Map<Integer,Integer> sellacountmap=new HashMap<Integer,Integer>();
		try {
			while(rs.next()){
				int proid=rs.getInt(3);                      //商品编号
			    int sellacount=rs.getInt(4);                 //销售数量
			    double prosellprice=rs.getDouble(5);        //销售单价
			    double selle=sellacount*prosellprice;       //销售额
			    if(!sellemap.containsKey(proid)){
			    	sellemap.put(proid, selle);
			    	sellacountmap.put(proid, sellacount);                    //销售量
			    }else{ 
			    	double d=sellemap.get(proid);      //商品编号对应的销售额
 			    	double z=d+selle;                  //增加后的销售额
 			    	
			    	int c=sellacountmap.get(proid);    //商品编号对应的销售数量         //销售量
			    	int y=c+sellacount;                //销售后的销售数量
			    	
			    	sellemap.put(proid, z);
			    	sellacountmap.put(proid, y);
			    }
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
			System.out.println(sellemap);
			System.out.println(sellacountmap);

		int size=sellemap.size();
		cells=new Object[size][3];
		
		int i=0;
		
		Set<Integer> k=sellemap.keySet();
		for(Integer key:k){
			
			cells[i][0]=("准字"+(1000+key)).toString().trim();
			
			System.out.println(cells[i][0]+"----------------");
			
			ProBean bean=new KuCunDaoImpl().getProBean(key);                     
			String name=bean.getProName();    
			
			cells[i][1]=name;
			
			System.out.println(cells[i][1]+"----------------");
			
			cells[i][2]=sellemap.get(key).toString().trim();
			
			System.out.println(cells[i][2]+"----------------");
			
			i++;
		}
        JFrame jf=new JFrame("销售额统计");
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
