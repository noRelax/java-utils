package com.itstar.system.swing;
//download by http://www.codefans.net
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JPasswordField;

import com.itstar.system.domainimple.UserDomainimple;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Point;
import javax.swing.WindowConstants;

public class UpdatePwd extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JPasswordField jPasswordField = null;

	private JPasswordField jPasswordField1 = null;

	private JPasswordField jPasswordField2 = null;

	private UserDomainimple ud=null;

	private JComboBox jComboBox = null;

	private JButton jButton = null;

	private JButton jButton1 = null;
	
	private String username=null;
	
	private String upassword=null;

	/**
	 * This is the default constructor
	 */
	public UpdatePwd() {
		super();
		ud=new UserDomainimple();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(377, 260);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocation(new Point(450, 250));
		this.setContentPane(getJContentPane());
		this.setTitle("更改密码");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(30, 155, 70, 30));
			jLabel3.setText("确认密码:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(30, 110, 70, 30));
			jLabel2.setText("新密码:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(30, 65, 70, 30));
			jLabel1.setText("旧密码:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(30, 20, 70, 30));
			jLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel.setText("用户：");
			jLabel1.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel2.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel3.setHorizontalAlignment(SwingConstants.TRAILING);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getJPasswordField(), null);
			jContentPane.add(getJPasswordField1(), null);
			jContentPane.add(getJPasswordField2(), null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(new Rectangle(130, 65, 150, 30));
		}
		return jPasswordField;
	}

	/**
	 * This method initializes jPasswordField1	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordField1() {
		if (jPasswordField1 == null) {
			jPasswordField1 = new JPasswordField();
			jPasswordField1.setBounds(new Rectangle(130, 110, 150, 30));
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
			jPasswordField2.setBounds(new Rectangle(130, 155, 150, 30));
		}
		return jPasswordField2;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox(ud.queryUser());
			jComboBox.setBounds(new Rectangle(130, 22, 150, 30));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					username=e.getItem().toString();
				}
			});
		}
		return jComboBox;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton("更改");
			jButton.setBounds(new Rectangle(60, 192, 88, 30));
			upassword=jPasswordField1.getText();
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (!upassword.equals(jPasswordField2.getText())) {
						JOptionPane.showMessageDialog(null, "两次密码输入不相同",
								"错误", JOptionPane.WARNING_MESSAGE);
					}else{
						if(!ud.isExist(username, jPasswordField.getText())){
							JOptionPane.showMessageDialog(null, "用户名或密码有误",
									"Fail", JOptionPane.WARNING_MESSAGE);
						}else{							
							if(ud.updatepwd(username, upassword))
								JOptionPane.showMessageDialog(null, "修改成功", "OK",
										JOptionPane.PLAIN_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, "修改失败",
										"Fail", JOptionPane.ERROR_MESSAGE);
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
			jButton1 = new JButton("关闭");
			jButton1.setBounds(new Rectangle(200, 192, 88, 30));
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
				}
			});
		}
		return jButton1;
	}

}  //  @jve:decl-index=0:visual-constraint="218,17"
