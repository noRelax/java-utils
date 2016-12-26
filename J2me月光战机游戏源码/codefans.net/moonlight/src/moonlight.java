// download by http://www.codefans.net
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class moonlight extends MIDlet
{
    static Display display;
    private MainCanvas mc;
    
    public moonlight()
    {
        display = Display.getDisplay(this);
        mc = new MainCanvas(display, this);
    }

    protected void pauseApp()
    {
    }

    protected void startApp()
        throws MIDletStateChangeException
    {
        Display.getDisplay(this).setCurrent(mc);
    }

    protected void destroyApp(boolean flag)
        throws MIDletStateChangeException
    {
    }

    
}