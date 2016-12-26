package com.itstar.erp.ui.jichuxinxi.producttypeswing;

import javax.swing.SwingUtilities;
import java.awt.HeadlessException;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import com.itstar.erp.dao.baiscinfo.dao.ProTypeDao;
import com.itstar.erp.dao.baiscinfo.impl.ProTypeDaoImpl;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.producttype.ProTypeBean;

public class ProTypeTianJia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JTextArea protyperemark = null;
	private JTextField protypeid = null;
	private JTextField protypename = null;
	private JTextField protypedanwei = null;
	private JTextField protypecreatetime = null;
	private JComboBox ywyid = null;
	private JButton okButton = null;
	private JButton resetButton = null;
	String value;
	String date=new GetTime().getTime();  //  @jve:decl-index=0:
	Map<String,Integer> map=new HashMap<String,Integer>();  //  @jve:decl-index=0:
	/**
	 * This method initializes protyperemark	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getProtyperemark() {
		if (protyperemark == null) {
			protyperemark = new JTextArea(5,15);
			protyperemark.setBounds(new Rectangle(77, 165, 210, 77));
		}
		return protyperemark;
	}

	/**
	 * This method initializes protypeid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getProtypeid() {
		if (protypeid == null) {
			protypeid = new JTextField();
			String table= "tb_productType_info";
			ResultSet rs=new GetResultSet().getResultSet(table);
			int proTypeID = 0;
			try {
				while(rs.next()){
					proTypeID=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			protypeid.setText("type"+(proTypeID+1000));
			protypeid.setEditable(false);
			
			protypeid.setBounds(new Rectangle(131, 25, 153, 22));
		}
		return protypeid;
	}

	/**
	 * This method initializes protypename	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getProtypename() {
		if (protypename == null) {
			protypename = new JTextField();
			protypename.setBounds(new Rectangle(132, 67, 150, 22));
		}
		return protypename;
	}

	/**
	 * This method initializes protypedanwei	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getProtypedanwei() {
		if (protypedanwei == null) {
			protypedanwei = new JTextField();
			protypedanwei.setBounds(new Rectangle(132, 122, 149, 22));
		}
		return protypedanwei;
	}

	/**
	 * This method initializes protypecreatetime	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getProtypecreatetime() {
		if (protypecreatetime == null) {
			protypecreatetime = new JTextField();
			protypecreatetime.setBounds(new Rectangle(361, 63, 127, 28));
			protypecreatetime.setText(date);
			protypecreatetime.setEditable(false);
		}
		return protypecreatetime;
	}

	/**
	 * This method initializes ywyid	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getYwyid() {
		if (ywyid == null) {
			Vector v=new Vector();
			v.add("");
			String table="tb_yewuyuan_info";
			ResultSet rs=new GetResultSet().getResultSet(table);
			try {
				while(rs.next()){
					int id=rs.getInt(1);
					String name=rs.getString(2);
					v.add(name);
					map.put(name, id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			ywyid = new JComboBox(model);
			ywyid.setBounds(new Rectangle(362, 120, 124, 27));
			ywyid.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					value=ywyid.getSelectedItem().toString();
				}
			});
		}
		return ywyid;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(298, 198, 69, 28));
			okButton.setText("确定");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				ProTypeBean bean;
				ProTypeDao ptdi;
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(value!=null){
						String name=protypename.getText().trim();
						String time=date;
						String danwei=protypedanwei.getText().trim();
						int id=map.get(value);
						String remark=protyperemark.getText().trim();
						if(name.equals("")||danwei.equals("")||value.equals("")){
							
							JOptionPane.showMessageDialog(okButton, "除备注外，其他不允许为空");
							
						}else{
							ResultSet yz=new GetRS().getResultSet("select proTypeName from tb_productType_info where proTypeName='"+name+"'");
							try {
								if(yz.next()){
									JOptionPane.showMessageDialog(okButton, "此商品类别已存在!!");
								}else{
								bean=new ProTypeBean();
								ptdi=new ProTypeDaoImpl();
								bean.setProTypeCreateTime(time);
								bean.setProTypeDanwei(danwei);
								bean.setProTypeName(name);
								bean.setProTypeRemark(remark);
								bean.setYwyID(id);
								ptdi.insert(bean);
								JOptionPane.showMessageDialog(okButton, "添加成功");
								dispose();
								
								ProTypeTianJia protypetianjia=new ProTypeTianJia();
								protypetianjia.setVisible(true);
								protypetianjia.setLocationRelativeTo(null);
								protypetianjia.setSize(500,300);
}
							} catch (HeadlessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}}else{
						
						JOptionPane.showMessageDialog(okButton, "请选择创建人！");
						
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
			resetButton.setBounds(new Rectangle(390, 197, 79, 32));
			resetButton.setText("重置");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ywyid.setSelectedIndex(0);
					protypename.setText("");
					protypedanwei.setText("");
					protyperemark.setText("");
				}
			});
		}
		return resetButton;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ProTypeTianJia thisClass = new ProTypeTianJia();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public ProTypeTianJia() {
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
		this.setTitle("增加商品类别");
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
			jLabel5.setBounds(new Rectangle(23, 163, 50, 24));
			jLabel5.setText("   备注");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(285, 121, 74, 27));
			jLabel4.setText("   创建人");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(282, 59, 78, 31));
			jLabel3.setText("  创建时间");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(24, 120, 92, 27));
			jLabel2.setText("商品类别单位");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(25, 65, 90, 31));
			jLabel1.setText("商品类别名称");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(28, 21, 87, 29));
			jLabel.setText("商品类别编号");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getProtyperemark(), null);
			jContentPane.add(getProtypeid(), null);
			jContentPane.add(getProtypename(), null);
			jContentPane.add(getProtypedanwei(), null);
			jContentPane.add(getProtypecreatetime(), null);
			jContentPane.add(getYwyid(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getResetButton(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="283,11"
