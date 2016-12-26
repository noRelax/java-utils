package lgcsgwxt.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.sql.*;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import lgcsgwxt.dialog.Mytable;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lgcsgwxt.DBAccess;
import lgcsgwxt.means.Put_SQL;
import javax.swing.JOptionPane;
import java.util.TimerTask;
import java.util.Timer;
import lgcsgwxt.means.GetTime;
import lgcsgwxt.means.Insert_stock;
import lgcsgwxt.means.Select_Means;
import lgcsgwxt.means.UpdateData;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;

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
public class Put_Out_Depot extends JDialog {
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

    Vector colnames = new Vector(); //表头
    Vector colnames1 = new Vector();
    Vector colnames2 = new Vector();


    JScrollPane jScrollPane1 = new JScrollPane();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JButton jButton4 = new JButton();
    JTextField jTextField6 = new JTextField();
    JTextField jTextField7 = new JTextField();
    JLabel jLabel10 = new JLabel();
    JLabel jLabel11 = new JLabel();
    JLabel jLabel12 = new JLabel();


    public Put_Out_Depot(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Put_Out_Depot() {
        this(new Frame(), "JoinAndExceed", false);
        this.setSize(665, 480);
    }

    private void jbInit() throws Exception {
        panel1.setLayout(null);
        this.getContentPane().setLayout(null);
        jLabel4.setBorder(BorderFactory.createEtchedBorder());
        jLabel4.setBounds(new Rectangle(31, 7, 603, 36));
        jLabel5.setText("货单录入");
        jLabel5.setBounds(new Rectangle(29, 54, 102, 26));
        jLabel6.setText("商品编号：");
        jLabel6.setBounds(new Rectangle(64, 89, 68, 21));
        jTextField2.setBounds(new Rectangle(131, 89, 130, 21));
        jTextField2.addFocusListener(new Put_Out_Depot_jTextField2_focusAdapter(this));
        jLabel7.setText("数  量：");
        jLabel7.setBounds(new Rectangle(293, 89, 54, 21));
        jTextField3.setBounds(new Rectangle(343, 89, 72, 21));
        jLabel8.setText("进货单价：");
        jLabel8.setBounds(new Rectangle(440, 89, 68, 21));
        jTextField4.setBounds(new Rectangle(503, 89, 84, 21));
        jLabel9.setBorder(BorderFactory.createEtchedBorder());
        jLabel9.setBounds(new Rectangle(31, 82, 603, 58));
        jScrollPane1.setBounds(new Rectangle(29, 150, 603, 248));
        jButton1.setBounds(new Rectangle(74, 409, 111, 30));
        jButton1.setSelectedIcon(null);
        jButton1.setText("确   认");
        jButton1.addActionListener(new Put_Out_Depot_jButton1_actionAdapter(this));

        jButton2.setBounds(new Rectangle(206, 409, 111, 30));
        jButton2.setText("删  除");
        jButton2.addActionListener(new Put_Out_Depot_jButton2_actionAdapter(this));
        jButton3.setBounds(new Rectangle(342, 409, 111, 30));
        jButton3.setText("重   置");
        jButton3.addActionListener(new Put_Out_Depot_jButton3_actionAdapter(this));
        jButton4.setBounds(new Rectangle(477, 409, 111, 30));
        jButton4.setText("提交");
        jButton4.addActionListener(new Put_Out_Depot_jButton4_actionAdapter(this));
        this.getContentPane().setBackground(Color.pink);
        jTextField6.setEnabled(false);
        jTextField6.setBounds(new Rectangle(343, 114, 72, 21));
        jTextField7.setBounds(new Rectangle(503, 114, 83, 21));
        jLabel10.setText("仓 库 号：");
        jLabel10.setBounds(new Rectangle(64, 114, 68, 15));
        jLabel11.setText("总金额：");
        jLabel11.setBounds(new Rectangle(293, 114, 48, 15));
        jLabel12.setText("经 手 人：");
        jLabel12.setBounds(new Rectangle(440, 114, 60, 15));
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 0, 1000); //得到当前时间
        jComboBox3.setBounds(new Rectangle(131, 114, 130, 21));
        this.getContentPane().add(panel1, null);
        jComboBox2.setBounds(new Rectangle(488, 14, 94, 20));
        jLabel3.setText("制单人：");
        jLabel3.setBounds(new Rectangle(426, 15, 59, 20));
        jTextField1.setBounds(new Rectangle(280, 14, 131, 20));
        jLabel2.setText("时间：");
        jLabel2.setBounds(new Rectangle(242, 14, 84, 20));
        jComboBox1.setBounds(new Rectangle(134, 14, 84, 20));
        this.getContentPane().add(jLabel5);
        this.getContentPane().add(jScrollPane1);
        this.getContentPane().add(jTextField2);
        this.getContentPane().add(jLabel12);
        this.getContentPane().add(jLabel8);
        this.getContentPane().add(jTextField6);
        this.getContentPane().add(jTextField3);
        this.getContentPane().add(jLabel7);
        this.getContentPane().add(jLabel6);
        this.getContentPane().add(jTextField4);
        this.getContentPane().add(jLabel11);
        this.getContentPane().add(jTextField7);
        this.getContentPane().add(jLabel10);
        this.getContentPane().add(jComboBox1);
        this.getContentPane().add(jComboBox2);
        this.getContentPane().add(jLabel3);
        this.getContentPane().add(jTextField1);
        this.getContentPane().add(jLabel2);
        this.getContentPane().add(jLabel1);
        this.getContentPane().add(jLabel4);
        this.getContentPane().add(jButton2);
        this.getContentPane().add(jButton1);
        this.getContentPane().add(jButton3);
        this.getContentPane().add(jButton4);
        this.getContentPane().add(jComboBox3);
        this.getContentPane().add(jLabel9);
        jLabel1.setText("类别：");
        jLabel1.setBounds(new Rectangle(98, 14, 84, 20));
        panel1.setBounds(new Rectangle(0, 399, 1, 1));
        jComboBox1.addItem("入库");
        jComboBox1.addItem("出库");
        jComboBox2.addItem("郭健伟");
        jComboBox2.addItem("谭震欣");
        jComboBox2.addItem("徐敏");

        jComboBox3.addItem("CK0001");
        jComboBox3.addItem("CK0002");
        jComboBox3.addItem("CK0003");
        jTextField1.setText(ts.toString().substring(0, 19));
        colnames.add("编号");
        colnames.add("数量");
        colnames.add("进货价");
        colnames.add("总金额");
        colnames.add("日期");
        colnames.add("仓库号");
        colnames.add("经手人");
        colnames.add("填单人");
        colnames2.add(colnames1);
        jTable1 = Mytable.maketable(colnames2, colnames); //显示内容
        jScrollPane1.getViewport().add(jTable1); //把表装入容器
    }

