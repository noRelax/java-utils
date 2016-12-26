package webServer;

import HTTP.*;

/**
 * Implemented by any object used to handle HTTP requests.  
 */
public interface HTTPRequestHandler
{

    /**http://www.codefans.net
     * Returns true if and only if the specified request can be handled by this handler
     */
    public boolean canHandle(HTTPRequest request);

    /**
     * Handles the specified request
     */
    public void handleRequest(HTTPRequest request);

}