package com.itstar.erp.manager;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.itstar.erp.dao.Admin;
import com.itstar.erp.dao.AdminImpl;

public class Modify extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel old = null;
	private JPasswordField oldField = null;
	private JLabel rspw = null;
	private JPasswordField rpswField = null;
	private JLabel ok = null;
	private JPasswordField okField = null;
	private JButton modify = null;
	private JButton btCanel = null;
    private Admin admin;  //  @jve:decl-index=0:
	/**
	 * This is the default constructor
	 */
	public Modify(Admin admin) {
		 initialize();
		 this.admin=admin;
		System.out.println("----000909---"+this.getAdmin().getUserid());
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	private void initialize() {
		this.setSize(384, 234);
		this.setLocation(377, 220);
		this.setContentPane(getJContentPane());
		this.setTitle("密码修改");
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			ok = new JLabel();
			ok.setBounds(new Rectangle(49, 103, 84, 24));
			ok.setFont(new Font("Dialog", Font.BOLD, 14));
			ok.setText("确认新密码");
			rspw = new JLabel();
			rspw.setBounds(new Rectangle(65, 63, 52, 25));
			rspw.setFont(new Font("Dialog", Font.BOLD, 14));
			rspw.setText("新密码");
			old = new JLabel();
			old.setBounds(new Rectangle(63, 26, 60, 24));
			old.setFont(new Font("Dialog", Font.BOLD, 14));
			old.setText("旧密码");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(old, null);
			jContentPane.add(getOldField(), null);
			jContentPane.add(rspw, null);
			jContentPane.add(getRpswField(), null);
			jContentPane.add(ok, null);
			jContentPane.add(getOkField(), null);
			jContentPane.add(getModify(), null);
			jContentPane.add(getBtCanel(), null);
		}
		return jContentPane;
	}

	
	private JPasswordField getOldField() {
		if (oldField == null) {
			oldField = new JPasswordField();
			oldField.setBounds(new Rectangle(145, 27, 125, 22));
		}
		return oldField;
	}

	/**
	 * This method initializes rpswField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getRpswField() {
		if (rpswField == null) {
			rpswField = new JPasswordField();
			rpswField.setBounds(new Rectangle(147, 63, 125, 22));
		}
		return rpswField;
	}

	/**
	 * This method initializes okField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getOkField() {
		if (okField == null) {
			okField = new JPasswordField();
			okField.setBounds(new Rectangle(150, 103, 125, 22));
		}
		return okField;
	}

	/**
	 * This method initializes modify	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModify() {
		if (modify == null) {
			modify = new JButton();
			modify.setBounds(new Rectangle(88, 148, 62, 21));
			modify.setText("修改");
			modify.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String str=getAdmin().getPsd();
					System.out.println("----------"+str);
					if("".equals(oldField.getText()))
					{ JOptionPane.showMessageDialog(null,"输入的旧密码为空!" ); }
					else if("".equals(rpswField.getText()))
					{  JOptionPane.showMessageDialog(null,"输入的新密码为空!" ); }
					else if("".equals(okField.getText()))
				    {  JOptionPane.showMessageDialog(null,"输入的确认密码为空!" ); 
					   oldField.setText("");
					   rpswField.setText("");
					   okField.setText("");
					   }
				   else {
					     System.out.println(oldField.getText());
					     System.out.println(getAdmin().getPsd());
					     if(!oldField.getText().equals(getAdmin().getPsd())){
					    	 JOptionPane.showMessageDialog(null, "输入的旧密码不正确!");
					    	   oldField.setText("");
							   rpswField.setText("");
							   okField.setText("");
							   oldField.requestFocus();
					     }
					     else  if(rpswField.getText().equals(oldField.getText())){
							   JOptionPane.showMessageDialog(null, "输入的不是新密码!");
							   oldField.setText("");
							   rpswField.setText("");
							   okField.setText("");
							   oldField.requestFocus();
						   }
						   else if(!(rpswField.getText().equals(okField.getText()))){
							   JOptionPane.showMessageDialog(null, "新密码和确认密码不一致!");
							   oldField.setText("");
							   rpswField.setText("");
							   okField.setText("");
							   oldField.requestFocus();
						   }
						   else{ Admin adminNew=getAdmin();
						   String strPsdNew=rpswField.getText();
						   adminNew.setPsd(strPsdNew);
					   new AdminImpl().modifyAdmin(adminNew);
					   JOptionPane.showMessageDialog(null,"密码修改成功!");
					   dispose();
					   }
					  }
				   }
			});
		}
		return modify;
	}

	/**
	 * This method initializes btCanel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtCanel() {
		if (btCanel == null) {
			btCanel = new JButton();
			btCanel.setBounds(new Rectangle(202, 148, 61, 23));
			btCanel.setText("取消");
			btCanel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				oldField.setText("");
				rpswField.setText("");
				okField.setText("");
				oldField.requestFocus();
				}
			});
		}
		return btCanel;
	}
}  
