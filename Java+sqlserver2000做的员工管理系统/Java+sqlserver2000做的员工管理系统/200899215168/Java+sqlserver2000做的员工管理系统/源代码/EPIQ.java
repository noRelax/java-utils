//���Ͳ�ѯ��



package classsource;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;

public class EPIQ extends JInternalFrame{

	JLabel lb1=new JLabel("�� �� �� Ϣ �� ѯ");
   	JLabel lb2=new JLabel("Ա�����:");
   	JLabel lb3=new JLabel("Ա��������");
   	JTextField txt1=new JTextField(10);
   	JTextField txt2=new JTextField(10);
   	JButton btn1=new JButton("��ѯ");

   	JTable table;
	DefaultTableModel dtm;
	String columns[] = {"�����˱��","������������","����ʱ��","���͵ص�","����ԭ��","��ע"};
	public EPIQ(){
		setTitle("������Ϣ��ѯ");

		dtm = new DefaultTableModel();
		table = new JTable(dtm);
		JScrollPane sl = new JScrollPane(table);
		//sl.HORIZONTAL_SCROLLBAR_ALWAYS;
		//sl.VERTICAL_SCROLLBAR_ALWAYS;

		dtm.setColumnCount(6);
		dtm.setColumnIdentifiers(columns);
		//dtm.insertRow(0,columns);
//		String s[]={"","","","",""};
//		dtm.addRow(s);
//		dtm.addRow(s);

		getContentPane().setLayout(null);
		lb1.setBounds(200,10,300,30);
		lb1.setFont(new Font("����",Font.BOLD,24));
		getContentPane().add(lb1);

		Font f=new Font("����",Font.PLAIN,12);
		lb2.setBounds(10,60,80,25);
		lb2.setFont(f);
		getContentPane().add(lb2);
		txt1.setBounds(80,60,80,23);
		txt1.setFont(f);
		getContentPane().add(txt1);
		lb3.setBounds(10,90,80,25);
		lb3.setFont(f);
   	    getContentPane().add(lb3);
   	    txt2.setBounds(80,90,80,23);
   	    txt2.setFont(f);
   	    getContentPane().add(txt2);
   	    btn1.setBounds(90,130,60,25);
   	    btn1.setFont(f);
   	    getContentPane().add(btn1);

   	    //���ñ߿�
		txt1.setBorder(BorderFactory.createLineBorder(Color.black));
   	    txt2.setBorder(BorderFactory.createLineBorder(Color.black));
   	    btn1.setBorder(BorderFactory.createRaisedBevelBorder());
		sl.setBorder(BorderFactory.createLineBorder(Color.black));

		//�������ݿ�
		Database.joinDB();

//��ʼ��������------------------------------------------------------------------
		String scEPIQ="select * from EncouragementPunishInformation";

		if(Database.query(scEPIQ)){
        			System.out.println(scEPIQ);
        			try{
        				while(Database.rs.next()){
        					String number = ("" + Database.rs.getInt("EP_Number"));
        					String name = Database.rs.getString("EP_Name");
        					System.out.println(name);
        					String date = Database.rs.getString("EP_Date");
        					System.out.println(date);
        					String address = Database.rs.getString("EP_Address");
        					System.out.println(address);
        					String causation = Database.rs.getString("EP_Causation");
        					System.out.println(causation);
        					String remark = Database.rs.getString("EP_Remark");
        					System.out.println(remark);


        					Vector v = new Vector();
        					v.add(number);
        					v.add(name);
        					v.add(date);
        					v.add(address);
        					v.add(causation);
        					v.add(remark);

        					dtm.addRow(v);
       					}
        				}
        		   	catch(Exception eEPIQ){}

        			}

//------------------------------------------------------------------

//Ϊ��ѯ��ť���¼�--------------------------------------------------------
        btn1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		System.out.println("��ѯ��ť�¼�ִ��");
        		String sEPIQ;
        		int rc=dtm.getRowCount();
        		//System.out.println(dtm.getRowCount());
        	    	for(int i=0;i<rc;i++){
        						dtm.removeRow(0);
        						}

        		if(txt1.getText().equals("")&&txt2.getText().equals("")){
        			sEPIQ="select * from EncouragementPunishInformation";

        			}
        		else if(txt2.getText().equals("")){
        			sEPIQ="select * from EncouragementPunishInformation where EP_Number = '" + txt1.getText() +"'";
        			}
        		else{
        			sEPIQ="select * from EncouragementPunishInformation where EP_Number = '" + txt1.getText() +"' or EP_Name like '%"+ txt2.getText() +"%'";
        			}
        		System.out.println(sEPIQ);
                   //sEPIQ="select * from EncouragementPunishInformation where EP_Number = '" + txt1.getText() +"' or EP_Name= '"+ txt2.getText() +"'";

        		if(Database.query(sEPIQ)){
        			System.out.println(sEPIQ);
        			try{
        				while(Database.rs.next()){
        					String number = ("" + Database.rs.getInt("EP_Number"));
        					String name = Database.rs.getString("EP_Name");
        					System.out.println(name);
        					String date = Database.rs.getString("EP_Date");
        					System.out.println(date);
        					String address = Database.rs.getString("EP_Address");
        					System.out.println(address);
        					String causation = Database.rs.getString("EP_Causation");
        					System.out.println(causation);
        					String remark = Database.rs.getString("EP_Remark");
        					System.out.println(remark);


        					Vector v = new Vector();
        					v.add(number);
        					v.add(name);
        					v.add(date);
        					v.add(address);
        					v.add(causation);
        					v.add(remark);

        					dtm.addRow(v);
       					}
        				}
        		   	catch(Exception eEPIQ){}

        			}
       		}
        	});
//------------------------------------------------------------------
		sl.setBounds(180,60,425,290);
		getContentPane().add(sl);

		setSize(630,400);
		this.setClosable(true);
		//this.setm
		setVisible(true);
		}
	}
