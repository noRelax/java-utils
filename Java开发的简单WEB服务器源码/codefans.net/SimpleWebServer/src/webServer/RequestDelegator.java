package webServer;

import HTTP.*;

import java.util.*;
import java.io.*;

/**
 * Handles delegation of HTTP request handling to the appropriate handler.
 * If you want to make a more dynamic handler, implement HTTPRequestHandler 
 * and add it to the handlers list in the method addHandlers() in this delegator.
 */
public class RequestDelegator implements HTTPRequestHandler
{
 private static final File HANDLE_FAILURE_RESPONSEFILE = new File("data\\requestHandleFailure.html");
 private static final String failureResponseContent = getFailureContent();
 private LinkedList<HTTPRequestHandler> handlers;

    private static String getFailureContent()
    {
        String result = "Server failed to handle request.";
        if (!HANDLE_FAILURE_RESPONSEFILE.exists())
        {
            System.err.println("Check that '" + HANDLE_FAILURE_RESPONSEFILE+" exists.");
            return result;
        }
        try
        {
            byte[] content = new byte[(int)HANDLE_FAILURE_RESPONSEFILE.length()];
            FileInputStream in = new FileInputStream(HANDLE_FAILURE_RESPONSEFILE);
              in.read(content);
              in.close();
              return new String(content);
        }
        catch (java.lang.Throwable t)
        {
            t.printStackTrace();
            System.err.println("Unable to load " + HANDLE_FAILURE_RESPONSEFILE);
        }
        return result;
    }

    public RequestDelegator()
    {
        handlers = new LinkedList<HTTPRequestHandler>();
        addHandlers();
    }

    /**
     * Adds the other request handlers that will be delegated to.
     */
    private void addHandlers()
    {
        handlers.add(new FileHTTPRequestHandler());
    }

    public boolean canHandle(HTTPRequest request)
    {
        return true;
    }

   public void handleRequest(HTTPRequest request)
   { 
     // handle the request by passing it to another handler. 
       for (HTTPRequestHandler handler: handlers)
       {
          if (handler.canHandle(request))
          {
              handler.handleRequest(request);
             return;
          }
       }

       try
       {
          // no other handlers could handle this so respond with a message to inform the client.
          HTTPResponse failureResponse = new HTTPResponse();
           failureResponse.setContent(failureResponseContent);
           failureResponse.setContentType("text/html");
           failureResponse.writeTo(request.getSocket().getOutputStream());
       }
       catch (Throwable t)
       {
          t.printStackTrace();
          System.err.println("Unable to send failure response to client.");
       }
   }

}