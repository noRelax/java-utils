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
import java.awt.Color;
import java.util.Timer;
import lgcsgwxt.means.GetTime;
import java.util.TimerTask;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import lgcsgwxt.means.*;
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
public class JoinAndExceed extends JDialog {
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    JPanel panel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    JLabel jLabel2 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel3 = new JLabel();
    JComboBox jComboBox2 = new JComboBox();
    JLabel jLabel4 = new JLabel();
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
    JButton jButton5 = new JButton();
    JLabel jLabel5 = new JLabel();
    JScrollPane jScrollPane2 = new JScrollPane();
    JTextArea jTextArea1 = new JTextArea();


    public JoinAndExceed(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public JoinAndExceed() {
        this(new Frame(), "JoinAndExceed", false);
        this.setSize(600, 500);
    }

    private void jbInit() throws Exception {
        panel1.setLayout(null);
        this.getContentPane().setLayout(null);
        jLabel4.setBorder(BorderFactory.createEtchedBorder());
        jLabel4.setBounds(new Rectangle(29, 7, 536, 36));
        jLabel6.setText("商品编号：");
        jLabel6.setBounds(new Rectangle(45, 50, 68, 21));
        jTextField2.setBounds(new Rectangle(112, 50, 127, 21));
        jTextField2.addFocusListener(new JoinAndExceed_jTextField2_focusAdapter(this));
        jLabel7.setText("数量：");
        jLabel7.setBounds(new Rectangle(263, 50, 38, 21));
        jTextField3.setBounds(new Rectangle(297, 50, 68, 21));
        jLabel8.setText("进货单价：");
        jLabel8.setBounds(new Rectangle(394, 50, 68, 21));
        jTextField4.setBounds(new Rectangle(457, 50, 68, 21));
        jLabel9.setBorder(BorderFactory.createEtchedBorder());
        jLabel9.setBounds(new Rectangle(29, 42, 536, 41));
        jScrollPane1.setBounds(new Rectangle(29, 82, 536, 270));
        jButton1.setBounds(new Rectangle(72, 436, 81, 30));
        jButton1.setSelectedIcon(null);
        jButton1.setText("确   认");
        jButton1.addActionListener(new JoinAndExceed_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(173, 436, 81, 30));
        jButton2.setText("删除");
        jButton2.addActionListener(new JoinAndExceed_jButton2_actionAdapter(this));
        jButton3.setBounds(new Rectangle(274, 436, 81, 30));
        jButton3.setText("撤   消");
        jButton3.addActionListener(new JoinAndExceed_jButton3_actionAdapter(this));
        jButton4.setBounds(new Rectangle(375, 436, 81, 30));
        jButton4.setText("提   交");
        jButton4.addActionListener(new JoinAndExceed_jButton4_actionAdapter(this));
        panel1.setBackground(Color.pink);
        this.getContentPane().setBackground(Color.pink);
        this.setForeground(Color.pink);
        jButton5.setBounds(new Rectangle(476, 436, 81, 30));
        jButton5.setToolTipText("");
        jButton5.setText("查询");
        jButton5.addActionListener(new JoinAndExceed_jButton5_actionAdapter(this));
        jLabel5.setFont(new java.awt.Font("宋体", Font.PLAIN, 30));
        jLabel5.setText("备注：");
        jLabel5.setBounds(new Rectangle(47, 366, 96, 45));
        jScrollPane2.setBounds(new Rectangle(120, 352, 444, 70));
        this.getContentPane().add(panel1, null);
        jComboBox2.setBounds(new Rectangle(433, 14, 94, 20));
        jLabel3.setText("制单人：");
        jLabel3.setBounds(new Rectangle(380, 14, 84, 20));
        jTextField1.setBounds(new Rectangle(225, 14, 131, 20));
        jLabel2.setText("时间：");
        jLabel2.setBounds(new Rectangle(187, 14, 84, 20));
        jComboBox1.setBounds(new Rectangle(79, 14, 84, 20));
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 0, 1000); //得到当前时间
        this.getContentPane().add(jComboBox2);
        this.getContentPane().add(jTextField1);
        this.getContentPane().add(jComboBox1);
        this.getContentPane().add(jLabel1);
        this.getContentPane().add(jLabel2);
        this.getContentPane().add(jLabel3);
        this.getContentPane().add(jLabel4);
        this.getContentPane().add(jButton5);
        this.getContentPane().add(jButton1);
        this.getContentPane().add(jButton2);
        this.getContentPane().add(jButton3);
        this.getContentPane().add(jButton4);
        this.getContentPane().add(jTextField2);
        this.getContentPane().add(jLabel9);
        this.getContentPane().add(jLabel6);
        this.getContentPane().add(jLabel7);
        this.getContentPane().add(jTextField3);
        this.getContentPane().add(jLabel8);
        this.getContentPane().add(jTextField4);
        this.getContentPane().add(jScrollPane1);
        this.getContentPane().add(jScrollPane2);
        this.getContentPane().add(jLabel5);
        jScrollPane2.getViewport().add(jTextArea1);
        jScrollPane1.getViewport().add(jTable1);
        jLabel1.setText("类别：");
        jLabel1.setBounds(new Rectangle(43, 14, 84, 20));
        panel1.setBounds(new Rectangle(0, 399, 1, 1));
        jComboBox1.addItem("  进  货 ");
        jComboBox1.addItem("  退  货 ");
        jComboBox2.addItem("郭健伟");
        jComboBox2.addItem("谭震欣");
        jComboBox2.addItem("徐敏");
        jTextField1.setText(ts.toString().substring(0, 19));
        colnames.add("商品编号");
        colnames.add("商品名称");
        colnames.add("数量");
        colnames.add("进货价");
        colnames.add("日期");
        colnames.add("金额");

        colnames1.add("");
        colnames1.add("");
        colnames1.add("");
        colnames1.add("");
        colnames1.add("");
        colnames2.add(colnames1);
        jTable1 = Mytable.maketable(colnames2, colnames); //显示内容
    }

