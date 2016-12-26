package black_white;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import haha_game.RecButton;
public class main extends JFrame implements ActionListener,MouseListener{
	private static int n=10,stop_times=0,over_style=0;
	private int x=0,y=0,num1=2,num2=2;					//记录雷的总数目
	private JTextField player1_txt,player2_txt;
	private JPanel main_panel=new JPanel();	
	private JLabel[][] label;
	private int[][] mark;
	private int[][] cost1;
	private int[][] cost2;
	private JLabel player1_label,player2_label;
	private RecButton reset_button;
	private int i,j,wide=0,height=0,turn=1;
	private boolean complete=true,failed=false;
	Icon image;
	public main()
	{
		label=new JLabel[n][n];
		cost1=new int[n][n];
		cost2=new int[n][n];
		mark=new int[n][n];
	setTitle("HAHA极品黑白棋");
	wide=n*30+45;
	height=n*30+125;
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
			label[i][j]=new JLabel();
			label[i][j].addMouseListener(this);
			label[i][j].setBounds(new Rectangle(1+30*j, 1+30*(i),30,30));
			main_panel.add(label[i][j]);
		}
	}
	map_ini();
	player1_label=new JLabel("玩家:");
	player2_label=new JLabel("电脑:");
	
	player1_label.setFont(new Font("宋体",Font.BOLD,15));
	player2_label.setFont(new Font("宋体",Font.BOLD,15));
	
	player1_label.setForeground(Color.red);
	player2_label.setForeground(Color.red);
	//Download by http://www.codefans.net
	player1_txt=new JTextField(10);
	player2_txt=new JTextField(10);
	
	player1_txt.setText(""+num1);player1_txt.setBackground(Color.DARK_GRAY);
	player2_txt.setText(""+num2);player2_txt.setBackground(Color.DARK_GRAY);
	
	player1_txt.setEnabled(false);
	player2_txt.setEnabled(false);
	
	reset_button=new RecButton("RESET");
	reset_button.setForeground(Color.green);
	reset_button.addActionListener(this);
	
	main_panel.setBounds(new Rectangle(10,40,30*n,30*n));
	player1_label.setBounds(new Rectangle(5,10,45,20));
	player1_txt.setBounds(new Rectangle(50,10,30,20));
	player2_label.setBounds(new Rectangle(100,10,45,20));
	player2_txt.setBounds(new Rectangle(145,10,30,20));
	reset_button.setBounds(new Rectangle(190,10,80,20));
	
	c.add(main_panel);
	c.add(player1_label);
	c.add(player1_txt);
	c.add(player2_label);
	c.add(player2_txt);
	c.add(reset_button);
}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("RESET"))
		{
			map_ini();
			complete=false;
			failed=false;
			num1=2;player1_txt.setText(num1+"");
			num2=2;player2_txt.setText(num2+"");
			//cvc();
		}
	}
	private boolean complete()
	{
		boolean over1=true,over2=true,over3=true;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				if(mark[i][j]==0)
				{
					over1=false;
				}
				else if(mark[i][j]==1)
				{
					over2=false;
				}
				else
				{
					over3=false;
				}
			}
		}
		if(over1||over2||over3)
		{
			if(over1) over_style=0;
			if(over2||over3) over_style=1;
		}
		return (over1||over2||over3);
	}
	private void over()
	{
		if(i==0)		//正常结束
		{
			if(num1>num2)
				JOptionPane.showMessageDialog(null,"游戏结束,你输了");
			else if(num1<num2)
				JOptionPane.showMessageDialog(null,"游戏结束,电脑输了");
			else if(num1==num2)
				JOptionPane.showMessageDialog(null,"游戏结束,平局");
		}
		else if(i==1)		//非正常结束
		{
			if(num1==0)
				JOptionPane.showMessageDialog(null, "傻逼啊！！！！啊哈哈");
			else if(num2==0)
				JOptionPane.showMessageDialog(null, "靠，这么牛逼啊");
			else
			{
				if(num1>num2)
					JOptionPane.showMessageDialog(null,"游戏结束,你输了");
				if(num1<num2)
					JOptionPane.showMessageDialog(null,"游戏结束,电脑输了");
				if(num1==num2)
					JOptionPane.showMessageDialog(null,"游戏结束,平局");
			}
		}
	}
	public void mouseClicked(MouseEvent e)
	{ 
		JLabel l=(JLabel)e.getSource();
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
		if (e.getClickCount() == 1&&mark[x][y]==0) 
		{
			if(can_be_setted())
			{
				stop_times=0;
				if(set1(x,y)&&turn==1)
				{
					if(x==0&&y==0)
					{
						image=new ImageIcon("black_white_pic//left-top"+turn+".jpg");
						label[x][y].setIcon(image);
						if(turn==1){num1++;	player1_txt.setText(num1+"");}
						else {num2++;	player2_txt.setText(num2+"");}
						mark[x][y]=turn;
						if(complete())
							over();
					}
					else if(x==0&&y!=0&&y!=n-1)
					{
						image=new ImageIcon("black_white_pic//top"+turn+".jpg");
						label[x][y].setIcon(image);
						if(turn==1){num1++;	player1_txt.setText(num1+"");}
						else {num2++;	player2_txt.setText(num2+"");}
						mark[x][y]=turn;
						if(complete())
							over();
					}
					else if(x==0&y==n-1)
					{
						image=new ImageIcon("black_white_pic//right-top"+turn+".jpg");
						label[x][y].setIcon(image);
						if(turn==1){num1++;	player1_txt.setText(num1+"");}
						else {num2++;	player2_txt.setText(num2+"");}
						mark[x][y]=turn;
						if(complete())
							over();
					}
					else if(x!=0&&y==0&&x!=n-1)
					{
						image=new ImageIcon("black_white_pic//left"+turn+".jpg");
						label[x][y].setIcon(image);
						if(turn==1){num1++;	player1_txt.setText(num1+"");}
						else {num2++;	player2_txt.setText(num2+"");}
						mark[x][y]=turn;
						if(complete())
							over();
					}
					else if(x!=0&&y==n-1&&x!=n-1)
					{
						image=new ImageIcon("black_white_pic//right"+turn+".jpg");
						label[x][y].setIcon(image);
						if(turn==1){num1++;	player1_txt.setText(num1+"");}
						else {num2++;	player2_txt.setText(num2+"");}
						mark[x][y]=turn;
						if(complete())
							over();
					}
					else if(x==n-1&&y==0)
					{
						image=new ImageIcon("black_white_pic//left-bottom"+turn+".jpg");
						label[x][y].setIcon(image);
						if(turn==1){num1++;	player1_txt.setText(num1+"");}
						else {num2++;	player2_txt.setText(num2+"");}
						mark[x][y]=turn;
						if(complete())
							over();
					}
					else if(x==n-1&&y==n-1)
					{
						image=new ImageIcon("black_white_pic//right-bottom"+turn+".jpg");
						label[x][y].setIcon(image);
						if(turn==1){num1++;	player1_txt.setText(num1+"");}
						else {num2++;	player2_txt.setText(num2+"");}
						mark[x][y]=turn;
						if(complete())
							over();
					}
					else if(x==n-1&&y!=0&&y!=n-1)
					{
						image=new ImageIcon("black_white_pic//bottom"+turn+".jpg");
						label[x][y].setIcon(image);
						if(turn==1){num1++;	player1_txt.setText(num1+"");}
						else {num2++;	player2_txt.setText(num2+"");}
						mark[x][y]=turn;
						if(complete())
							over();
					}
					else
					{
						image=new ImageIcon("black_white_pic//center"+turn+".jpg");
						label[x][y].setIcon(image);
						if(turn==1){num1++;	player1_txt.setText(num1+"");}
						else {num2++;	player2_txt.setText(num2+"");}
						mark[x][y]=turn;
						
						if(complete())
							over();
					}				
					change_turn();
				}
				else
					return;
			}
			else
			{
				stop_times++;
				if(complete())
					over();
				if(stop_times==2)
				{
					over_style=1;
					over();
				}
				change_turn();
			}
			if(can_be_setted()&&turn==2)
			{
				stop_times=0;
				update_cost();
				get_max_cost();
				player_auto();
				if(complete())
					over();
				change_turn();
			}
			else
			{
				stop_times++;
				if(complete())
					over();
				if(stop_times==2)
				{
					over_style=1;
					over();
				}
				change_turn();
			}
		}
	}
	private void player_auto()
	{
		int next=0;
		if(turn==1) next=2;
		else next=1;
		if(x==0&&y==0)
		{
			image=new ImageIcon("black_white_pic//left-top"+turn+".jpg");
			mark[x][y]=turn;
			if(turn==1){num1++;player1_txt.setText(num1+"");}
			else{num2++;player2_txt.setText(num2+"");}
		}
		else if(x==0&&y!=0&&y!=n-1)
		{
			image=new ImageIcon("black_white_pic//top"+turn+".jpg");
			mark[x][y]=turn;
			if(turn==1){num1++;player1_txt.setText(num1+"");}
			else{num2++;player2_txt.setText(num2+"");}
		}
		else if(x==0&y==n-1)
		{
			image=new ImageIcon("black_white_pic//right-top"+turn+".jpg");
			mark[x][y]=turn;
			if(turn==1){num1++;player1_txt.setText(num1+"");}
			else{num2++;player2_txt.setText(num2+"");}
		}
		else if(x!=0&&y==0&&x!=n-1)
		{
			image=new ImageIcon("black_white_pic//left"+turn+".jpg");
			mark[x][y]=turn;
			if(turn==1){num1++;player1_txt.setText(num1+"");}
			else{num2++;player2_txt.setText(num2+"");}
		}
		else if(x!=0&&y==n-1&&x!=n-1)
		{
			image=new ImageIcon("black_white_pic//right"+turn+".jpg");
			mark[x][y]=turn;
			if(turn==1){num1++;player1_txt.setText(num1+"");}
			else{num2++;player2_txt.setText(num2+"");}
		}
		else if(x==n-1&&y==0)
		{
			image=new ImageIcon("black_white_pic//left-bottom"+turn+".jpg");
			mark[x][y]=turn;
			if(turn==1){num1++;player1_txt.setText(num1+"");}
			else{num2++;player2_txt.setText(num2+"");}
		}
		else if(x==n-1&&y==n-1)
		{
			image=new ImageIcon("black_white_pic//right-bottom"+turn+".jpg");
			mark[x][y]=turn;
			if(turn==1){num1++;player1_txt.setText(num1+"");}
			else{num2++;player2_txt.setText(num2+"");}
		}
		else if(x==n-1&&y!=0&&y!=n-1)
		{
			image=new ImageIcon("black_white_pic//bottom"+turn+".jpg");
			mark[x][y]=turn;
			if(turn==1){num1++;player1_txt.setText(num1+"");}
			else{num2++;player2_txt.setText(num2+"");}
		}
		else
		{
			image=new ImageIcon("black_white_pic//center"+turn+".jpg");
			mark[x][y]=turn;
			if(turn==1){num1++;player1_txt.setText(num1+"");}
			else{num2++;player2_txt.setText(num2+"");}
		}
		label[x][y].setIcon(image);
		set1(x,y);
	}
	private void change_turn()
	{
		if(turn==1)
			turn=2;
		else
			turn=1;
	}
	private void map_ini()
	{
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				if(i==0&&j==0)
				{
					image=new ImageIcon("black_white_pic//left-top0.jpg");
					mark[i][j]=0;
				}
				else if(i==0&&j!=0&&j!=n-1)
				{
					image=new ImageIcon("black_white_pic//top0.jpg");
					mark[i][j]=0;
				}
				else if(i==0&j==n-1)
				{
					image=new ImageIcon("black_white_pic//right-top0.jpg");
					mark[i][j]=0;
				}
				else if(i!=0&&j==0&&i!=n-1)
				{
					image=new ImageIcon("black_white_pic//left0.jpg");
					mark[i][j]=0;
				}
				else if(i!=0&&j==n-1&&i!=n-1)
				{
					image=new ImageIcon("black_white_pic//right0.jpg");
					mark[i][j]=0;
				}
				else if(i==n-1&&j==0)
				{
					image=new ImageIcon("black_white_pic//left-bottom0.jpg");
					mark[i][j]=0;
				}
				else if(i==n-1&&j==n-1)
				{
					image=new ImageIcon("black_white_pic//right-bottom0.jpg");
					mark[i][j]=0;
				}
				else if(i==n-1&&j!=0&&j!=n-1)
				{
					image=new ImageIcon("black_white_pic//bottom0.jpg");
					mark[i][j]=0;
				}
				else
				{
					if((i==4&&j==4)||(i==5&&j==5))
					{
						image=new ImageIcon("black_white_pic//center1.jpg");
						mark[i][j]=1;
					}
					else if((i==5&&j==4)||(i==4&&j==5))
					{
						image=new ImageIcon("black_white_pic//center2.jpg");
						mark[i][j]=2;
					}
					else
					{
						image=new ImageIcon("black_white_pic//center0.jpg");
						mark[i][j]=0;
					}
				}
				label[i][j].setIcon(image);
				cost1[i][j]=0;
				cost2[i][j]=0;
			}
		}
	}
	public void update_cost()
	{
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				cost1[i][j]=0;
				cost2[i][j]=0;
			}
		}
		for(int p=0;p<n;p++)
		{
			for(int q=0;q<n;q++)
			{
				if(mark[p][q]==0)
				{
					cost(p,q);
					//System.out.print(cost2[p][q]+"\t");
				}
			}
			//System.out.println();
		}
	}
	public void cost(int a,int b)
	{
		boolean temp;
		int temp_cost=0;
			//向下计算权值
		if(a<n-2)
		{
			temp_cost=0;
			temp=false;
			for(i=a+1;i<n;i++)
			{
				if(mark[i][b]==1)
				{
					temp=true;break;
				}
				else if(mark[i][b]==0)	break;
				else if(mark[i][b]==2)	temp_cost++;
			}
			if(temp)
			{
				cost1[a][b]+=temp_cost;	
			}
		}
			//向上计算权值
		if(a>1)
		{
			temp_cost=0;
			temp=false;
			for(i=a-1;i>=0;i--)
			{
				if(mark[i][b]==1)
				{
					temp=true;break;
				}
				else if(mark[i][b]==0)	break;
				else if(mark[i][b]==2)	temp_cost++;
			}
			if(temp)
			{
				cost1[a][b]+=temp_cost;	
			}
		}
			//向右计算权值
		if(b<n-2)
		{
			temp_cost=0;
			temp=false;
			for(j=b+1;j<n;j++)
			{
				if(mark[a][j]==1)
				{
					temp=true;break;
				}
				else if(mark[a][j]==0)	break;
				else if(mark[a][j]==2)	temp_cost++;
			}
			if(temp) 
			{
				cost1[a][b]+=temp_cost;
				
			}
		}
			//向左计算权值
		if(b>1)
		{
			temp_cost=0;
			temp=false;
			for(j=b-1;j>=0;j--)
			{
				if(mark[a][j]==1)
				{
					temp=true;break;
				}
				else if(mark[a][j]==0)	break;
				else if(mark[a][j]==2)	temp_cost++;
			}
			if(temp) 
			{
				cost1[a][b]+=temp_cost;
				
			}
		}
//			向左上计算权值
		if(a>1&&b>1)
		{
			temp_cost=0;
			temp=false;
			for(j=b-1,i=a-1;j>=0&&i>=0;j--,i--)
			{
				if(mark[i][j]==1)
				{
					temp=true;break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==2)	temp_cost++;
			}
			if(temp) 
			{
				cost1[a][b]+=temp_cost;
				
			}
		}
//			向左下计算权值
		if(a<n-2&&b>1)
		{
			temp_cost=0;
			temp=false;
			for(j=b-1,i=a+1;j>=0&&i<n;j--,i++)
			{
				if(mark[i][j]==1)
				{
					temp=true;break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==2)	temp_cost++;
			}
			if(temp) 
			{
				cost1[a][b]+=temp_cost;
				
			}
		}
//			向右下计算权值
		if(a<n-2&&b<n-2)
		{
			temp_cost=0;
			temp=false;
			for(j=b+1,i=a+1;j<n&&i<n;j++,i++)
			{
				if(mark[i][j]==1)
				{
					temp=true;break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==2)	temp_cost++;
			}
			if(temp) 
			{
				cost1[a][b]+=temp_cost;
				
			}
		}
//			向右上计算权值
		if(a>1&&b<n-2)
		{
			temp_cost=0;
			temp=false;
			for(j=b+1,i=a-1;j<n&&i>=0;j++,i--)
			{
				if(mark[i][j]==1)
				{
					temp=true;break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==2)	temp_cost++;
			}
			if(temp) 
			{
				cost1[a][b]+=temp_cost;
				
			}
		}
			//向下计算权值
		if(a<n-2)
		{
			temp_cost=0;
			temp=false;
			for(i=a+1;i<n;i++)
			{
				if(mark[i][b]==2)
				{
					temp=true;break;
				}
				else if(mark[i][b]==0)	break;
				else if(mark[i][b]==1)	temp_cost++;
			}
			if(temp) 
			{
				cost2[a][b]+=temp_cost;
				if(temp_cost!=0)
					special_position_cost(a,b);
			}
		}
			//向上计算权值
		if(a>1)
		{
			temp_cost=0;
			temp=false;
			for(i=a-1;i>=0;i--)
			{
				if(mark[i][b]==2)
				{
					temp=true;break;
				}
				else if(mark[i][b]==0)	break;
				else if(mark[i][b]==1)	temp_cost++;
			}
			if(temp) 
			{
				cost2[a][b]+=temp_cost;
				if(temp_cost!=0)
					special_position_cost(a,b);
			}
		}
			//向右计算权值
		if(b<n-2)
		{
			temp_cost=0;
			temp=false;
			for(j=b+1;j<n;j++)
			{
				if(mark[a][j]==2)
				{
					temp=true;break;
				}
				else if(mark[a][j]==0)	break;
				else if(mark[a][j]==1)	temp_cost++;
			}
			if(temp) 
			{
				cost2[a][b]+=temp_cost;
				if(temp_cost!=0)
					special_position_cost(a,b);
			}
		}
			//向左计算权值
		if(b>1)
		{
			temp_cost=0;
			temp=false;
			for(j=b-1;j>=0;j--)
			{
				if(mark[a][j]==2)
				{
					temp=true;break;
				}
				else if(mark[a][j]==0)	break;
				else if(mark[a][j]==1)	temp_cost++;
			}
			if(temp) 
			{
				cost2[a][b]+=temp_cost;
				if(temp_cost!=0)
					special_position_cost(a,b);
			}
		}
//			向左上计算权值
		if(a>1&&b>1)
		{
			temp_cost=0;
			temp=false;
			for(j=b-1,i=a-1;j>=0&&i>=0;j--,i--)
			{
				if(mark[i][j]==2)
				{
					temp=true;break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==1)	temp_cost++;
			}
			if(temp) 
			{
				cost2[a][b]+=temp_cost;
				if(temp_cost!=0)
					special_position_cost(a,b);
			}
		}
//			向左下计算权值
		if(a<n-2&&b>1)
		{
			temp_cost=0;
			temp=false;
			for(j=b-1,i=a+1;j>=0&&i<n;j--,i++)
			{
				if(mark[i][j]==2)
				{
					temp=true;break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==1)	temp_cost++;
			}
			if(temp) 
			{
				cost2[a][b]+=temp_cost;
				if(temp_cost!=0)
					special_position_cost(a,b);
			}
		}
//			向右下计算权值
		if(a<n-2&&b<n-2)
		{
			temp_cost=0;
			temp=false;
			for(j=b+1,i=a+1;j<n&&i<n;j++,i++)
			{
				if(mark[i][j]==2)
				{
					temp=true;break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==1)	temp_cost++;
			}
			if(temp) 
			{
				cost2[a][b]+=temp_cost;
				if(temp_cost!=0)
					special_position_cost(a,b);
			}
		}
//			向右上计算权值
		if(a>1&&b<n-2)
		{
			temp_cost=0;
			temp=false;
			for(j=b+1,i=a-1;j<n&&i>=0;j++,i--)
			{
				if(mark[i][j]==2)
				{
					temp=true;break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==1)	temp_cost++;
			}
			if(temp) 
			{
				cost2[a][b]+=temp_cost;
				if(temp_cost!=0)
					special_position_cost(a,b);
			}
		}
	}
	public boolean set1(int a,int b)
	{
		boolean temp;
		boolean setted=false;
		int next=0;
		int starti,startj;
		if(turn==1) next=2;
		else next=1;
			//向下吃子

		if(a<n-2)
		{
			temp=false;
			for(i=a+1;i<n;i++)
			{
				if(mark[i][b]==turn)
				{
					if(temp)
					{
						setted=true;
						for(int index=a+1;index<i;index++)
						{
							change_color(index,b,turn);
						}
						break;
					}
					else
						break;
				}
				else if(mark[i][b]==0)	break;
				else if(mark[i][b]==next)	temp=true;
			}
		}
			//向上吃子
		if(a>1)
		{
			temp=false;
			for(i=a-1;i>=0;i--)
			{
				if(mark[i][b]==turn)
				{
					if(temp)
					{
						setted=true;
						for(int index=a-1;index>i;index--)
						{
							change_color(index,b,turn);
						}
						break;
					}
					else
						break;
				}
				else if(mark[i][b]==0)	break;
				else if(mark[i][b]==next)	temp=true;
			}
		}
			//向右计算权值
		if(b<n-2)
		{
			temp=false;
			for(j=b+1;j<n;j++)
			{
				if(mark[a][j]==turn)
				{
					if(temp)
					{
						setted=true;
						for(int index=b+1;index<j;index++)
						{
							change_color(a,index,turn);
						}
						break;
					}
					else
						break;
				}
				else if(mark[a][j]==0)	break;
				else if(mark[a][j]==next)	temp=true;
			}
		}
			//向左计算权值
		if(b>1)
		{
			temp=false;
			for(j=b-1;j>=0;j--)
			{
				if(mark[a][j]==turn)
				{
					if(temp)
					{
						setted=true;
						for(int index=b-1;index>j;index--)
						{
							change_color(a,index,turn);
						}
						break;
					}
					else
						break;
				}
				else if(mark[a][j]==0)	break;
				else if(mark[a][j]==next)	temp=true;
			}
		}
//			向左上计算权值
		if(a>1&&b>1)
		{
			temp=false;
			for(j=b-1,i=a-1;j>=0&&i>=0;j--,i--)
			{
				if(mark[i][j]==turn)
				{
					if(temp)
					{
						setted=true;
						for(int index1=a-1,index2=b-1;index1>i&&index2>j;index1--,index2--)
						{
							change_color(index1,index2,turn);
						}
						break;
					}
					else
						break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==next)	temp=true;
			}
		}
//			向左下计算权值
		if(a<n-2&&b>1)
		{
			temp=false;
			for(j=b-1,i=a+1;j>=0&&i<n;j--,i++)
			{
				if(mark[i][j]==turn)
				{
					if(temp)
					{
						setted=true;
						for(int index1=a+1,index2=b-1;index1<i&&index2>j;index1++,index2--)
						{
							change_color(index1,index2,turn);
						}
						break;
					}
					else
						break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==next)	temp=true;
			}
		}
//			向右下计算权值
		if(a<n-2&&b<n-2)
		{
			temp=false;
			for(j=b+1,i=a+1;j<n&&i<n;j++,i++)
			{
				if(mark[i][j]==turn)
				{
					if(temp)
					{
						setted=true;
						for(int index1=a+1,index2=b+1;index1<i&&index2<j;index1++,index2++)
						{
							change_color(index1,index2,turn);
						}
						break;
					}
					else
						break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==next)	temp=true;
			}
		}
//			向右上计算权值
		if(a>1&&b<n-2)
		{
			temp=false;
			for(j=b+1,i=a-1;j<n&&i>=0;j++,i--)
			{
				if(mark[i][j]==turn)
				{
					if(temp)
					{
						setted=true;
						for(int index1=a-1,index2=b+1;index1>i&&index2<j;index1--,index2++)
						{
							change_color(index1,index2,turn);
						}
						break;
					}
					else
						break;
				}
				else if(mark[i][j]==0)	break;
				else if(mark[i][j]==next)	temp=true;
			}
		}
		return setted;
	}
	public void change_color(int index1,int index2,int t)	//t为吃子方代号
	{
		if(t==1)
		{
			num1++;
			player1_txt.setText(num1+"");
			num2--;
			player2_txt.setText(num2+"");
		}
		else
		{
			num2++;
			player2_txt.setText(num2+"");
			num1--;
			player1_txt.setText(num1+"");
		}
		if(index1==0&&index2==0)
		{
			image=new ImageIcon("black_white_pic//left-top"+t+".jpg");
			mark[index1][index2]=t;
			//try{Thread.sleep(1000);}catch(Exception e){}
		}
		else if(index1==0&&index2!=0&&index2!=n-1)
		{
			image=new ImageIcon("black_white_pic//top"+t+".jpg");
			mark[index1][index2]=t;
			//try{Thread.sleep(1000);}catch(Exception e){}
		}
		else if(index1==0&index2==n-1)
		{
			image=new ImageIcon("black_white_pic//right-top"+t+".jpg");
			mark[index1][index2]=t;
			//try{Thread.sleep(1000);}catch(Exception e){}
		}
		else if(index1!=0&&index2==0&&index1!=n-1)
		{
			image=new ImageIcon("black_white_pic//left"+t+".jpg");
			mark[index1][index2]=t;
			//try{Thread.sleep(1000);}catch(Exception e){}
		}
		else if(index1!=0&&index2==n-1&&index1!=n-1)
		{
			image=new ImageIcon("black_white_pic//right"+t+".jpg");
			mark[index1][index2]=t;
			//try{Thread.sleep(1000);}catch(Exception e){}
		}
		else if(index1==n-1&&index2==0)
		{
			image=new ImageIcon("black_white_pic//left-bottom"+t+".jpg");
			mark[index1][index2]=t;
			//try{Thread.sleep(1000);}catch(Exception e){}
		}
		else if(index1==n-1&&index2==n-1)
		{
			image=new ImageIcon("black_white_pic//right-bottom"+t+".jpg");
			mark[index1][index2]=t;
			//try{Thread.sleep(1000);}catch(Exception e){}
		}
		else if(index1==n-1&&index2!=0&&index2!=n-1)
		{
			image=new ImageIcon("black_white_pic//bottom"+t+".jpg");
			mark[index1][index2]=t;
			//try{Thread.sleep(1000);}catch(Exception e){}
		}
		else
		{
			image=new ImageIcon("black_white_pic//center"+t+".jpg");
			mark[index1][index2]=t;
			//try{Thread.sleep(1000);}catch(Exception e){}
		}
		label[index1][index2].setIcon(image);
	}
	public void cvc()
	{
		if(turn==2)
		{
			update_cost();
			get_max_cost();
			player_auto();
			if(complete())
				over();
			else
			{
				change_turn();
			}
		}
	}
	public boolean check(int a,int b)
	{
			boolean temp;
			boolean setted=false;
			int next=0;
			int starti,startj;
			if(turn==1) next=2;
			else next=1;
				//向下吃子

			if(a<n-2)
			{
				temp=false;
				for(i=a+1;i<n;i++)
				{
					if(mark[i][b]==turn)
					{
						if(temp)
						{
							setted=true;
							break;
						}
						else
							break;
					}
					else if(mark[i][b]==0)	break;
					else if(mark[i][b]==next)	temp=true;
				}
			}
				//向上吃子
			if(a>1)
			{
				temp=false;
				for(i=a-1;i>=0;i--)
				{
					if(mark[i][b]==turn)
					{
						if(temp)
						{
							setted=true;
							break;
						}
						else
							break;
					}
					else if(mark[i][b]==0)	break;
					else if(mark[i][b]==next)	temp=true;
				}
			}
				//向右计算权值
			if(b<n-2)
			{
				temp=false;
				for(j=b+1;j<n;j++)
				{
					if(mark[a][j]==turn)
					{
						if(temp)
						{
							setted=true;
							break;
						}
						else
							break;
					}
					else if(mark[a][j]==0)	break;
					else if(mark[a][j]==next)	temp=true;
				}
			}
				//向左计算权值
			if(b>1)
			{
				temp=false;
				for(j=b-1;j>=0;j--)
				{
					if(mark[a][j]==turn)
					{
						if(temp)
						{
							setted=true;
							break;
						}
						else
							break;
					}
					else if(mark[a][j]==0)	break;
					else if(mark[a][j]==next)	temp=true;
				}
			}
//				向左上计算权值
			if(a>1&&b>1)
			{
				temp=false;
				for(j=b-1,i=a-1;j>=0&&i>=0;j--,i--)
				{
					if(mark[i][j]==turn)
					{
						if(temp)
						{
							setted=true;
							break;
						}
						else
							break;
					}
					else if(mark[i][j]==0)	break;
					else if(mark[i][j]==next)	temp=true;
				}
			}
//				向左下计算权值
			if(a<n-2&&b>1)
			{
				temp=false;
				for(j=b-1,i=a+1;j>=0&&i<n;j--,i++)
				{
					if(mark[i][j]==turn)
					{
						if(temp)
						{
							setted=true;
							break;
						}
						else
							break;
					}
					else if(mark[i][j]==0)	break;
					else if(mark[i][j]==next)	temp=true;
				}
			}
//				向右下计算权值
			if(a<n-2&&b<n-2)
			{
				temp=false;
				for(j=b+1,i=a+1;j<n&&i<n;j++,i++)
				{
					if(mark[i][j]==turn)
					{
						if(temp)
						{
							setted=true;
							break;
						}
						else
							break;
					}
					else if(mark[i][j]==0)	break;
					else if(mark[i][j]==next)	temp=true;
				}
			}
//				向右上计算权值
			if(a>1&&b<n-2)
			{
				temp=false;
				for(j=b+1,i=a-1;j<n&&i>=0;j++,i--)
				{
					if(mark[i][j]==turn)
					{
						if(temp)
						{
							setted=true;
							break;
						}
						else
							break;
					}
					else if(mark[i][j]==0)	break;
					else if(mark[i][j]==next)	temp=true;
				}
			}
			return setted;
		}
	public boolean can_be_setted()
	{
		boolean set=false;
		for(int iii=0;iii<n;iii++)
		{
			for(int jjj=0;jjj<n;jjj++)
			{
				if(mark[iii][jjj]==0)
				{
					if(check(iii,jjj))
					{
						set=true;break;
					}
				}
			}
			if(set==true) break;
		}
		return set;
	}
	public void special_position_cost(int a,int b)
	{
		if((a==0&&b==0)||(a==n-1&&b==0)||(a==n-1&&b==n-1)||(a==0&&b==n-1))
		{
			cost2[a][b]+=100;
		}
		else if(a==0||b==0||a==n-1||b==n-1)
		{
			cost2[a][b]+=20;
		}
		else if(a>1&&b>1&&a<n-2&&b<n-2&&(a==2||b==2||a==n-2||b==n-2))
		{
			cost2[a][b]+=10;
		}
	}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void get_max_cost()
	{
		int maxx=0,maxy=0;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				if(turn==1)
				{
					if(cost1[maxx][maxy]<cost1[i][j])
					{
						maxx=i;
						maxy=j;
					}
				}
				else if(turn==2)
				{
					if(cost2[maxx][maxy]<cost2[i][j])
					{
						maxx=i;
						maxy=j;
					}
				}
			}
		}
		x=maxx;
		y=maxy;
	}
	public void print_table(int[][] t)
	{
		for(int a=0;a<10;a++)
		{
			for(int b=0;b<10;b++)
			{
				System.out.print(t[a][b]+"\t");
			}
			System.out.println();
		}
	}
	public static void main(String args[])
	{
		main m=new main();
		m.setVisible(true);
	}
}