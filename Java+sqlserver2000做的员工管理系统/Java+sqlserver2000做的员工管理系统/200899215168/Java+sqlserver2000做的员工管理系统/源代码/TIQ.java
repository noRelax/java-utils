//��ѵ��ѯ��


package classsource;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;

public class TIQ extends JInternalFrame{

	JLabel lb1=new JLabel("�� ѵ �� Ϣ �� ѯ");
   	JLabel lb2=new JLabel("��ѵ���:");
   	JLabel lb3=new JLabel("Ա������:");
   	JTextField ttxtid=new JTextField(10);
   	JTextField ttxtname=new JTextField(10);
   	JButton btn1=new JButton("��ѯ");

   	JTable table;
	DefaultTableModel dtm;
	String columns[] = {"��ѵ���","��ѵ����","Ա������","��ѵ����","��ѵ����"};
	public TIQ(){
		setTitle("�� ѵ �� Ϣ �� ѯ");

		dtm=new DefaultTableModel();
		table = new JTable(dtm);
		JScrollPane s1=new JScrollPane(table);
		//sl.HORIZONTAL_SCROLLBAR_ALWAYS;
		//sl.VERTICAL_SCROLLBAR_ALWAYS;
		dtm.setColumnCount(5);
		dtm.setColumnIdentifiers(columns);

		getContentPane().setLayout(null);
		lb1.setBounds(200,10,300,30);
		lb1.setFont(new Font("����",Font.BOLD,24));
		getContentPane().add(lb1);

		Font f=new Font("����",Font.PLAIN,12);
		lb2.setBounds(10,60,80,25);
		lb2.setFont(f);
		getContentPane().add(lb2);
		ttxtid.setBounds(80,60,80,23);
		ttxtid.setFont(f);
		getContentPane().add(ttxtid);
		lb3.setBounds(10,90,80,25);
		lb3.setFont(f);
   	    getContentPane().add(lb3);
   	    ttxtname.setBounds(80,90,80,23);
   	    ttxtname.setFont(f);
   	    getContentPane().add(ttxtname);
   	    btn1.setBounds(90,130,60,25);
   	    btn1.setFont(f);
   	    getContentPane().add(btn1);

		s1.setBounds(180,60,425,290);
		getContentPane().add(s1);

//���ñ߿�---------------------------------------------------------------------------
		ttxtid.setBorder(BorderFactory.createLineBorder(Color.black));
   	    ttxtname.setBorder(BorderFactory.createLineBorder(Color.black));
   	    btn1.setBorder(BorderFactory.createRaisedBevelBorder());
		s1.setBorder(BorderFactory.createLineBorder(Color.black));

 //___________�������ݿ�_______________________________________________________________
		Database.joinDB();

		String Tim="select * from TrainInformation";
		if(Database.query(Tim)){
			System.out.println(Tim);
			try{
				while(Database.rs.next()){
					String number=(""+Database.rs.getString("T_Number"));
					System.out.println(number);
					String Content=Database.rs.getString("T_Content");
					System.out.println(Content);
					String name=Database.rs.getString("T_Name");
					System.out.println(name);
					String date=Database.rs.getString("T_Date");
					System.out.println(date);
					String Money=Database.rs.getString("T_Money");
					System.out.println(Money);

					Vector v=new Vector();
					v.add(number);
					v.add(Content);
					v.add(name);
					v.add(date);
					v.add(Money);

					dtm.addRow(v);


				}
			}
			catch(Exception eTIQ){
			  System.out.println("��ʼ�����ʧ�ܣ�");
			}
		}
//Ϊ��ѯ��ť���¼�--------------------------------------------------------
        btn1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		System.out.println("��ѯ��ť���¼�");
        		String STIQ;
        		int rc=dtm.getRowCount();
        		for(int i=0;i<rc;i++){
        			dtm.removeRow(0);
        		}
      //------------------------
        		if(ttxtid.getText().equals("")&&ttxtname.getText().equals("")){
        		   STIQ="select * from TrainInformation";
        		}
        		else if(ttxtname.getText().equals("")){
        			STIQ="select * from TrainInformation where T_Number = '" + ttxtid.getText() +"'";
        			}
        		else{
        		   STIQ="select * from TrainInformation where T_Number = '" + ttxtid.getText() +"' or T_Name like '%"+ ttxtname.getText() +"%'";
          		}
        		System.out.println(STIQ);
        		if(Database.query(STIQ)){
        			try{
						while(Database.rs.next()){
							String number=(""+Database.rs.getString("T_Number"));
							System.out.println(number);
							String Content=Database.rs.getString("T_Content");
							System.out.println(Content);
							String name=Database.rs.getString("T_Name");
							System.out.println(name);
							String date=Database.rs.getString("T_Date");
							System.out.println(date);
							String Money=Database.rs.getString("T_Money");
							System.out.println(Money);

							Vector v=new Vector();
							v.add(number);
							v.add(Content);
							v.add(name);
							v.add(date);
							v.add(Money);

							dtm.addRow(v);


						}
        			}
        			catch(Exception eT){}

        		}
        	}

        });

		setSize(630,400);
		this.setClosable(true);
		setVisible(true);
		}
	}
