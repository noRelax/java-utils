package com.message.servlet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 生成验证码
 * @author 董成龙
 *
 */
public class ChineseCheckCode extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChineseCheckCode() {
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
		response.setHeader("Pragma", "No-cache");	
		response.setHeader("Cache-Control", "No-cache");//文档不缓存;
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");//设置响应类型为图片
		int width =166;//图片的宽
		int height=45;//高
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);//创建图片缓冲区
		Graphics g=image.getGraphics();//获取这个缓冲区的graphics
		Random random=new Random();//创建随机类
		Font mFont=new Font("宋体",Font.ITALIC,26);//创建字体
		g.setColor(getRandColor(200,250));//设置颜色
		g.fillRect(0, 0, width, height);//填充整个图 就是绘制背景
		g.setFont(mFont);//设置字体
		g.setColor(getRandColor(180,200));//设置字的颜色
		String sRand="";
		//输出随机验证码
		String ctmp="";
		int itmp=0;
		for(int i=0;i<4;i++)
		{
			String[] rBase={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
			//生成第一位的区码
			int r1=random.nextInt(3)+11;//生成随机数
			String str_r1=rBase[r1];
			//第二位的区码
			int r2;
			if(r1==13){
				r2=random.nextInt(7);
			}else
			{
				r2=random.nextInt(16);
			}
			String str_r2=rBase[r2];
			int r3=random.nextInt(6)+10;
			String str_r3=rBase[r3];
			int r4;
			if(r3==10){
				r4=random.nextInt(15)+1;
			}else
				if(r3==15)
			{
				r4=random.nextInt(15);
			}else
			{
				r4=random.nextInt(16);
			}
			String str_r4=rBase[r4];
			byte[] bytes=new byte[2];
			String str_r12=str_r1+str_r2;
			int tempLow=Integer.parseInt(str_r12,16);
			bytes[0]=(byte)tempLow;
			String str_r34=str_r3+str_r4;
			int tempHigh=Integer.parseInt(str_r34,16);
			bytes[1]=(byte)tempHigh;
			ctmp=new String(bytes);//根据生成的字节数组生成汉字
			sRand+=ctmp;
			Color color=new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110));//生成一个颜色
			g.setColor(color);
			Graphics2D g2d_word=(Graphics2D)g;//创建2d graphics
			AffineTransform trans=new AffineTransform();  //用于旋转，缩放
			trans.rotate(random.nextInt(45)*3.14/180,26*i+8,7);//旋转
			float scaleSize=random.nextFloat()+0.7f;
			if(scaleSize>1f) scaleSize=1f;
			trans.scale(scaleSize, scaleSize);//缩放
			g2d_word.setTransform(trans);//放进去
			g.drawString(ctmp, width/6*i+23, height/3);//在背景上画出字来
		}
		HttpSession session=request.getSession(true);//获取session
		session.setAttribute("testnumber", sRand);//把验证码放进session 用于后面的验证
		g.dispose();//释放掉资源
		ImageIO.write(image,"JPEG",response.getOutputStream());//流进客户端
	}
	//用于生成颜色
	public Color getRandColor(int s,int e)
	{
		Random random=new Random();
		if(s>255)s=255;
		if(e>255)e=255;
		int r=s+random.nextInt(e-s);
		int g=s+random.nextInt(e-s);
		int b=s+random.nextInt(e-s);
		return new Color(r,g,b);
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
