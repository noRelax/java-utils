import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ChatClient extends JPanel{
	private InputStream inputStream;
	private OutputStream outputStream;
	private JTextField sayText = new JTextField();
	private Socket Socket;
	private DefaultListModel listModel = new DefaultListModel();
	private JList list = new JList(listModel);
	private JTextField nameText = new JTextField(10);
	private BufferedOutputStream bos;
	private JButton sendButton = new JButton("发送");
	private String host = "192.168.1.4";
	private int port = 8082;
	public void init(){
		nameText.setText("默认用户");
		setLayout(new BorderLayout());
		add(this.getContextPanel(),BorderLayout.CENTER);
		add(this.getButtomPanel(),BorderLayout.SOUTH);
		this.startClient(host,port);
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Chat Client");
		frame.setSize(400,500);
		frame.setLocation(200,100);
		ChatClient chatClient = new ChatClient();
		chatClient.init();
		frame.getContentPane().add(chatClient);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
	
	private boolean flag = true;
	
	private void startClient(String host,int port){
		try{
			Socket = new Socket(InetAddress.getByName(host),port);
			inputStream = Socket.getInputStream();
			outputStream = Socket.getOutputStream();
			bos = new BufferedOutputStream(outputStream , 1024);
			System.out.println(bos);
			new ResevWord(inputStream).start();	
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
			flag = false;
		}
	}
	private void sendData(){
		String word = nameText.getText()+"说: "+sayText.getText();
		try{
			bos.write(word.getBytes());
			bos.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
		sayText.setText("");
	}
	
	class ResevWord extends Thread{
		InputStream ips = null;
		BufferedInputStream bis = null;
		byte[] bytes = new byte[1024];
		public ResevWord(InputStream ips){
			this.ips = ips;
			bis = new BufferedInputStream(ips,1024);
		}
		public void run(){
			while(flag)
			{
				try{
					try{
						bis.read(bytes);
						String word = new String(bytes);
						word = word.trim();
						listModel.add(0, word);
					}catch(IOException e){
						e.printStackTrace();
						flag = false;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	private JPanel getContextPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		p1.add(new JLabel("我的名字： "));
		p1.add(nameText);
		panel.add(p1,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel getButtomPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(sayText,BorderLayout.CENTER);
		sendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendData();
			}
		});
		panel.add(sendButton,BorderLayout.EAST);
		return panel;
	}
}
