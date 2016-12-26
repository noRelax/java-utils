package casper;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Tool //道具类
{
	
	private int[][] subImage;
	private int[] idSubImage;
	private Image img;
	
	public int x,y;
	public int width,height;
	
	public int function;//0 加50的生命，1 加10的技能值，2 减 80 生命
	
	private Product p;
	
	public int isShow = 0;
	
	
	public Tool()
	{
	}
	
	public Tool(Image img,int[][]  subImage,int x,int y,int width,int height,int function,Product p)
	{
		this.img = img;
		this.subImage = subImage;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.function = function;
		this.p = p;
		idSubImage = subImage[function];
		
	}
	public void setPos(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setProduct(Product p)
	{
		this.p = p;
	}
	public void setShow(int isShow)
	{
		this.isShow = isShow;
	}
	
	public boolean isCollidingWithProduct()
	{
		
		if((p.x >= x && p.x <= (x+width) && p.y >= y && p.y <= (y+height)) ||(x >= p.x && x <= (p.x+p.cellW) && y >= p.y && y <= (p.y+p.cellH)) ){
			
			if(isShow == 0)
			{
				p.sex.setRoleHp(function);
				isShow = 1;
			}
			return true;
		}
		
		return false;
		
		
	}
	private void drawSubImage(Graphics g,int x,int y)
	{
		g.setClip(x,y,idSubImage[2],idSubImage[3]);
		g.drawImage(img,x-idSubImage[0],y-idSubImage[1],Graphics.TOP|Graphics.LEFT);
		g.setClip(0,0,176,208);
		
	}
	public void paint(Graphics g)
	{
		if(isShow == 0)
		{
		 drawSubImage(g,x,y);
		}
	}
	
	
	
}