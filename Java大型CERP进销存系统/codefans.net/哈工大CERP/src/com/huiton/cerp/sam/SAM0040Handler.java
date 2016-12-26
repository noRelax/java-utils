package com.huiton.cerp.sam;

/**
 * Title:        数据库连接配置
 * Description:  数据库连接配置，包括增加，修改，删除
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
import com.huiton.pub.tools.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SAM0040Handler extends RequestHandlerSupport
{

    public SAM0040Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM0040Handler: processRequest()");
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
            if (queryType.equals("year"))
            {
                condition += " and year='" + queryValue + "'" ;
            }
            else  if (queryType.equals("dbCode"))
            {
                condition += " and db_code like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("dbName"))
            {
                condition += " and db_name like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("osName"))
            {
                condition += " and os_name like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("dbTypeName"))
            {
                condition += " and db_type_name like '%" + queryValue + "%'" ;
            }
            else  if (queryType.equals("driverManager"))
            {
                condition += " and driver_manager like '%" + queryValue + "%'" ;
            }
            else
            {
                //do nothing
            }
        }

        strConditions = "(" + condition + ") and company_code='"
                + companyCode
                + "' order by year desc,sys_code";

        strTables = "scg_db_config_v" ;

        strFields = "sys_code,year,os_name,db_type_name,"
            + "db_code,db_name,driver_manager,db_url,db_user,db_pass" ;

        Debug.println("----opFlag=" + opFlag);

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode, SubsystemKeys.SAM, "SAM0040Handler");

            if (opFlag.equals("addNew")) //新增
            {
              Vector osVct = null;
              Vector dbVct = null;
              Vector drVct = null;

              int vctSize = 100 ;
              pageQuery.getData("os_code,os_name","scg_os_type"
                ,"1=1 order by os_code",vctSize);

              osVct = pageQuery.dividePage(1);

              pageQuery.getData("db_type_code,db_type_name","scg_db_type",
                "1=1 order by db_type_code",vctSize);

              dbVct = pageQuery.dividePage(1);

              pageQuery.getData("driver_code,driver_manager",
                "scg_driver_manager",
                "1=1 order by driver_code",vctSize);

              drVct = pageQuery.dividePage(1);

              request.setAttribute("osVct",osVct);
              request.setAttribute("dbVct",dbVct);
              request.setAttribute("drVct",drVct);

            }
            else if (opFlag.equals("insert") || opFlag.equals("insMore")) //插入
            {
                String sys_code= request.getParameter("sys_code");
                String year = request.getParameter("year");
                String os_code = request.getParameter("os_code");
                String db_type_code = request.getParameter("db_type_code");
                String db_code = request.getParameter("db_code");
                String db_name = request.getParameter("db_name");
                String driver_manager = request.getParameter("driver_manager");
                String db_url = request.getParameter("db_url");
                String db_user=request.getParameter("db_user");
                String db_pass=request.getParameter("db_pass");

                sys_code = Show.getString(sys_code);
                year = Show.getString(year);
                os_code = Show.getString(os_code);
                db_type_code = Show.getString(db_type_code);
                db_code = Show.getString(db_code);
                db_name = Show.getString(db_name);
                driver_manager = Show.getString(driver_manager);
                db_url = Show.getString(db_url);
                db_user = Show.getString(db_user);
                db_pass = Show.getString(db_pass);
                db_pass = cerp_crypt.get_encoded_pass(db_pass);

                if (sys_code.length()<1 || year.length()<1 )
                {
                    outFlag = "0" ;
                }else
                {
                    sql = "insert into scg_db_config(company_code,sys_code,"
                      + "year,os_code,db_type_code,db_code,db_name,"
                      + "driver_manager,db_url,db_user,db_pass) values('"
                      + companyCode + "','" + sys_code + "','" + year + "','"
                      + os_code + "','" + db_type_code + "','" + db_code
                      + "','" + db_name + "','" + driver_manager + "','"
                      + db_url + "','" + db_user + "','" + db_pass + "')";

                    if (!pageQuery.simpleUpdate(sql))
                      outFlag = "0" ;
                }

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
            else if (opFlag.equals("modify"))
            {
              String sys_code= request.getParameter("sys_code");
              String year = request.getParameter("year");

              sys_code = Show.getString(sys_code);
              year = Show.getString(year);

              if (sys_code.length()>0 && year.length()>0)
              {
                strFields = "sys_code,year,db_code,db_name,os_code,"
                  + "db_type_code,driver_manager,db_url,db_user,db_pass";

                strTables = "scg_db_config";

                strConditions = "company_code='" + companyCode
                  + "' and sys_code='" + sys_code
                  + "' and year='" + year + "'";

                pageQuery.getData(strFields,strTables,strConditions,1);
                vct = pageQuery.dividePage(1);

              }

              Vector osVct = null;
              Vector dbVct = null;
              Vector drVct = null;

              int vctSize = 100 ;
              pageQuery.getData("os_code,os_name","scg_os_type"
                ,"1=1 order by os_code",vctSize);

              osVct = pageQuery.dividePage(1);

              pageQuery.getData("db_type_code,db_type_name","scg_db_type",
                "1=1 order by db_type_code",vctSize);

              dbVct = pageQuery.dividePage(1);

              pageQuery.getData("driver_manager",
                "scg_driver_manager",
                "1=1 order by driver_manager",vctSize);

              drVct = pageQuery.dividePage(1);

              request.setAttribute("osVct",osVct);
              request.setAttribute("dbVct",dbVct);
              request.setAttribute("drVct",drVct);

            }
            else if (opFlag.equals("update") || opFlag.equals("updMore")) //更新
            {
                String sys_code= request.getParameter("sys_code");
                String year = request.getParameter("year");
                String os_code = request.getParameter("os_code");
                String db_type_code = request.getParameter("db_type_code");
                String db_code = request.getParameter("db_code");
                String db_name = request.getParameter("db_name");
                String driver_manager = request.getParameter("driver_manager");
                String db_url = request.getParameter("db_url");
                String db_user=request.getParameter("db_user");
                String db_pass=request.getParameter("db_pass");

                sys_code = Show.getString(sys_code);
                year = Show.getString(year);
                os_code = Show.getString(os_code);
                db_type_code = Show.getString(db_type_code);
                db_code = Show.getString(db_code);
                db_name = Show.getString(db_name);
                driver_manager = Show.getString(driver_manager);
                db_url = Show.getString(db_url);
                db_user = Show.getString(db_user);
                db_pass = Show.getString(db_pass);
                db_pass = cerp_crypt.get_encoded_pass(db_pass);

                if (sys_code.length()<1 || year.length()<1 )
                {
                    outFlag = "0" ;
                }else
                {
                    sql = "update scg_db_config "
                      + " set os_code='" + os_code
                      + "',db_type_code='" + db_type_code
                      + "',db_code='" + db_code
                      + "',db_name='" + db_name
                      + "',driver_manager='" + driver_manager
                      + "',db_url='" + db_url
                      + "',db_user='" + db_user
                      + "',db_pass='" + db_pass + "' "
                      + " where company_code='" + companyCode
                      + "' and sys_code='" + sys_code
                      + "' and year='" + year + "'" ;

                    if (!pageQuery.simpleUpdate(sql))
                      outFlag = "0" ;
                }

                if (opFlag.equals("update"))
                {
                  pageQuery.getData(strFields,strTables,strConditions,
                    pageSize);
                  pageCount = pageQuery.pageCount ;
                  currentPage = (currentPage>pageCount?pageCount : currentPage);
                  vct = pageQuery.dividePage(currentPage,pageSize);
                }
            }
            else if (opFlag.equals("delete")) //删除
            {
                String sys_code= request.getParameter("sys_code");
                String year = request.getParameter("year");

                sys_code = (sys_code==null ? "" : sys_code.trim());
                year = (year==null ? "" : year.trim());

                if (sys_code.length()>0 && year.length()>0)
                {
                    sql = "delete from scg_db_config "
                          + " where company_code='" + companyCode
                          + "' and sys_code='" + sys_code
                          + "' and year='" + year + "'" ;

                    pageQuery.simpleUpdate(sql);
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