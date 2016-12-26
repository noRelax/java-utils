/*
 * $Id: MainServlet.java,v 1.14.4.8 2001/03/15 00:40:02 brydon Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits r?erv?.
 */

package com.huiton.mainframe.control.web;

/**张爱军
 * 2002/01/24
 * 我在此类中加入了权限控制，超时判定，并可将超时用户自动导应引至登陆页面
 * 可改进之处：今后计划实现超时用户登陆后，进入用户要执行的程序
 * */
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.beans.Beans;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import com.huiton.mainframe.util.JNDINames;

import com.huiton.mainframe.control.exceptions.SigninFailedException;
import com.huiton.mainframe.control.exceptions.GeneralFailureException;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.mainframe.control.web.ModelManager;
import com.huiton.mainframe.control.web.CustomerWebImpl;
import com.huiton.mainframe.control.web.ScreenFlowXmlDAO;
import com.huiton.mainframe.control.web.RequestProcessor;
import com.huiton.mainframe.util.WebKeys;
import com.huiton.mainframe.util.JSPUtil;
import com.huiton.cerp.pub.util.DebugUtil;
import com.huiton.cerp.pub.util.functions.*;

public class MainServlet extends HttpServlet {

    private HashMap urlMappings;
    private CerpSAM cs = new CerpSAM();
    private static String signinScreen = null;
    private static String noRightScreen = null;
    private static String sessionTimeoutScreen = null;

    public void init()
    {
        Debug.println("MainServlet: Initializing");
        String requestMappingsURL = null;
        try
        {
            requestMappingsURL = getServletContext().getResource("/WEB-INF/xml/requestmappings.xml").toString();
        }
        catch (java.net.MalformedURLException ex)
        {
            Debug.println("ScreenFlowManager: initializing ScreenFlowManager malformed URL exception: " + ex);
        }
        urlMappings = ScreenFlowXmlDAO.loadRequestMappings(requestMappingsURL);
        getServletContext().setAttribute(WebKeys.URLMappingsKey, urlMappings);

        Debug.println("MainServlet: loaded urlMappings");
        String serverType = null;
        try
        {
            InitialContext ic = new InitialContext();
            serverType = (String)ic.lookup(JNDINames.SERVER_TYPE);
            getServletContext().setAttribute(WebKeys.ServerTypeKey, serverType);
        }
        catch (NamingException ex)
        {
            Debug.println("Server Type not specified in deployment descriptor: using default J2ee Security Adapter");
        }
        getScreenFlowManager();
        getRequestProcessor();

        signinScreen = getScreenFlowManager().getSigninScreen();
        //不用超时页面，直接显示登陆页面
        //sessionTimeoutScreen = getScreenFlowManager().getSessionTimeoutScreen();
        sessionTimeoutScreen = signinScreen ;

        noRightScreen = getScreenFlowManager().getNoRightScreen();
        Debug.println("signinScreen=" + signinScreen
            + ", sessionTimeoutScreen=" + sessionTimeoutScreen
            + ",noRightScreen=" + noRightScreen);
        Debug.println("MainServlet: Initialization complete");
    }

    public  void doPost(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }

    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        String progValue = request.getRequestURI(); //cerp/wfs/wfs2000
        String selectedURL = request.getPathInfo(); // for mapping /wfs/wfs2000

        // the current tomcat is resetting the outputstream so this is a workaround
        if ((selectedURL != null) && selectedURL.equals("/white")) return;

        //创建了一个ModelManager,作用是：构造一个model，使得web tier可以据此访问model objects，放在了session中。
        ScreenFlowManager screenManager = null;
        ModelManager modelManager= (ModelManager)request.getSession().getAttribute(WebKeys.ModelManagerKey);
        if ( modelManager == null )
        {
            try
            {
                //装载进入一个实例
                modelManager = (ModelManager)Beans.instantiate(this.getClass().getClassLoader(), "com.huiton.mainframe.control.web.ModelManager");
            }
            catch (Exception exc)
            {
                throw new ServletException ("Cannot create bean of class ModelManager");
            }
            session.setAttribute(WebKeys.ModelManagerKey, modelManager);
            modelManager.init(getServletContext(), session);
         }
         Debug.println("MainServlet: url " + selectedURL);

         // check if url is protected before processing request
         URLMapping current = getURLMapping(selectedURL);
         CustomerWebImpl customer = modelManager.getCustomerWebImpl();
         request.setAttribute("lang", customer.getLanguage());

