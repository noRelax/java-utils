package com.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.message.bean.ErShou;
import com.message.bean.ZuFang;
import com.message.dao.ErShouDAO;
import com.message.dao.ZuFangDAO;
import com.message.service.ErShouService;
import com.message.service.ZuFangService;

public class ZuFangServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ZuFangServlet() {
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
			ZuFangService condao=new ZuFangService();
			List list=condao.Lookid(id, 1, 1, new ZuFangDAO());
			if(list.iterator().hasNext()){
				ZuFang con=(ZuFang)list.iterator().next();
				request.setAttribute("obj", con);
				RequestDispatcher re=request.getRequestDispatcher("zufangitem.jsp");
				re.forward(request, response);
			}else{
				PrintWriter out = response.getWriter();
				out.print("�Ҳ�����������");
				out.close();
			}
		}else
		{
			PrintWriter out = response.getWriter();
			out.print("���ʴ�������ȷ���ʱ���վ");
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
