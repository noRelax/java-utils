/*
 * $Id: Debug.java,v 1.1.1.1 2002/02/02 05:20:25 Administrator Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits réservés.
 */

package com.huiton.mainframe.util.tracer;

/**
 * This class is just a helper class to make it handy
 * to print out debug statements
 */
public final class Debug {

    public static final boolean debuggingOn = true;

    public static void assert(boolean condition) {
        if (!condition) {
            println("Assert Failed: ");
            throw new IllegalArgumentException();
        }
    }

    public static void print(String msg) {
        if (debuggingOn) {
            System.out.print(msg);
        }
    }

    public static void println(String msg) {
        if (debuggingOn) {
            System.out.println(msg);
        }
    }

    public static void print(Exception e, String msg) {
        print((Throwable)e, msg);
    }

    public static void print(Exception e) {
        print(e, null);
    }

    public static void print(Throwable t, String msg) {
        if (debuggingOn) {
            System.out.println("Received throwable with Message: "+t.getMessage());
            if (msg != null)
                System.out.print(msg);
            t.printStackTrace();
        }
    }

    public static void print(Throwable t) {
        print(t, null);
    }
}
