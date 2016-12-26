package QQ_test1;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.*;

public class search extends JFrame implements ActionListener
{
	private JPanel panel1,panel2;
	private JLabel nickname_label,number_label,sex_label,state_label,province_label,region_label,age_label;
	private JTextField nickname_txt,number_txt;
	private CheckboxGroup sex_box,state_box;
	private Checkbox sex1,sex2,sex3,state1,state2;
	private RecButton search_button,close_button,search_button2,close_button2;
	JComboBox province_option,region_option,age_option;
	private String num,sql,nick_name,group_name;
	private	msg_sender send;
	private Vector result;
	public search(String n,String nick)
	{
		num=n;nick_name=nick;
		pane_ini();
	}
	public search(String n,String nick,String gr)
	{
		num=n;nick_name=nick;group_name=gr;
		pane_ini();
	}
	public void pane_ini()
	{
		setTitle("HAHA CHAT--交友查询");
		setSize(300,343);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
		try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}catch(Exception e){e.printStackTrace();}
		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		
		JTabbedPane jtp=new JTabbedPane();
		jtp.setBounds(new Rectangle(5, 5,283, 300));
		
		panel1=new JPanel();
		panel2=new JPanel();
		panel1.setLayout(null);
		panel2.setLayout(null);
		jtp.add("精确查询",panel1);
		jtp.add("特征查询",panel2);
		panel1.setBackground(Color.black);
		panel2.setBackground(Color.black);
		////第二个窗口
		nickname_label=new JLabel("昵称：");
		number_label=new JLabel("haha号码：");
		sex_label=new JLabel("性别：");
		state_label=new JLabel("在线状态：");
		province_label=new JLabel("省份：");
		region_label=new JLabel("地区：");
		age_label=new JLabel("年龄：");
		
		nickname_txt=new JTextField(10);
		number_txt=new JTextField(10);
		
		search_button2=new RecButton("查找");
		close_button2=new RecButton("关闭");
		search_button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		close_button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		search_button2.setActionCommand("查找2");
		search_button2.addActionListener(this);
		close_button2.addActionListener(this);
		
		sex_box=new CheckboxGroup(); 
		sex1=new Checkbox("不限",sex_box,true);
		sex2=new Checkbox("男",sex_box,false);
		sex3=new Checkbox("女",sex_box,false);
		
		state_box=new CheckboxGroup(); 
		state1=new Checkbox("在线",state_box,false);
		state2=new Checkbox("全部",state_box,true);
		
		province_option=new JComboBox();
		province_option.setMaximumRowCount(20);
		province_ini(province_option);
		
		region_option=new JComboBox();
		province_option.addActionListener(new ActionListener(){
	      	public void actionPerformed(ActionEvent evt){ 
	      		region_option.removeAllItems();
	      		regions_ini(region_option);
	         }
	        });
		province_option.addActionListener(new ActionListener(){
	      	public void actionPerformed(ActionEvent evt){ 
	      		region_option.removeAllItems();
	      		regions_ini(region_option);
	         }
	        });
		region_option.setMaximumRowCount(15);
		regions_ini(region_option);

		age_option=new JComboBox();
		age_option.addItem("--不限年龄--");
		age_option.addItem("0-15岁");age_option.addItem("16-22岁");
		age_option.addItem("23-30岁");age_option.addItem("30-40岁");
		age_option.addItem("40-无限");
		
		state_label.setBounds(new Rectangle(30, 20,100, 20));
		state1.setBounds(new Rectangle(110, 20,50, 20));
		state2.setBounds(new Rectangle(175, 20,50, 20));
		sex_label.setBounds(new Rectangle(30, 50,100, 20));
		sex1.setBounds(new Rectangle(110, 50,40, 20));
		sex2.setBounds(new Rectangle(160, 50,40, 20));
		sex3.setBounds(new Rectangle(205, 50,40, 20));
		province_label.setBounds(new Rectangle(30, 80,100, 20));
		province_option.setBounds(new Rectangle(110,80,125, 20));
		region_label.setBounds(new Rectangle(30, 110,100, 20));
		region_option.setBounds(new Rectangle(110, 110,125,20));
		age_label.setBounds(new Rectangle(30, 140,100, 20));
		age_option.setBounds(new Rectangle(110, 140,125, 20));
		search_button2.setBounds(new Rectangle(50, 210,70, 30));
		close_button2.setBounds(new Rectangle(130, 210,70, 30));
		
