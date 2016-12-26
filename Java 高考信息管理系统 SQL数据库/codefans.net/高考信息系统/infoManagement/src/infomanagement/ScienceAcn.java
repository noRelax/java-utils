package infomanagement;
//download by http://www.codefans.net
import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.sql.*;
import com.borland.dx.sql.dataset.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ScienceAcn extends JDialog {
  private JPanel jPanel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JLabel jLabel4 = new JLabel();
  private JTextField jTextField2 = new JTextField();
  private JLabel jLabel5 = new JLabel();
  private JTextField jTextField3 = new JTextField();
  private JLabel jLabel6 = new JLabel();
  private JTextField jTextField4 = new JTextField();
  private JLabel jLabel7 = new JLabel();
  private JTextField jTextField5 = new JTextField();
  private JLabel jLabel8 = new JLabel();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private Database database1 = new Database();

  public ScienceAcn(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public ScienceAcn() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    //
    //
    jPanel1.setLayout(xYLayout1);
    jLabel1.setForeground(Color.red);
    jLabel1.setBorder(BorderFactory.createEtchedBorder());
    jLabel1.setText("          理科成绩录入");
    jLabel2.setBorder(BorderFactory.createEtchedBorder());
    jLabel3.setForeground(Color.red);
    jLabel3.setText("     数学");
    jLabel4.setForeground(Color.red);
    jLabel4.setText("     英语");
    jLabel5.setForeground(Color.red);
    jLabel5.setText("     语文");
    jLabel6.setForeground(Color.red);
    jLabel6.setText("     物理");
    jLabel7.setForeground(Color.red);
    jLabel7.setText("     化学");
    jLabel8.setBorder(BorderFactory.createEtchedBorder());
    jButton1.setText("确定");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setText("取消");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    database1.setConnection(new com.borland.dx.sql.dataset.ConnectionDescriptor("jdbc:odbc:local", "sa", "", false, "sun.jdbc.odbc.JdbcOdbcDriver"));
    jTextField1.setText("0");
    jTextField2.setText("0");
    jTextField3.setText("0");
    jTextField4.setText("0");
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jLabel1,       new XYConstraints(0, 1, 400, 26));
    jPanel1.add(jLabel3,  new XYConstraints(20, 45, 90, 20));
    jPanel1.add(jTextField1,    new XYConstraints(140, 45, 120, 20));
    jPanel1.add(jLabel4,   new XYConstraints(20, 90, 90, 20));
    jPanel1.add(jTextField2,   new XYConstraints(140, 90, 120, 20));
    jPanel1.add(jLabel5,    new XYConstraints(20, 135, 90, 20));
    jPanel1.add(jTextField3,    new XYConstraints(140, 135, 120, 20));
    jPanel1.add(jLabel6,   new XYConstraints(20, 180, 90, 20));
    jPanel1.add(jTextField4,   new XYConstraints(140, 180, 120, 20));
    jPanel1.add(jLabel7,   new XYConstraints(20, 225, 90, 20));
    jPanel1.add(jTextField5,      new XYConstraints(140, 225, 120, 20));
    jPanel1.add(jLabel2, new XYConstraints(1, 26, 399, 275));
    jPanel1.add(jLabel8,    new XYConstraints(1, 255, 397, 44));
    jPanel1.add(jButton1,        new XYConstraints(200, 265, 90, 30));
    jPanel1.add(jButton2,      new XYConstraints(300, 265, 90, 30));
  }
     public int Math(){
       return Integer.parseInt(jTextField1.getText().trim()) ;
     }
     public int English(){
      return Integer.parseInt(jTextField2.getText().trim()) ;
     }
     public int Chinese(){
       return Integer.parseInt(jTextField3.getText().trim()) ;
     }
     public int Physics(){
       return Integer.parseInt(jTextField4.getText().trim()) ;
     }
     public int Chemistry(){
       return Integer.parseInt(jTextField5.getText().trim()) ;
     }
     void cancel(){
       dispose() ;
     }

  void jButton2_actionPerformed(ActionEvent e) {
    cancel() ;
  }

  void jButton1_actionPerformed(ActionEvent e) {
   ScienceAchievement scienceAchievement = new ScienceAchievement() ;
   int cardID = scienceAchievement.cardID() ;
   String studentName = scienceAchievement.studentName() ;
   String studentBefore = scienceAchievement.studentBefore() ;
   int Math = Math() ;
   int English = English() ;
   int Chinese = Chinese() ;
   int Physics = Physics() ;
   int Chemistry = Chemistry() ;

   int MathID = 1 ;
   int EnglishID = 2 ;
   int ChineseID = 3 ;
   int PhysicsID = 4 ;
   int ChemistryID = 5 ;

   String MathName = "数学" ;
   String EnglishName = "英语" ;
   String ChineseName = "语文" ;
   String PhysicsName = "物理" ;
   String ChemistryName = "化学" ;

   Statement sql = database1.createStatement() ;
   String stmt = "INSERT INTO [info].[dbo].[science](cardID,studentName,studentBefore)"+
                "VALUES('"+cardID+"', '"+studentName+"' , '"+studentBefore+"')" ;
   String stmt1 = "INSERT INTO [info].[dbo].[science2](cardID,subjectID,subject,achievement)"+
                 "VALUES('"+cardID+"','"+MathID+"','"+MathName+"','"+Math+"')" ;
   String stmt2 = "INSERT INTO [info].[dbo].[science2](cardID,subjectID,subject,achievement)"+
                  "VALUES('"+cardID+"','"+EnglishID+"','"+EnglishName+"','"+English+"')" ;
   String stmt3 = "INSERT INTO [info].[dbo].[science2](cardID,subjectID,subject,achievement)" +
                  "VALUES('"+cardID+"','"+ChineseID+"','"+ChineseName+"','"+Chinese+"')" ;
   String stmt4 = "INSERT INTO [info].[dbo].[science2](cardID,subjectID,subject,achievement)" +
                  "VALUES('"+cardID+"','"+PhysicsID+"','"+PhysicsName+"','"+Physics+"')" ;
   String stmt5 = "INSERT INTO [info].[dbo].[science2](cardID,subjectID,subject,achievement)" +
                  "VALUES('"+cardID+"','"+ChemistryID+"','"+ChemistryName+"','"+Chemistry+"')" ;
   try{
     sql.executeUpdate(stmt) ;
     sql.executeUpdate(stmt1) ;
     sql.executeUpdate(stmt2) ;
     sql.executeUpdate(stmt3) ;
     sql.executeUpdate(stmt4) ;
     sql.executeUpdate(stmt5) ;
   }
    catch(Exception www){}
  }
}