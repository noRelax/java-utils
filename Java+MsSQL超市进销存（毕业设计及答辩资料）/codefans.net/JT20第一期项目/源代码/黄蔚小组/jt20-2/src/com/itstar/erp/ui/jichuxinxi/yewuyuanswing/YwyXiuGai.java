package com.itstar.erp.ui.jichuxinxi.yewuyuanswing;

import javax.swing.SwingUtilities;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.itstar.erp.dao.baiscinfo.dao.YwyDao;
import com.itstar.erp.dao.baiscinfo.impl.YwyDaoImpl;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.vo.yewuyuan.YwyBean;

public class YwyXiuGai extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JComboBox ywynameComboBox = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField ywyid = null;
	private JTextField ywyphone = null;
	private JTextField ywyaddress = null;
	DefaultComboBoxModel model;
	String value="";  //  @jve:decl-index=0:
	private JButton okButton = null;
	private JButton resetButton = null;
	/**
	 * This method initializes ywynameComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getYwynameComboBox() {
		if (ywynameComboBox == null) {
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
			ywynameComboBox = new JComboBox(model);
			ywynameComboBox.setBounds(new Rectangle(363, 39, 119, 27));
			ywynameComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						value=ywynameComboBox.getSelectedItem().toString();
						YwyDao ydi=new YwyDaoImpl();
						YwyBean bean=ydi.Query(value);
						System.out.println(value);
						
						if(!value.equals(""))
						ywyid.setText("yewuyuan"+(1000+bean.getYwyID()));
						else{
							ywyid.setText("");
						}
						ywyid.setEditable(false);
						ywyphone.setText(bean.getYwyPhone());
						ywyaddress.setText(bean.getYwyAddress());
				}
				}});
		}
		return ywynameComboBox;
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
	 * This method initializes ywyid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getYwyid() {
		if (ywyid == null) {
			ywyid = new JTextField(); ywyid.setEditable(false);
			ywyid.setBounds(new Rectangle(165, 40, 105, 29));
		}
		return ywyid;
	}

	/**
	 * This method initializes ywyphone	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getYwyphone() {
		if (ywyphone == null) {
			ywyphone = new JTextField();
			ywyphone.setBounds(new Rectangle(166, 102, 257, 30));
		}
		return ywyphone;
	}

	/**
	 * This method initializes ywyaddress	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getYwyaddress() {
		if (ywyaddress == null) {
			ywyaddress = new JTextField();
			ywyaddress.setBounds(new Rectangle(169, 150, 254, 28));
		}
		return ywyaddress;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(170, 205, 71, 25));
			okButton.setText("确定");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				YwyBean bean;
				YwyDao ydi;
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(value.equals("")){
						JOptionPane.showMessageDialog(okButton, "请选择一个业务员");
					}else{
					String phone=ywyphone.getText().trim();
					String address=ywyaddress.getText().trim();
					if(phone.equals("")||address.equals("")){
						JOptionPane.showMessageDialog(okButton, "内容部不允许为空 ！");
					}else{
						bean=new YwyBean();
						
						bean.setYwyPhone(phone);
						bean.setYwyAddress(address);
						bean.setYwyName(value);
						ydi=new YwyDaoImpl();
						ydi.update(bean, value);
						JOptionPane.showMessageDialog(okButton, "保存成功！");
						dispose();
						
						
						YwyXiuGai ywyxiugai=new YwyXiuGai();
						ywyxiugai.setVisible(true);
						ywyxiugai.setLocationRelativeTo(null);
						ywyxiugai.setSize(500,300);
					}
				}}
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
			resetButton.setBounds(new Rectangle(303, 204, 65, 32));
			resetButton.setText("重置");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ywyid.setText("");
					ywynameComboBox.setSelectedIndex(0);
					ywyphone.setText("");
					ywyaddress.setText("");
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
				YwyXiuGai thisClass = new YwyXiuGai();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public YwyXiuGai() {
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
		this.setTitle("业务员资料修改");
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
			jLabel3.setBounds(new Rectangle(68, 151, 82, 29));
			jLabel3.setText("业务员地址");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(70, 101, 84, 29));
			jLabel2.setText("业务员电话");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(281, 39, 74, 28));
			jLabel1.setText("业务员姓名");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(70, 36, 85, 32));
			jLabel.setText("业务员编号");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getYwynameComboBox(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getYwyid(), null);
			jContentPane.add(getYwyphone(), null);
			jContentPane.add(getYwyaddress(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getResetButton(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="311,16"
