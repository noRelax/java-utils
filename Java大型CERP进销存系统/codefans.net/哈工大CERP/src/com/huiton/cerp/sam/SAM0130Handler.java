package com.huiton.cerp.sam;

/**
 * Title:        菜单定义
 * Description:  菜单定义，包括增加，修改，删除
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

public class SAM0130Handler extends RequestHandlerSupport
{

    public SAM0130Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM0130Handler: processRequest()");
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
        //进入时不查询
        //opFlag = (opFlag.length()<1 ? "query" : opFlag);

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
            if (queryType.equals("menuCode"))
            {
                condition += " and menu_code like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("menuCodeFather"))
            {
                condition += " and menu_code_father like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("progCode"))
            {
                condition += " and prog_code like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("menuNameCn"))
            {
                condition += " and menu_name_cn like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("menuNameEn"))
            {
                condition += " and menu_name_en like '%" + queryValue + "%'" ;
            }
            else
            {
                //do nothing
            }
        }

        if (condition.length()>0)
        {
            strConditions = "(" + condition + ") and company_code='"
                + companyCode
                + "' order by sys_code,menu_code_father,menu_index";
        }
        else
        {
            strConditions = "company_code='" + companyCode
                + "' order by sys_code,menu_code_father,menu_index";
        }

        strTables = "scg_main_menu" ;

        strFields = "sys_code,menu_code,menu_name_cn,menu_name_en,"
            + "menu_code_father,menu_level,menu_index,leaf_flag,prog_sys_code,"
            + "prog_code";

        Debug.println("----opFlag=" + opFlag);

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode, SubsystemKeys.SAM, "SAM0130Handler");

            if (opFlag.equals("addNew")) //新增
            {

            }
            else if (opFlag.equals("insert") || opFlag.equals("insMore")) //插入
            {
                String sys_code= request.getParameter("sys_code");
                String menu_code = request.getParameter("menu_code");
                String menu_name_cn = request.getParameter("menu_name_cn");
                String menu_name_en = request.getParameter("menu_name_en");
                String menu_index = request.getParameter("menu_index");
                String leaf_flag = request.getParameter("leaf_flag");
                String prog_sys_code = request.getParameter("prog_sys_code");
                String prog_code = request.getParameter("prog_code");
                String menu_code_father=request.getParameter("menu_code_father");

                sys_code = Show.getString(sys_code);
                menu_code = Show.getString(menu_code);
                menu_name_cn = Show.getString(menu_name_cn);
                menu_name_en = Show.getString(menu_name_en);
                menu_index = Show.getString(menu_index);
                leaf_flag = Show.getString(leaf_flag);
                prog_sys_code = Show.getString(prog_sys_code);
                menu_code_father = Show.getString(menu_code_father);
                if (menu_index.length()<1)
                  menu_index = "1" ;

                if (leaf_flag.length()<1)
                    leaf_flag = "N" ;

                if (sys_code.length()<1 || prog_code.length()<1 ||
                  menu_code.length()<1 || prog_sys_code.length()<1)
                {
                    outFlag = "0" ;
                }else
                {
                    boolean bFlag = false ; //SQL执行结果标记

                    if (menu_code_father.length()>0)
                    {
                      sql = "insert into " + strTables
                        + "(company_code,sys_code,menu_code,menu_name_cn,"
                        + "menu_name_en,menu_code_father,menu_level,"
                        + "menu_index,leaf_flag,prog_sys_code,prog_code)"
                        + " select "
                        + "'" + companyCode + "','" + sys_code
                        + "','" + menu_code + "','" + menu_name_cn
                        + "','" + menu_name_en + "','" + menu_code_father
                        + "',menu_level+1"
                        + ",'" + menu_index + "','" + leaf_flag
                        + "','" + prog_sys_code + "','" + prog_code
                        + "' from " + strTables + " where "
                        + " company_code='" + companyCode
                        + "' and sys_code='" + sys_code
                        + "' and menu_code='" + menu_code_father + "'" ;
                    }
                    else
                    {
                      sql = "insert into " + strTables
                        + "(company_code,sys_code,menu_code,menu_name_cn,"
                        + "menu_name_en,menu_code_father,menu_level,"
                        + "menu_index,leaf_flag,prog_sys_code,prog_code)"
                        + " values( "
                        + "'" + companyCode + "','" + sys_code
                        + "','" + menu_code + "','" + menu_name_cn
                        + "','" + menu_name_en + "','" + menu_code_father
                        + "',1"
                        + ",'" + menu_index + "','" + leaf_flag
                        + "','" + prog_sys_code + "','" + prog_code
                        + "')" ;
                    }

                    Debug.println(sql);
                    bFlag = pageQuery.simpleUpdate(sql);
                    outFlag = (bFlag ? "1" : "0") ;

                    //更新用户菜单
                    if (bFlag)
                    {
                      sql = "update sam_user_info set menu_flag='N'"
                        + " where company_code='" + companyCode + "'";

                      pageQuery.simpleUpdate(sql);
                    }
                }

                //查询
                if (outFlag.equals("1"))
                {
                    if (opFlag.equals("insert"))
                    {
                        pageQuery.getData(strFields,strTables,strConditions,
                            pageSize);
                        pageCount = pageQuery.pageCount ;
                        currentPage =
                            (currentPage>pageCount ? pageCount : currentPage);
                        vct = pageQuery.dividePage(currentPage,pageSize);
                    }
                }
            }
            else if (opFlag.equals("modify"))
            {
                String sys_code= request.getParameter("sys_code");
                String menu_code = request.getParameter("menu_code");

                sys_code = Show.getString(sys_code);
                menu_code = Show.getString(menu_code);

                if (sys_code.length()>0 && menu_code.length()>0)
                {
                    sql = "company_code='" + companyCode
                        + "' and sys_code='" + sys_code
                        + "' and menu_code='" + menu_code + "'" ;

                    rs = pageQuery.getData(strFields,strTables,sql,1) ;
                    if (rs!=null && rs.next())
                    {
                        String menu_name_cn = rs.getString("menu_name_cn");
                        String menu_name_en = rs.getString("menu_name_en");
                        String menu_code_father = rs.getString("menu_code_father");
                        String menu_index = rs.getString("menu_index");
                        String leaf_flag = rs.getString("leaf_flag");
                        String prog_sys_code = rs.getString("prog_sys_code");
                        String prog_code = rs.getString("prog_code");

                        menu_name_cn = Show.getString(menu_name_cn);
                        menu_name_en = Show.getString(menu_name_en);
                        menu_code_father = Show.getString(menu_code_father);
                        menu_index = Show.getString(menu_index);
                        leaf_flag = Show.getString(leaf_flag);
                        prog_sys_code = Show.getString(prog_sys_code);
                        prog_code = Show.getString(prog_code);

                        request.setAttribute("sys_code",sys_code);
                        request.setAttribute("menu_code",menu_code);
                        request.setAttribute("menu_name_cn",menu_name_cn);
                        request.setAttribute("menu_name_en",menu_name_en);
                        request.setAttribute("menu_code_father",menu_code_father);
                        request.setAttribute("menu_index",menu_index);
                        request.setAttribute("leaf_flag",leaf_flag);
                        request.setAttribute("prog_sys_code",prog_sys_code);
                        request.setAttribute("prog_code",prog_code);

                        if (leaf_flag.equals("N"))
                        {
                          sql = "select 1 from " + strTables
                            + " where company_code='" + companyCode
                            + "' and sys_code='" + sys_code
                            + "' and menu_code_father='" + menu_code + "'";

                          rs = pageQuery.getData(sql);
                          if (rs!=null&&rs.next())
                          {
                            request.setAttribute("has_child","1");
                          }
                        }
                    }
                    else
                    {
                        outFlag = "0" ;
                    }
                }
                else
                {
                    outFlag = "0" ;
                }

                if (outFlag.equals("0")) //修改失败 query
                {
                    pageQuery.getData(strFields,strTables,strConditions,pageSize);
                    pageCount = pageQuery.pageCount ;
                    currentPage = (currentPage>pageCount ? pageCount : currentPage);
                    vct = pageQuery.dividePage(currentPage,pageSize);
                }
            }
            else if (opFlag.equals("update") || opFlag.equals("updMore")) //更新
            {
                String sys_code= request.getParameter("sys_code");
                String menu_code = request.getParameter("menu_code");

                sys_code = Show.getString(sys_code);
                menu_code = Show.getString(menu_code);

                if (sys_code.length()>0 && menu_code.length()>0)
                {
                  //修改
                  String menu_name_cn = request.getParameter("menu_name_cn");
                  String menu_name_en = request.getParameter("menu_name_en");
                  String menu_index = request.getParameter("menu_index");
                  String leaf_flag = request.getParameter("leaf_flag");
                  String prog_sys_code = request.getParameter("prog_sys_code");
                  String prog_code = request.getParameter("prog_code");
                  String menu_code_father=request.getParameter("menu_code_father");

                  menu_name_cn = Show.getString(menu_name_cn);
                  menu_name_en = Show.getString(menu_name_en);
                  menu_index = Show.getString(menu_index);
                  leaf_flag = Show.getString(leaf_flag);
                  prog_sys_code = Show.getString(prog_sys_code);
                  menu_code_father = Show.getString(menu_code_father);
                  if (menu_index.length()<1)
                    menu_index = "1" ;

                  if (leaf_flag.length()<1)
                    leaf_flag = "N" ;

                  boolean bFlag = false ;
                  if (menu_code_father.length()<1)
                  {
                    sql = "update " + strTables
                        + " set menu_name_cn='" + menu_name_cn
                        + "',menu_name_en='" + menu_name_en
                        + "',menu_code_father='',menu_level=1"
                        + ",menu_index=" + menu_index
                        + ",leaf_flag='" + leaf_flag
                        + "',prog_sys_code='" + prog_sys_code
                        + "',prog_code='" + prog_code + "'"
                        + " where company_code='" + companyCode
                        + "' and sys_code='" + sys_code
                        + "' and menu_code='" + menu_code + "'" ;
                  }
                  else
                  {
                    sql = "update " + strTables
                        + " set menu_name_cn='" + menu_name_cn
                        + "',menu_name_en='" + menu_name_en
                        + "',menu_code_father='" + menu_code_father
                        + "',menu_level="
                        + "(select menu_level+1 from " + strTables
                        + " where company_code='" + companyCode
                        + "' and sys_code='" + sys_code
                        + "' and menu_code='" + menu_code_father + "')"
                        + ",menu_index=" + menu_index
                        + ",leaf_flag='" + leaf_flag
                        + "',prog_sys_code='" + prog_sys_code
                        + "',prog_code='" + prog_code + "'"
                        + " where company_code='" + companyCode
                        + "' and sys_code='" + sys_code
                        + "' and menu_code='" + menu_code + "'" ;
                  }

                  Debug.print("sql="+sql);
                  bFlag = pageQuery.simpleUpdate(sql);
                  if (bFlag)
                  {
                    sql = "update sam_user_info set menu_flag='N'"
                        + " where company_code='" + companyCode + "'";

                    pageQuery.simpleUpdate(sql);
                  }
                  else
                  {
                    outFlag = "0" ;
                  }
                }
                else
                {
                    outFlag = "0" ;
                }

                if (outFlag.equals("1")) //查询
                {
                    if (opFlag.equals("update"))
                    {
                        pageQuery.getData(strFields,strTables,strConditions,
                            pageSize);
                        pageCount = pageQuery.pageCount ;
                        currentPage = (currentPage>pageCount ?
                            pageCount : currentPage);
                        vct = pageQuery.dividePage(currentPage,pageSize);
                    }
                }
            }
            else if (opFlag.equals("delete")) //删除
            {
                String sys_code= request.getParameter("sys_code");
                String menu_code = request.getParameter("menu_code");

                sys_code = (sys_code==null ? "" : sys_code.trim());
                menu_code = (menu_code==null ? "" : menu_code.trim());

                if (sys_code.length()>0 && menu_code.length()>0)
                {
                    sql = " select 1 from scg_main_menu " //主菜单
                        + " where company_code='" + companyCode
                        + "' and sys_code='" + sys_code
                        + "' and menu_code_father='" + menu_code + "'" ;

                    Debug.println("sql="+sql);
                    rs = pageQuery.getData(sql);
                    if (rs!=null && rs.next())
                    {
                        outFlag = "0" ;
                    }
                    else
                    {
                        sql = "delete from " + strTables
                          + " where company_code='" + companyCode
                          + "' and sys_code='" + sys_code
                          + "' and menu_code='" + menu_code + "'" ;

                        Debug.println("sql="+sql);
                        pageQuery.simpleUpdate(sql);

                        sql = "delete from sam_user_menu "
                          + " where company_code='" + companyCode
                          + "' and sys_code='" + sys_code
                          + "' and menu_code='" + menu_code + "'" ;

                        Debug.println("sql="+sql);
                        pageQuery.simpleUpdate(sql);
                    }
                }

                //query
                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount ;
                currentPage = (currentPage>pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);
            }
            else if (opFlag.equals("query")) //查询
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
            e.printStackTrace();
            outFlag = "0" ;
        }

        vct = (vct==null ? new Vector() : vct);
        sysVct = (sysVct==null ? new Vector() : sysVct);
        request.setAttribute("sysVct",sysVct);
        request.setAttribute("vct",vct);
        request.setAttribute("outFlag",outFlag);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("querySysCode",querySysCode);
        request.setAttribute("queryType",queryType);
        request.setAttribute("queryValue",queryValue);
        request.setAttribute("currentPage",currentPage+"");
        request.setAttribute("pageCount",pageCount+"");

        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}