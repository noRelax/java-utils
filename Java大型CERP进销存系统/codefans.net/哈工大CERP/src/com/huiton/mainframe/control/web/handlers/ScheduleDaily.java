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

public class ScheduleDaily extends Schedule
{

    //私有成员变量
    private int currentPage = 0; //当前页
    private int pageCount = 0; //总页数
    private String selectAll = "0" ;  //全选标记

    //构造器
    public ScheduleDaily(String companyCode,String userUniqueNo)
    {
        super(companyCode,userUniqueNo);
    }

    //禁用的构造器
    public ScheduleDaily() throws Exception
    {
        super();
    }


    //保护方法

     //初始化参数
    protected void m_initParams(HttpServletRequest request)
    {
        try
        {
            //全选标记
            selectAll = request.getParameter("selectAll");
            selectAll = ((selectAll==null || selectAll.equals("")) ? "0" : selectAll.trim());

            //日期 today
            strToday = request.getParameter("strToday");
            strToday = (strToday==null ? "" : strToday.trim());
            strToday = (strToday.length()<1 ? new CommonDate().getYMD() : strToday);

            //操作方式
            opFlag = request.getParameter("opFlag");
            opFlag = (opFlag==null ? "" : opFlag.trim());
            opFlag = (opFlag.length()<1 ? "query" : opFlag);

            //当前页
            String m_currentPage = request.getParameter("currentPage");
            m_currentPage = ((m_currentPage==null || m_currentPage.equals(""))? "1" : m_currentPage.trim());
            currentPage = Integer.parseInt(m_currentPage);
            currentPage = (currentPage < 1 ? 1 : currentPage);

            //总页数
            m_currentPage = request.getParameter("pageCount");
            m_currentPage = ((m_currentPage==null || m_currentPage.equals(""))? "1" : m_currentPage.trim());
            pageCount = Integer.parseInt(m_currentPage);
            pageCount = (pageCount < 1 ? 1 : pageCount);

        }
        catch(Exception e){}

        return ;
    }

    //设置属性
    protected void m_setAttr(HttpServletRequest request)
    {
        vct = (vct==null ? new Vector() : vct);
        request.setAttribute("vct",vct);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("outFlag",outFlag);
        request.setAttribute("pageCount",pageCount+"");
        request.setAttribute("currentPage",currentPage+"");
        request.setAttribute("selectAll",selectAll);
        request.setAttribute("strToday",strToday);

    }

    //查询
    protected void m_query(HttpServletRequest request)
    {
        if (outFlag.equals("0"))
            return ;

        vct = null;

        try
        {
                strFields = "start_time,stop_time,event_name,event_location,event_subject,log_no,deal_flag";
                String strConditions = null;
                if (condition.length()<1)
                {
                    strConditions = "company_code='" + companyCode
                        + "' and user_unique_no='" + userUniqueNo
                        + "' and cur_date='" + strToday + "' order by start_time";
                }else
                {
                    strConditions = condition + " and (company_code='"+companyCode
                        +"' and user_unique_no='"+userUniqueNo
                        + "' and cur_date='" + strToday + "') order by start_time";
                }

                strTables = "sam_user_event";
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount;
                pageCount = (pageCount < 1 ? 1 : pageCount);
                currentPage = (currentPage > pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);
        }
        catch(Exception e){}
    }


    protected void m_delAll(HttpServletRequest request)
    {
        try
        {
                // 删除所有符合条件的记录
                String strConditions = null;
                if (condition.length()<1)
                {
                    strConditions = "company_code='" + companyCode
                        + "' and user_unique_no='" + userUniqueNo
                        + "' and cur_date='" + strToday + "'";
                }else
                {
                    strConditions = condition + " and (company_code='"+companyCode
                        +"' and user_unique_no='"+userUniqueNo
                        + "' and cur_date='" + strToday + "')";
                }

                strTables = "sam_user_event";

                pageQuery.simpleUpdate("delete from " + strTables
                    + " where " + strConditions);

                currentPage = 1 ;
                pageCount = 1 ;
                selectAll = "0" ;
        }
        catch(Exception e)
        {
            outFlag = "0" ;
        }

    }



}