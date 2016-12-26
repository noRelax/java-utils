package system_setting;

import java.awt.*;

import javax.swing.*;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.*;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.border.TitledBorder;

/**
 * <p>Title:勿忘软件,lzquan </p>
 *
 * <p>Description:勿忘软件 </p>
 *
 * <p>Copyright: 泉水依然 Copyright (c) 2007-03-20</p>
 *
 * <p>Company: 泉水依然</p>
 *
 * @author :权哥,湖南农业大学科学技术师范学院04计算机教育班.
 *
 * QQ:25241418
 */
public class Memoire_Setting extends JFrame {
    XYLayout xYLayout1 = new XYLayout();
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    TitledBorder titledBorder1 = new TitledBorder("");
    TitledBorder titledBorder2 = new TitledBorder("");
    public Memoire_Setting() {
        try {
            jbInit();
            this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(xYLayout1);
        this.getContentPane().setBackground(new Color(236, 233, 255));
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        xYLayout1.setWidth(400);
        xYLayout1.setHeight(273);
        jPanel1.setBackground(Color.white);
        jPanel1.setBorder(titledBorder2);
        this.getContentPane().add(jPanel1, new XYConstraints(0, 0, 124, 273));
        this.setTitle("-- ^_^ -- 勿忘提醒设置面板 -- ^_^ --");
        this.getContentPane().add(jPanel2, new XYConstraints(124, 0, 276, 273));
    }

    public static void main(String[] args) {
        Memoire_Setting memoire_setting = new Memoire_Setting();
        memoire_setting.setLocation(300, 200);
        memoire_setting.setSize(400, 300);
        memoire_setting.setVisible(true);
    }
}
