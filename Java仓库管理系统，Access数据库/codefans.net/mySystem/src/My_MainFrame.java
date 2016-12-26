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
	private JButton stuff_in = null;//ԭ�Ͻ���
	private JButton product_in = null;//��Ʒ����
	private JButton stuff_quit = null;//ԭ������
	private JButton stuff_stock = null;//ԭ�Ͽ��
	private JButton product_stock = null;//��Ʒ���
	private JButton yield_draw = null;//��������
	//private JButton yield_consume = null;//��������
	private JButton yield_off = null;//��������
	private JButton yield_scrap = null;//��������
	//private JButton old_record = null;//�ֿ��¼
	private JButton product_out = null;//��Ʒ���
	private JButton product_return = null;//��Ʒ����
	private JButton product_cancle = null;//��Ʒ�˻�
	private JButton handle_record = null;//������¼
	private JButton flotsam_record = null;//���ϼ�¼
	private JButton product_out_record = null;//������¼
	private JButton stuff_old_record = null;//ԭ�ϼ�¼
	private JButton product_old_record = null;//��Ʒ��¼
	private JButton help = null;//����
	private JButton about = null;//����
	private JButton exit_system = null;//�˳�ϵͳ
	private String sql = null;
	
	
	public My_MainFrame() {
		// TODO �Զ����ɹ��캯�����
	}
	public My_MainFrame(String name){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception exe){System.err.print(exe.getMessage());}
		java.awt.Toolkit tool = frame.getToolkit();
		Image image = tool.getImage("res/logo.jpg");
		java.awt.Dimension dimn = tool.getScreenSize();
		String title = "�ֿ����ϵͳ--"+name;
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
			//��Դ��ڵ���dispose()������ر�ʱ����
			public void windowClosed(WindowEvent e){}
			//�û���ͼ�Ӵ��ڵ�ϵͳ�˵��йرմ���ʱ����
			public void windowClosing(WindowEvent e){
				String name = frame.getTitle().trim().substring(8);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(System.currentTimeMillis());
				String day = sdf.format(date);
				String s ="'"+day+"','"+name+"','"+"�˳�"+"'";
				String sql = "insert into handle_record values("+s+")";
				USeDB.UpdateDB(sql);
				frame.dispose();
				System.exit(0);
			}
			//�����ڲ����ǻ����ʱ����
			public void windowDeactivated(WindowEvent e){}
			//���ڴ���С��״̬��Ϊ����״̬ʱ����
			public void windowDeiconified(WindowEvent e){}
			//���ڴ�����״̬��Ϊ��С״̬ʱ����
			public void windowIconified(WindowEvent e){}
			//�����״α�Ϊ�ɼ�ʱ����
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
		javax.swing.JMenu systemMenu = new javax.swing.JMenu("ϵͳ����");
		javax.swing.JMenuItem logoin = new javax.swing.JMenuItem("ע      ��");
		logoin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new AddUser();
			}
		});
		javax.swing.JMenuItem logoout = new javax.swing.JMenuItem("ɾ���û�");
		logoout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String jp = new JOptionPane().showInputDialog(frame,"������Ҫɾ�����û�����","�����û���",JOptionPane.YES_NO_CANCEL_OPTION).trim();
				if(USeDB.delUser(jp)){
					JOptionPane.showConfirmDialog(null, "�û�ɾ���ɹ�!");
				}
				JOptionPane.showConfirmDialog(null, "�û�ɾ��ʧ��!");
			}
		});
		javax.swing.JMenuItem datatidy = new javax.swing.JMenuItem("��������");
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
		javax.swing.JMenu stuffMenu = new javax.swing.JMenu("ԭ�Ϲ���");
		javax.swing.JMenuItem stuff_in = new javax.swing.JMenuItem("ԭ�Ͻ���");
		stuff_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				AddStuffIn("ԭ�Ͻ���");
			}
		});
		javax.swing.JMenuItem stuff_quit = new javax.swing.JMenuItem("ԭ���˻�");
		stuff_quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e2){
				AddStuffQuit("ԭ���˻�");
			}
		});
		javax.swing.JMenuItem stuff_stock = new javax.swing.JMenuItem("ԭ�Ͽ��");
		stuff_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e3){
				String tit[] = {"ԭ�ϱ��","ԭ����","������","�ջ�Ա","����Ա","��λ","����","���","��ɫ","��Ŵ�","ʱ��","��ע"};
				String sql = "select * from stuff_in";
				AddTable("ԭ�Ͽ��",tit,sql);
			}
		});
		javax.swing.JMenuItem yield_draw = new javax.swing.JMenuItem("��������");
		yield_draw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e4){
				AddYieldDraw("��������");
			}
		});
		javax.swing.JMenuItem yield_off = new javax.swing.JMenuItem("��������");
		yield_off.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e4){
				AddStuffIn("��������");
			}
		});
		javax.swing.JMenuItem yield_scrap = new javax.swing.JMenuItem("��������");
		yield_scrap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e5){
				AddYieldScrap("��������");
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
		javax.swing.JMenu productMenu = new javax.swing.JMenu("��Ʒ����");
		javax.swing.JMenuItem product_in = new javax.swing.JMenuItem("��Ʒ����");
		product_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e6){
				AddProductIn("��Ʒ����");
			}
		});
		javax.swing.JMenuItem product_stock = new javax.swing.JMenuItem("��Ʒ���");
		product_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e7){
				String tit[] ={"��Ʒ���","��Ʒ��","�ͻ�","��Ʒ���","��λ","����","������λ","���ʱ��","��ע"};
				/*Vector ve = new Vector();
				ve.add("��Ʒ���");
				ve.add("��Ʒ��");
				ve.add("�ͻ�");
				ve.add("��Ʒ���");
				ve.add("��λ");
				ve.add("����");
				ve.add("������λ");
				ve.add("���ʱ��");
				ve.add("��ע");*/
				String sql = "select * from product_in";
				AddTable("��Ʒ���",tit,sql);
			}
		});
		javax.swing.JMenuItem product_out = new javax.swing.JMenuItem("��Ʒ����");
		product_out.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e6){
				AddProductOut("��Ʒ����");
			}
		});
		javax.swing.JMenuItem product_return = new javax.swing.JMenuItem("��Ʒ����");
		product_return.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e6){
				AddProductIn("��Ʒ����");
			}
		});
		javax.swing.JMenuItem product_cancle = new javax.swing.JMenuItem("��Ʒ�˻�");
		product_cancle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e6){
				AddProductIn("��Ʒ�˻�");
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
		javax.swing.JMenu stockMenu = new javax.swing.JMenu("��¼����");
		javax.swing.JMenuItem handle_record = new javax.swing.JMenuItem("������¼");
		handle_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from handle_record";
				String title[] = {"�������","������Ա","��ʼʱ��","�뿪ʱ��","������ע"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("�������");
				v.add("������Ա");
				v.add("��ʼʱ��");
				v.add("�뿪ʱ��");
				v.add("������ע");*/
				AddTable("������¼",title,sql);
			}
		});
		javax.swing.JMenuItem flotsam_record = new javax.swing.JMenuItem("���ϼ�¼");
		flotsam_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from scrap_record";
				String title[] = {"���ϱ��","������Ա","����ʱ��","����ע"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("���ϱ��");
				v.add("������Ա");
				v.add("����ʱ��");
				v.add("����ע");*/
				AddTable("���ϼ�¼",title,sql);
			}
		});
		javax.swing.JMenuItem product_out_record = new javax.swing.JMenuItem("������¼");
		product_out_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from product_out";
				String title[] = {"��Ʒ���","�ͻ�����","��Ʒ����","��Ʒ���","��Ʒ��λ","��Ʒ����","������λ","����ʱ��","��ע����"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("��Ʒ���");
				v.add("�ͻ�����");
				v.add("��Ʒ����");
				v.add("��Ʒ���");
				v.add("��Ʒ��λ");
				v.add("��Ʒ����");
				v.add("������λ");
				v.add("����ʱ��");
				v.add("��ע����");*/
				AddTable("������¼",title,sql);
			}
		});
		javax.swing.JMenuItem stuff_old_record = new javax.swing.JMenuItem("ԭ�ϼ�¼");
		stuff_old_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from stuff_old_record";
				String ti[] = {"ԭ�ϱ��","ԭ����","�ջ�Ա","�ջ�Ա","����Ա","��λ","����","���","��ɫ","��Ŵ�","ʱ��","��ע"};
				/*java.util.Vector ve = new java.util.Vector();
				ve.add("ԭ�ϱ��");
				ve.add("ԭ����");
				ve.add("�ջ�Ա");
				ve.add("����Ա");
				ve.add("��λ");
				ve.add("����");
				ve.add("���");
				ve.add("���");
				ve.add("��ɫ");
				ve.add("��Ŵ�");
				ve.add("ʱ��");
				ve.add("��ע");*/
				AddTable("ԭ�ϼ�¼",ti,sql);
			}
		});
		javax.swing.JMenuItem product_old_record = new javax.swing.JMenuItem("��Ʒ��¼");
		product_old_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from product_old_record";
				String title[] = {"��Ʒ���","��Ʒ��","�ͻ�","��Ʒ���","��λ","����","������λ","���ʱ��","��ע"};
				/*java.util.Vector ve = new java.util.Vector();
				ve.add("��Ʒ���");
				ve.add("��Ʒ��");
				ve.add("�ͻ�");
				ve.add("��Ʒ���");
				ve.add("��λ");
				ve.add("����");
				ve.add("������λ");
				ve.add("���ʱ��");
				ve.add("��ע");*/
				AddTable("��Ʒ��¼",title,sql);
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
		javax.swing.JMenu helpMenu = new javax.swing.JMenu("����");
		javax.swing.JMenuItem help = new javax.swing.JMenuItem("����");
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddHelp();
			}
		});
		javax.swing.JMenuItem about = new javax.swing.JMenuItem("����");
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
		
		stuff_in = new javax.swing.JButton("ԭ�Ͻ���");
		stuff_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddStuffIn("ԭ�Ͻ���");
			}
		});
		stuff_quit = new javax.swing.JButton("ԭ������");
		stuff_quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddStuffQuit("ԭ���˻�");
			}
		});
		stuff_stock = new javax.swing.JButton("ԭ�Ͽ��");
		stuff_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tit[] = {"ԭ�ϱ��","ԭ����","������","�ջ�Ա","����Ա","��λ","����","���","��ɫ","��Ŵ�","ʱ��","��ע"};
				String sql = "select * from stuff_in";
				AddTable("ԭ�Ͽ��",tit,sql);
			}
		});
		yield_draw = new javax.swing.JButton("��������");
		yield_draw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddYieldDraw("��������");
			}
		});
		yield_off = new javax.swing.JButton("��������");
		yield_off.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddStuffIn("��������");
			}
		});
		yield_scrap = new javax.swing.JButton("��������");
		yield_scrap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddYieldScrap("��������");
			}
		});
		product_in = new javax.swing.JButton("��Ʒ����");
		product_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("��Ʒ����");
			}
		});
		product_stock = new javax.swing.JButton("��Ʒ���");
		product_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tit[] ={"��Ʒ���","��Ʒ��","�ͻ�","��Ʒ���","��λ","����","������λ","���ʱ��","��ע"};
				/*Vector ve = new Vector();
				ve.add("��Ʒ���");
				ve.add("��Ʒ��");
				ve.add("�ͻ�");
				ve.add("��Ʒ���");
				ve.add("��λ");
				ve.add("����");
				ve.add("������λ");
				ve.add("���ʱ��");
				ve.add("��ע");*/
				String sql = "select * from product_in";
				AddTable("��Ʒ���",tit,sql);
			}
		});
		product_out = new javax.swing.JButton("��Ʒ����");
		product_out.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductOut("��Ʒ����");
			}
		});
		product_return = new javax.swing.JButton("��Ʒ����");
		product_return.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("��Ʒ����");
			}
		});
		product_cancle = new javax.swing.JButton("��Ʒ�˻�");
		product_cancle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("��Ʒ�˻�");
			}
		});
		handle_record = new javax.swing.JButton("������¼");
		handle_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from handle_record";
				String title[] = {"����ʱ��","�����û�","������ע"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("�������");
				v.add("������Ա");
				v.add("��ʼʱ��");
				v.add("�뿪ʱ��");
				v.add("������ע");*/
				AddTable("������¼",title,sql);
			}
		});
		flotsam_record = new javax.swing.JButton("���ϼ�¼");
		flotsam_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from scrap_record";
				String title[] = {"���ϱ��","������Ա","����ʱ��","����ע"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("���ϱ��");
				v.add("������Ա");
				v.add("����ʱ��");
				v.add("����ע");*/
				AddTable("���ϼ�¼",title,sql);
			}
		});
		product_out_record = new javax.swing.JButton("������¼");
		product_out_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from product_out";
				String title[] = {"��Ʒ���","�ͻ�����","��Ʒ����","��Ʒ���","��Ʒ��λ","��Ʒ����","������λ","����ʱ��","��ע����"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("��Ʒ���");
				v.add("�ͻ�����");
				v.add("��Ʒ����");
				v.add("��Ʒ���");
				v.add("��Ʒ��λ");
				v.add("��Ʒ����");
				v.add("������λ");
				v.add("����ʱ��");
				v.add("��ע����");*/
				AddTable("������¼",title,sql);
			}
		});
		help = new javax.swing.JButton("��     ��");
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddHelp();
			}
		});
		about = new javax.swing.JButton("��     ��");
		about.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new UserAbout();
			}
		});
		exit_system = new javax.swing.JButton("�˳�ϵͳ");
		exit_system.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String name = frame.getTitle().trim().substring(8);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(System.currentTimeMillis());
				String day = sdf.format(date);
				String s ="'"+day+"','"+name+"','"+"�˳�"+"'";
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
				AddStuffIn("ԭ�Ͻ���");
			}
		});
		javax.swing.ImageIcon image7 = new javax.swing.ImageIcon("res/stuffquit.jpg");
		stuff_quit = new javax.swing.JButton(image7);
		stuff_quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddStuffQuit("ԭ���˻�");
			}
		});
		javax.swing.ImageIcon image5 = new javax.swing.ImageIcon("res/stuffstock.jpg");
		stuff_stock = new javax.swing.JButton(image5);
		stuff_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tit[] = {"ԭ�ϱ��","ԭ����","ԭ����","�ջ�Ա","����Ա","��λ","����","���","��ɫ","��Ŵ�","ʱ��","��ע"};
				/*Vector ve = new Vector();
				ve.add("ԭ�ϱ��");
				ve.add("ԭ����");
				ve.add("�ջ�Ա");
				ve.add("����Ա");
				ve.add("��λ");
				ve.add("����");
				ve.add("���");
				ve.add("���");
				ve.add("��ɫ");
				ve.add("��Ŵ�");
				ve.add("ʱ��");
				ve.add("��ע");
				*/
				String sql = "select * from stuff_in";
				AddTable("ԭ�Ͽ��",tit,sql);
			}
		});
		javax.swing.ImageIcon image8 = new javax.swing.ImageIcon("res/yielddraw.jpg");
		yield_draw = new javax.swing.JButton(image8);
		yield_draw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddYieldDraw("��������");
			}
		});
		javax.swing.ImageIcon image9 = new javax.swing.ImageIcon("res/yield_off.jpg");
		yield_off = new javax.swing.JButton(image9);
		yield_off.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddStuffIn("��������");
			}
		});
		javax.swing.ImageIcon image10 = new javax.swing.ImageIcon("res/yieldscrap.jpg");
		yield_scrap = new javax.swing.JButton(image10);
		yield_scrap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddYieldScrap("��������");
			}
		});
		javax.swing.ImageIcon image11 = new javax.swing.ImageIcon("res/productin.jpg");
		product_in = new javax.swing.JButton(image11);
		product_in.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("��Ʒ����");
			}
		});
		javax.swing.ImageIcon image12 = new javax.swing.ImageIcon("res/productstock.jpg");
		product_stock = new javax.swing.JButton(image12);
		product_stock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tit[] ={"��Ʒ���","��Ʒ��","�ͻ�","��Ʒ���","��λ","����","������λ","���ʱ��","��ע"};
				/*Vector ve = new Vector();
				ve.add("��Ʒ���");
				ve.add("��Ʒ��");
				ve.add("�ͻ�");
				ve.add("��Ʒ���");
				ve.add("��λ");
				ve.add("����");
				ve.add("������λ");
				ve.add("���ʱ��");
				ve.add("��ע");
				*/
				String sql = "select * from product_in";
				AddTable("��Ʒ���",tit,sql);
			}
		});
		javax.swing.ImageIcon image13 = new javax.swing.ImageIcon("res/productout.jpg");
		product_out = new javax.swing.JButton(image13);
		product_out.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductOut("��Ʒ����");
			}
		});
		javax.swing.ImageIcon image14 = new javax.swing.ImageIcon("res/productreturn.jpg");
		product_return = new javax.swing.JButton(image14);
		product_return.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("��Ʒ����");
			}
		});
		javax.swing.ImageIcon image15 = new javax.swing.ImageIcon("res/productcancle.jpg");
		product_cancle = new javax.swing.JButton(image15);
		product_cancle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddProductIn("��Ʒ�˻�");
			}
		});
		javax.swing.ImageIcon image1 = new javax.swing.ImageIcon("res/holdrecord.jpg");
		handle_record = new javax.swing.JButton(image1);
		handle_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from handle_record";
				String title[] = {"����ʱ��","������Ա","������ע"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("�������");
				v.add("������Ա");
				v.add("��ʼʱ��");
				v.add("�뿪ʱ��");
				v.add("������ע");*/
				AddTable("������¼",title,sql);
			}
		});
		javax.swing.ImageIcon image4 = new javax.swing.ImageIcon("res/flotsamrecord.jpg");
		flotsam_record = new javax.swing.JButton(image4);
		flotsam_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from scrap_record";
				String title[] = {"���ϱ��","������Ա","����ʱ��","����ע"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("���ϱ��");
				v.add("������Ա");
				v.add("����ʱ��");
				v.add("����ע");*/
				AddTable("���ϼ�¼",title,sql);
			}
		});
		//java.awt.Insets inset = new java.awt.Insets(0,0,0,0);
		javax.swing.ImageIcon image = new javax.swing.ImageIcon("res/outrecord.jpg");
		product_out_record = new javax.swing.JButton(image);
		//product_out_record.getInsets(inset);
		product_out_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from product_out";
				String title[] = {"��Ʒ���","�ͻ�����","��Ʒ����","��Ʒ���","��Ʒ��λ","��Ʒ����","������λ","����ʱ��","��ע����"};
				/*java.util.Vector v = new java.util.Vector();
				v.add("��Ʒ���");
				v.add("�ͻ�����");
				v.add("��Ʒ����");
				v.add("��Ʒ���");
				v.add("��Ʒ��λ");
				v.add("��Ʒ����");
				v.add("������λ");
				v.add("����ʱ��");
				v.add("��ע����");*/
				AddTable("������¼",title,sql);
			}
		});
		javax.swing.ImageIcon image2 = new javax.swing.ImageIcon("res/stuff_old.jpg");
		stuff_old_record = new javax.swing.JButton(image2);
		stuff_old_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from stuff_old_record";
				String ti[] = {"ԭ�ϱ��","ԭ����","�ջ�Ա","�ջ�Ա","����Ա","��λ","����","���","��ɫ","��Ŵ�","ʱ��","��ע"};
				/*java.util.Vector ve = new java.util.Vector();
				ve.add("ԭ�ϱ��");
				ve.add("ԭ����");
				ve.add("�ջ�Ա");
				ve.add("����Ա");
				ve.add("��λ");
				ve.add("����");
				ve.add("���");
				ve.add("���");
				ve.add("��ɫ");
				ve.add("��Ŵ�");
				ve.add("ʱ��");
				ve.add("��ע");*/
				AddTable("ԭ�ϼ�¼",ti,sql);
			}
		});
		javax.swing.ImageIcon image3 = new javax.swing.ImageIcon("res/product.jpg");
		product_old_record = new javax.swing.JButton(image3);
		product_old_record.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sql = "select * from product_old_record";
				String title[] = {"��Ʒ���","��Ʒ��","�ͻ�","��Ʒ���","��λ","����","������λ","���ʱ��","��ע"};
				AddTable("��Ʒ��¼",title,sql);
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
				String s ="'"+day+"','"+name+"','"+"�˳�"+"'";
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
		tabbedPane.addTab("��ӭ",icon ,component);
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
		 * �˷��������ݴ���
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
		tabbedPane.addTab("����",icon ,new UserHelp());
		int b = tabbedPane.getTabCount();
		tabbedPane.setSelectedIndex(b-1);
	}
}
