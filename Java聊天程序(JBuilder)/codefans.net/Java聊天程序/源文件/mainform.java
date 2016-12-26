package chat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.lang.Exception;
import java.net.*;
import java.io.*;
import java.util.Date;
import javax.swing.event.*;

/**
 * <p>Title: 使用JAVA制作的局域网聊天程序</p>
 * <p>Description: 我们的JAVA作业</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author RoadAhead
 * @version 1.0
 */

public class mainform extends JFrame
{
  private JPanel contentPane;
  static JPanel jPanel1 = new JPanel();
  private JPanel jPanel2 = new JPanel();
  private JButton but_online = new JButton();
  private BorderLayout borderLayout2 = new BorderLayout();
  private JMenuBar jMenuBar1 = new JMenuBar();
  static JSplitPane jsp8 = new JSplitPane();
  private JPanel jPanel5 = new JPanel();
  private JButton but_exit = new JButton();
  private JMenu jMenu1 = new JMenu();
  private JButton but_offline = new JButton();
  private JButton but_about = new JButton();
  private JPanel pan_state = new JPanel();
  static JTextPane ed_input = new JTextPane();
  private PaneLayout paneLayout1 = new PaneLayout();
  private TitledBorder titledBorder1;
  private TitledBorder titledBorder2;
  static Label lab_uname = new Label();
  static JLabel lab_uimage = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel lab_info = new JLabel();
  static final int PORT=5001;
  static String curuserip="";
  static String curusername="";
  static String curusericon="";
  static String myname="";
  static String myicon="";
  static String myip;
  static String onstartset="";
  static String filename="chatset.amy";
  static String winstate="0";
  static int maxStrCount=10000;
  static int onlinestate=2;//是否在线 1: 在线 2:断线 3: 离线
  static String onofflinetime=""; //当离线时的回答
  private JButton but_setup = new JButton();
  static String[][] userlist;//用户列表
  static JButton[] but_userlist; //用户图象按钮
  private JScrollPane jsp = new JScrollPane();
  static JPanel pan_userlist = new JPanel();
  private TitledBorder titledBorder3;
  private PaneLayout paneLayout2 = new PaneLayout();
  static JLabel lab_my = new JLabel();
  private TitledBorder titledBorder4;

  newthread mythread=new newthread();
  static DatagramPacket sendpacket,receivepacket;
  static DatagramSocket sendsocket,receivesocket;
  private JScrollPane jScrollPane1 = new JScrollPane();
  static JTextArea ed_show = new JTextArea();
  private JCheckBox cbo_bc = new JCheckBox();
  private JScrollPane jScrollPane2 = new JScrollPane();
  JOptionPane pane = new JOptionPane();
  Date nowtime=new Date();
  private JPopupMenu pop_menu = new JPopupMenu();
  private JMenuItem jMenuItem1 = new JMenuItem();
  private JMenuItem jMenuItem2 = new JMenuItem();
  private JButton but_pause = new JButton();
  private BoxLayout2 boxLayout21 = new BoxLayout2();
  private Label label1 = new Label();
  private Label label2 = new Label();
  private BorderLayout borderLayout1 = new BorderLayout();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();

