// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi

import com.nokia.mid.ui.*;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.lcdui.*;

class ZapperCanvas extends FullCanvas
{

    int page;
    private Command cmdExit;
    private Command cmdClear;
    protected int fireKey;
    protected int leftKey;
    protected int rightKey;
    protected int upKey;
    protected int downKey;
    protected int oneKey;
    int WIDTH;
    int HEIGHT;
    Image offscreen;
    Image logo;
	//************************
    private int targetFPS=5;
    private int introTimer=0;
    Image logo1;
//*************************

	
    Image arrow;
    int ttt;
    Road road;
    int id;
    int keyTemp;
    ZASM midlet;
    Hashtable Cars;
    boolean showBump;
    boolean accel;
    int quadrant;
    int lane;
    int opponents;
    int music;
    int music1;
    boolean changeLaneUp;
    boolean changeLaneDn;
    boolean gameStart;
    boolean gameOver;
    boolean timeOut;
    boolean outoffuel;
    boolean showSignals;
    int signalCtr;
    boolean paused;
    timeKeeper time;
    Image signal;
    int joyStickctr;
    int score[];
    int level;
    Image outoftime;
    int speed;
    Image inner;
    //Image flagImg;
    //Image indiagames;
    Image zapperImg;
    Image tcarimg;
    int inx[];
    int iny[];
    int selRectPos;
    int fuel;
    int rank;
    boolean loaded;
    Image fuelImg;
    public static boolean medialoaded = false;
    public static int totalMedia = 20;
    public static int loadedMedia = 0;
    boolean hiScore;
    int loadCtr;
    int cpage;
    public Image kbImg;
    static GameDataManager gdm;
    static String rname = new String();
    int rscore;
    static String rnames[];
    int rscores[];
    int MAX_TOP_SCORES;
    int Score;
    SoundPlayer gamesound;
    static boolean sndEnable = true;
    int about;
    int hCtr;

    public ZapperCanvas(ZASM zapper)
    {
        page = 0;
        ttt = 0;
        keyTemp = 0;
        Cars = new Hashtable();
        showBump = false;
        accel = false;
        quadrant = 0;
        lane = 4;
        music = 0;
        music1 = 0;
        changeLaneUp = false;
        changeLaneDn = false;
        gameStart = false;
        gameOver = false;
        timeOut = false;
        outoffuel = false;
        showSignals = true;
        paused = false;
        joyStickctr = 0;
        score = new int[4];
        level = 3;
        speed = 0;
        fuel = 100;
        rank = 0;
        loaded = false;
        loadCtr = 0;
        cpage = 1;
        MAX_TOP_SCORES = 3;
        about = 0;
        midlet = zapper;
        WIDTH = getWidth();
        HEIGHT = getHeight();
        fireKey = getKeyCode(8);
        leftKey = getKeyCode(2);
        rightKey = getKeyCode(5);
        upKey = getKeyCode(1);
        downKey = getKeyCode(6);
        if(!isDoubleBuffered())
            offscreen = Image.createImage(WIDTH, HEIGHT);
        try
        {

            kbImg = Image.createImage("/keyboard.png");
            logo = Image.createImage("/logo.png");
        }
        catch(Exception exception)
        {
            System.out.println("异常" + exception);
        }

        gamesound = new SoundPlayer(zapper);

        road = new Road();
        gdm = new GameDataManager(this);

        rnames = new String[MAX_TOP_SCORES];
        rscores = new int[MAX_TOP_SCORES];

        for(int i = 0; i < MAX_TOP_SCORES; i++)
        {
            rnames[i] = new String("");
            rscores[i] = 0;
        }

        gdm.SetupKeyBoard(kbImg);
        gdm.object.getRecords();

        try
        {


            fuelImg = Image.createImage("/fuel.png");
            outoftime = Image.createImage("/outoftime.png");
            loadedMedia = 1;
            signal = Image.createImage("/signal.png");
            loadedMedia = 2;
            //indiagames = Image.createImage("/indiagames.png");
            loadedMedia = 5;
            //zapperImg = Image.createImage("/zapper.png");
            loadedMedia = 6;
            //inner = Image.createImage("/inside.png");
            inner = Image.createImage("/title.png");
            loadedMedia = 7;
            //flagImg = Image.createImage("/flag2.png");
            loadedMedia = 8;
            arrow = Image.createImage("/arrow.png");
            loadedMedia = 9;
            logo = Image.createImage("/p1.png");//logo = Image.createImage("/logo.png");
            loadedMedia = 10;
			logo1 = Image.createImage("/p2.png");
			loadedMedia = 11;

        }
        catch(Exception exception1)
        {
            System.out.println("图片不存在" + exception1);
        }
        initBG();
        medialoaded = true;
    }

    public void initBG()
    {
        int i = 3 + 176 / inner.getWidth();
        int j = 3 + 208 / inner.getHeight();
        inx = new int[i];
        iny = new int[j];
        for(int k = 0; k < i; k++)
            inx[k] = k * inner.getWidth();

        for(int l = 0; l < j; l++)
            iny[l] = l * inner.getHeight();

        System.gc();
    }

