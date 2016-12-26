//培训管理类


package classsource;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Trainmanage extends JInternalFrame {
	
	private JButton btadd,btamend,btdelet,btleft,btright,btsave;
    private JLabel lb1,lb2,lb3,lb4,lb5;
    private JTextField tcontent,tdate,tmoney,tnumber,tname;

    public Trainmanage() {

        initComponents();
    }

    private void initComponents() {

    	setTitle("培训信息管理");
    	Font f = new Font("新宋体", 0, 14);
        lb1 = new JLabel("员工培训信息管理");
        lb2 = new JLabel("培训编号:");
        lb3 = new JLabel("培训内容:");
        lb4 = new JLabel("培训天数:");
        lb5 = new JLabel("培训费用:");
        JLabel lb6 = new JLabel("员工姓名：");
        tnumber = new JTextField();
        tcontent = new JTextField();
        tmoney = new JTextField();
        tdate = new JTextField();
        tname = new JTextField();
        btright = new JButton("<<");
        btleft = new JButton(">>");
        btdelet = new JButton("删除");
        btamend = new JButton("修改");
        btsave = new JButton("保存");
        btsave.setEnabled(false);
        btadd = new JButton("添加");

        getContentPane().setLayout(null);

        lb1.setFont(new java.awt.Font("新宋体", 1, 18));
        lb1.setForeground(new Color(0, 51, 255));

        getContentPane().add(lb1);
        lb1.setBounds(110, 10, 160, 30);

        lb6.setFont(f);
        getContentPane().add(lb6);
        lb6.setBounds(30,40,70,20);

        lb2.setFont(f);
        getContentPane().add(lb2);
        lb2.setBounds(30, 80, 70, 20);

        lb3.setFont(f);
        getContentPane().add(lb3);
        lb3.setBounds(30, 200, 70, 20);

        lb4.setFont(f);
        getContentPane().add(lb4);
        lb4.setBounds(30, 120, 70, 20);

        lb5.setFont(f);
        getContentPane().add(lb5);
        lb5.setBounds(30, 160, 70, 20);

        getContentPane().add(tname);
        tname.setBounds(120,40,110,23);

        getContentPane().add(tnumber);
        tnumber.setBounds(120, 80, 110, 23);

        getContentPane().add(tcontent);
        tcontent.setBounds(120, 200, 170, 23);

        getContentPane().add(tmoney);
        tmoney.setBounds(120, 160, 140, 23);

        getContentPane().add(tdate);
        tdate.setBounds(120, 120, 110, 23);
        btright.setFont(f);
        getContentPane().add(btright);
        btright.setBounds(330, 255, 50, 20);

        btleft.setFont(f);
        getContentPane().add(btleft);
        btleft.setBounds(330, 235, 50, 20);

        btdelet.setFont(f);
        getContentPane().add(btdelet);
        btdelet.setBounds(250, 245, 70, 25);

        btamend.setFont(f);
        getContentPane().add(btamend);
        btamend.setBounds(170, 245, 70, 25);

        btsave.setFont(f);
        getContentPane().add(btsave);
        btsave.setBounds(10, 245, 70, 25);

        btadd.setFont(f);
        getContentPane().add(btadd);
        btadd.setBounds(90, 245, 70, 25);

//连接数据库
        Database.joinDB();
//初始化窗体数据-----------------------------------------------------------------------------
        String sql="select * from TrainInformation";
        try{
        	if(Database.query(sql)){
        		Database.rs.next();
        		tname.setText(Database.rs.getString("T_Name"));
        		tnumber.setText(Database.rs.getString("T_Number"));
                tcontent.setText(Database.rs.getString("T_Content"));
                tmoney.setText(Database.rs.getString("T_Money"));
                tdate.setText(Database.rs.getString("T_Date"));
        		}
        	}
        catch(Exception e){
        	System.out.println(e);
        	}

//----------------------------------------------------------------------------------------

//为上一条下一条按钮加事件-----------------------------------------------------------------
        btright.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		try{
        			if(Database.rs.previous()){
		        		tname.setText(Database.rs.getString("T_Name"));
		        		tnumber.setText(Database.rs.getString("T_Number"));
		                tcontent.setText(Database.rs.getString("T_Content"));
		                tmoney.setText(Database.rs.getString("T_Money"));
		                tdate.setText(Database.rs.getString("T_Date"));
        				}
        			}
        		catch(Exception er){}
        		}
        	});

        btleft.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		try{
        			if(Database.rs.next()){
		        		tname.setText(Database.rs.getString("T_Name"));
		        		tnumber.setText(Database.rs.getString("T_Number"));
		                tcontent.setText(Database.rs.getString("T_Content"));
		                tmoney.setText(Database.rs.getString("T_Money"));
		                tdate.setText(Database.rs.getString("T_Date"));
        				}
        			}
        		catch(Exception el){}
               }
        	});
