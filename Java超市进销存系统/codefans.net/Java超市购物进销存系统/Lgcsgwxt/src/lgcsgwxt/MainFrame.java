package lgcsgwxt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.tree.TreePath;
import lgcsgwxt.dialog.*;
import lgcsgwxt.means.*;
import lgcsgwxt.*;
import java.util.Vector;
import java.awt.Dimension;

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
public class MainFrame extends JFrame {
    JPanel contentPane;
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenuFile = new JMenu();
    JToolBar jToolBar = new JToolBar();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    ImageIcon image1 = new ImageIcon(lgcsgwxt.MainFrame.class.getResource(
            "openFile.png"));
    ImageIcon image2 = new ImageIcon(lgcsgwxt.MainFrame.class.getResource(
            "closeFile.png"));
    ImageIcon image3 = new ImageIcon(lgcsgwxt.MainFrame.class.getResource(
            "help.png"));
    JLabel statusBar = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTree jTree1 = new JTree();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenu jMenu1 = new JMenu();
    JMenu jMenu2 = new JMenu();
    JMenu jMenu3 = new JMenu();
    JMenu jMenu4 = new JMenu();
    JMenuItem jMenuItem5 = new JMenuItem();
    JMenuItem jMenuItem7 = new JMenuItem();
    JMenuItem jMenuItem9 = new JMenuItem();
    JMenu jMenu5 = new JMenu();
    JMenuItem jMenuItem10 = new JMenuItem();
    JMenuItem jMenuItem11 = new JMenuItem();
    JMenuItem jMenuItem17 = new JMenuItem();
    JMenu jMenu7 = new JMenu();
    JMenuItem jMenuItem18 = new JMenuItem();
    JMenuItem jMenuItem19 = new JMenuItem();
    JScrollPane jScrollPane2 = new JScrollPane();
    JTable jTable1 = new JTable();
    TitledBorder titledBorder1 = new TitledBorder("");
    JMenuItem jMenuItem2 = new JMenuItem();
    JMenuItem jMenuItem3 = new JMenuItem();
    JMenuItem jMenuItem15 = new JMenuItem();
    JMenuItem jMenuItem16 = new JMenuItem();
    JMenu jMenu9 = new JMenu();
    JMenuItem jMenuItem8 = new JMenuItem();
    JMenuItem jMenuItem20 = new JMenuItem();
    JMenuItem jMenuItem21 = new JMenuItem();
    JMenuItem jMenuItem22 = new JMenuItem();
    JMenuItem jMenuItem23 = new JMenuItem();
    JMenuItem jMenuItem24 = new JMenuItem();
    JMenuItem jMenuItem25 = new JMenuItem();
    public MainFrame() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Component initialization.
     *
     * @throws java.lang.Exception
     */
    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        setSize(new Dimension(930, 600));
        setTitle("鲁广超市进销存系统");
        statusBar.setText(" ");
        statusBar.setBounds(new Rectangle(0, 585, 800, 15));
        //设置树
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("进销存管理");

        DefaultMutableTreeNode Man2 = new DefaultMutableTreeNode("销售管理");
        DefaultMutableTreeNode Man3 = new DefaultMutableTreeNode("库存查询");
        DefaultMutableTreeNode Man4 = new DefaultMutableTreeNode("用户管理");
        DefaultMutableTreeNode Man5 = new DefaultMutableTreeNode("信息查询");
        DefaultMutableTreeNode Man6 = new DefaultMutableTreeNode("商品管理");

        DefaultMutableTreeNode annal = new DefaultMutableTreeNode("入库/出库");
        DefaultMutableTreeNode amend = new DefaultMutableTreeNode("进/退货");
        DefaultMutableTreeNode delete = new DefaultMutableTreeNode("新增商品");
        jMenuItem1.addActionListener(new MainFrame_jMenuItem1_actionAdapter(this));

