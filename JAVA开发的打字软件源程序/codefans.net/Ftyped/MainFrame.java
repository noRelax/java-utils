import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Net.NetTools;

public class MainFrame extends JDesktopPane implements ActionListener{ 
	Dimension screen;
	Toolkit toolkit=Toolkit.getDefaultToolkit();
	private JMenuBar fMenu;
	private JMenu fMenuSys;
	private JMenu fMenuLx;
	private JMenuItem fMenuS;
	private JMenu fMenuChat;
	private JMenuItem fMenuLocal;
	private JMenuItem fMenuLAN;
	private JMenu fMenuHelp;
	private JMenuItem fMenuSoft;
	private JMenuItem fMenuAbout;
	private JMenuItem fMenuShortcutKey;
	private JMenuItem fMenuStudy;
	private JMenuItem fMenuNet;
	private JMenuItem fMenuCollocateLocal;
	private JMenu fMenuCollocate;
	private JMenuItem fMenuGameWord;
	private JMenuItem fMenuGameKey;
	private JMenu fMenuGame;
	private JMenuItem fMenuC;
	private JMenu fMenuTest;
	private JMenuItem fMenuArticle;
	private JMenuItem fMenuCode;
	private JMenuItem fMenuEtymon;
	private JMenuItem fMenuKey;
	private JMenuItem fMenuExit;
	private JMenuItem fMenuShowPort;
	private JMenuItem fMenuBM;
	private Icon ico;
	
