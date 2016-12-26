// Decompiled by DJ v2.3.3.38 Copyright 2000 Atanas Neshkov  Date: 2001-11-28 11:24:09
// Home Page : http//members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   WFS0111Handler.java

package com.huiton.mainframe.control.web.handlers;

import com.huiton.cerp.pub.util.DBOperators;
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

public class SetDefaultPageHandler extends RequestHandlerSupport
{

    public SetDefaultPageHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SetDefaultPageHandler: processRequest()");
        ModelManager mm = (ModelManager)request.getSession().getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String lang = customer.getLanguage();

        String sql = null;
        String m_url = request.getParameter("m_url");
        m_url = (m_url==null ? "" : m_url.trim());

        // trim the host if necessary
        Debug.println("before trimed m_url="+m_url);
        int startPos = m_url.indexOf("//");
        startPos = (startPos>-1 ? startPos+2 : 0); // get start Position
        int idxPos = m_url.indexOf("/",startPos);
        idxPos = (idxPos>-1 ? idxPos : 0 );

        m_url = m_url.substring(idxPos); // get substring
        Debug.println("after trimed m_url="+m_url);

        JdbOp dbOperator =  new JdbOp("", "sam");
        sql = "update sam_user_info set default_page='"+m_url+"' "
        		+ " where company_code='"+companyCode
                        + "' and user_unique_no='"+userUniqueNo+"'" ;

        Debug.println(sql);
        // 1 means succeed, 0 means failed
        String flag = (dbOperator.simpleUpdate(sql) ? "1" : "0");
        if (flag.equals("1"))
        {
            request.getSession().setAttribute("default_page",m_url);
            customer.setDefaultPage(m_url);
        }
        request.setAttribute("flag",flag);
        return null;
    }
}
