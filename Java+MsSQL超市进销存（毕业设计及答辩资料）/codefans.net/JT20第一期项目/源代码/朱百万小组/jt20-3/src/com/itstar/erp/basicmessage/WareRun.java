package com.itstar.erp.basicmessage;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.itstar.erp.bean.Goods;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class WareRun extends JFrame {

	private static final long serialVersionUID = 1L;

	private Object[][] cells = null; // @jve:decl-index=0:

	private JPanel jContentPane = null;

	private int width = Toolkit.getDefaultToolkit().getScreenSize().width;

	private int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JButton jButton2 = null;

	private JScrollPane jScrollPane = null;

	public JTable jTable = null;

	private JButton jButton31 = null;
	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("增加商品");
			jButton.setSize(new Dimension(97, 27));
			jButton.setLocation(new Point(30, 18));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AddRun ad = new AddRun();
					ad.setVisible(true);
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
			jButton1.setText("删除商品");
			jButton1.setSize(new Dimension(97, 27));
			jButton1.setLocation(new Point(330, 18));
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = jTable.getSelectedRow();
					if (row == -1) {
						JOptionPane jop = new JOptionPane();
						jop.showMessageDialog(jop, "请选择要删除的记录");
					}
					if (row != -1) {
						Goods gd = new Goods();
						
						JOptionPane jop = new JOptionPane();
						int i = jop.showConfirmDialog(jop, "你确定要删除吗？", "确认对话框",
								jop.YES_NO_OPTION);
						if (i == JOptionPane.YES_OPTION) {
							int rowcount = getJTable().getSelectedRow();
						
							gd.setGName(jTable.getValueAt(jTable.getSelectedRow(), 1).toString());
							gd.setGId(Integer.parseInt(getJTable().getValueAt(rowcount, 0).toString()));
							gd.delet();
						} else {
							return;
						}
						jTable.setModel(getTableModel());
					}
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
			jButton2.setMnemonic(KeyEvent.VK_UNDEFINED);
			jButton2.setLocation(new Point(180, 18));
			jButton2.setSize(new Dimension(97, 27));
			jButton2.setHorizontalAlignment(SwingConstants.CENTER);
			jButton2.setText("修改商品");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int rowcount = getJTable().getSelectedRow();

					if (rowcount == -1) {
						JOptionPane j = new JOptionPane();
						j.showMessageDialog(j, "请选择一行记录");
					}
					if (!(rowcount == -1)) {
						UpdRun ads = new UpdRun();
						getJTable().getValueAt(rowcount, 1);
						ads.setVisible(true);
						ads.getJTextField3().setText(getJTable().getValueAt(rowcount, 0)+"");
						ads.getJTextField1().setText((String) getJTable().getValueAt(rowcount, 1));
						ads.getJTextField2().setText((String) getJTable().getValueAt(rowcount, 2));
						ads.getJTextField6().setText((String) getJTable().getValueAt(rowcount, 3));
						ads.getJTextField4().setText((String) getJTable().getValueAt(rowcount, 4));
						ads.getJTextField5().setText((String) getJTable().getValueAt(rowcount, 5));
					
					}
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
			jScrollPane.setBounds(new Rectangle(3, 63, 590, 300));
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
			jTable.setBorder(null);
			jTable.setModel(getTableModel());
		}
		return jTable;
	}

	public  TableModel getTableModel() {
		Goods gd = new Goods();
		List result = gd.queryAll();

		String[] columns = { "商品编号", "商品名称","供应商地址","供应商编号","进货价（RMB）","出货价（RMB）" };
		
		cells=new Object[result.size()][6];
		
		for(int i=0;i<result.size();i++){
			cells[i]=(Object[]) result.get(i);
		}
		return new DefaultTableModel(cells, columns);
	}

	public WareRun() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(600, 400);
		this.setContentPane(getJContentPane());
		
		this.setTitle("商品管理");
		this.setResizable(false);
		this.setLocation((width - 600) / 2, (height - 400) / 2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJButton31(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton31	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton31() {
		if (jButton31 == null) {
			jButton31 = new JButton();
			jButton31.setText("刷  新");
			jButton31.setSize(new Dimension(97, 27));
			jButton31.setLocation(new Point(480, 18));
			jButton31.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTable.setModel(getTableModel());
				}
			});
		}
		return jButton31;
	}
	
	

}
