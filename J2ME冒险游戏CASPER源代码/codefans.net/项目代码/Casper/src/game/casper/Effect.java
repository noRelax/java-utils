package casper;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Effect //特效类~~~属于细节处理
{
	private int x,y;
	private int[][] actSubImage;
	private int[] idSubImage;
	private int id = 0;
	private Image img;
	
	private int timeRecord = 0;
	private boolean isAnimation = false;
	
	
	public Effect()
	{
		
		
	}
	public Effect(Image img,int[][] actSubImage,int x,int y)
	{
		this.img = img;
		this.actSubImage = actSubImage;
		idSubImage = actSubImage[id];
		this.x = x;
		this.y = y;
	}
	
	
	public void setPos(int x,int y)
	{
		this.x = x;
		this.y = y;
		
	}
	public void setIsAnimation()
	{
		
		isAnimation = true;
		
	}
	
	
	public void events()
	{
		if(isAnimation)
		{
			timeRecord++;
			if(timeRecord >= 4)
			{
				timeRecord = 0;
				isAnimation = false;
			}
			setAction();
		}
		
		
		
	}
	
	
	
	private void setAction()
	{



			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;


		
	}
	
	
	public void paint(Graphics g)
	{
		
		if(isAnimation)
		{
		  drawSubImage(x,y,g);
		}
	}
	private void drawSubImage(int x,int y,Graphics g)
	{
		g.setClip(x,y,idSubImage[2],idSubImage[3]);
		g.drawImage(img,x-idSubImage[0],y-idSubImage[1],Graphics.TOP|Graphics.LEFT);
		g.setClip(0,0,176,208);
		
	}
	
	
}