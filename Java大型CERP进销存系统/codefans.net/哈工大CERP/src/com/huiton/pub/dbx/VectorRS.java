package com.huiton.pub.dbx;
import java.util.*;
import java.sql.*;

public class VectorRS
{
    private int m_pageSize;
    private int m_pageCount;
    private int m_curPage;

    private String m_mySql;
    private String[] m_primaryKey;
    private String[][] m_startKey;
    private String[][] m_endKey;

    public VectorRS(String mySql, String[] primaryKey, int pageSize) {
        this.m_mySql = mySql;
        this.m_primaryKey = primaryKey;
        this.m_pageSize = pageSize;

        m_pageCount = 0;
        m_curPage = 0;

        int maxPageCount = 500000 / this.m_pageSize;
        m_startKey = new String[maxPageCount][m_primaryKey.length];
        m_endKey = new String[maxPageCount][m_primaryKey.length];

        for(int i=0;i<m_primaryKey.length;i++) {
            m_startKey[0][i] = "";
            m_endKey[0][i] = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
        }
    }

    public VectorRS(String mySql, String[] primaryKey) {
        this.m_mySql = mySql;
        this.m_primaryKey = primaryKey;
        this.m_pageSize = 50;

        m_pageCount = 0;
        m_curPage = 0;

        int maxPageCount = 500000 / this.m_pageSize;
        m_startKey = new String[maxPageCount][m_primaryKey.length];
        m_endKey = new String[maxPageCount][m_primaryKey.length];

        for(int i=0;i<m_primaryKey.length;i++) {
            m_startKey[0][i] = "";
            m_endKey[0][i] = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
        }
    }

    public void setPrimaryKey(String[] primaryKey) {
        this.m_primaryKey = primaryKey;
    }

        // Get the first page of the ResultSet.
        public Vector firstPage() {
            m_curPage = 1;
            return getVector("first");
        }

        public Vector nextPage() {
            m_curPage++;
            return getVector("next");
        }

        public Vector previousPage() {
                m_curPage--;
                return getVector("previous");
        }

        public Vector lastPage() {
                m_curPage = m_pageCount;
                return getVector("last");
        }

        // Get the Result in Vector from.
        private Vector getVector(String pageFlag) {

                try {
                        JdbQuery dbQuery = new JdbQuery();

                        // Connect to the database.
                        dbQuery.connect();

                        // Get the query string, which returns only one page of the ResultSet.
                        String mySql = getQueryString(m_mySql, pageFlag);
            // System.out.println(mySql);

                        // Open a ResultSet.
                        ResultSet myResultSet = dbQuery.getData(mySql);
                        Vector myData = new Vector(m_pageSize);
                        String[][] strRecords;

                        // Get the ResultSet's meta-info.
                        ResultSetMetaData rsMeta = myResultSet.getMetaData();
                        int colCount = rsMeta.getColumnCount();
                        strRecords = new String[m_pageSize][colCount];

                        // Put the data into a vector.
                        int i;
                        for(i=0;(i<m_pageSize) && myResultSet.next();i++) {
                                for(int j=0;j<colCount;j++) {
                    String tmpValue = myResultSet.getString(j+1);
                                        strRecords[i][j] = tmpValue;
                                }

                                myData.addElement(strRecords[i]);

                                //Clear strRecords[]
                /*
                                for(int n=0;n<strRecords.length;n++)
                                        strRecords[n] = "";
                    */
                        }

                        // Remember the page count.
                        if(i<m_pageSize)
                                m_pageCount = m_curPage;

                        // Record the start key.
                        m_startKey[m_curPage-1] = (String[])myData.firstElement();
                        // Record the last key.
                        m_endKey[m_curPage-1] = (String[])myData.lastElement();

            // System.out.println(m_startKey[m_curPage-1][0]);
            // System.out.println(m_endKey[m_curPage-1][0]);

                        // disconnect to the database.
                        dbQuery.disConnect();

                        return myData;
                }
                catch(SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                        return null;
                }
        }

        // Get the query string, which returns only one page of the ResultSet.
        private String getQueryString(String mySql, String pageFlag) {
                String tmpStartValue = "", tmpEndValue = "";
                String tmpFields = "";

                for(int i=0;i<m_primaryKey.length;i++) {
                        // tmpStartValue += getFixedLengthString(m_startKey[(m_curPage-1)-1][i], " ", 100);
                        // tmpEndValue += getFixedLengthString(m_endKey[(m_curPage-1)-1][i], " ", 100);

                        tmpFields += " + CAST(" + m_primaryKey[i].trim() + " AS char(100))";
                }
        int fieldsLen = tmpFields.length();
        tmpFields = tmpFields.substring(3);

        if(pageFlag.toLowerCase().equals("first"))
                        return mySql;
                else if(pageFlag.toLowerCase().equals("next"))
                        return mySql + " AND " + tmpFields + " > '" + getNextCriValue()[1] + "' ";
                else if(pageFlag.toLowerCase().equals("previous"))
                        return mySql + " AND " + tmpFields + " BETWEEN '" + getPreviousCriValue()[0]
                                         + "' AND '" + getPreviousCriValue()[1] + "' ";
        else if(pageFlag.toLowerCase().equals("last"))
            return mySql;
        else
            return mySql;
        }

    private String[] getNextCriValue() {
        String[] tmpValue = { "", "" };
                for(int i=0;i<m_primaryKey.length;i++) {
            tmpValue[0] += getFixedLengthString(m_startKey[(m_curPage-1)-1][i], " ", 100);
                        tmpValue[1] += getFixedLengthString(m_endKey[(m_curPage-1)-1][i], " ", 100);
        }
        return tmpValue;
    }

    private String[] getPreviousCriValue() {
        String[] tmpValue = { "", "" };
                for(int i=0;i<m_primaryKey.length;i++) {
            tmpValue[0] += getFixedLengthString(m_startKey[m_curPage-1][i], " ", 100);
                        tmpValue[1] += getFixedLengthString(m_endKey[m_curPage-1][i], " ", 100);
        }
        return tmpValue;
    }

        private String getFixedLengthString(String myStr, String strFill, int maxLength) {
                while(myStr.length()<maxLength)
                        myStr += strFill;

                return myStr;
        }
}