    public void DrawBGS(Graphics g)
    {
        //int i = WIDTH / inner.getWidth() + 3;
        //int j = HEIGHT / inner.getHeight() + 3;
        //for(int k = 0; k < i; k++)
        //{
            //for(int l = 0; l < j; l++)
                //g.drawImage(inner, inx[k], iny[l], 0x10 | 0x4);
                g.drawImage(inner, 0, 0, 0x10 | 0x4);

        //}

        //if(inx[0] > -1 * inner.getWidth())
       // {
            //for(int i1 = 0; i1 < i; i1++)
                //inx[i1] -= 6;

           // for(int j1 = 0; j1 < j; j1++)
                //iny[j1] -= 8;

        //} 
        //else
        //{
           // initBG();
       // }
        if(page == 1)
        {
            DirectGraphics directgraphics = DirectUtils.getDirectGraphics(g);
            //g.drawImage(flagImg, 10, 15, 0x10 | 0x4);
            //directgraphics.drawImage(flagImg, 90, 15, 0x10 | 0x4, 8192);
            //g.setColor(255, 255, 255);
            //g.drawImage(indiagames, WIDTH / 2 - indiagames.getWidth() / 2, (HEIGHT / 2 - indiagames.getHeight()) + 10, 0x10 | 0x4);
            //g.drawImage(zapperImg, WIDTH / 2 - zapperImg.getWidth() / 2, 5, 0x10 | 0x4);
            byte byte0 = 30;
            g.setColor(255, 255, 255);
            g.setFont(Font.getFont(0, 1, 8));
            if(joyStickctr % 2 == 0)
            {
                g.drawString("按5键开始比赛", 50, 185, 20);
                joyStickctr = 0;
            }
            joyStickctr++;
        } else
        {
           // g.drawImage(zapperImg, WIDTH / 2 - zapperImg.getWidth() / 2, 5, 0x10 | 0x4);
        }
    }

