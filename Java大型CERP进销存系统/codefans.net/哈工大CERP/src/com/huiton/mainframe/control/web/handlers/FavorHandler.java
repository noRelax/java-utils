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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FavorHandler extends RequestHandlerSupport
{

    public FavorHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----FavorHandler: processRequest()");
        ModelManager mm = (ModelManager)request.getSession().getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String lang = customer.getLanguage();
        PageQuery pageQuery = DBOperators.getPageQuery(request,
            sessionCode, SubsystemKeys.SAM, "FavorHandler");

        String favorName = "favor_name_cn";

        if (lang.equalsIgnoreCase("en"))
        {
           favorName = "favor_name_en";
        }

        int pageSize = 10000;
        String mySql = null;
        String strTables = "sam_user_favor_v";

        // get favor _type first
        mySql = " user_unique_no='"+userUniqueNo+"'"
            + " and company_code='"+companyCode+"'"
            + " order by favor_type";

        pageQuery.getData("distinct favor_type",strTables,
            mySql,pageSize);

        Vector pageVector = pageQuery.dividePage(1, pageSize);
        request.setAttribute("pageVector_type",pageVector);

        mySql = " user_unique_no='"+userUniqueNo+"'"
            + " and company_code='"+companyCode+"'"
            + " order by favor_type,favor_index";

        pageQuery.getData(favorName+",prog_value,favor_type",
                            strTables,mySql,pageSize);
        pageVector = pageQuery.dividePage(1, pageSize);

        Debug.println("get vector,it has "+pageVector.size()+" records");
        request.setAttribute(WebKeys.PageVectorKey,pageVector);
        return null;
    }
}
