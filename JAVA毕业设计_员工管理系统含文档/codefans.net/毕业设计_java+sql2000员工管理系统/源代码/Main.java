//�������࣬���Զ�������


package classsource;

import java.awt.*;//����
import java.awt.event.*;//����
import javax.swing.*;//����
import java.sql.*;//����

public class Main extends JFrame implements Runnable{

	Thread t=new Thread(this);//�ڴ����ﴴ���̲߳�ʵ����
	JDesktopPane deskpane = new JDesktopPane();//�ڴ����ｨ���������沢ʵ����
	JPanel p = new JPanel();//����һ����岢ʵ����
	Label lp1=new Label("��  ӭ  ʹ  ��  Ա  ��  ��  ��  ϵ  ͳ !           �� �� �� �� �� �� �� �� !        �� ϵ ͳ �� ֹ �� �� �� ҵ �� ; !");

//�˵��ϵ�ͼ�괴����ʵ����----------------------------------------------------------------------------
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
//��--------------------------------------------------------------------------------

	public Main(){//���캯��
		setTitle("Ա������ϵͳ");//���ô������
		Container con = getContentPane();
		con.setLayout(new BorderLayout());//����һ������
		con.add(deskpane,BorderLayout.CENTER);//ʵ����������Ĳ���

		Font f =new Font("������",Font.PLAIN,12);//����һ�����壬�Ժ���������ȫ�������������壬����Ū��ô�������ڵ�
		
		JMenuBar mb = new JMenuBar();//ʵ�����˵���

		//ʵ�����˵���ʼ
		JMenu systemM = new JMenu("ϵͳ����");
		systemM.setFont(f);
		JMenu manageM = new JMenu("��Ϣ����");
		manageM.setFont(f);
		JMenu employeeMM = new JMenu("Ա����Ϣ����");//�������Ϣ����Ķ����˵�
		employeeMM.setFont(f);
		JMenu selectM = new JMenu("��Ϣ��ѯ");
		selectM.setFont(f);
		JMenu employeeSM =new JMenu("Ա����Ϣ��ѯ");//�������Ϣ��ѯ�Ķ����˵�
		employeeSM.setFont(f);
		//JMenu statisticM = new JMenu("��������");
	    //statisticM.setFont(f);
		JMenu helpM = new JMenu("����");
		helpM.setFont(f);
		JMenu aboutM=new JMenu("����");
		aboutM.setFont(f);
		//ʵ�����˵�����

		//ʵ����ϵͳ����˵��Ĳ˵���
		JMenuItem password = new JMenuItem("�����޸�");
		password.setFont(f);
		JMenuItem land = new JMenuItem("���µ�½");
		land.setFont(f);
		JMenuItem addDelete = new JMenuItem("���/ɾ���û�");
		addDelete.setFont(f);
		JMenuItem exit = new JMenuItem("�˳�ϵͳ");
		exit.setFont(f);
		systemM.add(password);
		systemM.add(land);
		systemM.add(addDelete);
		systemM.add(exit);
		//ʵ����ϵͳ����˵��Ĳ˵������

//Ϊϵͳ����˵����¼�-----------------------------------------------------------------------------
        password.addActionListener(new ActionListener(){//�����޸ļ���
        	public void actionPerformed(ActionEvent e){
        		System.out.println("AmendPassword");
        		deskpane.add(new AmendPassword());
        		}
        	});

        land.addActionListener(new ActionListener(){//���µ�½����
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Land");
        		setVisible(false);
        		new Land();
        		}
        	});

        addDelete.addActionListener(new ActionListener(){//���/ɾ���û�����
        	public void actionPerformed(ActionEvent e){
        		deskpane.add(new AddDeleteUser());
        		}
        	});

        exit.addActionListener(new ActionListener(){//�˳�ϵͳ����
        	public void actionPerformed(ActionEvent e){
        		//new JOptionPane().showMessageDialog(
        		setVisible(false);
        		}
        	});
//--------------------------------------------------------------------------------------------
		
