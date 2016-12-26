package uml_gaokai;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import com.borland.dx.sql.dataset.*;
import com.borland.dbswing.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class SalesWindow extends JDialog {
  private Database database1 = new Database();
  private QueryDataSet queryDataSet1 = new QueryDataSet();
  private JPanel jPanel1 = new JPanel();
  private JdbTable jdbTable1 = new JdbTable();
  private XYLayout xYLayout1 = new XYLayout();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();

  public SalesWindow(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public SalesWindow() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    database1.setConnection(new com.borland.dx.sql.dataset.ConnectionDescriptor("jdbc:odbc:gaokaiSQL", "sa", "", false, "sun.jdbc.odbc.JdbcOdbcDriver"));
    jPanel1.setLayout(xYLayout1);
    jButton1.setText("查看销售信息");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setText("退出");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    queryDataSet1.setQuery(new com.borland.dx.sql.dataset.QueryDescriptor(database1, "SELECT vendition_TABLE.VenditionID,vendition_TABLE.GoodsName,vendition_TABLE.GoodsCustomNumber,vendit" +
      "ion_TABLE.CustomerName,vendition_TABLE.VenditionDate,vendition_TABLE.GoodsSize,vendition_TABLE.GoodsP" +
      "rice FROM UML_CS.dbo.vendition_TABLE", null, true, Load.ALL));
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jdbTable1,        new XYConstraints(-1, 0, 336, 263));
    jPanel1.add(jButton1,   new XYConstraints(13, 274, 121, 21));
    jPanel1.add(jButton2,   new XYConstraints(232, 275, 94, 20));
  }

  void jButton1_actionPerformed(ActionEvent e) {
    jdbTable1.setDataSet(queryDataSet1);
    queryDataSet1.refresh();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    dispose() ;
  }
}