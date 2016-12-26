import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;

/*<p>Title:HappyChat����ϵͳ�������</p>
 *<p>Description:ϵͳ�û���¼���������</p>
 *<p>Copyright:Copyright (C)2006</p>
 *<p>Filename:ChatRoom.java</p>
 *@author ��־
 *@version 1.0
 */

public class ChatRoom extends Thread implements ActionListener {
	private JComboBox daXiaoComboBox;

	private JComboBox yangShiComboBox;

	private JComboBox zitiComboBox;

	private JComboBox emote;

	static JFrame frmChat;

	JPanel pnlChat;

	JButton btnCls, btnExit, btnSend, btnClear, btnSave, btnTimer;

	JLabel lblUserList, lblUserMessage, lblSendMessage, lblChatUser;

	JLabel lblUserTotal, lblCount, lblBack;

	JTextField txtMessage;

	java.awt.List lstUserList;

	TextArea taUserMessage;

	JComboBox cmbUser;

	JCheckBox chPrivateChat;

	String strServerIp, strLoginName;

	Thread thread;

	final JLabel headLabel = new JLabel();

	// ���ڽ��������ڶ�λ
	Dimension scrnsize;

	Toolkit toolkit = Toolkit.getDefaultToolkit();

	Message messobj = null;

	String serverMessage = "";

	final JSlider blueSlider = new JSlider();

	final JSlider redSlider = new JSlider();

	final JSlider greenSlider = new JSlider();

	final JLabel hanziLabel = new JLabel();

