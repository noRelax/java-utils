package QQ_test1;

import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

import javax.swing.*;
public class friend_inf  extends JFrame implements MouseListener
{
	public static JLabel face_pic2;
	public static String head_pic_path;
	private JLabel num_label,nickname_label,sex_label,age_label,province_label,region_label
					,personal_sign_label,real_name_label,birth_animal_label,blood_style_label,
					birthday_label,constellation_label,occupation_label
					,personal_tells_label;
	private JPanel main_panel,basic_inf_panel,haha_zone_panel,detail_inf_panel;
	private JLabel email_label,address_label,cellphone_label,telephone_label;
	private JLabel haha_zone_label1,haha_zone_label2;
	private JLabel personal_sign_label2,personal_tells_label2;
	private	msg_sender send;
	private String num;
	private Icon image;
	public friend_inf(String n)
	{
		num=n;
		setTitle("HAHA-CHAT�鿴����("+n+")");
		setSize(450,450);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);            //���н�������ʾ����Ļ����
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}catch(Exception e){e.printStackTrace();}
		Container c = getContentPane();
		c.setLayout(null);
		c.setLayout(new BorderLayout());
		c.setBackground(Color.black);
		final Image image1 = Toolkit.getDefaultToolkit().createImage("pic\\friend_inf2.jpg");
		basic_inf_panel= new JPanel(){ 
			protected void paintChildren(Graphics g) { 
				g.drawImage(image1,0,0,this); 
				super.paintChildren(g); 
			} 
		};
		haha_zone_panel= new JPanel(){ 
			protected void paintChildren(Graphics g) { 
				g.drawImage(image1,0,0,this); 
				super.paintChildren(g); 
			} 
		}; 
		detail_inf_panel= new JPanel(){ 
			protected void paintChildren(Graphics g) { 
				g.drawImage(image1,0,0,this); 
				super.paintChildren(g); 
			}
		};
		basic_inf_ini();
		haha_zone_ini();
		detail_inf_ini();
		
		main_panel= new JPanel();
		JScrollPane scroll=new JScrollPane(main_panel);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		main_panel.setBackground(Color.black);
		main_panel.setLayout(null);
		
		main_panel.add(basic_inf_panel);	
		main_panel.add(num_label);
		main_panel.add(nickname_label);
		main_panel.add(personal_sign_label);
		main_panel.add(personal_sign_label2);
		main_panel.add(face_pic2);
		main_panel.add(sex_label);
		main_panel.add(age_label);
		main_panel.add(real_name_label);
		main_panel.add(province_label);
		main_panel.add(region_label);
		
		main_panel.add(haha_zone_panel);
		main_panel.add(haha_zone_label1);
		main_panel.add(haha_zone_label2);
		
		main_panel.add(detail_inf_panel);
		main_panel.add(birth_animal_label);
		main_panel.add(blood_style_label);
		main_panel.add(occupation_label);
		main_panel.add(birthday_label);
		main_panel.add(constellation_label);
		main_panel.add(email_label);
		main_panel.add(address_label);
		main_panel.add(cellphone_label);
		main_panel.add(telephone_label);
		main_panel.add(personal_tells_label);
		main_panel.add(personal_tells_label2);
      	ini();
		c.add(scroll,BorderLayout.CENTER);
	}
	public void basic_inf_ini()
	{
		basic_inf_panel.setLayout(null);
		JLabel basic_inf_label=new JLabel("������Ϣ��");
		basic_inf_label.setFont(new Font("����",Font.PLAIN,18));
		basic_inf_label.setForeground(Color.red);
		basic_inf_label.setBounds(new Rectangle(0,0,120, 20));
		basic_inf_panel.add(basic_inf_label);
		
		//================================================ͼƬ����
		String path=image_ini();
		Icon face_image2=new ImageIcon("face\\"+path);
		head_pic_path=path;
		face_pic2=new JLabel();
		face_pic2.setIcon(face_image2);
		face_pic2.addMouseListener(this);
		
		num_label=new JLabel("HAHA�˺ţ�    "+num);
		nickname_label=new JLabel("HAHA�ǳƣ�    ");
		personal_sign_label=new JLabel("<html>����<br>ǩ��");
		sex_label=new JLabel("�Ա�");
		age_label=new JLabel("���䣺");
		province_label=new JLabel("���磺");
		region_label=new JLabel("ѧУ��");
		real_name_label=new JLabel("������");
		personal_sign_label2=new JLabel(); 
		
		//������Ϣ��
		num_label.setForeground(Color.green);
		nickname_label.setForeground(Color.green);
		personal_sign_label.setForeground(Color.green);
		sex_label.setForeground(Color.green);
		age_label.setForeground(Color.green);
		province_label.setForeground(Color.green);
		region_label.setForeground(Color.green);
		real_name_label.setForeground(Color.green);
		personal_sign_label2.setForeground(Color.green);
		
		basic_inf_panel.setBounds(new Rectangle(10,20,430,20));
		face_pic2.setBounds(new Rectangle(315,40,50,50));
		num_label.setBounds(new Rectangle(15,50,160, 20));
		nickname_label.setBounds(new Rectangle(15,70,160,20));
		real_name_label.setBounds(new Rectangle(15,90,80,20));
		age_label.setBounds(new Rectangle(120,90,100,20));
		sex_label.setBounds(new Rectangle(235,90,100,20));
		province_label.setBounds(new Rectangle(15,110,100,20));
		region_label.setBounds(new Rectangle(120,110,110, 20));
		personal_sign_label.setBounds(new Rectangle(15,130,40,40));
		personal_sign_label2.setBounds(new Rectangle(55,130,200,40));
	}
	public void haha_zone_ini()
	{
		haha_zone_panel.setLayout(null);
		JLabel haha_zone_label=new JLabel("HAHA�ռ䣺");
		haha_zone_label.setFont(new Font("����",Font.PLAIN,18));
		haha_zone_label.setForeground(Color.red);
		haha_zone_label.setBounds(new Rectangle(0,0,120, 20));
		haha_zone_panel.add(haha_zone_label);		
		
		haha_zone_label1=new JLabel();
		image=new ImageIcon("pic\\haha_zone.jpg");
		haha_zone_label1.setIcon(image);
		
		haha_zone_label2=new JLabel("HAHA�ռ���ҳ");
		haha_zone_label2.setForeground(Color.green);
		haha_zone_label2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		//HAHA�ռ���
		haha_zone_panel.setBounds(new Rectangle(10,170,430,20));
		haha_zone_label1.setBounds(new Rectangle(10,195,20,20));
		haha_zone_label2.setBounds(new Rectangle(30,195,430,20));
	}
	public void detail_inf_ini()
	{
		detail_inf_panel.setLayout(null);
		JLabel detail_inf_label=new JLabel("��ϸ��Ϣ��");
		detail_inf_label.setFont(new Font("����",Font.PLAIN,18));
		detail_inf_label.setForeground(Color.red);
		detail_inf_label.setBounds(new Rectangle(0,0,120, 20));
		detail_inf_panel.add(detail_inf_label);
		
		birth_animal_label=new JLabel("��Ф��");
		blood_style_label=new JLabel("Ѫ�ͣ�");
		birthday_label=new JLabel("���գ�");
		constellation_label=new JLabel("������");
		occupation_label=new JLabel("ְҵ��");
		personal_tells_label=new JLabel("<html>����<br>˵��");
		email_label=new JLabel("�����ʼ���");
		address_label=new JLabel("��ϵ��ַ��");
		cellphone_label=new JLabel("�ֻ����룺");
		telephone_label=new JLabel("�绰���룺");
		personal_tells_label2=new JLabel();
		
		birth_animal_label.setForeground(Color.green);
		blood_style_label.setForeground(Color.green);
		birthday_label.setForeground(Color.green);
		constellation_label.setForeground(Color.green);
		occupation_label.setForeground(Color.green);
		personal_tells_label.setForeground(Color.green);
		email_label.setForeground(Color.green);
		address_label.setForeground(Color.green);
		cellphone_label.setForeground(Color.green);
		telephone_label.setForeground(Color.green);
		personal_tells_label2.setForeground(Color.green);
		
		detail_inf_panel.setBounds(new Rectangle(10,220,430,20));
		birth_animal_label.setBounds(new Rectangle(15,245,110, 20));
		blood_style_label.setBounds(new Rectangle(135,245,120, 20));
		occupation_label.setBounds(new Rectangle(260,245,100, 20));
		constellation_label.setBounds(new Rectangle(15,270,110, 20));
		birthday_label.setBounds(new Rectangle(135,270,120,20));
		
		email_label.setBounds(new Rectangle(15,295,180,20));
		address_label.setBounds(new Rectangle(195,295,150,20));
		cellphone_label.setBounds(new Rectangle(15,320,170,20));
		telephone_label.setBounds(new Rectangle(195,320,150,20));
		
		personal_tells_label.setBounds(new Rectangle(15,340,40,40));
		personal_tells_label2.setBounds(new Rectangle(55,340,200,40));
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
			
			nickname_label.setText("HAHA�ǳƣ�    "+nickname);
			real_name_label.setText("������"+realname+"");
			age_label.setText("���䣺"+age);
			sex_label.setText("��    ��"+sex);
			province_label.setText("ʡ�ݣ�"+province);
			region_label.setText("������"+region);
			personal_sign_label2.setText(""+personal_sign+"");
			
			birth_animal_label.setText("��Ф��"+birth_animal);
			blood_style_label.setText("Ѫ�ͣ�"+blood_style);
			occupation_label.setText("ְλ��"+occupation);
			constellation_label.setText("������"+constellation);
			birthday_label.setText("���գ�"+birthmonth+"��"+birthday+"�� ");
			email_label.setText("�����ʼ���"+email);
			address_label.setText("��ϵ��ַ��"+address);
			cellphone_label.setText("�ֻ����룺"+cellphone);
			telephone_label.setText("�绰���룺"+tel);
			personal_tells_label2.setText("<html>"+personal_tells+"</html>");

		}catch(Exception e2){e2.printStackTrace();}
	}

	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e)
	{
	}
	public void mouseEntered(MouseEvent e){	}
	public void mouseExited(MouseEvent e){ }
	public void mouseClicked(MouseEvent e){ }
	public static void main(String args[])
	{
		friend_inf reg=new friend_inf("6263");
		reg.setVisible(true);
	}
}

