package uml_gaokai;
//download by http://www.codefans.net
import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import com.borland.dx.sql.dataset.*;
import java.awt.event.*;
import java.sql.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class AddOrderForm extends JDialog {
  private JPanel panel1 = new JPanel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JPanel jPanel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField2 = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JTextField jTextField3 = new JTextField();
  private JLabel jLabel4 = new JLabel();
  private JTextField jTextField4 = new JTextField();
  private JLabel jLabel5 = new JLabel();
  private JTextField jTextField5 = new JTextField();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private Database database1 = new Database();

  public AddOrderForm(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public AddOrderForm() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    jPanel1.setLayout(xYLayout1);
    jLabel1.setText("商品名称");
    jLabel2.setText("订货数量");
    jLabel3.setText("订货厂商");
    jLabel4.setText("订货日期");
    jLabel5.setText("订货状态");
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
    database1.setConnection(new com.borland.dx.sql.dataset.ConnectionDescriptor("jdbc:odbc:gaokaiSQL", "sa", "", false, "sun.jdbc.odbc.JdbcOdbcDriver"));
    getContentPane().add(panel1);
    panel1.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jLabel1,   new XYConstraints(11, 24, 81, 20));
    jPanel1.add(jTextField1,      new XYConstraints(93, 23, 133, 23));
    jPanel1.add(jLabel2,   new XYConstraints(11, 68, 78, 22));
    jPanel1.add(jTextField2,       new XYConstraints(93, 67, 133, 25));
    jPanel1.add(jLabel3,     new XYConstraints(11, 113, 79, 23));
    jPanel1.add(jTextField3,     new XYConstraints(93, 113, 132, 24));
    jPanel1.add(jLabel4,  new XYConstraints(11, 155, 66, 24));
    jPanel1.add(jTextField4,     new XYConstraints(93, 152, 134, 26));
    jPanel1.add(jLabel5,  new XYConstraints(8, 200, 76, 26));
    jPanel1.add(jTextField5,   new XYConstraints(93, 198, 136, 25));
    jPanel1.add(jButton1,  new XYConstraints(317, 140, 83, 24));
    jPanel1.add(jButton2,   new XYConstraints(316, 191, 84, 24));
  }
  //增加订货信息
  void jButton1_actionPerformed(ActionEvent e) {
    try{
    String BuyerGoodsName = jTextField1.getText() ;
    String BuyerGoodsNumber = jTextField2.getText() ;
    String BuyerManufacturer = jTextField3.getText() ;
    String BuyerDate = jTextField4.getText() ;
    String BuyerStatus = jTextField5.getText() ;
    Statement sql = database1.createStatement() ;
    sql.executeUpdate("INSERT INTO [UML_CS].[dbo].[Buyer_TABLE](BuyerGoodsName,BuyerGoodsNumber,BuyerManufacturer,BuyerDate,BuyerStatus) VALUES ('"+BuyerGoodsName+"','"+BuyerGoodsNumber+"','"+BuyerManufacturer+"','"+BuyerDate+"','"+BuyerStatus+"')") ;
    dispose() ;
    }
    catch(SQLException www){
    www.printStackTrace();
    }
  }

  void jButton2_actionPerformed(ActionEvent e) {
    dispose() ;
  }
}