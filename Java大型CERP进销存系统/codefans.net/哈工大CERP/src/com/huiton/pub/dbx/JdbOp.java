/**
 * ���ݿ�����������
 * ��װ�˼�¼�����ӡ��޸ġ�ɾ���ȹ���
 * ���ݿ����ӹ��������������ɣ�cn.com.huiton.ConnectionPool.ConnectionPool��
 *
 * ʹ�ò��裺
 * 1����������
 * 2��ʹ�� connect() ���������ݿ⽨�����ӣ�
 * 3���������ݿ������
 * 4��ʹ�� disConnect() ���������ݿ�Ͽ����ӣ�
 * 5�����ٶ���
 *
 * @@author=�⽣������
 */

package com.huiton.pub.dbx;

import java.sql.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.*;

import com.huiton.mainframe.util.tracer.*;

public class JdbOp
{
        public Connection m_conn;
        public JdbObj preDbObj = null;    //Ԥ�����ɵ����ݿ������
        public String m_errMsg = "";
        public String sessionCode = null;   //��ǰ�Ự����,�����ݿ�����
        public Statement m_statement = null;
        Vector stVector = new Vector();     //�����´����ĻỰ����finalize���ͷ���
        boolean autoCommit = true;
        String cfgFile = "";
        boolean connectDirect = false;;    //ֱ�����ӱ�־����ʹ�ù�˾���ꡢ��ϵͳ��ʽֱ������ʱ����Ϊtrue

        public static final int DBNAME_SQLSERVER = 0;
        public static final int DBNAME_ORACLE = 0;
        public static final int DBNAME_SYBASE = 0;


        public JdbOp() throws Exception{
          /*connect();*/
          createConnect();  //��ȱʡ������ʼ�����ݿ����wt
        }

        /**
         * Description: ���캯����ʹ��sessionCode��ȡCerp���ݿ�����
         * @param sessionCode��ϵͳ��½ʱ�����ĻỰΨһ��ʶ
         * @param sysCode: Ҫ���ӵ�ϵͳ���롣���Ϊ�գ�����"sam"��
         */
         public JdbOp(String sessionCode,String sysCode) throws Exception{
          if (!createConnect(sessionCode,sysCode))
            throw (new Exception(m_errMsg));
         }

        /**
         * Description: ���캯����ʹ��sessionCode��ȡCerp���ݿ����ӣ������������ƶ�һ�����ݿ����ӵ������ļ�
         * @param sessionCode��ϵͳ��½ʱ�����ĻỰΨһ��ʶ
         * @param sysCode: Ҫ���ӵ�ϵͳ���롣���Ϊ�գ�����"sam"��
         * @param cfgFile: �����ļ���
         */
         public JdbOp(String sessionCode,String sysCode, String cfgFile) throws Exception{
          if (cfgFile!=null && !cfgFile.equalsIgnoreCase(""))
            this.cfgFile = cfgFile;
          if (!createConnect(sessionCode,sysCode))
            throw (new Exception(m_errMsg));
         }

         /**
          * ���ո����Ĳ�����ֱ���������ݿ⡣������ֻ����Cerp
          * @param companyCode  ��˾����
          * @param year ���
          * @param sysCode  ��ϵͳ����
          * @param isDirect ����������Ӧ��������Ϊtrue
          * @throws Exception �׳��κδ���
          */
         public JdbOp(String companyCode, String year, String sysCode,boolean isDirect) throws Exception {
          if (!createConnect(companyCode,year,sysCode))
            throw new Exception("�������ݿ����ӳ���: " + this.m_errMsg);
          connectDirect = true;
         }

