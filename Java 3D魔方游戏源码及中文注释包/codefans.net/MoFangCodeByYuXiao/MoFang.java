
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.Applet ;

import javax.swing.*;
import javax.swing.JFrame.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.picking.*;
import com.sun.j3d.utils.picking.behaviors.*;
import com.sun.j3d.utils.picking.PickTool ;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.applet.MainFrame ;
import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.behaviors.keyboard.*;
import com.sun.j3d.utils.image.TextureLoader ;


public class MoFang 
{
    //控制量
    public static String[]newTypeArray=
    {
        "0","1","2","3","4","5","6","7" 
    }
    ;
    public static boolean[]controlLayerArray=
    {
        true,true,false,true,false,false,false,false 
    }
    ;
    
    //整个程序三个组件
    //三维显示模块
    public static PanelAddedScene3D testPanelAddedScene3D=new PanelAddedScene3D();
    //魔方状态数据及变化模块
    public static MofangStatusMessage aMofangStatusMessage=new MofangStatusMessage("new");
    ;
    //窗体和界面
    public static MyMainFrame theMainFrame=new MyMainFrame();
    
    public static void main(String args[])
    {
        
        //显示场景
        testPanelAddedScene3D.typeArray=newTypeArray ;
        testPanelAddedScene3D.prepare();
        testPanelAddedScene3D.addAllbranchGroupToU(controlLayerArray);
        theMainFrame.totlePanel.tab.add("3D场景",testPanelAddedScene3D);
        
        
        //显示窗体
        theMainFrame.setSize(650,650);
        theMainFrame.setVisible(true);
        
        //初始状态数据显示到窗体和场景
        aMofangStatusMessage.outToATable();
        BranchGroup3 aBranchGroup3=(BranchGroup3)(testPanelAddedScene3D.branchGroupArray[3]);
        aBranchGroup3.setStatus(aMofangStatusMessage.dataMessage);
        
        theMainFrame.repaint();
        
        //测试====================
        //下个版本将使用键盘控制状态变化
        
        //状态数据变化
        System.out.println("will 1,0,90");
        Mywait.myWait();
        aMofangStatusMessage.moFang3DRotate(1,0,90);
        
        //虚拟动画表演
        aBranchGroup3.dongHua(1,0,90);
        Mywait.myWait();
        
        //状态刷新到窗体和场景
        aMofangStatusMessage.outToATable();
        aBranchGroup3=(BranchGroup3)(testPanelAddedScene3D.branchGroupArray[3]);
        aBranchGroup3.setStatus(aMofangStatusMessage.dataMessage);
        
        /*测试
        //状态数据变化
        //System.out.println("will 2,1,90");
        Mywait.myWait();
        aMofangStatusMessage.moFang3DRotate(2,1,90);
        
        //虚拟动画表演
        aBranchGroup3.dongHua(2,1,90);
        Mywait.myWait();
        
        //状态刷新到窗体和场景
        aMofangStatusMessage.outToATable();
        aBranchGroup3=(BranchGroup3)(testPanelAddedScene3D.branchGroupArray[3]);
        aBranchGroup3.setStatus(aMofangStatusMessage.dataMessage);
        
        //状态数据变化
        //System.out.println ("will 0,2,-90");
        Mywait.myWait();
        aMofangStatusMessage.moFang3DRotate(0,2,-90);
        
        //虚拟动画表演
        aBranchGroup3.dongHua(0,2,-90);
        Mywait.myWait();
        
        //状态刷新到窗体和场景
        aMofangStatusMessage.outToATable();
        aBranchGroup3=(BranchGroup3)(testPanelAddedScene3D.branchGroupArray[3]);
        aBranchGroup3.setStatus(aMofangStatusMessage.dataMessage);
        */
        
    }
}

