package uml_gaokai;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class AdminWindow extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private JButton jButton4 = new JButton();
  private JButton jButton5 = new JButton();

  public AdminWindow(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public AdminWindow() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    jButton1.setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
    jButton1.setText("顾客登录");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
    jButton2.setText("采购员登录");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
    jButton3.setText("销售员登录");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jButton4.setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
    jButton4.setText("经理登录");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    jButton5.setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
    jButton5.setText("用户管理");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    panel1.add(jButton1,  new XYConstraints(0, 0, -1, 35));
    panel1.add(jButton4,    new XYConstraints(281, 1, 100, 33));
    panel1.add(jButton5,       new XYConstraints(376, 1, 93, 33));
    panel1.add(jButton3, new XYConstraints(182, 1, 98, 33));
    panel1.add(jButton2,   new XYConstraints(84, 1, 100, 33));
  }

//userManagement 用户管理（）涉及到用户的增删改
  void jButton5_actionPerformed(ActionEvent e) {
    UserManagement userManagement = new UserManagement() ;
    Dimension dlgSize = userManagement.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    userManagement.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    userManagement.setModal(true);
    userManagement.pack();
    userManagement.show();
  }
//admin作为顾客登录
  void jButton1_actionPerformed(ActionEvent e) {
    CustomerWindow customerWindow =new CustomerWindow() ;
    Dimension dlgSize = customerWindow.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    customerWindow.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    customerWindow.setModal(true);
    customerWindow.pack();
    customerWindow.show();
  }
  //admin作为采购员登录
  void jButton2_actionPerformed(ActionEvent e) {
    BuyerWindow buyerWindow =new BuyerWindow() ;
    Dimension dlgSize = buyerWindow.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    buyerWindow.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    buyerWindow.setModal(true);
    buyerWindow.pack();
    buyerWindow.show();
  }
  //admin作为销售员登录
  void jButton3_actionPerformed(ActionEvent e) {
    SalesWindow salesWindow =new SalesWindow() ;
    Dimension dlgSize = salesWindow.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    salesWindow.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    salesWindow.setModal(true);
    salesWindow.pack();
    salesWindow.show();
  }
  //admin作为经理登录
  void jButton4_actionPerformed(ActionEvent e) {
    ManagementWindow managementWindow =new ManagementWindow() ;
    Dimension dlgSize = managementWindow.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    managementWindow.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    managementWindow.setModal(true);
    managementWindow.pack();
    managementWindow.show();
  }
}