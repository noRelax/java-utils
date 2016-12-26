package casper;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import java.io.IOException;
import map.*;
import game.Casper;

import java.io.InputStream;
import javax.microedition.media.*;
import javax.microedition.media.MediaException;


public class CasperCanvas extends Canvas implements Runnable  //主画布类，控制关卡，资源分配，
{
	private Casper casper;
	
	
	private int keyState;
	private int mapIndex = 0;//关卡
	private boolean gameStart = false;
	private boolean firstRun = true;//游戏首次运行
	private int coverIndex;//封面索引
	private boolean isPage = false;
	private int helpPageIndex = 0;//页面索引
	private int timeRecord = 0;//计时
	private boolean isRoleDied = false;
	private int id = 0;
	private int[][][] subImage ={ {{0,0,120,120},{120,0,120,120},{240,0,120,120},{360,0,120,120},{480,0,120,120}},
                                {{0,120,120,120},{120,120,120,120},{240,120,120,120},{360,120,120,120},{480,120,120,120}},
                                {{0,240,120,120},{120,240,120,120},{240,240,120,120},{360,240,120,120},{480,240,120,120}},
                                {{0,360,120,120},{120,360,120,120},{240,360,120,120},{360,360,120,120},{480,360,120,120}}
                              };   
	                            
	private int[][] actSubImage;
	private int[] idSubImage;
	