  //Construct the frame
  public mainform()
  {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }
  //Component initialization
  private void jbInit() throws Exception
  {
    try{
      sendsocket=new DatagramSocket();
      receivesocket=new DatagramSocket(5001);
    }
    catch(SocketException se){
      se.printStackTrace();
    }

    mainform.userlist=new String[1][5];
    but_userlist=new JButton [1];

    //setIconImage(Toolkit.getDefaultToolkit().createImage(mainform.class.getResource("[Your Icon]")));
    contentPane = (JPanel) this.getContentPane();
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    titledBorder4 = new TitledBorder("");
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(523, 356));
    this.setTitle("我们的聊天程序 Alt+Enter 发送消息");
    this.addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowOpened(WindowEvent e)
      {
        this_windowOpened(e);
      }
      public void windowActivated(WindowEvent e)
      {
      }
    });
    jPanel1.setBackground(Color.white);
    jPanel1.setLayout(borderLayout2);
    jPanel2.setBackground(Color.white);
    jPanel2.setBorder(titledBorder4);
    jPanel2.setToolTipText("");
    jPanel2.setLayout(paneLayout2);
    but_online.setBackground(Color.white);
    but_online.setFont(new java.awt.Font("Serif", 0, 12));
    but_online.setBorder(null);
    but_online.setHorizontalTextPosition(SwingConstants.CENTER);
    but_online.setText("在线");
    but_online.setVerticalAlignment(SwingConstants.BOTTOM);
    but_online.setVerticalTextPosition(SwingConstants.BOTTOM);
    but_online.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseExited(MouseEvent e)
      {
        but_online_mouseExited(e);
      }
    });
    but_online.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
    {
      public void mouseMoved(MouseEvent e)
      {
        but_online_mouseMoved(e);
      }
    });
    but_online.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        but_online_actionPerformed(e);

      }
    });
    jsp8.setBorder(null);
    jsp8.setContinuousLayout(true);
    jsp8.setDividerSize(2);
    jsp8.setLeftComponent(pan_userlist);
    but_exit.setBackground(Color.white);
    but_exit.setFont(new java.awt.Font("Serif", 0, 12));
    but_exit.setBorder(null);
    but_exit.setHorizontalTextPosition(SwingConstants.CENTER);
    but_exit.setText("退出");
    but_exit.setVerticalAlignment(SwingConstants.BOTTOM);
    but_exit.setVerticalTextPosition(SwingConstants.BOTTOM);
    but_exit.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
    {
      public void mouseMoved(MouseEvent e)
      {
        but_exit_mouseMoved(e);
      }
    });
    but_exit.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mousePressed(MouseEvent e)
      {
        but_exit_mousePressed(e);
      }
      public void mouseExited(MouseEvent e)
      {
        but_exit_mouseExited(e);
      }
    });
    but_exit.addKeyListener(new java.awt.event.KeyAdapter()
    {
      public void keyTyped(KeyEvent e)
      {
        but_exit_keyTyped(e);
      }
    });
    jMenu1.setText("文件");
    but_offline.setBackground(Color.white);
    but_offline.setFont(new java.awt.Font("Serif", 0, 12));
    but_offline.setBorder(null);
    but_offline.setHorizontalTextPosition(SwingConstants.CENTER);
    but_offline.setText("断线");
    but_offline.setVerticalAlignment(SwingConstants.BOTTOM);
    but_offline.setVerticalTextPosition(SwingConstants.BOTTOM);
    but_offline.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseExited(MouseEvent e)
      {
        but_offline_mouseExited(e);
      }
    });
    but_offline.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
    {
      public void mouseMoved(MouseEvent e)
      {
        but_offline_mouseMoved(e);
      }
    });
    but_offline.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        but_offline_actionPerformed(e);
      }
    });
    but_about.setBackground(Color.white);
    but_about.setFont(new java.awt.Font("Serif", 0, 12));
    but_about.setBorder(null);
    but_about.setHorizontalTextPosition(SwingConstants.CENTER);
    but_about.setText("关于这个程序");
    but_about.setVerticalAlignment(SwingConstants.BOTTOM);
    but_about.setVerticalTextPosition(SwingConstants.BOTTOM);
    but_about.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseExited(MouseEvent e)
      {
        but_about_mouseExited(e);
      }
    });
    but_about.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
    {
      public void mouseMoved(MouseEvent e)
      {
        but_about_mouseMoved(e);
      }
    });
    but_about.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        but_about_actionPerformed(e);
      }
    });
    jPanel5.setLayout(paneLayout1);
    pan_state.setBackground(Color.orange);
    pan_state.setBorder(BorderFactory.createEtchedBorder());
    pan_state.setLayout(boxLayout21);
    contentPane.setFont(new java.awt.Font("Serif", 0, 12));
    contentPane.setBorder(BorderFactory.createLineBorder(Color.black));

    jLabel2.setBackground(Color.orange);
    jLabel2.setFont(new java.awt.Font("Serif", 0, 12));
    jLabel2.setText("                                ");
    ed_input.addKeyListener(new java.awt.event.KeyAdapter()
    {
      public void keyReleased(KeyEvent e)
      {

      }
      public void keyTyped(KeyEvent e)
      {
        ed_input_keyTyped(e);
      }
    });
    but_setup.setBackground(Color.white);
    but_setup.setFont(new java.awt.Font("Serif", 0, 12));
    but_setup.setBorder(null);
    but_setup.setHorizontalTextPosition(SwingConstants.CENTER);
    but_setup.setText("设置");
    but_setup.setVerticalAlignment(SwingConstants.BOTTOM);
    but_setup.setVerticalTextPosition(SwingConstants.BOTTOM);
    but_setup.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseExited(MouseEvent e)
      {
        but_setup_mouseExited(e);
      }
    });
    but_setup.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
    {
      public void mouseMoved(MouseEvent e)
      {
        but_setup_mouseMoved(e);
      }
    });
    but_setup.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        but_setup_actionPerformed(e);
      }
    });
    jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    jsp.setMinimumSize(new Dimension(50, 50));
    pan_userlist.setBackground(Color.white);
    pan_userlist.setMinimumSize(new Dimension(60, 60));
    pan_userlist.setLayout(verticalFlowLayout1);
    lab_my.setBackground(Color.white);
    lab_my.setHorizontalAlignment(SwingConstants.CENTER);
    lab_my.setHorizontalTextPosition(SwingConstants.CENTER);
    lab_my.setVerticalAlignment(SwingConstants.BOTTOM);
    lab_my.setVerticalTextPosition(SwingConstants.BOTTOM);
    cbo_bc.setBackground(Color.orange);
    cbo_bc.setFont(new java.awt.Font("Serif", 0, 12));
    cbo_bc.setText("广播方式");
    ed_show.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseReleased(MouseEvent e)
      {
        ed_show_mouseReleased(e);
      }
    });
    jMenuItem1.setText("清除内容");
    jMenuItem1.addMenuKeyListener(new javax.swing.event.MenuKeyListener()
    {
      public void menuKeyTyped(MenuKeyEvent e)
      {
      }
      public void menuKeyPressed(MenuKeyEvent e)
      {
      }
      public void menuKeyReleased(MenuKeyEvent e)
      {
        jMenuItem1_menuKeyReleased(e);
      }
    });
    jMenuItem2.setText("保存到文件");
    jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseReleased(MouseEvent e)
      {
        jMenuItem2_mouseReleased(e);
      }
    });
    but_pause.setBackground(Color.white);
    but_pause.setFont(new java.awt.Font("Serif", 0, 12));
    but_pause.setBorder(null);
    but_pause.setHorizontalTextPosition(SwingConstants.CENTER);
    but_pause.setText("离线");
    but_pause.setVerticalAlignment(SwingConstants.BOTTOM);
    but_pause.setVerticalTextPosition(SwingConstants.BOTTOM);
    but_pause.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
    {
      public void mouseMoved(MouseEvent e)
      {
        but_pause_mouseMoved(e);
      }
    });
    but_pause.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseExited(MouseEvent e)
      {
        but_pause_mouseExited(e);
      }
    });
    but_pause.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        but_pause_actionPerformed(e);
      }
    });
    label1.setFont(new java.awt.Font("Serif", 0, 12));
    label1.setText("      ");
    ed_show.setFont(new java.awt.Font("SansSerif", 0, 12));
    ed_input.setFont(new java.awt.Font("Serif", 0, 12));
    lab_uname.setFont(new java.awt.Font("Serif", 0, 12));
    lab_uimage.setFont(new java.awt.Font("Serif", 0, 12));
    lab_info.setFont(new java.awt.Font("Dialog", 0, 12));
    contentPane.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jsp8,  BorderLayout.CENTER);
    jsp8.add(jPanel5, JSplitPane.RIGHT);
    contentPane.add(jPanel2, BorderLayout.NORTH);
    jMenuBar1.add(jMenu1);
    pan_state.add(jLabel2, null);
    pan_state.add(lab_uname, null);
    pan_state.add(lab_uimage, null);
    pan_state.add(lab_info, null);
    pan_state.add(label1, null);
    pan_state.add(cbo_bc, null);
    jsp8.add(jsp, JSplitPane.LEFT);
    jsp.getViewport().add(pan_userlist, null);
    jScrollPane1.getViewport().add(ed_show, null);
    jScrollPane2.getViewport().add(ed_input, null);
    pop_menu.add(jMenuItem1);
    pop_menu.add(jMenuItem2);
    jPanel2.add(but_online,                     new PaneConstraints("but_online", "but_online", PaneConstraints.ROOT, 0.5f));
    jPanel2.add(but_offline,                     new PaneConstraints("but_offonline", "but_online", PaneConstraints.RIGHT, 0.87860084f));
    jPanel2.add(but_setup,                     new PaneConstraints("but_set", "but_offonline", PaneConstraints.RIGHT, 0.72196263f));
    jPanel2.add(but_pause,                     new PaneConstraints("jButton1", "but_offonline", PaneConstraints.LEFT, 0.5f));
    jPanel2.add(but_about,                     new PaneConstraints("but_about", "but_set", PaneConstraints.RIGHT, 0.8039867f));
    jPanel2.add(but_exit,                     new PaneConstraints("but_exit", "but_about", PaneConstraints.RIGHT, 0.76033056f));
    jPanel2.add(lab_my,                     new PaneConstraints("jLabel1", "but_exit", PaneConstraints.RIGHT, 0.6683938f));
    jPanel2.add(label2,     new PaneConstraints("label2", "jLabel1", PaneConstraints.LEFT, 0.380597f));
    jPanel5.add(pan_state, new PaneConstraints("jPanel3", "jPanel3", PaneConstraints.ROOT, 0.5f));
    jPanel5.add(jScrollPane1, new PaneConstraints("jScrollPane1", "jPanel3", PaneConstraints.TOP, 0.66549295f));
    jPanel5.add(jScrollPane2, new PaneConstraints("jScrollPane2", "jPanel3", PaneConstraints.BOTTOM, 0.47761196f));

    Icon icon1=new ImageIcon("online.gif");
    Icon icon2=new ImageIcon("offline.gif");
    Icon icon3=new ImageIcon("setup.gif");
    Icon icon4=new ImageIcon("about.gif");
    Icon icon5=new ImageIcon("exit.gif");
    Icon iconlk=new ImageIcon("lk.gif");
    Icon iconfh=new ImageIcon("fh.gif");

    but_online.setIcon(icon1);
    but_offline.setIcon(icon2);
    but_setup.setIcon(icon3);
    but_about.setIcon(icon4);
    but_exit.setIcon(icon5);
    but_pause.setIcon(iconlk);

    try
    {
      InetAddress localHost=InetAddress.getLocalHost();
      myip=localHost.getHostAddress();
    }
    catch(Exception aa)
    {

    }




  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e)
  {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING)
    {
      System.exit(0);
    }
  }//end procedure



  void ed_input_keyTyped(KeyEvent e)
  {
    if((mainform.onlinestate!=1)&(e.isAltDown()))
    {
      JOptionPane.showMessageDialog(null, "没有处于在线状态,不能发送消息!");
      return;
    }
    if ((!cbo_bc.isSelected())&(mainform.getuserip().trim()=="")&e.isAltDown())
    {
      JOptionPane.showMessageDialog(null,"你还没有选择给谁发消息!");
      return;
    }
    String userinput;
    userinput=ed_input.getText().trim();
    if (e.isAltDown()&(e.getKeyChar()=='\n'))
    {
      if (cbo_bc.isSelected())
        userinput=ctools.topack("16",userinput);
      else
        userinput=ctools.topack("06",userinput);
        ed_show.append("我对"+curusername+"说："+ed_input.getText());
      ctools.senddata(mainform.getuserip(),userinput);
      ed_input.setText("");

      if(winstate.equals("1"))
        this.setState(ICONIFIED);
    }

  }//end procedure

  void but_setup_actionPerformed(ActionEvent e)
  {
    frm_set fw=new frm_set();
    fw.setVisible(true);
  } //procedurte end


