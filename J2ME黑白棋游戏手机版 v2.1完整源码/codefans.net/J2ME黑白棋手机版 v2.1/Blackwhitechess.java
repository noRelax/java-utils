import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Blackwhitechess extends MIDlet implements CommandListener
{
	//Command
	private Command exitCommand;
	private Command scoreCommand;
	
	private Command aboutCommand;
	
	private MyGameCanvas myGameCanvas;
	
	private int oldState;
	
	public Blackwhitechess() 
	{
		oldState = AllDef.READY;
		//Command
		exitCommand = new Command("退出",Command.EXIT,2);
		
		scoreCommand = new Command("分数", Command.SCREEN, 3);
		aboutCommand = new Command("关于", Command.SCREEN, 3);
		
		myGameCanvas = new MyGameCanvas();
	
		myGameCanvas.addCommand(exitCommand);		
		myGameCanvas.addCommand(scoreCommand);
		myGameCanvas.addCommand(aboutCommand);
		myGameCanvas.setCommandListener(this);
		
		Display.getDisplay(this).setCurrent(myGameCanvas);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException 
	{
		
	}

	protected void pauseApp() 
	{

	}

	protected void startApp() throws MIDletStateChangeException 
	{
		  
	}
	
	public void commandAction(Command c, Displayable d)
	{
		if(exitCommand == c)
		{
			try{
				destroyApp(false);
				notifyDestroyed();
			}catch(MIDletStateChangeException e){}
		}
		else if(scoreCommand == c)
		{
			myGameCanvas.m_game.m_state = AllDef.SCORE;
		}
		else if(myGameCanvas.runCommand == c)
		{
			myGameCanvas.m_game.m_state = oldState = AllDef.RUN;
		}
		else if(aboutCommand == c)
		{
			myGameCanvas.m_game.m_state = AllDef.ABOUT;
		}
		else if(myGameCanvas.backCommand == c)
		{
			myGameCanvas.m_game.m_state = oldState;
		}
		else if(myGameCanvas.fullCommand == c)
		{
			myGameCanvas.full = true;
		}
		else if(myGameCanvas.notFullCommand == c)
		{
			myGameCanvas.full = false;
		}
	}

}
