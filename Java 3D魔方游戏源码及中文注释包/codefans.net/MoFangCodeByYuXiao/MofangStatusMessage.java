//Finishing www.codefans.net


import javax.swing.table.*;
import java.util.*;
import java.applet.Applet ;
import java.awt.*;
import java.awt.BorderLayout ;
import com.sun.j3d.utils.universe.SimpleUniverse ;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.behaviors.keyboard.*;
import com.sun.j3d.utils.picking.behaviors.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame.*;
import javax.swing.*;
import java.io.*;
class Point3D implements Serializable 
{
    
    //======================================
    //使用Ax=y ，由x计算y。若要由y求A，只需用x=A-1y,例如90度的反变换矩阵为-90度，等价于180度
    //-----------------------------------------
    public static int[][]bianHuanJuZhenX90=
    {
        {
            1,0,0 
        }
        ,
        {
            0,0,-1 
        }
        ,
        {
            0,1,0 
        }
        ,
    }
    ;
    //----------------------------------
    //-----------------------------------------
    public static int[][]bianHuanJuZhenX180=
    {
        {
            1,0,0 
        }
        ,
        {
            0,-1,0 
        }
        ,
        {
            0,0,-1 
        }
        ,
    }
    ;
    //----------------------------------
    //-----------------------------------------
    public static int[][]bianHuanJuZhenX270=
    {
        {
            1,0,0 
        }
        ,
        {
            0,0,1 
        }
        ,
        {
            0,-1,0 
        }
        ,
    }
    ;
    //----------------------------------
    //======================================
    
    //-----------------------------------------
    public static int[][]bianHuanJuZhenY90=
    {
        {
            0,0,1 
        }
        ,
        {
            0,1,0 
        }
        ,
        {
            -1,0,0 
        }
        ,
    }
    ;
    //----------------------------------
    
    //-----------------------------------------
    public static int[][]bianHuanJuZhenY180=
    {
        {
            -1,0,0 
        }
        ,
        {
            0,1,0 
        }
        ,
        {
            0,0,-1 
        }
        ,
    }
    ;
    //----------------------------------
    //-----------------------------------------
    public static int[][]bianHuanJuZhenY270=
    {
        {
            0,0,-1 
        }
        ,
        {
            0,1,0 
        }
        ,
        {
            1,0,0 
        }
        ,
    }
    ;
    //----------------------------------
    //======================================
    //-----------------------------------------
    public static int[][]bianHuanJuZhenZ90=
    {
        {
            0,-1,0 
        }
        ,
        {
            1,0,0 
        }
        ,
        {
            0,0,1 
        }
        ,
    }
    ;
    //----------------------------------
    //-----------------------------------------
    public static int[][]bianHuanJuZhenZ180=
    {
        {
            -1,0,0 
        }
        ,
        {
            0,-1,0 
        }
        ,
        {
            0,0,1 
        }
        ,
    }
    ;
    //----------------------------------
    //-----------------------------------------
    public static int[][]bianHuanJuZhenZ270=
    {
        {
            0,1,0 
        }
        ,
        {
            -1,0,0 
        }
        ,
        {
            0,0,1 
        }
        ,
    }
    ;
    //----------------------------------
    //=================================
    int x=0 ;
    int y=0 ;
    int z=0 ;
    
    Point3D(int px,int py,int pz)
    {
        x=px ;
        y=py ;
        z=pz ;
    }
    
    String myToString()
    {
        return("x="+String.valueOf(x)+"  y="+String.valueOf(y)+"  z="+String.valueOf(z));
    }
    
    void setValue(int px,int py,int pz)
    {
        x=px ;
        y=py ;
        z=pz ;
    }
    
    void point3DRotate(int fangShi,int jiaoDU)
    {
        //System.out.println ("点旋转 fangShi="+String.valueOf (fangShi)+" jiaoDU="+String.valueOf (jiaoDU));
        
        if(fangShi==0)
        {
            point3DRotateX(jiaoDU);
        }
        else if(fangShi==1)
        {
            point3DRotateY(jiaoDU);
        }
        else if(fangShi==2)
        {
            point3DRotateZ(jiaoDU);
        }
        else 
        {
            System.out.println("you must set it with 012--"+String.valueOf(fangShi));
        }
    }
    
