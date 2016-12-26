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
public class UpdClient extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

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

	private JTextField jTextField3 = null;

	private JTextField jTextField6 = null;

	private JLabel jLabel6 = null;

	private JTextField jTextField7 = null;

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
			jButton.setBounds(new Rectangle(31, 261, 100, 25));
			jButton.setText("修改");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Client gd = new Client();
					gd.setCId(Integer.parseInt(jTextField3.getText()));
					gd.setCName(jTextField2.getText());
					gd.setCAddress(jTextField6.getText());					
					gd.setCPhone(jTextField4.getText());
					gd.setCAcount(jTextField5.getText());
					gd.setCEmail(jTextField7.getText());
					gd.update();
					JOptionPane.showMessageDialog(null, "修改成功");
					dispose(); 
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
			jButton1.setText("重新输入");
			jButton1.setLocation(new Point(241, 261));
			jButton1.setSize(new Dimension(90, 25));
			jButton1.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    

					getJTextField2().setText("");
					getJTextField4().setText("");
					getJTextField5().setText("");
					getJTextField6().setText("");
					getJTextField7().setText("");
					
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
	JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setBounds(new Rectangle(313, 17, 10, 22));
			jTextField3.setVisible(false);
		}
		return jTextField3;
	}

	/**
	 * This method initializes jTextField6	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setBounds(new Rectangle(130, 120, 200, 25));
		}
		return jTextField6;
	}

	/**
	 * This method initializes jTextField7	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setSize(new Dimension(200, 25));
			jTextField7.setLocation(new Point(129, 210));
		}
		return jTextField7;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UpdRun thisClass = new UpdRun();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public UpdClient() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 346);
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
			jLabel6.setLocation(new Point(30, 213));
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(30, 183, 80, 25));
			jLabel5.setText("银行帐号：");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(110, 17, 143, 39));
			jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel4.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel4.setText("修改信息");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(30, 150, 80, 25));
			jLabel3.setText("客户电话：");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(30, 120, 80, 25));
			jLabel2.setText("客户地址");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(30, 90, 80, 25));
			jLabel1.setText("客户姓名：");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
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
			jContentPane.add(getJTextField3(), null);
			jContentPane.add(getJTextField6(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(getJTextField7(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
