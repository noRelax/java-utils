package system_setting;

import java.awt.*;

import javax.swing.*;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.*;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.border.TitledBorder;

/**
 * <p>Title:�������,lzquan </p>
 *
 * <p>Description:������� </p>
 *
 * <p>Copyright: Ȫˮ��Ȼ Copyright (c) 2007-03-20</p>
 *
 * <p>Company: Ȫˮ��Ȼ</p>
 *
 * @author :Ȩ��,����ũҵ��ѧ��ѧ����ʦ��ѧԺ04�����������.
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
        this.setTitle("-- ^_^ -- ��������������� -- ^_^ --");
        this.getContentPane().add(jPanel2, new XYConstraints(124, 0, 276, 273));
    }

    public static void main(String[] args) {
        Memoire_Setting memoire_setting = new Memoire_Setting();
        memoire_setting.setLocation(300, 200);
        memoire_setting.setSize(400, 300);
        memoire_setting.setVisible(true);
    }
}
