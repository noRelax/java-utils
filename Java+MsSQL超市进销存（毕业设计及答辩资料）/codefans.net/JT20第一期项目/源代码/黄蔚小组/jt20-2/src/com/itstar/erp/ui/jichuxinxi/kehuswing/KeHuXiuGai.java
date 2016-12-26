package com.itstar.erp.ui.jichuxinxi.kehuswing;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import com.itstar.erp.dao.baiscinfo.dao.KeHuDao;
import com.itstar.erp.dao.baiscinfo.impl.KeHuDaoImpl;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.vo.kehu.KeHuBean;

public class KeHuXiuGai extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JTextField kehuid = null;
	private JComboBox kehunamejComboBox = null;
	private JTextField kehuzip = null;
	private JTextField kehuconn = null;
	private JTextField kehuconnphone = null;
	private JTextField kehuphone = null;
	private JTextField kehuemail = null;
	private JTextField kehuaddress = null;
	private JButton okButton = null;
	private JButton resetButton = null;
	DefaultComboBoxModel model;
	String value;
	DefaultComboBoxModel jComboBox;
	/**
	 * This method initializes kehuid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuid() {
		if (kehuid == null) {
			kehuid = new JTextField();
			kehuid.setEditable(false);
			kehuid.setBounds(new Rectangle(139, 32, 91, 22));
		}
		return kehuid;
	}

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
					String kehuName=rs.getString(2);
					v.add(kehuName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			model= new DefaultComboBoxModel(v);
			kehunamejComboBox = new JComboBox(model);
			kehunamejComboBox.setBounds(new Rectangle(339, 31, 130, 27));
			kehunamejComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						value=kehunamejComboBox.getSelectedItem().toString();
						KeHuDao khdi=new KeHuDaoImpl();
						KeHuBean bean=khdi.Query(value);
						System.out.println(value);
						kehuid.setText("gys"+(1000+bean.getKehuID()));
						kehuzip.setText(bean.getKehuZip());
						kehuconn.setText(bean.getKehuConn());
						kehuphone.setText(bean.getKehuPhone());
						kehuconnphone.setText(bean.getKehuConnPhone());
						kehuemail.setText(bean.getKehuEmail());
						kehuaddress.setText(bean.getKehuAddress());
				}}}
			);
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
	 * This method initializes kehuzip	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuzip() {
		if (kehuzip == null) {
			kehuzip = new JTextField();
			kehuzip.setBounds(new Rectangle(142, 80, 90, 22));
		}
		return kehuzip;
	}

	/**
	 * This method initializes kehuconn	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuconn() {
		if (kehuconn == null) {
			kehuconn = new JTextField();
			kehuconn.setBounds(new Rectangle(342, 76, 122, 22));
		}
		return kehuconn;
	}

	/**
	 * This method initializes kehuconnphone	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuconnphone() {
		if (kehuconnphone == null) {
			kehuconnphone = new JTextField();
			kehuconnphone.setBounds(new Rectangle(360, 125, 122, 22));
		}
		return kehuconnphone;
	}

	/**
	 * This method initializes kehuphone	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuphone() {
		if (kehuphone == null) {
			kehuphone = new JTextField();
			kehuphone.setBounds(new Rectangle(142, 127, 148, 22));
		}
		return kehuphone;
	}

	/**
	 * This method initializes kehuemail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuemail() {
		if (kehuemail == null) {
			kehuemail = new JTextField();
			kehuemail.setBounds(new Rectangle(138, 167, 327, 22));
		}
		return kehuemail;
	}

	/**
	 * This method initializes kehuaddress	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuaddress() {
		if (kehuaddress == null) {
			kehuaddress = new JTextField();
			kehuaddress.setBounds(new Rectangle(138, 211, 327, 22));
		}
		return kehuaddress;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(164, 238, 80, 22));
			okButton.setText("确定");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				KeHuBean bean;
				KeHuDao khdi;
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String phone=kehuphone.getText().trim();
					String zip=kehuzip.getText().trim();
					String conn=kehuconn.getText().trim();
					String connphone=kehuconnphone.getText().trim();
					String email=kehuemail.getText().trim();
					String address=kehuaddress.getText().trim();
					if(phone.equals("")||conn.equals("")||connphone.equals("")||address.equals("")){
						JOptionPane.showMessageDialog(okButton, "除Email,邮编外，不许为空 ！");
					}else{
						bean=new KeHuBean();
						bean.setKehuPhone(phone);
						bean.setKehuAddress(address);
						bean.setKehuConn(conn);
						bean.setKehuConnPhone(connphone);
						bean.setKehuEmail(email);
						bean.setKehuName(value);
						bean.setKehuConnPhone(phone);
						bean.setKehuZip(zip);
						khdi=new KeHuDaoImpl();
						khdi.update(bean, value);
						JOptionPane.showMessageDialog(okButton, "保存成功！");
						dispose();
						
						KeHuXiuGai kehuxiugai=new KeHuXiuGai();
						kehuxiugai.setVisible(true);
						kehuxiugai.setSize(500,300);
						kehuxiugai.setLocationRelativeTo(null);
						
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
			resetButton.setBounds(new Rectangle(276, 238, 65, 22));
			resetButton.setText("重置");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					kehuphone.setText("");
					kehuzip.setText("");
					kehuconn.setText("");
					kehuconnphone.setText("");
					kehuemail.setText("");
					kehuaddress.setText("");
				}
			});
		}
		return resetButton;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				KeHuXiuGai thisClass = new KeHuXiuGai();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public KeHuXiuGai() {
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
		this.setTitle("客户资料修改");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(66, 211, 56, 24));
			jLabel7.setText("客户地址");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(63, 165, 62, 26));
			jLabel6.setText("客户Email");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(291, 123, 65, 27));
			jLabel5.setText("联系人电话");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(256, 77, 61, 25));
			jLabel4.setText("联系人");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(252, 29, 66, 27));
			jLabel3.setText("客户名称");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(64, 123, 64, 25));
			jLabel2.setText("客户电话");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(60, 76, 68, 26));
			jLabel1.setText("客户邮编");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(61, 28, 70, 30));
			jLabel.setText("客户编号");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(getKehuid(), null);
			jContentPane.add(getKehunamejComboBox(), null);
			jContentPane.add(getKehuzip(), null);
			jContentPane.add(getKehuconn(), null);
			jContentPane.add(getKehuconnphone(), null);
			jContentPane.add(getKehuphone(), null);
			jContentPane.add(getKehuemail(), null);
			jContentPane.add(getKehuaddress(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getResetButton(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="309,22"
