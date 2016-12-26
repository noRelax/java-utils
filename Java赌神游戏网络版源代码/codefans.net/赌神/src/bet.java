// *******************************************************
//
// FILENAME:    	bet.java
// PROJECT:		BetSprite
// DESCRIPTION:		赌神网络版
// Download by http://www.codefans.net
// *******************************************************
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class bet extends MIDlet implements CommandListener
{
        private Display display;
        private betmain Canvaschess;
          private Command exitCommand = new Command("退出", Command.EXIT,1);
          private Command newCommand = new Command("新游戏", Command.OK,2 );
          private Command peaceCommand =new Command("和局", Command.HELP, 3);
          private Command failCommand = new Command("认输", Command.BACK, 4);
          public void commandAction(Command c, Displayable s)
          {
                    if (c == exitCommand)
                    {	exit();		}
                    if(c==newCommand)
                    {	startApp();	}
                    if(c==peaceCommand)
                    {	startApp();	}
                    if(c==failCommand)
                    {	startApp();	}
          }
          public bet()
        {
                display=Display.getDisplay(this);
                Canvaschess=new betmain(this);
        }
        protected void startApp()
        {
                display.setCurrent(Canvaschess);
                try{
                              Canvaschess.addCommand(exitCommand);
                              Canvaschess.addCommand(newCommand);
                              Canvaschess.addCommand(peaceCommand);
                              Canvaschess.addCommand(failCommand);
                              Canvaschess.setCommandListener(this);
                    }
                    catch(Exception e) {	e.printStackTrace();	}
                Canvaschess.start();
        }
        protected void pauseApp()
        {	}
            protected void destroyApp(boolean unconditional)
             {     	}
        public void exit()
        {
                destroyApp(true);
                notifyDestroyed();
        }
}