    Vector vector1 = new Vector();
    public void jButton1_actionPerformed(ActionEvent e) {
        Vector select1 = Select_Means.Select_sort2(jTextField2.getText());
        if (select1.size() == 0) {
            JOptionPane.showMessageDialog(this, "商品不存在", "错误",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }
        Vector select2 = (Vector) select1.get(0);
        Vector vector = new Vector();
        double allMoney = 0;
        vector.add(jTextField2.getText());
        vector.add(select2.get(3).toString());
        vector.add(jTextField3.getText());
        vector.add(jTextField4.getText());
        vector.add(jTextField1.getText());

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
        vector.add(dd.toString());
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

    public void insertData(String NextBack) {

        int insertnumber = 0;
        while (insertnumber < vector1.size()) {
            Vector insertVector = new Vector();
            insertVector = (Vector) vector1.get(insertnumber);
            String str1 = insertVector.get(0).toString(); //商品编号
            String str2 = insertVector.get(1).toString(); //商品名称
            String str3 = insertVector.get(2).toString(); //商品数量
            Double num1 = new Double(str3); //转数据类型
            String str4 = insertVector.get(3).toString(); //单价
            Double num2 = new Double(str4); //转数据类型
            String str5 = insertVector.get(4).toString(); //日期
            String str6 = jComboBox2.getSelectedItem().toString();
            String str7 = NextBack;
            String str8 = jTextArea1.getText();
            int i = Insert_stock.Insert_NextBackStock(str1, str2, num1, num2,
                    str5, str6, str7, str8);
            if (i == 0) {
                insertnumber++;
                String str = "第" + i + "条记录数据写入失败";
                JOptionPane.showMessageDialog(this, str, "错误",
                                              JOptionPane.ERROR_MESSAGE);
                return;

            }
            insertnumber++;
            if (insertnumber == vector1.size()) {
                JOptionPane.showMessageDialog(this, "数据录入成功", "提示",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public void jButton4_actionPerformed(ActionEvent e) {
        if (jComboBox1.getSelectedItem().equals("  进  货 ")) {
            insertData("1");
        }
        if (jComboBox1.getSelectedItem().equals("  退  货 ")) {
            insertData("0");
        }

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

    public void jButton5_actionPerformed(ActionEvent e) {
        if (jComboBox1.getSelectedItem().equals("  进  货 ")) {
            Vector selectData = new Vector();
            selectData = Select_Means.Select_inoutSale(jTextField2.getText(),
                    "1");
            int i = 0;
            vector1.removeAllElements();
            while (i < selectData.size()) {
                Vector select1 = new Vector();
                Vector selectData1 = new Vector();
                select1 = (Vector) selectData.get(i);

                selectData1.add(select1.get(0).toString());
                selectData1.add(select1.get(1).toString());
                selectData1.add(select1.get(2).toString());
                selectData1.add(select1.get(3).toString());
                selectData1.add(select1.get(4).toString());
                Double num1 = new Double(selectData1.get(2).toString());
                Double num2 = new Double(selectData1.get(3).toString());
                double num3 = ((int) (num2 * num1 * 100)) / 100.0;
                Double num4 = new Double(num3);
                selectData1.add(num4.toString());
                i++;
                vector1.add(selectData1);
            }

            jTable1 = Mytable.maketable(vector1, colnames); //显示内容
            jScrollPane1.getViewport().add(jTable1); //把表装入容器
        }
        if (jComboBox1.getSelectedItem().equals("  退  货 ")) {
            Vector selectData = new Vector();
            selectData = Select_Means.Select_inoutSale(jTextField2.getText(),
                    "0");
            int i = 0;
            vector1.removeAllElements();
            while (i < selectData.size()) {
                Vector select1 = new Vector();
                Vector selectData1 = new Vector();
                select1 = (Vector) selectData.get(i);
                selectData1.add(select1.get(0).toString());
                selectData1.add(select1.get(1).toString());
                selectData1.add(select1.get(2).toString());
                selectData1.add(select1.get(3).toString());
                selectData1.add(select1.get(4).toString());
                Double num1 = new Double(selectData1.get(2).toString());
                Double num2 = new Double(selectData1.get(3).toString());
                double num3 = ((int) (num2 * num1 * 100)) / 100.0;
                Double num4 = new Double(num3);
                selectData1.add(num4.toString());
                i++;
                vector1.add(selectData1);
            }

            jTable1 = Mytable.maketable(vector1, colnames); //显示内容
            jScrollPane1.getViewport().add(jTable1); //把表装入容器

        }

    }

    class RemindTask extends TimerTask {
        public void run() {
            jTextField1.setText(GetTime.getTime());
        }
    }

}


class JoinAndExceed_jButton5_actionAdapter implements ActionListener {
    private JoinAndExceed adaptee;
    JoinAndExceed_jButton5_actionAdapter(JoinAndExceed adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton5_actionPerformed(e);
    }
}


class JoinAndExceed_jTextField2_focusAdapter extends FocusAdapter {
    private JoinAndExceed adaptee;
    JoinAndExceed_jTextField2_focusAdapter(JoinAndExceed adaptee) {
        this.adaptee = adaptee;
    }

    public void focusLost(FocusEvent e) {
        adaptee.jTextField2_focusLost(e);
    }
}


class JoinAndExceed_jButton4_actionAdapter implements ActionListener {
    private JoinAndExceed adaptee;
    JoinAndExceed_jButton4_actionAdapter(JoinAndExceed adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton4_actionPerformed(e);
    }
}


class JoinAndExceed_jButton3_actionAdapter implements ActionListener {
    private JoinAndExceed adaptee;
    JoinAndExceed_jButton3_actionAdapter(JoinAndExceed adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton3_actionPerformed(e);
    }
}


class JoinAndExceed_jButton2_actionAdapter implements ActionListener {
    private JoinAndExceed adaptee;
    JoinAndExceed_jButton2_actionAdapter(JoinAndExceed adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}


class JoinAndExceed_jButton1_actionAdapter implements ActionListener {
    private JoinAndExceed adaptee;
    JoinAndExceed_jButton1_actionAdapter(JoinAndExceed adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}
