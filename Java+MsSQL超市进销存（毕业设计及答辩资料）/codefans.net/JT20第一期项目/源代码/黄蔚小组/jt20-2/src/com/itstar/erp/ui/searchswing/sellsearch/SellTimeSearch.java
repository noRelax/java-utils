package com.itstar.erp.ui.searchswing.sellsearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.product.ProBean;

import java.sql.*;

public class SellTimeSearch {
	
	
	
	
	
		
	
	static Map<Integer,String> ywyidnamemap=new HashMap<Integer,String>();      //业务员     名称-----编号
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
	static Map<Integer,String> kehuidnamemap=new HashMap<Integer,String>();         //客户     编号----名称
	static {
		String table="tb_kehu_info";
		ResultSet rs=new GetResultSet().getResultSet(table);
		try {
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				kehuidnamemap.put(id, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args){
		
//		new SellTimeSearch().init(String time1,String time2);
	}
	public void init(String time1,String time2){
		
		Object[][] cells;

		
		
		 ResultSet	rs=new GetRS().getResultSet("select * from tb_sell_info where sellDateTime between '"+time1+"' and  '"+time2+"'");
		
			
		
		
		String[] colnames={"销售编号","商品名称","商品进价","销售价格","销售数量","客户名称","经手人","销售金额","盈利","销售时间"}; 
		
		
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
		cells=new Object[i][10];
		int j=0;
		try {
			rs.beforeFirst() ;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			while(rs.next()){
				System.out.println("Yo Yo YO");
				
				int sellid=rs.getInt(1);                                                //销售编号
				String selldatetime=rs.getString(2);    
				String stime=new GetTime().format(selldatetime);//销售时间
				String time=new GetTime().toformat(selldatetime);
				int proid=rs.getInt(3);                           //商品编号
				int sellacount=rs.getInt(4);                                            //销售数量
				double prosellprice=rs.getDouble(5);                                     //销售价格
				double selljine=sellacount*prosellprice;                                 //销售金额
				zongselljine+=selljine;
				int ywyid=rs.getInt(6);                           //业务员编号
				String remark=rs.getString(7);
				int kehuid=rs.getInt(8);                             //客户编号
				 
				String kehuname=kehuidnamemap.get(kehuid);                                  //客户名称
				String ywyname=ywyidnamemap.get(ywyid);                                       //经手人
				

				
				
				ProBean bean=new KuCunDaoImpl().getProBean(proid);                     
				String name=bean.getProName();                                            //商品名称
				double price=bean.getProPrice();                                          // 商品进价
				
				double yingli=(prosellprice-price)*sellacount;                                 //盈利
				
				zongyingli+=yingli;                                     //总盈利
				
//				{"销售编号","商品名称","商品进价","销售价格","销售数量","客户名称","经手人",,"销售金额"，"盈利","销售时间"};                  
				
				cells[j][0]="XS"+time+"_"+(1000+sellid);
				System.out.println("-------------");
				cells[j][1]=name;
				System.out.println(cells[j][1]+"-------------");
				cells[j][2]=String.valueOf(price).trim()+"   元";
				cells[j][3]=String.valueOf(prosellprice).trim()+"    元";
				cells[j][4]=String.valueOf(sellacount).trim();
				cells[j][5]=String.valueOf(kehuname).trim();
				cells[j][6]=String.valueOf(ywyname).trim();
				cells[j][7]=String.valueOf(selljine).trim()+"   元";
				cells[j][8]=String.valueOf(yingli).trim()+"   元";
				cells[j][9]=String.valueOf(stime).trim();
				j++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame jf=new JFrame("销售时间段查询");
		
		JTable jt=new JTable(cells,colnames);
		
		jt.setBackground(Color.white);
		jt.setRowHeight (30);
		jt.setSelectionForeground (Color.red);//
		jt.setEnabled(false);
		
		JPanel jp=new JPanel();
		JLabel clabel=new JLabel();
		clabel.setText("    "+"          总销售金额           ");
		JTextField tacount=new JTextField();
		tacount.setEditable(false);
		tacount.setText("    "+zongselljine+"            元 ");
		JLabel plabel=new JLabel();
		plabel.setText("    "+"         总盈利            ");
		JTextField tprice=new JTextField();
		tprice.setEditable(false);
		tprice.setText("    "+zongyingli+"        元");
		jp.add(clabel);
		jp.add(tacount);
		jp.add(plabel);
		jp.add(tprice);
		JScrollPane js=new JScrollPane(jt);
		jf.add(js);
		jf.add(jp,BorderLayout.SOUTH);
		jf.setVisible(true);
		jf.setSize(800,600);
		jf.setLocationRelativeTo(null);
		
		
		}
	

}
