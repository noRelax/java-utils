package com.huiton.cerp.sam;

/**
 * Title:        登录用户定义
 * Description:
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      HUITON
 * @author
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

public class SAM2400Handler extends RequestHandlerSupport
{

    public SAM2400Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM2400Handler: processRequest()");
        HttpSession session = request.getSession();
        ModelManager mm = (ModelManager)session.getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();

        String opFlag = null; //operation flag
        String outFlag = "1"; // outcome flag, 1 means succeeds
        Vector vct = null; //保存记录
        Vector roleVct = null;
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
        String bodyName = request.getParameter("bodyName");
        String bodyCode = request.getParameter("bodyCode");
        String deptCode = request.getParameter("deptCode");

        bodyName = Show.getString(bodyName);
        bodyCode = Show.getString(bodyCode);
        deptCode = Show.getString(deptCode);

        if (bodyName.length()>0)
          condition += " and body_name like '%" + bodyName + "%'" ;

        if (bodyCode.length()>0)
          condition += " and body_code='" + bodyCode + "'" ;

        if (deptCode.length()>0)
          condition += " and dept_code='" + deptCode + "'" ;

        strConditions = "(" + condition + ") "
          + " and company_code='" + companyCode
          + "' order by dept_code,body_code";

        strFields = "user_unique_no,body_code,body_name,body_desc,auth_flag,dept_code";

        strTables = "epd_address_book";

        Debug.println("----opFlag=" + opFlag);

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode,SubsystemKeys.SAM, "SAM2400Handler");

            if (opFlag.equals("insert"))
            {
                //插入
                String body_code = request.getParameter("body_code");
                String body_name = request.getParameter("body_name");
                String body_desc = request.getParameter("body_desc");
                String dept_code = request.getParameter("dept_code");


                body_code = Show.getString(body_code);
                body_name = Show.getString(body_name);
                body_desc = Show.getString(body_desc);
                dept_code = Show.getString(dept_code);

                if (body_code.length()<1)
                  body_code = "O";

                if (body_code.length()>0)
                {
                    String user_unique_no =
                      CerpGetNo.getNo(companyCode,"sam_user")+"";

                    sql = "insert into epd_address_book "
                        + "(company_code,user_unique_no,body_code,body_name,"
                        + "body_desc,dept_code,auth_flag,hrm)" + " values('"
                        + companyCode + "','"
                        + user_unique_no + "','"
                        + body_code + "','"
                        + body_name + "','"
                        + body_desc + "','"
                        + dept_code + "','N','Y')";

                    pageQuery.addBatchSql(sql);
                    pageQuery.exeBatchSql();
                }
                else
                {
                  outFlag = "0" ;
                }
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount ;
                currentPage = (currentPage>pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage);
            }

            else if (opFlag.equals("update") ) //更新
            {
                String user_unique_no = request.getParameter("user_unique_no");
                String body_code = request.getParameter("body_code");
                String body_name = request.getParameter("body_name");
                String body_desc = request.getParameter("body_desc");
                String dept_code = request.getParameter("dept_code");

                body_code = Show.getString(body_code);
                body_name = Show.getString(body_name);
                body_desc = Show.getString(body_desc);
                user_unique_no = Show.getString(user_unique_no);
                dept_code = Show.getString(dept_code);

                if (body_code.length()<1)
                  body_code = "O" ;

                if (user_unique_no.length()>0)
                {
                    sql = "update epd_address_book "
                      + " set body_code='" + body_code
                      + "',body_name='" + body_name
                      + "',body_desc='" + body_desc
                      + "',dept_code='" + dept_code
                      + "' where company_code='" + companyCode
                      + "' and user_unique_no='" + user_unique_no + "'" ;

                    if (!pageQuery.simpleUpdate(sql))
                      outFlag = "0" ;
                }
                else
                {
                  outFlag = "0" ;
                }
            }
            else if (opFlag.equals("delete"))
            {
                String user_unique_no = request.getParameter("user_unique_no");
                user_unique_no = Show.getString(user_unique_no);

                if (user_unique_no.length()>0)
                {
                    sql = " select 1 from sam_user_info "
                        + " where company_code='" + companyCode
                        + "' and user_unique_no='" + user_unique_no + "'" ;

                    rs = pageQuery.getData(sql);

                    if (rs != null && rs.next())
                    {
                        outFlag = "0" ; //用户被授权
                    }
                    else
                    {
                        sql = " delete from epd_address_book "
                            + " where company_code='" + companyCode
                            + "' and user_unique_no='" + user_unique_no + "'";

                        pageQuery.addBatchSql(sql);
                        pageQuery.exeBatchSql();
                    }
                }

                //query
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount ;
                currentPage = (currentPage>pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage);
            }
            else //query
            {
                //查询
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount ;
                currentPage = (currentPage>pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage);
            }

            pageQuery.getData("user_unique_no,role_code","sam_user_role","company_code='"+companyCode+"'",100);
            roleVct = pageQuery.dividePage(1);
        }
        catch (Exception e)
        {
          e.printStackTrace();
          outFlag = "0" ;
        }

        vct = (vct==null ? new Vector() : vct);
        roleVct = (roleVct==null ? new Vector() : roleVct);
        request.setAttribute("vct",vct);
        request.setAttribute("roleVct",roleVct);
        request.setAttribute("outFlag",outFlag);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("currentPage",currentPage+"");
        request.setAttribute("pageCount",pageCount+"");

        request.setAttribute("bodyName",bodyName);
        request.setAttribute("bodyCode",bodyCode);
        request.setAttribute("deptCode",deptCode);

        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}
