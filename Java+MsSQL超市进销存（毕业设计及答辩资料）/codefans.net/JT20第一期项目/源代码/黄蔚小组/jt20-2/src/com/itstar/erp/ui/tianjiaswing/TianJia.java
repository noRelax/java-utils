package com.itstar.erp.ui.tianjiaswing;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;

import com.itstar.erp.dao.tianjiayonghu.TianJiaUserDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.vo.user.UserBean;
import java.awt.Color;

public class TianJia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField yhm = null;
	private JPasswordField mm1 = null;
	private JPasswordField mm2 = null;
	private JButton okButton = null;
	private JButton resetButton = null;
	String user;
	UserBean userbean=new UserBean();  //  @jve:decl-index=0:
	private JComboBox jComboBox = null;
	private JLabel jLabel3 = null;
	String value="请选择";  //  @jve:decl-index=0:
	String scvalue="请选择";  //  @jve:decl-index=0:
	private JButton jButton = null;
	private JComboBox scC = null;
	/**
	 * This method initializes yhm	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getYhm() {
		if (yhm == null) {
			yhm = new JTextField();
			yhm.setBounds(new Rectangle(136, 17, 114, 22));
		}
		return yhm;
	}

	/**
	 * This method initializes mm1	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getMm1() {
		if (mm1 == null) {
			mm1 = new JPasswordField();
			mm1.setBounds(new Rectangle(135, 41, 113, 22));
		}
		return mm1;
	}

	/**
	 * This method initializes mm2	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getMm2() {
		if (mm2 == null) {
			mm2 = new JPasswordField();
			mm2.setBounds(new Rectangle(139, 75, 109, 22));
		}
		return mm2;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(39, 145, 110, 29));
			okButton.setText("确定添加");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ResultSet rs=new GetRS().getResultSet("select * from tb_user_info where userName='"+user+"'");
					try {
						while(rs.next()){
							userbean.setUserName(rs.getString(2));
							userbean.setUserPower(rs.getInt(4));
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
				
					
					String yhmt=yhm.getText().trim();
					String mm1t=mm1.getText().trim();
					String mm2t=mm2.getText().trim();
					
					ResultSet r=new GetRS().getResultSet("select userID from tb_user_info where userName='"+yhmt+"'");
					int x=0;
					try {
						if(r.next()){
							x=r.getInt(1);
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					
					if(value.equals("请选择")){
						JOptionPane.showMessageDialog(null, "请选择权限 ");
					}else if(yhmt.equals("")){
						JOptionPane.showMessageDialog(null, "用户名不能为空 ");
					}else if(mm1t.equals("")||mm2t.equals("")){
						JOptionPane.showMessageDialog(null, "密码不能为空  ");
					}else if(!mm1t.equals(mm2t)){
						JOptionPane.showMessageDialog(null, "两次输入的密码不相同 ");
					}else if(x!=0){
						JOptionPane.showMessageDialog(null, "已存在此用户  ");
					}else{
						int power=getpower(value);
						UserBean bean=new UserBean();
						bean.setUserName(yhmt);     //用户名
						bean.setUserPassWord(mm1t);  //用户密码
						bean.setUserPower(power);          //用户权限
						
						new TianJiaUserDaoImpl().insert(bean);
						
						JOptionPane.showMessageDialog(null, "添加用户 成功  ");
						
						
					}
				}
			});
		}
		return okButton;
	}

	/**
	 * This method initializes resetButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getResetButton() {
		if (resetButton == null) {
			resetButton = new JButton();
			resetButton.setBounds(new Rectangle(165, 144, 100, 28));
			resetButton.setText("重置");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					yhm.setText("");
					mm1.setText("");
					mm2.setText("");
					yhm.requestFocus(true);
					jComboBox.setSelectedIndex(0);
				}
			});
		}
		return resetButton;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			
			Vector v=new Vector();
			v.add("请选择");
			v.add("管理员");
			v.add("普通用户");
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			jComboBox = new JComboBox(model);
			jComboBox.setBounds(new Rectangle(144, 106, 102, 27));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						value = jComboBox.getSelectedItem().toString();
						
						ResultSet rs=new GetRS().getResultSet("select * from tb_user_info where userName='"+user+"'");
						try {
							while(rs.next()){
								userbean.setUserName(rs.getString(2));
								userbean.setUserPower(rs.getInt(4));
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						
						if(userbean.getUserPower()==0 && !value.equals("请选择")){
							JOptionPane.showMessageDialog(null, "普通用户 无权使用此功能 ");
							jComboBox.setSelectedIndex(0);
						}
						if(userbean.getUserPower()==2 && value.equals("管理员")){
							JOptionPane.showMessageDialog(null, "只有超级管理员才可以添加管理员  ");
							jComboBox.setSelectedIndex(0);
						}
					}
				}
			});
		}
		return jComboBox;
	}

	/**
	 * @param args
	 */
	

	/**
	 * This is the default constructor
	 */
	public TianJia(String user) {
		super();
		initialize();
		this.user=user;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(482, 244);
		this.setContentPane(getJContentPane());
		this.setTitle("用户管理");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(43, 112, 78, 18));
			jLabel3.setText("         权限");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(43, 75, 76, 22));
			jLabel2.setText("   确认密码");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(43, 45, 77, 18));
			jLabel1.setText("     密  码");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(41, 17, 86, 18));
			jLabel.setText("     用 户 名");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getYhm(), null);
			jContentPane.add(getMm1(), null);
			jContentPane.add(getMm2(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getResetButton(), null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getScC(), null);
		}
		return jContentPane;
	}
	public int getpower(String value){
		int i=0;
		if(value.equals("管理员")){
			i=2;
		}
		if(value.equals("普通用户")){
			i=0;
		}
		return i;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(347, 149, 101, 24));
			jButton.setText("删除用户");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(scvalue.equals("请选择")){
						JOptionPane.showMessageDialog(null, "请选择要删除的用户  ");
					}else{
						ResultSet rs=new GetRS().getResultSet("select * from tb_user_info where userName='"+user+"'");
						try {
							while(rs.next()){
								userbean.setUserName(rs.getString(2));
								userbean.setUserPower(rs.getInt(4));
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						
						
						ResultSet s=new GetRS().getResultSet("select * from tb_user_info where userName='"+scvalue+"'");
						UserBean b=new UserBean();
						try {
							while(s.next()){
								b.setUserName(s.getString(2));
								b.setUserPower(s.getInt(4));
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						
						
						if(userbean.getUserPower()==1 && b.getUserPower()!=1){
							new TianJiaUserDaoImpl().delete(b.getUserID());
							JOptionPane.showMessageDialog(null, "  删除成功  ");
						}else if(b.getUserPower()==1 ){
							JOptionPane.showMessageDialog(null, "超级管理员不能被删除  ");
						}else if(userbean.getUserPower()==b.getUserPower()){
							JOptionPane.showMessageDialog(null, "权限级别一样  不能互相删除  ");
						}else{
							new TianJiaUserDaoImpl().delete(b.getUserID());
							JOptionPane.showMessageDialog(null, "  删除成功  ");
						}
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes scC	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getScC() {
		if (scC == null) {
			ResultSet rs=new GetRS().getResultSet("select userName,userPower from tb_user_info ");
			Vector v=new Vector();
			v.add("请选择");
			try {
				while(rs.next()){
					String s1=rs.getString(1).trim();
					v.add(rs.getString(1).trim());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			scC = new JComboBox(model);
			scC.setBounds(new Rectangle(341, 108, 114, 27));
			scC.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						scvalue = scC.getSelectedItem().toString();
					}
				}
			});
		}
		return scC;
	}
}  //  @jve:decl-index=0:visual-constraint="297,21"
