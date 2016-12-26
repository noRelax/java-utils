import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JInternalFrame;
import javax.swing.*;
import javax.swing.table.*;

import java.net.*;
import java.io.*;


public class LanManage extends JInternalFrame{

	
	JTable table = null;
	DefaultTableModel defaultModel = null;
	private JLabel userCount;
	private JButton btnCelean;
	private JButton saveData;
	private JButton beginTest;
	private JLabel conUser;
	private JTextField hintMessage;
	private JLabel hint;
	private JComboBox time;
	private JLabel testTime;
	private JButton TextBrowse;
	private JTextField txtText;
	private JLabel testText;
	private JPanel p;
	JScrollPane s;
	
	//配置属性
	private int ServerPort;
	private int LinePort;
	private String netTestText;
	private int netTestTime;
	
	//网络属性
	ServerSocket server;
	Socket client;
	BufferedReader in;
	PrintWriter out;
	
	//用户信息
	private String estate;
	private String userName;
	private String ime;
	private String remark;
	private String clientIP;
	private String clientName;
	private int countWord;
	private int rightWord;
	private int errorWord;
	private double mWord;
	
	private int a=0;
	
	private void startServer(){//启动服务器
		try{
			server=new ServerSocket(ServerPort);
		}catch(IOException e){}
	}
	
	
	
	private void readProperty(){//读取配置文件
		InputStream is = this.getClass().getResourceAsStream("Config.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			is.close();
			ServerPort = Integer.parseInt(properties.getProperty("ServerPort")); 
			LinePort = Integer.parseInt(properties.getProperty("LinePort"));
			netTestText = properties.getProperty("netTestText"); 
			netTestTime = Integer.parseInt(properties.getProperty("netTestTime"));
			}
		catch(IOException ex) {}
	}
	
	public LanManage(){//组件用户界面
		readProperty();
		String[] name={"状态","用户","输入法","备注","IP","主机名","总字数","正确","错误","每分钟"};
		defaultModel=new DefaultTableModel(new Object[0][0],name);
		table=new JTable(defaultModel);

		JScrollPane s = new JScrollPane(table);
		
		p=new JPanel();
		GridBagLayout pLayout = new GridBagLayout();
		pLayout.rowWeights = new double[] {0.0, 0.1, 0.1, 0.1, 0.1};
		pLayout.rowHeights = new int[] {13, 7, 7, 7, 7};
		pLayout.columnWeights = new double[] {0.0, 0.1, 0.1, 0.1, 0.1, 0.1};
		pLayout.columnWidths = new int[] {55, 20, 7, 7, 7, 7};
		p.setLayout(pLayout);
		Container contentPane = this.getContentPane();
		contentPane.add(s, BorderLayout.CENTER); 
		p.setPreferredSize(new java.awt.Dimension(657, 100));
		p.setBorder(BorderFactory.createTitledBorder(""));
		contentPane.add(p,BorderLayout.SOUTH);
		{
			testText = new JLabel();
			p.add(testText, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			testText.setText("\u6d4b\u8bd5\u6587\u672c:");
			testText.setFont(new java.awt.Font("新宋体",0,12));
		}
		{
			txtText = new JTextField();
			p.add(txtText, new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			txtText.setEditable(false);
			txtText.setText(netTestText);
			
		}
		{
			TextBrowse = new JButton();
			p.add(TextBrowse, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			TextBrowse.setText("...");
			TextBrowse.setFont(new java.awt.Font("新宋体",0,12));
		}
		{
			testTime = new JLabel();
			p.add(testTime, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
			testTime.setText("\u6d4b\u8bd5\u65f6\u95f4:");
			testTime.setFont(new java.awt.Font("新宋体",0,12));
		}
		{
			ComboBoxModel timeModel = new DefaultComboBoxModel();
			time = new JComboBox();
			p.add(time, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			time.setModel(timeModel);
			time.setFont(new java.awt.Font("新宋体",0,12));
			time.addItem(netTestTime+"分钟");
			time.addItem("1分钟");
			for(int i=5;i<=60;i=i+5){
				time.addItem(i+"分钟");
			}
		}
		{
			hint = new JLabel();
			p.add(hint, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			hint.setText("\u63d0\u793a\u4fe1\u606f:");
			hint.setFont(new java.awt.Font("新宋体",0,12));
		}
		{
			hintMessage = new JTextField();
			p.add(hintMessage, new GridBagConstraints(1, 2, 5, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			conUser = new JLabel();
			p.add(conUser, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			conUser.setText("\u8fde\u63a5\u5230\u670d\u52a1\u5668\u7684\u7528\u6237:");
			conUser.setFont(new java.awt.Font("新宋体",0,12));
		}
		{
			userCount = new JLabel();
			p.add(userCount, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			userCount.setText("0");
			userCount.setFont(new java.awt.Font("新宋体",0,12));
		}
		{
			beginTest = new JButton();
			p.add(beginTest, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			beginTest.setText("\u5f00\u59cb\u6d4b\u8bd5");
			beginTest.setFont(new java.awt.Font("新宋体",0,12));
		}
		{
			saveData = new JButton();
			p.add(saveData, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			saveData.setText("\u5b58\u50a8\u6d4b\u8bd5\u7ed3\u679c");
			saveData.setFont(new java.awt.Font("新宋体",0,12));
		}
		{
			btnCelean = new JButton();
			p.add(btnCelean, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			btnCelean.setText("\u5173\u95ed");
			btnCelean.setFont(new java.awt.Font("新宋体",0,12));
			btnCelean.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					exit();
				}
			});
		}

		setLayer(4);
        setBounds(142,0,0,0); 
        this.setPreferredSize(new java.awt.Dimension(657, 549));//内部窗体大小
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        this.setVisible(true);
        this.pack();
        startServer();
	}
	
	private void exit(){
		this.doDefaultCloseAction();
		PublicData.onChoice=true;
	}

}
