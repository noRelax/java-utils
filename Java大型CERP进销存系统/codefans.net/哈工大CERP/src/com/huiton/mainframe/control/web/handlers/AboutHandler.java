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

public class AboutHandler extends RequestHandlerSupport
{

    public AboutHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----AboutHandler: processRequest()");
        ModelManager mm = (ModelManager)request.getSession().getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String lang = customer.getLanguage();
        ResultSet rs = null;
        JdbOp dbOperator = new JdbOp("","sam");

        String companyName = "";

        String mySql = "select company_name from sam_company ";
        if (lang.equals("en")) //switch language
        {
            mySql = "select company_name_en from sam_company ";
        }
        mySql += " where company_code='"+companyCode+"'";

        Debug.println("mySql="+mySql);
        rs = dbOperator.getData(mySql);

        if (rs != null && rs.next())
        {
            companyName = rs.getString(1);
            companyName = (companyName==null ? "" : companyName.trim());
        }

        Debug.println("companyName="+companyName);
        request.setAttribute("company_name",companyName);
        Debug.println("abouthandler over");
        return null;
    }
}
