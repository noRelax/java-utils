package com.huiton.cerp.sam;
/**
 * Title:        公用/内置帐号定义
 * Description:  定义公用/内置帐号
 * Copyright:    Copyright (c) 2002
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

public class SAM2600Handler extends RequestHandlerSupport
{

    public SAM2600Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM2600Handler: processRequest()");
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
        String bodyCode = request.getParameter("bodyCode");
        String bodyName = request.getParameter("bodyName");
        String authFlag = request.getParameter("authFlag");

        bodyCode = Show.getString(bodyCode);
        bodyName = Show.getString(bodyName);
        authFlag = Show.getString(authFlag);

        if (bodyCode.length()>0)
          condition += " and body_code like '%" + bodyCode + "%'" ;

        if (bodyName.length()>0)
          condition += " and body_name like '%" + bodyName + "%'" ;

        if (authFlag.length()>0)
          condition += " and auth_flag='" + authFlag + "'" ;

        strConditions = "(" + condition + ") "
          + " and company_code='" + companyCode
          + "' order by body_code";

        strFields = "user_unique_no,body_code,body_name,auth_flag" ;
        strTables = "epd_address_guest_v";

        Debug.println("----opFlag=" + opFlag);

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode,SubsystemKeys.SAM, "SAM2600Handler");

            if (opFlag.equals("addNew"))
            {
              //新增
            }
            else if (opFlag.equals("insert")||opFlag.equals("insMore"))
            {
                //插入
                String body_code = request.getParameter("body_code");
                String body_name = request.getParameter("body_name");
                String auth_flag = request.getParameter("auth_flag");

                body_code = Show.getString(body_code);
                body_name = Show.getString(body_name);
                auth_flag = Show.getString(auth_flag);

                if (auth_flag.length()<1)
                  auth_flag = "O";

                if (body_code.length()>0)
                {
                    String user_unique_no =
                      CerpGetNo.getNo(companyCode,"sam_user")+"";

                    sql = "insert into epd_address_book "
                        + "(company_code,user_unique_no,body_code,body_name,"
                        + " auth_flag,body_type4)" + " values('"
                        + companyCode + "','"
                        + user_unique_no + "','"
                        + body_code + "','"
                        + body_name + "','"
                        + auth_flag + "','Y')";

                    pageQuery.simpleUpdate(sql);

                }
                else
                {
                  outFlag = "0" ;
                }
            }
            else if (opFlag.equals("modify")) //修改
            {
              String user_unique_no = request.getParameter("user_unique_no");
              user_unique_no = Show.getString(user_unique_no);

              strConditions = "company_code='" + companyCode
                + "' and user_unique_no='" + user_unique_no + "'" ;

              pageQuery.getData(strFields,strTables,strConditions,1);
              vct = pageQuery.dividePage(1);

            }
            else if (opFlag.equals("update") || opFlag.equals("updMore")) //更新
            {
                String user_unique_no = request.getParameter("user_unique_no");
                String body_code = request.getParameter("body_code");
                String body_name = request.getParameter("body_name");
                String auth_flag = request.getParameter("auth_flag");

                user_unique_no = Show.getString(user_unique_no);
                body_code = Show.getString(body_code);
                body_name = Show.getString(body_name);
                auth_flag = Show.getString(auth_flag);

                if (auth_flag.length()<1)
                  auth_flag = "O" ;

                if (user_unique_no.length()>0)
                {
                    sql = "update epd_address_book "
                      + " set body_code='" + body_code
                      + "',body_name='" + body_name
                      + "',auth_flag='" + auth_flag
                      + "' where company_code='" + companyCode
                      + "' and user_unique_no='" + user_unique_no + "'" ;

                    pageQuery.simpleUpdate(sql);

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
        request.setAttribute("bodyCode",bodyCode);
        request.setAttribute("bodyName",bodyName);
        request.setAttribute("authFlag",authFlag);

        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}