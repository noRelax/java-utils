/**
 * Title:        通用组件开发
 * Description:  com.huiton.functions.DateFunc	日期函数
 * Copyright:    Copyright (c) 2001
 * Company:      huiton
 * @author 吴剑
 * @version 1.0
 */

package com.huiton.pub.dbx;

import java.util.*;
import java.text.*;

public class DateFunc {

    // 年月日之间的分隔符，长度不得大于1
    public static final String SEPARATOR = "/";

    /**
     * 获取当前日期
     * @return 表示当前日期的字符串，格式为 YYYY/MM/DD
     */
    public String getDate() throws Exception
    {
        java.util.Date myDate = new java.util.Date();
        String dateString = DateFormat.getDateInstance().format(myDate);

        String myYear, myMonth, myDay;
        myYear = dateString.substring(0,4);
        myMonth = dateString.substring(5, dateString.lastIndexOf("-"));
        myDay = dateString.substring(dateString.lastIndexOf("-")+1, dateString.length());
        if(myMonth.length()==1)
            myMonth = "0" + myMonth;
        if(myDay.length()==1)
            myDay = "0" + myDay;

        return myYear + SEPARATOR + myMonth + SEPARATOR + myDay;
    }

    /**
     * 计算两个日期之间的差值，单位（年月日）由 datePart（yy, mm, dd）决定
     * 日期格式：yyyy/mm/dd
     * @param String datePart: 日期计算结果的单位（年月日 yy, mm, dd）
     * @param String startDate: 开始日期，减数
     * @param String endDate: 终止日期，被减数
     * @return 整数值，单位由datePart参数确定
     */
    public int dateDiff(String datePart, String startDate, String endDate)
        throws Exception
    {
        // 日期格式检查
        if(!(checkDateFormat(startDate)||checkDateFormat(endDate)))
            throw new Exception("日期格式错误！");

        datePart = datePart.toLowerCase();
        if(!(datePart=="yy"||datePart=="mm"||datePart=="dd"))
            throw new Exception("日期单位参数错误!");

        // 把日期的年、月、日部分转换成整数
        int startYear = Integer.valueOf(startDate.substring(0,4)).intValue();
        int startMonth = Integer.valueOf(startDate.substring(5,7)).intValue();
        int startDay = Integer.valueOf(startDate.substring(8,10)).intValue();

        int endYear = Integer.valueOf(endDate.substring(0,4)).intValue();
        int endMonth = Integer.valueOf(endDate.substring(5,7)).intValue();
        int endDay = Integer.valueOf(endDate.substring(8,10)).intValue();

        // 如果计算年差
        if(datePart=="yy")
            return endYear - startYear;
        // 如果计算月差
        else if(datePart=="mm")
            return (endYear - startYear) * 12 + (endMonth - startMonth);
        // 如果计算日差
        else {
            // 利用Calendar对象进行日期计算
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            startCal.set(startYear, intMonthValueToCalendarMonthValue(startMonth), startDay);
            endCal.set(endYear, intMonthValueToCalendarMonthValue(endMonth), endDay);
            int delta;
            if(startCal.after(endCal))
                delta = -1;
            else
                delta = 1;

            int result;
            for(result=0;!startCal.equals(endCal);result++)
                startCal.add(Calendar.DATE, delta);

            return result * delta;
        }
    }


    /**
     * 计算日期加法
     * 日期格式：yyyy/mm/dd
     * @param String datePart: 增加的单位（年月日；yy, mm, dd）
     * @param String myDate: 日期，被加数
     * @param int delta：增量
     * @return 字符串形式的日期（yyyy/mm/dd）
     */
    public String dateAdd(String datePart, String myDate, int delta)
        throws Exception
    {
        // 日期格式检查
        if(!checkDateFormat(myDate))
            throw new Exception("日期格式错误！");

        datePart = datePart.toLowerCase();
        if(!(datePart=="yy"||datePart=="mm"||datePart=="dd"))
            throw new Exception("日期单位参数错误!");

        // 把日期的年、月、日部分转换成整数
        int myYear = Integer.valueOf(myDate.substring(0,4)).intValue();
        int myMonth = Integer.valueOf(myDate.substring(5,7)).intValue();
        int myDay = Integer.valueOf(myDate.substring(8,10)).intValue();


        // 利用Calendar对象进行日期计算
        Calendar cal = Calendar.getInstance();
        cal.set(myYear, intMonthValueToCalendarMonthValue(myMonth), myDay);

        if(datePart=="yy")
            cal.add(cal.YEAR, delta);
        if(datePart=="mm")
            cal.add(cal.MONTH, delta);
        if(datePart=="dd")
            cal.add(cal.DAY_OF_MONTH, delta);

        // 把计算结果转换成 yyyy/mm/dd 格式
        String strYear = String.valueOf(cal.get(cal.YEAR));
        String strMonth = String.valueOf(calendarMonthValueTointMonthValue(cal.get(cal.MONTH)));
        String strDay = String.valueOf(cal.get(cal.DAY_OF_MONTH));

        while(strMonth.length()<2)
            strMonth = "0" + strMonth;
        while(strDay.length()<2)
            strDay = "0" + strDay;

        return strYear + SEPARATOR + strMonth + SEPARATOR + strDay;
    }


