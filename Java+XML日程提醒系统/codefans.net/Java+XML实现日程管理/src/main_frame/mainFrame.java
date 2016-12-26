package main_frame;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.*;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.JToggleButton;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JScrollBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * import package
 */
import today_list.*;
import user_help.*;
import list_all.*;
import new_memoire.*;
import dboperate.*;
import java.awt.event.MouseEvent;
import system_setting.Memoire_Setting;
import awokeOperate.ShowFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import com.sun.media.sound.Toolkit;


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
public class mainFrame extends JFrame implements Runnable{
    xmlConn notForgetXmlConn = null;//new xmlConn("Not_Forget",9);
    /**
     * 新建对象,用来获取其它窗体的组件.
     */
    Object[][] Data = null;//notForgetXmlConn.getData();//获取所有记录.
    Object[][] compareDate = null;
    defaule_show ds = new defaule_show();
    Today_List tl = new Today_List();

    //所有列表.
    List_All list_all = new List_All();

    //创建更新xml对象.
    XmlUpdate xmlUpdate = new XmlUpdate();

    JPanel jPanel1 = new JPanel();
    XYLayout xYLayout1 = new XYLayout();
    JPanel jPanel2 = new JPanel();
    Border border1 = BorderFactory.createMatteBorder(6, 6, 6, 6, Color.gray);
    JToolBar jToolBar1 = new JToolBar();
    Border border2 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
            Color.white, new Color(165, 163, 151));
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JToolBar jToolBar2 = new JToolBar();
    JButton jButton4 = new JButton();
    JButton jButton5 = new JButton();
    JButton jButton6 = new JButton();
    JButton jButton7 = new JButton();
    JButton jButton8 = new JButton();
    JToolBar jToolBar3 = new JToolBar();
    JButton jButton9 = new JButton();
    PaneLayout paneLayout1 = new PaneLayout();
    XYLayout xYLayout2 = new XYLayout();
    JLabel statusBar = new JLabel();
    Object[] awokeData = new Object[9];//提醒数据.
    public int todayRow;//统计今天有多少行.
    public boolean isNewMemoire = false;//表示当前时候新建.如果新建了必须重新获取记录值.
    public void setisNewMemoire(){
        this.isNewMemoire = true;
    }
    public mainFrame() {
        try {
            this.notForgetXmlConn = new xmlConn("Not_Forget",9);
            this.Data = notForgetXmlConn.getData();//获取所有记录.
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getTodayData(this.Data);
        this.setTitle("-- ^_^ -- 勿忘 -- 提醒簿 -- ^_^ --");
        this.setSize(500,400);
        this.setResizable(false);//固定窗体大小
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);//关闭窗口时退出
        getContentPane().setLayout(xYLayout1);
        jToolBar1.setLayout(xYLayout1);
        this.getContentPane().setBackground(new Color(227, 228, 241));
        xYLayout1.setWidth(500);
        xYLayout1.setHeight(400);
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setLayout(xYLayout2);
        jPanel2.setBorder(BorderFactory.createLoweredBevelBorder());
        jPanel2.setLayout(paneLayout1);
        jToolBar1.setToolTipText("");
        jButton1.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
        jButton1.setText("新建");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        jButton2.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
        jButton2.setText("今日");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        jButton3.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
        jButton3.setText("列表");
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton3_actionPerformed(e);
            }
        });
        jButton4.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
        jButton4.setText("修改");
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton4_actionPerformed(e);
            }
        });
        jButton5.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
        jButton5.setText("删除");
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton5_actionPerformed(e);
            }
        });
        jButton6.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
        jButton6.setText("过滤");
        jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton6_actionPerformed(e);
            }
        });
        jButton7.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
        jButton7.setText("设置");
        jButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton7_actionPerformed(e);
            }
        });
        jButton8.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
        jButton8.setText("帮助");
        jButton8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton8_actionPerformed(e);
            }
        });
        jButton9.setFont(new java.awt.Font("宋体", Font.PLAIN, 15));
        jButton9.setText("退出");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton9_actionPerformed(e);
            }
        });
        statusBar.setText("-- ^_^ -- 欢迎使用勿忘软件 -- ^_^ --");
        this.getContentPane().add(jPanel1, new XYConstraints( 0, 329, 506, 32));
        this.getContentPane().add(jToolBar1, new XYConstraints( -3, 0, 507, 69));
        jToolBar1.add(jButton1, new XYConstraints(2, 10, 44, 51));
        jToolBar1.add(jButton3, new XYConstraints(97, 10, 44, 51));
        jToolBar1.add(jButton4, new XYConstraints(166, 10, 44, 51));
        jToolBar1.add(jButton6, new XYConstraints(263, 10, 44, 51));
        jToolBar1.add(jButton5, new XYConstraints(215, 10, 44, 51));
        jToolBar1.add(jButton7, new XYConstraints(326, 10, 44, 51));
        jToolBar1.add(jButton2, new XYConstraints(50, 10, 44, 51));
        jToolBar1.add(jButton8, new XYConstraints(372, 10, 44, 51));
        jToolBar1.add(jButton9, new XYConstraints(420, 10, 44, 51));
        jToolBar1.add(jToolBar3, new XYConstraints(311, 0, 15, 67));
        jToolBar1.add(jToolBar2, new XYConstraints(146, 0, 15, 68));
        jPanel1.add(statusBar, new XYConstraints(5, 0, 492, 26));
        jPanel2.add(this.ds.getDefaultShow(),new XYConstraints(0,69,500,260));
        this.getContentPane().add(jPanel2, new XYConstraints( -4, 69, 506, 261));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                mainFrame frame = (mainFrame) evt.getSource();
                frame.minimize(frame);
            }
        });


    }

    public void minimize(mainFrame frame) {
        minmizeFrame minmizeframe = new minmizeFrame();
        minmizeframe.setMainFrame(this);
        minmizeframe.setSize(200, 30);
        minmizeframe.setLocation(824, 715);
        minmizeframe.setVisible(true);
    }
    /**
     * 退出系统.
     */
    public void jButton9_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    /**
     * 今日列表
     */
    public void jButton2_actionPerformed(ActionEvent e) {
        List_All list = new List_All();
        this.list_all = list;
        Today_List today = new Today_List();
        //提供主面板.
        today.setMainFrame(this);
        //today.setTodayRow(this.todayRow);
        today.setListAll(this.list_all);
        today.refreshJTable();
        this.jPanel2.removeAll();
        this.jPanel2.add(today.getTodayList());
        this.jPanel2.updateUI();


    }


    /**
     * 显示整个列表
     * @param e ActionEvent
     */
    public void jButton3_actionPerformed(ActionEvent e) {
        this.showAllList();
    }



    /**
     * 帮助
     */
    public void jButton8_actionPerformed(ActionEvent e) {
        this.showHelp();
    }


    /**
     * 显示各个窗体
     */
    public void showDefaultShow(){//默认窗体
        defaule_show show = new defaule_show();
        this.jPanel2.removeAll();
        this.jPanel2.add(show.getDefaultShow());
        this.jPanel2.updateUI();
    }



        /**
         * 显示所有列表.
         */
        public void showAllList() { //所有列表
            List_All list = new List_All();
            this.list_all = list;
            this.jPanel2.removeAll();
            this.jPanel2.add(this.list_all.getAllListPanel());
            this.statusBar.setText("当前查看:  提醒列表共有 " + this.list_all.getMemoireCount() +
                                   " 条记录.-- ^_^--");
            this.list_all.setMainFrame(this);
            this.list_all.refreshJtable();
            this.jPanel2.updateUI();
            //设置选中的id为空防止在其它区域操作.
            this.list_all.setSelectID();
        }
    /**
     * 显示帮助
     */
    public void showHelp(){//帮助
        Help_HandBook help = new Help_HandBook();
        this.jPanel2.removeAll();
        this.jPanel2.add(help.getHelpPanel());
        this.jPanel2.updateUI();
        this.statusBar.setText("当前查看:-- ^_^-- 用户帮助 -- ^_^--");
        //设置选中的id为空防止在其它区域操作.
        this.list_all.setSelectID();
    }

    /**
     * 设置状态栏信息.
     * @param Message String
     */
    public void setStatusBar(String Message) {
        this.statusBar.setText(Message);
    }


    /**
     * 新建备忘
     */
    public void jButton1_actionPerformed(ActionEvent e) {
        this.showDefaultShow();
        New_Memoire new_memoir = new New_Memoire();
        new_memoir.setSize(500, 300);
        new_memoir.setLocationRelativeTo(null);
        new_memoir.setVisible(true);
        new_memoir.setMainFrame(this);
        //设置选中的id为空防止在其它区域操作.
        this.list_all.setSelectID();
    }

    /**
     * 删除记录.
     * @param e ActionEvent
     */
    public void jButton5_actionPerformed(ActionEvent e) {
        String selectID = this.list_all.getSelectID();
        if (selectID.equals("")) {
            JOptionPane.showMessageDialog(null, "抱歉,操作失败！\n 请先选择要删除的记录！");
        }else{
            int choose = JOptionPane.showConfirmDialog(null,
                    "确认删除编号为 ' " + selectID + " ' 的记录吗？\n 删除后不可恢复！",
                    "删除提示: ^_^ 请选择一个选项",
                    JOptionPane.YES_NO_OPTION);
            if (choose == 0) {
                xmlConn xml = new xmlConn("Not_Forget", 9);
                Object[][] allData = xml.getData();
                if (this.xmlUpdate.delData(allData, selectID)) {
                    this.showAllList();
                    JOptionPane.showMessageDialog(null, " ^_^ 操作成功！");
                }else{
                    JOptionPane.showMessageDialog(null, "抱歉,删除失败！");
                }
            }
        }
    }


    /**
     * 过滤
     * @param e ActionEvent
     */
    public void jButton6_actionPerformed(ActionEvent e) {
        String selectID = this.list_all.getSelectID();
        if (selectID.equals("")) {
            JOptionPane.showMessageDialog(null, "抱歉,操作失败！\n 请先选择要过滤的记录！");
        } else {
            int choose = JOptionPane.showConfirmDialog(null,
                    "确认将编号为 ' " + selectID + " ' 的备忘录过滤吗？",
                    "过滤提示: ^_^ 请选择一个选项",
                    JOptionPane.YES_NO_OPTION);
            if (choose == 0) {
                xmlConn xml = new xmlConn("Not_Forget", 9);
                Object[][] allData = xml.getData();
                if (this.xmlUpdate.filtrateMemoire(allData, selectID)) {
                    this.showAllList();
                    JOptionPane.showMessageDialog(null, " ^_^ 操作成功！");
                } else {
                    JOptionPane.showMessageDialog(null, "抱歉,操作失败！");
                }
            }
        }
    }

    /**
     * 修改记录
     * @param e ActionEvent
     */
    public void jButton4_actionPerformed(ActionEvent e) {
        try{
            Edit_Memoire editMemoire = new Edit_Memoire();
            editMemoire.setTitle("勿忘软件 -- 编辑备忘录 -- ^_^");
            editMemoire.setSize(500, 300);
            editMemoire.setEditValue(this.getAllEditMessage());
            //将frame传给Edit_Memoire,供操作完成后使用.
            editMemoire.setMainFrame(this);
            editMemoire.setLocationRelativeTo(null);
            editMemoire.setVisible(true);
        }catch(Exception e3){
            JOptionPane.showMessageDialog(null,"抱歉,操作失败！\n 请选择要操作的记录进行编辑！");
            //this.showAllList();
        }
    }

     //返回当前选中的id对应的所有信息.
    public Object[] getAllEditMessage(){
        xmlConn xml = new xmlConn("Not_Forget", 9);
        String selectID = this.list_all.getSelectID();
        //获取所有备忘录信息.
        Object[][] memoireData = xml.getData();
        //用来存储单个id对应的所有信息.
        Object[] allEidtMessage = new Object[9];
            /**
             * 将id相同的行所有信息取出.
             */
            for (int i = 0; i < memoireData.length; i++) {
                String thisID = memoireData[i][0].toString().trim();
                if (thisID.equals(selectID)) {
                    for (int j = 0; j < memoireData[i].length; j++) {
                        allEidtMessage[j] = memoireData[i][j];
                    }
                }
            }
        return allEidtMessage;
    }

    //供其它窗体使用.
    public Object getMainFrame(){
        return this;
    }


    /**
     * 设置面板
     * @param e ActionEvent
     */
    public void jButton7_actionPerformed(ActionEvent e) {
        /*
          Memoire_Setting memoire_setting = new Memoire_Setting();
          memoire_setting.setLocation(300, 200);
          memoire_setting.setSize(400, 300);
          memoire_setting.setVisible(true);
         */
    }

    /**
     * 提醒方法
     * @param args String[]
     */
    //弹出消息
    public void showMessageDialog(String Message){
        JOptionPane.showMessageDialog(null,Message);
    }

    //窗口闪烁
    public void showFrameFlash(){

    }
    //弹出窗口.
    public void showFrame(){
            ShowFrame showFrame = new ShowFrame();
            //设置显示内容
            showFrame.setFrameTitle("提醒窗口: 第" + this.awokeData[0].toString() +
                                    "条备忘录");
            showFrame.setMemoireTitle(this.awokeData[1].toString());
            showFrame.setMemoireContent("         " +
                                        this.awokeData[2].toString());
            //提供主面板,使其调用内部方法.
            showFrame.setMainFrame(this);
            //设置id
            showFrame.setEditID(this.awokeData[0].toString());
            //设置所有数据.
            showFrame.setAllData(this.Data);
            //设置更改的数据.
            showFrame.setEditValue(this.awokeData);
            showFrame.setSize(400, 250);
            showFrame.setLocationRelativeTo(null);
            showFrame.setVisible(true);
    }
    /**
     * 把所有的数据返回.
     * @return Object[][]
     */
    public Object[][] getAllData(){
        return this.Data;
    }
    /**
     * 返回编辑的数据
     * @return Object[]
     */
    public Object[] getThisMemoireData(){
        return this.awokeData;
    }




    //播放声音
    public void playSound(){

    }


    /**
     *得到与当前相比较的数据.
     * @return Object[][]
     */
    public void setCompareData() {
        if(this.isNewMemoire){
            //如果新建了数据,则更改记录.
            xmlConn getValue = new xmlConn("Not_Forget",9);
            this.Data = getValue.getData();
            this.getTodayData(this.Data);
            this.list_all = new List_All();
            this.isNewMemoire = false;
        }
    }

    public Object[][] getCompareData(){
        return this.compareDate;
    }

    /**
     * 获取今日的数据.
     * @return Object[][]
     */
    public void getTodayData(Object[][] allData) {
        //获取今日有用的的提醒.
        int allDataRowCount = this.Data.length;
        int compareRowCount = 0;
        int memoireRow = 0;
        java.util.Date utildate = new java.util.Date();
        String systemDate = utildate.toString().substring(0, 10).trim();
        //计算今天要提醒的行数.
        for (int i = 0; i < this.Data.length; i++) {
            String getDate = this.Data[i][4].toString().substring(0,10);
            String isYouxiao = this.Data[i][7].toString();
            if(getDate.equals(systemDate)&& isYouxiao.equals("1")){
                compareRowCount++;
            }
        }
        this.todayRow = compareRowCount;
        //得到行后给compareData分配内存.
        Object[][] Memoire = new Object[compareRowCount][9];
        for (int i = 0; i < this.Data.length; i++) {
            String getDate = this.Data[i][4].toString().substring(0, 10);
            String isYouxiao = this.Data[i][7].toString();
            //System.out.println(this.Data[i][0]+"getDate :" + getDate + "  isYouxiao:" + isYouxiao);
            if (getDate.equals(systemDate) && isYouxiao.equals("1")) {
                for (int j = 0; j < 9; j++) {
                    Memoire[memoireRow][j] = this.Data[i][j];
                }//else后可计算过期提醒.
                memoireRow++;
            }
        }
        this.compareDate = Memoire;
    }




    /**
     * 获取过期数据.
     * @return Object[][]
     */
    public Object[][] getOverdueData() {
        //获取过期提醒.
        int allDataRowCount = this.Data.length;
        int overdueRowCount = 0;
        int memoireRow = 0;
        //计算过期提醒的行数.
        for (int i = 0; i < this.Data.length; i++) {
            String auditing = this.Data[i][8].toString();
            String isYouxiao = this.Data[i][7].toString();
            if(auditing.equals("0")&& isYouxiao.equals("0")){
                overdueRowCount++;
            }
        }
        //得到行后给OverdueData分配内存.
        Object[][] OverdueData = new Object[overdueRowCount][9];
        for (int i = 0; i < this.Data.length; i++) {
            String getDate = this.Data[i][8].toString();
            String isYouxiao = this.Data[i][7].toString();
            if (getDate.equals("0") && isYouxiao.equals("0")) {
                for (int j = 0; j < 9; j++) {
                    OverdueData[memoireRow][j] = this.Data[i][j];
                }
                memoireRow++;
            }
        }
        return OverdueData;
    }



        public Object[] getEditValue(){
            Object[] editingMemoire = new Object[9];
            for (int i = 0; i < editingMemoire.length; i++) {
                if (i == 8 || i == 7) {
                    editingMemoire[i] = 0;
                } else {
                    editingMemoire[i] = this.awokeData[i];
                }
            }
            return editingMemoire;
    }

    /**
     * 提醒操作.
     * @param awokeModel String
     */
    public void setAwokeMode(String awokeModel) {
        if (awokeModel.equals("默认提醒")) {
            this.showMessageDialog(this.awokeData[2].toString());
        } else if (awokeModel.equals("弹出窗口")) {
            this.showFrame();
        } else if (awokeModel.equals("闪烁提醒")) {
            this.showFrameFlash();
        } else if (awokeModel.equals("播放声音")) {
            this.playSound();
        }else{
            this.showMessageDialog(this.awokeData[2].toString());
        }
    }
    /**
     * 当前时间与数组时间相比较.
     */
    public void compareTime() {
        java.util.Date utildate = new java.util.Date();
        String nowTime = utildate.toString().substring(11, 16).trim();
        for (int i = 0; i < this.compareDate.length; i++) {
            String getTime = this.compareDate[i][4].toString().substring(11, 16).
                             trim();
            String awokeModel = this.compareDate[i][5].toString();
            if (nowTime.compareTo(getTime) <= 0) {
                if (nowTime.equals(getTime)) {
                    //提醒.
                    for (int j = 0; j < this.compareDate[i].length; j++) {
                        this.awokeData[j] = this.compareDate[i][j];
                    }
                    //执行提醒方法.
                    this.setAwokeMode(awokeModel);
                    XmlUpdate update = new XmlUpdate();
                    update.updateData(this.getEditValue(), this.Data,this.awokeData[0].toString());
                    //告知主窗体已经有数据更改.
                    this.setisNewMemoire();

                }
            }
        }
    }

    //线程run方法实现
    public void run() {
        while(true){
            //获取比较的数据.
            this.setCompareData();
            //与当前时间相比较
            this.compareTime();
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        mainFrame mainframe = new mainFrame();
        mainframe.setLocation(300, 200);
        mainframe.setSize(500, 400);
        mainframe.setVisible(true);
        Thread notForget = new Thread(mainframe,"Not_Forget");
        notForget.start();

    }

}


