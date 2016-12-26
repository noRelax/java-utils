package casper;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Box implements Physics //箱子类
{
	private int[][][] subImage;
	private int[][] actSubImage;
	private int[] idSubImage;
	private Image img;
	
	public int x,y;
	private int width,height;//做为物件用这个E文
	private int action = 0;//0 待机 1 毁灭
	private int id = 0;
	private int mapIndex = 5;
	
	private Product p;
	public int[][] mapData;
	private int mapW,mapH;
	
	
	private Box b1,b2,b3;
	
	public boolean isShow = true;
	private boolean isLost = false;
	private int timeRecord = 0;
	
	public Box()
	{
    
	}
	public Box(Image img,int[][][] subImage,int x,int y,int width,int height,int[][] mapData,int mapW,int mapH)
	{
		this.img = img;
		this.subImage = subImage;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.mapData = mapData;
		this.mapW = mapW;
		this.mapH = mapH;
		actSubImage = subImage[action];
		idSubImage = actSubImage[id];
		
	}
	public void events()
	{
		
		if(isShow)
		{
		  setDint();
		  setAction();
		  if(isCollidingWithProduct())
		  {
		   if(p.belong == 1)
		   {
		  	 if(p.xSpeed >= 8)
		  	 {
		  		 isLost = true;
		  	 }
		  	
		   }
		  }
		  
		  if(isLost)
		  {
		  	timeRecord++;
		  	action=1;
		  	if(timeRecord >= 4)
		  	{
		  		isShow = false;
		  	}
		  }
		 
		 
		}else{
			setPos(-20,-20);
		 }
		
	}
	public void setIsShow()
	{
		isShow = true;
		isLost = false;
		action = 0;
	}
	
	
	public boolean isCollidingUP()
	{
		if(isCollidingWithProduct(x+(width/2),y))return true;
		return false;
		 
	}
		public boolean isCollidingLEFT()
	{
		if(isCollidingWithProduct(x,y+(height/2)))return true;
		return false;
		 
	}
	
			public boolean isCollidingRIGHT()
	{
		if(isCollidingWithProduct(x+width,y+(height/2)))return true;
		return false;
		 
	}
	
	
	
	
	public void setPos(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	public void setBox(Box b1,Box b2,Box b3)
	{
		this.b1 = b1;
		this.b2 = b2;
		this.b3 = b3;
	}
	
	public void setProduct(Product p)
	{
		this.p = p;
	}
	
	private void setDint()//对于空间力的处理
	{
		int w = width/2;
		int h = height/2;
		if(isCollidingWithMap((x+w)/mapW,(y+height)/mapW)){
			
			if(isCollidingWithBox(x+w,y+height,b1))mapIndex = 0;
			if(isCollidingWithBox(x+w,y+height,b2))mapIndex = 0;
			if(isCollidingWithBox(x+w,y+height,b3))mapIndex = 0;
			
			
		switch(mapIndex)
		{
			
			
			case 0:
			      break;
			case 1:
			     
			      break;
			case 2:
			     
			      break;
			case 3:

			      break;
			case 4:
			      
			      break; 
			case 5:
            y += (3+acceleration);
			      break;                             
			      
		}
	   }
	   
	   
	   

	

	   

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
		       
		       
		  }
	  }
  	
  }
	
	
	
	public boolean isCollidingWithProduct()
	{
		if((x >= p.x && x <= (p.x+p.cellW) && y >= p.y && y <= p.y+p.cellH) || (p.x >= x && p.x <= (x+width) && p.y >= y && p.y <= (y+height) )  || ( x >= p.x && x <= (p.x+p.cellW) && (y+height) >= p.y && (y+height) <= p.y+p.cellH) || ( (x+height) >= p.x && (x+height) <= (p.x+p.cellW) && y >= p.y && y <= p.y+p.cellH))return true;
		return false;
	}


   
	
	
	private boolean isCollidingWithProduct(int x,int y)
	{
     if(x >= p.x && x <= (p.x+p.cellW) && y >= p.y && y <= (p.y+p.cellH))return true;
		return false;
	}
	
	private boolean isCollidingWithBox(int x,int y,Box b)
	{
		if(x >= b.x && x <= (b.x+b.width) && y >= b.y && y <= (b.y+b.height))return true;
		return false;
		
	}
	
	public boolean isCollidingWithMap(int cellX,int cellY)
	{
		
		if(cellX >= 0 && cellX < mapData[0].length && cellY>= 0 && cellY < mapData.length )
		{
			if(mapData[cellY][cellX] > 0 && mapData[cellY][cellX] <= 12)
			{
				mapIndex = 0;
				return true;
			}
			if(mapData[cellY][cellX] == 13 || mapData[cellY][cellX] == 17 || mapData[cellY][cellX] == 18 )
			{
				mapIndex = 1;
				return true;
			}
			if(mapData[cellY][cellX] >13 && mapData[cellY][cellX] <= 16)
			{
				mapIndex = 2;
				return true;
			}
			if(mapData[cellY][cellX] == 19)
			{
				mapIndex = 3;
				return true;
			}
			if(mapData[cellY][cellX] == 20 || mapData[cellY][cellX] == 23)
			{
				mapIndex = 4;
				return true;
			}
			
				
			
		}
		  mapIndex = 5;
			return true;
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