        /**
         * ����һ����¼
         * @return boolean �����ɹ����� TRUE�����򷵻� FALSE
         * @param String table ����
         * @param String[] fields �ֶ���������
         * @param String[] types �ֶ��������飬����char, varchar���ͣ���Ӧֵ��string���������Ϳ�Ϊ����ֵ
         * @param String[] values �ֶ�����Ӧ��ֵ������
         */
        public boolean insert(String table,
                              String[] fields,
                              String[] values) throws SQLException, Exception
        {
              m_errMsg = "";
                        if(fields.length!=values.length)
                                throw new Exception("The numerber of fields and values must be identical.");

                        if(m_conn==null) connect();

                        Statement statement = m_conn.createStatement();

                        String strFields="", strValues="";

                        for(int i=0;i<fields.length;i++) {
                                strFields += fields[i] + ", ";
                                strValues += "'" + values[i] + "', ";
                        }

                        strFields = strFields.substring(0, strFields.length() - 2);
                        strValues = strValues.substring(0, strValues.length() - 2);

                        String mySql = "INSERT INTO " + table + "("
                                + strFields + ") VALUES(" + strValues + ")";

                        // System.out.println(mySql);
                        statement.execute(mySql);

                        return true;
        }


        /**
         * ����һ����¼
         * @return boolean �����ɹ����� TRUE�����򷵻� FALSE
         * @param String criteria ɸѡ����
         * @param String table ����
         * @param String[] fields �ֶ���������
         * @param String[] types �ֶ��������飬����char, varchar���ͣ���Ӧֵ��string���������Ϳ�Ϊ����ֵ
         * @param String[] values �ֶ�����Ӧ��ֵ������
         */
        public boolean update(String table,
                              String criteria,
                              String[] fields,
                              String[] values)
        {     m_errMsg = "";
                try {
                        if(fields.length!=values.length)
                                throw new Exception("The numerber of fields and values must be identical.");

                        if(m_conn==null) connect();

                        Statement statement = m_conn.createStatement();

                        String strFields="";
                        for(int i=0;i<fields.length;i++) {
                                strFields += fields[i] + " = '" + values[i] + "', ";
                        }
                        strFields = strFields.substring(0, strFields.length() - 2);

                        if(criteria.equals("")) criteria = " 1=1 ";
                        System.out.println("UPDATE " + table + " SET " + strFields + " WHERE " + criteria);
                        statement.execute("UPDATE " + table + " SET " + strFields + " WHERE " + criteria);

                        return true;
                }
                catch(Exception e) {
//                        disConnect();
                  m_errMsg =this.getClass().getName() + ".update(): " + e;
                        System.out.println(e.toString());
                        return false;
                }
        }


        /**
         * ɾ��һ����¼
         * @return boolean �����ɹ����� TRUE�����򷵻� FALSE
         * @param String table ����
         * @param String criteria ɸѡ����
         */
        public boolean delete(String table, String criteria)
        {m_errMsg = "";
                try {
                        if(m_conn==null) connect();
                        Statement statement = m_conn.createStatement();
                        statement.execute("DELETE FROM " + table + " WHERE " + criteria);

                        return true;
                }
                catch(Exception e) {
//                        disConnect();
                        System.out.println(e.toString());
                        return false;
                }
        }


        /**
         * void connect()		�����ݿ⽨������
         *
         * ����ֵ��				void
         *
         * ������
         * modify by wt: 2001.7.12. remove ���ӳ�
         */
        public void connect() {
                if(m_conn!=null) return;

                try {
/*
                        ConnectionPool pool = new ConnectionPool();
                        if(pool.getDriver()==null) {
                            pool.setDriver("sun.jdbc.odbc.JdbcOdbcDriver");
                            pool.setURL("jdbc:odbc:EJBTest");
                            pool.setUsername("cerp95");
                            pool.setPassword("ttbq");
                            pool.setSize(5);
                            pool.initializePool();
                        }

                        conn = pool.getConnection();
*/
                  System.out.println("-----in JdbOp:");
                  createConnect();
                }
                catch(Exception e) {
                        disConnect();
            e.printStackTrace();
                }
        }


