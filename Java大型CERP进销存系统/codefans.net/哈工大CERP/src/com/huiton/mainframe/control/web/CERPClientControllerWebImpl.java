/*
 * $Id: CERPClientControllerWebImpl.java,v 1.1.1.1 2002/02/02 05:20:25 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits r?erv?.
 */
package com.huiton.mainframe.control.web;

import java.util.Locale;
import java.util.Collection;
import javax.rmi.PortableRemoteObject;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.mainframe.util.JNDINames;
import com.huiton.mainframe.util.WebKeys;
import com.huiton.mainframe.util.JSPUtil;
import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.ejb.CERPClientControllerHome;
import com.huiton.mainframe.control.web.ModelManager;

import java.rmi.RemoteException;
import javax.ejb.FinderException;
import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;


import com.huiton.mainframe.control.exceptions.GeneralFailureException;
import com.huiton.mainframe.control.exceptions.CERPEventException;
import com.huiton.mainframe.control.exceptions.CERPAppException;

/**
 * This class is essentially just a proxy object that calls methods on the
 * EJB tier using the com.huiton.mainframe.control.ejb.ShoppingClientControllerEJB
 * object. All the methods that access the EJB are synchronized so
 * that concurrent requests do not happen to the stateful session bean.
 *
 * @see com.huiton.mainframe.control.ejb.ShoppingClientController
 * @see com.huiton.mainframe.control.ejb.ShoppingClientControllerEJB
 * @see com.huiton.mainframe.control.event.EStoreEvent
 */
public class CERPClientControllerWebImpl implements java.io.Serializable {

    private com.huiton.mainframe.control.ejb.CERPClientController cccEjb;
    private HttpSession session;

    public CERPClientControllerWebImpl() {
    }

    /**
     * constructor for an HTTP client.
     * @param the HTTP session object for a client
     */
    public CERPClientControllerWebImpl(HttpSession session) {
        this.session = session;
        ModelManager mm = (ModelManager)session.getAttribute(WebKeys.ModelManagerKey);
 //       cccEjb = mm.getCCCEJB();
    }

    /**
     * feeds the specified event to the state machine of the business logic.
     *
     * @param ese is the current event
     * @return a list of models that got updated because of the
     *         processing of this event.
     * @exception com.huiton.mainframe.control.EStoreEventException <description>
     * @exception com.huiton.mainframe.control.GeneralFailureException
     */
    public synchronized Collection handleEvent(CERPEvent ese)
        throws CERPEventException {
        try {
            Debug.println("CERPClientController: handleEvent()");
            return cccEjb.handleEvent(ese);
        } catch (RemoteException re) {
                throw new GeneralFailureException(re.getMessage());
        }
    }

     /**
     * frees up all the resources associated with this controller and
     * destroys itself.
     */
    public synchronized void remove() {
        // call ejb remove on self/shopping cart/etc.
        try {
            cccEjb.remove();
        } catch(RemoveException re){
            // ignore, after all its only a remove() call!
            Debug.print(re);
        } catch(RemoteException re){
            // ignore, after all its only a remove() call!
            Debug.print(re);
        }
    }
}
