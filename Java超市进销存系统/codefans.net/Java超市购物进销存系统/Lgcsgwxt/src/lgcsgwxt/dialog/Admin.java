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
import lgcsgwxt.MainFrame;
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
public class Admin extends JDialog {
    Vendition_dialog form;
    JPanel panel1 = new JPanel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JPasswordField jPasswordField1 = new JPasswordField();
    public Admin(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Admin() {
        this(new Frame(), "管理员登录", false);
        this.setSize(400, 300);
        this.setLocation(300, 200);

    }

    public Admin(Vendition_dialog form) {
        this(new Frame(), "管理员登录", false);
        this.setSize(400, 300);
        this.setLocation(300, 200);
        this.form = form;
    }


    private void jbInit() throws Exception {
        panel1.setLayout(null);
        this.getContentPane().setBackground(Color.pink);
        panel1.setBackground(Color.pink);
        jTextField1.setBounds(new Rectangle(158, 87, 101, 25));
        jLabel1.setText("用户名");
        jLabel1.setBounds(new Rectangle(78, 87, 49, 25));
        jLabel2.setText("密  码");
        jLabel2.setBounds(new Rectangle(76, 124, 49, 25));
        jLabel3.setFont(new java.awt.Font("宋体", Font.PLAIN, 20));
        jLabel3.setText("管理员登录");
        jLabel3.setBounds(new Rectangle(145, 30, 113, 27));
        jButton1.setBounds(new Rectangle(75, 206, 83, 25));
        jButton1.setText("登  录");
        jButton1.addActionListener(new Admin_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(210, 206, 83, 25));
        jButton2.setText("取  消");
        jButton2.addActionListener(new Admin_jButton2_actionAdapter(this));
        jPasswordField1.setBounds(new Rectangle(158, 125, 101, 25));
        getContentPane().add(panel1);
        panel1.add(jLabel3);
        panel1.add(jLabel1);
        panel1.add(jTextField1);
        panel1.add(jLabel2);
        panel1.add(jButton1);
        panel1.add(jButton2);
        panel1.add(jPasswordField1);
    }

    public void jButton1_actionPerformed(ActionEvent e) {
        Vector user = new Vector();
        String name = jTextField1.getText();
        String password = jPasswordField1.getText();
        user = Select_Means.Select_User(name, password);
        if (user.size() != 0) {
            this.setVisible(false);
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            mainFrame.setLocation(50, 50);
            form.setVisible(false);
        }

        else {
            JOptionPane.showMessageDialog(this, "用户名或密码不正确,请重新输入", "错误",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void jButton2_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }


}


class Admin_jButton2_actionAdapter implements ActionListener {
    private Admin adaptee;
    Admin_jButton2_actionAdapter(Admin adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}


class Admin_jButton1_actionAdapter implements ActionListener {
    private Admin adaptee;
    Admin_jButton1_actionAdapter(Admin adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}


//Vector user = new Vector();
//        String name = jTextField1.getText();
//        String password = jTextField2.getText();
//        user = Select_Means.Select_User(name, password);
//        if (user.size() != 0) {
//            MainFrame mainFrame = new MainFrame();
//            mainFrame.setVisible(true);
//            mainFrame.setLocation(50, 50);
//            this.setVisible(false);
//            Vendition_dialog vdialog = new Vendition_dialog();
//            vdialog.setVisible(false);
//        }
//
//        else {
//            JOptionPane.showMessageDialog(this, "用户名或密码不正确,请重新输入", "错误",
//                                          JOptionPane.ERROR_MESSAGE);
//            return;
//        }
