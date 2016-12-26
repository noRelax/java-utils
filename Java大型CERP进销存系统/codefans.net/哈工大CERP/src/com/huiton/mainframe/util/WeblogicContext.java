package com.huiton.mainframe.util;

/**
 * Title:        CERP≤‚ ‘øÚº‹
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author Œ‚Ω£
 * @version 1.0
 */
import javax.naming.*;
import java.util.Properties;

public class WeblogicContext {

    public WeblogicContext() {
    }

    public static Context getInitialContext() {
        String url = "t3://localhost:7001";
        String user = null;
        String password = null;
        Properties properties = null;
        try {
            properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            properties.put(Context.PROVIDER_URL, url);
            if (user != null) {
                properties.put(Context.SECURITY_PRINCIPAL, user);
                properties.put(Context.SECURITY_CREDENTIALS, password == null ? "" : password);
            }
            return new InitialContext(properties);
        }

        catch(Exception e) {
            System.out.println("-- Unable to connect to WebLogic server at " + url);
            System.out.println("-- Please make sure that the server is running.");
            return null;
        }
    }
}