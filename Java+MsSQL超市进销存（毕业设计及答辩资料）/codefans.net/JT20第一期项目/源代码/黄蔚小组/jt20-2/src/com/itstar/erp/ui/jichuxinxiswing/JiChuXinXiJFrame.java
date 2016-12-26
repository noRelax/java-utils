package com.itstar.erp.ui.jichuxinxiswing;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.Rectangle;
import javax.swing.JLabel;
import javax  .swing.JButton;
import javax.swing.border.BevelBorder;

import com.itstar.erp.ui.jichuxinxi.kehuswing.KeHuShanChu;
import com.itstar.erp.ui.jichuxinxi.kehuswing.KeHuTianJia;
import com.itstar.erp.ui.jichuxinxi.kehuswing.KeHuXiuGai;
import com.itstar.erp.ui.jichuxinxi.productswing.ProShanChu;
import com.itstar.erp.ui.jichuxinxi.productswing.ProTianJia;
import com.itstar.erp.ui.jichuxinxi.productswing.ProXiuGai;
import com.itstar.erp.ui.jichuxinxi.producttypeswing.ProTypeShanChu;
import com.itstar.erp.ui.jichuxinxi.producttypeswing.ProTypeTianJia;
import com.itstar.erp.ui.jichuxinxi.producttypeswing.ProTypeXiuGai;
import com.itstar.erp.ui.jichuxinxi.supplyswing.SupplyShanChu;
import com.itstar.erp.ui.jichuxinxi.supplyswing.SupplyTianJia;
import com.itstar.erp.ui.jichuxinxi.supplyswing.SupplyXiuGai;
import com.itstar.erp.ui.jichuxinxi.yewuyuanswing.YwyShanChu;
import com.itstar.erp.ui.jichuxinxi.yewuyuanswing.YwyTianJia;
import com.itstar.erp.ui.jichuxinxi.yewuyuanswing.YwyXiuGai;



public class JiChuXinXiJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JPanel jPanel5 = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JLabel jLabel2 = null;
	private JButton jButton2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JButton tianjiakehuButton = null;
	private JButton xiugaikehuButton = null;
	private JButton shanchukehuButton = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JButton ywytianjiaButton3 = null;
	private JButton ywyxiugaiButton3 = null;
	private JButton ywyshanchuButton3 = null;
	private JLabel jLabel9 = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JButton tianjia = null;
	private JButton tiaozheng = null;
	private JButton shanchu = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	private JLabel jLabel14 = null;
	private JButton tianjiapro = null;
	private JButton xiugaipro = null;
	private JButton shanchupro = null;
	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(3, 0, 690, 469));
			jTabbedPane.addTab("供应商资料管理", null, getJPanel(), null);
			jTabbedPane.addTab("客户资料管理", null, getJPanel2(), null);
			jTabbedPane.addTab("业务员资料管理", null, getJPanel3(), null);
			jTabbedPane.addTab("商品类别管理", null, getJPanel5(), null);
			jTabbedPane.addTab("商品信息管理", null, getJPanel1(), null);
			jTabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));
