package com.itstar.erp;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JButton;

public class SellEAP extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField jTextField1 = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JTextField jTextField4 = null;
	private JComboBox jComboBox = null;
	private JComboBox jComboBox1 = null;
	private JTextField jTextField = null;
	private JLabel jLabel6 = null;
	private JTextArea jTextArea = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(109, 56, 116, 25));
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
			jTextField2.setBounds(new Rectangle(108, 97, 116, 25));
		}
		return jTextField2;
	}

	/**
	 * This method initializes jTextField4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setBounds(new Rectangle(333, 55, 116, 25));
		}
		return jTextField4;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(333, 18, 116, 25));
		}
		return jComboBox;
	}

	/**
	 * This method initializes jComboBox1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.setBounds(new Rectangle(108, 18, 116, 25));
		}
		return jComboBox1;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(334, 95, 116, 25));
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(new Rectangle(114, 141, 336, 69));
			jTextArea.add(new JScrollBar());
		}
		return jTextArea;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(93, 233, 109, 32));
			jButton.setText("OK");
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
			jButton1.setBounds(new Rectangle(264, 234, 109, 32));
			jButton1.setText("RESET");
		}
		return jButton1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * This is the default constructor
	 */
	public SellEAP() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel6 = new JLabel();
		jLabel6.setBounds(new Rectangle(8, 138, 96, 43));
		jLabel6.setText("Count\nDiscribe:");
		jLabel5 = new JLabel();
		jLabel5.setBounds(new Rectangle(231, 94, 94, 28));
		jLabel5.setText("SWareCount:");
		jLabel4 = new JLabel();
		jLabel4.setBounds(new Rectangle(231, 51, 94, 28));
		jLabel4.setText("SWareName:");
		jLabel3 = new JLabel();
		jLabel3.setBounds(new Rectangle(232, 16, 94, 28));
		jLabel3.setText("SWareNumber:");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(8, 94, 94, 28));
		jLabel2.setText("FWareCount:");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(9, 51, 94, 28));
		jLabel1.setText("FWareName:");
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(8, 17, 94, 28));
		jLabel.setText("FWareNumber:");
		this.setSize(498, 284);
		this.setLayout(null);
		this.add(jLabel, null);
		this.add(jLabel1, null);
		this.add(getJTextField1(), null);
		this.add(jLabel2, null);
		this.add(getJTextField2(), null);
		this.add(jLabel3, null);
		this.add(jLabel4, null);
		this.add(jLabel5, null);
		this.add(getJTextField4(), null);
		this.add(getJComboBox(), null);
		this.add(getJComboBox1(), null);
		this.add(getJTextField(), null);
		this.add(jLabel6, null);
		this.add(getJTextArea(), null);
		this.add(getJButton(), null);
		this.add(getJButton1(), null);
	}

}  //  @jve:decl-index=0:visual-constraint="15,10"
