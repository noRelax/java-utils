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
		String[] colnames={"��Ʒ���","��Ʒ����","����","�������","�����"};
		
		
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
				int proid=rs.getInt(1);                             //��Ʒ���
				int kucunacount=rs.getInt(2);                       //�������
				totalacount+=kucunacount;                              //������
				ProBean bean=new KuCunDaoImpl().getProBean(proid);
				String name=bean.getProName();                         //��Ʒ����
				double price=bean.getProPrice();                      // ��Ʒ����
				double jine=kucunacount*price;                     // �����
				totalprice+=jine;
//				String[] colnames={"��Ʒ���","��Ʒ����","����","�������","�����"};
				
				cells[j][0]="׼��"+(1000+proid);
				cells[j][1]=name.trim();
				cells[j][2]=String.valueOf(price).trim()+"   Ԫ";
				cells[j][3]=String.valueOf(kucunacount).trim();
				cells[j][4]=String.valueOf(jine).trim()+"   Ԫ";
				j++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame jf=new JFrame("���ȫ����Ϣ");
		
		JTable jt=new JTable(cells,colnames);
		
		jt.setBackground(Color.white);
		jt.setRowHeight (30);
		jt.setSelectionForeground (Color.red);//
		jt.setEnabled(false);
		
		JPanel jp=new JPanel();
		JLabel clabel=new JLabel();
		clabel.setText("    "+"          ���������           ");
		JTextField tacount=new JTextField();
		tacount.setEditable(false);
		tacount.setText("    "+totalacount+"             ");
		JLabel plabel=new JLabel();
		plabel.setText("    "+"         ����ܽ��               ");
		JTextField tprice=new JTextField();
		tprice.setEditable(false);
		tprice.setText("    "+totalprice+"        Ԫ");
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
	
	
	

