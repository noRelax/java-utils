package com.huiton.cerp.pub.util;

import com.huiton.pub.dbx.*;

/**
 * <p>Title: 数据库常用工具</p>
 * <p>Description: 符合Cerp约定的一些数据库常用工具</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: BRITC</p>
 * @author 王涛
 * @version 1.0
 */

public class DBUtil
{

  public DBUtil()
  {
  }

  /**
   * 按照起止日期生成查询条件子串。在Cerp中由于许多日期带有时、分、秒信息，而在查询条件中止输入日期，不输入时间，从而使得终止日期的记录不能查出。
   * 本函数将形成只比较前十位数字的查询条件字符串，虑掉了时间。比较时使用>=和<=操作符
   *  使用举例：
   *  	DBUtil.dateCondition(JdbOp.DBNAME_SQLSERVER,"i.founder_date",“2002/01/01","2002/01/31")
   * 返回的字符串如下：
   *  	substring(i.founder_date,1,10)>='2002/01/01' and substring(i.founder_date,1,10)<='2002/01/31'   * @param dbName  数据库名，在JdbOp中定义的DBNAME常数
   * @param dateField 数据库中的日期字段名
   * @param sDate 起始日期，可以是null
   * @param eDate 终止日期，可以是null
   * @return  返回查询子串
   */
  public static String dateCondition(int dbName, String dateField, String sDate, String eDate)
  {
    String subFunc = JdbOp.convertDbFunc("substring",dbName);

    String condStr="";
    if (sDate != null && sDate.trim().length()>0)
      condStr = subFunc+"("+dateField+",1,10)>='"+sDate+"' and ";
    if (eDate != null && eDate.trim().length()>0)
      condStr += subFunc+"("+dateField+",1,10)<='"+eDate+"'";

    return condStr.trim();
  }
}