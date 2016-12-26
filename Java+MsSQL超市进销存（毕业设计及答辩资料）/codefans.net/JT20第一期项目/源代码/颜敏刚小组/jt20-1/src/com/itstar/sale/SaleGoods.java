package com.itstar.sale;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JSplitPane;
import java.awt.Point;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.WindowConstants;

public class SaleGoods extends JFrame {

	private static final long serialVersionUID = 1L;
	private JSplitPane jSplitPane = null;
	private JSplitPane jSplitPane2 = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JScrollPane jScrollPane = null;
	private JTree jTree = null;
	private SaleDao sd=null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel3 = null;
	private JComboBox jComboBox1 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	private JLabel jLabel10 = null;
	private JComboBox jComboBox2 = null;
	private JLabel jLabel11 = null;
	private JTextField jTextField = null;
	private JLabel jLabel12 = null;
	private JTextField jTextField1 = null;
	private JLabel jLabel13 = null;
	private JTextField jTextField2 = null;
	private JLabel jLabel14 = null;
	private JTextField jTextField3 = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable = null;
	private DefaultTableModel dftm;
	private String[] colname={"供应商名称","详细地址","电子邮件","联系人手机","联系人","邮编",
			"银行账号","电话"};
	private JLabel jLabel15 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private long storagenum;			//库存量
	private String severname=null;        			//供应商名称
	private String guestname=null;					//客户名称
	private String dsquare=null;					//结算方式
	private String sid=null;						//销售编号
	private String saledate=null;					//销售日期
	private String goodsname=null;					//商品名称
	private String gid=null;						//商品编号
	private String saleprice=null;					//销售单价
	private String salenumber=null;				//销售数量
	
