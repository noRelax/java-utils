package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class NewsSearcher_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    javax.servlet.jsp.PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html; charset=GBK");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"NewsSearcher_error.jsp", true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>\r\nNewsSearcher\r\n");
      out.write("</title>\r\n");
      out.write("</head>\r\n\r\n");
      out.write("<center>\r\n\t");
      out.write("<form name=\"search\" action=\"/results\" method=\"get\">\r\n\t\t");
      out.write("<p>\r\n\t\t\t");
      out.write("<input name=\"QueryContent\" size=\"44\"/>&nbsp;Search Criteria\r\n\t\t");
      out.write("</p>\r\n\t\t");
      out.write("<p>\r\n\t\t\t");
      out.write("<input type=\"submit\" value=\"Search\"/>\r\n\t\t");
      out.write("</p>\r\n        ");
      out.write("</form>\r\n");
      out.write("</center>\r\n\r\n\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }
}
