package com.huiton.pub.dbx;

import java.sql.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import com.huiton.pub.tools.*;
import com.huiton.pub.lan_tools.*;

/**
 * Title:    数据库对象类，
 * Description:  对象生成后自动建立连接对象和会话对象，它封装了数据库对象的建立过程，
 * 其它的数据库操作应该继承本类
 * Copyright:    Copyright (c) 2001
 * Company: 利玛信息技术有限公司
 * @author：王涛
 * @version 1.0
 */
public class JdbObj {

  /**
   * @const: driver 驱动程序名
   * @const: url 数据源名
   * @const: userName 用户名
   * @const: password 用户口令
   * @const: conn 连接对象
   * @const:
   * @const:
   */
  public String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
  public String url = "jdbc:odbc:camps";
  public String userName = "huiton";
  public String password = "cerp";
  public String database = "";
  public Connection conn = null;
  public String m_errMsg = "没有错误信息";
  String m_sessionCode = null;
  String m_sysCode = null;
  String cfgFile = "btcDatabase.cfg";      //缺省配置文件名

  boolean login = false;

  public int m_scrollType = ResultSet.TYPE_SCROLL_SENSITIVE;     //缺省结果集油标类型
  public int m_updateType = ResultSet.CONCUR_READ_ONLY;
  private boolean autoClose = true;      //对象销毁时自动关闭联接

/**
 * title: 缺省构造函数，
 * Description: 从配置文件"btcDatabase.cfg"中读取数据库配置，配置文件必须放置在当前系统的工作目录下
 * 其缺省设置如下：
 * 驱动：ODBC/JDBC
 * 用户名:huiton,
 * 口令：cerp，
 * 数据源：cerp
*/
  public JdbObj() throws Exception {
    readCfg();
  }

  /**
   * 构造函数
   * Description 本函数利用登陆的用户代号在SAM（系统管理)库中获取连库所需要的参数，并返回相应连接
   * @param sessionCode: 登陆用户的代号
   * @param sysCode：登陆要访问的系统名称。一个系统可能会对应不同的数据库。如果sysCode为空或等于"sam"则返回系统管理库
   */
  public JdbObj(String sessionCode,String sysCode) throws Exception {
    m_sessionCode = sessionCode;
    m_sysCode = sysCode;
    readCfg();
  }

  /**
   * 构造函数
   * Description 本函数利用登陆的用户代号在SAM（系统管理)库中获取连库所需要的参数，并返回相应连接。本函数可以制定一个新的数据库连接配置文件
   * @param sessionCode: 登陆用户的代号
   * @param sysCode：登陆要访问的系统名称。一个系统可能会对应不同的数据库。如果sysCode为空或等于"sam"则返回系统管理库
   *
   */
  public JdbObj(String sessionCode,String sysCode,String cfgFile) throws Exception {
    //确认配置文件名有效
    if (cfgFile==null || cfgFile=="")
      cfgFile = "btcDatabase.cfg";
    this.cfgFile = cfgFile;
    m_sessionCode = sessionCode;
    m_sysCode = sysCode;
    readCfg();
  }

  /**
   * title: 带参构造函数
   * @param dbType 数据库类型。如"Sql"，"Oracle"等。该常量在DbType中定义
   * @param dbUrl 数据源。根据不同的数据源，可打开不同的库。该常量在DbUrl中定义
   */
  public JdbObj(int dbType,int dbUrl) throws Exception {

    switch (dbType) { //设置数据库启动程序
      case DbType.JDBC_ODBC:    //jdbc_odbc桥
        driver = "sun.jdbc.odbc.JdbcOdbcDriver";
        switch (dbUrl) {
          case DbUrl.CERP:
            url = "jdbc:odbc:camps";
            break;
          default:
            m_errMsg = "不可识别的url, 请使用DbUrl提供的常数";
        }
        break;
      case DbType.SQL:    //SQL Server驱动程序
        driver = "com.inet.tds.TdsDriver";
        switch (dbUrl) {
          case DbUrl.CERP:
            url = "jdbc:inetdae:127.0.0.1:1433?database=camps9&charset=GBK";
            break;
          case DbUrl.MASTER:
            url = "jdbc:inetdae:127.0.0.1:1433?database=master&charset=GBK";
            userName = "sa";
            password = "";
            break;
          default:
            m_errMsg = "不可识别的url, 请使用DbUrl提供的常数";
        }
        break;
      default:
        m_errMsg = "不可使用的数据库类型, 请使用DbType提供的常数";
    }

  }

  /**
   * 析构函数
   */
   public void finalize () throws Exception {
    if (this.autoClose)
      conn.close();    //关闭数据库连接
   }

