package QQ_test1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
public class search_result  extends JFrame implements ActionListener
{
private JLabel result_label;
private RecButton commit_button,cancle_button;
private table result_table;
private Vector array,header;     //row为行，vec为列
private String num,nick_name,group_name="";
msg_sender send;
String sql;
public search_result(Vector m,String n,String nick)
{
	array=new Vector();
	array=m;num=n;nick_name=nick;
	pane_ini();
}
public search_result(Vector m,String n,String nick,String gr)
{
	array=new Vector();
	array=m;num=n;nick_name=nick;group_name=gr;
	pane_ini();
}
public void pane_ini()
{
	setTitle("HAHA好友查询窗口");
	setSize(520,390);
	setBackground(Color.BLACK);
	setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
	try
	{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	}catch(Exception e){e.printStackTrace();}
	Container c = getContentPane();
	c.setLayout(null);
	c.setBackground(Color.black);
	
	result_label=new JLabel("查询结果：");
	result_label.setForeground(Color.green);
	result_label.setFont(new Font("宋体",Font.HANGING_BASELINE,24));

	header=new Vector();
	//id,nickname,age,sex,college,entry_year,state
	header.addElement("账号");header.addElement("昵称");
	header.addElement("年龄");header.addElement("性别");
	header.addElement("地区");header.addElement("状态");

	result_table=new table(array,header);
	
	commit_button=new RecButton("加为好友");
	cancle_button=new RecButton("取消");
	commit_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	cancle_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	commit_button.addActionListener(this);
	cancle_button.addActionListener(this);
	commit_button.setForeground(Color.green);
	cancle_button.setForeground(Color.green);
	
	
	result_label.setBounds(new Rectangle(25, 20,290, 20));
	result_table.setBounds(new Rectangle(10, 50,490, 250));
	commit_button.setBounds(new Rectangle(300, 310,90, 30));
	cancle_button.setBounds(new Rectangle(400, 310,70, 30));
	
	c.add(result_label);
	c.add(result_table);
	c.add(commit_button);
	c.add(cancle_button);
}
public void actionPerformed(ActionEvent e)
{
	if(e.getActionCommand().equals("加为好友"))
	{
		int row=result_table.getselectedrow();
		System.out.println(row);
		String friend_id=result_table.getValueAt(row,0);
		String friend_nickname=result_table.getValueAt(row,1);
		
		if(group_name==null||group_name.equals(""))			//开始未指定分组
		{
			add_friend add=new add_friend(num,friend_id,nick_name,friend_nickname);
			add.setVisible(true);
		}
		else			//开始指定分组
		{
			sql="insert into friend(id,friend_id,remark,firmed,group_name) values('"+num+"'," +
			"'"+friend_id+"','"+friend_nickname+"','true','"+group_name+"')";
			try{
				send=new msg_sender(0,sql);
				boolean msg=send.updated();
			}catch(Exception e2){System.out.println("添加好友错误");}
			
			sql="insert into friend(id,friend_id,remark,firmed,group_name) values('"+friend_id+"'," +
			"'"+num+"','"+nick_name+"','true','-')";
			System.out.println(sql);
			try{
				send=new msg_sender(0,sql);
				boolean msg=send.updated();
				if(msg) 
				{
					msg_window win1=new msg_window("添加好友成功，等待对方确认");
					dispose();
					win1.setVisible(true);
				}
				else JOptionPane.showMessageDialog(null, "添加好友失败");
			}catch(Exception e2){System.out.println("添加好友错误");}
		}
	}
	else if(e.getActionCommand().equals("取消"))
	{
		dispose();
	}
}
public static void main(String args[])
{
	/*search_result re=new search_result();
	re.setVisible(true);*/
}
}
