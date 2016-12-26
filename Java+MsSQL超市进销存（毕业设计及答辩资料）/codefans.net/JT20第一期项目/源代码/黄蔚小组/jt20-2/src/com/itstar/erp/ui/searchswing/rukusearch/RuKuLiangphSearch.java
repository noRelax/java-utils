package com.itstar.erp.ui.searchswing.rukusearch;


import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;


import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.product.ProBean;

public class RuKuLiangphSearch {
	
	
	
	
	
		
	
	static Map<Integer,String> ywyidnamemap=new HashMap<Integer,String>();      //ҵ��Ա     ���-----����
	static{
		String table="tb_yewuyuan_info";
		ResultSet rs=new GetResultSet().getResultSet(table);
		try {
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				ywyidnamemap.put(id,name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static Map<Integer,Integer> prosp =new HashMap<Integer,Integer>();         //��Ʒ     ��Ʒ���----��Ӧ�̱��
	static {
		String table="tb_product_info";
		ResultSet rs=new GetResultSet().getResultSet(table);
		try {
			while(rs.next()){
				int proid=rs.getInt(1);
				int spid=rs.getInt(7);
				prosp.put(proid, spid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static Map<Integer,String> spidnamemap=new HashMap<Integer,String>();         //��Ӧ��     ���----����
	static {
		String table="tb_supply_info";
		ResultSet rs=new GetResultSet().getResultSet(table);
		try {
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				spidnamemap.put(id, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new RuKuLiangphSearch().init();
	}
	public void init(){
		
		
		
		Object[][] cells;
		ResultSet rs=null;
		
			rs=new GetRS().getResultSet("select * from tb_ruku_info order by rukuAcount desc ");
			
			
		
		
		String[] colnames={"�������","��Ʒ����","��Ʒ����","�����","��Ӧ������","������","�����","���ʱ��"}; 
		
		
		int i=0;
		

		
		
		double zongyingli=0;
		double zongselljine=0;
		
		try {
			while(rs.next()){
				i++;
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		cells=new Object[i][8];
		int j=0;
		try {
			rs.beforeFirst() ;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			while(rs.next()){
				System.out.println("Ha Ha Ha");
				
				int rukuid=rs.getInt(1);                                                     //�����
				String rukudatetime=rs.getString(2);    
				String stime=new GetTime().format(rukudatetime);                           //���ʱ��   ��----
				String time=new GetTime().toformat(rukudatetime);                         //���ʱ��   ��---
				                           
				int rukuacount=rs.getInt(3);                                                 //�������
				int proid=rs.getInt(4);                                  //��Ʒ���
				int ywyid=rs.getInt(5);                                 //ҵ��Ա���
				String remark=rs.getString(6);                          // ��ⱸע
				
				int id=prosp.get(proid);
				
//				 int gysid = 0;
//				ResultSet yz=new GetRS().getResultSet("select * from tb_product_info where proID="+proid) ;
//				if(yz.next()){
//					gysid=rs.getInt(7);
//				}
//					//��Ӧ�̱��
				String spname=spidnamemap.get(proid);                                            //��Ӧ������                       
				String ywyname=ywyidnamemap.get(ywyid);                                        //������
				

				
				
				ProBean bean=new KuCunDaoImpl().getProBean(proid);                     
				String name=bean.getProName();                                              //��Ʒ����
				double price=bean.getProPrice();                                            // ��Ʒ����
				
				double rukujine=rukuacount*price;                                             //�����
				
				                               
				
//				{"�����","��Ʒ����","��Ʒ����","�������","��Ӧ������","������","�����","���ʱ��"}                
				cells[j][0]=String.valueOf(rukuacount).trim();
				
				cells[j][1]=name;
				cells[j][2]=String.valueOf(price).trim()+"   Ԫ";
				cells[j][3]="RK"+time+"_"+(1000+rukuid);
				cells[j][4]=String.valueOf(spname);
				cells[j][5]=String.valueOf(ywyname);
				cells[j][6]=String.valueOf(rukujine).trim()+"   Ԫ";
				cells[j][7]=String.valueOf(stime).trim();
				
				j++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame jf=new JFrame("�����������С����");
		
		JTable jt=new JTable(cells,colnames);
		
		jt.setBackground(Color.white);
		jt.setRowHeight (30);
		jt.setSelectionForeground (Color.red);//
		jt.setEnabled(false);
		

		JScrollPane js=new JScrollPane(jt);
		jf.add(js);

		jf.setVisible(true);
		jf.setSize(800,600);
		jf.setLocationRelativeTo(null);
		
		
		}
	

}
