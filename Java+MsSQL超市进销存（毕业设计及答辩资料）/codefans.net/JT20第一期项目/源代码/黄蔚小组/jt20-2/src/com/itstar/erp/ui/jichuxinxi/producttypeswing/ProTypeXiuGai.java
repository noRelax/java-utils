package com.itstar.erp.ui.jichuxinxi.producttypeswing;

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
import javax.swing.JTextArea;
import javax.swing.JButton;

import com.itstar.erp.dao.baiscinfo.dao.ProTypeDao;
import com.itstar.erp.dao.baiscinfo.impl.ProTypeDaoImpl;
import com.itstar.erp.util.GetResultSet;
import com.itstar.erp.vo.producttype.ProTypeBean;

public class ProTypeXiuGai extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JComboBox ywyidjCombobox = null;
	private JLabel jLabel6 = null;
	private JTextArea protyperemark = null;
	private JButton okButton = null;
	private JLabel jLabel2 = null;
	private JButton resetButton = null;
	private JTextField protypeid = null;
	private JComboBox protypenameComboBox = null;
	private JTextField protypedanwei = null;
	private JTextField yuanywyid = null;
	String value="";  //  @jve:decl-index=0:
	String value1="";  //  @jve:decl-index=0:
	Map<Integer,String> ywyidnamemap=new HashMap<Integer,String>();  //  @jve:decl-index=0:
	Map<String,Integer> ywynameidmap=new HashMap<String,Integer>();
	/**
	 * This method initializes ywyidjCombobox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getYwyidjCombobox() {
		if (ywyidjCombobox == null) {
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
			ywyidjCombobox = new JComboBox(model);
			ywyidjCombobox.setBounds(new Rectangle(337, 136, 147, 31));
			ywyidjCombobox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						value1=ywyidjCombobox.getSelectedItem().toString();
					
				}
			}
		});
		}
		return ywyidjCombobox;
	}

	/**
	 * This method initializes protyperemark	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getProtyperemark() {
		if (protyperemark == null) {
			protyperemark = new JTextArea();
			protyperemark.setBounds(new Rectangle(126, 180, 177, 79));
		}
		return protyperemark;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(309, 223, 74, 32));
			okButton.setText("确定");
			okButton.addActionListener(new java.awt.event.ActionListener() {
//					ProTypeBean bean;
					ProTypeDao ptdi;
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(value.equals("")){
						JOptionPane.showMessageDialog(okButton, "请选择商品类别！");
					}else if(value1.equals("")){
						JOptionPane.showMessageDialog(okButton, "请选择创建人！");
					}else{
						String danwei=protypedanwei.getText().trim();
						String remark=protyperemark.getText().trim();
						if(danwei.equals("")){
							JOptionPane.showMessageDialog(okButton, "单位不能为空");
						}else{
							ptdi=new ProTypeDaoImpl();
							
							String chuangjianren=value1.equals("")?yuanywyid.getText():value1;
							int ywid=ywynameidmap.get(chuangjianren);
							ptdi.update(danwei,ywid,remark,value);
							JOptionPane.showMessageDialog(okButton, "修改成功");
							dispose();
							
							ProTypeXiuGai protypexiugai=new ProTypeXiuGai();
							protypexiugai.setVisible(true);
							protypexiugai.setSize(500,300);
							protypexiugai.setLocationRelativeTo(null);
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
			resetButton.setBounds(new Rectangle(392, 220, 82, 36));
			resetButton.setText("重置");
			resetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					protypedanwei.setText("");
					protyperemark.setText("");
					yuanywyid.setText("");
					ywyidjCombobox.setSelectedIndex(0);
					protypenameComboBox.setSelectedIndex(0);
					protypeid.setText("");
				}
			});
		}
		return resetButton;
	}

	/**
	 * This method initializes protypeid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getProtypeid() {
		if (protypeid == null) {
			protypeid = new JTextField();
			protypeid.setEditable(false);
			protypeid.setBounds(new Rectangle(131, 23, 108, 31));
		}
		return protypeid;
	}

	/**
	 * This method initializes protypenameComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getProtypenameComboBox() {
		if (protypenameComboBox == null) {
			Vector v=new Vector();
			v.add("");
			String table="tb_productType_info";
			ResultSet rs=new GetResultSet().getResultSet(table);
			try {
				while(rs.next()){
					int id=rs.getInt(1);
					String name=rs.getString(2);
					String danwei=rs.getString(3);
					int ywyid=rs.getInt(5);
					v.add(name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String table1="tb_yewuyuan_info";
			ResultSet rs1=new GetResultSet().getResultSet(table1);
			try {
				while(rs1.next()){
					int id=rs1.getInt(1);
					String name=rs1.getString(2);
					ywyidnamemap.put(id, name);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			DefaultComboBoxModel model=new DefaultComboBoxModel(v);
			protypenameComboBox = new JComboBox(model);
			protypenameComboBox.setBounds(new Rectangle(342, 24, 122, 27));
			protypenameComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){ 
						value=protypenameComboBox.getSelectedItem().toString();
						ProTypeBean bean=new ProTypeDaoImpl().Query(value);
						protypeid.setText("type"+(1000+bean.getProTypeID()));	
						protypedanwei.setText(bean.getProTypeDanwei());
						
						
						yuanywyid.setText(ywyidnamemap.get(bean.getYwyID()));
						
						System.out.println(ywyidnamemap.get(bean.getYwyID()));
						
						protyperemark.setText(bean.getProTypeRemark());
						
						
						
				}}
			});
		}
		return protypenameComboBox;
	}
//	public String getYwyName(int ywyid){
//		Connection conn=new DBConnection().getConnection();
//		String name = null;
//		try {
//			Statement s=conn.createStatement();
//			ResultSet rs=s.executeQuery("select ywyName from tb_yewuyuan_info where ywyID="+ywyid);
//			if(rs.next()){
//				name=rs.getString(1);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return name;
//	}
	/**
	 * This method initializes protypedanwei	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getProtypedanwei() {
		if (protypedanwei == null) {
			protypedanwei = new JTextField();
			protypedanwei.setBounds(new Rectangle(137, 81, 103, 22));
		}
		return protypedanwei;
	}

	/**
	 * This method initializes yuanywyid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getYuanywyid() {
		if (yuanywyid == null) {
			yuanywyid = new JTextField();
			yuanywyid.setBounds(new Rectangle(334, 79, 131, 22));
			yuanywyid.setEditable(false);
		}
		return yuanywyid;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ProTypeXiuGai thisClass = new ProTypeXiuGai();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public ProTypeXiuGai() {
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
		this.setTitle("商品类别资料调整");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(244, 136, 79, 36));
			jLabel2.setText("   创建人");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(47, 183, 76, 27));
			jLabel6.setText("   备注");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(36, 77, 94, 34));
			jLabel5.setText("   商品类别单位");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(242, 76, 82, 34));
			jLabel4.setText("  原创建人");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(240, 21, 96, 33));
			jLabel1.setText("  商品类别名称");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(38, 22, 92, 34));
			jLabel.setText("  商品类别编号");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getYwyidjCombobox(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(getProtyperemark(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getResetButton(), null);
			jContentPane.add(getProtypeid(), null);
			jContentPane.add(getProtypenameComboBox(), null);
			jContentPane.add(getProtypedanwei(), null);
			jContentPane.add(getYuanywyid(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="277,14"
