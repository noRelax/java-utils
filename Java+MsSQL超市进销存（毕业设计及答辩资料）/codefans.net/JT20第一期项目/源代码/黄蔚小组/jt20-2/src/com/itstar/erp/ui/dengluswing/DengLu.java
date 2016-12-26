package com.itstar.erp.ui.dengluswing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


import com.itstar.erp.dao.tianjiayonghu.TianJiaUserDaoImpl;
import com.itstar.erp.ui.mainswing.ZhuChuangKou;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.util.GetTime;
import com.itstar.erp.vo.user.UserBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DengLu extends JFrame {
	String date=new GetTime().getTime(1);  //  @jve:decl-index=0:

	private static final long serialVersionUID = 1L;
	private JDesktopPane jDesktopPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField = null;
	private JPasswordField jPasswordField = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JLabel jLabel3 = null;
	/**
	 * This method initializes jDesktopPane	
	 * 	
	 * @return javax.swing.JDesktopPane	
	 */
	
//public void paint(Graphics g){
	//g.drawString("华夏英才软件学院", 136, 153);
	private JDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(492, 444, 258, 43));
			jLabel3.setText(date);
//			jLabel3.setName(date);
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(254, 252, 89, 28));
			jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel2.setText("PASSWORD");
			jLabel1 = new JLabel();
			jLabel1.setVerticalAlignment(SwingConstants.CENTER);
			jLabel1.setVerticalTextPosition(SwingConstants.BOTTOM);
			jLabel1.setSize(new Dimension(88, 32));
			jLabel1.setLocation(new Point(251, 201));
			jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel1.setBackground(new Color(238, 0, 238));
			jLabel1.setText("USER");
			jLabel = new JLabel();
			jLabel.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel.setBackground(new Color(153, 153, 255));
			jLabel.setForeground(new Color(51, 51, 51));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel.setBounds(new Rectangle(-1, 21, 763, 96));
			jLabel.setText("进销存管理系统");
			jDesktopPane = new JDesktopPane();
			jDesktopPane.setBackground(new Color(204, 204, 204));
			jDesktopPane.add(jLabel1, null);
			jDesktopPane.add(jLabel2, null);
			jDesktopPane.add(getJTextField(), null);
			jDesktopPane.add(getJPasswordField(), null);
			jDesktopPane.add(getJButton(), null);
			jDesktopPane.add(getJButton1(), null);
			jDesktopPane.add(jLabel, null);
			jDesktopPane.add(jLabel3, null);
			
		}
		return jDesktopPane;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			
//			jTextField.setFont(f.BOTTOM_ALIGNMENT);
			jTextField.setBounds(new Rectangle(371, 202, 96, 33));
			jTextField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		}
		return jTextField;
	}

	/**
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(new Rectangle(374, 255, 94, 29));
			jPasswordField.setForeground(Color.black);
			jPasswordField.setFont(new Font("Dialog", Font.BOLD, 14));
			jPasswordField.setBackground(Color.white);
		
		}
		return jPasswordField;
	}
	public void close()
	{
		this.dispose();
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(241, 300, 106, 32));
			jButton.setBackground(Color.lightGray);
			jButton.setText("LOGIN");
			
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String user=jTextField.getText().toString().trim();
					String pass=jPasswordField.getText().toString().trim();
					boolean flag=new TianJiaUserDaoImpl().test(user,pass);
					System.out.print(flag);
					
				
					
					if(true==flag){
						ResultSet rs=new GetRS().getResultSet("select * from tb_user_info where userName='"+user+"'");
						UserBean userbean=new UserBean();
						try {
							while(rs.next()){
								userbean.setUserName(rs.getString(2));
								userbean.setUserPower(rs.getInt(4));
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						if(userbean.getUserPower()==1){
						      JOptionPane.showMessageDialog(null, "您是超级管理员 ，欢迎使用  ");
						}else if(userbean.getUserPower()==2){
							 JOptionPane.showMessageDialog(null, "您是普通管理员 ，欢迎使用  ");
						}else if(userbean.getUserPower()==0){
							 JOptionPane.showMessageDialog(null, "您是普通用户 ，欢迎使用  ");
						}
						ZhuChuangKou z=new ZhuChuangKou(user);
						z.setVisible(true);
						jTextField.setText("");
						jPasswordField.setText("");
					}else{
						JOptionPane.showMessageDialog(null, "登录失败，请重新输入 ");
						jTextField.setText("");
						jPasswordField.setText("");
						jTextField.requestFocus(true);
					}
					
				}
			});
		}
		return jButton;
	}
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBackground(Color.lightGray);
			jButton1.setBounds(new Rectangle(373, 302, 125, 29));
			jButton1.setText("RESET");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTextField.setText("");
					jPasswordField.setText("");
					jTextField.requestFocus(true);
					
				}
			});
		}
		return jButton1;
	}
	

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DengLu thisClass = new DengLu();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				thisClass.setVisible(true);
				 setDefaultLookAndFeelDecorated(true); 
				
				
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public DengLu() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJDesktopPane());
		this.setBounds(new Rectangle(400, 250, 770, 536));
		this.setBackground(new Color(153, 153, 153));
		this.setTitle("进销存管理系统");
	   this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   this.setLocationRelativeTo(null);
	  
	   
	}
 
    /**   
      *   @param   args   
      */   
 

}   
 
  //  @jve:decl-index=0:visual-constraint="86,9"