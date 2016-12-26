/**
 * ���ݿ��ѯ���
 * ���� SQL ����ò�ѯ���
 *
 * ���ݿ����ӹ��������������ɣ�cn.com.huiton.ConnectionPool.ConnectionPool��
 *
 * ʹ�ò��裺
 * 1����������
 * 2��ʹ�� connect() ���������ݿ⽨�����ӣ�
 * 3���������ݿ������
 * 4��ʹ�� disConnect() ���������ݿ�Ͽ����ӣ�
 * 5�����ٶ���
 *
 * @@author=�⽣
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
         *                                             *						���� SQL ����ȡ��¼��
         *
         * ����ֵ��
         *                                             *						ResultSet ��¼��
         *
         * ������
     *                      String mySql  SQL���
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
         *                                             *						ִ�� SQL ���
         *
         * ����ֵ��
         *                                             *						boolean
         *
         * ������
     *                      String mySql  SQL���
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
         * void connect()		�����ݿ⽨������
         *
         * ����ֵ��				void
         *
         * ������
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
      {m_ErrMsg = preDbObj.m_errMsg;
       return false;
      }
   return true;
  }


        /**
         * void disConnect()	�Ͽ����ݿ�����
         *
         * ����ֵ��
         *                                             *						void
         *
         * ������
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
     * �������ݿ����Ӷ���
     * @return Connection����
     */
    public Connection getConnection() {
        return m_conn;
    }
  private void jbInit() throws Exception {
  }
}