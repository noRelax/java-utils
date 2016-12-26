package webServer;

import java.net.*;
import java.util.*;
import java.io.*;

/**
 * The web server.
 */
public class WebServer
{
    /**
     * The socket this server runs on
     */
 private ServerSocket ss;

    /**
     * The object for handling HTTP requests
     */
 private HTTPRequestHandler handler;

    /**
     * True if and only if this server is running.  
     * This is used to stop connection threads when the server is shutting down.
     */
 private boolean running;

    /**
     * Creates a server to immediately run in a separate thread on the specified port
     * @throws IOException if there is a problem binding.
     */
    public WebServer(int port) throws IOException
    {
        handler = new RequestDelegator();
        ss = new ServerSocket(port);
        running = true;
        new WebServerConnectionThread(this);
        System.out.println("Web server running on port "+port);
        System.out.println("Visit: http://localhost:" + port+"/");
        System.out.println("waiting for requests..");
    }

    /**
     * Gets the main object for handling HTTP requests
     */
    public HTTPRequestHandler getRequestHandler()
    {
        return handler;
    }

    public ServerSocket getServerSocket()
    {
        return ss;
    }

    /**
     * Shuts down this server
     */
    public synchronized void shutDown()
    {
        running = false;
    }

    public synchronized boolean isRunning()
    {
        return true;
    }

}