//------------------------------------------------------------------

//为添加保存按钮加事件------------------------------------------------------------
        btadd.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		btsave.setEnabled(true);
        		tnumber.setText("");
		        tcontent.setText("");
		        tmoney.setText("");
		        tdate.setText("");
		        tname.setText("");
               }
        	});

        btsave.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		if(tnumber.getText().equals("")||tcontent.getText().equals("")||tmoney.getText().equals("")
        		   ||tdate.getText().equals("")||tname.getText().equals("")){
        		   	new JOptionPane().showMessageDialog(null,"所有数据均不能为空！");
        		   	}
        		else{
        			String sqlInsert="insert TrainInformation values('"+ tnumber.getText() +"'," +
        			                 "'"+ tcontent.getText() +"','"+ tname.getText() +"','"+ tdate.getText() +"'," +
        			                 "'"+ tmoney.getText() +"')";
        			try{
        				if(Database.executeSQL(sqlInsert)){
        					new JOptionPane().showMessageDialog(null,"数据添加成功！");
        					Database.joinDB();
        					String sql="select * from TrainInformation";
        					Database.query(sql);
        					}
        				}
        			catch(Exception einsert){}
        			}
               }
        	});
//-----------------------------------------------------------------------------------

//为修改删除按钮加事件---------------------------------------------------------------
        btdelet.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		String sqldelete="delete from TrainInformation where T_Number='"+ tnumber.getText() +"'";
                try{
                	if(Database.executeSQL(sqldelete)){
                		new JOptionPane().showMessageDialog(null,"数据删除成功！");
    					Database.joinDB();
    					String sql="select * from TrainInformation";
    					Database.query(sql);
    					Database.rs.first();
		        		tname.setText(Database.rs.getString("T_Name"));
		        		tnumber.setText(Database.rs.getString("T_Number"));
		                tcontent.setText(Database.rs.getString("T_Content"));
		                tmoney.setText(Database.rs.getString("T_Money"));
		                tdate.setText(Database.rs.getString("T_Date"));
                		}
                	}
                catch(Exception edelete){}
               }
        	});

        btamend.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        	   String sqlupdate="update TrainInformation set T_Money='"+ tmoney.getText() +"'," +
        	                    "T_Content='"+ tcontent.getText() +"',T_Name='"+ tname.getText() +"',"+
        	                    "T_Date='"+ tdate.getText() +"' where T_Number='"+ tnumber.getText()+"'";
                try{
                	if(Database.executeSQL(sqlupdate)){
                		new JOptionPane().showMessageDialog(null,"数据修改成功！");
    					Database.joinDB();
    					String sql="select * from TrainInformation";
    					Database.query(sql);
                		}
                	}
                catch(Exception edelete){}
                }
        	});
//-----------------------------------------------------------------------------------
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-403)/2, (screenSize.height-329)/2, 403, 329);
        this.setClosable(true);
        setVisible(true);
    }

//    public static void main(String args[]) {
//
//                new Trainmanage().setClosable(true);
//    }

}
