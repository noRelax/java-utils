 /*
 *JAVA小游戏－俄罗斯方块
 *谢谢支持
 *2009年8月16日－17日
 *jys1109@126.com
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.applet.*;
import java.lang.String.*;
import java.lang.*;
import java.io.*;

public class Block extends JPanel implements ActionListener,KeyListener//应该是继承JPanel
{
	static Button but[] = new Button[6];
	static Button noStop = new Button("取 消 暂 停");
	static Label scoreLab = new Label("分数:");
	static Label infoLab = new Label("提示:");
	static Label speedLab = new Label("级数:");
	static Label scoreTex = new Label("0");
	static Label infoTex = new Label(" ");
	static Label speedTex = new Label("1");
	
	static JFrame jf = new JFrame();
	static MyTimer timer; 
	static ImageIcon icon=new ImageIcon("resource/Block.jpg");
	static JMenuBar mb = new JMenuBar();
	static JMenu menu0 = new JMenu("游  戏 ");
	static JMenu menu1 = new JMenu("帮  助 ");
	static JMenuItem mi0 = new JMenuItem("新 游 戏");
	static JMenuItem mi1 = new JMenuItem("退  出");
	static JMenuItem mi1_0 = new JMenuItem("关  于");
    static JDialog dlg_1;
	static JTextArea dlg_1_text = new JTextArea();
	static int startSign = 0;//游戏开始标志 0 未开始 1 开始 2 暂停
	static String butLab[] = {"开 始 游 戏","重 新 开 始","降 低 级 数","提 高 级 数","游 戏 暂 停","退 出 游 戏"};
	static int game_body[][] = new int[19][10];
	static int game_sign_x[] = new int[4];//用于记录4个方格的水平位置
	static int game_sign_y[] = new int[4];//用于记录4个方格的垂直位置
	static boolean downSign = false;//是否落下
	static int blockNumber = 1;//砖块的编号
	static int gameScore = 0;//游戏分数
	static int speedMark = 1;
	
	public static void main(String args[]) 
	{
		Block myBlock = new Block();
		mb.add(menu0);
		mb.add(menu1);
		menu0.add(mi0);
		menu0.add(mi1);
		menu1.add(mi1_0);
	    jf.setJMenuBar(mb);	
	    
	    myBlock.init();
	    jf.add(myBlock);
	    jf.setSize(565,501);
		jf.setResizable(false);
		jf.setTitle("俄罗斯方块");
		jf.setIconImage(icon.getImage());
		jf.setLocation(200,100);
		jf.show();
		timer = new MyTimer(myBlock); //启动线程
        timer.setDaemon(true); 
        timer.start();
        timer.suspend();
	}
	public void init()
	{
    	setLayout(null);
    	for(int i = 0;i < 6;i++)
    	{
    		but[i] = new Button(butLab[i]);
    		add(but[i]);
    		but[i].addActionListener(this);
    		but[i].addKeyListener(this);
    		but[i].setBounds(360,(240 + 30 * i),160,25);
    	}
        
        add(scoreLab);
        add(scoreTex);
        add(speedLab);
        add(speedTex);
        add(infoLab);
        add(infoTex);
        add(scoreLab);
        scoreLab.setBounds(320,15,30,20);
        scoreTex.setBounds(360,15,160,20);
		scoreTex.setBackground(Color.white);
		speedLab.setBounds(320,45,30,20);
		speedTex.setBounds(360,45,160,20);
		speedTex.setBackground(Color.white);
		
		but[1].setEnabled(false);
		but[4].setEnabled(false);
		
		infoLab.setBounds(320,75,30,20);
		infoTex.setBounds(360,75,160,20);
		infoTex.setBackground(Color.white);
		noStop.setBounds(360,360,160,25);
		noStop.addActionListener(this);
		noStop.addKeyListener(this);
		mi0.addActionListener(this);
		mi1.addActionListener(this);
		mi1_0.addActionListener(this);
		num_csh_game();
		rand_block();
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource() == but[0])//开始游戏
    	{
    		startSign = 1;
    		infoTex.setText("游戏已经开始!");
    		but[0].setEnabled(false);
    		but[1].setEnabled(true);
		    but[4].setEnabled(true);
		    timer.resume(); 
    	}
    	if(e.getSource() == but[1]||e.getSource() == mi0)//重新开始游戏
    	{
    		startSign = 0;
    		gameScore = 0;
    		timer.suspend();
    		num_csh_restart();
    		repaint();
    		rand_block();
    		scoreTex.setText("0");
    		infoTex.setText("新游戏!");
    		but[0].setEnabled(true);
    		but[1].setEnabled(false);
		    but[4].setEnabled(false);
    	}
    	if(e.getSource() == but[2])//降低级数
    	{
    		infoTex.setText("降低级数!");
    		speedMark--;
    		if(speedMark <= 1)
    		{
    			speedMark = 1;
    			infoTex.setText("已经是最低级数!");
    		}
    		speedTex.setText(speedMark + "");
    	}
    	if(e.getSource() == but[3])//提高级数
    	{
    		infoTex.setText("提高级数!");
    		speedMark++;
    		if(speedMark >= 9)
    		{
    			speedMark = 9;
    			infoTex.setText("已经是最高级数!");
    		}
    		speedTex.setText(speedMark + "");
    	}
    	if(e.getSource() == but[4])//游戏暂停
    	{
    		this.add(noStop);
    		this.remove(but[4]);
    		infoTex.setText("游戏暂停!");
    		timer.suspend();
    	}
    	if(e.getSource() == noStop)//取消暂停
    	{
    		this.remove(noStop);
    		this.add(but[4]);
    		infoTex.setText("继续游戏!");
    		timer.resume();
    	}
    	if(e.getSource() == but[5]||e.getSource() == mi1)//退出游戏
    	{
    		jf.dispose();
    	}
    	if(e.getSource() == mi1_0)//退出游戏
    	{
    		dlg_1 = new JDialog(jf,"关 于");
		    try{
		    	FileInputStream io = new FileInputStream("resource/guanyu.txt");//得到路径
		        byte a[] = new byte[io.available()];
		        io.read(a);
		        io.close();
		        String str = new String(a);
		        dlg_1_text.setText(str);
		        }
		        catch(Exception g){}
		        dlg_1_text.setEditable(false);
    		    dlg_1.add(dlg_1_text);
			    dlg_1.pack();
                dlg_1.setResizable(false);
                dlg_1.setSize(200, 120);
                dlg_1.setLocation(400, 240);
                dlg_1.show();
    	}
    }
    
    public void rand_block()//随机产生砖块
    {
    	int num;
		num = (int)(Math.random() * 6) + 1;//产生0~6之间的随机数
		blockNumber = num;
		switch(blockNumber)
		{
			case 1: block1(); blockNumber = 1; break;
			case 2: block2(); blockNumber = 2; break;
			case 3: block3(); blockNumber = 3; break;
			case 4: block4(); blockNumber = 4; break;
			case 5: block5(); blockNumber = 5; break;
			case 6: block6(); blockNumber = 6; break;
			case 7: block7(); blockNumber = 7; break;
		}
    } 
    
    public void change_body(int blockNumber)//改变砖块状态
    {
    	dingwei();
    	if(blockNumber == 1&&downSign == false)//变换长条2种情况
    	{
    		if(game_sign_y[0] == game_sign_y[1]&&game_sign_y[3] <= 16)//说明长条是横着的
    		{
    			if(game_body[game_sign_y[0] - 1][game_sign_x[0] + 1] != 2&&game_body[game_sign_y[3] + 2][game_sign_x[3] - 2] != 2)
    			{
    				num_csh_game();
    			    game_body[game_sign_y[0] - 1][game_sign_x[0] + 1] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1]] = 1;
    			    game_body[game_sign_y[2] + 1][game_sign_x[2] - 1] = 1;
    			    game_body[game_sign_y[3] + 2][game_sign_x[3] - 2] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}
    		}
    		if(game_sign_x[0] == game_sign_x[1]&&game_sign_x[0] >= 1&&game_sign_x[3] <= 7)//说明长条是竖着的
    		{
    			if(game_body[game_sign_y[0] + 1][game_sign_x[0]-1] != 2&&game_body[game_sign_y[3] - 2][game_sign_x[3] + 2] != 2)
    			{
    				num_csh_game();
    			    game_body[game_sign_y[0] + 1][game_sign_x[0] - 1] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1]]=1;
    			    game_body[game_sign_y[2] - 1][game_sign_x[2] + 1] = 1;
    			    game_body[game_sign_y[3] - 2][game_sign_x[3] + 2] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}
    		}
    	}
    	if(blockNumber == 3&&downSign == false)//变换转弯1有4种情况
    	{
    		if(game_sign_x[0] == game_sign_x[1]&&game_sign_x[0] == game_sign_x[2]&&game_sign_y[2] == game_sign_y[3]&&game_sign_x[0] >= 1)
    		{
    			if(game_body[game_sign_y[0] + 1][game_sign_x[0] - 1] != 2&&game_body[game_sign_y[2] - 1][game_sign_x[2] + 1] != 2&&game_body[game_sign_y[3] - 2][game_sign_x[3]] != 2)
    			{
    			    num_csh_game();
    			    game_body[game_sign_y[0] + 1][game_sign_x[0] - 1] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1]] = 1;
    			    game_body[game_sign_y[2] - 1][game_sign_x[2] + 1] = 1;
    			    game_body[game_sign_y[3] - 2][game_sign_x[3]] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}	
    		}
    		if(game_sign_y[1] == game_sign_y[2]&&game_sign_y[2] == game_sign_y[3]&&game_sign_x[0] == game_sign_x[3]&&game_sign_y[1] <= 17)
    		{
    			if(game_body[game_sign_y[0]][game_sign_x[0] - 2] != 2&&game_body[game_sign_y[1] + 1][game_sign_x[1] + 1] != 2&&game_body[game_sign_y[3] - 1][game_sign_x[3] - 1] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0]][game_sign_x[0] - 2] = 1;	
    			    game_body[game_sign_y[1] + 1][game_sign_x[1] + 1] = 1;
    			    game_body[game_sign_y[2]][game_sign_x[2]] = 1;
    			    game_body[game_sign_y[3] - 1][game_sign_x[3] - 1] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}	
    		}
    		if(game_sign_x[1] == game_sign_x[2]&&game_sign_x[1] == game_sign_x[3]&&game_sign_y[0] == game_sign_y[1]&&game_sign_x[3] <= 8)
    		{
    			if(game_body[game_sign_y[0] + 2][game_sign_x[0]] != 2&&game_body[game_sign_y[1] + 1][game_sign_x[1] - 1] != 2&&game_body[game_sign_y[3] - 1][game_sign_x[3] + 1] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0] + 2][game_sign_x[0]] = 1;	
    			    game_body[game_sign_y[1] + 1][game_sign_x[1] - 1] = 1;
    			    game_body[game_sign_y[2]][game_sign_x[2]] = 1;
    			    game_body[game_sign_y[3] - 1][game_sign_x[3] + 1] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}	
    		}
    		if(game_sign_y[0] == game_sign_y[1]&&game_sign_y[1] == game_sign_y[2]&&game_sign_x[0] == game_sign_x[3])
    		{
    			if(game_body[game_sign_y[0] + 1][game_sign_x[0] + 1] != 2&&game_body[game_sign_y[2] - 1][game_sign_x[2] - 1] != 2&&game_body[game_sign_y[3]][game_sign_x[3] + 2] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0] + 1][game_sign_x[0] + 1] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1]] = 1;
    			    game_body[game_sign_y[2] - 1][game_sign_x[2] - 1] = 1;
    			    game_body[game_sign_y[3]][game_sign_x[3] + 2] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}	
    		}
    	}
    	if(blockNumber == 4&&downSign == false)//变换转弯2有4种情况
    	{
    		if(game_sign_x[0] == game_sign_x[1]&&game_sign_x[0] == game_sign_x[3]&&game_sign_y[1] == game_sign_y[2]&&game_sign_x[3] <= 7)
    		{
    			if(game_body[game_sign_y[0] + 2][game_sign_x[0]] != 2&&game_body[game_sign_y[1] + 1][game_sign_x[1] + 1] != 2&&game_body[game_sign_y[3]][game_sign_x[3] + 2] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0] + 2][game_sign_x[0]] = 1;
    			    game_body[game_sign_y[1] + 1][game_sign_x[1] + 1] = 1;
    			    game_body[game_sign_y[2]][game_sign_x[2]] = 1;
    			    game_body[game_sign_y[3]][game_sign_x[3] + 2] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}	
    		}
    		if(game_sign_y[1] == game_sign_y[2]&&game_sign_y[1] == game_sign_y[3]&&game_sign_x[0] == game_sign_x[2])
    		{
    			if(game_body[game_sign_y[1]][game_sign_x[1] + 2] != 2&&game_body[game_sign_y[2] - 1][game_sign_x[2] + 1] != 2&&game_body[game_sign_y[3] - 2][game_sign_x[3]] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0]][game_sign_x[0]] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1] + 2] = 1;
    			    game_body[game_sign_y[2] - 1][game_sign_x[2] + 1] = 1;
    			    game_body[game_sign_y[3] - 2][game_sign_x[3]] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}	
    		}
    		if(game_sign_x[0] == game_sign_x[2]&&game_sign_x[0] == game_sign_x[3]&&game_sign_y[1] == game_sign_y[2]&&game_sign_x[0] >= 2)
    		{
    			if(game_body[game_sign_y[0]][game_sign_x[0] - 2] != 2&&game_body[game_sign_y[2] - 1][game_sign_x[2] - 1] != 2&&game_body[game_sign_y[3] - 2][game_sign_x[3]] != 2)
    			{
        			num_csh_game();
    		    	game_body[game_sign_y[0]][game_sign_x[0] - 2] = 1;
    		    	game_body[game_sign_y[1]][game_sign_x[1]] = 1;
    		    	game_body[game_sign_y[2] - 1][game_sign_x[2] - 1] = 1;
    			    game_body[game_sign_y[3] - 2][game_sign_x[3]] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}	
    		}
    		if(game_sign_y[0] == game_sign_y[1]&&game_sign_y[0] == game_sign_y[2]&&game_sign_x[1] == game_sign_x[3]&&game_sign_y[0] <= 16)
    		{
    			if(game_body[game_sign_y[0] + 2][game_sign_x[0]] != 2&&game_body[game_sign_y[1] + 1][game_sign_x[1] - 1] != 2&&game_body[game_sign_y[2]][game_sign_x[2] - 2] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0] + 2][game_sign_x[0]] = 1;
    			    game_body[game_sign_y[1] + 1][game_sign_x[1] - 1] = 1;
    			    game_body[game_sign_y[2]][game_sign_x[2] - 2] = 1;
    			    game_body[game_sign_y[3]][game_sign_x[3]] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}	
    		}
    	}
    	if(blockNumber == 5&&downSign == false)//变换转弯3有4种情况
    	{
    		if(game_sign_x[0] == game_sign_x[2]&&game_sign_x[2] == game_sign_x[3]&&game_sign_y[0] == game_sign_y[1]&&game_sign_x[1] >= 2)
    		{
    			if(game_body[game_sign_y[0] + 1][game_sign_x[0] - 1] != 2&&game_body[game_sign_y[1]][game_sign_x[1] - 2] != 2&&game_body[game_sign_y[3] - 1][game_sign_x[3] + 1] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0] + 1][game_sign_x[0] - 1] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1] - 2] = 1;
    			    game_body[game_sign_y[2]][game_sign_x[2]] = 1;
    			    game_body[game_sign_y[3] - 1][game_sign_x[3] + 1] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}
    		}
    		if(game_sign_y[1] == game_sign_y[2]&&game_sign_y[2] == game_sign_y[3]&&game_sign_x[0] == game_sign_x[1]&&game_sign_y[0] <= 16)
    		{
    			if(game_body[game_sign_y[0] + 2][game_sign_x[0]] != 2&&game_body[game_sign_y[1] + 1][game_sign_x[1] + 1] != 2&&game_body[game_sign_y[3] - 1][game_sign_x[3] - 1] != 2)
    			{
       			    num_csh_game();
    			    game_body[game_sign_y[0] + 2][game_sign_x[0]] = 1;
    		     	game_body[game_sign_y[1] + 1][game_sign_x[1] + 1] = 1;
    		    	game_body[game_sign_y[2]][game_sign_x[2]] = 1;
    			    game_body[game_sign_y[3] - 1][game_sign_x[3] - 1] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}
    		}
    		if(game_sign_x[0] == game_sign_x[1]&&game_sign_x[1] == game_sign_x[3]&&game_sign_y[2] == game_sign_y[3])
    		{
    			if(game_body[game_sign_y[0] + 1][game_sign_x[0] - 1] != 2&&game_body[game_sign_y[2]][game_sign_x[2] + 2] != 2&&game_body[game_sign_y[3] - 1][game_sign_x[3] + 1] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0] + 1][game_sign_x[0] - 1] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1]] = 1;
    			    game_body[game_sign_y[2]][game_sign_x[2] + 2] = 1;
    			    game_body[game_sign_y[3] - 1][game_sign_x[3] + 1] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}
    		}
    		if(game_sign_y[0] == game_sign_y[1]&&game_sign_y[1] == game_sign_y[2]&&game_sign_x[2] == game_sign_x[3])
    		{
    			if(game_body[game_sign_y[0] + 1][game_sign_x[0] + 1] != 2&&game_body[game_sign_y[2] - 1][game_sign_x[2] - 1] != 2&&game_body[game_sign_y[3] - 2][game_sign_x[3]] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0] + 1][game_sign_x[0] + 1] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1]] = 1;
    			    game_body[game_sign_y[2] - 1][game_sign_x[2] - 1] = 1;
    			    game_body[game_sign_y[3] - 2][game_sign_x[3]] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}
    		}
    	}
    	if(blockNumber == 6&&downSign == false)//变换两层砖块1的2种情况
    	{
    		if(game_sign_x[0] == game_sign_x[2]&&game_sign_x[0] >= 2)
    		{
    			if(game_body[game_sign_y[0]][game_sign_x[0] - 2] != 2&&game_body[game_sign_y[2] - 1][game_sign_x[2] -1 ] != 2&&game_body[game_sign_y[3] - 1][game_sign_x[3] + 1] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0]][game_sign_x[0] - 2] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1]] = 1;
    			    game_body[game_sign_y[2] - 1][game_sign_x[2] - 1] = 1;
    			    game_body[game_sign_y[3] - 1][game_sign_x[3] + 1] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}
    		}
    		if(game_sign_y[0] == game_sign_y[1]&&game_sign_y[3] <= 17)
    		{
    			if(game_body[game_sign_y[0]][game_sign_x[0] + 2] != 2&&game_body[game_sign_y[1] + 1][game_sign_x[1] + 1] != 2&&game_body[game_sign_y[3] + 1][game_sign_x[3] - 1] != 2)
    			{
       			    num_csh_game();
    			    game_body[game_sign_y[0]][game_sign_x[0] + 2] = 1;
    			    game_body[game_sign_y[1] + 1][game_sign_x[1] + 1] = 1;
    			    game_body[game_sign_y[2]][game_sign_x[2]] = 1;
    			    game_body[game_sign_y[3] + 1][game_sign_x[3] - 1] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}
    		}
    	}
    	if(blockNumber == 7&&downSign == false)//变换两层砖块2的2种情况
    	{
    		if(game_sign_x[0] == game_sign_x[1]&&game_sign_x[0] <= 16)
    		{
    			if(game_body[game_sign_y[0]][game_sign_x[0] + 2] != 2&&game_body[game_sign_y[1] - 1][game_sign_x[1] + 1] != 2&&game_body[game_sign_y[3] - 1][game_sign_x[3] - 1] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0]][game_sign_x[0] + 2] = 1;
    			    game_body[game_sign_y[1] - 1][game_sign_x[1] + 1] = 1;
    			    game_body[game_sign_y[2]][game_sign_x[2]] = 1;
    			    game_body[game_sign_y[3] - 1][game_sign_x[3] - 1] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}
    		}
    		if(game_sign_y[0] == game_sign_y[1]&&game_sign_y[2] <= 17)
    		{
    			if(game_body[game_sign_y[0] + 1][game_sign_x[0] - 1] != 2&&game_body[game_sign_y[1]][game_sign_x[1] - 2] != 2&&game_body[game_sign_y[2] + 1][game_sign_x[2] + 1] != 2)
    			{
        			num_csh_game();
    			    game_body[game_sign_y[0] + 1][game_sign_x[0] - 1] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1] - 2] = 1;
    			    game_body[game_sign_y[2] + 1][game_sign_x[2] + 1] = 1;
    			    game_body[game_sign_y[3]][game_sign_x[3]] = 1;
    			    infoTex.setText("游戏进行中!");
    			    repaint();
    			}
    		}
    	}
    }
    
    public void num_csh_game()//数组清零
    {
    	for(int i = 0;i < 19;i++)
    	{
    		for(int j = 0;j < 10;j++)
    		{
    			if(game_body[i][j] == 2)
    			{
    				game_body[i][j] = 2;
    			}
    			else
    			{
    				game_body[i][j] = 0;
    			}
    		}
    	}
    }
    
    public void num_csh_restart()//重新开始时数组清零
    {
    	for(int i = 0;i < 19;i++)
    	{
    		for(int j = 0;j < 10;j++)
    		{
    			game_body[i][j] = 0;
    		}
    	}
    }
    
    public void keyTyped(KeyEvent e){}    
    
    public void keyPressed(KeyEvent e)
    {
    	if(e.getKeyCode() == KeyEvent.VK_DOWN&&startSign == 1)//处理下键
    	{
    		this.down();
    	}
    	if(e.getKeyCode() == KeyEvent.VK_LEFT&&startSign == 1)//处理左键
    	{
    		this.left();
    	}
    	if(e.getKeyCode() == KeyEvent.VK_RIGHT&&startSign == 1)//处理右键
    	{
    		this.right();
    	}
    	if(e.getKeyCode() == KeyEvent.VK_UP&&startSign == 1)//处理上键转换
    	{
    		this.change_body(blockNumber);
    	}
    	if(startSign == 0)
    	{
    		infoTex.setText("游戏未开始或已结束!");
    	}
    }
    
    public void keyReleased(KeyEvent e){}
    
    public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fill3DRect(0,0,300,450,true);
		for(int i = 0;i < 19;i++)
		{
			for(int j = 0;j < 10;j++)
			{
				if(game_body[i][j] == 1)
				{
				    g.setColor(Color.blue);
		            g.fill3DRect(30*j,30*(i-4),30,30,true);
				}
				if(game_body[i][j] == 2)
				{
				    g.setColor(Color.magenta);
		            g.fill3DRect(30*j,30*(i-4),30,30,true);
				}
			}
		}	
	}
	
	public void left()//向左移动
	{
		int sign = 0;
		dingwei();
		for(int k = 0;k < 4;k++)
		{
			if(game_sign_x[k] == 0||game_body[game_sign_y[k]][game_sign_x[k] - 1] == 2)
			{
				sign = 1;
			}
		}
		if(sign == 0&&downSign == false)
		{
			num_csh_game();
			for(int k = 0;k < 4;k++)
		    {
		    	game_body[game_sign_y[k]][game_sign_x[k] - 1] = 1;
		    }
		    infoTex.setText("向左移动!");
		    repaint();
		}
	}
	
	public void right()//向右移动
	{
		int sign = 0;
		dingwei();
		for(int k = 0;k < 4;k++)
		{
			if(game_sign_x[k] == 9||game_body[game_sign_y[k]][game_sign_x[k] + 1] == 2)
			{
				sign = 1;
			}
		}
		if(sign == 0&&downSign == false)
		{
			num_csh_game();
			for(int k = 0;k < 4;k++)
		    {
		    	game_body[game_sign_y[k]][game_sign_x[k] + 1] = 1;
		    }
		    infoTex.setText("向右移动!");
		    repaint();
		}
	}
	
	public void down()//下落
	{
		int sign = 0;
		dingwei();
		for(int k = 0;k < 4;k++)
		{
			if(game_sign_y[k] == 18||game_body[game_sign_y[k] + 1][game_sign_x[k]] == 2)
			{
				sign = 1;
				downSign = true;
				changeColor();
				cancelDW();
				getScore();
				if(game_over() == false)
				{
				    rand_block();
				    repaint();
				}
			}
		}
		if(sign == 0)
		{
			num_csh_game();
		    for(int k = 0;k < 4;k++)
		    {
		        game_body[game_sign_y[k] + 1][game_sign_x[k]] = 1;
		    }
		    infoTex.setText("游戏进行中!");
		    repaint();
		}
	}
	
	public boolean game_over()//判断游戏是否结束
	{
		int sign=0;
		for(int i = 0;i < 10;i++)
		{
			if(game_body[4][i] == 2)
			{
				sign = 1;
			}
		}
		if(sign == 1)
		{
			infoTex.setText("游戏结束!");
			changeColor();
			repaint();
			startSign = 0;
			timer.suspend();
			return true;
		}
		else
		return false;
	}
	
	public void getScore()//满行消除方法
	{
		for(int i = 0;i < 19;i++)
		{
			int sign = 0;
			for(int j = 0;j < 10;j++)
			{
				if(game_body[i][j] == 2)
				{
					sign++;
				}
			}
			if(sign == 10)
			{
				gameScore += 100;
				scoreTex.setText(gameScore+"");
				infoTex.setText("恭喜得分!");
				for(int j = i;j >= 1;j--)
				{
					for(int k = 0;k < 10;k++)
				    {
					    game_body[j][k] = game_body[j - 1][k];
				    }
				}
			}
		}
	}
		
	public void changeColor()//给已经落下的块换色
	{
		downSign = false;
		for(int k = 0;k < 4;k++)
		{
		    game_body[game_sign_y[k]][game_sign_x[k]] = 2;
		}
	}
	
	public void dingwei()//确定其位置
	{
		int k = 0;
		cancelDW();
		for(int i = 0;i < 19;i++)
		{
			for(int j = 0;j < 10;j++)
			{
				if(game_body[i][j] == 1)
				{
					game_sign_x[k] = j;
					game_sign_y[k] = i;
					k++;
				}
			}
		}
	}
	
	public void cancelDW()//将定位数组初始化
	{
		for(int k = 0;k < 4;k++)
		{
			game_sign_x[k] = 0;
			game_sign_y[k] = 0;
		}
	}
	
	public void block1()//长条
	{
		game_body[0][4] = 1;
		game_body[1][4] = 1;
		game_body[2][4] = 1;
		game_body[3][4] = 1;
	}
	
	public void block2()//正方形
	{
		game_body[3][4] = 1;
		game_body[2][4] = 1;
		game_body[3][5] = 1;
		game_body[2][5] = 1;
	}
	public void block3()//3加1(下)
	{
		game_body[1][4] = 1;
		game_body[2][4] = 1;
		game_body[3][4] = 1;
		game_body[3][5] = 1;
	}
	public void block4()//3加1(中)
	{
		game_body[1][4] = 1;
		game_body[2][4] = 1;
		game_body[3][4] = 1;
		game_body[2][5] = 1;
	}
	public void block5()//3加1(上)
	{
		game_body[1][4] = 1;
		game_body[2][4] = 1;
	    game_body[3][4] = 1;
		game_body[1][5] = 1;
	}
	public void block6()//转折1
	{
		game_body[1][5] = 1;
		game_body[2][5] = 1;
		game_body[2][4] = 1;
		game_body[3][4] = 1;
	}
	public void block7()//转折2
	{
		game_body[1][4] = 1;
		game_body[2][4] = 1;
		game_body[2][5] = 1;
		game_body[3][5] = 1;
	}
}

//定时线程 
class MyTimer extends Thread
{
	Block myBlock; 
    public MyTimer(Block myBlock)
    {
    	this.myBlock = myBlock;
    }
    public void run()
    {
        while(myBlock.startSign == 1)
        {
        	try{
        	    sleep((10-myBlock.speedMark + 1)*100); 
        	    myBlock.down();
            }
            catch(InterruptedException e){}
        } 
   }
} 

