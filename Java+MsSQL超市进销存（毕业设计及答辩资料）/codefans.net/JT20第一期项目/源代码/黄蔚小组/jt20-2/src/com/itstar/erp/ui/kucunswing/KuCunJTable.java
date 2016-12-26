package com.itstar.erp.ui.kucunswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.vo.product.ProBean;


public class KuCunJTable {

	
	
	
	KuCunJTable(){
		
	}
	public static void main(String[] args){
		new KuCunJTable().init();
	}
	void init(){
		
		Object[][] cells;
		String tb="tb_kucun_Info";
		ResultSet rs=new GetResultSet().getResultSet(tb);
		String[] colnames={"商品编号","商品名称","进价","库存数量","库存金额"};
		
		
		int i=0;
		
		int totalacount=0;
		double totalprice=0;
		
		
		
		try {
			while(rs.next()){
				i++;
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		cells=new Object[i][5];
		int j=0;
		try {
			rs.beforeFirst() ;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			while(rs.next()){
				System.out.println("ooooo");
				int proid=rs.getInt(1);                             //商品编号
				int kucunacount=rs.getInt(2);                       //库存数量
				totalacount+=kucunacount;                              //总数量
				ProBean bean=new KuCunDaoImpl().getProBean(proid);
				String name=bean.getProName();                         //商品名称
				double price=bean.getProPrice();                      // 商品进价
				double jine=kucunacount*price;                     // 库存金额
				totalprice+=jine;
//				String[] colnames={"商品编号","商品名称","进价","库存数量","库存金额"};
				
				cells[j][0]="准字"+(1000+proid);
				cells[j][1]=name.trim();
				cells[j][2]=String.valueOf(price).trim()+"   元";
				cells[j][3]=String.valueOf(kucunacount).trim();
				cells[j][4]=String.valueOf(jine).trim()+"   元";
				j++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame jf=new JFrame("库存全部信息");
		
		JTable jt=new JTable(cells,colnames);
		
		jt.setBackground(Color.white);
		jt.setRowHeight (30);
		jt.setSelectionForeground (Color.red);//
		jt.setEnabled(false);
		
		JPanel jp=new JPanel();
		JLabel clabel=new JLabel();
		clabel.setText("    "+"          库存总数量           ");
		JTextField tacount=new JTextField();
		tacount.setEditable(false);
		tacount.setText("    "+totalacount+"             ");
		JLabel plabel=new JLabel();
		plabel.setText("    "+"         库存总金额               ");
		JTextField tprice=new JTextField();
		tprice.setEditable(false);
		tprice.setText("    "+totalprice+"        元");
		jp.add(clabel);
		jp.add(tacount);
		jp.add(plabel);
		jp.add(tprice);
		JScrollPane js=new JScrollPane(jt);
		jf.add(js);
		jf.add(jp,BorderLayout.SOUTH);
		jf.setVisible(true);
		jf.setSize(700,500);
		jf.setLocationRelativeTo(null);
		
		
		}
	}
	
	
	

