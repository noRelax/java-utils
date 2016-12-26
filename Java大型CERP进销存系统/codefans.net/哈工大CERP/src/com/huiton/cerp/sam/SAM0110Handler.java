package com.huiton.cerp.sam;

/**
 * Title:        子系统定义
 * Description:  功能描述
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      BRITC
 * @author 张爱军
 * @version 1.0
 */

import com.huiton.cerp.pub.util.*;
import com.huiton.cerp.pub.util.functions.*;
import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.exceptions.DuplicateRecordException;
import com.huiton.mainframe.control.web.CustomerWebImpl;
import com.huiton.mainframe.control.web.ModelManager;
import com.huiton.mainframe.control.web.handlers.RequestHandlerSupport;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.pub.dbx.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SAM0110Handler extends RequestHandlerSupport
{

    public SAM0110Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM0110Handler: processRequest()");
        HttpSession session = request.getSession();
        ModelManager mm = (ModelManager)session.getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();

        String opFlag = null; //operation flag
        String outFlag = "1"; // outcome flag, 1 means succeeds
        Vector vct = null; //保存记录
        String sql = null;
        int currentPage = 1; //当前页
        int pageCount = 1; //总页数
        int pageSize = 16;

        ResultSet rs = null;
        String strFields = null;
        String strTables = null;
        String strConditions = null;

        opFlag = request.getParameter("opFlag");
        opFlag = (opFlag==null ? "" : opFlag.trim());
        opFlag = (opFlag.length()<1 ? "query" : opFlag);

        // get currentPage and pageCount
        String m_currentPage = request.getParameter("currentPage");
        m_currentPage = (m_currentPage==null ? "1" : m_currentPage.trim());
        currentPage = Integer.parseInt(m_currentPage);
        currentPage = (currentPage < 1 ? 1 : currentPage);
        m_currentPage = request.getParameter("pageCount");
        m_currentPage = (m_currentPage==null ? "1" : m_currentPage.trim());
        pageCount = Integer.parseInt(m_currentPage);
        pageCount = (pageCount < 1 ? 1 : pageCount);

        //查询条件
        String condition = "";  // set query Condition to nothing
        String queryType = request.getParameter("queryType");//查询方式
        queryType = (queryType==null ? "" : queryType.trim());
        String queryValue = request.getParameter("queryValue");//查询值
        queryValue = (queryValue==null ? "" : queryValue.trim());

        if (queryValue.length()<1 || queryType.length()<1)
        {
            condition = "";
        }
        else
        {
            if (queryType.equals("sysCode"))
            {
                condition = "sys_code like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("sysName"))
            {
                condition = "sys_name like '%" + queryValue + "%'" ;
            }
            else
            {
                condition = "";
            }

        }

        if (condition.length()<1)
        {
            strConditions = " company_code='"+companyCode
                + "' order by sys_code ";
        }else
        {
            strConditions = condition + " and (company_code='"
                + companyCode + "') order by sys_code";
        }

        strFields = "sys_code,sys_name" ;
        strTables = "scg_system";

        Debug.println("----opFlag=" + opFlag);

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode,SubsystemKeys.SAM, "SAM0110Handler");

            if (opFlag.equals("insert"))
            {
                //插入
                String sysCode = request.getParameter("sys_code");
                String sysName = request.getParameter("sys_name");

                sysCode = (sysCode==null ? "" : sysCode.trim());
                sysName = (sysName==null ? "" : sysName.trim());

                if (sysCode.length()<1 || sysName.length()<1 )
                {
                    outFlag = "0" ;
                }
                else
                {
                    sql = "insert into " + strTables
                        + "(company_code,sys_code,sys_name) values('"
                        + companyCode + "','" + sysCode + "','"
                        + sysName + "')";

                    Debug.println("--------sql="+sql);

                    if (!pageQuery.simpleUpdate(sql))
                        outFlag = "0" ;
                }

                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount ;
                currentPage = (currentPage>pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);
            }
            else if (opFlag.equals("update")) //更新
            {
                String sysCode = request.getParameter("sys_code");
                String sysName = request.getParameter("sys_name");

                sysCode = (sysCode==null ? "" : sysCode.trim());
                sysName = (sysName==null ? "" : sysName.trim());

                if (sysCode.length()<1 || sysName.length()<1 )
                {
                    outFlag = "0" ;
                }
                else
                {
                    sql = "update " + strTables
                        + " set sys_name='" + sysName
                        + "' where company_code='" + companyCode
                        + "' and sys_code='" + sysCode + "'";

                    Debug.println("--------sql="+sql);

                    if (!pageQuery.simpleUpdate(sql))
                        outFlag = "0" ;
                }
            }
            else if (opFlag.equals("delete"))
            {
                String sysCode = request.getParameter("sys_code");
                sysCode = (sysCode==null ? "" : sysCode.trim());

                if (sysCode.length()>0)
                {
                    sql = " select 1 from scg_program "
                        + " where company_code='" + companyCode
                        + "' and sys_code='" + sysCode
                        + "' " ;

                    Debug.println("--------sql="+sql);

                    rs = pageQuery.getData(sql);

                    if (rs != null && rs.next())
                    {
                        outFlag = "0" ; //公司占用
                    }
                    else
                    {
                        sql = "delete from " +  strTables
                            + " where company_code='" + companyCode
                            + "' and sys_code='" + sysCode + "'";

                        Debug.println("--------sql="+sql);
                        pageQuery.simpleUpdate(sql);
                    }
                }

                //query
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount ;
                currentPage = (currentPage>pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);
            }
            else //query
            {
                //查询
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount ;
                currentPage = (currentPage>pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);
            }

        }
        catch (Exception e)
        {
          e.printStackTrace();
          outFlag = "0" ;
        }

        vct = (vct==null ? new Vector() : vct);
        request.setAttribute("vct",vct);
        request.setAttribute("outFlag",outFlag);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("currentPage",currentPage+"");
        request.setAttribute("pageCount",pageCount+"");
        request.setAttribute("queryType",queryType);
        request.setAttribute("queryValue",queryValue);

        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}