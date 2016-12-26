package com.huiton.cerp.sam;

/**
 * Title:        用户权限查看
 * Description:  用户权限查看
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
import com.huiton.mainframe.control.web.handlers.*;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.pub.dbx.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SAM3350Handler extends RequestHandlerSupport
{

    public SAM3350Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM3350Handler: processRequest()");
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

        //get user info
        String body_code = request.getParameter("body_code");
        String body_name = request.getParameter("body_name");
        String user_unique_no = request.getParameter("user_unique_no");

        String bak_bodyName = request.getParameter("bak_bodyName");
        String bak_bodyCode = request.getParameter("bak_bodyCode");
        String bak_bodyType = request.getParameter("bak_bodyType");
        String bak_deptName = request.getParameter("bak_deptName");
        String bak_positionName = request.getParameter("bak_positionName");
        String bak_roleName = request.getParameter("bak_roleName");
        String bak_authFlag = request.getParameter("bak_authFlag");
        String bak_currentPage = request.getParameter("bak_currentPage");
        String bak_pageCount = request.getParameter("bak_pageCount");

        body_code = Show.getString(body_code);
        body_name = Show.getString(body_name);
        user_unique_no = Show.getString(user_unique_no);
        bak_currentPage = Show.getString(bak_currentPage);
        bak_pageCount = Show.getString(bak_pageCount);
        bak_bodyName = Show.getString(bak_bodyName);
        bak_bodyCode = Show.getString(bak_bodyCode);
        bak_bodyType = Show.getString(bak_bodyType);
        bak_deptName = Show.getString(bak_deptName);
        bak_positionName = Show.getString(bak_positionName);
        bak_roleName = Show.getString(bak_roleName);
        bak_authFlag = Show.getString(bak_authFlag);

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
            else if (queryType.equals("progNameCn"))
            {
                condition += " and prog_name_cn like '%" + queryValue + "%'" ;
            }
            else if (queryType.equals("progNameEn"))
            {
                condition += " and prog_name_en like '%" + queryValue + "%'" ;
            }
            else if (queryType.equals("progValue"))
            {
                condition += " and prog_value like '%" + queryValue + "%'" ;
            }
            else
            {
                //do nothing
            }
        }

        strConditions = "(" + condition + ") and company_code='"
          + companyCode
          + "' and user_unique_no='" + user_unique_no + "'"
          + " order by sys_code,prog_code";

        strTables = "sam_user_prog_right_v" ;
        strFields = "sys_code,prog_code,prog_name_cn,prog_name_en" ;

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode, SubsystemKeys.SAM, "SAM3350Handler");

            if (opFlag.equals("update")) //更新该用户
            {
              SamUserRight sur = new SamUserRight(companyCode,user_unique_no);
              sur.update();
            }
            else if (opFlag.equals("updAll")) //更新所有
            {
              sql = "select user_unique_no,prog_flag,menu_flag "
                + " from sam_user_info "
                + " where company_code='" + companyCode
                + "' and (prog_flag='N' or menu_flag='N')";

              rs = pageQuery.getData(sql);

              if (rs!=null&&rs.next())
              {
                SamUserRight sur = new SamUserRight(companyCode,rs.getString(1),
                  rs.getString(2),rs.getString(3));
                sur.update();
              }
            }
            else if (opFlag.equals("fUpdate")) //强制更新该用户
            {
              SamUserRight sur = new SamUserRight(companyCode,user_unique_no,
                null , null);
              sur.forceUpdate();
            }
            else if (opFlag.equals("fUpdAll")) //强制更新所有用户
            {
              sql = "select user_unique_no "
                + " from sam_user_info "
                + " where company_code='" + companyCode + "'";

              rs = pageQuery.getData(sql);

              if (rs!=null&&rs.next())
              {
                SamUserRight sur = new SamUserRight(companyCode,rs.getString(1),
                  null ,null);
                sur.forceUpdate();
              }
            }
            else //查询
            {
              pageQuery.getData(strFields,strTables,strConditions,pageSize);
              pageCount = pageQuery.pageCount ;
              //pageCount = (pageCount < 1 ? 1 : pageCount);
              currentPage = (currentPage>pageCount ? pageCount : currentPage);
              vct = pageQuery.dividePage(currentPage,pageSize);
            }

            //取得系统代码和名称
            int sysSize = 100; //子系统数目
            sql = "company_code='" + companyCode + "'" ;
            pageQuery.getData("sys_code,sys_name","scg_system",sql,sysSize);
            sysVct = pageQuery.dividePage(1,sysSize);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            outFlag = "0";
        }

        vct = (vct==null ? new Vector() : vct);
        sysVct = (sysVct==null ? new Vector() : sysVct);
        request.setAttribute("sysVct",sysVct);
        request.setAttribute("vct",vct);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("querySysCode",querySysCode);
        request.setAttribute("queryType",queryType);
        request.setAttribute("queryValue",queryValue);
        request.setAttribute("currentPage",currentPage+"");
        request.setAttribute("pageCount",pageCount+"");

        request.setAttribute("body_name",body_name);
        request.setAttribute("body_code",body_code);
        request.setAttribute("user_unique_no",user_unique_no);
        request.setAttribute("bak_currentPage",bak_currentPage);
        request.setAttribute("bak_pageCount",bak_pageCount);
        request.setAttribute("bak_bodyName",bak_bodyName);
        request.setAttribute("bak_bodyCode",bak_bodyCode);
        request.setAttribute("bak_bodyType",bak_bodyType);
        request.setAttribute("bak_deptName",bak_deptName);
        request.setAttribute("bak_positionName",bak_positionName);
        request.setAttribute("bak_roleName",bak_roleName);
        request.setAttribute("bak_authFlag",bak_authFlag);

        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}