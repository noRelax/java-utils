 /*
 *JAVAС��Ϸ������˹����
 *лл֧��
 *2009��8��16�գ�17��
 *jys1109@126.com
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.applet.*;
import java.lang.String.*;
import java.lang.*;
import java.io.*;

public class Block extends JPanel implements ActionListener,KeyListener//Ӧ���Ǽ̳�JPanel
{
	static Button but[] = new Button[6];
	static Button noStop = new Button("ȡ �� �� ͣ");
	static Label scoreLab = new Label("����:");
	static Label infoLab = new Label("��ʾ:");
	static Label speedLab = new Label("����:");
	static Label scoreTex = new Label("0");
	static Label infoTex = new Label(" ");
	static Label speedTex = new Label("1");
	
	static JFrame jf = new JFrame();
	static MyTimer timer; 
	static ImageIcon icon=new ImageIcon("resource/Block.jpg");
	static JMenuBar mb = new JMenuBar();
	static JMenu menu0 = new JMenu("��  Ϸ ");
	static JMenu menu1 = new JMenu("��  �� ");
	static JMenuItem mi0 = new JMenuItem("�� �� Ϸ");
	static JMenuItem mi1 = new JMenuItem("��  ��");
	static JMenuItem mi1_0 = new JMenuItem("��  ��");
    static JDialog dlg_1;
	static JTextArea dlg_1_text = new JTextArea();
	static int startSign = 0;//��Ϸ��ʼ��־ 0 δ��ʼ 1 ��ʼ 2 ��ͣ
	static String butLab[] = {"�� ʼ �� Ϸ","�� �� �� ʼ","�� �� �� ��","�� �� �� ��","�� Ϸ �� ͣ","�� �� �� Ϸ"};
	static int game_body[][] = new int[19][10];
	static int game_sign_x[] = new int[4];//���ڼ�¼4�������ˮƽλ��
	static int game_sign_y[] = new int[4];//���ڼ�¼4������Ĵ�ֱλ��
	static boolean downSign = false;//�Ƿ�����
	static int blockNumber = 1;//ש��ı��
	static int gameScore = 0;//��Ϸ����
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
		jf.setTitle("����˹����");
		jf.setIconImage(icon.getImage());
		jf.setLocation(200,100);
		jf.show();
		timer = new MyTimer(myBlock); //�����߳�
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
    	if(e.getSource() == but[0])//��ʼ��Ϸ
    	{
    		startSign = 1;
    		infoTex.setText("��Ϸ�Ѿ���ʼ!");
    		but[0].setEnabled(false);
    		but[1].setEnabled(true);
		    but[4].setEnabled(true);
		    timer.resume(); 
    	}
    	if(e.getSource() == but[1]||e.getSource() == mi0)//���¿�ʼ��Ϸ
    	{
    		startSign = 0;
    		gameScore = 0;
    		timer.suspend();
    		num_csh_restart();
    		repaint();
    		rand_block();
    		scoreTex.setText("0");
    		infoTex.setText("����Ϸ!");
    		but[0].setEnabled(true);
    		but[1].setEnabled(false);
		    but[4].setEnabled(false);
    	}
    	if(e.getSource() == but[2])//���ͼ���
    	{
    		infoTex.setText("���ͼ���!");
    		speedMark--;
    		if(speedMark <= 1)
    		{
    			speedMark = 1;
    			infoTex.setText("�Ѿ�����ͼ���!");
    		}
    		speedTex.setText(speedMark + "");
    	}
    	if(e.getSource() == but[3])//��߼���
    	{
    		infoTex.setText("��߼���!");
    		speedMark++;
    		if(speedMark >= 9)
    		{
    			speedMark = 9;
    			infoTex.setText("�Ѿ�����߼���!");
    		}
    		speedTex.setText(speedMark + "");
    	}
    	if(e.getSource() == but[4])//��Ϸ��ͣ
    	{
    		this.add(noStop);
    		this.remove(but[4]);
    		infoTex.setText("��Ϸ��ͣ!");
    		timer.suspend();
    	}
    	if(e.getSource() == noStop)//ȡ����ͣ
    	{
    		this.remove(noStop);
    		this.add(but[4]);
    		infoTex.setText("������Ϸ!");
    		timer.resume();
    	}
    	if(e.getSource() == but[5]||e.getSource() == mi1)//�˳���Ϸ
    	{
    		jf.dispose();
    	}
    	if(e.getSource() == mi1_0)//�˳���Ϸ
    	{
    		dlg_1 = new JDialog(jf,"�� ��");
		    try{
		    	FileInputStream io = new FileInputStream("resource/guanyu.txt");//�õ�·��
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
    
    public void rand_block()//�������ש��
    {
    	int num;
		num = (int)(Math.random() * 6) + 1;//����0~6֮��������
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
    
    public void change_body(int blockNumber)//�ı�ש��״̬
    {
    	dingwei();
    	if(blockNumber == 1&&downSign == false)//�任����2�����
    	{
    		if(game_sign_y[0] == game_sign_y[1]&&game_sign_y[3] <= 16)//˵�������Ǻ��ŵ�
    		{
    			if(game_body[game_sign_y[0] - 1][game_sign_x[0] + 1] != 2&&game_body[game_sign_y[3] + 2][game_sign_x[3] - 2] != 2)
    			{
    				num_csh_game();
    			    game_body[game_sign_y[0] - 1][game_sign_x[0] + 1] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1]] = 1;
    			    game_body[game_sign_y[2] + 1][game_sign_x[2] - 1] = 1;
    			    game_body[game_sign_y[3] + 2][game_sign_x[3] - 2] = 1;
    			    infoTex.setText("��Ϸ������!");
    			    repaint();
    			}
    		}
    		if(game_sign_x[0] == game_sign_x[1]&&game_sign_x[0] >= 1&&game_sign_x[3] <= 7)//˵�����������ŵ�
    		{
    			if(game_body[game_sign_y[0] + 1][game_sign_x[0]-1] != 2&&game_body[game_sign_y[3] - 2][game_sign_x[3] + 2] != 2)
    			{
    				num_csh_game();
    			    game_body[game_sign_y[0] + 1][game_sign_x[0] - 1] = 1;
    			    game_body[game_sign_y[1]][game_sign_x[1]]=1;
    			    game_body[game_sign_y[2] - 1][game_sign_x[2] + 1] = 1;
    			    game_body[game_sign_y[3] - 2][game_sign_x[3] + 2] = 1;
    			    infoTex.setText("��Ϸ������!");
    			    repaint();
    			}
    		}
    	}
    	if(blockNumber == 3&&downSign == false)//�任ת��1��4�����
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
    			    infoTex.setText("��Ϸ������!");
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
    			    infoTex.setText("��Ϸ������!");
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
    			    infoTex.setText("��Ϸ������!");
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
    			    infoTex.setText("��Ϸ������!");
    			    repaint();
    			}	
    		}
    	}
    	if(blockNumber == 4&&downSign == false)//�任ת��2��4�����
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
    			    infoTex.setText("��Ϸ������!");
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
    			    infoTex.setText("��Ϸ������!");
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
    			    infoTex.setText("��Ϸ������!");
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
    			    infoTex.setText("��Ϸ������!");
    			    repaint();
    			}	
    		}
    	}
    	if(blockNumber == 5&&downSign == false)//�任ת��3��4�����
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
    			    infoTex.setText("��Ϸ������!");
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
    			    infoTex.setText("��Ϸ������!");
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
    			    infoTex.setText("��Ϸ������!");
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
    			    infoTex.setText("��Ϸ������!");
    			    repaint();
    			}
    		}
    	}
    	if(blockNumber == 6&&downSign == false)//�任����ש��1��2�����
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
    			    infoTex.setText("��Ϸ������!");
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
    			    infoTex.setText("��Ϸ������!");
    			    repaint();
    			}
    		}
    	}
    	if(blockNumber == 7&&downSign == false)//�任����ש��2��2�����
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
    			    infoTex.setText("��Ϸ������!");
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
    			    infoTex.setText("��Ϸ������!");
    			    repaint();
    			}
    		}
    	}
    }
    
    public void num_csh_game()//��������
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
    
    public void num_csh_restart()//���¿�ʼʱ��������
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
    	if(e.getKeyCode() == KeyEvent.VK_DOWN&&startSign == 1)//�����¼�
    	{
    		this.down();
    	}
    	if(e.getKeyCode() == KeyEvent.VK_LEFT&&startSign == 1)//�������
    	{
    		this.left();
    	}
    	if(e.getKeyCode() == KeyEvent.VK_RIGHT&&startSign == 1)//�����Ҽ�
    	{
    		this.right();
    	}
    	if(e.getKeyCode() == KeyEvent.VK_UP&&startSign == 1)//�����ϼ�ת��
    	{
    		this.change_body(blockNumber);
    	}
    	if(startSign == 0)
    	{
    		infoTex.setText("��Ϸδ��ʼ���ѽ���!");
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
	
	public void left()//�����ƶ�
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
		    infoTex.setText("�����ƶ�!");
		    repaint();
		}
	}
	
	public void right()//�����ƶ�
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
		    infoTex.setText("�����ƶ�!");
		    repaint();
		}
	}
	
	public void down()//����
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
		    infoTex.setText("��Ϸ������!");
		    repaint();
		}
	}
	
	public boolean game_over()//�ж���Ϸ�Ƿ����
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
			infoTex.setText("��Ϸ����!");
			changeColor();
			repaint();
			startSign = 0;
			timer.suspend();
			return true;
		}
		else
		return false;
	}
	
	public void getScore()//������������
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
				infoTex.setText("��ϲ�÷�!");
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
		
	public void changeColor()//���Ѿ����µĿ黻ɫ
	{
		downSign = false;
		for(int k = 0;k < 4;k++)
		{
		    game_body[game_sign_y[k]][game_sign_x[k]] = 2;
		}
	}
	
	public void dingwei()//ȷ����λ��
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
	
	public void cancelDW()//����λ�����ʼ��
	{
		for(int k = 0;k < 4;k++)
		{
			game_sign_x[k] = 0;
			game_sign_y[k] = 0;
		}
	}
	
	public void block1()//����
	{
		game_body[0][4] = 1;
		game_body[1][4] = 1;
		game_body[2][4] = 1;
		game_body[3][4] = 1;
	}
	
	public void block2()//������
	{
		game_body[3][4] = 1;
		game_body[2][4] = 1;
		game_body[3][5] = 1;
		game_body[2][5] = 1;
	}
	public void block3()//3��1(��)
	{
		game_body[1][4] = 1;
		game_body[2][4] = 1;
		game_body[3][4] = 1;
		game_body[3][5] = 1;
	}
	public void block4()//3��1(��)
	{
		game_body[1][4] = 1;
		game_body[2][4] = 1;
		game_body[3][4] = 1;
		game_body[2][5] = 1;
	}
	public void block5()//3��1(��)
	{
		game_body[1][4] = 1;
		game_body[2][4] = 1;
	    game_body[3][4] = 1;
		game_body[1][5] = 1;
	}
	public void block6()//ת��1
	{
		game_body[1][5] = 1;
		game_body[2][5] = 1;
		game_body[2][4] = 1;
		game_body[3][4] = 1;
	}
	public void block7()//ת��2
	{
		game_body[1][4] = 1;
		game_body[2][4] = 1;
		game_body[2][5] = 1;
		game_body[3][5] = 1;
	}
}

//��ʱ�߳� 
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

