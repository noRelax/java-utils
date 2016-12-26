// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi

import com.nokia.mid.ui.FullCanvas;
import java.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;

public class GameDataManager
{
    class InitGame
    {

        public void getRecords()
        {
            try
            {
                try
                {
                    for(int i = 0; i < MaxRecords; i++)
                    {
                        GameDataManager.recordscore = RecordStore.openRecordStore(GameDataManager.RSname[i], true);
                        int j = GameDataManager.recordscore.getNumRecords();
                        if(j > 0)
                        {
                            GameDataManager.scoredata = GameDataManager.recordscore.getRecord(GameDataManager.scoreid);
                            DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(GameDataManager.scoredata));
                            boolean flag = datainputstream.readBoolean();
                            GameDataManager.scoreval = datainputstream.readInt();
                            GameDataManager.namedata = GameDataManager.recordscore.getRecord(GameDataManager.nameid);
                            DataInputStream datainputstream1 = new DataInputStream(new ByteArrayInputStream(GameDataManager.namedata));
                            boolean flag1 = datainputstream1.readBoolean();
                            GameDataManager.nameval = datainputstream1.readUTF();
                            GameDataManager.recordscore.closeRecordStore();
                        } else
                        {
                            GameDataManager.nameval = "***";
                            GameDataManager.scoreval = 0;
                            GameDataManager.recordscore.closeRecordStore();
                        }
                        GameDataManager _tmp = GameDataManager.this;
                        ZapperCanvas.rnames[i] = GameDataManager.nameval;
                        owner.rscores[i] = GameDataManager.scoreval;
                    }

                }
                catch(RecordStoreException recordstoreexception) { }
            }
            catch(IOException ioexception) { }
        }

