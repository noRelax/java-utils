/*
 * $Id: SigninEvent.java,v 1.1.1.1 2002/02/02 05:20:26 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits réservés.
 */

package com.huiton.mainframe.control.event;

/**
 * This event is sent from the web tier to the EJB Controller to
 * notify the EJB Controller that a user has logged into the
 * application.
 */
public class SigninEvent extends CERPEventSupport {

    private String userName;
    private String password;

    public SigninEvent(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEventName() {
        return "java:comp/env/event/SigninEvent";
    }

    public String toString() {
        return "SigninEvent: userName=" + userName + ", password=" + password;
    }

}
