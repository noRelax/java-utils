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

public class DeskTopHandler extends RequestHandlerSupport
{

    public DeskTopHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----DeskTopHandler: processRequest()");
        HttpSession session = request.getSession();
        ModelManager mm = (ModelManager)session.getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String lang = customer.getLanguage();

        int pageSizeSchedule = 10; //工作日程显示最大数；
        int pageSizeHint = 10; //今日提示显示最大数；

        int pageSize = 0;
        String strFields = null;
        String strTables = null;
        String strConditions = null;
        String sql = null;

        Vector vctSchedule = null;
        Vector vctHint = null;

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode,"sam","DeskTopHandler");

            // 工作日程 schedule

            String strToday = (new CommonDate()).getYMD();

            strFields = "start_time,stop_time,event_name,event_location,event_subject,log_no";
            strTables = "sam_user_event";
            strConditions = "company_code='" + companyCode
                + "' and user_unique_no='" + userUniqueNo
                + "' and cur_date='" + strToday + "' order by start_time";

            Debug.println("strCondition="+strConditions);
            pageSize = pageSizeSchedule;
            pageQuery.getData(strFields,strTables,strConditions,pageSize);
            vctSchedule = pageQuery.dividePage(1,pageSize);

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
            pageSize = pageSizeHint;
            pageQuery.getData(strFields,strTables,strConditions,pageSize);
            vctHint = pageQuery.dividePage(1,pageSize);
        }
        catch (Exception e)
        {

        }

        vctHint = (vctHint==null ? new Vector() : vctHint);
        vctSchedule = (vctSchedule==null ? new Vector() : vctSchedule);

        request.setAttribute("vctSchedule",vctSchedule);
        request.setAttribute("vctHint",vctHint);
        return null;
    }
}
