package com.itstar.erp.ui.searchswing.tuikusearch;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.dao.tuihuo.TuiHuoDaoImpl;
import com.itstar.erp.dao.tuiku.TuiKuDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.product.ProBean;
import com.itstar.erp.vo.ruku.RuKuBean;


public class TuiKuEdxSearch {
	
	public static void main(String[] args){
		new TuiKuEdxSearch().init();
	}
	
	public void init(){
		Object[][] cells;
		
		ResultSet rs=new GetRS().getResultSet("select t1.* from tb_tuiku_info t1,tb_ruku_info t2,tb_product_info t3 where t1.rukuID=t2.rukuID and t2.proID=t3.proID order by t1.tkAcount*t3.proPrice desc");
		String[] colnames={"�˿���","�����","��Ʒ����","��Ʒ����","�������","�˿�����","�˿���","���ʱ��","�˿�ʱ��","�˿�ԭ��","������"}; 
		
		int i=0;
		
		try {
			while(rs.next()){
				i++;
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		cells=new Object[i][11];
		
		int j=0;
		try {
			rs.beforeFirst() ;                 //�ƶ��α�
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			while(rs.next()){
				System.out.println("Ha Ha Ha");
//				{"�˿���","�����","��Ʒ����","��Ʒ����","�������","�˿�����","�˿���","���ʱ��","�˿�ʱ��","�˿�ԭ��","������"};
				int tuikuid=rs.getInt(1);                                                     //�˿���
				int rukuid=rs.getInt(2);                                                        //�����
				String tuikutime=rs.getString(3);                                               //�˿�ʱ��
				
				String tktime=new GetTime().format(tuikutime);                                      //�˿�ʱ�� ���   ��-----
				String ttime=new GetTime().toformat(tuikutime);                                       //��-------   �˿�ʱ��
				
				int tuikuacount=rs.getInt(4);                                                  //�˿�����
				String tuikuremark=rs.getString(5);                                                      //�˿ⱸע
				int tuikuywyid=rs.getInt(6);                                                //�˿�ҵ��Ա���
				
				
				RuKuBean rukubean=new TuiKuDaoImpl().getRuKuBean(rukuid);
				int proid=rukubean.getProID();                                                  //��Ʒ���
				int rukuacount=rukubean.getRukuAcount();                                          //�������
				String rukutime=rukubean.getRukuDateTime();                                        //���ʱ��
				
				String rktime=new GetTime().format(rukutime);                           //���ʱ��  ���   ��------
				String xtime=new GetTime().toformat(rukutime);                            //���ʱ��
				
			
				
				
				ProBean probean=new KuCunDaoImpl().getProBean(proid);                     
				String proname=probean.getProName();                                                //��Ʒ����
				double proprice=probean.getProPrice();                                               // ��Ʒ����
				
				String ywyname=new TuiHuoDaoImpl().getywyName(tuikuywyid);                        //�˻�ҵ��Ա����
				
				double tuikujine=tuikuacount*proprice;                                                  //�˿���
				
			
				
				                               
				
//		{"�˿���","�����","��Ʒ����","��Ʒ����","�������","�˿�����","�˿���","���ʱ��","�˿�ʱ��","�˿�ԭ��","������"};
//				
				cells[j][0]=String.valueOf(tuikujine).trim()+"     Ԫ";
				cells[j][1]="RK"+xtime+"_"+(1000+rukuid);
				cells[j][2]=String.valueOf(proname).trim();
				cells[j][3]=String.valueOf(proprice).trim();
				cells[j][4]=String.valueOf(rukuacount).trim();
				cells[j][5]=String.valueOf(tuikuacount).trim();
				cells[j][6]="TK"+ttime+"_"+(1000+tuikuid);
				cells[j][7]=String.valueOf(rktime).trim();
				cells[j][8]=String.valueOf(tktime).trim();
				cells[j][9]=String.valueOf(tuikuremark).trim();
				cells[j][10]=String.valueOf(ywyname).trim();
				
				
				
				j++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame jf=new JFrame("���˿����С���в�ѯ");
		
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
