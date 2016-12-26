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
 * ������֤��
 * @author ������
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
		response.setHeader("Cache-Control", "No-cache");//�ĵ�������;
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");//������Ӧ����ΪͼƬ
		int width =166;//ͼƬ�Ŀ�
		int height=45;//��
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);//����ͼƬ������
		Graphics g=image.getGraphics();//��ȡ�����������graphics
		Random random=new Random();//���������
		Font mFont=new Font("����",Font.ITALIC,26);//��������
		g.setColor(getRandColor(200,250));//������ɫ
		g.fillRect(0, 0, width, height);//�������ͼ ���ǻ��Ʊ���
		g.setFont(mFont);//��������
		g.setColor(getRandColor(180,200));//�����ֵ���ɫ
		String sRand="";
		//��������֤��
		String ctmp="";
		int itmp=0;
		for(int i=0;i<4;i++)
		{
			String[] rBase={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
			//���ɵ�һλ������
			int r1=random.nextInt(3)+11;//���������
			String str_r1=rBase[r1];
			//�ڶ�λ������
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
			ctmp=new String(bytes);//�������ɵ��ֽ��������ɺ���
			sRand+=ctmp;
			Color color=new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110));//����һ����ɫ
			g.setColor(color);
			Graphics2D g2d_word=(Graphics2D)g;//����2d graphics
			AffineTransform trans=new AffineTransform();  //������ת������
			trans.rotate(random.nextInt(45)*3.14/180,26*i+8,7);//��ת
			float scaleSize=random.nextFloat()+0.7f;
			if(scaleSize>1f) scaleSize=1f;
			trans.scale(scaleSize, scaleSize);//����
			g2d_word.setTransform(trans);//�Ž�ȥ
			g.drawString(ctmp, width/6*i+23, height/3);//�ڱ����ϻ�������
		}
		HttpSession session=request.getSession(true);//��ȡsession
		session.setAttribute("testnumber", sRand);//����֤��Ž�session ���ں������֤
		g.dispose();//�ͷŵ���Դ
		ImageIO.write(image,"JPEG",response.getOutputStream());//�����ͻ���
	}
	//����������ɫ
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
