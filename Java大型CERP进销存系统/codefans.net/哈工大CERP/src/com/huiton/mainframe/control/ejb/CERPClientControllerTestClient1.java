package com.huiton.mainframe.control.ejb;

import com.huiton.mainframe.control.event.CERPEvent;
import java.io.PrintStream;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

// Referenced classes of package com.huiton.mainframe.control.ejb:
//            CERPClientControllerHome, CERPClientController

public class CERPClientControllerTestClient1
{

    private static final String ERROR_NULL_REMOTE = "Remote interface reference is null.  It must be created by calling one of the Ho" +
"me interface methods first."
;
    private static final int MAX_OUTPUT_LINE_LENGTH = 100;
    private boolean logging;
    private CERPClientControllerHome cAPMSClientControllerHome;
    private CERPClientController cAPMSClientController;

    public CERPClientControllerTestClient1()
    {
        logging = true;
        cAPMSClientControllerHome = null;
        cAPMSClientController = null;
        long startTime = 0L;
        if(logging)
        {
            log("Initializing bean access.");
            startTime = System.currentTimeMillis();
        }
        try
        {
            Context ctx = getInitialContext();
            Object ref = ctx.lookup("javacompenvejbcccCcc");
            cAPMSClientControllerHome = (CERPClientControllerHome)PortableRemoteObject.narrow(ref, com.huiton.mainframe.control.ejb.CERPClientControllerHome.class);
            if(logging)
            {
                long endTime = System.currentTimeMillis();
                log("Succeeded initializing bean access.");
                log(String.valueOf(String.valueOf((new StringBuffer("Execution time: ")).append(endTime - startTime).append(" ms."))));
            }
        }
        catch(Exception e)
        {
            if(logging)
            {
                log("Failed initializing bean access.");
            }
            e.printStackTrace();
        }
    }

    private Context getInitialContext()
        throws Exception
    {
        String url = "t3://localhost:7001";
        String user = null;
        String password = null;
        Properties properties = null;
        try
        {
            properties = new Properties();
            properties.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
            properties.put("java.naming.provider.url", url);
            if(user != null)
            {
                properties.put("java.naming.security.principal", user);
                properties.put("java.naming.security.credentials", password != null ? ((Object) (password)) : "");
            }
            InitialContext initialcontext = new InitialContext(properties);
            return initialcontext;
        }
        catch(Exception e)
        {
            log("Unable to connect to WebLogic server at ".concat(String.valueOf(String.valueOf(url))));
            log("Please make sure that the server is running.");
            throw e;
        }
    }

    public CERPClientController create()
    {
        long startTime = 0L;
        if(logging)
        {
            log("Calling create()");
            startTime = System.currentTimeMillis();
        }
        try
        {
            cAPMSClientController = cAPMSClientControllerHome.create();
            if(logging)
            {
                long endTime = System.currentTimeMillis();
                log("Succeeded: create()");
                log(String.valueOf(String.valueOf((new StringBuffer("Execution time: ")).append(endTime - startTime).append(" ms."))));
            }
        }
        catch(Exception e)
        {
            if(logging)
            {
                log("Failed: create()");
            }
            e.printStackTrace();
        }
        if(logging)
        {
            log(String.valueOf(String.valueOf((new StringBuffer("Return value from create(): ")).append(cAPMSClientController).append("."))));
        }
        return cAPMSClientController;
    }

    public Collection handleEvent(CERPEvent cse)
    {
        Collection returnValue = null;
        if(cAPMSClientController == null)
        {
            System.out.println("Error in handleEvent(): Remote interface reference is null.  It must be created " +
"by calling one of the Home interface methods first."
);
            return returnValue;
        }
        long startTime = 0L;
        if(logging)
        {
            log(String.valueOf(String.valueOf((new StringBuffer("Calling handleEvent(")).append(cse).append(")"))));
            startTime = System.currentTimeMillis();
        }
        try
        {
            returnValue = cAPMSClientController.handleEvent(cse);
            if(logging)
            {
                long endTime = System.currentTimeMillis();
                log(String.valueOf(String.valueOf((new StringBuffer("Succeeded: handleEvent(")).append(cse).append(")"))));
                log(String.valueOf(String.valueOf((new StringBuffer("Execution time: ")).append(endTime - startTime).append(" ms."))));
            }
        }
        catch(Exception e)
        {
            if(logging)
            {
                log(String.valueOf(String.valueOf((new StringBuffer("Failed: handleEvent(")).append(cse).append(")"))));
            }
            e.printStackTrace();
        }
        if(logging)
        {
            log(String.valueOf(String.valueOf((new StringBuffer("Return value from handleEvent(")).append(cse).append("): ").append(returnValue).append("."))));
        }
        return returnValue;
    }

    public void testRemoteCallsWithDefaultArguments()
    {
        if(cAPMSClientController == null)
        {
            System.out.println("Error in testRemoteCallsWithDefaultArguments(): Remote interface reference is nu" +
"ll.  It must be created by calling one of the Home interface methods first."
);
            return;
        } else
        {
            handleEvent(null);
            return;
        }
    }

    private void log(String message)
    {
        if(message == null)
        {
            System.out.println("-- null");
            return;
        }
        if(message.length() > 100)
        {
            System.out.println(String.valueOf(String.valueOf((new StringBuffer("-- ")).append(message.substring(0, 100)).append(" ..."))));
        } else
        {
            System.out.println("-- ".concat(String.valueOf(String.valueOf(message))));
        }
    }

    public static void main(String args[])
    {
        CERPClientControllerTestClient1 client = new CERPClientControllerTestClient1();
    }

    static
    {

    }
}