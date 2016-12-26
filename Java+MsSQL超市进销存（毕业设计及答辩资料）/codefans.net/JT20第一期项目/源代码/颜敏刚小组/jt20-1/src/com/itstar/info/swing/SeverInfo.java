package com.itstar.info.swing;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.itstar.info.bean.GuestBean;
import com.itstar.info.bean.SeversBean;
import com.itstar.info.domain.DoGestInfo;
import com.itstar.info.domain.DoGoodsInfo;
import com.itstar.info.domain.DoSeverInfo;
import javax.swing.WindowConstants;
import java.awt.Point;

public class SeverInfo extends JFrame {

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
	private JLabel jLabel7 = null;
	private JButton jButton1 = null;
	private JButton jButton = null;
	private JTextField jTextField = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField2 = null;
	private JTextField jTextField3 = null;
	private JTextField jTextField4 = null;
	private JTextField jTextField5 = null;
	private JTextField jTextField6 = null;
	private JTextField jTextField7 = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	private JLabel jLabel14 = null;
	private JLabel jLabel15 = null;
	private JLabel jLabel16 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
	private JTextField jTextField8 = null;
	private JTextField jTextField9 = null;
	private JTextField jTextField10 = null;
	private JTextField jTextField11 = null;
	private JTextField jTextField12 = null;
	private JTextField jTextField13 = null;
	private JTextField jTextField14 = null;
	private JTextField jTextField15 = null;
	private JComboBox jComboBox = null;
	/**
	 * This is the default constructor
	 */
	public SeverInfo() {
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
		this.setTitle("供应商管理");
		this.setLocation(new Point(450, 250));
		this.setSize(new Dimension(500, 400));
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
			jTabbedPane.addTab("供应商新增", null, getJPanel(), null);
			jTabbedPane.addTab("供应商修改和删除", null, getJPanel1(), null);
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
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(30, 225, 100, 25));
			jLabel7.setText("银行账号：");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(30, 185, 100, 25));
			jLabel6.setText("邮箱：");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(260, 145, 90, 25));
			jLabel5.setText("联系人手机：");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(30, 145, 100, 25));
			jLabel4.setText("联系人：");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(260, 105, 90, 25));
			jLabel3.setText("邮编：");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(30, 105, 100, 25));
			jLabel2.setText("联系号码：");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(30, 65, 100, 25));
			jLabel1.setText("地址：");
			jLabel = new JLabel();
			jLabel.setText("供应商名称：");
			jLabel.setBounds(new Rectangle(30, 25, 100, 25));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel2, null);
			jPanel.add(jLabel3, null);
			jPanel.add(jLabel4, null);
			jPanel.add(jLabel5, null);
			jPanel.add(jLabel6, null);
			jPanel.add(jLabel7, null);
			jPanel.add(getJButton1(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(getJTextField2(), null);
			jPanel.add(getJTextField3(), null);
			jPanel.add(getJTextField4(), null);
			jPanel.add(getJTextField5(), null);
			jPanel.add(getJTextField6(), null);
			jPanel.add(getJTextField7(), null);
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
			jLabel16 = new JLabel();
			jLabel16.setBounds(new Rectangle(30, 260, 90, 25));
			jLabel16.setText("选择供应商：");
			jLabel15 = new JLabel();
			jLabel15.setBounds(new Rectangle(30, 220, 90, 25));
			jLabel15.setText("银行账号：");
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(30, 180, 90, 25));
			jLabel14.setText("邮箱：");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(250, 140, 90, 25));
			jLabel13.setText("联系人手机：");
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(30, 140, 90, 25));
			jLabel12.setText("联系人：");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(250, 100, 90, 25));
			jLabel11.setText("邮编：");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(30, 100, 90, 25));
			jLabel10.setText("联系号码：");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(30, 60, 90, 25));
			jLabel9.setText("地址：");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(30, 20, 90, 25));
			jLabel8.setText("供应商名称：");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(jLabel8, null);
			jPanel1.add(jLabel9, null);
			jPanel1.add(jLabel10, null);
			jPanel1.add(jLabel11, null);
			jPanel1.add(jLabel12, null);
			jPanel1.add(jLabel13, null);
			jPanel1.add(jLabel14, null);
			jPanel1.add(jLabel15, null);
			jPanel1.add(jLabel16, null);
			jPanel1.add(getJButton3(), null);
			jPanel1.add(getJButton4(), null);
			jPanel1.add(getJTextField8(), null);
			jPanel1.add(getJTextField9(), null);
			jPanel1.add(getJTextField10(), null);
			jPanel1.add(getJTextField11(), null);
			jPanel1.add(getJTextField12(), null);
			jPanel1.add(getJTextField13(), null);
			jPanel1.add(getJTextField14(), null);
			jPanel1.add(getJTextField15(), null);
			jPanel1.add(getJComboBox(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(120, 280, 95, 30));
			jButton1.setText("添加");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					SeversBean sever=new SeversBean();
					sever.setSname(jTextField.getText().trim());
					sever.setSaddress(jTextField1.getText().trim());
					sever.setSnumber(jTextField2.getText().trim());
					sever.setSpostCode(jTextField3.getText().trim());
					sever.setSperson(jTextField4.getText().trim());
					sever.setSphone(jTextField5.getText().trim());
					sever.setSemail(jTextField6.getText().trim());
					sever.setSbank(jTextField7.getText().trim());
					DoSeverInfo doseinfo=new DoSeverInfo();
					doseinfo.doseverinsert(sever);
					initjComboBox();
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(260, 280, 95, 30));
			jButton.setText("重设");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					jTextField.setText("");
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");
					jTextField4.setText("");
					jTextField5.setText("");
					jTextField6.setText("");
					jTextField7.setText("");
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(150, 25, 300, 25));
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
			jTextField1.setBounds(new Rectangle(150, 65, 300, 25));
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
			jTextField2.setBounds(new Rectangle(150, 105, 100, 25));
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
			jTextField3.setBounds(new Rectangle(355, 105, 95, 25));
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
			jTextField4.setBounds(new Rectangle(150, 145, 100, 25));
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
			jTextField5.setBounds(new Rectangle(355, 145, 95, 25));
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
			jTextField6.setBounds(new Rectangle(150, 185, 300, 25));
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
			jTextField7.setBounds(new Rectangle(150, 225, 300, 25));
		}
		return jTextField7;
	}


	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setBounds(new Rectangle(90, 295, 100, 30));
			jButton3.setText("修改");
			jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					SeversBean sever=new SeversBean();
					sever.setSname(jTextField8.getText().trim());
					sever.setSaddress(jTextField9.getText().trim());
					sever.setSnumber(jTextField10.getText().trim());
					sever.setSpostCode(jTextField11.getText().trim());
					sever.setSperson(jTextField12.getText().trim());
					sever.setSphone(jTextField13.getText().trim());
					sever.setSemail(jTextField14.getText().trim());
					sever.setSbank(jTextField15.getText().trim());
					DoSeverInfo dosever=new DoSeverInfo();
					dosever.doseveralter(sever);
				}
			});
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
			jButton4.setBounds(new Rectangle(250, 295, 100, 30));
			jButton4.setText("删除");
			jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					SeversBean sever=new SeversBean();
					sever.setSname(jTextField8.getText().trim());
					//String str=jTextField8.getText().trim();
					DoSeverInfo dosever=new DoSeverInfo();
					dosever.doseverdelete(sever);
					jComboBox.removeItem(jTextField8.getText().trim());
				}
			});
		}
		return jButton4;
	}

	/**
	 * This method initializes jTextField8	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
			jTextField8.setBounds(new Rectangle(135, 20, 315, 25));
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
			jTextField9.setBounds(new Rectangle(135, 60, 315, 25));
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
			jTextField10.setBounds(new Rectangle(135, 100, 100, 25));
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
			jTextField11.setBounds(new Rectangle(350, 100, 100, 25));
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
			jTextField12.setBounds(new Rectangle(135, 140, 100, 25));
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
			jTextField13.setBounds(new Rectangle(350, 140, 100, 25));
		}
		return jTextField13;
	}

	/**
	 * This method initializes jTextField14	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField14() {
		if (jTextField14 == null) {
			jTextField14 = new JTextField();
			jTextField14.setBounds(new Rectangle(135, 180, 315, 25));
		}
		return jTextField14;
	}

	/**
	 * This method initializes jTextField15	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField15() {
		if (jTextField15 == null) {
			jTextField15 = new JTextField();
			jTextField15.setBounds(new Rectangle(135, 220, 315, 25));
		}
		return jTextField15;
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
			jComboBox.setBounds(new Rectangle(135, 260, 140, 25));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){
						DoSeverInfo dosever=new DoSeverInfo();
						SeversBean sever=dosever.setcom((String) e.getItem());
						jTextField8.setText(sever.getSname());
						jTextField9.setText(sever.getSaddress());
						jTextField10.setText(sever.getSnumber());
						jTextField11.setText(sever.getSpostCode());
						jTextField12.setText(sever.getSperson());
						jTextField13.setText(sever.getSphone());
						jTextField14.setText(sever.getSemail());
						jTextField15.setText(sever.getSbank());
					}
				}
			});
		}
		return jComboBox;
	}
	
	public void initjComboBox(){
		jComboBox.removeAllItems();
		jComboBox.addItem("选择");
		List<String> list=DoSeverInfo.docom();
		for(String item:list){
			jComboBox.addItem(item);
		}
	}

}  //  @jve:decl-index=0:visual-constraint="14,5"