        jMenuItem2.setText("添加/修改用户");
        jMenuItem2.addActionListener(new MainFrame_jMenuItem2_actionAdapter(this));
        jMenuItem3.setText("个人账户查询");
        jMenuItem3.addActionListener(new MainFrame_jMenuItem3_actionAdapter(this));
        jToolBar.setBackground(SystemColor.control);
        jMenuItem9.addActionListener(new MainFrame_jMenuItem9_actionAdapter(this));
        jMenuItem15.setText("后台转前台");
        jMenuItem15.addActionListener(new MainFrame_jMenuItem15_actionAdapter(this));
        jMenuItem17.addActionListener(new MainFrame_jMenuItem17_actionAdapter(this));
        jMenuItem16.setText("新增商品");
        jMenuItem16.addActionListener(new MainFrame_jMenuItem16_actionAdapter(this));
        jMenuItem10.addActionListener(new MainFrame_jMenuItem10_actionAdapter(this));
        jMenuItem7.addActionListener(new MainFrame_jMenuItem7_actionAdapter(this));
        jMenuItem11.addActionListener(new MainFrame_jMenuItem11_actionAdapter(this));
        jMenu9.setText("按商品类别查询");
        jMenuItem8.setText("电器类查询");
        jMenuItem8.addActionListener(new MainFrame_jMenuItem8_actionAdapter(this));
        jMenuItem20.setText("礼品类查询");
        jMenuItem20.addActionListener(new MainFrame_jMenuItem20_actionAdapter(this));
        jMenuItem21.setText("日用类查询");
        jMenuItem21.addActionListener(new MainFrame_jMenuItem21_actionAdapter(this));
        jMenuItem22.setText("蔬果类查询");
        jMenuItem22.addActionListener(new MainFrame_jMenuItem22_actionAdapter(this));
        jMenuItem23.setText("食品类查询");
        jMenuItem23.addActionListener(new MainFrame_jMenuItem23_actionAdapter(this));
        jMenuItem24.setText("生鲜类查询");
        jMenuItem24.addActionListener(new MainFrame_jMenuItem24_actionAdapter(this));
        jMenuItem18.addActionListener(new MainFrame_jMenuItem18_actionAdapter(this));
        jMenuItem19.addActionListener(new MainFrame_jMenuItem19_actionAdapter(this));
        jMenuItem25.setText("按商品条形码查询");
        jMenuItem25.addActionListener(new MainFrame_jMenuItem25_actionAdapter(this));
        contentPane.setBackground(Color.pink);
        jMenuFile.setBackground(Color.pink);
        jMenu1.setBackground(Color.pink);
        jMenu2.setBackground(Color.pink);
        jMenu3.setBackground(Color.pink);
        jMenu5.setBackground(Color.pink);
        jMenu4.setBackground(Color.pink);
        jMenuItem5.addActionListener(new MainFrame_jMenuItem5_actionAdapter(this));
        Man6.add(annal);
        Man6.add(amend);
        Man6.add(delete);

        jMenuFile.setActionCommand("File");
        jMenuItem1.setText("退出");
        jMenu1.setText("采购");
        jMenu2.setText("仓库");
        jMenu3.setText("账户管理");
        jMenu4.setText("作者信息");
        jMenuItem5.setText("作者");
        jMenuItem7.setText("入库/出库");
        jMenuItem9.setText("进/退货");
        jMenu5.setText("查询");
        jMenuItem10.setText("采购单");
        jMenuItem11.setText("付款单");
        jMenuItem17.setText("销售统计");
        jMenu7.setText("查询商品信息");
        jMenuItem18.setText("按商品名称查询");
        jMenuItem19.setText("按商品编号查询");
        jScrollPane2.setBorder(BorderFactory.createLineBorder(Color.black));
        jScrollPane2.setBounds(new Rectangle(167, 93, 750, 435));

        root.add(Man2);
        root.add(Man3);
        root.add(Man4);
        root.add(Man5);
        root.add(Man6);

        DefaultMutableTreeNode Sells = new DefaultMutableTreeNode("销售统计");
        Man2.add(Sells);
        DefaultMutableTreeNode Storeinfr = new DefaultMutableTreeNode("库存信息浏览");
        DefaultMutableTreeNode Store1 = new DefaultMutableTreeNode("按库存商品编号查询");

        Man3.add(Storeinfr);
        Man3.add(Store1);

