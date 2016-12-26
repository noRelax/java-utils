/**
 * 
 */
package com.itstar.query.swing;

import javax.swing.JPanel;
import javax.swing.JFrame; //import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.util.List;
import java.util.ArrayList;

import com.itstar.query.doquerying.ExportExcel;
import com.itstar.query.doquerying.GetGoodFromType;
import com.itstar.query.doquerying.GetGoodInforDone;
import com.itstar.query.doquerying.GetGoodNameDone;
import com.itstar.query.doquerying.GetGoodTypeDone;

import com.itstar.query.item.GoodsQueryItem;
import javax.swing.WindowConstants;
import java.awt.Point;

/**
 * @author Administrator
 * 
 */
public class JGoodsQuery extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel1 = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton jButton = null;
	private JButton jButtonExport = null;
	private JTextField jTextField = null;
	private JButton jButtonAll = null;
	private List<String> Gname = null;

	/**
	 * This is the default constructor
	 */
	public JGoodsQuery() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(619, 349);
		this.setLocation(new Point(450, 250));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("商品信息查询");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(0, 49, 96, 29));
			jLabel1.setText("请选择商品类型");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(1, 2, 96, 29));
			jLabel.setText("请输入商品名称");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButtonExport(), null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getJButtonAll(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getJComboBox() {
		if (jComboBox == null) {

			List<String> Gtype = new GetGoodTypeDone().getGoodType();

			jComboBox = new JComboBox();
			jComboBox.setSelectedItem(null);
            jComboBox.addItem("请选择");
			for (int index = 0; index < Gtype.size(); index++) {
				jComboBox.addItem(Gtype.get(index));
			}

			jComboBox.setBounds(new Rectangle(114, 49, 96, 29));
			jComboBox.addItemListener(new java.awt.event.ItemListener() {

				public void itemStateChanged(java.awt.event.ItemEvent e) {
					// Object[][]cells=null;
					// String[] colnames = { "商品编号", "商品名称", "商品数量", "产地", "类型",
					// "计量单位", "备注",
					// "供应商名称" };
					if(!e.getItem().toString().equals("请选择"))
					{
						fillJTable(jTable, new GetGoodFromType().getGoodFromType(e
								.getItem().toString()));
						jComboBox.removeItem("请选择");
						
					}
					
					// List<GoodsQueryItem> goods=new
					// ArrayList<GoodsQueryItem>();
					// DefaultTableModel
					// model=(DefaultTableModel)jTable.getModel();
					// goods=new
					// GetGoodFromType().getGoodFromType(e.getItem().toString());
					// for(int i=0;i<goods.size();i++){
					// cells[i][0]=goods.get(i).getGid();
					// cells[i][1]=goods.get(i).getGname();
					// cells[i][2]=goods.get(i).getGnumber();
					// cells[i][3]=goods.get(i).getGproduce();
					// cells[i][4]=goods.get(i).getGtype();
					// cells[i][5]=goods.get(i).getGunit();
					// cells[i][6]=goods.get(i).getGannotate();
					// cells[i][7]=goods.get(i).getGsever();
					// }
					// model.setDataVector(cells, colnames);

				}
			});
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
			jScrollPane.setBounds(new Rectangle(1, 79, 609, 234));
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
	@SuppressWarnings( { "unchecked" })
	private void fillJTable(JTable jTable, List list) {
		Object[][] cells = null;
		String[] colnames = { "商品编号", "商品名称", "商品数量", "产地", "类型", "计量单位", "备注",
				"供应商名称" }; // @jve:decl-index=0:
		List<GoodsQueryItem> goods = new ArrayList<GoodsQueryItem>();
		goods = list;
		int length = list.size();
		cells = new Object[length][8];
		for (int i = 0; i < goods.size(); i++) {
			cells[i][0] = goods.get(i).getGid();
			cells[i][1] = goods.get(i).getGname();
			cells[i][2] = goods.get(i).getGnumber();
			cells[i][3] = goods.get(i).getGproduce();
			cells[i][4] = goods.get(i).getGtype();
			cells[i][5] = goods.get(i).getGunit();
			cells[i][6] = goods.get(i).getGannotate();
			cells[i][7] = goods.get(i).getGsever();

		}
		DefaultTableModel model = (DefaultTableModel) jTable.getModel();
		model.setColumnIdentifiers(colnames);
		model.setDataVector(cells, colnames);

	}

	@SuppressWarnings("unchecked")
	private void fillJTable(JTable jTable) {
		Object[][] cells = null;
		String[] colnames = { "商品编号", "商品名称", "商品数量", "产地", "类型", "计量单位", "备注",
				"供应商名称" }; // @jve:decl-index=0:
		List<GoodsQueryItem> list = new ArrayList<GoodsQueryItem>();
		list = new GetGoodInforDone().getAllGoodInfor();
		int length = list.size();
		cells = new Object[length][8];
		for (int i = 0; i < list.size(); i++) {
			cells[i][0] = list.get(i).getGid();
			cells[i][1] = list.get(i).getGname();
			cells[i][2] = list.get(i).getGnumber();
			cells[i][3] = list.get(i).getGproduce();
			cells[i][4] = list.get(i).getGtype();
			cells[i][5] = list.get(i).getGunit();
			cells[i][6] = list.get(i).getGannotate();
			cells[i][7] = list.get(i).getGsever();

		}
		DefaultTableModel model = (DefaultTableModel) jTable.getModel();
		model.setColumnIdentifiers(colnames);
		model.setDataVector(cells, colnames);

	}

	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(230, 48, 98, 29));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("unchecked")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					List<GoodsQueryItem> goods = new ArrayList<GoodsQueryItem>();
					DefaultTableModel model = (DefaultTableModel) jTable
							.getModel();
					if (Gname == null)
						Gname = (new GetGoodNameDone().getGoodName());
					int flag = 0;
					for (String str : Gname) {
						if (jTextField.getText().trim().equals(str)) {
							goods = new GetGoodInforDone()
									.getGoodInfor(jTextField.getText());
							String[] colnames = { "商品编号", "商品名称", "商品数量", "产地",
									"类型", "计量单位", "备注", "供应商名称" };
							Object[][] cells = null;
							int length = goods.size();
							cells = new Object[length][8];
							for (int i = 0; i < length; i++) {
								cells[i][0] = goods.get(i).getGid();
								cells[i][1] = goods.get(i).getGname();
								cells[i][2] = goods.get(i).getGnumber();
								cells[i][3] = goods.get(i).getGproduce();
								cells[i][4] = goods.get(i).getGtype();
								cells[i][5] = goods.get(i).getGunit();
								cells[i][6] = goods.get(i).getGannotate();
								cells[i][7] = goods.get(i).getGsever();
							}
							model.setDataVector(cells, colnames);
							flag = 1;
						}
					}
					if (flag == 0) {
						JOptionPane.showMessageDialog(null, "没有查找到所输入商品，请重新输入");
						jTextField.setText(null);
						jTextField.requestFocus();
					}
				}
			});
			jButton.setText("查询");
		}
		return jButton;
	}

	/**
	 * This method initializes jButtonExport
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonExport() {
		if (jButtonExport == null) {
			jButtonExport = new JButton();
			jButtonExport.setBounds(new Rectangle(229, 2, 98, 29));
			jButtonExport
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							ExportExcel exportExcel = new ExportExcel(jTable);
							exportExcel.export();
						}
					});
			jButtonExport.setText("导出Excel");
		}
		return jButtonExport;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(112, 2, 96, 29));
		}
		return jTextField;
	}

	/**
	 * This method initializes jButtonAll
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAll() {
		if (jButtonAll == null) {
			jButtonAll = new JButton();
			jButtonAll.setBounds(new Rectangle(341, 48, 129, 29));
			jButtonAll.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fillJTable(jTable);
				}
			});
		}
		jButtonAll.setText("查询所有商品");
		return jButtonAll;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
