package com.itstar.info.swing;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.itstar.info.bean.GoodsBean;
import com.itstar.info.domain.DoGoodsInfo;
import com.itstar.info.domain.DoSeverInfo;

import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Point;

public class GoodsInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JTextField jTextField = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField2 = null;
	private JTextField jTextField3 = null;
	private JTextField jTextField4 = null;
	private JTextField jTextField5 = null;
	private JTextField jTextField6 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	private JTextField jTextField7 = null;
	private JTextField jTextField8 = null;
	private JTextField jTextField9 = null;
	private JTextField jTextField10 = null;
	private JTextField jTextField11 = null;
	private JTextField jTextField12 = null;
	private JTextField jTextField13 = null;
	private JLabel jLabel14 = null;
	private JComboBox jComboBox = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JLabel jLabel15 = null;
	private JComboBox jComboBox1 = null;
	private JComboBox jComboBox2 = null;
	/**
	 * This is the default constructor
	 */
	public GoodsInfo() {
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
		this.setTitle("商品管理");
		this.setLocation(new Point(450, 250));
		this.setSize(new Dimension(500, 400));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJTabbedPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("新增商品", null, getJPanel(), null);
			jTabbedPane.addTab("商品修改和删除", null, getJPanel1(), null);
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
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(30, 235, 100, 25));
			jLabel6.setText("备注：");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(30, 200, 100, 25));
			jLabel5.setText("供应商：");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(30, 165, 100, 25));
			jLabel4.setText("计量单位：");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(30, 130, 100, 25));
			jLabel3.setText("商品类型：");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(30, 95, 100, 25));
			jLabel2.setText("产址：");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(30, 60, 100, 25));
			jLabel1.setText("商品名称：");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(30, 25, 100, 25));
			jLabel.setText("商品编号：");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel2, null);
			jPanel.add(jLabel3, null);
			jPanel.add(jLabel4, null);
			jPanel.add(jLabel5, null);
			jPanel.add(jLabel6, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(getJTextField2(), null);
			jPanel.add(getJTextField3(), null);
			jPanel.add(getJTextField4(), null);
			jPanel.add(getJTextField5(), null);
			jPanel.add(getJTextField6(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
			jPanel.add(getJComboBox2(), null);
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
			jLabel15 = new JLabel();
			jLabel15.setBounds(new Rectangle(235, 261, 100, 25));
			jLabel15.setText("选择供应商：");
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(30, 260, 100, 25));
			jLabel14.setText("选择商品：");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(30, 220, 100, 25));
			jLabel13.setText("备注：");
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(30, 180, 100, 25));
			jLabel12.setText("供应商：");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(235, 140, 100, 25));
			jLabel11.setText("计量单位：");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(30, 140, 100, 25));
			jLabel10.setText("商品类型：");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(30, 100, 100, 25));
			jLabel9.setText("产址：");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(30, 60, 100, 25));
			jLabel8.setText("商品名称：");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(30, 20, 100, 25));
			jLabel7.setText("商品编号：");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(jLabel7, null);
			jPanel1.add(jLabel8, null);
			jPanel1.add(jLabel9, null);
			jPanel1.add(jLabel10, null);
			jPanel1.add(jLabel11, null);
			jPanel1.add(jLabel12, null);
			jPanel1.add(jLabel13, null);
			jPanel1.add(getJTextField7(), null);
			jPanel1.add(getJTextField8(), null);
			jPanel1.add(getJTextField9(), null);
			jPanel1.add(getJTextField10(), null);
			jPanel1.add(getJTextField11(), null);
			jPanel1.add(getJTextField12(), null);
			jPanel1.add(getJTextField13(), null);
			jPanel1.add(jLabel14, null);
			jPanel1.add(getJComboBox(), null);
			jPanel1.add(getJButton2(), null);
			jPanel1.add(getJButton3(), null);
			jPanel1.add(jLabel15, null);
			jPanel1.add(getJComboBox1(), null);
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
			jTextField.setBounds(new Rectangle(135, 25, 300, 25));
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
			jTextField1.setBounds(new Rectangle(135, 60, 300, 25));
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
			jTextField2.setBounds(new Rectangle(135, 95, 300, 25));
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
			jTextField3.setBounds(new Rectangle(135, 130, 300, 25));
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
			jTextField4.setBounds(new Rectangle(135, 165, 300, 25));
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
			jTextField5.setBounds(new Rectangle(135, 200, 146, 25));
			jTextField5.setEditable(false);
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
			jTextField6.setBounds(new Rectangle(135, 235, 300, 25));
		}
		return jTextField6;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(100, 280, 100, 30));
			jButton.setText("新增");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					GoodsBean goods=new GoodsBean();
					goods.setGid(jTextField.getText().trim());
					goods.setGname(jTextField1.getText().trim());
					goods.setGproduce(jTextField2.getText().trim());
					goods.setGtype(jTextField3.getText().trim());
					goods.setGunit(jTextField4.getText().trim());
					goods.setGsever(jTextField5.getText().trim());
					//goods.setGsever(jComboBox2.)
					goods.setGannotate(jTextField6.getText().trim());
					DoGoodsInfo dogoods=new DoGoodsInfo();
					dogoods.dogoodsinsert(goods);
					initjComboBox();
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
			jButton1.setBounds(new Rectangle(250, 280, 100, 30));
			jButton1.setText("重置");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					jTextField.setText("");
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");
					jTextField4.setText("");
					jTextField5.setText("");
					jTextField6.setText("");
					jTextField.requestFocus();
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jTextField7	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setBounds(new Rectangle(135, 20, 300, 25));
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
			jTextField8.setBounds(new Rectangle(135, 60, 300, 25));
			jTextField8.setEditable(false);
		}
		return jTextField8;
	}

	/**
	 * This method initializes jTextField9	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField9() {
		if (jTextField9 == null) {
			jTextField9 = new JTextField();
			jTextField9.setBounds(new Rectangle(135, 100, 300, 25));
		}
		return jTextField9;
	}

	/**
	 * This method initializes jTextField10	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField10() {
		if (jTextField10 == null) {
			jTextField10 = new JTextField();
			jTextField10.setBounds(new Rectangle(135, 140, 90, 25));
		}
		return jTextField10;
	}

	/**
	 * This method initializes jTextField11	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField11() {
		if (jTextField11 == null) {
			jTextField11 = new JTextField();
			jTextField11.setBounds(new Rectangle(345, 140, 90, 25));
		}
		return jTextField11;
	}

	/**
	 * This method initializes jTextField12	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField12() {
		if (jTextField12 == null) {
			jTextField12 = new JTextField();
			jTextField12.setBounds(new Rectangle(135, 180, 300, 25));
			jTextField12.setEditable(false);
		}
		return jTextField12;
	}

	/**
	 * This method initializes jTextField13	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField13() {
		if (jTextField13 == null) {
			jTextField13 = new JTextField();
			jTextField13.setBounds(new Rectangle(135, 220, 300, 25));
		}
		return jTextField13;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			initjComboBox();
			jComboBox.setBounds(new Rectangle(135, 260, 90, 25));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){
						DoGoodsInfo dogoods=new DoGoodsInfo();
						List<String> list1=dogoods.dogoodssevercom(e.getItem().toString());
						
						GoodsBean goods=new GoodsBean();	
						goods=dogoods.setgoodscom(e.getItem().toString());
						jTextField7.setText(goods.getGid());
						jTextField8.setText(goods.getGname());
						jTextField9.setText(goods.getGproduce());
						jTextField10.setText(goods.getGtype());
						jTextField11.setText(goods.getGunit());
						jTextField12.setText(goods.getGsever());
						jTextField13.setText(goods.getGannotate());
						jComboBox1.removeAllItems();
						for(String item2:list1){
							jComboBox1.addItem(item2);
						}
					}
				}
			});
		}
		return jComboBox;
	}
	public void initjComboBox(){
		jComboBox.removeAllItems();
		jComboBox.addItem("选择");
		List<String> list=DoGoodsInfo.docom();
		for(String item:list){
			jComboBox.addItem(item);
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
			jButton2.setBounds(new Rectangle(100, 300, 100, 25));
			jButton2.setText("修改");
			jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					GoodsBean goods=new GoodsBean();
					goods.setGid(jTextField7.getText().trim());
					goods.setGname(jTextField8.getText().trim());
					goods.setGproduce(jTextField9.getText().trim());
					goods.setGtype(jTextField10.getText().trim());
					goods.setGunit(jTextField11.getText().trim());
					goods.setGsever(jTextField12.getText().trim());
					goods.setGannotate(jTextField13.getText().trim());
					DoGoodsInfo dogoods=new DoGoodsInfo();
					dogoods.doalter(goods);
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
			jButton3.setBounds(new Rectangle(250, 300, 100, 25));
			jButton3.setText("删除");
			jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					DoGoodsInfo dogoods=new DoGoodsInfo();
					GoodsBean goods=new GoodsBean();
					goods.setGname(jTextField8.getText().trim());
					goods.setGsever(jTextField12.getText().trim());
					dogoods.dodelete(goods);
//					jComboBox1.removeItem(jTextField2.getText().trim());
//					if(jComboBox1.getItemCount()==0){
//						jComboBox.removeItem(jTextField8.getText().trim());
//					}
					initjComboBox();
					//jComboBox.removeItem(ItemEvent.SELECTED);
				}
			});
		}
		return jButton3;
	}

	/**
	 * This method initializes jComboBox1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.setBounds(new Rectangle(345, 260, 90, 25));
			jComboBox1.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){
						DoGoodsInfo dogoods=new DoGoodsInfo();
						String s=jTextField8.getText().trim();
						GoodsBean goods=new GoodsBean();
						goods=dogoods.setgoodssevercom(s,e.getItem().toString());
						System.out.println("----gongyingshang-----"+e.getItem().toString());
						jTextField7.setText(goods.getGid());
						jTextField8.setText(goods.getGname());
						jTextField9.setText(goods.getGproduce());
						jTextField10.setText(goods.getGtype());
						jTextField11.setText(goods.getGunit());
						jTextField12.setText(goods.getGsever());
						jTextField13.setText(goods.getGannotate());
					}
					
				}
			});
		}
		return jComboBox1;
	}

	/**
	 * This method initializes jComboBox2	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox2() {
		if (jComboBox2 == null) {
			jComboBox2 = new JComboBox();
			initjComboBox2();
			jComboBox2.setBounds(new Rectangle(291, 200, 142, 25));
			jComboBox2.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){
						jTextField5.setText(e.getItem().toString());
					}
				}
			});
		}
		return jComboBox2;
	}
	
	public void initjComboBox2(){
		jComboBox2.removeAllItems();
		jComboBox2.addItem("选择");
		List<String> list=DoGoodsInfo.doallsever();
		for(String item:list){
			jComboBox2.addItem(item);
		}
	}

}
