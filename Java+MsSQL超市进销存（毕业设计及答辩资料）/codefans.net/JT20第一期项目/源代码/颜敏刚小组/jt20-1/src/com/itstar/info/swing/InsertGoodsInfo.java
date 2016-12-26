package com.itstar.info.swing;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JList;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.Point;
import javax.swing.JToggleButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;

public class InsertGoodsInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JList jList = null;
	private JList jList1 = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel5 = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField2 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JTextField jTextField3 = null;
	private JLabel jLabel8 = null;
	private JTextField jTextField4 = null;
	private JLabel jLabel9 = null;
	private JTextField jTextField5 = null;
	private JLabel jLabel10 = null;
	private JTextField jTextField6 = null;
	private JButton jButton6 = null;
	private JButton jButton5 = null;
	/**
	 * This is the default constructor
	 */
	public InsertGoodsInfo() {
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
		Dimension d = getToolkit().getScreenSize();
		setBounds((d.width-600)/2,(d.height-450)/2,600,450);
	}
	
	public static void main(String[] args){
		InsertGoodsInfo info = new InsertGoodsInfo();
		info.setVisible(true);
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
			jContentPane.setPreferredSize(new Dimension(0, 0));
			jContentPane.add(getJPanel(), BorderLayout.NORTH);
			jContentPane.add(getJPanel1(), BorderLayout.SOUTH);
			jContentPane.add(getJPanel2(), BorderLayout.CENTER);
			jContentPane.add(getJPanel3(), BorderLayout.EAST);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel2 = new JLabel();
			jLabel2.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jLabel2.setPreferredSize(new Dimension(65, 20));
			jLabel2.setBounds(new Rectangle(70, 15, 80, 25));
			jLabel2.setText("商品编号：");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setPreferredSize(new Dimension(0, 50));
			jPanel.add(jLabel2, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJButton(), null);
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
			jPanel1.setPreferredSize(new Dimension(0, 50));
			jPanel1.add(getJButton1(), null);
			jPanel1.add(getJButton2(), null);
			jPanel1.add(getJButton3(), null);
			jPanel1.add(getJButton4(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 0;
			jLabel1 = new JLabel();
			jLabel1.setText("商品名称：");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("商品类别：");
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.setPreferredSize(new Dimension(300, 0));
			jPanel2.add(jLabel, gridBagConstraints);
			jPanel2.add(jLabel1, gridBagConstraints1);
			jPanel2.add(getJList(), gridBagConstraints2);
			jPanel2.add(getJList1(), gridBagConstraints3);
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
			jLabel10 = new JLabel();
			jLabel10.setText("备注：");
			jLabel10.setLocation(new Point(11, 215));
			jLabel10.setSize(new Dimension(65, 25));
			jLabel9 = new JLabel();
			jLabel9.setText("供应商：");
			jLabel9.setLocation(new Point(11, 185));
			jLabel9.setSize(new Dimension(65, 25));
			jLabel8 = new JLabel();
			jLabel8.setText("产地：");
			jLabel8.setSize(new Dimension(65, 25));
			jLabel8.setLocation(new Point(11, 155));
			jLabel7 = new JLabel();
			jLabel7.setText("单位：");
			jLabel7.setLocation(new Point(11, 125));
			jLabel7.setSize(new Dimension(65, 25));
			jLabel6 = new JLabel();
			jLabel6.setText("商品名称：");
			jLabel6.setLocation(new Point(11, 95));
			jLabel6.setSize(new Dimension(65, 25));
			jLabel5 = new JLabel();
			jLabel5.setText("商品编号：");
			jLabel5.setSize(new Dimension(65, 25));
			jLabel5.setLocation(new Point(11, 65));
			jLabel4 = new JLabel();
			jLabel4.setText("类别：");
			jLabel4.setSize(new Dimension(65, 25));
			jLabel4.setPreferredSize(new Dimension(38, 18));
			jLabel4.setLocation(new Point(11, 35));
			jLabel3 = new JLabel();
			jLabel3.setText("详细资料：");
			jLabel3.setLocation(new Point(11, 3));
			jLabel3.setSize(new Dimension(80, 25));
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setPreferredSize(new Dimension(250, 1));
			jPanel3.add(jLabel3, null);
			jPanel3.add(jLabel4, null);
			jPanel3.add(getJComboBox(), null);
			jPanel3.add(jLabel5, null);
			jPanel3.add(getJTextField1(), null);
			jPanel3.add(getJTextField2(), null);
			jPanel3.add(jLabel6, null);
			jPanel3.add(jLabel7, null);
			jPanel3.add(getJTextField3(), null);
			jPanel3.add(jLabel8, null);
			jPanel3.add(getJTextField4(), null);
			jPanel3.add(jLabel9, null);
			jPanel3.add(getJTextField5(), null);
			jPanel3.add(jLabel10, null);
			jPanel3.add(getJTextField6(), null);
			jPanel3.add(getJButton6(), null);
			jPanel3.add(getJButton5(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}
		return jList;
	}

	/**
	 * This method initializes jList1	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList1() {
		if (jList1 == null) {
			jList1 = new JList();
			jList1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		}
		return jList1;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setPreferredSize(new Dimension(4, 20));
			jTextField.setBounds(new Rectangle(160, 15, 120, 25));
		}
		return jTextField;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setPreferredSize(new Dimension(60, 20));
			jButton.setBounds(new Rectangle(290, 15, 80, 25));
			jButton.setText("查询");
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
			jButton1.setBounds(new Rectangle(90, 16, 90, 25));
			jButton1.setText("新增");
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
			jButton2.setBounds(new Rectangle(210, 16, 90, 25));
			jButton2.setText("修改");
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
			jButton3.setBounds(new Rectangle(330, 16, 90, 25));
			jButton3.setText("删除");
		}
		return jButton3;
	}

	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setBounds(new Rectangle(450, 16, 90, 25));
			jButton4.setText("退出");
		}
		return jButton4;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setSize(new Dimension(150, 25));
			jComboBox.setLocation(new Point(80, 35));
		}
		return jComboBox;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setSize(new Dimension(150, 25));
			jTextField1.setLocation(new Point(80, 65));
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
			jTextField2.setLocation(new Point(80, 95));
			jTextField2.setSize(new Dimension(150, 25));
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
			jTextField3.setSize(new Dimension(150, 25));
			jTextField3.setLocation(new Point(80, 125));
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
			jTextField4.setLocation(new Point(80, 155));
			jTextField4.setSize(new Dimension(150, 25));
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
			jTextField5.setSize(new Dimension(150, 25));
			jTextField5.setLocation(new Point(80, 185));
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
			jTextField6.setLocation(new Point(80, 215));
			jTextField6.setSize(new Dimension(150, 25));
		}
		return jTextField6;
	}

	/**
	 * This method initializes jButton6	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("重置");
			jButton6.setSize(new Dimension(80, 25));
			jButton6.setLocation(new Point(125, 280));
		}
		return jButton6;
	}

	/**
	 * This method initializes jButton5	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("保存");
			jButton5.setLocation(new Point(35, 280));
			jButton5.setSize(new Dimension(80, 25));
		}
		return jButton5;
	}

}  //  @jve:decl-index=0:visual-constraint="-109,-93"
