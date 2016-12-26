import java.awt.*;
import javax.swing.*;
import java.util.*;
// Finishing Codefans.net
public class StillClock extends JPanel
{
	private int hour;
	private int minute;
	private int second;
	
	public StillClock()
	{
		setCurrentTime();
	}
	
	public StillClock(int hour,int minute,int second)
	{
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public int getHour()
	{
		return minute;
	}
	
	public int getMinute()
	{
		return minute;
	}
	public void setMinute(int minute)
	{
		this.minute = minute;
		repaint();
	}
	
	public int getSecond()
	{
		return second;
	}
	
	public void setSecond(int second)
	{
		this.second = second;
		repaint();
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);//initialize parameter
		int clockRadius = (int)(Math.min(getWidth(),getHeight())*0.8*0.5);
		int xCenter = getWidth()/2;
		int yCenter = getHeight()/2;
		
		g.setColor(Color.black);//draw circle
		g.drawOval(xCenter-clockRadius,yCenter-clockRadius,2*clockRadius,2*clockRadius);
		g.drawString("12",xCenter-5,yCenter-clockRadius+12);
		g.drawString("9",xCenter-clockRadius+3,yCenter+5);
		g.drawString("3",xCenter+clockRadius-10,yCenter+3);
		g.drawString("6",xCenter-3,yCenter+clockRadius-3);
		
		int sLenght = (int)(clockRadius*0.8);//draw second hand
		int xSecond = (int)(xCenter+sLenght*Math.sin(second*(2*Math.PI/60)));
		int ySecond = (int)(yCenter-sLenght*Math.cos(second*(2*Math.PI/60)));
		g.setColor(Color.red);
		g.drawLine(xCenter,yCenter,xSecond,ySecond);
		
		int mLenght = (int)(clockRadius*0.8);//draw minute hand 
		int xMinute = (int)(xCenter+mLenght*Math.sin(minute*(2*Math.PI/60)));
		int yMinute = (int)(yCenter-mLenght*Math.cos(minute*(2*Math.PI/60)));
		g.setColor(Color.blue);
		g.drawLine(xCenter,yCenter,xMinute,yMinute);
		
		int hLenght = (int)(clockRadius*0.5);//draw hour hand
		int xHour = (int)(xCenter+hLenght*Math.sin((hour%12+minute/60.0)*(2*Math.PI/12)));
		int yHour = (int)(yCenter-hLenght*Math.cos((hour%12+minute/60.0)*(2*Math.PI/12)));
		g.setColor(Color.green);
		g.drawLine(xCenter,yCenter,xHour,yHour);
		
	}	
		public void setCurrentTime()
		{
			Calendar c = new GregorianCalendar();
			
			this.hour = c.get(Calendar.HOUR_OF_DAY);
			this.minute = c.get(Calendar.MINUTE);
			this.second = c.get(Calendar.SECOND);
		}
		
		public Dimension getPreferedsize()
		{
			return new Dimension(200,200);
		}
	}
