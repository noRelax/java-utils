import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import LanClient.*;


public class Tool extends JInternalFrame implements ActionListener{
	
	MainFrame parent;
	
	private JButton btnJP;
	private JButton btnZG;
	private JButton btnJM;
	private JButton btnWZ;
	private JButton btnLocalTest;
	private JButton btnLANTest;
	private JButton btnBMCX;
	private JButton btnExit;
	private JButton btnHelp;
	private JPanel p;
	private ImageIcon image;
	
	public Tool(MainFrame parent){
		this.parent=parent;
		
		//添加按钮
		p=new JPanel();
		image=new ImageIcon("Image/JP.gif");
		btnJP=new JButton("键盘练习(1)",image);
		btnJP.setFont(new Font("x",Font.PLAIN,12));
		btnJP.setMnemonic('1');
		btnJP.addActionListener(this);
		
		image=new ImageIcon("Image/ZG.gif");
		btnZG=new JButton("字根练习(2)",image);
		btnZG.setFont(new Font("x",Font.PLAIN,12));
		btnZG.setMnemonic('2');
		btnZG.addActionListener(this);
		
		image=new ImageIcon("Image/JM.gif");
		btnJM=new JButton("简码练习(3)",image);
		btnJM.setFont(new Font("x",Font.PLAIN,12));
		btnJM.setMnemonic('3');
		btnJM.addActionListener(this);
		
		image=new ImageIcon("Image/WZ.gif");
		btnWZ=new JButton("文章练习(4)",image);
		btnWZ.setFont(new Font("x",Font.PLAIN,12));
		btnWZ.setMnemonic('4');
		btnWZ.addActionListener(this);
		
		image=new ImageIcon("Image/LocalTest.gif");
		btnLocalTest=new JButton("本地测试(5)",image);
		btnLocalTest.setFont(new Font("x",Font.PLAIN,12));
		btnLocalTest.setMnemonic('5');
		btnLocalTest.addActionListener(this);
		
		image=new ImageIcon("Image/LANTest.gif");
		btnLANTest=new JButton("网络测试(6)",image);
		btnLANTest.setFont(new Font("x",Font.PLAIN,12));
		btnLANTest.setMnemonic('6');
		btnLANTest.addActionListener(this);
		
		image=new ImageIcon("Image/BMCX.gif");
		btnBMCX=new JButton("编码查询(7)",image);
		btnBMCX.setFont(new Font("x",Font.PLAIN,12));
		btnBMCX.setMnemonic('7');
		btnBMCX.addActionListener(this);
		
		image=new ImageIcon("Image/help.gif");
		btnHelp=new JButton("帮        助(8)",image);
		btnHelp.setFont(new Font("x",Font.PLAIN,12));
		btnHelp.setMnemonic('8');
		btnHelp.addActionListener(this);
		
		image=new ImageIcon("Image/Exit.gif");
		btnExit=new JButton("退出系统(Q)",image);
		btnExit.setFont(new Font("x",Font.PLAIN,12));
		btnExit.setMnemonic('Q');
		btnExit.addActionListener(this);
		
		p.setLayout(new FlowLayout());
		p.add(btnJP);
		p.add(btnZG);
		p.add(btnJM);
		p.add(btnWZ);
		p.add(btnLocalTest);
		p.add(btnLANTest);
		p.add(btnBMCX);
		p.add(btnHelp);
		p.add(btnExit);
		add(this.p);
		
		
		setLayer(1); 
	    setBounds(1,0,0,0); 
	    setPreferredSize(new Dimension(140,325)); 
	    ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
	    this.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE); 
	    pack();
	    setVisible(true); 
	}
	//实现ActionListener接口的方法
	public void actionPerformed(ActionEvent e){
		if(PublicData.onChoice==true){
			if(e.getSource()==btnJP){
				PublicData.onChoice=false;
				PublicData.Lang="English";
				PublicData.Text="Text/English_"+(int)(Math.random()*5+1)+".txt";
				PublicData.choice=1;
				showFrame();
			}
			if(e.getSource()==btnZG){
				PublicData.onChoice=false;
				PublicData.Lang="Chinese";
				PublicData.choice=2;
				showFrame();
			}
			if(e.getSource()==btnJM){
				PublicData.onChoice=false;
				PublicData.Lang="Chinese";
				PublicData.Text="Text/TwoCode.txt";
				PublicData.choice=3;
				showFrame();
			}
			if(e.getSource()==btnWZ){
				PublicData.onChoice=false;
				PublicData.Lang="Chinese";
				PublicData.Text="Text/Chinese_"+(int)(Math.random()*5+1)+".txt";
				PublicData.choice=3;
				showFrame();
			}
			if(e.getSource()==btnLocalTest){
				PublicData.onChoice=false;
				PublicData.Lang="Chinese";
				PublicData.Text="Text/Chinese_"+(int)(Math.random()*5+1)+".txt";
				PublicData.choice=3;
				showFrame();
			}
			if(e.getSource()==btnLANTest){
				InputStream is = this.getClass().getResourceAsStream("Config.properties");
				Properties properties = new Properties();
				try{
					properties.load(is);
					is.close();
					if((Integer.parseInt(properties.getProperty("TestMode"))==1) ? true:false){
						PublicData.onChoice=false;
						LanManage lanmanage=new LanManage();
						parent.add(lanmanage,4);
					}
					else{
						new Client();
					}

				}catch(IOException ex){}
			}
			if(e.getSource()==btnBMCX){
				PublicData.onChoice=false;
				QueryCoding coding=new QueryCoding();
				parent.add(coding,6);
			}
			if(e.getSource()==btnHelp){
				PublicData.onChoice=false;
				Help help=new Help();
				parent.add(help,4);
			}
			if(e.getSource()==btnExit){
				int i=JOptionPane.showConfirmDialog(this.parent,"你真的要退出吗?","疯狂打字",
						JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(i==0){
					System.exit(0);
				}
			}
		}
		
	}
	
	private void showFrame(){
		Hint hint =new Hint(this);
		parent.add(hint,2);
	}
}
