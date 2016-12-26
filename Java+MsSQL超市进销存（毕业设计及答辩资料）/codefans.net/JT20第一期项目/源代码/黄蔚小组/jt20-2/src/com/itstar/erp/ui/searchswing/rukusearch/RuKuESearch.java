package com.itstar.erp.ui.searchswing.rukusearch;


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

public class RuKuESearch {
		
	public static void main(String[] args) {
		
		init();
	}
	
	public static void init(){
		Object[][] cells;
		String tb="tb_ruku_Info";
		ResultSet rs=new GetResultSet().getResultSet(tb);
		String[] colnames={"商品编号","商品名称","入库额"}; 
		
       Map<Integer,Double> rukuemap=new HashMap<Integer,Double>();
		try {
			while(rs.next()){
	
			    int rukuacount=rs.getInt(3);                 //入库数量
			    int proid=rs.getInt(4);                      //商品编号
			    
			    ProBean bean=new KuCunDaoImpl().getProBean(proid);                     
				String name=bean.getProName();                                            //商品名称
				double price=bean.getProPrice();                                          // 商品进价
				
				double rukue=rukuacount*price;                                             //入库金额
			    if(!rukuemap.containsKey(proid)){
			    	rukuemap.put(proid, rukue);
			    }else{ 
			    	double d=rukuemap.get(proid);      //商品编号对应的入库额
 			    	double z=d+rukue;                  //增加后的入库额
 			    	
			    	
			    	rukuemap.put(proid, z);
			    }
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
			System.out.println(rukuemap);
		int size=rukuemap.size();
		cells=new Object[size][3];
		
		int i=0;
		
		Set<Integer> k=rukuemap.keySet();
		for(Integer key:k){
			
			cells[i][0]=("准字"+(1000+key)).toString();
			
			System.out.println(cells[i][0]+"----------------");
			
			ProBean bean=new KuCunDaoImpl().getProBean(key);                     
			String name=bean.getProName();    
			
			cells[i][1]=name;
			
			System.out.println(cells[i][1]+"----------------");
			
			cells[i][2]=rukuemap.get(key).toString();
			
			System.out.println(cells[i][2]+"----------------");
			
			i++;
		}
        JFrame jf=new JFrame("入库金额数量大小排行查询");
		JTable jt=new JTable(cells,colnames);
		
		jt.setRowHeight (30);
		jt.setEnabled(false);
		JScrollPane js=new JScrollPane(jt);
		jf.add(js);
		jf.setVisible(true);
		jf.setSize(800,600);
		jf.setLocationRelativeTo(null);
      
	}
	
 }