    int[]acculate(int[][]juZhen,int[]shuRu,int n)
    {
        //N为维数
        
        int[]shuChu=new int[n];
        for(int i=0;i<n;i++)
        {
            shuChu[i]=0 ;
            //每次计算一个分量
            for(int j=0;j<n;j++)
            {
                shuChu[i]+=juZhen[i][j]*shuRu[j];
                //每次分量由三个数相加
            }
        }
        
        //MyPrintln.println ("result:");
        //MyPrintln.println (shuChu,3);
        
        return shuChu ;
    }
    
    void point3DRotateX(int jiaoDU)
    {
        //System.out.println ("-点旋转 x"+"jiaoDU="+String.valueOf (jiaoDU));
        
        
        int[]shuRu=new int[3];
        shuRu[0]=x ;
        shuRu[1]=y ;
        shuRu[2]=z ;
        //	MyPrintln.println ("");
        //	MyPrintln.println (bianHuanJuZhenX90,3,3);
        //	MyPrintln.println (shuRu,3);
        int[]jieGuo=new int[3];
        
        if(jiaoDU==90||jiaoDU==-270)
        {
            //System.out.println ("90xok");
            
            jieGuo=acculate(bianHuanJuZhenX90,shuRu,3);
        }
        
        else if(jiaoDU==180||jiaoDU==-180)
        {
            //System.out.println ("180xok");
            jieGuo=acculate(bianHuanJuZhenX180,shuRu,3);
        }
        
        else if(jiaoDU==270||jiaoDU==-90)
        {
            //System.out.println ("270xok");
            jieGuo=acculate(bianHuanJuZhenX270,shuRu,3);
        }
        else 
        {
            MyPrintln.println("jiaodu error:"+jiaoDU);
        }
        
        x=jieGuo[0];
        y=jieGuo[1];
        z=jieGuo[2];
        //		MyPrintln.println (jieGuo,3);
    }
    
    void point3DRotateY(int jiaoDU)
    {
        //System.out.println ("-点旋转 y"+"jiaoDU="+String.valueOf (jiaoDU));
        
        
        int[]shuRu=new int[3];
        shuRu[0]=x ;
        shuRu[1]=y ;
        shuRu[2]=z ;
        //	MyPrintln.println ("");
        //	MyPrintln.println (bianHuanJuZhenX90,3,3);
        //	MyPrintln.println (shuRu,3);
        int[]jieGuo=new int[3];
        
        if(jiaoDU==90||jiaoDU==-270)
        {
            //System.out.println ("90yok");
            
            jieGuo=acculate(bianHuanJuZhenY90,shuRu,3);
        }
        
        else if(jiaoDU==180||jiaoDU==-180)
        {
            //System.out.println ("180yok");
            jieGuo=acculate(bianHuanJuZhenY180,shuRu,3);
        }
        
        else if(jiaoDU==270||jiaoDU==-90)
        {
            //System.out.println ("270yok");
            jieGuo=acculate(bianHuanJuZhenY270,shuRu,3);
        }
        else 
        {
            MyPrintln.println("jiaodu error:"+jiaoDU);
        }
        
        x=jieGuo[0];
        y=jieGuo[1];
        z=jieGuo[2];
    }
    
