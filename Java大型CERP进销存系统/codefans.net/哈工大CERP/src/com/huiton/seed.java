package seed;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class seed extends HttpServlet
{
	public void doGet(HttpServletRequest request ,HttpServletResponse response)
	throws IOException ,ServletException
	{
		PrintWriter out=response.getWriter();
		out.println("Hello!\n");
		out.println("I");
		out.close();
	}
	public void doPost(HttpServletRequest request ,HttpServletResponse response)
	throws IOException ,ServletException
	{
		
	}
}