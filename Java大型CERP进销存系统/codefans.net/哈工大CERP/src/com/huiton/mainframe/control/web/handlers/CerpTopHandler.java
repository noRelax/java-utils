// Decompiled by DJ v2.3.3.38 Copyright 2000 Atanas Neshkov  Date: 2001-11-28 11:24:09
// Home Page : http//members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   WFS0111Handler.java

package com.huiton.mainframe.control.web.handlers;

import com.huiton.cerp.pub.util.*;
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

public class CerpTopHandler extends RequestHandlerSupport
{

    public CerpTopHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----CerpTopHandler: processRequest()");
        ModelManager mm = (ModelManager)request.getSession().getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String userName = customer.getUserName();
        String userDept = customer.getUserDeptName();
        String lang = customer.getLanguage();
        String userCode = customer.getUserId();
        String year = customer.getCurrentYear();

        ResultSet rs = null;
        JdbOp dbOperator = new JdbOp("","sam");

        String companyName = "";
        String loginMsg = "";

        // switch language according to the lang
        String mySql = "select company_name from sam_company ";
        if (lang.equals("en"))
        {
            mySql = "select company_name_en from sam_company ";
        }
        mySql += " where company_code='"+companyCode+"'";

        rs = dbOperator.getData(mySql);

        if (rs != null && rs.next())
        {
            companyName = rs.getString(1);
            companyName = (companyName==null ? "" : companyName.trim());
        }

        request.setAttribute("company_code",companyCode);
        request.setAttribute("user_code",userCode);
        request.setAttribute("user_dept",userDept);
        request.setAttribute("year",year);
        request.setAttribute("company_name",companyName);
        request.setAttribute("user_name",userName);
        return null;
    }
}
