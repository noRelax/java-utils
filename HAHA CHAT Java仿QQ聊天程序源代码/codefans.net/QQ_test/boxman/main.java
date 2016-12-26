package boxman;
//Download by http://www.codefans.net
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import haha_game.RecButton;
public class main extends JFrame implements ActionListener,Runnable{
	private static int level_num;
	private static int time=0,level=1;
	private int num,x=0,y=0,m=0,n=0;					//记录雷的总数目
	private JTextField time_txt,level_txt;
	private JPanel main_panel=new JPanel();	
	private JLabel[][] label;
	private bitmap table;
	private maps map;
	private int c_t[][];
	private JLabel time_label,level_down_label;
	private int i,j,wide=0,height=0;
	private RecButton start_button,pause_button,reset_button,level_up_button,level_down_button;
	private Thread splashThread; 
	private boolean pause=true,completed=true;
	private Icon image;
	public main(int nu)
	{
		map=new maps();
		table=map.get_map(nu-1);
		level_num=map.level_n;
		m=table.row;
		n=table.col;
		label=new JLabel[m][n];
		c_t=new int[m][n];
		for(i=0;i<m;i++)
			for(j=0;j<n;j++)
				c_t[i][j]=table.t[i][j];
	setTitle("HAHA极品推箱子");
	wide=n*41+120;
	height=m*41+90;
	setSize(wide,height);
	setLocationRelativeTo(null); 
	Container c=getContentPane();
	c.setLayout(null);
	c.setBackground(Color.black);
	main_panel.setLayout(null);
	main_panel.setBackground(Color.LIGHT_GRAY);
	for(i=0;i<m;i++)
	{
		for(j=0;j<n;j++)
		{
			label[i][j]=new JLabel();
			if(table.t[i][j]==0)
				image=new ImageIcon("boxman_pic//floor.jpg");
			else if(table.t[i][j]==1)
				image=new ImageIcon("boxman_pic//box.jpg");
			else if(table.t[i][j]==2)
				image=new ImageIcon("boxman_pic//man.jpg");
			else if(table.t[i][j]==3)
				image=new ImageIcon("boxman_pic//floor2.jpg");
			else if(table.t[i][j]==4)
				image=new ImageIcon("boxman_pic//box2.jpg");
			else
				image=new ImageIcon("boxman_pic//wall.jpg");
			label[i][j].setIcon(image);
			label[i][j].setBounds(new Rectangle(1+41*j, 1+41*(i),40,40));
			main_panel.add(label[i][j]);
		}
	}

	time_label=new JLabel("Time:");
	level_down_label=new JLabel("<<");
	
	time_label.setFont(new Font("宋体",Font.BOLD,15));
	level_down_label.setFont(new Font("宋体",Font.BOLD,15));
	
	time_label.setForeground(Color.red);
	level_down_label.setForeground(Color.red);
	
	time_txt=new JTextField(10);
	level_txt=new JTextField(10);
	
	time_txt.setText(""+time);time_txt.setBackground(Color.DARK_GRAY);
	level_txt.setText(""+level);level_txt.setBackground(Color.DARK_GRAY);
	
	time_txt.setEnabled(false);
	level_txt.setEnabled(false);
	
	start_button=new RecButton("START");
	pause_button=new RecButton(null);
	pause_button.setText("PAUSE");
	reset_button=new RecButton("RESET");
	level_up_button=new RecButton(">>");
	level_down_button=new RecButton("<<");
	
	start_button.setForeground(Color.green);
	pause_button.setForeground(Color.green);
	reset_button.setForeground(Color.green);
	level_up_button.setForeground(Color.green);
	level_down_button.setForeground(Color.green);
	
	start_button.addActionListener(this);
	pause_button.addActionListener(this);
	reset_button.addActionListener(this);
	level_up_button.addActionListener(this);
	level_down_button.addActionListener(this);
	start_button.addKeyListener(new key_listener()) ;
	reset_button.addKeyListener(new key_listener()) ;
	
	main_panel.setBounds(new Rectangle(10,40,41*n,41*m));
	time_label.setBounds(new Rectangle(5,10,45,20));
	time_txt.setBounds(new Rectangle(50,10,30,20));
	level_down_button.setBounds(new Rectangle(100,10,50,20));
	level_txt.setBounds(new Rectangle(160,10,30,20));
	level_up_button.setBounds(new Rectangle(200,10,50,20));
	start_button.setBounds(new Rectangle(n*42+20,160,80,30));
	pause_button.setBounds(new Rectangle(n*42+20,200,80,30));
	reset_button.setBounds(new Rectangle(n*42+20,240,80,30));
	
	c.add(main_panel);
	c.add(time_label);
	c.add(time_txt);
	c.add(level_down_button);
	c.add(level_txt);
	c.add(level_up_button);
	c.add(start_button);
	c.add(pause_button);
	c.add(reset_button);
	this.start();
}
	public void ini()
	{
		for(i=0;i<m;i++)
		{
			for(j=0;j<n;j++)
			{
				c_t[i][j]=table.t[i][j];
				if(table.t[i][j]==0)
					image=new ImageIcon("boxman_pic//floor.jpg");
				else if(table.t[i][j]==1)
					image=new ImageIcon("boxman_pic//box.jpg");
				else if(table.t[i][j]==2)
					image=new ImageIcon("boxman_pic//man.jpg");
				else if(table.t[i][j]==3)
					image=new ImageIcon("boxman_pic//floor2.jpg");
				else if(table.t[i][j]==4)
					image=new ImageIcon("boxman_pic//box2.jpg");
				else
					image=new ImageIcon("boxman_pic//wall.jpg");
				label[i][j].setIcon(image);
			}
		}
		pause=false;
		completed=false;
		time=0;
		time_txt.setText(time+"");
		for(i=0;i<m;i++)
			for(j=0;j<n;j++)
				if(c_t[i][j]==2)
				{
					x=i;y=j;
					break;
				}
	}
	public void map_ini()
	{
		for(i=0;i<m;i++)
		{
			for(j=0;j<n;j++)
			{
				if(table.t[i][j]==0)
					image=new ImageIcon("boxman_pic//floor.jpg");
				else if(table.t[i][j]==1)
					image=new ImageIcon("boxman_pic//box.jpg");
				else if(table.t[i][j]==2)
					image=new ImageIcon("boxman_pic//man.jpg");
				else if(table.t[i][j]==3)
					image=new ImageIcon("boxman_pic//floor2.jpg");
				else if(table.t[i][j]==4)
					image=new ImageIcon("boxman_pic//box2.jpg");
				else
					image=new ImageIcon("boxman_pic//wall.jpg");
				label[i][j].setIcon(image);
			}
		}
		pause=true;
		completed=true;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("START"))
		{
			ini();	
		}
		else if(e.getActionCommand().equals("PAUSE"))
		{
			if(!pause)
			{
				pause=true;
				pause_button.setText("CONTINUE");
			}
			else
			{
				pause=false;
				pause_button.setText("PAUSE");
			}
		}
		else if(e.getActionCommand().equals("RESET"))
		{
			ini();	
		}
		else if(e.getActionCommand().equals(">>"))
		{
			if(level<level_num)
			{
				level++;
				level_txt.setText(""+level);
				map_ini();
				this.dispose();
				main m=new main(level);
				m.setVisible(true);
			}
		}
		else if(e.getActionCommand().equals("<<"))
		{
			if(level>1)
			{
				level--;
				level_txt.setText(""+level);
				map_ini();
				this.dispose();
				main m=new main(level);
				m.setVisible(true);
			}	
		}
	}
	public void start()
	{
		this.toFront();  //窗口前端显示
		splashThread=new Thread(this);  //实例化线程
		splashThread.start();  //开始运行线程
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
		}catch(Exception e){System.out.println("计时出错");}
	}
	private boolean complete()
	{
		boolean over=true;
		for(i=0;i<m;i++)
		{
			for(j=0;j<n;j++)
			{
				if(table.t[i][j]==3||table.t[i][j]==4)
				{
					if(c_t[i][j]!=4)
					{
						over=false;
						break;
					}
				}
			}
			if(!over)
				break;
		}
		return over;
	}
	private void over()
	{
		//try{Thread.sleep(1000);}catch(Exception e){}
		pause=true;
		JOptionPane.showMessageDialog(null, "牛逼啊！！！！");
		
		if(level<level_num-1)
		{
			level++;
			this.dispose();
			main m=new main(level);
			m.setVisible(true);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "游戏结束！");
			System.exit(0);
		}
	}
	private class key_listener  extends KeyAdapter
	{
		public void keyReleased(KeyEvent key)
		{
			char c=key.getKeyChar();
			System.out.println((int)c);
			if(c=='1')
			{ 
				left();
			}
			else if(c=='3')
			{ 
				right();
			}
			else if(c== '5')
			{ 
				up();
			}
			else if(c=='2')
			{ 
				down();
			}
			else if(c==27)
				System.exit(0);
		}
	}	
	private void left()
	{
		if(y>0&&(c_t[x][y-1]==0||c_t[x][y-1]==3))   //左边为空或目标
		{
			if(c_t[x][y-1]==0)
			{
				image=new ImageIcon("boxman_pic//man_left.jpg");
				label[x][y-1].setIcon(image);
				c_t[x][y-1]=3;
				if(c_t[x][y]==6)
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y-1;
			}
			else if(c_t[x][y-1]==3)
			{
				image=new ImageIcon("boxman_pic//man_left.jpg");
				label[x][y-1].setIcon(image);
				c_t[x][y-1]=6;
				if(c_t[x][y]==6)
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y-1;
			}
		}
		else if(y>1&&(c_t[x][y-2]==0||c_t[x][y-2]==3)&&(c_t[x][y-1]==1||c_t[x][y-1]==4))
		{
			if(c_t[x][y-2]==0&&c_t[x][y-1]==1)//左1：箱子，左2：空
			{
				image=new ImageIcon("boxman_pic//man_left.jpg");
				label[x][y-1].setIcon(image);
				c_t[x][y-1]=2;
				image=new ImageIcon("boxman_pic//box.jpg");
				label[x][y-2].setIcon(image);
				c_t[x][y-2]=1;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y-1;
			}
			else if(c_t[x][y-2]==0&&c_t[x][y-1]==4)//左1：箱子目标，左2：空
			{
				image=new ImageIcon("boxman_pic//man_left.jpg");
				label[x][y-1].setIcon(image);
				c_t[x][y-1]=6;
				image=new ImageIcon("boxman_pic//box.jpg");
				label[x][y-2].setIcon(image);
				c_t[x][y-2]=1;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y-1;
			}
			else if(c_t[x][y-2]==3&&c_t[x][y-1]==1)//左1：箱子，左2：目标
			{
				image=new ImageIcon("boxman_pic//man_left.jpg");
				label[x][y-1].setIcon(image);
				c_t[x][y-1]=2;
				image=new ImageIcon("boxman_pic//box2.jpg");
				label[x][y-2].setIcon(image);
				c_t[x][y-2]=4;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y-1;
			}
			else if(c_t[x][y-2]==3&&c_t[x][y-1]==4)//左1：箱子目标，左2：目标
			{
				image=new ImageIcon("boxman_pic//man_left.jpg");
				label[x][y-1].setIcon(image);
				c_t[x][y-1]=6;
				image=new ImageIcon("boxman_pic//box2.jpg");
				label[x][y-2].setIcon(image);
				c_t[x][y-2]=4;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y-1;
			}
		}
		if(complete())
		{over();}
	}
	private void right()
	{
		if(y<n-1&&(c_t[x][y+1]==0||c_t[x][y+1]==3))   //左边为空或目标
		{
			if(c_t[x][y+1]==0)
			{
				image=new ImageIcon("boxman_pic//man_right.jpg");
				label[x][y+1].setIcon(image);
				c_t[x][y+1]=3;
				if(c_t[x][y]==6)
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y+1;
			}
			else if(c_t[x][y+1]==3)
			{
				image=new ImageIcon("boxman_pic//man_right.jpg");
				label[x][y+1].setIcon(image);
				c_t[x][y+1]=6;
				if(c_t[x][y]==6)
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y+1;
			}
		}
		else if(y<n-2&&(c_t[x][y+2]==0||c_t[x][y+2]==3)&&(c_t[x][y+1]==1||c_t[x][y+1]==4))
		{
			if(c_t[x][y+2]==0&&c_t[x][y+1]==1)//左1：箱子，左2：空
			{
				image=new ImageIcon("boxman_pic//man_right.jpg");
				label[x][y+1].setIcon(image);
				c_t[x][y+1]=2;
				image=new ImageIcon("boxman_pic//box.jpg");
				label[x][y+2].setIcon(image);
				c_t[x][y+2]=1;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y+1;
			}
			else if(c_t[x][y+2]==0&&c_t[x][y+1]==4)//左1：箱子目标，左2：空
			{
				image=new ImageIcon("boxman_pic//man_right.jpg");
				label[x][y+1].setIcon(image);
				c_t[x][y+1]=6;
				image=new ImageIcon("boxman_pic//box.jpg");
				label[x][y+2].setIcon(image);
				c_t[x][y+2]=1;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y+1;
			}
			else if(c_t[x][y+2]==3&&c_t[x][y+1]==1)//左1：箱子，左2：目标
			{
				image=new ImageIcon("boxman_pic//man_right.jpg");
				label[x][y+1].setIcon(image);
				c_t[x][y+1]=2;
				image=new ImageIcon("boxman_pic//box2.jpg");
				label[x][y+2].setIcon(image);
				c_t[x][y+2]=4;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y+1;
			}
			else if(c_t[x][y+2]==3&&c_t[x][y+1]==4)//左1：箱子目标，左2：目标
			{
				image=new ImageIcon("boxman_pic//man_right.jpg");
				label[x][y+1].setIcon(image);
				c_t[x][y+1]=6;
				image=new ImageIcon("boxman_pic//box2.jpg");
				label[x][y+2].setIcon(image);
				c_t[x][y+2]=4;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				y=y+1;
			}
		}
		if(complete())
		{over();}
	}
	private void up()
	{
		if(x>0&&(c_t[x-1][y]==0||c_t[x-1][y]==3))   //左边为空或目标
		{
			if(c_t[x-1][y]==0)
			{
				image=new ImageIcon("boxman_pic//man_up.jpg");
				label[x-1][y].setIcon(image);
				c_t[x-1][y]=3;
				if(c_t[x][y]==6)
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x-1;
			}
			else if(c_t[x-1][y]==3)
			{
				image=new ImageIcon("boxman_pic//man_up.jpg");
				label[x-1][y].setIcon(image);
				c_t[x-1][y]=6;
				if(c_t[x][y]==6)
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x-1;
			}
		}
		else if(x>1&&(c_t[x-2][y]==0||c_t[x-2][y]==3)&&(c_t[x-1][y]==1||c_t[x-1][y]==4))
		{
			if(c_t[x-2][y]==0&&c_t[x-1][y]==1)//左1：箱子，左2：空
			{
				image=new ImageIcon("boxman_pic//man_up.jpg");
				label[x-1][y].setIcon(image);
				c_t[x-1][y]=2;
				image=new ImageIcon("boxman_pic//box.jpg");
				label[x-2][y].setIcon(image);
				c_t[x-2][y]=1;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x-1;
			}
			else if(c_t[x-2][y]==0&&c_t[x-1][y]==4)//左1：箱子目标，左2：空
			{
				image=new ImageIcon("boxman_pic//man_up.jpg");
				label[x-1][y].setIcon(image);
				c_t[x-1][y]=6;
				image=new ImageIcon("boxman_pic//box.jpg");
				label[x-2][y].setIcon(image);
				c_t[x-2][y]=1;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x-1;
			}
			else if(c_t[x-2][y]==3&&c_t[x-1][y]==1)//左1：箱子，左2：目标
			{
				image=new ImageIcon("boxman_pic//man_up.jpg");
				label[x-1][y].setIcon(image);
				c_t[x-1][y]=2;
				image=new ImageIcon("boxman_pic//box2.jpg");
				label[x-2][y].setIcon(image);
				c_t[x-2][y]=4;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x-1;
			}
			else if(c_t[x-2][y]==3&&c_t[x-1][y]==4)//左1：箱子目标，左2：目标
			{
				image=new ImageIcon("boxman_pic//man_up.jpg");
				label[x-1][y].setIcon(image);
				c_t[x-1][y]=6;
				image=new ImageIcon("boxman_pic//box2.jpg");
				label[x-2][y].setIcon(image);
				c_t[x-2][y]=4;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x-1;
			}
		}
		if(complete())
		{over();}
	}
	private void down()
	{
		if(x<m-1&&(c_t[x+1][y]==0||c_t[x+1][y]==3))   //左边为空或目标
		{
			if(c_t[x+1][y]==0)
			{
				image=new ImageIcon("boxman_pic//man_down.jpg");
				label[x+1][y].setIcon(image);
				c_t[x+1][y]=3;
				if(c_t[x][y]==6)
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x+1;
			}
			else if(c_t[x+1][y]==3)
			{
				image=new ImageIcon("boxman_pic//man_down.jpg");
				label[x+1][y].setIcon(image);
				c_t[x+1][y]=6;
				if(c_t[x][y]==6)
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x+1;
			}
		}
		else if(x<m-2&&(c_t[x+2][y]==0||c_t[x+2][y]==3)&&(c_t[x+1][y]==1||c_t[x+1][y]==4))
		{
			if(c_t[x+2][y]==0&&c_t[x+1][y]==1)//左1：箱子，左2：空
			{
				image=new ImageIcon("boxman_pic//man_down.jpg");
				label[x+1][y].setIcon(image);
				c_t[x+1][y]=2;
				image=new ImageIcon("boxman_pic//box.jpg");
				label[x+2][y].setIcon(image);
				c_t[x+2][y]=1;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x+1;
			}
			else if(c_t[x+2][y]==0&&c_t[x+1][y]==4)//左1：箱子目标，左2：空
			{
				image=new ImageIcon("boxman_pic//man_down.jpg");
				label[x+1][y].setIcon(image);
				c_t[x+1][y]=6;
				image=new ImageIcon("boxman_pic//box.jpg");
				label[x+2][y].setIcon(image);
				c_t[x+2][y]=1;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x+1;
			}
			else if(c_t[x+2][y]==3&&c_t[x+1][y]==1)//左1：箱子，左2：目标
			{
				image=new ImageIcon("boxman_pic//man_down.jpg");
				label[x+1][y].setIcon(image);
				c_t[x+1][y]=2;
				image=new ImageIcon("boxman_pic//box2.jpg");
				label[x+2][y].setIcon(image);
				c_t[x+2][y]=4;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x+1;
			}
			else if(c_t[x+2][y]==3&&c_t[x+1][y]==4)//左1：箱子目标，左2：目标
			{
				image=new ImageIcon("boxman_pic//man_down.jpg");
				label[x+1][y].setIcon(image);
				c_t[x+1][y]=6;
				image=new ImageIcon("boxman_pic//box2.jpg");
				label[x+2][y].setIcon(image);
				c_t[x+2][y]=4;
				if(c_t[x][y]==6)//本地：人目标
				{
					image=new ImageIcon("boxman_pic//floor2.jpg");
					c_t[x][y]=3;
				}
				else//本地：人
				{
					image=new ImageIcon("boxman_pic//floor.jpg");
					c_t[x][y]=0;
				}
				label[x][y].setIcon(image);
				x=x+1;
			}
		}
		if(complete())
		{over();}
	}
	public static void main(String args[])
	{
		main m=new main(level);
		m.setVisible(true);
		/*boxman s=new boxman();
		s.setVisible(true);*/
	}
}