        /**
         * void disConnect()	�Ͽ����ݿ�����
         *
         * ����ֵ��				void
         *
         * ������
         */
        public void disConnect() {
                try {
                        if(m_conn!=null)
                                m_conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

  /**
   *Title: ������
   *Description ���������ݸ����Ĳ����������ݱ�ʹ�ñ������Ѿ�����������conn
   *@param tblName Ҫ�����ı���
   *@param FieldDef[] ����ֶζ���
   *@return �����ɹ�����Ѿ����ڣ�����true�����򷵻�false
   */
  public boolean createTbl(String tblName, FieldDef[] fields) {
     m_errMsg = "";
     String mySql = "Create table " + tblName + " (";

try{
    if (preDbObj == null) {//���ӻ�û�н�������ȱʡ��ʽ����֮
      if (!createConnect()){
        m_errMsg = preDbObj.m_errMsg;
        return false;
      }
    }

    //������������
    if (tblName==null || tblName.length()==0) {
      m_errMsg = "Ҫ�����ı����Ƿ���" + tblName;
      return false;
    }

    if (preDbObj.hasTable(tblName))
      return true;  //�Ѿ��иñ�ֱ�ӷ���

    if (fields.length==0) {
      m_errMsg = "û���ֶζ���";
      return false;
    }

    //������
    int fCounter;
    m_errMsg = "";
    String keyStr = "";   //��ֵ
    //ѭ�����Ҫ�������ֶ�
    for (fCounter = 0; fCounter < fields.length; fCounter++){
      if (fCounter > 0)
        mySql += ",";     //ƴ��һ��ǰ�ӷָ���
      mySql += fields[fCounter].colName + " ";
      mySql += fields[fCounter].colType;
      if (fields[fCounter].colDft!=null &&  fields[fCounter].colDft.length() > 0)
        mySql += " default " + fields[fCounter].colDft;         //��ȱʡֵ
      else if (!fields[fCounter].nullable)    //������Ϊ��
        mySql += " not null";
      //��Ӽ�ֵ
      if (fields[fCounter].isPriKey) {
        if (keyStr.length() == 0)
          keyStr = ", primary key(" + fields[fCounter].colName;
        else
          keyStr += "," + fields[fCounter].colName;
      }
    }
    //������
    if (keyStr.length() > 0)
      {//�м�ֵ����
       keyStr += ")";
       mySql += keyStr + ")";
      }

    //ִ�����
    Statement statement = preDbObj.conn.createStatement();
//System.out.println(mySql);
    statement.execute(mySql);

    return true;
}catch (Exception e) {
  m_errMsg = e.getMessage() + "--\n" + mySql;
  System.out.println(m_errMsg);
  return false;
}

  }

  /**
   * Title: ��������
   * Description: �������ڵ�ǰ���ӵ����ݿ��д����������
   * @param idxName ������
   * @param tblName ����
   * @param isUnique �Ƿ�ΪΨһ����
   * @param fields �����ֶζ���
   * @return ���������ɹ��������Ѿ����ڣ�����true�����򷵻�false
   */
  public boolean createIndex(String idxName, String tblName, boolean isUnique, IdxField[] fields) {
    m_errMsg = "";

    String mySql = "Create ";   //�����������
try{
    if (preDbObj == null) {//���ӻ�û�н�������ȱʡ��ʽ����֮
      if (!createConnect()){
        m_errMsg = preDbObj.m_errMsg;
        return false;
      }
      return false;
    }

    //�������Ƿ���Ч
    if (idxName.length() == 0)  {
      m_errMsg = "�յ�������";
      return false;
    }
    if (tblName.length() == 0)  {
      m_errMsg = "Ҫ�������ı�����Ч��" + tblName;
      return false;
    }

    if (preDbObj.hasIndex(tblName,idxName))
      return true;

    if (fields.length == 0) {
      m_errMsg = "û�������ֶζ���";
      return false;
    }

    //��������
    int loopCounter;
    if (isUnique)
      mySql += "Unique ";
    mySql += "Index " + idxName + " on " + tblName + "(";
    for (loopCounter = 0; loopCounter < fields.length; loopCounter++) {
      if (loopCounter == 0)
        mySql += fields[loopCounter].fieldName;
      else
        mySql += "," + fields[loopCounter].fieldName;
      if (!fields[loopCounter].isAsc) {
        //����
        mySql += " Desc";
      }

    }
    mySql += ")";

    //��������
    Statement st = preDbObj.conn.createStatement();
//System.out.println(mySql);
    st.execute(mySql);

   return true;
}catch (Exception e) {
 m_errMsg = e.toString() + "--\n" + mySql;
 System.out.println(m_errMsg);
 return false;
}
  }

  /**
   * Title: ���캯��
   * Description: �������������ݿ����ͺ�URL��ʼ�����ݿ�
   * @Param: type ���ݿ����͡����ó�����DbType���ж���
   * @param: url Ҫ���ӵ����ݿ⡣���ó�����DbUrl�ж���
   */
  public JdbOp(int type, int url) throws Exception {
    if (!createConnect(type, url))
      throw new Exception("�޷��������ݿ�����");
  }
  /**
    * ���캯��
    * @param Connection conn ���ݿ�����
   */
    public JdbOp(Connection conn) throws Exception{
        m_conn = conn;
        m_statement = m_conn.createStatement();
    }

  /**
   * @Title: �������ݿ����Ӷ���
   * @Description: �������������ݿ����ͺ�URL��ʼ�����ݿ�
   * @Param: type ���ݿ����͡����ó�����DbType���ж���
   * @param: url Ҫ���ӵ����ݿ⡣���ó�����DbUrl�ж���
   */
  boolean createConnect(int type, int url) throws Exception {
    if (preDbObj != null)
      preDbObj.finalize();  //�Ѿ����ڶ���,����֮
    preDbObj = new JdbObj(type,url);    //�����������Ӷ���
    if (preDbObj.active(DbConstStr.DEVPASSWORD))  //����������ݿ�����
      m_conn = preDbObj.conn;
    else
      {m_errMsg = preDbObj.m_errMsg;
       return false;
      }
    m_statement = m_conn.createStatement();
    return true;
  }

  /**
   * @Title: �������ݿ����Ӷ���
   * @Description: ��������ȱʡ��ʽ�������ݿ����Ӷ���
   */
  boolean createConnect() throws Exception {
    if (preDbObj != null)
      preDbObj.finalize();  //�Ѿ����ڶ���,����֮
    preDbObj = new JdbObj();    //�����������Ӷ���

    if (preDbObj.active(DbConstStr.DEVPASSWORD))  //����������ݿ�����
      m_conn = preDbObj.conn;
    else
      {m_errMsg = preDbObj.m_errMsg;
       return false;
      }

    m_statement = m_conn.createStatement();
   return true;
  }

  /**
   * ���ݸ����Ĳ����������ݿ����Ӷ��󡣱�����Ӧ��JdbOp()���캯�����ã�ֻ������cerpϵͳ
   * @param companyCode Ҫ���ӵĹ�˾��
   * @param year  Ҫ���ӵ����
   * @param sysCode Ҫ���ӵ���ϵͳ��
   * @return  �ɹ�ʱ����true�����򷵻�false
   * @throws Exception  �׳���������
   */
  public boolean createConnect(String companyCode,String year,String sysCode) throws Exception {
    if (preDbObj != null)
      preDbObj.finalize();  //�Ѿ����ڶ���,����֮
    preDbObj = new JdbObj();    //�����������Ӷ���
    if (preDbObj.active(DbConstStr.DEVPASSWORD,companyCode,year,sysCode))  //����������ݿ�����
      m_conn = preDbObj.conn;
    else
      {m_errMsg = preDbObj.m_errMsg;
       return false;
      }

    m_statement = m_conn.createStatement();
   return true;
  }

  /**
   * @Title: �������ݿ����Ӷ���
   * @Description: �����������û�������ʽ�������ݿ����Ӷ�������cerp9�����ӷ�ʽ
   * @param sessionCode: �Ự����
   * @param sysCode: �����ϵͳ����
   */
  protected boolean createConnect(String sessionCode, String sysCode) {
  try {
    if (preDbObj != null)
      preDbObj.finalize();  //�Ѿ����ڶ���,����֮
    if (!cfgFile.equalsIgnoreCase(""))
      preDbObj = new JdbObj(sessionCode,sysCode,cfgFile);    //�����������Ӷ���
    else
      preDbObj = new JdbObj(sessionCode,sysCode);    //�����������Ӷ���

    if (preDbObj.active(DbConstStr.DEVPASSWORD))  //����������ݿ�����
      m_conn = preDbObj.conn;
    else
      {m_errMsg = preDbObj.m_errMsg;
       return false;
      }
    m_statement = m_conn.createStatement(preDbObj.m_scrollType,preDbObj.m_updateType);
//    m_statement = m_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    if (m_statement == null)
      throw (new Exception("�����Ự�䲢ʧ��"));

   this.sessionCode = sessionCode;
   return true;
  }catch (Exception e)  {
    m_errMsg = "JdbOp:createConnect: " + e;
    return false;
  }

  }

  /**
   * ���÷��صĽ�������͡���������Ч�󣬵�ǰ�ĻỰ�䲢�����ر�
   * @param scrollType: ���������������
   * @param updateType: ���������ģʽ
   */
   public void setRstType(int scrollType, int updateType) throws Exception{
    m_errMsg = "";
    if (m_statement != null)
      m_statement.close();
    m_statement = m_conn.createStatement(scrollType,updateType);

   }

  /**
   * ���ɽ����
   * Description: �����ε��ñ����ܽ�ֻ�ܱ��浱ǰ�����
   * @param mySql: ���ڲ�������������
   */
   public ResultSet getData(String mySql) throws Exception {
        m_errMsg = "";
    try {
       // System.out.println("Native Form: " + m_conn.nativeSQL(mySql));
      ResultSet rs = m_statement.executeQuery(mySql);

      return rs;
    }
    catch (Exception e) {
      m_errMsg =this.getClass().getName() + ".getData(): " + e;
//      return null;
      System.out.println(m_errMsg+":\n" + mySql);
      throw new Exception(m_errMsg);
   }
  }

  /**
   * ���ɽ����
   * Description: ������ʹ���µĻỰ���ؽ������
   * @param mySql: ���ڲ�������������
   */
   public ResultSet getDataNew(String mySql) {
        m_errMsg = "";
    try {
       // System.out.println("Native Form: " + m_conn.nativeSQL(mySql));
      Statement st = m_conn.createStatement(preDbObj.m_scrollType,preDbObj.m_updateType);
      ResultSet rs = st.executeQuery(mySql);

      return rs;
    }
    catch (Exception e) {
      m_errMsg =this.getClass().getName() + ".getDataNew(): " + e;
      System.out.println(m_errMsg);
      return null;
   }
  }

  /**
   * ִ��Sql���
   * @param mySql: Ҫִ�е�Sql���
   */
  public boolean exeSql(String mySql) {
    m_errMsg = "";
try{
    Statement st = m_conn.createStatement();
    st.execute(mySql);
    st.close();
    return true;
}catch (Exception e)  {
  m_errMsg =this.getClass().getName() + ".exeSql(): " + e + "�������:\n" + mySql;
  System.out.println(m_errMsg);
  return false;
}
  }

  /**
   * ִ��Sql��䣬�����ʹ���µĻỰ��������ƻ��Ѿ����صĽ����
   * @param mySql: Ҫִ�е�Sql���
   */
  public boolean exeSqlNT(String mySql) {
    m_errMsg = "";
try{
    Statement st = m_conn.createStatement();
    boolean rt = st.execute(mySql);
    st.close();
    return rt;
}catch (Exception e)  {
  m_errMsg =this.getClass().getName() + ".exeSql(): " + e;
  System.out.println(m_errMsg);
  return false;
}
  }

  /**
   * �������ת��ΪVector������������һά�ַ���������ɡ����������Ϊ���ֶ���
   * @param Rst:Ҫת���Ľ����
   * @return �ɹ��Ƿ������������򷵻�null;
   */
  public Vector rst2Vector(ResultSet rst) throws Exception {
  try {
    Vector vRst = new Vector();

     //��ȡ��ͷ��Ϣ
     ResultSetMetaData rstm = rst.getMetaData();
//System.out.println("getmeta");
     int colcount = rstm.getColumnCount(), i;
     String colname[] = new String[colcount];
     //�γ��ֶ�������
     for (i=1; i<=colcount; i++)
       colname[i-1] = rstm.getColumnName(i);    //�ֶ�����1��ʼ������Ӧ��0��ʼ
     vRst.addElement(colname);    //�ֶ�������λ��0λ��
//System.out.println("cr field");

     //�γɼ�¼��,��ʸ��������1��ʼ
     rst.beforeFirst();
     while (rst.next())
       {String row[] = new String[colcount];
        for (i=0; i<colcount; i++)
          {row[i] = rst.getString(i+1);
           if (row[i]==null)
             row[i] = "";
          }
//System.out.println(row[0]);
        vRst.addElement(row);
       }

    return vRst;
  }catch (Exception e)  {
    return null;
  }

  }

  /**
   * �������ת��ΪVector������������һά�ַ���������ɡ�ͨ����������ѡ�����ɻ������ֶ���ͷ��
   * @param rst Ҫת���Ľ����
   * @param hasTitle
   * @return �ɹ�ʱ�������������򷵻�null;
   * @throws Exception �׳����д���
   */
  public Vector rst2Vector(ResultSet rst, boolean hasTitle) throws Exception {
    Vector vc = rst2Vector(rst);
    if (!hasTitle)
      vc.remove(0);
    return vc;
  }

  /**
   * �������ת��ΪVector������������HashMap������ɡ�ÿ��HashMap�������ֶ�����Ϊ��ֵ���ֶ�ֵ�ַ�����Ϊֵ
   * @param rst Ҫת���Ľ����
   * @return �ɹ�ʱ�������������򷵻�null;
   * @throws Exception �׳����д���
   */
  public Vector rst2HashVector(ResultSet rst) {
  try {
    Vector vRst = new Vector();

     //��ȡ��ͷ��Ϣ
     ResultSetMetaData rstm = rst.getMetaData();
//System.out.println("getmeta");
     int colcount = rstm.getColumnCount(), i;
     String colname[] = new String[colcount];
     //�γ��ֶ�������
     for (i=1; i<=colcount; i++)
       colname[i-1] = rstm.getColumnName(i);    //�ֶ�����1��ʼ������Ӧ��0��ʼ
//System.out.println("cr field");

     //�γɼ�¼��,��ʸ��������1��ʼ
     rst.beforeFirst();
     while (rst.next())
       {HashMap hm = new HashMap();
        for (i=0; i<colcount; i++)
          hm.put(colname[i],rst.getString(i+1));
        vRst.addElement(hm);
       }
    rst.beforeFirst();
    return vRst;
  }catch (Exception e)  {
    return null;
  }
  }

  /**
   * ���ص�ǰ��¼�Ĺ�ϣӳ�䡣
   * @param rst ������ǰ��¼�Ľ������
   * @return  �ɹ�ʱ���ص�ǰ��¼�Ĺ�ϣӳ�䣬���������Ĺ���ڵ�һ����¼֮ǰ���Է��ص�һ����¼��ʧ��ʱ����null��
   */
  public HashMap recMap(ResultSet rst)  {
  try {
    if (rst.isBeforeFirst())
      rst.next();
     //��ȡ��ͷ��Ϣ
     ResultSetMetaData rstm = rst.getMetaData();
     int colcount = rstm.getColumnCount(), i;
     String colname[] = new String[colcount];
     //�γ��ֶ�������
     for (i=1; i<=colcount; i++)
       colname[i-1] = rstm.getColumnName(i);    //�ֶ�����1��ʼ������Ӧ��0��ʼ

     //�γɼ�¼��,��ʸ��������1��ʼ
     HashMap hm = new HashMap();
     for (i=0; i<colcount; i++)
       hm.put(colname[i],rst.getString(i+1));
    return hm;

  }catch (Exception e)  {
    e.printStackTrace();
    m_errMsg = "recMap error: " + e;
    return null;
  }
  }
  /**
   * ������������ص��ֶζ���
   * @param rst: Ҫ��ȡ����Ľ����
   * @return ���ص�����������������ݾ�ΪFieldDef���ͣ�ֻ���ֶ������ֶ����͡����ֶγ��ȱ����أ�ʧ��ʱ���ؿ�
   */
  public Vector getFieldsDef(ResultSet rst)  {
       m_errMsg = "";
  try {
    Vector vFields = new Vector();
     //��ȡ��ͷ��Ϣ
     ResultSetMetaData rstm = rst.getMetaData();
//System.out.println("getmeta");
     int colcount = rstm.getColumnCount(), i;
     //�γ��ֶ�������
     for (i=1; i<=colcount; i++)  {
        FieldDefn fd = new FieldDefn();
        fd.colName = rstm.getColumnName(i); //�ֶ�����1��ʼ
        fd.colType = rstm.getColumnType(i);
        fd.colLen = rstm.getPrecision(i);

        vFields.add(fd);
//        vFields.addElement(new Integer(rstm.getPrecision(i)));
     }
    return vFields;
  }catch (Exception e)  {
    m_errMsg =this.getClass().getName() + ".getFieldsDef(): " + e;
    System.out.println(m_errMsg);
    return null;
  }
  }


  /**
   * �����������䣬��������getDataʹ����ͬ�ĻỰ���
   * @param sqlStr: Ҫ��ӵ����
   * @return �ɹ��r����true, ���򷵻�false
   */
  public boolean addBatchSql(String sqlStr)  {
try {
    if (autoCommit)  {
      //��������ʼ����exeBatch�м������񣬲��ر��Զ�ִ��
      autoCommit = false;
      m_conn.setAutoCommit(false);
    }
    m_statement.addBatch(sqlStr);
    return true;
}catch (Exception e)  {
    m_errMsg =this.getClass().getName() + ".addBatchSql(): " + e;
    System.out.println(m_errMsg);
    return false;
}
  }

  /**
   * ִ�����������
   * @return �ɹ�ʱ����ÿһ�����ĵ�״ֵ̬���飬���򷵻�null
   */
  public int [] exeBatchSql() throws Exception{
try{
    int[] ret;
    ret = m_statement.executeBatch();
    autoCommit = true;  //�����Զ�ִ��
    m_conn.setAutoCommit(true);
    m_conn.commit();
    return ret;

}catch (Exception e)  {
    m_errMsg =this.getClass().getName() + ".exeBatchSql(): " + e;
    m_errMsg += "\n���񱻻ع�.";
    m_conn.rollback();
    autoCommit = true;  //�����Զ�ִ��
    m_conn.setAutoCommit(true);
    System.out.println(m_errMsg);
    return null;
}
  }

  /**
   * �޸�һ����¼���÷��������������º�ɾ����¼
   * @param  String sql ���в�����sql���
   * @return boolean �����ɹ����� TRUE�����򷵻� FALSE
   */
    public boolean simpleUpdate (String sql) throws Exception
    {
     m_errMsg = "";
     try  {
            if(m_conn == null)
                connect();
            Statement stmt = m_conn.createStatement();
            boolean rt = stmt.execute(sql);
            stmt.close();
            return true;

        }
      catch(Exception e)
        {
          m_errMsg =this.getClass().getName() + ".exeSql(): " + e;
          System.out.println(m_errMsg);
         return false;
        }
    }


  /**
   * ��ȡ������Ϣ
   */
  public String getErrMsg() {
    return m_errMsg;
  }

  /**
   * �������ݿ�
   * @param dbName: Ҫ�������ݿ���
   * @return ��������ɹ����򷵻�true������ǰ���ݿ�����ָ���µ����ݿ⣬���򷵻�false
   */
 public boolean createDataBase(String dbName) {
  m_errMsg = "";
try {
  exeSql("Create DataBase " + dbName);
  m_conn.setCatalog(dbName);
  return true;

}catch (Exception e)  {
  m_errMsg =this.getClass().getName() + ".createDataBase(): " + e;
  System.out.println(m_errMsg);
  return false;
}

 }

 /**
  * �������ݿ������Զ����ٷ�ʽ
  * @param autoClose: Ϊtrueʱ�Զ��ر����ӣ���Ҳ��ȱʡ��ʽ
  */
 public void setAutoClose(boolean autoClose)  {
  preDbObj.setAutoClose(autoClose);
 }
  public void finalize() {
    /**@todo: Override this java.lang.Object method*/
  }

        /**
         * ����һ����¼ added by wujian
         * @return boolean �����ɹ����� TRUE�����򷵻� FALSE
         * @param String table ����
         * @param Map recordMap �ֶ����Ƽ�ȡֵ
         */
        public boolean insert(String table,
                              Map recordMap) throws SQLException, Exception
        {
            if(m_conn==null) connect();
            Statement statement = m_conn.createStatement();
            String field="", value="", mfields="", mvalues="";

            Iterator itf = recordMap.keySet().iterator();
            while(itf.hasNext()) {
                field = (String)itf.next();
                value = (String)recordMap.get(field);
                value = value==null ? "":value;

                mfields += field + ",";
                mvalues += "'" + value + "',";
            }
            mfields = mfields.substring(0, mfields.length() - 1);
            mvalues = mvalues.substring(0, mvalues.length() - 1);

            String mySql = "INSERT INTO " + table + "("
                          + mfields + ") VALUES(" + mvalues + ")";

            System.out.println(mySql);
            statement.execute(mySql);
            return true;
        }

  /**
   * ���ղ�ͬ�����ݿ�����ת�����ݿ���ú��������ڲ�ͬ���ݿ��������ͬ���ܵĺ��������������ƺͷ�����ͬ�����������������ת����
   * ������ֻ������CERPϵͳ
   * @param funcExp Ҫת���ĺ������ʽ
   * @return  �ɹ�ʱ����ת����ĺ������ʽ�����򷵻�null
   */
  public String convertDbFunc(String funcExp)  {
    m_errMsg="";
  try {
    return funcExp;
  }catch (Exception e)  {
    e.printStackTrace();
    m_errMsg = "convertDbFunc: " + e;
    return null;
  }
  }

  /**
   * ���ղ�ͬ�����ݿ�����ת�����ݿ���ú��������ڲ�ͬ���ݿ��������ͬ���ܵĺ��������������ƺͷ�����ͬ�����������������ת����
   * ������ֻ������CERPϵͳ
   * @param funcExp Ҫת���ĺ������ʽ
   * @param dbName  ���ݿ�������������JdbOp�����DBNAME������һ����
   * @return
   */
  public static String convertDbFunc(String funcExp,int dbName)  {
  try {
    return funcExp;
  }catch (Exception e)  {
    e.printStackTrace();
    return null;
  }
  }

        /**
         * ɾ��һ����¼
         * @return boolean �����ɹ����� TRUE�����򷵻� FALSE
         * @param String table ����
         * @param String criteria ɸѡ����
         */
        public boolean deleteBatch(String table, String criteria)
        {m_errMsg = "";
                try {
                        if(m_conn==null) connect();
                        Statement statement = m_conn.createStatement();
                        addBatchSql("DELETE FROM " + table + " WHERE " + criteria);

                        return true;
                }
                catch(Exception e) {
//                        disConnect();
                        System.out.println(e.toString());
                        return false;
                }
        }

        /**
         * ����һ����¼ added by wujian
         * @return boolean �����ɹ����� TRUE�����򷵻� FALSE
         * @param String table ����
         * @param Map recordMap �ֶ����Ƽ�ȡֵ
         */
        public boolean insertBatch(String table,
                              Map recordMap) throws SQLException, Exception
        {
            if(m_conn==null) connect();
            Statement statement = m_conn.createStatement();
            String field="", value="", mfields="", mvalues="";

            Iterator itf = recordMap.keySet().iterator();
            while(itf.hasNext()) {
                field = (String)itf.next();
                value = (String)recordMap.get(field);
                value = value==null ? "":value;

                mfields += field + ",";
                mvalues += "'" + value + "',";
            }
            mfields = mfields.substring(0, mfields.length() - 1);
            mvalues = mvalues.substring(0, mvalues.length() - 1);

            String mySql = "INSERT INTO " + table + "("
                          + mfields + ") VALUES(" + mvalues + ")";

//            System.out.println(mySql);
            addBatchSql(mySql);
            return true;
        }


}//end of class