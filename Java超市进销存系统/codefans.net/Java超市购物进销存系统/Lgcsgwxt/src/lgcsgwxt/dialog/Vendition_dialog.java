package lgcsgwxt.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.util.Vector;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.*;
import java.awt.Insets;
import lgcsgwxt.MainFrame;
import lgcsgwxt.means.*;
import javax.swing.DebugGraphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
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
public class Vendition_dialog extends JDialog {

    JPanel panel1 = new JPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel1 = new JLabel();
    JTextField jTextField2 = new JTextField();
    JTextField jTextField3 = new JTextField();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel jLabel8 = new JLabel();
    JLabel jLabel9 = new JLabel();
    TitledBorder titledBorder1 = new TitledBorder("");

    JTable jTable1 = new JTable();
    JTableHeader jTableHeader1 = jTable1.getTableHeader();

    Vector colnames = new Vector(); //表头
    Vector colnames1 = new Vector(); //测试
    Vector colnames2 = new Vector(); //测试
    Vector colnames4 = new Vector();
    JTextField jTextField5 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JTextField jTextField6 = new JTextField();
    JLabel jLabel10 = new JLabel();
    JLabel jLabel11 = new JLabel();
    JLabel jLabel12 = new JLabel();
    JTextField jTextField4 = new JTextField();
    public Vendition_dialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Vendition_dialog() {
        this(new Frame(), "前台收银程序", false);
        this.setSize(800, 600);
        this.setLocation(50, 50);

    }

