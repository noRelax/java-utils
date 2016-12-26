//主函数类，可以独立运行


package classsource;

import java.awt.*;//倒包
import java.awt.event.*;//倒包
import javax.swing.*;//倒包
import java.sql.*;//倒包

public class Main extends JFrame implements Runnable{

	Thread t=new Thread(this);//在窗体里创建线程并实例化
	JDesktopPane deskpane = new JDesktopPane();//在窗体里建立虚拟桌面并实例化
	JPanel p = new JPanel();//创建一个面板并实例化
	Label lp1=new Label("欢  迎  使  用  员  工  管  理  系  统 !           有 不 明 白 请 看 帮 助 !        本 系 统 禁 止 用 作 商 业 用 途 !");

//菜单上的图标创建并实例化----------------------------------------------------------------------------
   	ImageIcon icon1=new ImageIcon("image//tjsc.gif");
	ImageIcon icon2=new ImageIcon("image//cxdl.gif");
	ImageIcon icon3=new ImageIcon("image//xgmm.gif");
	ImageIcon icon4=new ImageIcon("image//tcxt.gif");
	ImageIcon icon5=new ImageIcon("image//jj.gif");
	ImageIcon icon6=new ImageIcon("image//help.gif");
	ImageIcon icon7=new ImageIcon("image//cx.gif");
	ImageIcon icon8=new ImageIcon("image//gl.gif");
	ImageIcon icon9=new ImageIcon("image//xt.gif");
	ImageIcon icon10=new ImageIcon("image//xxgl.gif");
	ImageIcon icon11=new ImageIcon("image//xxcx.gif");
	ImageIcon icon12=new ImageIcon("image//bz.gif");
	ImageIcon icon13=new ImageIcon("image//gy.gif");
	ImageIcon icon14=new ImageIcon("image//glxx.gif");
	ImageIcon icon15=new ImageIcon("image//cxxx.gif");
//完--------------------------------------------------------------------------------

	public Main(){//构造函数
		setTitle("员工管理系统");//设置窗体标题
		Container con = getContentPane();
		con.setLayout(new BorderLayout());//创建一个布局
		con.add(deskpane,BorderLayout.CENTER);//实例虚拟桌面的布局

		Font f =new Font("新宋体",Font.PLAIN,12);//设置一个字体，以后设置字体全部调用这种字体，懒得弄那么花花哨哨的
		
		JMenuBar mb = new JMenuBar();//实例化菜单栏

		//实例化菜单开始
		JMenu systemM = new JMenu("系统管理");
		systemM.setFont(f);
		JMenu manageM = new JMenu("信息管理");
		manageM.setFont(f);
		JMenu employeeMM = new JMenu("员工信息管理");//这个是信息管理的二级菜单
		employeeMM.setFont(f);
		JMenu selectM = new JMenu("信息查询");
		selectM.setFont(f);
		JMenu employeeSM =new JMenu("员工信息查询");//这个是信息查询的二级菜单
		employeeSM.setFont(f);
		//JMenu statisticM = new JMenu("休闲娱乐");
	    //statisticM.setFont(f);
		JMenu helpM = new JMenu("帮助");
		helpM.setFont(f);
		JMenu aboutM=new JMenu("关于");
		aboutM.setFont(f);
		//实例化菜单结束

		//实例化系统管理菜单的菜单项
		JMenuItem password = new JMenuItem("密码修改");
		password.setFont(f);
		JMenuItem land = new JMenuItem("重新登陆");
		land.setFont(f);
		JMenuItem addDelete = new JMenuItem("添加/删除用户");
		addDelete.setFont(f);
		JMenuItem exit = new JMenuItem("退出系统");
		exit.setFont(f);
		systemM.add(password);
		systemM.add(land);
		systemM.add(addDelete);
		systemM.add(exit);
		//实例化系统管理菜单的菜单项结束

//为系统管理菜单加事件-----------------------------------------------------------------------------
        password.addActionListener(new ActionListener(){//密码修改监听
        	public void actionPerformed(ActionEvent e){
        		System.out.println("AmendPassword");
        		deskpane.add(new AmendPassword());
        		}
        	});

        land.addActionListener(new ActionListener(){//重新登陆监听
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Land");
        		setVisible(false);
        		new Land();
        		}
        	});

        addDelete.addActionListener(new ActionListener(){//添加/删除用户监听
        	public void actionPerformed(ActionEvent e){
        		deskpane.add(new AddDeleteUser());
        		}
        	});

        exit.addActionListener(new ActionListener(){//退出系统监听
        	public void actionPerformed(ActionEvent e){
        		//new JOptionPane().showMessageDialog(
        		setVisible(false);
        		}
        	});
//--------------------------------------------------------------------------------------------
		
