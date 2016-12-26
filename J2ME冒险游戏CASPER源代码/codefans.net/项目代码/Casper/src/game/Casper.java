package game;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import casper.CasperCanvas;


public class Casper extends MIDlet implements CommandListener
{
	
	private Display display;
	private CasperCanvas casperCanvas;
	
	public Casper()
	{
		
		display = Display.getDisplay(this);
		casperCanvas = new CasperCanvas(this);
		casperCanvas.setCommandListener(this);
		
		
	}
	
	protected void startApp()
	{
		display.setCurrent(casperCanvas);
	}
	protected void pauseApp()
	{
		
	}
	protected void destroyApp(boolean args0)
	{
		
	}
	public void setExit()
	{
		  destroyApp(false);
			notifyDestroyed();
	}
	
	public void commandAction(Command c,Displayable d)
	{
		
	
	}
	
	
	
}

