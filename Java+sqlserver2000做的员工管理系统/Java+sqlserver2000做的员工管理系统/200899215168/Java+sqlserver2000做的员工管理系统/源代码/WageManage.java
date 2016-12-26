//薪筹管理类

package classsource;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class WageManage extends JInternalFrame{

	private JLabel lbl7=new JLabel("员工编号：");
   	private JLabel lbl1=new JLabel("员工姓名：");
   	private JLabel lbl2=new JLabel(" 福  利：");
   	private JLabel lbl4=new JLabel("基本工资：");
   	private JLabel lbl3=new JLabel(" 奖  金：");
   	private JLabel lbl5=new JLabel("薪资计算：");
   	private JLabel lbl6=new JLabel("实发工资：");


   	private JTextField stid=new JTextField(10);
   	private JTextField stname=new JTextField(10);
   	private JTextField stsalary=new JTextField(10);
   	private JTextField stboon=new JTextField(10);
   	private JTextField stprize=new JTextField(10);
   	private JTextField stcounter=new JTextField(10);
   	private JTextField stfact=new JTextField(10);
 	ImageIcon icon1=new ImageIcon("image//up.gif");
	ImageIcon icon2=new ImageIcon("image//down.gif");
   	private JButton btnadd=new JButton("添加");
   	private JButton delete=new JButton("删除");
   	private JButton updete=new JButton("修改");
   	private JButton save=new JButton("保存");
   	private JButton up=new JButton("<<");
   	private JButton next=new JButton(">>");



	public WageManage(){

	   	initComponents();
	}
		private void initComponents() {
		setTitle("薪资信息管理");
		Font f=new Font("宋体",Font.PLAIN,12);

		getContentPane().setLayout(null);
		lbl7.setBounds(30,30,80,25);
		lbl7.setFont(f);
		getContentPane().add(lbl7);
		stid.setBounds(100,30,100,25);
		stid.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(stid);
//--------------------------------------------------
		lbl1.setBounds(30,70,80,25);
		lbl1.setFont(f);
		getContentPane().add(lbl1);
		stname.setBounds(100,70,100,25);
		stname.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(stname);

        lbl2.setBounds(230,70,80,25);
        lbl2.setFont(f);
        getContentPane().add(lbl2);
        stsalary.setBounds(300,70,100,25);
        stsalary.setBorder(BorderFactory.createLineBorder(Color.black));
        getContentPane().add(stsalary);
 //----------------------------------
        lbl3.setBounds(30,110,80,25);
        lbl3.setFont(f);
        getContentPane().add(lbl3);
        stboon.setBounds(100,110,100,25);
        stboon.setBorder(BorderFactory.createLineBorder(Color.black));
        getContentPane().add(stboon);

		lbl4.setBounds(230,110,80,25);
		lbl4.setFont(f);
		getContentPane().add(lbl4);
		stprize.setBounds(300,110,100,25);
		stprize.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(stprize);
 //--------------------------------
        lbl5.setBounds(30,150,80,25);
        lbl5.setFont(f);
        getContentPane().add(lbl5);
        stcounter.setBounds(100,150,100,25);
        stcounter.setBorder(BorderFactory.createLineBorder(Color.black));
        getContentPane().add(stcounter);

        lbl6.setBounds(230,150,80,25);
        lbl6.setFont(f);
        getContentPane().add(lbl6);
        stfact.setBounds(300,150,100,25);
        stfact.setBorder(BorderFactory.createLineBorder(Color.black));
        getContentPane().add(stfact);
 //-------------------------------------------------------

        //按钮
        btnadd.setBounds(30,220,60,25);
        btnadd.setFont(f);
        btnadd.setBorder(BorderFactory.createRaisedBevelBorder());
        getContentPane().add(btnadd);
        delete.setBounds(110,220,60,25);
        delete.setFont(f);
        delete.setBorder(BorderFactory.createRaisedBevelBorder());
        getContentPane().add(delete);
        updete.setBounds(190,220,60,25);
        updete.setFont(f);
        updete.setBorder(BorderFactory.createRaisedBevelBorder());
        getContentPane().add(updete);
        save.setBounds(270,220,60,25);
        save.setFont(f);
        save.setBorder(BorderFactory.createRaisedBevelBorder());
        getContentPane().add(save);

        up.setBounds(350,218,60,15);
        up.setBorder(BorderFactory.createRaisedBevelBorder());
        getContentPane().add(up);
        next.setBounds(350,232,60,15);
        next.setBorder(BorderFactory.createRaisedBevelBorder());
        getContentPane().add(next);
//---连接数据库----------------------------------------------------------------------------
        Database.joinDB();
        //初始化数据--------
        String sqlw="select * from WageInformation";
        try{
           if(Database.query(sqlw)){
           	  Database.rs.next();
           	  String wNumber=(""+Database.rs.getInt("W_Number"));
           	  String wName=Database.rs.getString("W_Name");
           	  String wBasicWage=Database.rs.getString("W_BasicWage");
           	  String wBoon=Database.rs.getString("W_Boon");
           	  String wBonus=Database.rs.getString("W_Bonus");
           	  String wCountMethod=Database.rs.getString("W_CountMethod");
           	  String wFactWage=Database.rs.getString("W_FactWage");

           	  stid.setText(wNumber);
           	  stname.setText(wName);
           	  stsalary.setText(wBasicWage);
        	  stboon.setText(wBoon);
        	  stprize.setText(wBonus);
        	  stcounter.setText(wCountMethod);
        	  stfact.setText(wFactWage);
           }
        }
        catch(Exception esql){
        	   System.out.println("薪资信息管理-初始化数据失败!");
        }
 //------按钮事件--------------------------------------------------------------------------
       up.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
          	try{
          	if(Database.rs.previous()){
           	  String wNumber=(""+Database.rs.getString("W_Number"));
           	  String wName=Database.rs.getString("W_Name");
           	  String wBasicWage=Database.rs.getString("W_BasicWage");
           	  String wBoon=Database.rs.getString("W_Boon");
           	  String wBonus=Database.rs.getString("W_Bonus");
           	  String wCountMethod=Database.rs.getString("W_CountMethod");
           	  String wFactWage=Database.rs.getString("W_FactWage");
           	  stid.setText(wNumber);
           	  stname.setText(wName);
           	  stsalary.setText(wBasicWage);
        	  stboon.setText(wBoon);
        	  stprize.setText(wBonus);
        	  stcounter.setText(wCountMethod);
        	  stfact.setText(wFactWage);
          	}
          	}
          	catch(Exception eup){
          	  System.out.println("以到最前一条!");
          	}
          }
       });
       next.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
           	 try{
           	 if(Database.rs.next()){
           	  String wNumber=(""+Database.rs.getString("W_Number"));
           	  String wName=Database.rs.getString("W_Name");
           	  String wBasicWage=Database.rs.getString("W_BasicWage");
           	  String wBoon=Database.rs.getString("W_Boon");
           	  String wBonus=Database.rs.getString("W_Bonus");
           	  String wCountMethod=Database.rs.getString("W_CountMethod");
           	  String wFactWage=Database.rs.getString("W_FactWage");
           	  stid.setText(wNumber);
           	  stname.setText(wName);
           	  stsalary.setText(wBasicWage);
        	  stboon.setText(wBoon);
        	  stprize.setText(wBonus);
        	  stcounter.setText(wCountMethod);
        	  stfact.setText(wFactWage);
           	 }
           	 }
           	 catch(Exception enext){
           	 	System.out.println("以到最后一条");
           	 }
           }
       });
