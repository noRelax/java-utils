package casper;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Bullet //子弹类
{
	public int[][][] subImage;//多种动作
	private int[][] actSubImage;
	private int[] idSubImage;
	private Image img;
  private int x,y,width,height;
  private int distance = 0;
  private int ar = 80;//射程
  public int attackDir=2;//攻击方向
	private int id = 0;
	public int action = 0;
	
	public int sex;
	
	
	private Product p;
	private Monster m;
	public boolean isShow = false;
	public boolean isOver = true;
	
	public Bullet(Image img,int[][][] subImage,int x,int y,int width,int height)
	{
		this.img = img;
		this.subImage = subImage;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height; 
		actSubImage = subImage[action];
		idSubImage = actSubImage[id];
		
	}
	public void setSize(int width,int height)
	{
		this.width = width;
		this.height = height;
	}
	
	
	public void setPos(int x,int y)
	{
		
		this.x = x;
		this.y = y;
		
	}
	public void setSex(int sex)
	{
		this.sex = sex;
		action = sex;
	}
	
	public void setFire()//子弹飞出动画Product
	{
		if(isShow)
		{
			isOver = false;
		
			 if(attackDir == 2)
			 {
			  x+=4;
			  distance +=4;
			  if(distance >= ar)
			 {
				 isShow = false;
				 isOver = true;
				 distance = 0;
			 }
			 }else if(attackDir == 3)
			 {
				 x-=4;
				 distance -=4;
				  if(distance <= -ar)
			 {
				 isShow = false;
				 isOver = true;
				 distance = 0;
			 }
			  }
			
			
			setAction();
			if(isCollidingWithProduct())
			{
				p.sex.setAttackStone(sex);
				isShow = false;
				isOver = true;
				distance = 0;
			}
			
			
		}
		
		
		
		
	}
	
	public void setFireForMonster()//子弹飞出动画monster
	{
		if(isShow)
		{
			isOver = false;
		
			 if(attackDir == 2)
			 {
			  x+=4;
			  distance +=4;
			  if(distance >= ar)
			 {
				 isShow = false;
				 isOver = true;
				 distance = 0;
			 }
			 }else if(attackDir == 3)
			 {
				 x-=4;
				 distance -=4;
				  if(distance <= -ar)
			 {
				 isShow = false;
				 isOver = true;
				 distance = 0;
			 }
			  }
			
			
			setAction();
			if(isCollidingWithMonster())
			{
				m.sex.setAttackStone(sex);
				isShow = false;
				isOver = true;
				distance = 0;
			}
			
			
		}
		
		
		
		
	}
	
	public void setAR(int ar)//射程
	{
		this.ar = ar;
	}
	
	
	
	public void setProduct(Product p)
	{
		this.p = p;
		
	}
	public void setMonster(Monster m)
	{
		this.m = m;
	}
	
	public void setAction()
	{
		if(action < subImage.length)
		{
		switch(action)
		{
			case 0:
			      actSubImage = subImage[0];
			      id %= actSubImage.length;
			      idSubImage = actSubImage[id];
			      id++;
			      break;
			case 1:
			      actSubImage = subImage[1];
			      id %= actSubImage.length;
			      idSubImage = actSubImage[id];
			      id++;
			      break;
			case 2:
			      actSubImage = subImage[2];
			      id %= actSubImage.length;
			      idSubImage = actSubImage[id];
			      id++;
			      break;
			      
			
			
		}
		
	}
		
	}
	
	
	
	
	
	public boolean isCollidingWithProduct()
	{
		
		if((x >= p.x && x <= (p.x+p.cellW) && y >= p.y && y <= p.y+p.cellH) || (p.x >= x && p.x <= (x+width) && p.y >= y && p.y <= (y+height) )  )return true;
		return false;
		
	}
	
	public boolean isCollidingWithMonster()
	{
		
		if((x >= m.x && x <= (m.x+m.cellW) && y >= m.y && y <= m.y+m.cellH) || (m.x >= x && m.x <= (x+width) && m.y >= y && m.y <= (y+height) )  )return true;
		return false;
	}
	
	public void setShow(boolean isShow)
	{
		this.isShow = isShow;
	}
	
	public void paint(Graphics g)
	{
		if(isShow)
		{
		 drawSubImage(g,x,y);
		}
		
	}
	
	
	
	
	private void drawSubImage(Graphics g,int x,int y)
	{
		g.setClip(x,y,idSubImage[2],idSubImage[3]);
		g.drawImage(img,x-idSubImage[0],y-idSubImage[1],Graphics.TOP|Graphics.LEFT);
		g.setClip(0,0,176,208);
		
	}
	
}