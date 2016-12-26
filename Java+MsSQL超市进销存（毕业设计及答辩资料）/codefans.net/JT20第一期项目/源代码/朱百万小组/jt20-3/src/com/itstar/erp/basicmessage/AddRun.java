package com.itstar.erp.basicmessage;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.itstar.erp.bean.Goods;
import com.itstar.erp.bean.Manufacturer;
import com.itstar.erp.bean.Stock;
public class AddRun extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private int width = Toolkit.getDefaultToolkit().getScreenSize().width;

	private int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	private JLabel jLabel5 = null;


	private JTextField jTextField1 = null;

	private JTextField jTextField2 = null;


	private JTextField jTextField4 = null;

	private JTextField jTextField5 = null;

	private JComboBox jComboBox = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(130, 60, 200, 25));
		}
		return jTextField1;
	}

	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public  JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setBounds(new Rectangle(130, 90, 200, 25));
		}
		return jTextField2;
	}

	/**
	 * This method initializes jTextField4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public  JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setBounds(new Rectangle(130, 150, 200, 25));
		}
		return jTextField4;
	}

	/**
	 * This method initializes jTextField5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public  JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField5.setBounds(new Rectangle(130, 180, 200, 25));
		}
		return jTextField5;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public  JComboBox getJComboBox() {
		Manufacturer tt=new Manufacturer();
		List list=tt.query();
		if (jComboBox == null) {
			
			jComboBox = new JComboBox();
			jComboBox.setBounds(new Rectangle(130, 120, 200, 25));
			for(int i=0;i<list.size();i++){
				jComboBox.addItem(list.get(i));
			}
		}
		return jComboBox;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(45, 226, 90, 25));
			jButton.setText("确认添加");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if("".equals(jTextField1.getText())
						||"".equals((jTextField2).getText())
						||"".equals((jTextField4).getText())
						||"".equals((jTextField5).getText())){
						JOptionPane.showMessageDialog(null, "信息不能为空");
						jTextField1.requestFocus();	
					}
                    
					else if(!jTextField4.getText().matches("^[0-9]*$")){
							JOptionPane.showMessageDialog(null, "进货价为数字");
							jTextField4.setText("");
							jTextField4.requestFocus();
							return;
							}
					else if(!jTextField5.getText().matches("^[0-9]*$")){
							JOptionPane.showMessageDialog(null, "出货价要为数字");
							jTextField5.setText("");
							jTextField5.requestFocus();	
							return;
					}
					else{
							new Goods();					
							Goods.setGName(jTextField1.getText());
							Goods.setGAddress(jTextField2.getText());
							Goods.setGMId(Integer.parseInt(jComboBox.getSelectedItem().toString()));					
							Goods.setGInPrice(new Float(jTextField4.getText()).floatValue());
							Goods.setGOutPrice(new Float(jTextField5.getText()).floatValue());
							Goods.addManufacturer();
							JOptionPane.showMessageDialog(null, "添加成功");
							WareRun wr = new WareRun();
						    wr.jTable.setModel(wr.getTableModel());
							dispose();  
							}		
						}				
				}
					);
			
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
			jButton1.setBounds(new Rectangle(160, 226, 90, 25));
			jButton1.setText("重新输入");
			jButton1.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					getJTextField1().setText("");
					getJTextField2().setText("");
					getJTextField4().setText("");
					getJTextField5().setText("");
					jTextField1.requestFocus();
				}
			});
		}
		return jButton1;
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AddRun thisClass = new AddRun();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public AddRun() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(400,300);
		this.setContentPane(getJContentPane());
		this.setTitle("增加商品");
		this.setResizable(false);
		this.setLocation((width - 400) / 2, (height - 300) / 2);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(30, 183, 80, 25));
			jLabel5.setText("出货价：");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(110, 17, 143, 39));
			jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel4.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel4.setText("添加商品");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(30, 150, 80, 25));
			jLabel3.setText("进货价：");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(30, 120, 80, 25));
			jLabel2.setText("供应商编号：");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(30, 90, 80, 25));
			jLabel1.setText("供应商地址：");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(30, 60, 80, 25));
			jLabel.setText("商品名称：");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getJTextField1(), null);
			jContentPane.add(getJTextField2(), null);
			jContentPane.add(getJTextField4(), null);
			jContentPane.add(getJTextField5(), null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
		}
		return jContentPane;
	}

}
