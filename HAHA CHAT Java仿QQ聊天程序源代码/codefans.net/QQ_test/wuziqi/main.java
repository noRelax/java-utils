package wuziqi;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import haha_game.RecButton;
public class main extends JFrame implements ActionListener,MouseListener{
	private static int n=22,over_style=0;
	private int x=0,y=0,num1=2,num2=2,count=0;					//记录雷的总数目
	private JPanel main_panel=new JPanel();	
	private JLabel[][] label;
	private int[][] mark;
	private int[][] cost1;
	private int[][] cost2;
	private int[][] cost3;		//计算cost1和cost2的总和
	private int[][][] player1;
	private int[][][] player2;
	private RecButton reset_button;
	private int i,j,wide=0,height=0,turn=1,w,h;
	private boolean complete=false,failed=false;
	String path="wuziqi_pic2//";
	Icon image;
	public main()
	{
		label=new JLabel[n][n];
		cost1=new int[n][n];
		cost2=new int[n][n];
		cost3=new int[n][n];
		mark=new int[n][n];
		player1=new int[n][n][4];
		player2=new int[n][n][4];		
	setTitle("HAHA极品五子棋");
	image=new ImageIcon(path+"left-top.jpg");
	h=image.getIconHeight();
	w=image.getIconWidth();
	wide=n*w+35;
	height=n*h+115;
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
			if(i>0&&j>0&&i<n-1&&j<n-1)
				label[i][j].addMouseListener(this);
			label[i][j].setBounds(new Rectangle(1+w*j, 1+h*(i),w,h));
			main_panel.add(label[i][j]);
		}
	}
	map_ini();
	
	reset_button=new RecButton("RESET");
	reset_button.setForeground(Color.green);
	reset_button.addActionListener(this);
	
	main_panel.setBounds(new Rectangle(10,60,w*n,h*n));
	reset_button.setBounds(new Rectangle(wide/2-50,10,80,30));
	
	c.add(main_panel);
	c.add(reset_button);
}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("RESET"))
		{
			map_ini();
			complete=false;
			failed=false;
		}
	}
	private boolean complete()
	{
		boolean over=false;
		int num=1;
		if(count>300)
		{
			over=true;
		}
		for(int aa=1;aa<n-1;aa++)
		{
			for(int bb=1;bb<n-1;bb++)
			{
				if(mark[aa][bb]==1)//只需要对每一个点向下，左下，右下，右判断即可
				{		
					//向下搜索
					if(aa<n-6)
					{
						for(int cc=aa+1;cc<n-1&&cc<aa+5;cc++)
						{
							if(mark[cc][bb]==0||mark[cc][bb]==2)
							{
								break;
							}
							else
							{
								num++;
							}
						}
						if(num==5)
						{
							over=true;
							failed=false;
							System.out.println(aa+"    "+bb);
							for(int temp=0;temp<5;temp++)
							{
								image=new ImageIcon(path+"center-win1.jpg");
								label[aa+temp][bb].setIcon(image);
							}
							return true;
						}
						else num=1;
					}
					
//					向左下搜索
					if(aa<n-6&&bb>5)
					{
						for(int cc=aa+1,dd=bb-1;cc<n-1&&cc<aa+5&&dd>1&&dd>bb-5;cc++,dd--)
						{
							if(mark[cc][dd]==0||mark[cc][dd]==2)
							{
								break;
							}
							else
							{
								num++;
							}
						}
						if(num==5)
						{
							over=true;
							failed=false;
							System.out.println(aa+"    "+bb);
							for(int temp=0;temp<5;temp++)
							{
								image=new ImageIcon(path+"center-win1.jpg");
								label[aa+temp][bb-temp].setIcon(image);
							}
							return true;
						}
						else num=1;
					}
					
					//向右下搜索
					if(aa<n-6&&bb<n-6)
					{
						for(int cc=aa+1,dd=bb+1;cc<n-1&&cc<aa+5&&dd<n-1&&dd<bb+5;cc++,dd++)
						{
							if(mark[cc][dd]==0||mark[cc][dd]==2)
							{
								break;
							}
							else
							{
								num++;
							}
						}
						if(num==5)
						{
							over=true;
							failed=false;
							System.out.println(aa+"    "+bb);
							for(int temp=0;temp<5;temp++)
							{
								image=new ImageIcon(path+"center-win1.jpg");
								label[aa+temp][bb+temp].setIcon(image);
							}
							return true;
						}
						else num=1;
					}
					
					//向右搜索
					if(bb<n-6)
					{
						for(int dd=bb+1;dd<n-1&&dd<bb+5;dd++)
						{
							if(mark[aa][dd]==0||mark[aa][dd]==2)
							{
								break;
							}
							else
							{
								num++;
							}
						}
						if(num==5)
						{
							over=true;
							failed=false;
							System.out.println(aa+"    "+bb);
							for(int temp=0;temp<5;temp++)
							{
								image=new ImageIcon(path+"center-win1.jpg");
								label[aa][bb+temp].setIcon(image);
							}
							return true;
						}
						else num=1;
					}
					
				}
				else if(mark[aa][bb]==2)//只需要对每一个点向下，左下，右下，右判断即可
				{
					
					//向下搜索
					if(aa<n-6)
					{
						for(int cc=aa+1;cc<n-1&&cc<aa+5;cc++)
						{
							if(mark[cc][bb]==0||mark[cc][bb]==1)
							{
								break;
							}
							else
							{
								num++;
							}
						}
						if(num==5)
						{
							over=true;
							failed=true;
							System.out.println(aa+"    "+bb);
							for(int temp=0;temp<5;temp++)
							{
								image=new ImageIcon(path+"center-win2.jpg");
								label[aa+temp][bb].setIcon(image);
							}
							return true;
						}
						else num=1;
					}
					
//					向左下搜索
					if(aa<n-6&&bb>5)
					{
						for(int cc=aa+1,dd=bb-1;cc<n-1&&cc<aa+5&&dd>1&&dd>bb-5;cc++,dd--)
						{
							if(mark[cc][dd]==0||mark[cc][dd]==1)
							{
								break;
							}
							else
							{
								num++;
							}
						}
						if(num==5)
						{
							over=true;
							failed=true;
							System.out.println(aa+"    "+bb);
							for(int temp=0;temp<5;temp++)
							{
								image=new ImageIcon(path+"center-win2.jpg");
								label[aa+temp][bb-temp].setIcon(image);
							}
							return true;
						}
						else num=1;
					}
					
					//向右下搜索
					if(aa<n-6&&bb<n-6)
					{
						for(int cc=aa+1,dd=bb+1;cc<n-1&&cc<aa+5&&dd<n-1&&dd<bb+5;cc++,dd++)
						{
							if(mark[cc][dd]==0||mark[cc][dd]==1)
							{
								break;
							}
							else
							{
								num++;
							}
						}
						if(num==5)
						{
							over=true;
							failed=true;
							System.out.println(aa+"    "+bb);
							for(int temp=0;temp<5;temp++)
							{
								image=new ImageIcon(path+"center-win2.jpg");
								label[aa+temp][bb+temp].setIcon(image);
							}
							return true;
						}
						else num=1;
					}
					
					//向右搜索
					if(bb<n-6)
					{
						for(int dd=bb+1;dd<n-1&&dd<bb+5;dd++)
						{
							if(mark[aa][dd]==0||mark[aa][dd]==1)
							{
								break;
							}
							else
							{
								num++;
							}
						}
						if(num==5)
						{
							over=true;
							failed=true;
							System.out.println(aa+"    "+bb);
							for(int temp=0;temp<5;temp++)
							{
								image=new ImageIcon(path+"center-win2.jpg");
								label[aa][bb+temp].setIcon(image);
							}
							return true;
						}
						else num=1;
					}
					
				}
			}
		}
		return over;
	}
	private void over()
	{
		complete=true;
		if(failed)
			JOptionPane.showMessageDialog(null,"游戏结束,电脑赢了");
		else
			JOptionPane.showMessageDialog(null,"游戏结束,你赢了");
	}
	public void mouseClicked(MouseEvent e)
	{ 
		JLabel l=(JLabel)e.getSource();
		for(i=1;i<n-1;i++)
		{
			for(j=1;j<n-1;j++)
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
			if(turn==1&&!complete)
			{

				image=new ImageIcon(path+"center"+turn+".jpg");
				label[x][y].setIcon(image);
				mark[x][y]=turn;
				if(complete())
					over();			
				change_turn();
			}
			if(turn==2&&!complete)
			{
				update_cost();
				get_max_cost();
				player_auto();
				if(complete())
					over();
				change_turn();
			}
		}
	}
	private void player_auto()
	{
		image=new ImageIcon(path+"center"+turn+".jpg");
		mark[x][y]=turn;
		label[x][y].setIcon(image);
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
					image=new ImageIcon(path+"left-top.jpg");
					mark[i][j]=-1;
				}
				else if(i==0&&j!=0&&j!=n-1)
				{
					image=new ImageIcon(path+"top.jpg");
					mark[i][j]=-1;
				}
				else if(i==0&j==n-1)
				{
					image=new ImageIcon(path+"right-top.jpg");
					mark[i][j]=-1;
				}
				else if(i!=0&&j==0&&i!=n-1)
				{
					image=new ImageIcon(path+"left.jpg");
					mark[i][j]=-1;
				}
				else if(i!=0&&j==n-1&&i!=n-1)
				{
					image=new ImageIcon(path+"right.jpg");
					mark[i][j]=-1;
				}
				else if(i==n-1&&j==0)
				{
					image=new ImageIcon(path+"left-bottom.jpg");
					mark[i][j]=-1;
				}
				else if(i==n-1&&j==n-1)
				{
					image=new ImageIcon(path+"right-bottom.jpg");
					mark[i][j]=-1;
				}
				else if(i==n-1&&j!=0&&j!=n-1)
				{
					image=new ImageIcon(path+"bottom.jpg");
					mark[i][j]=-1;
				}
				else
				{
					image=new ImageIcon(path+"center0.jpg");
					mark[i][j]=0;
				}
				label[i][j].setIcon(image);
				cost1[i][j]=0;
				cost2[i][j]=0;
				cost3[i][j]=0;
				for(int k=0;k<4;k++)
				{
					player1[i][j][k]=0;
					player2[i][j][k]=0;
				}
			}
		}
		turn=1;
	}
	public void update_cost()
	{
		for(i=1;i<n-1;i++)
		{
			for(j=1;j<n-1;j++)
			{
				cost1[i][j]=0;
				cost2[i][j]=0;
				cost3[i][j]=0;
				player1[i][j][0]=0;player1[i][j][1]=0;player1[i][j][2]=0;player1[i][j][3]=0;
				player2[i][j][0]=0;player2[i][j][1]=0;player2[i][j][2]=0;player2[i][j][3]=0;
				
			}
		}
		for(int p=0;p<n;p++)
		{
			for(int q=0;q<n;q++)
			{
				if(mark[p][q]==0)
				{
					cost(p,q,1);
					cost1[p][q]=player1[p][q][0]+player1[p][q][1]+player1[p][q][2]+player1[p][q][3];
					cost(p,q,2);
					cost2[p][q]=player2[p][q][0]+player2[p][q][1]+player2[p][q][2]+player2[p][q][3];
					cost3[p][q]=cost1[p][q]+cost2[p][q];
				}
			}
		}
	}
	public void cost(int a,int b,int side)
	{
		int index;
		int temp1=0;int temp2=0;
		boolean side1=false,side2=false;
		int t=0;
		if(side==1) t=2;
		else t=1;
		
		/*当碰到一个空白点时，以它为中心向左挨个查找，假如碰到己方的子则记录然后继续
		 * ，假如碰到对方的子、空白点或边界就停止查找。左边完成后再向右进 
		行同样的操作；最后把左右两边的记录合并起来，得到的数据就是该点横向上的棋型*/

		//纵向计算权值
		i=a-1;j=b;
		side1=false;side2=false;
		for(index=0;index<5;index++)
		{
			if(i-index>0)
			{
				if(mark[i-index][j]==0)
				{
					side1=true;
					break;
				}
				else if(mark[i-index][j]==t)
				{
					side1=false;
					break;
				}
				else
				{
					temp1++;
				}
			}
			else
			{
				side1=false;
				break;
			}
		}
		
		i=a+1;j=b;
		for(index=0;index<5;index++)
		{
			if(i+index<n-1)
			{
				if(mark[i+index][j]==0)
				{
					side2=true;
					break;
				}
				else if(mark[i+index][j]==t)
				{
					side2=false;
					break;
				}
				else
				{
					temp2++;
				}
			}
			else
			{
				side2=false;
				break;
			}
		}
		if(side==1) player1[a][b][0]=type_cost2(temp1+temp2+1,side1,side2);
		if(side==2) player2[a][b][0]=type_cost1(temp1+temp2+1,side1,side2);

		
		//横向计算权值
		i=a;j=b-1;
		side1=false;side2=false;
		temp1=0;temp2=0;
		for(index=0;index<5;index++)
		{
			if(j-index>0)
			{
				if(mark[i][j-index]==0)
				{
					side1=true;
					break;
				}
				else if(mark[i][j-index]==t)
				{
					side1=false;
					break;
				}
				else
				{
					temp1++;
				}
			}
			else
			{
				side1=false;
				break;
			}
		}
		
		i=a;j=b+1;
		for(index=0;index<5;index++)
		{
			if(j+index<n-1)
			{
				if(mark[i][j+index]==0)
				{
					side2=true;
					break;
				}
				else if(mark[i][j+index]==t)
				{
					side2=false;
					break;
				}
				else
				{
					temp2++;
				}
			}
			else
			{
				side2=false;
				break;
			}
		}
		if(side==1) player1[a][b][1]=type_cost2(temp1+temp2+1,side1,side2);
		if(side==2) player2[a][b][1]=type_cost1(temp1+temp2+1,side1,side2);
		
		//主对角线计算权值
		i=a-1;j=b-1;
		temp1=0;temp2=0;
		side1=false;side2=false;
		for(index=0;index<5;index++)
		{
			if(i-index>0&&j-index>0)
			{
				if(mark[i-index][j-index]==0)
				{
					side1=true;
					break;
				}
				else if(mark[i-index][j-index]==t)
				{
					side1=false;
					break;
				}
				else
				{
					temp1++;
				}
			}
			else
			{
				side1=false;
				break;
			}
		}
		
		i=a+1;j=b+1;
		for(index=0;index<5;index++)
		{
			if(i+index<n-1&&j+index<n-1)
			{
				if(mark[i+index][j+index]==0)
				{
					side2=true;
					break;
				}
				else if(mark[i+index][j+index]==t)
				{
					side2=false;
					break;
				}
				else
				{
					temp2++;
				}
			}
			else
			{
				side2=false;
				break;
			}
		}
		if(side==1) player1[a][b][2]=type_cost2(temp1+temp2+1,side1,side2);
		if(side==2) player2[a][b][2]=type_cost1(temp1+temp2+1,side1,side2);
		
		//副对角线计算权值
		i=a+1;j=b-1;
		temp1=0;temp2=0;
		side1=false;side2=false;
		for(index=0;index<5;index++)
		{
			if(i+index<n-1&&j-index>0)
			{
				if(mark[i+index][j-index]==0)
				{
					side1=true;
					break;
				}
				else if(mark[i+index][j-index]==t)
				{
					side1=false;
					break;
				}
				else
				{
					temp1++;
				}
			}
			else
			{
				side1=false;
				break;
			}
		}
		
		i=a-1;j=b+1;
		for(index=0;index<5;index++)
		{
			if(i-index>0&&j+index<n-1)
			{
				if(mark[i-index][j+index]==0)
				{
					side2=true;
					break;
				}
				else if(mark[i-index][j+index]==t)
				{
					side2=false;
					break;
				}
				else
				{
					temp2++;
				}
			}
			else
			{
				side2=false;
				break;
			}
		}
		if(side==1) player1[a][b][3]=type_cost2(temp1+temp2+1,side1,side2);
		if(side==2) player2[a][b][3]=type_cost1(temp1+temp2+1,side1,side2);
	}
	public int type_cost1(int num,boolean side1,boolean side2)		//电脑权值设置
	{//num表示添加上中间点以后子的数量，side1表示前部是不是空格，side2表示后部分是不是空格
		switch(num)
		{
			case 1:
				//System.out.println("1");
				if(side1&&side2)			//活1,权值为5
					return 5;
				else if((side1&&!side2)||(!side1&&side2))		//冲1，权值为1
					return 1;
				else if(!side1&&!side2)							//死1，权值为0
					return 0;
			case 2:
				//System.out.println("2");
				if(side1&&side2)								//活2,权值为30
					return 30;
				else if((side1&&!side2)||(!side1&&side2))		//冲2，权值为3
					return 3;
				else if(!side1&&!side2)							//死2，权值为0
					return 0;
			case 3:
				//System.out.println("3");
				if(side1&&side2)								//活3,权值为300
					return 3000;
				else if((side1&&!side2)||(!side1&&side2))		//冲3，权值为11
					return 11;
				else if(!side1&&!side2)							//死3，权值为0
					return 0;
			case 4:
				//System.out.println("4");
				if(side1&&side2)								//活4,权值为900
					return 60000;
				else if((side1&&!side2)||(!side1&&side2))		//冲4，权值为400
					return 5000;
				else if(!side1&&!side2)							//死4，权值为0
					return 0;
			case 5:
			default:
				return 100000;
		}
	}
	public int type_cost2(int num,boolean side1,boolean side2)
	{//num表示添加上中间点以后子的数量，side1表示前部是不是空格，side2表示后部分是不是空格
		switch(num)
		{
			case 1:
				//System.out.println("1");
				if(side1&&side2)			//活1,权值为5
					return 5;
				else if((side1&&!side2)||(!side1&&side2))		//冲1，权值为1
					return 1;
				else if(!side1&&!side2)							//死1，权值为0
					return 0;
			case 2:
				//System.out.println("2");
				if(side1&&side2)								//活2,权值为30
					return 40;
				else if((side1&&!side2)||(!side1&&side2))		//冲2，权值为3
					return 3;
				else if(!side1&&!side2)							//死2，权值为0
					return 0;
			case 3:
				//System.out.println("3");
				if(side1&&side2)								//活3,权值为300
					return 4000;
				else if((side1&&!side2)||(!side1&&side2))		//冲3，权值为11
					return 900;
				else if(!side1&&!side2)							//死3，权值为0
					return 0;
			case 4:
				//System.out.println("4");
				if(side1&&side2)								//活4,权值为900
					return 50000;
				else if((side1&&!side2)||(!side1&&side2))		//冲4，权值为400
					return 9000;
				else if(!side1&&!side2)							//死4，权值为0
					return 0;
			case 5:
			default:
				return 100000;
		}
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
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void get_max_cost()
	{
		max_point[] max=new max_point[n];
		int max_index=0;
		int maxx=1,maxy=1;
		for(i=0;i<n;i++)
		{
			max[i]=new max_point();
		}
		for(i=1;i<n-1;i++)
		{
			for(j=1;j<n-1;j++)
			{
				if(cost3[maxx][maxy]<cost3[i][j])
				{
					maxx=i;
					maxy=j;
				}
			}
		}
		max[0].x=maxx;
		max[0].y=maxy;
		for(i=1;i<n-1;i++)
		{
			for(j=1;j<n-1;j++)
			{
				if(cost3[i][j]==cost3[maxx][maxy]&&max_index<n)
				{
					max_index++;
					max[max_index].x=i;
					max[max_index].y=j;
				}
			}
		}
		int rand=(int)(Math.random()*(max_index+1));
		//print_table(cost2);
		//System.out.println("最佳位置：x="+maxx+"   y="+maxy);
		x=max[rand].x;
		y=max[rand].y;
	}
	public void print_table(int[][] t)
	{
		for(int a=0;a<22;a++)
		{
			for(int b=0;b<22;b++)
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