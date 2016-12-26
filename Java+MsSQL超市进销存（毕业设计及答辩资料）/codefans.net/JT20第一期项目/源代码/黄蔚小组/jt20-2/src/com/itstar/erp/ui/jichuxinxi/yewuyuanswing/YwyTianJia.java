package com.itstar.erp.ui.jichuxinxi.yewuyuanswing;

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

import com.itstar.erp.dao.baiscinfo.dao.YwyDao;
import com.itstar.erp.dao.baiscinfo.impl.YwyDaoImpl;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.vo.yewuyuan.YwyBean;

public class YwyTianJia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField ywyid = null;
	private JTextField ywyname = null;
	private JTextField ywyphone = null;
	private JTextField ywyaddress = null;
	private JButton okButton = null;
	private JButton resetButton = null;

	/**
	 * This method initializes ywyid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getYwyid() {
		if (ywyid == null) {
			ywyid = new JTextField();
			ResultSet rs=getResultSet();
			int ywyID = 0;
			try {
				while(rs.next()){
					ywyID=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ywyid.setText("yewuyuan"+(ywyID+1000));
			ywyid.setEditable(false);

			ywyid.setBounds(new Rectangle(157, 36, 95, 22));
		}
		return ywyid;
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
	 * This method initializes ywyname	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getYwyname() {
		if (ywyname == null) {
			ywyname = new JTextField();
			ywyname.setBounds(new Rectangle(344, 37, 110, 22));
		}
		return ywyname;
	}

	/**
	 * This method initializes ywyphone	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getYwyphone() {
		if (ywyphone == null) {
			ywyphone = new JTextField();
			ywyphone.setBounds(new Rectangle(161, 88, 247, 22));
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
			ywyaddress.setBounds(new Rectangle(167, 133, 242, 22));
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
			okButton.setBounds(new Rectangle(140, 195, 69, 30));
			okButton.setText("确定");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				YwyBean bean;
				YwyDao ydi;
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String name=ywyname.getText().trim();
					String phone=ywyphone.getText().trim();
					String address=ywyaddress.getText().trim();
					if(name.equals("")||phone.equals("")||address.equals("")){
						JOptionPane.showMessageDialog(okButton, "请填写全部内容！");
					}else{
						ResultSet yz=new GetRS().getResultSet("select ywyName from tb_yewuyuan_info where ywyName='"+name+"'");
						try {
							if(yz.next()){
								JOptionPane.showMessageDialog(okButton, "此客户已存在！！！");
							}else{
							bean=new YwyBean();
							bean.setYwyPhone(phone);
							bean.setYwyAddress(address);
							bean.setYwyName(name);
							ydi=new YwyDaoImpl();
							ydi.insert(bean);
							JOptionPane.showMessageDialog(okButton, "添加成功！");
							dispose();
							
							YwyTianJia ywytianjia=new YwyTianJia();
							ywytianjia.setVisible(true);
							ywytianjia.setSize(500, 300);
							ywytianjia.setLocationRelativeTo(null);
}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
			resetButton.setBounds(new Rectangle(296, 194, 76, 29));
			resetButton.setText("重置");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ywyname.setText("");
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
				YwyTianJia thisClass = new YwyTianJia();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public YwyTianJia() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle("业务员添加");
		this.setSize(500, 300);
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
			jLabel3.setBounds(new Rectangle(74, 132, 75, 27));
			jLabel3.setText("业务员地址");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(71, 85, 76, 26));
			jLabel2.setText("业务员电话");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(262, 36, 75, 27));
			jLabel1.setText("业务员姓名");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(73, 34, 76, 28));
			jLabel.setText("业务员编号");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getYwyid(), null);
			jContentPane.add(getYwyname(), null);
			jContentPane.add(getYwyphone(), null);
			jContentPane.add(getYwyaddress(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getResetButton(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="269,20"
