package com.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.message.bean.Content;
import com.message.dao.ContentDAO;
import com.message.service.ContentService;

public class ContentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ContentServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		response.setContentType("text/html");
		String idstr=request.getParameter("id");
		if(idstr!=null)
		{
			int id=Integer.parseInt(idstr);
			ContentService condao=new ContentService();
			List list=condao.Lookid(id, 1, 1, new ContentDAO());
			if(list.iterator().hasNext()){
				Content con=(Content)list.iterator().next();
				request.setAttribute("obj", con);
				RequestDispatcher re=request.getRequestDispatcher("Contentitem.jsp");
				re.forward(request, response);
			}else{
				PrintWriter out = response.getWriter();
				out.print("找不到这条数据");
				out.close();
			}
		}else
		{
			PrintWriter out = response.getWriter();
			out.print("访问错误！请正确访问本网站");
			out.close();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
