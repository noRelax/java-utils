package webServer;

import HTTP.*;
import java.net.*;
import java.io.*;

/**http://www.codefans.net
 * Handles single request/response pairs by invoking the WebServer's HTTPRequestHandler. 
 */
public class WebServerClientConnectionThread extends Thread
{ 
 private WebServer ws;
 private Socket s;

    public WebServerClientConnectionThread(Socket s, WebServer ws)
    {
        this.ws = ws;
        this.s = s;
        start();
    }

    private void handleRequest(HTTPRequest request)
    {
        HTTPRequestHandler handler = ws.getRequestHandler();
          handler.handleRequest(request);
    }

    public void run()
    {
        try
        {
            while (ws.isRunning() && (!s.isClosed()))
            {
                HTTPRequest request = new HTTPRequest(s);
                
                 handleRequest(request);
                 s.close();

            }
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            System.out.println("There was a problem handling a connection with a client.");
        }
    }

}