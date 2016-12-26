package casper;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Graphics;

public class Monster implements Physics //怪物类  
{
	public int x,y;
	private int lastX,lastY;//移动前的坐标
	private int xSpeed = 1;//左右移动量
	private int ySpeed = 4;
	public Image img;
	public int cellW,cellH;
	public int[][][] subImage;
	private int[][] actSubImage;
	private int[] idSubImage;
	
	public int action = 4;//出始动作
	private int id = 0;
	public int belong = 0;//初始属性
	public Sex sex;
	public Bullet bullet;
	public int attackDir = 2;//攻击方向
	
	private int timeRecord = 0;
	
	
	private Product p;
	
	public int[][] mapData;
	private int mapIndex = 5;
	private int mapW,mapH;
	
	
	private int animationRecord = 0;//计时
	public boolean isDied = false;
	
	
	public Monster()
	{
		
	}
	
	public Monster(Image img,int cellW,int cellH,int[][][] subImage,int belong,int[][][] bulletSubImage,int bwidth,int bheight,int[][] mapData,int mapW,int mapH)
	{
		this.img = img;
		this.cellW = cellW;
		this.cellH = cellH;
		this.subImage = subImage;
		this.belong = belong;
		this.mapData = mapData;
		this.mapW = mapW;
		this.mapH = mapH;
		actSubImage = subImage[action];
		idSubImage = actSubImage[id];
		
		
		sex =new Sex();
		bullet =new Bullet(img,bulletSubImage,x,y,bwidth,bheight);
		sex.setRoleSex(belong);
		
	}
	
	
	
	public void paint(Graphics g)
	{
		if(!isDied)
		{
		 bullet.paint(g);
		 drawSubImage(g,x,y);
		}
	}
	
	
	private void drawSubImage(Graphics g,int x,int y)
	{

		g.setClip(x,y,idSubImage[2],idSubImage[3]);
		g.drawImage(img,x-idSubImage[0],y-idSubImage[1],Graphics.TOP|Graphics.LEFT);
		g.setClip(0,0,176,208);
	}
	
	
	public void setPos(int x,int y)
	{
		this.x = x;
		this.y = y;
		lastX = x;
		lastY = y;
		
	}
	public void setProduct(Product p)
	{
		this.p = p;
		bullet.setProduct(p);
		
	}
	public void setSpeed(int xSpeed,int ySpeed)
	{
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		
	}
	
	
	public void setFire()
	{
		if(bullet.isOver)
		 {
		 	 timeRecord++;
		 	 if(timeRecord ==3)
		 	 { 
		 	 	timeRecord =0;
		 	  bullet.isShow = true;
		 	 }
		 	 
		 }
	}
	public void setBelong(int belong)
	{
		this.belong = belong;
		sex.setRoleSex(belong);
	}
	public void events()
	{
		
		if(!isDied)
		{
			if(!sex.isDie())
			{
		  setDint();
		  setAnimation();
		 
		  if(!bullet.isShow)
		  {
			
			  if(attackDir == 2)bullet.attackDir = 2;
			  if(attackDir == 3)bullet.attackDir = 3;
			  bullet.setSex(belong);
		  }
		  bullet.setFire();
		  if(bullet.isOver)bullet.setPos(x,y+(cellW/2));
		  }
		  
		   if(sex.isDie())
    {
    	action = 5;
    	animationRecord++;
    	if(animationRecord == actSubImage.length)
    	{
    		isDied = true;
    		animationRecord = 0;
    	}
    }
		  
		setAction();
		
		}else{
			
			setPos(-20,-20);
		}
		
	}
	
	
	
	private void setDint()//对于空间力的处理
	{
		int w = cellW/2;
		int h = cellH/2;
		if(isCollidingWithMap((x+w)/mapW,(y+cellH)/mapW)){
		switch(mapIndex)
		{
			
			
			case 0:
			      y -= 1;
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
            y += acceleration;
			      break;                             
			      
		}
	   }

	   	if(isCollidingWithMap((x+w)/mapW,y/mapW)){
		switch(mapIndex)
		{
			
			
			case 0:
			      //y+=ySpeed;
			      
			      break;
		}
	   }
	   if(isCollidingWithMap(x/mapW,(y+h)/mapW)){
		switch(mapIndex)
		{
			
			
			case 0:
			      x += xSpeed;
			      break;                            
			      
		}
	   }
	   if(isCollidingWithMap((x+cellW)/mapW,(y+h)/mapW)){
		  switch(mapIndex)
		 {
			
			
			case 0:
			       x-=xSpeed;
			      break;                         
			      
		  }
	   }

	   

	}
	public void setMove(int dir)
	{
		switch(dir)
		{
			case 0:
			      y -= ySpeed;
			      break;
			case 2:
			      x += xSpeed;
			      break;
			case 3:
			      x -= xSpeed;
			      break;
			
		}
		
		
		
	}
	
	private void setAnimation()//动画方向
	{
		int bx = x-lastX;
	  int by = y-lastY;
		if(bx < 0)
		{
			action = 1;
			attackDir = 3;
		}
		if(bx > 0)
		{
			action = 2;
			attackDir = 2;
		}
		if(bx == 0 && by == 0)
		{
			action = 0;
		}
		
		//旧换新
		lastX = x;
		lastY = y;
		
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
			case 3:
			     actSubImage = subImage[3];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 4:
			     actSubImage = subImage[4];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 5:
			     actSubImage = subImage[5];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
		       break;
		       
		       
		  }
	  }
  	
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
	
	
	
	public boolean isCollidingWithProduct()
	{
		if((x >= p.x && x <= (p.x+p.cellW) && y >= p.y && y <= p.y+p.cellH) || (p.x >= x && p.x <= (x+cellW) && p.y >= y && p.y <= (y+cellH) )  )return true;
		return false;
	}
	
	
	
	
	
	
	
	
}