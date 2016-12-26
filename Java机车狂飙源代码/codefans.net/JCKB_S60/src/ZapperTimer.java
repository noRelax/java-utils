// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi

import java.util.Random;
import java.util.TimerTask;
import javax.microedition.lcdui.Canvas;

class ZapperTimer extends TimerTask
{

    private ZapperCanvas canvas;
    private Random random;
    int counter;
    static int cnt = 0;

    public ZapperTimer(ZapperCanvas zappercanvas)
    {
        counter = 0;
        canvas = zappercanvas;
    }

    public final void run()
    {
        counter++;
        canvas.repaint();
        switch(canvas.page)
        {
        case 1: // '\001'
        default:
            break;

        case 6: // '\006'
            if(canvas.gameStart)
            {
                canvas.time.finalTime -= 2;
                canvas.time.setTime(canvas.time.finalTime);
                if(canvas.time.finalTime <= 0)
                    canvas.timeOut = true;
                else
                if(canvas.fuel <= 0)
                    canvas.outoffuel = true;
            }
            break;
        }
    }

}
