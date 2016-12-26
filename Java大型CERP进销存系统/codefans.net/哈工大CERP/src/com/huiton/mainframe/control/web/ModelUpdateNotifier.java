/*
 * $Id: ModelUpdateNotifier.java,v 1.1.1.1 2002/02/02 05:20:25 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits réservés.
 */

package com.huiton.mainframe.control.web;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.mainframe.control.exceptions.GeneralFailureException;
import com.huiton.mainframe.control.exceptions.CERPAppException;

/**
 * This class is responsible for providing methods to add objects as listeners
 * for a particular model update event and for notifying the listeners when the
 * event actually occurs.
 */
public class ModelUpdateNotifier implements java.io.Serializable {

    private HashMap listenerMap;

    public ModelUpdateNotifier() {
        listenerMap = new HashMap();
    }

    public void notifyListeners(Collection updatedModelList) throws
                                                    CERPAppException {

        for (Iterator it1 = updatedModelList.iterator() ; it1.hasNext() ;) {
            String modelType = (String) it1.next();
            Collection listeners = (Collection)listenerMap.get(modelType);
            if (listeners != null) {
                for (Iterator it2 = listeners.iterator(); it2.hasNext(); ) {
                    ((ModelUpdateListener) it2.next()).performUpdate();
                }
            }
        }
    }

    /**
     * This method is called by ???WebImp Object when the ???WebImp Object
     * construct, in order to conserve the object handle which need to be
     * update when the EJB state have changed.
     */
    public void addListener(String modelType, Object listener) {

        if (listenerMap.get(modelType) == null) {
            ArrayList listeners = new ArrayList();
            listeners.add(listener);
            listenerMap.put(modelType,listeners);
        } else {
            ((ArrayList) listenerMap.get(modelType)).add(listener);
        }
    }
}

