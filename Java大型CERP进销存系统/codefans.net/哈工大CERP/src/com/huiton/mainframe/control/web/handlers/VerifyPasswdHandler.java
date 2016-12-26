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

public class VerifyPasswdHandler extends RequestHandlerSupport
{

    public VerifyPasswdHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----VerifyPasswdHandler: processRequest()");
        HttpSession session = request.getSession();
        ModelManager mm = (ModelManager)session.getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String lang = customer.getLanguage();

        String oldPass = request.getParameter("old_pass");
        String newPass = request.getParameter("new_pass");
        oldPass = (oldPass==null ? "" : oldPass.trim());
        newPass = (newPass==null ? "" : newPass.trim());

        // get old data first
        JdbOp dbOperator = DBOperators.getJdbOp(request, sessionCode, "sam");
        String sql = "select user_pass,pass_mendable from sam_user_auth "
                + " where company_code='"+companyCode
                + "' and user_unique_no='"+userUniqueNo+"'";
        Debug.println(sql);

        String flag = null;
        // output flag; 1 mean succeed,0 means failed
        // 2 means has no right, 3 means password doesn't match

        ResultSet rs = dbOperator.getData(sql);
        if (rs != null && rs.next())
        {
            String userPass = rs.getString(1);
            String passMendable = rs.getString(2);
            userPass = (userPass==null ? "" : userPass.trim());
            passMendable = (passMendable==null ? "" : passMendable.trim());
            if (passMendable.equalsIgnoreCase("Y")) //whether has the right
            {
                if (userPass.equals(oldPass)) // pass ok
                {
                    sql = "update sam_user_auth "
                        + " set user_pass='"+newPass
                        + "' where company_code='"+companyCode
                        + "' and user_unique_no='"+userUniqueNo+"'";

                    // update
                    flag = (dbOperator.simpleUpdate(sql) ? "1" : "0");

                }else
                {
                   flag = "3"; // password dosn't match
                }
            }else
            {
                    flag = "2"; // no right
            }
        }else
        {
            flag = "9"; // db error
        }

        // 1 means succeed, 0 means failed

        Debug.println("flag="+flag);
        request.setAttribute("flag",flag);
        return null ;
    }
}
