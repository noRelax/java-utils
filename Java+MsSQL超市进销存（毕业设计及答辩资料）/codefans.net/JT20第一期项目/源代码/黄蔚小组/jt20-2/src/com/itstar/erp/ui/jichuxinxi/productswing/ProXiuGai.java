package com.itstar.erp.ui.jichuxinxi.productswing;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.itstar.erp.dao.baiscinfo.dao.ProDao;
import com.itstar.erp.dao.baiscinfo.impl.ProDaoImpl;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.vo.product.ProBean;

public class ProXiuGai extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JButton okButton = null;
	private JButton resetButton = null;
	private JComboBox pronameComboBox = null;
	private JTextField proid = null;
	private JTextField proprice = null;
	private JTextField spname = null;
	private JTextField newproprice = null;
	private JComboBox ywyname = null;
	String value="";
	String value1="";
	Map<String,Integer> ywynameidmap=new HashMap<String,Integer>();  //  @jve:decl-index=0:
	static Map<Integer,String> spidnamemap=new HashMap<Integer,String>();  //  @jve:decl-index=0:
	static{
		String table="tb_supply_info";
		ResultSet rs=new GetResultSet().getResultSet(table);
		try {
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				spidnamemap.put(id, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(224, 207, 98, 26));
			okButton.setText("确定修改");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(value.equals("")||value1.equals("")){
						JOptionPane.showMessageDialog(okButton, "下拉框必须选择！");
					}else{
						int ywyid=ywynameidmap.get(value1);
						String newprice=newproprice.getText().trim();
						if(newprice.equals("")){
							new ProDaoImpl().update(ywyid, value);
							JOptionPane.showMessageDialog(okButton, "修改成功！");
							dispose();
							
							ProXiuGai proxiugai=new ProXiuGai();
							proxiugai.setVisible(true);
							proxiugai.setLocationRelativeTo(null);
							proxiugai.setSize(500,300);
						}else {
							double d=0;
							
							try{
								d=Double.parseDouble(newprice);
								
								if(d<=0){
									JOptionPane.showMessageDialog(okButton, "进价必须大于零");
								}else{
								new ProDaoImpl().update(ywyid, d, value);
								JOptionPane.showMessageDialog(okButton, "修改成功！");
								dispose();
								
								ProXiuGai proxiugai=new ProXiuGai();
								proxiugai.setVisible(true);
								proxiugai.setLocationRelativeTo(null);
								proxiugai.setSize(500,300);
							}
								
							}catch(NumberFormatException e1){
								JOptionPane.showMessageDialog(okButton, "请输入数字");
							}
							
							
							
							
							}
						
					}
				}
			});
		}
		return okButton;
	}

	/**
	 * This method initializes resetButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getResetButton() {
		if (resetButton == null) {
			resetButton = new JButton();
			resetButton.setBounds(new Rectangle(347, 209, 100, 27));
			resetButton.setText("重置");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pronameComboBox.setSelectedIndex(0);
					proid.setText("");
					proprice.setText("");
					spname.setText("");
					newproprice.setText("");
					ywyname.setSelectedIndex(0);
				}
			});
		}
		return resetButton;
	}

	/**
	 * This method initializes pronameComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getPronameComboBox() {
		if (pronameComboBox == null) {
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
			pronameComboBox = new JComboBox(model);
			pronameComboBox.setBounds(new Rectangle(103, 25, 147, 27));
			pronameComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						value=pronameComboBox.getSelectedItem().toString();
						ProDao pdi=new ProDaoImpl();
						ProBean bean=pdi.Query(value);
						System.out.println(value);
						proid.setText("准字"+(1000+bean.getProID()));
						proprice.setText(""+bean.getProPrice());
						int gysid=bean.getSpID();
						spname.setText(spidnamemap.get(gysid));
				}
				}
			});
		}
		return pronameComboBox;
	}

	/**
	 * This method initializes proid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getProid() {
		if (proid == null) {
			proid = new JTextField();
			proid.setEditable(false);
			proid.setBounds(new Rectangle(327, 26, 163, 22));
		}
		return proid;
	}

	/**
	 * This method initializes proprice	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getProprice() {
		if (proprice == null) {
			proprice = new JTextField();
			proprice.setEditable(false);
			proprice.setBounds(new Rectangle(100, 77, 117, 22));
		}
		return proprice;
	}

	/**
	 * This method initializes spname	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSpname() {
		if (spname == null) {
			spname = new JTextField();
			spname.setEditable(false);
			spname.setBounds(new Rectangle(285, 77, 203, 22));
		}
		return spname;
	}

	/**
	 * This method initializes newproprice	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNewproprice() {
		if (newproprice == null) {
			newproprice = new JTextField();
			newproprice.setBounds(new Rectangle(101, 123, 157, 22));
		}
		return newproprice;
	}

	/**
	 * This method initializes ywyname	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getYwyname() {
		if (ywyname == null) {
			Vector v=new Vector();
			v.add("");
			String table="tb_yewuyuan_info";
			ResultSet rs=new GetResultSet().getResultSet(table);
			try {
				while(rs.next()){
					int id=rs.getInt(1);
					String name=rs.getString(2);
					v.add(name);
					ywynameidmap.put(name, id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			ywyname = new JComboBox(model);
			ywyname.setBounds(new Rectangle(342, 117, 142, 27));
			ywyname.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						value1=ywyname.getSelectedItem().toString();
					}
				}
			});
		}
		return ywyname;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ProXiuGai thisClass = new ProXiuGai();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public ProXiuGai() {
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
		this.setTitle("商品资料修改");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(267, 120, 73, 24));
			jLabel5.setText("    经手人");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(218, 74, 66, 29));
			jLabel4.setText("供应商名称");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(250, 25, 75, 25));
			jLabel3.setText("    商品编号");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(8, 120, 85, 28));
			jLabel2.setText("    最新进价");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(6, 73, 96, 29));
			jLabel1.setText("    原进价");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(11, 23, 84, 28));
			jLabel.setText("  请选择商品");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getResetButton(), null);
			jContentPane.add(getPronameComboBox(), null);
			jContentPane.add(getProid(), null);
			jContentPane.add(getProprice(), null);
			jContentPane.add(getSpname(), null);
			jContentPane.add(getNewproprice(), null);
			jContentPane.add(getYwyname(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="128,16"
