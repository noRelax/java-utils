// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi

import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.lcdui.*;

public class Car
{

    int speed;
    int lane;
    int maxSpeed;
    int carID;
    boolean userControlled;
    Image userCar[];
    //Image tyre;
    int m;//新加的
    Image compCar[];
    int carPos;
    int carX;
    int carY;
    boolean showBump;
    int bumpCtr;
    int springCtr;
    int quadrant;
    int laneCtr;
    int oldlane;
    int newlane;
    boolean accel;
    boolean changeLane;
    boolean lapComplete;
    int fuel;
    int distance;
    int lastLane;
    int finalX;
    int mapCarx;
    ZapperCanvas zap;
    Enumeration enum1;
    int changeX;
    int roadX;
    int finalDistance;
    int timeCtr;
    int speedCtr;
    boolean bumped;

    public Car(int i, int j, int k, int l, boolean flag, ZapperCanvas zappercanvas)
    {
        speed = 0;
        showBump = false;
        quadrant = 0;
        accel = true;
        changeLane = false;
        lapComplete = false;
        fuel = 100;
        mapCarx = 0;
        timeCtr = 0;
        bumped = false;
        maxSpeed = k;
        lane = j;
        oldlane = j;
        carID = i;
        changeX = i * 150;
        userControlled = flag;
        carPos = l;
        zap = zappercanvas;
        enum1 = zappercanvas.Cars.keys();
        finalDistance = zappercanvas.road.finalDistance;
        finalX = zappercanvas.road.finishX;
        userCar = new Image[6];
        carY = 55 + j * 20;
        fuel = 100;
        if(i == 0)
        {
            try
            {
                userCar[0] = Image.createImage("/car0.png");
                userCar[1] = Image.createImage("/car1.png");
                userCar[2] = Image.createImage("/car2.png");
                userCar[3] = Image.createImage("/car3.png");
                userCar[4] = Image.createImage("/car4.png");
                userCar[5] = Image.createImage("/car5.png");
                //tyre = Image.createImage("/tyre1.png");
            }
            catch(Exception exception)
            {
                System.out.println("没有发现图片 car");
            }
            accel = false;
        } else
        if(i == 1 || i == 3)
            try
            {
                userCar[0] = Image.createImage("/car1/car0.png");
                userCar[1] = Image.createImage("/car1/car1.png");
                userCar[2] = Image.createImage("/car1/car2.png");
                userCar[3] = Image.createImage("/car1/car3.png");
                userCar[4] = Image.createImage("/car1/car4.png");
                userCar[5] = Image.createImage("/car1/car5.png");
                //tyre = Image.createImage("/tyre1.png");
            }
            catch(Exception exception1)
            {
                System.out.println("没有发现图片 car1");
            }
        else
        if(i == 2)
            try
            {
                userCar[0] = Image.createImage("/car2/car0.png");
                userCar[1] = Image.createImage("/car2/car1.png");
                userCar[2] = Image.createImage("/car2/car2.png");
                userCar[3] = Image.createImage("/car2/car3.png");
                userCar[4] = Image.createImage("/car2/car4.png");
                userCar[5] = Image.createImage("/car2/car5.png");
                //tyre = Image.createImage("/tyre1.png");
            }
            catch(Exception exception2)
            {
                System.out.println("没有发现图片2");
            }
    }

    public void drawCar(Graphics g, int i)
    {
        roadX = i;
        if(!showBump && !changeLane)
        {
            if(carPos == 1)
            {
                springCtr++;
                if(springCtr % 2 == 0)
                {
                    for(int m = 0; m < 2; m++)//这里是循环图片的
            {
                   // g.drawImage(userCar[1], carX + i, carY, 0x4 | 0x10);
                   g.drawImage(userCar[m], carX + i, carY, 0x4 | 0x10);
                  }
                    g.setColor(103, 87, 15);
                    //g.fillRoundRect(carX + 1 + i, ((carY + userCar[1].getHeight()) - 4) + tyre.getHeight(), 64, 3, 8, 8);
                } else
                {
                    g.drawImage(userCar[1], carX + i, carY + 1, 0x4 | 0x10);
                    g.setColor(103, 87, 15);
                    //g.fillRoundRect(carX + 1 + i, ((carY + userCar[1].getHeight()) - 4) + tyre.getHeight(), 64, 3, 8, 8);
                }
                //g.drawImage(tyre, carX + 9 + i, (carY + userCar[1].getHeight()) - 4, 0x4 | 0x10);
                carX += speed;
            }
        } else
        if(showBump)
            showBumperMovement(g);
        else
        if(changeLane)
            changeLane(oldlane, newlane, g);
        if(carID == 0)
        {
            timeCtr++;
            g.setFont(Font.getFont(0, 0, 8));
            g.setColor(255, 255, 0);
            if(speed < 10)
                g.drawString("时速 : 0" + speed * 10 + " 公里", 5, 5, 0x4 | 0x10);
            else
                g.drawString("时速 : " + speed * 10 + " 公里", 5, 5, 0x4 | 0x10);
        }else
        if(carID == 1)
            g.setColor(255, 255, 255);
        else
        if(carID == 2)
            g.setColor(228, 116, 48);
        else
        if(carID == 3)
            g.setColor(255, 255, 255);
        mapCarx = distance / ((distance + zap.road.finishX) / 86);
        if(carID == 0 && timeCtr % 2 == 0)
            g.fillRect(4 + mapCarx, 183 + (lane - 1) * 5, 4, 4);//显示摩托车的哪个小进度条 g.fillRect(4 + mapCarx, 183 + (lane - 1) * 5, 4, 4);
        else
        if(carID != 0)
            g.fillRect(4 + mapCarx, 183 + (lane - 1) * 5, 4, 4);//g.fillRect(4 + mapCarx, 183 + (lane - 1) * 5
    }

