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
	
	private JButton jbQuery = new JButton("��ѯ");
	private JButton jbManager = new JButton("����");
	private JButton jbDingPiao = new JButton("��Ʊ");
	private JButton jbTuiPiao = new JButton("��Ʊ");
	private JButton jbAbout = new JButton("����");
	
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
		    query.setTitle("�����ѯϵͳ");
		    query.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    query.setVisible(true);
		}
		   
		else if (e.getSource() == jbManager )
		{
			String zhangHao = JOptionPane.showInputDialog(null,"����������ʺ�:",
			                                              "�ʺ���֤",JOptionPane.PLAIN_MESSAGE);
			if (zhangHao == null)
			   return;
			if (!zhangHao.equals("0302") )
			{
				JOptionPane.showMessageDialog(null,"�Բ���!����ʺŲ���ȷ!",
				                              "�ʺŴ���",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String password = JOptionPane.showInputDialog(null,"�������������:",
			                                              "������֤",JOptionPane.PLAIN_MESSAGE);
			if (password == null )
			    return;
			if (!password.equals("0302"))
			{
				JOptionPane.showMessageDialog(null,"�Բ���!������벻��ȷ!",
				                              "�ʺŴ���",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			manager = new TestDB();
			
			manager.setSize(470,370);
			manager.setResizable(false);
		    manager.setTitle("�������ϵͳ");
		    manager.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			manager.setVisible(true);
		}
		
		else if (e.getSource() == jbDingPiao)
		{
			dingPiao = new Hangkong();
			
			dingPiao.setSize(430,300);
			dingPiao.setResizable(false);
			dingPiao.setTitle("���ڻ�Ʊʵʱ�ٶ�");
			dingPiao.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);			
			dingPiao.setVisible(true);
		}
		   
		else if (e.getSource() == jbTuiPiao)
		{
			tuiPiao = new TuiPiao();
			
			tuiPiao.setSize(470,370);
			tuiPiao.setResizable(false);
	    	tuiPiao.setTitle("������Ʊϵͳ");
	    	tuiPiao.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	    	
	    	tuiPiao.setVisible(true);
		}
		
		else if (e.getSource() == jbAbout)
		{
			String information = "������:" + "  �⺣�� ���� ���� ��ݼ" + "\n" +
           	  	                 "�汾: " + "     1.2" + "\n" + 
           	  	                 "ʱ��: " + "     2004-12" + "\n" +
           	  	                 "��ַ: " + "     ��������ҵ��ѧ���ѧԺ03��2��";
           	  	                 
			JOptionPane.showMessageDialog(null,information,"����",JOptionPane.INFORMATION_MESSAGE);
		}		   
	}
}