		//ʵ������Ϣ����Ĳ˵���
		JMenuItem departmentM = new JMenuItem("������Ϣ����");
		departmentM.setFont(f);
		JMenuItem employeeM = new JMenuItem("������Ϣ����");
		employeeM.setFont(f);
		JMenuItem trainM = new JMenuItem("��ѵ��Ϣ����");
		trainM.setFont(f);
		JMenuItem encouragementPunishM = new JMenuItem("������Ϣ����");
		encouragementPunishM.setFont(f);
		JMenuItem wageM =new JMenuItem("н����Ϣ����");
		wageM.setFont(f);
		employeeMM.add(trainM);
		employeeMM.add(employeeM);
		employeeMM.add(encouragementPunishM);
		employeeMM.add(wageM);
		manageM.add(employeeMM);
		manageM.add(departmentM);
		//ʵ������Ϣ����Ĳ˵������

//Ϊ����˵����¼�------------------------------------------------------------------------------
        departmentM.addActionListener(new ActionListener(){//������Ϣ�������
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Departmentmanage");
        		deskpane.add(new Departmentmanage());
        		}
        	});

        employeeM.addActionListener(new ActionListener(){//������Ϣ�������
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Employeemanage");
        		deskpane.add(new Employeemanage());
        		}
        	});

        trainM.addActionListener(new ActionListener(){//��ѵ��Ϣ�������
        	public void actionPerformed(ActionEvent e){
        		System.out.println("Trainmanage");
        		deskpane.add(new Trainmanage());
        		}
        	});

        encouragementPunishM.addActionListener(new ActionListener(){//������Ϣ�������
        	public void actionPerformed(ActionEvent e){
        		System.out.println("EncouragementPunish");
        		deskpane.add(new EncouragementPunish());
        		}
        	});

        wageM.addActionListener(new ActionListener(){//н����Ϣ�������
        	public void actionPerformed(ActionEvent e){
        		System.out.println("WageManage");
        		deskpane.add(new WageManage());
        		}
        	});
//-----------------------------------------------------------------------------------------------

		//ʵ������Ϣ��ѯ�Ĳ˵���
		
		JMenuItem departmentS = new JMenuItem("������Ϣ��ѯ");
		departmentS.setFont(f);
		JMenuItem employeeS = new JMenuItem("������Ϣ��ѯ");
		employeeS.setFont(f);
		JMenuItem trainS = new JMenuItem("��ѵ��Ϣ��ѯ");
		trainS.setFont(f);
		JMenuItem encouragementPunishS = new JMenuItem("������Ϣ��ѯ");
		encouragementPunishS.setFont(f);
		JMenuItem wageS =new JMenuItem("н����Ϣ��ѯ");
		wageS.setFont(f);
		employeeSM.add(trainS);
		employeeSM.add(employeeS);
		employeeSM.add(encouragementPunishS);
		employeeSM.add(wageS);
		selectM.add(employeeSM);
		selectM.add(departmentS);
		
		//ʵ������Ϣ��ѯ�Ĳ˵������

//Ϊ��ѯ�˵����¼�---------------------------------------------------------

		departmentS.addActionListener(new ActionListener(){//������Ϣ��ѯ����
			public void actionPerformed(ActionEvent e){
				System.out.println("DIQ");
				deskpane.add(new DIQ());
				}
			});


		employeeS.addActionListener(new ActionListener(){//������Ϣ��ѯ����
			public void actionPerformed(ActionEvent e){
				System.out.println("BIQ");
				deskpane.add(new BIQ());
				}
			});

		trainS.addActionListener(new ActionListener(){//��ѵ��Ϣ��ѯ����
			public void actionPerformed(ActionEvent e){
				System.out.println("TIQ");
				deskpane.add(new TIQ());
				}
			});

		encouragementPunishS.addActionListener(new ActionListener(){//������Ϣ��ѯ����
			public void actionPerformed(ActionEvent e){
				System.out.println("EPIQ");
				deskpane.add(new EPIQ());
				}
			});


		wageS.addActionListener(new ActionListener(){//н����Ϣ��ѯ����
			public void actionPerformed(ActionEvent e){
				System.out.println("SIQ");
				deskpane.add(new SIQ());
				}
			});