	private String str1,str2;
	
	
	private int isChangeView = 0;
	private	int a1 = 0;
	private int a2 = 0;
	private	int a3 = 0;
	private int a4 = 0;
	private	int a5 = 0;
	private int a6 = 0;
	
	
	
	
	private Image imgButton;
	private Image imgCover;
	private Image imgPause;
	
	
	private boolean isPause = false;
	private int pauseIndex = 0;
	
	
	private MapManage mapManage;
	private Image imgMap;
	private int[][] mapSubImage = {{0,0,0,0},
		                             {0,0,20,20},{20,0,20,20},{40,0,20,20},{60,0,20,20},{80,0,20,20},{100,0,20,20},
		                             {0,20,20,20},{20,20,20,20},{40,20,20,20},{60,20,20,20},{80,20,20,20},{100,20,20,20},
		                             {0,40,20,20},{20,40,20,20},{40,40,20,20},{60,40,20,20},{80,40,20,20},{100,40,20,20},
		                             {0,60,20,20},{20,60,20,20},{40,60,20,20},{60,60,20,20},{80,60,20,20},{100,60,20,20},
		                             {0,80,20,20},{20,80,20,20},{40,80,20,20},{60,80,20,20},{80,80,20,20},{100,80,20,20},
		                             {0,100,20,20},{20,100,20,20},{40,100,20,20},{60,100,20,20},{80,100,20,20},{100,100,20,20}
		
		                              };
	public int[][] mapData1 =  {
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 1, 2, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 
2, 2, 2, 2 },
{ 0, 0, 0, 0, 7, 8, 8, 9, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 10, 11, 11, 11, 11, 12, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 7, 8, 9, 0, 0, 0, 0, 4, 5, 5, 
5, 5, 5, 5 },
{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 23, 23, 23, 2, 16, 15, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 
18, 2, 23, 23, 23, 23, 23, 23, 23, 23, 2, 2, 2, 3, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 
3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 3, 0, 0, 4, 5, 5, 
5, 5, 5, 5 },
{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 19, 19, 19, 5, 5, 14, 
16, 15, 0, 0, 0, 0, 7, 9, 0, 0, 0, 0, 0, 13, 18, 17, 
5, 5, 19, 19, 19, 19, 19, 19, 19, 19, 5, 5, 5, 6, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 5, 5, 
6, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0, 1, 2, 3, 0, 0, 
0, 0, 0, 0, 10, 11, 11, 11, 11, 11, 12, 0, 0, 4, 5, 5, 
5, 5, 5, 5 },
{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 19, 19, 19, 5, 5, 5, 
5, 14, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 17, 5, 5, 
5, 5, 19, 19, 19, 19, 19, 19, 19, 19, 5, 5, 5, 6, 0, 0, 
0, 0, 0, 0, 0, 7, 8, 9, 0, 0, 0, 0, 4, 5, 5, 5, 
6, 0, 0, 4, 6, 0, 0, 0, 0, 0, 0, 4, 5, 6, 0, 0, 
1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 5, 
5, 5, 5, 5 },
{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 19, 19, 19, 5, 5, 5, 
5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 
5, 5, 19, 19, 19, 19, 19, 19, 19, 19, 5, 5, 5, 5, 2, 2, 
2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5, 5, 5, 5, 
6, 0, 0, 4, 6, 0, 1, 2, 3, 0, 0, 4, 5, 6, 0, 0, 
4, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 5, 
5, 5, 5, 5 },
{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 19, 19, 19, 19, 19, 19, 
19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 
19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 5, 5, 5, 5, 5, 5, 
5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 
6, 0, 0, 4, 6, 0, 4, 5, 6, 0, 0, 4, 5, 6, 0, 0, 
4, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 5, 
5, 5, 5, 5 }
};
	
	public int[][] mapData2 = {
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 24, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 20, 20, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 20, 20, 20, 20, 20, 20, 20, 20, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
33, 0, 0, 0, 0, 0, 0, 0, 33, 33, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
25, 0, 0, 0, 0, 0, 0, 0, 25, 25, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 }
};
	
	public int[][] mapData3 = {
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 27, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 23, 23, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 23, 23, 23, 23, 23, 23, 23, 23, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 34, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
34, 0, 0, 0, 0, 0, 0, 0, 34, 34, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 },
{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 26, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
26, 0, 0, 0, 0, 0, 0, 0, 26, 26, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
0, 0, 0, 0 }
};
		                              
	
	
	private Product p1;
	private Image imgProduct;
	private int[][][] productSubImage = {
	                                    {{32,0,16,16},{32,0,16,16},{32,0,16,16},{32,0,16,16},{32,0,16,16}},
	                                    {{16,0,16,16},{16,0,16,16},{16,0,16,16},{16,0,16,16},{16,0,16,16}},
		                                  {{0,0,16,16},{0,0,16,16},{0,0,16,16},{0,0,16,16},{0,0,16,16}},
		                                  {{32,0,16,16},{32,16,16,16},{32,0,16,16},{32,16,16,16},{32,0,16,16}},
		                                  {{16,0,16,16},{16,16,16,16},{16,0,16,16},{16,16,16,16},{16,0,16,16}},
		                                  {{0,0,16,16},{0,16,16,16},{0,0,16,16},{0,16,16,16},{0,0,16,16}},
	                                    {{32,0,16,16},{32,16,16,16},{32,0,16,16},{32,16,16,16},{32,0,16,16}},
		                                  {{16,0,16,16},{16,16,16,16},{16,0,16,16},{16,16,16,16},{16,0,16,16}},
		                                  {{0,32,16,16},{16,32,16,16},{0,32,16,16},{16,32,16,16},{0,32,16,16}},
		                                  {{32,0,16,16},{32,16,16,16},{32,0,16,16},{32,16,16,16},{32,0,16,16}},
		                                  {{16,0,16,16},{16,16,16,16},{16,0,16,16},{16,16,16,16},{16,0,16,16}},
	                                    {{0,48,16,16},{16,48,16,16},{0,48,16,16},{16,48,16,16},{0,48,16,16}},
		                                  {{320,0,16,16},{320,0,16,16},{320,0,16,16},{320,0,16,16},{320,0,16,16}},
		                                  {{320,16,16,16},{320,16,16,16},{320,16,16,16},{320,16,16,16},{320,16,16,16}},
		                                  {{320,32,16,16},{320,32,16,16},{320,32,16,16},{320,32,16,16},{320,32,16,16}},
		                                  {{320,0,16,16},{320,0,16,16},{320,0,16,16},{320,0,16,16},{320,0,16,16}},
		                                  {{320,16,16,16},{320,16,16,16},{320,16,16,16},{320,16,16,16},{320,16,16,16}},
		                                  {{320,32,16,16},{320,32,16,16},{320,32,16,16},{320,32,16,16},{320,32,16,16}},
		                                  {{48,0,32,32},{80,0,32,32},{112,0,32,32},{144,0,32,32},{112,0,32,32}},
		                                  {{48,0,32,32},{176,0,32,32},{208,0,32,32},{240,0,32,32},{272,0,32,32}},
		                                  {{144,32,32,32},{176,32,32,32},{208,32,32,32},{240,32,32,32},{272,32,32,32}},
		                                  {{32,32,32,32},{64,32,32,32},{32,32,32,32},{64,32,32,32},{96,32,32,32}},
		                                  {{0,64,16,16},{16,64,16,16},{0,64,16,16},{16,64,16,16},{0,64,16,16}}
		                                   
	                                     };
	                                     
	private int[][][] bulletSubImage = { {{304,32,16,16}},
		                                   {{304,16,16,16}},
		                                   {{304,0,16,16}}
		                                    }; 
  private int[][] effectSubImage = {
  	                                {0,20,20,20},{20,20,20,20}
  	                               };                                    
	
	
	private int[][][] bullet2SubImage = { {{90,0,16,16}},
		                                   {{90,0,16,16}},
		                                   {{90,0,16,16}}
		                                    };                
	                                     
	                                     
	                                     
	private Image imgBG;
	private LoadData  loadData;
	private LoadData1 loadData1;
	
	
	private Image imgP2;//for monster
	
	
	private AI ai;
	private boolean isBoss = false;
	private Monster m1,m2;
	private int[][][] monsterSubImage ={ 
	                                   {{0,0,30,30},{0,0,30,30}},
	                                   {{0,60,30,30},{30,60,30,30}},
	                                   {{0,30,30,30},{30,30,30,30}},
	                                   {{0,120,30,30},{30,120,30,30}},
	                                   {{0,90,30,30},{30,90,30,30}},
	                                   {{30,0,30,30},{60,0,30,30}},
	                                   
	                                  
	                                  };
		 
		 
		 
		 
	private Tool tool1,tool2;
	private int[][] toolSubImage = {
		                              {0,0,16,16},
		                              {16,0,16,16}
		                              };
  
	private Image imgTool;
	
	private Trap trap1,trap2;
	
	private Image imgTrap;
	private int[][][] trapSubImage = {
		                                {{0,0,20,80},{0,0,20,20},{0,0,20,80},{0,0,20,20},{0,0,20,80}},
		                                {{40,0,20,80},{60,0,20,80},{80,0,20,80},{100,0,20,80},{120,0,20,80}},
		                                {{0,0,20,80},{20,0,20,80},{0,0,20,80},{20,0,20,80},{0,0,20,80}} 		
		                               };
	private int[][] button1SubImage = {
		                                 {0,80,20,20},
		                                 {20,80,20,20}
	                                  };
	
	
	
	
	private Box box1,box2,box3,box4;
	private int[][][] boxSubImage = {
		                              {{40,0,20,20}},
		                              {{40,20,20,20}}
		                             }; 
	
	private Player musicPlayer;
	
	
	public CasperCanvas(Casper casper)
	{
		this.casper = casper;
		
		try
		{
			imgMap = Image.createImage("/background.png");
			imgProduct = Image.createImage("/ball.png");
	  }catch(IOException e)
	  {
	  	e.printStackTrace();
	  	}
		try
		{
			imgBG = Image.createImage("/bg.png");
			imgP2 = Image.createImage("/monster.png");
	  }catch(IOException e)
	  {
	  	e.printStackTrace();
	  	}
	  	try
		{
			imgCover = Image.createImage("/cover.png");
			imgTool = Image.createImage("/tool.png");
	  }catch(IOException e)
	  {
	  	e.printStackTrace();
	  	}
	  		try
		{
			imgButton = Image.createImage("/button.png");
			imgTrap = Image.createImage("/trap.png");
	  }catch(IOException e)
	  {
	  	e.printStackTrace();
	  	}
	  	try
		{
			imgPause = Image.createImage("/pause.png");
	  }catch(IOException e)
	  {
	  	e.printStackTrace();
	  	}
		loadData = new LoadData();
		loadData1 = new LoadData1();
		
		actSubImage = subImage[coverIndex];
		idSubImage = actSubImage[id];


		
		
		mapManage = new MapManage(imgMap,mapData1,mapData2,mapData3,100,10,20,20,mapSubImage);
		mapManage.load();
    
    
    
    p1 = new Product(imgProduct,16,16,productSubImage,1,mapData1,20,20,2,bulletSubImage,6,6,imgTool,effectSubImage);
    
    m1 = new Monster(imgP2,30,30,monsterSubImage,2,bullet2SubImage,6,6,mapData1,20,20);
    m2 = new Monster(imgP2,30,30,monsterSubImage,2,bullet2SubImage,6,6,mapData1,20,20);
    ai = new AI();
		p1.setPos(20,50);

		
		m1.setPos(500,50);
		m1.setProduct(p1);
		
		m2.setPos(1520,50);
		m2.setProduct(p1);
		
		tool1 = new Tool(imgTool,toolSubImage,440,160,16,16,0,p1);
		tool2 = new Tool(imgTool,toolSubImage,1880,20,16,16,1,p1);
		
		
		trap1 = new Trap(imgTrap,trapSubImage,1400,40,20,80,true,button1SubImage);
		trap1.setProduct(p1);
		trap1.setMonster(m1);
		trap2 = new Trap(imgTrap,trapSubImage,1420,40,20,80,true,button1SubImage);
		trap2.setProduct(p1);
		trap2.setMonster(m1);
		
		
		box1 = new Box(imgTool,boxSubImage,420,110,20,20,mapData1,20,20);
	  box1.setProduct(p1);
	  box2 = new Box(imgTool,boxSubImage,420,80,20,20,mapData1,20,20);
	  box2.setProduct(p1);
	  box3 = new Box(imgTool,boxSubImage,420,60,20,20,mapData1,20,20);
	  box3.setProduct(p1);
	  box4 = new Box(imgTool,boxSubImage,420,40,20,20,mapData1,20,20);
	  box4.setProduct(p1);
	  
	  box1.setBox(box2,box3,box4);
	  box2.setBox(box1,box3,box4);
	  box3.setBox(box1,box2,box4);
	  box4.setBox(box1,box2,box3);
	  
	  box1.isShow = false;
	  box2.isShow = false;
	  box3.isShow = false;
	  box4.isShow = false;
		
		Thread t = new Thread(this);
		t.start();
		
	}
	public void run()
	{
		while(true)
		{
			if(!gameStart)
			{
				if(firstRun)
				{
				   timeRecord++;
					if((keyState&1)!= 0 || (keyState&2)!= 0 ||(keyState&4)!= 0 || (keyState&8)!= 0 ||(keyState&16)!= 0 || (keyState&32)!= 0 ||(keyState&64)!= 0 || (keyState&128)!= 0)
					{
					  
					  firstRun = false;
					  timeRecord = 0;
					}
					
				}else
				{
					
					if(!isPage)
					{
					 if((keyState&4)!= 0)
					 {
						
						coverIndex--;
						if(coverIndex < 0)coverIndex = 3;
					 }
					if((keyState&8)!=0)
					{
						
						coverIndex++;
						if(coverIndex > 3)coverIndex = 0;
					}
					
					if((keyState&128)!=0)
					{
						if(coverIndex == 0)
						{
						 gameStart = true;
						 mapIndex = 0;
						 isPause = false;
						  try{
				      InputStream is = getClass().getResourceAsStream("/mm.mid");
             musicPlayer = Manager.createPlayer(is,"audio/midi");
             musicPlayer.prefetch();
             musicPlayer.setLoopCount(-1);
						 	musicPlayer.start();}catch (IOException ioe) {
            }catch(MediaException e1){}
						}
						if(coverIndex == 1)
						{
						 isPage = true;
						 helpPageIndex = 1;
						}
						if(coverIndex == 3)
						{
						  casper.setExit();
						}
					}
					
					setAction();
				 }else
				 {
				 	if((keyState&1)!= 0 || (keyState&2)!= 0 ||(keyState&4)!= 0 || (keyState&8)!= 0 ||(keyState&16)!= 0 || (keyState&32)!= 0 ||(keyState&64)!= 0 || (keyState&128)!= 0)
					{
					  isPage = false;
					  helpPageIndex = 0;
					}
				 	
				 	
				 }
				}
				
				
				repaint();
				 try{
				 Thread.sleep(100);
		   }catch(Exception e){e.printStackTrace();}
				
			}
      if(gameStart)
      {
      	if(!isPause)
      	{
      	if(isChangeView != 2 && isChangeView != 3)
       {
      	
       if(!isBoss)
       {
         setAI(ai.setAction(m1.x,m1.y,p1.x,p1.y),m1);
         setAI(ai.setAction(m2.x,m2.y,p1.x,p1.y),m2);
       }else{
       	  setBossAI(ai.setBossAction(m1.x,m1.y,p1.x,p1.y),m1);
       	}
       
       
			 events();
       setAim(p1,m1,m2);
       m1.events();
			 m2.events();
			 tool1.isCollidingWithProduct();
			 tool2.isCollidingWithProduct();
			 setTollGate();
			 mapManage.setAction();
			 
			 trap1.events();
			 trap2.events();
			 
			 box1.events();
			 box2.events();
			 box3.events();
			 box4.events();
			 isCollidingWithBox();
			 
			 isGameOver();
			 
			}
			 }else{
			 	
			 	timeRecord++;
			 	if(timeRecord >= 1)
			 	{
			 		timeRecord = 0;
			 	 if((keyState&1)!=0)
			 	 {
			 		 pauseIndex--;
			 		 if(pauseIndex < 0)pauseIndex = 1;
			 	 }
			 		 if((keyState&2)!=0)
			 	 {
			 		 pauseIndex++;
			 		 if(pauseIndex > 1)pauseIndex = 0;
			 	 }
			 		 if((keyState&128)!=0)
			 	 {
			 		 if(pauseIndex == 0)
			 		 {
			 			 isPause = false;
			 		 }
			 		 if(pauseIndex == 1)
			 		 {
			 			 gameStart = false;
			 			 musicPlayer.close();
			 		 }
			 		
			 	 }
			 	}
			 	
			}
			 
			 
			 repaint();
			 try{
				 Thread.sleep(100);
		   }catch(Exception e){e.printStackTrace();}
			}
	 }

	}
	
	
	private void isCollidingWithBox()
	{
		p1.cDown = (box1.isCollidingUP() || box2.isCollidingUP() ||box3.isCollidingUP() ||box4.isCollidingUP() );
		p1.cRight = (box1.isCollidingLEFT() || box2.isCollidingLEFT() ||box3.isCollidingLEFT() ||box4.isCollidingLEFT() );
		p1.cLeft = (box1.isCollidingRIGHT() || box2.isCollidingRIGHT() ||box3.isCollidingRIGHT() ||box4.isCollidingRIGHT() );
		
		
	}
	
	
	
	
	
	private void events()
	{
		if((keyState&1)!=0)
		{
			p1.setPower(0,-6);
			
	  }
			if((keyState&2)!=0)
		{
			p1.setPower(0,2);
			
	  }
			if((keyState&4)!=0)
		{
			if(p1.x > 0)
			{
			 
			 p1.setPower(-2,0);
			}
			
	  }
			if((keyState&8)!=0)
		{
			p1.setPower(2,0);
			
	  }
    if((keyState&128)!=0)
		{
			p1.setFire();
	  }
	  if((keyState&32)!=0)
		{
			p1.setChange(0);
	  }
	  if((keyState&16)!=0)
		{
			p1.setChange(1);
	  }
	  if((keyState&64)!=0)
		{
			p1.setChange(2);
	  }
    if((keyState&256)!=0)
    {
    	isPause = true;
    }

		
		
	}
	private void eventsForCover()//封面键盘按键处理
	{
		
		
	}
	
	private void setAI(int ai,Monster m)
	{
		switch(ai)
		{
			case 0:
			      m.action = 0;
			      break;
			case 1:
			      m.setMove(3);
			      break;
			case 2:
			      m.setMove(2);
			      break;
			case 3:
			      m.attackDir = 3;
			      m.action =  3;
			      m.setFire();
			      break;
			case 4:
			      m.attackDir = 2;
			      m.action = 4;
			      m.setFire();
			      break;
			case 5:
			      
			      break;
			
		}
		
	}
	
	private void setBossAI(int ai,Monster m)
	{
		switch(ai)
		{
			case 0:
			      m.setMove(0);
			      break;
			case 1:
			      m.setMove(3);
			      break;
			case 2:
			      m.setMove(2);
			      break;
			case 3:
			      m.setMove(3);
			      m.attackDir = 3;
			      m.action =  3;
			      m.setFire();
			      break;
			case 4:
			      m.setMove(2);
			      m.attackDir = 2;
			      m.action = 4;
			      m.setFire();
			      break;
			case 5:
			      
			      break;
			
		}
		
		
		
	}
	
	
	
	protected void paint(Graphics g)
	{
		if(!gameStart)
		{
			if(firstRun)
			{
				g.drawImage(imgCover,0,0,Graphics.TOP|Graphics.LEFT);
				 if(timeRecord > 5)
				 {
					g.setColor(255,255,255);
					g.drawString("<任意键开始>",60,10,Graphics.TOP|Graphics.LEFT);
					if(timeRecord >= 10)timeRecord = 0;
				 }
				
				
			}else
			{
				g.setColor(0,0,0);
				g.fillRect(0,0,176,220);
				drawSubImage(g,30,30);
				
				if(isPage)
				{
					g.setColor(0,0,0);
				 g.fillRect(0,0,176,220);
				 g.setColor(255,255,255);
				 if(helpPageIndex == 1)
				 {
				  g.drawString("游戏通过2、4、6、8键",10,10,Graphics.TOP|Graphics.LEFT);
				  g.drawString("来表示跳跃、左、右、下",10,30,Graphics.TOP|Graphics.LEFT);
				  g.drawString("方向，通过1、3、0键",10,50,Graphics.TOP|Graphics.LEFT);
				  g.drawString(" 自由切换属性铁、木、水 ",10,70,Graphics.TOP|Graphics.LEFT);
				  g.drawString("通过5键来表示攻击",10,90,Graphics.TOP|Graphics.LEFT);
				  g.drawString("#号键为菜单.",10,110,Graphics.TOP|Graphics.LEFT);
				  g.drawString("策划：黄静",10,130,Graphics.TOP|Graphics.LEFT);
				  g.drawString("美术：彭举",10,150,Graphics.TOP|Graphics.LEFT);
				  g.drawString("程序：王泉",10,170,Graphics.TOP|Graphics.LEFT);
				  
				 }
				}
				
				
			}
			
		}
		if(gameStart)
		{
			
			int ox = 60-p1.x;
			if(ox > 0) ox = 0;
			//if(ox > 1824)ox = 1824;
			//if(oy < 0)oy = 0;
			int oy = 0;
			if(mapIndex == 9)
			{
			 oy = 100-p1.y;
			 if(oy > 0)oy = 0;
			}
			
			if(mapIndex == 5 && ox < -560)ox = -560;
			if(mapIndex == 9)ox = 0;
			
			
		 g.setColor(255,255,255);
		 g.fillRect(0,0,300,300);
		 g.drawImage(imgBG,0,0,Graphics.TOP|Graphics.LEFT);
		 g.setClip(0,0,20,20);
		 g.drawImage(imgProduct,-304,-48,Graphics.TOP|Graphics.LEFT);
		 g.setClip(0,0,176,208);
		 p1.sex.paint(g);
		 if(p1.belong == 0)
		 {
		  g.setClip(75,0,20,20);
		  g.drawImage(imgPause,75,0,Graphics.TOP|Graphics.LEFT);
		  g.setClip(0,0,176,208);
		 }
		 if(p1.belong == 1)
		 {
		  g.setClip(75,0,20,20);
		  g.drawImage(imgPause,35,0,Graphics.TOP|Graphics.LEFT);
		  g.setClip(0,0,176,208);
		 }
		  if(p1.belong == 2)
		 {
		  g.setClip(75,0,20,20);
		  g.drawImage(imgPause,55,0,Graphics.TOP|Graphics.LEFT);
		  g.setClip(0,0,176,208);
		 }
		 
		 if(isBoss)m1.sex.paint(g);
		 //g.translate(60-p1.x,100-p1.y);
		 g.translate(ox,oy);
		 mapManage.paint(g);
		 p1.paint(g);
		 tool1.paint(g);
		 tool2.paint(g);
		 m1.paint(g);
		 m2.paint(g);
		 
		 trap1.paint(g);
		 trap2.paint(g);
		 
		 box1.paint(g);
		 box2.paint(g);
		 box3.paint(g);
		 box4.paint(g);
		 
		 if(isRoleDied)
		 {
		 	g.setColor(255,0,0);
		 	g.drawString("GameOver!",p1.x,100,Graphics.TOP|Graphics.LEFT);
		 }
		 
		 
		 
		 setAnimation(g);
		 
		 if(isChangeView == 2 || isChangeView == 3)
		 {
		 	 g.setColor(255,255,255);
		 	 g.drawString(str1,50,20,Graphics.TOP|Graphics.LEFT);
		 	 g.drawString(str2,60,60,Graphics.TOP|Graphics.LEFT);
		 }
		 
		 if(isPause)
		 {
		 	g.setClip(p1.x,0,60,45);
		 	g.drawImage(imgPause,p1.x,-20,Graphics.TOP|Graphics.LEFT );
		 	if(pauseIndex == 0)
		 	{
		 		g.setClip(p1.x,0,60,25);
		 		g.drawImage(imgPause,p1.x-60,-20,Graphics.TOP|Graphics.LEFT );
		 	}
		 	if(pauseIndex == 1) 
		 	{
		 		g.setClip(p1.x,25,60,20);
		 		g.drawImage(imgPause,p1.x-60,-20,Graphics.TOP|Graphics.LEFT );
		 	}
		 	g.setClip(0,0,176,208);
		 }
		 
		 
		 
		}
	}
	
	private void setAim(Product p,Monster m1,Monster m2)
	{
		int distance1 = Math.abs(p.x - m1.x);
		int distance2 = Math.abs(p.x - m2.x); 
		if(distance1 < distance2)
		{
			p.events(m1);
		}else
		{
			p.events(m2);
		}
		
	}
	
	private void setAnimation(Graphics g)//转场动画
	{
		if(isChangeView == 1)
		{
			 a1 = 0;
			 a2 = 0;
			 a3 = 0;
			 a4 = 0;
			 a5 = 0;
			 a6 = 0;
			
			isChangeView = 2;
			
		}
		if(isChangeView == 2)
		{
			g.setColor(0,0,0);
			if(a1 < 40){a1+=4;}
			g.fillRect(0,0,176,a1);
			if(a1 >= 10 && a2 < 40 ){a2+=4;}
			g.fillRect(0,40,176,a2);
			if(a2 >= 10 && a3 < 40 ){a3+=4;}
			g.fillRect(0,80,176,a3);
			if(a3 >= 10 && a4 < 40 ){a4+=4;}
			g.fillRect(0,120,176,a4);
			if(a4 >= 10 && a5 < 40 ){a5+=4;}
			g.fillRect(0,160,176,a5);
			if(a5 >= 10 && a6 < 40 ){a6+=4;} 
			g.fillRect(0,200,176,a6);
			
			if(a6 >= 40)isChangeView = 3;
			
    }
    if(isChangeView == 3)
    {
    	g.setColor(0,0,0);
			if(a6 > 0){a6-=4;}g.fillRect(0,200,176,a6);
			if(a6 <= 30 && a5 > 0 ){a5-=4;}g.fillRect(0,160,176,a5);
			if(a5 <= 30 && a4 > 0 ){a4-=4;}g.fillRect(0,120,176,a4);
			if(a4 <= 30 && a3 > 0 ){a3-=4;}g.fillRect(0,80,176,a3);
			if(a3 <= 30 && a2 > 0 ){a2-=4;}g.fillRect(0,40,176,a2);
			if(a2 <= 30 && a1 > 0 ){a1-=4;}g.fillRect(0,0,176,a1);
    	
    	
    	if(a1 <= 0)isChangeView = 4;
    	
    	
    }
		
		
		
	}
	
	
	
	private void setTollGate()
	{
		if(mapIndex == 0)
		{
			
			mapIndex = 1;
			isChangeView = 1;
			
			try
		{ 
			imgMap = null;
			imgBG = null;
			imgMap = Image.createImage("/background.png");
			imgBG = Image.createImage("/bg.png");
	  }catch(IOException e)
	  {
	  	e.printStackTrace();
	  	}
			
			
			isBoss = false;
			
			str1 = null;
			str2 = null;
			str1 = new String("关卡 1-1");
      str2 = new String("雪山");
			
				
			p1.setPos(10,40);
			p1.sex.roleHp = 50;
			p1.isDied = false;
			m1.sex.roleHp = 50;
			m1.isDied = false;
			m1.setPos(500,50);
			m1.setBelong(2);
			m1.sex.isBoss = false;
			m1.cellW = 30;
			m1.cellH = 30;
			m1.subImage = monsterSubImage;
			m1.bullet.subImage = bullet2SubImage;
			m1.bullet.setSize(6,6);
			m1.bullet.setAR(80);
			ai.setAI(200,50);
			
			m2.sex.roleHp = 50;
			m2.isDied = false;
			m2.setPos(1520,50);

		
	
    

    tool1.setPos(440,160);
    tool1.setShow(0);
    tool2.setPos(1880,20);
    tool2.setShow(0);
  
    mapData1 =loadData.setMapData1();
    mapData2 =loadData.setMapData2();
    mapData3 =loadData.setMapData3();
    
    p1.mapData = mapData1;
   m1.mapData = mapData1;
   m2.mapData = mapData1;
  
   mapManage = new MapManage(imgMap,mapData1,mapData2,mapData3,100,10,20,20,mapSubImage);
	 mapManage.load();
 
  

    trap1.setPos(1400,40); 
    trap1.setIsTime(true);
    trap2.setPos(1420,40);
    trap2.setIsTime(true);
    
    
    
    
    
			
			
			
			
		}
		else if(mapIndex == 1)
		{
			if(p1.x >= 1940)mapIndex = 2;
			
		}
		else if(mapIndex == 2)
		{
			str1 = null;
			str2 = null;
			str1 = new String("关卡 1-2");
      str2 = new String("雪山");
			
			
			isChangeView = 1;
			
			
			p1.setPos(10,40);
			m1.sex.roleHp = 50;
			m1.isDied = false;
			m1.setPos(320,50);
			m2.sex.roleHp = 50;
			m2.isDied = false;
			m2.setPos(1620,20);
			mapIndex = 3;
			


  tool1.setPos(600,20);
  tool1.setShow(0);
  tool2.setPos(1860,100);
  tool2.setShow(0);
  
  mapData1 = loadData.setMapData01();
  mapData2 = loadData.setMapData02();
  mapData3 = loadData.setMapData03();
  
  p1.mapData = mapData1;
  m1.mapData = mapData1;
  m2.mapData = mapData1;
  
  mapManage = new MapManage(imgMap,mapData1,mapData2,mapData3,100,10,20,20,mapSubImage);
	mapManage.load();
  trap1.setPos(1000,40); 
  trap2.setPos(1360,0);
			
			
		}
		else if(mapIndex == 3)
		{
			if(p1.x >= 1940)mapIndex = 4;
		}
		else if(mapIndex == 4)
		{
			str1 = null;
			str2 = null;
			str1 = new String("关卡 1-3");
      str2 = new String("雪山");
			
			
			isChangeView = 1;
			isBoss = true;
			m1.sex.isBoss = true;
			m1.setBelong(1);
			
			int[][][] bulletBossSubImage = { {{120,0,30,30},{120,30,30,30}},
		                                   {{120,0,30,30},{120,30,30,30}},
		                                   {{120,0,30,30},{120,30,30,30}}
		                                    };        
			
			int[][][] bossSubImage ={ 
	                                   {{0,150,40,40},{0,150,40,40}},
	                                   {{0,330,40,40},{60,330,40,40}},
	                                   {{0,270,40,40},{60,270,40,40}},
	                                   {{0,210,40,40},{60,210,40,40}},
	                                   {{60,30,40,40},{60,90,40,40}},
	                                   {{0,150,30,30},{60,150,30,30}},
	                                   
	                                  
	                                  };
			m1.subImage = bossSubImage;
			m1.bullet.subImage = bulletBossSubImage;
			m1.bullet.setSize(30,30);
			m1.cellW = 40;
			m1.cellH = 40;
			
			p1.setPos(10,40);
			m1.sex.roleHp = 1000;
			m1.isDied = false;
			m1.bullet.setAR(120);
			m1.setPos(500,50);
			ai.setAI(200,150);
			mapIndex = 5;
		



  tool1.setPos(340,120);
  tool1.setShow(0);

  
  
  
  mapData1 = loadData.setMapData11();
  mapData2 = loadData.setMapData12();
  mapData3 = loadData.setMapData13();
  
  p1.mapData = mapData1;
  m1.mapData = mapData1;
  
  mapManage = new MapManage(imgMap,mapData1,mapData2,mapData3,100,10,20,20,mapSubImage);
	mapManage.load();
	
	trap1.setPos(560,80);
	trap1.setIsTime(false);
	trap1.setButton(680,80,20,20); 
  trap2.setPos(580,80);
  trap2.setIsTime(false);
	trap2.setButton(680,80,20,20); 
			
		}
		else if(mapIndex == 5)
		{
			if(m1.sex.roleHp <= 0)mapIndex = 6;
			
		}
		else if(mapIndex == 6)
		{
			
			mapIndex = 7;
			isChangeView = 1;
			
			
			str1 = null;
			str2 = null;
			str1 = new String("关卡 2-1");
      str2 = new String("火山");
			
			isBoss = false;
				
			p1.setPos(10,40);
			p1.sex.roleHp = 50;
			p1.isDied = false;
			m1.sex.roleHp = 50;
			m1.isDied = false;
			m1.setPos(240,60);
			m1.setBelong(2);
			m1.sex.isBoss = false;
			m1.cellW = 30;
			m1.cellH = 30;
			m1.subImage = monsterSubImage;
			m1.bullet.subImage = bullet2SubImage;
			m1.bullet.setSize(6,6);
			m1.bullet.setAR(80);
			ai.setAI(200,50);
			
			m2.sex.roleHp = 50;
			m2.isDied = false;
			m2.setPos(1320,60);
			
			
			
		  try
		{ 
			imgMap = null;
			imgBG = null;
			imgMap = Image.createImage("/background1.png");
			imgBG = Image.createImage("/bg1.png");
			
	  }catch(IOException e)
	  {
	  	e.printStackTrace();
	  	}
	  	
		  try
		{ 
			imgTrap = null;
			imgP2 = null;
			imgTrap = Image.createImage("/trap1.png");
			imgP2 = Image.createImage("/monster1.png");
			
	  }catch(IOException e)
	  {
	  	e.printStackTrace();
	  	}
	  
	  m1.img = imgP2;
	  m2.img = imgP2;
    

    tool1.setPos(1400,140);
    tool1.setShow(0);
    
    box1.setIsShow();
    box2.setIsShow();
    box3.setIsShow();
    box4.setIsShow();
    
    box1.setPos(1495,100);
    box2.setPos(1495,80);
    box3.setPos(1495,60);
    box4.setPos(1495,40);
    
  
    mapData1 =loadData1.setMapData1();
    mapData2 =loadData1.setMapData2();
    mapData3 =loadData1.setMapData3();
    
    p1.mapData = mapData1;
    m1.mapData = mapData1;
    m2.mapData = mapData1;
    
    box1.mapData = mapData1;
    box2.mapData = mapData1;
    box3.mapData = mapData1;
    box4.mapData = mapData1;
    
    mapManage = new MapManage(imgMap,mapData1,mapData2,mapData3,100,10,20,20,mapSubImage);
	  mapManage.load();
 
    int[][][] trap1SubImage = {
		                                {{0,0,20,40},{0,38,20,2},{0,0,20,40},{0,38,20,2},{0,0,20,40}},
		                                {{0,0,20,40},{20,0,20,40},{40,0,20,40},{60,0,20,40},{80,0,20,40}},
		                                {{0,0,20,40},{20,0,20,40},{0,0,20,40},{20,0,20,40},{0,0,20,40}} 		
		                               };
 
 
  
    trap1.img = imgTrap;
    trap1.setPos(1000,80); 
    trap1.setIsTime(true);
    trap1.subImage = trap1SubImage;
    trap1.width = 20;
    trap1.height = 40;
    trap2.img = imgTrap;
    trap2.setPos(1700,100);
    trap2.setIsTime(true);
    trap2.subImage = trap1SubImage;
    trap2.width = 20;
    trap2.height = 40;
			
		}else if(mapIndex == 7)
		{
			
			if(p1.x >= 1980)mapIndex = 8;
			
			
		}else if(mapIndex == 8)
		{
			str1 = null;
			str2 = null;
			str1 = new String("关卡 2-2");
      str2 = new String("火山");
			
			
			isChangeView = 1;
			
			
			p1.setPos(30,1960);
			m1.sex.roleHp = 50;
			m1.isDied = false;
			m1.setPos(120,1340);
			m2.sex.roleHp = 50;
			m2.isDied = false;
			m2.setPos(40,780);
			mapIndex = 9;
			
			 try
		{ 
			imgMap = null;
			imgBG = null;
			imgMap = Image.createImage("/background1.png");
			imgBG = Image.createImage("/bg1.png");
	  }catch(IOException e)
	  {
	  	e.printStackTrace();
	  	}
			


  tool1.setPos(60,660);
  tool1.setShow(0);
  tool2.setPos(20,1020);
  tool2.setShow(0);
  
  mapData1 = loadData1.setMapData01();
  mapData2 = loadData1.setMapData02();
  mapData3 = loadData1.setMapData03();
  
  p1.mapData = mapData1;
  m1.mapData = mapData1;
  m2.mapData = mapData1;
  
  mapManage = new MapManage(imgMap,mapData1,mapData2,mapData3,9,100,20,20,mapSubImage);
	mapManage.load();
  trap1.setPos(120,1720); 
  trap2.setPos(100,540);
  
		}else if(mapIndex == 9)
		{
			
			if(p1.x <= 20 && p1.y <= 40)mapIndex = 10;
		}else if(mapIndex == 10)
		{
			str1 = null;
			str2 = null;
			str1 = new String("关卡 2-3");
      str2 = new String("火山");
			
			
			isChangeView = 1;

			try
		{ 
			imgMap = null;
			imgBG = null;
			imgMap = Image.createImage("/background1.png");
			imgBG = Image.createImage("/bg1.png");
	  }catch(IOException e)
	  {
	  	e.printStackTrace();
	  	}

			
			p1.setPos(10,40);
			m1.sex.roleHp = 50;
			m1.isDied = false;
			m1.setPos(500,40);
			m2.sex.roleHp = 50;
			m2.isDied = false;
			m2.setPos(1260,40);
			mapIndex = 11;
		




  
  
  
  mapData1 = loadData1.setMapData11();
  mapData2 = loadData1.setMapData12();
  mapData3 = loadData1.setMapData13();
  
  p1.mapData = mapData1;
  m1.mapData = mapData1;
  m2.mapData = mapData1;
  
  mapManage = new MapManage(imgMap,mapData1,mapData2,mapData3,100,10,20,20,mapSubImage);
	mapManage.load();
	
	 trap1.setPos(360,60);
   trap2.setPos(940,60);

			
		}else if(mapIndex == 11)
		{
			
		}
		
		
		
		
		
		
		
	
	}
	
	private void isGameOver()
	{
		if(mapIndex != 9)
		{
			if( p1.y >= 220 )p1.sex.roleHp -= 50;
		  
		}
		
		if(p1.sex.roleHp <= 0 )
		 {
		 	isRoleDied=true;
		 	musicPlayer.close();
		 }
		if(isRoleDied)
		{
		 timeRecord++;
		 if(timeRecord >= 20)
		 {
		 	gameStart = false;
		 	isRoleDied = false;
		 	timeRecord = 0;
		 }
		}
		
	}
	
	
	
	protected void keyPressed(int keyCode)
	{
		int k = this.getGameAction(keyCode);
		switch(k)
		{
			case UP:
			      keyState |= 1;
			      break;
			case DOWN:
			      keyState |= 2;
			      break;
			case LEFT:
			      keyState |= 4;
			      break;
			case RIGHT:
			      keyState |= 8;
			      break;
		    case FIRE:
		        keyState |= 128;
		        break;
			
		}
		switch(keyCode)
		{
			case KEY_NUM1:
			      keyState |= 16;
			      break;
			case KEY_NUM0:
			      keyState |= 32;
			      break;
		  case KEY_NUM3:
		        keyState |= 64;
		        break;
			case KEY_POUND:
			      keyState |= 256;
			      break;
		}
		
	}
	protected void keyReleased(int keyCode)
	{
		int k = this.getGameAction(keyCode);
		switch(k)
		{
			case UP:
			      keyState &=~1;
			      break;
			case DOWN:
			      keyState &=~2;
			      break;
			case LEFT:
			      keyState &=~4;
			      break;
			case RIGHT:
			      keyState &=~8;
			      break;
		  case FIRE:
		        keyState &=~128;
		        break;      
			
		}
		
		switch(keyCode)
		{
			case KEY_NUM1:
			      keyState &=~16;
			      break;
			case KEY_NUM0:
			      keyState &=~32;
			      break;
		  case KEY_NUM3:
		        keyState &=~64;
		        break;
		  case KEY_POUND:
			      keyState &=~ 256;
			      break;
			
		}
		
	}
	
	
	private void drawSubImage(Graphics g,int x,int y)
	{
		g.setClip(x,y,idSubImage[2],idSubImage[3]);
		g.drawImage(imgButton,x-idSubImage[0],y-idSubImage[1],Graphics.TOP|Graphics.LEFT);
		g.setClip(0,0,176,208);
	}
	
	private void setAction()
	{
		
		if(coverIndex < subImage.length)
		{
		switch(coverIndex)
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
		       
		       
		  }
	  }
  	
		
	}
	
	
	
	
}