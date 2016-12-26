package casper;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


public class Product implements Physics  //主元素类 实现主角的功能
{
	
	public final static int UP = 0;
	public final static int DOWN = 1;
	public final static int RIGHT = 2;
	public final static int LEFT = 3;
	
	public int lxDint=1;//y方向力
	public int lxfDint= -1;//x方向力
	public int rxDint=1;
	public int rxfDint = -1;
	
	public int yDirection=0;//当前移动上的Y方向
	public int xDirection=0;//x方向
	
	public boolean isAir;
	public boolean isWater;
	public boolean isSlop;
	public boolean isUp;
	public boolean isOnWater;
	
	
	private boolean isRight = false;
	private boolean isLeft = false;
	private boolean isOn = false;
	
	
	
	private Image img;
	public int cellW,cellH;
	private int[][][] subImage;
	private int[][] actSubImage;
	private int[] idSubImage;
	
	
	public int action = 0;//初始动作 
	private int attackDir = RIGHT;//攻击方向
  public int belong = 0;//0木 1铁 2水
  private boolean isChange = false;//是否为变身状态
  public boolean isDied = false;//死亡动画
  private boolean isSpaceAction = false;//空闲动画
  private int spaceAction = 0;//空闲动作编号
  private int spaceTime = 0;//空闲计时
  public Sex sex;//对应的属性计算
  public Bullet bullet;//封装进来的子弹
  private int animationRecord = 0;//动画播放贞数记录
  private int timeRecord = 0;
	
	private int id = 0;
	private int isAnimationOver = 0;//0未启动 1播放中 2完成
	
	public int gravity;
	public int hardness;
	private int dint=1,fDint=1;
	
	public int x=0,y=0;//主坐标
	public int xSpeed,ySpeed;//X，Y的最终改变量
	private int dir,pDir;
	
	
	private int mapIndex = 5;
	public int[][] mapData;
	private int mapW,mapH;//单元格大小
	

	
	
	private Monster p;
	
	private Effect effect;
	private boolean lastAppearance = false;
	
	
	public boolean cDown = false;
	public boolean cRight = false;
	public boolean cLeft = false;
	
	private boolean isPassage =false;
	
	
	public Product(Image img,int cellW,int cellH,int[][][] subImage,int gravity,int[][] mapData,int mapW,int mapH,int hardness,int[][][] bulletSubImage,int bwidth,int bheight,Image imgEffect,int[][] effectSubImage)
	{
		this.img = img;
		this.cellW = cellW;
		this.cellH = cellH;
		this.subImage = subImage;
		this.gravity = gravity;
		this.mapData = mapData;
		this.mapW = mapW;
		this.mapH = mapH;
		this.hardness = hardness;
		dir = DOWN;
		actSubImage = subImage[action];
		idSubImage = actSubImage[id];
		dint = gravity;
		sex =new Sex();

		
		bullet = new Bullet(img,bulletSubImage,x,y,bwidth,bheight);
		effect = new Effect(imgEffect,effectSubImage,x,y);
	}

	
	public Product()
	{
		
	}
	
