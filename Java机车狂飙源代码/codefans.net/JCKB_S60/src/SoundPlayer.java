// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi

import com.nokia.mid.sound.Sound;
import java.io.DataInputStream;
import javax.microedition.midlet.MIDlet;

public class SoundPlayer
{

    static Sound signalSound;
    static Sound lapSound;
    static Sound fuelSound;
    static Sound bgSound;
    static Sound hallSound;
    static Sound hiscoreSound;
    int pickAlive;
    int placeAlive;

    public SoundPlayer(MIDlet midlet)
    {
        pickAlive = 0;
        placeAlive = 0;
        try
        {
            byte abyte0[] = new byte[1000];
            signalSound = GameSound(midlet, "/signal1.png", abyte0);
            lapSound = GameSound(midlet, "/lap1.png", abyte0);
            fuelSound = GameSound(midlet, "/fuel1.png", abyte0);
            bgSound = GameSound(midlet, "/bg1.png", abyte0);
            hallSound = GameSound(midlet, "/hall1.png", abyte0);
            hiscoreSound = GameSound(midlet, "/hiscore1.png", abyte0);

        }
        catch(Exception exception)
        {
           //System.out.println("bbbaaa");
            exception.printStackTrace();
        }
    }

    public static Sound GameSound(MIDlet midlet, String s, byte abyte0[])
        throws Exception
    {
        DataInputStream datainputstream = new DataInputStream(midlet.getClass().getResourceAsStream(s));
        int i = datainputstream.read(abyte0);
        byte abyte1[] = new byte[i];
        System.arraycopy(abyte0, 0, abyte1, 0, abyte1.length);
        Sound sound = new Sound(abyte1, 1);
        datainputstream.close();
        return sound;
    }

    public void stopSounds()
    {
        signalSound.stop();
        lapSound.stop();
        fuelSound.stop();
        bgSound.stop();
        hallSound.stop();
        hiscoreSound.stop();
    }

    public void playSignal()
    {
        play(signalSound);
    }

    public void playlapComplete()
    {
        stopSounds();
        play(lapSound);
    }

    public void playFuelSound()
    {
        stopSounds();
        play(fuelSound);
    }

    public void playBg()
    {
        play(bgSound);
    }

    public void playHall()
    {
        stopSounds();
        play(hallSound);
    }

    public void playHiscore()
    {
        stopSounds();
        play(hiscoreSound);
    }

    public void play(Sound sound)
    {
        if(sound.getState() != 0)
            sound.play(1);
    }
}
