package lgcsgwxt.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

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
public class Guayuqwomen extends JDialog {
    JPanel panel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    TitledBorder titledBorder1 = new TitledBorder("");
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel jLabel8 = new JLabel();
    JLabel jLabel2 = new JLabel();
    public Guayuqwomen(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Guayuqwomen() {
        this(new Frame(), "Guayuqwomen", false);
        this.setSize(500, 420);
        this.setLocation(300, 200);
    }

    private void jbInit() throws Exception {
        panel1.setLayout(null);
        panel1.setBackground(Color.pink);
        jLabel1.setFont(new java.awt.Font("隶书", Font.PLAIN, 30));
        jLabel1.setBorder(titledBorder1);
        jLabel1.setText("   关  于  本  软  件");
        jLabel1.setBounds(new Rectangle(73, 34, 346, 53));
        jLabel3.setFont(new java.awt.Font("隶书", Font.PLAIN, 30));
        jLabel3.setText("作者: 郭建伟");
        jLabel3.setBounds(new Rectangle(156, 140, 201, 36));
        jLabel4.setFont(new java.awt.Font("隶书", Font.PLAIN, 30));
        jLabel4.setText("本软件仅供学习参考之用");
        jLabel4.setBounds(new Rectangle(90, 185, 388, 36));
        jLabel7.setFont(new java.awt.Font("隶书", Font.PLAIN, 30));
        jLabel7.setText("联系方式:microsoftgjw@163.com");
        jLabel7.setBounds(new Rectangle(27, 263, 462, 36));
        jLabel8.setFont(new java.awt.Font("隶书", Font.PLAIN, 30));
        jLabel8.setText("      QQ:  56750109");
        jLabel8.setBounds(new Rectangle(27, 302, 462, 36));
        jLabel2.setFont(new java.awt.Font("隶书", Font.PLAIN, 30));
        jLabel2.setText("欢迎交流学习");
        jLabel2.setBounds(new Rectangle(156, 224, 388, 36));
        getContentPane().add(panel1);
        panel1.add(jLabel1);
        panel1.add(jLabel3);
        panel1.add(jLabel4);
        panel1.add(jLabel2);
        panel1.add(jLabel7);
        panel1.add(jLabel8);
    }
}
