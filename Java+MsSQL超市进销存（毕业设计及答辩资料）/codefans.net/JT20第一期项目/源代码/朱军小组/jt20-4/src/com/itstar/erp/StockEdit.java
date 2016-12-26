package com.itstar.erp;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.itstar.dao.Dao;


public class StockEdit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField jTextField = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField2 = null;  //  @jve:decl-index=0:
	private JTextField jTextField3 = null;
	private JTextField jTextField4 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private int id;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JTextField jTextField5 = null;
	private JTextField jTextField6 = null;
	private JTextField jTextField7 = null;
	private JTextField jTextField8 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	Stock stock=null;
	public StockEdit(Stock stock){
		super();
		initialize();
		this.stock=stock;
		id=stock.getId();
		getJTextField().setText(stock.getNumber());
		getJTextField1().setText(stock.getName());
		getJTextField2().setText(stock.getSimplename());
		getJTextField3().setText(stock.getMadein());
		getJTextField4().setText(stock.getUnit());
		getJTextField5().setText(stock.getGuige());
		getJTextField6().setText(stock.getPizhunwenhao());
		getJTextField7().setText(stock.getGysname());
		getJTextField8().setText(stock.getBeizhu());
	}
	/**
 * This method initializes jTextField	
 * 	
 * @return javax.swing.JTextField	
 */
private JTextField getJTextField() {
	if (jTextField == null) {
		jTextField = new JTextField();
		jTextField.setBounds(new Rectangle(122, 17, 136, 25));
	}
	return jTextField;
}
	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(121, 64, 136, 24));
		}
		return jTextField1;
	}
	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setBounds(new Rectangle(124, 106, 135, 22));
		}
		return jTextField2;
	}
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setBounds(new Rectangle(124, 144, 133, 23));
		}
		return jTextField3;
	}
	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setBounds(new Rectangle(121, 192, 137, 23));
		}
		return jTextField4;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() { 
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(48, 451, 81, 34));
			jButton.setText("修改");
			jButton.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e){
					try {
						Dao.getConnection();
						System.out.print(getJTextField().getText()+getJTextField1().getText()+getJTextField2().getText()+getJTextField3().getText()+getJTextField4().getText());
						String str="INSERT INTO "+ SellQuery.biaoMing+"(id,spname,spjianchen,madein,unit,guige,pizhunwenhao,gysname,beizhu)VALUES("+"'"+getJTextField().getText()+"'"+","+"'"+getJTextField1().getText()+"'"+","+"'"+getJTextField2().getText()+"'"+","+"'"+getJTextField3().getText()+"'"+","+"'"+getJTextField4().getText()+"'"+","+"'"+getJTextField5().getText()+"'"+","+"'"+getJTextField6().getText()+"'"+","+"'"+getJTextField7().getText()+"'"+","+"'"+getJTextField8().getText()+"'"+")";
						String str2="delete from "+SellQuery.biaoMing+" where sid="+id;
						boolean boo=Dao.updata(str);
						boolean boo2=Dao.updata(str2);
						if(boo==true&&boo2==true)
							javax.swing.JOptionPane.showMessageDialog(null, "修改成功！");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
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
			jButton1.setBounds(new Rectangle(182, 453, 97, 36));
			jButton1.setText("取消");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getJTextField().setText(stock.getNumber());
					getJTextField1().setText(stock.getName());
					getJTextField2().setText(stock.getSimplename());
					getJTextField3().setText(stock.getMadein());
					getJTextField4().setText(stock.getUnit());
					getJTextField5().setText(stock.getGuige());
					getJTextField6().setText(stock.getPizhunwenhao());
					getJTextField7().setText(stock.getGysname());
					getJTextField8().setText(stock.getBeizhu());
				}
			});
		}
		return jButton1;
	}
	/**
	 * This method initializes jTextField5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField5.setBounds(new Rectangle(120, 240, 136, 25));
		}
		return jTextField5;
	}
	/**
	 * This method initializes jTextField6	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setBounds(new Rectangle(119, 284, 139, 25));
		}
		return jTextField6;
	}
	/**
	 * This method initializes jTextField7	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setBounds(new Rectangle(118, 321, 136, 24));
		}
		return jTextField7;
	}
	/**
	 * This method initializes jTextField8	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
			jTextField8.setBounds(new Rectangle(118, 360, 139, 24));
		}
		return jTextField8;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				WareEdit thisClass = new WareEdit();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public StockEdit() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(393, 561);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(21, 358, 81, 28));
			jLabel8.setText("备注:");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(21, 319, 79, 25));
			jLabel7.setText("供应商名称：");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(22, 281, 76, 31));
			jLabel6.setText("批准文号：");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(21, 243, 78, 25));
			jLabel5.setText("guige：");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(23, 187, 73, 29));
			jLabel4.setText("商unit：");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(24, 141, 70, 26));
			jLabel3.setText("生产厂商：");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(24, 104, 69, 24));
			jLabel2.setText("商品简称：");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(24, 60, 68, 27));
			jLabel1.setText("商品名：");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(23, 14, 68, 26));
			jLabel.setText("商品编号：");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJTextField1(), null);
			jContentPane.add(getJTextField2(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getJTextField3(), null);
			jContentPane.add(getJTextField4(), null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(getJTextField5(), null);
			jContentPane.add(getJTextField6(), null);
			jContentPane.add(getJTextField7(), null);
			jContentPane.add(getJTextField8(), null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(jLabel8, null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="5,-20"
