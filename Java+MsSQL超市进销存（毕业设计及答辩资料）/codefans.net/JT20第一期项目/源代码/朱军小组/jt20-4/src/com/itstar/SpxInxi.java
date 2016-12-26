package com.itstar;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.dao.Daosp;

import javax.swing.JComboBox;

public class SpxInxi extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTabbedPane jTabbedPane = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private int width = Toolkit.getDefaultToolkit().getScreenSize().width;

	private int hihg = Toolkit.getDefaultToolkit().getScreenSize().height;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JTextField jTextField = null;

	private JTextField jTextField1 = null;

	private JLabel jLabel2 = null;

	private JTextField jTextField2 = null;

	private JLabel jLabel3 = null;

	private JTextField jTextField3 = null;

	private JLabel jLabel4 = null;

	private JTextField jTextField4 = null;

	private JLabel jLabel5 = null;

	private JTextField jTextField5 = null;

	private JLabel jLabel6 = null;

	private JTextField jTextField6 = null;

	private JLabel jLabel7 = null;

	private JTextField jTextField7 = null;

	private JLabel jLabel8 = null;

	private JTextField jTextField8 = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private Daosp daosp;  //  @jve:decl-index=0:

	private JPanel jPanel2 = null;

	private JLabel jLabel9 = null;

	private JLabel jLabel11 = null;

	private JTextField jTextField9 = null;

	private JLabel jLabel21 = null;

	private JTextField jTextField21 = null;

	private JLabel jLabel31 = null;

	private JTextField jTextField31 = null;

	private JLabel jLabel41 = null;

	private JTextField jTextField41 = null;

	private JLabel jLabel51 = null;

	private JTextField jTextField51 = null;

	private JLabel jLabel61 = null;

	private JTextField jTextField61 = null;

	private JLabel jLabel71 = null;

	private JTextField jTextField71 = null;

	private JLabel jLabel81 = null;

	private JTextField jTextField81 = null;

	private JLabel jLabel10 = null;

	private JComboBox jComboBox = null;

	private JLabel jLabel12 = null;

	private JButton jButton2 = null;

	private JButton jButton3 = null;

	/**
	 * This is the default constructor
	 */
	public SpxInxi() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		if (daosp == null)
			daosp = new Daosp();

		
		this.setSize(600, 500);
		this.setResizable(false);
		this.setContentPane(getJTabbedPane());
		this.setTitle("商品管理");
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
			jTabbedPane.addTab("商品信息添加", null, getJPanel(), "商品添加");
			jTabbedPane.addTab("商品信息修改与删除", null, getJPanel1(), "修改与删除");

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
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(4, 299, 80, 38));
			jLabel8.setText("供应商名称：");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(5, 242, 58, 31));
			jLabel7.setText("备注：");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(0, 184, 70, 29));
			jLabel6.setText("批准文号：");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(402, 182, 52, 30));
			jLabel5.setText("规格：");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(2, 127, 66, 30));
			jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel4.setText("单位：");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(330, 15, 54, 27));
			jLabel3.setText("产地：");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(333, 73, 60, 27));
			jLabel2.setText("商品简称:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(7, 15, 67, 28));
			jLabel1.setText("商品编号：");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("商品名称:");
			jLabel.setBounds(new Rectangle(7, 66, 58, 28));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, gridBagConstraints);
			jPanel.add(jLabel1, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(getJTextField2(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(getJTextField3(), null);
			jPanel.add(jLabel4, null);
			jPanel.add(getJTextField4(), null);
			jPanel.add(jLabel5, null);
			jPanel.add(getJTextField5(), null);
			jPanel.add(jLabel6, null);
			jPanel.add(getJTextField6(), null);
			jPanel.add(jLabel7, null);
			jPanel.add(getJTextField7(), null);
			jPanel.add(jLabel8, null);
			jPanel.add(getJTextField8(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);

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
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(getJPanel2(), null);
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
			jTextField.setBounds(new Rectangle(90, 12, 226, 38));
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
			jTextField1.setBounds(new Rectangle(89, 62, 225, 40));
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
			jTextField2.setBounds(new Rectangle(401, 68, 184, 40));
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
			jTextField3.setBounds(new Rectangle(400, 13, 181, 40));
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
			jTextField4.setBounds(new Rectangle(91, 121, 462, 37));
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
			jTextField5.setBounds(new Rectangle(461, 179, 126, 33));
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
			jTextField6.setBounds(new Rectangle(91, 178, 303, 41));
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
			jTextField7.setBounds(new Rectangle(88, 235, 380, 40));
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
			jTextField8.setBounds(new Rectangle(91, 299, 467, 40));
		}
		return jTextField8;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(104, 371, 98, 37));
			jButton.setText("添加");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (jTextField.getText().equals("")
							|| jTextField1.getText().equals("")
							|| jTextField2.getText().equals("")
							|| jTextField3.getText().equals("")
							|| jTextField4.getText().equals("")
							|| jTextField5.getText().equals("")
							|| jTextField6.getText().equals("")
							|| jTextField7.getText().equals("")
							|| jTextField8.getText().equals("")) {
						JOptionPane.showMessageDialog(SpxInxi.this, "信息不能为空");
					} else {
						if (daosp.spnamecz(jTextField1.getText())) {

							JOptionPane.showMessageDialog(SpxInxi.this,
									"商品信息添加失败，存在同商品", "商品添加信息",
									JOptionPane.INFORMATION_MESSAGE);

						} else {

							SpBean sp = new SpBean();
							sp.setId(jTextField.getText());
							sp.setSpsname(jTextField1.getText());
							sp.setSpjc(jTextField2.getText());
							sp.setMadein(jTextField3.getText());
							sp.setUnit(jTextField4.getText());
							sp.setGuige(jTextField5.getText());
							sp.setPizhunwenhao(jTextField6.getText());
							sp.setBeizhu(jTextField7.getText());
							sp.setGysmc(jTextField8.getText());

							if (daosp.insertsp(sp)) {

								JOptionPane.showMessageDialog(SpxInxi.this,
										"已成功添加信息", "商品添加信息",
										JOptionPane.INFORMATION_MESSAGE);
							getspxlk();
							} else {
								JOptionPane.showMessageDialog(SpxInxi.this,
										"商品信息添加失败", "商品添加信息",
										JOptionPane.INFORMATION_MESSAGE);

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
			jButton1.setBounds(new Rectangle(285, 369, 96, 37));
			jButton1.setText("重填");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {

					jTextField.setText("");
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");
					jTextField4.setText("");
					jTextField5.setText("");
					jTextField6.setText("");
					jTextField7.setText("");
					jTextField8.setText("");

				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jPanel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(1, 356, 98, 35));
			jLabel12.setText("选择商品名称：");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(89, 62, 233, 43));
			jLabel10.setText("");
			jLabel81 = new JLabel();
			jLabel81.setBounds(new Rectangle(4, 299, 80, 38));
			jLabel81.setText("\u4f9b\u5e94\u5546\u540d\u79f0\uff1a");
			jLabel71 = new JLabel();
			jLabel71.setBounds(new Rectangle(7, 239, 58, 31));
			jLabel71.setText("\u5907\u6ce8\uff1a");
			jLabel61 = new JLabel();
			jLabel61.setBounds(new Rectangle(0, 184, 70, 29));
			jLabel61.setText("\u6279\u51c6\u6587\u53f7\uff1a");
			jLabel51 = new JLabel();
			jLabel51.setBounds(new Rectangle(402, 182, 52, 30));
			jLabel51.setText("\u89c4\u683c\uff1a");
			jLabel41 = new JLabel();
			jLabel41.setBounds(new Rectangle(2, 127, 66, 30));
			jLabel41.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel41.setText("\u5355\u4f4d\uff1a");
			jLabel41.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel31 = new JLabel();
			jLabel31.setBounds(new Rectangle(330, 15, 54, 27));
			jLabel31.setText("\u4ea7\u5730\uff1a");
			jLabel21 = new JLabel();
			jLabel21.setBounds(new Rectangle(333, 73, 60, 27));
			jLabel21.setText("\u5546\u54c1\u7b80\u79f0:");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(7, 15, 67, 28));
			jLabel11.setText("\u5546\u54c1\u7f16\u53f7\uff1a");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(7, 66, 58, 28));
			jLabel9.setText("\u5546\u54c1\u540d\u79f0:");
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBounds(new Rectangle(2, -1, 585, 437));
			jPanel2.add(jLabel9, gridBagConstraints1);
			jPanel2.add(jLabel11, null);
			jPanel2.add(getJTextField9(), null);
			jPanel2.add(jLabel21, null);
			jPanel2.add(getJTextField21(), null);
			jPanel2.add(jLabel31, null);
			jPanel2.add(getJTextField31(), null);
			jPanel2.add(jLabel41, null);
			jPanel2.add(getJTextField41(), null);
			jPanel2.add(jLabel51, null);
			jPanel2.add(getJTextField51(), null);
			jPanel2.add(jLabel61, null);
			jPanel2.add(getJTextField61(), null);
			jPanel2.add(jLabel71, null);
			jPanel2.add(getJTextField71(), null);
			jPanel2.add(jLabel81, null);
			jPanel2.add(getJTextField81(), null);
			jPanel2.add(jLabel10, null);
			jPanel2.add(getJComboBox(), null);
			jPanel2.add(jLabel12, null);
			jPanel2.add(getJButton2(), null);
			jPanel2.add(getJButton3(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jTextField9
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField9() {
		if (jTextField9 == null) {
			jTextField9 = new JTextField();
			jTextField9.setBounds(new Rectangle(90, 12, 226, 38));
		}
		return jTextField9;
	}

	/**
	 * This method initializes jTextField21
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField21() {
		if (jTextField21 == null) {
			jTextField21 = new JTextField();
			jTextField21.setBounds(new Rectangle(401, 68, 184, 40));
		}
		return jTextField21;
	}

	/**
	 * This method initializes jTextField31
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField31() {
		if (jTextField31 == null) {
			jTextField31 = new JTextField();
			jTextField31.setBounds(new Rectangle(400, 13, 181, 40));
		}
		return jTextField31;
	}

	/**
	 * This method initializes jTextField41
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField41() {
		if (jTextField41 == null) {
			jTextField41 = new JTextField();
			jTextField41.setBounds(new Rectangle(91, 121, 462, 37));
		}
		return jTextField41;
	}

	/**
	 * This method initializes jTextField51
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField51() {
		if (jTextField51 == null) {
			jTextField51 = new JTextField();
			jTextField51.setBounds(new Rectangle(461, 179, 126, 33));
		}
		return jTextField51;
	}

	/**
	 * This method initializes jTextField61
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField61() {
		if (jTextField61 == null) {
			jTextField61 = new JTextField();
			jTextField61.setBounds(new Rectangle(91, 178, 303, 41));
		}
		return jTextField61;
	}

	/**
	 * This method initializes jTextField71
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField71() {
		if (jTextField71 == null) {
			jTextField71 = new JTextField();
			jTextField71.setBounds(new Rectangle(88, 235, 466, 40));
		}
		return jTextField71;
	}

	/**
	 * This method initializes jTextField81
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField81() {
		if (jTextField81 == null) {
			jTextField81 = new JTextField();
			jTextField81.setBounds(new Rectangle(91, 299, 466, 40));
		}
		return jTextField81;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			getspxlk();
			jComboBox.setBounds(new Rectangle(111, 353, 344, 35));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					SpBean sp4 = new SpBean();
					sp4 = daosp.getspInfos(e.getItem().toString());
					jTextField9.setText(sp4.getId());
					jLabel10.setText(sp4.getSpsname());
					jTextField21.setText(sp4.getSpjc());
					jTextField31.setText(sp4.getMadein());
					jTextField41.setText(sp4.getUnit());
					jTextField51.setText(sp4.getGuige());
					jTextField61.setText(sp4.getPizhunwenhao());
					jTextField71.setText(sp4.getBeizhu());
					jTextField81.setText(sp4.getGysmc());

					// System.out.println("itemStateChanged()"); // TODO
					// Auto-generated Event stub itemStateChanged()
				}
			});
		}
		return jComboBox;
	}

	public void getspxlk(){
		
		jComboBox.removeAllItems();
		List list3=daosp.spxlkchaxun();
	  
		Iterator ite=list3.iterator();
		
		while(ite.hasNext()){
	
			jComboBox.addItem(ite.next());
	
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
			jButton2.setBounds(new Rectangle(179, 397, 91, 29));
			jButton2.setText("修改信息");
			jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {

					if (jTextField9.getText().equals("")
							|| jLabel10.getText().equals("")
							|| jTextField31.getText().equals("")
							|| jTextField21.getText().equals("")
							|| jTextField41.getText().equals("")
							|| jTextField51.getText().equals("")
							|| jTextField61.getText().equals("")
							|| jTextField71.getText().equals("")
							|| jTextField81.getText().equals("")) {

						JOptionPane.showMessageDialog(SpxInxi.this, "修改信息不能为空");

					}else{
						SpBean sp5=new SpBean();
						sp5.setId(jTextField9.getText());
						sp5.setSpsname(jLabel10.getText());
						sp5.setSpjc(jTextField21.getText());
						sp5.setMadein(jTextField31.getText());
						sp5.setUnit(jTextField41.getText());
						sp5.setGuige(jTextField51.getText());
					   sp5.setPizhunwenhao(	jTextField61.getText());
					   sp5.setBeizhu(	jTextField71.getText());
					 	sp5.setGysmc(jTextField81.getText());
						if (daosp.updatexiugaisp(sp5)) {
							JOptionPane.showMessageDialog(
									SpxInxi.this, "修改信息成功",
									"商品信息修改与删除",
					         JOptionPane.INFORMATION_MESSAGE);
							getspxlk();
						} else
							JOptionPane.showMessageDialog(
									SpxInxi.this, "修改信息失败",
									"商品信息修改与删除",
									JOptionPane.INFORMATION_MESSAGE);

					}

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
			jButton3.setBounds(new Rectangle(337, 396, 86, 29));
			jButton3.setText("删除商品");
			jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					

					SpBean sp7 = new SpBean();
					sp7.setId(jTextField9.getText());
					sp7.setSpsname(jLabel10.getText());
					sp7.setSpjc(jTextField21.getText());
					sp7.setMadein(jTextField31.getText());
					;
					sp7.setUnit(jTextField41.getText());
					sp7.setGuige(jTextField51.getText());
					sp7.setPizhunwenhao(jTextField61.getText());
					sp7.setBeizhu(jTextField71.getText());
					sp7.setGysmc(jTextField81.getText());
					
					if (JOptionPane.showConfirmDialog(null, "删除此商品？", "操作提示",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
					
					if (daosp.updatedeletesp(sp7)) {

						JOptionPane.showMessageDialog(SpxInxi.this,
								"删除商品成功", "商品信息修改与删除",
								JOptionPane.INFORMATION_MESSAGE);
						 getspxlk();
					} else {
						JOptionPane.showMessageDialog(SpxInxi.this,
								"删除商品失败", "商品信息修改与删除",
								JOptionPane.INFORMATION_MESSAGE);

					
					
					
					}
					
				}
			});
		
		
		
		
		}
		return jButton3;
	}

}
