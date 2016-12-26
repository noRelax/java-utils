package com.itstar.query.gysinfo;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.table.DefaultTableModel;



import java.util.List;
import java.awt.Scrollbar;
import java.awt.ScrollPane;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.WindowConstants;


public class Gysinfo extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	public static  JTextField jTextField = null;

	private JLabel jLabel = null;

	private JButton jButton = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JTextField jTextField1 = null;

	private JButton jButton1 = null;

	private JButton jButton2 = null;

	private JScrollPane jScrollPane = null;

	private JTable jTable = null;

	/**
	 * This is the default constructor
	 */
	public Gysinfo() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(713, 493);
		this.setLocation(new Point(450, 250));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("供应商查询");
		new xianshi().filetable(jTable);
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(303, 55, 65, 18));
			jLabel2.setText("供应商编号");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(7, 56, 65, 18));
			jLabel1.setText("供应商姓名");
			jLabel = new JLabel();
			jLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel.setSize(new Dimension(506, 37));
			jLabel.setLocation(new Point(70, 10));
			jLabel.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel.setText("供应商查询");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setFont(new Font("Dialog", Font.BOLD, 12));
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJTextField1(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(getJScrollPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(74, 55, 132, 26));
		}
		return jTextField;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(216, 56, 76, 22));
			jButton.setText("查询");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					List<GysBean> list = new ArrayList<GysBean>();
					list=new chaxun().queryLike(jTextField.getText().toString());
					System.out.println("-----------list--size----"+list.size());
//					if(list.get(0).getSname().equals(jTextField.getText()))
//					{
//					new juti().textfiled(jTable,jTextField.getText());
//					jTextField.requestFocus();
//					}
//					else {
//						jTextField.setText(null);
//					}
					

					Object[][] cells = null;
					String[] tableforname = { "供应商编号", "供应商名称", "供应商地址", "邮件", "联系人手机",
							"联系人姓名", "邮编", "银行帐号", "供应商电话", "标记" };
					int length = list.size();
					cells = new Object[length][9];
					for (int i = 0; i < list.size(); i++) {
						cells[i][0] = list.get(i).getSid();
						cells[i][1] = list.get(i).getSname();
						cells[i][2] = list.get(i).getSaddress();
						cells[i][3] = list.get(i).getSemail();
						cells[i][4] = list.get(i).getSphone();
						cells[i][5] = list.get(i).getSperson();
						cells[i][6] = list.get(i).getSpostCode();
						cells[i][7] = list.get(i).getSbank();
						cells[i][8] = list.get(i).getSnumber();
					}
					DefaultTableModel model = (DefaultTableModel)jTable.getModel();
					model.setColumnIdentifiers(tableforname);
					model.setDataVector(cells, tableforname);
				
					
				}
			});
			
			
		}
		return jButton;
	}



	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(376, 55, 132, 26));
		}
		return jTextField1;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(522, 55, 76, 22));
			jButton1.setText("查询 ");
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					List<GysBean> list = new ArrayList<GysBean>();
					list=new chabianhao().queryLike(Integer.parseInt(jTextField1.getText()));
					System.out.println("-----------list--size----"+list.size());
					Object[][] cells = null;
					String[] tableforname = { "供应商编号", "供应商名称", "供应商地址", "邮件", "联系人手机",
							"联系人姓名", "邮编", "银行帐号", "供应商电话", "标记" };
					int length = list.size();
					cells = new Object[length][9];
					for (int i = 0; i < list.size(); i++) {
						cells[i][0] = list.get(i).getSid();
						cells[i][1] = list.get(i).getSname();
						cells[i][2] = list.get(i).getSaddress();
						cells[i][3] = list.get(i).getSemail();
						cells[i][4] = list.get(i).getSphone();
						cells[i][5] = list.get(i).getSperson();
						cells[i][6] = list.get(i).getSpostCode();
						cells[i][7] = list.get(i).getSbank();
						cells[i][8] = list.get(i).getSnumber();
					}
					DefaultTableModel model = (DefaultTableModel)jTable.getModel();
					model.setColumnIdentifiers(tableforname);
					model.setDataVector(cells, tableforname);
		
		
					
				}
			});
			
			
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(610, 54, 91, 24));
			jButton2.setText("查询所有");
			jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					new xianshi().filetable(jTable);
				}
			});
			
			
		}
		return jButton2;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(30, 118, 640, 305));
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
	}

	
		
	


