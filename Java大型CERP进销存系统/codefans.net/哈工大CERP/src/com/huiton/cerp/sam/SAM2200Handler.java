package com.huiton.cerp.sam;

/**
 * Title:        部门定义
 * Description:  部门定义
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

public class SAM2200Handler extends RequestHandlerSupport
{

    public SAM2200Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM2200Handler: processRequest()");
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
            else  if (queryType.equals("deptCodeFather"))
            {
                condition += " and dept_code_father like '%"
                  + queryValue + "%'";
            }
            else  if (queryType.equals("deptNameFather"))
            {
                condition += " and dept_name_father like '%"
                  + queryValue + "%'";
            }
            else  if (queryType.equals("masterPositionCode"))
            {
                condition += " and master_position_code like '%"
                  + queryValue + "%'";
            }
            else  if (queryType.equals("masterPositionName"))
            {
                condition += " and master_position_name like '%"
                  + queryValue + "%'";
            }
            else
            {
            }
        }

        strConditions = "(" + condition + ") "
          + " and company_code='" + companyCode
          + "' order by dept_name_father,dept_code";

        strFields = "dept_code,dept_name,dept_name_brief,dept_name_father,"
          + "master_position_name,dept_code_father,master_position_code" ;

        strTables = "sam_dept_v";

        Debug.println("----opFlag=" + opFlag);

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode,SubsystemKeys.SAM, "SAM2200Handler");

            if (opFlag.equals("insert"))
            {
                //插入
                String dept_code = request.getParameter("dept_code");
                String dept_name = request.getParameter("dept_name");
                String dept_name_brief =
                  request.getParameter("dept_name_brief");
                String dept_code_father =
                  request.getParameter("dept_code_father");
                String master_position_code =
                  request.getParameter("master_position_code");

                dept_code = Show.getString(dept_code);
                dept_name = Show.getString(dept_name);
                dept_name_brief = Show.getString(dept_name_brief);
                dept_code_father = Show.getString(dept_code_father);
                master_position_code = Show.getString(master_position_code);

                if (dept_code.length()<1 || dept_name.length()<1 )
                {
                    outFlag = "0" ;
                }
                else
                {
                    sql = "insert into sam_dept "
                        + "(company_code,dept_code,dept_name,dept_name_brief,"
                        + "dept_code_father,master_position_code) values('"
                        + companyCode + "','" + dept_code + "','"
                        + dept_name + "','" + dept_name_brief + "','"
                        + dept_code_father + "','"
                        + master_position_code + "')";

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
                String dept_code = request.getParameter("dept_code");
                String dept_name = request.getParameter("dept_name");
                String dept_name_brief =
                  request.getParameter("dept_name_brief");
                String dept_code_father =
                  request.getParameter("dept_code_father");
                String master_position_code =
                  request.getParameter("master_position_code");

                dept_code = Show.getString(dept_code);
                dept_name = Show.getString(dept_name);
                dept_name_brief = Show.getString(dept_name_brief);
                dept_code_father = Show.getString(dept_code_father);
                master_position_code = Show.getString(master_position_code);

                if (dept_code.length()<1 || dept_name.length()<1 )
                {
                    outFlag = "0" ;
                }
                else
                {
                  //check recursive
                  SamUtil su = new SamUtil();
                  if (su.isDeptRecursive(companyCode,dept_code_father,dept_code))
                  {
                    //循环
                    sql = "update sam_dept set dept_name='" + dept_name
                      + "',dept_name_brief='" + dept_name_brief
                      + "',master_position_code='" + master_position_code
                      + "' where company_code='" + companyCode
                      + "' and dept_code='" + dept_code + "'" ;

                    if (!pageQuery.simpleUpdate(sql))
                      outFlag = "0" ;

                    request.setAttribute("dept_recusive","1");

                  }
                  else
                  {
                    sql = "update sam_dept set dept_name='" + dept_name
                      + "',dept_name_brief='" + dept_name_brief
                      + "',master_position_code='" + master_position_code
                      + "',dept_code_father='" + dept_code_father
                      + "' where company_code='" + companyCode
                      + "' and dept_code='" + dept_code + "'" ;

                    if (!pageQuery.simpleUpdate(sql))
                      outFlag = "0" ;
                  }
                }
            }
            else if (opFlag.equals("delete"))
            {
                String dept_code = request.getParameter("dept_code");
                dept_code = (dept_code==null ? "" : dept_code.trim());

                if (dept_code.length()>0)
                {
                    sql = " select 1 from sam_user_position "
                        + " where company_code='" + companyCode
                        + "' and dept_code='" + dept_code
                        + "' union "
                        + " select 1 from epd_address_book "
                        + " where company_code='" + companyCode
                        + "' and dept_code='" + dept_code
                        + "' union "
                        + " select 1 from sam_dept "
                        + " where company_code='" + companyCode
                        + "' and dept_code_father='" + dept_code
                        + "' " ;

                    rs = pageQuery.getData(sql);

                    if (rs != null && rs.next())
                    {
                        outFlag = "0" ; //公司占用
                    }
                    else
                    {
                        sql = "delete from sam_dept "
                            + " where company_code='" + companyCode
                            + "' and dept_code='" + dept_code + "'";

                        if (!pageQuery.simpleUpdate(sql))
                          outFlag = "0" ;
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