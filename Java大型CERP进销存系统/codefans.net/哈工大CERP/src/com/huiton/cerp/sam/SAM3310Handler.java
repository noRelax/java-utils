package com.huiton.cerp.sam;

/**
 * Title:        用户程序查询，删除
 * Description:  用户程序查询，删除
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

public class SAM3310Handler extends RequestHandlerSupport
{

    public SAM3310Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM3310Handler: processRequest()");
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

        //get selectAll
        String selectAll = request.getParameter("selectAll");
        selectAll = (selectAll==null?"":selectAll.trim());
        if (selectAll.length()<1)
          selectAll = "0" ;

        //get user info
        String role_code = request.getParameter("role_code");
        String role_name = request.getParameter("role_name");

        String bak_queryValue = request.getParameter("bak_queryValue");
        String bak_queryName = request.getParameter("bak_queryName");
        String bak_currentPage = request.getParameter("bak_currentPage");
        String bak_pageCount = request.getParameter("bak_pageCount");

        role_code = Show.getString(role_code);
        role_name = Show.getString(role_name);

        bak_currentPage = Show.getString(bak_currentPage);
        bak_pageCount = Show.getString(bak_pageCount);
        bak_queryValue = Show.getString(bak_queryValue);
        bak_queryName = Show.getString(bak_queryName);


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
        String queryProgCode = request.getParameter("queryProgCode");//查询值
        queryProgCode = (queryProgCode==null ? "" : queryProgCode.trim());
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

        if (queryProgCode.length()>0)
        {
             condition += " and prog_code like '%" + queryProgCode + "%'" ;
        }

        //if (condition.length()>0)
        {
            strConditions = "(" + condition + ") and company_code='"
                + companyCode + "' and role_code='"+role_code
                + "' order by sys_code,prog_code";
        }

        strTables = "sam_role_prog_v" ;
        strFields = "sys_code,prog_code,prog_name_cn,prog_name_en" ;

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode, SubsystemKeys.SAM, "SAM3310Handler");

            if (opFlag.equals("delete"))
            {
                // 删除选中记录
                String [] id = request.getParameterValues("id");
                if (id!=null)
                {
                  for (int ii=0;ii<id.length;ii++)
                  {
                    // split id into sys_code and prog_code
                    int sepPos = id[ii].indexOf("__");
                    sepPos = (sepPos<0 ? id[ii].length() : sepPos);

                    String mm_sys_code = id[ii].substring(0,sepPos);
                    String mm_prog_code =
                      id[ii].substring((sepPos==id[ii].length() ?
                        sepPos : sepPos+2));

                    Debug.println("id["+ii+"] = " +id[ii]);
                    String delSql = "delete from sam_role_prog "
                            + " where company_code='" + companyCode
                            + "' and role_code='" + role_code
                            + "' and sys_code='" + mm_sys_code
                            + "' and prog_code='" + mm_prog_code + "'";

                    pageQuery.simpleUpdate(delSql);
                  }

                  //inform 所有具有role_code的user通知用户权限已修改
                  sql = "update sam_user_info set prog_flag='N',menu_flag='N'"
                    + " where company_code='"+companyCode
                    + "' and user_unique_no in "
                    + " (select user_unique_no from sam_user_role "
                    + " where company_code='"+companyCode
                    + "' and role_code='"+role_code+"')" ;
                  Debug.println(sql);
                  pageQuery.addBatchSql(sql);

                  //更新角色对应的程序数目
                  sql = "update sam_role set prog_num=("+
                        "select count(*) from sam_role_prog where "+
                        "company_code='" + companyCode+
                        "' and role_code='" + role_code+
                        "') where company_code='" + companyCode+
                        "' and role_code='" + role_code+"'";
                  Debug.println(sql);
                  pageQuery.addBatchSql(sql);
                  pageQuery.exeBatchSql();

                }


                // show data
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount;
                //pageCount = (pageCount < 1 ? 1 : pageCount);
                currentPage = (currentPage > pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);

            }else if (opFlag.equals("delAll"))
            {
              // 删除所有符合条件的记录
                strConditions = condition
                  + " and company_code='" + companyCode
                  + "' and role_code='" + role_code + "' ";

                strTables = "sam_role_prog";

                boolean bFlag = pageQuery.simpleUpdate("delete from "
                    + strTables + " where " + strConditions);

                //inform 所有具有role_code的user通知用户权限已修改
                sql = "update sam_user_info set prog_flag='N',menu_flag='N'"
                    + " where company_code='"+companyCode
                    + "' and user_unique_no in "
                    + " (select user_unique_no from sam_user_role "
                    + " where company_code='"+companyCode
                    + "' and role_code='"+role_code+"')" ;
                Debug.println(sql);

                  pageQuery.addBatchSql(sql);

                  //更新角色对应的程序数目
                  sql = "update sam_role set prog_num=("+
                        "select count(*) from sam_role_prog where "+
                        "company_code='" + companyCode+
                        "' and role_code='" + role_code+
                        "') where company_code='" + companyCode+
                        "' and role_code='" + role_code+"'";
                  Debug.println(sql);
                  pageQuery.addBatchSql(sql);
                  pageQuery.exeBatchSql();

                outFlag = (bFlag ? "1" : "0");
                pageCount = 0 ;
                currentPage = 0 ;
                selectAll = "0" ;
                vct = new Vector();

            }else //query
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
        request.setAttribute("selectAll",selectAll);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("querySysCode",querySysCode);
        request.setAttribute("queryProgCode",queryProgCode);
        request.setAttribute("currentPage",currentPage+"");
        request.setAttribute("pageCount",pageCount+"");

        request.setAttribute("role_name",role_name);
        request.setAttribute("role_code",role_code);
        request.setAttribute("bak_currentPage",bak_currentPage);
        request.setAttribute("bak_pageCount",bak_pageCount);
        request.setAttribute("bak_queryValue",bak_queryValue);
        request.setAttribute("bak_queryName",bak_queryName);


        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}