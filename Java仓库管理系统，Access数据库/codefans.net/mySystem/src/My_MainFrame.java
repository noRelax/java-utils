package src;
//Download by http://www.codefans.net 
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Vector;

public class My_MainFrame {
	private final static javax.swing.ImageIcon icon = new javax.swing.ImageIcon("res/logo1.jpg");
	private JFrame frame = new javax.swing.JFrame();
	private JMenuBar menuBar = null;
	private JToolBar toolBar = null;
	private JSplitPane splitPane = null;
	private JScrollPane scrollPane = null;
	private JPanel panel = null;
	private JTabbedPane tabbedPane = new javax.swing.JTabbedPane();
	private JButton stuff_in = null;//原料进入
	private JButton product_in = null;//产品进入
	private JButton stuff_quit = null;//原料退料
	private JButton stuff_stock = null;//原料库存
	private JButton product_stock = null;//产品库存
	private JButton yield_draw = null;//生产领料
	//private JButton yield_consume = null;//生产发料
	private JButton yield_off = null;//生产退料
	private JButton yield_scrap = null;//生产废料
	//private JButton old_record = null;//仓库记录
	private JButton product_out = null;//产品输出
	private JButton product_return = null;//产品返修
	private JButton product_cancle = null;//产品退货
	private JButton handle_record = null;//操作记录
	private JButton flotsam_record = null;//废料记录
	private JButton product_out_record = null;//出货记录
	private JButton stuff_old_record = null;//原料记录
	private JButton product_old_record = null;//成品记录
	private JButton help = null;//帮助
	private JButton about = null;//关于
	private JButton exit_system = null;//退出系统
	private String sql = null;
	
	
	public My_MainFrame() {
		// TODO 自动生成构造函数存根
	}
	public My_MainFrame(String name){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception exe){System.err.print(exe.getMessage());}
		java.awt.Toolkit tool = frame.getToolkit();
		Image image = tool.getImage("res/logo.jpg");
		java.awt.Dimension dimn = tool.getScreenSize();
		String title = "仓库管理系统--"+name;
		frame.setTitle(title);
		frame.setIconImage(image);
		frame.setFocusable(true);
		frame.setLayout(new java.awt.BorderLayout());
		frame.setJMenuBar(createJMenuBar());
		frame.add(createJToolBar(),"North");
		frame.add(createSplitPane(),"Center");
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent e){}
			//因对窗口调用dispose()而将其关闭时调用
			public void windowClosed(WindowEvent e){}
			//用户试图从窗口的系统菜单中关闭窗口时调用
			public void windowClosing(WindowEvent e){
				String name = frame.getTitle().trim().substring(8);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(System.currentTimeMillis());
				String day = sdf.format(date);
				String s ="'"+day+"','"+name+"','"+"退出"+"'";
				String sql = "insert into handle_record values("+s+")";
				USeDB.UpdateDB(sql);
				frame.dispose();
				System.exit(0);
			}
			//当窗口不再是活动窗口时调用
			public void windowDeactivated(WindowEvent e){}
			//窗口从最小化状态变为正常状态时调用
			public void windowDeiconified(WindowEvent e){}
			//窗口从正常状态变为最小状态时调用
			public void windowIconified(WindowEvent e){}
			//窗口首次变为可见时调用
			public void windowOpened(WindowEvent e){}
		});
		//frame.setSize(dimn);
		frame.setPreferredSize(dimn);
		frame.setBackground(java.awt.Color.pink);
		frame.pack();
		frame.validate();
		frame.setVisible(true);
	}
	private JMenu createSystem_Manage_Menu(){
		javax.swing.JMenu systemMenu = new javax.swing.JMenu("系统管理");
		javax.swing.JMenuItem logoin = new javax.swing.JMenuItem("注      册");
		logoin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new AddUser();
			}
		});
		javax.swing.JMenuItem logoout = new javax.swing.JMenuItem("删除用户");
		logoout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String jp = new JOptionPane().showInputDialog(frame,"请输入要删除的用户名！","输入用户名",JOptionPane.YES_NO_CANCEL_OPTION).trim();
				if(USeDB.delUser(jp)){
					JOptionPane.showConfirmDialog(null, "用户删除成功!");
				}
				JOptionPane.showConfirmDialog(null, "用户删除失败!");
			}
		});
		javax.swing.JMenuItem datatidy = new javax.swing.JMenuItem("数据整理");
		logoin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		systemMenu.add(logoin);
		systemMenu.add(logoout);
		systemMenu.add(datatidy);
		return systemMenu;
	}
	private JMenu createStuff_Manage_Menu()
	{
		javax.swing.JMenu stuffMenu = new javax.swing.JMenu("原料管理");
		javax.swing.JMenuItem stuff_in = new javax.swing.JMenuItem("原料进入");
		stuff_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				AddStuffIn("原料进入");
			}
		});
		javax.swing.JMenuItem stuff_quit = new javax.swing.JMenuItem("原料退还");
		stuff_quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e2){
				AddStuffQuit("原料退还");
			}
		});
		javax.swing.JMenuItem stuff_stock = new javax.swing.JMenuItem("原料库存");
		stuff_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e3){
				String tit[] = {"原料编号","原料名","生产商","收货员","检验员","单位","数量","规格","颜色","存放处","时间","备注"};
				String sql = "select * from stuff_in";
				AddTable("原料库存",tit,sql);
			}
		});
		javax.swing.JMenuItem yield_draw = new javax.swing.JMenuItem("生产开单");
		yield_draw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e4){
				AddYieldDraw("生产开单");
			}
		});
		javax.swing.JMenuItem yield_off = new javax.swing.JMenuItem("生产退料");
		yield_off.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e4){
				AddStuffIn("生产退料");
			}
		});
		javax.swing.JMenuItem yield_scrap = new javax.swing.JMenuItem("生产废料");
		yield_scrap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e5){
				AddYieldScrap("生产废料");
			}
		});
		stuffMenu.add(stuff_in);
		stuffMenu.add(stuff_quit);
		stuffMenu.addSeparator();
		stuffMenu.add(stuff_stock);
		stuffMenu.add(yield_draw);
		stuffMenu.addSeparator();
		stuffMenu.add(yield_off);
		stuffMenu.add(yield_scrap);
		return stuffMenu;
	}
	private JMenu createProduct_Manage_Menu(){
		javax.swing.JMenu productMenu = new javax.swing.JMenu("成品管理");
		javax.swing.JMenuItem product_in = new javax.swing.JMenuItem("成品进入");
		product_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e6){
				AddProductIn("成品进入");
			}
		});
		javax.swing.JMenuItem product_stock = new javax.swing.JMenuItem("成品库存");
		product_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e7){
				String tit[] ={"成品编号","成品名","客户","成品规格","单位","数量","生产单位","入库时间","备注"};
				/*Vector ve = new Vector();
				ve.add("成品编号");
				ve.add("成品名");
				ve.add("客户");
				ve.add("成品规格");
				ve.add("单位");
				ve.add("数量");
				ve.add("生产单位");
				ve.add("入库时间");
				ve.add("备注");*/
				String sql = "select * from product_in";
				AddTable("成品库存",tit,sql);
			}
		});
		javax.swing.JMenuItem product_out = new javax.swing.JMenuItem("成品发货");
		product_out.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e6){
				AddProductOut("成品发货");
			}
		});
		javax.swing.JMenuItem product_return = new javax.swing.JMenuItem("成品返修");
		product_return.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e6){
				AddProductIn("成品返修");
			}
		});
		javax.swing.JMenuItem product_cancle = new javax.swing.JMenuItem("成品退货");
		product_cancle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e6){
				AddProductIn("成品退货");
			}
		});
		productMenu.add(product_in);
		productMenu.add(product_stock);
		productMenu.addSeparator();
		productMenu.add(product_out);
		productMenu.add(product_return);
		productMenu.addSeparator();
		productMenu.add(product_cancle);
		return productMenu;
	}
	private JMenu createStroeroom_Manage_Stock(){
		javax.swing.JMenu stockMenu = new javax.swing.JMenu("记录管理");
		javax.swing.JMenuItem handle_record = new javax.swing.JMenuItem("操作记录");
		handle_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from handle_record";
				String title[] = {"操作编号","操作人员","开始时间","离开时间","操作备注"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("操作编号");
				v.add("操作人员");
				v.add("开始时间");
				v.add("离开时间");
				v.add("操作备注");*/
				AddTable("操作记录",title,sql);
			}
		});
		javax.swing.JMenuItem flotsam_record = new javax.swing.JMenuItem("废料记录");
		flotsam_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from scrap_record";
				String title[] = {"废料编号","操作人员","处理时间","处理备注"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("废料编号");
				v.add("操作人员");
				v.add("处理时间");
				v.add("处理备注");*/
				AddTable("废料记录",title,sql);
			}
		});
		javax.swing.JMenuItem product_out_record = new javax.swing.JMenuItem("出货记录");
		product_out_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from product_out";
				String title[] = {"产品编号","客户名称","产品名称","产品规格","产品单位","产品数量","生产单位","出货时间","备注事项"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("产品编号");
				v.add("客户名称");
				v.add("产品名称");
				v.add("产品规格");
				v.add("产品单位");
				v.add("产品数量");
				v.add("生产单位");
				v.add("出货时间");
				v.add("备注事项");*/
				AddTable("出货记录",title,sql);
			}
		});
		javax.swing.JMenuItem stuff_old_record = new javax.swing.JMenuItem("原料记录");
		stuff_old_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from stuff_old_record";
				String ti[] = {"原料编号","原料名","收货员","收货员","检验员","单位","数量","规格","颜色","存放处","时间","备注"};
				/*java.util.Vector ve = new java.util.Vector();
				ve.add("原料编号");
				ve.add("原料名");
				ve.add("收货员");
				ve.add("检验员");
				ve.add("单位");
				ve.add("数量");
				ve.add("规格");
				ve.add("规格");
				ve.add("颜色");
				ve.add("存放处");
				ve.add("时间");
				ve.add("备注");*/
				AddTable("原料记录",ti,sql);
			}
		});
		javax.swing.JMenuItem product_old_record = new javax.swing.JMenuItem("成品记录");
		product_old_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from product_old_record";
				String title[] = {"成品编号","成品名","客户","成品规格","单位","数量","生产单位","入库时间","备注"};
				/*java.util.Vector ve = new java.util.Vector();
				ve.add("成品编号");
				ve.add("成品名");
				ve.add("客户");
				ve.add("成品规格");
				ve.add("单位");
				ve.add("数量");
				ve.add("生产单位");
				ve.add("入库时间");
				ve.add("备注");*/
				AddTable("成品记录",title,sql);
			}
		});
		stockMenu.add(handle_record);
		stockMenu.add(flotsam_record);
		stockMenu.addSeparator();
		stockMenu.add(product_out_record);
		stockMenu.add(stuff_old_record);
		stockMenu.addSeparator();
		stockMenu.add(product_old_record);
		return stockMenu;
	}
	private JMenu createHelp_Menu(){
		javax.swing.JMenu helpMenu = new javax.swing.JMenu("帮助");
		javax.swing.JMenuItem help = new javax.swing.JMenuItem("帮助");
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddHelp();
			}
		});
		javax.swing.JMenuItem about = new javax.swing.JMenuItem("关于");
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new UserAbout();
			}
		});
		helpMenu.add(help);
		helpMenu.add(about);
		return helpMenu;
	}
	private JMenuBar createJMenuBar(){
		menuBar = new javax.swing.JMenuBar(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				if(!isOpaque()){return;}
				Graphics2D g2d = (Graphics2D) g;
				//int rule = AlphaComposite.SRC_OVER;
				AlphaComposite opaque = AlphaComposite.SrcOver;
				//AlphaComposite blend = AlphaComposite.getInstance(rule, 0.6f);
				//AlphaComposite set = AlphaComposite.Src;
				int width = getWidth();
				int height = getHeight();
				GradientPaint gradientPaint = new GradientPaint(0,0,java.awt.Color.green,width/2,height/2,java.awt.Color.yellow,false);
				g2d.setComposite(opaque);
				g2d.setPaint(gradientPaint);
				g2d.fillRect(0, 0, width,height);
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
			}
		};
		menuBar.add(createSystem_Manage_Menu());
		menuBar.add(createStuff_Manage_Menu());
		menuBar.add(createProduct_Manage_Menu());
		menuBar.add(createStroeroom_Manage_Stock());
		menuBar.add(createHelp_Menu());
		menuBar.setBackground(java.awt.Color.pink);
		return menuBar;
	}
	private JToolBar createJToolBar(){
		toolBar = new javax.swing.JToolBar(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				if(!isOpaque()){return;}
				Graphics2D g2d = (Graphics2D) g;
				//int rule = AlphaComposite.SRC_OVER;
				AlphaComposite opaque = AlphaComposite.SrcOver;
				//AlphaComposite blend = AlphaComposite.getInstance(rule, 0.6f);
				//AlphaComposite set = AlphaComposite.Src;
				int width = getWidth();
				int height = getHeight();
				GradientPaint gradientPaint = new GradientPaint(0,0,java.awt.Color.green,width/2,height/2,java.awt.Color.yellow,false);
				g2d.setComposite(opaque);
				g2d.setPaint(gradientPaint);
				g2d.fillRect(0, 0, width,height);
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
			}
		};
		
		stuff_in = new javax.swing.JButton("原料进入");
		stuff_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddStuffIn("原料进入");
			}
		});
		stuff_quit = new javax.swing.JButton("原料退料");
		stuff_quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddStuffQuit("原料退还");
			}
		});
		stuff_stock = new javax.swing.JButton("原料库存");
		stuff_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tit[] = {"原料编号","原料名","生产商","收货员","检验员","单位","数量","规格","颜色","存放处","时间","备注"};
				String sql = "select * from stuff_in";
				AddTable("原料库存",tit,sql);
			}
		});
		yield_draw = new javax.swing.JButton("生产开单");
		yield_draw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddYieldDraw("生产开单");
			}
		});
		yield_off = new javax.swing.JButton("生产退料");
		yield_off.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddStuffIn("生产退料");
			}
		});
		yield_scrap = new javax.swing.JButton("生产废料");
		yield_scrap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddYieldScrap("生产废料");
			}
		});
		product_in = new javax.swing.JButton("成品进入");
		product_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("成品进入");
			}
		});
		product_stock = new javax.swing.JButton("成品库存");
		product_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tit[] ={"成品编号","成品名","客户","成品规格","单位","数量","生产单位","入库时间","备注"};
				/*Vector ve = new Vector();
				ve.add("成品编号");
				ve.add("成品名");
				ve.add("客户");
				ve.add("成品规格");
				ve.add("单位");
				ve.add("数量");
				ve.add("生产单位");
				ve.add("入库时间");
				ve.add("备注");*/
				String sql = "select * from product_in";
				AddTable("成品库存",tit,sql);
			}
		});
		product_out = new javax.swing.JButton("成品发货");
		product_out.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductOut("成品发货");
			}
		});
		product_return = new javax.swing.JButton("成品返修");
		product_return.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("成品返修");
			}
		});
		product_cancle = new javax.swing.JButton("成品退货");
		product_cancle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("成品退货");
			}
		});
		handle_record = new javax.swing.JButton("操作记录");
		handle_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from handle_record";
				String title[] = {"操作时间","操作用户","操作备注"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("操作编号");
				v.add("操作人员");
				v.add("开始时间");
				v.add("离开时间");
				v.add("操作备注");*/
				AddTable("操作记录",title,sql);
			}
		});
		flotsam_record = new javax.swing.JButton("废料记录");
		flotsam_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from scrap_record";
				String title[] = {"废料编号","操作人员","处理时间","处理备注"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("废料编号");
				v.add("操作人员");
				v.add("处理时间");
				v.add("处理备注");*/
				AddTable("废料记录",title,sql);
			}
		});
		product_out_record = new javax.swing.JButton("出货记录");
		product_out_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from product_out";
				String title[] = {"产品编号","客户名称","产品名称","产品规格","产品单位","产品数量","生产单位","出货时间","备注事项"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("产品编号");
				v.add("客户名称");
				v.add("产品名称");
				v.add("产品规格");
				v.add("产品单位");
				v.add("产品数量");
				v.add("生产单位");
				v.add("出货时间");
				v.add("备注事项");*/
				AddTable("出货记录",title,sql);
			}
		});
		help = new javax.swing.JButton("帮     助");
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddHelp();
			}
		});
		about = new javax.swing.JButton("关     于");
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new UserAbout();
			}
		});
		exit_system = new javax.swing.JButton("退出系统");
		exit_system.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String name = frame.getTitle().trim().substring(8);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(System.currentTimeMillis());
				String day = sdf.format(date);
				String s ="'"+day+"','"+name+"','"+"退出"+"'";
				String sql = "insert into handle_record values("+s+")";
				USeDB.UpdateDB(sql);
				frame.dispose();
				System.exit(0);
			}
		});
		toolBar.add(stuff_in);
		toolBar.addSeparator();
		toolBar.add(stuff_quit);
		toolBar.addSeparator();
		toolBar.add(stuff_stock);
		toolBar.addSeparator();
		toolBar.add(yield_draw);
		toolBar.addSeparator();
		toolBar.add(yield_off);
		toolBar.addSeparator();
		toolBar.add(yield_scrap);
		toolBar.addSeparator();
		toolBar.add(product_in);
		toolBar.addSeparator();
		toolBar.add(product_stock);
		toolBar.addSeparator();
		toolBar.add(product_out);
		toolBar.addSeparator();
		toolBar.add(product_return);
		toolBar.addSeparator();
		toolBar.add(product_cancle);
		toolBar.addSeparator();
		toolBar.add(handle_record);
		toolBar.addSeparator();
		toolBar.add(flotsam_record);
		toolBar.addSeparator();
		toolBar.add(product_out_record);
		toolBar.addSeparator();
		toolBar.add(help);
		toolBar.addSeparator();
		toolBar.add(about);
		toolBar.addSeparator();
		toolBar.add(exit_system);
		toolBar.setBackground(java.awt.Color.pink);
		return toolBar;
	}
	private JScrollPane createScrollPane(){
		panel = new javax.swing.JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				if(!isOpaque()){return;}
				Graphics2D g2d = (Graphics2D) g;
				//int rule = AlphaComposite.SRC_OVER;
				AlphaComposite opaque = AlphaComposite.SrcOver;
				//AlphaComposite blend = AlphaComposite.getInstance(rule, 0.6f);
				//AlphaComposite set = AlphaComposite.Src;
				int width = getWidth();
				int height = getHeight();
				GradientPaint gradientPaint = new GradientPaint(0,0,java.awt.Color.green,width/2,height/2,java.awt.Color.yellow,false);
				g2d.setComposite(opaque);
				g2d.setPaint(gradientPaint);
				g2d.fillRect(0, 0, width,height);
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
			}
		};
		panel.setLayout(new javax.swing.BoxLayout(panel,BoxLayout.Y_AXIS ));
		javax.swing.ImageIcon image6 = new javax.swing.ImageIcon("res/stuffin.jpg");
		stuff_in = new javax.swing.JButton(image6);
		stuff_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddStuffIn("原料进入");
			}
		});
		javax.swing.ImageIcon image7 = new javax.swing.ImageIcon("res/stuffquit.jpg");
		stuff_quit = new javax.swing.JButton(image7);
		stuff_quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddStuffQuit("原料退还");
			}
		});
		javax.swing.ImageIcon image5 = new javax.swing.ImageIcon("res/stuffstock.jpg");
		stuff_stock = new javax.swing.JButton(image5);
		stuff_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tit[] = {"原料编号","原料名","原料名","收货员","检验员","单位","数量","规格","颜色","存放处","时间","备注"};
				/*Vector ve = new Vector();
				ve.add("原料编号");
				ve.add("原料名");
				ve.add("收货员");
				ve.add("检验员");
				ve.add("单位");
				ve.add("数量");
				ve.add("规格");
				ve.add("规格");
				ve.add("颜色");
				ve.add("存放处");
				ve.add("时间");
				ve.add("备注");
				*/
				String sql = "select * from stuff_in";
				AddTable("原料库存",tit,sql);
			}
		});
		javax.swing.ImageIcon image8 = new javax.swing.ImageIcon("res/yielddraw.jpg");
		yield_draw = new javax.swing.JButton(image8);
		yield_draw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddYieldDraw("生产开单");
			}
		});
		javax.swing.ImageIcon image9 = new javax.swing.ImageIcon("res/yield_off.jpg");
		yield_off = new javax.swing.JButton(image9);
		yield_off.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddStuffIn("生产退料");
			}
		});
		javax.swing.ImageIcon image10 = new javax.swing.ImageIcon("res/yieldscrap.jpg");
		yield_scrap = new javax.swing.JButton(image10);
		yield_scrap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddYieldScrap("生产废料");
			}
		});
		javax.swing.ImageIcon image11 = new javax.swing.ImageIcon("res/productin.jpg");
		product_in = new javax.swing.JButton(image11);
		product_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("成品进入");
			}
		});
		javax.swing.ImageIcon image12 = new javax.swing.ImageIcon("res/productstock.jpg");
		product_stock = new javax.swing.JButton(image12);
		product_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tit[] ={"成品编号","成品名","客户","成品规格","单位","数量","生产单位","入库时间","备注"};
				/*Vector ve = new Vector();
				ve.add("成品编号");
				ve.add("成品名");
				ve.add("客户");
				ve.add("成品规格");
				ve.add("单位");
				ve.add("数量");
				ve.add("生产单位");
				ve.add("入库时间");
				ve.add("备注");
				*/
				String sql = "select * from product_in";
				AddTable("成品库存",tit,sql);
			}
		});
		javax.swing.ImageIcon image13 = new javax.swing.ImageIcon("res/productout.jpg");
		product_out = new javax.swing.JButton(image13);
		product_out.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductOut("成品发货");
			}
		});
		javax.swing.ImageIcon image14 = new javax.swing.ImageIcon("res/productreturn.jpg");
		product_return = new javax.swing.JButton(image14);
		product_return.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("成品返修");
			}
		});
		javax.swing.ImageIcon image15 = new javax.swing.ImageIcon("res/productcancle.jpg");
		product_cancle = new javax.swing.JButton(image15);
		product_cancle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("成品退货");
			}
		});
		javax.swing.ImageIcon image1 = new javax.swing.ImageIcon("res/holdrecord.jpg");
		handle_record = new javax.swing.JButton(image1);
		handle_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from handle_record";
				String title[] = {"操作时间","操作人员","操作备注"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("操作编号");
				v.add("操作人员");
				v.add("开始时间");
				v.add("离开时间");
				v.add("操作备注");*/
				AddTable("操作记录",title,sql);
			}
		});
		javax.swing.ImageIcon image4 = new javax.swing.ImageIcon("res/flotsamrecord.jpg");
		flotsam_record = new javax.swing.JButton(image4);
		flotsam_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from scrap_record";
				String title[] = {"废料编号","操作人员","处理时间","处理备注"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("废料编号");
				v.add("操作人员");
				v.add("处理时间");
				v.add("处理备注");*/
				AddTable("废料记录",title,sql);
			}
		});
		//java.awt.Insets inset = new java.awt.Insets(0,0,0,0);
		javax.swing.ImageIcon image = new javax.swing.ImageIcon("res/outrecord.jpg");
		product_out_record = new javax.swing.JButton(image);
		//product_out_record.getInsets(inset);
		product_out_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from product_out";
				String title[] = {"产品编号","客户名称","产品名称","产品规格","产品单位","产品数量","生产单位","出货时间","备注事项"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("产品编号");
				v.add("客户名称");
				v.add("产品名称");
				v.add("产品规格");
				v.add("产品单位");
				v.add("产品数量");
				v.add("生产单位");
				v.add("出货时间");
				v.add("备注事项");*/
				AddTable("出货记录",title,sql);
			}
		});
		javax.swing.ImageIcon image2 = new javax.swing.ImageIcon("res/stuff_old.jpg");
		stuff_old_record = new javax.swing.JButton(image2);
		stuff_old_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from stuff_old_record";
				String ti[] = {"原料编号","原料名","收货员","收货员","检验员","单位","数量","规格","颜色","存放处","时间","备注"};
				/*java.util.Vector ve = new java.util.Vector();
				ve.add("原料编号");
				ve.add("原料名");
				ve.add("收货员");
				ve.add("检验员");
				ve.add("单位");
				ve.add("数量");
				ve.add("规格");
				ve.add("规格");
				ve.add("颜色");
				ve.add("存放处");
				ve.add("时间");
				ve.add("备注");*/
				AddTable("原料记录",ti,sql);
			}
		});
		javax.swing.ImageIcon image3 = new javax.swing.ImageIcon("res/product.jpg");
		product_old_record = new javax.swing.JButton(image3);
		product_old_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from product_old_record";
				String title[] = {"成品编号","成品名","客户","成品规格","单位","数量","生产单位","入库时间","备注"};
				AddTable("成品记录",title,sql);
			}
		});
		javax.swing.ImageIcon image16 = new javax.swing.ImageIcon("res/help.jpg");
		help = new javax.swing.JButton(image16);
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddHelp();
			}
		});
		javax.swing.ImageIcon image17 = new javax.swing.ImageIcon("res/about.jpg");
		about = new javax.swing.JButton(image17);
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new UserAbout();
			}
		});
		javax.swing.ImageIcon image18 = new javax.swing.ImageIcon("res/cancle.jpg");
		exit_system = new javax.swing.JButton(image18);
		//exit_system.setBackground(java.awt.Color.lightGray);
		exit_system.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String name = frame.getTitle().trim().substring(8);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(System.currentTimeMillis());
				String day = sdf.format(date);
				String s ="'"+day+"','"+name+"','"+"退出"+"'";
				String sql = "insert into handle_record values("+s+")";
				USeDB.UpdateDB(sql);
				frame.dispose();
				System.exit(0);
			}
		});
		
		panel.add(stuff_in);
		panel.add(stuff_quit);
		panel.add(stuff_stock);
		panel.add(yield_draw);
		panel.add(yield_off);
		panel.add(yield_scrap);
		panel.add(product_in);
		panel.add(product_stock);
		panel.add(product_out);
		panel.add(product_return);
		panel.add(product_cancle);
		panel.add(handle_record);
		panel.add(flotsam_record);
		panel.add(product_out_record);
		panel.add(stuff_old_record);
		panel.add(product_old_record);
		panel.add(help);
		panel.add(about);
		panel.add(exit_system);
		panel.setBackground(java.awt.Color.pink);
		scrollPane = new javax.swing.JScrollPane(panel);
		scrollPane.setBackground(java.awt.Color.pink);
		return scrollPane;
	}
	private JTabbedPane createHandlePane(){
		//tabbedPane = new javax.swing.JTabbedPane();
		javax.swing.ImageIcon image = new javax.swing.ImageIcon("res/welcome1.jpg");
		javax.swing.JLabel component = new javax.swing.JLabel(image);
		tabbedPane.addTab("欢迎",icon ,component);
		//tabbedPane.setBackground(java.awt.Color.pink);
		tabbedPane.setBackgroundAt(0, java.awt.Color.pink);
		return tabbedPane;
	}
	private JSplitPane createSplitPane(){
		splitPane = new javax.swing.JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,createScrollPane(),createHandlePane()){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				if(!isOpaque()){return;}
				Graphics2D g2d = (Graphics2D) g;
				//int rule = AlphaComposite.SRC_OVER;
				AlphaComposite opaque = AlphaComposite.SrcOver;
				//AlphaComposite blend = AlphaComposite.getInstance(rule, 0.6f);
				//AlphaComposite set = AlphaComposite.Src;
				int width = getWidth();
				int height = getHeight();
				GradientPaint gradientPaint = new GradientPaint(0,0,java.awt.Color.green,width/2,height/2,java.awt.Color.yellow,false);
				g2d.setComposite(opaque);
				g2d.setPaint(gradientPaint);
				g2d.fillRect(0, 0, width,height);
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
			}
		};
		splitPane.setDividerLocation(138);
		splitPane.setDividerSize(5);
		splitPane.setOneTouchExpandable(true);
		splitPane.setBackground(java.awt.Color.pink);
		return splitPane;
	}
	/*private void AddScrapRecord(String st){
		int i = tabbedPane.indexOfTab(st);
		if(i>-1)tabbedPane.remove(i);
		tabbedPane.addTab(st, new createUserScrapInterFace());
		int b = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(b-1);
	}*/
	private void AddStuffIn(String s){
		int i = tabbedPane.getTabCount();
		if(i>0)tabbedPane.removeAll();
		tabbedPane.addTab(s, icon,new createUserStuffInterFace());
		int b = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(b-1);
	}
	private void AddStuffQuit(String s){
		int i = tabbedPane.getTabCount();
		if(i>0)tabbedPane.removeAll();
		tabbedPane.addTab(s,icon ,new createStuffQuitInterFace());
		int b = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(b-1);
	}
	private void AddTable(String title,String st[],String sq){
		int i = tabbedPane.getTabCount();
		if(i>0)tabbedPane.removeAll();
		tabbedPane.addTab(title,icon,new createUserTable(st,sq));
		int b = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(b-1);
	}
	/*private void AddTable(String title,Vector v,String sql){
		/*
		 * 此方法致数据错误
		 */
		/*int i = tabbedPane.indexOfTab(title);
		if(i>-1)tabbedPane.remove(i);
		tabbedPane.addTab(title, new createUserTable(v,sql));
		int b = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(b-1);
	}
	*/
	private void AddYieldDraw(String title){
		int i = tabbedPane.getTabCount();
		if(i>0)tabbedPane.removeAll();
		tabbedPane.addTab(title,icon, new createUserYieldDrawInterFace());
		int b = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(b-1);
	}
	private void AddYieldScrap(String title){
		int i = tabbedPane.getTabCount();
		if(i>0)tabbedPane.removeAll();
		tabbedPane.addTab(title,icon ,new createUserYieldScrapInterFace());
		int b = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(b-1);
	}
	private void AddProductIn(String title){
		int i = tabbedPane.getTabCount();
		if(i>0)tabbedPane.removeAll();
		tabbedPane.addTab(title,icon ,new createUserProductInterFace());
		int b = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(b-1);
	}
	private void AddProductOut(String title){
		int i = tabbedPane.getTabCount();
		if(i>0)tabbedPane.removeAll();
		tabbedPane.addTab(title,icon ,new createUserProductOutInterFace());
		int b = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(b-1);
	}
	private void AddHelp(){
		int i = tabbedPane.getTabCount();
		if(i>0)tabbedPane.removeAll();
		tabbedPane.addTab("帮助",icon ,new UserHelp());
		int b = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(b-1);
	}
}
