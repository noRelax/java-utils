package com.itstar.erp.ui.jichuxinxi.yewuyuanswing;

import javax.swing.SwingUtilities;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.itstar.erp.dao.baiscinfo.dao.YwyDao;
import com.itstar.erp.dao.baiscinfo.impl.YwyDaoImpl;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.util.GetRS;

import javax.swing.JTextField;

public class YwyShanChu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox ywyshanchujComboBox = null;
	private JButton okButton = null;
	DefaultComboBoxModel model;
	String value="";
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel3 = null;
	private JTextField addr = null;
	private JTextField phone = null;
	/**
	 * This method initializes ywyshanchujComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getYwyshanchujComboBox() {
		if (ywyshanchujComboBox == null) {
			Vector v=new Vector();
			v.add("");
			ResultSet rs=getResultSet();
			try {
				while(rs.next()){
					String ywyName=rs.getString(2);
					v.add(ywyName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			model= new DefaultComboBoxModel(v);
			ywyshanchujComboBox = new JComboBox(model);
			ywyshanchujComboBox.setBounds(new Rectangle(238, 38, 189, 41));
			ywyshanchujComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					value=ywyshanchujComboBox.getSelectedItem().toString();
					if(value!=null){
						ResultSet r=new GetRS().getResultSet("select ywyPhone,ywyAddress from tb_yewuyuan_info where ywyName='"+value+"'");
						String yphone = null;
						String yaddr = null;
						try {
							if(r.next()){
								try {
									yphone=r.getString(1);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								try {
									yaddr=r.getString(2);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						phone.setText(yphone);
						addr.setText(yaddr);
						
					}
				}
			});
		}
		return ywyshanchujComboBox;
	}
	
	private static ResultSet getResultSet(){
		Connection conn=new DBConnection().getConnection();
		Statement s;
		ResultSet rs = null;
		try {
			s=conn.createStatement();
			rs=s.executeQuery("select * from tb_yewuyuan_info");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
		return rs;
		
	}
	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(333, 202, 112, 35));
			okButton.setText("确定删除");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(value.equals("")){
						JOptionPane.showMessageDialog(okButton, "请选择要删除的业务员名称");					
						}else{
					YwyDao ydi=new YwyDaoImpl();
					ydi.delete(value);
					JOptionPane.showMessageDialog(okButton, "删除成功");
					dispose();
					
					
					YwyShanChu ywyshanchu=new YwyShanChu();
					ywyshanchu.setVisible(true);
					ywyshanchu.setSize(500,300);
					ywyshanchu.setLocationRelativeTo(null);
				}}
			});
		}
		return okButton;
	}

	/**
	 * This method initializes addr	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAddr() {
		if (addr == null) {
			addr = new JTextField();
			addr.setBounds(new Rectangle(189, 152, 183, 22));
		}
		return addr;
	}

	/**
	 * This method initializes phone	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPhone() {
		if (phone == null) {
			phone = new JTextField();
			phone.setBounds(new Rectangle(190, 108, 177, 22));
		}
		return phone;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				YwyShanChu thisClass = new YwyShanChu();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public YwyShanChu() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(500,300);
		this.setContentPane(getJContentPane());
		this.setTitle("业务员资料删除");
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
			jLabel3.setBounds(new Rectangle(101, 154, 75, 18));
			jLabel3.setText("    地址");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(94, 106, 80, 27));
			jLabel1.setText("     电话");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(22, 39, 197, 42));
			jLabel.setText("   请输入要删除的业务员名称");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getYwyshanchujComboBox(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getAddr(), null);
			jContentPane.add(getPhone(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="240,19"
