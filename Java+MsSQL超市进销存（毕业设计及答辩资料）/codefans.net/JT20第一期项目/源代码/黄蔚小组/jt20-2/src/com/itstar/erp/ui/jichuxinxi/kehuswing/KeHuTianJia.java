package com.itstar.erp.ui.jichuxinxi.kehuswing;

import javax.swing.SwingUtilities;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.itstar.erp.dao.baiscinfo.dao.KeHuDao;
import com.itstar.erp.dao.baiscinfo.impl.KeHuDaoImpl;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.vo.kehu.KeHuBean;

public class KeHuTianJia extends JFrame {

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
	private JTextField kehuname = null;
	private JTextField kehuphone = null;
	private JTextField kehuzip = null;
	private JTextField kehuconn = null;
	private JTextField kehuconnphone = null;
	private JTextField kehuemail = null;
	private JTextField kehuaddress = null;
	private JButton okButton = null;
	private JButton resetButton = null;
	/**
	 * This method initializes kehuid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuid() {
		if (kehuid == null) {
			kehuid = new JTextField();
			kehuid.setBounds(new Rectangle(123, 25, 123, 22));
			ResultSet rs=getResultSet();
			int kehuID = 0;
			try {
				while(rs.next()){
					kehuID=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			kehuid.setText("kh"+(kehuID+1000));
			kehuid.setEditable(false);

			
		}
		return kehuid;
	}

	/**
	 * This method initializes kehuname	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuname() {
		if (kehuname == null) {
			kehuname = new JTextField();
			kehuname.setBounds(new Rectangle(129, 59, 114, 22));
		}
		return kehuname;
	}

	/**
	 * This method initializes kehuphone	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuphone() {
		if (kehuphone == null) {
			kehuphone = new JTextField();
			kehuphone.setBounds(new Rectangle(130, 99, 112, 22));
		}
		return kehuphone;
	}

	/**
	 * This method initializes kehuzip	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuzip() {
		if (kehuzip == null) {
			kehuzip = new JTextField();
			kehuzip.setBounds(new Rectangle(339, 26, 103, 22));
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
			kehuconn.setBounds(new Rectangle(343, 65, 100, 22));
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
			kehuconnphone.setBounds(new Rectangle(348, 100, 96, 22));
		}
		return kehuconnphone;
	}

	/**
	 * This method initializes kehuemail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKehuemail() {
		if (kehuemail == null) {
			kehuemail = new JTextField();
			kehuemail.setBounds(new Rectangle(130, 151, 308, 22));
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
			kehuaddress.setBounds(new Rectangle(132, 188, 303, 22));
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
			okButton.setBounds(new Rectangle(189, 223, 69, 26));
			okButton.setText("确定");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				KeHuBean bean;
				KeHuDao daoi;
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String name=kehuname.getText().trim();
					String phone=kehuphone.getText().trim();
					String zip=kehuzip.getText().trim();
					String conn=kehuconn.getText().trim();
					String connphone=kehuconnphone.getText().trim();
					String email=kehuemail.getText().trim();
					String address=kehuaddress.getText().trim();
					if(name.equals("")||phone.equals("")||conn.equals("")||connphone.equals("")||address.equals("")){
						JOptionPane.showMessageDialog(okButton, "除Email,邮编外，不许为空 ！");
					}else{
						ResultSet yz=new GetRS().getResultSet("select kehuName from tb_kehu_info where kehuName='"+name+"'");
						try {
							if(yz.next()){
								JOptionPane.showMessageDialog(okButton, "此客户已存在");
							}else{
							bean=new KeHuBean();
							bean.setKehuPhone(phone);
							bean.setKehuAddress(address);
							bean.setKehuConn(conn);
							bean.setKehuConnPhone(connphone);
							bean.setKehuEmail(email);
							bean.setKehuName(name);
							bean.setKehuConnPhone(phone);
							bean.setKehuZip(zip);
							daoi=new KeHuDaoImpl();
							daoi.insert(bean);
							JOptionPane.showMessageDialog(okButton, "添加成功！");
							dispose();
							KeHuTianJia kehutianjia=new KeHuTianJia();
							kehutianjia.setVisible(true);
							kehutianjia.setSize(500, 300);
							kehutianjia.setLocationRelativeTo(null);
}
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}}
				}
			});
		}
		return okButton;
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
	 * This method initializes resetButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getResetButton() {
		if (resetButton == null) {
			resetButton = new JButton();
			resetButton.setBounds(new Rectangle(290, 222, 74, 25));
			resetButton.setText("重置");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					kehuname.setText("");
					kehuphone.setText("");
					kehuzip.setText("");
					kehuconn.setText("");
					kehuconnphone.setText("");
					kehuemail.setText("");
					kehuaddress.setText("");
					kehuid.setText("");
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
				KeHuTianJia thisClass = new KeHuTianJia();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public KeHuTianJia() {
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
		this.setTitle("客户资料添加");
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
			jLabel7.setBounds(new Rectangle(53, 148, 58, 27));
			jLabel7.setText("客户Email");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(263, 103, 77, 25));
			jLabel6.setText("联系人电话");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(271, 64, 53, 25));
			jLabel5.setText("联系人");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(265, 26, 55, 26));
			jLabel4.setText("客户邮编");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(53, 191, 65, 24));
			jLabel3.setText("客户地址");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(54, 99, 62, 27));
			jLabel2.setText("客户电话");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(51, 58, 64, 25));
			jLabel1.setText("客户名称");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(50, 23, 66, 25));
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
			jContentPane.add(getKehuname(), null);
			jContentPane.add(getKehuphone(), null);
			jContentPane.add(getKehuzip(), null);
			jContentPane.add(getKehuconn(), null);
			jContentPane.add(getKehuconnphone(), null);
			jContentPane.add(getKehuemail(), null);
			jContentPane.add(getKehuaddress(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getResetButton(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="238,15"
