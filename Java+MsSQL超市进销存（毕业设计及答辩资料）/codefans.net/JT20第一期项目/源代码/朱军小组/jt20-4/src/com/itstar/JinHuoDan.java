package com.itstar;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

public class JinHuoDan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel1 = null;
	private JTextField jTextField = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField1 = null;
	private JLabel jLabel3 = null;
	private JTextField jTextField2 = null;
	private JScrollPane jScrollPane1 = null;
	private JScrollPane jScrollPane2 = null;
	private JTable jTable = null;
	private DefaultTableModel dddd;
	private JLabel jLabel4 = null;
	private JTextField jTextField3 = null;
	private JLabel jLabel5 = null;
	private JTextField jTextField4 = null;
	private JLabel jLabel6 = null;
	private JTextField jTextField5 = null;
	private JButton jButton = null;
	/**
	 * This is the default constructor
	 */
	public JinHuoDan() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(600, 500);
		this.setContentPane(getJPanel());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(348, 374, 69, 24));
			jLabel6.setText("品种数量：");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(9, 408, 71, 22));
			jLabel5.setText("合计金额：");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(6, 366, 75, 26));
			jLabel4.setText("货品总数：");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(347, 55, 61, 26));
			jLabel3.setText("联系人：");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(11, 57, 66, 25));
			jLabel2.setText("进货时间：");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(347, 6, 56, 30));
			jLabel1.setText("经手人：");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("供应商：");
			jLabel.setBounds(new Rectangle(9, 9, 61, 30));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, gridBagConstraints);
			jPanel.add(getJComboBox(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(jLabel3, null);
			jPanel.add(getJTextField2(), null);
			jPanel.add(getJScrollPane1(), null);
			jPanel.add(jLabel4, null);
			jPanel.add(getJTextField3(), null);
			jPanel.add(jLabel5, null);
			jPanel.add(getJTextField4(), null);
			jPanel.add(jLabel6, null);
			jPanel.add(getJTextField5(), null);
			jPanel.add(getJButton(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(81, 9, 256, 34));
		}
		return jComboBox;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(414, 11, 174, 28));
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
			jTextField1.setBounds(new Rectangle(82, 53, 255, 31));
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
			jTextField2.setBounds(new Rectangle(415, 48, 174, 31));
		}
		return jTextField2;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(2, 88, 592, 272));
			jScrollPane1.setViewportView(getJScrollPane2());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJTable());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		dddd=(DefaultTableModel) jTable.getModel();
		cshbge();
			
		
		
		}
		return jTable;
	}
public void cshbge(){
	
	String[] columnNames = {"商品名称", "商品编号", "产地", "单位", "规格", "单价",
			"数量", "批号", "批准文号"};
	dddd.setColumnIdentifiers(columnNames);
	String[][] date=new String[1][8];
	date[0][0]="a";
	date[0][1]="b";
	date[0][2]="b";
	date[0][3]="b";
	date[0][4]="b";
	date[0][5]="b";
	date[0][6]="b";
	date[0][7]="b";
	
	dddd.setDataVector(date, columnNames);
	                                            

}

/**
 * This method initializes jTextField3	
 * 	
 * @return javax.swing.JTextField	
 */
private JTextField getJTextField3() {
	if (jTextField3 == null) {
		jTextField3 = new JTextField();
		jTextField3.setBounds(new Rectangle(98, 370, 232, 26));
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
		jTextField4.setBounds(new Rectangle(100, 407, 231, 27));
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
		jTextField5.setBounds(new Rectangle(428, 373, 162, 28));
	}
	return jTextField5;
}

/**
 * This method initializes jButton	
 * 	
 * @return javax.swing.JButton	
 */
private JButton getJButton() {
	if (jButton == null) {
		jButton = new JButton();
		jButton.setBounds(new Rectangle(347, 405, 66, 21));
		jButton.setText("添加");
	}
	return jButton;
}



}  //  @jve:decl-index=0:visual-constraint="65,28"