//为添加删除保存修改按钮加事件----------------------------------------
     //添加
     btnadd.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
           	save.setEnabled(true);
           	stid.setText("");
           	stid.setEditable(false);
           	stname.setText("");
           	stsalary.setText("");
        	stboon.setText("");
        	stprize.setText("");
        	stcounter.setText("");
        	stfact.setText("");
        }
     });
     //保存
     save.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        	if(stname.getText().equals("")||stsalary.getText().equals("")||stboon.getText().equals("")||
        	stprize.getText().equals("")||stcounter.getText().equals("")||stfact.getText().equals("")){
        		System.out.println("以上信息必须填写!");
        	}
        	else{
           	 // String wNumber=stid.getText();
           	  String wName=stname.getText();
           	  String wBasicWage=stsalary.getText();
           	  String wBoon=stboon.getText();
           	  String wBonus=stprize.getText();
           	  String wCountMethod=stcounter.getText();
           	  String wFactWage=stfact.getText();
           	  String Insert="insert WageInformation values('"+wName+"','"+wBasicWage+"','"+wBoon+"','"+wBonus+"','"+wCountMethod+"','"+wFactWage+"')";
              try{
              	if(Database.executeSQL(Insert)){
              		stid.setEditable(true);
              		save.setEnabled(false);
              		new JOptionPane().showMessageDialog(null,"添加数据成功！");
              		Database.joinDB();
              		String sql="select * from WageInformation";
              		Database.query(sql);
              		Database.rs.last();

		           	  String wNumber1=(""+Database.rs.getString("W_Number"));
		           	  String wName1=Database.rs.getString("W_Name");
		           	  String wBasicWage1=Database.rs.getString("W_BasicWage");
		           	  String wBoon1=Database.rs.getString("W_Boon");
		           	  String wBonus1=Database.rs.getString("W_Bonus");
		           	  String wCountMethod1=Database.rs.getString("W_CountMethod");
		           	  String wFactWage1=Database.rs.getString("W_FactWage");
		           	  stid.setText(wNumber1);
		           	  stname.setText(wName1);
		           	  stsalary.setText(wBasicWage1);
		        	  stboon.setText(wBoon1);
		        	  stprize.setText(wBonus1);
		        	  stcounter.setText(wCountMethod1);
		        	  stfact.setText(wFactWage1);
                }
                else{
   	  				new JOptionPane().showMessageDialog(null,"添加数据不成功！");
                }
              }
              catch(Exception esave){
   	  				new JOptionPane().showMessageDialog(null,"添加数据不成功！");
              }
         	}
        }

     });
   //删除
   delete.addActionListener(new ActionListener(){
   	  public void actionPerformed(ActionEvent edel){
   	  	try{
   	  	   String sqle="delete from WageInformation where W_Number ='"+stid.getText()+"'";
   	  	   System.out.println(sqle);
   	       if(Database.executeSQL(sqle)){
   			  new JOptionPane().showMessageDialog(null,"数据删除成功！");
   			  Database.joinDB();
   	  		  String sqll="select * from WageInformation";
   	  		  Database.query(sqll);
   	  		  Database.rs.last();
           	  String wNumber1=(""+Database.rs.getString("W_Number"));
           	  String wName1=Database.rs.getString("W_Name");
           	  String wBasicWage1=Database.rs.getString("W_BasicWage");
           	  String wBoon1=Database.rs.getString("W_Boon");
           	  String wBonus1=Database.rs.getString("W_Bonus");
           	  String wCountMethod1=Database.rs.getString("W_CountMethod");
           	  String wFactWage1=Database.rs.getString("W_FactWage");
	          stid.setText(wNumber1);
           	  stname.setText(wName1);
           	  stsalary.setText(wBasicWage1);
        	  stboon.setText(wBoon1);
        	  stprize.setText(wBonus1);
        	  stcounter.setText(wCountMethod1);
        	  stfact.setText(wFactWage1);
   	     }
   	   }
   	   catch(Exception edelete){
   	   	  System.out.println("数据删除失败!");
   	   }
   	  }

   });
   //修改
   updete.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent eupd){
      	 try{
   			String supdate="update WageInformation set W_Name='"+ stname.getText()+"',W_BasicWage='"+ stid.getText() +"',W_Boon='"+stboon.getText()+"',W_Bonus='"+stprize.getText()+"',W_CountMethod='"+stcounter.getText()+"',W_FactWage='"+stfact.getText()+"' where W_Number='"+ stid.getText()+"'";
      	 	if(Database.executeSQL(supdate)){
   			  new JOptionPane().showMessageDialog(null,"数据修改成功！");
   			  Database.joinDB();
   	  		  String sqll="select * from WageInformation";
   	  		  Database.query(sqll);
   	  		  Database.rs.last();
           	  String wNumber1=(""+Database.rs.getString("W_Number"));
           	  String wName1=Database.rs.getString("W_Name");
           	  String wBasicWage1=Database.rs.getString("W_BasicWage");
           	  String wBoon1=Database.rs.getString("W_Boon");
           	  String wBonus1=Database.rs.getString("W_Bonus");
           	  String wCountMethod1=Database.rs.getString("W_CountMethod");
           	  String wFactWage1=Database.rs.getString("W_FactWage");
	          stid.setText(wNumber1);
           	  stname.setText(wName1);
           	  stsalary.setText(wBasicWage1);
        	  stboon.setText(wBoon1);
        	  stprize.setText(wBonus1);
        	  stcounter.setText(wCountMethod1);
        	  stfact.setText(wFactWage1);
        	}
      	 }
      	 catch(Exception eupdete){
      	 	System.out.println("修改数据失败!");
      	 }
      }


   });
//---------------------------------------------------------------------------------
		setSize(460,350);
		this.setClosable(true);
		setVisible(true);
		}

	}