    public void moveCar()
    {
        if(userControlled)
            maxSpeed = fuel / 4;
        if(!lapComplete)
        {
            if(accel && speed < maxSpeed && !bumped && carID != 0)
            {
                speed++;
                distance = distance + speed;
            } else
            if(accel && speed < maxSpeed && bumped && carID != 0)
            {
                if(speedCtr > 2)
                {
                    speed++;
                    distance = distance + speed;
                    speedCtr = 0;
                }
            } else
            if(accel && speed < maxSpeed && carID == 0)
            {
                speed++;
                distance = distance + speed;
            }
            for(enum1 = zap.Cars.keys(); enum1.hasMoreElements();)
            {
                String s = (String)enum1.nextElement();
                Car car = (Car)zap.Cars.get(s);
                if(car.carID != carID && car.lane == lane && car.carX + zap.road.mainX > carX && car.carX + zap.road.mainX < carX + 65)
                {
                    if(car.lane > 1)
                    {
                        car.oldlane = car.lane;
                        car.newlane = car.lane - 1;
                        car.roadX = zap.road.mainX;
                        car.changeLane = true;
                    }
                    if(lane < 4)
                    {
                        lane = oldlane;
                        newlane = lane + 1;
                        changeLane = true;
                    }
                    speed = 0;
                }
            }

            if(carX > changeX && !userControlled)
            {
                changeX += changeX;
                changeLane = true;
                if(lane == 4)
                    newlane = lane - 1;
                else
                    newlane = lane + 1;
            }
        } else
        {
            zap.Cars.remove(Integer.toString(carID));
            speed = 0;
            System.out.println(carID + " 清除");
        }
    }

    public void showBumperMovement(Graphics g)
    {
        bumpCtr++;
        if(bumpCtr <= 3)
        {
            g.setColor(103, 87, 15);
            //g.fillRoundRect(carX + roadX, ((carY + userCar[1].getHeight()) - 4) + tyre.getHeight(), 55, 5, 5, 5);
            g.drawImage(userCar[3], carX + roadX, carY - 5, 0x4 | 0x10);
            if(bumpCtr == 3)
                speed -= speed / 3;
        } else
        if(bumpCtr > 3 && bumpCtr <= 6)
        {
            g.setColor(103, 87, 15);
            //g.fillRoundRect(carX + 10 + roadX, ((carY + userCar[1].getHeight()) - 4) + tyre.getHeight(), 45, 5, 5, 5);
            g.drawImage(userCar[5], carX + roadX, carY - speed / 2, 0x4 | 0x10);
            if(bumpCtr == 6)
                speed--;
        } else
        if(bumpCtr > 6 && bumpCtr <= 9)
        {
            g.setColor(103, 87, 15);
            //g.fillRoundRect(carX + 10 + roadX, ((carY + userCar[1].getHeight()) - 4) + tyre.getHeight(), 55, 5, 5, 5);
            g.drawImage(userCar[4], carX + roadX, carY - speed / 2, 0x4 | 0x10);
            if(bumpCtr == 9)
                speed -= speed / 2;
        } else
        if(bumpCtr > 9)
        {
            g.drawImage(userCar[5], carX + roadX, carY, 0x4 | 0x10);
            g.setColor(103, 87, 15);
            //g.fillRoundRect(carX + roadX, ((carY + userCar[1].getHeight()) - 4) + tyre.getHeight(), 65, 5, 5, 5);
            speed--;
            bumpCtr = 0;
            showBump = false;
        }
        if(speed < 0)
            speed = 0;
        carX += speed;
    }

    public void changeLane(int i, int j, Graphics g)
    {
        laneCtr++;
        if(!userControlled)
            carX += speed;
        if(i < j && j < 5)
        {
            if(laneCtr < 3)
            {
                g.drawImage(userCar[2], carX + roadX, carY + 10, 0x4 | 0x10);
            } else
            {
                lane = lane + 1;
                carY = 55 + lane * 20;
                g.drawImage(userCar[1], carX + roadX, carY + 1, 0x4 | 0x10);
                g.setColor(103, 87, 15);
                //g.fillRoundRect(carX + roadX, ((carY + userCar[1].getHeight()) - 4) + tyre.getHeight(), 65, 5, 5, 5);
                //g.drawImage(tyre, carX + 9 + roadX, (carY + userCar[1].getHeight()) - 4, 0x4 | 0x10);
                changeLane = false;
                lastLane = oldlane;
                oldlane = j;
                laneCtr = 0;
            }
        } else
        if(i > j && j >= 1)
            if(laneCtr < 3)
            {
                g.drawImage(userCar[0], carX + roadX, carY - 10, 0x4 | 0x10);
            } else
            {
                lane = lane - 1;
                carY = 55 + lane * 20;
                laneCtr = 0;
                g.drawImage(userCar[1], carX + roadX, carY + 1, 0x4 | 0x10);
                g.setColor(103, 87, 15);
                //g.fillRoundRect(carX + roadX, ((carY + userCar[1].getHeight()) - 4) + tyre.getHeight(), 65, 5, 5, 5);
                //g.drawImage(tyre, carX + 9 + roadX, (carY + userCar[1].getHeight()) - 4, 0x4 | 0x10);
                changeLane = false;
                lastLane = oldlane;
                oldlane = j;
            }
        laneCtr++;
    }
}