        DefaultMutableTreeNode UserNa = new DefaultMutableTreeNode("添加/修改用户");
        DefaultMutableTreeNode UserAdd = new DefaultMutableTreeNode("个人账户查询");

        Man4.add(UserNa);
        Man4.add(UserAdd);
        DefaultMutableTreeNode search1 = new DefaultMutableTreeNode("按商品名称查询");
        DefaultMutableTreeNode search2 = new DefaultMutableTreeNode("按商品编号查询");
        DefaultMutableTreeNode search3 = new DefaultMutableTreeNode("按商品条形码查询");
        DefaultMutableTreeNode search5 = new DefaultMutableTreeNode("按商品类别查询");

        DefaultMutableTreeNode sort1 = new DefaultMutableTreeNode("电器类查询");
        DefaultMutableTreeNode sort2 = new DefaultMutableTreeNode("礼品类查询");
        DefaultMutableTreeNode sort3 = new DefaultMutableTreeNode("日用类查询");
        DefaultMutableTreeNode sort4 = new DefaultMutableTreeNode("蔬果类查询");
        DefaultMutableTreeNode sort5 = new DefaultMutableTreeNode("食品类查询");
        DefaultMutableTreeNode sort6 = new DefaultMutableTreeNode("生鲜类查询");

        search5.add(sort1);
        search5.add(sort2);
        search5.add(sort3);
        search5.add(sort4);
        search5.add(sort5);
        search5.add(sort6);

        Man5.add(search1);
        Man5.add(search2);
        Man5.add(search3);
        Man5.add(search5);

