package lgcsgwxt.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.util.Vector;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import lgcsgwxt.means.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;
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
public class Stock_Dialog extends JDialog {
    Vector colnames = new Vector(); //表头
    Vector colnames1 = new Vector(); //测试
    Vector colnames2 = new Vector(); //测试
    JPanel panel1 = new JPanel();
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();
    JTextField jTextField3 = new JTextField();
    JTextField jTextField4 = new JTextField();
    JTextField jTextField5 = new JTextField();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JTextField jTextField6 = new JTextField();
    JLabel jLabel6 = new JLabel();
    JTextField jTextField7 = new JTextField();
    JLabel jLabel7 = new JLabel();
    JLabel jLabel11 = new JLabel();
    JTextField jTextField8 = new JTextField();
    JLabel jLabel8 = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTable jTable1 = new JTable();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JButton jButton4 = new JButton();
    JButton jButton5 = new JButton();
    public Stock_Dialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Stock_Dialog() {
        this(new Frame(), "Stock_Dialog", false);
        this.setSize(787, 550);
    }

    private void jbInit() throws Exception {
        panel1.setLayout(null);
        jTextField1.setBounds(new Rectangle(104, 28, 111, 21));
        jTextField1.addFocusListener(new Stock_Dialog_jTextField1_focusAdapter(this));
        jTextField2.setEnabled(false);
        jTextField2.setBounds(new Rectangle(286, 28, 111, 21));
        jTextField3.setBounds(new Rectangle(469, 28, 111, 21));
        jTextField4.setText("1");
        jTextField4.setBounds(new Rectangle(104, 59, 111, 21));
        jTextField4.addFocusListener(new Stock_Dialog_jTextField4_focusAdapter(this));
        jTextField5.setEnabled(false);
        jTextField5.setBounds(new Rectangle(286, 59, 111, 21));
        jLabel1.setText("商品编号");
        jLabel1.setBounds(new Rectangle(37, 28, 75, 21));
        jLabel2.setText("商品名称");
        jLabel2.setBounds(new Rectangle(226, 28, 90, 19));
        jLabel3.setText("采购部门");
        jLabel3.setBounds(new Rectangle(411, 27, 75, 21));
        jLabel4.setText("数    量");
        jLabel4.setBounds(new Rectangle(37, 59, 75, 21));
        jLabel5.setText("单    价");
        jLabel5.setBounds(new Rectangle(226, 59, 75, 21));
        jTextField6.setEnabled(false);
        jTextField6.setBounds(new Rectangle(469, 60, 111, 21));
        jLabel6.setText("金    额");
        jLabel6.setBounds(new Rectangle(411, 60, 75, 21));
        jTextField7.setEnabled(false);
        jTextField7.setBounds(new Rectangle(642, 27, 128, 21));
        jLabel7.setText("下单日期");
        jLabel7.setBounds(new Rectangle(589, 27, 75, 21));
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 0, 1000); //得到当前时间

        jLabel11.setText("jLabel11");
        jLabel11.setBounds(new Rectangle(61, 140, 75, 21));
        jTextField8.setBounds(new Rectangle(676, 59, 90, 21));
        jLabel8.setText("付款期限(天)");
        jLabel8.setBounds(new Rectangle(594, 60, 75, 15));
        jScrollPane1.getViewport().setBackground(SystemColor.control);
        jScrollPane1.setBounds(new Rectangle(14, 126, 765, 381));
        this.setForeground(Color.pink);
        panel1.setBackground(Color.pink);
        jButton1.setBounds(new Rectangle(137, 93, 83, 25));
        jButton1.setText("确定");
        jButton1.addActionListener(new Stock_Dialog_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(244, 93, 83, 25));
        jButton2.setText("删除");
        jButton2.addActionListener(new Stock_Dialog_jButton2_actionAdapter(this));
        jButton3.setBounds(new Rectangle(351, 93, 83, 25));
        jButton3.setText("提交");
        jButton3.addActionListener(new Stock_Dialog_jButton3_actionAdapter(this));
        jButton4.setBounds(new Rectangle(457, 93, 83, 25));
        jButton4.setText("全部清空");
        jButton4.addActionListener(new Stock_Dialog_jButton4_actionAdapter(this));
        jButton5.setBounds(new Rectangle(564, 93, 83, 25));
        jButton5.setText("退出");
        jButton5.addActionListener(new Stock_Dialog_jButton5_actionAdapter(this));
        getContentPane().add(panel1);
        panel1.add(jLabel1);
        panel1.add(jLabel4);
        panel1.add(jLabel5);
        panel1.add(jScrollPane1);
        panel1.add(jTextField7);
        panel1.add(jTextField8);
        panel1.add(jButton5);
        panel1.add(jButton1);
        panel1.add(jButton2);
        panel1.add(jButton3);
        panel1.add(jButton4);
        panel1.add(jTextField1);
        panel1.add(jTextField4);
        panel1.add(jLabel2);
        panel1.add(jTextField5);
        panel1.add(jTextField2);
        panel1.add(jTextField6);
        panel1.add(jLabel3);
        panel1.add(jLabel6);
        panel1.add(jTextField3);
        panel1.add(jLabel7);
        panel1.add(jLabel8);
        colnames.add("商品编号");
        colnames.add("商品名称");
        colnames.add("采购部门");
        colnames.add("数    量");
        colnames.add("单    价");
        colnames.add("金    额");
        colnames.add("下单日期");
        colnames.add("付款期限");

