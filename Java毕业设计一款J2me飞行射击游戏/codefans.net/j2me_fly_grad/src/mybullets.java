//玩家子弹类
//2005-5-4
//源码爱好者整理下载——http://www.codefans.net
//子弹因为飞机的不同而不同，比如霍克三，他的子弹射程为半屏，速度为3，而日96舰，则子弹射程只为2/3屏
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
public class mybullets extends Sprite
{   public  int score=0;//战果

	private int[][] bullets;//子弹数组
	//[i][0]子弹X坐标
	//[i][1]子弹Y坐标
	//[i][2]子弹Y方向速度
	//[i][3]子弹存活状态
	private int bulletstotal;//数组的长度，即一次可以打出多少炮弹，视飞机型号不同而不同
    public int width,bulletheight;//屏幕的高和宽
  	public int no=0;
	public mybullets(Image img,int picwidth,int picheight,int bulletstotal,int width,int height)
	{       
			super(img,picwidth,picheight);
			this.bulletstotal=bulletstotal;
			bullets=new int[bulletstotal][4];
			this.width=width;
			this.bulletheight=height/7;//初始华霍克3子弹射程			

	}
    public void initBullets(int i)//初始化子弹状态数组//???
    {
    		bullets[i][3]=1;
    		bullets[i][2]=0;
    		

    }
  
    public void updata(int i) //根据速度更新子弹下一桢的位置，出界消失
    {
    	bullets[i][1]+=bullets[i][2];
    }

    public void setfirstposition(int x,int y,int nof,Sprite sprite[],Image img)//第一科子弹的起始位置
    {   
    	
    	sprite[nof].setVisible(true);
        sprite[nof+1].setVisible(true);
        sprite[nof+2].setVisible(true);
    	//nof第F颗子弹，NOT第NOF+2颗子
    	bullets[nof][0]=x+10;//第一子弹X位置
        bullets[nof][1]=y-24;//第一子弹（最上面的）Y位置,24是飞机左上的点的坐标，所以应减多点，和后面子弹拉开距离
        sprite[nof].setImage(img,6,6);
        bullets[nof+1][1]=bullets[nof][1]+10;//第N+1子弹Y位置
        bullets[nof+1 ][0]=x+10;//第N+1子弹X位置
        sprite[nof+1].setImage(img,6,6);
        bullets[nof+2][1]=bullets[nof+1][1]+10;//第N+2子弹Y位置
        bullets[nof+2 ][0]=x+10;//第N+2子弹X位置
        sprite[nof+2].setImage(img,6,6);
    }

    public void newposition(Sprite sprite[],int i,int v,Sprite jp0,Sprite jp1,Sprite jp2,Sprite boss,Image img)
    {  	
    	bullets[i][2]-=5;//因为子弹是向上走的，所以是减
    	sprite[i].setPosition(bullets[i][0],bullets[i][1]+bullets[i][2]);
    	sprite[i+1].setPosition(bullets[i][0],bullets[i+1][1]+bullets[i][2]);
    	sprite[i+2].setPosition(bullets[i][0],bullets[i+2][1]+bullets[i][2]);
        if (sprite[i].collidesWith(jp0,true))//玩家子弹与敌人碰撞检测
        {
        	sprite[i].setImage(img,32,32);
        	sprite[i+1].setImage(img,32,32);
        	sprite[i+2].setImage(img,32,32);
        	sprite[i].setFrame(1);
        	sprite[i+1].setFrame(1);
        	sprite[i+2].setFrame(2);
        	bullets[i][3]=0;
        	bullets[i+1][3]=0;
        	bullets[i+2][3]=0;
        	jp0.setVisible(false);
        	no=1;
        	
           score=score+1;
        	//jp0.setPosition(0,2500);
        	////sprite[i].setPosition(0,-1500);
        	//sprite[i+1].setPosition(0,-1500);
        	//sprite[i+2].setPosition(0,-1500);
        }
        
        if (sprite[i].collidesWith(jp1,true))
        {
        	sprite[i].setImage(img,32,32);
        	sprite[i+1].setImage(img,32,32);
        	sprite[i+2].setImage(img,32,32);
        	sprite[i].setFrame(1);
        	sprite[i+1].setFrame(1);
        	sprite[i+2].setFrame(2);
        	bullets[i][3]=0;
        	bullets[i+1][3]=0;
        	bullets[i+2][3]=0;
        	jp1.setVisible(false);
        	no=1;
       score=score+1;
  
        	//jp1.setPosition(0,2500);
        	////sprite[i].setPosition(0,-1500);
        	//sprite[i+1].setPosition(0,-1500);
        	//sprite[i+2].setPosition(0,-1500);
        }
        
        if (sprite[i].collidesWith(jp2,true))
        {
        	sprite[i].setImage(img,32,32);
        	sprite[i+1].setImage(img,32,32);
        	sprite[i+2].setImage(img,32,32);
        	sprite[i].setFrame(1);
        	sprite[i+1].setFrame(1);
        	sprite[i+2].setFrame(2);
        	bullets[i][3]=0;
        	bullets[i+1][3]=0;
        	bullets[i+2][3]=0;
        	jp2.setVisible(false);
        	no=1;
        score=score+1;
    
        	//jp2.setPosition(0,2500);
        	////sprite[i].setPosition(0,-1500);
        	//sprite[i+1].setPosition(0,-1500);
        	//sprite[i+2].setPosition(0,-1500);
        }
        

    
    }
    public boolean isAlive(int i)
    {
    	if (bullets[i][3]==1) return true;
            else return false;
    }
    public void setAlive(int i)
    {
    	for (int z=i;z<i+3;z++)
    	{
    		bullets[i][3]=0;
    	}
    	
    }
    public int rscore()
    {
    	return score;
    }
     
    
 
public void clean(int i,Sprite sprite[])
{

	for (int z=i;z<i+3;z++)
	{
		//sprite[z].setPosition(0,-1500);
		sprite[z].setVisible(false);
		no=1;	
	}
		
}


}
