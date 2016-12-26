package com.itstar;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.dao.DaoGys;

public class Gongyingshang extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTabbedPane jTabbedPane = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private JLabel jLabel = null;

	private JTextField jTextField = null;

	private JTextField jTextField1 = null;

	private JLabel jLabel2 = null;

	private JTextField jTextField2 = null;

	private JLabel jLabel3 = null;

	private JTextField jTextField3 = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JLabel jLabel4 = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel5 = null;

	private JTextField jTextField5 = null;

	private JLabel jLabel6 = null;

	private JTextField jTextField6 = null;

	private JLabel jLabel7 = null;

	private JTextField jTextField7 = null;

	private JLabel jLabel8 = null;

	private JComboBox jComboBox = null;

	private JButton jButton2 = null;

	private JButton jButton3 = null;

	private DaoGys dao = null; // @jve:decl-index=0:

	private int width = Toolkit.getDefaultToolkit().getScreenSize().width;

	private int hihg = Toolkit.getDefaultToolkit().getScreenSize().height;

	private JLabel jLabel9 = null;

	/**
	 * This is the default constructor
	 */
	public Gongyingshang() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		if (dao == null)
			dao = new DaoGys();

		this.setSize(500, 400);
		this.setResizable(false);
		this.setContentPane(getJTabbedPane());
		this.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setTitle("供应商管理");
		this.setLocation((width - 800) , (hihg - 600) / 2);
		this.setVisible(true);

	}

	/**
	 * This method initializes jTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("供应商信息添加", null, getJPanel(), null);
			jTabbedPane.addTab("供应商信息修改与删除", null, getJPanel1(), null);
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
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(4, 72, 72, 29));
			jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel1.setText("\u5730\u5740\uff1a");
			jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(5, 171, 77, 26));
			jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel3.setText("电子邮箱：");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(6, 124, 65, 27));
			jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel2.setText("电话：");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(2, 16, 82, 30));
			jLabel.setText("供应商全称:");

			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, gridBagConstraints);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(getJTextField2(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(getJTextField3(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
			jPanel.add(jLabel1, null);
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
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(114, 6, 356, 33));
			// jLabel9.setText("");

			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(5, 217, 86, 31));
			jLabel8.setText("选择供应商：");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(11, 153, 59, 30));
			jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel7.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel7.setText("Email:");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(13, 103, 55, 31));
			jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel6.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel6.setText("电话：");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(13, 54, 57, 29));
			jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel5.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel5.setText("地址：");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			jLabel4 = new JLabel();
			jLabel4.setText("供应商全称：");
			jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel4.setBounds(new Rectangle(1, 5, 91, 33));
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(jLabel4, gridBagConstraints1);
			jPanel1.add(jLabel5, null);
			jPanel1.add(getJTextField5(), null);
			jPanel1.add(jLabel6, null);
			jPanel1.add(getJTextField6(), null);
			jPanel1.add(jLabel7, null);
			jPanel1.add(getJTextField7(), null);
			jPanel1.add(jLabel8, null);
			jPanel1.add(getJComboBox(), null);
			jPanel1.add(getJButton2(), null);
			jPanel1.add(getJButton3(), null);
			jPanel1.add(jLabel9, null);

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
			jTextField.setBounds(new Rectangle(101, 17, 351, 31));
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
			jTextField1.setBounds(new Rectangle(102, 66, 348, 34));
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
			jTextField2.setBounds(new Rectangle(100, 120, 183, 34));
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
			jTextField3.setBounds(new Rectangle(102, 170, 305, 31));
		}
		return jTextField3;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton("添加");
			jButton.setBounds(new Rectangle(120, 255, 63, 37));
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {

					if (jTextField.getText().equals("")
							|| jTextField1.getText().equals("")
							|| jTextField2.getText().equals("")
							|| jTextField3.getText().equals("")) {

						JOptionPane.showMessageDialog(null,
								"信息不能为空");

					} else {
						if (dao.gyscz(jTextField.getText())) {
							JOptionPane.showMessageDialog(Gongyingshang.this,
									"供应商信息添加失败，存在同名供应商", "供应商添加信息",
									JOptionPane.INFORMATION_MESSAGE);

						} else {
							if (!jTextField2.getText().matches("\\d{7,11}")) {

								JOptionPane.showMessageDialog(
										Gongyingshang.this, " 请输入正确的电话位数");

							} else {
								if (!jTextField3.getText().matches(
										"[\\w]+@[\\w]+\\.[\\w]+")) {

									JOptionPane.showMessageDialog(
											Gongyingshang.this, " 输入的邮箱格式不正确");

								} else {
									GysBean gys = new GysBean();
									gys.setAllcall(jTextField.getText());
									gys.setAddress(jTextField1.getText());
									gys.setPnumber(jTextField2.getText());
									gys.setEmail(jTextField3.getText());

									if (dao.insert(gys)) {
										JOptionPane
												.showMessageDialog(
														Gongyingshang.this,
														"已成功添加供应商",
														"供应商添加信息",
														JOptionPane.INFORMATION_MESSAGE);
										getgysxiala();
									} else
										JOptionPane
												.showMessageDialog(
														Gongyingshang.this,
														"添加供应商失败",
														"供应商添加信息",
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
			jButton1.setBounds(new Rectangle(254, 255, 63, 37));
			jButton1.setText("重填");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					jTextField.setText("");
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");

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
			jTextField5.setBounds(new Rectangle(116, 52, 356, 33));
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
			jTextField6.setBounds(new Rectangle(116, 103, 195, 32));
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
			jTextField7.setBounds(new Rectangle(116, 152, 356, 38));
		}
		return jTextField7;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			getgysxiala();
			jComboBox.setBounds(new Rectangle(117, 214, 277, 32));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					/*
					 * if (dao == null) dao = new Dao();
					 */
					GysBean gys = new GysBean();
					gys = dao.getInfos(e.getItem().toString());
					jLabel9.setText(gys.getAllcall());
					jTextField5.setText(gys.getAddress());
					jTextField6.setText(gys.getPnumber());
					jTextField7.setText(gys.getEmail());
				}
			});

		}
		return jComboBox;
	}

	public void getgysxiala() {
		jComboBox.removeAllItems();
		List<String> list = dao.gysxlkchaxun();
		for (String s : list) {
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
			jButton2.setBounds(new Rectangle(102, 279, 87, 27));
			jButton2.setText("修改");

			jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {

					if (jTextField5.getText().equals("")
							|| jTextField6.getText().equals("")
							|| jTextField7.getText().equals("")) {
						JOptionPane.showMessageDialog(Gongyingshang.this,
								"修改信息不能为空");
					} else {
						GysBean gys5 = new GysBean();
						gys5.setAllcall(jLabel9.getText());
						gys5.setAddress(jTextField5.getText());
						gys5.setPnumber(jTextField6.getText());
						gys5.setEmail(jTextField7.getText());

						if (dao.updatexiugai(gys5)) {
							JOptionPane.showMessageDialog(Gongyingshang.this,
									"修改信息成功", "供应商添加信息",
									JOptionPane.INFORMATION_MESSAGE);
						} else
							JOptionPane.showMessageDialog(Gongyingshang.this,
									"修改信息失败", "供应商添加信息",
									JOptionPane.INFORMATION_MESSAGE);

					}

					// System.out.println("mouseClicked()");

				}
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
			jButton3.setBounds(new Rectangle(260, 276, 100, 28));

			jButton3.setText("删除供应商");
			jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {

					GysBean gys8 = new GysBean();
					gys8.setAllcall(jLabel9.getText());
					/*gys8.setAddress(jTextField5.getText());
					gys8.setPnumber(jTextField6.getText());
					gys8.setEmail(jTextField7.getText());*/

					if (JOptionPane.showConfirmDialog(null, "删除此供应商？", "操作提示",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)

						if (dao.updatedelete(gys8)) {

							JOptionPane.showMessageDialog(Gongyingshang.this,
									"删除供应商成功", "供应商信息删除与修改",
									JOptionPane.INFORMATION_MESSAGE);
							getgysxiala();
						} else {
							JOptionPane.showMessageDialog(Gongyingshang.this,
									"删除供应商失败", "供应商信息删除与修改",
									JOptionPane.INFORMATION_MESSAGE);
						}
				}

			});
		}
		return jButton3;
	}

}
