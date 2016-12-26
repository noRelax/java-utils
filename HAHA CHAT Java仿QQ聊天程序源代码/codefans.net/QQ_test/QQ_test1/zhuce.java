package QQ_test1;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.swing.*;
	/*
	 * JFrame��ģ�黯����
	 * 
	 * */
public class zhuce extends JFrame implements ActionListener, MouseListener
{
	private JPanel basic_inf,network_inf,psw_def,num_panel;
	private JTextField nickname_txt,age_txt,num_txt,price_txt;
	private JPasswordField password1,password2;
	private JLabel  basic_inf_label,nickname_label,sex_label,age_label,num_label,price_label;
	private JLabel psw_label,psw1_label,psw2_label;
	private JLabel network_label,province_label,region_label;
	private CheckboxGroup cg;
	private Checkbox r1,r2;
	private RecButton commit_button;
	private JComboBox province_option,region_option;
	private String sql; 
	private Socket socket;
	private PrintWriter out;
	private int port=2000;
	public static final int PORT2=8081;
	RecButton putong_button,pay_button,line_button1,line_button2;
	JLabel special_num_label,special_num_list;
	private DefaultListModel listModel=new DefaultListModel(); 
	private JList list = new JList();
	private msg_sender send;
	private Container c;
	public zhuce()
	{
		setTitle("HAHA CHAT �û�ע��");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);            //���н�������ʾ����Ļ����
		c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		putong_button=new RecButton("��ͨ�������� >>");
		line_button1=new RecButton("");
		line_button2=new RecButton("");
		special_num_label=new JLabel("�����������");
		special_num_list=new JLabel("��������б�");
		
		list_ini();
		list=new JList(listModel);
		list=new JList(listModel);
		list.setForeground(Color.green);
		list.setBackground(Color.black);
		list.addMouseListener(this);
		list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		//���������״

		JScrollPane scrollPane=new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		speical_pane();
		set_button(putong_button, 130,5,250,25);
		line_button1.setBounds(new Rectangle(0,40,550,6));
		set_label(special_num_label,200,50,250,20);
		line_button2.setBounds(new Rectangle(250,75,6,450));
		
