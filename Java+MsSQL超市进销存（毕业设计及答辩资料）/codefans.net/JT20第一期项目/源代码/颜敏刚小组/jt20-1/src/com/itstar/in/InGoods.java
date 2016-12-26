package com.itstar.in;

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

import com.itstar.in.InBean;
import com.itstar.in.InDao;

import java.awt.Font;
import javax.swing.WindowConstants;

public class InGoods extends JFrame {

	private static final long serialVersionUID = 1L;
	private JSplitPane jSplitPane = null;
	private JSplitPane jSplitPane2 = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JScrollPane jScrollPane = null;
	private JTree jTree = null;
	private InDao sd=null;
	private JLabel jLabel = null;
	private JLabel jLabel_Gname = null;
	private JLabel jLabel2 = null;
	private JComboBox jComboBox_Gsever = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel_Snumber = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel_Gid = null;
	private JLabel jLabel10 = null;
	private JComboBox jComboBox2 = null;
	private JLabel jLabel11 = null;
	private JTextField jTextField_Pprice = null;
	private JLabel jLabel12 = null;
	private JTextField jTextField_Pnumber = null;
	private JLabel jLabel13 = null;
	private JTextField jTextField_did = null;
	private JLabel jLabel14 = null;
	private JTextField jTextField_Ddate = null;
	private JScrollPane jScrollPane1 = null;
	private JTable jTable = null;
	private DefaultTableModel dftm;
	private String[] colname={"商品编号","商品名称","供应商名称","产地","类型","计量单位"};
	private JLabel jLabel15 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private String Sgname=null;					//商品名称
	private String dvariety=null;					//现有库存量
	private String ssname=null;        			//供应商名称
	private String dsquare=null;					//结算方式
	private String pdid=null;						//入库编号
	private String ddate=null;						//入库日期
	private String gid=null;						//商品编号
	private String pprice=null;					//入库单价
	private String pnumber=null;					//入库数量
	private String totalprice=null;				//入库总价	
	private JLabel jLabel121 = null;
	private JTextField jTextField_Dtotal = null;
	private JLabel jLabel1211 = null;
	private JLabel jLabel1212 = null;
	
