package com.huiton.cerp.pub.util.functions;

/**
 * Title:        通用组件开发
 * Description:  com.huiton.functions.LookField	根据某条件从表中取值
 * Copyright:    Copyright (c) 2001
 * Company:      huiton
 * @author 吴剑
 * @version 1.0
 */


import java.math.*;
import java.sql.*;
import com.huiton.pub.dbx.JdbOp;
import com.huiton.mainframe.util.tracer.Debug;

public class LookField {
    private String sessionCode;
    private String sysCode;

    public LookField(String sessionCode, String sysCode) {
        this.sessionCode = sessionCode;
        this.sysCode = sysCode;
    }

    /**
     * String dLookup()
     */
    public String dLookup(String strField,
                          String strTable,
                          String strCri) {


        String strSql = "SELECT DISTINCT " + strField + " FROM "
                        + strTable + " WHERE " + strCri;

        return getResult(strSql);
    }

    /**
     * String dSum()
     */
    public String dSum(String strField,
                          String strTable,
                          String strCri) {

        String strSql = "SELECT SUM(" + strField + ") FROM "
                        + strTable + " WHERE " + strCri;

        return getResult(strSql);
    }

    /**
     * String dCount()
     */
    public long dCount(String strField,
                          String strTable,
                          String strCri) {

        String strSql = "SELECT COUNT(" + strField + ") FROM "
                        + strTable + " WHERE " + strCri;

        String result = getResult(strSql);
        return result.equals("") ? 0:Long.parseLong(result);
    }

    /**
     * String dMax()
     */
    public String dMax(String strField,
                          String strTable,
                          String strCri) {

        String strSql = "SELECT MAX(" + strField + ") FROM "
                        + strTable + " WHERE " + strCri;
        String result = getResult(strSql);
        return result==null ? "":result;
    }

    /**
     * String dMin()
     */
    public String dMin(String strField,
                          String strTable,
                          String strCri) {

        String strSql = "SELECT MIN(" + strField + ") FROM "
                        + strTable + " WHERE " + strCri;
        String result = getResult(strSql);
        return result==null ? "":result;
    }

    /**
     * String dAvg()
     */
    public String dAvg(String strField,
                          String strTable,
                          String strCri) {

        String strSql = "SELECT AVG(" + strField + ") FROM "
                        + strTable + " WHERE " + strCri;

        return getResult(strSql);
    }

    /**
     * function used to get result from database.
     */
    private String getResult(String strSql) {
        try {
            Debug.println("LookField: getResult sessionCode = " + sessionCode);
            Debug.println("LookField: getResult sysCode = " + sysCode);
            JdbOp store = new JdbOp(sessionCode, sysCode);
            ResultSet rs = store.getData(strSql);

            Debug.println("LookField: getResult..." + strSql);

            if(rs==null || !rs.next())
                return "";
            else
                return rs.getString(1);
        }
        catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}