    void point3DRotateZ(int jiaoDU)
    {
        //System.out.println ("-点旋转 z"+"jiaoDU="+String.valueOf (jiaoDU));
        
        
        int[]shuRu=new int[3];
        shuRu[0]=x ;
        shuRu[1]=y ;
        shuRu[2]=z ;
        //	MyPrintln.println ("");
        //	MyPrintln.println (bianHuanJuZhenX90,3,3);
        //	MyPrintln.println (shuRu,3);
        int[]jieGuo=new int[3];
        
        if(jiaoDU==90||jiaoDU==-270)
        {
            //System.out.println ("90zok");
            
            jieGuo=acculate(bianHuanJuZhenZ90,shuRu,3);
        }
        
        else if(jiaoDU==180||jiaoDU==-180)
        {
            //System.out.println ("180zok");
            jieGuo=acculate(bianHuanJuZhenZ180,shuRu,3);
        }
        
        else if(jiaoDU==270||jiaoDU==-90)
        {
            //System.out.println ("270zok");
            jieGuo=acculate(bianHuanJuZhenZ270,shuRu,3);
        }
        else 
        {
            MyPrintln.println("jiaodu error:"+jiaoDU);
        }
        
        x=jieGuo[0];
        y=jieGuo[1];
        z=jieGuo[2];
    }
}

class BlockMessage implements Serializable 
{
    Point3D blockCenter ;
    Point3D blockEx ;
    Point3D blockEy ;
    Point3D blockEz ;
    
    
    
    BlockMessage(BlockMessage aBlockMessage)
    {
        blockCenter=new Point3D(aBlockMessage.blockCenter.x,aBlockMessage.blockCenter.y,aBlockMessage.blockCenter.z);
        blockEx=new Point3D(aBlockMessage.blockEx.x,aBlockMessage.blockEx.y,aBlockMessage.blockEx.z);
        blockEy=new Point3D(aBlockMessage.blockEy.x,aBlockMessage.blockEy.y,aBlockMessage.blockEy.z);
        blockEz=new Point3D(aBlockMessage.blockEz.x,aBlockMessage.blockEz.y,aBlockMessage.blockEz.z);
    }
    
    BlockMessage(int centerX,int centerY,int centerZ)
    {
        blockCenter=new Point3D(centerX,centerY,centerZ);
        blockEx=new Point3D(1,0,0);
        blockEy=new Point3D(0,1,0);
        blockEz=new Point3D(0,0,1);
    }
    
    BlockMessage(int centerX,int centerY,int centerZ,int exX,int exY,int exZ,int eyX,int eyY,int eyZ,int ezX,int ezY,int ezZ)
    {
        blockCenter=new Point3D(centerX,centerY,centerZ);
        blockEx=new Point3D(exX,exY,exZ);
        blockEy=new Point3D(eyX,eyY,eyZ);
        blockEz=new Point3D(ezX,ezY,ezZ);
    }
    
    void setBlockMessage(int centerX,int centerY,int centerZ,int exX,int exY,int exZ,int eyX,int eyY,int eyZ,int ezX,int ezY,int ezZ)
    {
        blockCenter.x=centerX ;
        blockCenter.y=centerY ;
        blockCenter.z=centerZ ;
        
        blockEx.x=exX ;
        blockEx.y=exY ;
        blockEx.z=exZ ;
        
        blockEy.x=eyX ;
        blockEy.y=eyY ;
        blockEy.z=eyZ ;
        
        blockEz.x=ezX ;
        blockEz.y=ezY ;
        blockEz.z=ezZ ;
    }
    
    void setBlockMessage(BlockMessage aBlockMessage)
    {
        blockCenter.x=aBlockMessage.blockCenter.x ;
        blockCenter.y=aBlockMessage.blockCenter.y ;
        blockCenter.z=aBlockMessage.blockCenter.z ;
        
        blockEx.x=aBlockMessage.blockEx.x ;
        blockEx.y=aBlockMessage.blockEx.y ;
        blockEx.z=aBlockMessage.blockEx.z ;
        
        blockEy.x=aBlockMessage.blockEy.x ;
        blockEy.y=aBlockMessage.blockEy.y ;
        blockEy.z=aBlockMessage.blockEy.z ;
        
        blockEz.x=aBlockMessage.blockEz.x ;
        blockEz.y=aBlockMessage.blockEz.y ;
        blockEz.z=aBlockMessage.blockEz.z ;
    }
    
