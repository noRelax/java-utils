package com.itstar;

import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import com.dao.Daok;

import javax.swing.JComboBox;

public class kehuxinxi extends JFrame {

	private static final long serialVersionUID = 1L;

	private int width = Toolkit.getDefaultToolkit().getScreenSize().width;

	private int hihg = Toolkit.getDefaultToolkit().getScreenSize().height;

	private JTabbedPane jTabbedPane = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private JLabel jLabel = null;

	private JTextField jTextField = null;

	private JLabel jLabel1 = null;

	private JTextField jTextField1 = null;

	private JLabel jLabel2 = null;

	private JTextField jTextField2 = null;

	private JLabel jLabel3 = null;

	private JTextField jTextField3 = null;

	private JTextField jTextField4 = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private Daok daok; // @jve:decl-index=0:

	private JLabel jLabel4 = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JTextField jTextField5 = null;

	private JLabel jLabel8 = null;

	private JTextField jTextField6 = null;

	private JLabel jLabel9 = null;

	private JTextField jTextField7 = null;

	private JLabel jLabel10 = null;

	private JTextField jTextField8 = null;

	private JLabel jLabel11 = null;

	private JComboBox jComboBox = null;

	private JButton jButton2 = null;

	private JButton jButton3 = null;

	private JLabel jLabel5 = null;

	/**
	 * This is the default constructor
	 */
	public kehuxinxi() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		if (daok == null)
			daok = new Daok();
		
