/*
 * $Id: CERPEvent.java,v 1.1.1.1 2002/02/02 05:20:26 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits réservés.
 */

package com.huiton.mainframe.control.event;


/**
 * This interface determines the required methods for an estore event
 */
public interface CERPEvent extends java.io.Serializable {
    /**
    *   Specifiy a logical name that is mapped to the event in
    *   in the Universal Remote Controller.
    */
    public String getEventName();
}
