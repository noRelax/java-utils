package com.itstar.erp.sale;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itstar.erp.dao.Init;

public class SearchUi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel top = null;
	private JLabel mode = null;
	private JComboBox choice = null;
	private JLabel condition = null;
	private JTextField conditionField = null;
	private JButton sumit = null;
	private JScrollPane full = null;
	private JTable result = null;
	private JButton clear = null;
	private DefaultTableModel model;

	public SearchUi() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(620, 380);
		this.setContentPane(getTop());
		this.setTitle("销售记录查询");
		this.setResizable(false);
		this.setVisible(true);
	}

	private JPanel getTop() {
		if (top == null) {
			condition = new JLabel();
			condition.setBounds(new Rectangle(220, 20, 43, 20));
			condition.setText("条 件");
			mode = new JLabel();
			mode.setBounds(new Rectangle(31, 20, 84, 20));
			mode.setText("选择查询方式");
			top = new JPanel();
			top.setLayout(null);
			top.add(mode, null);
			top.add(getChoice(), null);
			top.add(condition, null);
			top.add(getConditionField(), null);
			top.add(getSumit(), null);
			top.add(getFull(), null);
			top.add(getClear(), null);
		}
		return top;
	}

	private JComboBox getChoice() {
		if (choice == null) {
			choice = new JComboBox();
			choice.setBounds(new Rectangle(123, 20, 84, 20));
		}
		choice.addItem("商品名");
		choice.addItem("销售员");
		return choice;
	}

	
	private JTextField getConditionField() {
		if (conditionField == null) {
			conditionField = new JTextField();
			conditionField.setBounds(new Rectangle(260, 20, 84, 22));
		}
		return conditionField;
	}

	private JButton getSumit() {
		if (sumit == null) {
			sumit = new JButton();
			sumit.setBounds(new Rectangle(353, 22, 65, 20));
			sumit.setText("提交");
			sumit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String value = choice.getSelectedItem().toString();  
					System.out.println(value);
					System.out.println(conditionField.getText());
					if("商品名".equals(value)){
					if(full!=null){
						top.remove(full);
					}
					try{
						Connection conn=Init.getConnection();
						Statement st=null;
						ResultSet rs=null;
						st=conn.createStatement();
						String sql="select * from outstock where OGname='"+conditionField.getText()+"'";
						rs=st.executeQuery(sql);
						ResultSetMetaData rsmd=rs.getMetaData();
						Vector<String> columnNames=new Vector<String>();
						Vector data=new Vector();
						for(int i=0;i<rsmd.getColumnCount();i++){
							columnNames.add(rsmd.getColumnName(i+1));
						}
						while(rs.next()){
							Vector v=new Vector();
							for(int i=0;i<rsmd.getColumnCount();i++){
								v.add(rs.getString(i+1));
							}
							data.add(v);
						}
						model=new DefaultTableModel(data,columnNames);
						JTable table=new JTable(model);
						full=new JScrollPane(table);
						top.add(full);
						top.validate();
					}catch(Exception e1){
						e1.printStackTrace();
					}
				 }
					else {if(full!=null){
						top.remove(full);
					}
					try{
						Connection conn=Init.getConnection();
						Statement st=null;
						ResultSet rs=null;
						st=conn.createStatement();
						String sql="select * from outstock where Ousername='"+conditionField.getText()+"'";
						rs=st.executeQuery(sql);
						ResultSetMetaData rsmd=rs.getMetaData();
						Vector<String> columnNames=new Vector<String>();
						Vector data=new Vector();
						for(int i=0;i<rsmd.getColumnCount();i++){
							columnNames.add(rsmd.getColumnName(i+1));
						}
						while(rs.next()){
							Vector v=new Vector();
							for(int i=0;i<rsmd.getColumnCount();i++){
								v.add(rs.getString(i+1));
							}
							data.add(v);
						}
						model=new DefaultTableModel(data,columnNames);
						JTable table=new JTable(model);
						full=new JScrollPane(table);
						top.add(full);
						top.setVisible(true);
						top.validate();
					}catch(Exception e1){
						e1.printStackTrace();
					}
						
					}
				}
			});
	}
	return sumit;
	}

	
	private JScrollPane getFull() {
		if (full == null) {
			full = new JScrollPane();
			full.setBounds(new Rectangle(5, 50, 605, 260));
			full.setViewportView(getResult());
		}
		return full;
	}

	/**
	 * This method initializes result	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getResult() {
		if (result == null) {
			Object data[][]={{ 1,1,"basketball",87,"kobe","2009-11-12"}
			};
			String columnNames[] ={"记录号","商品号","商品名","商品数量","销售员","销售时间"};
			result = new JTable(data,columnNames);
		}
		return result;
	}

	private JButton getClear() {
		if (clear == null) {
			clear = new JButton();
			clear.setBounds(new Rectangle(430, 22, 65, 20));
			clear.setText("清空");
			clear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					conditionField.setText("");
					conditionField.requestFocus();
				}
			});
		}
		return clear;
	}

}  //  @jve:decl-index=0:visual-constraint="49,5" 
