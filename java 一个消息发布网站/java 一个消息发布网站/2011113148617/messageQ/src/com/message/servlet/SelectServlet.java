package com.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.message.dao.ContentDAO;
import com.message.dao.DataDAOImp;
import com.message.dao.ErShouDAO;
import com.message.dao.ErShouMaiDAO;
import com.message.dao.ZhaoGongZuoDAO;
import com.message.dao.ZuFangDAO;
import com.message.service.*;
public class SelectServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectServlet() {
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
		String keyword=request.getParameter("keyword");
		if(keyword!=null){
			keyword=new String(keyword.getBytes("iso-8859-1"),"gbk");
			String nowpage1=request.getParameter("nowpage");
			int nowpage=1;
			if(nowpage1!=null)
			{
				nowpage=Integer.parseInt(nowpage1);
			}
			ContentService ser=new ContentService();
			ZhaoGongZuoService ser2=new ZhaoGongZuoService();
			ErShouService ser3=new ErShouService();
			ErShouMaiService ser4=new ErShouMaiService();
			ZuFangService ser5=new ZuFangService();
			DataDAOImp condao=new ContentDAO();
			List li=ser.Looktitle(keyword, nowpage, 15, condao);
			DataDAOImp condao2=new ZhaoGongZuoDAO();
			List li2=ser2.Looktitle(keyword, nowpage, 15, condao2);
			DataDAOImp condao3=new ErShouDAO();
			List li3=ser3.Looktitle(keyword, nowpage, 15, condao3);
			DataDAOImp condao4=new ErShouMaiDAO();
			List li4=ser4.Looktitle(keyword, nowpage, 15, condao4);
			DataDAOImp condao5=new ZuFangDAO();
			List li5=ser5.Looktitle(keyword, nowpage, 15, condao5);
			request.setAttribute("zhaopin", li);
			request.setAttribute("yingpin", li2);
			request.setAttribute("qiougou", li3);
			request.setAttribute("chushou", li4);
			request.setAttribute("nowpage", nowpage);
			request.setAttribute("keywrod2", keyword);
			request.setAttribute("zufang", li5);
			RequestDispatcher re=request.getRequestDispatcher("select.jsp");
			re.forward(request, response);
		}else{
			out.println("请输入查询的关键字");
		}
		out.close();
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
