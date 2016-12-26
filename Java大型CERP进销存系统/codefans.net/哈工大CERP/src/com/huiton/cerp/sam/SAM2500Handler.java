package com.huiton.cerp.sam;

/**
 * Title:        角色定义
 * Description:  功能描述
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      BRITC
 * @author 王忠杰
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

public class SAM2500Handler extends RequestHandlerSupport
{

    public SAM2500Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM2500Handler: processRequest()");
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
            if (queryType.equals("roleCode"))
            {
                condition = "role_code like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("roleName"))
            {
                condition = "role_name like '%" + queryValue + "%'" ;
            }
            else
            {
                condition = "";
            }

        }

        if (condition.length()<1)
        {
            strConditions = " company_code='"+companyCode
                + "' order by role_code ";
        }else
        {
            strConditions = condition + " and (company_code='"
                + companyCode + "') order by role_code";
        }

        strFields = "role_code,role_name,role_desc,prog_num" ;
        strTables = "sam_role";

        Debug.println("----opFlag=" + opFlag);

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode,SubsystemKeys.SAM, "SAM2500Handler");

            if (opFlag.equals("insert"))
            {
                //插入
                String roleCode = request.getParameter("role_code");
                String roleName = request.getParameter("role_name");
                String roleDesc = request.getParameter("role_desc");
                String progNum  = request.getParameter("prog_num");

                roleCode = (roleCode==null ? "" : roleCode.trim());
                roleName = (roleName==null ? "" : roleName.trim());
                roleDesc = Show.getString(roleDesc);
                progNum = Show.getString(progNum);
                if(progNum.equals("")) progNum="0";

                if (roleCode.length()<1 || roleName.length()<1 )
                {
                    outFlag = "0" ;
                }
                else
                {
                    sql = "insert into " + strTables
                        + "(company_code,role_code,role_name,role_desc,prog_num) values('"
                        + companyCode + "','" + roleCode + "','"
                        + roleName + "','"+roleDesc+"',"+progNum+")";

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
                String roleCode = request.getParameter("role_code");
                String roleName = request.getParameter("role_name");
                String roleDesc = request.getParameter("role_desc");
                String progNum  = request.getParameter("prog_num");

                roleCode = (roleCode==null ? "" : roleCode.trim());
                roleName = (roleName==null ? "" : roleName.trim());
                roleDesc = Show.getString(roleDesc);
                progNum = Show.getString(progNum);
                if(progNum.equals("")) progNum="0";

                if (roleCode.length()<1 || roleName.length()<1 )
                {
                    outFlag = "0" ;
                }
                else
                {
                    sql = "update " + strTables
                        + " set role_name='" + roleName
                        + "',role_desc='"+ roleDesc
                        + "',prog_num="+ progNum
                        + " where company_code='" + companyCode
                        + "' and role_code='" + roleCode + "'";

                    Debug.println("--------sql="+sql);

                    if (!pageQuery.simpleUpdate(sql))
                        outFlag = "0" ;
                }
            }
            else if (opFlag.equals("delete"))
            {
                String roleCode = request.getParameter("role_code");
                roleCode = (roleCode==null ? "" : roleCode.trim());

                if (roleCode.length()>0)
                {
                    sql = " select 1 from sam_role_prog "
                        + " where company_code='" + companyCode
                        + "' and role_code='" + roleCode
                        + "' union "
                        + " select 1 from sam_user_role "
                        + " where company_code='" + companyCode
                        + "' and role_code='" + roleCode
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
                            + "' and role_code='" + roleCode + "'";

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