package com.itstar;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;

import com.itstar.erp.SellEAP;
import com.itstar.erp.SellQuery;
import com.itstar.erp.login.AddRole;
import com.itstar.erp.login.DeleteRole;
import com.itstar.erp.login.Login;
import com.itstar.erp.login.Ruku;
import com.itstar.erp.login.SellRank;
import com.itstar.erp.login.TuihuoChaxun;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow2 extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar jJMenuBar = null;

	private JMenu jMenu = null;

	private JMenuItem jMenuItem = null;

	private JMenuItem jMenuItem1 = null;

	private JMenuItem jMenuItem2 = null;

	private JMenu jMenu1 = null;

	private JMenuItem jMenuItem3 = null;

	private JMenuItem jMenuItem4 = null;

	private JMenu jMenu2 = null;

	private JMenuItem jMenuItem5 = null;

	private JMenu jMenu3 = null;

	private JMenuItem jMenuItem9 = null;

	private JMenuItem jMenuItem10 = null;

	private JMenuItem jMenuItem11 = null;

	private JMenuItem jMenuItem12 = null;

	private JMenu jMenu5 = null;

	private JPanel jPanel = null;

	private JLabel jLabel = null;
	private Login login;
	private JMenuItem menuItem;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem_2;

	/**
	 * This is the default constructor
	 */
	public MainWindow2(Login login) {
		super();
		this.login = login;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 600);
		this.setContentPane(getJPanel());
		this.setResizable(false);
		this.setJMenuBar(getJJMenuBar());
		this.setTitle("进销存管理系统");

	}

	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
			jJMenuBar.add(getJMenu1());
			jJMenuBar.add(getJMenu2());
			jJMenuBar.add(getJMenu3());
			jJMenuBar.add(getJMenu5());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText(" 基础信息管理");
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem1());
			jMenu.add(getJMenuItem2());

		}
		return jMenu;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			//jMenuItem.setText("客户查询");

			jMenuItem
					.setIcon(new ImageIcon(
							"D:/film/\u5b66\u4e60/java\u6559\u7a0b/\u9b54\u4e863/\u9879\u76ee/\u6e90\u4ee3\u7801/JXCManager/res/ActionIcon/\u5ba2\u6237\u4fe1\u606f\u7ba1\u7406.PNG"));
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					kehuxinxi  kehu= new kehuxinxi();
					kehu.setVisible(true);
					
					
					
					
					
					
					
					
					
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jMenuItem;
	}

	/**
	 * This method initializes jMenuItem1
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();

			jMenuItem1
					.setIcon(new ImageIcon(
							getClass()
									.getResource(
											"/jilei/ActionIcon/\u5546\u54c1\u4fe1\u606f\u7ba1\u7406.png")));
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					  SpxInxi spx=new SpxInxi(); 
					  spx.setVisible(true);
					
						
				}
			});
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes jMenuItem2
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();

			jMenuItem2
					.setIcon(new ImageIcon(getClass().getResource("/jilei/ActionIcon/\u4f9b\u5e94\u5546\u4fe1\u606f\u7ba1\u7406.png")));
			jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					Gongyingshang gon = new Gongyingshang();
					gon.setVisible(true);

					
				}
			});

			

		}
		return jMenuItem2;
	}

	/**
	 * This method initializes jMenu1
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText("进货管理");
			jMenu1.add(getJMenuItem3());
			jMenu1.add(getJMenuItem4());
		}
		return jMenu1;
	}

	/**
	 * This method initializes jMenuItem3
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();

			jMenuItem3.setIcon(new ImageIcon(getClass().getResource(
					"/jilei/ActionIcon/\u8fdb\u8d27\u5355.png")));
			jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					Ruku ruku = new Ruku();
					//ruku.setVisible(true);
					
					
					
					
					
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jMenuItem3;
	}

	/**
	 * This method initializes jMenuItem4
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem4() {
		if (jMenuItem4 == null) {
			jMenuItem4 = new JMenuItem();

			jMenuItem4.setIcon(new ImageIcon(getClass().getResource(
					"/jilei/ActionIcon/\u8fdb\u8d27\u9000\u8d27.png")));
			jMenuItem4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					new TuihuoChaxun();
				}
			});
		}
		return jMenuItem4;
	}

	/**
	 * This method initializes jMenu2
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu2() {
		if (jMenu2 == null) {
			jMenu2 = new JMenu();
			jMenu2.setText("销售管理");
			jMenu2.add(getJMenuItem5());
			jMenu2.add(getJMenuItem10());
		}
		return jMenu2;
	}

	/**
	 * This method initializes jMenuItem5
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem5() {
		if (jMenuItem5 == null) {
			jMenuItem5 = new JMenuItem();
			jMenuItem5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new SellRank();
				}
			});
			jMenuItem5.setText("");
			jMenuItem5.setIcon(new ImageIcon(getClass().getResource(
					"/jilei/ActionIcon/\u9500\u552e\u6392\u884c.png")));
		}
		return jMenuItem5;
	}

	/**
	 * This method initializes jMenu3
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu3() {
		if (jMenu3 == null) {
			jMenu3 = new JMenu();
			jMenu3.setText("\u8BE6\u7EC6\u67E5\u8BE2");
			jMenu3.add(getJMenuItem9());
			/*jMenu3.add(getJMenuItem10());
			jMenu3.add(getJMenuItem11());
			jMenu3.add(getJMenuItem12());*/
		}
		return jMenu3;
	}

	/**
	 * This method initializes jMenuItem9
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem9() {
		if (jMenuItem9 == null) {
			jMenuItem9 = new JMenuItem();
			jMenuItem9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new SellQuery();
				}
			});
			jMenuItem9.setText("\u57FA\u672C\u67E5\u8BE2");
		}
		return jMenuItem9;
	}

	/**
	 * This method initializes jMenuItem10
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem10() {
		if (jMenuItem10 == null) {
			jMenuItem10 = new JMenuItem();
			jMenuItem10.setText("退货处理");
			
		}
		return jMenuItem10;
	}
	private JMenuItem getJMenuItem11() {
		if (jMenuItem11 == null) {
			jMenuItem11 = new JMenuItem();
			jMenuItem11.setText("客户查询");
		}
		return jMenuItem11;
	}

	/**
	 * This method initializes jMenuItem11
	 * 
	 * @return javax.swing.JMenuItem
	 */