/*============================������Ҳ���Ժ���========================*/


		//JMenuItem game=new JMenuItem("����˹����");
		//JMenuItem countStatistic=new JMenuItem("");
		//game.setFont(f);
		//countStatistic.setFont(f);
        //statisticM.add(game);
        //statisticM.add(countStatistic);
        
//----------------����˹������Ϸ�¼�------����-----------------------

		//game.addActionListener(new ActionListener(){
        	//public void actionPerformed(ActionEvent e){
        	//	System.out.println("Game");
        	//	deskpane.add(new Game());
        	//	}
        	//});

/*============================������Ҳ���Ժ���========================*/



		JMenuItem help = new JMenuItem("����");
		help.setFont(f);
		JMenuItem about =new JMenuItem("����");
		about.setFont(f);
		helpM.add(help);
		aboutM.add(about);

//Ϊ�����˵����¼�-------------------------------------------------------------------------
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


//����ȫ���������ͼ��----------------------------------------------------------------------------------
    //����ͼ��
	    Image img=Toolkit.getDefaultToolkit().getImage("image\\main.gif");
	    setIconImage(img);
   //��Ӳ˵�ͼ��
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
		
//�������-------------------------------------------------------------------------------

   	    JToolBar jToolBar1 = new JToolBar();//����һ��������
   	    jToolBar1.setLayout(new GridLayout(9,1));//���ó����񲼾�
        JButton jButton1 = new JButton();//������ʵ������ť
        jButton1.setToolTipText("Ա��������Ϣ����");//���ð�ť��ͣ��Ϣ
        JButton jButton2 = new JButton();
        jButton2.setToolTipText("Ա��������Ϣ��ѯ");
        JButton jButton3 = new JButton();
        jButton3.setToolTipText("�޸�����");
        //JButton jButton4 = new JButton();
        JButton jButton5 = new JButton();
        jButton5.setToolTipText("������");
        JButton jButton6 = new JButton();
        jButton6.setToolTipText("�˳�ϵͳ");
        

        jToolBar1.setMaximumSize(new java.awt.Dimension(600, 50));//���ù��������ֵ
        jToolBar1.setMinimumSize(new java.awt.Dimension(600, 50));//���ù�������Сֵ
        
        //��ӹ������а�ť�ķ���
        
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
		
		
		//��ӹ������а�ť�ķ�������
		
		
		
        jToolBar1.setBounds(0, 0, 30, 600);//������λ��
        jToolBar1.setEnabled(false);//��ֹ���Ĵ�С
        con.add(jToolBar1,BorderLayout.WEST);//����



        //Label lp1=new Label("��  ӭ  ʹ  ��  Ա  ��  ��  ��  ϵ  ͳ !");
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


		//�̵߳ķ���
        	public void run(){
        	  System.out.println("�߳�������!");//�Ѻ���ʾ
        	  Toolkit t = Toolkit.getDefaultToolkit();
        	  int x=t.getScreenSize().width;
        	  System.out.println("x=" + x);

        	  //lp1.setFont( new Font("����",Font.ITALIC,"14"));
	          lp1.setForeground(Color.red);
		      while(true)
		         {
                     if(x<-600){
                     	x=t.getScreenSize().width;
                     	//System.out.println("xΪ:" + x);
                     	}
			         lp1.setBounds(x,0,700,20);
			         x-=10;
			         //System.out.println(x);
			         try{Thread.sleep(100);}catch(Exception e){}
		            //}
	           	 }
              }

	public static void main(String[] args){//������
		new Main();
		}
	}

/****************************************�������**********************************/