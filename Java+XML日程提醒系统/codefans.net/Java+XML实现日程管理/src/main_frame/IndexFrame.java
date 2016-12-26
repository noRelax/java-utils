package main_frame;

import java.awt.*;

import javax.swing.*;
import java.awt.BorderLayout;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.*;
import javax.swing.BorderFactory;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class IndexFrame extends JFrame implements Runnable{
    XYLayout xYLayout1 = new XYLayout();
    JProgressBar jProgressBar1 = new JProgressBar();
    JLabel jLabel1 = new JLabel();

    public IndexFrame() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(xYLayout1);
        xYLayout1.setHeight(264);
        xYLayout1.setWidth(350);
        jProgressBar1.setBorder(null);
        jLabel1.setText("jLabel1");
        this.getContentPane().add(jProgressBar1,
                                  new XYConstraints( -1, 227, 352, 23));
        this.getContentPane().add(jLabel1, new XYConstraints( -1, 0, 352, 230));

        jProgressBar1.setStringPainted(true);
        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setString("Not Forget is Now Loading...");
        jProgressBar1.setBackground(Color.white);
        jLabel1.setIcon(new ImageIcon("images/Index.jpg"));
        this.setUndecorated(true);

    }

    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.currentThread().sleep(10);
                jProgressBar1.setValue(jProgressBar1.getValue() + 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.dispose();
        mainFrame mainframe = new mainFrame();
        mainframe.setLocation(300, 200);
        mainframe.setSize(500, 400);
        mainframe.setVisible(true);
        Thread notForget = new Thread(mainframe, "Not_Forget");
        notForget.start();
    }

    public static void main(String[] args) {
        IndexFrame indexframe = new IndexFrame();
        indexframe.setSize(350, 246);
        indexframe.setLocationRelativeTo(null);
        indexframe.setVisible(true);
        Thread index = new Thread(indexframe,"Not_Forget");
        index.start();
    }

}
