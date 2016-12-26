package com.huiton.cerp.pub.util;

/**
 * Title:        WorkFlowSubsystem
 * Description:  ��������ϵͳ������
 * Copyright:    Copyright (c) 2001
 * Company:      BRITC
 * @author ������
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




/*@@˵��
    ��������һ�������ԣ�����ͨ�÷����������ơ�
    �ʺ��ڣ���������Ĳ�ѯ�����е�һ�����е�ĳ���ֶ�Ϊ�ض�ֵʱ������һ��������Ԫ����֮��Ӧ����ȡ����Ԫ���е�ĳЩ�ֶ�
    ���ޣ��ڶ������еĹؼ���Ϊһ��
         �ڶ������еĹؼ���Ϊ�ִ�
    @@param:mainDB--����ϵͳ��
            attachDB--����ϵͳ��
            tablenameMain--������
            tablenameAttached--������
            keyWord--�������ض��ֶ���ʾ�������ж�ӦԪ��ʱ��ֵ
            keyFieldLocation--�ض��ֶ���ȡ����Vector�������е����
            pagesize--ȡ1ҳ�ĳ���
            offset--�޸���Ԫ��ʱ������������ֵ
            mainSQL--�����ѯ����
            mainFields--�����ֶΣ����ڵ�һ���ֶ��������븽�����ϵ��ֵ
            mainNo--�����ֶ���
            attachFields--�����ֶΣ����ڵ�һ���ֶ����������������ϵ��ֵ����ֵ��������vector-row�г���
            attachNo--�����ֶ���
            currentpage--��ǰҳ
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
                    Debug.println("multiDBOperator:����������currentPageΪ��"+currentpage);
                    Debug.println("multiDBOperator:mainSQLΪ��"+mainSQL);
                    jdbOp=DBOperators.getJdbOp(request,sessioncode,attachDB);
                    pageQuery=DBOperators.getPageQuery(request,sessioncode,mainDB,funcCode);
                    Debug.println("multiDBOperator:������������ݿ����......");

                  int mainNo=mainFields.length;
                  int attachNo=attachFields.length;
                    //��ȡ��һ�����е�����ֶν����
                    String fields=mainFields[0];
                    for(int i=1;i<mainNo;i++)
                                                fields+=","+mainFields[i];
                    Debug.println("multiDBOperator:�����ݿ��ֶ�Ϊ��"+fields);
                    Debug.println("multiDBOperator:tableΪ��"+tablenameMain);
                    Debug.println("multiDBOperator:��������ݿ����Ϊ��"+mainDB);
                    Debug.println("multiDBOperator:�����ݿ���Ϊ:"+pageQuery.m_conn.getCatalog());
                    pageQuery.getData(fields,tablenameMain,mainSQL,pagesize);

                   this.pageCount=pageQuery.pageCount;
                    Debug.println("multiDBOperator:pagecountΪ��"+pageCount);
                    Debug.println("multiDBOperator:pageQueryȡ�������......");
                  if(currentpage<=0)currentpage=1;
                    Debug.println("multiDBOperator:��ǰҳΪ��"+currentpage);
                    Vector mainVCT=pageQuery.dividePage(currentpage,pagesize);
                   if(mainVCT==null){
                              Debug.println("multiDBOperator:�޼�¼�����ؿ�......");
                            return null;
                    }
                    Debug.println("multiDBOperator:pageQuery��ҳ���......");
                    Debug.println("multiDBOperator:pageQueryԪ����Ϊ��"+String.valueOf(mainVCT.size()));

                    //������ڶ�����Ĺ���
                    fields=attachFields[0];
                    Vector ultimateVCT=new Vector();
                  for(int i=1;i<attachNo;i++)
                                                fields+=","+attachFields[i];
                    Debug.println("multiDBOperator:�����ݿ��ֶ�Ϊ��"+fields);

                    for(int i=0;i<mainVCT.size();++i){
                            String VCTArray[]=new String[mainNo+attachNo-1];
                            String tmpVCTArray[]=new String[mainNo];
                            Debug.println("----MultiDBOperator:mainNo="+mainNo+"attachNo="+attachNo);
                            tmpVCTArray = (String[])mainVCT.elementAt(i);
                            Debug.println("----MultiDBOperator:��Vector ��ȡ�õ�"+i+"������");
                          for(int j=0;j<mainNo;j++){
                                           VCTArray[j]=tmpVCTArray[j];
                                           Debug.println("multiDBOperator:�����ݿ��е�����Ԫ��["+j+"]Ϊ��"+VCTArray[j]);
                                          }
                          if(VCTArray[keyFieldLocation].toLowerCase().equals(keyWord)){
                                         String attachSQL="select "+fields+" from "+tablenameAttached+" where "
                                                        +attachFields[0]+"='"+VCTArray[0]+"'";
                                         Debug.println("multiDBOperator:�����ݿ��ѯSQLΪ��"+attachSQL);
                                         ResultSet rs=jdbOp.getData(attachSQL);
                                          Debug.println("multiDBOperator:ȡ�ø����ݿ�����......");
                                   //   int appendLengh=attachFields.length;
                                         if(rs!=null){
                                                  rs.beforeFirst();
                                               if(rs.next()){
                                                       // String[] appendArray=new String[appendLength];
                                                    for(int j=1;j<attachNo;j++){
                                                                VCTArray[mainNo+j-1]=rs.getString(j+1);
                                                                Debug.println("multiDBOperator:�����ݿ��е�����Ԫ��["+j+"]Ϊ��"+VCTArray[mainNo+j-1]);
                                                                }
                                                    }else{
                                                    for(int j=0;j<attachNo-1;j++)
                                                                  VCTArray[mainNo+j]=offset;
                                                        Debug.println("multiDBOperator:���1");
                                                          }
                                              }else{
                                               for(int j=0;j<attachNo-1;j++)
                                                        VCTArray[mainNo+j]=offset;
                                                   Debug.println("multiDBOperator:���2");
                                                    }
                             }else{
                                       for(int j=0;j<attachNo-1;j++)
                                                        VCTArray[mainNo+j]=offset;
                                          Debug.println("multiDBOperator:���3");
                              }
                              ultimateVCT.addElement(VCTArray);
                              Debug.println("multiDBOperator:��һ�����鵽���շ��ص�ʸ����......");
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
              Debug.println("multiDBOperator:delSQLΪ��"+delSQL);
              jdbOp=DBOperators.getJdbOp(request,sessioncode,mainDB);
              Debug.println("multiDBOperator:��ȡס���ݿ����"+mainDB);
              String tempSQL="select "+mainFlagField+","+mainField+" from "+tablenameMain+" where "+delSQL;
              Debug.println("multiDBOperator:tempSQLΪ��"+tempSQL);
              ResultSet rs=jdbOp.getData(tempSQL);
              Debug.println("multiDBOperator:��������ݿ�����......");
              int mainFlagFieldColNo;
              int mainFiledColNo;
              mainFlagFieldColNo=rs.findColumn(mainFlagField);
              mainFiledColNo=rs.findColumn(mainField);
              if(rs!=null){
                      Debug.println("multiDBOperator:�������Ϊ��......");
                      rs.beforeFirst();
                    while(rs.next()){
                          String mainFlagValue=rs.getString(mainFlagFieldColNo);
                          String mainValue=rs.getString(mainFiledColNo);
                          Debug.println("multiDBOperator:�����ݿ��ʾ--"+mainFlagValue);
                          Debug.println("multiDBOperator:�����ݿ������ֶ�ֵ--"+mainValue);
                          if(mainFlagValue.equals(keyWord)){
                                  tempSQL=attachField+"="+mainValue;
                                  Debug.println("multiDBOperator:�ж�ӦԪ��---SQLΪ��"+tempSQL);
                                  jdbOp=DBOperators.getJdbOp(request,sessioncode,attachDB);
                                  jdbOp.delete(tablenameAttached,tempSQL);
                                  Debug.println("multiDBOperator:�����ݿ�ɾ��һ��Ԫ��");
                                  }
                        }
                }
              jdbOp=DBOperators.getJdbOp(request,sessioncode,mainDB);
              jdbOp.delete(tablenameMain,delSQL);
              Debug.println("multiDBOperator:�����ݿ�ɾ�����");
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
  /*���ڽ��������ݿ���������ѯһҳ
  param:

  public Vector multiDBDividePage(String dbnameMain,String dbnameAttached,String field,
                                    String keyword,int pagesize,String offset){
     Vector vct1=new Vector();
*/



