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

public class ExitHandler extends RequestHandlerSupport
{

    public ExitHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----ExitHandler: processRequest()");
        HttpSession session = request.getSession();
        ModelManager mm = (ModelManager)session.getAttribute("mm");
        if (mm == null)  return null;

        CustomerWebImpl customer = mm.getCustomerWebImpl();
        if (customer==null) return null ;
        if (!customer.isLoggedIn()) return null ;

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        CerpSAM cs = new CerpSAM();
        cs.logout(companyCode,sessionCode);

        session.invalidate();

        return null;
    }
}