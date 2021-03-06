package flight.manage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class UpdatePanel extends JPanel implements ItemListener,ActionListener
{
	private JTextField[] jtf=new JTextField[12];
	private JCheckBox[] radio=new JCheckBox[12];
	private JButton button=new JButton("确定");
	//it is used for the connection of the datebase
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private int flag=1;
	private boolean tag=false;
	
	public UpdatePanel()
	{
		//connect the database
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(Exception ex)
		{
		}
		//*********************************************
		JPanel p1=new JPanel();
		p1.setBorder(new TitledBorder("基本信息"));
		p1.setLayout(new GridLayout(6,4,5,5));
		p1.add(radio[0]=new JCheckBox("航班号    "));
		p1.add(jtf[0]=new JTextField(10));
		p1.add(radio[1]=new JCheckBox("星期      "));
		p1.add(jtf[1]=new JTextField(10));
		p1.add(radio[2]=new JCheckBox("公司      "));
		p1.add(jtf[2]=new JTextField(10));
		p1.add(radio[3]=new JCheckBox("座位      "));
		p1.add(jtf[3]=new JTextField(10));
		p1.add(radio[4]=new JCheckBox("起飞地    "));
		p1.add(jtf[4]=new JTextField(10));
		p1.add(radio[5]=new JCheckBox("抵达地    "));
		p1.add(jtf[5]=new JTextField(10));
		p1.add(radio[6]=new JCheckBox("起飞时间  "));
		p1.add(jtf[6]=new JTextField(10));
		p1.add(radio[7]=new JCheckBox("抵达时间  "));
		p1.add(jtf[7]=new JTextField(10));
		p1.add(radio[8]=new JCheckBox("儿童票价  "));
		p1.add(jtf[8]=new JTextField(10));
		p1.add(radio[9]=new JCheckBox("成人票价  "));
		p1.add(jtf[9]=new JTextField(10));
		p1.add(radio[10]=new JCheckBox("提前折扣"));
		p1.add(jtf[10]=new JTextField(10));
		p1.add(radio[11]=new JCheckBox("退票率"));
		p1.add(jtf[11]=new JTextField(10));
		//********************************************
		JPanel p2=new JPanel();
		p2.setBorder(new TitledBorder("操作"));
		p2.add(button);
		
		this.setLayout(new BorderLayout());
		this.add(p1,BorderLayout.CENTER);
		this.add(p2,BorderLayout.SOUTH);
		
		//at first the textfield is uneditable
		for(int i=0;i<=11;i++)
		jtf[i].setEditable(false);
		
		//add listener to the radiobutton
		for(int i=0;i<=11;i++)
		radio[i].addItemListener(this);
		
		//add listener to the button
		button.addActionListener(this);
		
		
	}
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getSource() instanceof JCheckBox)
		{
			//if the week is selected ,tell him the style
			if(radio[1].isSelected()&&flag==1)
			{JOptionPane.showMessageDialog(null,"输入格式如135","暗示",JOptionPane.WARNING_MESSAGE);flag=0;}
			for(int i=0;i<=11;i++)
			
			//change the chracter of the textfield
			if(radio[i].isSelected()) jtf[i].setEditable(true);
			for(int i=0;i<=11;i++)
			if(!radio[i].isSelected()) jtf[i].setEditable(false);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
		//connect the database
		String sql="";
		con = DriverManager.getConnection("jdbc:odbc:dsStudent","sa","");
	    stmt = con.createStatement();
		if(e.getSource() instanceof JButton)
		{
			
			
			//the textfield of the key word cannot be empty
			if(!radio[0].isSelected()||(radio[0].isSelected()&&jtf[0].getText().length()==0)) 
			JOptionPane.showMessageDialog(null,"关键字不能为空","错误",JOptionPane.ERROR_MESSAGE);
			
			else
			{
				//make sure every textfield selectde is full
				for(int i=2;i<=11;i++)
					if(radio[i].isSelected()&&jtf[i].getText().length()==0)
					{
						tag=true;break;
					}
				//if tere is textfield if not seleced 
				if(tag)
				{
					JOptionPane.showMessageDialog(null,"信息不能为空","错误",JOptionPane.ERROR_MESSAGE);
					tag = false;
				}
				   
				else
				{
					String sqlString = "select flight from flight where flight='" + jtf[0].getText().trim() + "'";
					ResultSet rs = stmt.executeQuery(sqlString);
					//decide the filght is exited
					int flag1 = 0;
					while(rs.next())
					{
						flag1 = 1;
					}
					//if it is exits
					if (flag1 == 0)
					{
						JOptionPane.showMessageDialog(null,"对不起!航班号不存在!",
						                              "错误信息",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//update the week
					if(radio[2].isSelected())
					{					
						sql="update flight set airFirm='"+jtf[2].getText()+"' where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);   
					}
				
			
				    //update the airfirm 
					if(radio[3].isSelected())
					{
						sql="update flight set seat="+Integer.parseInt(jtf[3].getText().trim())+" where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);				   
					}
			        //update the airfirm 
					if(radio[4].isSelected())
					{
						sql="update flight set start='"+jtf[4].getText()+"' where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					//update the airfirm 
					
					if(radio[5].isSelected())
					{
						sql="update flight set destination='"+jtf[5].getText()+"' where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					//update the airfirm 
					if(radio[6].isSelected())
					{
						sql="update flight set leaveTime='"+jtf[6].getText()+"' where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					//update the  
					if(radio[7].isSelected())
					{
						sql="update flight set arriveTime='"+jtf[7].getText()+"' where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					//update the childFare 
					if(radio[8].isSelected())
					{
						sql="update flight set childFare="+Float.parseFloat(jtf[8].getText().trim())+" where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					//update the adultFare 
					if(radio[9].isSelected())
					{
						sql="update flight set adultFare="+Float.parseFloat(jtf[9].getText().trim())+" where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					//update the disount 
					if(radio[10].isSelected())
					{
						sql="update flight set discount1="+Float.parseFloat(jtf[10].getText().trim())+" where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					////update the tuipiaoliu 
					if(radio[11].isSelected())
					{
						sql="update flight set discount2="+Float.parseFloat(jtf[11].getText().trim())+" where flight='"+jtf[0].getText()+"'";
					    stmt.executeUpdate(sql);
					}
					
					JOptionPane.showMessageDialog(null,"航班信息已经更新成功!",
					                              "成功信息",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
			
		}
		}
		catch(Exception ex)
		{
			
		}
	}
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ex)
		{
			
		}
		JFrame frame=new JFrame();
		frame.getContentPane().add(new UpdatePanel());
		frame.setSize(400,350);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}