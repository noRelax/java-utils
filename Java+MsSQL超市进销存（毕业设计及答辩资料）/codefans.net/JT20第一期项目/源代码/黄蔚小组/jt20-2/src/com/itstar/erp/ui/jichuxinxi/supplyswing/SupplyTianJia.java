package com.itstar.erp.ui.jichuxinxi.supplyswing;

import javax.swing.SwingUtilities;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.itstar.erp.dao.baiscinfo.dao.SupplyDao;
import com.itstar.erp.dao.baiscinfo.impl.SupplyDaoImpl;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.vo.supply.SupplyBean;

public class SupplyTianJia extends JFrame {

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
	private JTextField jTextField = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField2 = null;
	private JTextField jTextField3 = null;
	private JTextField jTextField4 = null;
	private JTextField jTextField5 = null;
	private JTextField jTextField6 = null;
	private JTextField jTextField7 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(140, 20, 95, 22));
			ResultSet rs = getResultSet();
			int spID = 0;
			try {
				while (rs.next()) {
					spID = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			jTextField.setText("gys" + (spID + 1000));
			jTextField.setEditable(false);

		}
		return jTextField;
	}

	private static ResultSet getResultSet() {
		Connection conn = new DBConnection().getConnection();
		Statement s;
		ResultSet rs = null;
		try {
			s = conn.createStatement();
			rs = s.executeQuery("select * from tb_supply_info");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rs;

	}

	/**
	 * This method initializes jTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(142, 53, 90, 22));
		}
		return jTextField1;
	}

	/**
	 * This method initializes jTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setBounds(new Rectangle(146, 88, 87, 24));
		}
		return jTextField2;
	}

	/**
	 * This method initializes jTextField3
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setBounds(new Rectangle(147, 127, 190, 28));
		}
		return jTextField3;
	}

	/**
	 * This method initializes jTextField4
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setBounds(new Rectangle(140, 171, 256, 29));
		}
		return jTextField4;
	}

	/**
	 * This method initializes jTextField5
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField5.setBounds(new Rectangle(330, 23, 108, 22));
		}
		return jTextField5;
	}

	/**
	 * This method initializes jTextField6
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setBounds(new Rectangle(333, 60, 113, 22));
		}
		return jTextField6;
	}

	/**
	 * This method initializes jTextField7
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setBounds(new Rectangle(331, 89, 113, 29));
		}
		return jTextField7;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(155, 208, 74, 34));
			jButton.setText("确定");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				SupplyDao daoi;
				SupplyBean bean;

				public void actionPerformed(java.awt.event.ActionEvent e) {
					String spaddress = jTextField4.getText().trim();
					String spconn = jTextField6.getText().trim();
					String spconnphone = jTextField7.getText().trim();
					String spemail = jTextField3.getText().trim();
					String spname = jTextField1.getText().trim();
					String spphone = jTextField2.getText().trim();
					String spzip = jTextField5.getText().trim();

					if (spaddress.equals("") || spconn.equals("")
							|| spconnphone.equals("") || spname.equals("")
							|| spphone.equals("")) {

						JOptionPane.showMessageDialog(jButton,
								"除Email,邮编外，不许为空 ！");

					}

					else {

						ResultSet yz = new GetRS()
								.getResultSet("select spName from tb_supply_info where spName='"
										+ spname + "'");

						try {
							if (yz.next()) {
								JOptionPane.showMessageDialog(jButton,
										"此供应商已存在！！！");
							} else {

								bean = new SupplyBean();
								bean.setSpAddress(jTextField4.getText().trim());
								bean.setSpConn(jTextField6.getText().trim());
								bean.setSpConnPhone(jTextField7.getText()
										.trim());
								bean.setSpEmail(jTextField3.getText().trim());
								bean.setSpName(jTextField1.getText().trim());
								bean.setSpPhone(jTextField2.getText().trim());
								bean.setSpZip(jTextField5.getText().trim());
								daoi = new SupplyDaoImpl();
								daoi.insert(bean);
								JOptionPane.showMessageDialog(jButton, "添加成功！");

								dispose();

								SupplyTianJia supplyTianJia = new SupplyTianJia();
								supplyTianJia.setVisible(true);
								supplyTianJia.setSize(500, 300);
								supplyTianJia.setLocationRelativeTo(null);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(260, 208, 77, 32));
			jButton1.setText("重置");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTextField4.setText("");
					jTextField6.setText("");
					jTextField7.setText("");
					jTextField3.setText("");
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField5.setText("");
				}
			});
		}
		return jButton1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SupplyTianJia thisClass = new SupplyTianJia();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public SupplyTianJia() {
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
		this.setTitle("供应商添加");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(238, 88, 84, 32));
			jLabel7.setText("联系人电话");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(58, 171, 75, 25));
			jLabel6.setText("供应商地址");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(238, 57, 84, 27));
			jLabel5.setText("联系人");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(59, 126, 81, 29));
			jLabel4.setText("联系人Email");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(245, 16, 85, 26));
			jLabel3.setText("供应商邮编");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(59, 86, 84, 31));
			jLabel2.setText("供应商电话");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(56, 53, 84, 24));
			jLabel1.setText("供应商名称");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(54, 18, 85, 26));
			jLabel.setText("供应商编号");
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
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getJTextField1(), null);
			jContentPane.add(getJTextField2(), null);
			jContentPane.add(getJTextField3(), null);
			jContentPane.add(getJTextField4(), null);
			jContentPane.add(getJTextField5(), null);
			jContentPane.add(getJTextField6(), null);
			jContentPane.add(getJTextField7(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
		}
		return jContentPane;
	}

} // @jve:decl-index=0:visual-constraint="308,19"
