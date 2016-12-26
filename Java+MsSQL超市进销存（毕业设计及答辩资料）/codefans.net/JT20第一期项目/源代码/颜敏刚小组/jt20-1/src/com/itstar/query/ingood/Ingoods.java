package com.itstar.query.ingood;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.WindowConstants;
import java.awt.Point;

public class Ingoods extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox jComboBox = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JTextField jTextField = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JLabel jLabel = null;

	/**
	 * This is the default constructor
	 */
	public Ingoods() {
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
		this.setLocation(new Point(450, 250));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("入库查询");
		new xianshi().filetable(jTable);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(3, 20, 55, 27));
			jLabel.setText("商品名称");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(jLabel, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(74, 21, 184, 26));
			jComboBox.addItem("商品编号");
		    jComboBox.addItem("商品名称");
		    jComboBox.addItem("商品类型");
		  
		    
			
			
		}
		return jComboBox;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(23, 92, 650, 304));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(273, 20, 194, 27));
		}
		return jTextField;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {			jButton = new JButton();
			jButton.setBounds(new Rectangle(473, 16, 82, 28));
			jButton.setText("查询 ");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					List<Gysbean> list = new ArrayList<Gysbean>();
					Object[][] cells = null;
					int type=getJComboBox().getSelectedIndex();
					chaxun cx=new chaxun();
					switch(type){
					case 0:
						list=cx.query(jTextField.getText().toString());
						break;
					case 1:
						list=cx.queryLike(jTextField.getText().toString());
						break;
					case 2:
						list=cx.querytype(jTextField.getText().toString());
						break;
					}
					
					//list=new chaxun().query(jTextField.getText().toString());
					String[] tableforname = { "入库编号", "商品编号", "商品名称", "商品类型", "进货单价",
							"数量", "计量单位", "总计金额", "供应商名称", "入库时间" ,"结算方式"};
					int length = list.size();
					cells = new Object[length][11];
					for (int i = 0; i < list.size(); i++) {
						cells[i][0] = list.get(i).getDid();
						cells[i][1] = list.get(i).getPgid();
						cells[i][2] = list.get(i).getGname();
						cells[i][3] = list.get(i).getGtype();
						cells[i][4] = list.get(i).getPprice();
						cells[i][5] = list.get(i).getPnumber();
						cells[i][6] = list.get(i).getGunit();
						cells[i][7] = list.get(i).getDtotal();
						cells[i][8] = list.get(i).getDsname();
						cells[i][9] = list.get(i).getDdate();
						cells[i][10]= list.get(i).getDsquare();
					}
					DefaultTableModel model = (DefaultTableModel) jTable.getModel();
					model.setColumnIdentifiers(tableforname);
					model.setDataVector(cells, tableforname);
				
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
			jButton1.setBounds(new Rectangle(569, 12, 110, 32));
			jButton1.setText("返回所有 ");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					new xianshi().filetable(jTable);
				}
			});
			
		}
		return jButton1;
	}

}  //  @jve:decl-index=0:visual-constraint="7,11"