	/**
	 * This is the default constructor
	 */
	public InGoods() {
		super();
		if(sd==null)
			sd=new InDao();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("JFrame");
		this.setResizable(false);
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
			jLabel1212 = new JLabel();
			jLabel1212.setBounds(new Rectangle(467, 93, 40, 29));
			jLabel1212.setText("元");
			jLabel1211 = new JLabel();
			jLabel1211.setBounds(new Rectangle(469, 177, 36, 31));
			jLabel1211.setText("元");
			jLabel121 = new JLabel();
			jLabel121.setBounds(new Rectangle(248, 182, 79, 28));
			jLabel121.setText("入库总价:");
			jLabel14 = new JLabel();
			jLabel14.setBounds(new Rectangle(6, 181, 76, 30));
			jLabel14.setText("入库日期：");
			jLabel13 = new JLabel();
			jLabel13.setBounds(new Rectangle(7, 141, 75, 30));
			jLabel13.setText("入库编号：");
			jLabel12 = new JLabel();
			jLabel12.setBounds(new Rectangle(249, 141, 77, 30));
			jLabel12.setText("入库总量:");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(245, 92, 77, 30));
			jLabel11.setText("入库单价：");
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(8, 95, 74, 30));
			jLabel10.setText("结算方式：");
			jLabel_Gid = new JLabel();
			jLabel_Gid.setBounds(new Rectangle(344, 51, 120, 30));
			jLabel_Gid.setText("未知");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(247, 47, 77, 30));
			jLabel8.setText("商品编号:");
			jLabel_Snumber = new JLabel();
			jLabel_Snumber.setBounds(new Rectangle(345, 10, 120, 30));
			InDao inNum=new InDao();
			jLabel_Snumber.setText("0");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(247, 10, 77, 30));
			jLabel4.setText("现有库存量:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(11, 48, 77, 30));
			jLabel2.setText("供应商：");
			jLabel_Gname = new JLabel();
			jLabel_Gname.setBounds(new Rectangle(95, 10, 130, 30));
			jLabel_Gname.setText("请左边选择");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(13, 10, 77, 30));
			jLabel.setText("商品名称:");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(jLabel, null);
			jPanel1.add(jLabel_Gname, null);
			jPanel1.add(jLabel2, null);
			jPanel1.add(getJComboBox_Gsever(), null);
			jPanel1.add(jLabel4, null);
			jPanel1.add(jLabel_Snumber, null);
			jPanel1.add(jLabel8, null);
			jPanel1.add(jLabel_Gid, null);
			jPanel1.add(jLabel10, null);
			jPanel1.add(getJComboBox2(), null);
			jPanel1.add(jLabel11, null);
			jPanel1.add(getJTextField_Pprice(), null);
			jPanel1.add(jLabel12, null);
			jPanel1.add(getJTextField_Pnumber(), null);
			jPanel1.add(jLabel13, null);
			jPanel1.add(getJTextField_did(), null);
			jPanel1.add(jLabel14, null);
			jPanel1.add(getJTextField_Ddate(), null);
			jPanel1.add(getJButton(), null);
			jPanel1.add(getJButton1(), null);
			jPanel1.add(jLabel121, null);
			jPanel1.add(getJTextField_Dtotal(), null);
			jPanel1.add(jLabel1211, null);
			jPanel1.add(jLabel1212, null);
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
			jLabel15.setText("商品的详细信息");
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
					jLabel_Gname.setText(nodeget);
					Sgname=nodeget;
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
	 * This method initializes jComboBox_Gsever	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox_Gsever() {
		if (jComboBox_Gsever == null) {
			jComboBox_Gsever = new JComboBox();
			jComboBox_Gsever.setBounds(new Rectangle(98, 47, 127, 30));
			jComboBox_Gsever.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					ssname=e.getItem().toString();
			
						String num=sd.querynumber(Sgname);
						gid=sd.querygid(Sgname,ssname);
						if(num!=null)
							jLabel_Snumber.setText(gid);
						jLabel_Gid.setText(gid);
			
					
				}
			});
		}
		return jComboBox_Gsever;
	}
	private void initJcomboBox(String Sgname){
		jComboBox_Gsever.removeAllItems();
		List<String> list= sd.querySever(Sgname);
		for(String st:list){
			jComboBox_Gsever.addItem(st);
		}
	}



	/**
	 * This method initializes jComboBox2	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox2() {
		if (jComboBox2 == null) {
//			String[] str={"现金结算","银行转账"};
//			jComboBox2 = new JComboBox(str);
			jComboBox2=new JComboBox();
			jComboBox2.addItem("现金结算");
			jComboBox2.addItem("银行转账");
			jComboBox2.setBounds(new Rectangle(98, 97, 130, 30));
			jComboBox2.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					dsquare=e.getItem().toString();
				}
			});
		}
		return jComboBox2;
	}

	/**
	 * This method initializes jTextField_Pprice	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_Pprice() {
		if (jTextField_Pprice == null) {
			jTextField_Pprice = new JTextField();
			jTextField_Pprice.setBounds(new Rectangle(345, 94, 120, 30));
			jTextField_Pprice.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					if(!jTextField_Pprice.getText().equals(""))
					try {
					double  price=	new Double(jTextField_Pprice.getText());
					if(price<0){
						JOptionPane.showMessageDialog(null, "单价不能为负数",
								"Fail", JOptionPane.ERROR_MESSAGE);
						jTextField_Pprice.setText("");
					}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请为单价 输入数字类型",
								"Fail", JOptionPane.ERROR_MESSAGE);
						jTextField_Pprice.setText("");
						
					}
					pprice=jTextField_Pprice.getText();
					if((!pnumber.equals(""))&&(!pprice.equals("")))
					{
						long number=new Long(pnumber);
						double price=new Double(pprice);
						totalprice=new Double((number*price)).toString();
						jTextField_Dtotal.setText(totalprice);
					}
				}
			});
		}
		return jTextField_Pprice;
	}

	/**
	 * This method initializes jTextField_Pnumber	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_Pnumber() {
		if (jTextField_Pnumber == null) {
			jTextField_Pnumber = new JTextField();
			jTextField_Pnumber.setBounds(new Rectangle(345, 136, 120, 30));
			jTextField_Pnumber.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					if(!jTextField_Pnumber.getText().equals(""))
					try {
						long num=new Long(jTextField_Pnumber.getText());
						if(num<0){
							JOptionPane.showMessageDialog(null, "数量不能为负数",
									"Fail", JOptionPane.ERROR_MESSAGE);
							jTextField_Pnumber.setText("");
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请为数量 输入数字整型",
								"Fail", JOptionPane.ERROR_MESSAGE);
						jTextField_Pnumber.setText("");	
					}
					pnumber=jTextField_Pnumber.getText();
					if((!pnumber.equals(""))&&(!pprice.equals("")))
					{
						long number=new Long(pnumber);
						double price=new Double(pprice);
						totalprice=new Double((number*price)).toString();
						jTextField_Dtotal.setText(totalprice);
					}
							
				}
			});
		}
		return jTextField_Pnumber;
	}

	/**
	 * This method initializes jTextField_did	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_did() {
		if (jTextField_did == null) {
			jTextField_did = new JTextField();
			jTextField_did.setBounds(new Rectangle(99, 139, 130, 30));
		}
		return jTextField_did;
	}

	/**
	 * This method initializes jTextField_Ddate	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_Ddate() {
		if (jTextField_Ddate == null) {
			jTextField_Ddate = new JTextField();
			jTextField_Ddate.setBounds(new Rectangle(99, 180, 130, 30));
			jTextField_Ddate.setEditable(false);
			Date date=new Date();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			String d=format.format(date).toString();
			jTextField_Ddate.setText(d);
		}
		return jTextField_Ddate;
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
		List<InBean> list= sd.querygoods(Sgname);
		String[][] table=new String[list.size()][6];
		int i=0;
		for(InBean sb:list){
			table[i][0]=sb.getGid();
			table[i][1]=sb.getGname();
			table[i][2]=sb.getGsever();
			table[i][3]=sb.getGproduce();
			table[i][4]=sb.getGtype(); 
			table[i][5]=sb.getGunit(); 
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
			jButton.setBounds(new Rectangle(103, 214, 124, 34));
			jButton.setText("入库");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pdid=jTextField_did.getText();
					ddate= jTextField_Ddate.getText();
					dvariety=jLabel_Snumber.getText();
					String type=sd.getType(gid);
					if(gid.equals("")||ddate.equals("")||pprice.equals("")||pnumber.equals("")||pdid.equals("")){
						JOptionPane.showMessageDialog(null, "填写数据不能为空",
								"Fail", JOptionPane.ERROR_MESSAGE);
					}else{
						if(sd.insertUpdate(pdid, totalprice, ssname, ddate, dsquare,
								gid, pprice, pnumber, dvariety,Sgname,ssname,type))						
							JOptionPane.showMessageDialog(null, "入库完成", "OK",
									JOptionPane.PLAIN_MESSAGE);	
						else
							JOptionPane.showMessageDialog(null, "入库失败",
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
			jButton1.setBounds(new Rectangle(302, 217, 124, 34));
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

	/**
	 * This method initializes jTextField_Dtotal	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_Dtotal() {
		if (jTextField_Dtotal == null) {
			jTextField_Dtotal = new JTextField();
			jTextField_Dtotal.setBounds(new Rectangle(344, 175, 124, 32));
			jTextField_Dtotal.setEditable(false);
		}
		return jTextField_Dtotal;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