    class RemindTask extends TimerTask {
        public void run() {
            jTextField1.setText(GetTime.getTime());
        }
    }


    public void InOut_Enter(String inout) {
        Vector select1 = new Vector();
        select1 = Select_Means.Select_sort2(jTextField2.getText());
        if (select1.size() == 0) {
            JOptionPane.showMessageDialog(this, "商品不存在", "错误",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }
        //处理数据
        Vector insertData = new Vector();

        int i = 0;
        while (i < vector1.size()) {
            insertData = (Vector) vector1.get(i);
            Integer num1 = new Integer(insertData.get(1).toString());
            Double num2 = new Double(insertData.get(2).toString());
            if(inout.equals("1")){
                UpdateData.in_StockPile(num1,insertData.get(0).toString());
            }
            if(inout.equals("0")){
                 UpdateData.out_StockPile(num1,insertData.get(0).toString());
             }

            int j = Insert_stock.Insert_EnterStock(insertData.get(0).toString(),
                    num1, num2, insertData.get(4).toString(),
                    insertData.get(5).toString(), insertData.get(6).toString(),
                    insertData.get(7).toString(), inout);
            if (j == 0) {
                i++;
                String str = "第" + i + "条记录数据写入失败";
                JOptionPane.showMessageDialog(this, str, "错误",
                                              JOptionPane.ERROR_MESSAGE);
                return;
            }
            i++;
            if (i == vector1.size()) {
                JOptionPane.showMessageDialog(this, "数据录入成功", "提示",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    public void jButton4_actionPerformed(ActionEvent e) {
        if (jComboBox1.getSelectedItem().toString().equals("入库")) {
            InOut_Enter("1");
        }
        if (jComboBox1.getSelectedItem().toString().equals("出库")) {
            InOut_Enter("0");
        }
    }

    Vector vector1 = new Vector();
    JComboBox jComboBox3 = new JComboBox();
    public void jButton1_actionPerformed(ActionEvent e) {
        Vector vector = new Vector();
        double allMoney = 0;
        vector.add(jTextField2.getText());
        vector.add(jTextField3.getText());
        vector.add(jTextField4.getText());
        try {
            Double num = new Double(jTextField3.getText());
            Double money = new Double(jTextField4.getText());
            allMoney = (int) (num * money * 100) / 100.0;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "输入的数字有误", "错误",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }
        Double dd = new Double(allMoney);
        jTextField6.setText(dd.toString());
        vector.add(dd.toString());
        vector.add(jTextField1.getText());
        vector.add(jComboBox3.getSelectedItem().toString());
        vector.add(jTextField7.getText());
        vector.add(jComboBox2.getSelectedItem().toString());
        vector1.add(vector);
        jTable1 = Mytable.maketable(vector1, colnames); //显示内容
        jScrollPane1.getViewport().add(jTable1); //把表装入容器
    }

    public void jButton2_actionPerformed(ActionEvent e) {
        int row = jTable1.getSelectedRow();
        vector1.remove(row);
        jTable1 = Mytable.maketable(vector1, colnames); //显示内容
        jScrollPane1.getViewport().add(jTable1); //把表装入容器
    }

    public void jButton3_actionPerformed(ActionEvent e) {
        vector1.removeAllElements();
        jTable1 = Mytable.maketable(vector1, colnames); //显示内容
        jScrollPane1.getViewport().add(jTable1); //把表装入容器
    }

    public void jTextField2_focusLost(FocusEvent e) {
        if (jTextField1.getText().length() != 0) {
             Vector select1 = Select_Means.Select_sort2(jTextField2.getText());
             Vector select2 = new Vector();
             if (select1.size() == 0) {
                 JOptionPane.showMessageDialog(this, "商品不存在", "错误",
                                               JOptionPane.ERROR_MESSAGE);
                 return;
             } else {
                 select2 = (Vector) select1.get(0);
                 Double pice=new Double(select2.get(4).toString());
                 double pice1=((int)(pice*100))/100.0;
                  Double pice2=new Double(pice1);
                 jTextField4.setText(pice2.toString()); //单价
             }
         }

    }


}


class Put_Out_Depot_jTextField2_focusAdapter extends FocusAdapter {
    private Put_Out_Depot adaptee;
    Put_Out_Depot_jTextField2_focusAdapter(Put_Out_Depot adaptee) {
        this.adaptee = adaptee;
    }

    public void focusLost(FocusEvent e) {
        adaptee.jTextField2_focusLost(e);
    }
}


class Put_Out_Depot_jButton3_actionAdapter implements ActionListener {
    private Put_Out_Depot adaptee;
    Put_Out_Depot_jButton3_actionAdapter(Put_Out_Depot adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton3_actionPerformed(e);
    }
}


class Put_Out_Depot_jButton2_actionAdapter implements ActionListener {
    private Put_Out_Depot adaptee;
    Put_Out_Depot_jButton2_actionAdapter(Put_Out_Depot adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}


class Put_Out_Depot_jButton1_actionAdapter implements ActionListener {
    private Put_Out_Depot adaptee;
    Put_Out_Depot_jButton1_actionAdapter(Put_Out_Depot adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}


class Put_Out_Depot_jButton4_actionAdapter implements ActionListener {
    private Put_Out_Depot adaptee;
    Put_Out_Depot_jButton4_actionAdapter(Put_Out_Depot adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton4_actionPerformed(e);
    }
}

//    public void jComboBox1_actionPerformed(ActionEvent e) {
//        String str=jComboBox1.getSelectedItem().toString();
//    if(str.equals("入库")){
//
//
//    }
//    }
//
//
//}


//class Put_Out_Depot_jComboBox1_actionAdapter implements ActionListener {
//    private Put_Out_Depot adaptee;
//    Put_Out_Depot_jComboBox1_actionAdapter(Put_Out_Depot adaptee) {
//        this.adaptee = adaptee;
//    }

//    public void actionPerformed(ActionEvent e) {
//        adaptee.jComboBox1_actionPerformed(e);
//    }
//}


//    class Put_Out_Depot_jButton1_actionAdapter implements ActionListener {
//        private Put_Out_Depot adaptee;
//        Put_Out_Depot_jButton1_actionAdapter(Put_Out_Depot adaptee) {
//            this.adaptee = adaptee;
//        }
//
//        public void actionPerformed(ActionEvent e) {
//            adaptee.jButton1_actionPerformed(e);
//        }
//    }
//}
