/**
 *  [Pallet.java]
 *  Download by http://www.codefans.net
 *  调色板，统一配色类
 *
 * 创建日期：(2003-10-29)
 * @author：ONE_Fox
 * @author：ONE_Fox@163.com
 */
 
 
package datechooser;
 
import java.awt.Color;
 
 
 
public class Pallet {
    
    public static Color backGroundColor = Color.gray;    //底色
    
    
    //月历表格配色----------------//
    public static Color palletTableColor = Color.white;  //日历表底色
    public static Color todayBackColor = Color.orange;     //今天背景色
    
    public static Color weekFontColor = Color.white;     //星期文字色
    public static Color dateFontColor = Color.black;     //日期文字色
    public static Color weekendFontColor = Color.red;    //周末文字色
    
    
    //控制条配色------------------//
    public static Color configLineColor = Color.pink;    //控制条底色
    public static Color cfgTextColor = Color.white;      //控制条标签文字色
    
    public static Color rbFontColor = Color.white;       //RoundBox文字色
    public static Color rbBorderColor = Color.red;       //RoundBox边框色
    public static Color rbButtonColor = Color.pink;      //RoundBox按钮色
    public static Color rbBtFontColor = Color.red;       //RoundBox按钮文字色
}