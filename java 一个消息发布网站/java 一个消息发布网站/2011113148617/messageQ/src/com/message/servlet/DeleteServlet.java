package com.message.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.message.bean.Content;
import com.message.bean.ErShou;
import com.message.bean.ErShouMai;
import com.message.bean.ZhaoGongZuo;
import com.message.bean.ZuFang;
import com.message.dao.ContentDAO;
import com.message.dao.ErShouDAO;
import com.message.dao.ErShouMaiDAO;
import com.message.dao.ZhaoGongZuoDAO;
import com.message.dao.ZuFangDAO;
import com.message.pachong.NiouChengQuanZi;
import com.message.pachong.XingTai123;
import com.message.pachong.XingTaiZhaoPin;

public class DeleteServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteServlet() {
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
		
		response.setContentType("text/html");
		String type=request.getParameter("type");
		String id=request.getParameter("id");
		if(type!=null&&id!=null){
			int id2=Integer.parseInt(type);
			int id3=Integer.parseInt(id);
			if(id2==1)
			{
				ContentDAO con=new ContentDAO();
				Content con2=new Content();
				con2.setId(id3);
				con.deleteContent(con2);
			}
			if(id2==2)
			{
				ZhaoGongZuoDAO con=new ZhaoGongZuoDAO();
				ZhaoGongZuo con2=new ZhaoGongZuo();
				con2.setId(id3);
				con.deleteContent(con2);
			}
			if(id2==3)
			{
				ErShouMaiDAO con=new ErShouMaiDAO();
				ErShouMai con2=new ErShouMai();
				con2.setId(id3);
				con.deleteContent(con2);
			}
			if(id2==4){
				ErShouDAO con=new ErShouDAO();
				ErShou con2=new ErShou();
				con2.setId(id3);
				con.deleteContent(con2);
			}
			if(id2==5){
				ZuFangDAO con=new ZuFangDAO();
				ZuFang con2=new ZuFang();
				con2.setId(id3);
				con.deleteContent(con2);
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