		state_label.setForeground(Color.green);
		sex_label.setForeground(Color.green);
		province_label.setForeground(Color.green);
		region_label.setForeground(Color.green);
		age_label.setForeground(Color.green);
		search_button2.setForeground(Color.green);
		close_button2.setForeground(Color.green);
		state1.setForeground(Color.green);
		state2.setForeground(Color.green);
		sex1.setForeground(Color.green);
		sex2.setForeground(Color.green);
		sex3.setForeground(Color.green);
		
		panel2.add(state_label);
		panel2.add(state1);
		panel2.add(state2);
		panel2.add(sex_label);
		panel2.add(sex1);
		panel2.add(sex2);
		panel2.add(sex3);
		panel2.add(province_label);
		panel2.add(province_option);
		panel2.add(region_label);
		panel2.add(region_option);
		panel2.add(age_label);
		panel2.add(age_option);
		panel2.add(search_button2);
		panel2.add(close_button2);
		/////////第二个窗口设置完毕//////////////////////////////////////////////////
		
		//设置第一个窗口
		Icon search_image1=new ImageIcon("pic\\search1.jpg");
		JLabel search_pic1=new JLabel();
		search_pic1.setIcon(search_image1);
		search_pic1.setBounds(new Rectangle(0,0,300,90));
		
		search_button=new RecButton("查找");
		search_button.setActionCommand("查找1");
		close_button=new RecButton("关闭");
		nickname_label=new JLabel("昵称：");
		number_label=new JLabel("haha号码:");
		nickname_txt=new JTextField(10);
		number_txt=new JTextField(10);
		search_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		close_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		search_button.addActionListener(this);
		close_button.addActionListener(this);
		
		nickname_label.setBounds(new Rectangle(30, 110,100, 20));
		nickname_txt.setBounds(new Rectangle(110, 110,100, 20));
		number_label.setBounds(new Rectangle(30, 133,100, 20));
		number_txt.setBounds(new Rectangle(110, 133,100, 20));
		search_button.setBounds(new Rectangle(50, 210,70, 30));
		close_button.setBounds(new Rectangle(130, 210,70, 30));
		
		
		nickname_label.setForeground(Color.green);
		number_label.setForeground(Color.green);
		search_button.setForeground(Color.green);
		close_button.setForeground(Color.green);
		
