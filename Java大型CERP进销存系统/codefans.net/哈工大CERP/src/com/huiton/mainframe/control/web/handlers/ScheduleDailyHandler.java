package com.huiton.mainframe.control.web.handlers;

/*
    author : zaj
    date : 2001/12/30
    program : ScheduleDailyHandler
    名称 : 工作日程
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
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ScheduleDailyHandler extends RequestHandlerSupport
{

    public ScheduleDailyHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        try
        {
            Debug.println("----ScheduleDailyHandler: processRequest()");
            ModelManager mm = (ModelManager)request.getSession().getAttribute("mm");
            CustomerWebImpl customer = mm.getCustomerWebImpl();

            String companyCode = customer.getCompanyCode();
            String userUniqueNo = customer.getUserUniqueNo();
            String sessionCode = customer.getSessionCode();
            String lang = customer.getLanguage();

            //opflag 操作方式
            String opFlag = request.getParameter("opFlag");
            opFlag = (opFlag==null ? "" : opFlag.trim());
            opFlag = (opFlag.length()<1 ? "query" : opFlag);

            ScheduleDaily sch = new ScheduleDaily(companyCode,userUniqueNo);

            if (opFlag.equalsIgnoreCase("query"))
                sch.query(request);
            else if (opFlag.equalsIgnoreCase("review"))
                sch.review(request);
            else if (opFlag.equalsIgnoreCase("modify"))
                sch.modify(request);
            else if (opFlag.equalsIgnoreCase("update"))
                sch.update(request);
            else if (opFlag.equalsIgnoreCase("addNew"))
                sch.addNew(request);
            else if (opFlag.equalsIgnoreCase("insert"))
                sch.insert(request);
            else if (opFlag.equalsIgnoreCase("delete"))
                sch.delete(request);
            else if (opFlag.equalsIgnoreCase("delAll"))
                sch.delAll(request);
            else if (opFlag.equalsIgnoreCase("insMore"))
                sch.insMore(request);
            else
                sch.query(request);

        }
        catch(Exception e)
        {
            Debug.println("sth is wrong now");
        }
        return null;
    }
}