		//实例化信息管理的菜单项
		JMenuItem departmentM = new JMenuItem("部门信息管理");
		departmentM.setFont(f);
		JMenuItem employeeM = new JMenuItem("基本信息管理");
		employeeM.setFont(f);
		JMenuItem trainM = new JMenuItem("培训信息管理");
		trainM.setFont(f);
		JMenuItem encouragementPunishM = new JMenuItem("奖罚信息管理");
		encouragementPunishM.setFont(f);
		JMenuItem wageM =new JMenuItem("薪资信息管理");
		wageM.setFont(f);
		employeeMM.add(trainM);
		employeeMM.add(employeeM);
		employeeMM.add(encouragementPunishM);
		employeeMM.add(wageM);
		manageM.add(employeeMM);
		manageM.add(departmentM);
		//实例化信息管理的菜单项结束

//为管理菜单加事件------------------------------------------------------------------------------
        departmentM.addActionListener(new ActionListener(){//部门信息管理监听
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Departmentmanage");
        		deskpane.add(new Departmentmanage());
        		}
        	});

        employeeM.addActionListener(new ActionListener(){//基本信息管理监听
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Employeemanage");
        		deskpane.add(new Employeemanage());
        		}
        	});

        trainM.addActionListener(new ActionListener(){//培训信息管理监听
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Trainmanage");
        		deskpane.add(new Trainmanage());
        		}
        	});

        encouragementPunishM.addActionListener(new ActionListener(){//奖罚信息管理监听
        	public void actionPerformed(ActionEvent e){
        		System.out.println("EncouragementPunish");
        		deskpane.add(new EncouragementPunish());
        		}
        	});

        wageM.addActionListener(new ActionListener(){//薪资信息管理监听
        	public void actionPerformed(ActionEvent e){
        		System.out.println("WageManage");
        		deskpane.add(new WageManage());
        		}
        	});
//-----------------------------------------------------------------------------------------------

		//实例化信息查询的菜单项
		
		JMenuItem departmentS = new JMenuItem("部门信息查询");
		departmentS.setFont(f);
		JMenuItem employeeS = new JMenuItem("基本信息查询");
		employeeS.setFont(f);
		JMenuItem trainS = new JMenuItem("培训信息查询");
		trainS.setFont(f);
		JMenuItem encouragementPunishS = new JMenuItem("奖罚信息查询");
		encouragementPunishS.setFont(f);
		JMenuItem wageS =new JMenuItem("薪资信息查询");
		wageS.setFont(f);
		employeeSM.add(trainS);
		employeeSM.add(employeeS);
		employeeSM.add(encouragementPunishS);
		employeeSM.add(wageS);
		selectM.add(employeeSM);
		selectM.add(departmentS);
		
		//实例化信息查询的菜单项结束

//为查询菜单加事件---------------------------------------------------------

		departmentS.addActionListener(new ActionListener(){//部门信息查询监听
			public void actionPerformed(ActionEvent e){
				System.out.println("DIQ");
				deskpane.add(new DIQ());
				}
			});


		employeeS.addActionListener(new ActionListener(){//基本信息查询监听
			public void actionPerformed(ActionEvent e){
				System.out.println("BIQ");
				deskpane.add(new BIQ());
				}
			});

		trainS.addActionListener(new ActionListener(){//培训信息查询监听
			public void actionPerformed(ActionEvent e){
				System.out.println("TIQ");
				deskpane.add(new TIQ());
				}
			});

		encouragementPunishS.addActionListener(new ActionListener(){//奖罚信息查询监听
			public void actionPerformed(ActionEvent e){
				System.out.println("EPIQ");
				deskpane.add(new EPIQ());
				}
			});


		wageS.addActionListener(new ActionListener(){//薪资信息查询监听
			public void actionPerformed(ActionEvent e){
				System.out.println("SIQ");
				deskpane.add(new SIQ());
				}
			});

/*============================作废了也许以后用========================*/


		//JMenuItem game=new JMenuItem("俄罗斯方块");
		//JMenuItem countStatistic=new JMenuItem("");
		//game.setFont(f);
		//countStatistic.setFont(f);
        //statisticM.add(game);
        //statisticM.add(countStatistic);
        
//----------------俄罗斯方块游戏事件------作废-----------------------

		//game.addActionListener(new ActionListener(){
        	//public void actionPerformed(ActionEvent e){
        	//	System.out.println("Game");
        	//	deskpane.add(new Game());
        	//	}
        	//});

/*============================作废了也许以后用========================*/



		JMenuItem help = new JMenuItem("帮助");
		help.setFont(f);
		JMenuItem about =new JMenuItem("关于");
		about.setFont(f);
		helpM.add(help);
		aboutM.add(about);

//为帮助菜单加事件-------------------------------------------------------------------------
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				deskpane.add(new About());
				}
			});
//-----------------------------------------------------------------------------------------

		mb.add(systemM);
		mb.add(manageM);
		mb.add(selectM);
//		mb.add(statisticM);
		mb.add(helpM);
		mb.add(aboutM);
	    setJMenuBar(mb);


//以下全都是在添加图标----------------------------------------------------------------------------------
    //窗口图标
	    Image img=Toolkit.getDefaultToolkit().getImage("image\\main.gif");
	    setIconImage(img);
   //添加菜单图标
		systemM.setIcon(icon9);
		manageM.setIcon(icon8);
		selectM.setIcon(icon7);
