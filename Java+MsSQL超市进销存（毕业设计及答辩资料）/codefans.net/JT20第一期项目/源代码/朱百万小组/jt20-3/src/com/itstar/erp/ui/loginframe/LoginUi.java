package com.itstar.erp.ui.loginframe;

/**
 * Fuction:login frame
 * @author zhubaiwan
 * @version 2.0 2009
 */

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import com.itstar.erp.dao.Admin;
import com.itstar.erp.dao.AdminDao;
import com.itstar.erp.dao.AdminImpl;
import com.itstar.erp.ui.listener.MouseListener;
import com.itstar.erp.ui.listener.TimeListener;
import com.itstar.erp.ui.mainframe.MainWin;

public class LoginUi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel title = null;
	private JTextField username = null;
	private JLabel backlabel = null;

	public LoginUi() {
		super();
		initialize();
		this.setResizable(false);
		this.setVisible(true);
	}

	private void initialize() {
		this.setSize(600, 500);
		this.setLocation(320, 130);
		this.setContentPane(getJContentPane());
		jContentPane.setLayout(null);
		this.setTitle("华夏英才软件工程20班第3组");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addControls();
	}

	private void addControls() {
		title = new JLabel();
		title.setBounds(new Rectangle(73, 50, 440, 60));
		title.setText("超市进销存管理系统");
		title.setFont(new Font("Dialog", Font.BOLD, 45));
		jContentPane.add(title, null);

		JLabel user = new JLabel();
		user.setText("管理员");
		user.setFont(new Font("Dialog", Font.BOLD, 24));
		user.setBounds(new Rectangle(140, 150, 130, 30));
		jContentPane.add(user, null);

		username = new JTextField(10);
		username.setFont(new Font("Dialog", Font.BOLD, 18));
		username.setBounds(new Rectangle(240, 150, 180, 30));
		jContentPane.add(username, null);

		JLabel pName = new JLabel();
		pName.setText("密   码");
		pName.setFont(new Font("Dialog", Font.BOLD, 24));
		pName.setBounds(new Rectangle(140, 220, 130, 30));
		jContentPane.add(pName, null);

		final JPasswordField Psd = new JPasswordField();
		Psd.setFont(new Font("Dialog", Font.BOLD, 18));
		Psd.setBounds(new Rectangle(240, 220, 180, 30));
		jContentPane.add(Psd, null);

		JButton bt1 = new JButton();
		bt1.setText("登陆");
		bt1.setFont(new Font("Dialog", Font.BOLD, 18));
		bt1.setBounds(new Rectangle(180, 290, 80, 40));
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if("".equals( username.getText())) {
//					JOptionPane.showMessageDialog(null, "你输入的管理员姓名为空,请重输！","错误信息", JOptionPane.ERROR_MESSAGE);
//					username.setText("");
//					Psd.setText("");
//				}
				
//				Admin admin = new Admin();
//				AdminDao adminOperate = new AdminImpl();
//				admin.setUserid(username.getText());
//				admin.setPsd(Psd.getText());
//				if(adminOperate.findAdmin(admin)) {
//				if (adminOperate.findLogin(admin)) {
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e1) {
//						e1.printStackTrace();
////					}
					new MainWin();
//					dispose();
//				} else {
//					JOptionPane.showMessageDialog(null, "你输入密码有错误,请重输！","错误信息", JOptionPane.ERROR_MESSAGE);
//					username.setText("");
//					Psd.setText("");
//					username.requestFocus();
//				}
//			}
//				else{
//					JOptionPane.showMessageDialog(null, "没有该管理员,请重输！","错误信息", JOptionPane.ERROR_MESSAGE);
//				username.setText("");
//				Psd.setText("");
//				username.requestFocus();  }
			}
		});
		
		jContentPane.add(bt1, null);

		JButton bt2 = new JButton();
		bt2.addMouseListener(new MouseListener());
		bt2.setText("退出");
		bt2.setFont(new Font("Dialog", Font.BOLD, 18));
		bt2.setBounds(new Rectangle(310, 290, 80, 40));
		jContentPane.add(bt2, null);

		JLabel clock = new JLabel();
		TimeListener m1 = new TimeListener(clock);
		Timer t1 = new Timer(1000, m1);
		t1.setInitialDelay(0);
		t1.start();
		clock.setFont(new Font("Dialog", Font.BOLD, 13));
		clock.setBounds(new Rectangle(120, 370, 350, 30));
		jContentPane.add(clock, null);

		ImageIcon img = new ImageIcon("src/com/itstar/erp/ui/loginframe/login.jpg");
		backlabel = new JLabel(img);
		backlabel.setBounds(new Rectangle(0, 0, 600, 500));

		jContentPane.add(backlabel, null);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {

			jContentPane = new JPanel();
		}
		return jContentPane;
	}

	public static void main(String[] args) {
		new LoginUi();
	}
}