    private void jbInit() throws Exception {
        panel1.setLayout(null);
        jTextField3.setEnabled(false);
        jTextField5.setFont(new java.awt.Font("宋体", Font.PLAIN, 30));
        jTextField5.setText("0");
        jTextField5.setBounds(new Rectangle(335, 520, 125, 40));

        jTextField5.addKeyListener(new Vendition_dialog_jTextField5_keyAdapter(this));
        jButton1.setBounds(new Rectangle(436, 489, 96, 25));
        jButton1.setText("删除商品");
        jButton1.addActionListener(new Vendition_dialog_jButton1_actionAdapter(this));

        jButton2.setBounds(new Rectangle(685, 489, 96, 25));
        jButton2.setText("交易取消");
        jButton2.addActionListener(new Vendition_dialog_jButton2_actionAdapter(this));
        jTextField2.addKeyListener(new Vendition_dialog_jTextField2_keyAdapter(this));
        jButton3.setBounds(new Rectangle(560, 489, 96, 25));
        jButton3.setToolTipText("");
        jButton3.setText("前台转后台");
        jButton3.addActionListener(new Vendition_dialog_jButton3_actionAdapter(this));
        jTextField6.setFont(new java.awt.Font("宋体", Font.PLAIN, 20));
        jTextField6.setBounds(new Rectangle(91, 489, 152, 21));
        jTextField6.addFocusListener(new
                                     Vendition_dialog_jTextField6_focusAdapter(this));
        jLabel10.setText("会员卡号");
        jLabel10.setBounds(new Rectangle(30, 488, 48, 15));
        jLabel6.setText("0");
        jLabel8.setText("0");
        jTextField1.setFont(new java.awt.Font("宋体", Font.PLAIN, 20));
        jTextField1.addFocusListener(new
                                     Vendition_dialog_jTextField1_focusAdapter(this));
        jTextField2.setFont(new java.awt.Font("宋体", Font.PLAIN, 20));
        jTextField2.setText("1");
        jLabel11.setBorder(BorderFactory.createEtchedBorder());
        jLabel11.setDebugGraphicsOptions(0);
        jLabel11.setBounds(new Rectangle(621, 460, 159, 21));
        jLabel12.setText("当前时间");
        jLabel12.setBounds(new Rectangle(560, 463, 48, 15));
        jTextField4.setEnabled(false);
        jTextField4.setText("100");
        jTextField4.setBounds(new Rectangle(436, 456, 96, 21));
        this.getContentPane().add(panel1, java.awt.BorderLayout.CENTER);
        jTextField1.setBounds(new Rectangle(91, 460, 152, 21));
        jLabel1.setText("商品编号");
        jLabel1.setBounds(new Rectangle(30, 463, 60, 15));
        jTextField2.setBounds(new Rectangle(302, 460, 72, 21));
        jTextField3.setBounds(new Rectangle(302, 489, 72, 21));
        jLabel2.setText("数量");
        jLabel2.setBounds(new Rectangle(267, 463, 42, 15));
        jLabel3.setText("单价");
        jLabel3.setBounds(new Rectangle(267, 489, 42, 15));
        jLabel4.setText("折扣");
        jLabel4.setBounds(new Rectangle(399, 460, 42, 15));
        jLabel5.setFont(new java.awt.Font("宋体", Font.PLAIN, 30));
        jLabel5.setText("应收:");
        jLabel5.setBounds(new Rectangle(26, 517, 88, 40));
        jLabel6.setBackground(Color.white);
        jLabel6.setFont(new java.awt.Font("宋体", Font.PLAIN, 30));
        jLabel6.setBorder(BorderFactory.createEtchedBorder());
        jLabel6.setBounds(new Rectangle(110, 517, 125, 40));
        jLabel7.setFont(new java.awt.Font("宋体", Font.PLAIN, 30));
        jLabel7.setText("找零:");
        jLabel7.setBounds(new Rectangle(473, 519, 88, 40));
        jLabel8.setFont(new java.awt.Font("宋体", Font.PLAIN, 30));
        jLabel8.setBorder(BorderFactory.createEtchedBorder());
        jLabel8.setBounds(new Rectangle(550, 520, 125, 40));
        jLabel9.setFont(new java.awt.Font("宋体", Font.PLAIN, 30));
        jLabel9.setText("实收:");
        jLabel9.setBounds(new Rectangle(251, 516, 88, 40));
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 0, 1000); //得到当前时间
        //jLabel11.setText(GetTime.getTime());
        panel1.add(jScrollPane1);
        jScrollPane1.getViewport().add(jTable1);
        panel1.add(jLabel5);
        panel1.add(jLabel6);
        panel1.add(jLabel9);
        panel1.add(jTextField5);
        panel1.add(jLabel7);
        panel1.add(jLabel8);
        panel1.add(jTextField2);
        panel1.add(jLabel1);
        panel1.add(jTextField1);
        panel1.add(jLabel2);
        panel1.add(jTextField6);
        panel1.add(jLabel10);
        panel1.add(jTextField3);
        panel1.add(jLabel3);
        panel1.add(jLabel4);
        panel1.add(jButton1);
        panel1.add(jButton3);
        panel1.add(jButton2);
        panel1.add(jLabel11);
        panel1.add(jLabel12);
        panel1.add(jTextField4);
        this.getContentPane().add(jTableHeader1, java.awt.BorderLayout.NORTH);
        colnames.add("商品编码");
        colnames.add("商品名称");
        colnames.add("规格");
        colnames.add("单位");
        colnames.add("数量");
        colnames.add("售价");
        colnames.add("折扣");
        colnames.add("金额");

