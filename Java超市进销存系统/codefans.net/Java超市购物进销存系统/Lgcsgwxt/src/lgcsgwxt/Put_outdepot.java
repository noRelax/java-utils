package lgcsgwxt;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.*;
import javax.swing.JPanel;
import lgcsgwxt.dialog.Mytable;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import java.sql.Timestamp;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.Vector;
import javax.swing.table.JTableHeader;

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
public class Put_outdepot extends JDialog {
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    JPanel panel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JComboBox jComboBox1 = new JComboBox();
    JLabel jLabel2 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel3 = new JLabel();
    JComboBox jComboBox2 = new JComboBox();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JTextField jTextField2 = new JTextField();
    JLabel jLabel7 = new JLabel();
    JTextField jTextField3 = new JTextField();
    JLabel jLabel8 = new JLabel();
    JTextField jTextField4 = new JTextField();
    JLabel jLabel9 = new JLabel();

    JTable jTable1 = new JTable();
    JTableHeader jTableHeader1 = jTable1.getTableHeader();

    Vector colnames=new Vector();//��ͷ
    Vector colnames1=new Vector();
    Vector colnames2=new Vector();


    JScrollPane jScrollPane1 = new JScrollPane();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JButton jButton4 = new JButton();


    public Put_outdepot(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Put_outdepot() {
        this(new Frame(), "JoinAndExceed", false);
        this.setSize(600,400);
    }

    private void jbInit() throws Exception {
        panel1.setLayout(null);
        this.getContentPane().setLayout(null);
        jLabel4.setBorder(BorderFactory.createEtchedBorder());
        jLabel4.setBounds(new Rectangle(29, 7, 536, 36));
        jLabel5.setText("����¼��");
        jLabel5.setBounds(new Rectangle(29, 54, 102, 26));
        jLabel6.setText("���/���룺");
        jLabel6.setBounds(new Rectangle(43, 88, 68, 21));
        jTextField2.setBounds(new Rectangle(110, 88, 127, 21));
        jLabel7.setText("������");
        jLabel7.setBounds(new Rectangle(261, 88, 38, 21));
        jTextField3.setBounds(new Rectangle(295, 88, 68, 21));
        jLabel8.setText("�������ۣ�");
        jLabel8.setBounds(new Rectangle(392, 88, 68, 21));
        jTextField4.setBounds(new Rectangle(455, 88, 68, 21));
        jLabel9.setBorder(BorderFactory.createEtchedBorder());
        jLabel9.setBounds(new Rectangle(29, 80, 535, 41));
        jScrollPane1.setBounds(new Rectangle(29, 150, 535, 144));
        jButton1.setBounds(new Rectangle(38, 319, 111, 30));
        jButton1.setSelectedIcon(null);
        jButton1.setText("ȷ   ��");
        jButton2.setBounds(new Rectangle(170, 319, 111, 30));
        jButton2.setText("ɾ����Ʒ");
        jButton3.setBounds(new Rectangle(306, 319, 111, 30));
        jButton3.setText("��   ��");
        jButton4.setBounds(new Rectangle(441, 319, 111, 30));
        jButton4.setText("�˳�");
        this.getContentPane().add(panel1, null);
        jComboBox2.setBounds(new Rectangle(433, 14, 94, 20));
        jLabel3.setText("�Ƶ��ˣ�");
        jLabel3.setBounds(new Rectangle(380, 14, 84, 20));
        jTextField1.setBounds(new Rectangle(225, 14, 131, 20));
        jLabel2.setText("ʱ�䣺");
        jLabel2.setBounds(new Rectangle(187, 14, 84, 20));
        jComboBox1.setBounds(new Rectangle(79, 14, 84, 20));
        this.getContentPane().add(jComboBox2);
        this.getContentPane().add(jTextField1);
        this.getContentPane().add(jComboBox1);
        this.getContentPane().add(jLabel1);
        this.getContentPane().add(jLabel2);
        this.getContentPane().add(jLabel3);
        this.getContentPane().add(jLabel4);
        this.getContentPane().add(jLabel5);
        this.getContentPane().add(jLabel6);
        this.getContentPane().add(jTextField2);
        this.getContentPane().add(jTextField3);
        this.getContentPane().add(jLabel7);
        this.getContentPane().add(jLabel8);
        this.getContentPane().add(jTextField4);
        this.getContentPane().add(jLabel9);
        this.getContentPane().add(jScrollPane1);
        this.getContentPane().add(jButton1);
        this.getContentPane().add(jButton2);
        this.getContentPane().add(jButton3);
        this.getContentPane().add(jButton4);
        jLabel1.setText("���");
        jLabel1.setBounds(new Rectangle(43, 14, 84, 20));
        panel1.setBounds(new Rectangle(0, 399, 1, 1));
        jComboBox1.addItem("  ��  �� ");
        jComboBox1.addItem("  ��  �� ");
        jComboBox2.addItem("����ΰ");
        jComboBox2.addItem("̷����");
        jComboBox2.addItem("����");
        jTextField1.setText(ts.toString().substring(0,19));
        colnames.add("���");
        colnames.add("��Ʒ����");
        colnames.add("����");
        colnames.add("������");
        colnames.add("���");

        colnames1.add("");
        colnames1.add("");
        colnames1.add("");
        colnames1.add("");
        colnames1.add("");
          colnames2.add(colnames1);
     jTable1 = Mytable.maketable(colnames2, colnames); //��ʾ����
     jScrollPane1.getViewport().add(jTable1);//�ѱ�װ������
    }
}
