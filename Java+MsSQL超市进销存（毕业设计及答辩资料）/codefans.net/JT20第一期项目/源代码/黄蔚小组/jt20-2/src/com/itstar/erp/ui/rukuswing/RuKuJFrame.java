package com.itstar.erp.ui.rukuswing;

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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import com.itstar.erp.dao.baiscinfo.impl.ProDaoImpl;
import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.dao.ruku.RuKuDaoImpl;
import com.itstar.erp.dao.tuihuo.TuiHuoDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.product.ProBean;
import com.itstar.erp.vo.ruku.RuKuBean;

public class RuKuJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JTextField rukuid = null;
	private JTextField rukudatetime = null;
	private JTextField proprice = null;
	private JTextField kucunacount = null;
	private JLabel jLabel8 = null;
	private JTextField rukuacount = null;
	private JButton okButton = null;
	private JLabel jLabel9 = null;
	private JTextField rukuremark = null;
	private JLabel jLabel10 = null;
	private JComboBox ywyname = null;
	String rkdate=new GetTime().getTime("");
	String date=new GetTime().getTime();
	String value="";  //  @jve:decl-index=0:
	String spvalue="";  //  @jve:decl-index=0:
	String ysyvalue="";  //  @jve:decl-index=0:

	private JComboBox jComboBox = null;
	private JButton reset = null;

	/**
	 * This method initializes rukuid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRukuid() {
		if (rukuid == null) {
			rukuid = new JTextField();
			String table="tb_ruku_info";
			ResultSet rs=new GetResultSet().getResultSet(table);
			int rukuID = 0;
			try {
				while(rs.next()){
					rukuID=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rukuid.setText("RK"+rkdate+"_"+(rukuID+1000));
			rukuid.setEditable(false);
			rukuid.setBounds(new Rectangle(146, 20, 182, 22));
		}
		return rukuid;
	}

	/**
	 * This method initializes rukudatetime	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRukudatetime() {
		if (rukudatetime == null) {
			rukudatetime = new JTextField();
			rukudatetime.setText(date);
			rukudatetime.setEditable(false);
			rukudatetime.setBounds(new Rectangle(441, 22, 236, 22));
		}
		return rukudatetime;
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
			proprice.setBounds(new Rectangle(148, 120, 201, 22));
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
			kucunacount.setBounds(new Rectangle(152, 149, 204, 22));
		}
		return kucunacount;
	}

	/**
	 * This method initializes rukuacount	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRukuacount() {
		if (rukuacount == null) {
			rukuacount = new JTextField();
			rukuacount.setBounds(new Rectangle(152, 179, 207, 22));
		}
		return rukuacount;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(546, 391, 118, 26));
			okButton.setText("确定入库");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int count = 0;
					if(spvalue.equals("")||ysyvalue.equals("")){
						JOptionPane.showMessageDialog(okButton, "请选择商品名称 以及 经手人");
					}else{
						String acount=rukuacount.getText().trim();
						if(acount.equals("")){
							JOptionPane.showMessageDialog(okButton, "请输入数量");
						}else{
							
							try{
								count=Integer.parseInt(acount);
								if(count<=0){
									JOptionPane.showMessageDialog(okButton, "不能为负数或零");
								}else{
									
								int proid=new RuKuDaoImpl().getproID(spvalue);
								
								int kucunAcount=new KuCunDaoImpl().getkucunAcount(proid);
								int total=count+kucunAcount;
								boolean has=new KuCunDaoImpl().test(proid);
								
								String remark=rukuremark.getText().trim();
								String ywy=ysyvalue;
								int ywyid =new TuiHuoDaoImpl().getywyID(ywy);
								RuKuBean bean=new RuKuBean();
								
								bean.setProID(proid);
								bean.setRukuDateTime(date);
								bean.setRukuAcount(count);
								bean.setYwyID(ywyid);
								bean.setRukuRemark(remark);
								
								new RuKuDaoImpl().insert(bean);
								
								if(has==true){
										new KuCunDaoImpl().update(proid,total);
										System.out.println("本来有的");
										
									}else{
										new KuCunDaoImpl().insert(proid,total);
										System.out.println("本来没的");
									}
								JOptionPane.showMessageDialog(okButton, "入库成功");
								dispose();
								
								RuKuJFrame rukujframe=new RuKuJFrame();
								rukujframe.setSize(700, 500);
								rukujframe.setLocationRelativeTo(null);
								rukujframe.setVisible(true);
								
								}
							}catch(NumberFormatException e1){
								JOptionPane.showMessageDialog(okButton, "请输入正整数");
							}
							
						}
					}
				}
			});
		}
		return okButton;
	}
	/**
	 * This method initializes rukuremark	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRukuremark() {
		if (rukuremark == null) {
			rukuremark = new JTextField();
			rukuremark.setBounds(new Rectangle(138, 244, 221, 47));
		}
		return rukuremark;
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
					String ywyName=rs.getString(2);
					v.add(ywyName);
//					ywynameidmap.put(ywyName, id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DefaultComboBoxModel model= new DefaultComboBoxModel(v);
			ywyname = new JComboBox(model);
			ywyname.setBounds(new Rectangle(529, 338, 147, 27));
			ywyname.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						ysyvalue=ywyname.getSelectedItem().toString();
					}
				}
			});
		}
		return ywyname;
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
			String table="tb_product_info";
			ResultSet rs=new GetResultSet().getResultSet(table);
			try {
				while(rs.next()){
					String name=rs.getString(3);
					v.add(name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			jComboBox = new JComboBox(model);
			jComboBox.setBounds(new Rectangle(146, 69, 231, 27));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						spvalue=jComboBox.getSelectedItem().toString();
					}
					if(!spvalue.equals("")){
						ResultSet r=new GetRS().getResultSet("select proPrice from tb_product_info where proName='"+spvalue+"'");
						double price = 0;
						try {
							while(r.next()){
								price=r.getDouble(1);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						proprice.setText(""+price);
						
						ResultSet s=new GetRS().getResultSet("select proID from tb_product_info  where proName='"+spvalue+"'");
						int id=0;
						try {
							while(s.next()){
								id=s.getInt(1);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ResultSet z=new GetRS().getResultSet("select kucunAcount from tb_kucun_info where proID="+id);
						int coun=0;
						try {
							while(z.next()){
								coun=z.getInt(1);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						kucunacount.setText(""+coun);
					}
					}
			});
		}
		return jComboBox;
	}

	/**
	 * This method initializes reset	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReset() {
		if (reset == null) {
			reset = new JButton();
			reset.setBounds(new Rectangle(445, 392, 86, 25));
			reset.setText("重置");
			reset.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jComboBox.setSelectedIndex(0);
					proprice.setText("");
					kucunacount.setText("");
					rukuacount.setText("");
					rukuremark.setText("");
					ywyname.setSelectedIndex(0);
				}
			});
		}
		return reset;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				RuKuJFrame thisClass = new RuKuJFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public RuKuJFrame() {
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
		this.setTitle("入库管理");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(446, 339, 70, 27));
			jLabel10.setText("    经手人");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(59, 244, 67, 27));
			jLabel9.setText("     备注");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(365, 121, 29, 18));
			jLabel8.setText("   元");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(54, 178, 88, 26));
			jLabel7.setText("    数量");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(53, 147, 91, 25));
			jLabel6.setText("  当前库存");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(52, 117, 92, 24));
			jLabel4.setText("    商品进价");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(47, 71, 95, 26));
			jLabel3.setText("   商品名称");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(342, 19, 90, 26));
			jLabel1.setText("   入库时间");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(43, 16, 101, 26));
			jLabel.setText("    入库编号");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(getRukuid(), null);
			jContentPane.add(getRukudatetime(), null);
			jContentPane.add(getProprice(), null);
			jContentPane.add(getKucunacount(), null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(getRukuacount(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(jLabel9, null);
			jContentPane.add(getRukuremark(), null);
			jContentPane.add(jLabel10, null);
			jContentPane.add(getYwyname(), null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(getReset(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="238,8"
