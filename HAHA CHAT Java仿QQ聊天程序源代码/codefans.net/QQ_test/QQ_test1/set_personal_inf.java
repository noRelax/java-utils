package QQ_test1;

import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

import javax.swing.*;
public class set_personal_inf  extends JFrame implements ActionListener,MouseListener
{
	public static JLabel face_pic2;
	public static String head_pic_path;
	private JLabel num_label,nickname_label,sex_label,age_label,province_label,region_label
					,personal_sign_label,real_name_label,brith_animal_label,blood_style_label, face_label,
					birth_month_label,birthday_label,birth_day_label,constellation_label,occupation_label
					,personal_tells_label;
	private JTextField nickname_txt,age_txt,real_name_txt;
	private JLabel old_pwd_label,new_pwd_label,confirm_pwd_label;
	private JLabel email_label,address_label,cellphone_label,telephone_label,level_label;
	private JTextField email_txt,address_txt,cellphone_txt,telephone_txt;
	private CheckboxGroup contect_method_level_box;
	private Checkbox level1,level2,level3;
	private JPasswordField old_pwd_txt,new_pwd_txt,confirm_pwd_txt;
	private JTextArea personal_sign_area,personal_tells_area;
	private CardLayout card; 
	private JPanel cardp,p1,p2,p3;
	private JComboBox sex_option,province_option,region_option,
					brith_animal_option,blood_style_option,birth_month_option,
					birth_day_option,constellation_option,occupation_option;
	private RecButton commit_button1,cancle_button1,commit_button2,cancle_button2,commit_button3,cancle_button3;
	private	msg_sender send;
	private String num;
	private Image image;
	public set_personal_inf(String n)
	{
		num=n;
		setTitle("HAHA-CHAT��������");
		setSize(538,370);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);            //���н�������ʾ����Ļ����
		try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}catch(Exception e){e.printStackTrace();}
		Container c = getContentPane();
		//c.setLayout(null);
		c.setLayout(new BorderLayout());
		c.setBackground(Color.black);
		cardp=new JPanel();
		card=new CardLayout();
     	cardp.setLayout(card);
		
     	Box v=Box.createVerticalBox();
    	Component strut0=Box.createVerticalStrut(1);
      	v.add(strut0);
      	Component strut01=Box.createVerticalStrut(8);
      	v.add(strut01);
    	JButton b1=new JButton("������Ϣ");
      	b1.addActionListener(this);
      	v.add(b1);
      	Component strut1=Box.createVerticalStrut(8);
      	v.add(strut1);
      	JButton b2=new JButton("��ϵ��ʽ");
      	b2.addActionListener(this);
      	v.add(b2);
      	Component strut3=Box.createVerticalStrut(8);
      	v.add(strut3);
      	JButton b3=new JButton("��������");
      	b3.addActionListener(this);
      	v.add(b3);
      	v.setBounds(new Rectangle(20,20,100,350));
      	cardp.setBounds(new Rectangle(100,20,400,350));
      	p1=ini_p1();
      	p2=ini_p2();
      	p3=ini_p3();
      	ini();
		cardp.add("������Ϣ",p1);
		cardp.add("��ϵ��ʽ",p2);
		cardp.add("��������",p3);
		c.add(v,BorderLayout.WEST);
		c.add(cardp,BorderLayout.CENTER);
		card.first(cardp);
		card.show(cardp,"������Ϣ" );
	}
	public JPanel ini_p1()
	{
		JPanel temp_p1;
		//temp_p1= new JPanel();
		final Image image1 = Toolkit.getDefaultToolkit().createImage("server_pic\\personal_inf.gif");
		temp_p1= new JPanel(){ 
			protected void paintChildren(Graphics g) { 
				g.drawImage(image1,0,0,this); 
				super.paintChildren(g); 
			} 
		}; 
		num_label=new JLabel("HAHA�˺ţ�     "+num);
		nickname_label=new JLabel("�ǳƣ�");
		personal_sign_label=new JLabel("<html>����<br>ǩ��");
		sex_label=new JLabel("�Ա�");
		age_label=new JLabel("��    �䣺");
		province_label=new JLabel("ʡ�ݣ�");
		region_label=new JLabel("��    ����");
		//entry_year_label=new JLabel("��ѧ��ݣ�");
		face_label=new JLabel("����ͷ��");
		face_label.setFont(new Font("����",Font.BOLD,16));
		face_label.addMouseListener(this);
		face_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		real_name_label=new JLabel("����:");
		brith_animal_label=new JLabel("��Ф:");
		blood_style_label=new JLabel("Ѫ    ��:");
		birthday_label=new JLabel("����:");
		birth_month_label=new JLabel("��");
		birth_day_label=new JLabel("��");
		constellation_label=new JLabel("����:");
		occupation_label=new JLabel("ְҵ:");
		personal_tells_label=new JLabel("<html>����<br>˵��");
		
		num_label.setForeground(Color.green);
		nickname_label.setForeground(Color.green);
		personal_sign_label.setForeground(Color.green);
		sex_label.setForeground(Color.green);
		age_label.setForeground(Color.green);
		province_label.setForeground(Color.green);
		region_label.setForeground(Color.green);
		//entry_year_label.setForeground(Color.green);
		face_label.setForeground(Color.green);
		real_name_label.setForeground(Color.green);
		brith_animal_label.setForeground(Color.green);
		blood_style_label.setForeground(Color.green);
		birthday_label.setForeground(Color.green);
		birth_month_label.setForeground(Color.green);
		birth_day_label.setForeground(Color.green);
		constellation_label.setForeground(Color.green);
		occupation_label.setForeground(Color.green);
		personal_tells_label.setForeground(Color.green);
		
		nickname_txt=new JTextField(10);
		age_txt=new JTextField(10);
		real_name_txt=new JTextField(20);
		personal_sign_area=new JTextArea(150,80);
		personal_sign_area.setLineWrap(true);//ȥ��ˮƽ������
	    JScrollPane scroll=new JScrollPane(personal_sign_area);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    personal_tells_area=new JTextArea(150,80);
	    personal_tells_area.setLineWrap(true);//ȥ��ˮƽ������
	    JScrollPane scroll2=new JScrollPane(personal_tells_area);
	    scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    
		province_option=new JComboBox();
		region_option=new JComboBox();
		sex_option=new JComboBox();
		brith_animal_option=new JComboBox();
		blood_style_option=new JComboBox();
		birth_month_option=new JComboBox();
		birth_day_option=new JComboBox();
		constellation_option=new JComboBox();
		occupation_option=new JComboBox();
		
		sex_option.addItem("-");
		sex_option.addItem("��");
		sex_option.addItem("Ů");
		
		province_option=new JComboBox();
		province_option.setMaximumRowCount(20);
		province_ini(province_option);
		
		province_option.addActionListener(new ActionListener(){
	      	public void actionPerformed(ActionEvent evt){ 
	      		region_option.removeAllItems();
	      		regions_ini(region_option);
	         }
	        });
		regions_ini(region_option);
		
		brith_animal_option.addItem("-");
		brith_animal_option.addItem("��");	brith_animal_option.addItem("ţ");
		brith_animal_option.addItem("��");	brith_animal_option.addItem("��");
		brith_animal_option.addItem("��");	brith_animal_option.addItem("��");
		brith_animal_option.addItem("��");	brith_animal_option.addItem("��");
		brith_animal_option.addItem("��");	brith_animal_option.addItem("��");
		brith_animal_option.addItem("��");	brith_animal_option.addItem("��");
		
		blood_style_option.addItem("-");		blood_style_option.addItem("A��");
		blood_style_option.addItem("B��");		blood_style_option.addItem("AB��");
		blood_style_option.addItem("O��");
		
		birth_month_option.addItem("-");		birth_month_option.addItem("1");
		birth_month_option.addItem("2");		birth_month_option.addItem("3");
		birth_month_option.addItem("4");		birth_month_option.addItem("5");
		birth_month_option.addItem("6");		birth_month_option.addItem("7");
		birth_month_option.addItem("8");		birth_month_option.addItem("9");
		birth_month_option.addItem("10");		birth_month_option.addItem("11");
		birth_month_option.addItem("12");	
		
		birth_day_option.addItem("-");		birth_day_option.addItem("1");
		birth_day_option.addItem("2");		birth_day_option.addItem("3");
		birth_day_option.addItem("4");		birth_day_option.addItem("5");
		birth_day_option.addItem("6");		birth_day_option.addItem("7");
		birth_day_option.addItem("8");		birth_day_option.addItem("9");
		birth_day_option.addItem("10");		birth_day_option.addItem("11");
		birth_day_option.addItem("12");		birth_day_option.addItem("13");
		birth_day_option.addItem("14");		birth_day_option.addItem("15");
		birth_day_option.addItem("16");		birth_day_option.addItem("17");
		birth_day_option.addItem("18");		birth_day_option.addItem("19");
		birth_day_option.addItem("20");		birth_day_option.addItem("21");
		birth_day_option.addItem("22");		birth_day_option.addItem("23");
		birth_day_option.addItem("24");		birth_day_option.addItem("25");
		birth_day_option.addItem("26");		birth_day_option.addItem("27");
		birth_day_option.addItem("28");		birth_day_option.addItem("29");
		birth_day_option.addItem("30");		birth_day_option.addItem("31");
		
		constellation_option.addItem("-");	constellation_option.addItem("ˮƿ��");
		constellation_option.addItem("˫����");	constellation_option.addItem("ĵ����");
		constellation_option.addItem("��ţ��");	constellation_option.addItem("˫����");
		constellation_option.addItem("��з��");	constellation_option.addItem("ʨ����");
		constellation_option.addItem("��Ů��");	constellation_option.addItem("�����");
		constellation_option.addItem("��Ы��");	constellation_option.addItem("������");
		constellation_option.addItem("Ħ����");
		
		occupation_option.addItem("-");	occupation_option.addItem("����ʦ");
		occupation_option.addItem("�����ҵ");	occupation_option.addItem("��ְ");
		occupation_option.addItem("����ҵ");		occupation_option.addItem("����ҵ");
		occupation_option.addItem("�ϰ�");		occupation_option.addItem("ȫְ");
		occupation_option.addItem("��ҵ");		occupation_option.addItem("ʧҵ��");
		occupation_option.addItem("����");		occupation_option.addItem("����");
		occupation_option.addItem("ѧ��");		occupation_option.addItem("��������");
		occupation_option.addItem("����ҵ");		occupation_option.addItem("����");
		
		commit_button1=new RecButton("ȷ��");
		cancle_button1=new RecButton("ȡ��");
		commit_button1.setActionCommand("ȷ��1");
		cancle_button1.setActionCommand("ȡ��1");
		commit_button1.addActionListener(this);
		cancle_button1.addActionListener(this);
		commit_button1.setForeground(Color.green);
		cancle_button1.setForeground(Color.green);
		commit_button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancle_button1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//================================================ͼƬ����
		String path=image_ini();
		Icon face_image2=new ImageIcon("face\\"+path);
		head_pic_path=path;
		face_pic2=new JLabel();
		face_pic2.setIcon(face_image2);
		face_pic2.addMouseListener(this);
		
		face_pic2.setBounds(new Rectangle(315,40,50,50));
		face_label.setBounds(new Rectangle(305,100,80, 20));
		num_label.setBounds(new Rectangle(15,15,160, 20));
		nickname_label.setBounds(new Rectangle(15,40,80, 20));
		nickname_txt.setBounds(new Rectangle(55,40,195, 20));
		personal_sign_label.setBounds(new Rectangle(15,70,40,40));
		scroll.setBounds(new Rectangle(55,65,195, 55));
		sex_label.setBounds(new Rectangle(15,130,50, 20));
		sex_option.setBounds(new Rectangle(55,130,70, 20));
		age_label.setBounds(new Rectangle(135,130,60, 20));
		age_txt.setBounds(new Rectangle(200,130,50, 20));
		real_name_label.setBounds(new Rectangle(260,130,40, 20));
		real_name_txt.setBounds(new Rectangle(300,130,110, 20));
		brith_animal_label.setBounds(new Rectangle(15,155,50, 20));
		brith_animal_option.setBounds(new Rectangle(55,155,70, 20));
		blood_style_label.setBounds(new Rectangle(135,155,60, 20));
		blood_style_option.setBounds(new Rectangle(200,155,50, 20));
		birthday_label.setBounds(new Rectangle(260,155,50,20));
		birth_month_option.setBounds(new Rectangle(300,155,40,20));
		birth_month_label.setBounds(new Rectangle(340,155,20, 20));
		birth_day_option.setBounds(new Rectangle(360,155,40,20));
		birth_day_label.setBounds(new Rectangle(400,155,50, 20));
		province_label.setBounds(new Rectangle(15,180,50, 20));
		province_option.setBounds(new Rectangle(55,180,70, 20));
		region_label.setBounds(new Rectangle(135,180,60, 20));
		region_option.setBounds(new Rectangle(200,180,50, 20));
		constellation_label.setBounds(new Rectangle(260,180,40, 20));
		constellation_option.setBounds(new Rectangle(300,180,110, 20));
		//region_label.setBounds(new Rectangle(15,205,50, 20));
		//region_option.setBounds(new Rectangle(55,205,195, 20));
		occupation_label.setBounds(new Rectangle(15,205,50,20));
		occupation_option.setBounds(new Rectangle(55,205,90,20));
		personal_tells_label.setBounds(new Rectangle(15,230,80,40));
		scroll2.setBounds(new Rectangle(55,230,355, 50));
		
		commit_button1.setBounds(new Rectangle(230,290,70, 24));
		cancle_button1.setBounds(new Rectangle(320,290,70, 24));
		cardp.setBounds(new Rectangle(110,20,500, 400));
		temp_p1.setBackground(Color.black);
		temp_p1.setLayout(null);
		temp_p1.add(num_label);
		temp_p1.add(nickname_label);
		temp_p1.add(nickname_txt);
		temp_p1.add(personal_sign_label);
		temp_p1.add(scroll);
		temp_p1.add(face_pic2);
		temp_p1.add(face_label);
		temp_p1.add(sex_label);
		temp_p1.add(sex_option);
		temp_p1.add(age_label);
		temp_p1.add(age_txt);
		temp_p1.add(real_name_label);
		temp_p1.add(real_name_txt);
		temp_p1.add(brith_animal_label);
		temp_p1.add(brith_animal_option);
		temp_p1.add(blood_style_label);
		temp_p1.add(blood_style_option);
		temp_p1.add(birthday_label);
		temp_p1.add(birth_month_option);
		temp_p1.add(birth_month_label);
		temp_p1.add(birth_day_option);
		temp_p1.add(birth_day_label);
		temp_p1.add(province_label);
		temp_p1.add(province_option);
		//temp_p1.add(entry_year_label);
		//temp_p1.add(entry_time_option);
		temp_p1.add(constellation_label);
		temp_p1.add(constellation_option);
		temp_p1.add(region_label);
		temp_p1.add(region_option);
		temp_p1.add(occupation_label);
		temp_p1.add(occupation_option);
		temp_p1.add(personal_tells_label);
		temp_p1.add(scroll2);
		temp_p1.add(commit_button1);
		temp_p1.add(cancle_button1);
		return temp_p1;
	}
	public JPanel ini_p2()
	{
		JPanel temp_p2;
		//image = Toolkit.getDefaultToolkit().createImage("server_pic\\change_pwd.gif");
		temp_p2= new JPanel(){ 
			protected void paintChildren(Graphics g) { 
				g.drawImage(image,0,0,this); 
				super.paintChildren(g); 
			} 
		}; 
		temp_p2.setBackground(Color.black);
		temp_p2.setLayout(null);
		
		level_label=new JLabel("�������ϣ�");
		email_label=new JLabel("�����ʼ���");
		address_label=new JLabel("��ϵ��ַ��");
		cellphone_label=new JLabel("�ֻ����룺");
		telephone_label=new JLabel("�绰���룺");
		
		level_label.setForeground(Color.green);
		email_label.setForeground(Color.green);
		address_label.setForeground(Color.green);
		cellphone_label.setForeground(Color.green);
		telephone_label.setForeground(Color.green);
		
		email_txt=new JTextField(50);
		address_txt=new JTextField(50);
		cellphone_txt=new JTextField(50);
		telephone_txt=new JTextField(50);
				
		contect_method_level_box=new CheckboxGroup(); 
		level1=new Checkbox("��ȫ����",contect_method_level_box,true);
		level2=new Checkbox("�����ѿɼ�",contect_method_level_box,false);
		level3=new Checkbox("��ȫ����",contect_method_level_box,false);
		
		level1.setForeground(Color.green);
		level2.setForeground(Color.green);
		level3.setForeground(Color.green);
		
		commit_button2=new RecButton("ȷ��");
		cancle_button2=new RecButton("ȡ��");
		commit_button2.setActionCommand("ȷ��2");
		cancle_button2.setActionCommand("ȡ��2");
		commit_button2.addActionListener(this);
		cancle_button2.addActionListener(this);
		commit_button2.setForeground(Color.green);
		cancle_button2.setForeground(Color.green);
		
		level_label.setBounds(new Rectangle(85,70,65,20));
		level1.setBounds(new Rectangle(150,70,70, 20));
		level2.setBounds(new Rectangle(220,70,80, 20));
		level3.setBounds(new Rectangle(300,70,70, 20));
		email_label.setBounds(new Rectangle(115,100,85, 20));
		email_txt.setBounds(new Rectangle(200,100,140, 20));
		address_label.setBounds(new Rectangle(115,130,85, 20));
		address_txt.setBounds(new Rectangle(200,130,140, 20));
		cellphone_label.setBounds(new Rectangle(115,160,85, 20));
		cellphone_txt.setBounds(new Rectangle(200,160,140, 20));
		telephone_label.setBounds(new Rectangle(115,190,85, 20));
		telephone_txt.setBounds(new Rectangle(200,190,140, 20));
		commit_button2.setBounds(new Rectangle(230,290,70, 24));
		cancle_button2.setBounds(new Rectangle(320,290,70, 24));
		commit_button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancle_button2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		temp_p2.add(level_label);
		temp_p2.add(level1);
		temp_p2.add(level2);
		temp_p2.add(level3);
		temp_p2.add(email_label);
		temp_p2.add(email_txt);
		temp_p2.add(address_label);
		temp_p2.add(address_txt);
		temp_p2.add(cellphone_label);
		temp_p2.add(cellphone_txt);
		temp_p2.add(telephone_label);
		temp_p2.add(telephone_txt);
		temp_p2.add(commit_button2);
		temp_p2.add(cancle_button2);
		
		return temp_p2;
	}
	public JPanel ini_p3()
	{
		JPanel temp_p3;
		image = Toolkit.getDefaultToolkit().createImage("server_pic\\change_pwd.gif");
		temp_p3 = new JPanel(){ 
			protected void paintChildren(Graphics g) { 
				g.drawImage(image,0,0,this); 
				super.paintChildren(g); 
			} 
		}; 
		temp_p3.setLayout(null);
		old_pwd_label=new JLabel("����������룺");
		new_pwd_label=new JLabel("�����������룺");
		confirm_pwd_label=new JLabel("�ٴ����������룺");
		
		old_pwd_label.setForeground(Color.green);
		new_pwd_label.setForeground(Color.green);
		confirm_pwd_label.setForeground(Color.green);
		
		old_pwd_txt=new JPasswordField(50);
		new_pwd_txt=new JPasswordField(50);
		confirm_pwd_txt=new JPasswordField(50);
		
		old_pwd_txt.setEchoChar('*');
		new_pwd_txt.setEchoChar('*');
		confirm_pwd_txt.setEchoChar('*');
		
		commit_button3=new RecButton("ȷ��");
		cancle_button3=new RecButton("ȡ��");
		commit_button3.setActionCommand("ȷ��3");
		cancle_button3.setActionCommand("ȡ��3");
		commit_button3.addActionListener(this);
		cancle_button3.addActionListener(this);
		commit_button3.setForeground(Color.green);
		cancle_button3.setForeground(Color.green);
		
		old_pwd_label.setBounds(new Rectangle(100,100,85, 20));
		old_pwd_txt.setBounds(new Rectangle(200,100,100, 20));
		new_pwd_label.setBounds(new Rectangle(100,130,85, 20));
		new_pwd_txt.setBounds(new Rectangle(200,130,100, 20));
		confirm_pwd_label.setBounds(new Rectangle(100,160,96, 20));
		confirm_pwd_txt.setBounds(new Rectangle(200,160,100, 20));
		commit_button3.setBounds(new Rectangle(230,290,70, 24));
		cancle_button3.setBounds(new Rectangle(320,290,70, 24));
		commit_button3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancle_button3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		temp_p3.add(old_pwd_label);
		temp_p3.add(old_pwd_txt);
		temp_p3.add(new_pwd_label);
		temp_p3.add(new_pwd_txt);
		temp_p3.add(confirm_pwd_label);
		temp_p3.add(confirm_pwd_txt);
		temp_p3.add(commit_button3);
		temp_p3.add(cancle_button3);
		
		return temp_p3;
	}
	public String image_ini()
	{
		String path="";
		String sql="select pic from user1 where id='"+num+"'";
		System.out.println(sql);
		try{
			send=new msg_sender(1,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			path=str.nextToken();
			System.out.println(path);
			return path;
		}catch(Exception e2){System.out.println("��ѯͷ�����");return null;}
	}
	public void ini()
	{
		String nickname,personal_sign,sex,age,realname,birth_animal,blood_style,birthmonth,birthday
				,province,constellation,region,occupation,personal_tells,email,address,cellphone,tel,level;
		
		//�Ի�����Ϣ����
		String sql="select nickname,sex,age,province,region from user1" +
				" where id='"+num+"'";
		//System.out.println(sql);
		try{
			send=new msg_sender(5,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			nickname=str.nextToken().trim();
			sex=str.nextToken().trim();
			age=str.nextToken().trim();
			province=str.nextToken().trim();
			region=str.nextToken().trim();
			
			nickname_txt.setText(nickname);
			if(sex.equals("��")||sex.equals("Ů"))
				sex_option.setSelectedItem(sex);
			else
				sex_option.setSelectedItem("-");
			age_txt.setText(age);
			province_option.setSelectedItem(province);
			region_option.setSelectedItem(region);
			
			sql="select personal_sign,realname,birth_animal,blood_style,birthmonth,birthday,constellation" +
					",occupation,personal_tells,email,address,cellphone,tel,l from user_inf where id='"+num+"'";
			//System.out.println(sql);
			send=new msg_sender(14,sql);
			msg=send.r_msg();
			str=new StringTokenizer(msg,"~");
			
			personal_sign=str.nextToken().trim();
			realname=str.nextToken().trim();
			birth_animal=str.nextToken().trim();
			blood_style=str.nextToken().trim();
			birthmonth=str.nextToken().trim();
			birthday=str.nextToken().trim();
			constellation=str.nextToken().trim();
			occupation=str.nextToken().trim();
			personal_tells=str.nextToken().trim();
			email=str.nextToken().trim();
			address=str.nextToken().trim();
			cellphone=str.nextToken().trim();
			tel=str.nextToken().trim();
			level=str.nextToken().trim();
		
			personal_sign_area.setText(personal_sign);
			real_name_txt.setText(realname);
			brith_animal_option.setSelectedItem(birth_animal);
			blood_style_option.setSelectedItem(blood_style);
			birth_month_option.setSelectedItem(birthmonth);
			birth_day_option.setSelectedItem(birthday);
			constellation_option.setSelectedItem(constellation);
			occupation_option.setSelectedItem(occupation);
			personal_tells_area.setText(personal_tells);
			email_txt.setText(email);
			address_txt.setText(address);
			cellphone_txt.setText(cellphone);
			telephone_txt.setText(tel);
			if(level.equals("��ȫ����"))
				level1.setState(true);
			else if(level.equals("��ȫ����"))
				level3.setState(true);
			else
				level2.setState(true);

		}catch(Exception e2){e2.printStackTrace();}
		
		
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
			
		}catch(Exception e2){System.out.println("��ѯʡ��ѧУ����");}
		return j_box;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("ȷ��1"))          
		{			
			String nickname,personal_sign,sex,age,realname,birth_animal,blood_style,birthmonth,birthday
			,province,constellation,region,occupation,personal_tells;
			nickname=nickname_txt.getText();
			personal_sign=personal_sign_area.getText();
			sex=(String)sex_option.getSelectedItem();
			age=age_txt.getText();
			realname=real_name_txt.getText();
			birth_animal=(String)brith_animal_option.getSelectedItem();
			blood_style=(String)blood_style_option.getSelectedItem();
			birthmonth=(String)birth_month_option.getSelectedItem();
			birthday=(String)birth_day_option.getSelectedItem();
			province=(String)province_option.getSelectedItem();
			constellation=(String)constellation_option.getSelectedItem();
			region=(String)region_option.getSelectedItem();
			occupation=(String)occupation_option.getSelectedItem();
			personal_tells=personal_tells_area.getText();
			

			try{
				String sql="update user1 set nickname='"+nickname+"',age='"+age+"',sex='"+sex+"',province='"+province+"',region='"+region+"',pic='"+head_pic_path+"' " +
						"where id='"+num+"'";
				send=new msg_sender(0,sql);
				sql="update user_inf set birthday='"+birthday+"',realname='"+realname+"'," +
						"birthmonth='"+birthmonth+"',birth_animal='"+birth_animal+"'," +
								"constellation='"+constellation+"',personal_sign='"+personal_sign+"'," +
										"personal_tells='"+personal_tells+"',blood_style='"+blood_style+"'," +
												"occupation='"+occupation+"' where id='"+num+"'";
				send=new msg_sender(0,sql);
				JOptionPane.showMessageDialog(null,"�������ѽ�����������");
				main.nick_name=nickname;
				main.nickname_txt.setText(nickname);
				Icon img=new ImageIcon("face\\"+head_pic_path);
				main.pic.setIcon(img);
				dispose();
			}catch(Exception e2){System.out.println("������Ϣ���ó���");}
		}
		else if(e.getActionCommand().equals("ȡ��1"))
		{
			dispose();
		}
		else if(e.getActionCommand().equals("ȷ��2"))          
		{
			String email,address,cellphone,tel,level;
			email=email_txt.getText();
			address=address_txt.getText();
			cellphone=cellphone_txt.getText();
			tel=telephone_txt.getText();
			level=(String)(contect_method_level_box.getSelectedCheckbox().getLabel());
			try{
				String sql="update user_inf set email='"+email+"',address='"+address+"',cellphone='"+cellphone+"'," +
				"tel='"+tel+"',l='"+level+"' where id='"+num+"'";
				send=new msg_sender(0,sql);
				JOptionPane.showMessageDialog(null,"�������ѽ�����������");
				dispose();
			}catch(Exception e2){System.out.println("������Ϣ���ó���");}
			
		}
		else if(e.getActionCommand().equals("ȡ��2"))
		{
			dispose();
		}
		else if(e.getActionCommand().equals("ȷ��3"))          
		{
			String old_pwd=old_pwd_txt.getText().trim();
			String pwd1=new_pwd_txt.getText().trim();
			String pwd2=confirm_pwd_txt.getText().trim();
			if(old_pwd.equals("")||pwd1.equals("")||!pwd1.equals(pwd2))
			{
				if(old_pwd.equals(""))
				{
					JOptionPane.showMessageDialog(null,"�����벻��Ϊ��");
					return;
				}
				else if(pwd1.equals(""))
				{
					JOptionPane.showMessageDialog(null,"�����벻��Ϊ��");
					return;
				}
				else if(!pwd1.equals(pwd2))
				{
					JOptionPane.showMessageDialog(null,"�����������벻��ͬ");
					return;
				}
			}
			else
			{
				String pwd="";
				String sql="select password from user1 where id='"+num+"'";
				System.out.println(sql);
				try{
					send=new msg_sender(1,sql);
					String msg=send.r_msg();
					StringTokenizer str=new StringTokenizer(msg,"~");
					pwd=str.nextToken().trim();
					System.out.println(pwd);
					if(!pwd.equals(old_pwd))
					{
						JOptionPane.showMessageDialog(null,"���������������");
						return;
					}
					else
					{
						sql="update user1 set password ='"+pwd1+"' where id='"+num+"'";
						send=new msg_sender(0,sql);
						JOptionPane.showMessageDialog(null,"�����޸ĳɹ�");
						dispose();
					}
				}catch(Exception e2){System.out.println("�����޸Ĵ���");}
			}
		}
		else if(e.getActionCommand().equals("ȡ��3"))
		{
			dispose();
		}
		else if(e.getActionCommand().equals("������Ϣ"))
		{
			card.show(cardp,"������Ϣ" );
		}
		else if(e.getActionCommand().equals("��������"))
		{
			card.show(cardp,"��������" );
		}

		else if(e.getActionCommand().equals("��ϵ��ʽ"))
		{
			card.show(cardp,"��ϵ��ʽ" );
		}




	}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e)
	{
		choose_head_pic choose=new choose_head_pic();
		choose.setVisible(true);
	}
	public void mouseEntered(MouseEvent e){	face_label.setForeground(Color.red);   }
	public void mouseExited(MouseEvent e){ 	face_label.setForeground(Color.green); }
	public void mouseClicked(MouseEvent e){	System.out.println("���Ѿ����°�ť");   }
	public static void main(String args[])
	{
		set_personal_inf reg=new set_personal_inf("3605");
		reg.setVisible(true);
	}
}
