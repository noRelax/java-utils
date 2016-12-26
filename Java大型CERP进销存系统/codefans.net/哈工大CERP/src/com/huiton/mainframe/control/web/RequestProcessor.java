/*
 * $Id: RequestProcessor.java,v 1.1.1.1 2002/02/02 05:20:25 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits r?erv?.
 */
package com.huiton.mainframe.control.web;

import java.util.Collection;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.rmi.RemoteException;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.mainframe.util.WebKeys;
import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.exceptions.CERPEventException;
import com.huiton.mainframe.control.web.ScreenFlowManager;

import com.huiton.mainframe.control.web.handlers.RequestHandler;


/**
 * This is the web tier controller for the sample application.
 *
 * This class is responsible for processing all requests received from
 * the Main.jsp and generating necessary events to modify data which
 * are sent to the ShoppingClientControllerWebImpl.
 *
 */
public class RequestProcessor implements java.io.Serializable {

    private ServletContext context;
    private HashMap urlMappings;

    /** Empty constructor for use by the JSP engine. */
    public RequestProcessor() {}


    public void init(ServletContext context) {
        this.context = context;
        urlMappings = (HashMap)context.getAttribute(WebKeys.URLMappingsKey);
    }

    /**
     * The UrlMapping object contains information that will match
     * a url to a mapping object that contains information about
     * the current screen, the RequestHandler that is needed to
     * process a request, and the RequestHandler that is needed
     * to insure that the propper screen is displayed.
    */

    private URLMapping getURLMapping(String urlPattern) {
        if ((urlMappings != null) && urlMappings.containsKey(urlPattern)) {
            return (URLMapping)urlMappings.get(urlPattern);
        } else {
            return null;
        }
    }


    /**
    * This method is the core of the RequestProcessor. It receives all requests
    *  and generates the necessary events.
    */
    public void processRequest(HttpServletRequest request) throws
        CERPEventException, Exception {

        Debug.println("RequestProcessing.processRequest: entering...");
        CERPEvent event = null;
        String selectedUrl = request.getPathInfo();
        Debug.println("________________:"+selectedUrl);
        ModelManager mm = (ModelManager)request.getSession().getAttribute(WebKeys.ModelManagerKey);
        CERPClientControllerWebImpl ccc = (CERPClientControllerWebImpl)request.getSession().getAttribute(WebKeys.WebControllerKey);
        if (ccc == null) {
            ccc = new CERPClientControllerWebImpl(request.getSession());
            mm.setCCC(ccc);
            request.getSession().setAttribute(WebKeys.WebControllerKey, ccc);
        }

        // 处理事件
       RequestHandler handler = getHandler(selectedUrl);  //RequestHandler: 抽象类
       if (handler != null) {
       //得到处理用户URL请求的handler
           handler.setServletContext(context);
        //开始执行
           handler.doStart(request);
           event = handler.processRequest(request);
        //从handler中返回需要ejb处理的event
           if (event != null) {
                   Collection updatedModelList = ccc.handleEvent(event);
                   mm.notifyListeners(updatedModelList);
           }
           handler.doEnd(request);
       }
    }

    /**
     * This method load the necessary RequestHandler class necessary to process a the
     * request for the specified URL.
     */

    private RequestHandler getHandler(String selectedUrl) {
        RequestHandler handler = null;
        URLMapping urlMapping = getURLMapping(selectedUrl);
        String requestProcessorString = null;
        if (urlMapping != null) {
            requestProcessorString = urlMapping.getRequestHandler();
            if (urlMapping.useRequestHandler()) {
                try {
                    Debug.println("RequestProcessor: loading handler " + requestProcessorString);
                    handler = (RequestHandler)getClass().getClassLoader().loadClass(requestProcessorString).newInstance();
                    Debug.println("RequestProcessor: loaded handler " + requestProcessorString);
                }
                catch (Exception ex) {
                   Debug.println("RequestProcessor caught loading handler: " + ex);
                   ex.printStackTrace();
                }
            }
        }
        return handler;
    }

}