         //process handlers 处理handlers
         if ((current != null) && current.requiresSignin())
         {
             String sessionCode = customer.getSessionCode();
             String companyCode = customer.getCompanyCode();
             String userUniqueNo = customer.getUserUniqueNo();

             //处理标签名称
             String lastSysCode = (String)session.getAttribute("mSysCode");
             String lastProgCode = (String)session.getAttribute("mProgCode");

             lastSysCode = (lastSysCode==null ? "" : lastSysCode.trim());
             lastProgCode = (lastProgCode==null ? "" : lastProgCode.trim());

             if (customer.isLoggedIn())
             {
                String [] value = cs.getSysAndProgCode(progValue,companyCode);
                String sysCode = value[0];
                String progCode = value[1];

                sysCode = (sysCode==null ? "" : sysCode.trim());
                progCode = (progCode==null ? "" : progCode.trim());

                Debug.println("--------------get sysCode="+sysCode+"'progCode="+progCode);
                if (!cs.hasSession(sessionCode))
                {
                    Debug.println("-----------会话超时，请重新登录/session timeout,please login again------");
                    //这儿最好加入显示页面
                    session.setAttribute(WebKeys.CurrentScreen, sessionTimeoutScreen);
                    session.setAttribute(WebKeys.SigninTargetURL, progValue);
                }
                else
                {

                    if (sysCode.equals(lastSysCode) && progCode.equals(lastProgCode))
                    {
                        doProcess(request);

                    }else
                    {
                        if (!cs.hasRight(sysCode,progCode,companyCode,userUniqueNo))
                        {
                            Debug.println("-----------您无权访问该页/no right,sorry------");
                            //这儿最好加入显示页面
                            session.setAttribute(WebKeys.CurrentScreen, noRightScreen);
                            session.setAttribute(WebKeys.SigninTargetURL, progValue);

                        }
                        else
                        {
                            cs.logProg(companyCode,userUniqueNo,sysCode,progCode);
                            session.setAttribute("mSysCode",sysCode);
                            session.setAttribute("mProgCode",progCode);

                            //保存子系统与程序代码
                            customer.setSysCode(sysCode);
                            customer.setProgCode(progCode);
                            customer.setInnerCondition(new InnerCondition(value[2]));

                            //设置程序参数
                            doProcess(request);
                         }
                     }
                 }
            } else
            {
                session.setAttribute(WebKeys.CurrentScreen, signinScreen);
                session.setAttribute(WebKeys.SigninTargetURL, progValue);
                Debug.println("MainServlet: Directing to login page " + signinScreen + "...");
            }
         }
         else
         {
             doProcess(request);
         }
         /*
           Default to the base language or the site.
           If a language is found in the session use that template.
         */
         Locale locale = JSPUtil.getLocale(request.getSession());

         HashMap templates = getScreenFlowManager().getTemplates(locale);
         String templateName = request.getParameter("template");
         Debug.println("-----------------in MainServlet: templateName: "+templateName);
         String nextScreen;

         Debug.println("MainServlet.doGet(): templates = " +
                      String.valueOf(templates!=null));
         DebugUtil.showHashMapRecord(templates);

         if(templateName==null)
            nextScreen = (String)templates.get("default");
         else
            nextScreen = (String)templates.get(templateName);

            //打开新的叶面：nextscreen
         getServletConfig().getServletContext().getRequestDispatcher(nextScreen).forward(request, response);
    }

    private void doProcess(HttpServletRequest request) throws ServletException {
        try
        {
            Debug.println("MainServlet.doProcess: entering...");
            getRequestProcessor().processRequest(request);
            getScreenFlowManager().getNextScreen(request);
        } catch (Throwable ex) {
            ex.printStackTrace();
            String className = ex.getClass().getName();
            String exceptionScreen = getScreenFlowManager().getExceptionScreen(className);
            Debug.println("MainServlet: target screen is: " + exceptionScreen);
            // put the exception in the request
            request.setAttribute("javax.servlet.jsp.jspException", ex);
            if (exceptionScreen != null) {
                request.getSession().setAttribute(WebKeys.CurrentScreen, exceptionScreen);
            } else {
                // send to general error screen
                Debug.println("MainServlet: unknown exception: " + className);
                throw new ServletException("MainServlet: unknown exception: " + className);
           }
       }
    }

    private RequestProcessor getRequestProcessor() {
         RequestProcessor rp = (RequestProcessor)getServletContext().getAttribute(WebKeys.RequestProcessorKey);
         if ( rp == null ) {
             Debug.println("MainServlet: initializing request processor");
             rp = new RequestProcessor();
             rp.init(getServletContext());
             getServletContext().setAttribute(WebKeys.RequestProcessorKey, rp);
        }
       return rp;
    }
//把screendefinition装载进来，生成一个manager保存在ScreenFlowManager中，作为调度screen的中心。
    private ScreenFlowManager getScreenFlowManager() {
            ScreenFlowManager screenManager = (ScreenFlowManager)getServletContext().getAttribute(WebKeys.ScreenManagerKey);
            if (screenManager == null ) {
                Debug.println("MainServlet: Loading screen flow definitions");
                screenManager = new ScreenFlowManager();
                screenManager.init(getServletContext());
                getServletContext().setAttribute(WebKeys.ScreenManagerKey, screenManager);
             }
        return screenManager;
    }

    /**
     * The UrlMapping object contains information that will match
     * a url to a mapping object that contains information about
     * the current screen, the RequestHandler that is needed to
     * process a request, and the RequestHandler that is needed
     * to insure that the propper screen is displayed.
    */
//从urlMapping这个HashMap中得到满足客户请求的URL的相关信息，存入URLMapping对象。
    private URLMapping getURLMapping(String urlPattern) {
        if ((urlMappings != null) && urlMappings.containsKey(urlPattern)) {
            return (URLMapping)urlMappings.get(urlPattern);
        } else {
            return null;
        }
    }
}
