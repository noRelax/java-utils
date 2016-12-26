package com.itstar.pd.swing;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itstar.pd.jdbc.StJdbc;
import com.itstar.pd.jdbc.StType;
import com.itstar.system.swing.ExportExcel;

import javax.swing.JButton;
import javax.swing.WindowConstants;
import java.awt.Point;

public class Check extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JScrollPane jScrollPane = null;

	private JTable jTable = null;

	private JLabel DatejLabel1 = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	/**
	 * This is the default constructor
	 */
	public Check() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(628, 419);
		this.setLocation(new Point(450, 250));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("盘点信息导出");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			
			
			DatejLabel1 = new JLabel();
			DatejLabel1.setBounds(new Rectangle(108, 14, 234, 26));
			Date date=new Date();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH-dd-ss");
//			format.format(date);
			String d=format.format(date).toString();
			DatejLabel1.setText(d);
			
			
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(25, 15, 61, 26));
			jLabel.setText("盘点时间");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(DatejLabel1, null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * @return 
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public void hdate(){
		Date date=new Date();

		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH-dd-ss");

		String d=date.toString();
	}
	
	private void fillJTable(JTable jTable){
		Object[][] cells = null;
		String[] colnames = { "库存编号", "商品名称", "供应商名称", "商品类型", "商品总价", "商品数量",};
		// @jve:decl-index=0:
		  List<StType> list=new ArrayList<StType>();
			try {
				list=  new StJdbc().getRs();
			} catch (InstantiationException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			int length=list.size();
			cells=new Object[length][6];
			for(int i=0;i<list.size();i++){
				 cells[i][0]=list.get(i).getSid();
				 cells[i][1]=list.get(i).getSgname();
				 cells[i][2]=list.get(i).getSsname();
				 cells[i][3]=list.get(i).getStype();
				 cells[i][4]=list.get(i).getSstotal();
				 cells[i][5]=list.get(i).getSnumber();
			}
			DefaultTableModel model=(DefaultTableModel)jTable.getModel();
			model.setColumnIdentifiers(colnames);
			model.setDataVector(cells, colnames);
		
	}
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(29, 56, 559, 278));
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
			this.fillJTable(jTable);
		}
		return jTable;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(241, 346, 110, 39));
			jButton.setText("关闭");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					System.exit(0);

//					jContentPane.setVisible(false);
					System.out.println("mousePressed()"); // TODO Auto-generated Event stub mousePressed()
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
			jButton1 = new JButton("导出为EXCEL");
			jButton1.setBounds(new Rectangle(442, 15, 133, 32));
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ExportExcel export=new ExportExcel(jTable);
					export.export();
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jComboBox1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	
	

}


