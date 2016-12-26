package com.huiton.mainframe.control.web.handlers;

/**
 * Title:        CERP测试框架
 * Description:  处理工作日程
 * Copyright:    Copyright (c) 2002
 * Company:      BRITC
 * @author 张爱军
 * @version 1.0
 */
import com.huiton.pub.dbx.*;
import java.sql.*;
import java.util.*;
import com.huiton.cerp.pub.util.functions.*;
import javax.servlet.http.*;
import com.huiton.mainframe.util.tracer.Debug;

public class ScheduleWeekly extends Schedule
{

    //私有成员变量
    private String op_date = null;    //操作日期

    //构造器
    public ScheduleWeekly(String companyCode,String userUniqueNo)
    {
        super(companyCode,userUniqueNo);
    }

    //禁用的构造器
    public ScheduleWeekly() throws Exception
    {
        super();
    }


    //保护方法

     //初始化参数
    protected void m_initParams(HttpServletRequest request)
    {
        try
        {
            //日期 today,strToday
            strToday = request.getParameter("strToday");
            strToday = (strToday==null ? "" : strToday.trim());
            CommonDate today = new CommonDate(strToday);
            today = today.firstDayOfTheWeek();
            strToday = today.getYMD();

            //操作方式
            opFlag = request.getParameter("opFlag");
            opFlag = (opFlag==null ? "" : opFlag.trim());
            opFlag = (opFlag.length()<1 ? "query" : opFlag);

            //数据量大小
            pageSize = 10 ;

            op_date = request.getParameter("op_date");
            op_date = (op_date==null ? "" : op_date.trim());
            op_date = (op_date.equals("") ? strToday : op_date);
        }
        catch(Exception e)
        {
            Debug.println("initParams throws exception");
        }
    }

    //设置属性
    protected void m_setAttr(HttpServletRequest request)
    {
        vct = (vct==null ? new Vector() : vct);
        request.setAttribute("vct",vct);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("outFlag",outFlag);
        request.setAttribute("strToday",strToday);
        request.setAttribute("op_date",op_date);

    }

    //查询
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

            for(int i=1;i<8;i++)
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

    //删除所有
    protected void m_delAll(HttpServletRequest request)
    {
        // no caller
    }


}