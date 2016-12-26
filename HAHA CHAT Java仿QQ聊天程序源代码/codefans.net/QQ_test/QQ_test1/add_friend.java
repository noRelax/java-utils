package QQ_test1;

import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

import javax.swing.*;

public class add_friend extends JFrame implements ActionListener,ItemListener
{
	private String num1,num2,group_name,nickname1,nickname2;
	private JLabel top_label,main_label;
	private JComboBox group_option;
	private RecButton summit_button,cancle_button;
	private msg_sender send;
	private CheckboxGroup cg;
	private Checkbox r1,r2;
	private JTextField group_txt;
	private String sql="";
	public add_friend(String n1,String n2,String name1,String name2)	//num1添加num2为好友
	{
		num1=n1;
		num2=n2;
		nickname1=name1;
		nickname2=name2;
		pane_ini();
	}
	public void pane_ini()
	{
		setTitle("HAHA好友添加窗口");
		setSize(320,235);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e){e.printStackTrace();}
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		group_option=new JComboBox();
		ini_group();
		top_label=new JLabel("<html>添加好友</html>");
		main_label=new JLabel();
		main_label.setLayout(null);
		
		top_label.setForeground(Color.red);
		top_label.setFont(new Font("黑体",Font.BOLD,40));
		
		summit_button=new RecButton("添加");
		cancle_button=new RecButton("取消");
		summit_button.setForeground(Color.green);
		cancle_button.setForeground(Color.green);
		summit_button.addActionListener(this);
		cancle_button.addActionListener(this);
		summit_button.setFont(new Font("宋体",Font.PLAIN,15));
		cancle_button.setFont(new Font("宋体",Font.PLAIN,15));
		
		cg=new CheckboxGroup();
		r1=new Checkbox("选择好友分组:",cg,true);
		r2=new Checkbox("添加好友分组:",cg,false);
		r1.setForeground(Color.green);
		r2.setForeground(Color.green);
		r1.setFont(new Font("宋体",Font.BOLD,15));
		r2.setFont(new Font("宋体",Font.BOLD,15));
		r1.addItemListener(this);
		r2.addItemListener(this);
		r1.setBackground(Color.black);
		r2.setBackground(Color.black);
		
		group_txt=new JTextField();
		group_txt.setEnabled(false);
		
		top_label.setBounds(new Rectangle(80, 10,290, 60));
		r1.setBounds(new Rectangle(40,80,120,25));
		group_option.setBounds(new Rectangle(170, 80,110, 25));
		r2.setBounds(new Rectangle(40,110,120,25));
		group_txt.setBounds(new Rectangle(170,110,110, 25));
		summit_button.setBounds(new Rectangle(90, 150,70, 30));
		cancle_button.setBounds(new Rectangle(170, 150,70, 30));
		
		main_label.add(top_label);
		main_label.add(r1);
		main_label.add(group_option);
		main_label.add(r2);
		main_label.add(group_txt);
		main_label.add(summit_button);
		main_label.add(cancle_button);
		main_label.setBounds(new Rectangle(0, 0,320, 200));
		c.add(main_label);
		this.getRootPane().setDefaultButton(summit_button);
	}
	public void ini_group()
	{
		String sql="select group_name from friend_group where id='"+num1+"'";
  		try{
  			send=new msg_sender(1,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			while(str.hasMoreTokens())
			{
				group_option.addItem((String)str.nextToken().trim());
			}
  		}catch(Exception e){System.out.println("读取昵称时出错");}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("添加"))
		{
			if(r1.getState())			//选择好友分组
			{
				group_name=(String)group_option.getSelectedItem();
				sql="insert into friend(id,friend_id,remark,firmed,group_name) values('"+num1+"'," +
					"'"+num2+"','"+nickname2+"','true','"+group_name+"')";
				try{
					send=new msg_sender(0,sql);
					
					sql="insert into friend(id,friend_id,remark,firmed,group_name) values('"+num2+"'," +
					"'"+num1+"','"+nickname1+"','true','陌生人')";
					send=new msg_sender(0,sql);
					JOptionPane.showMessageDialog(null,"服务器已接收到您的请求","消息",1);
					dispose();
				}catch(Exception e2){System.out.println("添加好友错误");}
			}
			else if(r2.getState())		//添加好友分组
			{
				group_name=group_txt.getText().trim();
				if(group_name.equals(""))
				{
					JOptionPane.showMessageDialog(null,"组名不能为空","警告",1);
					return;
				}
				else
				{
					sql="select group_name from friend_group where group_name='"+group_name+"' and id='"+num1+"'";
					try{
						send=new msg_sender(1,sql);
						String msg=send.r_msg();
						StringTokenizer str=new StringTokenizer(msg,"~");
						if(str.hasMoreTokens())
						{
							JOptionPane.showMessageDialog(null,"该分组已经存在","警告",1);
							return;
						}
					}catch(Exception e2){System.out.println("添加好友错误");}
					
					
					try{
						sql="insert into friend_group(id,group_name) values('"+num1+"','"+group_name+"')";
						send=new msg_sender(0,sql);
						
						sql="insert into friend(id,friend_id,remark,firmed,group_name) values('"+num1+"'," +
						"'"+num2+"','"+nickname2+"','true','"+group_name+"')";
						send=new msg_sender(0,sql);
						
						sql="insert into friend(id,friend_id,remark,firmed,group_name) values('"+num2+"'," +
						"'"+num1+"','"+nickname1+"','true','陌生人')";
						send=new msg_sender(0,sql);
						JOptionPane.showMessageDialog(null,"服务器已接收到您的请求","消息",1);
						dispose();
					}catch(Exception e2){System.out.println("添加好友错误");}
				}
			}
		}
		else if(e.getActionCommand().equals("取消"))
		{
			dispose();
		}
	}
	public static void main(String args[])
	{
		add_friend a=new add_friend("1111","6666","nickname","nickname2");
		a.setVisible(true);
		
	}
	public void itemStateChanged(ItemEvent item) 
	{
		if(item.getItem().equals("选择好友分组:"))
		{
			group_option.setEnabled(true);
			group_txt.setEnabled(false);
			group_option.requestFocus();
		}
		else if(item.getItem().equals("添加好友分组:"))
		{
			group_option.setEnabled(false);
			group_txt.setEnabled(true);
			group_txt.requestFocus();
		}
		
	}
}
