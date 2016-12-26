package com.huiton.cerp.sam;

/**
 * Title:        部门选择
 * Description:  部门选择
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
import com.huiton.cerp.sam.*;

public class SelectDeptHandler extends RequestHandlerSupport
{

    public SelectDeptHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SelectDeptHandler: processRequest()");
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
        String condition = "1=1";  // set query Condition to nothing
        String queryType = request.getParameter("queryType");//查询方式
        queryType = (queryType==null ? "" : queryType.trim());
        String queryValue = request.getParameter("queryValue");//查询值
        queryValue = (queryValue==null ? "" : queryValue.trim());
        String querySql = request.getParameter("querySql");
        querySql = (querySql==null ? "" : querySql.trim());
        String param = request.getParameter("param");//传入参数，由传入者处理
        param = (param==null ? "" : param.trim());

        String querySql_bak = querySql;

        //处理查询条件
        if (querySql.length()>0)
        {
            querySql = StringFunction.replaceAll(querySql,"||","=");
        }

        if (queryValue.length()>0 && queryType.length()>0)
        {
            if (queryType.equals("deptCode"))
            {
                condition += " and dept_code like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("deptName"))
            {
                condition += " and dept_name like '%" + queryValue + "%'" ;
            }
            else
            {
            }
        }

        if (querySql.length()>0)
        {
            condition += " and " + querySql ;
        }

        strConditions = "(" + condition + ") "
          + " and company_code='" + companyCode
          + "' order by dept_code_father,dept_code";

        strFields = "dept_code,dept_name" ;
        strTables = "sam_dept";

        Debug.println("----opFlag=" + opFlag);

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode,SubsystemKeys.SAM, "SelectDeptHandler");

            //查询
            pageQuery.getData(strFields,strTables,strConditions,pageSize);
            pageCount = pageQuery.pageCount ;
            currentPage = (currentPage>pageCount ? pageCount : currentPage);
            vct = pageQuery.dividePage(currentPage,pageSize);
        }
        catch (Exception e)
        {
          e.printStackTrace();
          outFlag = "0" ;
        }

        vct = (vct==null ? new Vector() : vct);
        request.setAttribute("vct",vct);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("param",param);
        request.setAttribute("querySql",querySql_bak);
        request.setAttribute("queryType",queryType);
        request.setAttribute("queryValue",queryValue);
        request.setAttribute("currentPage",currentPage+"");
        request.setAttribute("pageCount",pageCount+"");

        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}