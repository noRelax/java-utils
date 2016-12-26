package HTTP;

import java.io.*;
import java.util.*;

public class HTTPResponseCodes
{
  private static final File CODES_FILE = new File("data\\HTTPResponses.txt");

  /**
  * A map from response codes to reason phrases
  */
  private static final HashMap<Integer, String> reasonMapper = getResponseCodeReasonMapping();

    private static HashMap<Integer, String> getResponseCodeReasonMapping()
    {
        HashMap<Integer, String> result = new HashMap<Integer, String>();
        if (!CODES_FILE.exists())
        {
            System.err.println("Check that the file " + CODES_FILE+" exists.");
            return result;
        }
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(CODES_FILE));
            String line = br.readLine();

            while (line != null)
            {
                if (line.length() > 3)
                {
                    int indexOfSpace = line.indexOf(' ');
                    if (indexOfSpace > 0)
                    {
                        String intPart = line.substring(0, indexOfSpace);
                        Integer.parseInt(intPart);

                    }
                }
                line = br.readLine();
            }

        }
        catch (NumberFormatException nfe)
        {
            nfe.printStackTrace();
            System.err.println("Check that " + CODES_FILE
                +" is formatted to have valid numbers followed by spaces "
                +"before the HTTP reason phrases.");
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            System.err.println("Unable to load response codes properly.");
        }
        return result;
    }

    /**
     * @return a reason corresponding with the specified HTTP response code.
     * If one can't be found in the loaded list, "OK" is returned which matches the response for 200.
     */
    public static String getReasonForCode(int code)
    {
        Integer num = new Integer(code);
        String reason = reasonMapper.get(num);
        if (reason == null)
            return "OK";
        else
            return reason;
    }

}