static void setuserip(String ip)
 {
  curuserip=ip;
 }//end procedure

static String getuserip()
{
  return(curuserip);
}//end procedure


static void setusername(String name)
{
  curusername=name;
  lab_uname.setText("当前用户名: "+name);
}//end procedure


static String getusername()
{
  return(curusername);
}//end procedure


static void setusericon(String icon,String state)
{
  curusericon=icon;
  Icon myicon=new ImageIcon("face/"+icon.trim()+"-1.gif");
  lab_uimage.setIcon(myicon);
}//end procedure

static String getusericon()
{
  return(curusericon);
}




static void setmyname(String name)
{
  myname=name;
  lab_my.setText(" "+name+" ");
}//end procedure


static String getmyname()
{
  return(myname);
}//end procedure


static void setmyicon(String icon,int state)
{
  myicon=icon;
  Icon myicon=new ImageIcon("face/"+icon.trim()+"-1.gif");
  lab_my.setIcon(myicon);
}//end procedure

static String getmyicon()
{
  return(myicon);
}




  void but_exit_keyTyped(KeyEvent e)
  {
 System.exit(0);
  }

  void but_exit_mousePressed(MouseEvent e)
  {
 System.exit(0);
  }

  void this_windowOpened(WindowEvent e)
  {
    String r;
    r=ctools.readfromset(filename).trim();
    if (r.equals("1"))
    {
      frm_set fs=new frm_set();
      fs.show(true);
    }

  } //end procedure

  void but_online_actionPerformed(ActionEvent e)
  {
    if (onlinestate==2)
    {
      //修改
      if (!mythread.isAlive() )
        mythread.start();
      else
        mythread.resume();
      //end
      String s;
      s=ctools.topack("11",myname,myicon);
      ctools.senddata("255.255.255.255",s); //户发出上线通知
      onlinestate=1;
      lab_info.setText("已经连接!");
    }
    onlinestate=1;
  }




  void but_about_actionPerformed(ActionEvent e)
  {
    frm_about fa=new frm_about();
    fa.show();
  }

