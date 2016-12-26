import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.util.*;
import Net.*;

public class LanConfig extends JDialog {
	private JPanel p1;
	private JPanel p2;
	private ButtonGroup grp;
	private JRadioButton chatServer;
	private JButton btnBrowse;
	private JRadioButton testServer;
	private JButton btnCancel;
	private JButton btnOK;
	private JPanel p3;
	private JTextField txtPort;
	private JLabel lblport;
	private JRadioButton testClient;
	private JLabel msg1;
	private JComboBox cmb;
	private JLabel lblTestTime;
	private JTextField textFile;
	private JLabel testText;
	private JTextField txtServerPort;
	private JLabel serverPort;
	private ButtonGroup grp2;
	private JTextField txtUserName;
	private JLabel userName;
	private JRadioButton chatClient;
	private JLabel localIP;
	private JTextField txtLineIP;
	private JLabel LineIP;

	private String ChatUserName;
	private boolean ChatMode;
	private boolean TestMode;
	private int ServerPort;
	private int LinePort;
	private String netTestText;
	private int netTestTime;
	private String IP;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public LanConfig(){
		readProperty();
		initGUI();
	}
	private void readProperty(){
		InputStream is = this.getClass().getResourceAsStream("Config.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			is.close();
			ChatMode = (Integer.parseInt(properties.getProperty("ChatMode"))==1) ? true:false;
			ChatUserName = properties.getProperty("ChatUserName"); 
			TestMode = (Integer.parseInt(properties.getProperty("TestMode"))==1) ? true:false;
			ServerPort = Integer.parseInt(properties.getProperty("ServerPort")); 
			LinePort = Integer.parseInt(properties.getProperty("LinePort"));
			netTestText = properties.getProperty("netTestText"); 
			netTestTime = Integer.parseInt(properties.getProperty("netTestTime"));
			IP=properties.getProperty("LineIP");
			//System.out.println(ChatMode);
			}
		catch(IOException ex) {}
	}
	
	private void writeProperties() {
	     Properties prop = new Properties();
	     try {
	     	InputStream fis = new FileInputStream("Config.properties");
	      
	            prop.load(fis);
	            
	            OutputStream fos = new FileOutputStream("Config.properties");
	            prop.setProperty("ChatMode", (ChatMode==true)?"1":"0");
	            ChatUserName=txtUserName.getText();
	            prop.setProperty("ChatUserName", ChatUserName);
	            prop.setProperty("TestMode", (TestMode==true)?"1":"0");
	            ServerPort=Integer.parseInt(txtServerPort.getText());
	            prop.setProperty("ServerPort", ServerPort+"");
	            LinePort=Integer.parseInt(txtPort.getText());
	            prop.setProperty("LinePort", LinePort+"");
	            netTestText=textFile.getText();
	            prop.setProperty("netTestText", netTestText);
	            String time=(String)(cmb.getItemAt(cmb.getSelectedIndex()));
	            time=time.substring(0,time.length()-2);
	            netTestTime=Integer.parseInt(time);
	            prop.setProperty("netTestTime",netTestTime+"");
	            IP=txtLineIP.getText();
	            prop.setProperty("LineIP",IP);
	            prop.store(fos,"疯狂打字通v2.1");
	            
	        } 
	    catch (IOException e){}
	    prop.clear();
	    prop.clone();
	    }

