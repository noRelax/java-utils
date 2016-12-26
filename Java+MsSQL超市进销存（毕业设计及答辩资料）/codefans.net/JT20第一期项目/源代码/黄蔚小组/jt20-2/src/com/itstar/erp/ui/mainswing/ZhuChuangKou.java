package com.itstar.erp.ui.mainswing;



import java.awt.*;
import javax.swing.*;


import java.awt.Dimension;

import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JMenu;

import com.itstar.erp.ui.jichuxinxiswing.JiChuXinXiJFrame;
import com.itstar.erp.ui.kucunswing.KuCunJFrame;
import com.itstar.erp.ui.rukuswing.RuKuJFrame;
import com.itstar.erp.ui.searchswing.SearchJFrame;
import com.itstar.erp.ui.sellswing.SellJFrame;
import com.itstar.erp.ui.tianjiaswing.TianJia;
import com.itstar.erp.ui.tuihuoswing.TuiHuoJFrame;
import com.itstar.erp.ui.tuikuswing.TuiKuJFrame;
import com.itstar.erp.util.GetRS;
import com.itstar.erp.vo.user.UserBean;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZhuChuangKou extends JFrame {
	
	static int t = 0;
	JFrame f1 = null;

	Dimension d = getSize();
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenu jMenu = null;
	private JMenu jMenu1 = null;
	private JMenu jMenu2 = null;
	private JMenu jMenu3 = null;
	private JMenu jMenu4 = null;
	private JMenu jMenu5 = null;
	private JMenuItem jMenuItem = null;
	private JMenuItem jMenuItem1 = null;
	private JMenuItem jMenuItem2 = null;
	private JMenuItem jMenuItem3 = null;
	private JMenuItem jMenuItem4 = null;
	private JMenuItem jMenuItem5 = null;
	private JMenuItem jMenuItem6 = null;
	private JMenuItem jMenuItem7 = null;
	private JMenuItem jMenuItem8 = null;
	private JMenuItem jMenuItem9 = null;
	private JMenuItem jMenuItem10 = null;
	private JMenuItem jMenuItem11 = null;
	private JMenuItem jMenuItem12 = null;
	private JMenuItem jMenuItem13 = null;
	private JMenuBar jJMenuBar = null;
	private ScrollPane scrollPane = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton5 = null;

	private ZhuChuangKou obj = null;
	private JMenu jMenu6 = null;
	private JButton jButton4 = null;
	private JButton jButton6 = null;
	private JButton jButton7 = null;
	
	
	UserBean userbean=new UserBean();
//	UserBean userbean=new UserBean();  //  @jve:decl-index=0:
	String user;
	/**
	 * This is the default constructor
	 */
	public ZhuChuangKou(String user) {
		super();
		this.user=user;
		initialize();
		obj = this;
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
//		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("进销存管理系统");
		 JFrame.setDefaultLookAndFeelDecorated(true); 
		 this.setSize(800,600);
		 this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			// jContentPane.add(getScrollPane(), null);
			jContentPane.setFont(new Font("Dialog", Font.PLAIN, 12));
			jContentPane.setForeground(Color.white);
//			jContentPane.setBackground(new Color(204, 204, 204));
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(getJButton3(), null);
			jContentPane.add(getJButton5(), null);
			jContentPane.add(getJButton4(), null);
			jContentPane.add(getJButton6(), null);
			jContentPane.add(getJButton7(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("基础信息");
//			jMenu.setSize(new Dimension(d.width / 5, 23));
			jMenu.setBackground(new Color(44, 0, 255));
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem1());
			jMenu.add(getJMenuItem2());
			jMenu.add(getJMenuItem3());
			jMenu.add(getJMenuItem4());
		}
		return jMenu;
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
//			jMenu1.setSize(new Dimension(d.width / 5, 23));
			jMenu1.setBackground(new Color(187, 0, 238));
			jMenu1.add(getJMenuItem5());
			jMenu1.add(getJMenuItem6());
		}
		return jMenu1;
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
//			jMenu2.setSize(new Dimension(d.width / 5, 23));
			jMenu2.add(getJMenu3());
			jMenu2.add(getJMenuItem7());
		}
		return jMenu2;
	}

	/**
	 * This method initializes jMenu3
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu3() {
		if (jMenu3 == null) {
			jMenu3 = new JMenu();
			jMenu3.setText("销售单");
		}
		return jMenu3;
	}

	/**
	 * This method initializes jMenu4
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu4() {
		if (jMenu4 == null) {
			jMenu4 = new JMenu();
			jMenu4.setText("库存管理");
//			jMenu4.setSize(new Dimension(d.width / 5, 23));
			jMenu4.setActionCommand("库存管理");
			jMenu4.add(getJMenuItem9());
			jMenu4.add(getJMenuItem10());
		}
		return jMenu4;
	}

	/**
	 * This method initializes jMenu5
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu5() {
		if (jMenu5 == null) {
			jMenu5 = new JMenu();
			jMenu5.setText("报表中心");
//			jMenu5.setSize(new Dimension(0, 23));
			jMenu5.add(getJMenuItem11());
			jMenu5.add(getJMenuItem12());
			jMenu5.add(getJMenuItem13());
		}
		return jMenu5;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("客户资料");
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
			jMenuItem1.setText("供应商资料");
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
			jMenuItem2.setText("业务员资料");
		}
		return jMenuItem2;
	}

	/**
	 * This method initializes jMenuItem3
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("商品类别资料");
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
			jMenuItem4.setText("商品资料");
		}
		return jMenuItem4;
	}

	/**
	 * This method initializes jMenuItem5
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem5() {
		if (jMenuItem5 == null) {
			jMenuItem5 = new JMenuItem();
			jMenuItem5.setText("入库单");
		}
		return jMenuItem5;
	}

	/**
	 * This method initializes jMenuItem6
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem6() {
		if (jMenuItem6 == null) {
			jMenuItem6 = new JMenuItem();
			jMenuItem6.setText("采购退货单");
		}
		return jMenuItem6;
	}

	/**
	 * This method initializes jMenuItem7
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem7() {
		if (jMenuItem7 == null) {
			jMenuItem7 = new JMenuItem();
			jMenuItem7.setText("销售退货单");
		}
		return jMenuItem7;
	}

	/**
	 * This method initializes jMenuItem8
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem8() {
		if (jMenuItem8 == null) {
			jMenuItem8 = new JMenuItem();
		}
		return jMenuItem8;
	}

	/**
	 * This method initializes jMenuItem9
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem9() {
		if (jMenuItem9 == null) {
			jMenuItem9 = new JMenuItem();
			jMenuItem9.setText("库存盘店");
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
			jMenuItem10.setText("商品调价");
		}
		return jMenuItem10;
	}

	/**
	 * This method initializes jMenuItem11
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem11() {
		if (jMenuItem11 == null) {
			jMenuItem11 = new JMenuItem();
			jMenuItem11.setText("销售报表");
		}
		return jMenuItem11;
	}

	/**
	 * This method initializes jMenuItem12
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem12() {
		if (jMenuItem12 == null) {
			jMenuItem12 = new JMenuItem();
			jMenuItem12.setText("采购报表");
		}
		return jMenuItem12;
	}

	/**
	 * This method initializes jMenuItem13
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem13() {
		if (jMenuItem13 == null) {
			jMenuItem13 = new JMenuItem();
			jMenuItem13.setText("经营报表");
		}
		return jMenuItem13;
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
			jJMenuBar.add(getJMenu4());
			jJMenuBar.add(getJMenu5());
			jJMenuBar.add(getJMenu6());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setLocation(new Point(15, 34));
			jButton.setText("基础信息管理");
			jButton.setActionCommand("基础信息");
			jButton.setBackground(Color.white);
			jButton.setForeground(Color.black);
			jButton.setFont(new Font("Dialog", Font.BOLD, 14));
			jButton.setSize(new Dimension(124, 43));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t>0){
						JiChuXinXiJFrame f = new JiChuXinXiJFrame();
						f.setVisible(true);
						f1.dispose();
						f1=f;
						t++;
						
						
						
						
					}else{
						JiChuXinXiJFrame f = new JiChuXinXiJFrame();
						f.setVisible(true);
						f1=f;
						t++;
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
			jButton1.setBounds(new Rectangle(55, 100, 116, 39));
			jButton1.setActionCommand("进货管理");
			jButton1.setBackground(Color.white);
			jButton1.setForeground(Color.black);
			jButton1.setFont(new Font("Dialog", Font.BOLD, 14));
			jButton1.setText("入库管理");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t>0){
						RuKuJFrame f = new RuKuJFrame();
						f.setVisible(true);
						f1.dispose();
						f1=f;
						t++;
						
					}else{
						RuKuJFrame f = new RuKuJFrame();
						f.setVisible(true);
						f1=f;
						t++;
					}
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(149, 263, 127, 37));
			jButton2.setActionCommand("销售管理");
			jButton2.setBackground(Color.white);
			jButton2.setFont(new Font("Dialog", Font.BOLD, 14));
			jButton2.setText("销售管理");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t>0){
						f1.dispose();
						SellJFrame f = new SellJFrame();
						f1=f;
						f.setVisible(true);
						t++;
						
						
						
					}else{
						SellJFrame f = new SellJFrame();
						f.setVisible(true);
						f1=f;
						t++;
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
			jButton3 = new JButton();
			jButton3.setBounds(new Rectangle(127, 207, 110, 40));
			jButton3.setActionCommand("库存管理");
			jButton3.setBackground(Color.white);
			jButton3.setFont(new Font("Dialog", Font.BOLD, 14));
			jButton3.setText("库存盘点");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t>0){
						KuCunJFrame f = new KuCunJFrame();
						f.setVisible(true);
						f1.dispose();
						f1=f;
						t++;
						
						
					}else{
						KuCunJFrame f = new KuCunJFrame();
						f.setVisible(true);
						f1=f;
						t++;
					}

				}
			});
		}
		return jButton3;
	}

	/**
	 * This method initializes jButton5
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setBounds(new Rectangle(201, 373, 123, 36));
			jButton5.setActionCommand("报表中心");
			jButton5.setBackground(Color.white);
			jButton5.setFont(new Font("Dialog", Font.BOLD, 14));
			jButton5.setText("查询中心");
			jButton5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t>0){
						SearchJFrame f = new SearchJFrame();
						f.setVisible(true);
						f1.dispose();
					    f1=f;
						t++;
						
//						KuCunJFrame f = new KuCunJFrame();
//						f.setVisible(true);
//						f1.dispose();
//						f1=f;
//						t++;
						
					}else{
						SearchJFrame f = new SearchJFrame();
						f.setVisible(true);
						f1=f;
						t++;
					}
				}
			});

		}
		return jButton5;
	}

	/**
	 * This method initializes jMenu6	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu6() {
		if (jMenu6 == null) {
			jMenu6 = new JMenu();
			jMenu6.setText("添加用户");
			jMenu6.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
//					TianJia tianjia=new TianJia();
//					tianjia.setVisible(true);
//					tianjia.setLocationRelativeTo(null);
				}
				
			});
		}
		return jMenu6;
	}

	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setBounds(new Rectangle(99, 154, 111, 38));
			jButton4.setBackground(Color.white);
			jButton4.setText("退库管理");
			jButton4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					if(t>0){
						TuiKuJFrame f=new TuiKuJFrame();
						f.setVisible(true);
						f1.dispose();
						f1=f;
						t++;
						
					}else{
						TuiKuJFrame f=new TuiKuJFrame();
						f.setVisible(true);
						f1=f;
						t++;
					}
				}
			});
		}
		return jButton4;
	}

	/**
	 * This method initializes jButton6	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setBounds(new Rectangle(175, 320, 114, 34));
			jButton6.setBackground(Color.white);
			jButton6.setText("退货管理");
			jButton6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(t>0){
						TuiHuoJFrame f=new TuiHuoJFrame();
						f.setVisible(true);
						f1.dispose();
						f1=f;
						t++;
						
					}else{
						TuiHuoJFrame f=new TuiHuoJFrame();
						f.setVisible(true);
						f1=f;
						t++;
					}
				}
			});
		}
		return jButton6;
	}

	/**
	 * This method initializes jButton7	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setBounds(new Rectangle(266, 425, 101, 37));
			jButton7.setText("用户管理");
			jButton7.setFont(new Font("Dialog", Font.BOLD, 14));
			jButton7.setBackground(Color.white);
			jButton7.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if(t>0){
						ResultSet rs=new GetRS().getResultSet("select * from tb_user_info where userName='"+user+"'");
						try {
							while(rs.next()){
								userbean.setUserName(rs.getString(2));
								userbean.setUserPower(rs.getInt(4));
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						if(userbean.getUserPower()==1 || userbean.getUserPower()==2){
							TianJia f=new TianJia(userbean.getUserName());
							f.setVisible(true);
							f1.dispose();
							f1=f;
							t++;
						}else{
							JOptionPane.showMessageDialog(null, "只有管理员才有此权限");
						}
						
					}else{
						ResultSet rs=new GetRS().getResultSet("select * from tb_user_info where userName='"+user+"'");
						try {
							while(rs.next()){
								userbean.setUserName(rs.getString(2));
								userbean.setUserPower(rs.getInt(4));
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						if(userbean.getUserPower()==1|| userbean.getUserPower()==2){
							TianJia f=new TianJia(userbean.getUserName());
							f.setVisible(true);
							f1=f;
							t++;
						}else{
							JOptionPane.showMessageDialog(null, "只有管理员才有此权限");
						
					}
						
					}
				}
			
			});
		}
		return jButton7;
	}
}  //  @jve:decl-index=0:visual-constraint="123,7"