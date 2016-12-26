/*
 * $Id: CERPEventSupport.java,v 1.1.1.1 2002/02/02 05:20:26 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits réservés.
 */

package com.huiton.mainframe.control.event;

import java.io.Serializable;

/**
 * This is the base class for all events used by the application.
 * Currently this class only implements Serializable to ensure that
 * all events may be sent the the EJB container via RMI-IIOP.
 */
public class CERPEventSupport implements CERPEvent {

    public String getEventName() {
        return null;
    }

}
