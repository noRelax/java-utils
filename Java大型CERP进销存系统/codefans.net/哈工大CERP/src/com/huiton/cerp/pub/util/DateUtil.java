package com.huiton.cerp.pub.util;

/**
 * Title:        CERP���Կ��
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author �⽣
 * @version 1.0
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.huiton.mainframe.util.tracer.Debug;

public class DateUtil {
    private static final String DEFAULT_SEPARATOR = "/";

    public static String getCurrentDate() {
        return  getCurrentDate(DEFAULT_SEPARATOR);
    }

    public static String getCurrentDateTime() {
        return  getCurrentDateTime(DEFAULT_SEPARATOR);
    }

    public static String getCurrentDate(String separator) {
        Calendar cal = new GregorianCalendar();
        String strMon = String.valueOf(cal.get(Calendar.MONTH)+1);
        String strDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        if(strMon.length()<2) strMon = "0" + strMon;
        if(strDay.length()<2) strDay = "0" + strDay;

        return  String.valueOf(cal.get(Calendar.YEAR)) + separator
                + strMon + separator + strDay;
    }

    public static String getCurrentDateTime(String separator) {
        Calendar cal = new GregorianCalendar();
        String strHour = String.valueOf(cal.get(Calendar.HOUR));
        String strMinute = String.valueOf(cal.get(Calendar.MINUTE));
        String strSecond = String.valueOf(cal.get(Calendar.SECOND));

        int hour = cal.get(Calendar.HOUR);
        if(cal.get(Calendar.AM_PM)==Calendar.PM) hour += 12;

        strHour = String.valueOf(hour);
        if(strHour.length()<2) strHour = "0" + strHour;
        if(strMinute.length()<2) strMinute = "0" + strMinute;
        if(strSecond.length()<2) strSecond = "0" + strSecond;

        return  getCurrentDate(separator) + " " +
                strHour + ":" + strMinute + ":" + strSecond;
    }

    /**
     * ��ȡ����ʱ�䡣����������ӵ�ǰʱ����ϸ��������������
     * @param intervalDays �ӵ����������������������Ǹ���
     * @return  ���ؼ���������
     * @author wt
     */
    public static String getFallinDate(int intervalDays) {
      Calendar cal = new GregorianCalendar();
      cal.add(cal.DAY_OF_YEAR,intervalDays);
      return formatDate(cal);
    }

    /**
     * ��ȡ����ʱ�䡣����������ӵ�ǰʱ����ϸ��������������
     * @param intervalDays  ���ַ������Ĵӵ����������������������Ǹ�����������ݵ��ַ�������ת��Ϊ�������ͣ���������ȡ0
     * @return
     * @author wt
     */
    public static String getFallinDate(String intervalDays) {
      int interval;
      try {
        interval = Integer.parseInt(intervalDays);
      }catch (Exception e)  {
        interval = 0;
      }

      return getFallinDate(interval);
    }

    /**
     * ����Cerp��Ҫ�󷵻������ַ���
     * @param cal Ҫת������������
     * @return  ���ظ�ʽ����������ַ�����yy/mm/dd
     * @author wt
     */
    static String formatDate(Calendar cal)  {
      int monNum = cal.get(cal.MONTH)+1;
      int dayNum = cal.get(cal.DAY_OF_MONTH);
      String mon = monNum>9?String.valueOf(monNum):"0"+String.valueOf(monNum);
      String day = dayNum>9?""+dayNum:"0"+dayNum;
      return  ""+cal.get(Calendar.YEAR)+DEFAULT_SEPARATOR + mon + DEFAULT_SEPARATOR +day;
    }

    /**
     * ���ص�ǰ���
     * @return ��ǰ�Ĺ������ֵ
     */
    public static int getCurYear()
    {
      Calendar cal = new GregorianCalendar();
      return cal.get(cal.YEAR);

    }
}