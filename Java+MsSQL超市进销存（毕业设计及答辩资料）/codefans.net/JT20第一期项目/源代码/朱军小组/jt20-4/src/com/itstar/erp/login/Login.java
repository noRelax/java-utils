package com.itstar.erp.login;

//import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.itstar.MainWindow2;
import com.itstar.dao.Connect;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JLabel jLabel = null;

	private JTextField jTextField = null;

	private JLabel jLabel1 = null;
	
	private JLabel jLabel2 = null;

	private JPasswordField jTextField1 = null;
	
	private String username = null;
	public String getUsername() {
		return username;
	}
/*
	public String getPassword() {
		return password;
	}

	private String password = null;
	*/
	private ResultSet rs = null;
	public final static int HEIGHT = 381;
	public final static int WIDHT = 271;

	/**
	 * This is the default constructor
	 */
	public Login() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		//System.out.println(width+"  "+height);
		this.setSize(381, 271);
		this.setResizable(false);
		Toolkit tl = Toolkit.getDefaultToolkit();
		Dimension d = tl.getScreenSize();
		int width = (int)d.getWidth();
		int height = (int)d.getHeight();
		this.setLocation((width-271)/2,(height-381)/2);
		
		this.setContentPane(getJContentPane());
		this.setTitle("进销存登陆界面");
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(76, 98, 43, 22));
			jLabel1.setText("密  码");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(69, 45, 47, 24));
			jLabel.setText("用户名");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(30, 220, 201, 20));
			jContentPane = new JPanel();
			//jLabel2.setText("用户名或密码错误，请重新输入");
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJTextField1(), null);
			jContentPane.add(jLabel2,null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(83, 178, 67, 23));
			jButton.setText("登陆");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Connect.getConnect();
					boolean flag = false;
					String user = jTextField.getText();
					String password = jTextField1.getText();
					try{
						String sql = "select * from login";
						rs = Connect.stmt.executeQuery(sql);
						//Connect.conn.commit();
						//System.out.println("调用成功");
						while(rs.next()){
							String str1 = rs.getString("username");
							String str2 = rs.getString("password");
							if(str1.equals(user) && str2.equals(password)){
								flag = true;
								username = str1;
								password = str2;
								break;
							}else{
								
							}
						}
						Connect.conn.commit();
						if(flag){
							//System.out.println("true");
							new MainWindow2(Login.this).setVisible(true);
							//setVisible(false);
							dispose();
						}else{
							jLabel2.setText("用户名或密码错误，请重新输入");
							jTextField.setText("");
							jTextField1.setText("");
							jTextField.requestFocus();
						}
							
					}catch(SQLException e1){
						e1.printStackTrace();
					}finally{
						try{
							if(rs != null){
								rs.close();
								rs = null;
							}
						  }catch(SQLException e2){
							e2.printStackTrace();
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
			jButton1.setBounds(new Rectangle(206, 177, 62, 23));
			jButton1.setText("取消");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTextField.setText("");
					jTextField1.setText("");
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
			jTextField.setBounds(new Rectangle(131, 45, 146, 22));
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JPasswordField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JPasswordField();
			jTextField1.setBounds(new Rectangle(132, 97, 145, 22));
		}
		return jTextField1;
	}
	public static void main(String[] args){
		Login l = new Login();
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}