package com.itstar.system.swing;
//download by http://www.codefans.net
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import com.itstar.system.domainimple.UserDomainimple;
import java.awt.Point;

public class Login extends JFrame{

	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JTextField jTextField = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JButton jButton2 = null;

	private JPasswordField jPasswordField = null;
	
	private UserDomainimple udomain=null;  //  @jve:decl-index=0:
	
	private SystemMain stm=null;
	/**
	 * This is the default constructor
	 */
	public Login() {
		super();
		initialize();
		this.setVisible(true);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(44, 151, 71, 37));
		jLabel2.setText("密码：");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(44, 97, 71, 37));
		jLabel1.setText("用户名：");
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(60, 17, 305, 54));
		jLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setText("进销存管理系统");
		this.setLayout(null);
		this.setTitle("进销存管理系统");
		this.setPreferredSize(new Dimension(400, 300));
		this.setResizable(false);
		this.setSize(new Dimension(420, 300));
		this.setLocation(new Point(400, 300));
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(jLabel2, null);
		this.add(getJTextField(), null);
		this.add(getJButton(), null);
		this.add(getJButton1(), null);
		this.add(getJButton2(), null);
		this.add(getJPasswordField(), null);

	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(145, 97, 150, 33));
		}
		return jTextField;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(68, 206, 80, 32));
			jButton.setText("登陆");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					String username=jTextField.getText();
					String upassword=jPasswordField.getText();
					udomain=new UserDomainimple();
					if(udomain.isExist(username, upassword)){
						JOptionPane.showMessageDialog(null, "登陆成功", "OK",
								JOptionPane.PLAIN_MESSAGE);
						stm=	new SystemMain();
						stm.setVisible(true);
						setVisible(false);
					}
					else
						JOptionPane.showMessageDialog(null, "用户名或密码有误",
								"Fail", JOptionPane.WARNING_MESSAGE);
				}
			});

		}
		return jButton;
	}

	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(168, 206, 80, 32));
			jButton1.setText("重置");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()"); // TODO
					// Auto-generated
					// Event stub
					// mouseClicked()
					jTextField.setText("");
					jPasswordField.setText("");
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(268, 206, 80, 32));
			jButton2.setText("退出");
			jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()"); // TODO
					// Auto-generated
					// Event stub
					// mouseClicked()
					System.exit(0);
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jPasswordField
	 * 
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(new Rectangle(145, 151, 150, 33));

			jPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						String username=jTextField.getText();
						String upassword=jPasswordField.getPassword().toString();
						udomain=new UserDomainimple();
						if(udomain.isExist(username, upassword)){
							JOptionPane.showMessageDialog(null, "登陆成功", "OK",
									JOptionPane.PLAIN_MESSAGE);
							stm=	new SystemMain();
							stm.setVisible(true);
							setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, "用户名或密码有误",
									"Fail", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
		return jPasswordField;
	}
	public static void main(String args[]){
		new Login();
	}
} // @jve:decl-index=0:visual-constraint="159,47"
