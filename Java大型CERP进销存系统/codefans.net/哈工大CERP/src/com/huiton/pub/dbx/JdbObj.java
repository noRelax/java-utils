package com.huiton.pub.dbx;

import java.sql.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import com.huiton.pub.tools.*;
import com.huiton.pub.lan_tools.*;

/**
 * Title:    ���ݿ�����࣬
 * Description:  �������ɺ��Զ��������Ӷ���ͻỰ��������װ�����ݿ����Ľ������̣�
 * ���������ݿ����Ӧ�ü̳б���
 * Copyright:    Copyright (c) 2001
 * Company: ������Ϣ�������޹�˾
 * @author������
 * @version 1.0
 */
public class JdbObj {

  /**
   * @const: driver ����������
   * @const: url ����Դ��
   * @const: userName �û���
   * @const: password �û�����
   * @const: conn ���Ӷ���
   * @const:
   * @const:
   */
  public String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
  public String url = "jdbc:odbc:camps";
  public String userName = "huiton";
  public String password = "cerp";
  public String database = "";
  public Connection conn = null;
  public String m_errMsg = "û�д�����Ϣ";
  String m_sessionCode = null;
  String m_sysCode = null;
  String cfgFile = "btcDatabase.cfg";      //ȱʡ�����ļ���

  boolean login = false;

  public int m_scrollType = ResultSet.TYPE_SCROLL_SENSITIVE;     //ȱʡ������ͱ�����
  public int m_updateType = ResultSet.CONCUR_READ_ONLY;
  private boolean autoClose = true;      //��������ʱ�Զ��ر�����

/**
 * title: ȱʡ���캯����
 * Description: �������ļ�"btcDatabase.cfg"�ж�ȡ���ݿ����ã������ļ���������ڵ�ǰϵͳ�Ĺ���Ŀ¼��
 * ��ȱʡ�������£�
 * ������ODBC/JDBC
 * �û���:huiton,
 * ���cerp��
 * ����Դ��cerp
*/
  public JdbObj() throws Exception {
    readCfg();
  }

  /**
   * ���캯��
   * Description ���������õ�½���û�������SAM��ϵͳ����)���л�ȡ��������Ҫ�Ĳ�������������Ӧ����
   * @param sessionCode: ��½�û��Ĵ���
   * @param sysCode����½Ҫ���ʵ�ϵͳ���ơ�һ��ϵͳ���ܻ��Ӧ��ͬ�����ݿ⡣���sysCodeΪ�ջ����"sam"�򷵻�ϵͳ�����
   */
  public JdbObj(String sessionCode,String sysCode) throws Exception {
    m_sessionCode = sessionCode;
    m_sysCode = sysCode;
    readCfg();
  }

  /**
   * ���캯��
   * Description ���������õ�½���û�������SAM��ϵͳ����)���л�ȡ��������Ҫ�Ĳ�������������Ӧ���ӡ������������ƶ�һ���µ����ݿ����������ļ�
   * @param sessionCode: ��½�û��Ĵ���
   * @param sysCode����½Ҫ���ʵ�ϵͳ���ơ�һ��ϵͳ���ܻ��Ӧ��ͬ�����ݿ⡣���sysCodeΪ�ջ����"sam"�򷵻�ϵͳ�����
   *
   */
  public JdbObj(String sessionCode,String sysCode,String cfgFile) throws Exception {
    //ȷ�������ļ�����Ч
    if (cfgFile==null || cfgFile=="")
      cfgFile = "btcDatabase.cfg";
    this.cfgFile = cfgFile;
    m_sessionCode = sessionCode;
    m_sysCode = sysCode;
    readCfg();
  }

