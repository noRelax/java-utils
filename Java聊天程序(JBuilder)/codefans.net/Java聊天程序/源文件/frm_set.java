package chat;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;


/**
 * <p>Title: 使用JAVA制作的局域网聊天程序</p>
 * <p>Description: 我们的JAVA作业</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author RoadAhead
 * @version 1.0
 */

public class frm_set extends JFrame
{
  private JScrollPane jsp = new JScrollPane();
  private JPanel pan_icon = new JPanel();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  String uicon="";
  private JPanel jPanel1 = new JPanel();
  private PaneLayout paneLayout1 = new PaneLayout();
  private Label label1 = new Label();
  private JTextField ed_uname = new JTextField();
  private JLabel lab_icon = new JLabel();
  private JLabel jLabel1 = new JLabel();
  private JCheckBox cbo_onstart = new JCheckBox();
  private JCheckBox cbo_winstate = new JCheckBox();
  private Label label2 = new Label();
  private JTextField ed_ly = new JTextField();

  public frm_set()
  {
    this.setTitle("设置用户参数");

    this.setBounds(200,150,390,300);
   // this.setBounds();
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    readicon();
  }
  private void jbInit() throws Exception
  {
    this.getContentPane().setLayout(paneLayout1);
    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    jButton1.setFont(new java.awt.Font("Serif", 0, 12));
    jButton1.setText("保存");
    jButton1.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setFont(new java.awt.Font("Serif", 0, 12));
    jButton2.setText("退出");
    jButton2.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton2_actionPerformed(e);
      }
    });

    jPanel1.setBackground(Color.white);
    jPanel1.setLayout(null);
    label1.setFont(new java.awt.Font("Serif", 0, 12));
    label1.setText("设置您的名称:");
    label1.setBounds(new Rectangle(6, 14, 75, 18));
    ed_uname.setBounds(new Rectangle(98, 10, 151, 22));
    lab_icon.setToolTipText("");
    lab_icon.setBounds(new Rectangle(98, 50, 58, 48));
    jLabel1.setFont(new java.awt.Font("Serif", 0, 12));
    jLabel1.setText("设置您的图标:");
    jLabel1.setBounds(new Rectangle(11, 57, 83, 33));
    cbo_onstart.setBackground(Color.white);
    cbo_onstart.setFont(new java.awt.Font("Serif", 0, 12));
    cbo_onstart.setText("每次启动时出现");
    cbo_onstart.setBounds(new Rectangle(185, 76, 109, 26));

    this.addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowOpened(WindowEvent e)
      {
        this_windowOpened(e);
      }
    });
    cbo_winstate.setBackground(Color.white);
    cbo_winstate.setFont(new java.awt.Font("Serif", 0, 12));
    cbo_winstate.setText("发送后最小窗口");
    cbo_winstate.setBounds(new Rectangle(186, 48, 108, 26));
    label2.setFont(new java.awt.Font("Serif", 0, 12));
    label2.setText("您离线时的留言:");
    label2.setBounds(new Rectangle(12, 97, 91, 18));
    ed_ly.setFont(new java.awt.Font("Serif", 0, 12));
    ed_ly.setToolTipText("");
    ed_ly.setText("对不起,我马上回来.....");
    ed_ly.setBounds(new Rectangle(11, 122, 330, 26));
    jsp.getViewport().add(pan_icon, null);
    jPanel1.add(ed_uname, null);
    jPanel1.add(label1, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(lab_icon, null);
    jPanel1.add(cbo_onstart, null);
    jPanel1.add(cbo_winstate, null);
    jPanel1.add(label2, null);
    jPanel1.add(ed_ly, null);
    this.getContentPane().add(jsp,   new PaneConstraints("jsp", "jsp", PaneConstraints.ROOT, 0.5f));
    this.getContentPane().add(jPanel1,   new PaneConstraints("jPanel1", "jsp", PaneConstraints.TOP, 0.58801496f));
    this.getContentPane().add(jButton1,   new PaneConstraints("jButton1", "jsp", PaneConstraints.BOTTOM, 0.3902439f));
    this.getContentPane().add(jButton2,   new PaneConstraints("jButton2", "jButton1", PaneConstraints.RIGHT, 0.3149351f));
  }//peocedure end


  void readicon()
  {
    String s;
    try
    {
    RandomAccessFile file = new RandomAccessFile("face/face.ini","r");
    long filepoint=0;
    long length=file.length();
    while(filepoint<length)
    {
      JButton aa=new JButton();
      s=file.readLine();
      Icon myicon=new ImageIcon("face/"+s);
      aa.setName(s);
      aa.setIcon(myicon);
      aa.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae)
        {
          uicon=ae.toString();
          uicon=uicon.substring(uicon.lastIndexOf("on")+3);
          Icon icon=new ImageIcon("face/"+uicon);
          uicon=uicon.substring(0,uicon.lastIndexOf("-"));
          lab_icon.setIcon(icon);
        }
      });
      pan_icon.add(aa);
      filepoint=file.getFilePointer();
    }
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(null, e.getMessage());
    }


  }

  void jButton1_actionPerformed(ActionEvent e)
  {
    String fs="";
    String us;
    String state;
    if (ed_uname.getText().trim().length()==0 )
    {
       JOptionPane.showMessageDialog(null, "请输入你的名称!");
       ed_uname.grabFocus();
       return;
    }
    if (uicon.trim()=="")
    {
      JOptionPane.showMessageDialog(null, "你还没有选择图标呢!");
      return;
    }
    if (cbo_onstart.isSelected())  us="1"; else us="0";
    if (cbo_winstate.isSelected())  state="1"; else state="0";
    fs=ed_uname.getText().trim()+"\n"+uicon+"\n"+us+"\n"+state+"\n"+ed_ly.getText().trim();
    ctools.WriteToSet(mainform.filename,fs);//保存到文件
    mainform.setmyicon(uicon,1);
    mainform.setmyname(ed_uname.getText().trim());
    mainform.winstate=state;
    JOptionPane.showMessageDialog(null, "设置已保存并已生效!");

  }

  void jButton2_actionPerformed(ActionEvent e)
  {
    this.dispose();
  }

  void this_windowOpened(WindowEvent e)
  {
    ed_uname.setText(mainform.getmyname());
    if(!mainform.getmyicon().equals(""))
    {
      Icon icon=new ImageIcon("face/"+mainform.getmyicon().trim()+"-1.gif");
      lab_icon.setIcon(icon);
      uicon=mainform.getmyicon();
    }
    if (mainform.onstartset.equals("1"))
    {
      cbo_onstart.setSelected(true);
    }
    if (mainform.winstate.equals("1"))
    {
      cbo_winstate.setSelected(true);
    }
    ed_ly.setText(mainform.onofflinetime);

  }//////////// procedure end





}