        //初始化树
        jTree1 = new JTree(root);
        jTree1.addTreeSelectionListener(new
                                        MainFrame_jTree1_treeSelectionAdapter(this));
        jMenuFile.setText("文件");
        jToolBar.setBounds(new Rectangle(0, 0, 800, 29));
        jScrollPane1.setBounds(new Rectangle(13, 93, 152, 435));
        jMenuBar1.add(jMenuFile);
        jMenuFile.add(jMenuItem1);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu3);
        jMenuBar1.add(jMenu5);
        jMenuBar1.add(jMenu4);
        setJMenuBar(jMenuBar1);
        jButton1.setIcon(image1);
        jButton1.setToolTipText("Open File");
        jButton2.setIcon(image2);
        jButton2.setToolTipText("Close File");
        jButton3.setIcon(image3);
        jButton3.setToolTipText("Help");
        jToolBar.add(jButton1);
        jToolBar.add(jButton2);
        jToolBar.add(jButton3);
        contentPane.add(jToolBar, null);
        contentPane.add(statusBar, null);
        contentPane.add(jScrollPane1);
        contentPane.add(jScrollPane2);
        jScrollPane2.getViewport().add(jTable1);
        jScrollPane1.getViewport().add(jTree1);
        jMenu3.add(jMenuItem2);
        jMenu3.add(jMenuItem3);
        jMenu3.add(jMenuItem15);
        jMenu4.add(jMenuItem5);
        jMenu2.add(jMenuItem7);
        jMenu2.add(jMenuItem9);
        jMenu2.add(jMenuItem16);
        jMenu1.add(jMenuItem10);
        jMenu1.add(jMenuItem11);
        jMenu5.add(jMenuItem17);
        jMenu5.add(jMenu7);
        jMenu7.add(jMenuItem18);
        jMenu7.add(jMenuItem25);
        jMenu7.add(jMenuItem19);
        jMenu7.add(jMenu9);
        jMenu9.add(jMenuItem8);
        jMenu9.add(jMenuItem20);
        jMenu9.add(jMenuItem21);
        jMenu9.add(jMenuItem22);
        jMenu9.add(jMenuItem23);
        jMenu9.add(jMenuItem24);
    }

    /**
     * Help | About action performed.
     *
     * @param actionEvent ActionEvent
     */
    void jMenuHelpAbout_actionPerformed(ActionEvent actionEvent) {
        MainFrame_AboutBox dlg = new MainFrame_AboutBox(this);
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
                        (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.pack();
        dlg.setVisible(true);
    }

    public void jMenuItem1_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void select1(String nubmer) { //输入商品类别参数,把结果显示到表中
        Vector tablehead = new Vector();
        tablehead = Select_Means.tablehead();
        Vector dqselect = new Vector();
        dqselect = Select_Means.Select_sort1(nubmer);
        jTable1 = Mytable.maketable(dqselect, tablehead); //显示内容
        jScrollPane2.getViewport().add(jTable1); //把表装入容器

    }

    private void select_number() { //按商品编号查询
        String nubmer = JOptionPane.showInputDialog(this, "请输入商品编号", "提示",
                JOptionPane.INFORMATION_MESSAGE);

        if (nubmer.length() != 0) {
            Vector tablehead = new Vector();
            tablehead = Select_Means.tablehead();
            Vector dqselect = new Vector();
            dqselect = Select_Means.Select_sort2(nubmer);
            if (dqselect.size() == 0) {
                JOptionPane.showMessageDialog(this, "商品编号不存在", "提示",
                                              JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            jTable1 = Mytable.maketable(dqselect, tablehead); //显示内容
            jScrollPane2.getViewport().add(jTable1); //把表装入容器
        } else {
            JOptionPane.showMessageDialog(this, "输入不能为空值", "提示",
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }

    private void select_name() { //按商品名称查询
        String nubmer = JOptionPane.showInputDialog(this, "请输入商品名称",
                "提示", JOptionPane.INFORMATION_MESSAGE);
        if (nubmer.length() != 0) {
            Vector tablehead = new Vector();
            tablehead = Select_Means.tablehead();
            Vector dqselect = new Vector();
            dqselect = Select_Means.Select_sort3(nubmer);
            if (dqselect.size() == 0) {
                JOptionPane.showMessageDialog(this, "商品不存在", "提示",
                                              JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            jTable1 = Mytable.maketable(dqselect, tablehead); //显示内容
            jScrollPane2.getViewport().add(jTable1); //把表装入容器
        } else {
            JOptionPane.showMessageDialog(this, "输入不能为空值", "提示",
                                          JOptionPane.
                                          INFORMATION_MESSAGE);
            return;
        }
    }

    private void select_TreatyCode() { //按商品条形码查询
        String nubmer = JOptionPane.showInputDialog(this, "请输入按商品条形码",
                "提示", JOptionPane.INFORMATION_MESSAGE);
        if (nubmer.length() != 0) {
            Vector tablehead = new Vector();
            tablehead = Select_Means.tablehead();
            Vector dqselect = new Vector();
            dqselect = Select_Means.Select_sort4(nubmer);
            if (dqselect.size() == 0) {
                JOptionPane.showMessageDialog(this, "商品不存在", "提示",
                                              JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            jTable1 = Mytable.maketable(dqselect, tablehead); //显示内容
            jScrollPane2.getViewport().add(jTable1); //把表装入容器
        } else {
            JOptionPane.showMessageDialog(this, "输入不能为空值", "提示",
                                          JOptionPane.
                                          INFORMATION_MESSAGE);
            return;
        }

    }

    public void jTree1_valueChanged(TreeSelectionEvent e) {
        Object obj1 = jTree1.getLastSelectedPathComponent();
        String s = null;
        if (obj1 != null) {
            s = obj1.toString();

            if (s.equals("销售统计")) {
                Earning earn = new Earning();
                earn.setVisible(true);
                earn.setLocation(200, 0);
            }

            if (s.equals("库存信息浏览")) {
                Vector vec1 = new Vector();
                Vector vec2 = new Vector();
                vec2.add("库存编号");
                vec2.add("商品编码");
                vec2.add("商品名称");
                vec2.add("库存数量");
                vec1 = Select_Means.Select_All_repertory();
                jTable1 = Mytable.maketable(vec1, vec2); //显示内容
                jScrollPane2.getViewport().add(jTable1); //把表装入容器
            }
            if (s.equals("按库存商品编号查询")) {
             String str=  JOptionPane.showInputDialog(this,"请输入商品编号","提示",JOptionPane.INFORMATION_MESSAGE);
                if(str.length()==0){
                    JOptionPane.showMessageDialog(this,"您没有输入商品编号","提示",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
             Vector vec1 = new Vector();
                Vector vec2 = new Vector();
                vec2.add("库存编号");
                vec2.add("商品编码");
                vec2.add("商品名称");
                vec2.add("库存数量");
                vec1 = Select_Means.Select_repertory(str);
                if(vec1.size()==0){
                   JOptionPane.showMessageDialog(this,"商品不存在","提示",JOptionPane.INFORMATION_MESSAGE);                   return;
               }
                jTable1 = Mytable.maketable(vec1, vec2); //显示内容
                jScrollPane2.getViewport().add(jTable1); //把表装入容器
            }



            if (s.equals("添加/修改用户")) {
                Adminstrator admin_Enter = new Adminstrator();
                admin_Enter.setVisible(true);
                admin_Enter.setTitle("此功能需要管理员权限");
            }
            if (s.equals("个人账户查询")) {
                Select_card seCard = new Select_card();
                seCard.setVisible(true);
                seCard.setTitle("个人账户查询");
            }
            if (s.equals("入库/出库")) {
                Put_Out_Depot put_out = new Put_Out_Depot();
                put_out.setVisible(true);
                put_out.setLocation(200, 100);
                put_out.setTitle("进/出货表");
            }

            if (s.equals("进/退货")) {
                JoinAndExceed join = new JoinAndExceed();
                join.setVisible(true);
                join.setTitle("进/退货表");
                join.setLocation(200, 150);

            }

            if (s.equals("新增商品")) {
                AddMerchandise Merchandise = new AddMerchandise();
                Merchandise.setVisible(true);
                Merchandise.setTitle("新增商品表");
                Merchandise.setLocation(60, 100);

            }

            if (s.equals("电器类查询")) {
                select1("DQ1004");
            }

            if (s.equals("礼品类查询")) {
                select1("LP1006");
            }

            if (s.equals("日用类查询")) {
                select1("RY1005");
            }

            if (s.equals("蔬果类查询")) {
                select1("SG1003");
            }

            if (s.equals("食品类查询")) {
                select1("SP1002");
            }

            if (s.equals("生鲜类查询")) {
                select1("SX1001");
            }

            if (s.equals("按商品编号查询")) {
                select_number();
            }

            if (s.equals("按商品名称查询")) {
                select_name();
            }

            if (s.equals("按商品条形码查询")) {
                select_TreatyCode();
            }
        }

    }

    public void jMenuItem2_actionPerformed(ActionEvent e) {
        Adminstrator admin_Enter = new Adminstrator();
        admin_Enter.setVisible(true);
        admin_Enter.setTitle("此功能需要超级管理员权限");

    }

    public void jMenuItem3_actionPerformed(ActionEvent e) {
        Select_card seCard = new Select_card();
        seCard.setVisible(true);
        seCard.setTitle("个人账户查询");
    }

    public void jMenuItem4_actionPerformed(ActionEvent e) {
        Vendition_dialog vd = new Vendition_dialog();
        vd.setVisible(true);
    }

    public void jMenuItem9_actionPerformed(ActionEvent e) {
        JoinAndExceed join = new JoinAndExceed();
        join.setVisible(true);
        join.setTitle("进/退货表");
        join.setLocation(200, 150);

    }

    public void jMenuItem17_actionPerformed(ActionEvent e) {
        Earning earn = new Earning();
        earn.setVisible(true);
        earn.setLocation(200, 0);
    }

    public void jMenuItem16_actionPerformed(ActionEvent e) {
        AddMerchandise Merchandise = new AddMerchandise();
        Merchandise.setVisible(true);
        Merchandise.setTitle("新增商品表");
        Merchandise.setLocation(60, 100);
    }

    public void jMenuItem10_actionPerformed(ActionEvent e) {
        Stock_Dialog stock = new Stock_Dialog();
        stock.setVisible(true);
        stock.setLocation(150, 150);
        stock.setTitle("采购表");
    }

    public void jMenuItem7_actionPerformed(ActionEvent e) {
        Put_Out_Depot put_out = new Put_Out_Depot();
        put_out.setVisible(true);
        put_out.setLocation(200, 100);
        put_out.setTitle("进/出货表");
    }

    public void jMenuItem11_actionPerformed(ActionEvent e) {
        PaymentList paymentList = new PaymentList();
        paymentList.setVisible(true);
        paymentList.setLocation(100, 100);
        paymentList.setTitle("付款单");
    }

    public void jMenuItem18_actionPerformed(ActionEvent e) {
        select_name();
    }

    public void jMenuItem19_actionPerformed(ActionEvent e) {
        select_number();
    }

    public void jMenuItem8_actionPerformed(ActionEvent e) {
        select1("DQ1004");
    }

    public void jMenuItem20_actionPerformed(ActionEvent e) {
        select1("LP1006");
    }

    public void jMenuItem22_actionPerformed(ActionEvent e) {
        select1("SG1003");
    }

    public void jMenuItem23_actionPerformed(ActionEvent e) {
        select1("SP1002");
    }

    public void jMenuItem24_actionPerformed(ActionEvent e) {
        select1("SX1001");
    }

    public void jMenuItem21_actionPerformed(ActionEvent e) {
        select1("RY1005");
    }

    public void jMenuItem25_actionPerformed(ActionEvent e) {
        select_TreatyCode();
    }

    public void jMenuItem15_actionPerformed(ActionEvent e) {
        Vendition_dialog vendition_dialog = new Vendition_dialog();
        vendition_dialog.setVisible(true);
        this.setVisible(false);
    }

    public void jMenuItem5_actionPerformed(ActionEvent e) {
        Guayuqwomen guayuqwomen = new Guayuqwomen();
        guayuqwomen.setVisible(true);
        guayuqwomen.setTitle("关于我们");
    }


}


class MainFrame_jMenuItem15_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem15_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem15_actionPerformed(e);
    }
}


class MainFrame_jMenuItem25_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem25_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem25_actionPerformed(e);
    }
}


class MainFrame_jMenuItem21_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem21_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem21_actionPerformed(e);
    }
}


class MainFrame_jMenuItem24_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem24_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem24_actionPerformed(e);
    }
}


class MainFrame_jMenuItem23_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem23_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem23_actionPerformed(e);
    }
}