//			jTabbedPane.setBackground(Color.blue);
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
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(121, 147, 107, 37));
			jLabel2.setText(" 删除供应商资料");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(121, 87, 108, 35));
			jLabel1.setText("  修改供应商资料");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(117, 28, 112, 39));
			jLabel.setText("    添加供应商");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(getJButton2(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(129, 187, 89, 28));
			jLabel5.setText("  删除客户资料");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(130, 120, 86, 34));
			jLabel4.setText("  修改客户资料");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(124, 55, 91, 40));
			jLabel3.setText("   添加客户");
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.add(jLabel3, null);
			jPanel2.add(jLabel4, null);
			jPanel2.add(jLabel5, null);
			jPanel2.add(getTianjiakehuButton(), null);
			jPanel2.add(getXiugaikehuButton(), null);
			jPanel2.add(getShanchukehuButton(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(165, 200, 93, 35));
			jLabel8.setText("删除业务员资料");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(165, 125, 94, 39));
			jLabel7.setText("修改业务员资料");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(166, 39, 94, 44));
			jLabel6.setText("添加业务员");
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.add(jLabel6, null);
			jPanel3.add(jLabel7, null);
			jPanel3.add(jLabel8, null);
			jPanel3.add(getYwytianjiaButton3(), null);
			jPanel3.add(getYwyxiugaiButton3(), null);
			jPanel3.add(getYwyshanchuButton3(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel5	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(145, 184, 116, 33));
			jLabel11.setText("    删除商品类别");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(141, 107, 117, 37));
			jLabel10.setText("   商品类别资料调整");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(139, 36, 114, 39));
			jLabel9.setText("   增加商品类别");
			jPanel5 = new JPanel();
			jPanel5.setLayout(null);
			jPanel5.add(jLabel9, null);
			jPanel5.add(jLabel10, null);
			jPanel5.add(jLabel11, null);
			jPanel5.add(getTianjia(), null);
			jPanel5.add(getTiaozheng(), null);
			jPanel5.add(getShanchu(), null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(261, 34, 70, 32));
			jButton.setText("添加");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					SupplyTianJia supplyTianJia=new SupplyTianJia();
					supplyTianJia.setVisible(true);
					supplyTianJia.setSize(500,300);
					supplyTianJia.setLocationRelativeTo(null);
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
			jButton1.setBounds(new Rectangle(263, 90, 68, 30));
			jButton1.setText("修改");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					SupplyXiuGai supplyXiuGai=new SupplyXiuGai();
					supplyXiuGai.setVisible(true);
					supplyXiuGai.setSize(500,300);
					supplyXiuGai.setLocationRelativeTo(null);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(267, 151, 61, 28));
			jButton2.setText("删除");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					SupplyShanChu supplyShanChu=new SupplyShanChu();
					supplyShanChu.setVisible(true);
					supplyShanChu.setSize(500,300);
					supplyShanChu.setLocationRelativeTo(null);
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes tianjiakehuButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTianjiakehuButton() {
		if (tianjiakehuButton == null) {
			tianjiakehuButton = new JButton();
			tianjiakehuButton.setBounds(new Rectangle(254, 57, 69, 38));
			tianjiakehuButton.setText("添加");
			tianjiakehuButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					KeHuTianJia kehutianjia=new KeHuTianJia();
					kehutianjia.setVisible(true);
					kehutianjia.setSize(500, 300);
					kehutianjia.setLocationRelativeTo(null);
				}
			});
		}
		return tianjiakehuButton;
	}

	/**
	 * This method initializes xiugaikehuButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getXiugaikehuButton() {
		if (xiugaikehuButton == null) {
			xiugaikehuButton = new JButton();
			xiugaikehuButton.setBounds(new Rectangle(257, 123, 67, 27));
			xiugaikehuButton.setText("修改");
			xiugaikehuButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					KeHuXiuGai kehuxiugai=new KeHuXiuGai();
					kehuxiugai.setVisible(true);
					kehuxiugai.setSize(500,300);
					kehuxiugai.setLocationRelativeTo(null);
				}
			});
		}
		return xiugaikehuButton;
	}

	/**
	 * This method initializes shanchukehuButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getShanchukehuButton() {
		if (shanchukehuButton == null) {
			shanchukehuButton = new JButton();
			shanchukehuButton.setBounds(new Rectangle(255, 188, 66, 26));
			shanchukehuButton.setText("删除");
			shanchukehuButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					KeHuShanChu kehushanchu=new KeHuShanChu();
					kehushanchu.setVisible(true);
					kehushanchu.setSize(500,300);
					kehushanchu.setLocationRelativeTo(null);
				}
			});
		}
		return shanchukehuButton;
	}

	/**
	 * This method initializes ywytianjiaButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getYwytianjiaButton3() {
		if (ywytianjiaButton3 == null) {
			ywytianjiaButton3 = new JButton();
			ywytianjiaButton3.setBounds(new Rectangle(302, 44, 79, 36));
			ywytianjiaButton3.setText("添加");
			ywytianjiaButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					YwyTianJia ywytianjia=new YwyTianJia();
					ywytianjia.setVisible(true);
					ywytianjia.setSize(500, 300);
					ywytianjia.setLocationRelativeTo(null);
				}
			});
		}
		return ywytianjiaButton3;
	}

	/**
	 * This method initializes ywyxiugaiButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getYwyxiugaiButton3() {
		if (ywyxiugaiButton3 == null) {
			ywyxiugaiButton3 = new JButton();
			ywyxiugaiButton3.setBounds(new Rectangle(304, 126, 74, 34));
			ywyxiugaiButton3.setText("修改");
			ywyxiugaiButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					YwyXiuGai ywyxiugai=new YwyXiuGai();
					ywyxiugai.setVisible(true);
					ywyxiugai.setLocationRelativeTo(null);
					ywyxiugai.setSize(500,300);
				}
			});
		}
		return ywyxiugaiButton3;
	}

	/**
	 * This method initializes ywyshanchuButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getYwyshanchuButton3() {
		if (ywyshanchuButton3 == null) {
			ywyshanchuButton3 = new JButton();
			ywyshanchuButton3.setBounds(new Rectangle(303, 205, 77, 28));
			ywyshanchuButton3.setText("删除");
			ywyshanchuButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					YwyShanChu ywyshanchu=new YwyShanChu();
					ywyshanchu.setVisible(true);
					ywyshanchu.setSize(500,300);
					ywyshanchu.setLocationRelativeTo(null);
				}
			});
		}
		return ywyshanchuButton3;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(131, 176, 90, 31));
			jLabel14.setText("  删除商品信息");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(127, 114, 92, 33));
			jLabel13.setText("商品进价变动");
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(126, 43, 89, 34));
			jLabel12.setText("   添加商品");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(jLabel12, null);
			jPanel1.add(jLabel13, null);
			jPanel1.add(jLabel14, null);
			jPanel1.add(getTianjiapro(), null);
			jPanel1.add(getXiugaipro(), null);
			jPanel1.add(getShanchupro(), null);
//			jPanel1.setBackground(new Color(215, 223, 194));
		}
		return jPanel1;
	}

	/**
	 * This method initializes tianjia	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTianjia() {
		if (tianjia == null) {
			tianjia = new JButton();
			tianjia.setBounds(new Rectangle(307, 41, 97, 37));
			tianjia.setText("增加");
			tianjia.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ProTypeTianJia protypetianjia=new ProTypeTianJia();
					protypetianjia.setVisible(true);
					protypetianjia.setLocationRelativeTo(null);
					protypetianjia.setSize(500,300);
				}
			});
		}
		return tianjia;
	}

	/**
	 * This method initializes tiaozheng	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTiaozheng() {
		if (tiaozheng == null) {
			tiaozheng = new JButton();
			tiaozheng.setBounds(new Rectangle(309, 104, 95, 36));
			tiaozheng.setText("调整");
			tiaozheng.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ProTypeXiuGai protypexiugai=new ProTypeXiuGai();
					protypexiugai.setVisible(true);
					protypexiugai.setSize(500,300);
					protypexiugai.setLocationRelativeTo(null);
				}
			});
		}
		return tiaozheng;
	}

	/**
	 * This method initializes shanchu	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getShanchu() {
		if (shanchu == null) {
			shanchu = new JButton();
			shanchu.setBounds(new Rectangle(308, 183, 92, 32));
			shanchu.setText("删除");
			shanchu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ProTypeShanChu protypeshanchu=new ProTypeShanChu();
					protypeshanchu.setVisible(true);
					protypeshanchu.setSize(500,300);
					protypeshanchu.setLocationRelativeTo(null);
				}
			});
		}
		return shanchu;
	}

	/**
	 * This method initializes tianjiapro	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTianjiapro() {
		if (tianjiapro == null) {
			tianjiapro = new JButton();
			tianjiapro.setBounds(new Rectangle(279, 46, 80, 35));
			tianjiapro.setText("添加");
			tianjiapro.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ProTianJia protianjia =new ProTianJia();
					protianjia.setVisible(true);
					protianjia.setSize(500,300);
					protianjia.setLocationRelativeTo(null);
				}
			});
		}
		return tianjiapro;
	}

	/**
	 * This method initializes xiugaipro	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getXiugaipro() {
		if (xiugaipro == null) {
			xiugaipro = new JButton();
			xiugaipro.setBounds(new Rectangle(277, 114, 78, 32));
			xiugaipro.setText("修改");
			xiugaipro.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ProXiuGai proxiugai=new ProXiuGai();
					proxiugai.setVisible(true);
					proxiugai.setLocationRelativeTo(null);
					proxiugai.setSize(500,300);
				}
			});
		}
		return xiugaipro;
	}

	/**
	 * This method initializes shanchupro	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getShanchupro() {
		if (shanchupro == null) {
			shanchupro = new JButton();
			shanchupro.setBounds(new Rectangle(275, 177, 76, 31));
			shanchupro.setText("删除");
			shanchupro.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ProShanChu proshanchu=new ProShanChu();
					proshanchu.setVisible(true);
					proshanchu.setSize(500,300);
					proshanchu.setLocationRelativeTo(null);
				}
			});
		}
		return shanchupro;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JiChuXinXiJFrame thisClass = new JiChuXinXiJFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public JiChuXinXiJFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(700,500);
		this.setContentPane(getJContentPane());
		this.setTitle("基本信息管理");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTabbedPane(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="131,0"
