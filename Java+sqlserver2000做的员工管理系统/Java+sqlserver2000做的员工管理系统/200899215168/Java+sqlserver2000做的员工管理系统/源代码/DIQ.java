//���Ų�ѯ��



package classsource;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;

public class DIQ extends JInternalFrame{

	JLabel lb1=new JLabel("�� �� �� Ϣ �� ѯ");
   	JLabel lb2=new JLabel("���ű�ţ�");
   	JLabel lb3=new JLabel("�������ƣ�");
   	JTextField setxtid=new JTextField(10);
   	JTextField setxtname=new JTextField(10);
   	JButton btn1=new JButton("��ѯ");

   	JTable table;
	DefaultTableModel dtm;
	String columns[] = {"���ű��","��������","��������"};
	public DIQ(){
		setTitle("�� �� �� Ϣ �� ѯ");

		dtm = new DefaultTableModel();
		table = new JTable(dtm);
		//table.set PreferredScrollableViewportSize(new Dimension(400, 80));
        JScrollPane sl = new JScrollPane(table);


        dtm.setColumnCount(3);
		dtm.setColumnIdentifiers(columns);
		//dtm.insertRow(0,columns);

		getContentPane().setLayout(null);
		lb1.setBounds(200,10,300,30);
		lb1.setFont(new Font("����",Font.BOLD,24));
		getContentPane().add(lb1);

		Font f=new Font("����",Font.PLAIN,12);
		lb2.setBounds(10,60,80,25);
		lb2.setFont(f);
		getContentPane().add(lb2);
		setxtid.setBounds(80,60,80,23);
		setxtid.setFont(f);
		getContentPane().add(setxtid);
		lb3.setBounds(10,90,80,25);
		lb3.setFont(f);
   	    getContentPane().add(lb3);
   	    setxtname.setBounds(80,90,80,23);
   	    setxtname.setFont(f);
   	    getContentPane().add(setxtname);
   	    btn1.setBounds(90,130,60,25);
   	    btn1.setFont(f);
   	    getContentPane().add(btn1);

		sl.setBounds(180,60,425,290);
		//getContentPane().add(table);
		getContentPane().add(sl);

//���ñ߿�
		setxtid.setBorder(BorderFactory.createLineBorder(Color.black));
   	    setxtname.setBorder(BorderFactory.createLineBorder(Color.black));
   	    btn1.setBorder(BorderFactory.createRaisedBevelBorder());
		sl.setBorder(BorderFactory.createLineBorder(Color.black));

		//�������ݿ�
		Database.joinDB();

//Ϊ����ʹ������------------------------------------------------------------
         String csf="select * from DepartmentInformation";
         if(Database.query(csf)){
         	try{
         		while(Database.rs.next()){
         			        					String num = ("" + Database.rs.getInt("D_Number"));
        					System.out.println(num);
        					String name = Database.rs.getString("D_Name");
        					System.out.println(name);
        					String count = Database.rs.getString("D_Count");
        					System.out.println(count);
        				//	String result[]=new String[3];
        				//	result[1] =num;
        				//	result[2] =name;
        				//	result[3] =count;
        				//1�ü���
        					Vector v=new Vector();
        					v.add(num);
        					v.add(name);
        					v.add(count);
        					dtm.addRow(v);

         			}
         		}
         	catch(Exception ecsf){
         		System.out.println("��ʹ��������ݳ���!");}
         	}
//------------------------------------------------------------------------------

//Ϊ��ѯ��ť���¼�------------------------------------------------------
        btn1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		String sql;
        		int rc=dtm.getRowCount();
        		//System.out.println(dtm.getRowCount());
        	    	for(int i=0;i<rc;i++){
        						dtm.removeRow(0);
        						}
        		if(setxtid.getText().equals("")&&setxtname.getText().equals("")){
        			sql="select * from DepartmentInformation";
        			}
        		else if(setxtname.getText().equals("")){
        			sql = "select * from DepartmentInformation where D_Number = '" + setxtid.getText() +"'";
        			}
        		else{
        		sql = "select * from DepartmentInformation where D_Number = '" + setxtid.getText() +"' or D_Name like '%"+ setxtname.getText() +"%'";
        		    }

        		System.out.println(sql);
        			if(Database.query(sql)){
        				try{
        				while(Database.rs.next()){
        					String num = ("" + Database.rs.getInt("D_Number"));
        					System.out.println(num);
        					String name = Database.rs.getString("D_Name");

        					System.out.println(name);
        					String count = Database.rs.getString("D_Count");
        					System.out.println(count);
        				//	String result[]=new String[3];
        				//	result[1] =num;
        				//	result[2] =name;
        				//	result[3] =count;
        				//1�ü���
        					Vector v=new Vector();
        					v.add(num);
        					v.add(name);
        					v.add(count);
        					dtm.addRow(v);

        				//2������
        					//String result[]={num,name,count};
        					//dtm.addRow(result);
        					}
        				  }
        				 catch(Exception ee){}
        				}
        		}
        	});

//-------------------------------------------------------------------
		setSize(630,400);
		this.setClosable(true);
		setVisible(true);
		}
	}
