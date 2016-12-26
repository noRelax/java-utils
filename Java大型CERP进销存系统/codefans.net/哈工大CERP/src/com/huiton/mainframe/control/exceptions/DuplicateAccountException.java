/*
 * $Id: DuplicateAccountException.java,v 1.1.1.1 2002/02/02 05:20:26 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits réservés.
 */

package com.huiton.mainframe.control.exceptions;


/** Duplicate Account Exception
  * Signifys to app that someone tried to create
  * an account where the userid has already been used.
 */
public class DuplicateAccountException extends CERPEventException
                           {

    public DuplicateAccountException (String str) {
        super(str);
    }
}