		set_label(special_num_list,70,90,150,20);
		scrollPane.setBounds(new Rectangle(20,120,200,330));
		
		
		c.add(scrollPane);
		c.add(line_button1);
		c.add(line_button2);
	}
	public void speical_pane()
	{
		try{
			InetAddress addr = InetAddress.getByName(login.ip);
			socket=new Socket(addr,PORT2);
			out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
			}catch(Exception e){}

			num_panel=new JPanel();num_panel.setBackground(Color.black);
			basic_inf=new JPanel();
			network_inf=new JPanel();
			psw_def=new JPanel();
			
			num_panel.setLayout(null);
			basic_inf.setLayout(null);
			network_inf.setLayout(null);
			psw_def.setLayout(null);
					
			num_panel.setBounds(new Rectangle(265, 85,210,50));
			basic_inf.setBounds(new Rectangle(265, 140,210,110));
			psw_def.setBounds(new Rectangle(265,250,210, 90));
			network_inf.setBounds(new Rectangle(265, 340,210, 90));
			
			//���룬�۸񲿷���д
			num_label=new JLabel("���룺");	
			price_label=new JLabel("�۸�");	
			num_label.setForeground(Color.green);
			price_label.setForeground(Color.green);
			
			num_label.setFont(new Font("����",Font.BOLD,14));
			price_label.setFont(new Font("����",Font.BOLD,14));
			
			num_txt=new JTextField();
			price_txt=new JTextField();
			num_txt.setEnabled(false);
			price_txt.setEnabled(false);
			num_txt.setForeground(Color.red);
			price_txt.setForeground(Color.red);
			
			
			pay_button=new RecButton("����");
			pay_button.setFont(new Font("����",Font.BOLD,20));
			pay_button.setForeground(Color.green);
			pay_button.addActionListener(this);
			pay_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		//���������״

			num_label.setBounds(new Rectangle(5,5,60, 20));
			num_txt.setBounds(new Rectangle(70,5,55, 20));
			price_label.setBounds(new Rectangle(5,30,60, 20));
			price_txt.setBounds(new Rectangle(70,30,55, 20));
			pay_button.setBounds(new Rectangle(132, 5,77, 40));
			
			
			num_panel.add(num_label);
			num_panel.add(price_label);
			num_panel.add(num_txt);
			num_panel.add(price_txt);
			num_panel.add(pay_button);
			
//			��һ���֣�������Ϣ��д
			basic_inf_label=new JLabel("������Ϣ��");	
			basic_inf_label.setFont(new Font("����",Font.BOLD,14));
			nickname_label=new JLabel("�ǳƣ�");
			sex_label=new JLabel("�Ա�");
			age_label=new JLabel("���䣺");
			
			nickname_txt=new JTextField(10);
			age_txt=new JTextField(10);
			nickname_txt.setEnabled(false);
			age_txt.setEnabled(false);
			
			cg=new CheckboxGroup(); 
			r1=new Checkbox("��",cg,true);
			r2=new Checkbox("Ů",cg,false);
			r1.setEnabled(false);
			r2.setEnabled(false);
			
			basic_inf_label.setBounds(new Rectangle(5,5,290, 20));
			nickname_label.setBounds(new Rectangle(20,30,50, 20));
			nickname_txt.setBounds(new Rectangle(80,30,100, 20));
			age_label.setBounds(new Rectangle(20,55,50, 20));
			age_txt.setBounds(new Rectangle(80,55,100, 20));
			sex_label.setBounds(new Rectangle(20,80,50, 20));
			r1.setBounds(new Rectangle(80,80,50, 20));
			r2.setBounds(new Rectangle(140,80,50, 20));
			
			basic_inf.setBackground(Color.black);
			basic_inf_label.setForeground(Color.gray);
			nickname_label.setForeground(Color.gray);
			age_label.setForeground(Color.gray);
			sex_label.setForeground(Color.gray);
			r1.setForeground(Color.gray);
			r2.setForeground(Color.gray);
			
			basic_inf.add(basic_inf_label);
			basic_inf.add(nickname_label);
			basic_inf.add(nickname_txt);
			basic_inf.add(age_label);
			basic_inf.add(age_txt);
			basic_inf.add(sex_label);
			basic_inf.add(r1);
			basic_inf.add(r2);
			/////////////////////////////////////////////////////////��һ���֣�������Ϣ��д���
			
			//�ڶ����֣���������
			
			psw_label=new JLabel("�������룺");
			psw_label.setFont(new Font("����",Font.BOLD,14));
			psw1_label=new JLabel("���룺");
			psw2_label=new JLabel("ȷ�����룺");
			
			password1=new JPasswordField(10);
			password2=new JPasswordField(10);
			password1.setEchoChar('*');
			password2.setEchoChar('*');
			
			password1.setEnabled(false);
			password2.setEnabled(false);
			
			psw_label.setBounds(new Rectangle(5,5,290, 20));
			psw1_label.setBounds(new Rectangle(20,30,50, 20));
			password1.setBounds(new Rectangle(80,30,100, 20));
			psw2_label.setBounds(new Rectangle(20,55,80, 20));
			password2.setBounds(new Rectangle(80,55,100, 20));
			
			psw_def.setBackground(Color.black);
			psw_label.setForeground(Color.gray);
			psw1_label.setForeground(Color.gray);
			psw2_label.setForeground(Color.gray);
			
			psw_def.add(psw_label);
			psw_def.add(psw1_label);
			psw_def.add(password1);
			psw_def.add(psw2_label);
			psw_def.add(password2);
					
////////////////////////////////////////////////////////	/�ڶ����֣������������
			
			//�������֣���������
			network_label=new JLabel("�������磺");
			network_label.setFont(new Font("����",Font.BOLD,14));
			province_label=new JLabel("ʡ�ݣ�");
			region_label=new JLabel("������");
			
			province_option=new JComboBox();
			province_option.setMaximumRowCount(20);
			province_ini(province_option);
			
			region_option=new JComboBox();
			province_option.addActionListener(new ActionListener(){
		      	public void actionPerformed(ActionEvent evt){ 
		      		region_option.removeAllItems();
		      		region_ini(region_option);
		         }
		        });
			
			region_ini(region_option);
			region_option.setMaximumRowCount(15);
			
			province_option.setEnabled(false);
			region_option.setEnabled(false);
			
			network_label.setBounds(new Rectangle(5,5,290, 20));
			province_label.setBounds(new Rectangle(20,30,50, 20));
			province_option.setBounds(new Rectangle(80,30,100, 20));
			region_label.setBounds(new Rectangle(20,55,50, 20));
			region_option.setBounds(new Rectangle(80,55,100, 20));
			
			network_inf.setBackground(Color.black);
			network_label.setForeground(Color.gray);
			province_label.setForeground(Color.gray);
			region_label.setForeground(Color.gray);
			
			network_inf.add(network_label);
			network_inf.add(province_label);
			network_inf.add(province_option);
			network_inf.add(region_label);
			network_inf.add(region_option);
////////////////////////////////////////////////////////	/�������֣�������·�������
			
			////////���Ĳ��֣��ύ��ť
			commit_button=new RecButton("�ύ");
			commit_button.setForeground(Color.GREEN);
			commit_button.addActionListener(this);
			commit_button.setBounds(new Rectangle(335,430,70, 25));
			commit_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			commit_button.setEnabled(false);
////////////////////////////////////////////////////////	/���Ĳ��֣��ύ��ť�������	
			c.add(num_panel);
			c.add(basic_inf);
			c.add(psw_def);
			c.add(network_inf);
			c.add(commit_button);
	}
	public void list_ini()
	{
		String sql="select distinct id from special_num";
		try{
			send=new msg_sender(1,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			while(str.hasMoreTokens())
				listModel.addElement(str.nextToken());
			
		}catch(Exception e2){System.out.println("��ѯʡ�ݴ���");}
	}
	public void set_button(JButton b,int x,int y,int w,int h)
	{
		b.setForeground(Color.green);				//������ɫ
		b.setFont(new Font("����",Font.BOLD,16));		//��������
		b.addActionListener(this);
		b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		//���������״
		b.setBounds(new Rectangle(x, y,w,h));
		c.add(b);
	}
	public void set_label(JLabel l,int x,int y,int w,int h)
	{
		l.setForeground(Color.green);		//����������ɫ
		l.setFont(new Font("����",Font.BOLD,16));
		l.setBounds(new Rectangle(x,y,w,h));		//���ñ�ǩ��������
		c.add(l);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("�ύ"))
		{
			String nickname,sex,psw1,psw2,province,region,entry_year;
			int age=0;
			nickname=nickname_txt.getText();
			age=Integer.parseInt(age_txt.getText());
			if(r1.getState()) sex="��";
			else sex="Ů";
			psw1=password1.getText();
			psw2=password2.getText();
			province=(String)province_option.getSelectedItem();
			region=(String)region_option.getSelectedItem();
		//----------------------------------------------------------------����ų�����
			
			
		//---------------------------------------------------------------------------------
			String num;
			num=num_txt.getText();
			String pwd=((int)(Math.random()*1000))+"";	//�������
			
			set_mail m=new set_mail(num,pwd);
			m.setVisible(true);
			sql="insert into user1(id,nickname,age,sex,province,region,pic,state,password) values ('"+num+"','"+nickname+"',"+age+"," +
					"'"+sex+"','"+province+"','"+region+"','1.jpg','����','"+pwd+"')";
			System.out.println(sql);
			try{
				send=new msg_sender(0,sql);
				sql="insert into user_inf(id,birthday,realname,birthmonth,birth_animal,constellation," +
						"personal_sign,personal_tells,email,address,cellphone,tel,l,blood_style,occupation) values " +
						"('"+num+"','-','-','-','-','-','HAHA-CHAT','HAHA-CHAT','-','-','-','-','�����ѿɼ�','-','-')";
				send=new msg_sender(0,sql);
				boolean msg=send.updated();
				if(msg)
				{
					send("rgnothing");
					sql="insert into friend_group(id,group_name) values " +
					"('"+num+"','�ҵĺ���')";
					send=new msg_sender(0,sql);
					
					sql="insert into friend_group(id,group_name) values " +
					"('"+num+"','İ����')";
					send=new msg_sender(0,sql);
					
					sql="insert into friend_group(id,group_name) values " +
					"('"+num+"','������')";
					send=new msg_sender(0,sql);
					
					sql="delete from special_num where id='"+num+"'";
					send=new msg_sender(0,sql);
				}
				else JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�����ע����Ϣ");
				
			}catch(Exception e2){System.out.println("��ѯʡ��ѧУ����");}
		}
		else if(e.getActionCommand().equals("����"))
		{
			if(num_txt.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"��˫�����ѡ�����","HAHA-CHAT ��Ϣ",1);
				return;
			}
			JOptionPane.showMessageDialog(null,"����ɹ�������л�����Ϣ��д","HAHA-CHAT ��Ϣ",1);
			nickname_txt.setEnabled(true);
			age_txt.setEnabled(true);
			password1.setEnabled(true);
			password2.setEnabled(true);
			 basic_inf_label.setForeground(Color.green);
			 nickname_label.setForeground(Color.green);
			 sex_label.setForeground(Color.green);
			 age_label.setForeground(Color.green);
			 psw_label.setForeground(Color.green);
			 psw1_label.setForeground(Color.green);
			 psw2_label.setForeground(Color.green);
			 network_label.setForeground(Color.green);
			 province_label.setForeground(Color.green);
			 region_label.setForeground(Color.green);
			 
			 r1.setEnabled(true);r1.setForeground(Color.green);
			 r2.setEnabled(true);r2.setForeground(Color.green);
			 commit_button.setEnabled(true);commit_button.setForeground(Color.green);
			 province_option.setEnabled(true);
			 region_option.setEnabled(true);
		}
		else if(e.getActionCommand().equals("��ͨ�������� >>"))
		{
			register1 re=new register1();
			re.setVisible(true);
			dispose();
		}
	}
	public boolean exist(String num)
	{
		boolean exist=false;
		sql="select id from user1 where id='"+num+"'";
		try{
			send=new msg_sender(1,sql);
			String str=send.r_msg();
			if(str.equals(""))
				exist=false;
			else
				exist=true;
		}catch(Exception e2){System.out.println("��ѯʡ��ѧУ����");}
		return exist;
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
			
		}catch(Exception e2){System.out.println("��ѯʡ�ݴ���");}
		return j_box;
	}
	public JComboBox region_ini(JComboBox j_box)
	{
		String province=(String)province_option.getSelectedItem();
		String sql="select region from province_inf where province='"+province+"'";
		try{
			send=new msg_sender(1,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			while(str.hasMoreTokens())
				j_box.addItem(str.nextToken());
			
		}catch(Exception e2){System.out.println("��ѯʡ�ڵ�������");}
		return j_box;
	}
	private void send(String string)		//�������֪ͨ�û�ע�����Ϣ
	{
		   try
		   {
			   out.println(string);
		   }catch(Exception ee){ee.printStackTrace();
		   }
	}
	public static void main(String args[])
	{
		zhuce m=new zhuce();
		m.setVisible(true);
	}
	public void mouseClicked(MouseEvent e) 
	{
		if (e.getClickCount() == 2) 
		{ 
			int index = list.locationToIndex(e.getPoint()); 
			String number=list.getSelectedValue()+"";
			String price="";
			String sql="select price from special_num where id='"+number+"'";
			try{
				send=new msg_sender(1,sql);
				String msg=send.r_msg();
				StringTokenizer str=new StringTokenizer(msg,"~");
				if(str.hasMoreTokens())
					price=str.nextToken();
				num_txt.setText(number);
				price_txt.setText(price);
			}catch(Exception e2){System.out.println("��ѯʡ�ݴ���");}
		} 		
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