        colnames2.add(colnames1);
        jTable1 = Mytable.maketable(colnames2, colnames); //显示内容
        jScrollPane1.getViewport().add(jTable1); //把表装入容器
        jScrollPane1.setBounds(new Rectangle(24, 28, 746, 419));
        panel1.setBackground(Color.pink);
    }

    class RemindTask extends TimerTask {
        public void run() {
            jLabel11.setText(GetTime.getTime());
        }
    }


    public void jButton2_actionPerformed(ActionEvent e) {
        jTextField6.setEditable(true);
        jTextField6.setText("");
        colnames4.removeAllElements();
        jTable1 = Mytable.maketable(colnames4, colnames); //显示内容
        jScrollPane1.getViewport().add(jTable1); //把表装入容器
    }

    public void jTextField5_keyReleased(KeyEvent e) {
        int t = (int) e.getKeyChar();
        if (t == 10) { //如果输入的是回车键
            try {
                Double gather = new Double(jTextField5.getText());
                double gathering = ((int) (gather * 100)) / 100.0;
                Double account = new Double(jLabel6.getText());
                double givechange = ((int) ((gathering - account) * 100)) /
                                    100.0;
                if (givechange < 0) {
                    JOptionPane.showMessageDialog(this, "输入的金额不足", "错误",
                                                  JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Double givechange1 = new Double(givechange);

                jLabel8.setText(givechange1.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "输入的金额有误", "错误",
                                              JOptionPane.ERROR_MESSAGE);
                return;
            }
            Settle_Dialog sett = new Settle_Dialog();
            sett.setSize(400, 300);
            sett.setLocation(300, 250);
            sett.setVisible(true); //显示结账界面
            sett.jTextField1.setText(jLabel6.getText());
            sett.jTextField2.setText(jTextField5.getText());
            sett.jTextField3.setText(jLabel8.getText());
            jTextField6.setEditable(true); //会员卡号可编辑
            jTextField6.setText("");
            //商品编号  销售日期  商品数量 商品单价 折扣   会员卡号 商品名称
            int num1 = 0;
            while (num1 < colnames4.size()) { //更新数据库
                Vector ls = new Vector();
                ls = (Vector) colnames4.get(num1);
//                ls.get(0).toString();//商品编号
//                jLabel11.getText();//时间
                Integer number = new Integer(ls.get(4).toString()); //数量
                Double Price = new Double(ls.get(5).toString()); //单价
//              ls.get(6).toString();//折扣
//              jTextField6.getText();//会员卡号
//              ls.get(1).toString();//商品名称
                UpdateData.out_StockPile(number, ls.get(0).toString());
                Insert_stock.Insert_Sale(ls.get(0).toString(),
                                         jLabel11.getText(), number,
                                         Price, ls.get(6).toString(),
                                         jTextField6.getText(),
                                         ls.get(1).toString());
                num1++;
            }
            jTextField1.setText("");
            jTextField2.setText("1");
            colnames4.removeAllElements();
            jTable1 = Mytable.maketable(colnames4, colnames); //显示内容
            jScrollPane1.getViewport().add(jTable1); //把表装入容器

        }

    }


    public void jTextField2_keyReleased(KeyEvent e) {

        if (jTextField1.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "商品编号不能为空", "错误",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (jTextField2.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "商品数量不能为空", "错误",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }
        colnames.removeAllElements();
        colnames.add("商品编码");
        colnames.add("商品名称");
        colnames.add("规格");
        colnames.add("单位");
        colnames.add("数量");
        colnames.add("售价");
        colnames.add("折扣");
        colnames.add("金额");

        int t = (int) e.getKeyChar();
        if (t == 10) {
            Vector colnames2 = new Vector();
            Vector colnames3 = new Vector();
            Vector colnames5 = new Vector();
            // Vector User =new Vector();
            colnames2 = Select_Means.Select_sort2(jTextField1.getText()); //查询商品信息
            colnames3 = (Vector) colnames2.get(0);
            if (jTextField6.getText().length() == 0) {
            }
            jTextField3.setText(colnames3.get(4).toString());
            if (colnames3.size() == 0) {
                JOptionPane.showMessageDialog(this, "无此商品", "错误",
                                              JOptionPane.ERROR_MESSAGE);
                return;
            }

            colnames5.add(colnames3.get(0)); //商品编码
            colnames5.add(colnames3.get(3)); //商品名称
            colnames5.add(colnames3.get(5)); //商品规格
            colnames5.add(colnames3.get(6)); //商品单位
            colnames5.add(jTextField2.getText()); //数量
            colnames5.add(colnames3.get(4)); //单价
            colnames5.add(jTextField4.getText()); //折扣

            try {
                Integer number1 = new Integer(jTextField2.getText());
                Double number2 = new Double(jTextField3.getText());
                Double number3 = new Double(jTextField4.getText());
                double money = ((int) (number1 * number2 * number3)) / 100.0;
                Double Money = new Double(money);
                colnames5.add(Money.toString()); //金额
                colnames4.add(colnames5);
                jTable1 = Mytable.maketable(colnames4, colnames); //显示内容
                jScrollPane1.getViewport().add(jTable1); //把表装入容器
                int num = 0;
                double Payment = 0;
                while (num < colnames4.size()) {
                    Vector allMoney = new Vector();
                    allMoney = (Vector) colnames4.get(num);
                    Double allMoney1 = new Double(allMoney.get(7).toString());
                    Payment += allMoney1;
                    num++;
                }
                Double PaymentAll = new Double(Payment);
                jLabel6.setText(PaymentAll.toString());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "输入的数量不正确", "错误",
                                              JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    public void jButton1_actionPerformed(ActionEvent e) {
        int row = jTable1.getSelectedRow();
        colnames4.remove(row);
        jTable1 = Mytable.maketable(colnames4, colnames); //显示内容
        jScrollPane1.getViewport().add(jTable1); //把表装入容器
    }

    public void jButton3_actionPerformed(ActionEvent e) {
        Admin admin = new Admin(this);
        admin.setVisible(true);
        admin.setLocation(200, 200);
    }

    public void jTextField1_focusLost(FocusEvent e) {
        if (jTextField1.getText().length() != 0) {
            Vector select1 = Select_Means.Select_sort2(jTextField1.getText());
            Vector select2 = new Vector();
            if (select1.size() == 0) {
                JOptionPane.showMessageDialog(this, "商品不存在", "错误",
                                              JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                select2 = (Vector) select1.get(0);
                jTextField3.setText(select2.get(4).toString()); //单价
            }
        }
    }

    public void jTextField6_focusLost(FocusEvent e) {
        Vector User = new Vector();
        if (jTextField6.getText().length() == 0) {
        } else {
            User = Select_Means.Select_UserManager(jTextField6.
                    getText());
            if (User.size() == 0) {
                JOptionPane.showMessageDialog(this, "无此卡号", "错误",
                                              JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                String str = User.get(8).toString();
                jTextField4.setText(str);
                jTextField6.setEditable(false);
            }
        }

    }

}


class Vendition_dialog_jButton3_actionAdapter implements ActionListener {
    private Vendition_dialog adaptee;
    Vendition_dialog_jButton3_actionAdapter(Vendition_dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton3_actionPerformed(e);
    }
}


class Vendition_dialog_jTextField5_keyAdapter extends KeyAdapter {
    private Vendition_dialog adaptee;
    Vendition_dialog_jTextField5_keyAdapter(Vendition_dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void keyReleased(KeyEvent e) {
        adaptee.jTextField5_keyReleased(e);
    }
}


class Vendition_dialog_jTextField2_keyAdapter extends KeyAdapter {
    private Vendition_dialog adaptee;
    Vendition_dialog_jTextField2_keyAdapter(Vendition_dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void keyReleased(KeyEvent e) {
        adaptee.jTextField2_keyReleased(e);
    }
}


class Vendition_dialog_jTextField6_focusAdapter extends FocusAdapter {
    private Vendition_dialog adaptee;
    Vendition_dialog_jTextField6_focusAdapter(Vendition_dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void focusLost(FocusEvent e) {
        adaptee.jTextField6_focusLost(e);
    }
}


class Vendition_dialog_jTextField1_focusAdapter extends FocusAdapter {
    private Vendition_dialog adaptee;
    Vendition_dialog_jTextField1_focusAdapter(Vendition_dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void focusLost(FocusEvent e) {
        adaptee.jTextField1_focusLost(e);
    }
}


class Vendition_dialog_jButton2_actionAdapter implements ActionListener {
    private Vendition_dialog adaptee;
    Vendition_dialog_jButton2_actionAdapter(Vendition_dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}


class Vendition_dialog_jButton1_actionAdapter implements ActionListener {
    private Vendition_dialog adaptee;
    Vendition_dialog_jButton1_actionAdapter(Vendition_dialog adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}
