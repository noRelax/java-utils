package lgcsgwxt.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lgcsgwxt.means.GetTime;
import lgcsgwxt.means.Insert_stock;
import javax.swing.JOptionPane;
import lgcsgwxt.means.Select_Means;
import java.util.Vector;
import lgcsgwxt.means.*;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.JPasswordField;

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
public class Dialog_enroll extends JDialog {
    JPanel panel1 = new JPanel();
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();
    JTextField jTextField3 = new JTextField();
    JTextField jTextField5 = new JTextField();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JTextField jTextField7 = new JTextField();
    JTextField jTextField8 = new JTextField();
    JTextArea jTextArea1 = new JTextArea();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel jLabel8 = new JLabel();
    JTextField jTextField9 = new JTextField();
    JLabel jLabel9 = new JLabel();
    JLabel jLabel10 = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JComboBox jComboBox1 = new JComboBox();
    JButton jButton3 = new JButton();
    JButton jButton4 = new JButton();
    JButton jButton5 = new JButton();
    JPasswordField jPasswordField1 = new JPasswordField();
    public Dialog_enroll(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Dialog_enroll() {
        this(new Frame(), "注册用户", false);
        this.setSize(500, 400);
        this.setLocation(300, 200);
    }

    private void jbInit() throws Exception {

        panel1.setLayout(null);
        jTextField1.setBounds(new Rectangle(109, 48, 132, 27));
        jTextField2.setBounds(new Rectangle(109, 88, 132, 27));
        jTextField3.setBounds(new Rectangle(109, 129, 132, 27));
        jTextField5.setEnabled(false);
        jTextField5.setText("90");
        jTextField5.setBounds(new Rectangle(309, 173, 105, 27));
        jLabel1.setText("卡  号");
        jLabel1.setBounds(new Rectangle(51, 53, 42, 15));
        jLabel2.setText("持卡人");
        jLabel2.setBounds(new Rectangle(51, 94, 42, 15));
        jLabel3.setText("证件号");
        jLabel3.setBounds(new Rectangle(51, 134, 42, 15));
        jLabel4.setText("密  码");
        jLabel4.setBounds(new Rectangle(51, 173, 42, 15));
        jLabel5.setText("折扣率");
        jLabel5.setBounds(new Rectangle(254, 178, 42, 15));
        jTextField7.setEnabled(false);
        jTextField7.setText("1年");
        jTextField7.setBounds(new Rectangle(308, 87, 105, 27));
        jTextField8.setEnabled(false);
        jTextField8.setText("0");
        jTextField8.setBounds(new Rectangle(309, 128, 105, 27));
        jLabel6.setText("类型");
        jLabel6.setBounds(new Rectangle(254, 53, 42, 15));
        jLabel7.setText("期限");
        jLabel7.setBounds(new Rectangle(254, 93, 42, 15));
        jLabel8.setText("积分");
        jLabel8.setBounds(new Rectangle(254, 133, 42, 15));
        jTextField9.setEnabled(false);
        jTextField9.setBounds(new Rectangle(109, 209, 132, 27));
        jTextField9.setText(GetTime.getTime());
        jLabel9.setText("开卡日期");
        jLabel9.setBounds(new Rectangle(51, 214, 61, 15));
        jLabel10.setText("备注");
        jLabel10.setBounds(new Rectangle(254, 213, 48, 15));
        jScrollPane1.setBounds(new Rectangle(308, 210, 105, 82));
        jButton1.setBounds(new Rectangle(75, 318, 83, 25));
        jButton1.setText("注册");
        jButton1.addActionListener(new Dialog_enroll_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(275, 318, 83, 25));
        jButton2.setText("退出");
        jButton2.addActionListener(new Dialog_enroll_jButton2_actionAdapter(this));
        panel1.setBackground(Color.pink);
        jComboBox1.setBounds(new Rectangle(308, 48, 105, 27));
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 0, 1000); //得到当前时间
        jComboBox1.addItem("普通会员");
        jComboBox1.addItem("收银员");
        jComboBox1.addItem("管理员");
        jButton3.setBounds(new Rectangle(175, 318, 83, 25));
        jButton3.setText("修改");
        jButton3.addActionListener(new Dialog_enroll_jButton3_actionAdapter(this));
        jButton4.setBounds(new Rectangle(75, 279, 83, 25));
        jButton4.setText("删除用户");
        jButton4.addActionListener(new Dialog_enroll_jButton4_actionAdapter(this));
        jButton5.setBounds(new Rectangle(175, 279, 83, 25));
        jButton5.setText("查询");
        jButton5.addActionListener(new Dialog_enroll_jButton5_actionAdapter(this));
        jPasswordField1.setBounds(new Rectangle(109, 169, 132, 27));
        panel1.add(jTextField3);
        panel1.add(jTextField2);
        panel1.add(jLabel1);
        panel1.add(jLabel2);
        panel1.add(jLabel3);
        panel1.add(jLabel4);
        panel1.add(jLabel7);
        panel1.add(jLabel8);
        panel1.add(jLabel10);
        panel1.add(jLabel6);
        panel1.add(jTextField7);
        panel1.add(jTextField8);
        panel1.add(jScrollPane1);
        panel1.add(jButton1);
        panel1.add(jComboBox1);
        panel1.add(jButton3);
        panel1.add(jButton2);
        panel1.add(jButton4);
        panel1.add(jButton5);
        panel1.add(jTextField5);
        panel1.add(jLabel5);
        panel1.add(jTextField9);
        panel1.add(jLabel9);
        panel1.add(jTextField1);
        panel1.add(jPasswordField1);
        jScrollPane1.getViewport().add(jTextArea1);
        this.getContentPane().add(panel1, java.awt.BorderLayout.CENTER);
    }
    class RemindTask extends TimerTask {
               public void run() {
                  jTextField9.setText(GetTime.getTime());
               }
   }
    public void jButton2_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

