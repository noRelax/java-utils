/**
 * 数据库查询组件
 * 根据 SQL 语句获得查询结果
 *
 * 数据库连接功能由其他组件完成（cn.com.huiton.ConnectionPool.ConnectionPool）
 *
 * 使用步骤：
 * 1、创建对象；
 * 2、使用 connect() 方法与数据库建立连接；
 * 3、进行数据库操作；
 * 4、使用 disConnect() 方法与数据库断开连接；
 * 5、销毁对象。
 *
 * @@author=吴剑
 */


package com.huiton.pub.dbx;
import java.sql.*;
import com.huiton.cerp.pub.*;

public class JdbQuery
{
        protected Connection m_conn;
    protected Statement m_statement;

    protected int m_scrollType;
    protected int m_updatable;

    public JdbObj preDbObj = null;
    public String m_ErrMsg = "";

        public JdbQuery() {
        m_scrollType = ResultSet.TYPE_SCROLL_SENSITIVE;
        m_updatable = ResultSet.CONCUR_READ_ONLY;
                connect();
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
        }
        public JdbQuery(int scrollType, int updatable) {
        m_scrollType = scrollType;
        m_updatable = updatable;
                connect();
        }

        public void finalize() {
                disConnect();
        }


        /**
         * ResultSet GetData(String mySql)
         *                                             *						根据 SQL 语句获取记录集
         *
         * 返回值：
         *                                             *						ResultSet 记录集
         *
         * 参数：
     *                      String mySql  SQL语句
         */
        public ResultSet getData(String mySql)
        {
                try {
                        // System.out.println("Native Form: " + m_conn.nativeSQL(mySql));
                        ResultSet rs = m_statement.executeQuery(mySql);

                        return rs;
                }
                catch (Exception e) {
                        disConnect();
                        System.out.println(e.toString());
                        return null;
                }
        }

        /**
         * boolean RunSQL(String mySql)
         *                                             *						执行 SQL 语句
         *
         * 返回值：
         *                                             *						boolean
         *
         * 参数：
     *                      String mySql  SQL语句
         */
        public boolean runSql(String mySql)
        {
                try {
                        // System.out.println("Native Form: " + m_conn.nativeSQL(mySql));
            return m_statement.execute(mySql);
                }
                catch (Exception e) {
                        disConnect();
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
         * modify by wt:  2001.7.12 remove pool
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

                        m_conn = pool.getConnection();
*/
                        if (!createConnect())
                           throw  (new Exception(m_ErrMsg));
                        m_statement = m_conn.createStatement(m_scrollType, m_updatable);
                }
                catch(Exception e) {
                        disConnect();
            e.printStackTrace();
                }

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
      {m_ErrMsg = preDbObj.m_errMsg;
       return false;
      }
   return true;
  }


        /**
         * void disConnect()	断开数据库连接
         *
         * 返回值：
         *                                             *						void
         *
         * 参数：
         */
        public void disConnect() {
                try {
                        if(m_conn!=null)
                                m_conn.close();
            if(m_statement!=null)
                m_statement.close();
        }
        catch (SQLException e)
        {
                e.printStackTrace();
        }
    }

    /**
     * 返回数据库连接对象
     * @return Connection对象
     */
    public Connection getConnection() {
        return m_conn;
    }
  private void jbInit() throws Exception {
  }
}