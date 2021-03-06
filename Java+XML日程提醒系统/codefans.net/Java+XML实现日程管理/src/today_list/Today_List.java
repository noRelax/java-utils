package today_list;

import java.awt.*;

import javax.swing.*;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.*;
import list_all.List_All;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import main_frame.mainFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

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
public class Today_List extends JFrame {
    XYLayout xYLayout1 = new XYLayout();
    public JPanel jPanel1 = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    public JPanel jPanel3 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JPanel jPanel4 = new JPanel();
    JLabel jLabel2 = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    BorderLayout borderLayout1 = new BorderLayout();
    JScrollPane jScrollPane2 = new JScrollPane();
    BorderLayout borderLayout2 = new BorderLayout();
    String[] colHeads = {"编号", "标题", "提醒内容", "所属类别", "提醒时间"};
    //int rowCount;
    Object todayData[][] = {{"1", "测试标题", "测试内容", "普通提醒", "现在时间"}};
    Object overdueData[][] = null;
    mainFrame main_Frame = null;
    JTable jTodaytable = new JTable(todayData,colHeads);;
    JTable jOverduetable = new JTable(todayData,colHeads);;
    JTable jtable3 = null;
    JTable jtable4 = null;
    List_All list_all;
    public Today_List() {
        try {
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 设置行
     */
    public void setTodayRow(int rowCount){

    }

    private void jbInit() throws Exception {
        getContentPane().setLayout(xYLayout1);
        jPanel1.setLayout(xYLayout2);
        jPanel3.setBackground(new Color(236, 219, 255));
        jPanel3.setBorder(BorderFactory.createEtchedBorder());
        jPanel3.setLayout(borderLayout1);
        xYLayout1.setWidth(496);
        xYLayout1.setHeight(255);
        jLabel1.setText("今日提醒:");
        jLabel2.setText("过期提醒:");
        jPanel4.setBackground(new Color(236, 219, 255));
        jPanel4.setBorder(BorderFactory.createEtchedBorder());
        jPanel4.setLayout(borderLayout2);
        jTodaytable.addMouseListener(new Today_List_jTodaytable_mouseAdapter(this));
        jOverduetable.addMouseListener(new
                                       Today_List_jOverduetable_mouseAdapter(this));
        this.getContentPane().add(jPanel1, new XYConstraints(0, 0, 503, 254));
        jPanel1.add(jLabel1, new XYConstraints(18, 6, 95, 21));
        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jTodaytable);
        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);
        jScrollPane2.getViewport().add(jOverduetable);
        jPanel1.add(jLabel2, new XYConstraints(17, 128, 98, -1));
        jPanel1.add(jPanel4, new XYConstraints(2, 150, 495, 104));
        jPanel1.add(jPanel3, new XYConstraints(2, 29, 496, 94));
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }
    /**
     * 返回面板
     * @return JPanel
     */
    public JPanel getTodayList(){
        return this.jPanel1;
    }

    /**
     * 重新显示jtable.
     */
    public void refreshJTable() {
        //清空jpanel
        this.jPanel3.removeAll();
        //设置jTodaytable数据
        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        this.jtable3 = new JTable(this.setTodayData(),this.colHeads);
        this.jTodaytable = this.jtable3;
        jTodaytable.addMouseListener(new Today_List_jTodaytable_mouseAdapter(this));
        jScrollPane1.getViewport().add(jTodaytable);

        //设置jOverduetable数据
        this.setOverdueData();
         jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);
         this.jtable4 = new JTable(this.main_Frame.getOverdueData(), this.colHeads);
         this.jOverduetable = this.jtable4;
         jScrollPane2.getViewport().add(jOverduetable);
         jOverduetable.addMouseListener(new
                                       Today_List_jOverduetable_mouseAdapter(this));
         String Message = "今日提醒:  " + this.jTodaytable.getRowCount() + " , 过期提醒:  " +
                          this.jOverduetable.getRowCount() + ".";
         this.main_Frame.setStatusBar(Message);

    }

    /**
     * 获取主面板.
     * @param MainFrame Object
     */
    public void setMainFrame(Object MainFrame){
        this.main_Frame =(mainFrame) MainFrame;
    }

    /**
     * 设置今日的列表值
     */
    public Object[][] setTodayData(){
        Object[][] comPareData=this.main_Frame.getCompareData();
        int rowCount = comPareData.length;
        Object[][] todayMemoire = new Object[rowCount][5];
        for (int i = 0; i < rowCount; i++)
            for (int j = 0; j < 5; j++) {
                todayMemoire[i][j] = comPareData[i][j];
            }
        return todayMemoire;
    }

    /**
     * 设置过期的列表值.
     */
    public void setOverdueData(){
        this.overdueData = this.main_Frame.getOverdueData();
    }



    public void setListAll(Object listAll) {
        this.list_all = (List_All) listAll;
    }



    public void jTodaytable_mouseClicked(MouseEvent e) {
        int selectedRow = this.jTodaytable.getSelectedRow();
        String selectID = this.jTodaytable.getValueAt(selectedRow, 0).toString().
                          trim();
        this.list_all.setSelectID(selectID);
    }



    public void jOverduetable_mouseClicked(MouseEvent e) {
        int selectedRow = this.jOverduetable.getSelectedRow();
        String selectID = this.jOverduetable.getValueAt(selectedRow, 0).toString().
                          trim();
        this.list_all.setSelectID(selectID);

    }


    public static void main(String[] args) {
        Today_List today_list = new Today_List();
        today_list.setSize(500, 300);
        today_list.setVisible(true);
        today_list.setLocation(300, 300);
    }

}


class Today_List_jTodaytable_mouseAdapter extends MouseAdapter {
    private Today_List adaptee;
    Today_List_jTodaytable_mouseAdapter(Today_List adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jTodaytable_mouseClicked(e);
    }
}


class Today_List_jOverduetable_mouseAdapter extends MouseAdapter {
    private Today_List adaptee;
    Today_List_jOverduetable_mouseAdapter(Today_List adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jOverduetable_mouseClicked(e);
    }
}
