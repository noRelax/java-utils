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
		String[] colnames={"�˻�����","���۱��","��Ʒ����","��Ʒ����","���ۼ۸�","��������","�˻����","�˻����","����ʱ��","�˻�ʱ��","�˻�ԭ��","������"}; 
		
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
			rs.beforeFirst() ;                 //�ƶ��α�
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			while(rs.next()){
				System.out.println("Ha Ha Ha");
//				{"�˻����","���۱��","��Ʒ����","��Ʒ����","���ۼ۸�","��������","�˻�����","�˻����","����ʱ��","�˻�ʱ��","�˻�ԭ��","������"};
				int tuihuoid=rs.getInt(1);                                                     //�˻����
				int sellid=rs.getInt(2);                                                        //���۱��
				String tuihuotime=rs.getString(3);                                               //�˻�ʱ��
				
				String thtime=new GetTime().format(tuihuotime);                                      //�˻�ʱ�� ���   ��-----
				String ttime=new GetTime().toformat(tuihuotime);                                       //��-------   �˻�ʱ��
				
				String tuihuoremark=rs.getString(4);                                             //�˻�ԭ��
				int tuihuoywyid=rs.getInt(5);                                                      //�˻�ҵ��Ա���
				int tuihuoacount=rs.getInt(6);                                                //�˻�����
				
				
				SellBean sellbean=new TuiHuoDaoImpl().getSellBean(sellid);
				int proid=sellbean.getProID();                                                  //��Ʒ���
				int sellacount=sellbean.getSellAcount();                                          //��������
				String selltime=sellbean.getSellDateTime();                                        
				
				String xstime=new GetTime().format(selltime);                           //����ʱ��  ���   ��------
				String xtime=new GetTime().toformat(selltime);                            //����ʱ��
				
				double sellprice=sellbean.getSellPrice();                                           //���ۼ۸�
				
				
				ProBean probean=new KuCunDaoImpl().getProBean(proid);                     
				String proname=probean.getProName();                                                //��Ʒ����
				double proprice=probean.getProPrice();                                               // ��Ʒ����
				
				String ywyname=new TuiHuoDaoImpl().getywyName(tuihuoywyid);                        //�˻�ҵ��Ա����
				
				double tuihuojine=tuihuoacount*proprice;
				
			
				
				                               
				
//				{"�˻����","���۱��","��Ʒ����","��Ʒ����","���ۼ۸�","��������","�˻�����","�˻����","����ʱ��","�˻�ʱ��","�˻�ԭ��","������"};         
//				
				cells[j][0]=String.valueOf(tuihuoacount).trim();
				cells[j][1]="XS"+xstime+"_"+(1000+sellid);
				cells[j][2]=String.valueOf(proname).trim();
				cells[j][3]=String.valueOf(proprice).trim();
				cells[j][4]=String.valueOf(sellprice).trim();
				cells[j][5]=String.valueOf(sellacount).trim();
				cells[j][6]=String.valueOf(tuihuojine).trim()+"   Ԫ";
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
		
		JFrame jf=new JFrame("���˻�������С���в�ѯ");
		
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

