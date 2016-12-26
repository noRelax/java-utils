package SaoLei;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import haha_game.RecButton;
public class main extends JFrame implements ActionListener,Runnable,MouseListener{
	private static int n,time=0;
	private int num,bomb_left,x=0,y=0;					//��¼�׵�����Ŀ
	private JTextField time_txt,bomb_left_txt;
	private JPanel main_panel=new JPanel();	
	private JLabel[][] label;
	private boolean[][] discoverd;
	private boolean[][] table;
	private int[][] view_table;
	private boolean[][] mark;
	private JLabel time_label,bomb_left_label;
	private int i,j,wide=0,height=0,level=50;
	private Thread splashThread; 
	private boolean pause=true,completed=true,failed=false;
	public main(int nu)
	{
		n=nu;
		num=(int)(n*n/7);
		bomb_left=num;
		label=new JLabel[n][n];
		discoverd=new boolean[n][n];
		table=new boolean[n][n];
		view_table=new int[n][n];
		mark=new boolean[n][n];
	setTitle("HAHA��Ʒɨ��");
	wide=n*16+45;
	height=n*16+125;
	setSize(wide,height);
	setLocationRelativeTo(null); 
	Container c=getContentPane();
	c.setLayout(null);
	c.setBackground(Color.black);
	main_panel.setLayout(null);
	main_panel.setBackground(Color.LIGHT_GRAY);
	for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
		{
			discoverd[i][j]=false;
			table[i][j]=false;
			mark[i][j]=false;
			view_table[i][j]=0;
			label[i][j]=new JLabel();
			label[i][j].addMouseListener(this);
			Icon login_image=new ImageIcon("saolei_pic//rect.jpg");
			label[i][j].setIcon(login_image);
			label[i][j].setBounds(new Rectangle(1+17*j, 1+17*(i),16,16));
			main_panel.add(label[i][j]);
		}
	}
	
	//���ò˵�
	JMenu menu1=new JMenu("GAME");
	JMenuItem item=new JMenuItem("START");
	item.setBackground(Color.black);item.setForeground(Color.green);
	item.addActionListener(this);
	menu1.add(item);
	menu1.addSeparator();
	item=new JMenuItem("PAUSE");
	item.setBackground(Color.black);item.setForeground(Color.green);
	item.addActionListener(this);
	menu1.add(item);
	menu1.addSeparator();
	item=new JMenuItem("RESET");
	item.setBackground(Color.black);item.setForeground(Color.green);
	item.addActionListener(this);
	menu1.add(item);
	JMenuBar mb=new JMenuBar();
	mb.add(menu1);
	setJMenuBar(mb);
	
	
	time_label=new JLabel("Time:");
	bomb_left_label=new JLabel("LEFT:");
	
	time_label.setFont(new Font("����",Font.BOLD,15));
	bomb_left_label.setFont(new Font("����",Font.BOLD,15));
	
	time_label.setForeground(Color.red);
	bomb_left_label.setForeground(Color.red);
	
	time_txt=new JTextField(10);
	bomb_left_txt=new JTextField(10);
	
	time_txt.setText(""+time);time_txt.setBackground(Color.DARK_GRAY);
	bomb_left_txt.setText(""+bomb_left);bomb_left_txt.setBackground(Color.DARK_GRAY);
	
	time_txt.setEnabled(false);
	bomb_left_txt.setEnabled(false);
	
	main_panel.setBounds(new Rectangle(10,40,17*n,17*n));
	time_label.setBounds(new Rectangle(5,10,45,20));
	time_txt.setBounds(new Rectangle(50,10,30,20));
	bomb_left_label.setBounds(new Rectangle(100,10,45,20));
	bomb_left_txt.setBounds(new Rectangle(145,10,30,20));
	
