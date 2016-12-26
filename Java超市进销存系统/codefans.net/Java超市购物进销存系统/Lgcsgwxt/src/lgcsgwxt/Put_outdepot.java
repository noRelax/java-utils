package lgcsgwxt;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.*;
import javax.swing.JPanel;
import lgcsgwxt.dialog.Mytable;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import java.sql.Timestamp;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.Vector;
import javax.swing.table.JTableHeader;

/**
 * <p>Title: 鲁广超市进销存系统</p>
 *
 * <p>Description: 北大青鸟鲁广校区S1毕业设计</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: ST-117班</p>
 *
 * @author ST-117班第二小组
 * @version 1.0
 */
public class Put_outdepot extends JDialog {
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    JPanel panel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    JLabel jLabel2 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel3 = new JLabel();
    JComboBox jComboBox2 = new JComboBox();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JTextField jTextField2 = new JTextField();
    JLabel jLabel7 = new JLabel();
    JTextField jTextField3 = new JTextField();
    JLabel jLabel8 = new JLabel();
    JTextField jTextField4 = new JTextField();
    JLabel jLabel9 = new JLabel();

    JTable jTable1 = new JTable();
    JTableHeader jTableHeader1 = jTable1.getTableHeader();

    Vector colnames=new Vector();//表头
    Vector colnames1=new Vector();
    Vector colnames2=new Vector();


    JScrollPane jScrollPane1 = new JScrollPane();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JButton jButton4 = new JButton();


    public Put_outdepot(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Put_outdepot() {
        this(new Frame(), "JoinAndExceed", false);
        this.setSize(600,400);
    }

    private void jbInit() throws Exception {
        panel1.setLayout(null);
        this.getContentPane().setLayout(null);
        jLabel4.setBorder(BorderFactory.createEtchedBorder());
        jLabel4.setBounds(new Rectangle(29, 7, 536, 36));
        jLabel5.setText("货单录入");
        jLabel5.setBounds(new Rectangle(29, 54, 102, 26));
        jLabel6.setText("编号/条码：");
        jLabel6.setBounds(new Rectangle(43, 88, 68, 21));
        jTextField2.setBounds(new Rectangle(110, 88, 127, 21));
        jLabel7.setText("数量：");
        jLabel7.setBounds(new Rectangle(261, 88, 38, 21));
        jTextField3.setBounds(new Rectangle(295, 88, 68, 21));
        jLabel8.setText("进货单价：");
        jLabel8.setBounds(new Rectangle(392, 88, 68, 21));
        jTextField4.setBounds(new Rectangle(455, 88, 68, 21));
        jLabel9.setBorder(BorderFactory.createEtchedBorder());
        jLabel9.setBounds(new Rectangle(29, 80, 535, 41));
        jScrollPane1.setBounds(new Rectangle(29, 150, 535, 144));
        jButton1.setBounds(new Rectangle(38, 319, 111, 30));
        jButton1.setSelectedIcon(null);
        jButton1.setText("确   认");
        jButton2.setBounds(new Rectangle(170, 319, 111, 30));
        jButton2.setText("删除商品");
        jButton3.setBounds(new Rectangle(306, 319, 111, 30));
        jButton3.setText("撤   消");
        jButton4.setBounds(new Rectangle(441, 319, 111, 30));
        jButton4.setText("退出");
        this.getContentPane().add(panel1, null);
        jComboBox2.setBounds(new Rectangle(433, 14, 94, 20));
        jLabel3.setText("制单人：");
        jLabel3.setBounds(new Rectangle(380, 14, 84, 20));
        jTextField1.setBounds(new Rectangle(225, 14, 131, 20));
        jLabel2.setText("时间：");
        jLabel2.setBounds(new Rectangle(187, 14, 84, 20));
        jComboBox1.setBounds(new Rectangle(79, 14, 84, 20));
        this.getContentPane().add(jComboBox2);
        this.getContentPane().add(jTextField1);
        this.getContentPane().add(jComboBox1);
        this.getContentPane().add(jLabel1);
        this.getContentPane().add(jLabel2);
        this.getContentPane().add(jLabel3);
        this.getContentPane().add(jLabel4);
        this.getContentPane().add(jLabel5);
        this.getContentPane().add(jLabel6);
        this.getContentPane().add(jTextField2);
        this.getContentPane().add(jTextField3);
        this.getContentPane().add(jLabel7);
        this.getContentPane().add(jLabel8);
        this.getContentPane().add(jTextField4);
        this.getContentPane().add(jLabel9);
        this.getContentPane().add(jScrollPane1);
        this.getContentPane().add(jButton1);
        this.getContentPane().add(jButton2);
        this.getContentPane().add(jButton3);
        this.getContentPane().add(jButton4);
        jLabel1.setText("类别：");
        jLabel1.setBounds(new Rectangle(43, 14, 84, 20));
        panel1.setBounds(new Rectangle(0, 399, 1, 1));
        jComboBox1.addItem("  入  库 ");
        jComboBox1.addItem("  出  库 ");
        jComboBox2.addItem("郭健伟");
        jComboBox2.addItem("谭震欣");
        jComboBox2.addItem("徐敏");
        jTextField1.setText(ts.toString().substring(0,19));
        colnames.add("编号");
        colnames.add("商品名称");
        colnames.add("数量");
        colnames.add("进货价");
        colnames.add("金额");

        colnames1.add("");
        colnames1.add("");
        colnames1.add("");
        colnames1.add("");
        colnames1.add("");
          colnames2.add(colnames1);
     jTable1 = Mytable.maketable(colnames2, colnames); //显示内容
     jScrollPane1.getViewport().add(jTable1);//把表装入容器
    }
}