	JFrame frame;
	Tool tool;
    public MainFrame(){ 
    	JFrame.setDefaultLookAndFeelDecorated(true);
    	frame = new JFrame(); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        Container container = frame.getContentPane(); 
        container.add(this, BorderLayout.CENTER); 
        this.setPreferredSize(new java.awt.Dimension(800, 550)); 
        frame.setTitle("������ͨv2.1");
        Image ico=Toolkit.getDefaultToolkit().getImage("Image/f.gif");
        frame.setIconImage(ico);
        frame.setResizable(false);
        screen=toolkit.getScreenSize();
        frame.setLocation((screen.width-800)/2,screen.height/2-600/2);//���ڶ�λ
        //��Ӳ˵�
        fMenu = new JMenuBar();
		frame.setJMenuBar(fMenu);
		fMenu.setPreferredSize(new java.awt.Dimension(392, 23));
		{
			fMenuSys = new JMenu();
			fMenu.add(fMenuSys);
			fMenuSys.setText("ϵͳ(S)");
			fMenuSys.setFont(new Font("x",Font.PLAIN,12));
			fMenuSys.setMnemonic(java.awt.event.KeyEvent.VK_S);
			fMenuSys.setRolloverEnabled(true);
			{
				fMenuExit = new JMenuItem();
				fMenuSys.add(fMenuExit);
				fMenuExit.setText("�˳�");
				fMenuExit.addActionListener(this);
				fMenuExit.setFont(new Font("x",Font.PLAIN,12));
				fMenuExit.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/leave.gif")));
			}
		}
		{
			fMenuLx = new JMenu();
			fMenu.add(fMenuLx);
			fMenuLx.setText("��ϰ(E)");
			fMenuLx.setFont(new Font("x",Font.PLAIN,12));
			fMenuLx.setMnemonic(java.awt.event.KeyEvent.VK_E);
			fMenuLx.setRolloverEnabled(true);
			{
				fMenuKey = new JMenuItem();
				fMenuLx.add(fMenuKey);
				fMenuKey.setText("������ϰ");
				fMenuKey.addActionListener(this);
				fMenuKey.setFont(new Font("x",Font.PLAIN,12));
				fMenuKey.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/lx_1.gif")));
			}
			{
				fMenuEtymon = new JMenuItem();
				fMenuLx.add(fMenuEtymon);
				fMenuEtymon.setText("�ָ���ϰ");
				fMenuEtymon.addActionListener(this);
				fMenuEtymon.setFont(new Font("x",Font.PLAIN,12));
				fMenuEtymon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/lx_2.gif")));
			}
			{
				fMenuCode = new JMenuItem();
				fMenuLx.add(fMenuCode);
				fMenuCode.setText("������ϰ");
				fMenuCode.addActionListener(this);
				fMenuCode.setFont(new Font("x",Font.PLAIN,12));
				fMenuCode.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/lx_3.gif")));
			}
			{
				fMenuArticle = new JMenuItem();
				fMenuLx.add(fMenuArticle);
				fMenuArticle.setText("������ϰ");
				fMenuArticle.addActionListener(this);
				fMenuArticle.setFont(new Font("x",Font.PLAIN,12));
				fMenuArticle.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/lx_4.gif")));
			}
		}
		{
			fMenuTest = new JMenu();
			fMenu.add(fMenuTest);
			fMenuTest.setText("����(T)");
			fMenuTest.setFont(new Font("x",Font.PLAIN,12));
			fMenuTest.setMnemonic(java.awt.event.KeyEvent.VK_T);
			fMenuTest.setRolloverEnabled(true);
			{
				fMenuLAN = new JMenuItem();
				fMenuTest.add(fMenuLAN);
				fMenuLAN.setText("LAN����");
				fMenuLAN.addActionListener(this);
				fMenuLAN.setFont(new Font("x",Font.PLAIN,12));
				fMenuLAN.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/LAN.gif")));
			}
			{
				fMenuLocal = new JMenuItem();
				fMenuTest.add(fMenuLocal);
				fMenuLocal.setText("���ز���");
				fMenuLocal.addActionListener(this);
				fMenuLocal.setFont(new Font("x",Font.PLAIN,12));
				fMenuLocal.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/Local.gif")));
			}
		}
		{
			fMenuChat = new JMenu();
			fMenu.add(fMenuChat);
			fMenuChat.setText("����(L)");
			fMenuChat.setFont(new Font("x",Font.PLAIN,12));
			fMenuChat.setMnemonic(java.awt.event.KeyEvent.VK_L);
			fMenuChat.setRolloverEnabled(true);
			{
				fMenuS = new JMenuItem();
				fMenuChat.add(fMenuS);
				fMenuS.setText("��������ʽ");
				fMenuS.addActionListener(this);
				fMenuS.setFont(new Font("x",Font.PLAIN,12));
				fMenuS.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/pin_1.gif")));
			}
			{
				fMenuC = new JMenuItem();
				fMenuChat.add(fMenuC);
				fMenuC.setText("�ͻ��˷�ʽ");
				fMenuC.addActionListener(this);
				fMenuC.setFont(new Font("x",Font.PLAIN,12));
				fMenuC.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/pin_2.gif")));
			}
			{
				fMenuShowPort = new JMenuItem();
				fMenuChat.add(fMenuShowPort);
				fMenuShowPort.setText("ʾ����������");
				fMenuShowPort.addActionListener(this);
				fMenuShowPort.setFont(new Font("x",Font.PLAIN,12));
				fMenuShowPort.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/pin_3.gif")));
			}
		}
		{
			fMenuGame = new JMenu();
			fMenu.add(fMenuGame);
			fMenuGame.setText("��Ϸ(G)");
			fMenuGame.setFont(new Font("x",Font.PLAIN,12));
			fMenuGame.setMnemonic(java.awt.event.KeyEvent.VK_G);
			fMenuGame.setRolloverEnabled(true);
			{
				fMenuGameKey = new JMenuItem();
				fMenuGame.add(fMenuGameKey);
				fMenuGameKey.setText("�ַ��ս���");
				fMenuGameKey.addActionListener(this);
				fMenuGameKey.setFont(new Font("x",Font.PLAIN,12));
				fMenuGameKey.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/game.gif")));
			}
			{
				fMenuGameWord = new JMenuItem();
				fMenuGame.add(fMenuGameWord);
				fMenuGameWord.setText("���ֻ���ͧ");
				fMenuGameWord.addActionListener(this);
				fMenuGameWord.setFont(new Font("x",Font.PLAIN,12));
				fMenuGameWord.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/game.gif")));
			}
		}
		{
			fMenuCollocate = new JMenu();
			fMenu.add(fMenuCollocate);
			fMenuCollocate.setText("����(C)");
			fMenuCollocate.setFont(new Font("x",Font.PLAIN,12));
			fMenuCollocate.setMnemonic(java.awt.event.KeyEvent.VK_C);
			fMenuCollocate.setRolloverEnabled(true);
			{
				fMenuCollocateLocal = new JMenuItem();
				fMenuCollocate.add(fMenuCollocateLocal);
				fMenuCollocateLocal.setText("����");
				fMenuCollocateLocal.addActionListener(this);
				fMenuCollocateLocal.setFont(new Font("x",Font.PLAIN,12));
				fMenuCollocateLocal.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/s_1.gif")));
			}
			{
				fMenuNet = new JMenuItem();
				fMenuCollocate.add(fMenuNet);
				fMenuNet.setText("����");
				fMenuNet.addActionListener(this);
				fMenuNet.setFont(new Font("x",Font.PLAIN,12));
				fMenuNet.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/s_2.gif")));
			}
		}
		{
			fMenuHelp = new JMenu();
			fMenu.add(fMenuHelp);
			fMenuHelp.setText("����(H)");
			fMenuHelp.setFont(new Font("x",Font.PLAIN,12));
			fMenuHelp.setMnemonic(java.awt.event.KeyEvent.VK_H);
			fMenuHelp.setRolloverEnabled(true);
			{
				fMenuBM = new JMenuItem();
				fMenuHelp.add(fMenuBM);
				fMenuBM.setText("�����ѯ");
				fMenuBM.addActionListener(this);
				fMenuBM.setFont(new Font("x",Font.PLAIN,12));
				fMenuBM.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/q.gif")));
			}
			{
				fMenuStudy = new JMenuItem();
				fMenuHelp.add(fMenuStudy);
				fMenuStudy.setText("ѧϰ");
				fMenuStudy.addActionListener(this);
				fMenuStudy.setFont(new Font("x",Font.PLAIN,12));
				fMenuStudy.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/xx.gif")));
			}
			{
				fMenuSoft = new JMenuItem();
				fMenuHelp.add(fMenuSoft);
				fMenuSoft.setText("����");
				fMenuSoft.addActionListener(this);
				fMenuSoft.setFont(new Font("x",Font.PLAIN,12));
				fMenuSoft.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/h.gif")));
			}
			{
				fMenuShortcutKey = new JMenuItem();
				fMenuHelp.add(fMenuShortcutKey);
				fMenuShortcutKey.setText("��ݼ�");
				fMenuShortcutKey.addActionListener(this);
				fMenuShortcutKey.setFont(new Font("x",Font.PLAIN,12));
				fMenuShortcutKey.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/key.gif")));
			}
			{
				fMenuAbout = new JMenuItem();
				fMenuHelp.add(fMenuAbout);
				fMenuAbout.setText("��������");
				fMenuAbout.addActionListener(this);
				fMenuAbout.setFont(new Font("x",Font.PLAIN,12));
				fMenuAbout.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/about.gif")));
			}
		}
		
		//��ӹ�����.
		tool=new Tool(this);
		this.add(tool,1);
		//������칤����
		Chat chat=new Chat(this);
		this.add(chat,2);
		
        frame.pack(); 
        frame.setVisible(true);
    } 
	//  ʵ��ActionListener�ӿڵķ���
	public void actionPerformed(ActionEvent e){
		if(PublicData.onChoice==true){
			if(e.getSource()==fMenuExit){
				int i=JOptionPane.showConfirmDialog(this,"�����Ҫ�˳���?","������",
						JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(i==0){
					System.exit(0);
				}
			}
			if(e.getSource()==fMenuKey){
				PublicData.onChoice=false;
				PublicData.Lang="English";
				PublicData.Text="Text/English_"+(int)(Math.random()*5+1)+".txt";
				PublicData.choice=1;
				showFrame();
			}
			if(e.getSource()==fMenuEtymon){
				PublicData.onChoice=false;
				PublicData.Lang="Chinese";
				PublicData.choice=2;
				showFrame();
			}
			if(e.getSource()==fMenuCode){
				PublicData.onChoice=false;
				PublicData.Lang="Chinese";
				PublicData.Text="Text/TwoCode.txt";
				PublicData.choice=3;
				showFrame();
			}
			if(e.getSource()==fMenuArticle){
				PublicData.onChoice=false;
				PublicData.Lang="Chinese";
				PublicData.Text="Text/Chinese_"+(int)(Math.random()*5+1)+".txt";
				PublicData.choice=3;
				showFrame();
			}
			if(e.getSource()==fMenuLAN){
				
			}
			if(e.getSource()==fMenuLocal){
				PublicData.onChoice=false;
				PublicData.Lang="Chinese";
				PublicData.Text="Text/Chinese_"+(int)(Math.random()*5+1)+".txt";
				PublicData.choice=3;
				showFrame();
			}
			if(e.getSource()==fMenuS){
				
			}
			if(e.getSource()==fMenuC){
				
			}
			if(e.getSource()==fMenuShowPort){
				JOptionPane.showConfirmDialog(this,"����IP:"+NetTools.getIP()+"\n������:"+
						NetTools.getName()+"\n����˿ں�:"+(NetTools.getIpSect()+55555),"������",
						JOptionPane.YES_OPTION,JOptionPane.INFORMATION_MESSAGE);
			}
			if(e.getSource()==fMenuGameKey){
				
			}
			if(e.getSource()==fMenuGameWord){
				
			}
			if(e.getSource()==fMenuCollocateLocal){
				
			}
			if(e.getSource()==fMenuNet){
				new LanConfig();
			}
			if(e.getSource()==fMenuBM){
				PublicData.onChoice=false;
				QueryCoding coding=new QueryCoding();
				this.add(coding,6);
			}
			if(e.getSource()==fMenuStudy){
				
			}
			if(e.getSource()==fMenuSoft){
				
			}
			if(e.getSource()==fMenuShortcutKey){
				
			}
			if(e.getSource()==fMenuAbout){
				
			}
		}
	}
	private void showFrame(){
		Hint hint =new Hint(tool);
		this.add(hint,2);
	}
}