package lgcsgwxt.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.util.Vector;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import lgcsgwxt.means.Insert_stock;

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
public class AddMerchandise extends JDialog {
    JPanel panel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel jLabel8 = new JLabel();
    JLabel jLabel9 = new JLabel();
    JLabel jLabel10 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();
    JTextField jTextField4 = new JTextField();
    JTextField jTextField5 = new JTextField();
    JTextField jTextField6 = new JTextField();
    JTextField jTextField7 = new JTextField();
    JTextField jTextField8 = new JTextField();
    JTextField jTextField9 = new JTextField();
    JTextField jTextField10 = new JTextField();
    JScrollPane jScrollPane1 = new JScrollPane();

    JTable jTable1 = new JTable();
    JTableHeader jTableHeader1 = jTable1.getTableHeader();

    Vector colnames = new Vector(); //��ͷ
    Vector colnames1 = new Vector(); //����
    Vector colnames2 = new Vector(); //����

    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    JButton jButton4 = new JButton();
    JComboBox jComboBox1 = new JComboBox();


    public AddMerchandise(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public AddMerchandise() {
        this(new Frame(), "AddMerchandise", false);
        this.setSize(900, 480);
    }

    private void jbInit() throws Exception {
        panel1.setLayout(null);
        jLabel1.setText("��Ʒ���룺");
        jLabel1.setBounds(new Rectangle(15, 10, 85, 26));
        jLabel2.setText("�� �� �룺");
        jLabel2.setBounds(new Rectangle(15, 50, 85, 26));
        jLabel3.setText("����ţ�");
        jLabel3.setBounds(new Rectangle(15, 90, 85, 26));
        jLabel4.setText("��Ʒ���ƣ�");
        jLabel4.setBounds(new Rectangle(15, 130, 85, 26));
        jLabel5.setText("���ۼ�(Ԫ)��");
        jLabel5.setBounds(new Rectangle(15, 170, 85, 26));
        jLabel6.setText("��Ʒ���");
        jLabel6.setBounds(new Rectangle(15, 210, 85, 26));
        jLabel7.setText("������λ��");
        jLabel7.setBounds(new Rectangle(15, 250, 85, 26));
        jLabel8.setText("�� �� �ۣ�");
        jLabel8.setBounds(new Rectangle(15, 290, 85, 26));
        jLabel9.setText("������(��)��");
        jLabel9.setBounds(new Rectangle(15, 330, 85, 26));
        jLabel10.setText("��    ע��");
        jLabel10.setBounds(new Rectangle(15, 370, 85, 26));
        jComboBox1.addItem("������");
        jComboBox1.addItem("ʳƷ��");
        jComboBox1.addItem("�߹���");
        jComboBox1.addItem("������");
        jComboBox1.addItem("������");
        jComboBox1.addItem("��Ʒ��");
        jTextField1.setBounds(new Rectangle(91, 10, 125, 24));
        jTextField2.setBounds(new Rectangle(91, 50, 125, 24));
        jTextField4.setBounds(new Rectangle(91, 130, 125, 24));
        jTextField5.setBounds(new Rectangle(91, 170, 125, 24));
        jTextField6.setBounds(new Rectangle(91, 210, 125, 24));
        jTextField7.setBounds(new Rectangle(91, 250, 125, 24));
        jTextField8.setBounds(new Rectangle(91, 290, 125, 24));
        jTextField9.setBounds(new Rectangle(91, 330, 125, 24));
        jTextField10.setBounds(new Rectangle(91, 370, 125, 24));
        jScrollPane1.setBounds(new Rectangle(228, 10, 658, 383));
        jButton1.setBounds(new Rectangle(53, 418, 127, 27));
        jButton1.setText("��  ��");
        jButton1.addActionListener(new AddMerchandise_jButton1_actionAdapter(this));
        jButton2.setBounds(new Rectangle(274, 418, 127, 27));
        jButton2.setText("��  ��");
        jButton2.addActionListener(new AddMerchandise_jButton2_actionAdapter(this));
        jButton3.setBounds(new Rectangle(496, 418, 127, 27));
        jButton3.setText("ɾ  ��");
        jButton3.addActionListener(new AddMerchandise_jButton3_actionAdapter(this));
        jButton4.setBounds(new Rectangle(717, 418, 127, 27));
        jButton4.setText("��  ��");
        jButton4.addActionListener(new AddMerchandise_jButton4_actionAdapter(this));
        panel1.setBackground(Color.pink);
        jComboBox1.setBounds(new Rectangle(91, 90, 125, 24));
        getContentPane().add(panel1);
        panel1.add(jLabel1);
        panel1.add(jLabel2);
        panel1.add(jLabel3);
        panel1.add(jLabel4);
        panel1.add(jLabel5);
        panel1.add(jLabel6);
        panel1.add(jLabel7);
        panel1.add(jLabel8);
        panel1.add(jLabel9);
        panel1.add(jLabel10);
        panel1.add(jTextField5);
        panel1.add(jTextField1);
        panel1.add(jTextField2);
        panel1.add(jTextField4);
        panel1.add(jTextField6);
        panel1.add(jTextField7);
        panel1.add(jTextField8);
        panel1.add(jTextField9);
        panel1.add(jTextField10);
        panel1.add(jScrollPane1);
        panel1.add(jButton4);
        panel1.add(jButton3);
        panel1.add(jButton2);
        panel1.add(jButton1);
        panel1.add(jComboBox1);
        jScrollPane1.getViewport().add(jTable1);
        colnames.add("��Ʒ����");
        colnames.add("������");
        colnames.add("�����");
        colnames.add("��Ʒ����");
        colnames.add("���ۼ�(Ԫ)");
        colnames.add("��Ʒ���");
        colnames.add("������λ");
        colnames.add("������");
        colnames.add("������(��)");
        colnames.add("��ע");
        colnames2.add(colnames1);
        jTable1 = Mytable.maketable(colnames2, colnames); //��ʾ����
        jScrollPane1.getViewport().add(jTable1);
    }

    Vector addAllData = new Vector();
    public void jButton1_actionPerformed(ActionEvent e) {
        if ((jTextField1.getText().length() == 0) ||
            (jTextField2.getText().length() == 0) ||
            (jTextField4.getText().length() == 0) ||
            (jTextField5.getText().length() == 0) ||
            (jTextField6.getText().length() == 0) ||
            (jTextField7.getText().length() == 0) ||
            (jTextField8.getText().length() == 0) ||
            (jTextField9.getText().length() == 0)) {
            JOptionPane.showMessageDialog(this, "������д����������������д", "��ʾ",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Double dj = new Double(jTextField5.getText());
            Double jhj = new Double(jTextField5.getText());
            Double bzq = new Double(jTextField5.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "�ύ�����ݲ��Ϸ�������", "��ʾ",
                                          JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Vector addData = new Vector();
        addData.add(jTextField1.getText());
        addData.add(jTextField2.getText());
        if (jComboBox1.getSelectedItem().equals("������")) {
            addData.add("SX1001");
        }
        if (jComboBox1.getSelectedItem().equals("ʳƷ��")) {
            addData.add("SP1002");
        }
        if (jComboBox1.getSelectedItem().equals("�߹���")) {
            addData.add("SG1003");
        }
        if (jComboBox1.getSelectedItem().equals("������")) {
            addData.add("RY1005");
        }
        if (jComboBox1.getSelectedItem().equals("��Ʒ��")) {
            addData.add("LP1006");
        }
        if (jComboBox1.getSelectedItem().equals("������")) {
            addData.add("DQ1004");
        }
        addData.add(jTextField4.getText());
        addData.add(jTextField5.getText());
        addData.add(jTextField6.getText());
        addData.add(jTextField7.getText());
        addData.add(jTextField8.getText());
        addData.add(jTextField9.getText());
        addData.add(jTextField10.getText());
        addAllData.add(addData);
        jTable1 = Mytable.maketable(addAllData, colnames); //��ʾ����
        jScrollPane1.getViewport().add(jTable1);
    }

    public void jButton2_actionPerformed(ActionEvent e) {
        addAllData.removeAllElements();
        jTable1 = Mytable.maketable(addAllData, colnames); //��ʾ����
        jScrollPane1.getViewport().add(jTable1);
    }

    public void jButton3_actionPerformed(ActionEvent e) {
        int row = jTable1.getSelectedRow();
        addAllData.remove(row);
        jTable1 = Mytable.maketable(addAllData, colnames); //��ʾ����
        jScrollPane1.getViewport().add(jTable1);
    }

    public void jButton4_actionPerformed(ActionEvent e) {
        Vector addDate1 = new Vector();
        int i = 0;
        int number=0;
        while (i < addAllData.size()) {
            addDate1 = (Vector) addAllData.get(i);
            Double number1=new Double(addDate1.get(4).toString());
            Double number2=new Double(addDate1.get(7).toString());
            Double number3=new Double(addDate1.get(8).toString());
                 number=Insert_stock.Insert_merchandise(addDate1.get(0).toString(),
                    addDate1.get(1).toString(), addDate1.get(2).toString(),
                    addDate1.get(3).toString(), number1,
                    addDate1.get(5).toString(), addDate1.get(6).toString(),
                    number2, number3,
                    addDate1.get(9).toString());
                 i++;
                 String str="��"+i+"������д��ʧ��";
                 if(number==0){
                     JOptionPane.showMessageDialog(this, str, "��ʾ",
                                          JOptionPane.INFORMATION_MESSAGE);
                     return;
                 }
                 if(i == addAllData.size()){
                     JOptionPane.showMessageDialog(this, "�����ύ�ɹ�", "��ʾ",
                                          JOptionPane.INFORMATION_MESSAGE);
                 }

        }
    }
}


class AddMerchandise_jButton4_actionAdapter implements ActionListener {
    private AddMerchandise adaptee;
    AddMerchandise_jButton4_actionAdapter(AddMerchandise adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton4_actionPerformed(e);
    }
}


class AddMerchandise_jButton3_actionAdapter implements ActionListener {
    private AddMerchandise adaptee;
    AddMerchandise_jButton3_actionAdapter(AddMerchandise adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton3_actionPerformed(e);
    }
}


class AddMerchandise_jButton2_actionAdapter implements ActionListener {
    private AddMerchandise adaptee;
    AddMerchandise_jButton2_actionAdapter(AddMerchandise adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}


class AddMerchandise_jButton1_actionAdapter implements ActionListener {
    private AddMerchandise adaptee;
    AddMerchandise_jButton1_actionAdapter(AddMerchandise adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}
