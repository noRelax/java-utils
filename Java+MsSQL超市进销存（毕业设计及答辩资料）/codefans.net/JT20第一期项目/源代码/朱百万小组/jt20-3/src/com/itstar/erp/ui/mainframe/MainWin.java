package com.itstar.erp.ui.mainframe;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.itstar.erp.basicmessage.WareClient;
import com.itstar.erp.basicmessage.WareManufacturer;
import com.itstar.erp.basicmessage.WareRun;
import com.itstar.erp.dao.Admin;
import com.itstar.erp.function.Fuction;
import com.itstar.erp.function.Version;
import com.itstar.erp.instock.InStockUI;
//import com.itstar.erp.instock.Reback;
import com.itstar.erp.manager.AddManager;
import com.itstar.erp.manager.DelManager;
import com.itstar.erp.count.ShowClient;
import com.itstar.erp.count.ShowGoods;
import com.itstar.erp.count.ShowManufacturer;
import com.itstar.erp.count.ShowOutStock;
import com.itstar.erp.count.ShowStock;
//import com.itstar.erp.sale.Mingci;
import com.itstar.erp.sale.SaleOperateUI;
import com.itstar.erp.sale.ViewPanelUi;
import com.itstar.erp.ui.listener.TimeListener;

public class MainWin  extends  JFrame {
    protected static final Admin Admin = null;
	private JPanel jContentPane = null;
	JMenu mmuFormat = null;
	private Admin adminBean; // @jve:decl-index=0:
	private JMenuItem Query_5 = null;

	public Admin getAdminBean() {
		return adminBean;
	}

	public void setAdminBean(Admin adminBean) {
		this.adminBean = adminBean;
	}

	public MainWin() {
		initalize();
		this.setResizable(false);
		this.setVisible(true);
		this.adminBean = adminBean;
	}

	private void initalize() {
		this.setSize(750, 700);
		this.setLocation(230, 20);
		this.setContentPane(getJContentPane());
		jContentPane.setLayout(null);
		this.setTitle("华夏英才软件工程20班第3组");
		addControls();
	}

	void addControls() {
		JMenuBar jmb = new JMenuBar();
		jmb.setSize(800, 35);
		jContentPane.add(jmb, null);
		
		JMenu file = new JMenu("文件");
		JMenuItem file_1=new JMenuItem("保存");

		file_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog (jContentPane, "程序已保存!");
			}
			
		});
		JMenuItem file_2 = new JMenuItem("退出");
		
		file_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int result = JOptionPane.showConfirmDialog(null, 
						"您真的要退出程序吗?", "请确认", JOptionPane.YES_NO_OPTION);		
					if(result == JOptionPane.OK_OPTION){
						System.exit(0);	
			}
		}
		});
		jmb.add(file, null);
		file.add(file_1);
		file.add(file_2);
		
		file.addSeparator();
		
		JMenu Basic = new JMenu("管理部");
		JMenuItem Basic_1=new JMenuItem("商品管理");
		Basic_1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new WareRun();
			}
		});
		JMenuItem Basic_2=new JMenuItem("供应商管理");
		Basic_2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new WareManufacturer();
			}
		});
		JMenuItem Basic_3=new JMenuItem("顾客管理");
		Basic_3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new WareClient();
			}
		});
		Basic.add(Basic_1);
		Basic.add(Basic_2);
		Basic.add(Basic_3);
		jmb.add(Basic);
		
		JMenu Entry=new JMenu("采购部");
		JMenuItem ens_1=new JMenuItem("进货");
		ens_1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				new InStockUI();
			}
		});
		JMenuItem ens_2=new JMenuItem("退货");
		ens_2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
//				new Reback();
			}
		});
		
		Entry.add(ens_1);
		Entry.add(ens_2);
		jmb.add(Entry);
		
		JMenu Sale = new JMenu("销售管理");
		
		JMenuItem Sale_1 = new JMenuItem("执行销售");
		Sale_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new SaleOperateUI();
			}
		});
		
		JMenuItem Sale_2 = new JMenuItem("销售员排行榜");
		Sale_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
//				new Mingci();
			}
		});
		
		
		JMenuItem Sale_4 = new JMenuItem("销售看板");
		Sale_4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//new ViewPanelUi();
			}
		});
		
		jmb.add(Sale, null);
		Sale.add(Sale_1);
		Sale.add(Sale_2);
		Sale.add(Sale_4);

		JMenu Query = new JMenu("信息部");
			JMenuItem Query_1 = new JMenuItem("商品信息查询");
			Query_1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new ShowGoods();
				}
			});
			JMenuItem Query_2 = new JMenuItem("顾客信息查询");
			Query_2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                              new ShowClient();
				}
			});
			JMenuItem Query_3 = new JMenuItem("供应商信息查询");
			Query_3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				  new ShowManufacturer();
				}
			});
			JMenuItem Query_4 = new JMenuItem("库存信息查询");
			Query_4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new ShowStock();
				}
			});
			Query.add(Query_1);
			Query.add(Query_2);
			Query.add(Query_3);
			Query.add(Query_4);
			Query.add(getQuery_5());
			jmb.add(Query);

		
		JMenu Sys = new JMenu("系统管理");
		Sys.setSize(50, 35);
		
		JMenuItem Sys_1 = new JMenuItem("密码修改");
		Sys_1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				new com.itstar.erp.manager.Modify(adminBean);
				}
	});
		
		JMenuItem Sys_2= new JMenuItem("添加管理员");
		Sys_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 new AddManager();    
				 }
		});
		
		JMenuItem Sys_3 = new JMenuItem("删除管理员");
		Sys_3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
   	           new DelManager();
   	          }
	});
		Sys.setSize(50, 35);
		jmb.add(Sys);
		Sys.add(Sys_1);
		Sys.add(Sys_2);
		Sys.add(Sys_3);

		JMenu Help = new JMenu("帮     助");
		JMenuItem Help_1 = new JMenuItem("功能描述");
		 Help_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Fuction();
			}
		});
		JMenuItem Help_2 = new JMenuItem("版本所有");
		Help_2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			  new Version();
		}	
		});
		jmb.add(Help);
		Help.add(Help_1);
		Help.add(Help_2);
		
		JLabel clock = new JLabel();
		TimeListener m1 = new  TimeListener(clock);
		Timer t1 = new Timer(1000, m1);
		t1.setInitialDelay(0);
		t1.start();
		clock.setFont(new Font("Dialog", Font.BOLD, 13));
		clock.setBounds(new Rectangle(140, 600, 350, 30));
		jContentPane.add(clock, null);

		ImageIcon img = new ImageIcon("src/com/itstar/erp/ui/mainframe/MainWin.jpg");
		JLabel backlabel = new JLabel(img);
		backlabel.setBounds(new Rectangle(0, 0, 750, 700));

		jContentPane.add(backlabel, null);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
		}
		return jContentPane;
	}

	/**
	 * This method initializes Query_5	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getQuery_5() {
		if (Query_5 == null) {
			Query_5 = new JMenuItem("销售记录");
			Query_5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new ShowOutStock();
				}
			});
		}
		return Query_5;
	}
}
