import javax.microedition.lcdui.*;

public class transImage 	//Àı∑≈Õº∆¨
{
	public static final Image scale(Image src, int w, int h)
	 {
	    int srcW = src.getWidth();
	    int srcH = src.getHeight();
	    int dstW=w,dstH=h;
	    Image tmp = Image.createImage(dstW, srcH);
	    Graphics g = tmp.getGraphics();
	    int scale=16;    
	  int delta = (srcW << scale) / dstW;//…®√Ë≥§∂»
	    int pos = delta / 2;//…®√ËŒª÷√
	    for (int x = 0; x < dstW; x++)
	    {
	      g.setClip(x, 0, 1, srcH);
	      g.drawImage(src, x - (pos >> scale), 0, Graphics.LEFT | Graphics.TOP);
	      pos += delta;
	    }
	    Image dst = Image.createImage( dstW, dstH);
	    g = dst.getGraphics();
	    delta = (srcH << scale) / dstH;
	    pos = delta / 2;
	    for (int y = 0; y < dstH; y++) 
	    {
	      g.setClip(0,y, dstW, 1);
	      g.drawImage(tmp, 0, y - (pos >> scale), Graphics.LEFT | Graphics.TOP);
	      pos += delta;
	    }
	    return dst;
	 } 

}
