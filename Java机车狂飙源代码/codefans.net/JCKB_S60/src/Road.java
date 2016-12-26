// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi

import java.io.PrintStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Road
{

    Image road;
    Image bg;
    Image bumper;
    //Image start;
    //Image finish;
    Image startline;
    Image fuel;
    int roadX;
    int quadrant;
    int mainX;
    int bumperX;
    int finishX;
    int finalDistance;
    int map[];
    int fuelLane[];
    int fuelX[];
    int timeCtr;
    boolean showFuel[];
    int tempFuelX;

    public Road()
    {
        quadrant = 0;
        mainX = 0;
        tempFuelX = 1;
        timeCtr = 2000;
        try
        {
            road = Image.createImage("/road.png");
            bg = Image.createImage("/bg.png");
            bumper = Image.createImage("/bumper.png");
           // finish = Image.createImage("/finish.png");
            //start = Image.createImage("/start.png");
            startline = Image.createImage("/startline.png");
            fuel = Image.createImage("/fuel.png");
        }
        catch(Exception exception)
        {
            System.out.println("”Œœ∑Õº∆¨≤ª¥Ê‘⁄£°");
        }
    }

    public void setRoad(int i)
    {
        if(i == 1)
        {
            showFuel = new boolean[36];
            map = new int[36];
            map[0] = 1;
            map[1] = 1;
            map[2] = 2;
            map[3] = 3;
            map[4] = 2;
            map[5] = 3;
            map[6] = 2;
            map[7] = 1;
            map[8] = 3;
            map[9] = 2;
            map[10] = 1;
            map[11] = 3;
            map[12] = 1;
            map[13] = 3;
            map[14] = 3;
            map[14] = 2;
            map[15] = 1;
            map[16] = 1;
            map[17] = 3;
            map[18] = 2;
            map[19] = 1;
            map[20] = 2;
            map[21] = 1;
            map[22] = 3;
            map[23] = 3;
            map[24] = 2;
            map[25] = 1;
            map[26] = 3;
            map[27] = 1;
            map[28] = 3;
            map[29] = 1;
            map[30] = 2;
            map[31] = 3;
            map[32] = 1;
            map[33] = 2;
            map[34] = 1;
            map[35] = 1;
        } else
        if(i == 2)
        {
            showFuel = new boolean[46];
            map = new int[46];
            map[0] = 1;
            map[1] = 1;
            map[2] = 2;
            map[3] = 3;
            map[4] = 2;
            map[5] = 3;
            map[6] = 2;
            map[7] = 1;
            map[8] = 3;
            map[9] = 2;
            map[10] = 1;
            map[11] = 3;
            map[12] = 1;
            map[13] = 3;
            map[14] = 3;
            map[14] = 2;
            map[15] = 1;
            map[16] = 1;
            map[17] = 3;
            map[18] = 2;
            map[19] = 1;
            map[20] = 2;
            map[21] = 3;
            map[22] = 3;
            map[23] = 3;
            map[24] = 2;
            map[25] = 1;
            map[26] = 1;
            map[27] = 1;
            map[28] = 3;
            map[29] = 1;
            map[30] = 2;
            map[31] = 3;
            map[32] = 1;
            map[33] = 1;
            map[34] = 2;
            map[35] = 3;
            map[36] = 2;
            map[37] = 1;
            map[38] = 2;
            map[39] = 1;
            map[40] = 2;
            map[41] = 3;
            map[42] = 2;
            map[43] = 1;
            map[44] = 1;
            map[45] = 1;
        } else
        if(i == 3)
        {
            showFuel = new boolean[56];
            map = new int[56];
            map[0] = 1;
            map[1] = 3;
            map[2] = 1;
            map[3] = 3;
            map[4] = 2;
            map[5] = 3;
            map[6] = 2;
            map[7] = 1;
            map[8] = 3;
            map[9] = 1;
            map[10] = 2;
            map[11] = 3;
            map[12] = 2;
            map[13] = 3;
            map[14] = 3;
            map[14] = 2;
            map[15] = 2;
            map[16] = 1;
            map[17] = 3;
            map[18] = 2;
            map[19] = 1;
            map[20] = 2;
            map[21] = 2;
            map[22] = 3;
            map[23] = 3;
            map[24] = 2;
            map[25] = 2;
            map[26] = 1;
            map[27] = 1;
            map[28] = 3;
            map[29] = 1;
            map[30] = 2;
            map[31] = 3;
            map[32] = 3;
            map[33] = 3;
            map[34] = 2;
            map[35] = 3;
            map[36] = 1;
            map[37] = 3;
            map[38] = 2;
            map[39] = 2;
            map[40] = 3;
            map[41] = 2;
            map[42] = 1;
            map[43] = 1;
            map[44] = 2;
            map[45] = 3;
            map[46] = 1;
            map[47] = 3;
            map[48] = 2;
            map[49] = 2;
            map[50] = 3;
            map[50] = 3;
            map[51] = 3;
            map[52] = 3;
            map[53] = 1;
            map[54] = 1;
            map[55] = 2;
        }
        fuelX = new int[map.length];
        fuelLane = new int[map.length];
        for(int j = 0; j < map.length; j++)
            if(map[j] == 3)
            {
                if(tempFuelX == 4)
                    fuelLane[j] = 3;
                else
                if(tempFuelX == 1)
                    fuelLane[j] = 3;
                else
                if(tempFuelX == 2)
                    fuelLane[j] = 4;
                else
                if(tempFuelX == 3)
                    fuelLane[j] = 1;
                if(tempFuelX == 4)
                    tempFuelX = 3;
                else
                if(tempFuelX == 1)
                    tempFuelX = 4;
                else
                if(tempFuelX == 2)
                    tempFuelX = 1;
                else
                if(tempFuelX == 3)
                    tempFuelX = 2;
                showFuel[j] = true;
            }

        finalDistance = roadX + (map.length - 3) * road.getWidth();
    }

    public void moveRoad(int i)
    {
        mainX -= i;
    }

    public void drawRoad(Graphics g, int i)
    {
        for(int j = 0; j < map.length; j++)
        {
            g.drawImage(bg, mainX + roadX + j * road.getWidth(), 0, 0x4 | 0x10);
            g.drawImage(road, mainX + roadX + j * road.getWidth(), bg.getHeight(), 0x4 | 0x10);
            if(map[j] == 2)
            {
                bumperX = roadX + j * road.getWidth();
                g.drawImage(bumper, mainX + bumperX, bg.getHeight() + 15, 0x4 | 0x10);
            } else
            if(map[j] == 3)
            {
                fuelX[j] = roadX + j * road.getWidth() + road.getWidth() / 2;
                if(showFuel[j])
                    g.drawImage(fuel, mainX + fuelX[j], 75 + fuelLane[j] * 20, 0x4 | 0x10);
            }
            if(j == 0)
            {
                g.drawImage(startline, mainX + 65, bg.getHeight() + 9, 0x4 | 0x10);
                //g.drawImage(start, mainX + 100, bg.getHeight() + 10, 0x4 | 0x10);
            } else
            if(j == map.length - 2)
            {
                finishX = mainX + roadX + j * road.getWidth();
                g.drawImage(startline, mainX + roadX + j * road.getWidth(), bg.getHeight() + 9, 0x4 | 0x10);
               // g.drawImage(finish, finishX, bg.getHeight() + 10, 0x4 | 0x10);
            }
        }

    }
}
