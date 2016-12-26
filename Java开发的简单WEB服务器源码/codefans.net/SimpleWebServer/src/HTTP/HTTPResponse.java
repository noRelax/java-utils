package HTTP;

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

/**
 * 
 * HTTP response codes are described at: 
 * http://libraries.ucsd.edu/about/tools/http-response-codes.html
 */
public class HTTPResponse
{
 private HashMap<String, String> properties;
 private byte[] content;

  /**
  * ie. "1.1","1.0"
  */
 private String HTTPVersion;

 private int responseCodeNumber;

    /**
     * Creates a default response with a Content-Type of text/html, empty content, and HTTP version 1.1.
     */
    public HTTPResponse()
    { 
      content = new byte[0];
      properties = new HashMap<String, String>();
      setDefaultProperties();
    }

    /**
     * Sets the content type of this response.  
     * The content type gives a web browser some information 
     * about how to handle the content in this response.  For example, 
     * "text/html" will be rendered as an HTML page but "text/plain" 
     * will be rendered as plain text without processing any tags.
     */
    public void setContentType(String contentType)
    {
        setProperty("Content-Type", contentType);      
    }

    private void setDefaultProperties()
    {
        setContentType("text/html");
        setDateTo(new Date());
        setProperty("Server","SimpleWebServer");
        HTTPVersion = "1.1";
        setReponseCodeNumber(200); 
    }

    /**
     * Sets the creation date of this response.  
     * This method shouldn't be called unless you are trying 
     * to trick the web browser in some way.
     */
    public void setDateTo(Date d)
    { 
        /**
         *       Sun, 06 Nov 1994 08:49:37 GMT  ; RFC 822, updated by RFC 1123
      Sunday, 06-Nov-94 08:49:37 GMT ; RFC 850, obsoleted by RFC 1036
      Sun Nov  6 08:49:37 1994       ; ANSI C's asctime() format
         */
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("EEE, d MMM yyyy HH:mm:ss Z");
        String dateStr = sdf.format(d);
 
        setProperty("Date", sdf.format(d));
    }

    /**
     * Sets the content of this response.  
     * This will ensure the content sent to the web browser perfectly matches the specified data.
     */
    public void setContent(byte[] data)
    {
        if (data == null)
            return;

        this.content = data;
    }

    /**
     * This is a convenient overload of setContent(byte[]) 
     * for text based content like HTML, JavaScript, CSS, txt.. 
     * but will cause problems for binary content such as images 
     * because (new String(bytes)).getBytes() may not equal bytes.  
     * Some byte values are not treated as you would expect.
     */
    public void setContent(String contents)
    {
        setContent(contents.getBytes());
    }

    /**
     * Sets a header property.
     */
    public void setProperty(String name, String value)
    {
        properties.put(name, value);
    }

    private String getResponseCodeReason()
    {
        return HTTPResponseCodes.getReasonForCode(responseCodeNumber);
    }

    /**
     * @param responseCodeNumber indicates to the web browser or client what kind of response this is.  
     * For example 200 is a standard "OK" response where 404 indicates a missing file 
     * and 300 indicates the web browser should direct itself to another address.  
     */
    public void setReponseCodeNumber(int responseCodeNumber)
    {
        this.responseCodeNumber = responseCodeNumber;
    }
        
    private String getFirstLine()
    {
        return "HTTP/"+HTTPVersion+" "+responseCodeNumber+" "+getResponseCodeReason();
    }

    private byte[] getHeaderAsByteArray()
    {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bout);
        out.print(getFirstLine()+"\r\n");
        // loop through properties
        for (String name: properties.keySet())
        {
           out.print(name+": "+properties.get(name));
           out.print("\r\n");
        }

        out.print("\r\n"); 
        // the blank line to indicate the end of the response header.

        out.close(); /* ensure any buffers used by the PrintStream 
                      * are flushed into the byte array stream.
                      */
        return bout.toByteArray();
    }

    /**
     * Writes this response to the specified output stream
     */
    public void writeTo(OutputStream out) throws IOException
    { 
        // write header
       out.write(getHeaderAsByteArray());

        // write content
       out.write(content);
       out.close();
    }

}