package com.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.message.dao.DataDAOImp;
import com.message.dao.ZhaoGongZuoDAO;
import com.message.dao.ZuFangDAO;
import com.message.service.ZhaoGongZuoService;
import com.message.service.ZuFangService;

public class ZuFangProServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ZuFangProServlet() {
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

		response.setCharacterEncoding("gbk");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String nowpage1=request.getParameter("nowpage");
		int nowpage=1;
		if(nowpage1!=null)
		{
			nowpage=Integer.parseInt(nowpage1);
		}
		ZuFangService ser4=new ZuFangService();
		DataDAOImp condao4=new ZuFangDAO();
		List li4=ser4.LookAll( nowpage, 15, condao4);
		request.setAttribute("zufang", li4);
		request.setAttribute("nowpage", nowpage);
		RequestDispatcher re=request.getRequestDispatcher("zufang.jsp");
		re.forward(request, response);
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