//		statisticM.setIcon(icon12);
		helpM.setIcon(icon6);
		addDelete.setIcon(icon1);
		land.setIcon(icon2);
		password.setIcon(icon3);
		exit.setIcon(icon4);
		employeeMM.setIcon(icon5);
		employeeSM.setIcon(icon5);
		departmentM.setIcon(icon10);
		departmentS.setIcon(icon11);
//		game.setIcon(icon12);
//		countStatistic.setIcon(icon12);
		help.setIcon(icon6);
		aboutM.setIcon(icon13);
		about.setIcon(icon13);

		employeeM.setIcon(icon14);
		trainM.setIcon(icon14);
		encouragementPunishM.setIcon(icon14);
		wageM.setIcon(icon14);

		employeeS.setIcon(icon15);
		trainS.setIcon(icon15);
		encouragementPunishS.setIcon(icon15);
		wageS.setIcon(icon15);
		
//添加完了-------------------------------------------------------------------------------

   	    JToolBar jToolBar1 = new JToolBar();//创建一个工具栏
   	    jToolBar1.setLayout(new GridLayout(9,1));//设置成网格布局
        JButton jButton1 = new JButton();//创建并实例化按钮
        jButton1.setToolTipText("员工基本信息管理");//设置按钮悬停信息
        JButton jButton2 = new JButton();
        jButton2.setToolTipText("员工基本信息查询");
        JButton jButton3 = new JButton();
        jButton3.setToolTipText("修改密码");
        //JButton jButton4 = new JButton();
        JButton jButton5 = new JButton();
        jButton5.setToolTipText("计算器");
        JButton jButton6 = new JButton();
        jButton6.setToolTipText("退出系统");
        

        jToolBar1.setMaximumSize(new java.awt.Dimension(600, 50));//设置工具栏最大值
        jToolBar1.setMinimumSize(new java.awt.Dimension(600, 50));//设置工具栏最小值
        
        //添加工具栏中按钮的方法
        
        jButton1.setIcon(new ImageIcon("image//1.png"));
        jButton1.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Employeemanage");
        		deskpane.add(new Employeemanage());
        		}
        	});
        jToolBar1.add(jButton1);

        jButton2.setIcon(new ImageIcon("image//2.png"));
        jButton2.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		deskpane.add(new BIQ());
        		}
        	});
        jToolBar1.add(jButton2);

        jButton3.setIcon(new ImageIcon("image//3.png"));
        jButton3.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		deskpane.add(new AmendPassword());
        		}
        	});
        jToolBar1.add(jButton3);

    //    jButton4.setIcon(new ImageIcon("4.png"));
    //    jButton4.addActionListener(new ActionListener(){
    //    	public void actionPerformed(ActionEvent e){
     //   		System.out.println("Game");
     //   		deskpane.add(new Game());
      //  		}
      //  	});
      //  jToolBar1.add(jButton4);



        jButton5.setIcon(new ImageIcon("image//5.png"));
        jButton5.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Calculator");
        		deskpane.add(new Calculator());
        		}
        	});
        jToolBar1.add(jButton5);
        
        
        
        jButton6.setIcon(new javax.swing.ImageIcon("image//6.png"));
        jButton6.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
                System.exit(0);
        		}
        	});        
		jToolBar1.add(jButton6);
		
		
		//添加工具栏中按钮的方法结束
		
		
		
        jToolBar1.setBounds(0, 0, 30, 600);//工具栏位置
        jToolBar1.setEnabled(false);//禁止更改大小
        con.add(jToolBar1,BorderLayout.WEST);//布局



        //Label lp1=new Label("欢  迎  使  用  员  工  管  理  系  统 !");
        p.setLayout(new BorderLayout());
        p.add(lp1,BorderLayout.EAST);
        	t.start();

        con.add(p,BorderLayout.SOUTH);

	    Toolkit t = Toolkit.getDefaultToolkit();
	    int width = t.getScreenSize().width - 200;
	    int height = t.getScreenSize().height - 100;
	    setSize(width,height);
	    setLocation(150,100);
		setVisible(true);
		setResizable(false);
		}


		//线程的方法
        	public void run(){
        	  System.out.println("线程启动了!");//友好提示
        	  Toolkit t = Toolkit.getDefaultToolkit();
        	  int x=t.getScreenSize().width;
        	  System.out.println("x=" + x);

        	  //lp1.setFont( new Font("宋体",Font.ITALIC,"14"));
	          lp1.setForeground(Color.red);
		      while(true)
		         {
                     if(x<-600){
                     	x=t.getScreenSize().width;
                     	//System.out.println("x为:" + x);
                     	}
			         lp1.setBounds(x,0,700,20);
			         x-=10;
			         //System.out.println(x);
			         try{Thread.sleep(100);}catch(Exception e){}
		            //}
	           	 }
              }

	public static void main(String[] args){//主函数
		new Main();
		}
	}

/****************************************程序结束**********************************/