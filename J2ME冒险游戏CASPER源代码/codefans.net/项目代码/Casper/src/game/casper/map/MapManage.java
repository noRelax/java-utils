package map;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


public class MapManage //地图的管理分为3层~~~~1层碰撞层 2层效果层
{
	private Image img;
	public int[][] mapData1;
	public int[][] mapData2;
	public int[][] mapData3;
	private int[][] subImage;
	
	public int row,col;
	public int cellW,cellH;
	
	private DrawMap drawMap1,drawMap2;
	
	private int id = 0;
	
	
	
	public MapManage(Image img,int[][] mapData1,int[][] mapData2,int[][] mapData3,int row,int col,int cellW,int cellH,int[][] subImage)
	{
		this.img = img;
		this.mapData1 = mapData1;
		this.mapData2 = mapData2;
		this.mapData3 = mapData3;
		this.row = row;
		this.col = col;
		this.cellW = cellW;
		this.cellH = cellH;
		this.subImage = subImage;
		
	}
	
	public void load()
	{
		drawMap1 = new DrawMap(img,mapData1,subImage);
		drawMap1.setSize(row,col);
		drawMap1.setCellSize(cellW,cellH);
		drawMap1.initLayer();
		
		drawMap2 = new DrawMap(img,mapData2,subImage);
		drawMap2.setSize(row,col);
		drawMap2.setCellSize(cellW,cellH);
		drawMap2.initLayer();
		
		
	}
	public void setAction()
	{
		id++;
		if(id == 5)
		{
			drawMap2.setMapData(mapData3);
			
		}
		if(id == 10)
		{
			drawMap2.setMapData(mapData2);
			id=0;
		}
	}
	
	public void paint(Graphics g)
	{
		drawMap1.paint(g);
		drawMap2.paint(g);
		
	}
	
	
}