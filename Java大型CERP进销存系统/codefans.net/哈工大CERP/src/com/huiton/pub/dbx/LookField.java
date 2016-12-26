/**
 * Title:        通用组件开发
 * Description:  com.huiton.functions.LookField	根据某条件从表中取值
 * Copyright:    Copyright (c) 2001
 * Company:      huiton
 * @author 吴剑
 * @version 1.0
 */
package com.huiton.pub.dbx;

import java.math.*;
import java.sql.*;
import com.huiton.pub.dbx.*;

public class LookField {

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
    public String dCount(String strField,
                          String strTable,
                          String strCri) {

        String strSql = "SELECT COUNT(" + strField + ") FROM "
                        + strTable + " WHERE " + strCri;

        return getResult(strSql);
    }

    /**
     * String dMax()
     */
    public String dMax(String strField,
                          String strTable,
                          String strCri) {

        String strSql = "SELECT MAX(" + strField + ") FROM "
                        + strTable + " WHERE " + strCri;

        return getResult(strSql);
    }

    /**
     * String dMin()
     */
    public String dMin(String strField,
                          String strTable,
                          String strCri) {

        String strSql = "SELECT MIN(" + strField + ") FROM "
                        + strTable + " WHERE " + strCri;

        return getResult(strSql);
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
            JdbQuery store = new JdbQuery();
            ResultSet rs = store.getData(strSql);

            // System.out.println(strSql);

            if(!rs.next())
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