package com.huiton.mainframe.control.web.handlers;

public class SwitchSql {

    public static final String SQLSERVER = "SQLServer";
    public static final String ORACLE = "Oracle";
    public static final String NULL_STRING = "";

    public SwitchSql()
    {

    }
    public static String getHintSql(String db,String strToday)
    {
        if (db.equalsIgnoreCase(SQLSERVER))
        {
            return "DATEDIFF(day,'" + strToday + "',cur_date)<=alert_days" ;
        }
        else if  (db.equalsIgnoreCase(ORACLE))
        {
            return NULL_STRING ;
        }else
        {
            return NULL_STRING ;
        }
    }

    public static String getPlus(String db)
    {
      if (db.equalsIgnoreCase(SQLSERVER))
        {
            return "+" ;
        }
        else if  (db.equalsIgnoreCase(ORACLE))
        {
            return "||" ;
        }else
        {
            return NULL_STRING ;
        }
    }
}