class newthread extends Thread
{
  public void run()
  {
   String data="";
   String ip="";
   String type;
   while(true)
   {
     try{
       byte[] array=new byte[maxStrCount];
       receivepacket=new DatagramPacket(array,array.length);
       receivesocket.receive(receivepacket);
       ip=receivepacket.getAddress().toString();
       ip=ip.substring(ip.indexOf("/")+1,ip.length()).trim();  //取出IP地址

       //如果是收到自己的消息
       if (ip.equals(mainform.myip))
       {
         continue;
       }


       data=ctools.bytetostr(receivepacket.getData(),receivepacket.getLength());
       type=data.substring(0,2);
       switch(Integer.parseInt(type))
       {
         case 2:  //当有用户发出上线通知时
         {
           String tname;
           String ticon;
           int idx;
           int bh;

           tname=data.substring(2,22).trim();
           ticon=data.substring(22,27).trim();
           idx=ctools.scanlist(ip);
           if (idx<0)
           {
             ctools.addtolist(tname,ticon,ip,"1");
           };
           bh=ctools.scanlist(ip);
           if (bh>=0)
           {
             ctools.setuserstate(bh,"1");
           }
           break;
         }
         case 3: //收到某用户断线的消息
         {
           int bh;
           bh=ctools.scanlist(ip);
           if (bh>=0)
           {
             ctools.setuserstate(bh,"2");
           }
           break;
         }
         case 4://离线
         {
           int bh;
           bh=ctools.scanlist(ip);
           if (bh>=0)
           {
             ctools.setuserstate(bh,"3");
           }
           mainform.onlinestate=3;
           break;
         }
         case 6://聊天消息
         {
           String msg;
           String tname;
           int idx;
           msg=data.substring(2,data.length());
           idx=ctools.scanlist(ip);
           if (idx>0)
             tname=mainform.userlist[idx][1].trim();
           else
           {
             tname="未知用户(正在查询): ";
             ctools.senddata(ip,"12");
           }
           ed_show.append(nowtime.toLocaleString()+"  "+tname+": "+msg+'\n');
           if(onlinestate==3) //如用户为离线状态,则自动发送消息
           {
             ctools.senddata(ip,"06"+onofflinetime);
           }
           break;
         }
         case 11:  //当有用户发出上线通知时
         {
           String tname;
           String ticon;
           int idx;
           int bh;

           tname=data.substring(2,22).trim();
           ticon=data.substring(22,27).trim();
           idx=ctools.scanlist(ip);
           if (idx<0)
           {
             ctools.addtolist(tname,ticon,ip,"1");
           };
           String hpack=ctools.topack("02",myname,myicon);
           ctools.senddata(ip,hpack);
           bh=ctools.scanlist(ip);
           if (bh>=0)
           {
             ctools.setuserstate(bh,"1");
           }
           break;
         }
         case 12://收到询问自已信息的消息
         {
           String ts;
           ts=ctools.topack("11",myname,myicon);
           ctools.senddata(ip,ts);
           break;
         }
         case 14:  //重新上线
         {
           int bh;
           bh=ctools.scanlist(ip);
           if (bh>=0)
           {
             ctools.setuserstate(bh,"1");
           }
           else
             ctools.senddata(ip,"12");
           mainform.onlinestate=1;
           break;
         }
       case 16: //收到广播消息
       {
         String msg;
         String tname;
         int idx;

         msg=data.substring(2,data.length());
         idx=ctools.scanlist(ip);
         if (idx>0)
           tname=mainform.userlist[idx][1].trim();
         else
         {
           tname="未知用户(正在查询): ";
           ctools.senddata(ip,"12");
         }

         ed_show.append(nowtime.toLocaleString()+"  "+tname+"* "+msg+'\n');
         break;
       }
       }//end case
     }
     catch(IOException se){
   //    lab_info.setText(se.toString());
     }
   }
  }
}

  void but_offline_actionPerformed(ActionEvent e)
  {
    if (onlinestate!=2)
    {
//      mythread.stop(); // 由于演示需要,线程未停止,所以再次启动会出错
      mythread.suspend();
//      String hpack;
      ctools.senddata("255.255.255.255","03");
      mainform.onlinestate=2;
      lab_info.setText("已经下线,你不能再收发消息!");

    }
  }

  void but_online_mouseMoved(MouseEvent e)
  {
    but_online.setBorder(BorderFactory.createRaisedBevelBorder());
  }

  void but_offline_mouseMoved(MouseEvent e)
  {
    but_offline.setBorder(BorderFactory.createRaisedBevelBorder());

  }

  void but_setup_mouseMoved(MouseEvent e)
  {
    but_setup.setBorder(BorderFactory.createRaisedBevelBorder());

  }

  void but_about_mouseMoved(MouseEvent e)
  {
    but_about.setBorder(BorderFactory.createRaisedBevelBorder());

  }

  void but_exit_mouseMoved(MouseEvent e)
  {
    but_exit.setBorder(BorderFactory.createRaisedBevelBorder());

  }

  void but_online_mouseExited(MouseEvent e)
  {
    but_online.setBorder(BorderFactory.createEmptyBorder());

  }

  void but_offline_mouseExited(MouseEvent e)
  {
    but_offline.setBorder(BorderFactory.createEmptyBorder());

  }

  void but_setup_mouseExited(MouseEvent e)
  {
    but_setup.setBorder(BorderFactory.createEmptyBorder());

  }

  void but_about_mouseExited(MouseEvent e)
  {
    but_about.setBorder(BorderFactory.createEmptyBorder());

  }

  void but_exit_mouseExited(MouseEvent e)
  {
    but_exit.setBorder(BorderFactory.createEmptyBorder());

  }


  void ed_show_mouseReleased(MouseEvent e)
  {
    if (e.isPopupTrigger())
    {
      pop_menu.show(e.getComponent(),e.getX(),e.getY());
    }
  }

  void jMenuItem1_menuKeyReleased(MenuKeyEvent e)
  {
    mainform.ed_show.setText("");
  }

  void jMenuItem2_mouseReleased(MouseEvent e)
  {
     JFileChooser fc = new JFileChooser();
    if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(this)) {
      ctools.WriteToSet(fc.getSelectedFile().getPath(),ed_show.getText());
    }

  }

  void but_pause_actionPerformed(ActionEvent e)
  {

    if (onlinestate==1)
    {
      Icon iconfh=new ImageIcon("fh.gif");
      but_pause.setIcon(iconfh);
      String hpack;
      ctools.senddata("255.255.255.255","04");
      mainform.onlinestate=3;
      lab_info.setText("暂时离线");
      but_pause.setText("返回");
      return;
    }
    if (onlinestate==3)
    {
      Icon iconlk=new ImageIcon("lk.gif");
      but_pause.setIcon(iconlk);
      String hpack;
      ctools.senddata("255.255.255.255","14");
      mainform.onlinestate=1;
      lab_info.setText("上线");
      but_pause.setText("离开");
      return;
    }

  }

  void but_pause_mouseExited(MouseEvent e)
  {
    but_pause.setBorder(BorderFactory.createEmptyBorder());

  }

  void but_pause_mouseMoved(MouseEvent e)
  {
    but_pause.setBorder(BorderFactory.createRaisedBevelBorder());

  }






}