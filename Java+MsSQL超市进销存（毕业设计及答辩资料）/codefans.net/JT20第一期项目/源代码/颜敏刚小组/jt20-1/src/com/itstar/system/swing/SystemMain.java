package com.itstar.system.swing;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JButton;

import com.itstar.in.InGoods;
import com.itstar.info.swing.GoodsInfo;
import com.itstar.info.swing.GuestInfo;
import com.itstar.info.swing.SeverInfo;
import com.itstar.pd.swing.Check;
import com.itstar.query.gysinfo.Gysinfo;
import com.itstar.query.ingood.Ingoods;
import com.itstar.query.swing.JGoodsQuery;
import com.itstar.query.swing.JGuestQuery;
import com.itstar.query.swing.JSalesQuery;
import com.itstar.sale.SaleGoods;

public class SystemMain extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar jJMenuBar = null;

	private JMenu jMenu = null;

	private JMenu jMenu1 = null;

	private JMenu jMenu2 = null;

	private JMenu jMenu3 = null;

	private JMenu jMenu4 = null;

	private JMenuItem jMenuItem = null;

	private JMenuItem jMenuItem1 = null;

	private JMenuItem jMenuItem2 = null;

	private JMenuItem jMenuItem3 = null;

	private JMenuItem jMenuItem4 = null;

	private JMenuItem jMenuItem5 = null;

	private JMenuItem jMenuItem7 = null;

	private JMenuItem jMenuItem8 = null;

	private JMenuItem jMenuItem9 = null;

	private JMenuItem jMenuItem10 = null;

	private JMenuItem jMenuItem11 = null;

	private JMenuItem jMenuItem12 = null;

	private JMenuItem jMenuItem13 = null;

	private JMenuItem jMenuItem15 = null;

	private JMenuItem jMenuItem16 = null;

	private JPanel jPanel = null;

	private JToolBar jJToolBarBar = null;

	private JButton jButton = null;

	private JButton jButton1 = null;

	private JButton jButton2 = null;

	private JButton jButton3 = null;

	private JButton jButton4 = null;

	private JButton jButton5 = null;

	private JButton jButton6 = null;

	private JButton jButton7 = null;

	private JButton jButton8 = null;

	private AddUsers au = null;

	private UpdatePwd up = null;

	private JLabel jLabel = null;
	
	private SeverInfo severinfo=null;
	
	private GoodsInfo goodsinfo=null;
	
	private GuestInfo guestinfo=null;
	
	private SaleGoods salegoods=null;
	
	private InGoods ingoods=null;
	
	private Check checkit=null;
	
	private Gysinfo gyscheck=null;
	
	private JGuestQuery guestquery=null;
	
	private JGoodsQuery goodsquery=null;
	
	private JSalesQuery salesquery=null;
	
	private Ingoods buygoods=null;

	/**
	 * This is the default constructor
	 */
	public SystemMain() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("进销存管理系统");
		this.setLocation(new Point(300, 100));
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJPanel());
		this.setResizable(false);
		this.setJMenuBar(getJJMenuBar());
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
			jJMenuBar.add(getJMenu4());
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
			jMenu = new JMenu("信息管理");
			jMenu.setPreferredSize(new Dimension(80, 25));
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem1());
			jMenu.add(getJMenuItem2());
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
			jMenu1 = new JMenu("业务管理");
			jMenu1.setPreferredSize(new Dimension(80, 25));
			jMenu1.add(getJMenuItem3());
			jMenu1.add(getJMenuItem4());
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
			jMenu2 = new JMenu("库存管理");
			jMenu2.setPreferredSize(new Dimension(80, 25));
			jMenu2.add(getJMenuItem5());
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
			jMenu3 = new JMenu("查询统计");
			jMenu3.setPreferredSize(new Dimension(80, 25));
			jMenu3.add(getJMenuItem7());
			jMenu3.add(getJMenuItem8());
			jMenu3.add(getJMenuItem9());
			jMenu3.add(getJMenuItem10());
			jMenu3.add(getJMenuItem11());
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
			jMenu4 = new JMenu("系统管理");
			jMenu4.setPreferredSize(new Dimension(80, 25));
			jMenu4.add(getJMenuItem12());
			jMenu4.add(getJMenuItem13());
			jMenu4.add(getJMenuItem15());
			jMenu4.add(getJMenuItem16());
		}
		return jMenu4;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem("供应商管理");
			jMenuItem.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/GysGuanLi.png")));
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(severinfo==null)
						severinfo=new SeverInfo();
					severinfo.setVisible(true);
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
			jMenuItem1 = new JMenuItem("商品管理");
			jMenuItem1.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/ShangPinGuanLi.png")));
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(goodsinfo==null)
					goodsinfo=new GoodsInfo();
					goodsinfo.setVisible(true);
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
			jMenuItem2 = new JMenuItem("客户管理");
			jMenuItem2.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/KeHuGuanLi.png")));
			jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				if(guestinfo==null)	
					guestinfo=	new GuestInfo();
				guestinfo.setVisible(true);
				}
			});
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
			jMenuItem3 = new JMenuItem("进货单  ");
			jMenuItem3.setPreferredSize(new Dimension(93, 30));
			jMenuItem3.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/JinHuoDan.png")));
			jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(ingoods==null)
						ingoods=new InGoods();
						ingoods.setVisible(true);
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
			jMenuItem4 = new JMenuItem("销售单");
			jMenuItem4.setPreferredSize(new Dimension(93, 30));
			jMenuItem4.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/XiaoShouDan.png")));
			jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(salegoods==null)
					salegoods= new	SaleGoods();
					salegoods.setVisible(true);
				}
			});
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
			jMenuItem5 = new JMenuItem("库存盘点");
			jMenuItem5.setPreferredSize(new Dimension(110, 30));
			jMenuItem5.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/KuCunPanDian.png")));
			jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(checkit==null)
						checkit=new Check();
					checkit.setVisible(true);
				}
			});
		}
		return jMenuItem5;
	}

	/**
	 * This method initializes jMenuItem7
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem7() {
		if (jMenuItem7 == null) {
			jMenuItem7 = new JMenuItem("客户查询");
			jMenuItem7.setPreferredSize(new Dimension(93, 30));
			jMenuItem7.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/KeHuChaXun.png")));
			jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(guestquery==null)
						guestquery=new JGuestQuery();
					guestquery.setVisible(true);
				}
			});
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
			jMenuItem8 = new JMenuItem("商品查询");
			jMenuItem8.setPreferredSize(new Dimension(93, 30));
			jMenuItem8.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/ShangPinChaXun.png")));
			jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(goodsquery==null)
						goodsquery=new JGoodsQuery();
						goodsquery.setVisible(true);
				}
			});
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
			jMenuItem9 = new JMenuItem("供应商查询");
			jMenuItem9.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/GongYingShangChaXun.png")));
			jMenuItem9.setPreferredSize(new Dimension(129, 30));
			jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(gyscheck==null)
						gyscheck=new Gysinfo();
					gyscheck.setVisible(true);
				}
			});
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
			jMenuItem10 = new JMenuItem("销售查询");
			jMenuItem10.setPreferredSize(new Dimension(93, 30));
			jMenuItem10.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/XiaoShouChaXun.png")));
			jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(salesquery==null)
						salesquery=new JSalesQuery();
					salesquery.setVisible(true);
				}
			});
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
			jMenuItem11 = new JMenuItem("入库查询");
			jMenuItem11.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/RuKuChaXun.png")));
			jMenuItem11.setPreferredSize(new Dimension(129, 30));
			jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(buygoods==null)
						buygoods=new Ingoods();
					buygoods.setVisible(true);
				}
			});
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
			jMenuItem12 = new JMenuItem("系统用户管理");
			jMenuItem12.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/CzyGL.png")));
			jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (au == null) {
						au = new AddUsers();
					}
					au.setVisible(true);
				}
			});
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
			jMenuItem13 = new JMenuItem("更改用户密码");
			jMenuItem13.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/GengGaiMiMa.png")));
			jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (up == null) {
						up = new UpdatePwd();
					}
					up.setVisible(true);
				}
			});
		}
		return jMenuItem13;
	}

	/**
	 * This method initializes jMenuItem15
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem15() {
		if (jMenuItem15 == null) {
			jMenuItem15 = new JMenuItem("关于版本");
			jMenuItem15.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/aboutIcon.png")));
			jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(1);

					URL url = getClass().getResource("/com/image/aboutversion.png");
					ImageIcon icon = new ImageIcon(url); // 关于对话框的图片
					// 创建新的面板
					final JPanel panel = new JPanel(new BorderLayout());
					panel.setOpaque(false); // 设置面板透明
					JLabel label = new JLabel(icon); // 用关于对话框的图片创建标签
					panel.add(label, BorderLayout.CENTER); // 把标签添加到面板中间
					SystemMain.this.setGlassPane(panel); // 把面板设置为玻璃面板
					panel.setVisible(true); // 显示面板
					panel.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							panel.setVisible(false); // 单击隐藏该面板
						}
					});
				
				}

			});
		}
		return jMenuItem15;
	}

	/**
	 * This method initializes jMenuItem16
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getJMenuItem16() {
		if (jMenuItem16 == null) {
			jMenuItem16 = new JMenuItem("退出系统");
			jMenuItem16.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/exitIcon.png")));
			jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if ((JOptionPane.showConfirmDialog(null, "退出", "退出",
							JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION)
						System.exit(0);
				}
			});
		}
		return jMenuItem16;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setIcon(new ImageIcon(getClass().getResource("/com/image/background.PNG")));
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.add(getJJToolBarBar(), BorderLayout.NORTH);
			jPanel.add(jLabel, BorderLayout.CENTER);
		}
		return jPanel;
	}

	/**
	 * This method initializes jJToolBarBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJJToolBarBar() {
		if (jJToolBarBar == null) {
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setFloatable(false);
			jJToolBarBar.add(getJButton());
			jJToolBarBar.add(getJButton1());
			jJToolBarBar.add(getJButton2());
			jJToolBarBar.add(getJButton3());
			jJToolBarBar.add(getJButton4());
			jJToolBarBar.add(getJButton5());
			jJToolBarBar.add(getJButton6());
			jJToolBarBar.add(getJButton7());
			jJToolBarBar.add(getJButton8());
		}
		return jJToolBarBar;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setPreferredSize(new Dimension(1, 1));

			jButton.setHorizontalTextPosition(0);
			jButton.setBounds(new Rectangle(2, 2, 50, 50));
			jButton.setToolTipText("商品管理");
			jButton.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/ShangPinGuanLi.png")));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(severinfo==null)
						severinfo=new SeverInfo();
					severinfo.setVisible(true);
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
			jButton1.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/KeHuGuanLi.png")));
			jButton1.setToolTipText("客户管理");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(guestinfo==null)	
						guestinfo=	new GuestInfo();
					guestinfo.setVisible(true);
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
			jButton2.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/KeHuChaXun.png")));
			jButton2.setToolTipText("客户查询");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(guestquery==null)
						guestquery=new JGuestQuery();
					guestquery.setVisible(true);
					
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
			jButton3.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/ShangPinChaXun.png")));
			jButton3.setToolTipText("商品查询");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(goodsquery==null)
					goodsquery=new JGoodsQuery();
					goodsquery.setVisible(true);
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
			jButton4 = new JButton();
			jButton4.setToolTipText("入库查询");
			jButton4.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/RuKuChaXun.png")));
			jButton4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(buygoods==null)
						buygoods=new Ingoods();
					buygoods.setVisible(true);
				}
			});
		}
		return jButton4;
	}

	/**
	 * This method initializes jButton5
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setToolTipText("销售查询");
			jButton5.setIcon(new ImageIcon(getClass().getResource("/com/image/XiaoShouChaXun.png")));
			jButton5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(salesquery==null)
						salesquery=new JSalesQuery();
					salesquery.setVisible(true);
				}
			});
		}
		return jButton5;
	}

	/**
	 * This method initializes jButton6
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setIcon(new ImageIcon(getClass().getResource("/com/image/JinHuoDan.png")));
			jButton6.setToolTipText("进货单");
			jButton6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(ingoods==null)
						ingoods=new InGoods();
						ingoods.setVisible(true);
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
			jButton7.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/XiaoShouDan.png")));
			jButton7.setToolTipText("销售单");
			jButton7.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(salegoods==null)
						salegoods= new	SaleGoods();
						salegoods.setVisible(true);
				
				}
			
			});
		}
		return jButton7;
	}

	/**
	 * This method initializes jButton8
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton8() {
		if (jButton8 == null) {
			jButton8 = new JButton();
			jButton8.setIcon(new ImageIcon(getClass().getResource(
					"/com/image/exitIcon.png")));
			jButton8.setToolTipText("退出系统");
			jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if ((JOptionPane.showConfirmDialog(null, "退出", "退出",
							JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION)
						System.exit(0);
				}
			});
		}
		return jButton8;
	}

} // @jve:decl-index=0:visual-constraint="0,5"
//
//class About extends JPanel {
//
//	private static final long serialVersionUID = 1L;
//	private JLabel jLabel = null;
//
//	/**
//	 * This is the default constructor
//	 */
//	public About() {
//		super();
//		initialize();
//		final About about=this;
//		jLabel.addMouseListener(new java.awt.event.MouseAdapter() {
//			public void mouseClicked(java.awt.event.MouseEvent e) {
//				about.setVisible(false);
//			}
//		});
//	}
//
//	/**
//	 * This method initializes this
//	 * 
//	 * @return void
//	 */
//	private void initialize() {
////		ImageIcon icon = new ImageIcon(url);
//		jLabel = new JLabel();
//		jLabel.setBounds(new Rectangle(0, 0, 380, 250));
//		jLabel.setIcon(new ImageIcon(getClass().getResource("/com/image/aboutversion.PNG")));
//		jLabel.setText("JLabel");
//		this.setSize(380, 250);
//		this.setLayout(null);
//		this.setVisible(true);
//		this.add(jLabel, null);
//	}
//
//}
