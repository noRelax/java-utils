/**
 * 
 */
package com.itstar.query.swing;

import javax.swing.JPanel;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.JFrame; //import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
//import javax.swing.JComboBox;
import javax.swing.JButton; //import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableModel; //import java.awt.Dimension;

import java.util.ArrayList;
import java.util.List;

import com.itstar.query.doquerying.ExportExcel;
import com.itstar.query.doquerying.GetGuestNameDone;
import com.itstar.query.doquerying.GetGuestInforDone;
import com.itstar.query.doquerying.GetGuestItemDone;
import com.itstar.query.item.GuestQueryItem;
//import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Point;


/**
 * @author Administrator
 * 
 */
public class JGuestQuery extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
//	private JComboBox jComboBox = null;
	private JButton jButton = null;
	private JTable jTable = null;
	private JScrollPane jScrollPane = null;
	private JTextField jTextField = null;
	private JButton jButtonAll = null;
	private List<String> Gname =null;
	private JButton jButtonExport = null;

	/**
	 * @throws HeadlessException
	 */
	public JGuestQuery() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		initialize();
	}

	/**
	 * @param gc
	 */
	public JGuestQuery(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public JGuestQuery(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param title
	 * @param gc
	 */
	public JGuestQuery(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle("客户信息查询");
		this.setLocation(new Point(450, 250));
		this.setSize(new Dimension(700, 300));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setText("请输入客户名称");
			jLabel.setBounds(new Rectangle(4, 8, 100, 33));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
		//	jContentPane.add(getJComboBox(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTable(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getJButtonAll(), null);
			jContentPane.add(getJButtonExport(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */


	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	// 测试查询函数
	/*
	 * private void testQuery(){ Object[][] cells=null; cells = new
	 * GetGuestInforDone().getGuestInfor(); for(int i=0;i<cells.length;i++){
	 * for(int j=0;j<cells[i].length;j++){ System.out.println(cells[i][j]); } }
	 * }
	 */
//	private TableModel getTableModel() {
//		Object[][] cells = null;
//		String[] colnames = { "客户编号", "客户名称", "客户地址", "邮件", "联系人手机", "联系人姓名",
//				"邮编", "银行账号", "客户电话" }; // @jve:decl-index=0:
//		  List<GuestQueryItem> list=new ArrayList<GuestQueryItem>();
//			list=  new GetGuestInforDone().getGuestInfor();
//			int length=list.size();
//			cells=new Object[length][9];
//			for(int i=0;i<list.size();i++){
//				 cells[i][0]=list.get(i).getGid();
//				 cells[i][1]=list.get(i).getGname();
//				 cells[i][2]=list.get(i).getGaddress();
//				 cells[i][3]=list.get(i).getGemail();
//				 cells[i][4]=list.get(i).getGphone();
//				 cells[i][5]=list.get(i).getGperson();
//				 cells[i][6]=list.get(i).getGpostCode();
//				 cells[i][7]=list.get(i).getGbank();
//				 cells[i][8]=list.get(i).getGnumber();
//			}
//		TableModel dataModel = new DefaultTableModel(cells, colnames);
//		return dataModel;
//	}
@SuppressWarnings("unchecked")
private void fillJTable(JTable jTable){
	Object[][] cells = null;
	String[] colnames = { "客户编号", "客户名称", "客户地址", "邮件", "联系人手机", "联系人姓名",
			"邮编", "银行账号", "客户电话" }; // @jve:decl-index=0:
	  List<GuestQueryItem> list=new ArrayList<GuestQueryItem>();
		list=  new GetGuestInforDone().getGuestInfor();
		int length=list.size();
		cells=new Object[length][9];
		for(int i=0;i<list.size();i++){
			 cells[i][0]=list.get(i).getGid();
			 cells[i][1]=list.get(i).getGname();
			 cells[i][2]=list.get(i).getGaddress();
			 cells[i][3]=list.get(i).getGemail();
			 cells[i][4]=list.get(i).getGphone();
			 cells[i][5]=list.get(i).getGperson();
			 cells[i][6]=list.get(i).getGpostCode();
			 cells[i][7]=list.get(i).getGbank();
			 cells[i][8]=list.get(i).getGnumber();
		}
		DefaultTableModel model=(DefaultTableModel)jTable.getModel();
		model.setColumnIdentifiers(colnames);
		model.setDataVector(cells, colnames);
	
}
	private JTable getJTable() {
		if (jTable == null) {
		 
			jTable = new JTable();
			jTable.setBounds(new Rectangle(5, 262, 375, 80));
		
			
		}
		return jTable;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(266, 7, 73, 27));
			jButton.setText("查询");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("unchecked")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					DefaultTableModel model=(DefaultTableModel)jTable.getModel();
					if(Gname==null)
					Gname = (new GetGuestNameDone().getGuestName());
					int flag=0;
					for (String str:Gname) {
						if(jTextField.getText().trim().equals(str)) {
							Object[][]cells=new GetGuestItemDone().getGuestItemDone(jTextField.getText());
							String[] colnames = { "客户编号", "客户名称", "客户地址", "邮件", "联系人手机", "联系人姓名",
									"邮编", "银行账号", "客户电话" };
							model.setDataVector(cells, colnames);
							flag=1;
						}
					}
					if(flag==0){
						JOptionPane.showMessageDialog(null, "没有查找到用户，请重新输入");
						jTextField.setText(null);
						jTextField.requestFocus();
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(1, 42, 693, 215));
			jScrollPane.setViewportView(getJTable());
			fillJTable(jTable);
		}
		return jScrollPane;
		
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(147, 11, 90, 26));
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
			jButtonAll.setBounds(new Rectangle(401, 7, 115, 27));
			jButtonAll.setText("查询所有客户");
			jButtonAll.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				  fillJTable(jTable); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButtonAll;
	}

	/**
	 * This method initializes jButtonExport	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonExport() {
		if (jButtonExport == null) {
			jButtonExport = new JButton();
			jButtonExport.setBounds(new Rectangle(535, 7, 97, 25));
			jButtonExport.setText("导出Excel");
			jButtonExport.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ExportExcel exportExcel = new ExportExcel(jTable); 
					exportExcel.export(); 
				}
			});
		}
		return jButtonExport;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
