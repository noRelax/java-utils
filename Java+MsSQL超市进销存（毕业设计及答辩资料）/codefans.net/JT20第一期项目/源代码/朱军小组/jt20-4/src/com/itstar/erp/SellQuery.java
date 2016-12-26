package com.itstar.erp;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import com.itstar.dao.Dao;

public class SellQuery extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabel = null;
	private JTextField jTextField = null;
	private JButton jButton = null;
	public String[] head={"ID","NUMBER","PRICE","COUNT"};
	private String[] sellHead={"商品标识","商品编号","商品名","出售价格","出售数量","出售时间"};
	private String[] jinghuoHead={"商品标识","商品编号","商品名","生产厂商","进货价格","进数数量","进货厂商","进货时间"};
	private String[] stockHead={"商品标识","商品编号","商品名称","商品简称","制造商","库存单位","商品规格","批准文号","供应商名","备注"};
	private String[] kehuHead={"客户标识","客户编号","客户手机","客户地址","客户电话","客户邮箱"};

	public Object cells[][]={{null,null,null,null}};
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton jButton1 = null;
	private JLabel jLabel1 = null;
	private JComboBox jComboBox = null;
	public static String biaoMing=null;
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(68, 28, 86, 25));
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
			jButton.setBounds(new Rectangle(316, 30, 64, 23));
			jButton.setText("查询");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//String cxtj=jTextField.getText();
					String ywfw=(String)jComboBox.getSelectedItem();
					
					if(ywfw.equals("销售查询")){
						biaoMing="ERP_sell";
					String sql="SELECT * FROM  "+biaoMing;
					try {
						Dao dao=new Dao();
						dao.getConnection();
					
						dao.rs=dao.query(sql);
						java.util.List<Ware> list=new java.util.ArrayList<Ware>();
						
						while(dao.rs.next()){
					    Ware ware=new Ware();
						ware.setId(dao.rs.getInt(1));
						ware.setNumber(dao.rs.getString(2));
						ware.setName(dao.rs.getString(3));
						ware.setPrice(dao.rs.getString(4));
						ware.setCount(dao.rs.getString(4));
						ware.setTime(dao.rs.getString(6));
						list.add(ware);
						}
						dao.rs.close();
						dao.pst.close();
						dao.con.close();
						
						
						Object[][] cells= new Object[list.size()][6];
						Iterator<Ware> it=list.iterator();
						int i=0;
						while(it.hasNext()){
						   Ware ware=new Ware();
						   ware=it.next();
						   cells[i][0]=ware.getId();
						   cells[i][1]=ware.getNumber();
						   cells[i][2]=ware.getName();
						   cells[i][3]=ware.getPrice();
						   cells[i][4]=ware.getCount();
						   cells[i][5]=ware.getTime();
						   i++;
						}
						
						jTable = new JTable(cells,sellHead);
						jScrollPane.setViewportView(jTable);
					}catch (ClassNotFoundException e2) {
						e2.printStackTrace();
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
					else if(ywfw.equals("进货查询")){

						biaoMing="ERP_stockform";
					String sql="SELECT * FROM  "+biaoMing;
					try {
						Dao dao=new Dao();
						dao.getConnection();
					
						dao.rs=dao.query(sql);
						java.util.List<JingHuo> list=new java.util.ArrayList<JingHuo>();
						
						while(dao.rs.next()){
					   JingHuo jinghuo=new JingHuo();
					   jinghuo.setId(dao.rs.getInt(1));
					   jinghuo.setNumber(dao.rs.getString(2));
					   jinghuo.setName(dao.rs.getString(3));
					   jinghuo.setMadeIn(dao.rs.getString(4));
					   jinghuo.setPrice(dao.rs.getString(5));
					   jinghuo.setCount(dao.rs.getString(6));
					   jinghuo.setSupplier(dao.rs.getString(7));
					   jinghuo.setTime(dao.rs.getString(8));
						list.add(jinghuo);
						}
						dao.rs.close();
						dao.pst.close();
						dao.con.close();
						
						
						Object[][] cells= new Object[list.size()][8];
						Iterator<JingHuo> it=list.iterator();
						int i=0;
						while(it.hasNext()){
						JingHuo jinghuo=new JingHuo();
						   jinghuo=it.next();
						   cells[i][0]=jinghuo.getId();
						   cells[i][1]=jinghuo.getNumber();
						   cells[i][2]=jinghuo.getName();
						   cells[i][3]=jinghuo.getMadeIn();
						   cells[i][4]=jinghuo.getPrice();
						   cells[i][5]=jinghuo.getCount();
						   cells[i][6]=jinghuo.getSupplier();
						   cells[i][7]=jinghuo.getTime();
						   i++;
						}
						
						jTable = new JTable(cells,jinghuoHead);
						jScrollPane.setViewportView(jTable);
					}catch (ClassNotFoundException e2) {
						e2.printStackTrace();
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				
					}
					else if(ywfw.equals("客户查询")){


						biaoMing="kehugl";
					String sql="SELECT * FROM  "+biaoMing;
					try {
						Dao dao=new Dao();
						dao.getConnection();
					
						dao.rs=dao.query(sql);
						java.util.List<Customer> list=new java.util.ArrayList<Customer>();
						
						while(dao.rs.next()){
					   Customer customer=new Customer();
					   customer.setId(dao.rs.getInt(1));
					   customer.setNumber(dao.rs.getString(2));
					   customer.setKallcall(dao.rs.getString(3));
					   customer.setAddress(dao.rs.getString(4));
					   customer.setPhone(dao.rs.getString(5));
					   customer.setEamil(dao.rs.getString(6));
					   
						list.add(customer);
						}
						dao.rs.close();
						dao.pst.close();
						dao.con.close();
						
						
						Object[][] cells= new Object[list.size()][6];
						Iterator<Customer> it=list.iterator();
						int i=0;
						while(it.hasNext()){
						Customer customer=new Customer();
						   customer=it.next();
						   cells[i][0]=customer.getId();
						   cells[i][1]=customer.getNumber();
						   cells[i][2]=customer.getKallcall();
						   cells[i][3]=customer.getAddress();
						   cells[i][4]=customer.getPhone();
						   cells[i][5]=customer.getEamil();
						   
						   i++;
						}
						
						jTable = new JTable(cells,kehuHead);
						jScrollPane.setViewportView(jTable);
					}catch (ClassNotFoundException e2) {
						e2.printStackTrace();
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				
					
					}
					else if(ywfw.equals("商品库存")){


						biaoMing="spgl";
					String sql="SELECT * FROM  "+biaoMing;
					try {
						Dao dao=new Dao();
						dao.getConnection();
					
						dao.rs=dao.query(sql);
						java.util.List<Stock> list=new java.util.ArrayList<Stock>();
						
						while(dao.rs.next()){
				       Stock stock=new Stock();

				       stock.setId(dao.rs.getInt(1));
				       stock.setNumber(dao.rs.getString(2));
				       stock.setName(dao.rs.getString(3));
				       stock.setSimplename(dao.rs.getString(4));
				       stock.setMadein(dao.rs.getString(5));
				       stock.setUnit(dao.rs.getString(6));
				       stock.setGuige(dao.rs.getString(7));
				       stock.setPizhunwenhao(dao.rs.getString(8));
				       stock.setGysname(dao.rs.getString(9));
				       stock.setBeizhu(dao.rs.getString(10));
						list.add(stock);
						}
						dao.rs.close();
						dao.pst.close();
						dao.con.close();
						
						
						Object[][] cells= new Object[list.size()][10];
						Iterator<Stock> it=list.iterator();
						int i=0;
						while(it.hasNext()){
						Stock stock=new Stock();
						   stock=it.next();
						   cells[i][0]=stock.getId();
						   cells[i][1]=stock.getNumber();
						   cells[i][2]=stock.getName();
						   cells[i][3]=stock.getSimplename();
						   cells[i][4]=stock.getMadein();
						   cells[i][5]=stock.getUnit();
						   cells[i][6]=stock.getGuige();
						   cells[i][7]=stock.getPizhunwenhao();
						   cells[i][8]=stock.getGysname();
						   cells[i][9]=stock.getBeizhu();
						   i++;
						}
						
						jTable = new JTable(cells,stockHead);
						jScrollPane.setViewportView(jTable);
					}catch (ClassNotFoundException e2) {
						e2.printStackTrace();
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					
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
			jScrollPane.setBounds(new Rectangle(5, 60, 600, 550));
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
			jTable = new JTable(cells,head);
			jTable.setSize(new Dimension(500, 425));
		}
		return jTable;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(391, 30, 61, 25));
			jButton1.setText("修改");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (jTable.getSelectedRow()==-1||jTable.getValueAt(jTable.getSelectedRow(), 0)==null){
					JOptionPane.showMessageDialog(null, "所选列为空值或未选择！");
				}else if(biaoMing.equals("ERP_sell")){
					Ware ware = new Ware(); 
					//System.out.println(getJTable().getSelectedRow());
					ware.setId(Integer.parseInt(getJTable().getValueAt(getJTable().getSelectedRow(), 0).toString()));
					ware.setNumber(getJTable().getValueAt(getJTable().getSelectedRow(), 1).toString());
					ware.setName(getJTable().getValueAt(getJTable().getSelectedRow(), 2).toString());
					ware.setPrice(getJTable().getValueAt(getJTable().getSelectedRow(), 3).toString());
					ware.setCount(getJTable().getValueAt(getJTable().getSelectedRow(), 4).toString());
					ware.setTime(getJTable().getValueAt(getJTable().getSelectedRow(), 5).toString());
					new WareEdit(ware).setVisible(true);
				}
				else if(biaoMing.equals("ERP_stockform")){
					JingHuo jinghuo = new JingHuo(); 
					jinghuo.setId(Integer.parseInt(getJTable().getValueAt(getJTable().getSelectedRow(), 0).toString()));
					jinghuo.setNumber(getJTable().getValueAt(getJTable().getSelectedRow(), 1).toString());
					jinghuo.setName(getJTable().getValueAt(getJTable().getSelectedRow(), 2).toString());
					jinghuo.setMadeIn(getJTable().getValueAt(getJTable().getSelectedRow(), 3).toString());
					jinghuo.setPrice(getJTable().getValueAt(getJTable().getSelectedRow(), 4).toString());
					jinghuo.setCount(getJTable().getValueAt(getJTable().getSelectedRow(), 5).toString());
					jinghuo.setSupplier(getJTable().getValueAt(getJTable().getSelectedRow(), 6).toString());
					jinghuo.setTime(getJTable().getValueAt(getJTable().getSelectedRow(), 7).toString());
					new	JingHuoEdit(jinghuo).setVisible(true);
				}
				else if(biaoMing.equals("kehugl")){
					Customer customer=new Customer(); 
					customer.setId(Integer.parseInt(getJTable().getValueAt(getJTable().getSelectedRow(), 0).toString()));
					customer.setNumber(getJTable().getValueAt(getJTable().getSelectedRow(), 1).toString());
					customer.setKallcall(getJTable().getValueAt(getJTable().getSelectedRow(), 2).toString());
					customer.setAddress(getJTable().getValueAt(getJTable().getSelectedRow(), 3).toString());
					customer.setPhone(getJTable().getValueAt(getJTable().getSelectedRow(), 4).toString());
					customer.setEamil(getJTable().getValueAt(getJTable().getSelectedRow(), 5).toString());
					new	CustomerEdit(customer).setVisible(true);
				}
				else if(biaoMing.equals("spgl")){
					Stock stock=new Stock();
					stock.setId(Integer.parseInt(getJTable().getValueAt(getJTable().getSelectedRow(), 0).toString()));
					stock.setNumber(getJTable().getValueAt(getJTable().getSelectedRow(), 1).toString());
					stock.setName(getJTable().getValueAt(getJTable().getSelectedRow(), 2).toString());
					stock.setSimplename(getJTable().getValueAt(getJTable().getSelectedRow(), 3).toString());
					stock.setMadein(getJTable().getValueAt(getJTable().getSelectedRow(), 4).toString());
					stock.setUnit(getJTable().getValueAt(getJTable().getSelectedRow(), 5).toString());
					stock.setGuige(getJTable().getValueAt(getJTable().getSelectedRow(), 6).toString());
					stock.setPizhunwenhao(getJTable().getValueAt(getJTable().getSelectedRow(), 7).toString());
					stock.setGysname(getJTable().getValueAt(getJTable().getSelectedRow(), 8).toString());
					stock.setBeizhu(getJTable().getValueAt(getJTable().getSelectedRow(), 9).toString());
					new	StockEdit(stock).setVisible(true);
				}
				}
			});

		}
		return jButton1;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		String str[]={"商品库存","进货查询","销售查询","客户查询"};
		if (jComboBox == null) {
			jComboBox = new JComboBox(str);
			//jComboBox.add(str);
			jComboBox.setBounds(new Rectangle(227, 28, 83, 24));
		}
		return jComboBox;
	}

	/**
	 * @param args
	 */
/*	public static void main(String[] args) {


	}*/

	/**
	 * This is the default constructor
	 */
	public SellQuery() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(155, 28, 71, 22));
		jLabel1.setText("业务范围：");
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(8, 27, 57, 27));
		jLabel.setText("查询条件:");
		this.setSize(627, 671);
		Toolkit tl = Toolkit.getDefaultToolkit();
		Dimension d = tl.getScreenSize();
		int width = (int)d.getWidth();
		int height = (int)d.getHeight();
		this.setLocation((width-627)/2,(height-671)/2);
		
		this.setLayout(null);
		this.add(jLabel, null);
		this.add(getJTextField(), null);
		this.add(getJButton(), null);
		this.add(getJScrollPane(), null);
		this.add(getJButton1(), null);
		this.add(jLabel1, null);
		this.add(getJComboBox(), null);
		this.setVisible(true);
	}
	public static void main(String args[]){
		new SellQuery().setVisible(true);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
