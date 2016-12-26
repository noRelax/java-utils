package com.itstar;
//download by http://www.codefans.net
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel lblUserName = null;

	private JLabel lblPassWord = null;

	private JTextField txtUserName = null;

	private JButton btnConfirm = null;

	private JButton btnReset = null;

	private JLabel jLabel = null;

	private JPasswordField jPasswordField = null;

	/**
	 * This is the default constructor
	 */
	public LoginFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(466, 289);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("进销存管理系统");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(101, 14, 205, 67));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 36));
			jLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
			jLabel.setText("进销存登录");
			lblPassWord = new JLabel();
			lblPassWord.setBounds(new Rectangle(84, 142, 52, 29));
			lblPassWord.setHorizontalAlignment(SwingConstants.CENTER);
			lblPassWord.setText("密码");
			lblUserName = new JLabel();
			lblUserName.setBounds(new Rectangle(83, 99, 56, 24));
			lblUserName.setHorizontalTextPosition(SwingConstants.CENTER);
			lblUserName.setText("用户名");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setVisible(true);
			jContentPane.add(lblUserName, null);
			jContentPane.add(lblPassWord, null);
			jContentPane.add(getTxtUserName(), null);
			jContentPane.add(getBtnConfirm(), null);
			jContentPane.add(getBtnReset(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJPasswordField(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes txtUserName
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtUserName() {
		if (txtUserName == null) {
			txtUserName = new JTextField();
			txtUserName.setBounds(new Rectangle(179, 94, 133, 31));
		}
		return txtUserName;
	}

	/**
	 * This method initializes btnConfirm
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnConfirm() {
		if (btnConfirm == null) {
			btnConfirm = new JButton();
			btnConfirm.setBounds(new Rectangle(143, 194, 64, 24));
			btnConfirm.setText("确定");
			btnConfirm.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					String userName = txtUserName.getText();
					String passWord = jPasswordField.getText();
					JOptionPane jop=new JOptionPane();
					  Find find=new Find();
					
					
					if (find.lianjie(userName, passWord)) {
						jop.showMessageDialog(jop,"登录成功");
						new MainWindow2 ().setVisible(true);
						  setVisible(false);
					
					} else {
					
						jop.showMessageDialog(jop,"用户不存在请重新登录");
						
					}

				}

			});
		}
		return btnConfirm;
	}

	/**
	 * This method initializes btnReset
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton();
			btnReset.setBounds(new Rectangle(246, 195, 67, 22));
			btnReset.setText("重设");
			btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					   
			  txtUserName.setText("");
			 jPasswordField.setText("");
					
					System.out.println("mouseClicked()");
				}
				
			});
		   
		
		}
		return btnReset;
	}

	/**
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(new Rectangle(180, 139, 133, 29));
		}
		return jPasswordField;
	}

}  //  @jve:decl-index=0:visual-constraint="35,-2"