        InitGame()
        {
        }
    }


    ZapperCanvas owner;
    Image keyboardImg;
    int kbx;
    int kby;
    int kbw;
    int kbh;
    int curx;
    int cury;
    int curw;
    int curh;
    int curposx;
    int curposy;
    static String nameStr = "";
    boolean moving;
    char chars[][];
    boolean movedown;
    InitGame object;
    static boolean newHighScore = false;
    static boolean insidegame1 = false;
    static boolean datatosave = false;
    static String RSname[];
    static RecordStore recordscore = null;
    static String nameval = "";
    static int scoreval;
    static byte scoredata[];
    static byte namedata[];
    static int nameid = 2;
    static int scoreid = 1;
    int MaxRecords;
    static int number = 1;
    static int Score = 0;

    GameDataManager(ZapperCanvas zappercanvas)
    {
        moving = false;
        object = new InitGame();
        MaxRecords = 3;
        owner = zappercanvas;
        RSname = new String[3];
        RSname[0] = "清除数据";
        RSname[1] = "清除数据1";
        RSname[2] = "清除数据2";
    }

    public void SetupKeyBoard(Image image)
    {
        keyboardImg = image;
        kbw = keyboardImg.getWidth();
        kbh = keyboardImg.getHeight();
        kbx = owner.getWidth() / 2 - kbw / 2;
        kby = owner.getHeight();
        curx = 20;
        cury = 1;
        curw = 10;
        curh = 10;
        curposx = 0;
        curposy = 0;
        chars = new char[3][10];
        for(int i = 0; i < 10; i++)
            chars[0][i] = (char)(i + 97);

        for(int j = 0; j < 10; j++)
            chars[1][j] = (char)(j + 97 + 10);

        for(int k = 0; k < 6; k++)
            chars[2][k] = (char)(k + 97 + 20);

        movedown = false;
    }

    public void ShowKeyBoard(Graphics g)
    {
        if(movedown)
        {
            if(kby < owner.getHeight())
            {
                moving = true;
                kby += 20;
            } else
            {
                moving = false;
                movedown = false;
                owner.page = 9;
            }
        } else
        if(kby > owner.getHeight() / 2 - kbh / 2)
        {
            moving = true;
            kby -= 20;
        } else
        {
            moving = false;
        }
        if(!moving)
        {
            g.setColor(255, 255, 255);
            g.drawString("请输入你的名字", 27, 160, 0x10 | 0x4);
        }
        g.drawImage(keyboardImg, kbx, kby, 20);
        g.setColor(255, 0, 0);
        if(curposy == 3 && curposx == 6)
            g.drawRect(kbx + 90, kby + 40, 14, 13);
        else
        if(curposy == 3 && curposx > 6)
            g.drawRect(kbx + 109, kby + 41, 27, 10);
        else
        if(curposy == 99 && curposx == 99)
            g.drawRect(kbx + 90, kby + 60, 66, 11);
        else
            g.drawRect(kbx + curx, kby + cury, curw, curh);
        g.setFont(Font.getFont(0, 0, 8));
        g.drawString(nameStr.toUpperCase(), 24 + kbx, 58 + kby, 20);
    }

    public void HandleKeyInput(int i)
    {
        if(!moving)
        {
            if(i == owner.fireKey)
            {
                if(curposy == 3 && curposx == 6)
                {
                    if(nameStr.length() > 0)
                        nameStr = new String(nameStr.toCharArray(), 0, nameStr.length() - 1);
                    return;
                }
                if(curposy == 3 && curposx > 6)
                {
                    nameStr = "";
                    return;
                }
                if(curposy == 99 && curposx == 99)
                {
                    if(nameStr.length() > 0)
                    {
                        movedown = true;
                        number = owner.level;
                        Score = owner.Score;
                        saveRecords();
                        object.getRecords();
                    }
                    return;
                }
                if(nameStr.length() < 8)
                {
                    if(curposy == 0)
                        nameStr = nameStr + curposx;
                    else
                        nameStr = nameStr + chars[curposy - 1][curposx];
                    return;
                } else
                {
                    return;
                }
            }
            if(i == owner.leftKey)
                if(curposx > 0 && curposy < 4)
                {
                    curx -= 2 + curw;
                    curposx--;
                    return;
                } else
                {
                    return;
                }
            if(i == owner.rightKey)
            {
                if(curposy == 3 && curposx >= 7)
                    return;
                if(curposx < 9 && curposy < 4)
                {
                    curx += 2 + curw;
                    curposx++;
                    return;
                } else
                {
                    return;
                }
            }
            if(i == owner.upKey)
            {
                if(curposy == 99 && curposx == 99)
                {
                    curposy = 3;
                    curposx = 6;
                    cury -= 3 + curh;
                    return;
                }
                if(curposy > 0 && curposy < 4)
                {
                    cury -= 3 + curh;
                    curposy--;
                    return;
                } else
                {
                    return;
                }
            }
            if(i == owner.downKey)
            {
                if(curposy < 3)
                {
                    cury += 3 + curh;
                    curposy++;
                    return;
                }
                if(curposy == 3 && curposx >= 6)
                {
                    if(curposx > 6)
                        curx -= 2 + curw;
                    cury += 3 + curh;
                    curposy = 99;
                    curposx = 99;
                    return;
                } else
                {
                    return;
                }
            }
        }
    }

    static void saveRecords()
    {
        int i = 0;
        datatosave = false;
        i = Score;
        nameval = nameStr;
        try
        {
            try
            {
                String s = nameval;
                if(number == 1)
                    recordscore = RecordStore.openRecordStore(RSname[0], false);
                else
                if(number == 2)
                    recordscore = RecordStore.openRecordStore(RSname[1], false);
                else
                if(number == 3)
                    recordscore = RecordStore.openRecordStore(RSname[2], false);
                else
                if(number > 3 && number <= 10)
                    recordscore = RecordStore.openRecordStore(RSname[number - 1], false);
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
                ByteArrayOutputStream bytearrayoutputstream1 = new ByteArrayOutputStream();
                DataOutputStream dataoutputstream1 = new DataOutputStream(bytearrayoutputstream1);
                try
                {
                    dataoutputstream.writeBoolean(true);
                    dataoutputstream.writeUTF(s);
                    namedata = bytearrayoutputstream.toByteArray();
                    dataoutputstream1.writeBoolean(true);
                    dataoutputstream1.writeInt(i);
                    scoredata = bytearrayoutputstream1.toByteArray();
                    if(recordscore.getNumRecords() == 0)
                    {
                        scoreid = recordscore.addRecord(scoredata, 0, scoredata.length);
                        nameid = recordscore.addRecord(namedata, 0, namedata.length);
                    } else
                    {
                        recordscore.closeRecordStore();
                        if(number == 1)
                        {
                            RecordStore.deleteRecordStore(RSname[0]);
                            recordscore = RecordStore.openRecordStore(RSname[0], true);
                        } else
                        if(number == 2)
                        {
                            RecordStore.deleteRecordStore(RSname[1]);
                            recordscore = RecordStore.openRecordStore(RSname[1], true);
                        } else
                        if(number == 3)
                        {
                            RecordStore.deleteRecordStore(RSname[2]);
                            recordscore = RecordStore.openRecordStore(RSname[2], true);
                        } else
                        if(number > 3 && number <= 10)
                        {
                            RecordStore.deleteRecordStore(RSname[number - 1]);
                            recordscore = RecordStore.openRecordStore(RSname[number - 1], true);
                        }
                        scoreid = recordscore.addRecord(scoredata, 0, scoredata.length);
                        nameid = recordscore.addRecord(namedata, 0, namedata.length);
                    }
                    scoredata = recordscore.getRecord(scoreid);
                    DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(scoredata));
                    boolean flag = datainputstream.readBoolean();
                    scoreval = datainputstream.readInt();
                    namedata = recordscore.getRecord(nameid);
                    DataInputStream datainputstream1 = new DataInputStream(new ByteArrayInputStream(namedata));
                    boolean flag1 = datainputstream1.readBoolean();
                    nameval = datainputstream1.readUTF();
                    recordscore.closeRecordStore();
                }
                catch(IOException ioexception) { }
            }
            catch(RecordStoreNotFoundException recordstorenotfoundexception) { }
        }
        catch(RecordStoreException recordstoreexception) { }
    }

}
