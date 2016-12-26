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



public class SellEdxSearch {
	
	
	
	
	
		
	
	static Map<Integer,String> ywyidnamemap=new HashMap<Integer,String>();      //ҵ��Ա     ����-----���
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
	static Map<Integer,String> kehuidnamemap=new HashMap<Integer,String>();         //�ͻ�     ���----����
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
		
		new SellEdxSearch().init();
	}
	public void init(){
		
		Object[][] cells;

		ResultSet	rs=new GetRS().getResultSet("select * from tb_sell_info t1,tb_product_info t2 order by t1.sellAcount*(t1.proSellPrice-t2.proPrice) desc ");
		
		
		String[] colnames={"ӯ��","��Ʒ����","��Ʒ����","���ۼ۸�","��������","�ͻ�����","������","���۽��","���۱��","����ʱ��"}; 
		
		
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
				
				int sellid=rs.getInt(1);                                                //���۱��
				String selldatetime=rs.getString(2);    
				String stime=new GetTime().format(selldatetime);//����ʱ��
				String time=new GetTime().toformat(selldatetime);
				int proid=rs.getInt(3);                           //��Ʒ���
				int sellacount=rs.getInt(4);                                            //��������
				double prosellprice=rs.getDouble(5);                                     //���ۼ۸�
				double selljine=sellacount*prosellprice;                                 //���۽��
				zongselljine+=selljine;
				int ywyid=rs.getInt(6);                           //ҵ��Ա���
				String remark=rs.getString(7);
				int kehuid=rs.getInt(8);                             //�ͻ����
				 
				String kehuname=kehuidnamemap.get(kehuid);                                  //�ͻ�����
				String ywyname=ywyidnamemap.get(ywyid);                                       //������
				

				
				
				ProBean bean=new KuCunDaoImpl().getProBean(proid);                     
				String name=bean.getProName();                                            //��Ʒ����
				double price=bean.getProPrice();                                          // ��Ʒ����
				
				double yingli=(prosellprice-price)*sellacount;                                 //ӯ��
				
				zongyingli+=yingli;                                     //��ӯ��
				
//				{"���۱��","��Ʒ����","��Ʒ����","���ۼ۸�","��������","�ͻ�����","������",,"���۽��"��"ӯ��","����ʱ��"};                  
				
				cells[j][0]=String.valueOf(yingli).trim()+"   Ԫ";
				System.out.println("-------------");
				cells[j][1]=name;
				System.out.println(cells[j][1]+"-------------");
				cells[j][2]=String.valueOf(price).trim()+"   Ԫ";
				cells[j][3]=String.valueOf(prosellprice).trim()+"    Ԫ";
				cells[j][4]=String.valueOf(sellacount).trim();
				cells[j][5]=String.valueOf(kehuname).trim();
				cells[j][6]=String.valueOf(ywyname).trim();
				cells[j][7]=String.valueOf(selljine).trim()+"   Ԫ";
				
				cells[j][8]="XS"+time+"_"+(1000+sellid);
				cells[j][9]=String.valueOf(stime).trim();
				j++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame jf=new JFrame("��ӯ���������в�ѯ");
		
		JTable jt=new JTable(cells,colnames);
		
		jt.setBackground(Color.white);
		jt.setRowHeight (30);
		jt.setSelectionForeground (Color.red);//
		jt.setEnabled(false);
		
		JPanel jp=new JPanel();
		JLabel clabel=new JLabel();
		clabel.setText("    "+"          �����۽��           ");
		JTextField tacount=new JTextField();
		tacount.setEditable(false);
		tacount.setText("    "+zongselljine+"            Ԫ ");
		JLabel plabel=new JLabel();
		plabel.setText("    "+"         ��ӯ��            ");
		JTextField tprice=new JTextField();
		tprice.setEditable(false);
		tprice.setText("    "+zongyingli+"        Ԫ");
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
