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

import com.itstar.erp.bean.Manufacturer;
import java.awt.Dimension;
import java.awt.Point;
public class AddManufacturer extends JFrame {
	
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
			jButton.setText("ȷ�����");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if("".equals((jTextField2).getText())
						||"".equals((jTextField4).getText())
						||"".equals((jTextField5).getText())
						||"".equals((jTextField6).getText())){
						JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ��");
						jTextField2.requestFocus();	
					}
				   else if(!jTextField4.getText().matches("^[0-9]*$")){
					JOptionPane.showMessageDialog(null, "�绰����ҪΪ����");
					jTextField4.setText("");
					jTextField4.requestFocus();
					return;
					}
				  else if(!jTextField5.getText().matches("^[0-9]*$")){
					JOptionPane.showMessageDialog(null, "�����ʺ�ҪΪ����");
					jTextField5.setText("");
					jTextField5.requestFocus();
					return;
					}
				  else if(!jTextField6.getText().matches("\\w{1,}@\\w{1,}\56\\w{1,}")){
				  JOptionPane.showMessageDialog(null, "�����ʼ������ϸ�ʽ");
					jTextField6.setText("");
					jTextField6.requestFocus();
					return;
					}
					else{
						    Manufacturer gd = new Manufacturer();					
							gd.setMName(jTextField2.getText());
							gd.setMAddress(jTextField3.getText());					
							gd.setMPhone(jTextField4.getText());
							gd.setMAccount(jTextField5.getText());
							gd.setMEmail(jTextField6.getText());
							gd.addManufacturer();
							JOptionPane.showMessageDialog(null, "��ӳɹ�");
                            
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
			jButton1.setText("��������");
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
	public AddManufacturer() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 340);
		this.setContentPane(getJContentPane());
		this.setTitle("���ӹ�Ӧ��");
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
			jLabel6.setText("�����ʼ���");
			jLabel6.setSize(new Dimension(80, 25));
			jLabel6.setPreferredSize(new Dimension(65, 18));
			jLabel6.setLocation(new Point(30, 210));
			jLabel5 = new JLabel();
			jLabel5.setText("�����ʺţ�");
			jLabel5.setLocation(new Point(30, 180));
			jLabel5.setSize(new Dimension(80, 25));
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(110, 17, 143, 39));
			jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel4.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel4.setText("��ӹ�Ӧ��");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(30, 150, 80, 25));
			jLabel3.setText("��ϵ�绰��");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(30, 120, 80, 25));
			jLabel2.setText("��Ӧ�̵�ַ��");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(30, 90, 80, 25));
			jLabel1.setText("��Ӧ��������");
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
			jContentPane.add(jLabel6, null);
			jContentPane.add(getJTextField3(), null);
			jContentPane.add(getJTextField6(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="224,-17"
