// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi

import java.util.Timer;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public class ZASM extends MIDlet
    implements CommandListener
{

    public Display display;
    public ZapperCanvas canvas;
    private Timer timer;
    private ZapperTimer task;

    public ZASM()
    {
        display = Display.getDisplay(this);
    }

    protected void startApp()
    {
        CreateGameCanvas();
        display.setCurrent(canvas);
    }

    public void pauseApp()
    {
    }

    public void CreateGameCanvas()
    {
        canvas = new ZapperCanvas(this);
        timer = new Timer();
        task = new ZapperTimer(canvas);
        timer.schedule(task, 0L, 200L);
    }

    protected void destroyApp(boolean flag)
    {
        System.gc();
        display.setCurrent(null);
    }

    public void exitMIDlet()
    {
        destroyApp(true);
        notifyDestroyed();
    }

    public void commandAction(Command command, Displayable displayable)
    {
        if(command.getCommandType() == 7)
            destroyApp(true);
    }
}
