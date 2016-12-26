package HTTP;

import fileIO.*;
import java.util.*;
import java.io.*;
import java.net.*;

/**
 * Represents an HTTP request.
 * http://www.codefans.net
 * More implementation is needed on the parsing end of this to get this properly processing 
 * POST requests because data in the content area of the request would then need to be processed.
 */
public class HTTPRequest
{
  /**
   * GET,PUT,POST,HEAD...
  */
  private String method;

    /**
     * ie. "1.0", "1.1"
     */
  private String HTTPVersion;

    /**
     * The full value specified between the method and the HTTP version used by client.
     * ie. "/", "/index.html", "index.php?var1=bla"...
     */
  private String requestPath;

  /**
   * All the properties of the request.  
   * This includes "Host", "Accept-language"...
   */
  private HashMap<String, String> properties;
  private Socket s;


    /**
     * Initializes an HTTP request object by parsing the input off the input stream 
     */
    public HTTPRequest(Socket s) throws IOException
    {
        properties = new HashMap<String, String>();
        this.s = s;
        parseFrom(s.getInputStream());
    }

    public Socket getSocket()
    {
        return s;
    }

    private void extractFromFirstLine(String line) throws IOException
    {
        StringTokenizer st = new StringTokenizer(line," ");
        if (st.countTokens() < 3)
        {
            throw new IOException("Invalid HTTP request head first line: "+line);
        }
        method = st.nextToken();
        requestPath = st.nextToken();
        HTTPVersion = st.nextToken();
        int index = HTTPVersion.indexOf('/');
        if (index>=0) // if a problem with invalid protocol.
        {
            String protocol = HTTPVersion.substring(0, index);
            if (!protocol.equals("HTTP"))
                throw new IOException("Unsupported protocol: " + protocol + " should be HTTP");

            HTTPVersion = HTTPVersion.substring(index + 1);
        }
        else
            throw new IOException("Unsupported protocol in "+HTTPVersion);
    }

    private void extractProperty(String line) throws IOException
    {
        int index = line.indexOf(':');

        if (index < 0)
            throw new IOException("Invalid HTTP request header line missing ':' in "+line);

        String propertyName = line.substring(0, index);
        String value = line.substring(index + 1).trim();

         properties.put(propertyName, value);
    }

    public void parseFrom(InputStream in) throws IOException
    {
        LEDataInputStream br = new LEDataInputStream(in);
        String line = br.readLine();
        if (line == null)
            throw new EOFException("Unexpected end of HTTP request before first line");

        extractFromFirstLine(line);

        line = br.readLine();
        while (line != null)
        {
            extractProperty(line);
            line = br.readLine();
            if (line.length() < 2) // indicating end of head for request
            // (POST must be handled differently to get all the posted data)
                break;
        }
    }

    /**
     * Gets the request method
     * ie. "GET","SET","POST"...
     */
    public String getMethod()
    {
        return method;
    }

    /**
     * ie. "1.0", "1.1"
     */
    public String getHTTPVersion()
    {
        return HTTPVersion;
    }

    public String getProperty(String name)
    {
        return properties.get(name);
    }

    private static String URLStringToFileName(String urlString)
    {
        String result = "";
        // urlString may be ie. "/page%201.html"

        for (int i = 0; i < urlString.length(); i++)
        {
            char c = urlString.charAt(i);
            if (c == '%')
            {
                if (i < urlString.length() - 2)
                {
                    try
                    {
                        int val = Integer.parseInt(urlString.substring(i + 1, 2), 16);
                        result += (char)val;
                        i += 2;
                    }
                    catch (NumberFormatException nfe)
                    {
                        result += "" + c;
                    }
                }
                else
                    result += "" + c;
            }
            else
                result += "" + c;
        }

        return result;
    }

    private String getFileNamePart()
    {
        String path = getRequestPath();
        int index = path.indexOf('?');
        if (index > 0)
            path = path.substring(0, index);
        return path;    
    }

    /**
     * @return the relative file path of a file being requested
     */
    public String getFilePath()
    {
        String path = getFileNamePart();

        String separatorStr = System.getProperty("file.separator");
        if (separatorStr != null)
        {
            char separator = separatorStr.charAt(0);
            path = path.replace('/', separator);
        }

        return URLStringToFileName(path);
    }

    public boolean isForDirectory()
    {
        String path = getFileNamePart();
        return path.endsWith("/");
    }

    /**
     * Gets the full value specified between the method and the HTTP version used by client.
     * ie. "/", "/index.html", "index.php?var1=bla"...
     */
    public String getRequestPath()
    {
        return requestPath;
    }


}