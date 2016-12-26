package com.itstar.query.swing;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;

import java.awt.GridBagConstraints;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.itstar.query.doquerying.ExportExcel;
import com.itstar.query.doquerying.GetSalesInfor;
import com.itstar.query.item.SalesDetail;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JRadioButton;
 


public class JSalesQuery extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JTextField jSalesNumTxt = null;
	private JTextField jSalesDateFromTxt = null;
	private JTextField jCustomNameTxt = null;
	private JPanel jPanel = null;
	private JLabel jSalesDateLable = null;
	private JLabel jCustomNameLable = null;
	private JPanel jPanel1 = null;
	private JLabel jSalesNumLable = null;
	private JButton jQuerySalesNumButton = null;
	private JButton jExportButton = null;
	private JButton jQuerySalesDateButton = null;
	private JButton jQueryCustomNameButton = null;
	private JButton jQueryAllButton = null;
	private JLabel jGoodsNameLabel = null;
	private JTextField jGoodsNameTxt = null;
	private JButton jQueryGoodsNameButton = null;
	private JLabel jLabel = null;
	private JTextField jSalesDateToTxt = null;
	private JTextField jSalesDateTxt = null;
	private JLabel jLabel1 = null;
	private JRadioButton jDateRadioButton = null;
	private JRadioButton jDateRangeRadioButton = null;
   
	/**
	 * This is the default constructor
	 */
	public JSalesQuery() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(863, 433);
		this.setResizable(false);
		this.setLocation(new Point(250, 150));
		this.setContentPane(getJContentPane());
		this.setTitle("销售查询");
		ButtonGroup group=new ButtonGroup();
		group.add(jDateRadioButton);
		group.add(jDateRangeRadioButton);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJPanel1(), null);
			jContentPane.add(getJQueryAllButton(), null);
			jContentPane.add(getJExportButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(1, 112, 851, 282));
			jScrollPane.setViewportView(getJTable());
			fillJTable(jTable);
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable
	 * 
	 * @return javax.swing.JTable
	 */
	@SuppressWarnings({ "unchecked" })
	private void fillJTable(JTable jTable,List list){
		Object[][] cells = null;
		String[] colnames = { "销售编号", "商品编号", "商品名称","销售单价", "销售数量", "总金额", "客户名称",
				"销售日期", "结算方式" };
		List<SalesDetail>sales=new ArrayList<SalesDetail>();
		sales=list;
		int length=sales.size();
		cells=new Object[length][9];
		for(int i=0;i<sales.size();i++){
			 cells[i][0]=sales.get(i).getSid();
			 cells[i][1]=sales.get(i).getGid();
			 cells[i][2]=sales.get(i).getGname();
			 cells[i][3]=sales.get(i).getDprice();
			 cells[i][4]=sales.get(i).getDnumber();
			 cells[i][5]=sales.get(i).getTotal();
			 cells[i][6]=sales.get(i).getTperson();
			 cells[i][7]=sales.get(i).getSaledate();
			 cells[i][8]=sales.get(i).getDsquare();
		}
		DefaultTableModel model=(DefaultTableModel)jTable.getModel();
		model.setColumnIdentifiers(colnames);
		model.setDataVector(cells, colnames);
	
	}
	
	@SuppressWarnings("unchecked")
	private void fillJTable(JTable jTable){
		Object[][] cells = null;
		String[] colnames = { "销售编号", "商品编号", "商品名称","销售单价", "销售数量", "总金额", "客户名称",
				"销售日期", "结算方式" };
		List<SalesDetail>sales=new ArrayList<SalesDetail>();
		sales=new GetSalesInfor().getSalesInforAll();
		int length=sales.size();
		cells=new Object[length][9];
		for(int i=0;i<sales.size();i++){
			 cells[i][0]=sales.get(i).getSid();
			 cells[i][1]=sales.get(i).getGid();
			 cells[i][2]=sales.get(i).getGname();
			 cells[i][3]=sales.get(i).getDprice();
			 cells[i][4]=sales.get(i).getDnumber();
			 cells[i][5]=sales.get(i).getTotal();
			 cells[i][6]=sales.get(i).getTperson();
			 cells[i][7]=sales.get(i).getSaledate();
			 cells[i][8]=sales.get(i).getDsquare();
		}
		DefaultTableModel model=(DefaultTableModel)jTable.getModel();
		model.setColumnIdentifiers(colnames);
		model.setDataVector(cells, colnames);
	
	}
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}

	/**
	 * This method initializes jSalesNumTxt
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJSalesNumTxt() {
		if (jSalesNumTxt == null) {
			jSalesNumTxt = new JTextField();
			jSalesNumTxt.setSize(new Dimension(83, 22));
			jSalesNumTxt.setLocation(new Point(114, 10));
		}
		return jSalesNumTxt;
	}

	/**
	 * This method initializes jSalesDateFromTxt
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJSalesDateFromTxt() {
		if (jSalesDateFromTxt == null) {
			jSalesDateFromTxt = new JTextField();
			jSalesDateFromTxt.setBounds(new Rectangle(128, 37, 69, 24));
		}
		return jSalesDateFromTxt;
	}

	/**
	 * This method initializes jCustomNameTxt
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJCustomNameTxt() {
		if (jCustomNameTxt == null) {
			jCustomNameTxt = new JTextField();
			jCustomNameTxt.setBounds(new Rectangle(130, 74, 152, 22));
		}
		return jCustomNameTxt;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(9, 4, 86, 24));
			jLabel1.setText("精确日期查询");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(196, 39, 25, 18));
			jLabel.setText("到");
			jCustomNameLable = new JLabel();
			jCustomNameLable.setBounds(new Rectangle(6, 71, 86, 20));
			jCustomNameLable.setText("客户名称");
			jSalesDateLable = new JLabel();
			jSalesDateLable.setBounds(new Rectangle(8, 36, 86, 18));
			jSalesDateLable.setText("按时间段查询");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(4, 2, 395, 101));
			jPanel.add(getJSalesDateFromTxt(), null);
			jPanel.add(getJCustomNameTxt(), null);
			jPanel.add(jSalesDateLable, null);
			jPanel.add(jCustomNameLable, null);
			jPanel.add(getJQuerySalesDateButton(), null);
			jPanel.add(getJQueryCustomNameButton(), null);
			jPanel.add(jLabel, null);
			jPanel.add(getJSalesDateToTxt(), null);
			jPanel.add(getJSalesDateTxt(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJDateRadioButton(), null);
			jPanel.add(getJDateRangeRadioButton(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jGoodsNameLabel = new JLabel();
			jGoodsNameLabel.setBounds(new Rectangle(17, 71, 83, 18));
			jGoodsNameLabel.setText("商品名称");
			jSalesNumLable = new JLabel();
			jSalesNumLable.setBounds(new Rectangle(13, 10, 85, 22));
			jSalesNumLable.setText("销售编号");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.weightx = 1.0;
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(new Rectangle(407, 2, 320, 100));
			jPanel1.add(getJSalesNumTxt());
			jPanel1.add(jSalesNumLable, null);
			jPanel1.add(getJQuerySalesNumButton(), null);
			jPanel1.add(jGoodsNameLabel, null);
			jPanel1.add(getJGoodsNameTxt(), null);
			jPanel1.add(getJQueryGoodsNameButton(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jQuerySalesNumButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJQuerySalesNumButton() {
		if (jQuerySalesNumButton == null) {
			jQuerySalesNumButton = new JButton();
			jQuerySalesNumButton.setBounds(new Rectangle(210, 10, 98, 24));
			jQuerySalesNumButton.setText("查询");
			jQuerySalesNumButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				 fillJTable(jTable,new GetSalesInfor().getSalesInforID(jSalesNumTxt.getText()));// TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jQuerySalesNumButton;
	}

	/**
	 * This method initializes jExportButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJExportButton() {
		if (jExportButton == null) {
			jExportButton = new JButton();
			jExportButton.setText("导出Excel");
			jExportButton.setBounds(new Rectangle(739, 67, 109, 22));
			jExportButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ExportExcel exportExcel = new ExportExcel(jTable);
					exportExcel.export();// TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jExportButton;
	}

	/**
	 * This method initializes jQuerySalesDateButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJQuerySalesDateButton() {
		if (jQuerySalesDateButton == null) {
			jQuerySalesDateButton = new JButton();
			jQuerySalesDateButton.setBounds(new Rectangle(304, 37, 88, 22));
			jQuerySalesDateButton.setText("查询");
			jQuerySalesDateButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					 if(jDateRangeRadioButton.isSelected()) fillJTable(jTable,new GetSalesInfor().getSalesInforDate(jSalesDateFromTxt.getText(), jSalesDateToTxt.getText()));
					 else if(jDateRadioButton.isSelected())fillJTable(jTable,new GetSalesInfor().getSalesInforDate(jSalesDateTxt.getText()));// TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jQuerySalesDateButton;
	}

	/**
	 * This method initializes jQueryCustomNameButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJQueryCustomNameButton() {
		if (jQueryCustomNameButton == null) {
			jQueryCustomNameButton = new JButton();
			jQueryCustomNameButton.setBounds(new Rectangle(306, 75, 86, 20));
			jQueryCustomNameButton.setText("查询");
			jQueryCustomNameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fillJTable(jTable,new GetSalesInfor().getSalesInforGuestName(jCustomNameTxt.getText()));  // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jQueryCustomNameButton;
	}

	/**
	 * This method initializes jQueryAllButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJQueryAllButton() {
		if (jQueryAllButton == null) {
			jQueryAllButton = new JButton();
			jQueryAllButton.setBounds(new Rectangle(738, 14, 109, 24));
			jQueryAllButton.setText("查询所有");
			jQueryAllButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fillJTable(jTable); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jQueryAllButton;
	}

	/**
	 * This method initializes jGoodsNameTxt	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJGoodsNameTxt() {
		if (jGoodsNameTxt == null) {
			jGoodsNameTxt = new JTextField();
			jGoodsNameTxt.setBounds(new Rectangle(114, 69, 82, 22));
		}
		return jGoodsNameTxt;
	}

	/**
	 * This method initializes jQueryGoodsNameButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJQueryGoodsNameButton() {
		if (jQueryGoodsNameButton == null) {
			jQueryGoodsNameButton = new JButton();
			jQueryGoodsNameButton.setBounds(new Rectangle(212, 65, 97, 23));
			jQueryGoodsNameButton.setText("查询");
			jQueryGoodsNameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fillJTable(jTable,new GetSalesInfor().getSalesInforGoodsName(jGoodsNameTxt.getText())); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jQueryGoodsNameButton;
	}

	/**
	 * This method initializes jSalesDateToTxt	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJSalesDateToTxt() {
		if (jSalesDateToTxt == null) {
			jSalesDateToTxt = new JTextField();
			jSalesDateToTxt.setBounds(new Rectangle(217, 38, 66, 22));
		}
		return jSalesDateToTxt;
	}

	/**
	 * This method initializes jSalesDateTxt	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJSalesDateTxt() {
		if (jSalesDateTxt == null) {
			jSalesDateTxt = new JTextField();
			jSalesDateTxt.setBounds(new Rectangle(129, 5, 65, 22));
		}
		return jSalesDateTxt;
	}

	/**
	 * This method initializes jDateRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJDateRadioButton() {
		if (jDateRadioButton == null) {
			jDateRadioButton = new JRadioButton();
			jDateRadioButton.setBounds(new Rectangle(99, 5, 21, 21));
		}
		return jDateRadioButton;
	}

	/**
	 * This method initializes jDateRangeRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJDateRangeRadioButton() {
		if (jDateRangeRadioButton == null) {
			jDateRangeRadioButton = new JRadioButton();
			jDateRangeRadioButton.setBounds(new Rectangle(99, 36, 21, 21));
		}
		return jDateRangeRadioButton;
	}

} // @jve:decl-index=0:visual-constraint="4,-6"
