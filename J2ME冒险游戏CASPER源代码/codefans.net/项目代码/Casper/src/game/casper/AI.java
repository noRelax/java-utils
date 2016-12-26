package casper;

import java.util.Random;
import java.lang.Math;

public class AI //人工智能 分普通和BOSS2个级别
{
	private int x,y;
	private int ax,ay;
	private Random rand;
	
	private int timeRecord = 0;//计时
	private int timeRun = 0;//计步器
	
	private int attackDistance;
	private int moveDistance;
	
	public AI()
	{
		attackDistance = 50;
		moveDistance = 200;
	}
	
	
	public void setAI(int moveDistance,int attackDistance)
	{
		this.moveDistance = moveDistance;
		this.attackDistance = attackDistance;
	}
	
	
	
	
	public int setAction(int x,int y,int ax,int ay)
	{
		timeRecord++;
		if(timeRecord == 3)
		{
			timeRecord = 0;
		 int distance = x -ax;
		 if(distance > moveDistance || distance < -moveDistance)
		 {
			 return 0;
		 }else{
			if(distance >attackDistance || distance < -attackDistance)
			{
				timeRun++;
				if(timeRun < 10)
				{
					return 1;
				}
				if(timeRun >=10 && timeRun < 20)
				{
					return 2;
				}
				if(timeRun > 19)
				{
					timeRun = 0;
				}
				
			}else{
				//int rp = Math.abs(rand.nextInt())%101;
				if(distance >0)
				{
					return 3;
				}
				 if(distance <0)
				{
					return 4;
				}
				if(distance == 0)
				{
					return 0;
				}
				
				
			}
			
			
		 }
		}
		return 5;
		
	}
	
	public int setBossAction(int x,int y,int ax,int ay)
	{
	
		
			int distance = x - ax;
			if(distance > attackDistance)
			{
				return 1;
				
			}
			if(distance < -attackDistance )
			{
				return 2;
			}

			 if(distance <= attackDistance && distance > 0)
			 {
				 return 3;
			 }
			 if(distance >= -attackDistance && distance < 0 )
			 {
				 return 4;
			 }
			if(Math.abs(distance) < 5 && Math.abs(y-ay) >= 20 )
			{
				return 0;
			}
			
			
			
		
		
		return 5;
		
		
	}
	
	
	
	
}