    /**
     * 返回指定日期指定部分（年月日）的整数值
     * 日期格式：yyyy/mm/dd
     * @param String datePart: 增加的单位（年月日；yy, mm, dd）
     * @param String myDate: 日期
     * @return 指定日期部分的整数值
     */
    public int datePart(String datePart, String myDate)
        throws Exception
    {
        // 日期格式检查
        if(!checkDateFormat(myDate))
            throw new Exception("日期格式错误！");

        datePart = datePart.toLowerCase();
        if(!(datePart=="yy"||datePart=="mm"||datePart=="dd"))
            throw new Exception("日期单位参数错误!");

        // 返回结果
        if(datePart=="yy")
            return Integer.parseInt(myDate.substring(0, 4));
        else if(datePart=="mm")
            return Integer.parseInt(myDate.substring(5, 7));
        else
            return Integer.parseInt(myDate.substring(8, 10));
    }


    // 日期格式检查函数，格式正确返回TRUE，否则返回FALSE
    protected boolean checkDateFormat(String myDate) {
        // 返回结果
        boolean result = true;
        if(myDate.length()!=10) return false;
        if(myDate.indexOf(SEPARATOR)!=4) return false;
        if(myDate.lastIndexOf(SEPARATOR)!=7) return false;

        int myYear = Integer.valueOf(myDate.substring(0,4)).intValue();
        int myMonth = Integer.valueOf(myDate.substring(5,7)).intValue();
        int myDay = Integer.valueOf(myDate.substring(8,10)).intValue();

        result = result && (myYear>=1900 && myYear<=9999);
        result = result && (myMonth>=1 && myMonth<=12);
        result = result && (myDay>=1 && myDay<=31);

        return result;
    }

    // 整数月份到Calendar对象中月份常数的转换
    protected int intMonthValueToCalendarMonthValue(int myMonth) {
        int result;
        switch(myMonth) {
            case 1:
                result = Calendar.JANUARY;
                break;
            case 2:
                result = Calendar.FEBRUARY;
                break;
            case 3:
                result = Calendar.MARCH;
                break;
            case 4:
                result = Calendar.APRIL;
                break;
            case 5:
                result = Calendar.MAY;
                break;
            case 6:
                result = Calendar.JUNE;
                break;
            case 7:
                result = Calendar.JULY;
                break;
            case 8:
                result = Calendar.AUGUST;
                break;
            case 9:
                result = Calendar.SEPTEMBER;
                break;
            case 10:
                result = Calendar.OCTOBER;
                break;
            case 11:
                result = Calendar.NOVEMBER;
                break;
            case 12:
                result = Calendar.DECEMBER;
                break;
            default:
                result = 99;
        }

        return result;
    }

    // Calendar对象中月份常数到整数月份的转换
    protected int calendarMonthValueTointMonthValue(int myMonth) {
        int result;
        switch(myMonth) {
            case Calendar.JANUARY:
                result = 1;
                break;
            case Calendar.FEBRUARY:
                result = 2;
                break;
            case Calendar.MARCH:
                result = 3;
                break;
            case Calendar.APRIL:
                result = 4;
                break;
            case Calendar.MAY:
                result = 5;
                break;
            case Calendar.JUNE:
                result = 6;
                break;
            case Calendar.JULY:
                result = 7;
                break;
            case Calendar.AUGUST:
                result = 8;
                break;
            case Calendar.SEPTEMBER:
                result = 9;
                break;
            case Calendar.OCTOBER:
                result = 10;
                break;
            case Calendar.NOVEMBER:
                result = 11;
                break;
            case Calendar.DECEMBER:
                result = 12;
                break;
            default:
                result = 99;
        }

        return result;
    }
}