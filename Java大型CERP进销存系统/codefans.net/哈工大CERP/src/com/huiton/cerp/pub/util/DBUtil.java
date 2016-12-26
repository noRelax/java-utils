package com.huiton.cerp.pub.util;

import com.huiton.pub.dbx.*;

/**
 * <p>Title: ���ݿⳣ�ù���</p>
 * <p>Description: ����CerpԼ����һЩ���ݿⳣ�ù���</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: BRITC</p>
 * @author ����
 * @version 1.0
 */

public class DBUtil
{

  public DBUtil()
  {
  }

  /**
   * ������ֹ�������ɲ�ѯ�����Ӵ�����Cerp������������ڴ���ʱ���֡�����Ϣ�����ڲ�ѯ������ֹ�������ڣ�������ʱ�䣬�Ӷ�ʹ����ֹ���ڵļ�¼���ܲ����
   * ���������γ�ֻ�Ƚ�ǰʮλ���ֵĲ�ѯ�����ַ������ǵ���ʱ�䡣�Ƚ�ʱʹ��>=��<=������
   *  ʹ�þ�����
   *  	DBUtil.dateCondition(JdbOp.DBNAME_SQLSERVER,"i.founder_date",��2002/01/01","2002/01/31")
   * ���ص��ַ������£�
   *  	substring(i.founder_date,1,10)>='2002/01/01' and substring(i.founder_date,1,10)<='2002/01/31'   * @param dbName  ���ݿ�������JdbOp�ж����DBNAME����
   * @param dateField ���ݿ��е������ֶ���
   * @param sDate ��ʼ���ڣ�������null
   * @param eDate ��ֹ���ڣ�������null
   * @return  ���ز�ѯ�Ӵ�
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