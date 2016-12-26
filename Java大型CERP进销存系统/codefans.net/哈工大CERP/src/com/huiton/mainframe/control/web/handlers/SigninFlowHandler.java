 package com.huiton.mainframe.control.web.handlers;

/**
 * Title:        CERP≤‚ ‘øÚº‹
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author Œ‚Ω£
 * @version 1.0
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

import com.huiton.mainframe.control.exceptions.CERPEventException;
import com.huiton.mainframe.util.WebKeys;
import com.huiton.mainframe.util.tracer.Debug;

import com.huiton.mainframe.control.web.CustomerWebImpl;
import com.huiton.mainframe.control.web.ModelManager;
/**
 * SigninFlow Handler
 *
*/
public class SigninFlowHandler implements FlowHandler {


    public void doStart(HttpServletRequest request) {
    }

    public String processFlow(HttpServletRequest request)
        throws CERPEventException {
        Debug.println("SigninFlowHandler:processRequest");
        ModelManager mm = (ModelManager)request.getSession().getAttribute(WebKeys.ModelManagerKey);
        CustomerWebImpl customer = mm.getCustomerWebImpl();
        String nextScreen = null;
        if (customer.isLoggedIn()) {
            Debug.println("Login succeeded.");
            String targetScreen = (String)request.getSession().getAttribute(WebKeys.SigninTargetURL);
            if (targetScreen != null) return "TARGET_URL";
            else return "1";
        } else {
            Debug.println("Login failed.");
            return nextScreen = "2";
        }
    }

    public void doEnd(HttpServletRequest request) {

    }

}

