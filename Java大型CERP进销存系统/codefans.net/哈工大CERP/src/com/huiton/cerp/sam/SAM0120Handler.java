package com.huiton.cerp.sam;

/**
 * Title:        SCG2200 程序定义
 * Description:  程序定义，包括增加，修改，删除
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

public class SAM0120Handler extends RequestHandlerSupport
{

    public SAM0120Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM0120Handler: processRequest()");
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
            else  if (queryType.equals("checkRight"))
            {
                condition += " and check_right='" + queryValue + "'" ;
            }
            else  if (queryType.equals("mendFlag"))
            {
                condition += " and mend_flag='" + queryValue + "'" ;
            }
            else  if (queryType.equals("progLevel"))
            {
                if (queryValue.indexOf("=")>-1 ||
                    queryValue.indexOf(">")>-1 ||
                    queryValue.indexOf("<")>-1)
                {
                    condition += " and prog_level" + queryValue ;
                }else
                {
                    condition += " and prog_level=" + queryValue ;
                }
            }
            else  if (queryType.equals("progValue"))
            {
                condition += " and prog_value like '%" + queryValue + "%'";

            }
            else  if (queryType.equals("nodeFlag"))
            {
                condition += " and node_flag='" + queryValue + "'";

            }
            else  if (queryType.equals("progCodeFather"))
            {
                condition += " and prog_code_father like '%" + queryValue + "%'";

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
                + "' order by sys_code,prog_code_father,prog_code";
        }
        else
        {
            strConditions = "company_code='" + companyCode
                + "' order by sys_code,prog_code_father,prog_code";
        }

        strTables = "scg_program" ;

        strFields = "sys_code,prog_code,prog_name_cn,prog_name_en,prog_value,"
            + "prog_params,prog_code_father,prog_level,check_right,rst_url,"
            + "mend_flag,oper_flag,node_flag,table_name";

        Debug.println("----opFlag=" + opFlag);

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode, SubsystemKeys.SAM, "SAM0120Handler");

            if (opFlag.equals("addNew")) //新增
            {

            }
            else if (opFlag.equals("insert") || opFlag.equals("insMore")) //插入
            {
                String sys_code= request.getParameter("sys_code");
                String prog_code = request.getParameter("prog_code");
                String prog_name_cn = request.getParameter("prog_name_cn");
                String prog_name_en = request.getParameter("prog_name_en");
                String prog_value = request.getParameter("prog_value");
                String prog_level = request.getParameter("prog_level");
                String check_right = request.getParameter("check_right");
                String oper_flag = request.getParameter("oper_flag");
                String rst_url = request.getParameter("rst_url");
                String node_flag = request.getParameter("node_flag");
                String prog_params = request.getParameter("prog_params");
                String table_name = request.getParameter("table_name");
                String prog_code_father =
                    request.getParameter("prog_code_father");

                String mend_flag = "Y" ; //用户增加的程序默认可维护

                sys_code = (sys_code==null ? "" : sys_code.trim());
                prog_code = (prog_code==null ? "" : prog_code.trim());
                prog_name_cn = (prog_name_cn==null ? "" : prog_name_cn.trim());
                prog_name_en = (prog_name_en==null ? "" : prog_name_en.trim());
                prog_value = (prog_value==null ? "" : prog_value.trim());
                prog_level = (prog_level==null ? "" : prog_level.trim());
                check_right = (check_right==null ? "" : check_right.trim());
                oper_flag = (oper_flag==null ? "" : oper_flag.trim());
                rst_url = (rst_url==null ? "" : rst_url.trim());
                node_flag = (node_flag==null ? "" : node_flag.trim());
                prog_params = (prog_params==null ? "" : prog_params.trim());
                table_name = (table_name==null ? "" : table_name.trim());
                prog_code_father =
                    (prog_code_father==null ? "" : prog_code_father.trim());

                //程序级别
                prog_level = (prog_level.length()<1 ? "0" : prog_level);

                //检查权限
                check_right = (check_right.length()<1 ? "N" : check_right);

                //非操作程序
                oper_flag = (oper_flag.length()<1 ? "N" : oper_flag);

                //菜单结点
                node_flag = (node_flag.length()<1 ? "N" : node_flag);

                if (sys_code.length()<1 || prog_code.length()<1 )
                {
                    outFlag = "0" ;
                }else
                {
                    boolean bFlag = false ; //SQL执行结果标记
                    if (node_flag.equals("N")) //可执行的程序
                    {
                        if (prog_value.length()<1)
                        {
                            outFlag = "0" ;
                        }
                        else
                        {
                            sql = "insert into scg_program(company_code,sys_code,"
                                + "prog_code,prog_name_cn,prog_name_en,node_flag,"
                                + "prog_value,check_right,oper_flag,rst_url,"
                                + "mend_flag,prog_level,prog_code_father,"
                                + "prog_params,table_name) " + " values('"
                                + companyCode + "','" + sys_code
                                + "','" + prog_code + "','" + prog_name_cn
                                + "','" + prog_name_en + "','" + node_flag
                                + "','" + prog_value + "','" + check_right
                                + "','" + oper_flag + "','" + rst_url
                                + "','" + mend_flag + "'," + prog_level
                                + ",'" + prog_code_father + "','"
                                + prog_params + "','" + table_name + "')" ;

                            Debug.println("sql="+sql);
                            bFlag = pageQuery.simpleUpdate(sql);
                            outFlag = (bFlag ? "1" : "0") ;
                        }

                    }
                    else  //菜单结点
                    {
                        sql = "insert into scg_program(company_code,sys_code,"
                            + "prog_code,prog_name_cn,prog_name_en,node_flag,"
                            + "mend_flag) "
                            + " values('" + companyCode + "','" + sys_code
                            + "','" + prog_code + "','" + prog_name_cn
                            + "','" + prog_name_en + "','" + node_flag
                            + "','" + mend_flag+ "')" ;

                        Debug.println(sql);
                        bFlag = pageQuery.simpleUpdate(sql);
                        outFlag = (bFlag ? "1" : "0") ;
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
                String prog_code = request.getParameter("prog_code");

                sys_code = (sys_code==null ? "" : sys_code.trim());
                prog_code = (prog_code==null ? "" : prog_code.trim());

                if (sys_code.length()>0 && prog_code.length()>0)
                {
                    sql = "company_code='" + companyCode
                        + "' and sys_code='" + sys_code
                        + "' and prog_code='" + prog_code + "'" ;

                    rs = pageQuery.getData(strFields,strTables,sql,1) ;
                    if (rs!=null && rs.next())
                    {
                        String prog_name_cn = rs.getString("prog_name_cn");
                        String prog_name_en = rs.getString("prog_name_en");
                        String prog_value = rs.getString("prog_value");
                        String prog_params = rs.getString("prog_params");
                        String prog_code_father =
                            rs.getString("prog_code_father");
                        String prog_level = rs.getString("prog_level");
                        String check_right = rs.getString("check_right");
                        String rst_url = rs.getString("rst_url");
                        String mend_flag = rs.getString("mend_flag");
                        String oper_flag = rs.getString("oper_flag");
                        String node_flag = rs.getString("node_flag");
                        String table_name = rs.getString("table_name");

                        prog_name_cn = (prog_name_cn==null ? "" : prog_name_cn.trim());
                        prog_name_en = (prog_name_en==null ? "" : prog_name_en.trim());
                        prog_value = (prog_value==null ? "" : prog_value.trim());
                        prog_level = (prog_level==null ? "" : prog_level.trim());
                        check_right = (check_right==null ? "" : check_right.trim());
                        oper_flag = (oper_flag==null ? "" : oper_flag.trim());
                        rst_url = (rst_url==null ? "" : rst_url.trim());
                        node_flag = (node_flag==null ? "" : node_flag.trim());
                        prog_params = (prog_params==null ? "" : prog_params.trim());
                        table_name = (table_name==null ? "" : table_name.trim());
                        prog_code_father =
                            (prog_code_father==null ? "" : prog_code_father.trim());

                        mend_flag = (mend_flag==null ? "" : mend_flag.trim());

                        request.setAttribute("sys_code",sys_code);
                        request.setAttribute("prog_code",prog_code);
                        request.setAttribute("prog_name_cn",prog_name_cn);
                        request.setAttribute("prog_name_en",prog_name_en);
                        request.setAttribute("prog_value",prog_value);
                        request.setAttribute("prog_level",prog_level);
                        request.setAttribute("check_right",check_right);
                        request.setAttribute("oper_flag",oper_flag);
                        request.setAttribute("rst_url",rst_url);
                        request.setAttribute("node_flag",node_flag);
                        request.setAttribute("prog_params",prog_params);
                        request.setAttribute("prog_code_father",prog_code_father);
                        request.setAttribute("mend_flag",mend_flag);
                        request.setAttribute("table_name",table_name);
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
                String prog_code = request.getParameter("prog_code");

                sys_code = (sys_code==null ? "" : sys_code.trim());
                prog_code = (prog_code==null ? "" : prog_code.trim());

                String node_flag = request.getParameter("node_flag");
                node_flag = (node_flag==null ? "" : node_flag.trim());

                if (sys_code.length()>0 && prog_code.length()>0)
                {
                  if (node_flag.equals("Y")) //菜单结点
                  {
                    String prog_name_cn = request.getParameter("prog_name_cn");
                    String prog_name_en = request.getParameter("prog_name_en");

                    prog_name_cn = Show.getString(prog_name_cn);
                    prog_name_en = Show.getString(prog_name_en);

                    sql = "update " + strTables
                      + " set prog_name_cn='" + prog_name_cn
                      + "', prog_name_en='" + prog_name_en
                      + "' where company_code='" + companyCode
                      + "' and sys_code='" + sys_code
                      + "' and prog_code='" + prog_code + "'";

                    Debug.println("sql="+sql);
                    if (!pageQuery.simpleUpdate(sql))
                      outFlag = "0" ;
                  }
                  else //程序
                  {
                    String prog_name_cn = request.getParameter("prog_name_cn");
                    String prog_name_en = request.getParameter("prog_name_en");
                    String prog_value = request.getParameter("prog_value");
                    String prog_level = request.getParameter("prog_level");
                    String check_right = request.getParameter("check_right");
                    String oper_flag = request.getParameter("oper_flag");
                    String rst_url = request.getParameter("rst_url");
                    String prog_params = request.getParameter("prog_params");
                    String table_name = request.getParameter("table_name");

                    table_name = (table_name==null ? "" : table_name.trim());
                    prog_name_cn = Show.getString(prog_name_cn);
                    prog_name_en = Show.getString(prog_name_en);
                    prog_value = Show.getString(prog_value);
                    check_right = Show.getString(check_right);
                    if (check_right.equals(""))
                      check_right = "N" ;

                    oper_flag = Show.getString(oper_flag);
                    if (oper_flag.equals(""))
                      oper_flag = "N" ;

                    rst_url = Show.getString(rst_url);
                    prog_params = Show.getString(prog_params);

                    String updateSQL = "update " + strTables
                                + " set prog_name_cn='" + prog_name_cn
                                + "',prog_name_en='" + prog_name_en
                                + "',prog_value='" + prog_value
                                + "',prog_level=" + prog_level
                                + ",oper_flag='" + oper_flag
                                + "',rst_url='" + rst_url
                                + "',prog_params='" + prog_params
                                + "',check_right='" + check_right
                                + "',table_name='" + table_name + "'"
                                + " where company_code='" + companyCode
                                + "' and sys_code='" + sys_code
                                + "' and prog_code='" + prog_code + "'";

                    Debug.println("updateSQl="+updateSQL);

                    String checkRight = null;
                    String progCodeFather = null ;

                    //get check_right first
                    sql = "select check_right,prog_code_father "
                      + " from scg_program "
                      + " where company_code='" + companyCode
                      + "' and sys_code='" + sys_code
                      + "' and prog_code='" + prog_code + "'";

                    Debug.println("sql="+sql);

                    rs = pageQuery.getData(sql);
		            if (rs != null && rs.next())
		            {
                      checkRight = rs.getString(1);
                      progCodeFather = rs.getString(2);

                      checkRight = Show.getString(checkRight);
                      progCodeFather = Show.getString(progCodeFather);

                      //检查权限有无变化
                      if (checkRight.equals(check_right))
                      {
                        if (!pageQuery.simpleUpdate(updateSQL))
                          outFlag = "0" ;
                      }
                      else  //有变化
                      {
                        //是否菜单
                        boolean  isLeaf = false;
                        sql = "select 1 from scg_main_menu "
                          + " where company_code='" + companyCode
                          + "' and prog_sys_code='" + sys_code
                          + "' and prog_code='" + prog_code + "'";

                        Debug.println("sql="+sql);

                        rs = pageQuery.getData(sql);
                        if (rs!=null&&rs.next())
                          isLeaf = true ;

                        if (check_right.equals("Y"))//须授权,原来不用授权n-->y
                        {
                          if (progCodeFather.length()<1)//无父
                          {
                            if (isLeaf)//是菜单
                            {
                              sql = "delete from scg_main_menu "
                                + " where company_code='" + companyCode
                                + "' and prog_sys_code='" + sys_code
                                + "' and prog_code='" + prog_code + "'";

                              Debug.println("sql="+sql);
                              pageQuery.simpleUpdate(sql);
                            }
                            if (!pageQuery.simpleUpdate(updateSQL))
                                outFlag = "0" ;
                          }
                          else //有父
                          {
                            if (isLeaf)
                            {
                              sql = "update sam_user_info "
                                + " set prog_flag='N',menu_flag='N'"
                                + " where company_code='" + companyCode + "'";
                            }
                            else
                            {
                              sql = "update sam_user_info "
                                + " set prog_flag='N'"
                                + " where company_code='" + companyCode + "'";
                            }
                            Debug.println("sql="+sql);

                            pageQuery.simpleUpdate(sql);

                            if (!pageQuery.simpleUpdate(updateSQL))
                                outFlag = "0" ;
                          }
                        }
                        else //无须授权,原来须授权y-->n
                        {
                          if (progCodeFather.length()<1)//父为空
                          {
                            if (isLeaf)//menu
                            {
                              sql = "update sam_user_info set menu_flag='N'"
                                + " wehre company_code='" + companyCode + "'";

                              Debug.println("sql="+sql);

                              pageQuery.simpleUpdate(sql);
                            }
                            sql = "delete from sam_role_prog "
                                + " where company_code='" + companyCode
                                + "' and sys_code='" + sys_code
                                + "' and prog_code='" + prog_code + "'";

                            Debug.println("sql="+sql);

                            pageQuery.simpleUpdate(sql);

                            sql = "delete from sam_position_prog "
                                + " where company_code='" + companyCode
                                + "' and sys_code='" + sys_code
                                + "' and prog_code='" + prog_code + "'";

                            Debug.println("sql="+sql);

                            pageQuery.simpleUpdate(sql);

                            sql = "delete from sam_user_prog "
                                + " where company_code='" + companyCode
                                + "' and sys_code='" + sys_code
                                + "' and prog_code='" + prog_code + "'";

                            Debug.println("sql="+sql);

                            pageQuery.simpleUpdate(sql);

                            sql = "delete from sam_user_prog_right "
                                + " where company_code='" + companyCode
                                + "' and sys_code='" + sys_code
                                + "' and prog_code='" + prog_code + "'";

                            Debug.println("sql="+sql);

                            pageQuery.simpleUpdate(sql);

                            if (!pageQuery.simpleUpdate(updateSQL))
                                outFlag = "0" ;

                          }
                          else //有父
                          {
                            if (isLeaf)
                            {
                              sql = "update sam_user_info set menu_flag='N'"
                                + " wehre company_code='" + companyCode + "'";

                              Debug.println("sql="+sql);

                              pageQuery.simpleUpdate(sql);
                            }
                            if (!pageQuery.simpleUpdate(updateSQL))
                                outFlag = "0" ;
                          }
                        }
                      }
                    }
                    else
                    {
                      outFlag = "0" ;
                    }
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
                String prog_code = request.getParameter("prog_code");

                sys_code = (sys_code==null ? "" : sys_code.trim());
                prog_code = (prog_code==null ? "" : prog_code.trim());

                if (sys_code.length()>0 && prog_code.length()>0)
                {
                    sql = " select 1 from scg_main_menu " //主菜单
                        + " where company_code='" + companyCode
                        + "' and prog_sys_code='" + sys_code
                        + "' and prog_code='" + prog_code + "'"
                        + " union "
                        + " select 1 from scg_program " //有子程序或不可修改
                        + " where company_code='" + companyCode
                        + "' and sys_code='" + sys_code
                        + "' and (prog_code_father='" + prog_code
                        + "' or (prog_code='" + prog_code
                        + "' and mend_flag='N'))" ;

                    Debug.println("sql="+sql);
                    rs = pageQuery.getData(sql);
                    if (rs!=null && rs.next())
                    {
                        outFlag = "0" ;
                    }
                    else
                    {
                        //check right or not
                        sql = "select check_right,prog_code_father "
                            + " from " + strTables
                            + " where company_code='" + companyCode
                            + "' and sys_code='" + sys_code
                            + "' and prog_code='" + prog_code + "'" ;

                        Debug.println("sql="+sql);
                        rs = pageQuery.getData(sql);
                        if (rs!=null && rs.next())
                        {
                            String mCheckRight = rs.getString(1);
                            String mProgCodeFather = rs.getString(2);

                            mCheckRight =
                                (mCheckRight==null ? "" : mCheckRight.trim());

                            mProgCodeFather = (mProgCodeFather==null ?
                                "" : mProgCodeFather.trim());

                            //delete relatives first
                            {
                                if (mProgCodeFather.length()<1 &&
                                    mCheckRight.equalsIgnoreCase("Y"))
                                {//可能已被授权
                                    //角色
                                    sql = "delete from sam_role_prog "
                                        + " where company_code='" + companyCode
                                        + "' and sys_code='" + sys_code
                                        + "' and prog_code='" + prog_code + "'" ;

                                    pageQuery.simpleUpdate(sql);

                                    //职位
                                    sql = "delete from sam_position_prog "
                                        + " where company_code='" + companyCode
                                        + "' and sys_code='" + sys_code
                                        + "' and prog_code='" + prog_code + "'" ;

                                    pageQuery.simpleUpdate(sql);

                                    //人员
                                    sql = "delete from sam_user_prog "
                                        + " where company_code='" + companyCode
                                        + "' and sys_code='" + sys_code
                                        + "' and prog_code='" + prog_code + "'" ;

                                    pageQuery.simpleUpdate(sql);

                                    //人员权限
                                    sql = "delete from sam_user_prog_right "
                                        + " where company_code='" + companyCode
                                        + "' and sys_code='" + sys_code
                                        + "' and prog_code='" + prog_code + "'" ;

                                    pageQuery.simpleUpdate(sql);

                                }

                                sql = "delete from " + strTables
                                    + " where company_code='" + companyCode
                                    + "' and sys_code='" + sys_code
                                    + "' and prog_code='" + prog_code + "'" ;

                                Debug.println("sql="+sql);
                                pageQuery.simpleUpdate(sql);
                            }
                        }

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
                currentPage = 0;
                pageCount = 0;
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