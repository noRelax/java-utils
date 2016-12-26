package HTTP;

import java.io.*;
import java.util.*;
import org.w3c.dom.*;

/**http://www.codefans.net
 * For getting content types from file paths
 */
public class ContentTypes
{
 private static final File TYPES_FILE = new File("data\\contentTypes.xml");

    /**
     * Maps endings to corresponding content types
     */
 private static final HashMap<String,String> endingMap = getContentTypeMappings();

    private static HashMap<String, String> getContentTypeMappings()
    {
        HashMap<String, String> result = new HashMap<String, String>();
        if (!TYPES_FILE.exists())
        {
            return result;
        }
        try
        {
            Document doc = XMLParser.getDocumentFor(TYPES_FILE);
            NodeList nl = doc.getElementsByTagName("contentType");
            int len = nl.getLength();
            for (int i = 0; i < len; i++)
            {
                Element e = (Element)nl.item(i);
                String contentTypeStr = e.getAttribute("type");
                if (contentTypeStr == null)
                    System.err.println(
                        "There is a missing 'type' attribute for a "
                        + "contentType element in " + TYPES_FILE);
                else
                {
                    for (Node n = e.getFirstChild(); n != null; n = n.getNextSibling())
                    {
                        if (n instanceof Element)
                        {
                            Element ce = (Element)n;
                            if (ce.getTagName().equals("ending"))
                            {
                                String value = ce.getAttribute("value");
                                if (value == null)
                                {
                                    System.err.println(
                                        "There is a missing 'value' attribute for an "
                                        + "'ending' element for content type '"
                                        + contentTypeStr + "' in " + TYPES_FILE);
                                }
                                else
                                {
                                    result.put(value, contentTypeStr);
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            System.err.println("Unable to load content types information from " + TYPES_FILE);
        }
        return result;
    }

    public static String guessContentTypeFromPath(String path)
    {
        String result = "application/octet-stream"; // default type that is usually just downloaded.

        String endingIncludingDot = "";
        int dotIndex = path.lastIndexOf('.');
        if (dotIndex<0)
            return result;

        String str = path.substring(dotIndex);
        int paramStartIndex = str.indexOf('?');

          if (paramStartIndex>0)
            str = str.substring(0,paramStartIndex);

        String contentTypeStr = endingMap.get(str);
        if (contentTypeStr != null)
            result = contentTypeStr;

        return result;
    }
}