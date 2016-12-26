package com.huiton.mainframe.control.web.handlers;

import com.huiton.cerp.pub.util.functions.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Enumeration;
import java.util.Vector;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import com.huiton.cerp.pub.util.functions.*;
import com.huiton.mainframe.util.WebKeys;
import com.huiton.mainframe.util.JSPUtil;
import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.mainframe.control.web.ModelManager;
import com.huiton.mainframe.control.web.CustomerWebImpl;
import com.huiton.pub.dbx.JdbOp;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.cerp.pub.util.SubsystemKeys;
import com.huiton.cerp.pub.util.functions.*;

public class SigninHandler extends RequestHandlerSupport {

    public CERPEvent processRequest(HttpServletRequest request) throws Exception {
        Debug.println("Signin Handler: processRequest()");

        // 获得用户名、密码、公司代码、登录帐套年份
        String userCode =  request.getParameter(WebKeys.UsercodeKey);
        String password =  request.getParameter(WebKeys.PasswordKey);
        String companyCode = request.getParameter("company_code");
        String currentYear = request.getParameter("year");
        String lang = request.getParameter("lang");
        lang = ((lang==null || lang.equals("")) ? "zh" : lang.trim());

        // 从 ModelManager 获得 CustomerWebImpl 对象
        HttpSession session = request.getSession();
        ModelManager mm = (ModelManager)session.getAttribute(WebKeys.ModelManagerKey);
        CustomerWebImpl customer = mm.getCustomerWebImpl();
        customer.setLoggedIn(false);

        CerpSAM cs = new CerpSAM();
        cs.login(request,companyCode,userCode,password,currentYear);

        request.setAttribute("user_code",userCode);
        request.setAttribute("company_code",companyCode);
        request.setAttribute("year",currentYear);
        request.setAttribute("lang",lang);
        return null ;
    }
}


