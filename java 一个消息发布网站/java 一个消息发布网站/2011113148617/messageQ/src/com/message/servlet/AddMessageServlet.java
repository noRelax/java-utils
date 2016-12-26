package com.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.message.bean.ErShouMai;
import com.message.dao.ContentDAO;
import com.message.dao.DataDAOImp;
import com.message.dao.ErShouDAO;
import com.message.dao.ErShouMaiDAO;
import com.message.dao.ZhaoGongZuoDAO;
import com.message.dao.ZuFangDAO;
import com.message.service.ContentService;
import com.message.service.ErShouMaiService;
import com.message.service.ErShouService;
import com.message.service.ServiceImp;
import com.message.service.ZhaoGongZuoService;
import com.message.service.ZuFangService;

public class AddMessageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddMessageServlet() {
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
		response.setContentType("text/html; charset=gbk");
		String type=request.getParameter("type");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String testnum=request.getParameter("testnum");
		String testnum2=(String)request.getSession().getAttribute("testnumber");
		if(testnum.equals(testnum2)){
		if(type!=null&&title!=null&&content!=null&&testnum!=null)
		{
		ServiceImp imp=null;
		DataDAOImp dao=null;
		if(type.equals("1")){
			//招聘
			imp=new ContentService();
			dao=new ContentDAO();
		}
		if(type.equals("2")){
			//找工作
			imp=new ZhaoGongZuoService();
			dao=new ZhaoGongZuoDAO();
		}
		if(type.equals("3")){
			//卖物
			imp=new ErShouMaiService();
			dao=new ErShouMaiDAO();
		}
		if(type.equals("4")){
			//买物
			imp=new ErShouService();
			dao=new ErShouDAO();
		}
		if(type.equals("5")){
			//买物
			imp=new ZuFangService();
			dao=new ZuFangDAO();
		}
		imp.addContent(title, content, 1, dao);
		response.sendRedirect("index.jsp");
		}
		}else{
			PrintWriter out = response.getWriter();
			out.println("验证码错误;<a href='index.jsp'>返回主页</a>");
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
