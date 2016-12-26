package com.itstar.erp.basicmessage;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.itstar.erp.bean.Client;
import java.awt.Dimension;
import java.awt.Point;
public class AddClient extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="142,27"

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private int width = Toolkit.getDefaultToolkit().getScreenSize().width;

	private int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	private JLabel jLabel5 = null;


	private JTextField jTextField2 = null;


	private JTextField jTextField4 = null;

	private JTextField jTextField5 = null;

	private JButton jButton = null;

	private JButton jButton1 = null;


	private JLabel jLabel6 = null;

	private JTextField jTextField3 = null;

	private JTextField jTextField6 = null;

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
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(31, 255, 90, 25));
			jButton.setText("确认添加");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if( "".equals((jTextField2).getText())
						||"".equals((jTextField3).getText())
						||"".equals((jTextField4).getText())
						||"".equals((jTextField5).getText())
						||"".equals((jTextField6).getText())){
						JOptionPane.showMessageDialog(null, "信息不能为空");
						jTextField2.requestFocus();	
					}

					else if(!jTextField4.getText().matches("^[0-9]*$")){
						JOptionPane.showMessageDialog(null, "电话号码要为数字");
						jTextField4.setText("");
						jTextField4.requestFocus();
						return;
						}
					else if(!jTextField5.getText().matches("^[0-9]*$")){
						JOptionPane.showMessageDialog(null, "银行帐号要为数字");
						jTextField5.setText("");
						jTextField5.requestFocus();
						return;
						}
					else if(!jTextField6.getText().matches("\\w{1,}@\\w{1,}\56\\w{1,}")){
					    JOptionPane.showMessageDialog(null, "电子邮件不符合格式");
						jTextField6.setText("");
						jTextField6.requestFocus();
						return;
						}
					else{
							Client gd = new Client();					
							gd.setCName(jTextField2.getText());
							gd.setCAddress(jTextField3.getText());					
							gd.setCPhone(jTextField4.getText());
							gd.setCAcount(jTextField5.getText());
							gd.setCEmail(jTextField6.getText());
							gd.addCli();
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
			jButton1.setBounds(new Rectangle(237, 254, 90, 25));
			jButton1.setText("重新输入");
			jButton1.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					getJTextField2().setText("");
					getJTextField3().setText("");
					getJTextField4().setText("");
					getJTextField5().setText("");
					getJTextField6().setText("");
					jTextField2.requestFocus();
				}
			});
		}
		return jButton1;
	}
	/**
	 * This method initializes jTextField3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setLocation(new Point(130, 120));
			jTextField3.setSize(new Dimension(200, 25));
		}
		return jTextField3;
	}

	/**
	 * This method initializes jTextField6	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setSize(new Dimension(200, 25));
			jTextField6.setLocation(new Point(130, 210));
		}
		return jTextField6;
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
	public AddClient() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 333);
		this.setContentPane(getJContentPane());
		this.setTitle("增加客户");
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
			jLabel6 = new JLabel();
			jLabel6.setText("电子邮件：");
			jLabel6.setSize(new Dimension(80, 25));
			jLabel6.setPreferredSize(new Dimension(65, 18));
			jLabel6.setLocation(new Point(30, 210));
			jLabel5 = new JLabel();
			jLabel5.setText("银行帐号：");
			jLabel5.setLocation(new Point(30, 180));
			jLabel5.setSize(new Dimension(80, 25));
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(98, 18, 143, 39));
			jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel4.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel4.setText("添加客户");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(30, 150, 80, 25));
			jLabel3.setText("客户电话：");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(30, 120, 80, 25));
			jLabel2.setText("客户地址：");
			jLabel1 = new JLabel();
			jLabel1.setText("客户姓名：");
			jLabel1.setLocation(new Point(30, 89));
			jLabel1.setSize(new Dimension(80, 25));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setSize(new Dimension(360, 316));
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getJTextField2(), null);
			jContentPane.add(getJTextField4(), null);
			jContentPane.add(getJTextField5(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(getJTextField3(), null);
			jContentPane.add(getJTextField6(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="132,16"
