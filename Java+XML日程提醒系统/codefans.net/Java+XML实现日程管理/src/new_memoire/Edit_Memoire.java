package new_memoire;

import java.awt.*;

import javax.swing.*;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.text.*;
import dboperate.*;
import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import main_frame.mainFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

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
public class Edit_Memoire extends JFrame {
    //新建mainframe对象,供编辑完毕后操作.
    mainFrame main_frame = null;
    XYLayout xYLayout1 = new XYLayout();
    JTextArea jTextArea1 = new JTextArea();
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    TitledBorder titledBorder1 = new TitledBorder("");
    JLabel jLabel1 = new JLabel();
    XYLayout xYLayout3 = new XYLayout();
    JLabel jLabel2 = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea jTextArea2 = new JTextArea();
    JTextField jTextField1 = new JTextField();
    TitledBorder titledBorder2 = new TitledBorder("");
    JPanel jPanel3 = new JPanel();
    XYLayout xYLayout4 = new XYLayout();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JComboBox jComboBox_Class = new JComboBox();
    JComboBox jComboBox_mode = new JComboBox();
    /**
     * 动态读取提醒类别.
     */
    xmlConn classXmlConn = new xmlConn("Class_Name", 2);
    Object[][] data = classXmlConn.getData(); //获得类别的所有值

    xmlConn memoireXmlConn = new xmlConn("Not_Forget", 9);
    Object[][] memoireData = memoireXmlConn.getData(); //获得提醒记录所有值

    /**
     * 设置时间模式.
     */
    SpinnerModel model2 = new SpinnerDateModel();
    JSpinner jSpinner_Time = new JSpinner(model2);

