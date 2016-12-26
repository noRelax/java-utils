package com.itstar.system.swing;
//download by http://www.codefans.net
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.*;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.itstar.system.bean.UserBean;
import com.itstar.system.domainimple.UserDomainimple;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Point;

public class AddUsers extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private String[] colname={"�û���","����","Ȩ��"};

	private JTabbedPane jTabbedPane = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JLabel jLabel2 = null;

	private JTextField jTextField = null;

	private JPasswordField jPasswordField = null;

	private JPasswordField jPasswordField1 = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JTable jTable = null;
	
	private UserDomainimple ud =null;  //  @jve:decl-index=0:

	private JScrollPane jScrollPane = null;

	private JTextField jTextField1 = null;

	private JLabel jLabel3 = null;

	private JButton jButton2 = null;

	private JButton jButton3 = null;

	private JLabel jLabel4 = null;

	private JButton jButton4 = null;
	
	private DefaultTableModel dftm;

	/**
	 * This is the default constructor
	 */
	public AddUsers() {
		super();
		if(ud==null)
			ud=new UserDomainimple();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 300);
		this.setResizable(false);
		this.setLocation(new Point(450, 250));
		this.setContentPane(getJTabbedPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("���ϵͳ�û�", null, getJPanel(), null);
			jTabbedPane.addTab("ɾ��ϵͳ�û�", null, getJPanel1(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(24, 130, 91, 35));
			jLabel2.setText("ȷ�����룺");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(24, 80, 91, 35));
			jLabel1.setText("�������룺");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(24, 30, 91, 35));
			jLabel.setText("ϵͳ�û����ƣ�");
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel2, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJPasswordField(), null);
			jPanel.add(getJPasswordField1(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(97, 195, 103, 30));
			jLabel4.setText("��������ѡ��");
			jLabel3 = new JLabel("ɾ������Ϊ��");
			jLabel3.setBounds(new Rectangle(5, 195, 83, 31));
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(getJScrollPane(), null);
			jPanel1.add(jLabel3, null);
			jPanel1.add(getJButton2(), null);
			jPanel1.add(getJButton3(), null);
			jPanel1.add(jLabel4, null);
			jPanel1.add(getJButton4(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(153, 30, 210, 35));
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
			jPasswordField.setBounds(new Rectangle(153, 80, 210, 35));
		}
		return jPasswordField;
	}

	/**
	 * This method initializes jPasswordField1
	 * 
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordField1() {
		if (jPasswordField1 == null) {
			jPasswordField1 = new JPasswordField();
			jPasswordField1.setBounds(new Rectangle(153, 130, 210, 35));
		}
		return jPasswordField1;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton("���");
			jButton.setBounds(new Rectangle(80, 185, 110, 35));
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					String username = jTextField.getText();
					String upassword = jPasswordField.getText();
					if (ud.isExist(username)) {
						JOptionPane.showMessageDialog(null, "���û��Ѵ���", "��ʾ",
								JOptionPane.QUESTION_MESSAGE);
					} else {
						if (!upassword.equals(jPasswordField1.getText())) {
							JOptionPane.showMessageDialog(null, "�����������벻��ͬ",
									"����", JOptionPane.WARNING_MESSAGE);
						} else {
							if(ud.Add(username, upassword)){
								ud.userlist.add(new UserBean(username,upassword));
								JOptionPane.showMessageDialog(null, "��ӳɹ�", "OK",
										JOptionPane.PLAIN_MESSAGE);
							}
							else
								JOptionPane.showMessageDialog(null, "���ʧ��",
										"Fail", JOptionPane.ERROR_MESSAGE);
						}
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
			jButton1 = new JButton("����");
			jButton1.setBounds(new Rectangle(270, 185, 110, 35));
			jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					jTextField.setText("");
					jPasswordField.setText("");
					jPasswordField1.setText("");
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if(jTable==null){
			jTable = new JTable();
			dftm = (DefaultTableModel) jTable.getModel();
			dftm.setColumnIdentifiers(colname);
			initTable();
			jTable.setAutoCreateColumnsFromModel(true);
			jTable.setRowSelectionAllowed(true);
			jTable.setColumnSelectionAllowed(false);
			jTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(final MouseEvent e) {
					String uName;
					int selRow = jTable.getSelectedRow();
					uName = jTable.getValueAt(selRow, 0).toString().trim();
					jLabel4.setText(uName);
				}
			});
		}
		return jTable;
	}
	
	public void initTable(){
		dftm.setDataVector(ud.queryForDelete(),colname);
//		jScrollPane.setVisible(true);
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(3, 1, 483, 181));
			jScrollPane.setViewportView(getJTable());
			jScrollPane.setVisible(true);
			jTable.setVisible(true);
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("");
			jTextField1.setBounds(new Rectangle(97, 195, 155, 30));
		}
		return jTextField1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton("ɾ��");
			jButton2.setBounds(new Rectangle(288, 195, 80, 31));
			jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if((JOptionPane.showConfirmDialog(null, 
							"ȷ��ɾ��", "ȷ��ɾ��", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION){
					if(ud.deleteUser(jLabel4.getText())){
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "OK",
								JOptionPane.PLAIN_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "ɾ��ʧ��",
								"Fail", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton("�ر�");
			jButton3.setBounds(new Rectangle(385, 195, 80, 31));
			jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
				if((JOptionPane.showConfirmDialog(null, 
						"�˳�", "�˳�", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION)
				setVisible(false);

				}
			});
		}
		return jButton3;
	}

	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton("ˢ��");
			jButton4.setBounds(new Rectangle(210, 195, 60, 31));
			jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					initTable();
//					ExportExcel exportExcel = new ExportExcel(jTable);
//					exportExcel.export();
				}
			});
		}
		return jButton4;
	}
} // @jve:decl-index=0:visual-constraint="103,13"
