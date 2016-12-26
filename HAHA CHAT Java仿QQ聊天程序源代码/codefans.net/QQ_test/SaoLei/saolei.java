package SaoLei;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
import haha_game.RecButton;
public class saolei extends JFrame implements ActionListener{
	private int n=5;
	private RecButton submit_button;
	private JLabel msg_label,name_label;
	private CheckboxGroup ch;
	private Checkbox c1,c2,c3,c4;
	public saolei()
	{
	setTitle("brick");
	setSize(320,200);
	setLocationRelativeTo(null); 
	Container c=getContentPane();
	c.setLayout(null);
	c.setBackground(Color.black);
	
	name_label=new JLabel("HAHA极品扫雷");	
	name_label.setFont(new Font("宋体",Font.BOLD,35));	
	name_label.setForeground(Color.red);
	
	msg_label=new JLabel("请选择游戏级别");	
	msg_label.setFont(new Font("宋体",Font.BOLD,18));	
	msg_label.setForeground(Color.red);
	submit_button=new RecButton("SUBMIT");
	submit_button.addActionListener(this);
	
	ch=new CheckboxGroup(); 
	c1=new Checkbox("傻逼",ch,true);
	c2=new Checkbox("正常",ch,false);
	c3=new Checkbox("牛逼",ch,false);
	c4=new Checkbox("变态",ch,false);

	c1.setForeground(Color.green);
	c2.setForeground(Color.green);
	c3.setForeground(Color.green);
	c4.setForeground(Color.green);
	
	name_label.setBounds(new Rectangle(50,5,250,50));
	msg_label.setBounds(new Rectangle(100,55,150,50));
	c1.setBounds(new Rectangle(45,100,40,10));
	c2.setBounds(new Rectangle(110,100,40,10));
	c3.setBounds(new Rectangle(175,100,40,10));
	c4.setBounds(new Rectangle(240,100,40,10));
	submit_button.setBounds(new Rectangle(120,120,80,30));
	
	c.add(name_label);
	c.add(msg_label);
	c.add(c1);c.add(c2);c.add(c3);c.add(c4);
	c.add(submit_button);
}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("SUBMIT"))
		{
			if(c1.getState())
				n=10;
			else if(c2.getState())
				n=20;
			else if(c3.getState())
				n=25;
			else if(c4.getState())
				n=30;
			this.dispose();
			main m=new main(n);
			m.setVisible(true);
		}
		
	}
}
