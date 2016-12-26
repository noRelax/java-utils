package map;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class DrawMap //Ìù×©µÄ»æÖÆ
{
	private int[][] mapData;  
	private int[][] subImage;
	private int row,col;
	private int cellW,cellH;
	private Image img;
	private Image buffer;
	private Graphics gr;
	
	
	public DrawMap(Image img,int[][] mapData,int[][] subImage)
	{
		this.img = img;
		this.mapData = mapData;
		this.subImage = subImage;
		
		
	}
	public void setMapData(int[][] mapData)
	{
		this.mapData = mapData;
	}
	public void setCellSize(int cellW,int cellH)
	{
		this.cellW = cellW;
		this.cellH = cellH;
	}
	public void setSize(int row,int col)
	{
		this.row = row;
		this.col = col;
	}
	public void initLayer()
	{
		buffer = Image.createImage(col*cellW,row*cellH);
		gr = buffer.getGraphics();
	}
	
	public void paint(Graphics g)
	{
		for(int i = 0;i < mapData.length;i++)
		{
			for(int j = 0;j < mapData[0].length;j++)
			{
				if(mapData[i][j] > 0)
				{
					drawSubImage(g,j*cellW,i*cellH,subImage[mapData[i][j]]);
				}
				
			}
			
		}
		
	}
	private void drawSubImage(Graphics g,int x,int y,int[] subImage)
	{
		g.setClip(x,y,subImage[2],subImage[3]);
		g.drawImage(img,x-subImage[0],y-subImage[1],Graphics.TOP|Graphics.LEFT);
		g.setClip(0,0,buffer.getWidth(),buffer.getHeight());
			
	}
	
	
	
}