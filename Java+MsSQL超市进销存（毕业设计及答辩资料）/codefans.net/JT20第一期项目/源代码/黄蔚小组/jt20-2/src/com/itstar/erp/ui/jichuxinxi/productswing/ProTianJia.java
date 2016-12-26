package com.itstar.erp.ui.jichuxinxi.productswing;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import com.itstar.erp.dao.baiscinfo.dao.ProDao;
import com.itstar.erp.dao.baiscinfo.impl.ProDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.product.ProBean;

public class ProTianJia extends JFrame {

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
	private JButton okButton = null;
	private JButton resetButton = null;
	private JTextField proid = null;
	private JComboBox protypenameComboBox = null;
	private JComboBox spnameComboBox = null;
	private JTextField proname = null;
	private JTextField proprice = null;
	private JTextField protypedanwei = null;
	private JTextArea proremark = null;
	private JTextField procreatetime = null;
	String date = new GetTime().getTime();
	Map<String, Integer> spnameidmap = new HashMap<String, Integer>(); // @jve:decl-index=0:
	Map<String, String> leibiedanweimap = new HashMap<String, String>(); // @jve:decl-index=0:
	Map<String, Integer> protypenameidmap = new HashMap<String, Integer>(); // @jve:decl-index=0:
	Map<String, Integer> ywynameidmap = new HashMap<String, Integer>(); // @jve:decl-index=0:
	String leibievalue = ""; // @jve:decl-index=0:
	String spvalue = ""; // @jve:decl-index=0:
	String ywyvalue = ""; // @jve:decl-index=0:
	private JLabel jLabel8 = null;
	private JComboBox ywynameComboBox = null;

