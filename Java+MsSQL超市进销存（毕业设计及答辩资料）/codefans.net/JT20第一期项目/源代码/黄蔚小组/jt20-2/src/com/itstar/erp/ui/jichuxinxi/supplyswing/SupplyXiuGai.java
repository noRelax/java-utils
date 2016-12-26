package com.itstar.erp.ui.jichuxinxi.supplyswing;

import javax.swing.SwingUtilities;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.itstar.erp.dao.baiscinfo.dao.SupplyDao;
import com.itstar.erp.dao.baiscinfo.impl.SupplyDaoImpl;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.vo.supply.SupplyBean;

public class SupplyXiuGai extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel1 = null;
	private JComboBox jComboBox = null;
	DefaultComboBoxModel model ;
	private JLabel jLabel = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JTextField jTextField = null;
	private JLabel jLabel6 = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField2 = null;
	private JTextField jTextField3 = null;
	private JTextField jTextField4 = null;
	private JTextField jTextField5 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;  
	String value="";
	private JLabel jLabel7 = null;
	private JTextField jTextField6 = null;
	
	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			Vector v=new Vector();
			v.add("");
			ResultSet rs=getResultSet();
			try {
				while(rs.next()){
					String spName=rs.getString(2);
					v.add(spName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			model= new DefaultComboBoxModel(v);
			jComboBox = new JComboBox(model);
			jComboBox.setBounds(new Rectangle(321, 23, 155, 27));
			jComboBox.addItemListener(new ItemListener(){
				
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
					value=jComboBox.getSelectedItem().toString();
					SupplyDao spd=new SupplyDaoImpl();
					SupplyBean bean=spd.Query(value);
					System.out.println(value);
					jTextField.setText("gys"+(1000+bean.getSpID()));
					jTextField1.setText(bean.getSpPhone());
					jTextField2.setText(bean.getSpZip());
					jTextField6.setText(bean.getSpEmail());
					jTextField4.setText(bean.getSpConn());
					jTextField5.setText(bean.getSpPhone());
					jTextField3.setText(bean.getSpAddress());
					
					if(value.equals("")){
						jTextField.setText("");
						jTextField1.setText("");
						jTextField2.setText("");
						jTextField3.setText("");
						jTextField4.setText("");
						jTextField5.setText("");
						jTextField6.setText("");
						jComboBox.setSelectedIndex(0);
					}
						}
					}
				});
		}
		return jComboBox;
	}

	private static ResultSet getResultSet(){
		Connection conn=new DBConnection().getConnection();
		Statement s;
		ResultSet rs = null;
		try {
			s=conn.createStatement();
			rs=s.executeQuery("select * from tb_supply_info");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
		return rs;
		
	}
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(81, 29, 112, 22));
			jTextField.setEditable(false);
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
			jTextField1.setBounds(new Rectangle(101, 66, 121, 30));
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
			jTextField2.setBounds(new Rectangle(100, 115, 124, 22));
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
			jTextField3.setBounds(new Rectangle(102, 174, 354, 22));
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
			jTextField4.setBounds(new Rectangle(313, 66, 141, 22));
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
			jTextField5.setBounds(new Rectangle(310, 115, 148, 22));
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
			jButton.setBounds(new Rectangle(154, 206, 87, 37));
			jButton.setText("确定");
			
			jButton.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {   
					if(value.equals("")){
						JOptionPane.showMessageDialog(jButton, "请选择供应商");
					}else{
					String spphone=jTextField1.getText().trim();
					String spconn=jTextField4.getText().trim();
					String spzip=jTextField2.getText().trim();
					String spconnphone=jTextField5.getText().trim();
					String spemail=jTextField6.getText().trim();
					String spaddress=jTextField3.getText().trim();
					SupplyBean bean1=new SupplyBean();
					if(spphone.equals("")||spconn.equals("")||spconnphone.equals("")||spaddress.equals("")){
						JOptionPane.showMessageDialog(jButton, "除Email,邮编外，不许为空 ！");
					}else{
						
						bean1.setSpAddress(spaddress);
						bean1.setSpConn(spconn);
						bean1.setSpConnPhone(spconnphone);
						bean1.setSpEmail(spemail);
						bean1.setSpName(value);
						bean1.setSpPhone(spphone);
						bean1.setSpZip(spzip);
						SupplyDao sdi=new SupplyDaoImpl();
						sdi.update(bean1,value);
						JOptionPane.showMessageDialog(jButton, "保存成功");
						
						jTextField.setText("");
						jTextField1.setText("");
						jTextField2.setText("");
						jTextField3.setText("");
						jTextField4.setText("");
						jTextField5.setText("");
						jTextField6.setText("");
						jComboBox.setSelectedIndex(0);
						
					}}
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
			jButton1.setBounds(new Rectangle(332, 209, 77, 32));
			jButton1.setText("重置");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTextField.setText("");
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");
					jTextField4.setText("");
					jTextField5.setText("");
					jTextField6.setText("");
					jComboBox.setSelectedIndex(0);
				}
			});
		}
		return jButton1;
	}
	/**
	 * This method initializes jTextField6	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setBounds(new Rectangle(101, 140, 358, 22));
		}
		return jTextField6;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SupplyXiuGai thisClass = new SupplyXiuGai();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public SupplyXiuGai() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 300);
		this.setContentPane(getJContentPane());
		this.setTitle("供应商资料修改");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(17, 139, 77, 24));
			jLabel7.setText("联系人Email");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(10, 26, 73, 30));
			jLabel6.setText("供应商编号");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(13, 171, 85, 24));
			jLabel5.setText("供应商地址");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(233, 107, 75, 34));
			jLabel4.setText("联系人电话");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(236, 64, 69, 25));
			jLabel3.setText("联系人");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(13, 114, 78, 25));
			jLabel2.setText("供应商邮编");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 66, 78, 28));
			jLabel.setText("供应商电话");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(198, 25, 109, 26));
			jLabel1.setText("  供应商名称");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(getJTextField1(), null);
			jContentPane.add(getJTextField2(), null);
			jContentPane.add(getJTextField3(), null);
			jContentPane.add(getJTextField4(), null);
			jContentPane.add(getJTextField5(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(getJTextField6(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="299,-22"
