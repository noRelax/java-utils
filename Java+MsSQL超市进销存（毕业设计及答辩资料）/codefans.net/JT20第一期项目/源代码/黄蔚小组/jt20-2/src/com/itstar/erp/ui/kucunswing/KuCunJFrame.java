package com.itstar.erp.ui.kucunswing;

import javax.swing.SwingUtilities;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.util.GetResultSet;

public class KuCunJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox pronameComboBox = null;
	private JLabel jLabel = null;
	String provalue = ""; 
	static Map<Integer, String> proidnamemap = new HashMap<Integer, String>(); 
	static Map<String, Double> pronamepricemap = new HashMap<String, Double>(); 
	static Map<String, Integer> pronameidmap = new HashMap<String, Integer>(); 
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JTextField proid = null;
	private JTextField proname = null;
	private JTextField proprice = null;
	private JTextField kucunacount = null;
	private JTextField total = null;
	
	static {
		String table = "tb_product_info";
		ResultSet rs = new GetResultSet().getResultSet(table);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(3);
				double price = rs.getDouble(4);
				proidnamemap.put(id, name);
				pronamepricemap.put(name, price);
				pronameidmap.put(name, id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static Map<String, Integer> kucunidacountmap = new HashMap<String, Integer>(); // @jve:decl-index=0:
	static {

		String table = "tb_kucun_info";
		ResultSet rs = new GetResultSet().getResultSet(table);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				int count = rs.getInt(2);
				String name = proidnamemap.get(count);
				kucunidacountmap.put(name, count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method initializes pronameComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getPronameComboBox() {
		if (pronameComboBox == null) {
			Vector v = new Vector();
			v.add("");
			v.add("查询全部");
			String table = "tb_kucun_info";
			ResultSet rs = new GetResultSet().getResultSet(table);
			try {
				while (rs.next()) {
					int proid = rs.getInt(1);
					String name = proidnamemap.get(proid);
					v.add(name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DefaultComboBoxModel model = new DefaultComboBoxModel(v);
			pronameComboBox = new JComboBox(model);

			pronameComboBox.setBounds(new Rectangle(295, 66, 275, 36));
			pronameComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						provalue = pronameComboBox.getSelectedItem().toString();
						if (!provalue.equals("") && !provalue.equals("查询全部")) {
							int id = pronameidmap.get(provalue);
							String name = provalue;
							double price = pronamepricemap.get(provalue);
							int kcount = new KuCunDaoImpl().getkucunAcount(id);
							double ktotal = price * kcount;

							proid.setText("准字" + (1000 + id));
							proname.setText(name);
							proprice.setText("" + price);
							kucunacount.setText("" + kcount);
							total.setText("" + ktotal);

						}
						if (provalue.equals("查询全部")) {
							proid.setText("");
							proname.setText("");
							proprice.setText("");
							kucunacount.setText("");
							total.setText("");

							new KuCunJTable().init();
							pronameComboBox.setSelectedIndex(0);

						}
						if (provalue.equals("")) {
							proid.setText("");
							proname.setText("");
							proprice.setText("");
							kucunacount.setText("");
							total.setText("");
						}
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
			proid.setBounds(new Rectangle(52, 195, 114, 30));
		}
		return proid;
	}

	/**
	 * This method initializes proname
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getProname() {
		if (proname == null) {
			proname = new JTextField();
			proname.setEditable(false);
			proname.setBounds(new Rectangle(176, 197, 127, 29));
		}
		return proname;
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
			proprice.setBounds(new Rectangle(320, 193, 110, 34));
		}
		return proprice;
	}

	/**
	 * This method initializes kucunacount
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getKucunacount() {
		if (kucunacount == null) {
			kucunacount = new JTextField();
			kucunacount.setEditable(false);
			kucunacount.setBounds(new Rectangle(447, 195, 95, 32));
		}
		return kucunacount;
	}

	/**
	 * This method initializes total
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTotal() {
		if (total == null) {
			total = new JTextField();
			total.setEditable(false);
			total.setBounds(new Rectangle(558, 192, 108, 32));
		}
		return total;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				KuCunJFrame thisClass = new KuCunJFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public KuCunJFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(700, 500);
		this.setContentPane(getJContentPane());
		this.setTitle("库存管理");
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
			jLabel5.setBounds(new Rectangle(556, 149, 110, 27));
			jLabel5.setText("       库存金额");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(442, 147, 97, 31));
			jLabel4.setText("      库存数量");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(320, 149, 107, 31));
			jLabel3.setText("         进价");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(177, 148, 124, 32));
			jLabel2.setText("    商品名称");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(53, 145, 112, 33));
			jLabel1.setText("     商品编号");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(99, 69, 196, 35));
			jLabel.setText("                请选择商品名称");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getPronameComboBox(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getProid(), null);
			jContentPane.add(getProname(), null);
			jContentPane.add(getProprice(), null);
			jContentPane.add(getKucunacount(), null);
			jContentPane.add(getTotal(), null);
		}
		return jContentPane;
	}

} // @jve:decl-index=0:visual-constraint="215,10"
