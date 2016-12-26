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
 * <p>Title: ³�㳬�н�����ϵͳ</p>
 *
 * <p>Description: ��������³��У��S1��ҵ���</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: ST-117��</p>
 *
 * @author ST-117��ڶ�С��
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
        this(new Frame(), "����Ա��¼", false);
        this.setSize(400, 300);
        this.setLocation(300, 200);

    }

    private void jbInit() throws Exception {
        panel1.setLayout(null);
        this.getContentPane().setBackground(Color.pink);
        panel1.setBackground(Color.pink);
        jTextField1.setBounds(new Rectangle(158, 87, 101, 25));
        jButton1.setBounds(new Rectangle(81, 202, 83, 25));
        jButton1.setText("��¼");
        jButton1.addActionListener(new Adminstrator_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(195, 201, 83, 25));
        jButton2.setText("ȡ��");
        jButton2.addActionListener(new Adminstrator_jButton2_actionAdapter(this));
        jLabel1.setText("�û���");
        jLabel1.setBounds(new Rectangle(78, 87, 49, 25));
        jLabel2.setText("��  ��");
        jLabel2.setBounds(new Rectangle(76, 124, 49, 25));
        jLabel3.setFont(new java.awt.Font("����", Font.PLAIN, 20));
        jLabel3.setText("����Ա��¼");
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
            this.setVisible(false);//��֤ͨ�����ص�ǰ����
          Dialog_enroll dlogen = new Dialog_enroll();
          dlogen.setVisible(true);//����ע�ᴰ��
          dlogen.setTitle("ע���û�");

        }

        else{
         JOptionPane.showMessageDialog(this,"�û��������벻��ȷ,����������","����",JOptionPane.ERROR_MESSAGE);
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
