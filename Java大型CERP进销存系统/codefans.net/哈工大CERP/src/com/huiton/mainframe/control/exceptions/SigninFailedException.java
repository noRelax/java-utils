/*
 * $Id: SigninFailedException.java,v 1.1.1.1 2002/02/02 05:20:26 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits réservés.
 */

package com.huiton.mainframe.control.exceptions;

/**
* This exception is thrown when a user fails to propperly log into
* the application.
*/
public class SigninFailedException extends CERPEventException  {

    public SigninFailedException (String str) {
        super(str);
    }
}
