package HTTP;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

import java.io.*;
import java.util.*;

/**http://www.codefans.net
 * A class for XML parsing.  Java's API leaves parsing much 
 * more complicated than it should be.  This is to map the complexity in the API to 
 * single line method calls.  
 */
public class XMLParser
{
    /**
     * Parses the specified XML file to generate a Document.
     * @param f must be a valid XML file.
     * @throws SAXException if the file is not valid XML.  
     * If you have trouble validating one, opening the file in Firefox can help you troubleshoot.
     */
    public static Document getDocumentFor(File f) throws 
        IOException, SAXException,ParserConfigurationException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(f);
        return doc;
    }

}