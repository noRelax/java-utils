/*
 * $Id: ModelUpdateListener.java,v 1.1.1.1 2002/02/02 05:20:25 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits réservés.
 */

package com.huiton.mainframe.control.web;

import com.huiton.mainframe.control.exceptions.CERPAppException;

/**
 * This interface is implemented by objects which are interested in
 * getting the model update events. For example, CustomerWebImpl implements
 * this interface to get itself updated when account model gets updated.
*/

public interface ModelUpdateListener {

    public void performUpdate() throws CERPAppException;

}