  /**
   * title: ���ι��캯��
   * @param dbType ���ݿ����͡���"Sql"��"Oracle"�ȡ��ó�����DbType�ж���
   * @param dbUrl ����Դ�����ݲ�ͬ������Դ���ɴ򿪲�ͬ�Ŀ⡣�ó�����DbUrl�ж���
   */
  public JdbObj(int dbType,int dbUrl) throws Exception {

    switch (dbType) { //�������ݿ���������
      case DbType.JDBC_ODBC:    //jdbc_odbc��
        driver = "sun.jdbc.odbc.JdbcOdbcDriver";
        switch (dbUrl) {
          case DbUrl.CERP:
            url = "jdbc:odbc:camps";
            break;
          default:
            m_errMsg = "����ʶ���url, ��ʹ��DbUrl�ṩ�ĳ���";
        }
        break;
      case DbType.SQL:    //SQL Server��������
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
            m_errMsg = "����ʶ���url, ��ʹ��DbUrl�ṩ�ĳ���";
        }
        break;
      default:
        m_errMsg = "����ʹ�õ����ݿ�����, ��ʹ��DbType�ṩ�ĳ���";
    }

  }

  /**
   * ��������
   */
   public void finalize () throws Exception {
    if (this.autoClose)
      conn.close();    //�ر����ݿ�����
   }

  /**
   * �������ݿ�ʵ�塣ֻ���ڼ���ʵ���ſ��Խ�������������
   * @param devPassword ������Ա����
   */
  public boolean active (String devPassword) {
    m_errMsg = "";
  try{
//System.out.println("enter active");
    if (devPassword.compareToIgnoreCase(DbConstStr.DEVPASSWORD) != 0)
      {m_errMsg = "�������";
       login = false;
       return false;
      }
/*
System.out.println(driver);
System.out.println(url);
System.out.println(userName);
System.out.println(password);
*/
   //������SAM�������
   Class.forName(driver);//����
   Connection samConn = DriverManager.getConnection(url, userName, password);
   if (!database.equals(""))
    samConn.setCatalog(database);
   if (m_sessionCode == null || m_sysCode == null || m_sysCode.compareToIgnoreCase("sam")==0 ||
       m_sessionCode == "" || m_sysCode == "")  {
      conn = samConn; //����ϵͳ��
      return true;
   }
   //��ȡ��λ���ݿ�ĸ�����Ϣ
    Statement st = samConn.createStatement(m_scrollType,m_updateType);
    ResultSet rst = st.executeQuery("select company_code,year from sam_session where session_code = " + LanTools.toSqlString(m_sessionCode));
    if (rst == null)  {
      m_errMsg = "ϵͳ���ȡ����sam";
      samConn.close();
      return false;
    }
    if (!rst.next())
      {m_errMsg = "�Ự�Ѿ�����: " + m_sessionCode;
       samConn.close();
       return false;
      }
    //session��Ч����ȡ��˾�ź����
    String company = rst.getString("company_code");
    String year = rst.getString("year");
    //�����������ݿ�����
    String mySql = "select driver_manager,db_url,db_user,db_pass from scg_db_config where company_code = ";
    mySql += LanTools.toSqlString(company);
    mySql += " and sys_code = " + LanTools.toSqlString(m_sysCode);
    mySql += " and year = " + LanTools.toSqlString(year);
    rst = st.executeQuery(mySql);
    if (rst==null || !rst.next())  {
      m_errMsg = "���ݿⶨ�岻���ڣ����룺" + m_sysCode + " ��˾��" + company + " ��ݣ�" + year;
      samConn.close();
     return false;
    }

    driver = rst.getString("driver_manager");
    url = rst.getString("db_url");
    userName = rst.getString("db_user");
    password = cerp_crypt.get_decoded_pass(rst.getString("db_pass"));
    samConn.close();
   Class.forName(driver);//����
   conn = DriverManager.getConnection(url, userName, password);

   login = true;

   return true;
  }catch (Exception e)  {
    m_errMsg = "�������ݿ����(" + url + "," + userName + "," + password + ")��" + e;
    e.printStackTrace();
  }
    return false;
  }

  /**
   * description ��ѯ��ǰ���ݿ����Ƿ��Ѿ�����ĳ����
   * @param tblName ����
   * return �����ʱ����true�����򷵻�false
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
   * description ��ѯ��ǰ���ݿ����Ƿ��Ѿ�����ĳ����
   * @param dbName ����
   * return �����ʱ����true�����򷵻�false
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
   * description �����Ƿ���ָ��������
   * @param: idxName Ҫ���ҵ�������
   * @param: tblName �������ڵı���
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
   * �õ�ǰ���ӻ�ñ���ֶζ���
   * @param tblName Ҫ��õı���
   * @return  �ɹ�ʱ������FieldDefn��ɵ������飬���򷵻�null
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
      throw new Exception("û�б����Ϣ��" + tblName);
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
   * Description �������ļ��ж������ڵ���ϵͳ���ÿ�Ĳ���
   * return ��������ļ����ڣ�����true�����򷵻�false������ȱʡֵ
   */
  boolean readCfg() {
  try {
//  System.out.print(System.getProperty("user.dir"));
//    FileInputStream cfgFile = new FileInputStream("btcDatabase.cfg");
    FileInputStream cfgFile = new FileInputStream(this.cfgFile);

    //�������ò���
    Properties proObj = new Properties();
    proObj.load(cfgFile);
    driver = proObj.getProperty("Driver","sun.jdbc.odbc.JdbcOdbcDriver");
    url = proObj.getProperty("UrlSam","jdbc:odbc:camps");
    userName = proObj.getProperty("UserName","huiton");
    password = proObj.getProperty("Password","cerp");
    database = proObj.getProperty("Database","");

    //���ܿ���
//    if (this.cfgFile.equalsIgnoreCase("btcDatabase.cfg"))
      password = cerp_crypt.get_decoded_pass(password);
    return true;

   }catch (Exception e)
   {//����ȱʡֵ
    driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    url = "jdbc:odbc:camps";
    userName = "huiton";
    password = "cerp";

    m_errMsg = e.getMessage();
    return false;
   }
  }

  /**
   * ���ö���ȡ��ʱ�Ƿ�ر�����
   * @param autoClose��Ϊtrueʱ�Զ��ر����ӣ����������ӡ�ȱʡʱΪtrue
   */
  public void setAutoClose(boolean autoClose) {
    this.autoClose = autoClose;
  }

  /**
   * �����캯��ֻ������Cerpϵͳ����ʹ�������ֱ��������Ӧ�����ݿ⣬����ʹ��session��������Ӧ�ú�JdbObj()���캯������ʹ�á�
   * �������ݿ�ʵ�塣ֻ���ڼ���ʵ���ſ��Խ�������������
   * @param devPassword ������Ա����
   * @param companyCode   Ҫ���ӵĹ�˾����
   * @param year  Ҫ���ӵ����
   * @param sysCode Ҫ���ӵ���ϵͳ����
   * @return ���ݿ����ӳɹ��Ƿ���true�����򷵻�false
   */
  public boolean active (String devPassword,String companyCode,String year,String sysCode) {
    m_errMsg = "";
  try{
//System.out.println("enter active");
    if (devPassword.compareToIgnoreCase(DbConstStr.DEVPASSWORD) != 0)
      {m_errMsg = "�������";
       login = false;
       return false;
      }
    m_sessionCode = "Direct";     //ֱ������
    m_sysCode = sysCode;

   //������SAM�������
   Class.forName(driver);//����
   Connection samConn = DriverManager.getConnection(url, userName, password);
   if (!database.equals(""))
    samConn.setCatalog(database);
   if (m_sessionCode == null || m_sysCode == null || m_sysCode.compareToIgnoreCase("sam")==0 ||
       m_sessionCode == "" || m_sysCode == "")  {
      conn = samConn; //����ϵͳ��
      return true;
   }
    //�����������ݿ�����
    Statement st = samConn.createStatement();
    String mySql = "select driver_manager,db_url,db_user,db_pass from scg_db_config where company_code = ";
    mySql += LanTools.toSqlString(companyCode);
    mySql += " and sys_code = " + LanTools.toSqlString(m_sysCode);
    mySql += " and year = " + LanTools.toSqlString(year);
    ResultSet rst = st.executeQuery(mySql);
    if (rst==null || !rst.next())  {
      m_errMsg = "���ݿⶨ�岻���ڣ����룺" + m_sysCode + " ��˾��" + companyCode + " ��ݣ�" + year;
      samConn.close();
     return false;
    }

    driver = rst.getString("driver_manager");
    url = rst.getString("db_url");
    userName = rst.getString("db_user");
    password = cerp_crypt.get_decoded_pass(rst.getString("db_pass"));
    samConn.close();
   Class.forName(driver);//����
   conn = DriverManager.getConnection(url, userName, password);

   login = true;

   return true;
  }catch (Exception e)  {
    m_errMsg = "�������ݿ����(" + url + "," + userName + "," + password + ")��" + e;
    e.printStackTrace();
  }
    return false;
  }

}