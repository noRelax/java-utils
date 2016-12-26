package com.huiton.cerp.sam;

/**
 * Title:        选择程序
 * Description:  选择程序
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

public class SelectProgHandler extends RequestHandlerSupport
{

    public SelectProgHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SelectProgHandler: processRequest()");
        HttpSession session = request.getSession();
        ModelManager mm = (ModelManager)session.getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String lang = customer.getLanguage();

        String opFlag = null; //operation flag
        String outFlag = "1"; // outcome flag, 1 means succeeds
        Vector vct = null; //保存记录
        Vector sysVct = null ;
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
        //进入时查询
        opFlag = (opFlag.length()<1 ? "query" : opFlag);

        //查询条件
        String alterSys = request.getParameter("alterSys");
        String querySql = request.getParameter("querySql");
        String param = request.getParameter("param");//传入参数，由传入者处理

        alterSys = (alterSys==null ? "" : alterSys.trim());
        alterSys = (alterSys.length()<1 ? "0" : alterSys.trim());//默认不可改
        querySql = (querySql==null ? "" : querySql.trim());
        param = (param==null ? "" : param.trim());

        String querySql_bak = querySql;

        //处理查询条件
        if (querySql.length()>0)
        {
            querySql = StringFunction.replaceAll(querySql,"||","=");
        }

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
        String querySysCode = request.getParameter("querySysCode");//子系统代码
        querySysCode = (querySysCode==null ? "" : querySysCode.trim());

        Debug.println("-----alterSys=" + alterSys+",querySql="+querySql
          +",param="+param+",querySysCode="+querySysCode);

        //查询条件的处理
        if (querySysCode.length()>0)
        {
            condition = "sys_code='" + querySysCode + "'";
        }
        else
        {
            condition = "1=1";
        }

        if (queryValue.length()>0 && queryType.length()>0)
        {
            if (queryType.equals("progCode"))
            {
                condition += " and prog_code like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("progValue"))
            {
                condition += " and prog_value like '%" + queryValue + "%'";

            }
            else  if (queryType.equals("progCodeFather"))
            {
                condition += " and prog_code_father like '%"
                  + queryValue + "%'";
            }
            else  if (queryType.equals("progNameEn"))
            {
                condition += " and prog_name_en like '%"
                        + queryValue + "%'" ;
            }
            else if (queryType.equals("progNameCn"))
            {
                  condition += " and prog_name_cn like '%"
                    + queryValue + "%'" ;
            }
            else
            {
                //do nothing
            }
        }

        if (querySql.length()>0)
        {
            if (condition.length()>0)
            {
                condition += " and " + querySql ;
            }
            else
            {
                condition += querySql;
            }
        }

        if (condition.length()>0)
        {
            strConditions = "(" + condition + ") and company_code='"
                + companyCode
                + "' order by sys_code,prog_code_father,prog_code";
        }
        else
        {
            strConditions = "company_code='" + companyCode
                + "' order by sys_code,prog_code_father,prog_code";
        }

        strTables = "scg_program" ;
        strFields = "sys_code,prog_code,prog_name_cn,prog_name_en" ;

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode, SubsystemKeys.SAM, "SelectProgHandler");

            if (opFlag.equals("query")) //查询
            {
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount ;
                currentPage = (currentPage>pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);
            }
            else //刚进入
            {
                //do nothing
                pageCount = 0 ;
                currentPage = 0 ;
            }

            //取得系统代码和名称
            int sysSize = 100; //子系统数目
            sql = "company_code='" + companyCode + "'" ;
            pageQuery.getData("sys_code,sys_name","scg_system",sql,sysSize);
            sysVct = pageQuery.dividePage(1,sysSize);
        }
        catch (Exception e)
        {
            Debug.println("error occurs when process SelectProghandler");
        }

        vct = (vct==null ? new Vector() : vct);
        sysVct = (sysVct==null ? new Vector() : sysVct);
        request.setAttribute("sysVct",sysVct);
        request.setAttribute("vct",vct);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("alterSys",alterSys);
        request.setAttribute("param",param);
        request.setAttribute("querySql",querySql_bak);
        request.setAttribute("querySysCode",querySysCode);
        request.setAttribute("queryType",queryType);
        request.setAttribute("queryValue",queryValue);
        request.setAttribute("currentPage",currentPage+"");
        request.setAttribute("pageCount",pageCount+"");

        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}