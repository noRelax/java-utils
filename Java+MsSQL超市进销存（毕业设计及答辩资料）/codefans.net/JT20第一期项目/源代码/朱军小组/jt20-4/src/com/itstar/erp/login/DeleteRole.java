/**
 * [SQLServer 2000 Driver for JDBC]
 * [SQLServer]在将 varchar 值 'admin' 转换成数据类型 int 时失败。
 * 用户名或密码为字母报的错，但是用数字不会！！！
 */
package com.itstar.erp.login;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.itstar.dao.Connect;

public class DeleteRole extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="12,59"

	private JLabel jLabel = null;

	private JTextField jTextField = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JLabel jLabel1 = null;

	/**
	 * This is the default constructor
	 */
	public DeleteRole() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(461, 298);
		Toolkit tl = Toolkit.getDefaultToolkit();
		Dimension d = tl.getScreenSize();
		int width = (int)d.getWidth();
		int height = (int)d.getHeight();
		this.setLocation((width-461)/2,(height-298)/2);
		this.setContentPane(getJContentPane());
		this.setTitle("删除用户");
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
			jLabel1.setBounds(new Rectangle(17, 239, 311, 18));
			jLabel1.setText("");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(74, 40, 54, 28));
			jLabel.setText("用户名：");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setSize(new Dimension(167, 10));
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(jLabel1, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(147, 43, 155, 22));
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
			jButton.setBounds(new Rectangle(107, 170, 67, 27));
			jButton.setText("删除");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String str = jTextField.getText().trim();
					if(str.equals("")){
						jLabel1.setText("用户名不能为空");
						jTextField.setText("");
						jTextField.requestFocus();
					}
					else if(str.equals("admin")){
						jTextField.setText("");
						jLabel1.setText("");
						JOptionPane.showMessageDialog(DeleteRole.this, "不能删除管理员","ERROR",JOptionPane.ERROR_MESSAGE);
						
					}
					else{
						ResultSet rs = null;
						boolean flag = false;
						try{
							String s = "select username from Login";
							rs = Connect.stmt.executeQuery(s);
							while(rs.next()){
								String st = rs.getString(1);
									if(str.equals(st)){
										String str1 = "delete from Login where username='"+str+"'";
										Connect.stmt.executeUpdate(str1);
										
										jLabel1.setText("删除成功！");
										//Connect.conn.commit();
										jTextField.setText("");
										jTextField.requestFocus();
										break;
									}else{
										jTextField.setText("");
										jTextField.requestFocus();
										jLabel1.setText("用户名不存在");
									}
								}
							Connect.conn.commit();
								 
							} catch(SQLException e1){
								e1.printStackTrace();
							} finally{
								try{
									if(rs != null){
										rs.close();
										rs = null;
									}
								}catch(SQLException e1){
									e1.printStackTrace();
								}
								
							}
							
							/*if(flag){
								String str1 = "delete from Login where username="+str;
								Connect.stmt.executeUpdate(str1);
								
								jLabel1.setText("删除成功！");
								Connect.conn.commit();
								jTextField.setText("");
								jTextField.requestFocus();
							}else{
								jTextField.requestFocus();
								jLabel1.setText("用户名不存在");
							}*/
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
			jButton1.setBounds(new Rectangle(277, 166, 66, 27));
			jButton1.setText("取消");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTextField.setText("");
					jTextField.requestFocus();}
			});
		}
		return jButton1;
	}

}  //  @jve:decl-index=0:visual-constraint="101,29"
