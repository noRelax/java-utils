package com.itstar.erp.ui.searchswing.tuikusearch;



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
import com.itstar.erp.dao.tuiku.TuiKuDaoImpl;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.vo.product.ProBean;
import com.itstar.erp.vo.ruku.RuKuBean;

public class TuiKuESearch {
		
	public static void main(String[] args) {
		
		new TuiKuESearch().init();
		
	}
	
	public  void init(){
		Object[][] cells;
		String tb="tb_tuiku_Info";
		ResultSet rs=new GetResultSet().getResultSet(tb);
		String[] colnames={"商品编号","商品名称","退库金额"}; 
		
       Map<Integer,Double> tuikuemap=new HashMap<Integer,Double>();
     
		try {
			while(rs.next()){
				int rukuid=rs.getInt(2);                  //入库编号
				int tkacount=rs.getInt(4);                //退库数量
				RuKuBean rukubean=new TuiKuDaoImpl().getRuKuBean(rukuid);
				int proid=rukubean.getProID();                                                  //商品编号
			
				
				ProBean probean=new KuCunDaoImpl().getProBean(proid);                     
				String proname=probean.getProName();                                                //商品名称
				double proprice=probean.getProPrice();                                           //商品进价
				
				double tuikue=tkacount*proprice;                   //退库金额
				
				
				
				
				
				
				
			   
			    if(!tuikuemap.containsKey(proid)){
			    	tuikuemap.put(proid, tuikue);
			    	
			    }else{ 
			    	double d=tuikuemap.get(proid);      //商品编号对应的退库金额
 			    	double z=d+tuikue;                  //增加后的退库金额
 			    	
			    	
			    	
			    	tuikuemap.put(proid, z);
			    }
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
			System.out.println(tuikuemap);

		int size=tuikuemap.size();
		cells=new Object[size][3];
		
		int i=0;
		
		Set<Integer> k=tuikuemap.keySet();
		for(Integer key:k){
			
			cells[i][0]=("准字"+(1000+key)).toString().trim();
			
			System.out.println(cells[i][0]+"----------------");
			
			ProBean bean=new KuCunDaoImpl().getProBean(key);                     
			String name=bean.getProName();    
			
			cells[i][1]=name;
			
			System.out.println(cells[i][1]+"----------------");
			
			cells[i][2]=tuikuemap.get(key).toString().trim();
			
			System.out.println(cells[i][2]+"----------------");
			
			i++;
		}
        JFrame jf=new JFrame("退库金额统计");
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