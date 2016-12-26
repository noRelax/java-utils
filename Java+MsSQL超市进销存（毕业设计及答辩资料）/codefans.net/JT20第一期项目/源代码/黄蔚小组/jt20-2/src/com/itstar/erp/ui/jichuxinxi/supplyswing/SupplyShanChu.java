package com.itstar.erp.ui.jichuxinxi.supplyswing;

import javax.swing.SwingUtilities;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import com.itstar.erp.dao.baiscinfo.dao.SupplyDao;
import com.itstar.erp.dao.baiscinfo.impl.SupplyDaoImpl;
import com.itstar.erp.util.DBConnection;
import com.itstar.erp.util.GetRS;

public class SupplyShanChu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JComboBox jComboBox = null;
	DefaultComboBoxModel model ;
	String value="";
	private JLabel jLabel1 = null;
	private JTextField bh = null;
	private JTextField addr = null;
	private JLabel jLabel = null;
	private JLabel jLabel2 = null;
	private JTextField phone = null;
	private JLabel jLabel3 = null;
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(356, 167, 89, 41));
			jButton.setText("确定删除");
			jButton.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {   
					
					if(value.equals("")){
						JOptionPane.showMessageDialog(jButton, "请选择要删除的供应商");
					}else{
						int confirm=JOptionPane.showConfirmDialog(jButton, "确认删除此供应商信息吗?");
						if(confirm==JOptionPane.YES_OPTION){
						
					SupplyDao spd=new SupplyDaoImpl();
					spd.delete(value);
					JOptionPane.showMessageDialog(jComboBox, "删除成功");
					
					dispose();
					
					SupplyShanChu supplyShanChu=new SupplyShanChu();
					supplyShanChu.setVisible(true);
					supplyShanChu.setSize(500,300);
					supplyShanChu.setLocationRelativeTo(null);

					
					}
				}
				}
			});
		}
		return jButton;
	}

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
					String spName=rs.getString(2).trim();
					v.add(spName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			model= new DefaultComboBoxModel(v);
			jComboBox = new JComboBox(model);
			jComboBox.setBounds(new Rectangle(205, 16, 136, 37));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						value=jComboBox.getSelectedItem().toString();
						System.out.println(value);
						if(!value.equals("")){
							ResultSet yz=new GetRS().getResultSet("select * from tb_supply_info where spName='"+value+"'");
							try {
								if(yz.next()){
									bh.setText("gys"+(1000+yz.getInt(1)));
									addr.setText(yz.getString(3).trim());
									phone.setText(yz.getString(4).trim());
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						if(value.equals("")){
							bh.setText("");
							addr.setText("");
							phone.setText("");
						}
						
							}
				}
			});
		}
		return jComboBox;
	}
	/**
	 * @param args
	 */
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
	 * This method initializes bh	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getBh() {
		if (bh == null) {
			bh = new JTextField();
			bh.setEditable(false);
			bh.setBounds(new Rectangle(113, 82, 232, 22));
		}
		return bh;
	}
	/**
	 * This method initializes addr	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAddr() {
		if (addr == null) {
			addr = new JTextField();
			addr.setEditable(false);
			addr.setBounds(new Rectangle(117, 161, 227, 22));
		}
		return addr;
	}
	/**
	 * This method initializes phone	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPhone() {
		if (phone == null) {
			phone = new JTextField();
			phone.setEditable(false);
			phone.setBounds(new Rectangle(114, 119, 230, 22));
		}
		return phone;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SupplyShanChu thisClass = new SupplyShanChu();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public SupplyShanChu() {
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
		this.setTitle("供应商资料删除");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(27, 163, 72, 18));
			jLabel3.setText("供应商地址");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(29, 121, 67, 18));
			jLabel2.setText("供应商电话");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(25, 85, 76, 18));
			jLabel.setText("供应商编号");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(14, 18, 164, 35));
			jLabel1.setText("   请选择要删除的供应商");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getBh(), null);
			jContentPane.add(getAddr(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getPhone(), null);
			jContentPane.add(jLabel3, null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="333,17"
