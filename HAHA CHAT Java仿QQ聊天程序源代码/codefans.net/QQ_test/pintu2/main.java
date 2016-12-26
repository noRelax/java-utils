package pintu2;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
import haha_game.RecButton;
public class main extends JFrame implements ActionListener,Runnable{
		private static int n;
		private int w=0,h=0;
		private JTextField time_txt,steps_txt,level_txt;
		private JPanel main_panel=new JPanel();	
		private JLabel[][] label;
		private int[][] value;
		private RecButton start_button,pause_button,reset_button;
		private JLabel time_label,steps_label;
		private int i,j,wide=0,height=0,x=n-1,y=n-1,steps=0,level=50;
		public static int time=0;
		private Thread splashThread; 
		private boolean started=false,completed=true;
		private String path="";
		private CheckboxGroup ch;
		private Checkbox c1,c2,c3,c4;
		Icon image;
		public main(int nu)
		{
			n=nu;
			path="pintu_pic"+n+"//";
			label=new JLabel[n][n];
			value=new int[n][n];
			image=new ImageIcon(path+"1.jpg");
			w=image.getIconWidth();
			h=image.getIconHeight();
		setTitle("brick");
		wide=n*w+120;
		height=n*h+80;
		setSize(wide,height);
		setLocationRelativeTo(null); 
		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		main_panel.setLayout(null);
		main_panel.setBackground(Color.gray);
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				int num=i*n+j+1;
				label[i][j]=new JLabel();
				label[i][j].setBounds(new Rectangle(1+(w+1)*j, 1+(h+1)*(i),w,h));
				main_panel.add(label[i][j]);
				value[i][j]=0;
			}
		}
		
		time_label=new JLabel("Time");
		steps_label=new JLabel("Steps");
		
		time_label.setFont(new Font("宋体",Font.BOLD,15));
		steps_label.setFont(new Font("宋体",Font.BOLD,15));
		
		time_label.setForeground(Color.red);
		steps_label.setForeground(Color.red);
		
		time_txt=new JTextField(30);
		steps_txt=new JTextField(30);
		//level_txt=new JTextField(30);
		
		time_txt.setText(""+time);
		steps_txt.setText(""+steps);
		//level_txt.setText(""+n);
		
		time_txt.setEditable(false);
		steps_txt.setEditable(false);
		
		start_button=new RecButton("START");
		pause_button=new RecButton(null);
		pause_button.setText("PAUSE");
		reset_button=new RecButton("RESET");
		
		start_button.setForeground(Color.green);
		pause_button.setForeground(Color.green);
		reset_button.setForeground(Color.green);
		
		start_button.addActionListener(this);
		pause_button.addActionListener(this);
		reset_button.addActionListener(this);
		start_button.addKeyListener(new key_listener()) ;
		reset_button.addKeyListener(new key_listener()) ;
		
		ch=new CheckboxGroup(); 
		c1=new Checkbox("L1",ch,false);
		c2=new Checkbox("L2",ch,false);
		c3=new Checkbox("L3",ch,false);
		c4=new Checkbox("L4",ch,true);
		int avg=height/9;
		if(avg>30)
			avg=30;
		c1.setForeground(Color.green);
		c2.setForeground(Color.green);
		c3.setForeground(Color.green);
		c4.setForeground(Color.green);
		c1.setBounds(new Rectangle(10,20,30,10));
		c2.setBounds(new Rectangle(n*50/4+10,20,30,10));
		c3.setBounds(new Rectangle(n*50*2/4+10,20,30,10));
		c4.setBounds(new Rectangle(n*50*3/4+10,20,30,10));
		
		main_panel.setBounds(new Rectangle(10,40,w*n,h*n));
		//level_txt.setBounds(new Rectangle(n*50+20,5,80,avg));
		time_label.setBounds(new Rectangle(n*w+20,0,80,avg));
		time_txt.setBounds(new Rectangle(n*w+20,avg,80,avg));
		steps_label.setBounds(new Rectangle(n*w+20,2*avg,80,avg));
		steps_txt.setBounds(new Rectangle(n*w+20,5+3*avg,80,avg));
		start_button.setBounds(new Rectangle(n*w+20,10+4*avg,80,avg));
		pause_button.setBounds(new Rectangle(n*w+20,15+5*avg,80,avg));
		reset_button.setBounds(new Rectangle(n*w+20,20+6*avg,80,avg));
		c.add(c1);c.add(c2);c.add(c3);c.add(c4);
		c.add(main_panel);
		//c.add(level_txt);
		c.add(time_label);
		c.add(time_txt);
		c.add(steps_label);
		c.add(steps_txt);
		c.add(start_button);
		c.add(pause_button);
		c.add(reset_button);
		this.start();
	}
		public void ini()
		{
			started=true;
			completed=false;
			set_level();
			for(i=0;i<n;i++)
			{
				for(j=0;j<n;j++)
				{
					int num=i*n+j+1;
					//image=new ImageIcon(path+num+".jpg");
					//label[i][j].setIcon(image);
					value[i][j]=num;
				}
			}
			x=n-1;y=n-1;
			time=0;time_txt.setText("0");
			steps=0;steps_txt.setText("0");
			int rand=0,i=0;
			while(i<level)
			{
				rand=(int)(Math.random()*4)+1;
				//System.out.println(rand);
				switch(rand)
				{
					case 1:
						if(x>0)
							down();
						break;
					case 2:
						if(x<n-1)
							up();
						break;
					case 3:
						if(y>0)
							right();
						break;
					case 4:
						if(y<n-1)
							left();
						break;
				}
				i++;
			}
		}
		private void up()
		{
			if(!completed)
			{
				if(x>n-2) {System.out.println("下移出错");return;}
				/*String temp=label[x+1][y].getText();
				label[x][y].setText(temp);
				label[x+1][y].setText("");*/
				
				value[x][y]=value[x+1][y];
				image=new ImageIcon(path+value[x][y]+".jpg");
				label[x][y].setIcon(image);
				value[x+1][y]=n*n;
				image=new ImageIcon(path+value[x+1][y]+".jpg");
				label[x+1][y].setIcon(image);
				x=x+1;
			}
			
		}
		private void down()
		{
			if(!completed)
			{
				if(x<1) {System.out.println("下移出错");return;}
				/*String temp=label[x-1][y].getText();
				label[x][y].setText(temp);
				label[x-1][y].setText("");*/
				
				value[x][y]=value[x-1][y];
				image=new ImageIcon(path+value[x][y]+".jpg");
				label[x][y].setIcon(image);
				value[x-1][y]=n*n;
				image=new ImageIcon(path+value[x-1][y]+".jpg");
				label[x-1][y].setIcon(image);
				
				x=x-1;
			}
		}
		private void left()
		{
			if(!completed)
			{
				if(y>n-2) {System.out.println("右移出错");return;}
				/*String temp=label[x][y+1].getText();
				label[x][y].setText(temp);
				label[x][y+1].setText("");*/
				
				value[x][y]=value[x][y+1];
				image=new ImageIcon(path+value[x][y]+".jpg");
				label[x][y].setIcon(image);
				value[x][y+1]=n*n;
				image=new ImageIcon(path+value[x][y+1]+".jpg");
				label[x][y+1].setIcon(image);
				
				y=y+1;
			}
		}
		private void right()
		{
			if(!completed)
			{
				if(y<1) {System.out.println("左移出错");return;}
				/*String temp=label[x][y-1].getText();
				label[x][y].setText(temp);
				label[x][y-1].setText("");*/
				
				
				value[x][y]=value[x][y-1];
				image=new ImageIcon(path+value[x][y]+".jpg");
				label[x][y].setIcon(image);
				value[x][y-1]=n*n;
				image=new ImageIcon(path+value[x][y-1]+".jpg");
				label[x][y-1].setIcon(image);
				y=y-1;
			}
		}
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("START"))
			{
				ini();
			}
			else if(e.getActionCommand().equals("PAUSE"))
			{
				if(started==true)
				{
					started=false;
					pause_button.setText("CONTINUE");
				}
				else
				{
					started=true;
					pause_button.setText("PAUSE");
				}
			}
			else if(e.getActionCommand().equals("RESET"))
			{
				set_level();
				for(i=0;i<n;i++)
				{
					for(j=0;j<n;j++)
					{
						int num=i*n+j+1;
						image=new ImageIcon(path+num+".jpg");
						label[i][j].setIcon(image);
						value[i][j]=num;
					}
				}
				x=n-1;y=n-1;
				ini();		
				time=0;time_txt.setText("0");
				steps=0;steps_txt.setText("0");
				started=true;
				completed=false;
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
					if(started)
					{
						time++;
						time_txt.setText(time+"");
					}
					Thread.sleep(1000);
				}
			}catch(Exception e){System.out.println("计时出错");}
		}
		private class key_listener  extends KeyAdapter
		{
			public void keyReleased(KeyEvent key)
			{
				char c=key.getKeyChar();
				if(c=='5')
				{ 
					up();
					steps++;
					steps_txt.setText(""+steps);
					if(complete())
					{
						completed=true;
						over();
					}
				}
				else if(c== '2')
				{ 
					down();			
					steps++;
					steps_txt.setText(""+steps);
					if(complete())
					{
						completed=true;
						over();
					}
				}
				else if(c== '1')
				{ 
					left();			
					steps++;
					steps_txt.setText(""+steps);
					if(complete())
					{
						completed=true;
						over();
					}
				}
				else if(c=='3') 
				{ 
					right();			
					steps++;
					steps_txt.setText(""+steps);
					if(complete())
					{
						completed=true;
						over();
					}
				}
				else if(c==27)
					System.exit(0);
			}
		}	
		private boolean complete()
		{
			for(i=0;i<n;i++)
			{
				for(j=0;j<n;j++)
				{
					int num=i*n+j+1;
					System.out.print(value[i][j]+"\t");
					if(value[i][j]!=num&&((i+j)!=(2*n-2)))
						return false;
				}
				System.out.println();
			}
			return true;
		}
		private void set_level()
		{
			if(c1.getState())
				level=100;
			else if(c2.getState())
				level=500;
			else if(c3.getState())
				level=1000;
			else if(c4.getState())
				level=2000;
		}
		private void over()
		{
			//try{Thread.sleep(1000);}catch(Exception e){}
			started=false;
			JOptionPane.showMessageDialog(null, "牛逼啊！！！！");
		}
	public static void main(String args[])
	{
		choose c=new choose();
		c.setVisible(true);
	}
}