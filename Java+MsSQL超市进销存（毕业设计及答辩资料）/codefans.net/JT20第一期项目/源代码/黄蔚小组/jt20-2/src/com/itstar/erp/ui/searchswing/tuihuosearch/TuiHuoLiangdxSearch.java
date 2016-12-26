package com.itstar.erp.ui.searchswing.tuihuosearch;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.dao.tuihuo.TuiHuoDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.product.ProBean;
import com.itstar.erp.vo.sell.SellBean;


public class TuiHuoLiangdxSearch {
	
	
	
	
	public static void main(String[] args){
		new TuiHuoLiangdxSearch().init();
	}
	
	
	public void init(){
		
		Object[][] cells;
		ResultSet rs=new GetRS().getResultSet("select * from tb_tuihuo_info order by thAcount desc");
		String[] colnames={"退货数量","销售编号","商品名称","商品进价","销售价格","销售数量","退货金额","退货编号","销售时间","退货时间","退货原因","经手人"}; 
		
		int i=0;
		
		try {
			while(rs.next()){
				i++;
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		cells=new Object[i][12];
		
		int j=0;
		try {
			rs.beforeFirst() ;                 //移动游标
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			while(rs.next()){
				System.out.println("Ha Ha Ha");
//				{"退货编号","销售编号","商品名称","商品进价","销售价格","销售数量","退货数量","退货金额","销售时间","退货时间","退货原因","经手人"};
				int tuihuoid=rs.getInt(1);                                                     //退货编号
				int sellid=rs.getInt(2);                                                        //销售编号
				String tuihuotime=rs.getString(3);                                               //退货时间
				
				String thtime=new GetTime().format(tuihuotime);                                      //退货时间 编号   无-----
				String ttime=new GetTime().toformat(tuihuotime);                                       //有-------   退货时间
				
				String tuihuoremark=rs.getString(4);                                             //退货原因
				int tuihuoywyid=rs.getInt(5);                                                      //退货业务员编号
				int tuihuoacount=rs.getInt(6);                                                //退货数量
				
				
				SellBean sellbean=new TuiHuoDaoImpl().getSellBean(sellid);
				int proid=sellbean.getProID();                                                  //商品编号
				int sellacount=sellbean.getSellAcount();                                          //销售数量
				String selltime=sellbean.getSellDateTime();                                        
				
				String xstime=new GetTime().format(selltime);                           //销售时间  编号   无------
				String xtime=new GetTime().toformat(selltime);                            //销售时间
				
				double sellprice=sellbean.getSellPrice();                                           //销售价格
				
				
				ProBean probean=new KuCunDaoImpl().getProBean(proid);                     
				String proname=probean.getProName();                                                //商品名称
				double proprice=probean.getProPrice();                                               // 商品进价
				
				String ywyname=new TuiHuoDaoImpl().getywyName(tuihuoywyid);                        //退货业务员名称
				
				double tuihuojine=tuihuoacount*proprice;
				
			
				
				                               
				
//				{"退货编号","销售编号","商品名称","商品进价","销售价格","销售数量","退货数量","退货金额","销售时间","退货时间","退货原因","经手人"};         
//				
				cells[j][0]=String.valueOf(tuihuoacount).trim();
				cells[j][1]="XS"+xstime+"_"+(1000+sellid);
				cells[j][2]=String.valueOf(proname).trim();
				cells[j][3]=String.valueOf(proprice).trim();
				cells[j][4]=String.valueOf(sellprice).trim();
				cells[j][5]=String.valueOf(sellacount).trim();
				cells[j][6]=String.valueOf(tuihuojine).trim()+"   元";
				cells[j][7]="TH"+thtime+"_"+(1000+tuihuoid);
				cells[j][8]=String.valueOf(xtime).trim();
				cells[j][9]=String.valueOf(ttime).trim();
				cells[j][10]=String.valueOf(tuihuoremark).trim();
				cells[j][11]=String.valueOf(ywyname).trim();
				
				
				
				j++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame jf=new JFrame("按退货数量大小排行查询");
		
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

