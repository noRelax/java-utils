package casper;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Trap implements Physics //陷阱类 处理各种动态伤害动画计算
{
	
	private int x,y;
	public int width,height;
	private int tx,ty,tw,th;//触发位置
	private boolean isOpen = false;//是否触发
	private int function = 3;//伤害类型
	private boolean isTime = false;
	
	
	private int mapIndex = 0;
	private Product p;
	private Monster m;
	private int[][] mapData;
	public Image img;
	public int[][][] subImage;
	private int[][] actSubImage;
	private int[] idSubImage;
	private int id = 0;
	private int action = 0;//0 准备动作 1 触发动作 2结束动作
	private int appearance = 0;//0 未启动 1 启动
	private int[][] button1;
	
	
	private int timeRecord = 0;
	private int animationRecord = 0;
	
	public Trap(Image img,int[][][] subImage,int x,int y,int width,int height,boolean isTime,int[][] button1)
	{
		this.img = img;
		this.subImage = subImage;
		actSubImage = subImage[action];
		idSubImage = actSubImage[id];
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isTime = isTime;
		this.button1 = button1;
		
	}
	
	
	public Trap()
	{
		
	}
	public void setIsTime(boolean isTime)//打开关闭自动
	{
		this.isTime = isTime;
	}
	
	public void setProduct(Product p)
	{
		this.p = p;
	}
	public void setMonster(Monster m)
	{
		this.m = m;
	}
	public void setPos(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setButton(int tx,int ty,int tw,int th)
	{
		this.tx = tx;
		this.ty = ty;
		this.tw = tw;
		this.th = th;
		
	}
	
	
	public void events()
	{
			if(isTime)
			{ 
				action = 0;
				timeRecord++;
				if(timeRecord >= 40)
				{
					isOpen = true;
					timeRecord = 0;
				}
				if(isOpen)
				{
					action = 1;
					animationRecord++;
					if(isCollidingWithProduct())
					{
						p.sex.setAttackStone(function);
					}
					if(isCollidingWithMonster())
					{
						m.sex.setAttackStone(function);
					}
					if(animationRecord >= 10)
					{
						animationRecord = 0;
						isOpen = false;
					}
					
					
				}
				setAction();
				
			}else
			{
				action = 0;
		
				isCollidingWithButton();
				appearance = 0;
			
				if(isOpen)
				{
					action = 1;
					appearance = 1;
					animationRecord++;
					if(isCollidingWithProduct())
					{
						p.sex.setAttackStone(function);
					}
					if(isCollidingWithMonster())
					{
						m.sex.setAttackStone(function);
					}
					if(animationRecord >= 10)
					{
						animationRecord = 0;
						isOpen = false;
					}
					
					
				}
				setAction();
				
			}	
			
		
		
		
	}
	
	public void paint(Graphics g)
	{
		drawSubImage(g,x,y);
		if(!isTime)
		{
			g.setClip(tx,ty,button1[appearance][2],button1[appearance][3]);
		  g.drawImage(img,tx-button1[appearance][0],ty-button1[appearance][1],Graphics.TOP|Graphics.LEFT);
		  g.setClip(0,0,176,208);
		}
	}
	private void drawSubImage(Graphics g,int x,int y)
	{
		g.setClip(x,y,idSubImage[2],idSubImage[3]);
		g.drawImage(img,x-idSubImage[0],y-idSubImage[1],Graphics.TOP|Graphics.LEFT);
		g.setClip(0,0,176,208);
		
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
	
	public void isCollidingWithButton()
	{
		if((tx >= p.x && tx <= (p.x+p.cellW) && ty >= p.y && ty <= p.y+p.cellH) || (p.x >= tx && p.x <= (tx+tw) && p.y >= ty && p.y <= (ty+th) )  )
		{
			isOpen = true;
		 
		}
		
		
		
	}
	
	
	public boolean isCollidingWithProduct()
	{
		
		if((x >= p.x && x <= (p.x+p.cellW) && y >= p.y && y <= p.y+p.cellH) || (p.x >= x && p.x <= (x+width) && p.y >= y && p.y <= (y+height) ))return true;
		return false;
	}
	
	
	public boolean isCollidingWithMonster()
	{
		
		if((x >= m.x && x <= (m.x+m.cellW) && y >= m.y && y <= m.y+m.cellH) || (m.x >= x && m.x <= (x+width) && m.y >= y && m.y <= (y+height) ) || ( x >= m.x && x <= (m.x+m.cellW) && (y+height) >= m.y && (y+height) <= m.y+m.cellH) || ( (x+height) >= m.x && (x+height) <= (m.x+m.cellW) && y >= m.y && y <= m.y+m.cellH)  )return true;
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
	
	
	
	
	
	
}