    public void paint(Graphics g)
    {
        Graphics g1 = g;
        if(offscreen != null)
            g = offscreen.getGraphics();
        if(page == 1 && sndEnable)
            gamesound.playBg();
        if(page == 9 && sndEnable && music1 < 2)
        {
            gamesound.stopSounds();
            gamesound.playHall();
            music1++;
        }
        if(page == 0)
        {
            //g.setColor(0, 0, 0);
            //g.fillRect(0, 0, 176, 208);
            //g.setColor(255, 0, 0);
            //g.setColor(255, 255, 255);
            //g.fillRect(28, 170, 120, 10);
            //g.setColor(255, 0, 0);
            //g.fillRect(28, 170, (120 / totalMedia) * loadCtr, 10);
            //g.drawImage(logo, 0, 0, 0x4 | 0x10);//g.drawImage(logo, 32, 60, 0x4 | 0x10);
            //g.setColor(252, 255, 0);
            //g.drawRect(28, 170, 120, 10);
			//*******************************
           
        if(introTimer < targetFPS *2)
        {
             g.drawImage(logo, 0, 0, 0x4 | 0x10);
        } 
         else if(introTimer >= targetFPS * 2 && introTimer < targetFPS * 4){	
            	g.drawImage(logo1, 0, 0, 0x4 | 0x10);
        }
             
          introTimer++;
       //********************************

			
            loadCtr++;
            if(loadCtr >= totalMedia)
             
			page = 1;
        } else
        if(page == 1)
			
			
			 //g.setColor(0, 0, 0);
            //g.fillRect(0, 0, 176, 208);
            DrawBGS(g);
        
        if(page == 2)
        {
            if(paused || timeOut || outoffuel)
            {
                Cars.clear();
                paused = false;
                gameOver = false;
                gameStart = false;
                showSignals = true;
                changeLaneUp = false;
                changeLaneDn = false;
                outoffuel = false;
                timeOut = false;
                fuel = 100;
                road.mainX = 0;
            }
            DrawBGS(g);
            g.setFont(Font.getFont(0, 1, 8));
            g.setColor(252, 255, 255);
            g.drawString("    新的游戏", 30, 65, 0x10 | 0x4);//25, 65, 0x10 | 0x4);
            g.drawString("    帮助文档", 30, 85, 0x10 | 0x4);//25, 85, 0x10 | 0x4)
            g.drawString("    按键说明", 30, 105, 0x10 | 0x4);//25, 105, 0x10 | 0x4)
            g.drawString("    积分排行", 30, 125, 0x10 | 0x4);//25, 125, 0x10 | 0x4)
            g.drawString("    声音设置", 30, 145, 0x10 | 0x4);//25, 145, 0x10 | 0x4)
            g.drawString("    游戏说明", 30, 165, 0x10 | 0x4);//25, 165, 0x10 | 0x4)
            g.drawString("    退出游戏", 30, 185, 0x10 | 0x4);//25, 185, 0x10 | 0x4)
            g.setColor(255, 255, 255);
            switch(selRectPos)
            {
            case 0: // '\0'
                g.fillRoundRect(20, 60, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    新的游戏", 30, 65, 0x10 | 0x4);//25, 65, 0x10 | 0x4)
                break;

            case 1: // '\001'
                g.fillRoundRect(20, 80, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    帮助文档", 30, 85, 0x10 | 0x4);//25, 85, 0x10 | 0x4)
                break;

            case 2: // '\002'
                g.fillRoundRect(20, 100, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    按键说明", 30, 105, 0x10 | 0x4);//
                break;

            case 3: // '\003'
                g.fillRoundRect(20, 120, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    积分排行", 30, 125, 0x10 | 0x4);//
                break;

            case 4: // '\004'
                g.fillRoundRect(20, 140, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    声音设置", 30, 145, 0x10 | 0x4);//
                break;

            case 5: // '\005'
                g.fillRoundRect(20, 160, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    游戏说明", 30, 165, 0x10 | 0x4);//
                break;

            case 6: // '\006'
                g.fillRoundRect(20, 180, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    退出游戏", 30, 185, 0x10 | 0x4);//
                break;
            }
        } else
        if(page == 3)
        {
            DrawBGS(g);
            g.setFont(Font.getFont(0, 1, 8));
            g.setColor(255, 255, 255);
            g.setColor(62, 112, 226);
            g.fillRect(5, 40, 166, 160);
            g.setColor(255, 255, 0);
            g.drawRect(5, 40, 166, 160);
            g.setColor(255, 255, 255);
            g.setFont(Font.getFont(0, 1, 8));
            g.drawString("按键说明", 45, 50, 0x10 | 0x4);
            g.setFont(Font.getFont(0, 0, 8));
            int i = 65;
            byte byte2 = 17;
            byte byte5 = 10;
            g.drawString("按键菜单", byte5, i, 0x10 | 0x4);
            i += byte2;
            g.drawString("控制 / 数字键", byte5, i, 0x10 | 0x4);
            i += byte2;
            g.drawString("Right   / Num6 - 加速", byte5, i, 0x10 | 0x4);
            i += byte2;
            g.drawString("Up       / Num2 - 左转", byte5, i, 0x10 | 0x4);
            i += byte2;
            g.drawString("Down   / Num8 - 右转", byte5, i, 0x10 | 0x4);
            i += byte2;
            g.drawString("比赛中捡到燃料罐就会",byte5,i, 0x10 | 0x4);
            i += byte2;
            g.drawString("加快你车跑的速度",byte5,i,0x10|0x4);
            g.drawString("     返回", 110, 185, 0x10 | 0x4);
        } else
        if(page == 9)
        {
            DrawBGS(g);
            g.setColor(62, 112, 226);
            g.fillRect(5, 40, 166, 160);
            g.setColor(255, 255, 0);
            g.drawRect(5, 40, 166, 160);
            g.setColor(255, 255, 255);
            g.setFont(Font.getFont(0, 1, 8));
            g.drawString("积分排行", 50, 48, 0x10 | 0x4);
            g.setFont(Font.getFont(0, 0, 8));
            g.drawString("比赛练习", 55, 65, 0x10 | 0x4);
            g.drawString("姓名 ", 45, 80, 0x10 | 0x4);
            g.drawString("积分 ", 105, 80, 0x10 | 0x4);
            g.drawString("" + rnames[0], 45, 95, 0x10 | 0x4);
            g.drawString("" + rscores[0], 105, 95, 0x10 | 0x4);
            g.drawString("Championship", 55, 110, 0x10 | 0x4);
            g.drawString("姓名 ", 45, 125, 0x10 | 0x4);
            g.drawString("积分 ", 105, 125, 0x10 | 0x4);
            g.drawString("" + rnames[1], 45, 140, 0x10 | 0x4);
            g.drawString("" + rscores[1], 105, 140, 0x10 | 0x4);
            g.drawString("帮助", 75, 155, 0x10 | 0x4);
            g.drawString("姓名 ", 45, 170, 0x10 | 0x4);
            g.drawString("积分 ", 105, 170, 0x10 | 0x4);
            g.drawString("" + rnames[2], 45, 185, 0x10 | 0x4);
            g.drawString("" + rscores[2], 105, 185, 0x10 | 0x4);
        } else
        if(page == 4)
        {
            DrawBGS(g);
            g.setColor(62, 112, 226);
            g.fillRect(5, 40, 166, 160);
            g.setColor(255, 255, 0);
            g.drawRect(5, 40, 166, 160);
            g.setColor(255, 255, 255);
            g.setFont(Font.getFont(0, 1, 8));
            g.drawString("游戏帮助", 75, 45, 20);
            g.setFont(Font.getFont(0, 0, 8));
            byte byte0 = 12;
            byte byte3 = 10;//byte byte3 = 15;
            byte byte6 = 60;
            g.drawString("你准备好参加机车狂飚的比赛", byte3, byte6 + byte0 * 0, 20);
            g.drawString("了吧！所有参赛选手都是经过", byte3, byte6 + byte0 * 1, 20);
            g.drawString("精选出来的。本场比赛共分：", byte3, byte6 + byte0 * 2, 20);
            g.drawString("练习赛、锦标赛和马拉松赛三", byte3, byte6 + byte0 * 3, 20);
            g.drawString("级。游戏中你需要注意的就是", byte3, byte6 + byte0 * 4, 20);
            g.drawString("控制好你的速度和方向，小心", byte3, byte6 + byte0 * 5, 20);
            //g.drawString("否则容易撞车", byte3, byte6 + byte0 * 6, 20);
            //g.drawString("你要经常观察四周! ", byte3, byte6 + byte0 * 7, 20);
            g.drawString("撞车。你有信心吗？有的话那", byte3, byte6 + byte0 * 6, 20);//8，20
            g.drawString("就加油吧！", byte3, byte6 + byte0 * 7, 20);//9，20
            g.drawString("    返回", 110, (byte6 + byte0 * 11) - 5, 0x4 | 0x10);//请按键g.drawString("    返回", byte3 + 80, (byte6 + byte0 * 11) - 5, 0x4 | 0x10)
        } else
        if(page == 5)
        {
            DrawBGS(g);
            showSignals = true;
            signalCtr = 0;
            g.setFont(Font.getFont(0, 1, 8));
            g.setColor(255, 255, 255);
            g.drawString("游  戏  难   度", 45, 65, 0x4 | 0x10);
            if(level == 1)
                g.drawString("练  习  赛 ", 50, 95, 0x4 | 0x10);
            else
            if(level == 2)
                g.drawString("锦  标  赛 ", 50, 95, 0x4 | 0x10);
            else
            if(level == 3)
                g.drawString("马 拉 松 赛 ", 70, 95, 0x4 | 0x10);
            if(level == 1)
            {
                opponents = 1;
                time = new timeKeeper(900);
            } else
            if(level == 2)
            {
                opponents = 2;
                time = new timeKeeper(1200);
            } else
            {
                opponents = 3;
                time = new timeKeeper(1500);
            }
            g.drawString("其它赛手 : " + opponents, 25, 115, 0x4 | 0x10);
            g.drawString("比赛时间  : " + time.min + ":" + time.sec + ":" + time.ms, 25, 135, 0x4 | 0x10);
        } else
        if(page == 6)
        {
            if(paused)
                page = 11;
            if(gameOver)
            {
                gameOver = false;
                gameStart = false;
                page = 7;
            } else
            if(timeOut)
                page = 8;
            else
            if(outoffuel)
            {
                page = 8;
            } else
            {
                road.drawRoad(g, quadrant);//这里不知道是不是画红绿灯
                g.setColor(0, 0, 0);
                g.fillRect(0, 192, 176, 28);// g.fillRect(0, 180, 176, 28);
                g.setColor(255, 255, 0);
                g.drawRect(2, 192, 90, 15);  //左边的一个小黄框
                g.drawImage(fuelImg, 96, 192, 0x4 | 0x10); //马鞍的图片
                g.setColor(255, 255, 0);
                g.drawRect(120, 192, 51, 12);    //右边的小框
                g.setColor(255, 0, 0);
                if(gameStart)
                    g.fillRect(121, 193, fuel / 2, 11);  //填充的条是由一个变量控制的
                g.setColor(0, 0, 0);  //左上角的黑色框
                g.fillRect(0, 0, 176, 20);
                g.setColor(255, 255, 0);
                g.setFont(Font.getFont(0, 0, 8));
                //g.setColor(255, 255, 0);
                //g.setFont(Font.getFont(0, 0, 8));
                //g.setColor(255, 255, 0);
                //g.drawRect(2, 182, 90, 24);
                //g.drawImage(fuelImg, 96, 182, 0x4 | 0x10);
                //g.setColor(255, 255, 0);
                //g.drawRect(120, 184, 51, 12);
               // g.setColor(255, 0, 0);
               // if(gameStart)
                //g.fillRect(121, 185, fuel / 2, 11);
               // g.setColor(0, 0, 0);
               // g.fillRect(0, 0, 176, 20);
               // g.setColor(255, 255, 0);
               // g.setFont(Font.getFont(0, 0, 8));
				//**********************************原来是
                g.drawString("倒计时 : " + time.minS + ":" + time.secS + ":" + time.msS, getWidth()- 5, 5, 0x10 | 0x8);//getWidth()- 5, 5, 0x10 | 0x8);
                if(gameStart)
                {
                    drawCar(g);
                } else
                {
                    initializeCars(level, g);
                    ttt++;
                    if(ttt % 2 != 0)
                        g.drawImage(arrow, 74, getHeight() - 57, 3);
                    if(showSignals)
                    {
                        if(signalCtr <= 1)
                        {
                            if(sndEnable && signalCtr == 1)
                            {
                                gamesound.stopSounds();
                                gamesound.playSignal();
                            }
                           // g.drawImage(signal, 88 - signal.getWidth() / 2, 104 - signal.getHeight() / 2, 0x4 | 0x10);
                            g.drawImage(signal, 88 - signal.getWidth() / 2, (104 - signal.getHeight() / 2) + 2, 0x4 | 0x10);
                            g.setColor(255, 0, 0);
                            g.fillArc((88 - signal.getWidth() / 2) + 8, (104 - signal.getHeight() / 2) + 10, 10, 10, 0, 360);
                        } else
                        if(signalCtr > 1 && signalCtr <= 3)
                        {
                            if(sndEnable && signalCtr == 3)
                            {
                                gamesound.stopSounds();
                                gamesound.playSignal();
                            }
                            g.drawImage(signal, 88 - signal.getWidth() / 2, (104 - signal.getHeight() / 2) + 2, 0x4 | 0x10);
                            g.setColor(255, 0, 0);
                            g.fillArc((88 - signal.getWidth() / 2) + 8, (104 - signal.getHeight() / 2) + 10, 10, 10, 0, 360);
                            g.setColor(255, 255, 0);
                            g.fillArc((88 - signal.getWidth() / 2) + 20, (104 - signal.getHeight() / 2) + 10, 10, 10, 0, 360);
                        } else
                        if(signalCtr > 3 && signalCtr <= 6)
                        {
                            if(sndEnable && signalCtr == 5)
                            {
                                gamesound.stopSounds();
                                gamesound.playSignal();
                            }
                            g.drawImage(signal, 88 - signal.getWidth() / 2, (104 - signal.getHeight() / 2) + 2, 0x4 | 0x10);
                            g.setColor(255, 0, 0);//这里就是画红绿灯
                            g.fillArc((88 - signal.getWidth() / 2) + 8, (104 - signal.getHeight() / 2) + 10, 10, 10, 0, 360);
                            // g.fillArc((88 - signal.getWidth() / 2) + 1, (104 - signal.getHeight() / 2) + 7, 10, 10, 0, 360);
                            g.setColor(255, 255, 0);
                            g.fillArc((88 - signal.getWidth() / 2) + 20, (104 - signal.getHeight() / 2) + 10, 10, 10, 0, 360);
                            g.setColor(0, 255, 0);
                            g.fillArc((88 - signal.getWidth() / 2) + 33, (104 - signal.getHeight() / 2) + 10, 10, 10, 0, 360);
                        }
                        signalCtr++;
                        if(signalCtr >= 6)
                        {
                            showSignals = false;
                            gameStart = true;
                            signalCtr = 0;
                        }
                    }
                }
            }
        } else
        if(page == 7)
        {
            System.gc();
            DrawBGS(g);
            g.setFont(Font.getFont(0, 1, 8));
            g.setColor(255, 255, 255);
            g.drawString("完成比赛", 45, 50, 0x4 | 0x10);
            g.drawString("时间积分: " + time.finalTime, 25, 70, 0x4 | 0x10);
            g.drawString("马鞍积分  : " + fuel * 10, 25, 90, 0x4 | 0x10);
            g.drawString("等级 : " + (opponents - rank) * 10, 25, 110, 0x4 | 0x10);
            Score = time.finalTime + fuel * 10 + (opponents - rank) * 10;
            g.drawString("总积分          : " + Score, 25, 130, 0x4 | 0x10);
            road.mainX = 0;
            ttt = 0;
            if(Score > rscores[level - 1])
            {
                hCtr++;
                hiScore = true;
                if(hCtr % 2 == 0)
                    g.drawString("积分排行", 55, 160, 0x4 | 0x10);
            }
        } else
        if(page == 8)
        {
            System.gc();
            level = 1;
            Cars.clear();
            paused = false;
            gameOver = false;
            gameStart = false;
            showSignals = true;
            changeLaneUp = false;
            changeLaneDn = false;
            road.mainX = 0;
            g.setColor(0, 0, 0);
            g.fillRect(0, 0, 176, 208);
            if(timeOut)
                g.drawImage(outoftime, 0, 80, 0x4 | 0x10);
            else
            if(outoffuel)
            {
                fuel = 100;
                g.setColor(255, 255, 0);
                g.drawString("时间用尽", 50, 50, 0x4 | 0x10);
            }
        } else
        if(page == 11)
        {
            DrawBGS(g);
            g.setFont(Font.getFont(0, 1, 8));
            g.setColor(252, 255, 255);
            g.drawString("    继续游戏", 30, 55, 0x10 | 0x4);
            g.drawString("    新的游戏", 25 + 5, 75, 0x10 | 0x4);
            g.drawString("    游戏帮助", 25 + 5, 95, 0x10 | 0x4);
            g.drawString("    按键说明", 25 + 5, 115, 0x10 | 0x4);
            g.drawString("    积分排行", 25 + 5, 135, 0x10 | 0x4);
            g.drawString("    声音设置", 25 + 5, 155, 0x10 | 0x4);
            g.drawString("    游戏说明", 25 + 5, 175, 0x10 | 0x4);
            g.drawString("    退出游戏", 25 + 5, 195, 0x10 | 0x4);
            switch(selRectPos)
            {
            case 0: // '\0'
                g.fillRoundRect(20, 50, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    继续游戏", 25 + 5, 55, 0x10 | 0x4);
                break;

            case 1: // '\001'
                g.fillRoundRect(20, 70, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    新的比赛", 25 + 5, 75, 0x10 | 0x4);
                break;

            case 2: // '\002'
                g.fillRoundRect(20, 90, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    游戏帮助", 25 + 5, 95, 0x10 | 0x4);
                break;

            case 3: // '\003'
                g.fillRoundRect(20, 110, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    按键控制", 25 + 5, 115, 0x10 | 0x4);
                break;

            case 4: // '\004'
                g.fillRoundRect(20, 130, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    积分排行", 25 + 5, 135, 0x10 | 0x4);
                break;

            case 5: // '\005'
                g.fillRoundRect(20, 150, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    声音设置", 25 + 5, 155, 0x10 | 0x4);
                break;

            case 6: // '\006'
                g.fillRoundRect(20, 170, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    游戏说明", 25 + 5, 175, 0x10 | 0x4);
                break;

            case 7: // '\007'
                g.fillRoundRect(20, 190, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    退出游戏", 25 + 5, 195, 0x10 | 0x4);
                break;
            }
        } else
        if(page == 12)
        {
            DrawBGS(g);
            gdm.ShowKeyBoard(g);
            if(sndEnable && music < 2)
            {
                gamesound.stopSounds();
                gamesound.playHiscore();
                music++;
            }
        } else
        if(page == 13)
        {
            DrawBGS(g);
            g.setFont(Font.getFont(0, 1, 8));
            g.setColor(255, 255, 255);
            g.drawString("游  戏  难  度", 45, 65, 0x4 | 0x10);
            g.drawString("    练  习  赛", 35, 95, 0x10 | 0x4);
            g.drawString("    锦  标  赛", 35, 115, 0x10 | 0x4);
            g.drawString("   马 拉 松 赛", 35, 135, 0x10 | 0x4);
            switch(selRectPos)
            {
            case 0: // '\0'
                g.fillRoundRect(30, 90, 120, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    练  习  赛", 35, 95, 0x10 | 0x4);
                break;

            case 1: // '\001'
                g.fillRoundRect(30, 110, 120, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    锦  标  赛", 35, 115, 0x10 | 0x4);
                break;

            case 2: // '\002'
                g.fillRoundRect(30, 130, 120, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("   马 拉 松 赛", 35, 135, 0x10 | 0x4);
                break;
            }
        } else
        if(page == 14)
        {
            DrawBGS(g);
            g.setColor(62, 112, 226);
            g.fillRect(5, 40, 166, 160);
            g.setColor(255, 255, 0);
            g.drawRect(5, 40, 166, 160);
            g.setColor(255, 255, 255);
            g.setFont(Font.getFont(0, 1, 8));
            g.drawString("游戏说明", 50, 45, 20);
            g.setFont(Font.getFont(0, 0, 8));
            byte byte1 = 15;
            byte byte4 = 10;//byte byte4 = 15;
            byte byte7 = 65;
            //if(cpage == 1)
           // {
                g.drawString("北京东方达科技有限公司", byte4, byte7 + byte1 * 0, 20);
                g.drawString("客户邮箱:yanhairong@dafada.com.cn", byte4, byte7 + byte1 * 1, 20);
                g.drawString("客服电话: 010-83502955", byte4, byte7 + byte1 * 2, 20);
               // g.drawString("网  址：http://www.sifan.cn/",byte4, byte7 + byte1 * 3, 20);
                //g.drawString("诺基亚S60游戏", byte4, byte7 + byte1 * 3, 20);
               // g.drawString("www.sifan.cn", byte4, byte7 + byte1 * 4, 20);
                //g.drawString("info@indiagames.com", byte4, byte7 + byte1 * 5, 20);
                //g.drawString("\251 Indiagames 2002-2003.", byte4, byte7 + byte1 * 6, 20);
                //g.drawString("All rights reserved.", byte4, byte7 + byte1 * 7, 20);
                //g.drawString("Press joystick", 95, 185, 0x4 | 0x10);
            //} //else
            /**{
                g.drawString("感谢使用斯凡文化手机游戏", byte4, byte7 + byte1 * 0, 20);
                g.drawString("斯凡文化", byte4, byte7 + byte1 * 1, 20);
                g.drawString("打造互动数字媒体全平台！", byte4, byte7 + byte1 * 2, 20);
                //g.drawString("END-USER LICENSE AGREEMENT.", byte4, byte7 + byte1 * 3, 20);
                //g.drawString("www.indiagames.com/eula.asp", byte4, byte7 + byte1 * 4, 20);
            }*/
            g.drawString("  返回", 110, 185, 0x4 | 0x10);//请按键...
        } else
        if(page == 15)
        {
            DrawBGS(g);
            g.setFont(Font.getFont(0, 1, 8));
            g.setColor(255, 255, 255);
            g.drawString("声音设置", 70, 65, 0x4 | 0x10);
            if(sndEnable)
            {
                g.drawString("    声音 开 <<", 45, 85, 0x10 | 0x4);
                g.drawString("    声音 关", 45, 105, 0x10 | 0x4);
            } else
            {
                g.drawString("    声音 开", 45, 85, 0x10 | 0x4);
                g.drawString("    声音 关 <<", 45, 105, 0x10 | 0x4);
            }
            g.drawString("    游戏菜单", 45, 125, 0x10 | 0x4);
            switch(selRectPos)
            {
            default:
                break;

            case 0: // '\0'
                g.fillRoundRect(20, 80, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                if(sndEnable)
                    g.drawString("    声音 开 <<", 45, 85, 0x10 | 0x4);
                else
                    g.drawString("    声音 开", 45, 85, 0x10 | 0x4);
                break;

            case 1: // '\001'
                g.fillRoundRect(20, 100, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                if(sndEnable)
                    g.drawString("    声音 关", 45, 105, 0x10 | 0x4);
                else
                    g.drawString("    声音 关 <<", 45, 105, 0x10 | 0x4);
                break;

            case 2: // '\002'
                g.fillRoundRect(20, 120, 136, 18, 5, 5);
                g.setColor(0, 102, 204);
                g.drawString("    游戏菜单", 45, 125, 0x10 | 0x4);
                break;
            }
        }
        if(g != g1)
            g1.drawImage(offscreen, 0, 0, 20);
    }

    public void initializeCars(int i, Graphics g)
    {
        id = 0;
        Cars.put("" + id, new Car(id, 4, 25, 1, true, this));
        if(i == 1)
        {
            id++;
            Cars.put("" + id, new Car(id, 1, 23, 1, false, this));
        } else
        if(i == 2)
        {
            id++;
            Cars.put("" + id, new Car(id, 1, 23, 1, false, this));
            id++;
            Cars.put("" + id, new Car(id, 3, 24, 1, false, this));
        } else
        if(i == 3)
        {
            id++;
            Cars.put("" + id, new Car(id, 3, 17, 1, false, this));
            id++;
            Cars.put("" + id, new Car(id, 2, 20, 1, false, this));
            id++;
            Cars.put("" + id, new Car(id, 1, 21, 1, false, this));
        }
        Car car;
        for(Enumeration enumeration = Cars.keys(); enumeration.hasMoreElements(); car.drawCar(g, 0))
        {
            String s = (String)enumeration.nextElement();
            car = (Car)Cars.get(s);
        }

    }

    public void drawCar(Graphics g)
    {
        for(int i = 1; i <= 4; i++)
        {
            for(Enumeration enumeration = Cars.keys(); enumeration.hasMoreElements();)
            {
                String s = (String)enumeration.nextElement();
                Car car = (Car)Cars.get(s);
                if(car.lane == i && !car.lapComplete)
                {
                    car.moveCar();
                    if(car.userControlled)
                    {
                        fuel = car.fuel;
                        if(road.finishX <= -20)
                        {
                            if(sndEnable)
                            {
                                gamesound.stopSounds();
                                gamesound.playlapComplete();
                            }
                            gameOver = true;
                        }
                        if(changeLaneUp && car.oldlane > 1)
                        {
                            changeLaneUp = false;
                            car.changeLane = true;
                            car.oldlane = car.lane;
                            car.newlane = car.lane - 1;
                        } else
                        if(changeLaneDn && car.oldlane < 4)
                        {
                            changeLaneDn = false;
                            car.changeLane = true;
                            car.oldlane = car.lane;
                            car.newlane = car.lane + 1;
                        }
                        car.accel = accel;
                        car.carX = 0;
                        car.carY = car.carY;
                        road.moveRoad(car.speed);
                        car.drawCar(g, 0);
                        quadrant = car.quadrant;
                        if(-road.mainX + 65 >= (car.quadrant + 1) * road.road.getWidth())
                        {
                            if(!car.lapComplete)
                                car.quadrant++;
                            if(car.quadrant % 2 == 0)
                                car.fuel -= 5;
                            if(road.map[car.quadrant] == 2 && -road.mainX + 65 > road.roadX + road.map[car.quadrant] * road.road.getWidth())
                                car.showBump = true;
                        }
                        if(road.fuelLane[car.quadrant] == car.lane && road.showFuel[car.quadrant] && Math.abs(road.fuelX[car.quadrant] + road.mainX) <= 65)
                        {
                            if(fuel < 100)
                                car.fuel += 10;
                            if(car.fuel > 100)
                                car.fuel = 100;
                            if(sndEnable)
                                gamesound.playFuelSound();
                            road.showFuel[car.quadrant] = false;
                        }
                    } else
                    {
                        if(car.quadrant == road.map.length - 1)
                        {
                            car.lapComplete = true;
                            rank++;
                        }
                        car.drawCar(g, road.mainX);
                        if(car.carX + 65 >= (car.quadrant + 1) * road.road.getWidth())
                        {
                            if(car.quadrant % 2 == 0)
                                car.fuel -= 5;
                            if(!car.lapComplete)
                                car.quadrant++;
                            if(road.map[car.quadrant] == 2 && car.carX + 65 > road.roadX + road.map[car.quadrant] * road.road.getWidth())
                            {
                                car.showBump = true;
                                car.bumped = true;
                            }
                        }
                        if(car.quadrant != 2)
                            car.bumped = false;
                        if(road.fuelLane[car.quadrant] == car.lane && road.showFuel[car.quadrant] && Math.abs(road.fuelX[car.quadrant] + road.mainX) <= 65)
                        {
                            if(car.fuel <= 95)
                                car.fuel += 5;
                            road.showFuel[car.quadrant] = false;
                        }
                    }
                }
            }

        }

    }

    public void hideNotify()
    {
        if(page == 6 && gameStart)
            paused = true;
    }

    protected void keyPressed(int i)
    {
        if(keyTemp == 0)
        {
            if(page == 12)
                gdm.HandleKeyInput(i);
            else
            if(i == upKey || i == 50)
            {
                if(page == 11)
                {
                    if(selRectPos > 0)
                        selRectPos--;
                    else
                    if(selRectPos == 0)
                        selRectPos = 7;
                } else
                if(page == 2)
                {
                    if(selRectPos > 0)
                        selRectPos--;
                    else
                        selRectPos = 6;
                } else
                if(page == 13 || page == 15)
                {
                    if(selRectPos > 0)
                        selRectPos--;
                    else
                        selRectPos = 2;
                } else
                if(page == 6)
                    changeLaneUp = true;
            } else
            if(i == downKey || i == 56)
            {
                if(page == 2)
                {
                    if(selRectPos < 6)
                        selRectPos++;
                    else
                        selRectPos = 0;
                } else
                if(page == 11)
                {
                    if(selRectPos < 7)
                        selRectPos++;
                    else
                    if(selRectPos == 7)
                        selRectPos = 0;
                } else
                if(page == 13)
                {
                    if(selRectPos < 2)
                        selRectPos++;
                    else
                        selRectPos = 0;
                } else
                if(page == 6)
                    changeLaneDn = true;
                else
                if(page == 15)
                    if(selRectPos < 2)
                        selRectPos++;
                    else
                        selRectPos = 0;
            } else
            if(i == rightKey || i == 54)
            {
                if(page == 6 && !accel)
                {
                    accel = true;
                    speed = speed + 1;
                }
            } else
            if(i == leftKey || i == 52)
            {
                if(page == 6 && accel)
                    accel = false;
            } else
            if(i == fireKey || i == 53 || i == 8)//if(i == fireKey || i == 53 
            {
                if(page == 1)
                {
                    gamesound.stopSounds();
                    if(!paused)
                        page = 2;
                    else
                        page = 11;
                } else
                if(page == 3)
                {
                    if(!paused)
                        page = 2;
                    else
                        page = 11;
                } else
                if(page == 4)
                {
                    if(!paused)
                        page = 2;
                    else
                        page = 11;
                } else
                if(page == 5)
                {
                    road.setRoad(level);
                    page = 6;
                    if(sndEnable)
                        gamesound.stopSounds();
                } else
                if(page == 6)
                {
                    if(!showSignals)
                        showSignals = true;
                } else
                if(page == 7)
                {
                    if(hiScore)
                        page = 12;
                    else
                    if(!paused)
                        page = 2;
                    else
                        page = 11;
                } else
                if(page == 8)
                {
                    outoffuel = false;
                    timeOut = false;
                    if(!paused)
                        page = 2;
                    else
                        page = 11;
                } else
                if(page == 9)
                {
                    gamesound.stopSounds();
                    if(!paused)
                        page = 2;
                    else
                        page = 11;
                    GameDataManager.nameStr = "";
                    GameDataManager.nameval = "";
                    music1 = 0;
                } else
                if(page == 11)
                {
                    if(selRectPos == 0)
                    {
                        paused = false;
                        page = 6;
                        if(!sndEnable);
                    }
                    if(selRectPos == 1)
                    {
                        showSignals = true;
                        signalCtr = 0;
                        paused = false;
                        gameOver = false;
                        gameStart = false;
                        changeLaneUp = false;
                        changeLaneDn = false;
                        outoffuel = false;
                        timeOut = false;
                        fuel = 100;
                        road.mainX = 0;
                        page = 13;
                        selRectPos = 0;
                    } else
                    if(selRectPos == 2)
                    {
                        page = 4;
                        about = 0;
                    } else
                    if(selRectPos == 3)
                        page = 3;
                    else
                    if(selRectPos == 4)
                        page = 9;
                    if(selRectPos == 5)
                    {
                        page = 15;
                        if(sndEnable)
                            selRectPos = 0;
                        else
                            selRectPos = 1;
                    } else
                    if(selRectPos == 6)
                    {
                        cpage = 1;
                        page = 14;
                    } else
                    if(selRectPos == 7)
                        midlet.exitMIDlet();
                } else
                if(page == 13)
                {
                    if(selRectPos == 0)
                    {
                        level = 1;
                        page = 5;
                    } else
                    if(selRectPos == 1)
                    {
                        level = 2;
                        page = 5;
                    } else
                    if(selRectPos == 2)
                    {
                        level = 3;
                        page = 5;
                    }
                    music = 0;
                } else
                if(page == 14)
                {
                    if(cpage == 1)
                        cpage = 2;
                    else
                    if(!paused)
                        page = 2;
                    else
                        page = 11;
                } else
                if(page == 2)
                {
                    if(selRectPos == 0)
                        page = 13;
                    else
                    if(selRectPos == 1)
                    {
                        page = 4;
                        about = 0;
                    } else
                    if(selRectPos == 2)
                        page = 3;
                    else
                    if(selRectPos == 3)
                        page = 9;
                    if(selRectPos == 4)
                    {
                        if(sndEnable)
                            selRectPos = 0;
                        else
                            selRectPos = 1;
                        page = 15;
                    } else
                    if(selRectPos == 5)
                    {
                        cpage = 1;
                        page = 14;
                    } else
                    if(selRectPos == 6)
                        midlet.exitMIDlet();
                } else
                if(page == 15)
                {
                    if(selRectPos == 0)
                        sndEnable = true;
                    else
                    if(selRectPos == 1)
                    {
                        gamesound.stopSounds();
                        sndEnable = false;
                    }
                    if(selRectPos == 2)
                        if(!paused)
                        {
                            selRectPos = 4;
                            page = 2;
                        } else
                        {
                            selRectPos = 5;
                            page = 11;
                        }
                }
            } else
            if(i == -6 || i == -7)
            {
                if(page == 6 && gameStart)
                    paused = true;
                if(!paused)
                    page = 2;
                else
                    page = 11;
            }
            keyTemp = 1;
        }
    }

    protected void keyReleased(int i)
    {
        if(keyTemp == 1)
            keyTemp = 0;
    }

    public void commandAction(Command command, Displayable displayable)
    {
    }

}