	// ���췽��
	public ChatRoom(String name, String ip) {
		// Dialog d = new FontDialog();
		strServerIp = ip;
		strLoginName = name;
		frmChat = new JFrame("������" + "[�û�:" + name + "]");
		pnlChat = new JPanel();
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().add(pnlChat);

		Font fntDisp1 = new Font("����", Font.PLAIN, 12);

		String list[] = { "������" };
		btnCls = new JButton("����(C)");
		btnExit = new JButton("�˳�(X)");
		btnSend = new JButton("����(N)");
		btnSave = new JButton("����(S)");
		btnTimer = new JButton("ʱ��(T)");
		lblUserList = new JLabel("�������û��б�");
		lblUserMessage = new JLabel("��������Ϣ��");
		lblSendMessage = new JLabel("��������:");
		lblChatUser = new JLabel("���:");
		lblUserTotal = new JLabel("��������:");
		lblCount = new JLabel("0");
		lstUserList = new java.awt.List();
		txtMessage = new JTextField(170);
		cmbUser = new JComboBox(list);
		// chenmin
		cmbUser.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				freshHead();
			}
		});
		chPrivateChat = new JCheckBox("˽��");
		taUserMessage = new TextArea("", 300, 200,
				TextArea.SCROLLBARS_VERTICAL_ONLY);// ֻ�����¹���
		// taUserMessage.setText("aaaaaa����aaaa");
		taUserMessage.setForeground(new Color(0, 0, 0));
		taUserMessage.setEditable(false); // ����д��

		/***********************************************************************
		 * �ò��ֲ����ֶ����� setBounds�������λ�� * setFont�������塢���͡��ֺ� * setForeground�������ֵ���ɫ *
		 * setBackground���ñ���ɫ * setOpaque����������Ϊ͸��
		 */

		pnlChat.setLayout(null);
		pnlChat.setBackground(new Color(52, 130, 203));
		btnTimer.setBounds(400, 360, 80, 25);
		btnSave.setBounds(500, 330, 80, 25);
		btnCls.setBounds(400, 330, 80, 25);
		btnExit.setBounds(500, 360, 80, 25);
		btnSend.setBounds(500, 300, 80, 25);

		lblUserList.setBounds(5, 0, 120, 40);
		lblUserTotal.setBounds(130, 0, 60, 40);
		lblCount.setBounds(190, 0, 60, 40);
		lblUserMessage.setBounds(225, 0, 180, 40);
		lblChatUser.setBounds(10, 290, 40, 40);
		lblSendMessage.setBounds(210, 290, 60, 40);

		// lblUserTotal.setBounds(10,340,100,40);
		// lblCount.setBounds(73,340,100,40);
		lstUserList.setBounds(5, 40, 210, 255);
		taUserMessage.setBounds(225, 40, 360, 255);
		txtMessage.setBounds(270, 300, 210, 25);
		cmbUser.setBounds(50, 300, 80, 25);
		chPrivateChat.setBounds(333, 336, 60, 20);
		btnTimer.setFont(fntDisp1);
		btnCls.setFont(fntDisp1);
		btnExit.setFont(fntDisp1);
		btnSend.setFont(fntDisp1);
		btnSave.setFont(fntDisp1);
		lblUserList.setFont(fntDisp1);
		lblUserMessage.setFont(fntDisp1);
		lblChatUser.setFont(fntDisp1);
		lblSendMessage.setFont(fntDisp1);
		lblUserTotal.setFont(fntDisp1);
		lblCount.setFont(fntDisp1);
		cmbUser.setFont(fntDisp1);
		chPrivateChat.setFont(fntDisp1);
		// taUserMessage.setFont(new Font("������", Font.BOLD | Font.ITALIC, 20));

		lblUserList.setForeground(Color.YELLOW);
		lblUserMessage.setForeground(Color.YELLOW);
		lblSendMessage.setForeground(Color.black);
		lblChatUser.setForeground(Color.black);
		lblSendMessage.setForeground(Color.black);
		lblUserTotal.setForeground(Color.YELLOW);
		lblCount.setForeground(Color.YELLOW);
		cmbUser.setForeground(Color.black);
		chPrivateChat.setForeground(Color.black);
		lstUserList.setBackground(Color.white);
		taUserMessage.setBackground(Color.white);
		btnTimer.setBackground(Color.ORANGE);
		btnCls.setBackground(Color.ORANGE);
		btnExit.setBackground(Color.ORANGE);
		btnSend.setBackground(Color.PINK);
		btnSave.setBackground(Color.ORANGE);
		pnlChat.add(btnTimer);
		pnlChat.add(btnCls);
		pnlChat.add(btnExit);
		pnlChat.add(btnSend);
		pnlChat.add(btnSave);
		pnlChat.add(lblUserList);
		pnlChat.add(lblUserMessage);
		pnlChat.add(lblSendMessage);
		pnlChat.add(lblChatUser);
		pnlChat.add(lblUserTotal);
		pnlChat.add(lblCount);
		pnlChat.add(lstUserList);
		pnlChat.add(taUserMessage);
		pnlChat.add(txtMessage);
		pnlChat.add(cmbUser);
		pnlChat.add(chPrivateChat);

		frmChat.addWindowListener(new Windowclose());
		btnTimer.addActionListener(this);
		btnCls.addActionListener(this);
		btnExit.addActionListener(this);
		btnSend.addActionListener(this);
		btnSave.addActionListener(this);
		lstUserList.addActionListener(this);
		txtMessage.addActionListener(this);

		headLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headLabel.setIcon(new ImageIcon("face//1.JPG"));
		headLabel.setBounds(15, 335, 70, 60);
		pnlChat.add(headLabel);

		emote = new JComboBox();
		emote.setModel(new DefaultComboBoxModel(new String[] { "����", "΢Ц",
				"��Ц", "��ϲ", "����", "���", "�ٺ�", "ɵЦ", "����", "����", "����", "����",
				"����", "����", "����", "��ŭ", "����", "����", "����", "�ʺ�", "��Ц", "���",
				"ʾ��", "����", "����" }));
		emote.setBounds(141, 301, 60, 23);
		pnlChat.add(emote);

		zitiComboBox = new JComboBox();
		zitiComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				freshFont();
			}

		});
		zitiComboBox.setModel(new DefaultComboBoxModel(new String[] { "����",
				"����" }));
		zitiComboBox.setBounds(76, 337, 64, 23);
		pnlChat.add(zitiComboBox);

		yangShiComboBox = new JComboBox();
		yangShiComboBox.setModel(new DefaultComboBoxModel(new String[] { "����",
				"����", "б��", "��б" }));
		yangShiComboBox.setBounds(153, 337, 52, 23);
		yangShiComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				freshFont();
			}

		});
		pnlChat.add(yangShiComboBox);

		daXiaoComboBox = new JComboBox();
		daXiaoComboBox.setModel(new DefaultComboBoxModel(new String[] { "12",
				"14", "16", "18", "20" }));
		daXiaoComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				freshFont();
			}

		});
		daXiaoComboBox.setBounds(220, 335, 40, 23);
		pnlChat.add(daXiaoComboBox);

		blueSlider.setMaximum(255);
		blueSlider.setValue(128);
		blueSlider.setMinimum(0);
		blueSlider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				// TODO �Զ����ɷ������
				freshColor();
			}
		});
		// slider.setModel(null);
		blueSlider.setBounds(80, 365, 180, 10);
		pnlChat.add(blueSlider);

		redSlider.setMaximum(255);
		redSlider.setValue(128);
		redSlider.setMinimum(0);
		redSlider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				// TODO �Զ����ɷ������
				freshColor();
			}
		});
		redSlider.setBounds(80, 380, 180, 10);
		pnlChat.add(redSlider);

		greenSlider.setMaximum(255);
		greenSlider.setValue(128);
		greenSlider.setMinimum(0);
		greenSlider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				// TODO �Զ����ɷ������
				freshColor();
			}
		});
		greenSlider.setBounds(80, 395, 180, 10);
		pnlChat.add(greenSlider);

		hanziLabel.setForeground(new Color(128, 128, 128));
		hanziLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hanziLabel.setFont(new Font("����", Font.BOLD, 18));
		hanziLabel.setBackground(Color.ORANGE);
		hanziLabel.setText("����");
		hanziLabel.setBounds(273, 360, 57, 50);
		pnlChat.add(hanziLabel);

		// ��������ҳ����Ϣˢ���߳�
		Thread thread = new Thread(this);
		thread.start();

		// Icon log=new ImageIcon("images\\chat.jpg");
		// lblBack = new JLabel(log);
		// lblBack.setBounds(1, 1, 600,420);
		// pnlChat.add(lblBack);

		frmChat.setSize(600, 461);
		frmChat.setVisible(true);
		frmChat.setResizable(false);

		// �����ڶ�λ����Ļ����
		scrnsize = toolkit.getScreenSize();
		frmChat.setLocation(scrnsize.width / 2 - frmChat.getWidth() / 2,
				scrnsize.height / 2 - frmChat.getHeight() / 2);
		Image img = toolkit.getImage("images\\appico.jpg");
		frmChat.setIconImage(img);

	} // ���췽������

	protected void freshFont() {
		// TODO �Զ����ɷ������
		String ziti = zitiComboBox.getSelectedItem().toString();
		int yangShi = 0;
		String yangShiString = yangShiComboBox.getSelectedItem().toString();
		if (yangShiString.equals("����")) {
			yangShi = Font.PLAIN;
		} else if (yangShiString.equals("����")) {
			yangShi = Font.BOLD;
		} else if (yangShiString.equals("б��")) {
			yangShi = Font.ITALIC;
		} else if (yangShiString.equals("��б")) {
			yangShi = Font.BOLD | Font.ITALIC;
		}

		int daXiao = Integer.parseInt(daXiaoComboBox.getSelectedItem()
				.toString());
		taUserMessage.setFont(new Font(ziti, yangShi, daXiao));
		taUserMessage.setForeground(hanziLabel.getForeground());
	}

	protected void freshColor() {
		// TODO �Զ����ɷ������
		hanziLabel.setForeground(new Color(blueSlider.getValue(), redSlider
				.getValue(), greenSlider.getValue()));
		freshFont();
	}

	@SuppressWarnings("deprecation")
	public void run() {
		int intMessageCounter = 0;
		int intUserTotal = 0;
		boolean isFirstLogin = true; // �ж��Ƿ�յ�½
		boolean isFound; // �ж��Ƿ��ҵ��û�
		Vector user_exit = new Vector();

		try {
			// Socket toServer;
			// toServer = new Socket(strServerIp, 1001);
			for (;;) {
				Socket toServer;
				toServer = new Socket(strServerIp, 1001);
				// ����Ϣ����������
				messobj = new Message();
				ObjectOutputStream streamtoserver = new ObjectOutputStream(
						toServer.getOutputStream());
				streamtoserver.writeObject((Message) messobj);
				// �����Է���������Ϣ
				ObjectInputStream streamfromserver = new ObjectInputStream(
						toServer.getInputStream());
				messobj = (Message) streamfromserver.readObject();
				// //////ˢ��������Ϣ�б�//////////
				if (isFirstLogin) // ����յ�½
				{
					intMessageCounter = messobj.chat.size(); // ���θ��û���½ǰ����������
					isFirstLogin = false;
				}
				if (strLoginName.equals(messobj.ti)) {
					// taUserMessage.append("ni bei ti");
					exitChatRoom();
					JOptionPane.showMessageDialog(null, strLoginName
							+ "�㱻����Ա�޳�!");

				}
				
				if (!serverMessage.equals(messobj.serverMessage)) {
					serverMessage = messobj.serverMessage;
					taUserMessage.append("[ϵͳ��Ϣ]��" + serverMessage+"\n");
				}
				// taUserMessage.setText("");
				for (int i = intMessageCounter; i < messobj.chat.size(); i++) {
					Chat temp = (Chat) messobj.chat.elementAt(i);
					// chenmin

					String emote = temp.emote;
					if (emote.equals("����")) {
						emote = "";
					} else {
						emote += "��";
					}
					String temp_message;
					if (temp.chatUser.equals(strLoginName)) {
						if (temp.chatToUser.equals(strLoginName)) {
							temp_message = "ϵͳ��ʾ�����벻Ҫ�������" + "\n";
						} else {
							if (!temp.whisper) // �������Ļ�
							{
								temp_message = "���㡿�ԡ�" + temp.chatToUser + "��"
										+ emote + "˵��" + temp.chatMessage
										+ "\n";
							} else {
								temp_message = "���㡿���Ķԡ�" + temp.chatToUser
										+ "��" + emote + "˵��" + temp.chatMessage
										+ "\n";
							}
						}
					} else {
						if (temp.chatToUser.equals(strLoginName)) {
							if (!temp.whisper) // �������Ļ�
							{
								temp_message = "��" + temp.chatUser + "���ԡ��㡿"
										+ emote + "˵��" + temp.chatMessage
										+ "\n";
							} else {
								temp_message = "��" + temp.chatUser + "�����Ķԡ��㡿"
										+ emote + "˵��" + temp.chatMessage
										+ "\n";
							}
						} else {
							if (!temp.chatUser.equals(temp.chatToUser)) // �Է�û����������
							{
								if (!temp.whisper) // �������Ļ�
								{
									temp_message = "��" + temp.chatUser + "���ԡ�"
											+ temp.chatToUser + "��" + emote
											+ "˵��" + temp.chatMessage + "\n";
								} else {
									temp_message = "";
								}
							} else {
								temp_message = "";
							}
						}
					}
					taUserMessage.append(temp_message);
					intMessageCounter++;
				}

				// //////ˢ�������û�//////////
				lstUserList.clear();
				for (int i = 0; i < messobj.userOnLine.size(); i++) {
					String User = ((Customer) messobj.userOnLine.elementAt(i)).custName;
					lstUserList.addItem(User);
				}
				Integer a = new Integer(messobj.userOnLine.size());
				lblCount.setText(a.toString());
				// ��ʾ�û����������ҵ���Ϣ
				if (messobj.userOnLine.size() > intUserTotal) {
					// chenmin
					String tempstr = ((Customer) messobj.userOnLine
							.elementAt(messobj.userOnLine.size() - 1)).custName;
					// String tempstr = messobj.userOnLine.elementAt(
					// messobj.userOnLine.size() - 1).toString();
					if (!tempstr.equals(strLoginName)) {
						taUserMessage.append("��" + tempstr + "������" + "\n");
					}
				}
				// ��ʾ�û��뿪�����ҵ���Ϣ
				// ��ʾ�û��뿪�����ҵ���Ϣ
				if (messobj.userOnLine.size() < intUserTotal) {
					for (int b = 0; b < user_exit.size(); b++) {
						isFound = false;
						for (int c = 0; c < messobj.userOnLine.size(); c++) {
							String tempstr = ((Customer) user_exit.elementAt(b)).custName;

							if (tempstr.equals(((Customer) messobj.userOnLine
									.elementAt(c)).custName)) {
								isFound = true;
								break;
							}
						}
						if (!isFound) // û�з��ָ��û�
						{
							String tempstr = ((Customer) user_exit.elementAt(b)).custName;

							if (!tempstr.equals(strLoginName)) {
								taUserMessage.append("��" + tempstr + "������"
										+ "\n");
							}
						}
					}
				}
				user_exit = messobj.userOnLine;
				intUserTotal = messobj.userOnLine.size();
				streamtoserver.close();
				streamfromserver.close();
				toServer.close();
				Thread.sleep(3000);
			}

		} catch (Exception e) {
			@SuppressWarnings("unused")
			JOptionPane jop = new JOptionPane();
			JOptionPane.showMessageDialog(null, "�������ӷ�������");
			e.printStackTrace();
			frmChat.dispose();
			// System.out.println();
			// System.out.println(e.getMessage());
		}

	} // run()����

	private void exitChatRoom() {
		// TODO �Զ����ɷ������
		exit();
	}

	// /////////������ť��Ӧ//////////////
	public void actionPerformed(ActionEvent ae) {
		Object source = (Object) ae.getSource();
		if (source.equals(btnTimer)) {
			new Clock();
		}
		if (source.equals(btnCls)) {
			clearMessage();
		}
		if (source.equals(btnExit)) {
			exit();
		}
		if (source.equals(btnSend)) {
			sendMessage();
		}
		if (source.equals(btnSave)) {
			saveMessage();
		}
		if (source.equals(lstUserList)) // ˫���б��
		{
			changeUser();
		}
	} // actionPerformed()����

	// /////////�������ڹر���Ӧ//////////////
	class Windowclose extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			exit();
		}
	}

	// "����"��ť
	public void clearMessage() {
		taUserMessage.setText("");
	}

	// "�˳�"��ť
	public void exit() {
		Exit exit = new Exit();
		exit.exitname = strLoginName;
		// �����˳���Ϣ
		try {
			Socket toServer = new Socket(strServerIp, 1001);
			// �������������Ϣ
			ObjectOutputStream outObj = new ObjectOutputStream(toServer
					.getOutputStream());
			outObj.writeObject(exit);
			outObj.close();
			toServer.close();

			frmChat.dispose();
			// this.destroy();
		} catch (Exception e) {
		}

	} // exit()����

	// "����"��ť
	public void sendMessage() {
		Chat chatobj = new Chat();
		chatobj.chatUser = strLoginName;
		chatobj.chatMessage = txtMessage.getText();
		chatobj.chatToUser = String.valueOf(cmbUser.getSelectedItem());
		chatobj.whisper = chPrivateChat.isSelected() ? true : false;
		chatobj.emote = emote.getSelectedItem().toString();
		// �������������Ϣ
		try {
			Socket toServer = new Socket(strServerIp, 1001);
			ObjectOutputStream outObj = new ObjectOutputStream(toServer
					.getOutputStream());
			outObj.writeObject(chatobj);
			txtMessage.setText(""); // ����ı���
			outObj.close();
			toServer.close();
		} catch (Exception e) {
		}
	} // sendMessage()����

	// "����"��ť
	public void saveMessage() {
		try {
			FileOutputStream fileoutput = new FileOutputStream(
					this.strLoginName + "_message.txt", true);
			String temp = taUserMessage.getText();
			// System.out.println(temp);
			fileoutput.write(temp.getBytes());
			fileoutput.close();
			JOptionPane.showMessageDialog(null, "�����¼������" + this.strLoginName
					+ "_message.txt");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// ����ѡ�û���ӵ�cmbUser��
	public void changeUser() {

		boolean key = true;
		String selected = lstUserList.getSelectedItem();
		// JOptionPane.showMessageDialog(null, selected);
		for (int i = 0; i < cmbUser.getItemCount(); i++) {
			if (selected.equals(cmbUser.getItemAt(i))) {
				key = false;
				break;
			}
		}
		if (key == true) {
			cmbUser.insertItemAt(selected, 0);
		}
		String head = getUserHead(lstUserList.getSelectedItem());
		// JOptionPane.showMessageDialog(null, head);
		cmbUser.setSelectedItem(selected);

		// chenmin
		headLabel.setIcon(new ImageIcon("face//" + head + ".JPG"));
	} // changeUser()����

	// chenmin
	protected void freshHead() {
		// TODO �Զ����ɷ������
		String head = getUserHead(cmbUser.getSelectedItem().toString());
		// JOptionPane.showMessageDialog(null, head);
		// cmbUser.setSelectedItem(selected);

		// chenmin
		headLabel.setIcon(new ImageIcon("face//" + head + ".JPG"));
	}

	// chenmin
	private String getUserHead(String selectedItem) {
		// TODO �Զ����ɷ������
		String head = "oo";
		for (int i = 0; i < messobj.userOnLine.size(); i++) {
			String User = ((Customer) messobj.userOnLine.elementAt(i)).custName;
			head = ((Customer) messobj.userOnLine.elementAt(i)).custHead;
			// System.out.println(User+":"+head);
			if (User.equals(selectedItem)) {
				break;
			}
			// lstUserList.addItem(User);
		}
		return head;
	}

	public static void main(String args[]) {
		new ChatRoom("�����û�", "127.0.0.1");
	}

}