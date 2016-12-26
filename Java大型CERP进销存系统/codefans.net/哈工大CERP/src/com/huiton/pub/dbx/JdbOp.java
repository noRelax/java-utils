/**
 * 数据库基本操作组件
 * 封装了记录的增加、修改、删除等功能
 * 数据库连接功能由其他组件完成（cn.com.huiton.ConnectionPool.ConnectionPool）
 *
 * 使用步骤：
 * 1、创建对象；
 * 2、使用 connect() 方法与数据库建立连接；
 * 3、进行数据库操作；
 * 4、使用 disConnect() 方法与数据库断开连接；
 * 5、销毁对象。
 *
 * @@author=吴剑、王涛
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
        public JdbObj preDbObj = null;    //预先生成的数据库操作类
        public String m_errMsg = "";
        public String sessionCode = null;   //当前会话代码,由数据库生成
        public Statement m_statement = null;
        Vector stVector = new Vector();     //保存新创立的会话，在finalize中释放它
        boolean autoCommit = true;
        String cfgFile = "";
        boolean connectDirect = false;;    //直接连接标志，当使用公司、年、子系统方式直接连库时设置为true

        public static final int DBNAME_SQLSERVER = 0;
        public static final int DBNAME_ORACLE = 0;
        public static final int DBNAME_SYBASE = 0;


        public JdbOp() throws Exception{
          /*connect();*/
          createConnect();  //以缺省参数初始化数据库对象，wt
        }

        /**
         * Description: 构造函数。使用sessionCode获取Cerp数据库连接
         * @param sessionCode：系统登陆时创建的会话唯一标识
         * @param sysCode: 要联接的系统代码。如果为空，返回"sam"库
         */
         public JdbOp(String sessionCode,String sysCode) throws Exception{
          if (!createConnect(sessionCode,sysCode))
            throw (new Exception(m_errMsg));
         }

        /**
         * Description: 构造函数。使用sessionCode获取Cerp数据库连接，本函数可以制定一个数据库连接的配置文件
         * @param sessionCode：系统登陆时创建的会话唯一标识
         * @param sysCode: 要联接的系统代码。如果为空，返回"sam"库
         * @param cfgFile: 配置文件名
         */
         public JdbOp(String sessionCode,String sysCode, String cfgFile) throws Exception{
          if (cfgFile!=null && !cfgFile.equalsIgnoreCase(""))
            this.cfgFile = cfgFile;
          if (!createConnect(sessionCode,sysCode))
            throw (new Exception(m_errMsg));
         }

         /**
          * 按照给定的参数，直接连接数据库。本方法只用于Cerp
          * @param companyCode  公司代号
          * @param year 年号
          * @param sysCode  子系统代号
          * @param isDirect 保留参数，应总是设置为true
          * @throws Exception 抛出任何错误
          */
         public JdbOp(String companyCode, String year, String sysCode,boolean isDirect) throws Exception {
          if (!createConnect(companyCode,year,sysCode))
            throw new Exception("创建数据库联接出错: " + this.m_errMsg);
          connectDirect = true;
         }

        /**
         * 插入一条记录
         * @return boolean 操作成功返回 TRUE，否则返回 FALSE
         * @param String table 表名
         * @param String[] fields 字段名称数组
         * @param String[] types 字段类型数组，对于char, varchar类型，对应值是string，其它类型可为任意值
         * @param String[] values 字段明对应的值的数组
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
         * 更新一条记录
         * @return boolean 操作成功返回 TRUE，否则返回 FALSE
         * @param String criteria 筛选条件
         * @param String table 表名
         * @param String[] fields 字段名称数组
         * @param String[] types 字段类型数组，对于char, varchar类型，对应值是string，其它类型可为任意值
         * @param String[] values 字段明对应的值的数组
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
         * 删除一条记录
         * @return boolean 操作成功返回 TRUE，否则返回 FALSE
         * @param String table 表名
         * @param String criteria 筛选条件
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
         * void connect()		与数据库建立连接
         *
         * 返回值：				void
         *
         * 参数：
         * modify by wt: 2001.7.12. remove 连接池
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
         * void disConnect()	断开数据库连接
         *
         * 返回值：				void
         *
         * 参数：
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
   *Title: 创建表
   *Description 本方法根据给出的参数创建数据表。使用本类中已经创建的连接conn
   *@param tblName 要创建的表名
   *@param FieldDef[] 表的字段定义
   *@return 表创建成功或表已经存在，返回true。否则返回false
   */
  public boolean createTbl(String tblName, FieldDef[] fields) {
     m_errMsg = "";
     String mySql = "Create table " + tblName + " (";

try{
    if (preDbObj == null) {//连接还没有建立，以缺省方式建立之
      if (!createConnect()){
        m_errMsg = preDbObj.m_errMsg;
        return false;
      }
    }

    //分析建表数据
    if (tblName==null || tblName.length()==0) {
      m_errMsg = "要创建的表名非法：" + tblName;
      return false;
    }

    if (preDbObj.hasTable(tblName))
      return true;  //已经有该表，直接返回

    if (fields.length==0) {
      m_errMsg = "没有字段定义";
      return false;
    }

    //创建表
    int fCounter;
    m_errMsg = "";
    String keyStr = "";   //键值
    //循环添加要创建的字段
    for (fCounter = 0; fCounter < fields.length; fCounter++){
      if (fCounter > 0)
        mySql += ",";     //拼下一句前加分隔符
      mySql += fields[fCounter].colName + " ";
      mySql += fields[fCounter].colType;
      if (fields[fCounter].colDft!=null &&  fields[fCounter].colDft.length() > 0)
        mySql += " default " + fields[fCounter].colDft;         //有缺省值
      else if (!fields[fCounter].nullable)    //不允许为空
        mySql += " not null";
      //添加键值
      if (fields[fCounter].isPriKey) {
        if (keyStr.length() == 0)
          keyStr = ", primary key(" + fields[fCounter].colName;
        else
          keyStr += "," + fields[fCounter].colName;
      }
    }
    //完成语句
    if (keyStr.length() > 0)
      {//有键值定义
       keyStr += ")";
       mySql += keyStr + ")";
      }

    //执行语句
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
   * Title: 创建索引
   * Description: 本功能在当前连接的数据库中创建表的索引
   * @param idxName 索引名
   * @param tblName 表名
   * @param isUnique 是否为唯一索引
   * @param fields 索引字段定义
   * @return 索引创建成功或索引已经存在，返回true。否则返回false
   */
  public boolean createIndex(String idxName, String tblName, boolean isUnique, IdxField[] fields) {
    m_errMsg = "";

    String mySql = "Create ";   //创建索引语句
try{
    if (preDbObj == null) {//连接还没有建立，以缺省方式建立之
      if (!createConnect()){
        m_errMsg = preDbObj.m_errMsg;
        return false;
      }
      return false;
    }

    //检查参数是否有效
    if (idxName.length() == 0)  {
      m_errMsg = "空的索引名";
      return false;
    }
    if (tblName.length() == 0)  {
      m_errMsg = "要建索引的表名无效：" + tblName;
      return false;
    }

    if (preDbObj.hasIndex(tblName,idxName))
      return true;

    if (fields.length == 0) {
      m_errMsg = "没有索引字段定义";
      return false;
    }

    //创建索引
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
        //降序
        mySql += " Desc";
      }

    }
    mySql += ")";

    //创建索引
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
   * Title: 构造函数
   * Description: 本函数按照数据库类型和URL初始化数据库
   * @Param: type 数据库类型。可用常量在DbType类中定义
   * @param: url 要连接的数据库。可用常量在DbUrl中定义
   */
  public JdbOp(int type, int url) throws Exception {
    if (!createConnect(type, url))
      throw new Exception("无法建立数据库连接");
  }
  /**
    * 构造函数
    * @param Connection conn 数据库连接
   */
    public JdbOp(Connection conn) throws Exception{
        m_conn = conn;
        m_statement = m_conn.createStatement();
    }

  /**
   * @Title: 建立数据库连接对象
   * @Description: 本函数按照数据库类型和URL初始化数据库
   * @Param: type 数据库类型。可用常量在DbType类中定义
   * @param: url 要连接的数据库。可用常量在DbUrl中定义
   */
  boolean createConnect(int type, int url) throws Exception {
    if (preDbObj != null)
      preDbObj.finalize();  //已经存在对象,消除之
    preDbObj = new JdbObj(type,url);    //创建数据连接对象
    if (preDbObj.active(DbConstStr.DEVPASSWORD))  //激活并建立数据库连接
      m_conn = preDbObj.conn;
    else
      {m_errMsg = preDbObj.m_errMsg;
       return false;
      }
    m_statement = m_conn.createStatement();
    return true;
  }

  /**
   * @Title: 建立数据库连接对象
   * @Description: 本函数以缺省方式建立数据库连接对象
   */
  boolean createConnect() throws Exception {
    if (preDbObj != null)
      preDbObj.finalize();  //已经存在对象,消除之
    preDbObj = new JdbObj();    //创建数据连接对象

    if (preDbObj.active(DbConstStr.DEVPASSWORD))  //激活并建立数据库连接
      m_conn = preDbObj.conn;
    else
      {m_errMsg = preDbObj.m_errMsg;
       return false;
      }

    m_statement = m_conn.createStatement();
   return true;
  }

  /**
   * 根据给出的参数创建数据库联接对象。本函数应和JdbOp()构造函数连用，只适用于cerp系统
   * @param companyCode 要联接的公司号
   * @param year  要联接的年号
   * @param sysCode 要联接的子系统号
   * @return  成功时返回true，否则返回false
   * @throws Exception  抛出所有例外
   */
  public boolean createConnect(String companyCode,String year,String sysCode) throws Exception {
    if (preDbObj != null)
      preDbObj.finalize();  //已经存在对象,消除之
    preDbObj = new JdbObj();    //创建数据连接对象
    if (preDbObj.active(DbConstStr.DEVPASSWORD,companyCode,year,sysCode))  //激活并建立数据库连接
      m_conn = preDbObj.conn;
    else
      {m_errMsg = preDbObj.m_errMsg;
       return false;
      }

    m_statement = m_conn.createStatement();
   return true;
  }

  /**
   * @Title: 建立数据库连接对象
   * @Description: 本函数按照用户的请求方式建立数据库连接对象。这是cerp9的连接方式
   * @param sessionCode: 会话代码
   * @param sysCode: 请求的系统代码
   */
  protected boolean createConnect(String sessionCode, String sysCode) {
  try {
    if (preDbObj != null)
      preDbObj.finalize();  //已经存在对象,消除之
    if (!cfgFile.equalsIgnoreCase(""))
      preDbObj = new JdbObj(sessionCode,sysCode,cfgFile);    //创建数据连接对象
    else
      preDbObj = new JdbObj(sessionCode,sysCode);    //创建数据连接对象

    if (preDbObj.active(DbConstStr.DEVPASSWORD))  //激活并建立数据库连接
      m_conn = preDbObj.conn;
    else
      {m_errMsg = preDbObj.m_errMsg;
       return false;
      }
    m_statement = m_conn.createStatement(preDbObj.m_scrollType,preDbObj.m_updateType);
//    m_statement = m_conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    if (m_statement == null)
      throw (new Exception("创建会话句并失败"));

   this.sessionCode = sessionCode;
   return true;
  }catch (Exception e)  {
    m_errMsg = "JdbOp:createConnect: " + e;
    return false;
  }

  }

  /**
   * 设置返回的结果集类型。该设置生效后，当前的会话句并将被关闭
   * @param scrollType: 结果集光标滚动类型
   * @param updateType: 结果集更新模式
   */
   public void setRstType(int scrollType, int updateType) throws Exception{
    m_errMsg = "";
    if (m_statement != null)
      m_statement.close();
    m_statement = m_conn.createStatement(scrollType,updateType);

   }

  /**
   * 生成结果集
   * Description: 如果多次调用本功能将只能保存当前结果集
   * @param mySql: 用于产生结果集的语句
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
   * 生成结果集
   * Description: 本方法使用新的会话返回结果集。
   * @param mySql: 用于产生结果集的语句
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
   * 执行Sql语句
   * @param mySql: 要执行的Sql语句
   */
  public boolean exeSql(String mySql) {
    m_errMsg = "";
try{
    Statement st = m_conn.createStatement();
    st.execute(mySql);
    st.close();
    return true;
}catch (Exception e)  {
  m_errMsg =this.getClass().getName() + ".exeSql(): " + e + "语句如下:\n" + mySql;
  System.out.println(m_errMsg);
  return false;
}
  }

  /**
   * 执行Sql语句，该语句使用新的会话，因而不破坏已经返回的结果集
   * @param mySql: 要执行的Sql语句
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
   * 将结果集转换为Vector向量，向量由一维字符串数组组成。第零个数组为的字段名
   * @param Rst:要转换的结果集
   * @return 成功是返回向量，否则返回null;
   */
  public Vector rst2Vector(ResultSet rst) throws Exception {
  try {
    Vector vRst = new Vector();

     //获取表头信息
     ResultSetMetaData rstm = rst.getMetaData();
//System.out.println("getmeta");
     int colcount = rstm.getColumnCount(), i;
     String colname[] = new String[colcount];
     //形成字段名数组
     for (i=1; i<=colcount; i++)
       colname[i-1] = rstm.getColumnName(i);    //字段名从1开始，数组应从0开始
     vRst.addElement(colname);    //字段名数组位于0位置
//System.out.println("cr field");

     //形成记录集,从矢量的索引1开始
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
   * 将结果集转换为Vector向量，向量由一维字符串数组组成。通过参数可以选择生成或不生成字段名头。
   * @param rst 要转换的结果集
   * @param hasTitle
   * @return 成功时返回向量，否则返回null;
   * @throws Exception 抛出所有错误
   */
  public Vector rst2Vector(ResultSet rst, boolean hasTitle) throws Exception {
    Vector vc = rst2Vector(rst);
    if (!hasTitle)
      vc.remove(0);
    return vc;
  }

  /**
   * 将结果集转换为Vector向量，向量由HashMap对象组成。每个HashMap对象由字段名作为键值，字段值字符串作为值
   * @param rst 要转换的结果集
   * @return 成功时返回向量，否则返回null;
   * @throws Exception 抛出所有错误
   */
  public Vector rst2HashVector(ResultSet rst) {
  try {
    Vector vRst = new Vector();

     //获取表头信息
     ResultSetMetaData rstm = rst.getMetaData();
//System.out.println("getmeta");
     int colcount = rstm.getColumnCount(), i;
     String colname[] = new String[colcount];
     //形成字段名数组
     for (i=1; i<=colcount; i++)
       colname[i-1] = rstm.getColumnName(i);    //字段名从1开始，数组应从0开始
//System.out.println("cr field");

     //形成记录集,从矢量的索引1开始
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
   * 返回当前纪录的哈希映射。
   * @param rst 包括当前纪录的结果集。
   * @return  成功时返回当前纪录的哈希映射，如果结果集的光标在第一条记录之前，自返回第一条纪录。失败时返回null。
   */
  public HashMap recMap(ResultSet rst)  {
  try {
    if (rst.isBeforeFirst())
      rst.next();
     //获取表头信息
     ResultSetMetaData rstm = rst.getMetaData();
     int colcount = rstm.getColumnCount(), i;
     String colname[] = new String[colcount];
     //形成字段名数组
     for (i=1; i<=colcount; i++)
       colname[i-1] = rstm.getColumnName(i);    //字段名从1开始，数组应从0开始

     //形成记录集,从矢量的索引1开始
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
   * 返回与结果集相关的字段定义
   * @param rst: 要抽取定义的结果集
   * @return 返回的向量定义包含的数据均为FieldDef类型，只有字段名、字段类型、和字段长度被返回，失败时返回空
   */
  public Vector getFieldsDef(ResultSet rst)  {
       m_errMsg = "";
  try {
    Vector vFields = new Vector();
     //获取表头信息
     ResultSetMetaData rstm = rst.getMetaData();
//System.out.println("getmeta");
     int colcount = rstm.getColumnCount(), i;
     //形成字段名数组
     for (i=1; i<=colcount; i++)  {
        FieldDefn fd = new FieldDefn();
        fd.colName = rstm.getColumnName(i); //字段名从1开始
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
   * 添加批处理语句，本函数与getData使用相同的会话句柄
   * @param sqlStr: 要添加的语句
   * @return 成功時返回true, 否则返回false
   */
  public boolean addBatchSql(String sqlStr)  {
try {
    if (autoCommit)  {
      //设置事务开始，在exeBatch中激发事务，并关闭自动执行
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
   * 执行批处理程序
   * @return 成功时返回每一条语句的的状态值数组，否则返回null
   */
  public int [] exeBatchSql() throws Exception{
try{
    int[] ret;
    ret = m_statement.executeBatch();
    autoCommit = true;  //开启自动执行
    m_conn.setAutoCommit(true);
    m_conn.commit();
    return ret;

}catch (Exception e)  {
    m_errMsg =this.getClass().getName() + ".exeBatchSql(): " + e;
    m_errMsg += "\n事务被回滚.";
    m_conn.rollback();
    autoCommit = true;  //开启自动执行
    m_conn.setAutoCommit(true);
    System.out.println(m_errMsg);
    return null;
}
  }

  /**
   * 修改一条纪录，该方法可以用来更新和删除纪录
   * @param  String sql 进行操作的sql语句
   * @return boolean 操作成功返回 TRUE，否则返回 FALSE
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
   * 获取错误信息
   */
  public String getErrMsg() {
    return m_errMsg;
  }

  /**
   * 创建数据库
   * @param dbName: 要建的数据库名
   * @return 如果创建成功，则返回true并将当前数据库连接指向新的数据库，否则返回false
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
  * 设置数据库对象的自动销毁方式
  * @param autoClose: 为true时自动关闭联接，这也是缺省方式
  */
 public void setAutoClose(boolean autoClose)  {
  preDbObj.setAutoClose(autoClose);
 }
  public void finalize() {
    /**@todo: Override this java.lang.Object method*/
  }

        /**
         * 插入一条记录 added by wujian
         * @return boolean 操作成功返回 TRUE，否则返回 FALSE
         * @param String table 表名
         * @param Map recordMap 字段名称及取值
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
   * 按照不同的数据库类型转换数据库调用函数。由于不同数据库可能有相同功能的函数，但调用名称和方法不同，本函数将对其进行转换。
   * 本函数只能用于CERP系统
   * @param funcExp 要转换的函数表达式
   * @return  成功时返回转换后的函数表达式，否则返回null
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
   * 按照不同的数据库类型转换数据库调用函数。由于不同数据库可能有相同功能的函数，但调用名称和方法不同，本函数将对其进行转换。
   * 本函数只能用于CERP系统
   * @param funcExp 要转换的函数表达式
   * @param dbName  数据库名，它必须是JdbOp定义的DBNAME常数的一部分
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
         * 删除一条记录
         * @return boolean 操作成功返回 TRUE，否则返回 FALSE
         * @param String table 表名
         * @param String criteria 筛选条件
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
         * 插入一条记录 added by wujian
         * @return boolean 操作成功返回 TRUE，否则返回 FALSE
         * @param String table 表名
         * @param Map recordMap 字段名称及取值
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