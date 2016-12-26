package com.huiton.mainframe.control.web.handlers;

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
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TodayHintHandler extends RequestHandlerSupport
{

    public TodayHintHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----TodayHintHandler: processRequest()");
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
        String sql = null;

        int currentPage = 0; //当前页
        int pageCount = 0; //总页数

        opFlag = request.getParameter("opFlag");
        opFlag = (opFlag==null ? "" : opFlag.trim());
        opFlag = (opFlag.length()<1 ? "query" : opFlag);

        int pageSize = 15;

        String condition = "";  // set query Condition to nothing

        // get currentPage and pageCount
        String m_currentPage = request.getParameter("currentPage");
        m_currentPage = (m_currentPage==null ? "1" : m_currentPage.trim());
        currentPage = Integer.parseInt(m_currentPage);
        currentPage = (currentPage < 1 ? 1 : currentPage);

        m_currentPage = request.getParameter("pageCount");
        m_currentPage = (m_currentPage==null ? "1" : m_currentPage.trim());
        pageCount = Integer.parseInt(m_currentPage);
        pageCount = (pageCount < 1 ? 1 : pageCount);

        Debug.println("----opFlag=" + opFlag);

        String strFields = null;
        String strTables = null;
        String strConditions = null;

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode,"sam","TodayHintHandler");

            String strToday = (new CommonDate()).getYMD();

            // 今日提示 hint

            String db_type = SwitchSql.SQLSERVER;
            strFields = "cur_date,event_name,event_location,event_subject,log_no";
            strTables = "sam_user_event";
            strConditions = "company_code='" + companyCode
                + "' and user_unique_no='" + userUniqueNo
                + "' and cur_date>'" + strToday
                + "' and alert_days>0 and deal_flag='N' "
                + " and (" + SwitchSql.getHintSql(db_type,strToday)
                + ") order by cur_date,start_time" ;

            Debug.println("strCondition="+strConditions);
            pageQuery.getData(strFields,strTables,strConditions,pageSize);

            pageCount = pageQuery.pageCount;
            pageCount = (pageCount < 1 ? 1 : pageCount);
            currentPage = (currentPage > pageCount ? pageCount : currentPage);
            vct = pageQuery.dividePage(currentPage,pageSize);
        }
        catch (Exception e)
        {

        }

        vct = (vct==null ? new Vector() : vct);
        request.setAttribute("vct",vct);
        return null;
    }
}