        jTable1 = Mytable.maketable(colnames2, colnames); //显示内容
        jScrollPane1.getViewport().add(jTable1); //把表装入容器
        jScrollPane1.getViewport().add(jTable1);
    }
    class RemindTask extends TimerTask {
       public void run() {
          jTextField7.setText(GetTime.getTime());
       }
   }

    public void jTextField1_focusLost(FocusEvent e) {
        Vector select_merchandise = new Vector();
        String number = jTextField1.getText(); //得到商品编号
        if (number.length() == 0) {
            return;
        }
        select_merchandise = Select_Means.Select_sort2(number); //根据商品编号查找相应的信息
        if(select_merchandise.size()==0){
            JOptionPane.showMessageDialog(this, "无此商品", "错误",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }
        int i = 0;
        while (i < select_merchandise.size()) {
            Vector select = new Vector();
            select = (Vector) select_merchandise.get(i);
            jTextField2.setText(select.get(3).toString());
            jTextField5.setText(select.get(4).toString());
            i++;
            try {
                Integer number1 = new Integer(jTextField4.getText());
                Double number2 = new Double(jTextField5.getText());
                number2 = number2 * number1;
                Double number3 = new Double(number2);
                jTextField6.setText(number3.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "数量框中输入的不是数字", "错误",
                                              JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    Vector inserAll = new Vector();
    public void jButton1_actionPerformed(ActionEvent e) {
        if ((jTextField1.getText().length() == 0) ||
            (jTextField4.getText().length() == 0) ||
            (jTextField4.getText().length() == 0) ||
            (jTextField8.getText().length() == 0)) {
            JOptionPane.showMessageDialog(this, "请完整输入所有信息", "错误",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }
        Vector insertone = new Vector();
        insertone.add(jTextField1.getText());
        insertone.add(jTextField2.getText());
        insertone.add(jTextField3.getText());
        insertone.add(jTextField4.getText());
        insertone.add(jTextField5.getText());
        insertone.add(jTextField6.getText());
        insertone.add(jTextField7.getText());
        insertone.add(jTextField8.getText());
        inserAll.add(insertone);
        jTable1 = Mytable.maketable(inserAll, colnames); //显示内容
        jScrollPane1.getViewport().add(jTable1); //把表装入容器
    }

    public void jButton2_actionPerformed(ActionEvent e) {
        int row = jTable1.getSelectedRow(); //得到选中行数
        inserAll.remove(row); //删掉选中的数据
        jTable1 = Mytable.maketable(inserAll, colnames); //显示内容
        jScrollPane1.getViewport().add(jTable1); //把表装入容器
    }

    public void jButton4_actionPerformed(ActionEvent e) {
        inserAll.removeAllElements(); //清空结果集
        jTable1 = Mytable.maketable(inserAll, colnames); //显示内容
        jScrollPane1.getViewport().add(jTable1); //把表装入容器
    }

    public void jButton5_actionPerformed(ActionEvent e) {
        this.setVisible(false); //退出当前界面
    }

    public void jButton3_actionPerformed(ActionEvent e) {
        int i = 0;
        int insert = 0;
        if (inserAll.size() == 0) {
            JOptionPane.showMessageDialog(this, "没有可提交的数据", "提示",
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        } while (i < inserAll.size()) {
            Vector inserData = new Vector();
            inserData = (Vector) inserAll.get(i);
            i++;
            insert = Insert_stock.Insert_stock(inserData.get(0).toString(),
                                               inserData.get(1).toString(),
                                               inserData.get(2).toString(),
                                               inserData.get(3).toString(),
                                               inserData.get(4).toString(),
                                               inserData.get(5).toString(),
                                               inserData.get(6).toString(),
                                               inserData.get(7).toString());
            if (insert == 0) {
                JOptionPane.showMessageDialog(this, "数据写入失败", "提示",
                                              JOptionPane.INFORMATION_MESSAGE);

            }
            if(insert==inserAll.size()){
                JOptionPane.showMessageDialog(this, "数据记录成功", "提示",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void jTextField4_focusLost(FocusEvent e) {
        Integer num = new Integer(jTextField4.getText());
        Double num1 = new Double(jTextField5.getText());
        Double num2 = new Double(num1 * num);
        jTextField6.setText(num2.toString());
    }
}


class Stock_Dialog_jTextField4_focusAdapter extends FocusAdapter {
    private Stock_Dialog adaptee;
    Stock_Dialog_jTextField4_focusAdapter(Stock_Dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void focusLost(FocusEvent e) {
        adaptee.jTextField4_focusLost(e);
    }
}


class Stock_Dialog_jButton3_actionAdapter implements ActionListener {
    private Stock_Dialog adaptee;
    Stock_Dialog_jButton3_actionAdapter(Stock_Dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton3_actionPerformed(e);
    }
}


class Stock_Dialog_jButton5_actionAdapter implements ActionListener {
    private Stock_Dialog adaptee;
    Stock_Dialog_jButton5_actionAdapter(Stock_Dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton5_actionPerformed(e);
    }
}


class Stock_Dialog_jButton4_actionAdapter implements ActionListener {
    private Stock_Dialog adaptee;
    Stock_Dialog_jButton4_actionAdapter(Stock_Dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton4_actionPerformed(e);
    }
}


class Stock_Dialog_jButton2_actionAdapter implements ActionListener {
    private Stock_Dialog adaptee;
    Stock_Dialog_jButton2_actionAdapter(Stock_Dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}


class Stock_Dialog_jButton1_actionAdapter implements ActionListener {
    private Stock_Dialog adaptee;
    Stock_Dialog_jButton1_actionAdapter(Stock_Dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}


class Stock_Dialog_jTextField1_focusAdapter extends FocusAdapter {
    private Stock_Dialog adaptee;
    Stock_Dialog_jTextField1_focusAdapter(Stock_Dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void focusLost(FocusEvent e) {
        adaptee.jTextField1_focusLost(e);
    }
}
