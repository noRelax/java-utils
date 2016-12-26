package lgcsgwxt.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.Vector;
import lgcsgwxt.means.Select_Means;
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
public class Adminstrator extends JDialog {
    JPanel panel1 = new JPanel();
    JTextField jTextField1 = new JTextField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JPasswordField jPasswordField1 = new JPasswordField();
    public Adminstrator(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Adminstrator() {
        this(new Frame(), "管理员登录", false);
        this.setSize(400, 300);
        this.setLocation(300, 200);

    }

    private void jbInit() throws Exception {
        panel1.setLayout(null);
        this.getContentPane().setBackground(Color.pink);
        panel1.setBackground(Color.pink);
        jTextField1.setBounds(new Rectangle(158, 87, 101, 25));
        jButton1.setBounds(new Rectangle(81, 202, 83, 25));
        jButton1.setText("登录");
        jButton1.addActionListener(new Adminstrator_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(195, 201, 83, 25));
        jButton2.setText("取消");
        jButton2.addActionListener(new Adminstrator_jButton2_actionAdapter(this));
        jLabel1.setText("用户名");
        jLabel1.setBounds(new Rectangle(78, 87, 49, 25));
        jLabel2.setText("密  码");
        jLabel2.setBounds(new Rectangle(76, 124, 49, 25));
        jLabel3.setFont(new java.awt.Font("宋体", Font.PLAIN, 20));
        jLabel3.setText("管理员登录");
        jLabel3.setBounds(new Rectangle(145, 30, 113, 27));
        jPasswordField1.setBounds(new Rectangle(158, 123, 101, 25));
        getContentPane().add(panel1);
        panel1.add(jLabel3);
        panel1.add(jLabel1);
        panel1.add(jTextField1);
        panel1.add(jButton1);
        panel1.add(jButton2);
        panel1.add(jLabel2);
        panel1.add(jPasswordField1);
    }

    public void jButton1_actionPerformed(ActionEvent e) {
        Vector user=new Vector();
        String name = jTextField1.getText();
        String password = jPasswordField1.getText();
        user=Select_Means.Select_User(name,password);
        if(user.size()!=0){
            this.setVisible(false);//验证通过隐藏当前窗口
          Dialog_enroll dlogen = new Dialog_enroll();
          dlogen.setVisible(true);//弹出注册窗口
          dlogen.setTitle("注册用户");

        }

        else{
         JOptionPane.showMessageDialog(this,"用户名或密码不正确,请重新输入","错误",JOptionPane.ERROR_MESSAGE);
         return;
        }
    }

    public void jButton2_actionPerformed(ActionEvent e) {
this.setVisible(false);
    }
}


class Adminstrator_jButton2_actionAdapter implements ActionListener {
    private Adminstrator adaptee;
    Adminstrator_jButton2_actionAdapter(Adminstrator adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}


class Adminstrator_jButton1_actionAdapter implements ActionListener {
    private Adminstrator adaptee;
    Adminstrator_jButton1_actionAdapter(Adminstrator adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}
