/**
 *  [Test.java]
 *  Download by http://www.codefans.net
 *  ������
 *
 * �������ڣ�(2003-10-26)
 * @author��ONE_Fox
 * @author��ONE_Fox@163.com
 */
 
 
import datechooser.DateChooser;
import datechooser.Pallet;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.text.SimpleDateFormat;
 
 
 
public class Test extends JFrame {
    
    
    //�������----------------//
    private JTextField showField = new JTextField(15);
    private JButton testButton = new JButton("Tset Button");
    
    
    //�и�����(Farm)���������ڲ����� DateChooser
    private DateChooser dateChooser = new DateChooser(this);


/**
 * **�������췽��**
 **
    //�޸����ڣ��������ڲ����� DateChooser
    new DateChooser();
    
    //�и����ڣ������ڲ����� DateChooser
    new DateChooser(Frame owner, Calendar showMonth, int startYear, 
                                                     int lastYear);
    
    //�޸����ڣ������ڲ����� DateChooser
    new DateChooser(Calendar showMonth, int startYear, int lastYear);
*/
    
    


/**
 *  **������ɫ**
 **
 
    static {
        
        Pallet.backGroundColor = Color.gray;    //��ɫ
    
    
        //���������ɫ----------------//
        Pallet.palletTableColor = Color.white;  //�������ɫ
        Pallet.todayBackColor = Color.pink;     //���챳��ɫ
    
        Pallet.weekFontColor = Color.white;     //��������ɫ
        Pallet.dateFontColor = Color.black;     //��������ɫ
        Pallet.weekendFontColor = Color.red;    //��ĩ����ɫ
    
    
        //��������ɫ------------------//
        Pallet.configLineColor = Color.pink;    //��������ɫ
        Pallet.cfgTextColor = Color.white;      //��������ǩ����ɫ
    
        Pallet.rbFontColor = Color.white;       //RoundBox����ɫ
        Pallet.rbBorderColor = Color.red;       //RoundBox�߿�ɫ
        Pallet.rbButtonColor = Color.pink;      //RoundBox��ťɫ
        Pallet.rbBtFontColor = Color.red;       //RoundBox��ť����ɫ
    }
*/
    
    
 
 
 
//------���췽��-------------------------------------------------//
    public Test() {
        
        makeFace();
        addListener();
        
        show();
    }
    
    
 
 
//------����/����------------------------------------------------//
 
 
    public void makeFace() {
        
        setTitle("Test DateChooser");
        
        setLocation(100, 100);
        setSize(300, 200);
        
        getContentPane().setLayout(new FlowLayout());
        
        getContentPane().add(showField);
        getContentPane().add(testButton);
    }
    
    
    public void addListener() {
        
        testButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                
                dateChooser.showChooser(testButton, 
                                       e.getX() - DateChooser.width, e.getY());
                
                if(dateChooser.getDate() != null)
                    showField.setText(new SimpleDateFormat("[ yyyy��M��d��]")
                                               .format(dateChooser.getDate()));
            }
        });
        
        
        
        this.addWindowListener(new WindowAdapter(){  //��Ӵ��ڹر��¼�
            public void windowClosing(WindowEvent e){
                
                setVisible(false);
                dispose();
                
                System.exit(0);
            }
        });
    }
    
    
    
//------�������-------------------------------------------------//
 
 
    public static void main(String[] args) {
        
        //��������------------//
        new Test();
    }
}