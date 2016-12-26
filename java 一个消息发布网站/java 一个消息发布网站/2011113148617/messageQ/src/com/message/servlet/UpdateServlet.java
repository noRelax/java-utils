package com.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.message.pachong.NiouChengQuanZi;
import com.message.pachong.XingTai123;
import com.message.pachong.XingTaiZhaoPin;

public class UpdateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateServlet() {
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
		request.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");
		String name=request.getParameter("name");
		if(name!=null){
			int id=Integer.parseInt(name);

			if(id==1)
			{
				XingTai123 xingtai=new XingTai123();
				xingtai.ZhaoPin();
				XingTaiZhaoPin xi=new XingTaiZhaoPin();
				xi.ZhaoPin();
			}
			if(id==2)
			{
				XingTai123 xingtai=new XingTai123();
				xingtai.yingpin();
			}
			if(id==3)
			{
				XingTai123 xingtai=new XingTai123();
				xingtai.chushou();
				NiouChengQuanZi xingtai2=new NiouChengQuanZi();
				xingtai2.ershouMai();
			}
			if(id==4){
				XingTai123 xingtai=new XingTai123();
				xingtai.qiougou();
			}
			if(id==5){
				NiouChengQuanZi xingtai=new NiouChengQuanZi();
				xingtai.zufang();
			}
		}
		response.sendRedirect("index.jsp");
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
