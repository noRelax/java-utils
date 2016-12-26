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

public class SwitchLanguageHandler extends RequestHandlerSupport
{

    public SwitchLanguageHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SwitchLanguageHandler: processRequest()");
        HttpSession session = request.getSession();
        ModelManager mm = (ModelManager)session.getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String lang = customer.getLanguage();
        String m_lang = null;

        if (lang.equalsIgnoreCase("en"))
        {
            m_lang = "zh";
        }else
        {
            m_lang = "en";
        }

        JdbOp dbOperator = new JdbOp("", "sam");
        String sql = " update sam_user_info set language_type='"+m_lang+"' "
        		+ " where company_code='"+companyCode
                        + "' and user_unique_no='"+userUniqueNo+"'" ;

        Debug.println(sql);

        // 1 means succeed, 0 means failed
        String flag = (dbOperator.simpleUpdate(sql) ? "1" : "0");
        if (flag.equals("1"))
        {
            customer.setLanguage(m_lang);
            session.setAttribute("language",m_lang);
            request.setAttribute("lang",m_lang);
        }else  // failed to mend it
        {
            request.setAttribute("lang",lang);
        }

        Debug.println("flag="+flag);
        return null;
    }
}
