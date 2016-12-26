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

public class MenuTreeHandler extends RequestHandlerSupport
{

    public MenuTreeHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----MenuTreeHandler: processRequest()");
        ModelManager mm = (ModelManager)request.getSession().getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String lang = customer.getLanguage();
        PageQuery pageQuery = DBOperators.getPageQuery(request,
            sessionCode, SubsystemKeys.SAM, "MenuTreeHandler");

        //deal with viewing user menu
        String m_no = request.getParameter("user_no");
        String opFlag = request.getParameter("opFlag");
        m_no = (m_no==null ? "" : m_no.trim());
        opFlag = (opFlag==null ? "" : opFlag.trim());

        if (opFlag.equals("review") && m_no.length()>0)
        {
            userUniqueNo = m_no;
        }

        String menuName = "menu_name_cn";

        if (lang.equalsIgnoreCase("en"))
        {
            menuName = "menu_name_en";
        }

        int pageSize = 10000;
        String mySql = null;
        mySql = " user_unique_no='"+userUniqueNo+"'"
            + " and company_code='"+companyCode+"'"
            + " order by menu_level,menu_index";

        pageQuery.getData("sys_code,menu_code,menu_code_father,menu_level,leaf_flag,"+menuName+",prog_value",
                            "sam_user_menu_v",mySql,pageSize);
        Vector pageVector = pageQuery.dividePage(1, pageSize);

        Debug.println("get vector,it has "+pageVector.size()+" records");
        request.setAttribute(WebKeys.PageVectorKey,pageVector);
        return null;
    }
}