    public void jButton4_actionPerformed(ActionEvent e) {
        int i=0;
       i= DelData.delCard(jTextField1.getText());
       if (i != 0) {
            JOptionPane.showMessageDialog(this, "删除成功", "提示",
                                          JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "删除失败", "提示",
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        }


    }

    public void jButton1_actionPerformed(ActionEvent e) {
        String cardId = jTextField1.getText(); //卡号
        String name = jTextField2.getText(); //持卡人
        String IDcard = jTextField3.getText(); //身份证
        String password = jPasswordField1.getText(); //密码
        String CardDate = jTextField9.getText(); //开卡日期
        String UserGrade = jComboBox1.getSelectedItem().toString(); //用户类型
        String term = jTextField7.getText(); //期限
        String integral = jTextField8.getText(); //积分
        String agio = jTextField5.getText(); //折扣
        String remark = jTextArea1.getText();
        int i = Insert_stock.Insert_User(cardId, name, IDcard, password,
                                         CardDate,
                                         UserGrade, term, integral, agio,
                                         remark);
        if (i != 0) {
            JOptionPane.showMessageDialog(this, "开卡成功", "提示",
                                          JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "开卡失败", "提示",
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        }

    }

    public void jButton5_actionPerformed(ActionEvent e) {
        String cardId = jTextField1.getText(); //卡号
        Vector Select_vector = new Vector();
        Select_vector = Select_Means.Select_UserManager(cardId); //查询此卡是否存在
        if (Select_vector.size() == 0) {
            JOptionPane.showMessageDialog(this, "卡号不存在", "提示",
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        jTextField1.setText(Select_vector.get(0).toString()); //卡号
        jTextField2.setText(Select_vector.get(1).toString()); //持卡人
        jTextField3.setText(Select_vector.get(2).toString()); //身份证
        jTextField9.setText(Select_vector.get(4).toString()); //开卡日期
        if (Select_vector.get(5).toString().equals("普通会员")) {
            jComboBox1.removeAllItems();
            jComboBox1.addItem("普通会员");
            jComboBox1.addItem("收银员");
            jComboBox1.addItem("管理员");
        }
        if (Select_vector.get(5).toString().equals("收银员")) {
            jComboBox1.removeAllItems();
            jComboBox1.addItem("收银员");
            jComboBox1.addItem("普通会员");
            jComboBox1.addItem("管理员");
        }
        if (Select_vector.get(5).toString().equals("管理员")) {
            jComboBox1.removeAllItems();
            jComboBox1.addItem("管理员");
            jComboBox1.addItem("收银员");
            jComboBox1.addItem("普通会员");
        }
        jTextField7.setText(Select_vector.get(6).toString()); //期限
        jTextField8.setText(Select_vector.get(7).toString()); //积分
        jTextField5.setText(Select_vector.get(8).toString()); //折扣
        jTextArea1.setText(Select_vector.get(9).toString()); //备注

    }

    public void jButton3_actionPerformed(ActionEvent e) {
        String cardId = jTextField1.getText(); //卡号
        Vector Select_vector = new Vector();
        Select_vector = Select_Means.Select_UserManager(cardId); //查询此卡是否存在
        if (Select_vector.size() == 0) {
            JOptionPane.showMessageDialog(this, "卡号不存在", "提示",
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int i = UpdateData.UpdateCard(jTextField1.getText(),jTextField2.getText(),
                                      jTextField3.getText(),
                                      jPasswordField1.getText(),
                                      jTextField9.getText(),
                                      jComboBox1.getSelectedItem().toString(),
                                      jTextField7.getText(),
                                      jTextField8.getText(),
                                      jTextField5.getText(), jTextArea1.getText()
                                      );
        if (i == 0) {
            JOptionPane.showMessageDialog(this, "资料更新失败", "提示",
                                          JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "资料更新成功", "提示",
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }


    class Dialog_enroll_jButton1_actionAdapter implements ActionListener {
        private Dialog_enroll adaptee;
        Dialog_enroll_jButton1_actionAdapter(Dialog_enroll adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton1_actionPerformed(e);
        }
    }


    class Dialog_enroll_jButton4_actionAdapter implements ActionListener {
        private Dialog_enroll adaptee;
        Dialog_enroll_jButton4_actionAdapter(Dialog_enroll adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton4_actionPerformed(e);
        }
    }


    class Dialog_enroll_jButton2_actionAdapter implements ActionListener {
        private Dialog_enroll adaptee;
        Dialog_enroll_jButton2_actionAdapter(Dialog_enroll adaptee) {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) {
            adaptee.jButton2_actionPerformed(e);
        }
    }
}


class Dialog_enroll_jButton3_actionAdapter implements ActionListener {
    private Dialog_enroll adaptee;
    Dialog_enroll_jButton3_actionAdapter(Dialog_enroll adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton3_actionPerformed(e);
    }
}


class Dialog_enroll_jButton5_actionAdapter implements ActionListener {
    private Dialog_enroll adaptee;
    Dialog_enroll_jButton5_actionAdapter(Dialog_enroll adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton5_actionPerformed(e);
    }
}
