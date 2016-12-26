package com.itstar.erp.ui.tuihuoswing;

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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import com.itstar.erp.dao.kucun.KuCunDaoImpl;
import com.itstar.erp.dao.sell.SellDaoImpl;
import com.itstar.erp.dao.tuihuo.TuiHuoDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.sell.SellBean;
import com.itstar.erp.vo.tuihuo.TuiHuoBean;

public class TuiHuoJFrame extends JFrame {

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
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JButton resetButton = null;
	private JButton okButton = null;
	private JTextField xssjT = null;
	private JTextField pronameT = null;
	private JTextField xsslT = null;
	private JTextField khmcT = null;
	private JTextField thbhT = null;
	private JTextField thsjT = null;
	private JComboBox xsbhC = null;
	private JTextField xsjjT = null;
	private JTextArea thyyT = null;
	private JTextField xsywyT = null;
	private JTextField thslT = null;
	private JComboBox ywyC = null;
	String xsvalue="请选择";  //  @jve:decl-index=0:
	String ywyvalue="请选择";  //  @jve:decl-index=0:
	Map<String,Integer> sellbhidmap=new TreeMap<String,Integer>();  //  @jve:decl-index=0:
	String thdate=new GetTime().getTime("");  //  @jve:decl-index=0:
	String date=new GetTime().getTime();
	/**
	 * This method initializes resetButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getResetButton() {
		if (resetButton == null) {
			resetButton = new JButton();
			resetButton.setBounds(new Rectangle(395, 410, 89, 26));
			resetButton.setText("重置");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					xsbhC.setSelectedIndex(0);
					thyyT.setText("");
					thslT.setText("");
					ywyC.setSelectedIndex(0);
				}
			});
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
			okButton.setBounds(new Rectangle(514, 410, 110, 28));
			okButton.setText("确认退货");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(xsvalue.equals("请选择")||ywyvalue.equals("请选择")){
						JOptionPane.showMessageDialog(okButton, "下拉框必须选择");
					}else{
						
						int sellid=sellbhidmap.get(xsvalue);           //销售编号
						SellBean bean=new TuiHuoDaoImpl().getSellBean(sellid);
						xssjT.setText(new GetTime().format(bean.getSellDateTime()));//销售时间
						int proid=bean.getProID();                               //商品编号
						String proname=new SellDaoImpl().getproName(proid);       //商品名称
						int sellacount=bean.getSellAcount();                         //销售数量
						int kehuid=bean.getKehuID();                                    //客户编号
						String kehuname=new TuiHuoDaoImpl().getkehuName(kehuid);//客户名称
						double sellprice=bean.getSellPrice();                      //销售价格
						int ywyid=bean.getYwyID();                                 //业务员编号
						String ywyname=new TuiHuoDaoImpl().getywyName(ywyid);         //业务员名称
						
						
						
						String remark=thyyT.getText().trim();
						String sl=thslT.getText().trim();
						if(remark.equals("")||sl.equals("")){
							JOptionPane.showMessageDialog(okButton, "退货原因 与 退货数量 不能为空 ！");
						}else{
							try{
							int tuihuosl=Integer.parseInt(sl);
							if(tuihuosl<=0){
								JOptionPane.showMessageDialog(okButton, "退货数量必须大于零 !");
							}else if(tuihuosl>sellacount){
								JOptionPane.showMessageDialog(okButton, "退货数量大于已销售的数量，错误  !");
							}else{
								TuiHuoBean thbean=new TuiHuoBean();
								thbean.setSellID(sellid);
								thbean.setThTime(date);
								thbean.setThRemark(remark);
								thbean.setYwyID(ywyid);
								thbean.setThAcount(tuihuosl);
								int kucun=new TuiHuoDaoImpl().getkucun(proid);
								int left=kucun+tuihuosl;                              //库存剩余
								int sellleft=sellacount-tuihuosl;                     //销售数量剩余
								
								
								new SellDaoImpl().update(sellid, sellleft);             //更新销售表
								
								new KuCunDaoImpl().update(proid, left);                //更新库存
								
								new TuiHuoDaoImpl().insert(thbean);                    //更新退货表
								
								
								
								JOptionPane.showMessageDialog(okButton, "退货成功 ！！");
								dispose();
								
								TuiHuoJFrame tuihuojframe=new TuiHuoJFrame();
								tuihuojframe.setSize(700,500);
								tuihuojframe.setTitle("退货管理");
								tuihuojframe.setLocationRelativeTo(null);
								tuihuojframe.setVisible(true);
								
							}}catch(NumberFormatException e1){
								JOptionPane.showMessageDialog(okButton, "退货数量必须为数字 !");
							}
						}
					}
				}
			});
		}
		return okButton;
	}

	/**
	 * This method initializes xssjT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getXssjT() {
		if (xssjT == null) {
			xssjT = new JTextField();
			xssjT.setEditable(false);
			xssjT.setBounds(new Rectangle(456, 80, 199, 22));
		}
		return xssjT;
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
			pronameT.setBounds(new Rectangle(180, 136, 176, 22));
		}
		return pronameT;
	}

	/**
	 * This method initializes xsslT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getXsslT() {
		if (xsslT == null) {
			xsslT = new JTextField();
			xsslT.setEditable(false);
			xsslT.setBounds(new Rectangle(458, 135, 199, 22));
		}
		return xsslT;
	}

	/**
	 * This method initializes khmcT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getKhmcT() {
		if (khmcT == null) {
			khmcT = new JTextField();
			khmcT.setEditable(false);
			khmcT.setBounds(new Rectangle(185, 188, 177, 22));
		}
		return khmcT;
	}

	/**
	 * This method initializes thbhT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getThbhT() {
		if (thbhT == null) {
			thbhT = new JTextField();
			ResultSet rs=new GetRS().getResultSet("select max(thID) from tb_tuihuo_info");
			int thid=0;
			try {
				if(rs.next()){
					thid=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			thbhT.setText("TH"+thdate+"_"+(1000+thid));
			thbhT.setEditable(false);
			thbhT.setBounds(new Rectangle(178, 21, 181, 22));
		}
		return thbhT;
	}

	/**
	 * This method initializes thsjT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getThsjT() {
		if (thsjT == null) {
			thsjT = new JTextField();
			thsjT.setText(date);
			thsjT.setEditable(false);
			thsjT.setBounds(new Rectangle(456, 24, 193, 22));
		}
		return thsjT;
	}

	/**
	 * This method initializes xsbhC	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getXsbhC() {
		if (xsbhC == null) {
			
			String field1="sellID";
			String field2="sellDateTime";
			String table="tb_sell_info";
			List<Integer> thidlist=new TuiHuoDaoImpl().getIntList(field1, table);
			List selltimelist=new TuiHuoDaoImpl().getStringList(field2, table);
			Vector v=new Vector();
			v.add("请选择");
			String str;
			
			for(int i=0;i<thidlist.size();i++){
				str="XS"+new GetTime().toformat(selltimelist.get(i).toString())+"_"+(1000+(int)thidlist.get(i));
				v.add(str);
				sellbhidmap.put(str, (int)thidlist.get(i));
			}
			System.out.println(sellbhidmap);
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			xsbhC = new JComboBox(model);
			xsbhC.setBounds(new Rectangle(180, 80, 175, 27));
			xsbhC.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						xsvalue=xsbhC.getSelectedItem().toString().trim();
						if(!xsvalue.equals("请选择")){
							int sellid=sellbhidmap.get(xsvalue);
							SellBean bean=new TuiHuoDaoImpl().getSellBean(sellid);
							xssjT.setText(new GetTime().format(bean.getSellDateTime()));
							int proid=bean.getProID();
							String proname=new SellDaoImpl().getproName(proid);
							pronameT.setText(proname);
							xsslT.setText(""+bean.getSellAcount());
							int kehuid=bean.getKehuID();
							String kehuname=new TuiHuoDaoImpl().getkehuName(kehuid);
							khmcT.setText(kehuname);
							xsjjT.setText(""+bean.getSellPrice());
							int ywyid=bean.getYwyID();
							String ywyname=new TuiHuoDaoImpl().getywyName(ywyid);
							xsywyT.setText(ywyname);
						}
						if(xsvalue.equals("请选择")){
							xssjT.setText("");
							pronameT.setText("");
							xsslT.setText("");
							khmcT.setText("");
							xsjjT.setText("");
							xsywyT.setText("");
						}
					}
				}
			});
		}
		return xsbhC;
	}

	/**
	 * This method initializes xsjjT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getXsjjT() {
		if (xsjjT == null) {
			xsjjT = new JTextField();
			xsjjT.setEditable(false);
			xsjjT.setBounds(new Rectangle(455, 192, 204, 22));
		}
		return xsjjT;
	}

	/**
	 * This method initializes thyyT	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getThyyT() {
		if (thyyT == null) {
			thyyT = new JTextArea();
			thyyT.setBounds(new Rectangle(186, 241, 171, 131));
		}
		return thyyT;
	}

	/**
	 * This method initializes xsywyT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getXsywyT() {
		if (xsywyT == null) {
			xsywyT = new JTextField();
			xsywyT.setEditable(false);
			xsywyT.setBounds(new Rectangle(454, 231, 204, 22));
		}
		return xsywyT;
	}

	/**
	 * This method initializes thslT	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getThslT() {
		if (thslT == null) {
			thslT = new JTextField();
			thslT.setBounds(new Rectangle(459, 287, 202, 22));
		}
		return thslT;
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
			ywyC.setBounds(new Rectangle(464, 345, 197, 27));
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TuiHuoJFrame thisClass = new TuiHuoJFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public TuiHuoJFrame() {
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
		this.setTitle("退货管理");
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
			jLabel11.setBounds(new Rectangle(362, 352, 90, 18));
			jLabel11.setText("经手人");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(80, 238, 104, 25));
			jLabel10.setText("退货原因（必填）");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(362, 235, 80, 18));
			jLabel9.setText("销售经手人");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(363, 193, 78, 18));
			jLabel8.setText("商品销售价格");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(92, 191, 91, 18));
			jLabel7.setText("客户名称");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(359, 290, 90, 21));
			jLabel6.setText("退货数量");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(369, 134, 81, 27));
			jLabel5.setText("商品销售数量");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(361, 75, 85, 31));
			jLabel4.setText("商品销售时间");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(89, 133, 84, 26));
			jLabel3.setText("商品名称");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(362, 22, 84, 23));
			jLabel2.setText("退货时间");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(83, 80, 89, 24));
			jLabel1.setText("销售编号");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(83, 20, 88, 26));
			jLabel.setText("退货编号");
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
			jContentPane.add(jLabel10, null);
			jContentPane.add(jLabel11, null);
			jContentPane.add(getResetButton(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getXssjT(), null);
			jContentPane.add(getPronameT(), null);
			jContentPane.add(getXsslT(), null);
			jContentPane.add(getKhmcT(), null);
			jContentPane.add(getThbhT(), null);
			jContentPane.add(getThsjT(), null);
			jContentPane.add(getXsbhC(), null);
			jContentPane.add(getXsjjT(), null);
			jContentPane.add(getThyyT(), null);
			jContentPane.add(getXsywyT(), null);
			jContentPane.add(getThslT(), null);
			jContentPane.add(getYwyC(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="266,3"
