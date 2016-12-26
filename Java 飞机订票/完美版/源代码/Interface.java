package flight.Main;

import flight.query.*;
import flight.manage.*;
import flight.dingpiao.*;
import flight.tuipiao.TuiPiao;
import flight.assist.*;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.net.URL;

class Interface extends JPanel implements ActionListener
{
	private MyQuery query;
	private TestDB manager;
	private TuiPiao tuiPiao;
	private Hangkong dingPiao;
	
	private JButton jbQuery = new JButton("查询");
	private JButton jbManager = new JButton("管理");
	private JButton jbDingPiao = new JButton("订票");
	private JButton jbTuiPiao = new JButton("退票");
	private JButton jbAbout = new JButton("关于");
	
	public Interface()
	{
		this.setLayout(null);
		this.add(jbQuery);	
		this.add(jbDingPiao);
		this.add(jbTuiPiao);
		this.add(jbManager);
		this.add(jbAbout);
		
		jbQuery.setFont(new Font("Times",Font.PLAIN,12));
		jbDingPiao.setFont(new Font("Times",Font.PLAIN,12));
		jbTuiPiao.setFont(new Font("Times",Font.PLAIN,12));
		jbManager.setFont(new Font("Times",Font.PLAIN,12));
		jbAbout.setFont(new Font("Times",Font.PLAIN,12));
		
		jbQuery.setBounds(90,280,80,30);		
		jbDingPiao.setBounds(190,280,80,30);
		jbTuiPiao.setBounds(290,280,80,30);
		jbManager.setBounds(390,280,80,30);
		jbAbout.setBounds(430,350,80,30);
		
		jbQuery.addActionListener(this);
		jbManager.addActionListener(this);
		jbDingPiao.addActionListener(this);
		jbTuiPiao.addActionListener(this);
		jbAbout.addActionListener(this);
	}
	
	public void paintComponent(Graphics g)
	{	    	
		ImageIcon imageIcon = new ImageIcon("jiemian.gif");
		Image image = imageIcon.getImage();
		
		g.drawImage(image,0,0,this);		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == jbQuery)
		{
			query = new MyQuery();
			
			query.setSize(470,370);
		//	query.setResizable(false);
		    query.setTitle("航班查询系统");
		    query.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    query.setVisible(true);
		}
		   
		else if (e.getSource() == jbManager )
		{
			String zhangHao = JOptionPane.showInputDialog(null,"请输入你的帐号:",
			                                              "帐号验证",JOptionPane.PLAIN_MESSAGE);
			if (zhangHao == null)
			   return;
			if (!zhangHao.equals("0302") )
			{
				JOptionPane.showMessageDialog(null,"对不起!你的帐号不正确!",
				                              "帐号错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String password = JOptionPane.showInputDialog(null,"请输入你的密码:",
			                                              "密码验证",JOptionPane.PLAIN_MESSAGE);
			if (password == null )
			    return;
			if (!password.equals("0302"))
			{
				JOptionPane.showMessageDialog(null,"对不起!你的密码不正确!",
				                              "帐号错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			manager = new TestDB();
			
			manager.setSize(470,370);
			manager.setResizable(false);
		    manager.setTitle("航班管理系统");
		    manager.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			manager.setVisible(true);
		}
		
		else if (e.getSource() == jbDingPiao)
		{
			dingPiao = new Hangkong();
			
			dingPiao.setSize(430,300);
			dingPiao.setResizable(false);
			dingPiao.setTitle("国内机票实时速定");
			dingPiao.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);			
			dingPiao.setVisible(true);
		}
		   
		else if (e.getSource() == jbTuiPiao)
		{
			tuiPiao = new TuiPiao();
			
			tuiPiao.setSize(470,370);
			tuiPiao.setResizable(false);
	    	tuiPiao.setTitle("航班退票系统");
	    	tuiPiao.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	    	
	    	tuiPiao.setVisible(true);
		}
		
		else if (e.getSource() == jbAbout)
		{
			String information = "制作人:" + "  吴海贤 陈振 孟磊 余菁" + "\n" +
           	  	                 "版本: " + "     1.2" + "\n" + 
           	  	                 "时间: " + "     2004-12" + "\n" +
           	  	                 "地址: " + "     哈尔滨工业大学软件学院03级2班";
           	  	                 
			JOptionPane.showMessageDialog(null,information,"关于",JOptionPane.INFORMATION_MESSAGE);
		}		   
	}
}