    JCheckBox jCheckBox_use = new JCheckBox();
    JCheckBox jCheckBox_confirm = new JCheckBox();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JCheckBox jCheckBox1 = new JCheckBox();
    JLabel jLabel_Time = new JLabel();
    String editID = new String("");
    public Edit_Memoire() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setBackground(new Color(236, 233, 234));
        this.setTitle("勿忘提醒 -- 新建备忘录 -- ^_^");
        getContentPane().setLayout(xYLayout1);
        jTextArea1.setText("      编辑提示:\n      1)按新建时的要求编辑内容.\n      2)重新指定提醒时间需要选中复选框才有效.");
        jTextArea1.setFocusable(false);//不能获取焦点.
        xYLayout1.setWidth(490);
        xYLayout1.setHeight(259);
        jPanel1.setBackground(new Color(236, 233, 255));
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setLayout(xYLayout3);
        jPanel2.setLayout(xYLayout2);
        jPanel2.setBackground(new Color(236, 233, 255));
        jPanel2.setBorder(titledBorder1);
        jLabel1.setText("提醒名称:");
        jLabel2.setText("提醒内容:");
        jPanel3.setLayout(xYLayout4);
        jPanel3.setBackground(new Color(236, 233, 255));
        jPanel3.setBorder(BorderFactory.createEtchedBorder());
        jLabel3.setText("提醒类别:");
        jLabel4.setText("提醒方式:");
        jLabel6.setToolTipText("");
        jLabel6.setText("从前指定的时间为:");
        jCheckBox_use.setBackground(new Color(236, 233, 255));
        jCheckBox_use.setSelected(true);
        jCheckBox_use.setText("提醒有效");
        jCheckBox_confirm.setBackground(new Color(236, 233, 255));
        jCheckBox_confirm.setText("提醒需要确认");
        jButton1.setText("确定");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        jButton2.setText("取消");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        jSpinner_Time.setFont(new java.awt.Font("Dialog", Font.PLAIN, 15));
        jCheckBox1.setBackground(new Color(236, 233, 255));
        jCheckBox1.setText("重新指定时间,选中有效");
        jCheckBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jCheckBox1_actionPerformed(e);
            }
        });
        jLabel_Time.setText("jLabel5");
        this.getContentPane().add(jTextArea1, new XYConstraints(0, 0, 500, -1));
        this.getContentPane().add(jPanel2, new XYConstraints( -2, 217, 494, 36));
        jPanel1.add(jLabel1, new XYConstraints(23, 7, 58, -1));
        jPanel1.add(jScrollPane1, new XYConstraints(22, 53, 254, 88));
        jPanel1.add(jTextField1, new XYConstraints(86, 4, 190, -1));
        jPanel1.add(jLabel2, new XYConstraints(23, 33, 58, -1));
        this.getContentPane().add(jPanel3, new XYConstraints(288, 54, 204, 164));
        jScrollPane1.getViewport().add(jTextArea2);
        jPanel3.add(jComboBox_Class, new XYConstraints(70, 6, 109, 20));
        jPanel3.add(jLabel3, new XYConstraints(7, 8, 59, -1));
        jPanel2.add(jButton1, new XYConstraints(295, 2, 70, 22));
        jPanel2.add(jButton2, new XYConstraints(384, 2, 70, 22));
        jPanel2.add(jCheckBox_confirm, new XYConstraints(118, 2, 108, -1));
        jPanel2.add(jCheckBox_use, new XYConstraints(19, 2, 77, -1));
        jPanel3.add(jComboBox_mode, new XYConstraints(70, 44, 108, 20));
        jPanel3.add(jLabel4, new XYConstraints(7, 44, 59, -1));
        this.getContentPane().add(jPanel1, new XYConstraints(0, 54, 285, 163));
        jPanel3.add(jSpinner_Time, new XYConstraints(4, 130, 159, 23));
        jPanel3.add(jLabel6, new XYConstraints(8, 68, 150, 18));
        jPanel3.add(jLabel_Time, new XYConstraints(5, 86, 181, 20));
        jPanel3.add(jCheckBox1, new XYConstraints(1, 106, 184, -1));
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        jComboBox_mode.addItem("默认提醒");
        jComboBox_mode.addItem("弹出窗口");
        jComboBox_mode.addItem("闪烁提醒");
        jComboBox_mode.addItem("播放声音");
        this.jSpinner_Time.setEnabled(false);
        /**
         * 获取系统时间.
         */

        /**
         * 将提醒类别动态加载进来.
         */
        for(int i=0;i<this.data.length;i++){
               this.jComboBox_Class.addItem(data[i][1]);
            }


    }

    public void jButton2_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    /**
     * 提交信息.
     * @param e ActionEvent
     */
    public void jButton1_actionPerformed(ActionEvent e) {
        if(this.checkFrame()){
                XmlUpdate update = new XmlUpdate();
                if(update.updateData(this.getEditValue(),this.memoireData,this.editID)){
                    this.dispose();
                    this.main_frame.showAllList();
                    this.main_frame.setisNewMemoire();
                }else{
                    JOptionPane.showMessageDialog(null,"抱歉,操作失败!");
                    this.dispose();
                }
        }
    }

    /**
     * 取得当前运行的mainFrame窗体.
     * @param mainFrame Object
     */
    public void setMainFrame(Object mainFrame) {
        this.main_frame = (mainFrame) mainFrame;
    }

    /**
     * 取得新建的所有信息.
     * @return Object[]
     */
    public Object[] getEditValue(){
        Object editValue[] = new Object[9];
        try {
            editValue[0] = this.editID;
            editValue[1] = this.jTextField1.getText();
            editValue[2] = this.jTextArea2.getText();
            editValue[3] = this.jComboBox_Class.getSelectedItem();

            if (this.jCheckBox1.isSelected()) {
                editValue[4] = this.jSpinner_Time.getValue();
            }else{
                editValue[4] = this.jLabel_Time.getText();
            }
            editValue[5] = this.jComboBox_mode.getSelectedItem();
            if (this.jCheckBox_confirm.isSelected()) {
                editValue[6] = 1;
            } else {
                editValue[6] = 0;
            }
            if (this.jCheckBox_use.isSelected()) {
                editValue[7] = 1;
            } else {
                editValue[7] = 0;
            }
            if (this.jCheckBox_confirm.isSelected()) {
                editValue[8] = 1;
            }else{
                editValue[8] = 0;
            }
        } catch (Exception ex) {
            System.out.println("getvalue catch"+ex.getMessage());
            return null;
        }
        return editValue;
    }



    /**
     * 取得所有信息.包括插入的信息.
     * @param args String[]
     */
    public void setEditValue(Object[] frameValue) {
        this.editID = frameValue[0].toString();

        this.jTextField1.setText(frameValue[1].toString());
        this.jTextArea2.setText(frameValue[2].toString());
        this.jComboBox_Class.setSelectedItem(frameValue[3].toString());
        this.jLabel_Time.setText(frameValue[4].toString());
        this.jComboBox_mode.setSelectedItem(frameValue[5].toString());
        if (frameValue[6].toString().equals("1")) {
            this.jCheckBox_confirm.setSelected(true);
        } else {
            this.jCheckBox_confirm.setSelected(false);
        }
        if (frameValue[7].toString().equals("1")) {
            this.jCheckBox_use.setSelected(true);
        } else {
            this.jCheckBox_use.setSelected(false);
        }
        if (frameValue[8].toString().equals("1")) {
            this.jCheckBox_confirm.setSelected(true);
        }else{
            this.jCheckBox_confirm.setSelected(false);
        }
    }




    /**
     * 检查输入时候正确.
     * @param args String[]
     */
    public boolean checkFrame(){
        if (this.jTextArea2.getText().equals("") ||
            this.jTextField1.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"抱歉,标题和内容不能为空!");
            return false;
        }else{
            return true;
        }
    }

    /**
     * 监听是否重新指定时间。
     * @param e ChangeEvent
     */
    public void jCheckBox1_actionPerformed(ActionEvent e) {
        if (this.jCheckBox1.isSelected()) {
            this.jSpinner_Time.setEnabled(true);
        } else {
            this.jSpinner_Time.setEnabled(false);
        }
    }


    public static void main(String[] args) {
        Edit_Memoire em = new Edit_Memoire();
        em.setSize(500, 300);
        em.setLocationRelativeTo(null);
        em.setVisible(true);
    }

}



