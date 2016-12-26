package com.huiton.mainframe.control.web.handlers;

/**
 * Title:        �����ճ�
 * Description:  �������ճ�
 * Copyright:    Copyright (c) 2002
 * Company:      BRITC
 * @author �Ű���
 * @version 1.0
 */
import com.huiton.pub.dbx.*;
import java.sql.*;
import java.util.*;
import com.huiton.cerp.pub.util.functions.*;
import javax.servlet.http.*;
import com.huiton.mainframe.util.tracer.Debug;

public class ScheduleMonthly extends Schedule
{

    //˽�г�Ա����
    private String op_date = null;    //��������

    //������
    public ScheduleMonthly(String companyCode,String userUniqueNo)
    {
        super(companyCode,userUniqueNo);
    }

    //���õĹ�����
    public ScheduleMonthly() throws Exception
    {
        super();
    }


    //��������

     //��ʼ������
    protected void m_initParams(HttpServletRequest request)
    {
        try
        {
            //���� today,strToday
            strToday = request.getParameter("strToday");
            strToday = (strToday==null ? "" : strToday.trim());
            CommonDate today = new CommonDate(strToday);
            today = today.firstDayOfTheMonth();
            strToday = today.getYMD();

            //������ʽ
            opFlag = request.getParameter("opFlag");
            opFlag = (opFlag==null ? "" : opFlag.trim());
            opFlag = (opFlag.length()<1 ? "query" : opFlag);

            //��������С
            pageSize = 6 ; //ÿ����ʾ������

            op_date = request.getParameter("op_date");
            op_date = (op_date==null ? "" : op_date.trim());
            op_date = (op_date.equals("") ? strToday : op_date);
        }
        catch(Exception e)
        {
            Debug.println("initParams throws exception");
        }
    }

    //��������
    protected void m_setAttr(HttpServletRequest request)
    {
        vct = (vct==null ? new Vector() : vct);
        request.setAttribute("vct",vct);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("outFlag",outFlag);
        request.setAttribute("strToday",strToday);
        request.setAttribute("op_date",op_date);

    }

    //��ѯ
    protected void m_query(HttpServletRequest request)
    {
        if (outFlag.equals("0"))
            return ;

        try
        {
            strFields = "start_time,event_subject,log_no";
            String strConditions = null;
            strTables = "sam_user_event";

            CommonDate tmpDate = new CommonDate(strToday);
            int len = tmpDate.getDaysOfTheMonth()+1 ;
            for(int i=1;i<len;i++)
            {
                vct = null ;

                if (condition.length()<1)
                {
                    strConditions = "company_code='" + companyCode
                        + "' and user_unique_no='" + userUniqueNo
                        + "' and cur_date='" + tmpDate.getYMD() + "' order by start_time";
                }else
                {
                    strConditions = condition + " and (company_code='"+companyCode
                        +"' and user_unique_no='"+userUniqueNo
                        + "' and cur_date='" + tmpDate.getYMD() + "') order by start_time";
                }
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                vct = pageQuery.dividePage(1,pageSize);
                Debug.println("vct"+i+".size=" + (vct==null ? 0 : vct.size()));

                request.setAttribute("vct"+i,vct);
                tmpDate = tmpDate.nextDate();
            }
            vct = null;
        }
        catch(Exception e)
        {
            Debug.println("query throws exception");
        }
    }

    //ɾ������
    protected void m_delAll(HttpServletRequest request)
    {
        // no caller
    }


}