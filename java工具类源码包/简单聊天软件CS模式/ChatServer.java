import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ChatServer extends JPanel{
	private JPanel topPanel = new JPanel();
	private JPanel buttomPanel = new JPanel();
	private DefaultTableModel defaultModel;
	private JTable nameTable;
	private JButton exitButton;
	private JList list;
	private DefaultListModel listModel;
	private JButton sendButton = new JButton("发送");
	private JTextField sayWord = new JTextField();
	private Vector totalThread = new Vector();
	private int port = 8082;
	public ChatServer(){
		this.setLayout(new GridLayout(2,1));
	}
	public void init(){
		String[] columName = new String[] {"用户名","IP"	} ;
		String[][] tempData = new String[][]{{"",""}};		
		defaultModel = new DefaultTableModel(tempData,columName);
		nameTable = new JTable(defaultModel);
		nameTable.setRowHeight(22);
		exitButton = new JButton("关闭服务器");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		topPanel.setLayout(new BorderLayout());
		topPanel.add(new JLabel("在线用户",JLabel.LEFT),BorderLayout.NORTH);
		topPanel.add(new JScrollPane(nameTable),BorderLayout.CENTER);
		listModel = new DefaultListModel();
		list = new JList(listModel);
		JPanel temp = new JPanel(new BorderLayout());
		temp.add(sayWord,BorderLayout.CENTER);
		sendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				sendData();
			}
		});
		temp.add(sendButton,BorderLayout.EAST);
		JPanel temp2 = new JPanel();
		temp2.add(exitButton);
		temp.add(temp2,BorderLayout.SOUTH);
		
		buttomPanel.setLayout(new BorderLayout());
		buttomPanel.add(new JScrollPane(list),BorderLayout.CENTER);
		buttomPanel.add(temp,BorderLayout.SOUTH);
		
		this.add(topPanel);
		this.add(buttomPanel);
		new StartServer().start();
		new UpdataThread().start();
	}
	
	class StartServer extends Thread{
		public void run(){
			try{
				ServerSocket serverSocket = new ServerSocket(port);
				while(true){
					try{
						Socket Socket = serverSocket.accept();
						AcceptThread acceptThread = new AcceptThread(Socket);
						acceptThread.start();
						totalThread.add(acceptThread);
					}catch (IOException ex2){
						throw ex2;
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	private void addWord(String word){
		listModel.add(0, word);
	}
	
	private void sendData(){
		String temp = "服务器说： "+sayWord.getText().trim();
		sendWordToEveryone(temp.getBytes());
		addWord(temp);
	}
	
	private void sendWordToEveryone(byte[] bytes){
		Enumeration enum1 = totalThread.elements();
		while(enum1.hasMoreElements()){
			Object tempObject = enum1.nextElement();
			if(tempObject != null){
				AcceptThread tempThread = (AcceptThread) tempObject;
				if(tempThread.isAlive()){
					tempThread.sendWord(bytes);
				}
			}
		}
	}

	class UpdataThread extends Thread{
		public void run(){
			while(true){
				String[] columName = new String[] {"用户名称: ","IP"	};
				String[][] tempData = new String[][]{};
				defaultModel = new DefaultTableModel(tempData,columName);
				AcceptThread tempThread = null;
				Object tempObject = null;
				Enumeration enum1 = totalThread.elements();
				while(enum1.hasMoreElements()){
					tempObject = enum1.nextElement();
					if(tempObject != null){
						tempThread = (AcceptThread) tempObject;
						if(tempThread.isAlive())
							defaultModel.addRow(new String[]{
									tempThread.getUserName(),
									tempThread.getUserIP()});
						else
							tempObject = null;
					}
					
					nameTable.setModel(defaultModel);
				}
				try{
					sleep(6000);
				}catch(InterruptedException e){
					e.printStackTrace();
			}
		}
	}	
}
	public static void main(String[] args){
		JFrame mf = new JFrame("Chat Server");
		ChatServer server = new ChatServer();
		mf.getContentPane().add(server);
		mf.setBounds(200,100,450,450);
		mf.addWindowListener (new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		server.init();
		mf.setVisible(true);
	}
	
	class AcceptThread extends Thread{
		Socket Socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String name = "默认用户";
		String ip;
		boolean flag = true;
		BufferedInputStream bis;
		BufferedOutputStream bos;
		byte[] bytes;
		public AcceptThread(Socket Socket){
			this.Socket = Socket;
			ip = Socket.getInetAddress().getHostAddress();
			System.out.println(ip);
			try{
				inputStream = Socket.getInputStream();
				outputStream = Socket.getOutputStream();
				bos = new BufferedOutputStream(outputStream,1024);
			}catch(IOException e){
				e.printStackTrace();
				if(Socket != null){
					try{
						Socket.close();
					}catch (IOException e1){
						e1.printStackTrace();
					}
				}
				flag = false;
			}
		}
		
		public void run(){
			bis = new BufferedInputStream(inputStream,1024);
			while(flag){
				try{
					bytes = new byte[1024];
					bis.read(bytes);
					String tempS = new String(bytes);
					tempS = tempS.trim();
					name = tempS.substring(0,tempS.indexOf("说"));
					addWord(tempS);
					sendWordToEveryone(bytes);
				}catch(IOException e){
					e.printStackTrace();
					flag = false;
				}
			}
		}
		
		public void sendWord(byte[] bytes){
			try{
				bos.write(bytes);
				bos.flush();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		public String getUserName(){
			return name;
		}
		
		public String getUserIP(){
			return ip;
		}		
	}
}
