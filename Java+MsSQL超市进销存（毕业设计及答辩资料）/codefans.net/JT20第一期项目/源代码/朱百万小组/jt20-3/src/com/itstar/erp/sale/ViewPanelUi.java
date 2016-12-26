package com.itstar.erp.sale;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewPanelUi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton refresh = null;
	private JButton imp = null;
	private JScrollPane keybroad = null;
	private JTable message = null;
	private JLabel sum = null;
	private JTextField out = null;
	private JButton btOk = null;

	/**
	 * This is the default constructor
	 */
	public ViewPanelUi() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(600, 350);
		this.setContentPane(getJContentPane());
		this.setTitle("���ۿ���");
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
			sum = new JLabel();
			sum.setBounds(new Rectangle(326, 296, 61, 18));
			sum.setText("ӯ���ܶ�");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getRefresh(), null);
			jContentPane.add(getImp(), null);
			jContentPane.add(getKeybroad(), null);
			jContentPane.add(sum, null);
			jContentPane.add(getOut(), null);
			jContentPane.add(getBtOk(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes refresh	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefresh() {
		if (refresh == null) {
			refresh = new JButton();
			refresh.setBounds(new Rectangle(43, 290, 96, 24));
			refresh.setText("ˢ      ��");
		}
		return refresh;
	}

	/**
	 * This method initializes imp	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getImp() {
		if (imp == null) {
			imp = new JButton();
			imp.setBounds(new Rectangle(165, 291, 109, 24));
			imp.setText("����������");
		}
		return imp;
	}

	/**
	 * This method initializes keybroad	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getKeybroad() {
		if (keybroad == null) {
			keybroad = new JScrollPane();
			keybroad.setBounds(new Rectangle(3, 3, 590, 283));
			keybroad.setViewportView(getMessage());
		}
		return keybroad;
	}

	private JTable getMessage() {
		if (message == null) {
			Object data[][]={
			};
	        String colum[]={"��¼��","��Ʒ��","����","�ɱ���","���ۼ�","ӯ��","����Ա","����ʱ��"};
			message = new JTable(data,colum);
			message.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		
		return message;
	}


	private JTextField getOut() {
		if (out == null) {
			out = new JTextField();
			out.setBounds(new Rectangle(392, 295, 102, 18));
		}
		return out;
	}

	/**
	 * This method initializes btOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtOk() {
		if (btOk == null) {
			btOk = new JButton();
			btOk.setBounds(new Rectangle(513, 290, 60, 23));
			btOk.setText("����");
		}
		return btOk;
	}

}  //  @jve:decl-index=0:visual-constraint="121,2"
