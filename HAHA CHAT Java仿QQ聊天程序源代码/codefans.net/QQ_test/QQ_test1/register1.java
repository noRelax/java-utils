package QQ_test1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class register1  extends JFrame implements ActionListener,ItemListener
{
	private JTextArea agreement;
	private RecButton next_button;
	private CheckboxGroup cg;
	private Checkbox r1,r2;
	private JLabel top_label;
	public register1()
	{
		setTitle("HAHA-CHAT注册窗口");
		setSize(220,390);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
		try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}catch(Exception e){e.printStackTrace();}
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		
    	agreement=new JTextArea();
    	next_button=new RecButton("NEXT >>");
    	top_label=new JLabel("HAHA—CHAT服务协议");
    	top_label.setFont(new Font("宋体",Font.BOLD,16));
    	
    	cg=new CheckboxGroup(); 
		r1=new Checkbox("接受",cg,false);
		r2=new Checkbox("拒绝",cg,true);
    	
		r1.addItemListener(this);
		r2.addItemListener(this);
		next_button.addActionListener(this);
		agreement.setText("知识产权声明:\n" +
				"    “软件”的一切版权、商标权、专利权、商业秘密等知识产权，归HAHA所有。\n" +
				"用户使用须知:\n" +
				"    HAHA帐号的所有权归HAHA，用户完成申请注册手续后，获得HAHA帐号的使用权。\n" +
				"附加条款:\n" +
				"    本着有效利用HAHA帐号资源，保障更多合法用户权益为目的，对于恶意注册或者长期不登录的HAHA帐号，系统有权回收。");
		agreement.setLineWrap(true);
		
		agreement.setEditable(false);
		next_button.setEnabled(false);
		top_label.setForeground(Color.green);
		agreement.setForeground(Color.BLACK);
		next_button.setForeground(Color.gray);
		r1.setForeground(Color.green);
		r2.setForeground(Color.green);
    	
		top_label.setBounds(new Rectangle(25, 15,200,20));
		agreement.setBounds(new Rectangle(5, 35,200,260));
		r1.setBounds(new Rectangle(40, 300,70,20));
		r2.setBounds(new Rectangle(120, 300,70,20));
		next_button.setBounds(new Rectangle(65, 320,80,25));
		
		c.add(top_label);
		c.add(agreement);
		c.add(r1);
		c.add(r2);
		c.add(next_button);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("NEXT >>"))
		{
			register2 r=new register2();
			r.setVisible(true);
			dispose();
		}
	}
	public static void main(String args[])
	{
		register1 r=new register1();
		r.setVisible(true);
	}
	public void itemStateChanged(ItemEvent e) 
	{
		if(e.getItem().equals("接受"))
		{
			next_button.setEnabled(true);
			next_button.setForeground(Color.green);
		}
		else if(e.getItem().equals("拒绝"))
		{
			next_button.setEnabled(false);
			next_button.setForeground(Color.gray);
		}
		
	}
}
