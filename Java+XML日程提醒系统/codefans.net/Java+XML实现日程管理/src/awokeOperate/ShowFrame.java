package awokeOperate;

import java.awt.*;

import javax.swing.*;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.*;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dboperate.XmlUpdate;
import main_frame.mainFrame;
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
public class ShowFrame extends JFrame {
    XYLayout xYLayout1 = new XYLayout();
    JPanel jPanel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    XYLayout xYLayout2 = new XYLayout();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JLabel jTitle = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea jTextArea1 = new JTextArea();
    mainFrame main_Frame = null;
    String EditID;
    Object[] editValue = null;
    Object[][] allData = null;
    public ShowFrame() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(xYLayout1);
        jPanel1.setBackground(new Color(228, 236, 234));
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setLayout(xYLayout2);
        this.getContentPane().setBackground(new Color(236, 233, 255));
        jButton1.setText("确定");

        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });

        jButton2.setText("稍后提醒我");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        jTitle.setText("jLabel3");
        jTextArea1.setText("jTextArea1");
        jLabel2.setText("提醒内容:");
        jLabel1.setText("提醒标题:");
        this.getContentPane().add(jTitle, new XYConstraints(96, 8, 293, -1));
        this.getContentPane().add(jPanel1, new XYConstraints(0, 35, 401, 137));
        jPanel1.add(jLabel2, new XYConstraints(29, 5, 66, -1));
        jPanel1.add(jScrollPane1, new XYConstraints(9, 23, 376, 100));
        jScrollPane1.getViewport().add(jTextArea1);
        this.getContentPane().add(jLabel1, new XYConstraints(31, 7, 66, -1));
        this.getContentPane().add(jButton1, new XYConstraints(157, 177, 94, -1));
        this.getContentPane().add(jButton2, new XYConstraints(270, 177, 106, -1));
        this.setTitle("提醒窗口 -- ^_^ --");
        jTextArea1.setLineWrap(true); //激活自动换行功能
        jTextArea1.setWrapStyleWord(true); //激活断行不断字功能
        jTextArea1.setFocusable(false);//不能获取焦点.
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }

    /**
     * 确定
     * @param e ActionEvent
     */
    public void jButton1_actionPerformed(ActionEvent e) {
        XmlUpdate update = new XmlUpdate();
        if (update.updateData(this.editValue, this.allData, this.EditID)) {
            this.dispose();
            //告知主窗体已经有数据更改.
            this.main_Frame.setisNewMemoire();
        } else {
            JOptionPane.showMessageDialog(null, "抱歉,操作失败!");
            this.dispose();
        }
    }
    /**
     * 稍后提醒.
     * @param e ActionEvent
     */
    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
        //更改时间.
    }


    /**
     * 设置标题.
     */
    public void setMemoireTitle(String title) {
        this.jTitle.setText(title);
    }
    /**
     * 设置内容
     * @param content String
     */
    public void setMemoireContent(String content){
        this.jTextArea1.setText(content);
    }
    /**
     * 设置窗体标题
     * @param title String
     */
    public void setFrameTitle(String title){
        this.setTitle(title);
    }

    /**
     * 从mainFrame获取数据.
     * @param MainFrame Object
     */
    public void setMainFrame(Object MainFrame){
        this.main_Frame =(mainFrame) MainFrame;
    }

    /**
     * 获取当前的id
     */
    public void setEditID(String id){
        this.EditID = id;
    }
    /**
     * 得到当前单条记录的数据
     */
    public void setEditValue(Object[] Editing){
        Object[] editingMemoire = new Object[9];
        for (int i = 0; i < editingMemoire.length; i++) {
            if (i == 8 || i == 7) {
                editingMemoire[i] = 0;
            } else {
                editingMemoire[i] = Editing[i];
            }
        }
        this.editValue = editingMemoire;
    }
    /**
     * 得到所有的数据.
     */
    public void setAllData(Object[][] AllData){
        this.allData = AllData;
    }


    public static void main(String[] args) {
        ShowFrame showframe = new ShowFrame();
        showframe.setSize(400,250);
        showframe.setLocationRelativeTo(null);
        showframe.setVisible(true);
    }

}