		this.setSize(600, 500);
		this.setContentPane(getJTabbedPane());
		this.setResizable(false);
		this.setPreferredSize(new Dimension(500, 400));
		this.setTitle("客户信息管理");
		this.setLocation((width - 800) / 2, (hihg - 600) / 2);
		
	}

	/**
	 * This method initializes jTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("客户信息添加", null, getJPanel(), null);
			jTabbedPane.addTab("客户信息修改与删除", null, getJPanel1(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(14, 223, 55, 33));
			jLabel4.setText("邮箱：");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(5, 173, 95, 27));
			jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel3.setText("客户联系电话：");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(19, 126, 66, 27));
			jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel2.setText("客户地址：");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(20, 73, 67, 25));
			jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel1.setText("客户编号：");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("客户名称：");
			jLabel.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel.setBounds(new Rectangle(22, 23, 68, 27));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, gridBagConstraints);
			jPanel.add(getJTextField(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(getJTextField2(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(getJTextField3(), null);
			jPanel.add(getJTextField4(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
			jPanel.add(jLabel4, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(5, 29, 69, 31));
			jLabel5.setText("客户名称：");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(2, 307, 74, 27));
			jLabel11.setText("选择客户：");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(0, 256, 45, 27));
			jLabel10.setText("邮箱：");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(0, 211, 92, 27));
			jLabel9.setText("客户联系电话：");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(-1, 149, 69, 27));
			jLabel8.setText("客户地址：");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(-2, 88, 69, 33));
			jLabel7.setText("客户编号：");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(112, 22, 377, 39));
			// jLabel6.setText("JLabel");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(jLabel6, null);
			jPanel1.add(jLabel7, null);
			jPanel1.add(getJTextField5(), null);
			jPanel1.add(jLabel8, null);
			jPanel1.add(getJTextField6(), null);
			jPanel1.add(jLabel9, null);
			jPanel1.add(getJTextField7(), null);
			jPanel1.add(jLabel10, null);
			jPanel1.add(getJTextField8(), null);
			jPanel1.add(jLabel11, null);
			jPanel1.add(getJComboBox(), null);
			jPanel1.add(getJButton2(), null);
			jPanel1.add(getJButton3(), null);
			jPanel1.add(jLabel5, null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(108, 21, 348, 33));
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(109, 67, 348, 33));
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
			jTextField2.setBounds(new Rectangle(114, 119, 348, 33));
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
			jTextField3.setBounds(new Rectangle(117, 168, 192, 31));
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
			jTextField4.setBounds(new Rectangle(119, 220, 348, 33));
		}
		return jTextField4;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(134, 284, 63, 37));
			jButton.setText("添加");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (jTextField.getText().equals("")
							|| jTextField1.getText().equals("")
							|| jTextField2.getText().equals("")
							|| jTextField3.getText().equals("")) {
						JOptionPane.showMessageDialog(kehuxinxi.this, "信息不能为空");

					} else {
						if (daok.kehucz(jTextField.getText())) {
							JOptionPane.showMessageDialog(kehuxinxi.this,
									"客户信息添加失败，存在同名客户", "客户添加信息",
									JOptionPane.INFORMATION_MESSAGE);

						} else {
							if (!jTextField3.getText().matches("\\d{11,11}")) {

								JOptionPane.showMessageDialog(kehuxinxi.this,
										"  联系手机号为11位");

							} else {
								if (!jTextField4.getText().matches(
										"[\\w]+@[\\w]+\\.[\\w]+")) {

									JOptionPane.showMessageDialog(
											kehuxinxi.this, "输入的邮箱格式不正确");

								} else {

									KehuBean keh = new KehuBean();

									keh.setId(jTextField1.getText());
									keh.setKallcall(jTextField.getText());
									keh.setKaddress(jTextField2.getText());
									keh.setKpnumber(jTextField3.getText());
									keh.setKemail(jTextField4.getText());

									if (daok.insertk(keh)) {
										JOptionPane
												.showMessageDialog(
														kehuxinxi.this,
														"已成功添加客户",
														"客户添加信息",
														JOptionPane.INFORMATION_MESSAGE);

									   getkehuxiala();
									} else
										JOptionPane
												.showMessageDialog(
														kehuxinxi.this,
														"添加客户失败",
														"客户添加信息",
														JOptionPane.INFORMATION_MESSAGE);
								}
							}
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
			jButton1.setBounds(new Rectangle(285, 284, 63, 36));
			jButton1.setText("重填");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					jTextField.setText("");
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");
					jTextField4.setText("");

				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jTextField5
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField5.setBounds(new Rectangle(110, 85, 380, 38));
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
			jTextField6.setBounds(new Rectangle(110, 146, 382, 38));
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
			jTextField7.setBounds(new Rectangle(108, 204, 211, 32));
		}
		return jTextField7;
	}

	/**
	 * This method initializes jTextField8
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
			jTextField8.setBounds(new Rectangle(105, 253, 380, 37));
		}
		return jTextField8;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			
			jComboBox = new JComboBox();
			getkehuxiala();
			jComboBox.setBounds(new Rectangle(105, 309, 297, 34));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					
					KehuBean keh1 = new KehuBean();
					keh1 = daok.getkInfos(e.getItem().toString());
					jLabel6.setText(keh1.getKallcall());
					jTextField5.setText(keh1.getId());
					jTextField6.setText(keh1.getKaddress());
					jTextField7.setText(keh1.getKpnumber());
					jTextField8.setText(keh1.getKemail());

				}
			});
		}
		return jComboBox;
	}

	public void getkehuxiala() {
		jComboBox.removeAllItems();
		List<String> list2 = daok.kehuxlkchaxun();
		for (String s : list2) {
			jComboBox.addItem(s);

		}

	}

	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(184, 359, 73, 40));
			jButton2.setText("修改");
			jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {

					if (jTextField5.getText().equals("")
							|| jTextField6.getText().equals("")
							|| jTextField7.getText().equals("")
							|| jTextField8.getText().equals("")) {

						JOptionPane.showMessageDialog(kehuxinxi.this,
								"修改信息不能为空");

					} else {
						if (!jTextField7.getText().matches("\\d{11,11}")) {

							JOptionPane.showMessageDialog(kehuxinxi.this,
									" 请输入正确的电话位数才能修改");

						} else {
							if (!jTextField8.getText().matches(
									"[\\w]+@[\\w]+\\.[\\w]+")) {

								JOptionPane.showMessageDialog(kehuxinxi.this,
										"修改的邮箱格式不正确");

							} else {

								KehuBean kehu5 = new KehuBean();
								kehu5.setId(jTextField5.getText());
								kehu5.setKallcall(jLabel6.getText());
								kehu5.setKaddress(jTextField6.getText());
								kehu5.setKpnumber(jTextField7.getText());
								kehu5.setKemail(jTextField8.getText());

								if (daok.updatekehuxiugai(kehu5)) {
									JOptionPane.showMessageDialog(
											kehuxinxi.this, "修改信息成功",
											"客户信息修改与删除",
											JOptionPane.INFORMATION_MESSAGE);

								} else
									JOptionPane.showMessageDialog(
											kehuxinxi.this, "修改信息失败",
											"客户信息修改与删除",
											JOptionPane.INFORMATION_MESSAGE);

							}

						}

					}
				}

				// System.out.println("mouseClicked()"); // TODO Auto-generated
				// Event stub mouseClicked()

			});

		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setBounds(new Rectangle(329, 358, 92, 40));
			jButton3.setText("删除客户");
			jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {

					KehuBean kehu7 = new KehuBean();
					kehu7.setId(jTextField5.getText());
					kehu7.setKallcall(jLabel6.getText());
					kehu7.setKaddress(jTextField6.getText());
					kehu7.setKpnumber(jTextField7.getText());
					kehu7.setKemail(jTextField8.getText());

					if (JOptionPane.showConfirmDialog(null, "删除此客户？", "操作提示",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)

						if (daok.updatedeletekehu(kehu7)) {

							JOptionPane.showMessageDialog(kehuxinxi.this,
									"删除供应商成功", "供应商添加信息",
									JOptionPane.INFORMATION_MESSAGE);
					    getkehuxiala();
						} else {
							JOptionPane.showMessageDialog(kehuxinxi.this,
									"删除供应商失败", "供应商添加信息",
									JOptionPane.INFORMATION_MESSAGE);

						}

				}

			});
		}
		return jButton3;
	}

} // @jve:decl-index=0:visual-constraint="7,0"
