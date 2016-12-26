/*
 * $Id: ModelManager.java,v 1.1.1.1 2002/02/02 05:20:25 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits r?erv?.
 */
package com.huiton.mainframe.control.web;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.mainframe.util.WebKeys;
import com.huiton.cerp.pub.util.EJBUtil;

//import com.sun.j2ee.blueprints.shoppingcart.cart.model.ShoppingCartModel;
//import com.sun.j2ee.blueprints.shoppingcart.cart.ejb.ShoppingCart;
//import com.sun.j2ee.blueprints.customer.order.model.OrderModel;
//import com.sun.j2ee.blueprints.customer.account.model.AccountModel;
//import com.sun.j2ee.blueprints.customer.customer.ejb.Customer;
//import com.sun.j2ee.blueprints.personalization.profilemgr.model.ProfileMgrModel;
//import com.sun.j2ee.blueprints.personalization.profilemgr.ejb.ProfileMgr;

//import com.huiton.mainframe.control.web.ProfileMgrWebImpl;
import com.huiton.mainframe.control.ejb.CERPClientController;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.FinderException;
import com.huiton.mainframe.control.exceptions.GeneralFailureException;
import com.huiton.mainframe.control.exceptions.CERPAppException;

/**
 * This interface provides a convenient set of methods for the
 * web tier components to access all the model objects.
 * This class also insures that only one copy of the  model objects
 * are created for web tier access by placing a reference to the
 * model objects in the session.
 */
public class ModelManager extends ModelUpdateNotifier implements java.io.Serializable {

    private ServletContext context;
    private HttpSession session;
    private CERPClientController cccEjb = null;

    //private Customer custEjb = null;
    //private ProfileMgr proEjb = null;
    private CERPClientControllerWebImpl ccc = null;

    public ModelManager() { }

    public void init(ServletContext context, HttpSession session) {
        this.session = session;
        this.context = context;
        getCustomerWebImpl();
        //getProfileMgrModel();
    }

    public void setCCC(CERPClientControllerWebImpl ccc) {
        this.ccc = ccc;
    }


    public CustomerWebImpl getCustomerWebImpl() {
        CustomerWebImpl customer =
            (CustomerWebImpl)session.getAttribute(WebKeys.CustomerWebImplKey);
        if (customer == null) {
            customer = new CustomerWebImpl(this);
            session.setAttribute(WebKeys.CustomerWebImplKey, customer);
        }
        return customer;
    }

    /*
    public ProfileMgrModel getProfileMgrModel() {
        ProfileMgrModel pro = (ProfileMgrModel)
            session.getAttribute(WebKeys.ProfileMgrModelKey);
        if (pro == null) {
            pro = new ProfileMgrWebImpl(this);
            session.setAttribute(WebKeys.ProfileMgrModelKey, pro);
        }
        return pro;
    }
    */

    public CERPClientController getCCCEJB() {
        if (cccEjb == null) {
            try {
                cccEjb = EJBUtil.getCCCHome().create();
            } catch (CreateException ce) {
                throw new GeneralFailureException(ce.getMessage());
            } catch (RemoteException re) {
                throw new GeneralFailureException(re.getMessage());
            } catch (javax.naming.NamingException ne) {
                 throw new GeneralFailureException(ne.getMessage());
            }
        }
        return cccEjb;
    }



    /*
    public Customer getCustomerEJB() throws EStoreAppException {
        if (custEjb == null) {
            if (ccc == null) {
                throw new
                GeneralFailureException("ModelManager: Can not get customer EJB");
            } else {
                custEjb = ccc.getCustomerEJB();
            }
        }
        return custEjb;
    }

    public ProfileMgr getProfileMgrEJB() throws EStoreAppException {
        if (proEjb == null) {
            if (ccc == null) {
                throw new
                GeneralFailureException("ModelManager: Can not get profilemgr EJB");
            } else {
                proEjb = ccc.getProfileMgrEJB();
            }
        }
        return proEjb;
    }
    */
}

