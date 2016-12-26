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
 * <p>Title: ³�㳬�н�����ϵͳ</p>
 *
 * <p>Description: ��������³��У��S1��ҵ���</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: ST-117��</p>
 *
 * @author ST-117��ڶ�С��
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
        setTitle("³�㳬�н�����ϵͳ");
        statusBar.setText(" ");
        statusBar.setBounds(new Rectangle(0, 585, 800, 15));
        //������
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("���������");

        DefaultMutableTreeNode Man2 = new DefaultMutableTreeNode("���۹���");
        DefaultMutableTreeNode Man3 = new DefaultMutableTreeNode("����ѯ");
        DefaultMutableTreeNode Man4 = new DefaultMutableTreeNode("�û�����");
        DefaultMutableTreeNode Man5 = new DefaultMutableTreeNode("��Ϣ��ѯ");
        DefaultMutableTreeNode Man6 = new DefaultMutableTreeNode("��Ʒ����");

        DefaultMutableTreeNode annal = new DefaultMutableTreeNode("���/����");
        DefaultMutableTreeNode amend = new DefaultMutableTreeNode("��/�˻�");
        DefaultMutableTreeNode delete = new DefaultMutableTreeNode("������Ʒ");
        jMenuItem1.addActionListener(new MainFrame_jMenuItem1_actionAdapter(this));

        jMenuItem2.setText("���/�޸��û�");
        jMenuItem2.addActionListener(new MainFrame_jMenuItem2_actionAdapter(this));
        jMenuItem3.setText("�����˻���ѯ");
        jMenuItem3.addActionListener(new MainFrame_jMenuItem3_actionAdapter(this));
        jToolBar.setBackground(SystemColor.control);
        jMenuItem9.addActionListener(new MainFrame_jMenuItem9_actionAdapter(this));
        jMenuItem15.setText("��̨תǰ̨");
        jMenuItem15.addActionListener(new MainFrame_jMenuItem15_actionAdapter(this));
        jMenuItem17.addActionListener(new MainFrame_jMenuItem17_actionAdapter(this));
        jMenuItem16.setText("������Ʒ");
        jMenuItem16.addActionListener(new MainFrame_jMenuItem16_actionAdapter(this));
        jMenuItem10.addActionListener(new MainFrame_jMenuItem10_actionAdapter(this));
        jMenuItem7.addActionListener(new MainFrame_jMenuItem7_actionAdapter(this));
        jMenuItem11.addActionListener(new MainFrame_jMenuItem11_actionAdapter(this));
        jMenu9.setText("����Ʒ����ѯ");
        jMenuItem8.setText("�������ѯ");
        jMenuItem8.addActionListener(new MainFrame_jMenuItem8_actionAdapter(this));
        jMenuItem20.setText("��Ʒ���ѯ");
        jMenuItem20.addActionListener(new MainFrame_jMenuItem20_actionAdapter(this));
        jMenuItem21.setText("�������ѯ");
        jMenuItem21.addActionListener(new MainFrame_jMenuItem21_actionAdapter(this));
        jMenuItem22.setText("�߹����ѯ");
        jMenuItem22.addActionListener(new MainFrame_jMenuItem22_actionAdapter(this));
        jMenuItem23.setText("ʳƷ���ѯ");
        jMenuItem23.addActionListener(new MainFrame_jMenuItem23_actionAdapter(this));
        jMenuItem24.setText("�������ѯ");
        jMenuItem24.addActionListener(new MainFrame_jMenuItem24_actionAdapter(this));
        jMenuItem18.addActionListener(new MainFrame_jMenuItem18_actionAdapter(this));
        jMenuItem19.addActionListener(new MainFrame_jMenuItem19_actionAdapter(this));
        jMenuItem25.setText("����Ʒ�������ѯ");
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
        jMenuItem1.setText("�˳�");
        jMenu1.setText("�ɹ�");
        jMenu2.setText("�ֿ�");
        jMenu3.setText("�˻�����");
        jMenu4.setText("������Ϣ");
        jMenuItem5.setText("����");
        jMenuItem7.setText("���/����");
        jMenuItem9.setText("��/�˻�");
        jMenu5.setText("��ѯ");
        jMenuItem10.setText("�ɹ���");
        jMenuItem11.setText("���");
        jMenuItem17.setText("����ͳ��");
        jMenu7.setText("��ѯ��Ʒ��Ϣ");
        jMenuItem18.setText("����Ʒ���Ʋ�ѯ");
        jMenuItem19.setText("����Ʒ��Ų�ѯ");
        jScrollPane2.setBorder(BorderFactory.createLineBorder(Color.black));
        jScrollPane2.setBounds(new Rectangle(167, 93, 750, 435));

        root.add(Man2);
        root.add(Man3);
        root.add(Man4);
        root.add(Man5);
        root.add(Man6);

        DefaultMutableTreeNode Sells = new DefaultMutableTreeNode("����ͳ��");
        Man2.add(Sells);
        DefaultMutableTreeNode Storeinfr = new DefaultMutableTreeNode("�����Ϣ���");
        DefaultMutableTreeNode Store1 = new DefaultMutableTreeNode("�������Ʒ��Ų�ѯ");

        Man3.add(Storeinfr);
        Man3.add(Store1);

        DefaultMutableTreeNode UserNa = new DefaultMutableTreeNode("���/�޸��û�");
        DefaultMutableTreeNode UserAdd = new DefaultMutableTreeNode("�����˻���ѯ");

        Man4.add(UserNa);
        Man4.add(UserAdd);
        DefaultMutableTreeNode search1 = new DefaultMutableTreeNode("����Ʒ���Ʋ�ѯ");
        DefaultMutableTreeNode search2 = new DefaultMutableTreeNode("����Ʒ��Ų�ѯ");
        DefaultMutableTreeNode search3 = new DefaultMutableTreeNode("����Ʒ�������ѯ");
        DefaultMutableTreeNode search5 = new DefaultMutableTreeNode("����Ʒ����ѯ");

        DefaultMutableTreeNode sort1 = new DefaultMutableTreeNode("�������ѯ");
        DefaultMutableTreeNode sort2 = new DefaultMutableTreeNode("��Ʒ���ѯ");
        DefaultMutableTreeNode sort3 = new DefaultMutableTreeNode("�������ѯ");
        DefaultMutableTreeNode sort4 = new DefaultMutableTreeNode("�߹����ѯ");
        DefaultMutableTreeNode sort5 = new DefaultMutableTreeNode("ʳƷ���ѯ");
        DefaultMutableTreeNode sort6 = new DefaultMutableTreeNode("�������ѯ");

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

        //��ʼ����
        jTree1 = new JTree(root);
        jTree1.addTreeSelectionListener(new
                                        MainFrame_jTree1_treeSelectionAdapter(this));
        jMenuFile.setText("�ļ�");
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

    private void select1(String nubmer) { //������Ʒ������,�ѽ����ʾ������
        Vector tablehead = new Vector();
        tablehead = Select_Means.tablehead();
        Vector dqselect = new Vector();
        dqselect = Select_Means.Select_sort1(nubmer);
        jTable1 = Mytable.maketable(dqselect, tablehead); //��ʾ����
        jScrollPane2.getViewport().add(jTable1); //�ѱ�װ������

    }

    private void select_number() { //����Ʒ��Ų�ѯ
        String nubmer = JOptionPane.showInputDialog(this, "��������Ʒ���", "��ʾ",
                JOptionPane.INFORMATION_MESSAGE);

        if (nubmer.length() != 0) {
            Vector tablehead = new Vector();
            tablehead = Select_Means.tablehead();
            Vector dqselect = new Vector();
            dqselect = Select_Means.Select_sort2(nubmer);
            if (dqselect.size() == 0) {
                JOptionPane.showMessageDialog(this, "��Ʒ��Ų�����", "��ʾ",
                                              JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            jTable1 = Mytable.maketable(dqselect, tablehead); //��ʾ����
            jScrollPane2.getViewport().add(jTable1); //�ѱ�װ������
        } else {
            JOptionPane.showMessageDialog(this, "���벻��Ϊ��ֵ", "��ʾ",
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }

    private void select_name() { //����Ʒ���Ʋ�ѯ
        String nubmer = JOptionPane.showInputDialog(this, "��������Ʒ����",
                "��ʾ", JOptionPane.INFORMATION_MESSAGE);
        if (nubmer.length() != 0) {
            Vector tablehead = new Vector();
            tablehead = Select_Means.tablehead();
            Vector dqselect = new Vector();
            dqselect = Select_Means.Select_sort3(nubmer);
            if (dqselect.size() == 0) {
                JOptionPane.showMessageDialog(this, "��Ʒ������", "��ʾ",
                                              JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            jTable1 = Mytable.maketable(dqselect, tablehead); //��ʾ����
            jScrollPane2.getViewport().add(jTable1); //�ѱ�װ������
        } else {
            JOptionPane.showMessageDialog(this, "���벻��Ϊ��ֵ", "��ʾ",
                                          JOptionPane.
                                          INFORMATION_MESSAGE);
            return;
        }
    }

    private void select_TreatyCode() { //����Ʒ�������ѯ
        String nubmer = JOptionPane.showInputDialog(this, "�����밴��Ʒ������",
                "��ʾ", JOptionPane.INFORMATION_MESSAGE);
        if (nubmer.length() != 0) {
            Vector tablehead = new Vector();
            tablehead = Select_Means.tablehead();
            Vector dqselect = new Vector();
            dqselect = Select_Means.Select_sort4(nubmer);
            if (dqselect.size() == 0) {
                JOptionPane.showMessageDialog(this, "��Ʒ������", "��ʾ",
                                              JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            jTable1 = Mytable.maketable(dqselect, tablehead); //��ʾ����
            jScrollPane2.getViewport().add(jTable1); //�ѱ�װ������
        } else {
            JOptionPane.showMessageDialog(this, "���벻��Ϊ��ֵ", "��ʾ",
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

            if (s.equals("����ͳ��")) {
                Earning earn = new Earning();
                earn.setVisible(true);
                earn.setLocation(200, 0);
            }

            if (s.equals("�����Ϣ���")) {
                Vector vec1 = new Vector();
                Vector vec2 = new Vector();
                vec2.add("�����");
                vec2.add("��Ʒ����");
                vec2.add("��Ʒ����");
                vec2.add("�������");
                vec1 = Select_Means.Select_All_repertory();
                jTable1 = Mytable.maketable(vec1, vec2); //��ʾ����
                jScrollPane2.getViewport().add(jTable1); //�ѱ�װ������
            }
            if (s.equals("�������Ʒ��Ų�ѯ")) {
             String str=  JOptionPane.showInputDialog(this,"��������Ʒ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
                if(str.length()==0){
                    JOptionPane.showMessageDialog(this,"��û��������Ʒ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
             Vector vec1 = new Vector();
                Vector vec2 = new Vector();
                vec2.add("�����");
                vec2.add("��Ʒ����");
                vec2.add("��Ʒ����");
                vec2.add("�������");
                vec1 = Select_Means.Select_repertory(str);
                if(vec1.size()==0){
                   JOptionPane.showMessageDialog(this,"��Ʒ������","��ʾ",JOptionPane.INFORMATION_MESSAGE);                   return;
               }
                jTable1 = Mytable.maketable(vec1, vec2); //��ʾ����
                jScrollPane2.getViewport().add(jTable1); //�ѱ�װ������
            }



            if (s.equals("���/�޸��û�")) {
                Adminstrator admin_Enter = new Adminstrator();
                admin_Enter.setVisible(true);
                admin_Enter.setTitle("�˹�����Ҫ����ԱȨ��");
            }
            if (s.equals("�����˻���ѯ")) {
                Select_card seCard = new Select_card();
                seCard.setVisible(true);
                seCard.setTitle("�����˻���ѯ");
            }
            if (s.equals("���/����")) {
                Put_Out_Depot put_out = new Put_Out_Depot();
                put_out.setVisible(true);
                put_out.setLocation(200, 100);
                put_out.setTitle("��/������");
            }

            if (s.equals("��/�˻�")) {
                JoinAndExceed join = new JoinAndExceed();
                join.setVisible(true);
                join.setTitle("��/�˻���");
                join.setLocation(200, 150);

            }

            if (s.equals("������Ʒ")) {
                AddMerchandise Merchandise = new AddMerchandise();
                Merchandise.setVisible(true);
                Merchandise.setTitle("������Ʒ��");
                Merchandise.setLocation(60, 100);

            }

            if (s.equals("�������ѯ")) {
                select1("DQ1004");
            }

            if (s.equals("��Ʒ���ѯ")) {
                select1("LP1006");
            }

            if (s.equals("�������ѯ")) {
                select1("RY1005");
            }

            if (s.equals("�߹����ѯ")) {
                select1("SG1003");
            }

            if (s.equals("ʳƷ���ѯ")) {
                select1("SP1002");
            }

            if (s.equals("�������ѯ")) {
                select1("SX1001");
            }

            if (s.equals("����Ʒ��Ų�ѯ")) {
                select_number();
            }

            if (s.equals("����Ʒ���Ʋ�ѯ")) {
                select_name();
            }

            if (s.equals("����Ʒ�������ѯ")) {
                select_TreatyCode();
            }
        }

    }

    public void jMenuItem2_actionPerformed(ActionEvent e) {
        Adminstrator admin_Enter = new Adminstrator();
        admin_Enter.setVisible(true);
        admin_Enter.setTitle("�˹�����Ҫ��������ԱȨ��");

    }

    public void jMenuItem3_actionPerformed(ActionEvent e) {
        Select_card seCard = new Select_card();
        seCard.setVisible(true);
        seCard.setTitle("�����˻���ѯ");
    }

    public void jMenuItem4_actionPerformed(ActionEvent e) {
        Vendition_dialog vd = new Vendition_dialog();
        vd.setVisible(true);
    }

    public void jMenuItem9_actionPerformed(ActionEvent e) {
        JoinAndExceed join = new JoinAndExceed();
        join.setVisible(true);
        join.setTitle("��/�˻���");
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
        Merchandise.setTitle("������Ʒ��");
        Merchandise.setLocation(60, 100);
    }

    public void jMenuItem10_actionPerformed(ActionEvent e) {
        Stock_Dialog stock = new Stock_Dialog();
        stock.setVisible(true);
        stock.setLocation(150, 150);
        stock.setTitle("�ɹ���");
    }

    public void jMenuItem7_actionPerformed(ActionEvent e) {
        Put_Out_Depot put_out = new Put_Out_Depot();
        put_out.setVisible(true);
        put_out.setLocation(200, 100);
        put_out.setTitle("��/������");
    }

    public void jMenuItem11_actionPerformed(ActionEvent e) {
        PaymentList paymentList = new PaymentList();
        paymentList.setVisible(true);
        paymentList.setLocation(100, 100);
        paymentList.setTitle("���");
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
        guayuqwomen.setTitle("��������");
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
