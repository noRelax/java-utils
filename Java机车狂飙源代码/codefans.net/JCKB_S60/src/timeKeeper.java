// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi


public class timeKeeper
{

    int min;
    int sec;
    int ms;
    int finalTime;
    String minS;
    String secS;
    String msS;

    public timeKeeper(int i)
    {
        finalTime = i;
        setTime(finalTime);
    }

    public void setTime(int i)
    {
        int j = i;
        int k = i / 10;
        int l = k / 60;
        min = l;
        sec = Math.abs(l * 60 - k);
        ms = Math.abs(j - l * 60 * 10 - sec * 10) * 10;
        if(min > 0)
            minS = Integer.toString(min);
        else
            minS = "0" + Integer.toString(min);
        if(sec > 0)
            secS = Integer.toString(sec);
        else
            secS = "0" + Integer.toString(sec);
        if(ms > 0)
            msS = Integer.toString(ms);
        else
            msS = "0" + Integer.toString(ms);
    }
}
