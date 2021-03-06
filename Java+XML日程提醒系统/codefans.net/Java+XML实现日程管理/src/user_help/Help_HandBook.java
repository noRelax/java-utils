package user_help;

import java.awt.*;

import javax.swing.*;
import com.borland.jbcl.layout.PaneLayout;
import com.borland.jbcl.layout.*;
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
public class Help_HandBook extends JFrame {
    XYLayout xYLayout1 = new XYLayout();
    JPanel jPanel1 = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea jTextArea1 = new JTextArea();
    public Help_HandBook() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(500,300);
        getContentPane().setLayout(xYLayout1);
        jPanel1.setBackground(new Color(0, 233, 255));
        jPanel1.setLayout(xYLayout2);
        jTextArea1.setText("欢迎使用帮助!");
        jTextArea1.setLineWrap(true); //激活自动换行功能
        jTextArea1.setWrapStyleWord(true); //激活断行不断字功能
        jTextArea1.setFocusable(false);
        this.getContentPane().add(jPanel1, new XYConstraints( 0, 0, 500, 260));
        jScrollPane1.getViewport().add(jTextArea1);
        jPanel1.add(jScrollPane1, new XYConstraints(1, 0, 499, 260));
    }

    public JPanel getHelpPanel(){
        return this.jPanel1;
    }

    public static void main(String[] args) {
        Help_HandBook help_handbook = new Help_HandBook();
        help_handbook.setSize(500, 300);
        help_handbook.setVisible(true);
        help_handbook.setLocation(300, 300);

    }
}

