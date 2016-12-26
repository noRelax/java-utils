/**
 *  [Test.java]
 *  Download by http://www.codefans.net
 *  测试类
 *
 * 创建日期：(2003-10-26)
 * @author：ONE_Fox
 * @author：ONE_Fox@163.com
 */
 
 
import datechooser.DateChooser;
import datechooser.Pallet;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.text.SimpleDateFormat;
 
 
 
public class Test extends JFrame {
    
    
    //界面组件----------------//
    private JTextField showField = new JTextField(15);
    private JButton testButton = new JButton("Tset Button");
    
    
    //有父窗口(Farm)，不带日期参数的 DateChooser
    private DateChooser dateChooser = new DateChooser(this);


/**
 * **其他构造方法**
 **
    //无父窗口，不带日期参数的 DateChooser
    new DateChooser();
    
    //有父窗口，带日期参数的 DateChooser
    new DateChooser(Frame owner, Calendar showMonth, int startYear, 
                                                     int lastYear);
    
    //无父窗口，带日期参数的 DateChooser
    new DateChooser(Calendar showMonth, int startYear, int lastYear);
*/
    
    


/**
 *  **界面配色**
 **
 
    static {
        
        Pallet.backGroundColor = Color.gray;    //底色
    
    
        //月历表格配色----------------//
        Pallet.palletTableColor = Color.white;  //日历表底色
        Pallet.todayBackColor = Color.pink;     //今天背景色
    
        Pallet.weekFontColor = Color.white;     //星期文字色
        Pallet.dateFontColor = Color.black;     //日期文字色
        Pallet.weekendFontColor = Color.red;    //周末文字色
    
    
        //控制条配色------------------//
        Pallet.configLineColor = Color.pink;    //控制条底色
        Pallet.cfgTextColor = Color.white;      //控制条标签文字色
    
        Pallet.rbFontColor = Color.white;       //RoundBox文字色
        Pallet.rbBorderColor = Color.red;       //RoundBox边框色
        Pallet.rbButtonColor = Color.pink;      //RoundBox按钮色
        Pallet.rbBtFontColor = Color.red;       //RoundBox按钮文字色
    }
*/
    
    
 
 
 
//------构造方法-------------------------------------------------//
    public Test() {
        
        makeFace();
        addListener();
        
        show();
    }
    
    
 
 
//------方法/函数------------------------------------------------//
 
 
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
                    showField.setText(new SimpleDateFormat("[ yyyy年M月d日]")
                                               .format(dateChooser.getDate()));
            }
        });
        
        
        
        this.addWindowListener(new WindowAdapter(){  //添加窗口关闭事件
            public void windowClosing(WindowEvent e){
                
                setVisible(false);
                dispose();
                
                System.exit(0);
            }
        });
    }
    
    
    
//------程序入口-------------------------------------------------//
 
 
    public static void main(String[] args) {
        
        //启动测试------------//
        new Test();
    }
}