    void block3DRotate(int fangShi012,int jiaoDU)
    {
        blockCenter.point3DRotate(fangShi012,jiaoDU);
        blockEx.point3DRotate(fangShi012,jiaoDU);
        blockEy.point3DRotate(fangShi012,jiaoDU);
        blockEz.point3DRotate(fangShi012,jiaoDU);
    }
}

class MofangStatusMessage implements Serializable 
{
    //=========================
    //定义表结构
    //public static Vector statusVector=new Vector();
    //Mofang.theMainFrame.totlePanel.textPane.setText(statusVector.toString ());
    //System.out.println(statusVector);
    
    //==========================
    BlockMessage[][][]dataMessage ;
    
    
    MofangStatusMessage(MofangStatusMessage aMofangStatusMessage)
    {
        BlockMessage[][][]data=new BlockMessage[3][3][3];
        
        for(int i=0;i<3;i++)
        for(int j=0;j<3;j++)
        for(int k=0;k<3;k++)
        {
            data[i][j][k]=new BlockMessage(aMofangStatusMessage.dataMessage[i][j][k]);
        }
        
        dataMessage=data ;
    }
    
    void setMofangStatusMessage(MofangStatusMessage aMofangStatusMessage)
    {
        // BlockMessage[][][] data=new BlockMessage[3][3][3];
        
        for(int i=0;i<3;i++)
        for(int j=0;j<3;j++)
        for(int k=0;k<3;k++)
        {
            dataMessage[i][j][k].setBlockMessage(aMofangStatusMessage.dataMessage[i][j][k]);
        }
        
        //dataMessage=data;
    }
    
    
    MofangStatusMessage(BlockMessage[][][]arrayBlockMessage)
    {
        BlockMessage[][][]data=new BlockMessage[3][3][3];
        
        for(int i=0;i<3;i++)
        for(int j=0;j<3;j++)
        for(int k=0;k<3;k++)
        {
            data[i][j][k]=new BlockMessage(arrayBlockMessage[i][j][k]);
        }
        
        dataMessage=data ;
        
    }
    
    MofangStatusMessage(Vector aVector)
    {
        BlockMessage[][][]data=new BlockMessage[3][3][3];
        for(int i=0;i<3;i++)
        for(int j=0;j<3;j++)
        for(int k=0;k<3;k++)
        {
            //假设aVector有27个元素，每个元素是一个BlockMessage对象
            //0,0,0对应第1个元素
            //2，2，2对应第26个元素
            data[i][j][k]=new BlockMessage((BlockMessage)aVector.get(3*3*i+3*j+k));
        }
        dataMessage=data ;
    }
    