	public void setPos(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setMonster(Monster p)//传入物体
	{
		this.p = p;
	}
	
	public void setFire()//开火设置
	{
		if(bullet.isOver)
		{
			
			bullet.isShow = true;
			if(attackDir == RIGHT)
			{
				if(belong == 0)action = 15;
				if(belong == 1)action = 16;
				if(belong == 2)action = 17;
        			
			}
			if(attackDir == LEFT)
			{
				if(belong == 0)action = 12;
				if(belong == 1)action = 13;
				if(belong == 2)action = 14;
        			
			}
			
		}
		
		
		
	}
	
	public void setChange(int belong)//变身设置
	{
		if(!isAir)
		{
		 this.belong = belong;
		 sex.setRoleSex(belong);
		 isChange = true;
		 if(belong == 0)gravity = 1;
		 if(belong == 1)gravity = 2;
		 if(belong == 2)gravity = 2;
		 dint = gravity;
		}
	}
	
	public void events(Monster p)
	{
		if(!isDied)
	 {
	 	if(!sex.isDie())
	 	{
		 setMonster(p);
		 setDint();
		 bullet.setMonster(p);
		 if(belong == 2 && isWater)
		 {
		 	sex.roleHp -= 100;
		 }
		 setSpaceTime();
		 if((isOnWater == true) && (lastAppearance == false))
		 {
		 	 effect.setIsAnimation();
		 }
		  lastAppearance = isOnWater;
		  effect.setPos(x,y);
		  effect.events();
		 if(!bullet.isShow)
		 {
			 bullet.setSex(belong);
			 if(xSpeed > 0)
			 {
			 	bullet.attackDir = RIGHT;
			 	attackDir = RIGHT;
			 }
			 if(xSpeed < 0)
			 {
			 	bullet.attackDir = LEFT;
			 	attackDir = LEFT;
			 }
			 bullet.setPos(x,y);
			 if(!isChange)
			 {
			 	if(!isSpaceAction)
			 	{
			    setAnimation();
			  }
			  if(isSpaceAction)
			  {
			  	setSpaceAction();
			  }
			 }
			 
			 if(isChange)
			 {
			 	action = 20;
			 	animationRecord ++;
			 	if(animationRecord == 5)
			 	{
			 		
			 		animationRecord = 0;
			 		isChange = false;
			 	}
			 	
			 	
			 	
			 }
			 
		 }
		
		 bullet.setFireForMonster();
     if(bullet.isOver)bullet.setPos(x,y);
    }
    if(sex.isDie())
    {
    	action = 21;
    	animationRecord++;
    	if(animationRecord == actSubImage.length)
    	{
    		isDied = true;
    		animationRecord = 0;
    	}
    }
    
    
    setAction();
    
	 }
		//if(isDied)
		//{
			//setPos(-20,-20);
			
		//}
		
		
		
	}
	private void setSpaceTime()//空闲计时
	{
		if(!isWater)
	 {
		if(xSpeed == 0 && ySpeed == 0)
		{
			spaceTime ++;
			if(spaceTime >= 50)
			{
				
				isSpaceAction = true;
			}
		}else{
			spaceTime = 0;
			isSpaceAction = false;
			isAnimationOver = 0;
		}
	 }
		
	}
	
	private void setSpaceAction()//空闲动画
	{
		
		if(isAnimationOver == 0)
		{
			if(spaceAction == 0)
			{
				action = 18;
				spaceAction = 1;
			}else
			{
				action = 19;
				spaceAction = 0;
			}
			
			isAnimationOver = 1;
		}
		
		
	}
	
	
	private void setAnimation()//对应动画扑捉
	{
		if(belong == 0)
		{
			if(xSpeed == 0 && ySpeed == 0 )action = 0;
			if(xSpeed < 0)action = 3;
			if(xSpeed > 0)action = 3;
			if(ySpeed < 0)action = 6;
			if(ySpeed > 0)action = 9;


			
		}else if(belong == 1){
			if(xSpeed == 0 && ySpeed == 0 )action = 1;
			if(xSpeed < 0)action = 4;
			if(xSpeed > 0)action = 4;
			if(ySpeed < 0)action = 7;
			if(ySpeed > 0)action = 10;
			
		}else if(belong == 2){
			
			if(xSpeed == 0 && ySpeed == 0 )action = 2;
			if(xSpeed < 0)action = 5;
			if(xSpeed > 0)action = 5;
			if(ySpeed < 0)action = 8;
			if(ySpeed > 0)action = 11;
			
		}
		
	}
	
	public void setDint()//空间力的处理
	{
		int w = cellW/2;
		int h = cellH/2;
		//testDir();
		//System.out.println("the mapIndex is "+mapIndex+"the dir is "+dir);
		if(isCollidingWithMap((x+w)/mapW,(y+cellH)/mapW)){
			if(cDown)mapIndex = 0;
		switch(mapIndex)
		{
			
			
			case 0:
			       //y -= 1;
             if(ySpeed > dint){fDint = -(ySpeed*2-hardness);}
             if(ySpeed < dint){fDint = -dint;}
             if(ySpeed == 1){ySpeed = 0; fDint = -dint;}
             isOn = true;
             isOnWater = false;
             isAir = false;
             isSlop = false;
             isWater = false;
			      break;
			case 1:
			      y-=1;
			      isUp = true;
			      fDint = xDirection;
			      //fDint = 0;
			      isOn = false;
			      isOnWater = false;
			      isSlop = true;
			      isWater = false;
			      break;
			case 2:
			      y-=1;
			      isOn = false;
			      isOnWater = false;
			      isUp = false;
			      isSlop = true;
			      isWater = false;
			      break;
			case 3:
			      isOn = false;
            isOnWater = false;
			      isWater = true;
			      isAir = false;
			      isSlop = false;
			      break;
			case 4:
			      isOn = false;
			      isOnWater = true;
			      fDint = -1;
			      isWater = true;
			      isSlop = false;
			      isAir = false;
			      break; 
			case 5:
			      
            fDint = 0;
            isOn = false;
            isOnWater = false;
	          isAir = true;
	          isSlop = false;		
	          isWater = false;
			      break;                             
			      
		}
	   }

	   //System.out.println("the mapIndex is "+mapIndex);
	   	if(isCollidingWithMap((x+w)/mapW,y/mapW)){
		switch(mapIndex)
		{
			
			
			case 0:
			       if(ySpeed < dint){fDint = -(ySpeed-dint);}
             if(isWater)y+=1;
             //isAir = false;
             isWater = false;
			      break;
			case 1:

			      isSlop = true;
			      isWater = false;
			      break;
			case 2:

			      isSlop = true;
			      isWater = false;
			      break;
			case 3:

			      isWater = true;
			      isAir = false;
			      
			      break;
			case 4:


			      isWater = true;
			      
			      isAir = false;
			      break; 
			case 5:
	          	
	          //isWater = false;
			      break;                             
			      
		}
	   }
	   //System.out.println("the mapIndex is "+mapIndex);
	   if(isCollidingWithMap(x/mapW,(y+h)/mapW)){
	   	if(cLeft)mapIndex = 0;
		switch(mapIndex)
		{
			
			
			case 0:
			       if(!isSlop)
			       {
			        if(xSpeed < lxDint){lxfDint = -(xSpeed-lxDint);}
			       }
            isLeft = false;
			      break;
			case 1:
			 
			      fDint = xDirection;
			      isLeft = false;
			      isSlop = true;
			      isWater = false;
			      break;
			case 2:
			      isLeft = true;
			      isSlop = true;
			      isWater = false;
			      break;
			case 3:
			      
			      lxfDint = -lxDint;
            fDint = -2;
            isLeft = false;
			      isWater = true;
			      isAir = false;
			      //isSlop = false;
			      break;
			case 4:
            //xSpeed = 0;
            //ySpeed = 0;
            lxfDint = -lxDint;
            fDint = -1;
            isLeft = false;
			      isWater = true;
			      //isSlop = false;
			      isAir = false;
			      break; 
			case 5:
			      lxfDint = -lxDint;
			      isLeft = false;
	          //isSlop = false;	
	          //isWater = false;
			      break;                             
			      
		}
	   }
	   //System.out.println("the mapIndex is "+mapIndex);
	   if(isCollidingWithMap((x+cellW)/mapW,(y+h)/mapW)){
	   	if(cRight)mapIndex = 0;
		switch(mapIndex)
		{
			
			
			case 0:
			       if(!isSlop)
			       {
              if(xSpeed > rxDint){rxfDint = -(xSpeed+rxDint);}
              
             }
             isRight = false;
             //isAir = false;
			      break;
			case 1:
			       //ySpeed += xDirection;
			       x -= 1;
			      isRight = true;
			      isUp = true;
			      isSlop = true;
			      isWater = false;
			      break;
			case 2:
			      
			      x += 1;
			      isRight = false;
			      isUp = false;
			      isSlop = true;
			      isWater = false;
			      break;
			case 3:
			      rxfDint = -rxDint;
            fDint = -2;
            isRight = false;
			      isWater = true;
			      isAir = false;
			      break;
			case 4:
            //xSpeed = 0;
            if(ySpeed > 0)ySpeed = 0;
            rxfDint = -rxDint;
            fDint = -1;
            isRight = false;
			      isWater = true;
			      isAir = false;
			      break; 
			case 5:
			      rxfDint = -rxDint;
			      isRight = false;		
	          //isWater = false;
			      break;                             
			      
		}
	   }
	   //System.out.println("the mapIndex is "+mapIndex);
	   
	   
	   if(!isSlop)
	   {
	   	
	      
	     ySpeed += dint+fDint+yDirection;
	     xSpeed += lxDint+lxfDint+rxDint+rxfDint+xDirection;
	     //System.out.println("the xSpeed is "+xSpeed);
	    /* dir = DOWN;
	   	setDistance();
	   	dir = UP;
	   	setDistance();
	   	dir = LEFT;
	   	setDistance();
	   	dir = RIGHT;
	   	setDistance();*/
	   }
	   

    if(isSlop)
    {
    	if(!((isOn && isLeft)||(isOn && isRight)))
    	{
    	if(isUp == true)
    	{
    		 
          
          ySpeed += (-xDirection)+dint;
          xSpeed = -(ySpeed*2);

    	}
    		if(isUp ==false)
    	{
        
        ySpeed += xDirection+dint;
    	  xSpeed = ySpeed*2;

    	}
     }else{
     	
     	if(isUp == true)
    	{
    		 
          
          ySpeed += -dint;
          xSpeed = -(ySpeed*2);

    	}
    		if(isUp ==false)
    	{
        
        ySpeed += -dint;
    	  xSpeed = ySpeed*2;

    	}
     	
     }
     
     
    }
    if(isOnWater)
    {
    	timeRecord++;
    	if(timeRecord ==4)
    	{
    	  y-= 2;
    	}
    	if(timeRecord ==8)
    	{
    		y+= 2;
    	 timeRecord = 0;
    	}
    }
    //if(isWater)System.out.println("iswater!!!");
    if(xSpeed > 0)xSpeed--;
    if(xSpeed < 0)xSpeed++;
    xDirection = 0;
    yDirection = 0;
     if(ySpeed > 10)ySpeed = 10;
     if(ySpeed < -12)ySpeed = -10;
     //System.out.println("the ySpeed = "+ySpeed);
		y +=ySpeed;
		x +=xSpeed;
		
		setIsColliding();
		//testDir();
	}
	
	public void setIsColliding()
	{
		if(isCollidingWithProduct())
		{
		
			switch(pDir)
		{
			case UP:
			       //if(ySpeed > dint){fDint = -(ySpeed+hardness);}
             //if(ySpeed < dint){fDint = -dint;}
             p.sex.roleHp -= 100;
			       break;
			case RIGHT:
			       x+=2;
			       break;
			case LEFT:
			       x-=2;
			       break;
			case DOWN:
			       y +=5;
			       sex.roleHp -= 100;
			       break;
			       
			
			
		}
		
		
	  }
	}
	
	
	
	
	
	public void setPower(int xDirection,int yDirection)
	{
		this.xDirection = xDirection;
		if(xSpeed >5)this.xDirection = 0;
		if(xSpeed <-5)this.xDirection = 0;
		
		this.yDirection = yDirection;
		if(isAir)this.yDirection = 0;

		if((!isWater) && yDirection >0)this.yDirection = 0;
		if(isWater && yDirection < 0)this.yDirection = 0;
		if(isWater && yDirection > 0)this.yDirection = 1;

		
	}
	
	public void setAction()
	{
		if(isPassage)
		{
			action = 22;
			isPassage = false;
		}
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
			case 6:
			     actSubImage = subImage[6];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 7:
			     actSubImage = subImage[7];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 8:
			     actSubImage = subImage[8];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 9:
			     actSubImage = subImage[9];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 10:
			     actSubImage = subImage[10];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 11:
			     actSubImage = subImage[11];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 12:
			     actSubImage = subImage[12];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 13:
			     actSubImage = subImage[13];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 14:
			     actSubImage = subImage[14];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 15:
			     actSubImage = subImage[15];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 16:
			     actSubImage = subImage[16];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 17:
			     actSubImage = subImage[17];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 18:
			     actSubImage = subImage[18];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 19:
			     actSubImage = subImage[19];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 20:
			     actSubImage = subImage[20];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 21:
			     actSubImage = subImage[21];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			case 22:
			     actSubImage = subImage[22];
			     id %= actSubImage.length;
			     idSubImage = actSubImage[id];
			     id++;
			     break;
			     
		
			
		}
	 }
		
	}
	
	public void paint(Graphics g)
	{
		if(!isDied)
		{
			bullet.paint(g);
		 drawSubImage(g,x,y);
		 effect.paint(g);
		 
		}
		
	}
	
	private void drawSubImage(Graphics g,int x,int y)
	{
		int px = x-((idSubImage[2]-cellW)/2);
		int py = y-(idSubImage[3]-cellH);
		g.setClip(px,py,idSubImage[2],idSubImage[3]);
		g.drawImage(img,px-idSubImage[0],py-idSubImage[1],Graphics.TOP|Graphics.LEFT);
		g.setClip(0,0,176,208);
	}
	
	private void testDir()
	{
		if(xSpeed < 0)
		{
			dir = LEFT;
		}
		
		if(xSpeed >0)
		{
			dir = RIGHT;
		}
		if(ySpeed <0)
		{
			dir = UP;
		}
		if(ySpeed >0)
		{
			dir = DOWN;
			
		}
		
		
		
	}
	
	
	
	
	public boolean isCollidingWithMap(int cellX,int cellY)
	{
		if(cellX >= 0 && cellX < mapData[0].length && cellY>= 0 && cellY < mapData.length )
		{
			if((mapData[cellY][cellX] > 0 && mapData[cellY][cellX] <= 12) || mapData[cellY][cellX] == 35 || mapData[cellY][cellX]==36 )
			{
				if(mapData[cellY][cellX] ==7 || mapData[cellY][cellX] ==8 || mapData[cellY][cellX] ==9)sex.setAttackStoneForMap(2);
				mapIndex = 0;
				if(mapData[cellY][cellX] == 35 && belong == 2)
				{
					isPassage = true; 
				  mapIndex = 5;
				}
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
	
	private boolean isColliding(int ax,int ay)
	{
		if(ax >= x && ax <= (x+cellW) && ay >= y && ay <= (y+cellH) )return true;
		return false;
		
		
	}
	
	public boolean isCollidingWithProduct()
	{
		if(isColliding(p.x+(p.cellW/2),p.y))
		{ 
			pDir = UP;
			return true;
		}
		if(isColliding(p.x+(p.cellW/2),p.y+p.cellH))
		{
			pDir = DOWN;
			return true;
		}
		if(isColliding(p.x,p.y+(p.cellH/2)))
		{
			pDir = LEFT;
			return true;
		}
		if(isColliding(p.x+p.cellW,p.y+(p.cellH/2)))
		{
			pDir = RIGHT;
			return true;
		}
		
		return false;
	}
	private void setDistance()//根据方向 判断与最近碰撞的距离
	{
		int cx = x/mapW;
		int cy = y/mapW;
		
		if(cx >= 0 && cx < mapData[0].length && cy >= 0 && cy < mapData.length)
		{
			if(dir == UP)
			{
				for(int i=cy; i >= 0; i--)
				{
					if(mapData[i][cx]>0 && mapData[i][cx]<=12)
					{
						int b =(i*mapH)-y;
						if(ySpeed < b)
						{
							ySpeed = b;
						}
						break;
					}
				}
				
			}
			if(dir == DOWN)
			{
								for(int i=cy; i < mapData.length; i++)
				{
					if(mapData[i][cx]>0 && mapData[i][cx]<=12)
					{
						int b =(i*mapH)-y;
						if(ySpeed >b)
						{
							ySpeed = b;
						}
						break;
					}
				}
			}
			if(dir == LEFT)
			{
				for(int i=cx;i >= 0;i--)
				{
					if(mapData[cy][i]>0)
					{
						int b = (i*mapW)-x;
						if(xSpeed < b)
						{
							
							xSpeed = b;
						}
						break;
					}
				}
			}
			if(dir == RIGHT)
			{
				for(int i=cx;i < mapData[0].length;i++)
				{
					if(mapData[cy][i]>0)
					{
						int b = (i*mapW)-x;
						if(xSpeed > b)
						{
							xSpeed = b;
						}
						break;
					}
				}
			}
			
			
			
		}
		
		
		
	}
	
	
}