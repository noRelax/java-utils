package com.itstar.erp.manager;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.itstar.erp.dao.Admin;
import com.itstar.erp.dao.AdminDao;
import com.itstar.erp.dao.AdminImpl;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AddManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField Name = null;
	private JLabel jLabel1 = null;
	private JPasswordField psd = null;
	private JLabel jLabel2 = null;
	private JTextField age = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel3 = null;
	private JTextField Email = null;
	private JLabel jLabel4 = null;
	private JTextField Phone = null;
	private JButton Sumit = null;
	private JButton Rewrite = null;
	private JLabel piont = null;
	private JLabel piont1 = null;
	private JLabel piont11 = null;

	/**
	 * This is the default constructor
	 */
	public AddManager() {
		super();
		initialize();
		this.setResizable(false);
		this.setVisible(true);
		initialize();
	}

	private void initialize() {
		this.setSize(390, 342);
		this.setLocation(377, 150);
		this.setContentPane(getJContentPane());
		this.setTitle("添加管理员");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			piont11 = new JLabel();
			piont11.setBounds(new Rectangle(304, 141, 21, 19));
			piont11.setForeground(new Color(228, 40, 40));
			piont11.setHorizontalAlignment(SwingConstants.CENTER);
			piont11.setText("*");
			piont11.setFont(new Font("Dialog", Font.BOLD, 18));
			piont1 = new JLabel();
			piont1.setBounds(new Rectangle(297, 63, 20, 18));
			piont1.setFont(new Font("Dialog", Font.BOLD, 18));
			piont1.setForeground(new Color(228, 40, 40));
			piont1.setHorizontalAlignment(SwingConstants.CENTER);
			piont1.setText("*");
			piont = new JLabel();
			piont.setBounds(new Rectangle(297, 22, 22, 18));
			piont.setHorizontalAlignment(SwingConstants.CENTER);
			piont.setForeground(new Color(255, 0, 51));
			piont.setFont(new Font("Dialog", Font.BOLD, 18));
			piont.setText("*");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(70, 180, 77, 24));
			jLabel4.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel4.setText("电话号码");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(70, 140, 81, 24));
			jLabel3.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel3.setText("电子邮件");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(70, 100, 59, 25));
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel2.setText("年  龄");

			jLabel5 = new JLabel("**只能输入少于三位数字**");
			jLabel5.setBounds(new Rectangle(230, 100, 111, 25));
			jLabel5.setText("    *  年龄为数字");
			jLabel5.setForeground(new Color(218, 38, 38));
			jLabel5.setFont(new Font("Dialog", Font.BOLD, 12));

			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(70, 60, 56, 25));
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel1.setText("密  码");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(70, 20, 57, 28));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel.setText("姓   名");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJJPasswordField(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJTextField2(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getJTextField3(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getJTextField4(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(piont, null);
			jContentPane.add(piont1, null);
			jContentPane.add(piont11, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (Name == null) {
			Name = new JTextField();
			Name.setBounds(new Rectangle(166, 20, 128, 25));
		}
		return Name;
	}

	/**
	 * This method initializes jTextField1
	 * 
	 * @param <getJTextField>
	 * 
	 * @return javax.swing.JTextField
	 */
	private JPasswordField getJJPasswordField() {
		if (psd == null) {
			psd = new JPasswordField();
			psd.setBounds(new Rectangle(166, 60, 128, 25));
		}
		return psd;
	}

	/**
	 * This method initializes jTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField2() {
		if (age == null) {
			age = new JTextField();
			age.setBounds(new Rectangle(166, 100, 52, 22));
		}
		return age;
	}

	/**
	 * This method initializes jTextField3
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField3() {
		if (Email == null) {
			Email = new JTextField();
			Email.setBounds(new Rectangle(166, 140, 134, 23));
		}
		return Email;
	}

	/**
	 * This method initializes jTextField4
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField4() {
		if (Phone == null) {
			Phone = new JTextField();
			Phone.setBounds(new Rectangle(166, 180, 134, 22));
			Phone.setText("");
		}
		return Phone;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (Sumit == null) {
			Sumit = new JButton();
			Sumit.setBounds(new Rectangle(109, 227, 60, 33));
			Sumit.setText("添加");

			Sumit.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Admin admin = new Admin();
					if (!"".equals(Name.getText())) {
						admin.setUserid(Name.getText());
					if(!"".equals(psd.getText())){
						admin.setPsd(psd.getText());
						if(!"".equals(age.getText())){
							for(int i=0;i<age.getText().length();i++)
							{  if(age.getText().charAt(i)<'0' || age.getText().charAt(i)>'9'){
								JOptionPane.showMessageDialog(null, "年龄不能为字符!");
								age.setText("");
								age.requestFocus();
								return;
							      }
								}
							int num=Integer.valueOf(age.getText());
							admin.setSex(num);
							
							if("".equals(Email.getText())){JOptionPane.showMessageDialog(jContentPane, "电子邮件格不能为空!");
							Email.requestFocus();
							return; }
							else { int count1=0; int count2=0;
							for(int i=0;i<Email.getText().length();i++){ 
								if(Email.getText().charAt(i)=='@') count1++;
								if(Email.getText().charAt(i)=='.') count2++;
							}
							if(count1==1 && count2==1 && Email.getText().indexOf('.')>Email.getText().indexOf('@')+1
									&& Email.getText().indexOf('.')<Email.getText().length()-1)
							{  admin.setAEmail(Email.getText());  }
							else{
								JOptionPane.showMessageDialog(jContentPane, "电子邮件格式不正确!");
								Email.setText("");
								Email.requestFocus();
								return;
							}
							}
							
							admin.setAPhone(Phone.getText());
							AdminDao adminOperate = new AdminImpl();
							
							if (adminOperate.findAdmin(admin) == true) {
								JOptionPane.showMessageDialog(jContentPane, "用户已存在!");
								Name.setText("");
								psd.setText("");
								Email.setText("");
								Phone.setText("");
								Name.requestFocus(); }
							else {
								adminOperate.addAdmin(admin);
								JOptionPane.showMessageDialog(jContentPane,
										"用户已经添加成功,继续添加!");
								dispose();
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "年龄不能为空!");
						}
					}	
						   else {
							JOptionPane.showMessageDialog(null, "密码不能为空!");}
					
					} else {
						JOptionPane.showMessageDialog(null, "管理员名字不能为空!");
					}
				}
			});
		}
		return Sumit;
	}

	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1() {
		if (Rewrite == null) {
			Rewrite = new JButton();
			Rewrite.setBounds(new Rectangle(209, 227, 60, 33));
			Rewrite.setText("重写");

			Rewrite.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Name.setText("");
					age.setText("");
					psd.setText("");
					Email.setText("");
					Phone.setText("");
				}
			});
		}

		return Rewrite;
	}
}
