package com.itstar.erp.ui.jichuxinxi.productswing;

import javax.swing.SwingUtilities;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.itstar.erp.dao.baiscinfo.impl.ProDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetResultSet;
public class ProShanChu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JComboBox jComboBox = null;
	private JButton okButton = null;
	String value="";
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField bh = null;
	private JTextField jj = null;
	private JTextField sp = null;
	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			Vector v=new Vector();
			v.add("");
			String table="tb_product_info";
			ResultSet rs=new GetResultSet().getResultSet(table);
			try {
				while(rs.next()){
					int id=rs.getInt(1);
					String name=rs.getString(3);
					v.add(name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			jComboBox = new JComboBox(model);
			jComboBox.setBounds(new Rectangle(301, 33, 155, 33));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
						value=jComboBox.getSelectedItem().toString();
						if(!value.equals("")){
							ResultSet yz=new GetRS().getResultSet("select * from tb_product_info where proName='"+value+"'");
							
							try {
								if(yz.next()){
									bh.setText("准字"+(1000+yz.getInt(1)));
									jj.setText(yz.getString(4));
									int spid=yz.getInt(7);
									ResultSet sz=new GetRS().getResultSet("select spName from tb_supply_info where spID="+spid);
									String name = null;
									if(sz.next()){
										name=sz.getString(1);
									}
									sp.setText(name);
									
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						if(value.equals("")){
							bh.setText("");
							jj.setText("");
							sp.setText("");
						}
					}
				
			});
		}
		return jComboBox;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(370, 195, 116, 34));
			okButton.setText("确定删除");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(value.equals("")){
						JOptionPane.showMessageDialog(okButton, "请选择要删除的商品");
					}else{
						int confirm=JOptionPane.showConfirmDialog(okButton, "确认要删除此商品信息吗?");
						if(confirm==JOptionPane.YES_OPTION){
					new ProDaoImpl().delete(value);
					JOptionPane.showMessageDialog(okButton, "删除成功");
					dispose();
					
					ProShanChu proshanchu=new ProShanChu();
					proshanchu.setVisible(true);
					proshanchu.setSize(500,300);
					proshanchu.setLocationRelativeTo(null);
				}}}
			});
		}
		return okButton;
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
			bh.setBounds(new Rectangle(169, 84, 191, 28));
		}
		return bh;
	}

	/**
	 * This method initializes jj	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJj() {
		if (jj == null) {
			jj = new JTextField();
			jj.setEditable(false);
			jj.setBounds(new Rectangle(172, 126, 190, 22));
		}
		return jj;
	}

	/**
	 * This method initializes sp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSp() {
		if (sp == null) {
			sp = new JTextField();
			sp.setEditable(false);
			sp.setBounds(new Rectangle(172, 169, 194, 22));
		}
		return sp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ProShanChu thisClass = new ProShanChu();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public ProShanChu() {
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
		this.setTitle("商品资料删除");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(68, 168, 96, 25));
			jLabel3.setText("供应商名称");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(69, 126, 91, 26));
			jLabel2.setText("商品进价");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(71, 83, 89, 30));
			jLabel1.setText("商品编号");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(151, 33, 135, 35));
			jLabel.setText("  请选择要删除的商品");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(getBh(), null);
			jContentPane.add(getJj(), null);
			jContentPane.add(getSp(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="283,36"