  /**
   * 激活数据库实体。只有在激活实体后才可以进行其它操作。
   * @param devPassword 开发人员口令
   */
  public boolean active (String devPassword) {
    m_errMsg = "";
  try{
//System.out.println("enter active");
    if (devPassword.compareToIgnoreCase(DbConstStr.DEVPASSWORD) != 0)
      {m_errMsg = "口令错误";
       login = false;
       return false;
      }
/*
System.out.println(driver);
System.out.println(url);
System.out.println(userName);
System.out.println(password);
*/
   //创建与SAM库的连接
   Class.forName(driver);//驱动
   Connection samConn = DriverManager.getConnection(url, userName, password);
   if (!database.equals(""))
    samConn.setCatalog(database);
   if (m_sessionCode == null || m_sysCode == null || m_sysCode.compareToIgnoreCase("sam")==0 ||
       m_sessionCode == "" || m_sysCode == "")  {
      conn = samConn; //返回系统库
      return true;
   }
   //获取定位数据库的辅助信息
    Statement st = samConn.createStatement(m_scrollType,m_updateType);
    ResultSet rst = st.executeQuery("select company_code,year from sam_session where session_code = " + LanTools.toSqlString(m_sessionCode));
    if (rst == null)  {
      m_errMsg = "系统表存取错误：sam";
      samConn.close();
      return false;
    }
    if (!rst.next())
      {m_errMsg = "会话已经过期: " + m_sessionCode;
       samConn.close();
       return false;
      }
    //session有效，获取公司号和年份
    String company = rst.getString("company_code");
    String year = rst.getString("year");
    //获得请求的数据库连接
    String mySql = "select driver_manager,db_url,db_user,db_pass from scg_db_config where company_code = ";
    mySql += LanTools.toSqlString(company);
    mySql += " and sys_code = " + LanTools.toSqlString(m_sysCode);
    mySql += " and year = " + LanTools.toSqlString(year);
    rst = st.executeQuery(mySql);
    if (rst==null || !rst.next())  {
      m_errMsg = "数据库定义不存在：代码：" + m_sysCode + " 公司：" + company + " 年份：" + year;
      samConn.close();
     return false;
    }

    driver = rst.getString("driver_manager");
    url = rst.getString("db_url");
    userName = rst.getString("db_user");
    password = cerp_crypt.get_decoded_pass(rst.getString("db_pass"));
    samConn.close();
   Class.forName(driver);//驱动
   conn = DriverManager.getConnection(url, userName, password);

   login = true;

   return true;
  }catch (Exception e)  {
    m_errMsg = "连接数据库错误(" + url + "," + userName + "," + password + ")：" + e;
    e.printStackTrace();
  }
    return false;
  }

  /**
   * description 查询当前数据库中是否已经存在某个表
   * @param tblName 表名
   * return 表存在时返回true，否则返回false
   */
  public boolean hasTable(String tblName) throws Exception {
  try {
    DatabaseMetaData dbMeta = conn.getMetaData();
    ResultSet rstMeta = dbMeta.getTables(null,null,null,null);
    while (rstMeta.next())  {
//      System.out.println(rstMeta.getString("TABLE_NAME"));
     if (rstMeta.getString("TABLE_NAME").compareToIgnoreCase(tblName) == 0)
        return true;
    }
}catch (Exception e) {
  m_errMsg = e.getMessage();
}
    return false;
  }

  /**
   * description 查询当前数据库中是否已经存在某个库
   * @param dbName 表名
   * return 表存在时返回true，否则返回false
   */
  public boolean hasCatalog(String dbName) throws Exception {
  try {
    DatabaseMetaData dbMeta = conn.getMetaData();
    ResultSet rstMeta = dbMeta.getCatalogs();
    while (rstMeta.next())  {
//      System.out.println(rstMeta.getString("TABLE_NAME"));
     if (rstMeta.getString("TABLE_CAT").compareToIgnoreCase(dbName) == 0)
        return true;
    }
}catch (Exception e) {
  m_errMsg = e.getMessage();
}
    return false;
  }

  /**
   * description 查找是否有指定的索引
   * @param: idxName 要查找的索引名
   * @param: tblName 索引所在的表名
   */
  public boolean hasIndex(String tblName,String idxName) {
  try {
    DatabaseMetaData dbMeta = conn.getMetaData();
    ResultSet rstMeta = dbMeta.getIndexInfo(null,null,tblName,false,true);

//System.out.println(idxName+"\n");
    while (rstMeta.next())  {
      String iname = rstMeta.getString("INDEX_NAME");
      if (iname == null)
        continue;
//System.out.print(iname + "\t");
      if (iname.compareToIgnoreCase(idxName)== 0)
        return true;
//System.out.println("next");
    }
}catch (Exception e)  {
  m_errMsg = e.getMessage();
}
    return false;
  }

