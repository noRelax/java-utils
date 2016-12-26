package com.huiton.cerp.sam;

/**
 * Title:        用户认证授权
 * Description:  用户认证授权(进入页面)
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      BRITC
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

public class SAM3300Handler extends RequestHandlerSupport
{

    public SAM3300Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM3300Handler: processRequest()");
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
        int pageSize = 16; //每页记录数
        int maxNum = 100 ; //每次可更新的最多用户数

        ResultSet rs = null;
        String strFields = null;
        String strTables = null;
        String strConditions = null;

        opFlag = request.getParameter("opFlag");
        opFlag = (opFlag==null ? "" : opFlag.trim());
        //刚进入时不查opFlag = (opFlag.length()<1 ? "query" : opFlag);

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

        String bodyName = request.getParameter("bodyName");
        String bodyCode = request.getParameter("bodyCode");
        String bodyType = request.getParameter("bodyType");
        String deptName = request.getParameter("deptName");
        String positionName = request.getParameter("positionName");
        String roleName = request.getParameter("roleName");
        String authFlag = request.getParameter("authFlag");

        bodyName = Show.getString(bodyName);
        bodyCode = Show.getString(bodyCode);
        bodyType = Show.getString(bodyType);
        deptName = Show.getString(deptName);
        positionName = Show.getString(positionName);
        roleName = Show.getString(roleName);
        authFlag = Show.getString(authFlag);

        condition = "1=1" ;
        if (bodyName.length()>0)
          condition += " and body_name like '%" + bodyName + "%'" ;

        if (bodyCode.length()>0)
          condition += " and body_code like '%" + bodyCode + "%'" ;

        if (deptName.length()>0)
          condition += " and dept_name like '%" + deptName + "%'" ;

        if (positionName.length()>0)
          condition += " and position_name like '%" + positionName + "%'" ;

        if (roleName.length()>0)
          condition += " and role_name like '%" + roleName + "%'" ;

        if (authFlag.length()>0)
          condition += " and auth_flag='" + authFlag + "'" ;
        else
          condition += " and (auth_flag='N' or auth_flag='Y')";

        Debug.println("condition=" + condition);

        //用户类别
        if (bodyType.length()>0)
          condition += " and " + bodyType + "='Y'" ;

        strConditions = condition + " and company_code='"
          + companyCode + "' order by user_unique_no";

        strFields = "distinct user_unique_no,body_code,body_name,auth_flag" ;
        strTables = "sam_user_info_all_v";

        Debug.println("----opFlag=" + opFlag);

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode,SubsystemKeys.SAM, "SAM3300Handler");

            if (opFlag.equals("delete"))
            {
                String user_unique_no = request.getParameter("user_unique_no");
                user_unique_no = Show.getString(user_unique_no);

                if (user_unique_no.length()>0)
                {
                    //删除
                    String[] delTables = {
                      "sam_user_auth",
                      "sam_user_event",
                      "sam_user_info",
                      "sam_user_menu",
                      "sam_user_prog",
                      "sam_user_prog_right",
                      "sam_user_role"};

                    for(int ii=0;ii<delTables.length;ii++)
                    {
                      sql = " delete from " + delTables[ii]
                        + " where company_code='" + companyCode
                        + "' and user_unique_no='" + user_unique_no + "' " ;

                      pageQuery.simpleUpdate(sql);
                    }

                    //去掉职位权限
                    sql = "update sam_user_position set own_right='N' "
                      + " where company_code='" + companyCode
                      + "' and user_unique_no='" + user_unique_no + "' " ;

                    pageQuery.simpleUpdate(sql);

                    //更新地址本
                    sql = "update epd_address_book set auth_flag='N' "
                      + " where company_code='" + companyCode
                      + "' and user_unique_no='" + user_unique_no + "' " ;

                    pageQuery.simpleUpdate(sql);
                }

                //查询
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount ;
                //pageCount = (pageCount<1?1:pageCount);
                currentPage = (currentPage>pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);

            }
            else if (opFlag.equals("update"))
            {
              //更新变更用户的权限和菜单,不再查
              sql = "select count(*) "
                + " from sam_user_info "
                + " where company_code='" + companyCode
                + "' and (prog_flag='N' or menu_flag='N')";

              rs = pageQuery.getData(sql);
              if (rs!=null&&rs.next())
              {
                pageSize = rs.getInt(1);
                //应该限制数量
                if (pageSize>maxNum)
                  pageSize = maxNum ;
              }
              else
              {
                pageSize = 0 ;
              }

              if (pageSize > 0)
              {
                pageQuery.getData("user_unique_no,prog_flag,menu_flag",
                  "sam_user_info",
                  "company_code='" + companyCode
                    + "' and (prog_flag='N' or menu_flag='N')",pageSize);

                vct = pageQuery.dividePage(1,pageSize);
                for(int ii=0;ii<vct.size();ii++)
                {
                  String[] mmValue = (String[])vct.elementAt(ii);
                  SamUserRight sur = new SamUserRight(companyCode,
                    mmValue[0],mmValue[1],mmValue[2]);

                  sur.update();
                }
                vct = new Vector() ;
              }
            }
            else if (opFlag.equals("updAll"))
            {
              //更新所有用户的权限和菜单,不再查
              sql = "select count(*) "
                + " from sam_user_info "
                + " where company_code='" + companyCode + "'" ;

              rs = pageQuery.getData(sql);
              if (rs!=null&&rs.next())
              {
                pageSize = rs.getInt(1);

                //应该限制数量
                if (pageSize > maxNum)
                  pageSize = maxNum ;
              }
              else
              {
                pageSize = 0 ;
              }

              if (pageSize > 0)
              {
                pageQuery.getData("user_unique_no",
                  "sam_user_info",
                  "company_code='" + companyCode + "'", pageSize);

                vct = pageQuery.dividePage(1,pageSize);
                for(int ii=0;ii<vct.size();ii++)
                {
                  String[] mmValue = (String[])vct.elementAt(ii);
                  SamUserRight sur = new SamUserRight(companyCode,
                    mmValue[0],null,null);

                  sur.forceUpdate();
                }
              }
              vct = new Vector() ;
            }
            else if (opFlag.equals("query"))
            {
                //查询
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount ;
                //pageCount = (pageCount<1?1:pageCount);
                currentPage = (currentPage>pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);
            }
            else
            {
              currentPage = 0 ;
              pageCount = 0 ;
            }
        }
        catch (Exception e)
        {
          e.printStackTrace();
          outFlag = "0";
        }

        vct = (vct==null ? new Vector() : vct);
        request.setAttribute("vct",vct);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("outFlag",outFlag);
        request.setAttribute("currentPage",currentPage+"");
        request.setAttribute("pageCount",pageCount+"");
        request.setAttribute("bodyName",bodyName);
        request.setAttribute("bodyCode",bodyCode);
        request.setAttribute("bodyType",bodyType);
        request.setAttribute("deptName",deptName);
        request.setAttribute("positionName",positionName);
        request.setAttribute("roleName",roleName);
        request.setAttribute("authFlag",authFlag);

        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}