	/**
	 * This method initializes okButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(276, 225, 78, 26));
			okButton.setText("确定");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				ProBean bean;
				ProDao pdi;

				public void actionPerformed(java.awt.event.ActionEvent e) {

					String name = proname.getText().trim();
					String price = proprice.getText().trim();
					String remark = proremark.getText().trim();

					if (leibievalue.equals("") || spvalue.equals("")
							|| ywyvalue.equals("")) {
						JOptionPane.showMessageDialog(okButton, "下拉框内容必须选择");
					} else {
						if (name.equals("") || price.equals("")) {
							JOptionPane.showMessageDialog(okButton,
									"除备注外，其他不许为空 ！");
						} else {
							double d = 0;
							try {
								d = Double.parseDouble(price);
								if (d <= 0) {
									JOptionPane.showMessageDialog(okButton,
											"进价必须大于零");
								} else {
									ResultSet yz = new GetRS()
											.getResultSet("select proName from tb_product_info where proName='"
													+ name + "'");

									try {
										try {
											if (yz.next()) {
												JOptionPane.showMessageDialog(okButton, "此商品已存在");
											}
														
											else {

												bean = new ProBean();
												bean.setProTypeID(protypenameidmap
														.get(leibievalue));
												bean.setProName(name);
												bean.setProPrice(d);
												bean.setProCreateTime(date);
												bean.setYwyID(ywynameidmap
														.get(ywyvalue));
												bean.setSpID(spnameidmap
														.get(spvalue));
												bean.setProRemark(remark);
												pdi = new ProDaoImpl();
												pdi.insert(bean);
												JOptionPane.showMessageDialog(
														okButton, "添加成功！");
												dispose();

												ProTianJia protianjia = new ProTianJia();
												protianjia.setVisible(true);
												protianjia.setSize(500, 300);
												protianjia
														.setLocationRelativeTo(null);
											}
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									} catch (HeadlessException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}

							}

							catch (NumberFormatException e1) {
								JOptionPane.showMessageDialog(okButton,
										"进价必须为数字");
							}
						}
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
			resetButton.setBounds(new Rectangle(387, 230, 76, 20));
			resetButton.setText("重置");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					protypenameComboBox.setSelectedIndex(0);
					spnameComboBox.setSelectedIndex(0);
					ywynameComboBox.setSelectedIndex(0);
					proname.setText("");
					proprice.setText("");
					protypedanwei.setText("");
					proremark.setText("");
				}
			});
		}
		return resetButton;
	}

	/**
	 * This method initializes proid
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getProid() {
		if (proid == null) {
			proid = new JTextField();
			String table = "tb_product_info";
			ResultSet rs = new GetResultSet().getResultSet(table);
			int proID = 0;
			try {
				while (rs.next()) {
					proID = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			proid.setText("准字" + (proID + 1000));
			proid.setBounds(new Rectangle(141, 31, 98, 22));
			proid.setEditable(false);
		}
		return proid;
	}

	/**
	 * This method initializes protypenameComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getProtypenameComboBox() {
		if (protypenameComboBox == null) {
			String table = "tb_productType_info";
			Vector v = new Vector();
			v.add("");
			ResultSet rs = new GetResultSet().getResultSet(table);
			try {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String danwei = rs.getString(3);
					v.add(name);
					leibiedanweimap.put(name, danwei);
					protypenameidmap.put(name, id);
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
			DefaultComboBoxModel model = new DefaultComboBoxModel(v);
			protypenameComboBox = new JComboBox(model);
			protypenameComboBox.setBounds(new Rectangle(357, 32, 117, 27));
			protypenameComboBox
					.addItemListener(new java.awt.event.ItemListener() {
						public void itemStateChanged(java.awt.event.ItemEvent e) {
							if (e.getStateChange() == ItemEvent.SELECTED) {
								leibievalue = protypenameComboBox
										.getSelectedItem().toString();
								protypedanwei.setText(leibiedanweimap
										.get(leibievalue));
							}
						}
					});
		}
		return protypenameComboBox;
	}

	/**
	 * This method initializes spnameComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getSpnameComboBox() {
		if (spnameComboBox == null) {
			Vector v = new Vector();
			v.add("");
			String table = "tb_supply_info";
			ResultSet rs = new GetResultSet().getResultSet(table);
			try {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					v.add(name);
					spnameidmap.put(name, id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DefaultComboBoxModel mpdel = new DefaultComboBoxModel(v);
			spnameComboBox = new JComboBox(v);
			spnameComboBox.setBounds(new Rectangle(362, 80, 111, 27));
			spnameComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						spvalue = spnameComboBox.getSelectedItem().toString();
					}
				}
			});
		}
		return spnameComboBox;
	}

	/**
	 * This method initializes proname
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getProname() {
		if (proname == null) {
			proname = new JTextField();
			proname.setBounds(new Rectangle(138, 80, 97, 22));
		}
		return proname;
	}

	/**
	 * This method initializes proprice
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getProprice() {
		if (proprice == null) {
			proprice = new JTextField();
			proprice.setBounds(new Rectangle(137, 133, 96, 22));
		}
		return proprice;
	}

	/**
	 * This method initializes protypedanwei
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getProtypedanwei() {
		if (protypedanwei == null) {
			protypedanwei = new JTextField();
			protypedanwei.setBounds(new Rectangle(367, 132, 103, 22));
			protypedanwei.setEditable(false);
		}
		return protypedanwei;
	}

	/**
	 * This method initializes proremark
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getProremark() {
		if (proremark == null) {
			proremark = new JTextArea();
			proremark.setBounds(new Rectangle(134, 186, 112, 67));
		}
		return proremark;
	}

	/**
	 * This method initializes procreatetime
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getProcreatetime() {
		if (procreatetime == null) {
			procreatetime = new JTextField();
			procreatetime.setText(date);
			procreatetime.setEditable(false);
			procreatetime.setBounds(new Rectangle(367, 165, 109, 22));
		}
		return procreatetime;
	}

	/**
	 * This method initializes ywynameComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getYwynameComboBox() {
		if (ywynameComboBox == null) {
			String table = "tb_yewuyuan_info";
			Vector v = new Vector();
			v.add("");
			ResultSet rs = new GetResultSet().getResultSet(table);
			try {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					v.add(name);
					ywynameidmap.put(name, id);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DefaultComboBoxModel model = new DefaultComboBoxModel(v);
			ywynameComboBox = new JComboBox(model);
			ywynameComboBox.setBounds(new Rectangle(364, 192, 115, 27));
			ywynameComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						ywyvalue = ywynameComboBox.getSelectedItem().toString();
						System.out.println(ywyvalue);
					}
				}
			});
		}
		return ywynameComboBox;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ProTianJia thisClass = new ProTianJia();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public ProTianJia() {
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
		this.setTitle("商品添加");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(260, 196, 77, 18));
			jLabel8.setText("   经手人");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(65, 184, 63, 26));
			jLabel7.setText("  备注");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(252, 79, 92, 27));
			jLabel6.setText("   供应商名称");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(255, 165, 100, 26));
			jLabel5.setText("   商品添加时间");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(64, 130, 62, 28));
			jLabel4.setText("  商品进价");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(58, 77, 68, 27));
			jLabel3.setText("  商品名称");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(255, 131, 97, 27));
			jLabel2.setText("  商品单位");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(250, 29, 98, 27));
			jLabel1.setText("  商品类别");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(58, 28, 71, 28));
			jLabel.setText("  商品编号");
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
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getResetButton(), null);
			jContentPane.add(getProid(), null);
			jContentPane.add(getProtypenameComboBox(), null);
			jContentPane.add(getSpnameComboBox(), null);
			jContentPane.add(getProname(), null);
			jContentPane.add(getProprice(), null);
			jContentPane.add(getProtypedanwei(), null);
			jContentPane.add(getProremark(), null);
			jContentPane.add(getProcreatetime(), null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(getYwynameComboBox(), null);
		}
		return jContentPane;
	}

} // @jve:decl-index=0:visual-constraint="149,7"
