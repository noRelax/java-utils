/*
 * $Id: CERPEventException.java,v 1.1.1.1 2002/02/02 05:20:26 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits réservés.
 */

package com.huiton.mainframe.control.exceptions;

/**
 * This exception is the base class for all the event exceptions.
 */
public class CERPEventException extends Exception
    implements java.io.Serializable {

    public CERPEventException() {}

    public CERPEventException(String str) {
        super(str);
    }
}