class MainFrame_jMenuItem22_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem22_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem22_actionPerformed(e);
    }
}


class MainFrame_jMenuItem20_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem20_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem20_actionPerformed(e);
    }
}


class MainFrame_jMenuItem8_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem8_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem8_actionPerformed(e);
    }
}


class MainFrame_jMenuItem19_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem19_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem19_actionPerformed(e);
    }
}


class MainFrame_jMenuItem18_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem18_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem18_actionPerformed(e);
    }
}


class MainFrame_jMenuItem10_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem10_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem10_actionPerformed(e);
    }
}


class MainFrame_jMenuItem16_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem16_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem16_actionPerformed(e);
    }
}


class MainFrame_jMenuItem17_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem17_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem17_actionPerformed(e);
    }
}


class MainFrame_jMenuItem7_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem7_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem7_actionPerformed(e);
    }
}


class MainFrame_jMenuItem5_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem5_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem5_actionPerformed(e);
    }
}


class MainFrame_jMenuItem9_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem9_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem9_actionPerformed(e);
    }
}


class MainFrame_jMenuItem3_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem3_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem3_actionPerformed(e);
    }
}


class MainFrame_jMenuItem2_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem2_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem2_actionPerformed(e);
    }
}


class MainFrame_jMenuItem1_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem1_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem1_actionPerformed(e);
    }
}


class MainFrame_jTree1_treeSelectionAdapter implements TreeSelectionListener {
    private MainFrame adaptee;
    MainFrame_jTree1_treeSelectionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void valueChanged(TreeSelectionEvent e) {
        adaptee.jTree1_valueChanged(e);
    }
}


class MainFrame_jMenuItem11_actionAdapter implements ActionListener {
    private MainFrame adaptee;
    MainFrame_jMenuItem11_actionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem11_actionPerformed(e);
    }
}


class MainFrame_jMenuHelpAbout_ActionAdapter implements ActionListener {
    MainFrame adaptee;

    MainFrame_jMenuHelpAbout_ActionAdapter(MainFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        adaptee.jMenuHelpAbout_actionPerformed(actionEvent);
    }
}
