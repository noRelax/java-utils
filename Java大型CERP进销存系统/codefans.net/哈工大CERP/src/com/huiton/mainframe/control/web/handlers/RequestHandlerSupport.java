/*
 * $Id: RequestHandlerSupport.java,v 1.1.4.2 2001/03/15 00:40:07 brydon Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits réservés.
 */

package com.huiton.mainframe.control.web.handlers;

import com.huiton.mainframe.control.event.CERPEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;

/**
 * This class is the default implementation of the RequestHandler
 *
*/
public abstract class RequestHandlerSupport implements RequestHandler {

    protected ServletContext context;

    public void setServletContext(ServletContext context) {
        this.context  = context;
    }

    public void doStart(HttpServletRequest request){}
    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception {return null;}
    public void doEnd(HttpServletRequest request){}
}
