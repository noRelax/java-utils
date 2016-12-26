package com.itstar.erp.login;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.itstar.dao.Connect;

public class AddRole extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JPasswordField jPasswordField1 = null;

	private JPasswordField jPasswordField2 = null;

	private JLabel jLabel3 = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JTextField jTextField = null;
	private ResultSet rs = null;

	/**
	 * This is the default constructor
	 */
	public AddRole() {
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
		this.setSize(370, 313);
		Toolkit tl = Toolkit.getDefaultToolkit();
		Dimension d = tl.getScreenSize();
		int width = (int)d.getWidth();
		int height = (int)d.getHeight();
		this.setLocation((width-370)/2,(height-313)/2);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(12, 256, 250, 18));
			jLabel3.setText("");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(44, 149, 59, 18));
			jLabel2.setText("确认密码");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(49, 87, 47, 25));
			jLabel1.setText("密     码");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(47, 31, 51, 24));
			jLabel.setText("用 户 名 ");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJPasswordField1(), null);
			jContentPane.add(getJPasswordField2(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJTextField(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPasswordField1	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordField1() {
		if (jPasswordField1 == null) {
			jPasswordField1 = new JPasswordField();
			jPasswordField1.setBounds(new Rectangle(131, 91, 156, 22));
		}
		return jPasswordField1;
	}

	/**
	 * This method initializes jPasswordField2	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordField2() {
		if (jPasswordField2 == null) {
			jPasswordField2 = new JPasswordField();
			jPasswordField2.setBounds(new Rectangle(133, 149, 154, 22));
		}
		return jPasswordField2;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(268, 193, 65, 20));
			jButton.setText("添加");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String user = jTextField.getText().trim();
					String pwd = jPasswordField1.getText().trim();
					String pwd1 = jPasswordField2.getText().trim();
					boolean flag = false;

					if (user.equals("")) {
						jLabel3.setText("用户名不能为空");
						jTextField.requestFocus();
					} else {
						if (pwd.equals("")) {
							jLabel3.setText("密码不能为空");
							jPasswordField1.requestFocus();
						} else {
							try {
								String sql = "select username from Login";
								rs = Connect.stmt.executeQuery(sql);
								while (rs.next()) {
									String username = rs.getString(1);
									if (user.equals(username)) {
										flag = true;
										break;
									} else {

									}
								}
								Connect.conn.commit();
								if (flag) {
									jLabel3.setText("用户名已存在");
									jTextField.setText("");
									jPasswordField1.setText("");
									jPasswordField2.setText("");
									jTextField.requestFocus();
								} else {
									if (!pwd.equals(pwd1)) {
										jLabel3.setText("密码不相同，请重新输入");
										jPasswordField1.setText("");
										jPasswordField2.setText("");
										jPasswordField1.requestFocus();
									} else {
										String sql2 = "insert into Login values('"
												+ user
												+ "',"
												+ "'"
												+ pwd
												+ "')";
										Connect.stmt.executeUpdate(sql2);
										Connect.conn.commit();
										jLabel3.setText("插入成功");
										jTextField.setText("");
										jPasswordField1.setText("");
										jPasswordField2.setText("");
										jTextField.requestFocus();
									}
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

						}

					}
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
			jButton1.setBounds(new Rectangle(268, 228, 67, 22));
			jButton1.setText("取消");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jPasswordField1.setText("");
					jPasswordField2.setText("");
					jTextField.setText("");
					jTextField.requestFocus();
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(131, 33, 153, 22));
		}
		return jTextField;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
