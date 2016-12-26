package com.itstar.erp.ui.sellswing;

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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.dao.sell.SellDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.sell.SellBean;

public class SellJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JComboBox kehuComboBox = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	private JButton reset = null;
	private JButton okButton = null;
	private JTextField sellid = null;
	private JTextField selldatetime = null;
	private JComboBox pronameComboBox = null;
	private JTextField proprice = null;
	private JTextField sellprice = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JTextField kucunacount = null;
	private JTextField sellacount = null;
	private JTextArea sellremark = null;
	private JComboBox ywynameComboBox = null;
	
	String xsdate=new GetTime().getTime("");  
	String date=new GetTime().getTime();
	String provalue="";  
	String kehuvalue="";  
	String ywyvalue="";
	static Map<Integer,Integer> kcidacountmap=new HashMap<Integer,Integer>();  
	static{

		String table="tb_kucun_info";
		ResultSet rs=new GetResultSet().getResultSet(table);
		try {
			while(rs.next()){
				int proid=rs.getInt(1);
				int acount=rs.getInt(2);
				kcidacountmap.put(proid, acount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	static Map<String,String> pronamepricemap=new HashMap<String,String>();  //  @jve:decl-index=0:
	static Map<String,Integer> pronameidmap=new HashMap<String,Integer>();  //  @jve:decl-index=0:
	static Map<Integer,String> proidnamemap=new HashMap<Integer,String>();  //  @jve:decl-index=0:
	static{
		String table="tb_product_info";
		ResultSet rs=new GetResultSet().getResultSet(table);
		try {
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(3);
				String price=rs.getString(4);
				proidnamemap.put(id, name);
				pronamepricemap.put(name, price);
				pronameidmap.put(name, id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static Map<String,Integer> ywynameidmap=new HashMap<String,Integer>();
	static{
		String table="tb_yewuyuan_info";
		ResultSet rs=new GetResultSet().getResultSet(table);
		try {
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				ywynameidmap.put(name, id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	static Map<String,Integer> kehunameidmap=new HashMap<String,Integer>();
	static {
		String table="tb_kehu_info";
		ResultSet rs=new GetResultSet().getResultSet(table);
		try {
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				kehunameidmap.put(name, id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method initializes kehuComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getKehuComboBox() {
		if (kehuComboBox == null) {
			Vector v=new Vector();
			v.add("");
			String table="tb_kehu_info";
			ResultSet rs=new GetResultSet().getResultSet(table);
			try {
				while(rs.next()){
					int proid=rs.getInt(1);
					String name=rs.getString(2);
					v.add(name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			kehuComboBox = new JComboBox(model);
			kehuComboBox.setBounds(new Rectangle(160, 72, 219, 36));
			kehuComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						kehuvalue=kehuComboBox.getSelectedItem().toString();
						}
				}
			});
		}
		return kehuComboBox;
	}

	/**
	 * This method initializes reset	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReset() {
		if (reset == null) {
			reset = new JButton();
			reset.setBounds(new Rectangle(429, 380, 84, 29));
			reset.setText("重置");
			reset.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					kehuComboBox.setSelectedIndex(0);
					proprice.setText("");
					sellprice.setText("");
					kucunacount.setText("");
					sellacount.setText("");
					sellremark.setText("");
					ywynameComboBox.setSelectedIndex(0);
					pronameComboBox.setSelectedIndex(0);
				}
			});
		}
		return reset;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(530, 381, 86, 28));
			okButton.setText("确定销售");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(kehuvalue.equals("")||provalue.equals("")||ywyvalue.equals("")){
						JOptionPane.showMessageDialog(okButton, "下拉框必须选择");
					}else{
						String psellprice=sellprice .getText().trim();
						String acount=sellacount.getText().trim();
						if(psellprice.equals("")||acount.equals("")){
							JOptionPane.showMessageDialog(okButton, "请输入销售单价以及销售数量");
						}else{
							double pprice=0;
							int pacount=0;
							try{
								pprice=Double.parseDouble(psellprice);
								pacount=Integer.parseInt(acount);
								if(pprice<=0 || pacount<=0){
									JOptionPane.showMessageDialog(okButton, "销售单价以及数量必须大于零");
								}else{
								
									
									int id=pronameidmap.get(provalue);	
									
									ResultSet rs=new GetRS().getResultSet("select kucunAcount from tb_kucun_info where proID="+id);
									int kcount=0;
									try {
										if(rs.next()){
											kcount=rs.getInt(1);
										}
									} catch (SQLException e1) {
										
										e1.printStackTrace();
									}
									
									if(pacount>kcount || kcount==0){
										JOptionPane.showMessageDialog(okButton, "库存不足");
									}else{
										String yuanjia=pronamepricemap.get(provalue);
										double yj=Double.parseDouble(yuanjia);
										int confirm = 0;
										if(yj>pprice){
											confirm=JOptionPane.showConfirmDialog(okButton, "销售价低于进价，是否销售？？？");
										}
										if(confirm==JOptionPane.YES_OPTION){
										
												int left=kcount-pacount;
										
										
										
										String remark=sellremark.getText();
										SellBean bean=new SellBean();
										bean.setSellDateTime(date);
										bean.setProID(id);
										bean.setSellAcount(pacount);
										bean.setSellPrice(pprice);
										int yid=ywynameidmap.get(ywyvalue);
										bean.setYwyID(yid);
										int kid=kehunameidmap.get(kehuvalue);
										bean.setKehuID(kid);
										bean.setSellRemark(remark);
										
										
										new KuCunDaoImpl().update(id, left);
										new SellDaoImpl().insert(bean);
										
										
										JOptionPane.showMessageDialog(okButton, "销售成功");
										
										dispose();
										
										SellJFrame selljframe=new SellJFrame();
										selljframe.setSize(700, 500);
										selljframe.setTitle("销售管理");
										selljframe.setLocationRelativeTo(null);
										selljframe.setVisible(true);
										
									}}}
							}catch(NumberFormatException e1){
								JOptionPane.showMessageDialog(okButton, "销售单价以及数量必须为数字");
							}
							
							}
							
						}
					}});
				}
			
		
		return okButton;
	}

	/**
	 * This method initializes sellid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSellid() {
		if (sellid == null) {
			sellid = new JTextField();
			String table="tb_sell_info";
			ResultSet rs=new GetResultSet().getResultSet(table);
			int sellID = 0;
			try {
				while(rs.next()){
					sellID=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sellid.setText("XS"+xsdate+"_"+(1000+sellID));
			sellid.setEditable(false);
			sellid.setBounds(new Rectangle(161, 25, 216, 28));
		}
		return sellid;
	}

	/**
	 * This method initializes selldatetime	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSelldatetime() {
		if (selldatetime == null) {
			selldatetime = new JTextField();
			selldatetime.setText(date);
			selldatetime.setEditable(false);
			selldatetime.setBounds(new Rectangle(500, 27, 180, 25));
		}
		return selldatetime;
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
			String table="tb_kucun_info";
			ResultSet rs=new GetResultSet().getResultSet(table);
			try {
				while(rs.next()){
					int proid=rs.getInt(1);
					int kucunacount=rs.getInt(2);
					String name=proidnamemap.get(proid);
					v.add(name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			pronameComboBox = new JComboBox(model);
			pronameComboBox.setBounds(new Rectangle(163, 132, 214, 35));
			pronameComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						provalue=pronameComboBox.getSelectedItem().toString();
						
						proprice.setText(pronamepricemap.get(provalue));
						if(!provalue.equals("")){
							
							
							
							int id=pronameidmap.get(provalue);	
							ResultSet rs=new GetRS().getResultSet("select kucunAcount from tb_kucun_info where proID="+id);
							int kcount=0;
							try {
								if(rs.next()){
									kcount=rs.getInt(1);
								}
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}
							kucunacount.setText(""+kcount);            //
						}
						
						if(provalue.equals("")){
							kucunacount.setText("");
						}
						}
				}
			});
		}
		return pronameComboBox;
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
			proprice.setBounds(new Rectangle(142, 184, 173, 29));
		}
		return proprice;
	}

	/**
	 * This method initializes sellprice	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSellprice() {
		if (sellprice == null) {
			sellprice = new JTextField();
			sellprice.setBounds(new Rectangle(441, 189, 219, 22));
		}
		return sellprice;
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
			kucunacount.setBounds(new Rectangle(156, 226, 149, 22));
		}
		return kucunacount;
	}

	/**
	 * This method initializes sellacount	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSellacount() {
		if (sellacount == null) {
			sellacount = new JTextField();
			sellacount.setBounds(new Rectangle(442, 223, 220, 22));
		}
		return sellacount;
	}

	/**
	 * This method initializes sellremark	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getSellremark() {
		if (sellremark == null) {
			sellremark = new JTextArea();
			sellremark.setBounds(new Rectangle(149, 274, 156, 94));
		}
		return sellremark;
	}

	/**
	 * This method initializes ywynameComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getYwynameComboBox() {
		if (ywynameComboBox == null) {
			Vector v=new Vector();
			v.add("");
			String table="tb_yewuyuan_info";
			ResultSet rs=new GetResultSet().getResultSet(table);
			try {
				while(rs.next()){
					int proid=rs.getInt(1);
					String name=rs.getString(2);
					v.add(name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			ywynameComboBox = new JComboBox(model);
			ywynameComboBox.setBounds(new Rectangle(448, 279, 214, 27));
			ywynameComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						ywyvalue=ywynameComboBox.getSelectedItem().toString();
						}
				}
			});
		}
		return ywynameComboBox;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SellJFrame thisClass = new SellJFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public SellJFrame() {
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
		this.setTitle("销售管理");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(661, 191, 26, 18));
			jLabel11.setText("元");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(317, 188, 31, 18));
			jLabel10.setText("元");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(342, 277, 101, 30));
			jLabel9.setText("    经手人");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(71, 273, 72, 30));
			jLabel8.setText("    备注");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(345, 222, 94, 30));
			jLabel7.setText("   销售数量");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(69, 225, 79, 28));
			jLabel6.setText("    当前库存");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(344, 187, 93, 28));
			jLabel5.setText("     销售单价");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(59, 182, 81, 30));
			jLabel4.setText("   商品进价");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(63, 130, 86, 33));
			jLabel3.setText("    商品名称");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(66, 77, 86, 30));
			jLabel2.setText("    客户名称");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(405, 27, 89, 27));
			jLabel1.setText("   销售时间");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(61, 25, 90, 29));
			jLabel.setText("    销售编号");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getKehuComboBox(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(jLabel9, null);
			jContentPane.add(getReset(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getSellid(), null);
			jContentPane.add(getSelldatetime(), null);
			jContentPane.add(getPronameComboBox(), null);
			jContentPane.add(getProprice(), null);
			jContentPane.add(getSellprice(), null);
			jContentPane.add(jLabel10, null);
			jContentPane.add(jLabel11, null);
			jContentPane.add(getKucunacount(), null);
			jContentPane.add(getSellacount(), null);
			jContentPane.add(getSellremark(), null);
			jContentPane.add(getYwynameComboBox(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="207,12"