	c.add(main_panel);
	c.add(time_label);
	c.add(time_txt);
	c.add(bomb_left_label);
	c.add(bomb_left_txt);
	this.start();
}
	public void ini()
	{

		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				discoverd[i][j]=false;
				mark[i][j]=false;
				table[i][j]=false;
				view_table[i][j]=0;
				Icon login_image=new ImageIcon("saolei_pic//rect.jpg");
				label[i][j].setIcon(login_image);
			}
		}
		
		int rand=0,ci=0,cj=0;
		int count=0;	//��¼�׵ĵ�ǰ����
		//��ʼ������table����
		while(count<num)
		{
			ci=(int)(Math.random()*n);
			cj=(int)(Math.random()*n);
			if(table[ci][cj])
				continue;
			else
			{
				count++;
				table[ci][cj]=true;
			}
		}
		
		//��ʼ������view_table
		set_view_table();
		time=0;
		time_txt.setText(time+"");
		bomb_left=num;
		bomb_left_txt.setText(bomb_left+"");
		completed=false;
		pause=false;
		failed=false;
	}
	private void set_view_table()
	{
		int count=0;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				if(table[i][j])
					count=9;
				else
				{
					count=0;
					if(i==0&&j==0)
					{
						if(table[1][0]) count++;
						if(table[1][1]) count++;
						if(table[0][1]) count++;
					}
					else if(i==0&&j==n-1)
					{
						if(table[1][n-1]) count++;
						if(table[1][n-2]) count++;
						if(table[0][n-2]) count++;
					}
					else if(i==0&&j!=0&&j!=n-1)
					{
						if(table[1][j]) count++;
						if(table[0][j+1]) count++;
						if(table[0][j-1]) count++;
						if(table[1][j+1]) count++;
						if(table[1][j-1]) count++;
					}
					else if(j==0&&i==n-1)
					{
						if(table[n-2][0]) count++;
						if(table[n-2][1]) count++;
						if(table[n-1][1]) count++;
					}
					else if(i==n-1&&j==n-1)
					{
						if(table[n-2][n-2]) count++;
						if(table[n-1][n-2]) count++;
						if(table[n-2][n-1]) count++;
					}
					else if(j==0&&i!=0&&i!=n-1)
					{
						if(table[i-1][0]) count++;
						if(table[i+1][0]) count++;
						if(table[i-1][1]) count++;
						if(table[i+1][1]) count++;
						if(table[i][1]) count++;
					}
					else if(j==n-1&&i!=0&&i!=n-1)
					{
						if(table[i-1][n-1]) count++;
						if(table[i+1][n-1]) count++;
						if(table[i-1][n-2]) count++;
						if(table[i+1][n-2]) count++;
						if(table[i][n-2]) count++;
					}
					else if(i==n-1&&j!=0&&j!=n-1)
					{
						if(table[n-1][j-1]) count++;
						if(table[n-1][j+1]) count++;
						if(table[n-2][j-1]) count++;
						if(table[n-2][j+1]) count++;
						if(table[n-2][j]) count++;
					}
					else
					{
						if(table[i-1][j]) count++;
						if(table[i-1][j-1]) count++;
						if(table[i-1][j+1]) count++;
						if(table[i+1][j]) count++;
						if(table[i+1][j-1]) count++;
						if(table[i+1][j+1]) count++;
						if(table[i][j-1]) count++;
						if(table[i][j+1]) count++;
					}
				}
				view_table[i][j]=count;
			}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("START"))
		{
			pause=false;
			ini();			
		}
		else if(e.getActionCommand().equals("PAUSE"))
		{
			if(!pause)
			{
				pause=true;
			}
			else
			{
				pause=false;
			}
		}
		else if(e.getActionCommand().equals("RESET"))
		{
			pause=false;
			ini();	
		}
	}
	public void start()
	{
		this.toFront();  //����ǰ����ʾ
		splashThread=new Thread(this);  //ʵ�����߳�
		splashThread.start();  //��ʼ�����߳�
	}
	public void run()
	{
		try
		{
			boolean exchange=true,firm=false;	
			while(true)
			{
				if(!pause)
				{
					time++;
					time_txt.setText(time+"");
				}
				Thread.sleep(1000);
			}
		}catch(Exception e){System.out.println("��ʱ����");}
	}
	private boolean complete()
	{
		boolean over=true;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				if(view_table[i][j]==9&&!mark[i][j])
					over=false;
			}
		}
		return over;
	}
	private void over()
	{
		//try{Thread.sleep(1000);}catch(Exception e){}
		pause=true;
		JOptionPane.showMessageDialog(null, "ţ�ư���������");
	}
	public void mouseClicked(MouseEvent e)
	{ 
		JLabel l=(JLabel)e.getSource();
		Icon image;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				if(l==label[i][j])
				{
					x=i;y=j;
					break;
				}
			}
		}
		if(e.isMetaDown()&&!completed)//��������Ҽ�
		{
			if(mark[x][y])
			{
				image=new ImageIcon("saolei_pic//rect.jpg");
				l.setIcon(image);
				mark[x][y]=false;
				discoverd[x][y]=false;
				bomb_left++;
				bomb_left_txt.setText(bomb_left+"");
			}
			else
			{
				image=new ImageIcon("saolei_pic//rect2.jpg");
				l.setIcon(image);
				mark[x][y]=true;
				discoverd[x][y]=true;
				bomb_left--;
				bomb_left_txt.setText(bomb_left+"");
				if(complete())
					over();
			}
		}
		else if (e.getClickCount() == 1&&!completed&&!e.isMetaDown()) 
		{
			//�ҵ����panel��x,y����

			if(!discoverd[x][y])
			{
				if(view_table[x][y]==9)			//�ȵ�����
				{
					image=new ImageIcon("saolei_pic//��2.jpg");
					l.setIcon(image);
					mission_failed();
				}
				else if(view_table[x][y]==0)		//û���ף���������ɨ�跨����
				{
					image=new ImageIcon("saolei_pic//num0.jpg");
					l.setIcon(image);
					seed_spread(x,y);
				}
				else
				{
					image=new ImageIcon("saolei_pic//num"+view_table[x][y]+".jpg");
					l.setIcon(image);
					discoverd[x][y]=true;
				}
			}			
		}
		else if (e.getClickCount()==2&&!completed) 
		{
			double_clicked1();//��Χ����ȫ���������
			double_clicked2();//��Χ�����׶���discover��
		} 
	} 
	public void double_clicked1()
	{
		int real_num=0,current_num=0;
		real_num=view_table[x][y];
		int p=x,q=y;
		if(p==0&&q==0)
		{
			if(mark[1][0]&&table[1][0]) current_num++;
			if(mark[1][1]&&table[1][1]) current_num++;
			if(mark[0][1]&&table[0][1]) current_num++;
			if(current_num==real_num)
			{
				unpack(1,0);
				unpack(1,1);
				unpack(0,1);			
			}
		}
		else if(p==0&&q==n-1)
		{
			if(mark[1][n-1]&&table[1][n-1]) current_num++;
			if(mark[1][n-2]&&table[1][n-2]) current_num++;
			if(mark[0][n-2]&&table[0][n-2]) current_num++;
			if(current_num==real_num)
			{
				unpack(1,n-1);
				unpack(1,n-2);
				unpack(0,n-2);		
			}
		}
		else if(p==0&&q!=0&&q!=n-1)
		{
			if(mark[1][q]&&table[1][q]) current_num++;
			if(mark[0][q+1]&&table[0][q+1]) current_num++;
			if(mark[0][q-1]&&table[0][q-1]) current_num++;
			if(mark[1][q+1]&&table[1][q+1]) current_num++;
			if(mark[1][q-1]&&table[1][q-1]) current_num++;
			if(current_num==real_num)
			{
				unpack(1,q);
				unpack(0,q+1);
				unpack(0,q-1);
				unpack(1,q+1);
				unpack(1,q-1);			
			}
		}
		else if(q==0&&p==n-1)
		{
			if(mark[n-2][0]&&table[n-2][0]) current_num++;
			if(mark[n-2][1]&&table[n-2][1]) current_num++;
			if(mark[n-1][1]&&table[n-1][1]) current_num++;
			if(current_num==real_num)
			{
				unpack(n-2,n-2);
				unpack(n-2,n-2);
				unpack(n-1,1);		
			}
		}
		else if(p==n-1&&q==n-1)
		{
			if(mark[n-2][n-2]&&table[n-2][n-2]) current_num++;
			if(mark[n-1][n-2]&&table[n-1][n-2]) current_num++;
			if(mark[n-2][n-1]&&table[n-2][n-1]) current_num++;
			if(current_num==real_num)
			{
				unpack(n-2,n-2);
				unpack(n-1,n-2);
				unpack(n-2,n-1);		
			}
		}
		else if(q==0&&p!=0&&p!=n-1)
		{
			if(mark[p-1][0]&&table[p-1][0]) current_num++;
			if(mark[p+1][0]&&table[p+1][0]) current_num++;
			if(mark[p-1][1]&&table[p-1][1]) current_num++;
			if(mark[p+1][1]&&table[p+1][1]) current_num++;
			if(mark[p][1]&&table[p][1]) current_num++;
			if(current_num==real_num)
			{
				unpack(p-1,0);
				unpack(p+1,0);
				unpack(p-1,1);
				unpack(p+1,1);
				unpack(p,1);			
			}
			
		}
		else if(q==n-1&&p!=0&&p!=n-1)
		{
			if(mark[p-1][n-1]&&table[p-1][n-1]) current_num++;
			if(mark[p+1][n-1]&&table[p+1][n-1]) current_num++;
			if(mark[p-1][n-2]&&table[p-1][n-2]) current_num++;
			if(mark[p+1][n-2]&&table[p+1][n-2]) current_num++;
			if(mark[p][n-2]&&table[p][n-2]) current_num++;
			if(current_num==real_num)
			{
				unpack(p-1,n-1);
				unpack(p+1,n-1);
				unpack(p-1,n-2);
				unpack(p+1,n-2);
				unpack(p,n-2);		
			}
		}
		else if(p==n-1&&q!=0&&q!=n-1)
		{
			if(mark[n-1][q-1]&&table[n-1][q-1]) current_num++;
			if(mark[n-1][q+1]&&table[n-1][q+1]) current_num++;
			if(mark[n-2][q-1]&&table[n-2][q-1]) current_num++;
			if(mark[n-2][q+1]&&table[n-2][q+1]) current_num++;
			if(mark[n-2][q]&&table[n-2][q]) current_num++;
			if(current_num==real_num)
			{
				unpack(n-1,q-1);
				unpack(n-1,q+1);
				unpack(n-2,q-1);
				unpack(n-2,q+1);
				unpack(n-2,q);			
			}
		}
		else
		{
			if(mark[p-1][q]&&table[p-1][q]) current_num++;
			if(mark[p-1][q-1]&&table[p-1][q-1]) current_num++;
			if(mark[p-1][q+1]&&table[p-1][q+1]) current_num++;
			if(mark[p+1][q]&&table[p+1][q]) current_num++;
			if(mark[p+1][q-1]&&table[p+1][q-1]) current_num++;
			if(mark[p+1][q+1]&&table[p+1][q+1]) current_num++;
			if(mark[p][q-1]&&table[p][q-1]) current_num++;
			if(mark[p][q+1]&&table[p][q+1]) current_num++;
			if(current_num==real_num)
			{
				unpack(p-1,q);
				unpack(p-1,q-1);
				unpack(p-1,q+1);
				unpack(p+1,q);
				unpack(p+1,q-1);
				unpack(p+1,q+1);
				unpack(p,q-1);
				unpack(p,q+1);				
			}
		}
	}
	public void double_clicked2()
	{
		int real_num=0,current_num=0;
		real_num=view_table[x][y];
		int p=x,q=y;
		if(p==0&&q==0)
		{
			if(mark[1][0]||!discoverd[1][0]) current_num++;
			if(mark[1][1]||!discoverd[1][1]) current_num++;
			if(mark[0][1]||!discoverd[0][1]) current_num++;
			if(current_num==real_num)
			{
				unpack(1,0);
				unpack(1,1);
				unpack(0,1);			
			}
		}
		else if(p==0&&q==n-1)
		{
			if(mark[1][n-1]||!discoverd[1][n-1]) current_num++;
			if(mark[1][n-2]||!discoverd[1][n-2]) current_num++;
			if(mark[0][n-2]||!discoverd[0][n-2]) current_num++;
			if(current_num==real_num)
			{
				unpack(1,n-1);
				unpack(1,n-2);
				unpack(0,n-2);		
			}
		}
		else if(p==0&&q!=0&&q!=n-1)
		{
			if(mark[1][q]||!discoverd[1][q]) current_num++;
			if(mark[0][q+1]||!discoverd[0][q+1]) current_num++;
			if(mark[0][q-1]||!discoverd[0][q-1]) current_num++;
			if(mark[1][q+1]||!discoverd[1][q+1]) current_num++;
			if(mark[1][q-1]||!discoverd[1][q-1]) current_num++;
			if(current_num==real_num)
			{
				unpack(1,q);
				unpack(0,q+1);
				unpack(0,q-1);
				unpack(1,q+1);
				unpack(1,q-1);			
			}
		}
		else if(q==0&&p==n-1)
		{
			if(mark[n-2][0]||!discoverd[n-2][0]) current_num++;
			if(mark[n-2][1]||!discoverd[n-2][1]) current_num++;
			if(mark[n-1][1]||!discoverd[n-1][1]) current_num++;
			if(current_num==real_num)
			{
				unpack(n-2,n-2);
				unpack(n-2,n-2);
				unpack(n-1,1);		
			}
		}
		else if(p==n-1&&q==n-1)
		{
			if(mark[n-2][n-2]||!discoverd[n-2][n-2]) current_num++;
			if(mark[n-1][n-2]||!discoverd[n-1][n-2]) current_num++;
			if(mark[n-2][n-1]||!discoverd[n-2][n-1]) current_num++;
			if(current_num==real_num)
			{
				unpack(n-2,n-2);
				unpack(n-1,n-2);
				unpack(n-2,n-1);		
			}
		}
		else if(q==0&&p!=0&&p!=n-1)
		{
			if(mark[p-1][0]||!discoverd[p-1][0]) current_num++;
			if(mark[p+1][0]||!discoverd[p+1][0]) current_num++;
			if(mark[p-1][1]||!discoverd[p-1][1]) current_num++;
			if(mark[p+1][1]||!discoverd[p+1][1]) current_num++;
			if(mark[p][1]||!discoverd[p][1]) current_num++;
			if(current_num==real_num)
			{
				unpack(p-1,0);
				unpack(p+1,0);
				unpack(p-1,1);
				unpack(p+1,1);
				unpack(p,1);			
			}
			
		}
		else if(q==n-1&&p!=0&&p!=n-1)
		{
			if(mark[p-1][n-1]||!discoverd[p-1][n-1]) current_num++;
			if(mark[p+1][n-1]||!discoverd[p+1][n-1]) current_num++;
			if(mark[p-1][n-2]||!discoverd[p-1][n-2]) current_num++;
			if(mark[p+1][n-2]||!discoverd[p+1][n-2]) current_num++;
			if(mark[p][n-2]||!discoverd[p][n-2]) current_num++;
			if(current_num==real_num)
			{
				unpack(p-1,n-1);
				unpack(p+1,n-1);
				unpack(p-1,n-2);
				unpack(p+1,n-2);
				unpack(p,n-2);		
			}
		}
		else if(p==n-1&&q!=0&&q!=n-1)
		{
			if(mark[n-1][q-1]||!discoverd[n-1][q-1]) current_num++;
			if(mark[n-1][q+1]||!discoverd[n-1][q+1]) current_num++;
			if(mark[n-2][q-1]||!discoverd[n-2][q-1]) current_num++;
			if(mark[n-2][q+1]||!discoverd[n-2][q+1]) current_num++;
			if(mark[n-2][q]||!discoverd[n-2][q]) current_num++;
			if(current_num==real_num)
			{
				unpack(n-1,q-1);
				unpack(n-1,q+1);
				unpack(n-2,q-1);
				unpack(n-2,q+1);
				unpack(n-2,q);			
			}
		}
		else
		{
			if(mark[p-1][q]||!discoverd[p-1][q]) current_num++;
			if(mark[p-1][q-1]||!discoverd[p-1][q-1]) current_num++;
			if(mark[p-1][q+1]||!discoverd[p-1][q+1]) current_num++;
			if(mark[p+1][q]||!discoverd[p+1][q]) current_num++;
			if(mark[p+1][q-1]||!discoverd[p+1][q-1]) current_num++;
			if(mark[p+1][q+1]||!discoverd[p+1][q+1]) current_num++;
			if(mark[p][q-1]||!discoverd[p][q-1]) current_num++;
			if(mark[p][q+1]||!discoverd[p][q+1]) current_num++;
			if(current_num==real_num)
			{
				unpack(p-1,q);
				unpack(p-1,q-1);
				unpack(p-1,q+1);
				unpack(p+1,q);
				unpack(p+1,q-1);
				unpack(p+1,q+1);
				unpack(p,q-1);
				unpack(p,q+1);				
			}
		}
	}
	public void mission_failed()			//�ȵ����ˣ��ֶ�����
	{
		pause=true;completed=true;failed=true;
		Icon image;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				if(table[i][j]&&!discoverd[i][j])
				{
					image=new ImageIcon("saolei_pic//��.jpg");
					label[i][j].setIcon(image);
				}
				else if(!table[i][j]&&mark[i][j])
				{
					image=new ImageIcon("saolei_pic//��3.jpg");
					label[i][j].setIcon(image);
				}
			}
		}
		JOptionPane.showMessageDialog(null, "��������ɵ���˰ɡ���");
	}
	public void seed_spread(int p,int q)					//�������Χû���ף���������ɨ�跽������
	{
		if(p==0&&q==0)
		{
			unpack(1,0);
			unpack(1,1);
			unpack(0,1);
		}
		else if(p==0&&q==n-1)
		{
			unpack(1,n-1);
			unpack(1,n-2);
			unpack(0,n-2);
		}
		else if(p==0&&q!=0&&q!=n-1)
		{
			unpack(1,q);
			unpack(0,q+1);
			unpack(0,q-1);
			unpack(1,q+1);
			unpack(1,q-1);
		}
		else if(q==0&&p==n-1)
		{
			unpack(n-2,0);
			unpack(n-2,1);
			unpack(n-1,1);
		}
		else if(p==n-1&&q==n-1)
		{
			unpack(n-2,n-2);
			unpack(n-1,n-2);
			unpack(n-2,n-1);
		}
		else if(q==0&&p!=0&&p!=n-1)
		{
			unpack(p-1,0);
			unpack(p+1,0);
			unpack(p-1,1);
			unpack(p+1,1);
			unpack(p,1);
		}
		else if(q==n-1&&p!=0&&p!=n-1)
		{
			unpack(p-1,n-1);
			unpack(p+1,n-1);
			unpack(p-1,n-2);
			unpack(p+1,n-2);
			unpack(p,n-2);
		}
		else if(p==n-1&&q!=0&&q!=n-1)
		{
			unpack(n-1,q-1);
			unpack(n-1,q+1);
			unpack(n-2,q-1);
			unpack(n-2,q+1);
			unpack(n-2,q);
		}
		else
		{
			unpack(p-1,q);
			unpack(p-1,q-1);
			unpack(p-1,q+1);
			unpack(p+1,q);
			unpack(p+1,q-1);
			unpack(p+1,q+1);
			unpack(p,q-1);
			unpack(p,q+1);
		}
	}
	public void unpack(int p,int q)	//��ĳһ�������ò��ʾ����
	{
		if(!discoverd[p][q])
		{
			if(view_table[p][q]==9)
			{
				Icon image;
				image=new ImageIcon("saolei_pic//rect2.jpg");
				label[p][q].setIcon(image);
				mark[p][q]=true;
				discoverd[p][q]=true;
				bomb_left--;
				bomb_left_txt.setText(""+bomb_left);
				
			}
			else if(view_table[p][q]==0)		//û���ף���������ɨ�跨����
			{
				Icon image;
				image=new ImageIcon("saolei_pic//num0.jpg");
				label[p][q].setIcon(image);
				discoverd[p][q]=true;
				seed_spread(p,q);
			}
			else
			{
				Icon image;
				image=new ImageIcon("saolei_pic//num"+view_table[p][q]+".jpg");
				label[p][q].setIcon(image);
				discoverd[p][q]=true;
			}
		}
	}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public static void main(String args[])
	{
		/*main m=new main(10);
		m.setVisible(true);*/
		saolei s=new saolei();
		s.setVisible(true);
	}
}