		panel1.add(nickname_label);
		panel1.add(nickname_txt);
		panel1.add(number_label);
		panel1.add(number_txt);
		panel1.add(search_button);
		panel1.add(close_button);
		panel1.add(search_pic1);
		/////////第一个窗口设置完毕//////////////////////////////////////////////////
		c.add(jtp);
	}
	public JComboBox province_ini(JComboBox j_box)
	{
		String sql="select distinct province from province_inf";
		try{
			send=new msg_sender(1,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			while(str.hasMoreTokens())
				j_box.addItem(str.nextToken());
			
		}catch(Exception e2){System.out.println("查询省份错误");}
		return j_box;
	}
	public JComboBox regions_ini(JComboBox j_box)
	{
		String province=(String)province_option.getSelectedItem();
		String sql="select region from province_inf where province='"+province+"'";
		try{
			send=new msg_sender(1,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			while(str.hasMoreTokens())
				j_box.addItem(str.nextToken());
			
		}catch(Exception e2){System.out.println("查询省内学校错误");}
		return j_box;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("查找1"))
		{
			String id="",sex="",region="",pic="",state="";
			int age=0;
			String nickname=nickname_txt.getText(),haha_num=number_txt.getText();
			sql="select id,nickname,age,sex,region,pic,state from user1 where ";
			if(haha_num.length()!=0)
				sql+="id='"+haha_num+"'";
			if(haha_num.length()!=0&&nickname.length()!=0)
				sql+=" and nickname='"+nickname+"'";
			if(haha_num.length()==0&&nickname.length()!=0)
				sql+="nickname='"+nickname+"'";
	  		System.out.println(sql);
	  		try{
	  			send=new msg_sender(7,sql);
				String msg=send.r_msg();
				StringTokenizer str=new StringTokenizer(msg,"~");
				id=str.nextToken().trim();
				nickname=str.nextToken().trim();
				age=Integer.parseInt(str.nextToken().trim());
				sex=str.nextToken().trim();
				region=str.nextToken().trim();
				pic=str.nextToken().trim();
				state=str.nextToken().trim();
				
				result=new Vector();
				Vector row=new Vector();
				row.addElement(id);row.addElement(nickname);
				row.addElement(age);row.addElement(sex);
				row.addElement(region);
				row.addElement(state);
				result.addElement(row);
				if(group_name==null||group_name.equals(""))
				{
					search_result sr=new search_result(result,num,nick_name);
		  			sr.setVisible(true);
				}
				else
				{
					search_result sr=new search_result(result,num,nick_name,group_name);
		  			sr.setVisible(true);
				}
	  		}catch(Exception e2){
	  			System.out.println("search:"+e2.getStackTrace());
	  			}

		}
		else if(e.getActionCommand().equals("查找2"))
		{
			String state="",sex="",province="",region="",entry_year="",age="";
			sql="select id,nickname,age,sex,region,state from user1 where ";
			////////////////////////////////=========================获取对话框信息
			if(state1.getState())
			{state="在线";}
			else if(state2.getState())
			{state="全部";}
			if(sex1.getState())
			{sex="不限";}
			else if(sex2.getState())
			{sex="男";}
			else if(sex3.getState())
			{sex="女";}
			
			province=(String)province_option.getSelectedItem();
			region=(String)region_option.getSelectedItem();
			age=(String)age_option.getSelectedItem();
//=====================================================================================
			
			//==========================================构造sql语句
		if(state.endsWith("在线"))
			sql+="state='"+state+"' and ";
		if(sex.endsWith("男")||sex.endsWith("女"))
			sql+="sex='"+sex+"' and ";
		if(!province.endsWith("--不限省份--"))
			sql+="province='"+province+"' and ";
		if(!region.endsWith("--不限学校--"))
			sql+="region='"+region+"' and ";
		if(!age.endsWith("--不限年龄--"))
		{
			if(age.equals("0-15岁"))
			{
				sql+="age<16 and ";
			}
			else if(age.equals("16-22岁"))
			{
				sql+="age<23 and age>15 and";
			}
			else if(age.equals("23-30岁"))
			{
				sql+="age<31 and age>22 and";
			}
			else if(age.equals("30-40岁"))
			{
				sql+="age<41 and age>29 and";
			}
			else
			{
				sql+="age>40";
			}
		}
		int length=sql.length();
		sql=sql.substring(0,length-4);//将sql语句最后的and删除
		System.out.println(sql);
		//======================================================sql完成
		int age_int=0;
		String temp_id,temp_nickname,temp_sex,temp_region,temp_state;
  		try{
  			send=new msg_sender(6,sql);
			String msg=send.r_msg();
			set_vector sv=new set_vector();
    		result=sv.set(msg,6);
    		if(group_name==null||group_name.equals(""))
	    	{
		  		search_result sr=new search_result(result,num,nick_name);
		  		sr.setVisible(true);
	    	}
    		else
    		{
    			search_result sr=new search_result(result,num,nick_name,group_name);
		  		sr.setVisible(true);
    		}
  		}catch(Exception e2){	System.out.println("search:"+e2.getStackTrace());	}
		}
		else if(e.getActionCommand().equals("关闭"))
		{
			dispose();
		}
	}
	public static void main(String args[])
	{
		search s=new search("6263","haha");
		s.setVisible(true);
	}
}
