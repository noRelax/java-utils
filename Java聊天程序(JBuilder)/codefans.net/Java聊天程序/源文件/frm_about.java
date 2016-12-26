package chat;

import java.awt.*;
import java.lang.Object;
import javax.swing.*;
import java.awt.event.*;

/**
 * <p>Title: 使用JAVA制作的局域网聊天程序</p>
 * <p>Description: 我们的JAVA作业</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author RoadAhead
 * @version 1.0
 */

public class frm_about extends JFrame
{
  private JLabel lab_icon = new JLabel();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JPanel jPanel1 = new JPanel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JButton jButton1 = new JButton();
  private Label label1 = new Label();

  public frm_about()
  {
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception
  {
    lab_icon.setBorder(BorderFactory.createEtchedBorder());
    lab_icon.setBounds(new Rectangle(9, 9, 135, 95));
    this.getContentPane().setBackground(Color.white);
    this.getContentPane().setLayout(null);
    jLabel1.setFont(new java.awt.Font("Serif", 1, 28));
    jLabel1.setToolTipText("");
    jLabel1.setText(" RoadAhead");
    jLabel1.setBounds(new Rectangle(154, 22, 155, 24));
    jLabel2.setFont(new java.awt.Font("Serif", 0, 12));
    jLabel2.setText("    局域网聊天工具 V1.0");
    jLabel2.setBounds(new Rectangle(156, 80, 131, 19));
    jPanel1.setBackground(Color.lightGray);
    jPanel1.setBorder(BorderFactory.createEtchedBorder());
    jPanel1.setBounds(new Rectangle(-5, 125, 333, 72));
    jPanel1.setLayout(null);
    jLabel3.setFont(new java.awt.Font("Serif", 0, 12));
    jLabel3.setText("制作:荆州市国家税务局");
    jLabel3.setBounds(new Rectangle(17, 19, 217, 18));
    jLabel5.setFont(new java.awt.Font("Serif", 0, 12));
    jLabel5.setText("感谢: JAVA 老师 谢颖");
    jLabel5.setBounds(new Rectangle(18, 39, 129, 18));
    jButton1.setBounds(new Rectangle(250, 39, 64, 22));
    jButton1.setFont(new java.awt.Font("Serif", 0, 12));
    jButton1.setText("关闭");
    jButton1.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        jButton1_actionPerformed(e);
      }
    });
    label1.setText("E-Mail: iamhwj@163.com");
    label1.setBounds(new Rectangle(169, 96, 147, 18));
    this.getContentPane().add(lab_icon, null);
    this.getContentPane().add(jLabel1, null);
    this.getContentPane().add(jPanel1, null);
    jPanel1.add(jButton1, null);
    jPanel1.add(jLabel5, null);
    jPanel1.add(jLabel3, null);
    this.getContentPane().add(label1, null);
    this.getContentPane().add(jLabel2, null);
    Icon icon1=new ImageIcon("tiger.gif");
    lab_icon.setIcon(icon1);
    this.setTitle("关于这个程序");
    this.setBounds(200,200,330,220);

  }

  void jButton1_actionPerformed(ActionEvent e)
  {
    this.dispose();
  }
}