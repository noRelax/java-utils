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

public class RuKuAcountSearch {
		
	public static void main(String[] args) {
		
		init();
	}
	
	public static void init(){
		Object[][] cells;
		String tb="tb_ruku_Info";
		ResultSet rs=new GetResultSet().getResultSet(tb);
		String[] colnames={"��Ʒ���","��Ʒ����","�������"}; 
		
       Map<Integer,Integer> rukuacountmap=new HashMap<Integer,Integer>();
		try {
			while(rs.next()){
	
			    int rukuacount=rs.getInt(3);                 //�������
			    int proid=rs.getInt(4);                      //��Ʒ���
			    
			    ProBean bean=new KuCunDaoImpl().getProBean(proid);                     
				String name=bean.getProName();                                            //��Ʒ����
				
			    if(!rukuacountmap.containsKey(proid)){
			    	
			    	rukuacountmap.put(proid, rukuacount);                         // �������
			    }else{ 
			    	
 			    	
			    	int c=rukuacountmap.get(proid);    //��Ʒ��Ŷ�Ӧ���������         //�������
			    	int y=c+rukuacount;                //������������
			    	
			    	
			    	rukuacountmap.put(proid, y);
			    }
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
			System.out.println(rukuacountmap);

		int size=rukuacountmap.size();
		cells=new Object[size][3];
		
		int i=0;
		
		Set<Integer> k=rukuacountmap.keySet();
		for(Integer key:k){
			
			cells[i][0]=("׼��"+(1000+key)).toString();
			
			System.out.println(cells[i][0]+"----------------");
			
			ProBean bean=new KuCunDaoImpl().getProBean(key);                     
			String name=bean.getProName();    
			
			cells[i][1]=name;
			
			System.out.println(cells[i][1]+"----------------");
			
			cells[i][2]=rukuacountmap.get(key).toString();
			
			System.out.println(cells[i][2]+"----------------");
			
			i++;
		}
        JFrame jf=new JFrame("�����������С���в�ѯ");
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
