package com.itstar.erp.ui.jichuxinxi.kehuswing;

import javax.swing.SwingUtilities;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComboBox;

import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.itstar.erp.dao.baiscinfo.dao.KeHuDao;
import com.itstar.erp.dao.baiscinfo.impl.KeHuDaoImpl;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.util.GetRS;

public class KeHuShanChu extends JFrame {
	
			
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox kehunamejComboBox = null;
	private JButton okButton = null;
	DefaultComboBoxModel model;
	String value="";
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField bh = null;
	private JTextField phone = null;
	private JTextField addr = null;
	/**
	 * This method initializes kehunamejComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getKehunamejComboBox() {
		if (kehunamejComboBox == null) {
			Vector v=new Vector();
			v.add("");
			ResultSet rs=getResultSet();
			try {
				while(rs.next()){
					String kehuName=rs.getString(2).trim();
					v.add(kehuName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			model= new DefaultComboBoxModel(v);
			kehunamejComboBox = new JComboBox(model);
			kehunamejComboBox.setBounds(new Rectangle(216, 33, 178, 35));
			kehunamejComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						value=kehunamejComboBox.getSelectedItem().toString();
						System.out.println(value);
						if(!value.equals("")){
							ResultSet yz=new GetRS().getResultSet("select * from tb_kehu_info where kehuName='"+value+"'");
							try {
								if(yz.next()){
									bh.setText("kehu"+(1000+yz.getInt(1)));
									addr.setText(yz.getString(3));
									phone.setText(yz.getString(4));
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						if(value.equals("")){
							bh.setText("");
							addr.setText("");
							phone.setText("");
						}
					}}
			});
		}
		return kehunamejComboBox;
	}
	private static ResultSet getResultSet(){
		Connection conn=new DBConnection().getConnection();
		Statement s;
		ResultSet rs = null;
		try {
			s=conn.createStatement();
			rs=s.executeQuery("select * from tb_kehu_info");
			
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
			okButton.setBounds(new Rectangle(348, 178, 95, 41));
			okButton.setText("确定删除");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(value.equals("")){
						JOptionPane.showMessageDialog(okButton, "请选择要删除的客户名称");
					}else{
						int confirm=JOptionPane.showConfirmDialog(okButton, "确认删除此客户的资料吗？");
						if(confirm==JOptionPane.YES_OPTION){
					KeHuDao khdi=new KeHuDaoImpl();
					khdi.delete(value);
					JOptionPane.showMessageDialog(okButton, "删除成功");
					
					dispose();
					
					KeHuShanChu kehushanchu=new KeHuShanChu();
					kehushanchu.setVisible(true);
					kehushanchu.setSize(500,300);
					kehushanchu.setLocationRelativeTo(null);
				}}}
			});
		}
		return okButton;
	}

	/**
	 * This method initializes bh	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getBh() {
		if (bh == null) {
			bh = new JTextField();
			bh.setEditable(false);
			bh.setBounds(new Rectangle(162, 101, 149, 22));
		}
		return bh;
	}
	/**
	 * This method initializes phone	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPhone() {
		if (phone == null) {
			phone = new JTextField();
			phone.setEditable(false);
			phone.setBounds(new Rectangle(167, 146, 147, 22));
		}
		return phone;
	}
	/**
	 * This method initializes addr	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAddr() {
		if (addr == null) {
			addr = new JTextField();
			addr.setEditable(false);
			addr.setBounds(new Rectangle(169, 186, 149, 22));
		}
		return addr;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				KeHuShanChu thisClass = new KeHuShanChu();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public KeHuShanChu() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 300);
		this.setContentPane(getJContentPane());
		this.setTitle("客户资料删除");
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
			jLabel3.setBounds(new Rectangle(65, 184, 73, 24));
			jLabel3.setText("客户地址");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(60, 142, 84, 25));
			jLabel2.setText("客户电话");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(58, 99, 88, 23));
			jLabel1.setText("客户编号");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(29, 32, 179, 37));
			jLabel.setText("    请选择要删除的客户名称");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getKehunamejComboBox(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getBh(), null);
			jContentPane.add(getPhone(), null);
			jContentPane.add(getAddr(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="288,18"
