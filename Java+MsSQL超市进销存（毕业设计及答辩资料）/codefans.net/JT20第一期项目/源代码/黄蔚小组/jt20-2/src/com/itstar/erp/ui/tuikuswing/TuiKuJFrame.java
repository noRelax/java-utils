package com.itstar.erp.ui.tuikuswing;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector; 

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.dao.sell.SellDaoImpl;
import com.itstar.erp.dao.tuihuo.TuiHuoDaoImpl;
import com.itstar.erp.dao.tuiku.TuiKuDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.product.ProBean;
import com.itstar.erp.vo.ruku.RuKuBean;
import com.itstar.erp.vo.supply.SupplyBean;
import com.itstar.erp.vo.tuiku.TuiKuBean;

public class TuiKuJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	private JTextField tuikubhT = null;
	private JTextField tuikutimeT = null;
	private JComboBox rukubhC = null;
	private JTextField rukutimeT = null;
	private JTextField pronameT = null;
	private JTextField rukuacountT = null;
	private JTextField spnameT = null;
	private JTextField propriceT = null;
	private JTextArea tuikuremarkT = null;
	private JTextField rukuywyT = null;
	private JLabel jLabel10 = null;
	private JTextField tuikuacountT = null;
	private JLabel jLabel11 = null;
	private JComboBox ywyC = null;
	private JButton resetButton = null;
	private JButton okButton = null;
	String rkvalue="请选择";  //  @jve:decl-index=0:
	String ywyvalue="请选择";
	String tkdate=new GetTime().getTime("");  //  @jve:decl-index=0:
	String date=new GetTime().getTime();
	Map<String,Integer> rkbhidmap=new TreeMap<String,Integer>();  //  @jve:decl-index=0:
	/**
	 * This method initializes tuikubhT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTuikubhT() {
		if (tuikubhT == null) {
			tuikubhT = new JTextField();
			ResultSet rs=new GetRS().getResultSet("select max(tkID) from tb_tuiku_info");
			int tkid=0;
			try {
				if(rs.next()){
					tkid=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			tuikubhT.setText("TK"+tkdate+"_"+(1000+tkid));
			tuikubhT.setEditable(false);
			tuikubhT.setBounds(new Rectangle(191, 28, 193, 22));
		}
		return tuikubhT;
	}

	/**
	 * This method initializes tuikutimeT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTuikutimeT() {
		if (tuikutimeT == null) {
			tuikutimeT = new JTextField();
			tuikutimeT.setText(date);
			tuikutimeT.setEditable(false);
			tuikutimeT.setBounds(new Rectangle(484, 28, 195, 22));
		}
		return tuikutimeT;
	}

	/**
	 * This method initializes rukubhC	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getRukubhC() {
		if (rukubhC == null) {
			String field1="rukuID";
			String field2="rukuDateTime";
			String table="tb_ruku_info";
			List<Integer> rkidlist=new TuiHuoDaoImpl().getIntList(field1, table);
			List tuikutimelist=new TuiHuoDaoImpl().getStringList(field2, table);
			Vector v=new Vector();
			v.add("请选择");
			String str;
			
			for(int i=0;i<rkidlist.size();i++){
				str="TK"+new GetTime().toformat(tuikutimelist.get(i).toString())+"_"+(1000+(int)rkidlist.get(i));
				v.add(str);
				rkbhidmap.put(str, (int)rkidlist.get(i));
			}
			System.out.println(rkbhidmap);
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			rukubhC = new JComboBox(model);
			rukubhC.setBounds(new Rectangle(194, 70, 192, 27));
			rukubhC.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {

					if(e.getStateChange() == ItemEvent.SELECTED){ 
						rkvalue=rukubhC.getSelectedItem().toString().trim();
						if(!rkvalue.equals("请选择")){
							int rukuid=rkbhidmap.get(rkvalue);                          //入库编号
							RuKuBean bean=new TuiKuDaoImpl().getRuKuBean(rukuid);
							
							rukutimeT.setText(new GetTime().format(bean.getRukuDateTime()));//入库时间
							int proid=bean.getProID();                           //商品编号
							String proname=new SellDaoImpl().getproName(proid);      //商品名称      
							pronameT.setText(proname);
							
							ProBean probean=new TuiKuDaoImpl().getProBean(proid);
							double proprice=probean.getProPrice();                   //商品进价
							propriceT.setText(""+proprice);
							int spid=probean.getSpID();                         //供应商编号
							SupplyBean spbean=new TuiKuDaoImpl().getSpBean(spid);
							String spname=spbean.getSpName();                       //供应商名称
							spnameT.setText(spname);
							rukuacountT.setText(""+bean.getRukuAcount());           //入库数量
							
							int ywyid=bean.getYwyID();                            //业务员编号
							
						
							String ywyname=new TuiHuoDaoImpl().getywyName(ywyid);  //业务员名称
							rukuywyT.setText(ywyname);
						}
						if(rkvalue.equals("请选择")){
							rukutimeT.setText("");
							pronameT.setText("");
							rukuacountT.setText("");
							spnameT.setText("");
							propriceT.setText("");
							rukuywyT.setText("");
						}
					}
				
				}
			});
		}
		return rukubhC;
	}

	/**
	 * This method initializes rukutimeT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRukutimeT() {
		if (rukutimeT == null) {
			rukutimeT = new JTextField();
			rukutimeT.setEditable(false);
			rukutimeT.setBounds(new Rectangle(489, 71, 185, 22));
		}
		return rukutimeT;
	}

	/**
	 * This method initializes pronameT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPronameT() {
		if (pronameT == null) {
			pronameT = new JTextField();
			pronameT.setEditable(false);
			pronameT.setBounds(new Rectangle(192, 111, 195, 22));
		}
		return pronameT;
	}

	/**
	 * This method initializes rukuacountT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRukuacountT() {
		if (rukuacountT == null) {
			rukuacountT = new JTextField();
			rukuacountT.setEditable(false);
			rukuacountT.setBounds(new Rectangle(491, 114, 183, 22));
		}
		return rukuacountT;
	}

	/**
	 * This method initializes spnameT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSpnameT() {
		if (spnameT == null) {
			spnameT = new JTextField();
			spnameT.setEditable(false);
			spnameT.setBounds(new Rectangle(196, 164, 195, 22));
		}
		return spnameT;
	}

	/**
	 * This method initializes propriceT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPropriceT() {
		if (propriceT == null) {
			propriceT = new JTextField();
			propriceT.setEditable(false);
			propriceT.setBounds(new Rectangle(495, 164, 178, 22));
		}
		return propriceT;
	}

	/**
	 * This method initializes tuikuremarkT	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTuikuremarkT() {
		if (tuikuremarkT == null) {
			tuikuremarkT = new JTextArea();
			tuikuremarkT.setBounds(new Rectangle(196, 210, 190, 148));
		}
		return tuikuremarkT;
	}

	/**
	 * This method initializes rukuywyT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRukuywyT() {
		if (rukuywyT == null) {
			rukuywyT = new JTextField();
			rukuywyT.setEditable(false);
			rukuywyT.setBounds(new Rectangle(497, 200, 179, 22));
		}
		return rukuywyT;
	}

	/**
	 * This method initializes tuikuacountT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTuikuacountT() {
		if (tuikuacountT == null) {
			tuikuacountT = new JTextField();
			tuikuacountT.setBounds(new Rectangle(498, 262, 179, 22));
		}
		return tuikuacountT;
	}

	/**
	 * This method initializes ywyC	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getYwyC() {
		if (ywyC == null) {
			String field="ywyName";
			String table="tb_yewuyuan_info";
			List<String> list=new TuiHuoDaoImpl().getStringList(field, table);
			Vector v=new Vector();
			v.add("请选择");
			for(int i=0;i<list.size();i++){
				v.add(list.get(i));
			}
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			ywyC = new JComboBox(model);
			ywyC.setBounds(new Rectangle(502, 321, 178, 27));
			ywyC.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						ywyvalue=ywyC.getSelectedItem().toString().trim();
					}
				}
			});
		}
		return ywyC;
	}

	/**
	 * This method initializes resetButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getResetButton() {
		if (resetButton == null) {
			resetButton = new JButton();
			resetButton.setBounds(new Rectangle(405, 407, 90, 28));
			resetButton.setText("重置");
		}
		return resetButton;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(541, 403, 107, 28));
			okButton.setText("确定退库");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					if(rkvalue.equals("请选择")||ywyvalue.equals("请选择")){
						JOptionPane.showMessageDialog(okButton, "下拉框必须选择");
					}else{
						
						int rukuid=rkbhidmap.get(rkvalue);                          //入库编号
						RuKuBean bean=new TuiKuDaoImpl().getRuKuBean(rukuid);
						
						String rukutime=new GetTime().format(bean.getRukuDateTime());//入库时间
						int proid=bean.getProID();                           //商品编号
						String proname=new SellDaoImpl().getproName(proid);      //商品名称      
						
						
						ProBean probean=new TuiKuDaoImpl().getProBean(proid);
						double proprice=probean.getProPrice();                   //商品进价
					
						int spid=probean.getSpID();                         //供应商编号
						SupplyBean spbean=new TuiKuDaoImpl().getSpBean(spid);
						String spname=spbean.getSpName();                       //供应商名称
					
						int rukuacount=bean.getRukuAcount();           //入库数量
						
						int ywyid=bean.getYwyID();                            //业务员编号
						
					
						String ywyname=new TuiHuoDaoImpl().getywyName(ywyid);  //业务员名称
						rukuywyT.setText(ywyname);
						
						
						
						String remark=tuikuremarkT.getText().trim();
						String sl=tuikuacountT.getText().trim();
						if(remark.equals("")||sl.equals("")){
							JOptionPane.showMessageDialog(okButton, "退库原因 与 退库数量 不能为空 ！");
						}else{
							try{
							int tuikusl=Integer.parseInt(sl);
							if(tuikusl<=0){
								JOptionPane.showMessageDialog(okButton, "退库数量必须大于零 !");
							}else if(tuikusl>rukuacount){
								JOptionPane.showMessageDialog(okButton, "退库数量大于已入库的数量，错误  !");
							}else{
								TuiKuBean tkbean=new TuiKuBean();
								tkbean.setRukuID(rukuid);
								tkbean.setTkTime(date);
								tkbean.setTkRemark(remark);
								tkbean.setYwyID(ywyid);
								tkbean.setTkAcount(tuikusl);
								
								int kucun=new TuiHuoDaoImpl().getkucun(proid);
								int left=kucun-tuikusl;                              //库存剩余
								int rukuleft=rukuacount-tuikusl;                     //入库数量剩余
								
								
								new TuiKuDaoImpl().update(rukuid, rukuleft);             //更新入库表
								new KuCunDaoImpl().update(proid, left);                //更新库存
								new TuiKuDaoImpl().insert(tkbean);                    //更新退库表
								
								
								
								JOptionPane.showMessageDialog(okButton, "退库成功 ！！");
								dispose();
								
								TuiKuJFrame tuikujframe=new TuiKuJFrame();
								tuikujframe.setSize(700,500);
								tuikujframe.setTitle("退库管理");
								tuikujframe.setLocationRelativeTo(null);
								tuikujframe.setVisible(true);
								
							}}catch(NumberFormatException e1){
								JOptionPane.showMessageDialog(okButton, "退库数量必须为数字 !");
							}
						}
					}
				
				}
			});
		}
		return okButton;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TuiKuJFrame thisClass = new TuiKuJFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public TuiKuJFrame() {
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
		this.setTitle("退库管理");
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
			jLabel11.setBounds(new Rectangle(400, 330, 90, 18));
			jLabel11.setText("       经手人");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(396, 265, 93, 18));
			jLabel10.setText("    退库数量");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(395, 199, 94, 26));
			jLabel9.setText("入库经手人");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(71, 205, 113, 27));
			jLabel8.setText("退库原因（必填）");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(393, 161, 94, 24));
			jLabel7.setText("   商品进价");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(72, 159, 114, 28));
			jLabel6.setText("    供应商名称");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(396, 114, 88, 25));
			jLabel5.setText("  商品入库数量");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(73, 111, 108, 24));
			jLabel4.setText("      商品名称");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(397, 72, 88, 24));
			jLabel3.setText("  商品入库时间");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(74, 72, 106, 24));
			jLabel2.setText("     入库编号");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(396, 29, 82, 24));
			jLabel1.setText("    退库时间");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(79, 26, 104, 27));
			jLabel.setText("     退库编号");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(jLabel9, null);
			jContentPane.add(getTuikubhT(), null);
			jContentPane.add(getTuikutimeT(), null);
			jContentPane.add(getRukubhC(), null);
			jContentPane.add(getRukutimeT(), null);
			jContentPane.add(getPronameT(), null);
			jContentPane.add(getRukuacountT(), null);
			jContentPane.add(getSpnameT(), null);
			jContentPane.add(getPropriceT(), null);
			jContentPane.add(getTuikuremarkT(), null);
			jContentPane.add(getRukuywyT(), null);
			jContentPane.add(jLabel10, null);
			jContentPane.add(getTuikuacountT(), null);
			jContentPane.add(jLabel11, null);
			jContentPane.add(getYwyC(), null);
			jContentPane.add(getResetButton(), null);
			jContentPane.add(getOkButton(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="297,-29"
