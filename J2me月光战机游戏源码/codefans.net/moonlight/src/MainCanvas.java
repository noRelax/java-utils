import java.io.*;
import java.util.Random;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;
// download by http://www.codefans.net
public class MainCanvas extends Canvas
    implements Runnable, CommandListener
{   
    static int MainFlag = 0;
    static int ThreadSpeed = 100;
    static int ReadCount = 0;
    static int ik = 0;
    static int gk = 0;
    static boolean FlgSoftKey1 = false;
    static boolean FlgSoftKey2 = false;
    static boolean FlgPushDecide = false;
    static boolean FlgPushUp = false;
    static boolean FlgPushDown = false;
    static boolean FlgPushLeft = false;
    static boolean FlgPushRight = false;
    static int LogoCount = 0;
    static int TitleCount = 0;
    static int ClearWait = 0;
    static int Cursor = 0;
    static int BackMain = 0;
    static String StrHelpMenu[] = null;
    InputStream is;
    public static Command toggleCommand;
    public static Command exitCommand;
    static String SoftMess1 = "";
    static String SoftMess2 = "";
    RecordStore rs;
    static int rsId = 0;
    static Image offImage = null;
    static Graphics offGfx = null;
    static Image imgTitle;
    static Image imgMyShip;
    static Image imgIcon[] = new Image[4];
    static Image imgFace[] = new Image[2];
    static Image imgEye[] = new Image[2];
    static Image imgEnemy[] = new Image[7];
    static Image imgBoss[] = new Image[22];
    static Image imgMyShot[] = new Image[15];
    static Image imgEShot[] = new Image[10];
    static Image imgBomb[] = new Image[4];
    static Image imgStar[] = new Image[4];
    static boolean PlayerAlive = true;
    static int PlayerBomb = 0;
    static int PlayerX;
    static int PlayerY;
    static int PlayerXSp;
    static int PlayerYSp;
    static int PAttackMode;
    static int PAttackMain;
    static int Muteki = 0;
    static int MySpecialCount = 0;
    static int MyChar;
    static int ShotWait;
    static int AnimMyLazer;
    static int MyLazerLength;
    static boolean MyShotAlive[] = new boolean[30];
    static int MyShotType[] = new int[30];
    static int MyShotX[] = new int[30];
    static int MyShotY[] = new int[30];
    static int MyShotMX[] = new int[30];
    static int MyShotMY[] = new int[30];
    static int EnemyWait;
    static int EnemyType[] = new int[20];
    static boolean EnemyAlive[] = new boolean[20];
    static int EnemyLife[] = new int[20];
    static int EnemyX[] = new int[20];
    static int EnemyY[] = new int[20];
    static int EnemyXSize[] = new int[20];
    static int EnemyYSize[] = new int[20];
    static int EnemyFlag1[] = new int[20];
    static int EnemyFlag2[] = new int[20];
    static int EnemyBomb[] = new int[20];
    static int EnemyPoint[] = new int[20];
    static int BossWait = 0;
    static int BossType[] = new int[5];
    static boolean BossAlive[] = new boolean[5];
    static boolean BossDamage[] = new boolean[5];
    static int BossMaxLife = 0;
    static int BossLife[] = new int[5];
    static int BossX[] = new int[5];
    static int BossY[] = new int[5];
    static int BossXSize[] = new int[5];
    static int BossYSize[] = new int[5];
    static int BossFlag1[] = new int[5];
    static int BossFlag2[] = new int[5];
    static int BossFlag3[] = new int[5];
    static int BossFlag4[] = new int[5];
    static int BossBomb[] = new int[5];
    static int BossPoint[] = new int[5];
    static boolean BossTarget;
    static int BossTargetX;
    static int BossTargetY;
    static boolean EShotAlive[] = new boolean[100];
    static int EShotType[] = new int[100];
    static int EShotX[] = new int[100];
    static int EShotY[] = new int[100];
    static int EShotMX[] = new int[100];
    static int EShotMY[] = new int[100];
    static int EShotFlag[] = new int[100];
    static int StarType[] = new int[10];
    static int StarX[] = new int[10];
    static int StarY[] = new int[10];
    static int EncountType[] = new int[5];
    static int EncountNumber = 0;
    static boolean FlgOption[] = new boolean[3];
    static int Round = 1;
    static int Stage = 1;
    static int Score = 0;
    static int HiScore = 0;
    static int ExtendPoint = 0;
    static int BonusTotal = 0;
    static int BonusPoint = 0;
    static int BonusEnemy = 0;
    static int BonusEscape = 0;
    static int Rest = 3;
    static int MyLife = 5;
    static boolean GameClear = false;
    static boolean FlgGameOver = false;
    static int FlgBoss = 0;
    static int MapScroll = 0;
    static int StartCount = 0;
    static int ExtendCount = 0;
    static int OverCount = 0;
    static int HelpPage = 0;
    static int Path[] = new int[2];


    public MainCanvas(Display display1, moonlight moonlight1)
    {
        display = null;
        thread = null;
        god = new Random();
        SCREEN_WH = SCREEN_W / 2;
        is = null;
        rs = null;
        boolean flag = false;
        app = moonlight1;
        display = display1;
        try
        {
            boolean flag1 = false;
            Object obj = null;
            Object obj1 = null;
        }
        catch(Exception exception) { }
        offImage = Image.createImage(SCREEN_W, SCREEN_H);
        offGfx = offImage.getGraphics();
        DataLoad();
        for(int i = 0; i < FlgOption.length; i++)
            FlgOption[i] = true;

        thread = new Thread(this);
        thread.start();
        SetSoftKey();
    }

    public void paint(Graphics g)
    {
        Graphics g1 = g;
        g = offGfx;
        offGfx = g1;
    }

    public void run()
    {
        long l = 0L;
        do
        {
            try
            {
                Thread.yield();
                try
                {
                    if(System.currentTimeMillis() - l >= (long)ThreadSpeed)
                    {
                        l = System.currentTimeMillis();
                        AutoEvent();
                        ScanKey();
                        DrawGraphic();
                        repaint();
                        System.gc();
                    }
                }
                catch(Exception exception) { }
            }
            catch(Exception exception1)
            {
                return;
            }
            
        } while(true);
    }

    public void commandAction(Command command, Displayable displayable)
    {
    }

    public void AutoEvent()
    {
        boolean flag = false;
        boolean flag1 = false;
        switch(MainFlag)
        {
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        default:
            break;

        case 0: // '\0'
            try
            {
                Object obj = null;
                if(ReadCount != 0)
                    if(ReadCount >= 1 && ReadCount < 16)
                    {
                        if(ReadCount == 1)
                            is = getClass().getResourceAsStream("tshot");
                        int i = ReadCount - 1;
                        byte abyte0[] = new byte[SHOT_SIZE[i]];
                        is.read(abyte0);
                        imgMyShot[i] = Image.createImage(abyte0, 0, abyte0.length);
                        abyte0 = null;
                        System.gc();
                        if(ReadCount == 15)
                            is.close();
                    } else
                    {
                        imgTitle = Image.createImage("/title.png");
                        imgMyShip = Image.createImage("/jiki.png");
                        for(int i10 = 0; i10 < 4; i10++)
                            imgIcon[i10] = Image.createImage("/icon" + i10 + ".png");

                        for(int j10 = 0; j10 < 2; j10++)
                        {
                            imgFace[j10] = Image.createImage("/face" + j10 + ".png");
                            imgEye[j10] = Image.createImage("/eye" + j10 + ".png");
                        }

                        for(int k10 = 0; k10 < 7; k10++)
                            imgEnemy[k10] = Image.createImage("/z" + k10 + ".png");

                        for(int l10 = 0; l10 < 10; l10++)
                            imgEShot[l10] = Image.createImage("/eshot" + l10 + ".png");

                        for(int i11 = 0; i11 < 4; i11++)
                            imgBomb[i11] = Image.createImage("/bomb" + i11 + ".png");

                        for(int j11 = 0; j11 < 4; j11++)
                            imgStar[j11] = Image.createImage("/star" + j11 + ".png");

                        ChangeMain(2);
                    }
                ReadCount++;
            }
            catch(Exception exception) { }
            break;

        case 1: // '\001'
            LogoCount++;
            if(LogoCount >= 20)
                ChangeMain(2);
            break;

        case 7: // '\007'
            Object obj1 = null;
            int ai[] = {
                1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 
                3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 
                5, 5
            };
            try
            {
                if(ReadCount != 0)
                    if(ReadCount >= 1 && ReadCount < 11)
                    {
                        if(ReadCount == 1)
                            is = getClass().getResourceAsStream("tboss1");
                        int j = ReadCount - 1;
                        if(ai[ReadCount - 1] == Stage)
                        {
                            byte abyte1[] = new byte[BOSS_SIZE1[j]];
                            is.read(abyte1);
                            imgBoss[j] = Image.createImage(abyte1, 0, abyte1.length);
                            abyte1 = null;
                            System.gc();
                        } else
                        {
                            is.skip(BOSS_SIZE1[j]);
                        }
                        if(ReadCount == 10)
                            is.close();
                    } else
                    if(ReadCount >= 11 && ReadCount < 23)
                    {
                        if(ReadCount == 11)
                            is = getClass().getResourceAsStream("tboss2");
                        int k = ReadCount - 11;
                        if(ai[ReadCount - 1] == Stage)
                        {
                            byte abyte2[] = new byte[BOSS_SIZE2[k]];
                            is.read(abyte2);
                            imgBoss[k + 10] = Image.createImage(abyte2, 0, abyte2.length);
                            abyte2 = null;
                            System.gc();
                        } else
                        {
                            is.skip(BOSS_SIZE2[k]);
                        }
                        if(ReadCount == 22)
                            is.close();
                    } else
                    {
                        ChangeMain(8);
                    }
                ReadCount++;
            }
            catch(Exception exception1) { }
            break;

        case 8: // '\b'
            if(FlgBoss != 0);
            if(!PlayerAlive)
            {
                PlayerBomb++;
                if(PlayerBomb > 20)
                    if(Rest > 0)
                    {
                        PlayerAlive = true;
                        PlayerBomb = 0;
                        PlayerXSp = 0;
                        PlayerYSp = 0;
                        Muteki = 10;
                        MyLife = 6;
                        Rest--;
                    } else
                    {
                        FlgGameOver = true;
                    }
            }
            for(int k11 = 0; k11 < 5; k11++)
                BossDamage[k11] = false;

            if(MySpecialCount > 0)
                MySpecialCount--;
            if(Muteki > 0)
                Muteki--;
            if(StartCount > 0)
                StartCount--;
            if(ExtendCount > 0)
                ExtendCount--;
            for(int l11 = 0; l11 < 10; l11++)
                if(StarY[l11] >= SCREEN_H + 10)
                {
                    StarType[l11] = l11 & 3;
                    StarX[l11] = Math.abs(god.nextInt() % SCREEN_W);
                    StarY[l11] = -Math.abs(god.nextInt() % 100) - 10;
                } else
                {
                    StarY[l11] += (l11 >> 2) + 5;
                }

            for(int i23 = 0; i23 < 2; i23++)
            {
                for(int i12 = 0; i12 < 30; i12++)
                    if(MyShotAlive[i12])
                    {
                        MyShotX[i12] += MyShotMX[i12];
                        MyShotY[i12] += MyShotMY[i12];
                        if(MyShotX[i12] < -10)
                            MyShotAlive[i12] = false;
                        if(MyShotX[i12] > SCREEN_W + 10)
                            MyShotAlive[i12] = false;
                        if(MyShotY[i12] < 0)
                            MyShotAlive[i12] = false;
                        if(MyShotY[i12] > SCREEN_H + 10)
                            MyShotAlive[i12] = false;
                        if(FlgBoss == 2)
                        {
                            for(int j14 = 0; j14 < 5; j14++)
                                if(BossType[j14] != 0 && BossAlive[j14] && Math.abs(BossX[j14] - MyShotX[i12]) <= BossXSize[j14] >> 1 && Math.abs(BossY[j14] - MyShotY[i12]) <= BossYSize[j14] >> 1)
                                {
                                    MyShotAlive[i12] = false;
                                    BossDamage[j14] = true;
                                    BossLife[j14]--;
                                    if(BossLife[j14] <= 0)
                                    {
                                        BossAlive[j14] = false;
                                        PlusScore(BossPoint[j14]);
                                    }
                                }

                        } else
                        {
                            for(int k14 = 0; k14 < 20; k14++)
                                if(EnemyType[k14] != 0 && EnemyAlive[k14] && Math.abs(EnemyX[k14] - MyShotX[i12]) <= (EnemyXSize[k14] >> 1) + 4 && Math.abs(EnemyY[k14] - MyShotY[i12]) <= (EnemyYSize[k14] >> 1) + 4)
                                {
                                    MyShotAlive[i12] = false;
                                    EnemyLife[k14]--;
                                    if(EnemyLife[k14] <= 0)
                                    {
                                        EnemyAlive[k14] = false;
                                        PlusScore(EnemyPoint[k14]);
                                    }
                                }

                        }
                    }

            }

            if(PlayerAlive)
                if(PAttackMode == 0)
                {
                    ShotWait--;
                    if(ShotWait <= 0)
                    {
                        if(MyChar == 0)
                        {
                            CreateMyShot(0, 0, -8);
                            CreateMyShot(0, -2, -7);
                            CreateMyShot(0, 2, -7);
                        } else
                        {
                            CreateMyShot(0, 0, -8);
                            CreateMyShot(0, -2, -7);
                            CreateMyShot(0, 2, -7);
                            CreateMyShot(1, -4, -6);
                            CreateMyShot(2, 4, -6);
                        }
                        ShotWait = 3;
                    }
                } else
                {
                    AnimMyLazer = 1 - AnimMyLazer;
                    MyLazerLength = 0;
                    boolean flag2 = false;
                    byte byte4 = 0;
                    if(MyChar == 0)
                        byte4 = 20;
                    else
                        byte4 = 16;
                    for(int j12 = 0; j12 < 15; j12++)
                    {
                        MyLazerLength += byte4;
                        if(FlgBoss == 2)
                        {
                            for(int l14 = 0; l14 < 5; l14++)
                                if(BossType[l14] != 0 && BossAlive[l14] && BossY[l14] <= PlayerY && BossY[l14] >= PlayerY - MyLazerLength && Math.abs(BossX[l14] - PlayerX) <= (BossXSize[l14] >> 1) + 4)
                                {
                                    BossDamage[l14] = true;
                                    if(MyChar == 0)
                                        BossLife[l14] -= 4;
                                    else
                                        BossLife[l14] -= 3;
                                    if(BossLife[l14] <= 0)
                                    {
                                        BossAlive[l14] = false;
                                        PlusScore(BossPoint[l14]);
                                    } else
                                    {
                                        flag2 = true;
                                    }
                                }

                        } else
                        {
                            for(int i15 = 0; i15 < 20; i15++)
                                if(EnemyType[i15] != 0 && EnemyAlive[i15] && EnemyY[i15] <= PlayerY && EnemyY[i15] >= PlayerY - MyLazerLength && Math.abs(EnemyX[i15] - PlayerX) <= EnemyXSize[i15] >> 1)
                                {
                                    if(MyChar == 0)
                                        EnemyLife[i15] -= 5;
                                    else
                                        EnemyLife[i15] -= 3;
                                    if(EnemyLife[i15] <= 0)
                                    {
                                        EnemyAlive[i15] = false;
                                        PlusScore(EnemyPoint[i15]);
                                    } else
                                    {
                                        flag2 = true;
                                    }
                                }

                        }
                        if(flag2)
                            break;
                    }

                }
            for(int k12 = 0; k12 < 100; k12++)
                if(EShotAlive[k12])
                    switch(EShotType[k12])
                    {
                    default:
                        break;

                    case 0: // '\0'
                    case 1: // '\001'
                    case 2: // '\002'
                    case 3: // '\003'
                    case 4: // '\004'
                    case 5: // '\005'
                    case 9: // '\t'
                        EShotX[k12] += EShotMX[k12];
                        EShotY[k12] += EShotMY[k12];
                        if(EShotX[k12] <= -100)
                            EShotAlive[k12] = false;
                        if(EShotX[k12] >= (SCREEN_W + 10) * 10)
                            EShotAlive[k12] = false;
                        if(EShotY[k12] <= -100)
                            EShotAlive[k12] = false;
                        if(EShotY[k12] >= (SCREEN_H + 10) * 10)
                            EShotAlive[k12] = false;
                        break;

                    case 6: // '\006'
                        EShotX[k12] += EShotMX[k12];
                        EShotY[k12] += EShotMY[k12];
                        EShotMY[k12]++;
                        if(EShotX[k12] <= -100)
                            EShotAlive[k12] = false;
                        if(EShotX[k12] >= (SCREEN_W + 10) * 10)
                            EShotAlive[k12] = false;
                        if(EShotY[k12] <= -100)
                            EShotAlive[k12] = false;
                        if(EShotY[k12] >= (SCREEN_H + 10) * 10)
                            EShotAlive[k12] = false;
                        break;

                    case 7: // '\007'
                        EShotX[k12] += EShotMX[k12];
                        EShotY[k12] += EShotMY[k12];
                        if(EShotX[k12] <= 0)
                            EShotMX[k12] = Math.abs(EShotMX[k12]);
                        if(EShotX[k12] >= SCREEN_W * 10)
                            EShotMX[k12] = -Math.abs(EShotMX[k12]);
                        if(EShotY[k12] <= -100)
                            EShotAlive[k12] = false;
                        if(EShotY[k12] >= (SCREEN_H + 10) * 10)
                            EShotAlive[k12] = false;
                        break;

                    case 8: // '\b'
                        EShotX[k12] += EShotMX[k12];
                        EShotY[k12] += EShotMY[k12];
                        EShotFlag[k12]++;
                        if(EShotFlag[k12] >= 5)
                            EShotAlive[k12] = false;
                        break;

                    case 10: // '\n'
                        EShotX[k12] += EShotMX[k12];
                        EShotY[k12] += EShotMY[k12];
                        EShotFlag[k12]++;
                        if(EShotFlag[k12] >= 2)
                            CreateEShot(8, EShotX[k12] / 10, EShotY[k12] / 10, 0, 0);
                        if(EShotX[k12] <= -100)
                            EShotAlive[k12] = false;
                        if(EShotX[k12] >= (SCREEN_W + 10) * 10)
                            EShotAlive[k12] = false;
                        if(EShotY[k12] <= -100)
                            EShotAlive[k12] = false;
                        if(EShotY[k12] >= (SCREEN_H + 10) * 10)
                            EShotAlive[k12] = false;
                        break;

                    case 11: // '\013'
                        EShotFlag[k12]++;
                        if(EShotFlag[k12] > 120)
                        {
                            EShotX[k12] += EShotMX[k12];
                            EShotY[k12] += EShotMY[k12];
                        } else
                        if(EShotFlag[k12] % 12 < 10)
                        {
                            EShotX[k12] += EShotMX[k12];
                            EShotY[k12] += EShotMY[k12];
                        } else
                        if(EShotFlag[k12] % 12 < 12)
                        {
                            Path = SetTarget(EShotX[k12] / 10, EShotY[k12] / 10, PlayerX, PlayerY, 4);
                            EShotMX[k12] = Path[0];
                            EShotMY[k12] = Path[1];
                        }
                        if(EShotX[k12] <= -100)
                            EShotAlive[k12] = false;
                        if(EShotX[k12] >= (SCREEN_W + 10) * 10)
                            EShotAlive[k12] = false;
                        if(EShotY[k12] <= -100)
                            EShotAlive[k12] = false;
                        if(EShotY[k12] >= (SCREEN_H + 10) * 10)
                            EShotAlive[k12] = false;
                        break;
                    }

            if(FlgBoss == 2)
            {
                boolean flag3 = true;
                for(int l12 = 0; l12 < 5; l12++)
label0:
                    switch(BossType[l12])
                    {
                    default:
                        break;

                    case 1: // '\001'
                        if(l12 == 0)
                        {
                            if(!BossAlive[l12])
                            {
                                Muteki = 10;
                                BossAlive[1] = false;
                                BossAlive[2] = false;
                                BossBomb[l12]++;
                                if(BossBomb[l12] / 4 >= 5 && PlayerBomb <= 0)
                                {
                                    BossType[l12] = 0;
                                    GameClear = true;
                                }
                                break;
                            }
                            BossFlag2[l12]++;
                            int ai2[] = {
                                1, 1, 1, 2, 2, 2, 3, 3, 3
                            };
                            if(BossFlag2[l12] >= 10 && BossFlag2[l12] < 19)
                            {
                                int l = BossFlag2[l12] - 10;
                                if(Round != 1 && BossFlag2[l12] == 10)
                                {
                                    CreateEShot(5, BossX[l12] - 4, BossY[l12] + 17, -24, 32);
                                    CreateEShot(5, BossX[l12], BossY[l12] + 15, 0, 40);
                                    CreateEShot(5, BossX[l12] + 4, BossY[l12] + 17, 24, 32);
                                }
                                if(BossAlive[1])
                                {
                                    CreateEShot(ai2[l], BossX[l12] - 24, BossY[l12] - 16, CIRCLEX[l * 2 + 16] * 6, CIRCLEY[l * 2 + 16] * 6);
                                    if(Round != 1)
                                        CreateEShot(ai2[l], BossX[l12] - 24, BossY[l12] - 16, -CIRCLEX[l * 2 + 16] * 6, CIRCLEY[l * 2 + 16] * 6);
                                    flag3 = false;
                                }
                            } else
                            if(BossFlag2[l12] >= 30 && BossFlag2[l12] < 39)
                            {
                                int i1 = BossFlag2[l12] - 30;
                                if(Round != 1 && BossFlag2[l12] == 30)
                                {
                                    CreateEShot(5, BossX[l12] - 4, BossY[l12] + 17, -24, 32);
                                    CreateEShot(5, BossX[l12], BossY[l12] + 15, 0, 40);
                                    CreateEShot(5, BossX[l12] + 4, BossY[l12] + 17, 24, 32);
                                }
                                if(BossAlive[2])
                                {
                                    CreateEShot(ai2[i1], BossX[l12] + 24, BossY[l12] - 16, -CIRCLEX[i1 * 2 + 16] * 6, CIRCLEY[i1 * 2 + 16] * 6);
                                    if(Round != 1)
                                        CreateEShot(ai2[i1], BossX[l12] + 24, BossY[l12] - 16, CIRCLEX[i1 * 2 + 16] * 6, CIRCLEY[i1 * 2 + 16] * 6);
                                    flag3 = false;
                                }
                            } else
                            if(BossFlag2[l12] >= 50 && BossFlag2[l12] < 60 && BossFlag2[l12] % 3 == 0)
                            {
                                if(Round == 1)
                                {
                                    CreateEShot(4, BossX[l12] - 4, BossY[l12] + 17, 0, 60);
                                    CreateEShot(4, BossX[l12], BossY[l12] + 15, 0, 60);
                                    CreateEShot(4, BossX[l12] + 4, BossY[l12] + 17, 0, 60);
                                } else
                                {
                                    CreateEShot(4, BossX[l12] - 4, BossY[l12] + 17, 0, 80);
                                    CreateEShot(4, BossX[l12], BossY[l12] + 15, 0, 80);
                                    CreateEShot(4, BossX[l12] + 4, BossY[l12] + 17, 0, 80);
                                    if(BossAlive[1])
                                    {
                                        CreateEShot(4, BossX[l12] - 27 - 4, BossY[l12] + 6 + 25, 0, 80);
                                        CreateEShot(4, (BossX[l12] - 27) + 4, BossY[l12] + 6 + 25, 0, 80);
                                    }
                                    if(BossAlive[2])
                                    {
                                        CreateEShot(4, (BossX[l12] + 27) - 4, BossY[l12] + 6 + 25, 0, 80);
                                        CreateEShot(4, BossX[l12] + 27 + 4, BossY[l12] + 6 + 25, 0, 80);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] == 65)
                                BossFlag2[l12] = 0;
                            else
                            if(BossFlag2[l12] >= 80 && BossFlag2[l12] < 89 || BossFlag2[l12] >= 100 && BossFlag2[l12] < 109)
                            {
                                if(Round != 1 && BossFlag2[l12] % 4 == 0)
                                {
                                    CreateEShot(5, BossX[l12] - 4, BossY[l12] + 17, -24, 32);
                                    CreateEShot(5, BossX[l12] - 4, BossY[l12] + 17, -12, 36);
                                    CreateEShot(5, BossX[l12], BossY[l12] + 15, 0, 40);
                                    CreateEShot(5, BossX[l12] + 4, BossY[l12] + 17, 12, 36);
                                    CreateEShot(5, BossX[l12] + 4, BossY[l12] + 17, 24, 32);
                                }
                                if(BossFlag2[l12] % 3 == 0)
                                {
                                    if(BossAlive[1])
                                    {
                                        CreateEShot(4, BossX[l12] - 27 - 4, BossY[l12] + 6 + 25, 0, 60);
                                        CreateEShot(4, (BossX[l12] - 27) + 4, BossY[l12] + 6 + 25, 0, 60);
                                    }
                                    if(BossAlive[2])
                                    {
                                        CreateEShot(4, (BossX[l12] + 27) - 4, BossY[l12] + 6 + 25, 0, 60);
                                        CreateEShot(4, BossX[l12] + 27 + 4, BossY[l12] + 6 + 25, 0, 60);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] >= 120 && BossFlag2[l12] < 160)
                            {
                                if(BossFlag2[l12] >= 120 && BossFlag2[l12] < 129)
                                {
                                    int j1 = BossFlag2[l12] - 120;
                                    CreateEShot(ai2[j1], BossX[l12], BossY[l12], CIRCLEX[j1 * 2 + 16] * 4, CIRCLEY[j1 * 2 + 16] * 4);
                                } else
                                if(BossFlag2[l12] >= 130 && BossFlag2[l12] < 138)
                                {
                                    int k1 = BossFlag2[l12] - 130;
                                    CreateEShot(ai2[k1], BossX[l12], BossY[l12], -CIRCLEX[k1 * 2 + 17] * 4, CIRCLEY[k1 * 2 + 17] * 4);
                                }
                                if(BossFlag2[l12] >= 140 && BossFlag2[l12] < 149)
                                {
                                    int l1 = BossFlag2[l12] - 140;
                                    CreateEShot(ai2[l1], BossX[l12], BossY[l12], CIRCLEX[l1 * 2 + 16] * 4, CIRCLEY[l1 * 2 + 16] * 4);
                                } else
                                if(BossFlag2[l12] >= 150 && BossFlag2[l12] < 158)
                                {
                                    int i2 = BossFlag2[l12] - 150;
                                    CreateEShot(ai2[i2], BossX[l12], BossY[l12], -CIRCLEX[i2 * 2 + 17] * 4, CIRCLEY[i2 * 2 + 17] * 4);
                                }
                                if(Round != 1 && BossFlag2[l12] % 10 == 0)
                                {
                                    if(BossAlive[1])
                                    {
                                        Path = SetTarget(BossX[l12] - 24, BossY[l12] + 16, PlayerX, PlayerY, 4);
                                        CreateEShot(5, BossX[l12] - 24, BossY[l12] + 16, Path[0], Path[1]);
                                    }
                                    if(BossAlive[2])
                                    {
                                        Path = SetTarget(BossX[l12] + 24, BossY[l12] + 16, PlayerX, PlayerY, 4);
                                        CreateEShot(5, BossX[l12] + 24, BossY[l12] + 16, Path[0], Path[1]);
                                    }
                                }
                                flag3 = false;
                            } else
                            if(BossFlag2[l12] == 170)
                                BossFlag2[l12] = 70;
                            if(BossFlag2[l12] < 70 && BossLife[0] <= BossMaxLife / 2)
                                BossFlag2[l12] = 70;
                            switch(BossFlag1[l12])
                            {
                            default:
                                break;

                            case 0: // '\0'
                                BossY[l12] += 4;
                                if(BossY[l12] >= BossYSize[l12] / 2 + 0)
                                    BossFlag1[l12] = 1;
                                break label0;

                            case 1: // '\001'
                                if(!flag3)
                                    break label0;
                                BossX[l12] -= 2;
                                if(BossX[l12] <= BossXSize[l12] / 2)
                                    BossFlag1[l12] = 2;
                                break label0;

                            case 2: // '\002'
                                if(!flag3)
                                    break label0;
                                BossX[l12] += 2;
                                if(BossX[l12] >= SCREEN_W - BossXSize[l12] / 2)
                                    BossFlag1[l12] = 1;
                                break;
                            }
                            break;
                        }
                        if(!BossAlive[l12])
                        {
                            BossBomb[l12]++;
                            if(BossBomb[l12] >= 5)
                                BossType[l12] = 0;
                            break;
                        }
                        if(l12 == 1)
                        {
                            BossX[1] = BossX[0] - 27;
                            BossY[1] = BossY[0] + 6;
                            break;
                        }
                        if(l12 == 2)
                        {
                            BossX[2] = BossX[0] + 27;
                            BossY[2] = BossY[0] + 6;
                        }
                        break;

                    case 2: // '\002'
                        if(l12 == 0)
                        {
                            if(!BossAlive[l12])
                            {
                                Muteki = 10;
                                BossAlive[1] = false;
                                BossAlive[2] = false;
                                BossBomb[l12]++;
                                if(BossBomb[l12] / 4 >= 5 && PlayerBomb <= 0)
                                {
                                    BossType[l12] = 0;
                                    GameClear = true;
                                }
                                break;
                            }
                            BossFlag2[l12]++;
                            if(BossFlag2[l12] >= 10 && BossFlag2[l12] < 30 || BossFlag2[l12] >= 80 && BossFlag2[l12] < 90)
                            {
                                byte byte0;
                                if(Round == 1)
                                    byte0 = 3;
                                else
                                    byte0 = 2;
                                if(BossFlag2[l12] % byte0 == 0)
                                {
                                    if(BossAlive[1])
                                    {
                                        int j2 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] - 36, BossY[l12] + 12, CIRCLEX[j2] * 3, CIRCLEY[j2] * 3);
                                        j2 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] - 32, BossY[l12] + 6, CIRCLEX[j2] * 3, CIRCLEY[j2] * 3);
                                        j2 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] - 28, BossY[l12] + 0, CIRCLEX[j2] * 3, CIRCLEY[j2] * 3);
                                    }
                                    if(BossAlive[2])
                                    {
                                        int k2 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] + 36, BossY[l12] + 12, CIRCLEX[k2] * 3, CIRCLEY[k2] * 3);
                                        k2 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] + 32, BossY[l12] + 6, CIRCLEX[k2] * 3, CIRCLEY[k2] * 3);
                                        k2 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] + 28, BossY[l12] + 0, CIRCLEX[k2] * 3, CIRCLEY[k2] * 3);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] >= 50 && BossFlag2[l12] < 59)
                            {
                                int l2 = BossFlag2[l12] - 50;
                                int ai3[] = {
                                    1, 1, 1, 2, 2, 2, 3, 3, 3
                                };
                                CreateEShot(ai3[l2], BossX[l12] - 9, BossY[l12] - 4, CIRCLEX[l2 * 2 + 16] * 4, CIRCLEY[l2 * 2 + 16] * 4);
                                CreateEShot(ai3[l2], BossX[l12] + 9, BossY[l12] - 4, -CIRCLEX[l2 * 2 + 16] * 4, CIRCLEY[l2 * 2 + 16] * 4);
                                flag3 = false;
                            } else
                            if(BossFlag2[l12] >= 30 && BossFlag2[l12] < 49 || BossFlag2[l12] >= 70 && BossFlag2[l12] < 89)
                            {
                                if(Round != 1 && BossFlag2[l12] % 3 == 0)
                                {
                                    if(BossAlive[1])
                                        CreateEShot(4, BossX[l12] - 22, BossY[l12] - 15, 0, 60);
                                    if(BossAlive[2])
                                        CreateEShot(4, BossX[l12] + 22, BossY[l12] - 15, 0, 60);
                                }
                            } else
                            if(BossFlag2[l12] == 95)
                                BossFlag2[l12] = 0;
                            else
                            if(BossFlag2[l12] >= 110 && BossFlag2[l12] < 150)
                            {
                                if(BossFlag2[l12] >= 120 && BossFlag2[l12] < 140 && BossFlag2[l12] % 2 == 0)
                                {
                                    for(int j15 = 2; j15 <= 12; j15 += 2)
                                    {
                                        CreateEShot(5, BossX[l12] - 9, BossY[l12] - 4, CIRCLEX[j15 + 16] * 8, CIRCLEY[j15 + 16] * 8);
                                        CreateEShot(5, BossX[l12] + 9, BossY[l12] - 4, -CIRCLEX[j15 + 16] * 8, CIRCLEY[j15 + 16] * 8);
                                    }

                                    if(Round != 1)
                                        CreateEShot(5, BossX[l12], BossY[l12] - 4, 0, 80);
                                }
                                flag3 = false;
                            } else
                            if(BossFlag2[l12] >= 160 && BossFlag2[l12] < 169)
                            {
                                int i3 = BossFlag2[l12] - 160;
                                int ai4[] = {
                                    1, 1, 1, 2, 2, 2, 3, 3, 3
                                };
                                CreateEShot(ai4[i3], BossX[l12] - 9, BossY[l12] - 4, CIRCLEX[i3 * 2 + 16] * 4, CIRCLEY[i3 * 2 + 16] * 4);
                                CreateEShot(ai4[i3], BossX[l12] + 9, BossY[l12] - 4, -CIRCLEX[i3 * 2 + 16] * 4, CIRCLEY[i3 * 2 + 16] * 4);
                                byte byte1;
                                if(Round == 1)
                                    byte1 = 3;
                                else
                                    byte1 = 2;
                                if(BossFlag2[l12] % byte1 == 0)
                                {
                                    if(BossAlive[1])
                                    {
                                        int j3 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] - 36, BossY[l12] + 12, CIRCLEX[j3] * 3, CIRCLEY[j3] * 3);
                                        j3 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] - 32, BossY[l12] + 6, CIRCLEX[j3] * 3, CIRCLEY[j3] * 3);
                                        j3 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] - 28, BossY[l12] + 0, CIRCLEX[j3] * 3, CIRCLEY[j3] * 3);
                                    }
                                    if(BossAlive[2])
                                    {
                                        int k3 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] + 36, BossY[l12] + 12, CIRCLEX[k3] * 3, CIRCLEY[k3] * 3);
                                        k3 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] + 32, BossY[l12] + 6, CIRCLEX[k3] * 3, CIRCLEY[k3] * 3);
                                        k3 = Math.abs(god.nextInt() % 16) + 16;
                                        CreateEShot(0, BossX[l12] + 28, BossY[l12] + 0, CIRCLEX[k3] * 3, CIRCLEY[k3] * 3);
                                    }
                                }
                                if(Round != 1 && BossFlag2[l12] % 3 == 0)
                                {
                                    if(BossAlive[1])
                                        CreateEShot(4, BossX[l12] - 22, BossY[l12] - 15, 0, 60);
                                    if(BossAlive[2])
                                        CreateEShot(4, BossX[l12] + 22, BossY[l12] - 15, 0, 60);
                                }
                                flag3 = false;
                            } else
                            if(BossFlag2[l12] == 180)
                                BossFlag2[l12] = 100;
                            else
                            if(BossFlag2[l12] < 100 && BossLife[l12] <= BossMaxLife >> 1)
                                BossFlag2[l12] = 100;
                            switch(BossFlag1[l12])
                            {
                            default:
                                break;

                            case 0: // '\0'
                                BossY[l12] += 4;
                                if(BossY[l12] >= (BossYSize[l12] >> 1) + 0)
                                    BossFlag1[l12] = 1;
                                break label0;

                            case 1: // '\001'
                                if(!flag3)
                                    break label0;
                                BossX[l12] -= 2;
                                if(BossX[l12] <= BossXSize[l12] >> 1)
                                    BossFlag1[l12] = 2;
                                break label0;

                            case 2: // '\002'
                                if(!flag3)
                                    break label0;
                                BossX[l12] += 2;
                                if(BossX[l12] >= SCREEN_W - (BossXSize[l12] >> 1))
                                    BossFlag1[l12] = 1;
                                break;
                            }
                            break;
                        }
                        if(!BossAlive[l12])
                        {
                            BossBomb[l12]++;
                            if(BossBomb[l12] >= 5)
                                BossType[l12] = 0;
                            break;
                        }
                        if(l12 == 1)
                        {
                            BossX[1] = BossX[0] - 25;
                            BossY[1] = BossY[0] - 6;
                            break;
                        }
                        if(l12 == 2)
                        {
                            BossX[2] = BossX[0] + 25;
                            BossY[2] = BossY[0] - 6;
                        }
                        break;

                    case 3: // '\003'
                        if(l12 == 0)
                        {
                            if(!BossAlive[l12])
                            {
                                Muteki = 10;
                                BossAlive[1] = false;
                                BossAlive[2] = false;
                                BossBomb[l12]++;
                                if(BossBomb[l12] / 4 >= 5 && PlayerBomb <= 0)
                                {
                                    BossType[l12] = 0;
                                    GameClear = true;
                                }
                                break;
                            }
                            BossFlag2[l12]++;
                            if(BossFlag2[l12] >= 10 && BossFlag2[l12] < 50)
                            {
                                byte byte2;
                                if(Round == 1)
                                    byte2 = 3;
                                else
                                    byte2 = 2;
                                if(BossFlag2[l12] % byte2 == 0)
                                {
                                    int l3 = Math.abs(god.nextInt() % 41) - 20;
                                    CreateEShot(6, BossX[l12], BossY[l12] + 10, l3, 10);
                                }
                            } else
                            if(BossFlag2[l12] >= 70 && BossFlag2[l12] < 80)
                            {
                                if(BossFlag2[l12] % 2 == 0)
                                {
                                    if(BossAlive[1])
                                    {
                                        CreateEShot(1, BossX[l12] - 24, BossY[l12] - 8, -36, 48);
                                        CreateEShot(1, BossX[l12] - 24, BossY[l12] - 8, -18, 54);
                                        CreateEShot(1, BossX[l12] - 24, BossY[l12] - 8, 0, 60);
                                        CreateEShot(1, BossX[l12] - 24, BossY[l12] - 8, 18, 54);
                                        CreateEShot(1, BossX[l12] - 24, BossY[l12] - 8, 36, 48);
                                        if(Round != 1)
                                        {
                                            CreateEShot(1, BossX[l12] - 24, BossY[l12] - 8, -42, 42);
                                            CreateEShot(1, BossX[l12] - 24, BossY[l12] - 8, 42, 42);
                                        }
                                    }
                                    if(BossAlive[2])
                                    {
                                        CreateEShot(1, BossX[l12] + 24, BossY[l12] - 8, -36, 48);
                                        CreateEShot(1, BossX[l12] + 24, BossY[l12] - 8, -18, 54);
                                        CreateEShot(1, BossX[l12] + 24, BossY[l12] - 8, 0, 60);
                                        CreateEShot(1, BossX[l12] + 24, BossY[l12] - 8, 18, 54);
                                        CreateEShot(1, BossX[l12] + 24, BossY[l12] - 8, 36, 48);
                                        if(Round != 1)
                                        {
                                            CreateEShot(1, BossX[l12] + 24, BossY[l12] - 8, -42, 42);
                                            CreateEShot(1, BossX[l12] + 24, BossY[l12] - 8, 42, 42);
                                        }
                                    }
                                }
                                flag3 = false;
                            } else
                            if(BossFlag2[l12] >= 100 && BossFlag2[l12] < 120)
                            {
                                if(BossFlag2[l12] % 5 == 0)
                                {
                                    for(int k15 = 0; k15 < 10; k15++)
                                    {
                                        int i4;
                                        int l8;
                                        if(Round == 1)
                                        {
                                            i4 = Math.abs(god.nextInt() % 5) - 2;
                                            l8 = Math.abs(god.nextInt() % 5) - 2;
                                        } else
                                        {
                                            i4 = Math.abs(god.nextInt() % 9) - 4;
                                            l8 = Math.abs(god.nextInt() % 9) - 4;
                                        }
                                        Path = SetTarget(BossX[l12], BossY[l12] + 10, PlayerX, PlayerY, 4);
                                        CreateEShot(1, BossX[l12], BossY[l12] + 10, Path[0] + i4 * 4, Path[1] + l8 * 4);
                                    }

                                }
                                flag3 = false;
                            } else
                            if(BossFlag2[l12] == 120)
                                BossFlag2[l12] = 0;
                            else
                            if(BossFlag2[l12] >= 140 && BossFlag2[l12] < 150)
                            {
                                if(BossFlag2[l12] % 2 == 0)
                                {
                                    if(BossAlive[1])
                                    {
                                        CreateEShot(1, BossX[l12] - 14, BossY[l12] + 28, -36, 48);
                                        CreateEShot(1, BossX[l12] - 14, BossY[l12] + 28, -18, 54);
                                        CreateEShot(1, BossX[l12] - 14, BossY[l12] + 28, 0, 60);
                                        CreateEShot(1, BossX[l12] - 14, BossY[l12] + 28, 18, 54);
                                        CreateEShot(1, BossX[l12] - 14, BossY[l12] + 28, 36, 48);
                                        if(Round != 1)
                                        {
                                            CreateEShot(1, BossX[l12] - 14, BossY[l12] - 8, -42, 42);
                                            CreateEShot(1, BossX[l12] - 14, BossY[l12] - 8, 42, 42);
                                        }
                                    }
                                    if(BossAlive[2])
                                    {
                                        CreateEShot(1, BossX[l12] + 14, BossY[l12] + 28, -36, 48);
                                        CreateEShot(1, BossX[l12] + 14, BossY[l12] + 28, -18, 54);
                                        CreateEShot(1, BossX[l12] + 14, BossY[l12] + 28, 0, 60);
                                        CreateEShot(1, BossX[l12] + 14, BossY[l12] + 28, 18, 54);
                                        CreateEShot(1, BossX[l12] + 14, BossY[l12] + 28, 36, 48);
                                        if(Round != 1)
                                        {
                                            CreateEShot(1, BossX[l12] + 14, BossY[l12] - 8, -42, 42);
                                            CreateEShot(1, BossX[l12] + 14, BossY[l12] - 8, 42, 42);
                                        }
                                    }
                                }
                                flag3 = false;
                            } else
                            if(BossFlag2[l12] >= 160 && BossFlag2[l12] < 320)
                            {
                                if(BossFlag2[l12] == 240)
                                {
                                    Path = SetTarget(BossX[l12], BossY[l12], PlayerX, PlayerY, 4);
                                    CreateEShot(11, BossX[l12], BossY[l12], Path[0], Path[1]);
                                }
                                int j4 = (BossFlag2[l12] - 160) % 32;
                                CreateEShot(j4 % 3 + 1, BossX[l12], BossY[l12], CIRCLEX[j4] * 4, CIRCLEY[j4] * 4);
                                CreateEShot(j4 % 3 + 1, BossX[l12], BossY[l12], -CIRCLEX[j4] * 4, CIRCLEY[j4] * 4);
                                if(Round != 1)
                                {
                                    CreateEShot(j4 % 3 + 1, BossX[l12], BossY[l12], CIRCLEX[j4] * 4, -CIRCLEY[j4] * 4);
                                    CreateEShot(j4 % 3 + 1, BossX[l12], BossY[l12], -CIRCLEX[j4] * 4, -CIRCLEY[j4] * 4);
                                    if(BossFlag2[l12] == 160)
                                    {
                                        Path = SetTarget(BossX[l12], BossY[l12], PlayerX, PlayerY, 4);
                                        CreateEShot(11, BossX[l12], BossY[l12], Path[0], Path[1]);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] == 320)
                                BossFlag2[l12] = 130;
                            if(BossFlag2[l12] < 130 && BossLife[l12] <= BossMaxLife >> 1)
                                BossFlag2[l12] = 130;
                            switch(BossFlag1[l12])
                            {
                            default:
                                break;

                            case 0: // '\0'
                                BossY[l12] += 4;
                                if(BossY[l12] >= (BossYSize[l12] >> 1) + 0)
                                    BossFlag1[l12] = 1;
                                break label0;

                            case 1: // '\001'
                                if(!flag3)
                                    break label0;
                                BossX[l12]--;
                                if(BossX[l12] <= BossXSize[l12] >> 1)
                                    BossFlag1[l12] = 2;
                                break label0;

                            case 2: // '\002'
                                if(!flag3)
                                    break label0;
                                BossX[l12]++;
                                if(BossX[l12] >= SCREEN_W - (BossXSize[l12] >> 1))
                                    BossFlag1[l12] = 1;
                                break;
                            }
                            break;
                        }
                        if(!BossAlive[l12])
                        {
                            BossBomb[l12]++;
                            if(BossBomb[l12] >= 5)
                                BossType[l12] = 0;
                            break;
                        }
                        if(l12 == 1)
                        {
                            BossX[1] = BossX[0] - 21;
                            BossY[1] = BossY[0] + 7;
                            break;
                        }
                        if(l12 == 2)
                        {
                            BossX[2] = BossX[0] + 21;
                            BossY[2] = BossY[0] + 7;
                        }
                        break;

                    case 4: // '\004'
                        if(l12 == 0)
                        {
                            if(!BossAlive[l12])
                            {
                                Muteki = 10;
                                BossAlive[1] = false;
                                BossAlive[2] = false;
                                BossAlive[3] = false;
                                BossAlive[4] = false;
                                BossBomb[l12]++;
                                if(BossBomb[l12] / 4 >= 5 && PlayerBomb <= 0)
                                {
                                    BossType[l12] = 0;
                                    GameClear = true;
                                }
                                break;
                            }
                            if(BossFlag1[l12] >= 1)
                                BossFlag2[l12]++;
                            if(BossFlag2[l12] == 20)
                            {
                                for(int l15 = 1; l15 <= 4; l15++)
                                    if(BossAlive[l15])
                                    {
                                        CreateEShot(5, BossX[l15], BossY[l15], -24, 32);
                                        CreateEShot(5, BossX[l15], BossY[l15], 0, 40);
                                        CreateEShot(5, BossX[l15], BossY[l15], 24, 32);
                                    }

                            } else
                            if(BossFlag2[l12] >= 30 && BossFlag2[l12] < 35)
                            {
                                BossX[1] -= 6;
                                BossX[2] += 6;
                                if(Round != 1)
                                {
                                    if(BossAlive[1])
                                    {
                                        Path = SetTarget(BossX[1], BossY[1], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[1], BossY[1], Path[0], Path[1]);
                                    }
                                    if(BossAlive[2])
                                    {
                                        Path = SetTarget(BossX[2], BossY[2], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[2], BossY[2], Path[0], Path[1]);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] >= 50 && BossFlag2[l12] < 70)
                            {
                                if(BossFlag2[l12] % 4 == 0)
                                {
                                    for(int i16 = 1; i16 <= 4; i16++)
                                        if(BossAlive[i16])
                                            if(BossX[i16] <= SCREEN_W / 2)
                                                CreateEShot(7, BossX[i16], BossY[i16], 48, 16);
                                            else
                                                CreateEShot(7, BossX[i16], BossY[i16], -48, 16);

                                    if(Round != 1)
                                    {
                                        CreateEShot(7, BossX[l12], BossY[l12], 48, 16);
                                        CreateEShot(7, BossX[l12], BossY[l12], -48, 16);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] == 75)
                            {
                                BossY[1] += 6;
                                BossY[2] += 6;
                                if(BossY[1] < SCREEN_H - 15)
                                {
                                    BossFlag2[l12]--;
                                } else
                                {
                                    BossY[1] = SCREEN_H - 15;
                                    BossY[2] = SCREEN_H - 15;
                                }
                                if(Round != 1)
                                {
                                    if(BossAlive[1])
                                    {
                                        Path = SetTarget(BossX[1], BossY[1], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[1], BossY[1], Path[0], Path[1]);
                                    }
                                    if(BossAlive[2])
                                    {
                                        Path = SetTarget(BossX[2], BossY[2], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[2], BossY[2], Path[0], Path[1]);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] >= 90 && BossFlag2[l12] < 110)
                            {
                                if(BossFlag2[l12] % 4 == 0)
                                {
                                    for(int j16 = 1; j16 <= 4; j16++)
                                        if(BossAlive[j16])
                                        {
                                            Path = SetTarget(BossX[j16], BossY[j16], PlayerX, PlayerY, 4);
                                            CreateEShot(1, BossX[j16], BossY[j16], Path[0], Path[1]);
                                        }

                                }
                            } else
                            if(BossFlag2[l12] == 115)
                            {
                                BossY[1] -= 6;
                                BossY[2] -= 6;
                                if(BossY[1] > BossY[0] + 45)
                                {
                                    BossFlag2[l12]--;
                                } else
                                {
                                    BossY[1] = BossY[0] + 45;
                                    BossY[2] = BossY[0] + 45;
                                }
                                if(Round != 1)
                                {
                                    if(BossAlive[1])
                                    {
                                        Path = SetTarget(BossX[1], BossY[1], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[1], BossY[1], Path[0], Path[1]);
                                    }
                                    if(BossAlive[2])
                                    {
                                        Path = SetTarget(BossX[2], BossY[2], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[2], BossY[2], Path[0], Path[1]);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] >= 130 && BossFlag2[l12] < 150)
                            {
                                if(BossFlag2[l12] % 4 == 0)
                                {
                                    for(int k16 = 1; k16 <= 2; k16++)
                                        if(BossAlive[k16])
                                        {
                                            Path = SetTarget(BossX[k16], BossY[k16], PlayerX, PlayerY, 4);
                                            CreateEShot(1, BossX[k16], BossY[k16], Path[0], Path[1]);
                                        }

                                    for(int l16 = 3; l16 <= 4; l16++)
                                        if(BossAlive[l16])
                                            if(BossX[l16] <= SCREEN_W / 2)
                                                CreateEShot(7, BossX[l16], BossY[l16], 48, 16);
                                            else
                                                CreateEShot(7, BossX[l16], BossY[l16], -48, 16);

                                }
                            } else
                            if(BossFlag2[l12] >= 150 && BossFlag2[l12] < 155)
                            {
                                BossX[1] += 6;
                                BossX[2] -= 6;
                                if(Round != 1)
                                {
                                    if(BossAlive[1])
                                    {
                                        Path = SetTarget(BossX[1], BossY[1], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[1], BossY[1], Path[0], Path[1]);
                                    }
                                    if(BossAlive[2])
                                    {
                                        Path = SetTarget(BossX[2], BossY[2], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[2], BossY[2], Path[0], Path[1]);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] >= 180 && BossFlag2[l12] < 200)
                            {
                                for(int i17 = 1; i17 <= 2; i17++)
                                    if(BossAlive[i17])
                                        if(BossFlag2[l12] % 4 == 0)
                                        {
                                            if(BossX[i17] <= SCREEN_W / 2)
                                                CreateEShot(7, BossX[i17], BossY[i17], 48, 16);
                                            else
                                                CreateEShot(7, BossX[i17], BossY[i17], -48, 16);
                                        } else
                                        if(BossFlag2[l12] % 4 == 2)
                                            if(BossX[i17] <= SCREEN_W / 2)
                                                CreateEShot(7, BossX[i17], BossY[i17], -48, 16);
                                            else
                                                CreateEShot(7, BossX[i17], BossY[i17], 48, 16);

                                for(int j17 = 3; j17 <= 4; j17++)
                                    if(BossAlive[j17] && BossFlag2[l12] % 4 == 0)
                                        CreateEShot(4, BossX[j17], BossY[j17], 0, 80);

                            } else
                            if(BossFlag2[l12] >= 200 && BossFlag2[l12] < 205)
                            {
                                BossX[3] += 6;
                                BossX[4] -= 6;
                                if(Round != 1)
                                {
                                    if(BossAlive[3])
                                    {
                                        Path = SetTarget(BossX[3], BossY[3], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[3], BossY[3], Path[0], Path[1]);
                                    }
                                    if(BossAlive[4])
                                    {
                                        Path = SetTarget(BossX[4], BossY[4], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[4], BossY[4], Path[0], Path[1]);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] >= 240 && BossFlag2[l12] < 250)
                            {
                                if(Round == 1)
                                {
                                    if(BossFlag2[l12] % 4 == 0)
                                    {
                                        Path = SetTarget(BossX[l12], BossY[l12] + 10, PlayerX, PlayerY, 6);
                                        CreateEShot(5, BossX[l12], BossY[l12] + 10, Path[0], Path[1]);
                                        for(int k17 = 1; k17 <= 4; k17++)
                                            if(BossAlive[k17])
                                            {
                                                Path = SetTarget(BossX[k17], BossY[k17], PlayerX, PlayerY, 6);
                                                CreateEShot(5, BossX[k17], BossY[k17], Path[0], Path[1]);
                                            }

                                    }
                                } else
                                if(BossFlag2[l12] % 2 == 0)
                                {
                                    int k4 = Math.abs(god.nextInt() % 16) + 16;
                                    CreateEShot(0, BossX[l12], BossY[l12] + 10, CIRCLEX[k4] * 2, CIRCLEY[k4] * 2);
                                    k4 = Math.abs(god.nextInt() % 16) + 16;
                                    CreateEShot(0, BossX[l12], BossY[l12] + 10, CIRCLEX[k4] * 2, CIRCLEY[k4] * 2);
                                    k4 = Math.abs(god.nextInt() % 16) + 16;
                                    CreateEShot(0, BossX[l12], BossY[l12] + 10, CIRCLEX[k4] * 2, CIRCLEY[k4] * 2);
                                    if(BossAlive[3])
                                    {
                                        int l4 = Math.abs(god.nextInt() % 8) + 24;
                                        CreateEShot(1, BossX[3], BossY[3] + 10, CIRCLEX[l4] * 4, CIRCLEY[l4] * 4);
                                    }
                                    if(BossAlive[4])
                                    {
                                        int i5 = Math.abs(god.nextInt() % 8) + 16;
                                        CreateEShot(1, BossX[4], BossY[4] + 10, CIRCLEX[i5] * 4, CIRCLEY[i5] * 4);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] >= 250 && BossFlag2[l12] < 255)
                            {
                                BossX[1] -= 6;
                                BossX[2] += 6;
                                if(Round != 1)
                                {
                                    if(BossAlive[1])
                                    {
                                        Path = SetTarget(BossX[1], BossY[1], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[1], BossY[1], Path[0], Path[1]);
                                    }
                                    if(BossAlive[2])
                                    {
                                        Path = SetTarget(BossX[2], BossY[2], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[2], BossY[2], Path[0], Path[1]);
                                    }
                                }
                            } else
                            if(BossFlag2[l12] >= 270 && BossFlag2[l12] < 279)
                            {
                                for(int l17 = 1; l17 <= 2; l17++)
                                    if(BossAlive[l17])
                                    {
                                        int j5 = BossFlag2[l12] - 270;
                                        int ai5[] = {
                                            1, 1, 1, 2, 2, 2, 3, 3, 3
                                        };
                                        if(l17 == 1)
                                            CreateEShot(ai5[j5], BossX[l17], BossY[l17], -CIRCLEX[j5 * 2 + 16] * 4, CIRCLEY[j5 * 2 + 16] * 4);
                                        else
                                            CreateEShot(ai5[j5], BossX[l17], BossY[l17], CIRCLEX[j5 * 2 + 16] * 4, CIRCLEY[j5 * 2 + 16] * 4);
                                    }

                                for(int i18 = 3; i18 <= 4; i18++)
                                    if(BossAlive[i18] && BossFlag2[l12] % 4 == 0)
                                        CreateEShot(4, BossX[i18], BossY[i18], 0, 80);

                            } else
                            if(BossFlag2[l12] >= 280 && BossFlag2[l12] < 285)
                            {
                                BossX[1] += 6;
                                BossX[2] -= 6;
                                BossX[3] -= 6;
                                BossX[4] += 6;
                                if(Round != 1)
                                {
                                    for(int j18 = 1; j18 <= 4; j18++)
                                        if(BossAlive[j18])
                                        {
                                            Path = SetTarget(BossX[j18], BossY[j18], PlayerX, PlayerY, 4);
                                            CreateEShot(0, BossX[j18], BossY[j18], Path[0], Path[1]);
                                        }

                                }
                            } else
                            if(BossFlag2[l12] == 290)
                            {
                                BossY[1] += 6;
                                BossY[2] += 6;
                                if(BossY[1] < SCREEN_H - 15)
                                {
                                    BossFlag2[l12]--;
                                } else
                                {
                                    BossY[1] = SCREEN_H - 15;
                                    BossY[2] = SCREEN_H - 15;
                                }
                                if(Round != 1)
                                {
                                    for(int k18 = 1; k18 <= 2; k18++)
                                        if(BossAlive[k18])
                                        {
                                            Path = SetTarget(BossX[k18], BossY[k18], PlayerX, PlayerY, 4);
                                            CreateEShot(0, BossX[k18], BossY[k18], Path[0], Path[1]);
                                        }

                                }
                            } else
                            if(BossFlag2[l12] == 292)
                            {
                                if(Round != 1)
                                    CreateEShot(8, BossX[l12], BossY[l12] + 0, 0, 60);
                            } else
                            if(BossFlag2[l12] == 295)
                            {
                                for(int l18 = 1; l18 <= 2; l18++)
                                    if(BossAlive[l18])
                                    {
                                        CreateEShot(1, BossX[l18], BossY[l18], -42, -42);
                                        CreateEShot(1, BossX[l18], BossY[l18], 42, -42);
                                        CreateEShot(1, BossX[l18], BossY[l18], -42, 42);
                                        CreateEShot(1, BossX[l18], BossY[l18], 42, 42);
                                        CreateEShot(1, BossX[l18], BossY[l18], -60, 0);
                                        CreateEShot(1, BossX[l18], BossY[l18], 0, -60);
                                        CreateEShot(1, BossX[l18], BossY[l18], 60, 0);
                                        CreateEShot(1, BossX[l18], BossY[l18], 0, 60);
                                    }

                            } else
                            if(BossFlag2[l12] == 300)
                            {
                                BossY[1] -= 6;
                                BossY[2] -= 6;
                                if(BossY[1] > BossY[0] + 45)
                                {
                                    BossFlag2[l12]--;
                                } else
                                {
                                    BossY[1] = BossY[0] + 45;
                                    BossY[2] = BossY[0] + 45;
                                }
                                if(Round != 1)
                                {
                                    for(int i19 = 1; i19 <= 2; i19++)
                                        if(BossAlive[i19])
                                        {
                                            Path = SetTarget(BossX[i19], BossY[i19], PlayerX, PlayerY, 4);
                                            CreateEShot(0, BossX[i19], BossY[i19], Path[0], Path[1]);
                                        }

                                }
                            } else
                            if(BossFlag2[l12] == 310)
                            {
                                if(Round == 1)
                                    BossFlag2[l12] = 0;
                            } else
                            if(BossFlag2[l12] == 320)
                            {
                                BossY[3] += 6;
                                BossY[4] += 6;
                                if(BossY[3] < SCREEN_H - 15)
                                {
                                    BossFlag2[l12]--;
                                } else
                                {
                                    BossY[3] = SCREEN_H - 15;
                                    BossY[4] = SCREEN_H - 15;
                                }
                                for(int j19 = 3; j19 <= 4; j19++)
                                    if(BossAlive[j19])
                                    {
                                        Path = SetTarget(BossX[j19], BossY[j19], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[j19], BossY[j19], Path[0], Path[1]);
                                    }

                            } else
                            if(BossFlag2[l12] >= 325 && BossFlag2[l12] < 375)
                            {
                                if(BossFlag2[l12] % 2 == 0)
                                {
                                    if(BossAlive[3])
                                    {
                                        CreateEShot(1, BossX[3], BossY[3], 80, 0);
                                        CreateEShot(1, BossX[3], BossY[3], 0, -80);
                                    }
                                    if(BossAlive[4])
                                    {
                                        CreateEShot(1, BossX[4], BossY[4], -80, 0);
                                        CreateEShot(1, BossX[4], BossY[4], 0, -80);
                                    }
                                }
                                if(BossFlag2[l12] % 5 == 0)
                                {
                                    for(int k19 = 1; k19 <= 2; k19++)
                                        if(BossAlive[k19])
                                        {
                                            Path = SetTarget(BossX[k19], BossY[k19], PlayerX, PlayerY, 2);
                                            CreateEShot(0, BossX[k19], BossY[k19], Path[0], Path[1]);
                                        }

                                }
                            } else
                            if(BossFlag2[l12] == 380)
                            {
                                BossY[3] -= 6;
                                BossY[4] -= 6;
                                if(BossY[3] > BossY[0] + 0)
                                {
                                    BossFlag2[l12]--;
                                } else
                                {
                                    BossY[3] = BossY[0] + 0;
                                    BossY[4] = BossY[0] + 0;
                                }
                                for(int l19 = 3; l19 <= 4; l19++)
                                    if(BossAlive[l19])
                                    {
                                        Path = SetTarget(BossX[l19], BossY[l19], PlayerX, PlayerY, 4);
                                        CreateEShot(0, BossX[l19], BossY[l19], Path[0], Path[1]);
                                    }

                            } else
                            if(BossFlag2[l12] >= 390 && BossFlag2[l12] < 420)
                            {
                                if(BossFlag2[l12] % 10 == 0)
                                {
                                    for(int i20 = 1; i20 <= 4; i20++)
                                        if(BossAlive[i20])
                                        {
                                            Path = SetTarget(BossX[i20], BossY[i20], PlayerX, PlayerY, 4);
                                            CreateEShot(10, BossX[i20], BossY[i20], Path[0], Path[1]);
                                        }

                                }
                            } else
                            if(BossFlag2[l12] == 420)
                                BossFlag2[l12] = 0;
                            else
                            if(BossFlag2[l12] >= 1020 && BossFlag2[l12] < 1120)
                            {
                                if(BossFlag2[l12] % 10 == 0)
                                {
                                    for(int j20 = 0; j20 < 32; j20 += 2)
                                        CreateEShot(1, BossX[l12], BossY[l12] + 10, CIRCLEX[j20] * 8, CIRCLEY[j20] * 8);

                                } else
                                if(BossFlag2[l12] % 10 == 5)
                                {
                                    for(int k20 = 1; k20 < 32; k20 += 2)
                                        CreateEShot(1, BossX[l12], BossY[l12] + 10, CIRCLEX[k20] * 8, CIRCLEY[k20] * 8);

                                }
                                if(BossFlag2[l12] == 1020 || BossFlag2[l12] == 1070)
                                    if(Round == 1)
                                    {
                                        Path = SetTarget(BossX[l12], BossY[l12] + 10, PlayerX, PlayerY, 4);
                                        CreateEShot(6, BossX[l12], BossY[l12] + 10, Path[0], Path[1]);
                                    } else
                                    {
                                        Path = SetTarget(BossX[l12], BossY[l12] + 10, PlayerX, PlayerY, 4);
                                        CreateEShot(11, BossX[l12], BossY[l12] + 10, Path[0], Path[1]);
                                    }
                            } else
                            if(BossFlag2[l12] == 1120)
                                BossFlag2[l12] = 1020;
                            if(!BossAlive[1] && !BossAlive[2] && !BossAlive[3] && !BossAlive[4] && BossFlag2[l12] < 1000)
                                BossFlag2[l12] = 1000;
                            switch(BossFlag1[l12])
                            {
                            case 1: // '\001'
                            default:
                                break;

                            case 0: // '\0'
                                BossY[l12] += 4;
                                if(BossY[l12] >= BossYSize[l12] / 2 + 0)
                                    BossFlag1[l12] = 1;
                                break;
                            }
                            break;
                        }
                        if(!BossAlive[l12])
                        {
                            BossBomb[l12]++;
                            if(BossBomb[l12] >= 5)
                                BossType[l12] = 0;
                            break;
                        }
                        if(BossFlag1[0] != 0)
                            break;
                        if(l12 == 1)
                        {
                            BossX[1] = BossX[0] - 20;
                            BossY[1] = BossY[0] + 35;
                            break;
                        }
                        if(l12 == 2)
                        {
                            BossX[2] = BossX[0] + 20;
                            BossY[2] = BossY[0] + 35;
                            break;
                        }
                        if(l12 == 3)
                        {
                            BossX[3] = BossX[0] - 50;
                            BossY[3] = BossY[0] + 0;
                            break;
                        }
                        if(l12 == 4)
                        {
                            BossX[4] = BossX[0] + 50;
                            BossY[4] = BossY[0] + 0;
                        }
                        break;

                    case 5: // '\005'
                        if(!BossAlive[l12])
                        {
                            Muteki = 10;
                            BossAlive[1] = false;
                            BossAlive[2] = false;
                            BossAlive[3] = false;
                            BossAlive[4] = false;
                            BossBomb[l12]++;
                            if(BossBomb[l12] / 4 >= 5 && PlayerBomb <= 0)
                            {
                                BossType[l12] = 0;
                                GameClear = true;
                            }
                            break;
                        }
                        BossFlag2[l12]++;
                        if(BossFlag2[l12] < 100)
                        {
                            int k5 = Math.abs(god.nextInt() % 16) + 16;
                            int i9 = Math.abs(god.nextInt() % 17) - 8;
                            CreateEShot(5, SCREEN_W / 2 + i9, -10, CIRCLEX[k5] * 8, CIRCLEY[k5] * 8);
                            if(Round != 1)
                            {
                                int l5 = Math.abs(god.nextInt() % 16) + 16;
                                int j9 = Math.abs(god.nextInt() % 17) - 8;
                                CreateEShot(5, SCREEN_W / 2 + j9, -10, CIRCLEX[l5] * 8, CIRCLEY[l5] * 8);
                            }
                            BossLife[l12] = BossMaxLife;
                            flag3 = false;
                        } else
                        if(BossFlag2[l12] >= 100 && BossFlag2[l12] < 130)
                        {
                            BossY[l12] += 2;
                            int i6 = Math.abs(god.nextInt() % 16) + 16;
                            int k9 = Math.abs(god.nextInt() % 17) - 8;
                            CreateEShot(5, BossX[l12] + k9, BossY[l12], CIRCLEX[i6] * 8, CIRCLEY[i6] * 8);
                            if(Round != 1)
                            {
                                int j6 = Math.abs(god.nextInt() % 16) + 16;
                                int l9 = Math.abs(god.nextInt() % 17) - 8;
                                CreateEShot(5, BossX[l12] + l9, BossY[l12], CIRCLEX[j6] * 8, CIRCLEY[j6] * 8);
                            }
                            flag3 = false;
                        } else
                        if(BossFlag2[l12] >= 150 && BossFlag2[l12] < 200)
                        {
                            int k6 = Math.abs(god.nextInt() % 16) + 16;
                            CreateEShot(0, BossX[l12], BossY[l12], CIRCLEX[k6] * 4, CIRCLEY[k6] * 4);
                            k6 = Math.abs(god.nextInt() % 16) + 16;
                            CreateEShot(0, BossX[l12], BossY[l12], CIRCLEX[k6] * 4, CIRCLEY[k6] * 4);
                            k6 = Math.abs(god.nextInt() % 16) + 16;
                            if(Round == 1)
                                CreateEShot(0, BossX[l12], BossY[l12], CIRCLEX[k6] * 4, CIRCLEY[k6] * 4);
                            else
                                CreateEShot(1, BossX[l12], BossY[l12], CIRCLEX[k6] * 4, CIRCLEY[k6] * 4);
                        } else
                        if(BossFlag2[l12] >= 205 && BossFlag2[l12] < 215)
                        {
                            CreateEShot(4, BossX[l12], BossY[l12], 0, 120);
                            flag3 = false;
                        } else
                        if(BossFlag2[l12] >= 220 && BossFlag2[l12] < 270)
                        {
                            if(BossFlag2[l12] % 6 == 0)
                                CreateEShot(7, BossX[l12], BossY[l12], 48, 16);
                            else
                            if(BossFlag2[l12] % 6 == 3)
                                CreateEShot(7, BossX[l12], BossY[l12], -48, 16);
                        } else
                        if(BossFlag2[l12] >= 300 && BossFlag2[l12] < 310)
                        {
                            CreateEShot(4, BossX[l12], BossY[l12], 0, 120);
                            flag3 = false;
                        } else
                        if(BossFlag2[l12] >= 320 && BossFlag2[l12] < 370)
                        {
                            if(BossFlag2[l12] % 10 == 0)
                            {
                                for(int l20 = 0; l20 < 32; l20 += 2)
                                    CreateEShot(1, BossX[l12], BossY[l12] + 10, CIRCLEX[l20] * 6, CIRCLEY[l20] * 6);

                            } else
                            if(BossFlag2[l12] % 10 == 5)
                            {
                                for(int i21 = 1; i21 < 32; i21 += 2)
                                    CreateEShot(1, BossX[l12], BossY[l12] + 10, CIRCLEX[i21] * 6, CIRCLEY[i21] * 6);

                            }
                        } else
                        if(BossFlag2[l12] >= 375 && BossFlag2[l12] < 385)
                        {
                            CreateEShot(4, BossX[l12], BossY[l12], 0, 120);
                            flag3 = false;
                        } else
                        if(BossFlag2[l12] >= 390 && BossFlag2[l12] < 438)
                        {
                            int l6 = (BossFlag2[l12] - 390) % 16;
                            int ai6[] = {
                                1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 
                                2, 3, 3, 3, 3, 3
                            };
                            if(BossFlag2[l12] % 32 <= 16)
                                CreateEShot(ai6[l6], BossX[l12], BossY[l12], -CIRCLEX[l6 + 16] * 4, CIRCLEY[l6 + 16] * 4);
                            else
                                CreateEShot(ai6[l6], BossX[l12], BossY[l12], CIRCLEX[l6 + 16] * 4, CIRCLEY[l6 + 16] * 4);
                            flag3 = false;
                        } else
                        if(BossFlag2[l12] >= 440 && BossFlag2[l12] < 450)
                        {
                            CreateEShot(4, BossX[l12], BossY[l12], 0, 120);
                            flag3 = false;
                        } else
                        if(BossFlag2[l12] == 450)
                            BossFlag2[l12] = 150;
                        else
                        if(BossFlag2[l12] >= 1020 && BossFlag2[l12] < 1120)
                        {
                            if(Round == 1)
                            {
                                if(BossFlag2[l12] % 10 == 0)
                                {
                                    BossTarget = true;
                                    BossTargetX = PlayerX;
                                    BossTargetY = PlayerY;
                                    BossFlag3[l12] = 1;
                                }
                                if(BossFlag2[l12] % 10 == 5)
                                {
                                    CreateEShot(8, BossTargetX, BossTargetY, 0, 0);
                                    BossTarget = false;
                                    BossFlag3[l12] = 0;
                                }
                            } else
                            {
                                if(BossFlag2[l12] % 5 == 0)
                                {
                                    BossTarget = true;
                                    BossTargetX = PlayerX;
                                    BossTargetY = PlayerY;
                                    BossFlag3[l12] = 1;
                                }
                                if(BossFlag2[l12] % 5 == 4)
                                {
                                    CreateEShot(8, BossTargetX, BossTargetY, 0, 0);
                                    BossTarget = false;
                                    BossFlag3[l12] = 0;
                                }
                            }
                            flag3 = false;
                        } else
                        if(BossFlag2[l12] >= 1130 && BossFlag2[l12] < 1230)
                        {
                            if(BossFlag2[l12] == 1130 || Round != 1 && BossFlag2[l12] % 20 == 0)
                            {
                                Path = SetTarget(BossX[l12], BossY[l12], PlayerX, PlayerY, 4);
                                CreateEShot(11, BossX[l12], BossY[l12], Path[0], Path[1]);
                            }
                            if(BossFlag2[l12] % 10 == 0)
                            {
                                for(int j21 = 0; j21 < 32; j21 += 2)
                                    CreateEShot(1, BossX[l12], BossY[l12] + 10, CIRCLEX[j21] * 4, CIRCLEY[j21] * 4);

                            }
                        } else
                        if(BossFlag2[l12] >= 1240 && BossFlag2[l12] < 1340)
                        {
                            if(BossFlag2[l12] % 20 <= 5)
                            {
                                if(Round == 1)
                                {
                                    if(BossX[l12] < SCREEN_W / 2)
                                        CreateEShot(7, BossX[l12], BossY[l12], 96, 32);
                                    else
                                        CreateEShot(7, BossX[l12], BossY[l12], -96, 32);
                                } else
                                {
                                    CreateEShot(7, BossX[l12], BossY[l12], 96, 32);
                                    CreateEShot(7, BossX[l12], BossY[l12], -96, 32);
                                }
                                flag3 = false;
                            }
                            if(BossFlag2[l12] % 5 == 0)
                            {
                                CreateEShot(4, BossX[l12] - 4, BossY[l12], 0, 100);
                                CreateEShot(4, BossX[l12] + 4, BossY[l12], 0, 100);
                            }
                        } else
                        if(BossFlag2[l12] == 1340)
                            BossFlag2[l12] = 1000;
                        else
                        if(BossFlag2[l12] >= 2020 && BossFlag2[l12] < 2120)
                        {
                            if(BossFlag2[l12] % 20 == 0)
                            {
                                BossTarget = true;
                                BossTargetX = PlayerX;
                                BossTargetY = PlayerY;
                                BossFlag3[l12] = 2;
                                if(Round != 1)
                                {
                                    for(int k21 = 0; k21 < 32; k21 += 2)
                                        CreateEShot(0, BossX[l12], BossY[l12], CIRCLEX[k21] * 4, CIRCLEY[k21] * 4);

                                }
                            }
                            if(BossFlag2[l12] % 20 == 19)
                            {
                                CreateEShot(8, BossTargetX, BossTargetY, 0, 0);
                                for(int l21 = 0; l21 < 32; l21 += 2)
                                    if(Round == 1)
                                        CreateEShot(0, BossTargetX, BossTargetY, CIRCLEX[l21] * 4, CIRCLEY[l21] * 4);
                                    else
                                        CreateEShot(1, BossTargetX, BossTargetY, CIRCLEX[l21] * 4, CIRCLEY[l21] * 4);

                                BossTarget = false;
                                BossFlag3[l12] = 0;
                            }
                            flag3 = false;
                        } else
                        if(BossFlag2[l12] >= 2140 && BossFlag2[l12] < 2240)
                        {
                            if(BossFlag2[l12] % 20 == 0)
                            {
                                Path = SetTarget(BossX[l12], BossY[l12], PlayerX, PlayerY, 6);
                                CreateEShot(10, BossX[l12], BossY[l12], Path[0], Path[1]);
                            }
                            if(BossFlag2[l12] % 20 == 10)
                            {
                                for(int i22 = 16; i22 < 32; i22 += 2)
                                    CreateEShot(0, BossX[l12], BossY[l12] + 10, CIRCLEX[i22] * 4, CIRCLEY[i22] * 4);

                                if(Round != 1)
                                {
                                    for(int j22 = 17; j22 < 32; j22 += 2)
                                        CreateEShot(1, BossX[l12], BossY[l12] + 10, CIRCLEX[j22] * 3, CIRCLEY[j22] * 3);

                                }
                            }
                        } else
                        if(BossFlag2[l12] == 2240)
                            BossFlag2[l12] = 2000;
                        else
                        if(BossFlag2[l12] >= 3020)
                            if(Round == 1)
                            {
                                if(BossFlag2[l12] >= 3020 && BossFlag2[l12] < 3220)
                                {
                                    if(BossFlag2[l12] % 2 == 0)
                                    {
                                        int i7 = Math.abs(god.nextInt() % 8);
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, CIRCLEX[i7] * 6, CIRCLEY[i7] * 6);
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, CIRCLEX[i7 + 8] * 6, CIRCLEY[i7 + 8] * 6);
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, CIRCLEX[i7 + 16] * 6, CIRCLEY[i7 + 16] * 6);
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, CIRCLEX[i7 + 24] * 6, CIRCLEY[i7 + 24] * 6);
                                    }
                                } else
                                if(BossFlag2[l12] >= 3220 && BossFlag2[l12] < 3420)
                                {
                                    int j7 = Math.abs(god.nextInt() % SCREEN_W);
                                    CreateEShot(4, j7, 0, 0, 60);
                                } else
                                if(BossFlag2[l12] == 3430)
                                {
                                    CreateEShot(10, BossX[l12], BossY[l12], 0, 60);
                                    CreateEShot(10, BossX[l12] - 12, BossY[l12], 0, 60);
                                    CreateEShot(10, BossX[l12] + 12, BossY[l12], 0, 60);
                                    CreateEShot(10, BossX[l12] - 12, BossY[l12], -42, 42);
                                    CreateEShot(10, BossX[l12] + 12, BossY[l12], 42, 42);
                                    flag3 = false;
                                } else
                                if(BossFlag2[l12] >= 3440 && BossFlag2[l12] < 3540)
                                {
                                    if(BossFlag2[l12] % 2 == 0)
                                    {
                                        int k7 = ((BossFlag2[l12] - 3440) / 2) % 8;
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, CIRCLEX[k7] * 6, CIRCLEY[k7] * 6);
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, CIRCLEX[k7 + 8] * 6, CIRCLEY[k7 + 8] * 6);
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, CIRCLEX[k7 + 16] * 6, CIRCLEY[k7 + 16] * 6);
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, CIRCLEX[k7 + 24] * 6, CIRCLEY[k7 + 24] * 6);
                                    }
                                } else
                                if(BossFlag2[l12] >= 3540 && BossFlag2[l12] < 3640)
                                {
                                    if(BossFlag2[l12] % 2 == 0)
                                    {
                                        int l7 = ((BossFlag2[l12] - 3540) / 2) % 8;
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, -CIRCLEX[l7] * 6, -CIRCLEY[l7] * 6);
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, -CIRCLEX[l7 + 8] * 6, -CIRCLEY[l7 + 8] * 6);
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, -CIRCLEX[l7 + 16] * 6, -CIRCLEY[l7 + 16] * 6);
                                        CreateEShot(9, BossX[l12], BossY[l12] + 10, -CIRCLEX[l7 + 24] * 6, -CIRCLEY[l7 + 24] * 6);
                                    }
                                } else
                                if(BossFlag2[l12] == 3640)
                                    BossFlag2[l12] = 3020;
                            } else
                            if(BossFlag2[l12] >= 3020 && BossFlag2[l12] < 3220)
                            {
                                if(BossFlag2[l12] % 20 == 0)
                                {
                                    for(int k22 = 0; k22 < 32; k22 += 2)
                                        CreateEShot(9, BossX[l12], BossY[l12], CIRCLEX[k22] * 4, CIRCLEY[k22] * 4);

                                }
                                if(BossFlag2[l12] % 20 == 10)
                                {
                                    for(int l22 = 1; l22 < 32; l22++)
                                        CreateEShot(9, BossX[l12], BossY[l12], CIRCLEX[l22] * 4, CIRCLEY[l22] * 4);

                                }
                                if(BossFlag2[l12] % 20 == 0)
                                {
                                    Path = SetTarget(BossX[l12], BossY[l12], PlayerX, PlayerY, 6);
                                    CreateEShot(10, BossX[l12], BossY[l12], Path[0], Path[1]);
                                }
                            } else
                            if(BossFlag2[l12] >= 3240 && BossFlag2[l12] < 3304)
                            {
                                int i8 = BossFlag2[l12] - 3240;
                                CreateEShot(9, BossX[l12], BossY[l12], CIRCLEX[i8 % 32] * 6, CIRCLEY[i8 % 32] * 6);
                                CreateEShot(9, BossX[l12], BossY[l12], -CIRCLEX[i8 % 32] * 6, CIRCLEY[i8 % 32] * 6);
                                CreateEShot(9, BossX[l12], BossY[l12], CIRCLEX[i8 % 32] * 6, -CIRCLEY[i8 % 32] * 6);
                                CreateEShot(9, BossX[l12], BossY[l12], -CIRCLEX[i8 % 32] * 6, -CIRCLEY[i8 % 32] * 6);
                                CreateEShot(9, BossX[l12], BossY[l12], CIRCLEX[(i8 + 8) % 32] * 6, CIRCLEY[(i8 + 8) % 32] * 6);
                                CreateEShot(9, BossX[l12], BossY[l12], -CIRCLEX[(i8 + 8) % 32] * 6, CIRCLEY[(i8 + 8) % 32] * 6);
                                CreateEShot(9, BossX[l12], BossY[l12], CIRCLEX[(i8 + 8) % 32] * 6, -CIRCLEY[(i8 + 8) % 32] * 6);
                                CreateEShot(9, BossX[l12], BossY[l12], -CIRCLEX[(i8 + 8) % 32] * 6, -CIRCLEY[(i8 + 8) % 32] * 6);
                                if(BossFlag2[l12] == 3303)
                                    BossFlag2[l12] = 3239;
                            } else
                            if(BossFlag2[l12] == 3310)
                                BossFlag2[l12] = 3020;
                        if(BossLife[l12] < (BossMaxLife / 4) * 3 && BossFlag2[l12] < 1000)
                        {
                            BossFlag2[l12] = 1000;
                            BossFlag3[l12] = 0;
                            BossTarget = false;
                        }
                        if(BossLife[l12] < (BossMaxLife / 4) * 2 && BossFlag2[l12] < 2000)
                        {
                            BossFlag2[l12] = 2000;
                            BossFlag3[l12] = 0;
                            BossTarget = false;
                        }
                        if(BossLife[l12] < (BossMaxLife / 4) * 1 && BossFlag2[l12] < 3000)
                        {
                            BossFlag2[l12] = 3000;
                            BossFlag3[l12] = 0;
                            BossTarget = false;
                        }
                        switch(BossFlag1[l12])
                        {
                        default:
                            break label0;

                        case 0: // '\0'
                            if(!flag3)
                                break label0;
                            if(BossFlag2[l12] >= 3000)
                                BossX[l12]--;
                            else
                                BossX[l12] -= 4;
                            if(BossX[l12] <= BossXSize[l12] / 2)
                                BossFlag1[l12] = 1;
                            break label0;

                        case 1: // '\001'
                            break;
                        }
                        if(!flag3)
                            break;
                        if(BossFlag2[l12] >= 3000)
                            BossX[l12]++;
                        else
                            BossX[l12] += 4;
                        if(BossX[l12] >= SCREEN_W - BossXSize[l12] / 2)
                            BossFlag1[l12] = 0;
                        break;
                    }

            } else
            {
                for(int i13 = 0; i13 < 20; i13++)
                    if(EnemyType[i13] != 0 && !EnemyAlive[i13])
                    {
                        EnemyBomb[i13]++;
                        if(EnemyBomb[i13] >= 5)
                            EnemyType[i13] = 0;
                    } else
                    {
                        switch(EnemyType[i13])
                        {
                        case 0: // '\0'
                            break;

                        case 1: // '\001'
                            EnemyY[i13] += 4;
                            if(Round != 1)
                            {
                                EnemyFlag2[i13]++;
                                if(EnemyFlag2[i13] >= 10)
                                {
                                    Path = SetTarget(EnemyX[i13], EnemyY[i13], PlayerX, PlayerY, 4);
                                    CreateEShot(0, EnemyX[i13], EnemyY[i13], Path[0], Path[1]);
                                    EnemyFlag2[i13] = -10;
                                }
                            }
                            if(EnemyY[i13] >= SCREEN_H + 16)
                                EnemyEscape(i13);
                            break;

                        case 2: // '\002'
                            byte byte3;
                            if(Round == 1)
                                byte3 = 4;
                            else
                                byte3 = 6;
                            EnemyFlag1[i13]++;
                            if(EnemyFlag1[i13] < 10)
                                EnemyX[i13] -= byte3;
                            else
                            if(EnemyFlag1[i13] < 20)
                                EnemyX[i13] += byte3;
                            else
                                EnemyFlag1[i13] = 0;
                            EnemyY[i13] += byte3;
                            if(EnemyY[i13] >= SCREEN_H + 16)
                                EnemyEscape(i13);
                            break;

                        case 3: // '\003'
                            if(EnemyFlag1[i13] == 0)
                                EnemyX[i13] -= 6;
                            else
                                EnemyX[i13] += 6;
                            EnemyFlag2[i13]++;
                            if(Round == 1)
                            {
                                if(EnemyFlag2[i13] >= 15)
                                {
                                    Path = SetTarget(EnemyX[i13], EnemyY[i13], PlayerX, PlayerY, 4);
                                    CreateEShot(0, EnemyX[i13], EnemyY[i13], Path[0], Path[1]);
                                    EnemyFlag2[i13] = 0;
                                }
                            } else
                            if(EnemyFlag2[i13] >= 5)
                            {
                                Path = SetTarget(EnemyX[i13], EnemyY[i13], PlayerX, PlayerY, 4);
                                CreateEShot(0, EnemyX[i13], EnemyY[i13], Path[0], Path[1]);
                                EnemyFlag2[i13] = 0;
                            }
                            if(EnemyFlag1[i13] == 0 && EnemyX[i13] <= -16)
                                EnemyEscape(i13);
                            if(EnemyFlag1[i13] != 0 && EnemyX[i13] >= SCREEN_W + 16)
                                EnemyEscape(i13);
                            break;

                        case 4: // '\004'
                            if(EnemyFlag1[i13] == 0)
                            {
                                EnemyY[i13] += 6;
                                if(EnemyY[i13] >= SCREEN_H / 2)
                                    EnemyFlag1[i13] = 1;
                            } else
                            if(EnemyFlag1[i13] == 1)
                                EnemyY[i13]++;
                            else
                            if(EnemyFlag1[i13] == 2)
                                EnemyY[i13] -= 6;
                            if(EnemyFlag1[i13] == 1)
                            {
                                EnemyFlag2[i13]++;
                                if(EnemyFlag2[i13] % 10 == 0)
                                    if(Round == 1)
                                    {
                                        Path = SetTarget(EnemyX[i13], EnemyY[i13], PlayerX, PlayerY, 4);
                                        CreateEShot(0, EnemyX[i13], EnemyY[i13], Path[0], Path[1]);
                                    } else
                                    {
                                        Path = SetTarget(EnemyX[i13], EnemyY[i13], PlayerX, PlayerY, 5);
                                        CreateEShot(1, EnemyX[i13], EnemyY[i13], Path[0], Path[1]);
                                    }
                                if(EnemyFlag2[i13] >= 30)
                                    EnemyFlag1[i13] = 2;
                            }
                            if(EnemyY[i13] < -16)
                                EnemyEscape(i13);
                            if(EnemyY[i13] >= SCREEN_H + 16)
                                EnemyEscape(i13);
                            break;

                        case 5: // '\005'
                            EnemyY[i13]++;
                            if(EnemyY[i13] >= SCREEN_H + 16)
                                EnemyEscape(i13);
                            EnemyFlag1[i13]++;
                            if(EnemyFlag1[i13] == 20)
                            {
                                if(Round == 1)
                                {
                                    CreateEShot(0, EnemyX[i13], EnemyY[i13], 0, 40);
                                    CreateEShot(0, EnemyX[i13], EnemyY[i13], -30, 30);
                                    CreateEShot(0, EnemyX[i13], EnemyY[i13], 30, 30);
                                } else
                                {
                                    CreateEShot(1, EnemyX[i13], EnemyY[i13], 0, 40);
                                    CreateEShot(1, EnemyX[i13], EnemyY[i13], -30, 30);
                                    CreateEShot(1, EnemyX[i13], EnemyY[i13], 30, 30);
                                    CreateEShot(0, EnemyX[i13], EnemyY[i13], 0, 60);
                                    CreateEShot(0, EnemyX[i13], EnemyY[i13], -45, 45);
                                    CreateEShot(0, EnemyX[i13], EnemyY[i13], 45, 45);
                                }
                                break;
                            }
                            if(EnemyFlag1[i13] >= 30 && EnemyFlag1[i13] < 40)
                            {
                                if((EnemyFlag1[i13] & 1) != 0)
                                    break;
                                if(Round == 1)
                                {
                                    Path = SetTarget(EnemyX[i13], EnemyY[i13], PlayerX, PlayerY, 3);
                                    CreateEShot(1, EnemyX[i13], EnemyY[i13], Path[0], Path[1]);
                                } else
                                {
                                    Path = SetTarget(EnemyX[i13], EnemyY[i13], PlayerX, PlayerY, 5);
                                    CreateEShot(1, EnemyX[i13], EnemyY[i13], Path[0], Path[1]);
                                }
                                break;
                            }
                            if(EnemyFlag1[i13] == 40)
                                EnemyFlag1[i13] = 0;
                            break;

                        case 6: // '\006'
                            if(EnemyFlag1[i13] == 0)
                            {
                                EnemyY[i13] += 6;
                                if(EnemyY[i13] >= EnemyYSize[i13] / 2)
                                    EnemyFlag1[i13] = 1;
                            } else
                            if(EnemyFlag1[i13] == 5)
                            {
                                EnemyY[i13] -= 2;
                                if(EnemyY[i13] <= -EnemyYSize[i13] / 2)
                                    EnemyEscape(i13);
                            } else
                            if((EnemyFlag1[i13] & 1) == 1)
                            {
                                EnemyX[i13] += 2;
                                if(EnemyX[i13] >= SCREEN_W - EnemyXSize[i13] / 2)
                                    EnemyFlag1[i13]++;
                            } else
                            if((EnemyFlag1[i13] & 1) == 0)
                            {
                                EnemyX[i13] -= 2;
                                if(EnemyX[i13] <= 0 + EnemyXSize[i13] / 2)
                                    EnemyFlag1[i13]++;
                            }
                            EnemyFlag2[i13]++;
                            if(Round == 1)
                            {
                                if(EnemyFlag2[i13] >= 22)
                                {
                                    EnemyFlag2[i13] = 0;
                                    CreateEShot(5, EnemyX[i13], EnemyY[i13], -24, 32);
                                    CreateEShot(5, EnemyX[i13], EnemyY[i13], -12, 36);
                                    CreateEShot(5, EnemyX[i13], EnemyY[i13], 0, 40);
                                    CreateEShot(5, EnemyX[i13], EnemyY[i13], 12, 36);
                                    CreateEShot(5, EnemyX[i13], EnemyY[i13], 24, 32);
                                }
                                break;
                            }
                            if(EnemyFlag2[i13] >= 10 && EnemyFlag2[i13] < 20)
                            {
                                if(EnemyFlag2[i13] % 3 == 0)
                                {
                                    CreateEShot(5, EnemyX[i13], EnemyY[i13], -48, 64);
                                    CreateEShot(5, EnemyX[i13], EnemyY[i13], -24, 72);
                                    CreateEShot(5, EnemyX[i13], EnemyY[i13], 0, 80);
                                    CreateEShot(5, EnemyX[i13], EnemyY[i13], 24, 72);
                                    CreateEShot(5, EnemyX[i13], EnemyY[i13], 48, 64);
                                }
                                break;
                            }
                            if(EnemyFlag2[i13] == 20)
                                EnemyFlag2[i13] = 0;
                            break;

                        case 7: // '\007'
                            if(EnemyFlag1[i13] == 0)
                            {
                                EnemyY[i13] += 2;
                                if(EnemyY[i13] >= EnemyYSize[i13] / 2)
                                {
                                    EnemyFlag1[i13] = 1;
                                    if(Round == 1)
                                        EnemyFlag2[i13] = 0;
                                    else
                                        EnemyFlag2[i13] = -50;
                                }
                            } else
                            if(EnemyFlag1[i13] == 1)
                            {
                                EnemyFlag2[i13]++;
                                if(EnemyFlag2[i13] >= -46 && EnemyFlag2[i13] < -30)
                                {
                                    int j8 = EnemyFlag2[i13] + 46;
                                    int ai7[] = {
                                        1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 
                                        2, 3, 3, 3, 3, 3
                                    };
                                    if(EnemyX[i13] < SCREEN_W / 2)
                                        CreateEShot(ai7[j8], EnemyX[i13], EnemyY[i13], CIRCLEX[j8 + 16] * 4, CIRCLEY[j8 + 16] * 4);
                                    else
                                        CreateEShot(ai7[j8], EnemyX[i13], EnemyY[i13], -CIRCLEX[j8 + 16] * 4, CIRCLEY[j8 + 16] * 4);
                                } else
                                if(EnemyFlag2[i13] >= -10 && EnemyFlag2[i13] < 0)
                                {
                                    if(EnemyFlag2[i13] % 3 == 0)
                                    {
                                        CreateEShot(4, EnemyX[i13] - 15, EnemyY[i13] + 24, 0, 60);
                                        CreateEShot(4, EnemyX[i13] + 15, EnemyY[i13] + 24, 0, 60);
                                    }
                                } else
                                if(EnemyFlag2[i13] >= 10 && EnemyFlag2[i13] < 26)
                                {
                                    int k8 = EnemyFlag2[i13] - 10;
                                    int ai8[] = {
                                        1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 
                                        2, 3, 3, 3, 3, 3
                                    };
                                    if(EnemyX[i13] < SCREEN_W / 2)
                                        CreateEShot(ai8[k8], EnemyX[i13], EnemyY[i13], CIRCLEX[k8 + 16] * 4, CIRCLEY[k8 + 16] * 4);
                                    else
                                        CreateEShot(ai8[k8], EnemyX[i13], EnemyY[i13], -CIRCLEX[k8 + 16] * 4, CIRCLEY[k8 + 16] * 4);
                                } else
                                if(EnemyFlag2[i13] >= 40 && EnemyFlag2[i13] < 50)
                                {
                                    if(Round == 1)
                                    {
                                        if(EnemyFlag2[i13] % 3 == 0)
                                        {
                                            CreateEShot(4, EnemyX[i13] - 15, EnemyY[i13] + 24, 0, 60);
                                            CreateEShot(4, EnemyX[i13] + 15, EnemyY[i13] + 24, 0, 60);
                                        }
                                    } else
                                    if(EnemyFlag2[i13] % 5 == 0)
                                    {
                                        CreateEShot(10, EnemyX[i13], EnemyY[i13] + 12, 0, 60);
                                        CreateEShot(10, EnemyX[i13] - 15, EnemyY[i13] + 24, -42, 42);
                                        CreateEShot(10, EnemyX[i13] + 15, EnemyY[i13] + 24, 42, 42);
                                    }
                                } else
                                if(EnemyFlag2[i13] >= 60)
                                    EnemyFlag1[i13] = 2;
                            } else
                            if(EnemyFlag1[i13] == 2)
                                EnemyY[i13] += 2;
                            if(EnemyY[i13] >= SCREEN_H + 16)
                                EnemyEscape(i13);
                            break;

                        default:
                            EnemyY[i13] += 6;
                            if(EnemyY[i13] >= SCREEN_H + 16)
                                EnemyEscape(i13);
                            break;
                        }
                    }

            }
            if(PlayerAlive)
            {
                if(FlgBoss == 2)
                {
                    for(int j13 = 0; j13 < 5; j13++)
                        if(BossType[j13] != 0 && BossAlive[j13] && Math.abs(PlayerX - BossX[j13]) <= BossXSize[j13] / 2 && Math.abs(PlayerY - BossY[j13]) <= BossYSize[j13] / 2)
                            PlayerDead();

                } else
                {
                    for(int k13 = 0; k13 < 20; k13++)
                        if(EnemyType[k13] != 0 && EnemyAlive[k13] && Math.abs(PlayerX - EnemyX[k13]) <= EnemyXSize[k13] / 2 && Math.abs(PlayerY - EnemyY[k13]) <= EnemyYSize[k13] / 2)
                            PlayerDead();

                }
                int ai1[] = {
                    2, 4, 4, 4, 2, 4, 6, 4, 8, 4, 
                    4, 6
                };
                int ai9[] = {
                    2, 4, 4, 4, 6, 4, 6, 4, 8, 4, 
                    4, 6
                };
                for(int l13 = 0; l13 < 100; l13++)
                    if(EShotAlive[l13] && Math.abs(PlayerX - EShotX[l13] / 10) <= ai1[EShotType[l13]] && Math.abs(PlayerY - EShotY[l13] / 10) <= ai9[EShotType[l13]])
                    {
                        if(EShotType[l13] != 8)
                            EShotAlive[l13] = false;
                        PlayerDead();
                    }

            }
            switch(FlgBoss)
            {
            case 0: // '\0'
                EnemyWait--;
                if(EnemyWait <= 0)
                {
                    EncountEnemy();
                    EnemyWait = 10;
                }
                break;

            case 1: // '\001'
                BossWait++;
                if(BossWait >= 20)
                {
                    FlgBoss = 2;
                    CreateBoss(Stage);
                }
                break;
            }
            if(!FlgGameOver)
            {
                if(!GameClear)
                    break;
                ClearWait++;
                if(ClearWait >= 10)
                    ChangeMain(12);
                break;
            }
            OverCount++;
            if(OverCount >= 50)
            {
                ChangeMain(2);
                DataSave();
            }
            break;

        case 13: // '\r'
            for(int i14 = 0; i14 < 10; i14++)
                if(StarY[i14] >= SCREEN_H + 10)
                {
                    StarType[i14] = i14 % 4;
                    StarX[i14] = Math.abs(god.nextInt() % SCREEN_W);
                    StarY[i14] = -Math.abs(god.nextInt() % 100) - 10;
                } else
                {
                    StarY[i14] += i14 / 4 + 5;
                }

            break;
        }
    }

    public void EncountEnemy()
    {
        int ai[] = null;
        switch(Stage)
        {
        case 1: // '\001'
            ai = (new int[] {
                0, 0, 3, 2, 1, 3, 2, 1, 5, 5, 
                5, 0, 6, 3, 7, 2, 8, 1, 9, 0, 
                12, 12, 12, 0, 0, 0, 0, 0, 0, 0, 
                0, 0, 0
            });
            break;

        case 2: // '\002'
            ai = (new int[] {
                0, 0, 3, 3, 3, 9, 9, 9, 10, 11, 
                12, 10, 11, 12, 0, 13, 0, 3, 2, 1, 
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            });
            break;

        case 3: // '\003'
            ai = (new int[] {
                0, 0, 12, 11, 10, 12, 11, 10, 0, 13, 
                6, 7, 8, 9, 14, 6, 7, 8, 9, 0, 
                16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
                0
            });
            break;

        case 4: // '\004'
            ai = (new int[] {
                0, 0, 14, 1, 1, 1, 10, 12, 10, 12, 
                6, 7, 8, 9, 13, 4, 5, 4, 5, 0, 
                16, 14, 3, 3, 2, 2, 1, 0, 18, 0, 
                1, 2, 2, 3, 3, 0, 0, 0, 0, 0, 
                0, 0, 0, 0, 0
            });
            break;

        case 5: // '\005'
            ai = (new int[] {
                0, 0, 13, 0, 14, 0, 15, 0, 16, 0, 
                0, 15, 16, 0, 0, 18, 0, 0, 13, 17, 
                0, 0, 0, 19, 0, 0, 0, 0, 0, 0, 
                0, 0, 0, 0
            });
            break;

        default:
            ai = null;
            break;
        }
        CreateEnemyPat(ai[MapScroll]);
        MapScroll++;
        if(MapScroll >= ai.length)
        {
            FlgBoss = 1;
            for(int i = 0; i < 20; i++)
                EnemyAlive[i] = false;

        }
    }

    public void StageClear()
    {
        Stage++;
        if(Stage > 5)
            Stage = 1;
        MakeGame();
    }

    public void EnemyEscape(int i)
    {
        EnemyType[i] = 0;
        BonusEscape++;
    }

    public void PlusScore(int i)
    {
        Score += i;
        if(Score > 0x5f5e0ff)
            Score = 0x5f5e0ff;
        if(HiScore < Score)
            HiScore = Score;
        try
        {
            if(ExtendPoint < EXTEND.length && Score >= EXTEND[ExtendPoint])
            {
                ExtendPoint++;
                Rest++;
                ExtendCount = 10;
            }
        }
        catch(Exception exception) { }
    }

    public void PlayerDead()
    {
        if(!PlayerAlive)
            return;
        if(Muteki > 0)
            return;
        MyLife--;
        if(MyLife > 0)
        {
            Muteki = 4;
        } else
        {
            PlayerAlive = false;
            PAttackMode = 0;
            PAttackMain = 0;
        }
    }

    public void CreateMyShot(int i, int j, int k)
    {
        for(int l = 0; l < 30; l++)
        {
            if(MyShotAlive[l])
                continue;
            MyShotAlive[l] = true;
            MyShotType[l] = i;
            MyShotX[l] = PlayerX;
            MyShotY[l] = PlayerY;
            MyShotMX[l] = j;
            MyShotMY[l] = k;
            break;
        }

    }

    public void CreateEShot(int i, int j, int k, int l, int i1)
    {
        for(int j1 = 0; j1 < 100; j1++)
        {
            if(EShotAlive[j1])
                continue;
            EShotAlive[j1] = true;
            EShotType[j1] = i;
            EShotX[j1] = j * 10;
            EShotY[j1] = k * 10;
            EShotMX[j1] = l;
            EShotMY[j1] = i1;
            EShotFlag[j1] = 0;
            break;
        }

    }

    public void CreateEnemyPat(int i)
    {
        switch(i)
        {
        case 1: // '\001'
            CreateEnemy(1, SCREEN_W / 2 - 16, -8);
            CreateEnemy(1, SCREEN_W / 2 + 16, -8);
            CreateEnemy(1, SCREEN_W / 2 - 16, -24);
            CreateEnemy(1, SCREEN_W / 2 + 16, -24);
            CreateEnemy(1, SCREEN_W / 2 - 16, -40);
            CreateEnemy(1, SCREEN_W / 2 + 16, -40);
            break;

        case 2: // '\002'
            CreateEnemy(1, SCREEN_W / 2 - 32, -8);
            CreateEnemy(1, SCREEN_W / 2 + 32, -8);
            CreateEnemy(1, SCREEN_W / 2 - 32, -24);
            CreateEnemy(1, SCREEN_W / 2 + 32, -24);
            CreateEnemy(1, SCREEN_W / 2 - 32, -40);
            CreateEnemy(1, SCREEN_W / 2 + 32, -40);
            break;

        case 3: // '\003'
            CreateEnemy(1, SCREEN_W / 2 - 48, -8);
            CreateEnemy(1, SCREEN_W / 2 + 48, -8);
            CreateEnemy(1, SCREEN_W / 2 - 48, -24);
            CreateEnemy(1, SCREEN_W / 2 + 48, -24);
            CreateEnemy(1, SCREEN_W / 2 - 48, -40);
            CreateEnemy(1, SCREEN_W / 2 + 48, -40);
            break;

        case 4: // '\004'
            CreateEnemy(2, SCREEN_W / 2 - 16, -8);
            CreateEnemy(2, SCREEN_W / 2 + 16, -8);
            CreateEnemy(2, SCREEN_W / 2 - 32, -8);
            CreateEnemy(2, SCREEN_W / 2 + 32, -8);
            CreateEnemy(2, SCREEN_W / 2 - 48, -8);
            CreateEnemy(2, SCREEN_W / 2 + 48, -8);
            break;

        case 5: // '\005'
            CreateEnemy(2, SCREEN_W / 2 - 32, -8);
            CreateEnemy(2, SCREEN_W / 2 + 32, -8);
            CreateEnemy(2, SCREEN_W / 2 - 32, -24);
            CreateEnemy(2, SCREEN_W / 2 + 32, -24);
            CreateEnemy(2, SCREEN_W / 2 - 32, -40);
            CreateEnemy(2, SCREEN_W / 2 + 32, -40);
            break;

        case 6: // '\006'
            CreateEnemy(3, -8, 16);
            CreateEnemy(3, SCREEN_W + 8, 16);
            CreateEnemy(3, -24, 16);
            CreateEnemy(3, SCREEN_W + 24, 16);
            CreateEnemy(3, -40, 16);
            CreateEnemy(3, SCREEN_W + 40, 16);
            break;

        case 7: // '\007'
            CreateEnemy(3, -8, 32);
            CreateEnemy(3, SCREEN_W + 8, 32);
            CreateEnemy(3, -24, 32);
            CreateEnemy(3, SCREEN_W + 24, 32);
            CreateEnemy(3, -40, 32);
            CreateEnemy(3, SCREEN_W + 40, 32);
            break;

        case 8: // '\b'
            CreateEnemy(3, -8, 48);
            CreateEnemy(3, SCREEN_W + 8, 48);
            CreateEnemy(3, -24, 48);
            CreateEnemy(3, SCREEN_W + 24, 48);
            CreateEnemy(3, -40, 48);
            CreateEnemy(3, SCREEN_W + 40, 48);
            break;

        case 9: // '\t'
            CreateEnemy(3, -8, 64);
            CreateEnemy(3, SCREEN_W + 8, 64);
            CreateEnemy(3, -24, 64);
            CreateEnemy(3, SCREEN_W + 24, 64);
            CreateEnemy(3, -40, 64);
            CreateEnemy(3, SCREEN_W + 40, 64);
            break;

        case 10: // '\n'
            CreateEnemy(4, SCREEN_W / 2 - 16, -8);
            CreateEnemy(4, SCREEN_W / 2 + 16, -8);
            break;

        case 11: // '\013'
            CreateEnemy(4, SCREEN_W / 2 - 32, -8);
            CreateEnemy(4, SCREEN_W / 2 + 32, -8);
            break;

        case 12: // '\f'
            CreateEnemy(4, SCREEN_W / 2 - 48, -8);
            CreateEnemy(4, SCREEN_W / 2 + 48, -8);
            break;

        case 13: // '\r'
            CreateEnemy(5, SCREEN_W / 2, -8);
            break;

        case 14: // '\016'
            CreateEnemy(5, SCREEN_W / 2 - 32, -8);
            CreateEnemy(5, SCREEN_W / 2 + 32, -8);
            break;

        case 15: // '\017'
            CreateEnemy(5, SCREEN_W / 2, -8);
            CreateEnemy(5, SCREEN_W / 2 - 48, -8);
            CreateEnemy(5, SCREEN_W / 2 + 48, -8);
            break;

        case 16: // '\020'
            CreateEnemy(6, SCREEN_W / 2, -32);
            break;

        case 17: // '\021'
            CreateEnemy(6, SCREEN_W / 2 - 48, -32);
            CreateEnemy(6, SCREEN_W / 2 + 48, -32);
            break;

        case 18: // '\022'
            CreateEnemy(7, SCREEN_W / 2, -32);
            break;

        case 19: // '\023'
            CreateEnemy(7, SCREEN_W / 2 - 48, -32);
            CreateEnemy(7, SCREEN_W / 2 + 48, -32);
            break;
        }
    }

    public void CreateEnemy(int i, int j, int k)
    {
        int ai[] = {
            0, 16, 16, 16, 16, 32, 40, 38
        };
        int ai1[] = {
            0, 16, 16, 16, 16, 16, 48, 46
        };
        int ai2[] = {
            0, 100, 100, 100, 300, 1000, 2000, 5000
        };
        int ai3[] = {
            0, 1, 1, 3, 3, 50, 120, 150
        };
        for(int l = 0; l < 20; l++)
        {
            if(EnemyType[l] != 0)
                continue;
            BonusEnemy++;
            EnemyType[l] = i;
            EnemyAlive[l] = true;
            EnemyBomb[l] = 0;
            EnemyXSize[l] = ai[i];
            EnemyYSize[l] = ai1[i];
            EnemyFlag1[l] = 0;
            EnemyFlag2[l] = 0;
            EnemyLife[l] = 1;
            EnemyPoint[l] = ai2[i];
            EnemyX[l] = j;
            EnemyY[l] = k;
            EnemyLife[l] = ai3[i];
            switch(i)
            {
            case 2: // '\002'
                if(EnemyX[l] <= SCREEN_W / 2)
                    EnemyFlag1[l] = 10;
                else
                    EnemyFlag1[l] = 0;
                break;

            case 3: // '\003'
                if(EnemyX[l] <= SCREEN_W / 2)
                    EnemyFlag1[l] = 1;
                else
                    EnemyFlag1[l] = 0;
                break;
            }
            break;
        }

    }

    public void CreateBoss(int i)
    {
        int ai[] = {
            0, 20000, 30000, 30000, 50000, 0x186a0
        };
        int ai1[] = {
            0, 5000, 5000, 5000, 5000, 5000
        };
        for(int j = 0; j < 5; j++)
        {
            BossAlive[j] = false;
            BossType[j] = 0;
            BossX[j] = 0;
            BossY[j] = 0;
            BossXSize[j] = 0;
            BossYSize[j] = 0;
            BossLife[j] = 0;
            BossFlag1[j] = 0;
            BossFlag2[j] = 0;
            BossFlag3[j] = 0;
            BossFlag4[j] = 0;
            BossPoint[j] = 0;
        }

        BossPoint[0] = ai[i];
        for(int k = 1; k < 5; k++)
            BossPoint[k] = ai1[i];

        switch(i)
        {
        default:
            break;

        case 1: // '\001'
            BossAlive[0] = true;
            BossType[0] = 1;
            BossX[0] = SCREEN_W / 2;
            BossY[0] = -72;
            BossXSize[0] = 42;
            BossYSize[0] = 59;
            BossLife[0] = 700;
            BossFlag1[0] = 0;
            BossFlag2[0] = 0;
            for(int l = 1; l <= 2; l++)
            {
                BossAlive[l] = true;
                BossType[l] = 1;
                BossXSize[l] = 19;
                BossYSize[l] = 58;
                BossLife[l] = 300;
                BossFlag1[l] = 0;
                BossFlag2[l] = 0;
            }

            BossX[1] = BossX[0] - 27;
            BossY[1] = BossY[0] + 6;
            BossX[2] = BossX[0] + 27;
            BossY[2] = BossY[0] + 6;
            break;

        case 2: // '\002'
            BossAlive[0] = true;
            BossType[0] = 2;
            BossX[0] = SCREEN_W / 2;
            BossY[0] = -72;
            BossXSize[0] = 48;
            BossYSize[0] = 54;
            BossLife[0] = 1000;
            BossFlag1[0] = 0;
            BossFlag2[0] = 0;
            for(int i1 = 1; i1 <= 2; i1++)
            {
                BossAlive[i1] = true;
                BossType[i1] = 2;
                BossXSize[i1] = 23;
                BossYSize[i1] = 45;
                BossLife[i1] = 400;
                BossFlag1[i1] = 0;
                BossFlag2[i1] = 0;
            }

            BossX[1] = BossX[0] - 25;
            BossY[1] = BossY[0] - 6;
            BossX[2] = BossX[0] + 25;
            BossY[2] = BossY[0] - 6;
            break;

        case 3: // '\003'
            BossAlive[0] = true;
            BossType[0] = 3;
            BossX[0] = SCREEN_W / 2;
            BossY[0] = -72;
            BossXSize[0] = 22;
            BossYSize[0] = 45;
            BossLife[0] = 800;
            BossFlag1[0] = 0;
            BossFlag2[0] = 0;
            for(int j1 = 1; j1 <= 2; j1++)
            {
                BossAlive[j1] = true;
                BossType[j1] = 3;
                BossXSize[j1] = 28;
                BossYSize[j1] = 56;
                BossLife[j1] = 500;
                BossFlag1[j1] = 0;
                BossFlag2[j1] = 0;
            }

            BossX[1] = BossX[0] - 21;
            BossY[1] = BossY[0] + 7;
            BossX[2] = BossX[0] + 21;
            BossY[2] = BossY[0] + 7;
            break;

        case 4: // '\004'
            BossAlive[0] = true;
            BossType[0] = 4;
            BossX[0] = SCREEN_W / 2;
            BossY[0] = -72;
            BossXSize[0] = 40;
            BossYSize[0] = 40;
            BossLife[0] = 1500;
            BossFlag1[0] = 0;
            BossFlag2[0] = 0;
            for(int k1 = 1; k1 <= 4; k1++)
            {
                BossAlive[k1] = true;
                BossType[k1] = 4;
                BossXSize[k1] = 30;
                BossYSize[k1] = 30;
                BossLife[k1] = 500;
                BossFlag1[k1] = 0;
                BossFlag2[k1] = 0;
            }

            BossX[1] = BossX[0] - 20;
            BossY[1] = BossY[0] + 35;
            BossX[2] = BossX[0] + 20;
            BossY[2] = BossY[0] + 35;
            BossX[3] = BossX[0] - 50;
            BossY[3] = BossY[0] + 0;
            BossX[4] = BossX[0] + 50;
            BossY[4] = BossY[0] + 0;
            break;

        case 5: // '\005'
            BossAlive[0] = true;
            BossType[0] = 5;
            BossX[0] = SCREEN_W / 2;
            BossY[0] = -27;
            BossXSize[0] = 40;
            BossYSize[0] = 40;
            BossLife[0] = 4000;
            BossFlag1[0] = 0;
            BossFlag2[0] = 0;
            break;
        }
        BossMaxLife = BossLife[0];
    }

    public void ScanKey()
    {
        if(FlgSoftKey1)
        {
            switch(MainFlag)
            {
            case 5: // '\005'
            case 7: // '\007'
            case 11: // '\013'
            default:
                break;

            case 2: // '\002'
                ChangeMain(3);
                break;

            case 3: // '\003'
                switch(Cursor)
                {
                case 0: // '\0'
                    MakeNewGame();
                    ChangeMain(6);
                    break;

                case 1: // '\001'
                    ChangeMain(4);
                    break;

                case 2: // '\002'
                    ChangeMain(5);
                    break;

                case 3: // '\003'
                    BackMain = 3;
                    ChangeMain(10);
                    break;
                }
                break;

            case 4: // '\004'
                if(FlgOption[Cursor])
                    FlgOption[Cursor] = false;
                else
                    FlgOption[Cursor] = true;
                if(Cursor != 2)
                    break;
                
                break;

            case 6: // '\006'
                MyChar = Cursor;
                ChangeMain(7);
                break;

            case 8: // '\b'
                ChangeMain(9);
                break;

            case 9: // '\t'
                switch(Cursor)
                {
                case 0: // '\0'
                    ChangeMain(8);
                    break;

                case 1: // '\001'
                    BackMain = 9;
                    ChangeMain(10);
                    break;

                case 2: // '\002'
                    ChangeMain(2);
                    DataSave();
                    break;
                }
                break;

            case 10: // '\n'
                if(HelpPage <= 1)
                    HelpPage++;
                else
                    ChangeMain(BackMain);
                break;

            case 12: // '\f'
                BonusTotal += 100 - BonusPoint;
                if(BonusTotal >= 100)
                {
                    ChangeMain(14);
                    break;
                }
                if(Stage >= 5)
                {
                    ChangeMain(13);
                } else
                {
                    StageClear();
                    ChangeMain(7);
                }
                break;

            case 13: // '\r'
                if(Round == 1)
                {
                    StageClear();
                    Round++;
                    ChangeMain(7);
                } else
                {
                    DataSave();
                    ChangeMain(2);
                }
                break;

            case 14: // '\016'
                DataSave();
                ChangeMain(2);
                break;
            }
            FlgSoftKey1 = false;
            return;
        }
        if(FlgSoftKey2)
        {
            switch(MainFlag)
            {
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
            case 9: // '\t'
            default:
                break;

            case 2: // '\002'
                try
                {
                    app.destroyApp(false);
                    app.notifyDestroyed();
                }
                catch(Exception exception) { }
                break;

            case 3: // '\003'
                ChangeMain(2);
                break;

            case 4: // '\004'
            case 5: // '\005'
                ChangeMain(3);
                break;

            case 10: // '\n'
                if(HelpPage > 0)
                    HelpPage--;
                else
                    ChangeMain(BackMain);
                break;
            }
            FlgSoftKey2 = false;
            return;
        }
        if(FlgPushDecide)
        {
            switch(MainFlag)
            {
            default:
                break;

            case 8: // '\b'
                if(!PlayerAlive)
                    break;
                if(PAttackMain == 1)
                {
                    ShotWait = 0;
                    PAttackMain = 0;
                } else
                {
                    PAttackMain = 1;
                }
                break;
            }
            FlgPushDecide = false;
        }
        if(FlgPushUp)
        {
            switch(MainFlag)
            {
            default:
                break;

            case 3: // '\003'
                if(Cursor > 0)
                    Cursor--;
                break;

            case 4: // '\004'
            case 9: // '\t'
                if(Cursor > 0)
                    Cursor--;
                break;
            }
            FlgPushUp = false;
        }
        if(FlgPushDown)
        {
            switch(MainFlag)
            {
            default:
                break;

            case 3: // '\003'
                if(Cursor < 3)
                    Cursor++;
                break;

            case 9: // '\t'
                if(Cursor < 2)
                    Cursor++;
                break;

            case 4: // '\004'
                if(Cursor < 2)
                    Cursor++;
                break;
            }
            FlgPushDown = false;
        }
        switch(MainFlag)
        {
        default:
            break;

        case 6: // '\006'
            if(0 != (ik & 0x100000))
                Cursor = 0;
            if(0 != (ik & 0x400000))
                Cursor = 1;
            break;

        case 8: // '\b'
            if(PlayerAlive)
                if(0 != (ik & 0x20000))
                {
                    PlusPlayerX(-3);
                    PlusPlayerY(-3);
                } else
                if(0 != (ik & 0x80000))
                {
                    PlusPlayerX(3);
                    PlusPlayerY(-3);
                } else
                if(0 != (ik & 0x800000))
                {
                    PlusPlayerX(-3);
                    PlusPlayerY(3);
                } else
                if(0 != (ik & 0x2000000))
                {
                    PlusPlayerX(3);
                    PlusPlayerY(3);
                } else
                {
                    if(0 != (ik & 0x40000))
                        PlusPlayerY(-3);
                    if(0 != (ik & 0x100000))
                        PlusPlayerX(-3);
                    if(0 != (ik & 0x400000))
                        PlusPlayerX(3);
                    if(0 != (ik & 0x1000000))
                        PlusPlayerY(3);
                }
            if(PlayerX < 8)
                PlayerX = 8;
            if(PlayerX > SCREEN_W - 8)
                PlayerX = SCREEN_W - 8;
            if(PlayerY < 0)
                PlayerY = 0;
            if(PlayerY > SCREEN_H - 8)
                PlayerY = SCREEN_H - 8;
            if(PAttackMain == 0)
                PAttackMode = 0;
            else
                PAttackMode = 1;
            break;
        }
    }

    public void PlusPlayerX(int i)
    {
        if(i < 0)
            if(PlayerXSp >= 0)
                PlayerXSp = -3;
            else
            if(PlayerXSp > -6)
                PlayerXSp--;
        if(i > 0)
            if(PlayerXSp <= 0)
                PlayerXSp = 3;
            else
            if(PlayerXSp < 6)
                PlayerXSp++;
        PlayerX += PlayerXSp;
    }

    public void PlusPlayerY(int i)
    {
        if(i < 0)
            if(PlayerYSp >= 0)
                PlayerYSp = -3;
            else
            if(PlayerYSp > -6)
                PlayerYSp--;
        if(i > 0)
            if(PlayerYSp <= 0)
                PlayerYSp = 3;
            else
            if(PlayerYSp < 6)
                PlayerYSp++;
        PlayerY += PlayerYSp;
    }

    public void MakeNewGame()
    {
        boolean flag = false;
        if(FlgOption[0])
            Round = 1;
        else
            Round = 2;
        Stage = 1;
        Score = 0;
        ExtendPoint = 0;
        Rest = 3;
        MyChar = 0;
        BonusTotal = 0;
        MakeGame();
    }

    public void MakeGame()
    {
        FlgGameOver = false;
        GameClear = false;
        PlayerAlive = true;
        PlayerBomb = 0;
        PlayerX = SCREEN_W / 2;
        PlayerY = (SCREEN_H / 4) * 3;
        PlayerXSp = 0;
        PlayerYSp = 0;
        PAttackMode = 0;
        PAttackMain = 0;
        MyLife = 6;
        MapScroll = 0;
        EncountNumber = 0;
        FlgBoss = 0;
        BossWait = 0;
        ClearWait = 0;
        ShotWait = 0;
        AnimMyLazer = 0;
        StartCount = 20;
        MySpecialCount = 0;
        ExtendCount = 0;
        OverCount = 0;
        BonusPoint = 0;
        BonusEnemy = 0;
        BonusEscape = 0;
        for(int i = 0; i < 30; i++)
            MyShotAlive[i] = false;

        for(int j = 0; j < 100; j++)
            EShotAlive[j] = false;

        EnemyWait = 0;
        for(int k = 0; k < 20; k++)
        {
            EnemyType[k] = 0;
            EnemyAlive[k] = false;
            EnemyBomb[k] = 0;
        }

        for(int l = 0; l < 5; l++)
        {
            BossType[l] = 0;
            BossAlive[l] = false;
            BossBomb[l] = 0;
            BossFlag3[l] = 0;
        }

        BossTarget = false;
        BossTargetX = 0;
        BossTargetY = 0;
        for(int i1 = 0; i1 < 10; i1++)
        {
            StarType[i1] = i1 % 4;
            StarX[i1] = Math.abs(god.nextInt() % SCREEN_W);
            StarY[i1] = Math.abs(god.nextInt() % SCREEN_H);
        }

    }

    public void DrawGraphic()
    {
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        switch(MainFlag)
        {
        case 1: // '\001'
        case 11: // '\013'
        default:
            break;

        case 0: // '\0'
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            offGfx.setColor(0x808080);
            DrawStringC("PLEASE WAIT", SCREEN_W / 2 + 1, (((SCREEN_H / 2 - 5) + 1) - 2 - FONT_H) + 1);
            offGfx.setColor(0xffffff);
            DrawStringC("PLEASE WAIT", SCREEN_W / 2, ((SCREEN_H / 2 - 5) + 1) - 2 - FONT_H);
            offGfx.setColor(0x808080);
            offGfx.drawRect((SCREEN_W / 2 - 50) + 1, (SCREEN_H / 2 - 5) + 1, 101, 10);
            offGfx.setColor(0xffffff);
            offGfx.drawRect(SCREEN_W / 2 - 50, SCREEN_H / 2 - 5, 101, 10);
            offGfx.setColor(0x808000);
            offGfx.fillRect((SCREEN_W / 2 - 50) + 1, (SCREEN_H / 2 - 5) + 1, (ReadCount * 99) / 16 + 1, 9);
            offGfx.setColor(0xffffff);
            break;

        case 2: // '\002'
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            offGfx.drawImage(imgTitle, SCREEN_W / 2, SCREEN_H / 2, 1 | 2);
            break;

        case 3: // '\003'
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            StrHelpMenu = (new String[] {
                "New Game", "Options", "High Scores", "Help", "", "", "", ""
            });
            offGfx.setColor(0xffffff);
            for(int j1 = 0; j1 < StrHelpMenu.length >> 1; j1++)
                offGfx.drawString(StrHelpMenu[j1], 2, (FONT_H + 2) * j1, 0);

            offGfx.setColor(0xff0000);
            offGfx.drawString(StrHelpMenu[Cursor], 2, (FONT_H + 2) * Cursor, 0);
            break;

        case 4: // '\004'
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            StrHelpMenu = (new String[] {
                "Round 1", "Speed High", "", "Round 2", "Speed Low", ""
            });
            offGfx.setColor(0xffffff);
            for(int k1 = 0; k1 < StrHelpMenu.length >> 1; k1++)
                if(FlgOption[k1])
                    offGfx.drawString(StrHelpMenu[k1], 2, (FONT_H + 2) * k1, 0);
                else
                    offGfx.drawString(StrHelpMenu[k1 + (StrHelpMenu.length >> 1)], 2, (FONT_H + 2) * k1, 0);

            offGfx.setColor(0xff0000);
            if(FlgOption[Cursor])
                offGfx.drawString(StrHelpMenu[Cursor], 2, (FONT_H + 2) * Cursor, 0);
            else
                offGfx.drawString(StrHelpMenu[Cursor + (StrHelpMenu.length >> 1)], 2, (FONT_H + 2) * Cursor, 0);
            break;

        case 5: // '\005'
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            offGfx.setColor(0x808080);
            DrawStringHC("TOP " + HiScore, SCREEN_W / 2 + 1, (FONT_H + 2) * 1);
            offGfx.setColor(0xffffff);
            DrawStringHC("TOP " + HiScore, SCREEN_W / 2, (FONT_H + 2) * 1);
            break;

        case 10: // '\n'
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            String as[][] = {
                {
                    "How to play", "Press the 5", "key to toggle", "missile/laser", "", ""
                }, {
                    "Press the 0", "key to destroy", "all enemies on", "screen, expend-", "ing some of", "your shield."
                }, {
                    "The game is", "over if your", "country is", "destroyed.", "", ""
                }
            };
            for(int l1 = 0; l1 < 6; l1++)
            {
                offGfx.setColor(0x808080);
                DrawStringHC(as[HelpPage][l1], SCREEN_W / 2 + 1, (FONT_H + 2) * l1 + 2 + 1);
                offGfx.setColor(0xffffff);
                DrawStringHC(as[HelpPage][l1], SCREEN_W / 2, (FONT_H + 2) * l1 + 2);
            }

            break;

        case 6: // '\006'
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            byte byte0 = 40;
            byte byte1 = 50;
            boolean flag4 = false;
            if(Math.abs(god.nextInt() % 20) == 0)
                flag4 = true;
            else
                flag4 = false;
            offGfx.drawImage(imgFace[0], SCREEN_W / 2 - 5 - byte0 / 2, SCREEN_H / 2 - 2 - byte1 / 2, 2 | 1);
            offGfx.drawImage(imgFace[1], SCREEN_W / 2 + 5 + byte0 / 2, SCREEN_H / 2 - 2 - byte1 / 2, 2 | 1);
            if(Cursor == 0)
            {
                if(flag4)
                    offGfx.drawImage(imgEye[0], (SCREEN_W / 2 - 5 - byte0 / 2 - byte0 / 2) + 0, (SCREEN_H / 2 - 2 - byte1 / 2 - byte1 / 2) + 10, 0);
                offGfx.setColor(0xff0000);
                offGfx.drawRect(SCREEN_W / 2 - 5 - byte0 / 2 - byte0 / 2 - 1, SCREEN_H / 2 - 2 - byte1 / 2 - byte1 / 2 - 1, imgFace[0].getWidth() + 1, imgFace[1].getHeight() + 1);
            } else
            {
                if(flag4)
                    offGfx.drawImage(imgEye[1], ((SCREEN_W / 2 + 5 + byte0 / 2) - byte0 / 2) + 8, (SCREEN_H / 2 - 2 - byte1 / 2 - byte1 / 2) + 13, 0);
                offGfx.setColor(0xff0000);
                offGfx.drawRect((SCREEN_W / 2 + 5 + byte0 / 2) - byte0 / 2 - 1, SCREEN_H / 2 - 2 - byte1 / 2 - byte1 / 2 - 1, imgFace[1].getWidth() + 1, imgFace[1].getHeight() + 1);
            }
            offGfx.setColor(0xffffff);
            offGfx.drawRect(SCREEN_W / 2 - 50, SCREEN_H / 2 + 2, 100, 40);
            offGfx.setColor(0x808080);
            offGfx.drawString("ATK", (SCREEN_W / 2 - 50) + 2 + 1, SCREEN_H / 2 + 2 + 2 + 1, 0);
            offGfx.drawString("WIDE", (SCREEN_W / 2 - 50) + 2 + 1, SCREEN_H / 2 + 2 + 2 + 20 + 2 + 1, 0);
            offGfx.setColor(0xffffff);
            offGfx.drawString("ATK", (SCREEN_W / 2 - 50) + 2, SCREEN_H / 2 + 2 + 2, 0);
            offGfx.drawString("WIDE", (SCREEN_W / 2 - 50) + 2, SCREEN_H / 2 + 2 + 2 + 20 + 2, 0);
            offGfx.setColor(0xff0000);
            offGfx.fillRect((SCREEN_W / 2 - 10) + 2, SCREEN_H / 2 + 2 + 2, CHAR_POW[Cursor], 8);
            offGfx.fillRect((SCREEN_W / 2 - 10) + 2, SCREEN_H / 2 + 2 + 20 + 2, CHAR_WIDE[Cursor], 8);
            offGfx.setColor(0x800000);
            offGfx.fillRect((SCREEN_W / 2 - 10) + 2 + 1, SCREEN_H / 2 + 2 + 2 + 1, CHAR_POW[Cursor], 8);
            offGfx.fillRect((SCREEN_W / 2 - 10) + 2 + 1, SCREEN_H / 2 + 2 + 20 + 2 + 1, CHAR_WIDE[Cursor], 8);
            break;

        case 7: // '\007'
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            offGfx.setColor(0x808080);
            DrawStringC("PLEASE WAIT", SCREEN_W / 2 + 1, (((SCREEN_H / 2 - 5) + 1) - 2 - FONT_H) + 1);
            offGfx.setColor(0xffffff);
            DrawStringC("PLEASE WAIT", SCREEN_W / 2, ((SCREEN_H / 2 - 5) + 1) - 2 - FONT_H);
            offGfx.setColor(0x808080);
            offGfx.drawRect((SCREEN_W / 2 - 50) + 1, (SCREEN_H / 2 - 5) + 1, 101, 10);
            offGfx.setColor(0xffffff);
            offGfx.drawRect(SCREEN_W / 2 - 50, SCREEN_H / 2 - 5, 101, 10);
            offGfx.setColor(0x808000);
            offGfx.fillRect((SCREEN_W / 2 - 50) + 1, (SCREEN_H / 2 - 5) + 1, (ReadCount * 99) / 23 + 1, 9);
            offGfx.setColor(0xffffff);
            break;

        case 8: // '\b'
            if(MySpecialCount > 0)
                offGfx.setColor(255);
            else
                offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            for(int i2 = 0; i2 < 10; i2++)
                offGfx.drawImage(imgStar[StarType[i2]], StarX[i2], StarY[i2], 2 | 1);

            if(FlgBoss == 2)
            {
                for(int j2 = 0; j2 < 5; j2++)
                    switch(BossType[j2])
                    {
                    default:
                        break;

                    case 1: // '\001'
                        if(j2 == 0)
                        {
                            if(BossBomb[j2] == 0)
                            {
                                if(BossDamage[j2])
                                    offGfx.drawImage(imgBoss[1], BossX[j2], BossY[j2], 2 | 1);
                                else
                                    offGfx.drawImage(imgBoss[0], BossX[j2], BossY[j2], 2 | 1);
                                break;
                            }
                            if(BossBomb[j2] / 4 <= 3)
                            {
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + 0, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] + 0, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] + 0, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + 0, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                            }
                            break;
                        }
                        if(BossBomb[j2] == 0)
                        {
                            if(BossDamage[j2])
                                offGfx.drawImage(imgBoss[3], BossX[j2], BossY[j2], 2 | 1);
                            else
                                offGfx.drawImage(imgBoss[2], BossX[j2], BossY[j2], 2 | 1);
                        } else
                        {
                            DrawBomb(BossBomb[j2], BossX[j2], BossY[j2]);
                        }
                        break;

                    case 2: // '\002'
                        if(j2 == 0)
                        {
                            if(BossBomb[j2] == 0)
                            {
                                offGfx.drawImage(imgBoss[4], BossX[j2], BossY[j2], 2 | 1);
                                if(BossDamage[j2])
                                    offGfx.drawImage(imgBoss[5], BossX[j2], BossY[j2] - 5, 2 | 1);
                                break;
                            }
                            if(BossBomb[j2] / 4 <= 3)
                            {
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + 0, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] + 0, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] + 0, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + 0, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                            }
                            break;
                        }
                        if(BossBomb[j2] == 0)
                        {
                            if(j2 == 1)
                                if(BossDamage[j2])
                                    offGfx.drawImage(imgBoss[7], BossX[j2], BossY[j2], 2 | 1);
                                else
                                    offGfx.drawImage(imgBoss[6], BossX[j2], BossY[j2], 2 | 1);
                            if(j2 != 2)
                                break;
                            if(BossDamage[j2])
                                offGfx.drawImage(imgBoss[9], BossX[j2], BossY[j2], 2 | 1);
                            else
                                offGfx.drawImage(imgBoss[8], BossX[j2], BossY[j2], 2 | 1);
                        } else
                        {
                            DrawBomb(BossBomb[j2], BossX[j2], BossY[j2]);
                        }
                        break;

                    case 3: // '\003'
                        if(j2 == 0)
                        {
                            if(BossBomb[j2] == 0)
                            {
                                if(BossDamage[j2])
                                    offGfx.drawImage(imgBoss[11], BossX[j2], BossY[j2], 2 | 1);
                                else
                                    offGfx.drawImage(imgBoss[10], BossX[j2], BossY[j2], 2 | 1);
                                break;
                            }
                            if(BossBomb[j2] / 4 <= 3)
                            {
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + 0, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] + 0, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] + 0, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + 0, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                            }
                            break;
                        }
                        if(BossBomb[j2] == 0)
                        {
                            if(j2 == 1)
                                if(BossDamage[j2])
                                    offGfx.drawImage(imgBoss[13], BossX[j2], BossY[j2], 2 | 1);
                                else
                                    offGfx.drawImage(imgBoss[12], BossX[j2], BossY[j2], 2 | 1);
                            if(j2 != 2)
                                break;
                            if(BossDamage[j2])
                                offGfx.drawImage(imgBoss[15], BossX[j2], BossY[j2], 2 | 1);
                            else
                                offGfx.drawImage(imgBoss[14], BossX[j2], BossY[j2], 2 | 1);
                        } else
                        {
                            DrawBomb(BossBomb[j2], BossX[j2], BossY[j2]);
                        }
                        break;

                    case 4: // '\004'
                        if(j2 == 0)
                        {
                            if(BossBomb[j2] == 0)
                            {
                                if(BossDamage[j2])
                                    offGfx.drawImage(imgBoss[17], BossX[j2], BossY[j2], 2 | 1);
                                else
                                    offGfx.drawImage(imgBoss[16], BossX[j2], BossY[j2], 2 | 1);
                                break;
                            }
                            if(BossBomb[j2] / 4 <= 3)
                            {
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + 0, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] + 0, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] + 0, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + 0, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                                offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                            }
                            break;
                        }
                        if(BossBomb[j2] == 0)
                        {
                            if(BossDamage[j2])
                                offGfx.drawImage(imgBoss[19], BossX[j2], BossY[j2], 2 | 1);
                            else
                                offGfx.drawImage(imgBoss[18], BossX[j2], BossY[j2], 2 | 1);
                        } else
                        {
                            DrawBomb(BossBomb[j2], BossX[j2], BossY[j2]);
                        }
                        break;

                    case 5: // '\005'
                        if(BossBomb[j2] == 0)
                        {
                            if(BossDamage[j2])
                                offGfx.drawImage(imgBoss[21], BossX[j2], BossY[j2], 2 | 1);
                            else
                                offGfx.drawImage(imgBoss[20], BossX[j2], BossY[j2], 2 | 1);
                            if(BossFlag3[j2] == 1)
                            {
                                offGfx.setColor(0xffff00);
                                offGfx.drawLine(BossX[j2], BossY[j2], BossTargetX, BossTargetY);
                            }
                            if(BossFlag3[j2] == 2)
                            {
                                offGfx.setColor(0xff0000);
                                offGfx.drawLine(BossX[j2], BossY[j2], BossTargetX, BossTargetY);
                            }
                            break;
                        }
                        if(BossBomb[j2] / 4 <= 3)
                        {
                            offGfx.setColor(0xffffff);
                            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
                            offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                            offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + 0, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                            offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] - BossBomb[j2] * 3, 2 | 1);
                            offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] + 0, 2 | 1);
                            offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] + 0, 2 | 1);
                            offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] - BossBomb[j2] * 3, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                            offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + 0, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                            offGfx.drawImage(imgBomb[BossBomb[j2] / 4], BossX[j2] + BossBomb[j2] * 3, BossY[j2] + BossBomb[j2] * 3, 2 | 1);
                        }
                        break;
                    }

            } else
            {
                for(int k2 = 0; k2 < 20; k2++)
                    if(EnemyType[k2] != 0)
                        if(EnemyAlive[k2])
                            offGfx.drawImage(imgEnemy[EnemyType[k2] - 1], EnemyX[k2], EnemyY[k2], 2 | 1);
                        else
                            DrawBomb(EnemyBomb[k2], EnemyX[k2], EnemyY[k2]);

            }
            for(int l2 = 0; l2 < 30; l2++)
                if(MyShotAlive[l2])
                    offGfx.drawImage(imgMyShot[MyShotType[l2]], MyShotX[l2], MyShotY[l2], 2 | 1);

            if(PAttackMode == 1)
                if(MyChar == 0)
                {
                    offGfx.drawImage(imgMyShot[9 + AnimMyLazer], PlayerX, PlayerY - 8, 2 | 1);
                    int i = MyLazerLength / 20 - 1;
                    int i3;
                    for(i3 = 1; i3 < i; i3++)
                        offGfx.drawImage(imgMyShot[11 + AnimMyLazer], PlayerX, PlayerY - 8 - i3 * 20, 2 | 1);

                    offGfx.drawImage(imgMyShot[13 + AnimMyLazer], PlayerX, PlayerY - 8 - i3 * 20, 2 | 1);
                } else
                {
                    offGfx.drawImage(imgMyShot[3 + AnimMyLazer], PlayerX, PlayerY - 8, 2 | 1);
                    int j = MyLazerLength / 16 - 1;
                    int j3;
                    for(j3 = 1; j3 < j; j3++)
                        offGfx.drawImage(imgMyShot[5 + AnimMyLazer], PlayerX, PlayerY - 8 - j3 * 16, 2 | 1);

                    offGfx.drawImage(imgMyShot[7 + AnimMyLazer], PlayerX, PlayerY - 8 - j3 * 16, 2 | 1);
                }
            if(PlayerAlive)
            {
                if((Muteki & 1) == 0)
                    offGfx.drawImage(imgMyShip, PlayerX, PlayerY, 2 | 1);
            } else
            if(PlayerBomb < 5)
            {
                offGfx.drawImage(imgMyShip, PlayerX, PlayerY, 2 | 1);
                DrawBomb(PlayerBomb, PlayerX, PlayerY);
            } else
            if(PlayerBomb < 20)
            {
                int k = PlayerBomb - 5;
                DrawBomb(k, PlayerX - k * 5, PlayerY - k * 5);
                DrawBomb(k, PlayerX, PlayerY - k * 5);
                DrawBomb(k, PlayerX + k * 5, PlayerY - k * 5);
                DrawBomb(k, PlayerX - k * 5, PlayerY);
                DrawBomb(k, PlayerX + k * 5, PlayerY);
                DrawBomb(k, PlayerX - k * 5, PlayerY + k * 5);
                DrawBomb(k, PlayerX, PlayerY + k * 5);
                DrawBomb(k, PlayerX + k * 5, PlayerY + k * 5);
            }
            for(int k3 = 0; k3 < 100; k3++)
                if(EShotAlive[k3])
                    if(EShotType[k3] <= 6)
                        offGfx.drawImage(imgEShot[EShotType[k3]], EShotX[k3] / 10, EShotY[k3] / 10, 2 | 1);
                    else
                    if(EShotType[k3] == 7)
                    {
                        if(EShotMX[k3] < 0)
                            offGfx.drawImage(imgEShot[7], EShotX[k3] / 10, EShotY[k3] / 10, 2 | 1);
                        else
                            offGfx.drawImage(imgEShot[8], EShotX[k3] / 10, EShotY[k3] / 10, 2 | 1);
                    } else
                    if(EShotType[k3] == 8)
                        DrawBomb(EShotFlag[k3], EShotX[k3] / 10, EShotY[k3] / 10);
                    else
                    if(EShotType[k3] == 9)
                        offGfx.drawImage(imgEShot[9], EShotX[k3] / 10, EShotY[k3] / 10, 2 | 1);
                    else
                    if(EShotType[k3] == 11)
                        offGfx.drawImage(imgEShot[6], EShotX[k3] / 10, EShotY[k3] / 10, 2 | 1);

            if(FlgBoss == 2 && BossTarget)
            {
                offGfx.setColor(0xff0000);
                offGfx.drawRect(BossTargetX - 5, BossTargetY - 5, 10, 10);
                offGfx.drawLine(BossTargetX - 10, BossTargetY - 10, BossTargetX + 10, BossTargetY + 10);
                offGfx.drawLine(BossTargetX + 10, BossTargetY - 10, BossTargetX - 10, BossTargetY + 10);
            }
            if(FlgGameOver)
            {
                offGfx.setColor(0x808080);
                DrawStringC("GAME OVER", SCREEN_W / 2 + 1, SCREEN_H / 2 + 1);
                offGfx.setColor(0xffffff);
                DrawStringC("GAME OVER", SCREEN_W / 2, SCREEN_H / 2);
            } else
            if(StartCount > 0)
            {
                offGfx.setColor(0x808080);
                DrawStringC("STAGE " + Stage, SCREEN_W / 2 + 1, SCREEN_H / 2 + 1);
                DrawStringC("START", SCREEN_W / 2 + 1, SCREEN_H / 2 + 1 + FONT_H);
                if(Round == 1)
                    offGfx.setColor(0xffffff);
                else
                    offGfx.setColor(0xff0000);
                DrawStringC("STAGE " + Stage, SCREEN_W / 2, SCREEN_H / 2);
                DrawStringC("START", SCREEN_W / 2, SCREEN_H / 2 + FONT_H);
            } else
            if(FlgBoss == 1)
            {
                offGfx.setColor(0);
                DrawStringC("WARNING", SCREEN_W / 2 + 1, SCREEN_H / 2 + 1);
                if(BossWait % 2 == 0)
                    offGfx.setColor(0xff0000);
                else
                    offGfx.setColor(0xffffff);
                DrawStringC("WARNING", SCREEN_W / 2, SCREEN_H / 2);
            } else
            if(GameClear)
            {
                offGfx.setColor(0x808080);
                DrawStringC("CLEAR !!", SCREEN_W / 2 + 1, SCREEN_H / 2 + 1);
                offGfx.setColor(0xffffff);
                DrawStringC("CLEAR !!", SCREEN_W / 2, SCREEN_H / 2);
            } else
            if(ExtendCount > 0 && ExtendCount % 2 != 0)
            {
                offGfx.setColor(0xffffff);
                DrawStringC("1 UP", SCREEN_W / 2 + 1, SCREEN_H / 2 + 1);
                offGfx.setColor(0x80ff80);
                DrawStringC("1 UP", SCREEN_W / 2, SCREEN_H / 2);
            }
            if(FlgBoss == 2)
            {
                offGfx.setColor(0x808080);
                offGfx.drawRect((SCREEN_W / 2 - 50 - 1) + 1, 6, 101, 5);
                offGfx.setColor(0xffffff);
                offGfx.drawRect(SCREEN_W / 2 - 50 - 1, 5, 101, 5);
                if((BossLife[0] * 100) / BossMaxLife > 0)
                {
                    offGfx.setColor(65535);
                    offGfx.fillRect(SCREEN_W / 2 - 50, 6, (BossLife[0] * 100) / BossMaxLife, 4);
                }
            } else
            {
                offGfx.setColor(0x808080);
                offGfx.drawString("" + Score, 9, 3, 0);
                offGfx.setColor(0xffffff);
                offGfx.drawString("" + Score, 8, 2, 0);
            }
            for(int l3 = 0; l3 < Rest; l3++)
                offGfx.drawImage(imgIcon[1], SCREEN_W - imgIcon[1].getWidth() * (l3 + 1), SCREEN_H - imgIcon[1].getHeight(), 0);

            offGfx.drawImage(imgIcon[2], 2, SCREEN_H - imgIcon[2].getHeight(), 0);
            for(int i4 = 0; i4 < MyLife; i4++)
                offGfx.drawImage(imgIcon[3], 8 + 6 * i4, (SCREEN_H - imgIcon[2].getHeight()) + 1, 0);

            break;

        case 9: // '\t'
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            StrHelpMenu = (new String[] {
                "Continue", "Help", "Main Menu", "", "", ""
            });
            offGfx.setColor(0xffffff);
            for(int j4 = 0; j4 < StrHelpMenu.length >> 1; j4++)
                offGfx.drawString(StrHelpMenu[j4], 2, (FONT_H + 2) * j4, 0);

            offGfx.setColor(0xff0000);
            offGfx.drawString(StrHelpMenu[Cursor], 2, (FONT_H + 2) * Cursor, 0);
            break;

        case 12: // '\f'
            int l = (SCREEN_H / 2 - 50) + 5;
            int i1 = BonusTotal + (100 - BonusPoint);
            int i5 = ((l - 5) + 95) - imgFace[MyChar].getHeight();
            int j5 = (i5 + imgFace[MyChar].getHeight()) - 11;
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            offGfx.setColor(0x404040);
            offGfx.drawRect((SCREEN_W / 2 - 50) + 1, (SCREEN_H / 2 - 50) + 1, 100, 100);
            offGfx.setColor(0x808080);
            offGfx.fillRect(SCREEN_W / 2 - 50, SCREEN_H / 2 - 50, 100, 100);
            offGfx.drawImage(imgFace[MyChar], (SCREEN_W / 2 - 50) + 5, ((l - 5) + 95) - imgFace[MyChar].getHeight(), 0);
            offGfx.setColor(0);
            DrawStringHC("RESULT", SCREEN_W / 2 + 1, l + 1);
            DrawStringL("Destroyed", (SCREEN_W / 2 - 48) + 1, l + (FONT_H + 2) * 1 + 1);
            offGfx.setColor(0xffffff);
            DrawStringHC("RESULT", SCREEN_W / 2, l);
            DrawStringL("Destroyed", SCREEN_W / 2 - 48, l + (FONT_H + 2) * 1);
            offGfx.setFont(Font.getFont(0, 0, 8));
            int k5 = Font.getFont(0, 0, 8).getHeight();
            offGfx.setColor(0);
            DrawStringR("" + BonusPoint + "%", SCREEN_W / 2 + 48 + 1, i5 + 1);
            DrawStringR("Country", SCREEN_W / 2 + 48 + 1, (j5 - (k5 + 2)) + 1);
            offGfx.setColor(0xffffff);
            DrawStringR("" + BonusPoint + "%", SCREEN_W / 2 + 48, i5);
            DrawStringR("Country", SCREEN_W / 2 + 48, j5 - (k5 + 2));
            offGfx.setFont(Font.getDefaultFont());
            offGfx.setColor(128);
            offGfx.fillRect((SCREEN_W / 2 - 50) + 57 + 1, j5 + 1, 40, 9);
            if(i1 >= 100)
            {
                offGfx.setColor(0x800000);
                offGfx.fillRect((SCREEN_W / 2 - 50) + 57 + 1, j5 + 1, 40, 9);
            } else
            if(i1 > 0)
            {
                offGfx.setColor(0x800000);
                offGfx.fillRect((SCREEN_W / 2 - 50) + 57 + 1, j5 + 1, (i1 * 40) / 100, 9);
            }
            offGfx.setColor(255);
            offGfx.fillRect((SCREEN_W / 2 - 50) + 57, j5, 40, 9);
            if(i1 >= 100)
            {
                offGfx.setColor(0xff0000);
                offGfx.fillRect((SCREEN_W / 2 - 50) + 57, j5, 40, 9);
                break;
            }
            if(i1 > 0)
            {
                offGfx.setColor(0xff0000);
                offGfx.fillRect((SCREEN_W / 2 - 50) + 57, j5, (i1 * 40) / 100, 9);
            }
            break;

        case 13: // '\r'
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            for(int k4 = 0; k4 < 10; k4++)
                offGfx.drawImage(imgStar[StarType[k4]], StarX[k4], StarY[k4], 2 | 1);

            if(Round == 1)
            {
                offGfx.setColor(0x808080);
                DrawStringHC("Ok!!", SCREEN_W / 2 + 1, (FONT_H + 2) * 0 + 2 + 1);
                DrawStringHC("GameClear!!", SCREEN_W / 2 + 1, (FONT_H + 2) * 1 + 2 + 1);
                DrawStringHC("Try Next", SCREEN_W / 2 + 1, (FONT_H + 2) * 3 + 2 + 1);
                DrawStringHC("Hard Level!!", SCREEN_W / 2 + 1, (FONT_H + 2) * 4 + 2 + 1);
                offGfx.setColor(0xffffff);
                DrawStringHC("Ok!!", SCREEN_W / 2, (FONT_H + 2) * 0 + 2);
                DrawStringHC("GameClear!!", SCREEN_W / 2, (FONT_H + 2) * 1 + 2);
                DrawStringHC("Try Next", SCREEN_W / 2, (FONT_H + 2) * 3 + 2);
                DrawStringHC("Hard Level!!", SCREEN_W / 2, (FONT_H + 2) * 4 + 2);
            } else
            {
                offGfx.setColor(0x808080);
                DrawStringHC("Congratulation", SCREEN_W / 2 + 1, (FONT_H + 2) * 0 + 2 + 1);
                DrawStringHC("AllClear!!", SCREEN_W / 2 + 1, (FONT_H + 2) * 1 + 2 + 1);
                DrawStringHC("ThankYouFor", SCREEN_W / 2 + 1, (FONT_H + 2) * 3 + 2 + 1);
                DrawStringHC("Playing!!", SCREEN_W / 2 + 1, (FONT_H + 2) * 4 + 2 + 1);
                offGfx.setColor(0xffffff);
                DrawStringHC("Congratulation", SCREEN_W / 2, (FONT_H + 2) * 0 + 2);
                DrawStringHC("AllClear!!", SCREEN_W / 2, (FONT_H + 2) * 1 + 2);
                DrawStringHC("ThankYouFor", SCREEN_W / 2, (FONT_H + 2) * 3 + 2);
                DrawStringHC("Playing!!", SCREEN_W / 2, (FONT_H + 2) * 4 + 2);
            }
            break;

        case 14: // '\016'
            String as1[] = {
                "Country was", "destroyed!", "Mission", "failed"
            };
            offGfx.setColor(0);
            offGfx.fillRect(0, 0, SCREEN_W + 1, SCREEN_H + 1);
            for(int l4 = 0; l4 < as1.length; l4++)
            {
                offGfx.setColor(0x808080);
                DrawStringHC(as1[l4], SCREEN_W / 2 + 1, (FONT_H + 2) * l4 + 2 + 1);
                offGfx.setColor(0xffffff);
                DrawStringHC(as1[l4], SCREEN_W / 2, (FONT_H + 2) * l4 + 2);
            }

            break;
        }
        offGfx.setColor(0xffffff);
        DrawStringL(SoftMess1, 0, SCREEN_H - FONT_H);
        DrawStringR(SoftMess2, SCREEN_W, SCREEN_H - FONT_H);
    }

    public void DrawStringL(String s, int i, int j)
    {
        offGfx.drawString(s, i, j, 0);
    }

    public void DrawStringR(String s, int i, int j)
    {
        offGfx.drawString(s, i - offGfx.getFont().stringWidth(s), j, 0);
    }

    public void DrawStringC(String s, int i, int j)
    {
        offGfx.drawString(s, i - (offGfx.getFont().stringWidth(s) >> 1), j - (FONT_H >> 1), 0);
    }

    public void DrawStringHC(String s, int i, int j)
    {
        offGfx.drawString(s, i - (offGfx.getFont().stringWidth(s) >> 1), j, 0);
    }

    public void DrawBomb(int i, int j, int k)
    {
        switch(i)
        {
        case 0: // '\0'
        case 1: // '\001'
            offGfx.drawImage(imgBomb[0], j, k, 2 | 1);
            break;

        case 2: // '\002'
            offGfx.drawImage(imgBomb[1], j, k, 2 | 1);
            break;

        case 3: // '\003'
            offGfx.drawImage(imgBomb[2], j, k, 2 | 1);
            break;

        case 4: // '\004'
            offGfx.drawImage(imgBomb[3], j, k, 2 | 1);
            break;
        }
    }

    public void ChangeMain(int i)
    {
        MainFlag = i;
        Cursor = 0;
        ReadCount = 0;
        ThreadSpeed = 100;
        switch(i)
        {
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 9: // '\t'
        case 11: // '\013'
        default:
            break;

        case 1: // '\001'
            LogoCount = 0;
            break;

        case 2: // '\002'
            TitleCount = 0;
            System.gc();
            break;

        case 10: // '\n'
            HelpPage = 0;
            break;

        case 7: // '\007'
            for(int j = 0; j < imgBoss.length; j++)
                imgBoss[j] = null;

            System.gc();
            break;

        case 8: // '\b'
            if(FlgOption[1])
                ThreadSpeed = 100;
            else
                ThreadSpeed = 150;
            break;

        case 12: // '\f'
            if(BonusEnemy > 0)
                BonusPoint = ((BonusEnemy - BonusEscape) * 100) / BonusEnemy;
            else
                BonusPoint = 0;
            break;
        }
        SetSoftKey();
    }

    public void keyPressed(int i)
    {
        switch(i)
        {
        case -6: 
            FlgSoftKey1 = true;
            break;

        case -7: 
            FlgSoftKey2 = true;
            break;

        case 48: // '0'
            ik |= 0x10000;
            if(MainFlag == 8 && PlayerAlive && MyLife > 1 && MySpecialCount <= 0)
            {
                MyLife--;
                MySpecialCount = 5;
                if(FlgBoss == 2)
                {
                    for(int j = 0; j < 5; j++)
                        if(BossAlive[j])
                        {
                            BossDamage[j] = true;
                            BossLife[j] -= 50;
                            if(BossLife[j] <= 0)
                            {
                                BossAlive[j] = false;
                                PlusScore(BossPoint[j]);
                            }
                        }

                } else
                {
                    for(int k = 0; k < 20; k++)
                        if(EnemyAlive[k])
                        {
                            EnemyLife[k] -= 50;
                            if(EnemyLife[k] <= 0)
                            {
                                EnemyAlive[k] = false;
                                PlusScore(EnemyPoint[k]);
                            }
                        }

                }
            }
            break;

        case 49: // '1'
            ik |= 0x20000;
            ik &= 0xfdffffff;
            break;

        case 50: // '2'
            ik |= 0x40000;
            ik &= 0xfeffffff;
            FlgPushUp = true;
            break;

        case 51: // '3'
            ik |= 0x80000;
            ik &= 0xff7fffff;
            break;

        case 52: // '4'
            ik |= 0x100000;
            ik &= 0xffbfffff;
            FlgPushLeft = true;
            break;

        case 53: // '5'
            ik |= 0x200000;
            FlgPushDecide = true;
            break;

        case 54: // '6'
            ik |= 0x400000;
            ik &= 0xffefffff;
            FlgPushRight = true;
            break;

        case 55: // '7'
            ik |= 0x800000;
            ik &= 0xfff7ffff;
            break;

        case 56: // '8'
            ik |= 0x1000000;
            ik &= 0xfffbffff;
            FlgPushDown = true;
            break;

        case 57: // '9'
            ik |= 0x2000000;
            ik &= 0xfffdffff;
            break;

        case 42: // '*'
            ik |= 0x400;
            break;

        case 35: // '#'
            ik |= 8;
            break;

        default:
            try
            {
                gk = getGameAction(i);
            }
            catch(Exception exception) { }
            switch(gk)
            {
            case 1: // '\001'
                ik |= 0x40000;
                ik &= 0xfeffffff;
                FlgPushUp = true;
                break;

            case 6: // '\006'
                ik |= 0x1000000;
                ik &= 0xfffbffff;
                FlgPushDown = true;
                break;

            case 5: // '\005'
                ik |= 0x400000;
                ik &= 0xffefffff;
                FlgPushRight = true;
                break;

            case 2: // '\002'
                ik |= 0x100000;
                ik &= 0xffbfffff;
                FlgPushLeft = true;
                break;

            case 8: // '\b'
                ik |= 0x200000;
                FlgPushDecide = true;
                break;
            }
            break;
        }
    }

    public void keyReleased(int i)
    {
        switch(i)
        {
        case 48: // '0'
            ik &= 0xfffeffff;
            break;

        case 49: // '1'
            ik &= 0xfffdffff;
            break;

        case 50: // '2'
            ik &= 0xfffbffff;
            break;

        case 51: // '3'
            ik &= 0xfff7ffff;
            break;

        case 52: // '4'
            ik &= 0xffefffff;
            break;

        case 53: // '5'
            ik &= 0xffdfffff;
            break;

        case 54: // '6'
            ik &= 0xffbfffff;
            break;

        case 55: // '7'
            ik &= 0xff7fffff;
            break;

        case 56: // '8'
            ik &= 0xfeffffff;
            break;

        case 57: // '9'
            ik &= 0xfdffffff;
            break;

        case 42: // '*'
            ik &= 0xfffffbff;
            break;

        case 35: // '#'
            ik &= -9;
            break;

        case 36: // '$'
        case 37: // '%'
        case 38: // '&'
        case 39: // '\''
        case 40: // '('
        case 41: // ')'
        case 43: // '+'
        case 44: // ','
        case 45: // '-'
        case 46: // '.'
        case 47: // '/'
        default:
            try
            {
                gk = getGameAction(i);
            }
            catch(Exception exception)
            {
                ik = 0;
            }
            switch(gk)
            {
            case 1: // '\001'
                ik &= 0xfffbffff;
                break;

            case 6: // '\006'
                ik &= 0xfeffffff;
                break;

            case 5: // '\005'
                ik &= 0xffbfffff;
                break;

            case 2: // '\002'
                ik &= 0xffefffff;
                break;

            case 8: // '\b'
                ik &= 0xffdfffff;
                break;
            }
            break;
        }
    }

    public void SetSoftKey()
    {
        switch(MainFlag)
        {
        case 2: // '\002'
            SoftMess1 = "START";
            break;

        case 3: // '\003'
        case 4: // '\004'
        case 6: // '\006'
        case 9: // '\t'
            SoftMess1 = "SELECT";
            break;

        case 8: // '\b'
            SoftMess1 = "";
            break;

        case 10: // '\n'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
            SoftMess1 = "NEXT";
            break;

        case 5: // '\005'
        case 7: // '\007'
        case 11: // '\013'
        default:
            SoftMess1 = "";
            break;
        }
        switch(MainFlag)
        {
        case 2: // '\002'
            SoftMess2 = "EXIT";
            break;

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 10: // '\n'
            SoftMess2 = "BACK";
            break;

        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        default:
            SoftMess2 = "";
            break;
        }
    }

    public void DataSave()
    {
        byte abyte0[] = new byte[4];
        try
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
            dataoutputstream.writeInt(HiScore);
            rs = RecordStore.openRecordStore("HiScore", true);
            byte abyte1[] = bytearrayoutputstream.toByteArray();
            if(rs.getNumRecords() == 0)
                rsId = rs.addRecord(abyte1, 0, abyte1.length);
            else
                rs.setRecord(rsId, abyte1, 0, abyte1.length);
            rs.closeRecordStore();
            dataoutputstream.close();
            bytearrayoutputstream.close();
        }
        catch(Exception exception)
        {
            try
            {
                rs.closeRecordStore();
            }
            catch(Exception exception1) { }
        }
    }

    public void DataLoad()
    {
        byte abyte0[] = new byte[4];
        for(int i = 0; i < 4; i++)
            abyte0[i] = 0;

        try
        {
            rs = RecordStore.openRecordStore("HiScore", true);
            rsId = rs.getNextRecordID();
            if(rsId > 0)
                rsId--;
            if(rs.getNumRecords() == 0)
            {
                rsId = rs.addRecord(abyte0, 0, abyte0.length);
            } else
            {
                byte abyte1[] = rs.getRecord(rsId);
                ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte1);
                DataInputStream datainputstream = new DataInputStream(bytearrayinputstream);
                HiScore = datainputstream.readInt();
                datainputstream.close();
                bytearrayinputstream.close();
            }
            rs.closeRecordStore();
        }
        catch(Exception exception)
        {
            try
            {
                rs.closeRecordStore();
            }
            catch(Exception exception1) { }
        }
    }

    public int[] SetTarget(int i, int j, int k, int l, int i1)
    {
        int j1 = 0;
        int k1 = 0;
        byte byte0 = 9;
        if(Math.abs(i - k) > Math.abs(j - l))
        {
            if(k < i)
                j1 = -byte0;
            else
                j1 = byte0;
            if(l < j)
                k1 = -((Math.abs(j - l) * byte0) / Math.abs(i - k));
            else
                k1 = (Math.abs(j - l) * byte0) / Math.abs(i - k);
        } else
        if(Math.abs(i - k) < Math.abs(j - l))
        {
            if(l < j)
                k1 = -byte0;
            else
                k1 = byte0;
            if(k < i)
                j1 = -((Math.abs(i - k) * byte0) / Math.abs(j - l));
            else
                j1 = (Math.abs(i - k) * byte0) / Math.abs(j - l);
        } else
        {
            if(l < j)
                k1 = -byte0;
            else
                k1 = byte0;
            if(k < i)
                j1 = -byte0;
            else
                j1 = byte0;
        }
        int ai[] = {
            j1 * i1, k1 * i1
        };
        return ai;
    }

    moonlight app;
    Display display;
    Thread thread;
    Random god;
    final int SCREEN_W = getWidth();
    final int SCREEN_H = getHeight();
    final int FONT_H = Font.getDefaultFont().getHeight();
    final int SCREEN_WH;
    static final int MAIN_INIT = 0;
    static final int MAIN_LOGO = 1;
    static final int MAIN_TITLE = 2;
    static final int MAIN_MENU = 3;
    static final int MAIN_OPTION = 4;
    static final int MAIN_HIGHSCORE = 5;
    static final int MAIN_CHARSELECT = 6;
    static final int MAIN_LOADING = 7;
    static final int MAIN_GAME = 8;
    static final int MAIN_PAUSE = 9;
    static final int MAIN_HELP = 10;
    static final int MAIN_RESULT = 12;
    static final int MAIN_ENDING = 13;
    static final int MAIN_BADEND = 14;
    static final int IMGMAX_BOSS = 22;
    static final int IMGMAX_ENEMY = 7;
    static final int IMGMAX_SHOT = 15;
    static final int IMGMAX_ESHOT = 10;
    static final int IMGMAX_ICON = 4;
    static final int IMGMAX_STAR = 4;
    static final int MAX_MYSHOT = 30;
    static final int MAX_ESHOT = 100;
    static final int MAX_ENEMY = 20;
    static final int MAX_STAR = 10;
    static final int CIRCLEX[] = {
        10, 10, 9, 8, 7, 6, 3, 2, 0, -2, 
        -3, -6, -7, -8, -9, -10, -10, -10, -9, -8, 
        -7, -6, -3, -2, 0, 2, 3, 6, 7, 8, 
        9, 10, 10, 10, 9, 8, 7, 6, 3, 2
    };
    static final int CIRCLEY[] = {
        0, -2, -3, -6, -7, -8, -9, -10, -10, -10, 
        -9, -8, -7, -6, -3, -2, 0, 2, 3, 6, 
        7, 8, 9, 10, 10, 10, 9, 8, 7, 6, 
        3, 2, 0, -2, -3, -6, -7, -8, -9, -10
    };
    static final int CHAR_POW[] = {
        50, 30
    };
    static final int CHAR_WIDE[] = {
        30, 50
    };
    static final int BOSS_SIZE1[] = {
        862, 733, 511, 430, 999, 543, 484, 447, 483, 443
    };
    static final int BOSS_SIZE2[] = {
        579, 455, 711, 586, 707, 585, 701, 541, 410, 370, 
        416, 357
    };
    static final int SHOT_SIZE[] = {
        269, 250, 248, 315, 313, 296, 298, 313, 315, 330, 
        330, 283, 285, 325, 340
    };
    static final int EXTEND[] = {
        50000, 0x186a0, 0x30d40, 0x493e0, 0x7a120, 0xf4240
    };
    
}