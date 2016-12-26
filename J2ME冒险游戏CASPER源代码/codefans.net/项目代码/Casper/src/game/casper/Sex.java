package casper;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Sex  //属性计算类 计算各种属性伤害
{
	public int roleHp = 50;
	public int roleAT = 10;//技能值
	public int roleNum;
	public int roleSex;//0 为木，1 为铁，2 为水
	public boolean isBoss = false;
	public Sex()
	{
		
	}
	public void setRoleSex(int roleSex)
	{
		this.roleSex = roleSex;
		
	}
	
	public void setRoleHp(int function)
	{
		switch(function)
		{
			case 0:
			       roleHp += 25;
			       if(roleHp > 50)roleHp = 50;
			       break;
			case 1:
			      roleAT += 10;
			      if(roleAT > 50)roleAT = 50;
			      break;
			case 2:
			      roleHp -= 40;
			      break;
		}
		
		
	}
	public void setHp(int hp)//设置血量
	{
		
		roleHp = hp;
	}
	
	public void setAttackStone(int function) // 0 木 1 铁 2 水  3火的伤害
	{
		switch(function)
		{
			case 0:
			       if(roleSex == 0){roleHp -= 5;}
			       if(roleSex == 1){roleHp -= 2;}
			       if(roleSex == 2){roleHp -= 10;}
			       break;
			case 1:
			       if(roleSex == 0){roleHp -= 10;}
			       if(roleSex == 1){roleHp -= 5;}
			       if(roleSex == 2){roleHp -= 10;}
			       break;
			case 2:
			       if(roleSex == 0){roleHp -= 5;}
			       if(roleSex == 1){roleHp -= 2;}
			       if(roleSex == 2){roleHp -= 5;}
			       break;
			case 3:
			       if(roleSex == 0){roleHp -= 50;}
			       if(roleSex == 1){roleHp -= 5;}
			       if(roleSex == 2){roleHp -= 0;}
			       break;       
			       
			       
			
		}
		
	}
	
	public void setAttackStoneForMap(int function) // 0 木 1 铁 2 水  地图块伤害计算
	{
		switch(function)
		{
			case 0:
			       if(roleSex == 0){roleHp -= 2;}
			       if(roleSex == 1){roleHp -= 1;}
			       if(roleSex == 2){roleHp -= 3;}
			       break;
			case 1:
			       if(roleSex == 0){roleHp -= 3;}
			       if(roleSex == 1){roleHp -= 1;}
			       if(roleSex == 2){roleHp -= 3;}
			       break;
			case 2:
			       if(roleSex == 0){roleHp -= 2;}
			       if(roleSex == 1){roleHp -= 0;}
			       if(roleSex == 2){roleHp -= 2;}
			       break;
			case 3:
			       if(roleSex == 0){roleHp -= 50;}
			       if(roleSex == 1){roleHp -= 5;}
			       if(roleSex == 2){roleHp -= 0;}
			       break;             
			       
			       
			
		}
		
	}
	
	
	public boolean isDie()
	{
		if(roleHp <= 0)return true;
		return false;
		
	}
	
	public void paint(Graphics g)
	{
		if(!isBoss)
		{
		  g.setColor(255,0,0);
		  g.fillRect(20,0,roleHp,3);
		  g.setColor(0,255,255);
		  g.drawRect(20,0,50,4);
		  g.setColor(255,255,0);
		  g.fillRect(20,4,roleAT,3);
		  g.setColor(255,255,255);
		  g.drawRect(20,4,50,4);
		}else{
			
			g.setColor(255,0,0);
		  g.fillRect(20,40,roleHp/20,3);
		  g.setColor(0,255,255);
		  g.drawRect(20,40,50,4);
		  g.drawString("Boss",0,40,Graphics.TOP|Graphics.LEFT);
			
			
		}
		
	}
	
	
}