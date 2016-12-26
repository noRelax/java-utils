/**
 * Title:        ͨ���������
 * Description:  com.huiton.functions.DateFunc	���ں���
 * Copyright:    Copyright (c) 2001
 * Company:      huiton
 * @author �⽣
 * @version 1.0
 */

package com.huiton.pub.dbx;

import java.util.*;
import java.text.*;

public class DateFunc {

    // ������֮��ķָ��������Ȳ��ô���1
    public static final String SEPARATOR = "/";

    /**
     * ��ȡ��ǰ����
     * @return ��ʾ��ǰ���ڵ��ַ�������ʽΪ YYYY/MM/DD
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
     * ������������֮��Ĳ�ֵ����λ�������գ��� datePart��yy, mm, dd������
     * ���ڸ�ʽ��yyyy/mm/dd
     * @param String datePart: ���ڼ������ĵ�λ�������� yy, mm, dd��
     * @param String startDate: ��ʼ���ڣ�����
     * @param String endDate: ��ֹ���ڣ�������
     * @return ����ֵ����λ��datePart����ȷ��
     */
    public int dateDiff(String datePart, String startDate, String endDate)
        throws Exception
    {
        // ���ڸ�ʽ���
        if(!(checkDateFormat(startDate)||checkDateFormat(endDate)))
            throw new Exception("���ڸ�ʽ����");

        datePart = datePart.toLowerCase();
        if(!(datePart=="yy"||datePart=="mm"||datePart=="dd"))
            throw new Exception("���ڵ�λ��������!");

        // �����ڵ��ꡢ�¡��ղ���ת��������
        int startYear = Integer.valueOf(startDate.substring(0,4)).intValue();
        int startMonth = Integer.valueOf(startDate.substring(5,7)).intValue();
        int startDay = Integer.valueOf(startDate.substring(8,10)).intValue();

        int endYear = Integer.valueOf(endDate.substring(0,4)).intValue();
        int endMonth = Integer.valueOf(endDate.substring(5,7)).intValue();
        int endDay = Integer.valueOf(endDate.substring(8,10)).intValue();

        // ����������
        if(datePart=="yy")
            return endYear - startYear;
        // ��������²�
        else if(datePart=="mm")
            return (endYear - startYear) * 12 + (endMonth - startMonth);
        // ��������ղ�
        else {
            // ����Calendar����������ڼ���
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
     * �������ڼӷ�
     * ���ڸ�ʽ��yyyy/mm/dd
     * @param String datePart: ���ӵĵ�λ�������գ�yy, mm, dd��
     * @param String myDate: ���ڣ�������
     * @param int delta������
     * @return �ַ�����ʽ�����ڣ�yyyy/mm/dd��
     */
    public String dateAdd(String datePart, String myDate, int delta)
        throws Exception
    {
        // ���ڸ�ʽ���
        if(!checkDateFormat(myDate))
            throw new Exception("���ڸ�ʽ����");

        datePart = datePart.toLowerCase();
        if(!(datePart=="yy"||datePart=="mm"||datePart=="dd"))
            throw new Exception("���ڵ�λ��������!");

        // �����ڵ��ꡢ�¡��ղ���ת��������
        int myYear = Integer.valueOf(myDate.substring(0,4)).intValue();
        int myMonth = Integer.valueOf(myDate.substring(5,7)).intValue();
        int myDay = Integer.valueOf(myDate.substring(8,10)).intValue();


        // ����Calendar����������ڼ���
        Calendar cal = Calendar.getInstance();
        cal.set(myYear, intMonthValueToCalendarMonthValue(myMonth), myDay);

        if(datePart=="yy")
            cal.add(cal.YEAR, delta);
        if(datePart=="mm")
            cal.add(cal.MONTH, delta);
        if(datePart=="dd")
            cal.add(cal.DAY_OF_MONTH, delta);

        // �Ѽ�����ת���� yyyy/mm/dd ��ʽ
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
     * ����ָ������ָ�����֣������գ�������ֵ
     * ���ڸ�ʽ��yyyy/mm/dd
     * @param String datePart: ���ӵĵ�λ�������գ�yy, mm, dd��
     * @param String myDate: ����
     * @return ָ�����ڲ��ֵ�����ֵ
     */
    public int datePart(String datePart, String myDate)
        throws Exception
    {
        // ���ڸ�ʽ���
        if(!checkDateFormat(myDate))
            throw new Exception("���ڸ�ʽ����");

        datePart = datePart.toLowerCase();
        if(!(datePart=="yy"||datePart=="mm"||datePart=="dd"))
            throw new Exception("���ڵ�λ��������!");

        // ���ؽ��
        if(datePart=="yy")
            return Integer.parseInt(myDate.substring(0, 4));
        else if(datePart=="mm")
            return Integer.parseInt(myDate.substring(5, 7));
        else
            return Integer.parseInt(myDate.substring(8, 10));
    }


    // ���ڸ�ʽ��麯������ʽ��ȷ����TRUE�����򷵻�FALSE
    protected boolean checkDateFormat(String myDate) {
        // ���ؽ��
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

    // �����·ݵ�Calendar�������·ݳ�����ת��
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

    // Calendar�������·ݳ����������·ݵ�ת��
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