	/**
	 * This is the default constructor
	 */
	public SaleGoods() {
		super();
		if(sd==null)
			sd=new SaleDao();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("JFrame");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(700, 550));
		this.setLocation(new Point(450, 250));
		this.setContentPane(getJSplitPane());
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setDividerSize(2);
			jSplitPane.setDividerLocation(150);
			jSplitPane.setLeftComponent(getJScrollPane());
			jSplitPane.setRightComponent(getJSplitPane2());
		}
		return jSplitPane;
	}

	/**
	 * This method initializes jSplitPane2	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane2() {
		if (jSplitPane2 == null) {
			jSplitPane2 = new JSplitPane();
			jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPane2.setDividerLocation(250);
			jSplitPane2.setTopComponent(getJPanel1());
			jSplitPane2.setBottomComponent(getJPanel2());
			jSplitPane2.setDividerSize(2);
		}
		return jSplitPane2;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(13, 209, 77, 30));
			jLabel14.setText("销售日期：");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(13, 166,77, 30));
			jLabel13.setText("销售编号：");
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(247, 166, 77, 30));
			jLabel12.setText("销售数量:");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(247, 127, 77, 30));
			jLabel11.setText("销售单价：");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(13, 127,  77, 30));
			jLabel10.setText("结算方式：");
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(345, 88,120, 30));
			jLabel9.setText("未知");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(247, 88, 77, 30));
			jLabel8.setText("商品编号:");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(345, 48, 120, 30));
			jLabel7.setText("0.0");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(247, 48, 77, 30));
			jLabel6.setText("入库均价：");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(345, 10, 120, 30));
			jLabel5.setText("0");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(247, 10, 77, 30));
			jLabel4.setText("库存量:");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(13, 88, 77, 30));
			jLabel3.setText("客户:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(13, 48, 77, 30));
			jLabel2.setText("供应商：");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(95, 10, 130, 30));
			jLabel1.setText("请左边选择");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(13, 10, 77, 30));
			jLabel.setText("商品名称:");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(jLabel, null);
			jPanel1.add(jLabel1, null);
			jPanel1.add(jLabel2, null);
			jPanel1.add(getJComboBox(), null);
			jPanel1.add(jLabel3, null);
			jPanel1.add(getJComboBox1(), null);
			jPanel1.add(jLabel4, null);
			jPanel1.add(jLabel5, null);
			jPanel1.add(jLabel6, null);
			jPanel1.add(jLabel7, null);
			jPanel1.add(jLabel8, null);
			jPanel1.add(jLabel9, null);
			jPanel1.add(jLabel10, null);
			jPanel1.add(getJComboBox2(), null);
			jPanel1.add(jLabel11, null);
			jPanel1.add(getJTextField(), null);
			jPanel1.add(jLabel12, null);
			jPanel1.add(getJTextField1(), null);
			jPanel1.add(jLabel13, null);
			jPanel1.add(getJTextField2(), null);
			jPanel1.add(jLabel14, null);
			jPanel1.add(getJTextField3(), null);
			jPanel1.add(getJButton(), null);
			jPanel1.add(getJButton1(), null);
			initGuest();
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("提供该商品供应商的详细信息");
			jLabel15.setHorizontalAlignment(SwingConstants.CENTER);
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BorderLayout());
			jPanel2.add(getJScrollPane1(), BorderLayout.CENTER);
			jPanel2.add(jLabel15, BorderLayout.NORTH);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTree());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getJTree() {
		if (jTree == null) {
			DefaultMutableTreeNode root=new DefaultMutableTreeNode("商品类型");
			jTree = new JTree(root);
			jTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
				public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
					DefaultMutableTreeNode node=(DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
					if(node.isLeaf()){
					String nodeget=(String) node.getUserObject();
					jLabel1.setText(nodeget);
					initJcomboBox(nodeget);
					initTable(nodeget);
					}
				}
			});
			List<String> father=sd.getType();
			for(String fat:father){
				DefaultMutableTreeNode fath=new DefaultMutableTreeNode(fat);
				root.add(fath);
				List<String> chi=sd.getName(fat);
				for(String child:chi){
					DefaultMutableTreeNode chil=new DefaultMutableTreeNode(child);	
					fath.add(chil);
				}
			}
			
		}
		return jTree;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(95, 48, 130, 30));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					severname=e.getItem().toString();
					String gname=jLabel1.getText();
					String[] num=sd.querynumber(gname, severname);
					storagenum=new Long(num[0]);
					jLabel5.setText(num[0]);
					double total=new Double(num[1]);
					double number=new Double(num[0]);
					jLabel7.setText(new Double(total/number).toString());
					jLabel9.setText(sd.querySid(gname,severname));
					jTextField3.setText(getDate());
				}
			});
		}
		return jComboBox;
	}
	private void initJcomboBox(String gname){
		jComboBox.removeAllItems();
		List<String> list= sd.querySever(gname);
		for(String st:list){
			jComboBox.addItem(st);
		}
	}

	/**
	 * This method initializes jComboBox1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.setBounds(new Rectangle(95, 88,  130, 30));
			jComboBox1.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					guestname=e.getItem().toString();
				}
			});
		}
		return jComboBox1;
	}
	private void initGuest(){
		jComboBox1.removeAllItems();
		List<String> guest=sd.queryGuest();
		for(String str:guest){
			jComboBox1.addItem(str);
		}
	}

	/**
	 * This method initializes jComboBox2	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox2() {
		if (jComboBox2 == null) {
			String[] str={"现金结算","银行转账"};
			jComboBox2 = new JComboBox(str);
			jComboBox2.setBounds(new Rectangle(95, 127, 130, 30));
			jComboBox2.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					dsquare=e.getItem().toString();
				}
			});
		}
		return jComboBox2;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(345, 127, 120, 30));
			jTextField.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					if(!jTextField.getText().equals(""))
					try {
					double  price=	new Double(jTextField.getText());
					if(price<0){
						JOptionPane.showMessageDialog(null, "单价不能为负数",
								"Fail", JOptionPane.ERROR_MESSAGE);
						jTextField.setText("");
					}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请为单价 输入数字类型",
								"Fail", JOptionPane.ERROR_MESSAGE);
						jTextField.setText("");
						
					}
				}
			});
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(345, 166, 120, 30));
			jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					if(!jTextField1.getText().equals(""))
					try {
						long num=new Long(jTextField1.getText());
						if(num<0){
							JOptionPane.showMessageDialog(null, "数量不能为负数",
									"Fail", JOptionPane.ERROR_MESSAGE);
							jTextField1.setText("");
						}else if(num>storagenum){
							JOptionPane.showMessageDialog(null, "数量超出库存量",
									"Fail", JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请为数量 输入数字整型",
								"Fail", JOptionPane.ERROR_MESSAGE);
						jTextField1.setText("");	
					}
				}
			});
		}
		return jTextField1;
	}

	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setBounds(new Rectangle(95, 166, 130, 30));
		}
		return jTextField2;
	}

	/**
	 * This method initializes jTextField3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setBounds(new Rectangle(95, 209, 130, 30));
		}
		return jTextField3;
	}
	private String getDate(){
		java.sql.Date date= new java.sql.Date(new Date().getTime());
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		return sd.format(date).toString();
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			dftm = (DefaultTableModel) jTable.getModel();
			dftm.setColumnIdentifiers(colname);
		}
		return jTable;
	}
	public void initTable(String gname){
		List<String> list= sd.querySever(gname);
		String[][] table=new String[list.size()][8];
		int i=0;
		for(String sname:list){
			ServerBean sb=sd.queryserver(sname);
			table[i][0]=sb.getSname();
			table[i][1]=sb.getSaddress();
			table[i][2]=sb.getSemail();
			table[i][3]=sb.getSphone();
			table[i][4]=sb.getSperson();        
			table[i][5]=sb.getSpostcode();  			         			         			        
			table[i][6]=sb.getSbank();
			table[i][7]=sb.getSnumber(); 
			i++;
		}
		dftm.setDataVector(table,colname);

	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(255, 207, 124, 34));
			jButton.setFont(new Font("\u4eff\u5b8b_GB2312", Font.BOLD | Font.ITALIC, 14));
			jButton.setText("销售");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					sid=jTextField2.getText();
					saledate= jTextField3.getText();
					saleprice=jTextField.getText();
					salenumber=jTextField1.getText();
					gid=jLabel9.getText();
					if(sid.equals("")||saledate.equals("")||saleprice.equals("")||
							salenumber.equals("")||gid.equals("")){
						JOptionPane.showMessageDialog(null, "填写数据不能为空",
								"Fail", JOptionPane.ERROR_MESSAGE);
					}else{
						if(sd.insertUpdate(sid, guestname, saledate, dsquare, gid, saleprice, salenumber, storagenum))						
							JOptionPane.showMessageDialog(null, "销售完成", "OK",
									JOptionPane.PLAIN_MESSAGE);	
						else
							JOptionPane.showMessageDialog(null, "销售失败",
									"Fail", JOptionPane.ERROR_MESSAGE);
					}														
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(400, 207,  124, 34));
			jButton1.setText("退出");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if((JOptionPane.showConfirmDialog(null, 
							"退出", "退出", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION)
					setVisible(false);
				}
			});
		}
		return jButton1;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
