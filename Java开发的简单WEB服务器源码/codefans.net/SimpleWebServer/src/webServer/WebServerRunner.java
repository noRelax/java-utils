package webServer;
/**
 * 启动类
 * 启动方法：WebServerRunner Port
 * 既需要加上端口号
 */
import java.io.*;

public class WebServerRunner
{
    public static void main(String a[])
    {
        if (a.length < 1)
        {
            System.out.println("Usage java WebServerRunner [port number]");
        }
        try
        {
            WebServer ws = new WebServer(Integer.parseInt(a[0]));
        }
        catch (NumberFormatException nfe)
        {
            nfe.printStackTrace();
            System.out.println("Ensure that you gave a valid number for the port.");
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            System.out.println("Ensure that port "+a[0]+" is not currently in use.");
        }
    }
}