package webServer;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Waits for incoming client connections and responds to each by spawning 
 * a new WebServerClientConnectionThread to handle it.
 */
public class WebServerConnectionThread extends Thread 
{ 
 private WebServer ws;

   public WebServerConnectionThread(WebServer ws)
   {
      this.ws = ws;
      start();
   }

   public void run()
   {
     ServerSocket ss = ws.getServerSocket();

       try
       {
         while (ws.isRunning())
         {
            Socket s = ss.accept();
             new WebServerClientConnectionThread(s,ws);
         }
       }
       catch (Throwable t)
       {
          t.printStackTrace();
       }
   }

}