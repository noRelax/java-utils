package main_frame;

import java.awt.*;

import javax.swing.*;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.*;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

/**
 * <p>Title:勿忘软件,lzquan </p>
 *
 * <p>Description:勿忘软件 </p>
 *
 * <p>Copyright: 泉水依然 Copyright (c) 2007-03-20</p>
 *
 * <p>Company: 泉水依然</p>
 *
 * @author :李政权,湖南农业大学科学技术师范学院04计算机教育班.
 *
 * QQ:25241418
 */
public class defaule_show extends JFrame {
    XYLayout xYLayout1 = new XYLayout();
    JPanel jPanel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    XYLayout xYLayout2 = new XYLayout();
    public defaule_show() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(500,300);
        getContentPane().setLayout(xYLayout1);
        jLabel1.setText("jLabel1");
        this.getContentPane().add(jPanel1, new XYConstraints( 0, 0, 500, 260));
        jPanel1.add(jLabel1, new XYConstraints(0, 0, 500, 260));
        jPanel1.setBackground(new Color(236, 233, 255));
        jPanel1.setLayout(xYLayout2);
        jLabel1.setIcon(new ImageIcon("images/Not_Forget.jpg"));
    }

    public JPanel getDefaultShow(){
        return this.jPanel1;
    }

    public static void main(String[] args) {
        defaule_show defaule_show = new defaule_show();
        defaule_show.setSize(500,300);
        defaule_show.setLocationRelativeTo(null);
        defaule_show.setVisible(true);
    }
}
