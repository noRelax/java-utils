package com.huiton.cerp.pub.util;

/**
 * Title:        WorkFlowSubsystem
 * Description:  工作流子系统相关类包
 * Copyright:    Copyright (c) 2001
 * Company:      BRITC
 * @author 高云鹏
 * @version 1.0
 */
import java.sql.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.util.Vector;
import com.huiton.pub.dbx.JdbOp;
import com.huiton.pub.dbx.PageQuery;
import com.huiton.cerp.pub.util.WebKeys;
import com.huiton.cerp.pub.util.SubsystemKeys;
import com.huiton.cerp.pub.util.DBOperators;
import com.huiton.mainframe.util.tracer.Debug;

public class MultiDBOperator {


static private JdbOp jdbOp;
static private PageQuery pageQuery;
static private String mainDB;
static private String attachDB;
static private String tablenameMain;
static private String tablenameAttached;
static private String funcCode;
static private String sessioncode;
static private HttpServletRequest request;

static private int pageCount;
/*public MultiDBOperator( HttpServletRequest request,String mainDB,
                         String attachDB,String sessioncode,String funcCode){

                          jdbOp=getJdbOp(request,sessionCode,mainDB);
                          pageQuery=getPageQuery(request,sessionCode,mainDB,funcCode);
                         }*/
public MultiDBOperator(String mainDB,
                        String attachDB,
                        String tablenameMain,
                        String tablenameAttached,
                        String funcCode,
                        String sessioncode,
                        HttpServletRequest request)
                  throws Exception{
                          this.mainDB=mainDB;
                          this.attachDB=attachDB;
                          this.tablenameMain=tablenameMain;
                          this.tablenameAttached=tablenameAttached;
                          this.funcCode=funcCode;
                          this.sessioncode=sessioncode;
                          this.request=request;
                          this.pageQuery=DBOperators.getPageQuery(request,sessioncode,mainDB,funcCode);
                          this.jdbOp=jdbOp=DBOperators.getJdbOp(request,sessioncode,attachDB);
                          }




/*@@说明
    本方法有一定局限性，不是通用方法，须完善。
    适合于：对两个表的查询，其中的一个表中的某个字段为特定值时，另外一个表中有元组与之对应，须取出该元组中的某些字段
    局限：第二个表中的关键字为一个
         第二个表中的关键字为字串
    @@param:mainDB--主子系统名
            attachDB--副子系统名
            tablenameMain--主表名
            tablenameAttached--副表名
            keyWord--主表中特定字段昭示附表中有对应元组时的值
            keyFieldLocation--特定字段在取出的Vector中数组中的序号
            pagesize--取1页的长度
            offset--无附表元组时返回数组的填充值
            mainSQL--主表查询条件
            mainFields--主表字段：须在第一个字段中设置与附表的联系键值
            mainNo--主表字段数
            attachFields--附表字段：须在第一个字段中设置与主表的联系键值，该值不在最后的vector-row中出现
            attachNo--附表字段数
            currentpage--当前页
            */
public Vector multiDBDividePage(String keyWord,
                                      int keyFieldLocation,
                                      int pagesize,
                                         String offset,
                                         String mainSQL,
                                         String[] mainFields,
                                         String[] attachFields,
                                      int currentpage)
                                       throws SQLException, Exception{
                    Debug.println("multiDBOperator:参数带来的currentPage为："+currentpage);
                    Debug.println("multiDBOperator:mainSQL为："+mainSQL);
                    jdbOp=DBOperators.getJdbOp(request,sessioncode,attachDB);
                    pageQuery=DBOperators.getPageQuery(request,sessioncode,mainDB,funcCode);
                    Debug.println("multiDBOperator:获得主、副数据库代理......");

                  int mainNo=mainFields.length;
                  int attachNo=attachFields.length;
                    //获取第一个表中的相关字段结果集
                    String fields=mainFields[0];
                    for(int i=1;i<mainNo;i++)
                                                fields+=","+mainFields[i];
                    Debug.println("multiDBOperator:主数据库字段为："+fields);
                    Debug.println("multiDBOperator:table为："+tablenameMain);
                    Debug.println("multiDBOperator:获得主数据库参数为："+mainDB);
                    Debug.println("multiDBOperator:主数据库名为:"+pageQuery.m_conn.getCatalog());
                    pageQuery.getData(fields,tablenameMain,mainSQL,pagesize);

                   this.pageCount=pageQuery.pageCount;
                    Debug.println("multiDBOperator:pagecount为："+pageCount);
                    Debug.println("multiDBOperator:pageQuery取数据完成......");
                  if(currentpage<=0)currentpage=1;
                    Debug.println("multiDBOperator:当前页为："+currentpage);
                    Vector mainVCT=pageQuery.dividePage(currentpage,pagesize);
                   if(mainVCT==null){
                              Debug.println("multiDBOperator:无记录，返回空......");
                            return null;
                    }
                    Debug.println("multiDBOperator:pageQuery分页完成......");
                    Debug.println("multiDBOperator:pageQuery元素数为："+String.valueOf(mainVCT.size()));

                    //处理与第二个表的关联
                    fields=attachFields[0];
                    Vector ultimateVCT=new Vector();
                  for(int i=1;i<attachNo;i++)
                                                fields+=","+attachFields[i];
                    Debug.println("multiDBOperator:副数据库字段为："+fields);

                    for(int i=0;i<mainVCT.size();++i){
                            String VCTArray[]=new String[mainNo+attachNo-1];
                            String tmpVCTArray[]=new String[mainNo];
                            Debug.println("----MultiDBOperator:mainNo="+mainNo+"attachNo="+attachNo);
                            tmpVCTArray = (String[])mainVCT.elementAt(i);
                            Debug.println("----MultiDBOperator:从Vector 中取得第"+i+"个数组");
                          for(int j=0;j<mainNo;j++){
                                           VCTArray[j]=tmpVCTArray[j];
                                           Debug.println("multiDBOperator:主数据库中的数组元素["+j+"]为："+VCTArray[j]);
                                          }
                          if(VCTArray[keyFieldLocation].toLowerCase().equals(keyWord)){
                                         String attachSQL="select "+fields+" from "+tablenameAttached+" where "
                                                        +attachFields[0]+"='"+VCTArray[0]+"'";
                                         Debug.println("multiDBOperator:副数据库查询SQL为："+attachSQL);
                                         ResultSet rs=jdbOp.getData(attachSQL);
                                          Debug.println("multiDBOperator:取得副数据库结果集......");
                                   //   int appendLengh=attachFields.length;
                                         if(rs!=null){
                                                  rs.beforeFirst();
                                               if(rs.next()){
                                                       // String[] appendArray=new String[appendLength];
                                                    for(int j=1;j<attachNo;j++){
                                                                VCTArray[mainNo+j-1]=rs.getString(j+1);
                                                                Debug.println("multiDBOperator:副数据库中的数组元素["+j+"]为："+VCTArray[mainNo+j-1]);
                                                                }
                                                    }else{
                                                    for(int j=0;j<attachNo-1;j++)
                                                                  VCTArray[mainNo+j]=offset;
                                                        Debug.println("multiDBOperator:填充1");
                                                          }
                                              }else{
                                               for(int j=0;j<attachNo-1;j++)
                                                        VCTArray[mainNo+j]=offset;
                                                   Debug.println("multiDBOperator:填充2");
                                                    }
                             }else{
                                       for(int j=0;j<attachNo-1;j++)
                                                        VCTArray[mainNo+j]=offset;
                                          Debug.println("multiDBOperator:填充3");
                              }
                              ultimateVCT.addElement(VCTArray);
                              Debug.println("multiDBOperator:即一个数组到最终返回的矢量中......");
                        }
                            return ultimateVCT;
                }

public int getPageCount()
                  {
                          return this.pageCount;
         }

public void multiDBDelete(String mainFlagField,
                            String keyWord,
                            String mainField,
                            String attachField,
                            String delSQL)
                          throws SQLException, Exception{
              Debug.println("multiDBOperator:delSQL为："+delSQL);
              jdbOp=DBOperators.getJdbOp(request,sessioncode,mainDB);
              Debug.println("multiDBOperator:获取住数据库代理："+mainDB);
              String tempSQL="select "+mainFlagField+","+mainField+" from "+tablenameMain+" where "+delSQL;
              Debug.println("multiDBOperator:tempSQL为："+tempSQL);
              ResultSet rs=jdbOp.getData(tempSQL);
              Debug.println("multiDBOperator:查得主数据库数据......");
              int mainFlagFieldColNo;
              int mainFiledColNo;
              mainFlagFieldColNo=rs.findColumn(mainFlagField);
              mainFiledColNo=rs.findColumn(mainField);
              if(rs!=null){
                      Debug.println("multiDBOperator:结果集不为空......");
                      rs.beforeFirst();
                    while(rs.next()){
                          String mainFlagValue=rs.getString(mainFlagFieldColNo);
                          String mainValue=rs.getString(mainFiledColNo);
                          Debug.println("multiDBOperator:主数据库标示--"+mainFlagValue);
                          Debug.println("multiDBOperator:主数据库连接字段值--"+mainValue);
                          if(mainFlagValue.equals(keyWord)){
                                  tempSQL=attachField+"="+mainValue;
                                  Debug.println("multiDBOperator:有对应元组---SQL为："+tempSQL);
                                  jdbOp=DBOperators.getJdbOp(request,sessioncode,attachDB);
                                  jdbOp.delete(tablenameAttached,tempSQL);
                                  Debug.println("multiDBOperator:副数据库删除一条元组");
                                  }
                        }
                }
              jdbOp=DBOperators.getJdbOp(request,sessioncode,mainDB);
              jdbOp.delete(tablenameMain,delSQL);
              Debug.println("multiDBOperator:主数据库删除完成");
        return;

        }

public PageQuery getPageQuery(){
        return this.pageQuery;
    }

public JdbOp getJdbOp(){
        return this.jdbOp;
    }
}
 /*                   pageQuery.getData("USER_NAME,USER_UNIQUE_NO",
                                      "EPD_ADDRESS_EMPLOYEE_V", mySQL, SLIT_Vct.size());

                    jdbOp=getJdbOp(request,sessioncode,dbnameMain);
                    pageQuery=getPageQuery(request,sessioncode,dbnameMain,funcCode);
                    Vector mainVCT





  /*private DBOperators dbOperator;
  private JdbOp jdbOp;
  private PageQuery pageQuery;

  public MultiDBOperator() {
        this.dbOperator=new DBOperators();
  }

  public MultiDBOperator(String mainDB,String attachDB,sessioncode, funcCode) {
          this.dbOperator=new DBOperators();

  public JdbOp getJdbOp(HttpServletRequest request,
                          String sessionCode,
                          String subsystemKey){
        JdbOp tempJdbOp=dbOperator.getJdbOp(request,sessioncode,subsystemKey);
        this.jdbOp=tempJdbOp;
        return tempJdbOp;
  }

  public getPageQuery(HttpServletRequest request,
                        String sessionCode,
                        String subsystemKey,
                        String funcCode){
        PageQuery tempPageQuery=dbOperator.getPageQuery(request,sessioncode,subsystemKey,funcCode);
        this.pageQuery=tempPageQuery;
        return tempPageQuery;
  }
  /*用于将两个数据库联起来查询一页
  param:

  public Vector multiDBDividePage(String dbnameMain,String dbnameAttached,String field,
                                    String keyword,int pagesize,String offset){
     Vector vct1=new Vector();
*/



