/**
 * Created on 2009-8-20
 */
package com.itstar.erp.count;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.itstar.erp.bean.Goods;


/**
 * 显示查找商品信息
 * 
 * @author Zhang Li 
 */
public class ShowGoods extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable1 = null;
	private JLabel jLabel_Name = null;
	private JTextField jTextField_Name = null;
	private JButton jButton = null;
	/**
	 * This is the default constructor
	 */
	public ShowGoods() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
//		this.setSize(640, 480);
		this.setBounds(150, 150, 640, 480);
		this.setContentPane(getJContentPane());
		this.setTitle("商品信息");
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel_Name = new JLabel();
			jLabel_Name.setBounds(new Rectangle(5, 422, 80, 22));
			jLabel_Name.setText("请输入姓名：");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJScrollPane1(), null);
			jContentPane.add(jLabel_Name, null);
			jContentPane.add(getJTextField_Name(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(0, 0, 630, 360));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		String[] showName= {"名称","产地","供应商","进货价","出货价"};
		new Goods();
		if (jTable == null) {
			jTable = new JTable(Goods.selectGoods(),showName);
		}
		return jTable;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(0, 360, 630, 60));
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTable1	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable1(String gName) {
		String[] showName= {"名称","产地","供应商","进货价","出货价"};
		new Goods();

			jTable1 = new JTable(Goods.selectGoods(gName),showName);
		
		return jTable1;
	}

	/**
	 * This method initializes jTextField_Name	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_Name() {
		
		if (jTextField_Name == null) {
			jTextField_Name = new JTextField();
			jTextField_Name.setBounds(new Rectangle(90, 422, 420, 22));
		}
		return jTextField_Name;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(520, 422, 100, 22));
			jButton.setText("查找");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					jScrollPane1.setViewportView(getJTable1(jTextField_Name.getText()));
					jScrollPane1.setVisible(true);
				}
			});
		}
		return jButton;
	}

}
