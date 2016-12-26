//薪酬查询类



package classsource;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class SIQ extends JInternalFrame{

	JLabel lbl4=new JLabel("薪 资 信 息 查 询");
   	JLabel lbl5=new JLabel("员工编号：");
   	JLabel lbl6=new JLabel("员工姓名：");
   	JTextField stxtid=new JTextField(10);
   	JTextField stxtname=new JTextField(10);
   	JButton btn1=new JButton("查询");

   	JTable table;
	DefaultTableModel dtm;
	String columns[] = {"员工编号","员工姓名","基本工资","福利","奖金","计算方法","实发工资"};
	public SIQ(){
		setTitle("薪 资 信 息 查 询");

		dtm = new DefaultTableModel();
		table = new JTable(dtm);
		table.setPreferredScrollableViewportSize(new Dimension(400, 80));
        JScrollPane sl = new JScrollPane(table);

		dtm.setColumnCount(7);
		dtm.setColumnIdentifiers(columns);
//		String s[]={"","","","","","",""};
//		dtm.addRow(s);
//		dtm.addRow(s);

		getContentPane().setLayout(null);
		lbl4.setBounds(200,10,300,30);
		lbl4.setFont(new Font("宋体",Font.BOLD,24));
		getContentPane().add(lbl4);

		Font f=new Font("宋体",Font.PLAIN,12);
		lbl5.setBounds(10,60,80,25);
		lbl5.setFont(f);
		getContentPane().add(lbl5);
		stxtid.setBounds(80,60,80,23);
		stxtid.setFont(f);
		getContentPane().add(stxtid);
		lbl6.setBounds(10,90,80,25);
		lbl6.setFont(f);
   	    getContentPane().add(lbl6);
   	    stxtname.setBounds(80,90,80,23);
   	    stxtname.setFont(f);
   	    getContentPane().add(stxtname);
   	    btn1.setBounds(90,130,60,25);
   	    btn1.setFont(f);
   	    getContentPane().add(btn1);

   	    //设置边框
		stxtid.setBorder(BorderFactory.createLineBorder(Color.black));
   	    stxtname.setBorder(BorderFactory.createLineBorder(Color.black));
   	    btn1.setBorder(BorderFactory.createRaisedBevelBorder());
		sl.setBorder(BorderFactory.createLineBorder(Color.black));

   	    //连接数据库
   	    Database.joinDB();

//初始化表格数据--------------------------------------------------------------------
        		String sql;

        		sql="select * from WageInformation";

        		if(Database.query(sql)){
        		try{
        			System.out.println(sql);
        		    while(Database.rs.next()){
        		    	String number =("" + Database.rs.getInt("W_Number"));
        		    	String name = Database.rs.getString("W_Name");
        		    	String basicwage = Database.rs.getString("W_BasicWage");
        		    	String boon = Database.rs.getString("W_Boon");
        		    	String bonus = Database.rs.getString("W_Bonus");
        		    	String countmethod = Database.rs.getString("W_CountMethod");
        		    	String factwage = Database.rs.getString("W_FactWage");

        		    	Vector v =new Vector();
        		        v.add(number);
        		        v.add(name);
        		        v.add(basicwage);
        		        v.add(boon);
        		        v.add(bonus);
        		        v.add(countmethod);
        		        v.add(factwage);

        		        dtm.addRow(v);
        		    	}
        			}
        		catch(Exception ex){}
        		}
//----------------------------------------------------------------


//为查询按钮加事件-------------------------------------------------------
        btn1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		String sql;
        		int rc=dtm.getRowCount();
        		//System.out.println(dtm.getRowCount());
        	    	for(int i=0;i<rc;i++){
        						dtm.removeRow(0);
        						}

        		if(stxtid.getText().equals("")&&stxtname.getText().equals("")){
        			sql="select * from WageInformation";
        			}
        		else if(stxtname.getText().equals("")){
        			sql="select * from WageInformation where W_Number = '"+ stxtid.getText() +"'";
        			}
        		else{
        			sql="select * from WageInformation where W_Number = '"+ stxtid.getText() +"' or W_Name like '%"+ stxtname.getText()+"%'";
        			}

        		if(Database.query(sql)){
        		try{
        			System.out.println(sql);
        		    while(Database.rs.next()){
        		    	String number =("" + Database.rs.getInt("W_Number"));
        		    	String name = Database.rs.getString("W_Name");
        		    	String basicwage = Database.rs.getString("W_BasicWage");
        		    	String boon = Database.rs.getString("W_Boon");
        		    	String bonus = Database.rs.getString("W_Bonus");
        		    	String countmethod = Database.rs.getString("W_CountMethod");
        		    	String factwage = Database.rs.getString("W_FactWage");

        		    	Vector v =new Vector();
        		        v.add(number);
        		        v.add(name);
        		        v.add(basicwage);
        		        v.add(boon);
        		        v.add(bonus);
        		        v.add(countmethod);
        		        v.add(factwage);

        		        dtm.addRow(v);
        		    	}
        			}
        		catch(Exception ex){}
        		}
        	   }

        	});
//-------------------------------------------------------------------------

		sl.setBounds(180,60,425,290);
		getContentPane().add(sl);

		setSize(630,450);
		this.setClosable(true);
		setVisible(true);
		}
	}