	private void initGUI() {
		Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("\u7f51\u7edc\u914d\u7f6e");
				setIconImage(Toolkit.getDefaultToolkit().getImage("image/f.gif"));
			}
			{
				this.setSize(344, 315);
				setLocation((screen.width-getWidth())/2,(screen.height-getHeight())/2);
				this.setVisible(true);
				{
					{
						grp = new ButtonGroup();
					}
					{
						grp2 = new ButtonGroup();
					}
					p1 = new JPanel();
					getContentPane().add(p1);
					p1.setBorder(BorderFactory.createTitledBorder("网络聊天"));
					p1.setBounds(0, 0, 336, 56);
					{
						userName = new JLabel();
						p1.add(userName);
						userName.setText("\u7f51\u7edc\u540d:");
						userName.setFont(new java.awt.Font("新宋体",0,12));
					}
					{
						txtUserName = new JTextField();
						txtUserName.setText(ChatUserName);
						p1.add(txtUserName);
						txtUserName.setPreferredSize(new java.awt.Dimension(87, 20));
					}
					{
						chatServer = new JRadioButton();
						grp.add(chatServer);
						p1.add(chatServer);
						chatServer.setText("\u670d\u52a1\u5668\u65b9\u5f0f");
						chatServer.setFont(new java.awt.Font("新宋体", 0, 12));
						chatServer.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								ChatMode=true;
							}
						});
						if(ChatMode==true){
							chatServer.setSelected(true);
						}
					}
					{
						chatClient = new JRadioButton();
						grp.add(chatClient);
						p1.add(chatClient);
						chatClient.setText("\u5ba2\u6237\u673a\u65b9\u5f0f");
						chatClient.setFont(new java.awt.Font("新宋体",0,12));
						chatClient.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								ChatMode=false;
							}
						});
						if(ChatMode==false){
							chatClient.setSelected(true);
						}
					}
				}
				{
					p2 = new JPanel();
					p2.setLayout(null);
					getContentPane().add(p2);
					p2.setBorder(BorderFactory.createTitledBorder("网络测试"));
					p2.setBounds(0, 56, 336, 189);
					{
						testServer = new JRadioButton();
						grp2.add(testServer);
						p2.add(testServer);
						testServer.setText("\u670d\u52a1\u5668\u65b9\u5f0f");
						testServer.setFont(new java.awt.Font("新宋体",0,12));
						testServer.setBounds(7, 21, 112, 21);
						testServer.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								txtServerPort.setEnabled(true);
								textFile.setEnabled(true);
								btnBrowse.setEnabled(true);
								cmb.setEnabled(true);
								txtPort.setEnabled(false);
								txtLineIP.setEnabled(false);
								TestMode=true;
							}
						});
						if(TestMode==true){
							testServer.setSelected(true);
						}
					}
					{
						serverPort = new JLabel();
						p2.add(serverPort);
						serverPort.setText("\u670d\u52a1\u5668\u7aef\u53e3:");
						serverPort.setFont(new java.awt.Font("新宋体", 0, 12));
						serverPort.setBounds(24, 49, 70, 14);
					}
					{
						txtServerPort = new JTextField();
						p2.add(txtServerPort);
						txtServerPort.setBounds(91, 46, 91, 21);
						txtServerPort.setText(ServerPort+"");
						if(TestMode==false){
							txtServerPort.setEnabled(false);
						}
					}
					{
						testText = new JLabel();
						p2.add(testText);
						testText.setText("\u6d4b \u8bd5\u6587 \u672c:");
						testText.setBounds(24, 80, 70, 14);
						testText.setFont(new java.awt.Font("新宋体",0,12));
					}
					{
						textFile = new JTextField();
						p2.add(textFile);
						textFile.setBounds(91, 77, 203, 21);
						textFile.setText(netTestText);
						if(TestMode==false){
							textFile.setEnabled(false);
						}
					}
					{
						btnBrowse = new JButton();
						p2.add(btnBrowse);
						btnBrowse.setText("...");
						btnBrowse.setBounds(297, 77, 28, 20);
						btnBrowse.setFont(new java.awt.Font("新宋体",0,11));
						if(TestMode==false){
							btnBrowse.setEnabled(false);
						}
					}
					{
						lblTestTime = new JLabel();
						p2.add(lblTestTime);
						lblTestTime.setText("\u6d4b \u8bd5\u65f6 \u95f4:");
						lblTestTime.setBounds(24, 110, 70, 14);
						lblTestTime.setFont(new java.awt.Font("新宋体",0,12));
					}
					{
						ComboBoxModel cmbModel = new DefaultComboBoxModel();
						cmb = new JComboBox();
						p2.add(cmb);
						cmb.setModel(cmbModel);
						cmb.setBounds(91, 107, 203, 21);
						cmb.setFont(new java.awt.Font("新宋体",0,12));
						cmb.addItem(netTestTime+"分钟");
						cmb.setEditable(false);
						if(TestMode==false){
							cmb.setEnabled(false);
						}
						cmb.addItem("1分钟");
						for(int i=5;i<=60;i=i+5){
							cmb.addItem(i+"分钟");
						}
					}
					{
						msg1 = new JLabel();
						p2.add(msg1);
						msg1.setText("(\u5927\u4e8e0\u4e14\u5c0f\u4e8e65535)");
						msg1.setBounds(182, 42, 119, 28);
						msg1.setFont(new java.awt.Font("新宋体",0,12));
					}
					{
						testClient = new JRadioButton();
						grp2.add(testClient);
						p2.add(testClient);
						testClient.setText("\u5ba2\u6237\u673a\u65b9\u5f0f");
						testClient.setFont(new java.awt.Font("新宋体",0,12));
						testClient.setBounds(7, 133, 91, 21);
						testClient.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								txtServerPort.setEnabled(false);
								textFile.setEnabled(false);
								btnBrowse.setEnabled(false);
								cmb.setEnabled(false);
								txtPort.setEnabled(true);
								txtLineIP.setEnabled(true);
								TestMode=false;
							}
						});
						if(TestMode==false){
							testClient.setSelected(true);
						}
					}
					{
						lblport = new JLabel();
						p2.add(lblport);
						lblport.setText("\u8fde \u63a5\u7aef \u53e3:");
						lblport.setFont(new java.awt.Font("新宋体",0,12));
						lblport.setBounds(24, 161, 70, 14);
					}
					{
						txtPort = new JTextField();
						p2.add(txtPort);
						txtPort.setBounds(91, 158, 56, 21);
						txtPort.setText(LinePort+"");
						if(TestMode==true){
							txtPort.setEnabled(false);
						}
					}
					{
						LineIP = new JLabel();
						p2.add(LineIP);
						LineIP.setText("\u8fde\u63a5IP:");
						LineIP.setBounds(154, 162, 49, 14);
						LineIP.setFont(new java.awt.Font("新宋体",0,12));
					}
					{
						txtLineIP = new JTextField();
						p2.add(txtLineIP);
						txtLineIP.setBounds(196, 159, 98, 21);
						txtLineIP.setText(IP);
						if(TestMode==true){
							txtLineIP.setEnabled(false);
						}
					}
					{
						localIP = new JLabel();
						p2.add(localIP);
						localIP.setBounds(185, 21, 147, 21);
						localIP.setFont(new java.awt.Font("新宋体",0,12));
						localIP.setText("本机IP:"+NetTools.getIP());
					}
				}
				{
					p3 = new JPanel();
					getContentPane().add(p3);
					p3.setLayout(null);
					p3.setBounds(0, 245, 336, 35);
					{
						btnOK = new JButton();
						p3.add(btnOK);
						btnOK.setText("\u786e\u5b9a");
						btnOK.setBounds(189, 6, 63, 21);
						btnOK.setFont(new java.awt.Font("新宋体",0,12));
						btnOK.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								writeProperties();
							}
						});
					}
					{
						btnCancel = new JButton();
						p3.add(btnCancel);
						btnCancel.setText("\u53d6\u6d88");
						btnCancel.setBounds(266, 6, 63, 21);
						btnCancel.setFont(new java.awt.Font("新宋体",0,12));
						btnCancel.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
							}
						});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
