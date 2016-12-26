package com.huiton.mainframe.control.web.handlers;

/**
 * Title:        CERP测试框架
 * Description:  处理登录请求，用于验证用户，设置 CustomerWebImpl 对象状态
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author 吴剑
 * @version 1.0
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Enumeration;
import java.util.Vector;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import com.huiton.mainframe.util.WebKeys;
import com.huiton.mainframe.util.JSPUtil;
import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.mainframe.control.web.ModelManager;
import com.huiton.mainframe.control.web.CustomerWebImpl;
import com.huiton.pub.dbx.JdbOp;
import com.huiton.cerp.pub.util.SubsystemKeys;
import com.huiton.cerp.pub.util.functions.LookField;

public class ReloginHandler extends RequestHandlerSupport {

    public CERPEvent processRequest(HttpServletRequest request) throws Exception
    {
        Debug.println("Relogin Handler: processRequest()");
        String lang = request.getParameter("lang");

        lang = (lang==null ? "" : lang.trim());
        lang = (lang.length()<1 ? "zh" : lang);

        String user_code = request.getParameter("user_code");
        user_code = (user_code==null ? "" : user_code.trim());

        String year = request.getParameter("year");
        year = (year==null ? "" : year.trim());

        String company_code = request.getParameter("company_code");
        company_code = (company_code==null ? "" : company_code.trim());

        Debug.println("lang="+lang+",company_code="+company_code
            +",year="+year+",user_code="+user_code);

        request.setAttribute("company_code",company_code);
        request.setAttribute("user_code",user_code);
        request.setAttribute("year",year);
        request.setAttribute("lang",lang);
        return null;
    }
}


