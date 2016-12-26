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
import java.util.Vector;
import lgcsgwxt.means.Select_Means;
import javax.swing.JOptionPane;

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
public class Select_card extends JDialog {
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
    JButton jButton2 = new JButton();
    JComboBox jComboBox1 = new JComboBox();
    JButton jButton5 = new JButton();
    public Select_card(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Select_card() {
        this(new Frame(), "注册用户", false);
        this.setSize(500, 400);
        this.setLocation(300, 200);
    }

    private void jbInit() throws Exception {

        panel1.setLayout(null);
        jTextField1.setBounds(new Rectangle(126, 48, 105, 27));
        jTextField2.setEnabled(false);
        jTextField2.setBounds(new Rectangle(126, 88, 105, 27));
        jTextField3.setEnabled(false);
        jTextField3.setBounds(new Rectangle(126, 128, 105, 27));
        jTextField4.setEnabled(false);
        jTextField4.setBounds(new Rectangle(126, 168, 105, 27));
        jTextField5.setEnabled(false);
        jTextField5.setBounds(new Rectangle(126, 208, 105, 27));
        jLabel1.setText("卡号");
        jLabel1.setBounds(new Rectangle(51, 53, 42, 15));
        jLabel2.setText("持卡人");
        jLabel2.setBounds(new Rectangle(51, 94, 42, 15));
        jLabel3.setText("证件号");
        jLabel3.setBounds(new Rectangle(51, 134, 42, 15));
        jLabel4.setText("级别");
        jLabel4.setBounds(new Rectangle(51, 173, 42, 15));
        jLabel5.setText("折扣率");
        jLabel5.setBounds(new Rectangle(51, 213, 42, 15));
        jTextField7.setEnabled(false);
        jTextField7.setBounds(new Rectangle(308, 87, 105, 27));
        jTextField8.setEnabled(false);
        jTextField8.setBounds(new Rectangle(309, 128, 105, 27));
        jLabel6.setText("类型");
        jLabel6.setBounds(new Rectangle(254, 53, 42, 15));
        jLabel7.setText("期限");
        jLabel7.setBounds(new Rectangle(254, 93, 42, 15));
        jLabel8.setText("积分");
        jLabel8.setBounds(new Rectangle(254, 133, 42, 15));
        jTextField9.setEnabled(false);
        jTextField9.setDoubleBuffered(true);
        jTextField9.setBounds(new Rectangle(309, 168, 105, 27));
        jLabel9.setText("开卡日期");
        jLabel9.setBounds(new Rectangle(254, 173, 61, 15));
        jLabel10.setText("备注");
        jLabel10.setBounds(new Rectangle(254, 213, 48, 15));
        jScrollPane1.setBounds(new Rectangle(308, 210, 105, 82));
        jButton2.setBounds(new Rectangle(275, 317, 83, 25));
        jButton2.setText("取消");
        jButton2.addActionListener(new Select_card_jButton2_actionAdapter(this));
        panel1.setBackground(Color.pink);
        jComboBox1.setBounds(new Rectangle(308, 48, 105, 27));

        jComboBox1.addItem("普通会员");
        jComboBox1.addItem("收银员");
        jButton5.setBounds(new Rectangle(108, 317, 83, 25));
        jButton5.setText("查询");
        jButton5.addActionListener(new Select_card_jButton5_actionAdapter(this));
        jTextArea1.setEnabled(false);
        panel1.add(jTextField3);
        panel1.add(jTextField1);
        panel1.add(jTextField2);
        panel1.add(jLabel1);
        panel1.add(jLabel2);
        panel1.add(jLabel3);
        panel1.add(jLabel4);
        panel1.add(jLabel7);
        panel1.add(jLabel8);
        panel1.add(jLabel10);
        panel1.add(jLabel6);
        panel1.add(jLabel9);
        panel1.add(jLabel5);
        panel1.add(jTextField4);
        panel1.add(jTextField5);
        panel1.add(jTextField7);
        panel1.add(jTextField8);
        panel1.add(jTextField9);
        panel1.add(jScrollPane1);
        panel1.add(jComboBox1);
        panel1.add(jButton2);
        panel1.add(jButton5);
        jScrollPane1.getViewport().add(jTextArea1);
        this.getContentPane().add(panel1, java.awt.BorderLayout.CENTER);
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
        jTextField4.setText(Select_vector.get(3).toString()); //密码
        jTextField9.setText(Select_vector.get(4).toString()); //开卡日期
        if (Select_vector.get(5).toString().equals("普通会员")) {
            jComboBox1.removeAllItems();
            jComboBox1.addItem("普通会员");
            jComboBox1.addItem("收银员");

        }
        if (Select_vector.get(5).toString().equals("收银员")) {
            jComboBox1.removeAllItems();
            jComboBox1.addItem("收银员");
            jComboBox1.addItem("普通会员");

        }
        jTextField7.setText(Select_vector.get(6).toString()); //期限
        jTextField8.setText(Select_vector.get(7).toString()); //积分
        jTextField5.setText(Select_vector.get(8).toString()); //折扣
        jTextArea1.setText(Select_vector.get(9).toString()); //备注
    }

    public void jButton2_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

}


class Select_card_jButton2_actionAdapter implements ActionListener {
    private Select_card adaptee;
    Select_card_jButton2_actionAdapter(Select_card adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}


class Select_card_jButton5_actionAdapter implements ActionListener {
    private Select_card adaptee;
    Select_card_jButton5_actionAdapter(Select_card adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton5_actionPerformed(e);
    }
}