  /**
   * 用当前连接获得表的字段定义
   * @param tblName 要获得的表名
   * @return  成功时返回由FieldDefn组成的向量组，否则返回null
   */
  public Vector getTableFields(String tblName)
  {
  try
  {
    System.out.println("JdbObj: getTableFields");
    ResultSet rstMeta = conn.getMetaData().getColumns(null,null,tblName,null);
    if (rstMeta!= null)
    {
      Vector vFields = new Vector();
      FieldDefn fd;
      while (rstMeta.next())
      {
        fd = new FieldDefn();
        fd.colName = rstMeta.getString("COLUMN_NAME");
        fd.colType = rstMeta.getInt("DATA_TYPE");
        fd.colLen = rstMeta.getInt("COLUMN_SIZE");
        fd.nullable = rstMeta.getBoolean("NULLABLE");
        fd.colDft = rstMeta.getString("COLUMN_DEF");
        vFields.add(fd);
      }
      return vFields;
    }
    else
      throw new Exception("没有表的信息：" + tblName);
  }
  catch (Exception e)
  {
    e.printStackTrace();
    m_errMsg = e.getMessage();
    return null;
  }

  }

  /**
   * Title readCfg
   * Description 从配置文件中读出用于到开系统配置库的参数
   * return 如果配置文件存在，返回true，否则返回false并设置缺省值
   */
  boolean readCfg() {
  try {
//  System.out.print(System.getProperty("user.dir"));
//    FileInputStream cfgFile = new FileInputStream("btcDatabase.cfg");
    FileInputStream cfgFile = new FileInputStream(this.cfgFile);

    //读入配置参数
    Properties proObj = new Properties();
    proObj.load(cfgFile);
    driver = proObj.getProperty("Driver","sun.jdbc.odbc.JdbcOdbcDriver");
    url = proObj.getProperty("UrlSam","jdbc:odbc:camps");
    userName = proObj.getProperty("UserName","huiton");
    password = proObj.getProperty("Password","cerp");
    database = proObj.getProperty("Database","");

    //解密口令
//    if (this.cfgFile.equalsIgnoreCase("btcDatabase.cfg"))
      password = cerp_crypt.get_decoded_pass(password);
    return true;

   }catch (Exception e)
   {//设置缺省值
    driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    url = "jdbc:odbc:camps";
    userName = "huiton";
    password = "cerp";

    m_errMsg = e.getMessage();
    return false;
   }
  }

  /**
   * 设置对象取消时是否关闭联接
   * @param autoClose：为true时自动关闭联接，否则保留联接。缺省时为true
   */
  public void setAutoClose(boolean autoClose) {
    this.autoClose = autoClose;
  }

  /**
   * 本构造函数只适用于Cerp系统。它使用其参数直接联接相应的数据库，而不使用session。本函数应该和JdbObj()构造函数联合使用。
   * 激活数据库实体。只有在激活实体后才可以进行其它操作。
   * @param devPassword 开发人员口令
   * @param companyCode   要联接的公司代号
   * @param year  要联接的年份
   * @param sysCode 要联接的子系统代号
   * @return 数据库联接成功是返回true，否则返回false
   */
  public boolean active (String devPassword,String companyCode,String year,String sysCode) {
    m_errMsg = "";
  try{
//System.out.println("enter active");
    if (devPassword.compareToIgnoreCase(DbConstStr.DEVPASSWORD) != 0)
      {m_errMsg = "口令错误";
       login = false;
       return false;
      }
    m_sessionCode = "Direct";     //直接联接
    m_sysCode = sysCode;

   //创建与SAM库的连接
   Class.forName(driver);//驱动
   Connection samConn = DriverManager.getConnection(url, userName, password);
   if (!database.equals(""))
    samConn.setCatalog(database);
   if (m_sessionCode == null || m_sysCode == null || m_sysCode.compareToIgnoreCase("sam")==0 ||
       m_sessionCode == "" || m_sysCode == "")  {
      conn = samConn; //返回系统库
      return true;
   }
    //获得请求的数据库连接
    Statement st = samConn.createStatement();
    String mySql = "select driver_manager,db_url,db_user,db_pass from scg_db_config where company_code = ";
    mySql += LanTools.toSqlString(companyCode);
    mySql += " and sys_code = " + LanTools.toSqlString(m_sysCode);
    mySql += " and year = " + LanTools.toSqlString(year);
    ResultSet rst = st.executeQuery(mySql);
    if (rst==null || !rst.next())  {
      m_errMsg = "数据库定义不存在：代码：" + m_sysCode + " 公司：" + companyCode + " 年份：" + year;
      samConn.close();
     return false;
    }

    driver = rst.getString("driver_manager");
    url = rst.getString("db_url");
    userName = rst.getString("db_user");
    password = cerp_crypt.get_decoded_pass(rst.getString("db_pass"));
    samConn.close();
   Class.forName(driver);//驱动
   conn = DriverManager.getConnection(url, userName, password);

   login = true;

   return true;
  }catch (Exception e)  {
    m_errMsg = "连接数据库错误(" + url + "," + userName + "," + password + ")：" + e;
    e.printStackTrace();
  }
    return false;
  }

}