    MofangStatusMessage(String aString)
    {
        
        //System.out.println(aString);
        if(aString.compareTo("new")==0)
        {
            //System.out.println(aString);
            //Vector newStatus;
            //int id=0;
            //String name="new";
            BlockMessage[][][]data=
            {
                {
                    {
                        new BlockMessage(-1,-1,-1),
                        new BlockMessage(-1,-1,0),
                        new BlockMessage(-1,-1,1)
                    }
                    ,
                    {
                        new BlockMessage(-1,0,-1),
                        new BlockMessage(-1,0,0),
                        new BlockMessage(-1,0,1)
                    }
                    ,
                    {
                        new BlockMessage(-1,1,-1),
                        new BlockMessage(-1,1,0),
                        new BlockMessage(-1,1,1)
                    }
                }
                ,
                {
                    {
                        new BlockMessage(0,-1,-1),
                        new BlockMessage(0,-1,0),
                        new BlockMessage(0,-1,1)
                    }
                    ,
                    {
                        new BlockMessage(0,0,-1),
                        new BlockMessage(0,0,0),
                        new BlockMessage(0,0,1)
                    }
                    ,
                    {
                        new BlockMessage(0,1,-1),
                        new BlockMessage(0,1,0),
                        new BlockMessage(0,1,1)
                    }
                }
                ,
                {
                    {
                        new BlockMessage(1,-1,-1),
                        new BlockMessage(1,-1,0),
                        new BlockMessage(1,-1,1)
                    }
                    ,
                    {
                        new BlockMessage(1,0,-1),
                        new BlockMessage(1,0,0),
                        new BlockMessage(1,0,1)
                    }
                    ,
                    {
                        new BlockMessage(1,1,-1),
                        new BlockMessage(1,1,0),
                        new BlockMessage(1,1,1)
                    }
                }
            }
            ;
            //MofangStatusMessage newMofangStatusMessage=new MofangStatusMessage(data);
            
            dataMessage=data ;
            //outToATable();
        }
    }
    void outToATable()
    {
        //=========================================================
        System.out.println("");
        System.out.println("new status outToATable");
        /*声明表格头数组*/
        
        String[]tableHeads=
        {
            "块编号","自定义编号","中心点坐标(XYZ)","X向量点坐标(1,0,0)","Y向量点坐标(0,1,0)","Z向量点坐标(1,0,0)" 
        }
        ;
        //String[] tableHeads = {"姓名","姓名","姓名","姓名","姓名","姓名","姓名","姓名","姓名"};
        
        //"姓名","姓名","姓名","姓名","姓名","姓名","姓名","姓名","姓名"
        //
        /*将表格头转换过向量类型，以备表格模型使用*/
        Vector tableHeadName=new Vector();
        
        for(int l=0;l<tableHeads.length;l++)
        {
            tableHeadName.add(tableHeads[l]);
        }
        
        
        
        /*初始化表格数据，这些数据实例运行来源于数据库中*/
        
        /*表格向量, 每一个分量是一个行向量*/
        Vector row=new Vector();
        //row.add(tableHeadName);
        for(int i=0;i<3;i++)
        for(int j=0;j<3;j++)
        for(int k=0;k<3;k++)
        {
            Vector cell=new Vector();
            /*表格行向量，每一个分量是一个字符串。其实可以是任何对象*/
            //dataMessage
            cell.add(String.valueOf(i*9+j*3+k)+" [ "+String.valueOf(i)+","+String.valueOf(j)+","+String.valueOf(k)+" ] ");
            
            BranchGroup3 aBranchGroup3=(BranchGroup3)(MoFang.testPanelAddedScene3D.branchGroupArray[3]);
            cell.add(String.valueOf(aBranchGroup3.blockBianHaoZiDingYi[i][j][k]));
            
            
            cell.add(String.valueOf(dataMessage[i][j][k].blockCenter.x)+"," 
            +String.valueOf(dataMessage[i][j][k].blockCenter.y)+"," 
            +String.valueOf(dataMessage[i][j][k].blockCenter.z));
            
            cell.add(String.valueOf(dataMessage[i][j][k].blockEx.x)+"," 
            +String.valueOf(dataMessage[i][j][k].blockEx.y)+"," 
            +String.valueOf(dataMessage[i][j][k].blockEx.z));
            
            cell.add(String.valueOf(dataMessage[i][j][k].blockEy.x)+"," 
            +String.valueOf(dataMessage[i][j][k].blockEy.y)+"," 
            +String.valueOf(dataMessage[i][j][k].blockEy.z));
            
            cell.add(String.valueOf(dataMessage[i][j][k].blockEz.x)+"," 
            +String.valueOf(dataMessage[i][j][k].blockEz.y)+"," 
            +String.valueOf(dataMessage[i][j][k].blockEz.z));
            
            /*
            
             //为了找出场景中用的旋转，作以下计算，得到一个字符串向量
                       int[][]test1=
                    {
                        {
                            1,0,0
                        }
                        ,
                        {
                            0,1,0
                        }
                        ,
                        {
                            0,0,1
                        }
                    }
                    ;
                    int[][]test2=
                    {
                        {
                            1,0,0
                        }
                        ,
                        {
                            0,1,0
                        }
                        ,
                        {
                            0,0,1
                        }
                    }
                    ;
                    test2[0][0]=dataMessage[i][j][k].blockEx.x ;
                    test2[1][0]=dataMessage[i][j][k].blockEx.y ;
                    test2[2][0]=dataMessage[i][j][k].blockEx.z ;
            
                    test2[0][1]=dataMessage[i][j][k].blockEy.x ;
                    test2[1][1]=dataMessage[i][j][k].blockEy.y ;
                    test2[2][1]=dataMessage[i][j][k].blockEy.z ;
            
                    test2[0][2]=dataMessage[i][j][k].blockEz.x ;
                    test2[1][2]=dataMessage[i][j][k].blockEz.y ;
                    test2[2][2]=dataMessage[i][j][k].blockEz.z ;
            
                    int[] controlInt={1,1,1,1,1,1,1,1,1};
                    Vector myVector=BlockRotateStatusTableAndSouSuo.calculateControledRotateSelf(controlInt,test1,test2,-1,-1,2);
               //计算完毕
                    cell.add(myVector.toString());
                    */
            
            
            
            row.add(cell);
            /* 把行向量添加到表格向量中*/
            //System.out.println (row);
        }
        
        /*声明表格模型*/
        //System.out.println (row);
        //System.out.println (tableHeadName);
        DefaultTableModel tableModel=new DefaultTableModel();
        ///tableModel = new DefaultTableModel();
        /*用数据和头设置表格模型*/
        tableModel.setDataVector(row,tableHeadName);
        
        MyPrintln.println("table new out");
        MoFang.theMainFrame.totlePanel.table.setModel(tableModel);
        
        /*用数据和头设置表格模型*/
        
        //tableModel.setDataVector();
        MoFang.theMainFrame.totlePanel.table.setGridColor(Color.BLUE);
        
        //SimpleTable.table.setModel(SimpleTable.tableModel);
        
        ///////MainFrame.totlePanel.table.setGridColor (Color.blue);
        //tableModel.set
        //  Mofang.theMainFrame.totlePanel.table.setTableHeader (
        
        //MainFrame.totlePanel.table.repaint ();
    }
    
    
    void moFang3DRotate(int fangShi,int layer012,int jiaoDU)
    {
        
        if(fangShi==0)
        {
            
            for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            for(int k=0;k<3;k++)
            {
                if(dataMessage[i][j][k].blockCenter.x==layer012-1)
                {
                    dataMessage[i][j][k].block3DRotate(fangShi,jiaoDU);
                }
                
            }
        }
        else if(fangShi==1)
        {
            
            for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            for(int k=0;k<3;k++)
            {
                if(dataMessage[i][j][k].blockCenter.y==layer012-1)
                {
                    dataMessage[i][j][k].block3DRotate(fangShi,jiaoDU);
                }
                
            }
        }
        else if(fangShi==2)
        {
            
            for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            for(int k=0;k<3;k++)
            {
                if(dataMessage[i][j][k].blockCenter.z==layer012-1)
                {
                    dataMessage[i][j][k].block3DRotate(fangShi,jiaoDU);
                }
                
            }
        }
        
    }
    
}

class MyPrintln 
{
    public static String type1="systemOut" ;
    public static String type2="text" ;
    public static String type="systemOut" ;
    static void println(float[]floatArray,int n)
    {
        String outString ;
        outString="&" ;
        for(int j=0;j<n;j++)
        {
            outString+=" "+String.valueOf(floatArray[j]);
        }
        System.out.println("&"+outString);
    }
    
    static void println(int[]intArray,int n)
    {
        String outString ;
        outString="&" ;
        for(int j=0;j<n;j++)
        {
            outString+=" "+String.valueOf(intArray[j]);
        }
        System.out.println("&"+outString);
    }
    
    static void println(int[][]intArray,int hang,int lie)
    {
        
        String outString ;
        for(int i=0;i<hang;i++)
        {
            outString="&&&" ;
            for(int j=0;j<lie;j++)
            {
                outString+=" "+String.valueOf(intArray[i][j]);
            }
            System.out.println("&&"+outString);
        }
    }
    
    static void println(String aString)
    {
        if(type.compareTo(type1)==0)
        {
            System.out.println("->"+aString);
        }
        
        
        
        if(type.compareTo(type2)==0)
        {
            System.out.println("#"+aString);
            
        }
    }
}