/*	private JMenuItem getJMenuItem11() {
		if (jMenuItem11 == null) {
			jMenuItem11 = new JMenuItem();
			jMenuItem11.setText("入库查询");
		}
		return jMenuItem11;
	}*/

	/**
	 * This method initializes jMenuItem12
	 * 
	 * @return javax.swing.JMenuItem
	 */
/*	private JMenuItem getJMenuItem12() {
		if (jMenuItem12 == null) {
			jMenuItem12 = new JMenuItem();
			jMenuItem12.setText("入库退货查询");
		}
		return jMenuItem12;
	}*/

	/**
	 * This method initializes jMenu5
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu5() {
		if (jMenu5 == null) {
			jMenu5 = new JMenu();
			jMenu5.setText("系统管理");
			jMenu5.add(getMenuItem());
			jMenu5.add(getMenuItem_1());
			jMenu5.add(getMenuItem_2());
		}
		return jMenu5;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("JLabel");
			jLabel
					.setIcon(new ImageIcon(
							"D:/My Documents/My Pictures/\u552f\u7f8e\u7684\u7231/sdsads.PNG"));
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(jLabel, gridBagConstraints);
		}
		return jPanel;
	}
	private JMenuItem getMenuItem() {
		if (menuItem == null) {
			menuItem = new JMenuItem("\u6DFB\u52A0\u7528\u6237");
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(login.getUsername().equals("admin"))
						new AddRole();
					else
						JOptionPane.showMessageDialog(MainWindow2.this, "您没有该权限","Error",JOptionPane.ERROR_MESSAGE);
				}
			});
		}
		return menuItem;
	}
	private JMenuItem getMenuItem_1() {
		if (menuItem_1 == null) {
			menuItem_1 = new JMenuItem("\u5220\u9664\u7528\u6237");
			menuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(login.getUsername().equals("admin"))
						new DeleteRole();
					else
						JOptionPane.showMessageDialog(MainWindow2.this, "您没有该权限","Error",JOptionPane.ERROR_MESSAGE);
				}
			});
		}
		return menuItem_1;
	}
	private JMenuItem getMenuItem_2() {
		if (menuItem_2 == null) {
			menuItem_2 = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
			menuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return menuItem_2;
	}

	
	public MainWindow2(){
		super();
		initialize();
	}
	
	public static void main(String[] args){
		MainWindow2 w = new MainWindow2();
		w.setVisible(true);
	}
} 
