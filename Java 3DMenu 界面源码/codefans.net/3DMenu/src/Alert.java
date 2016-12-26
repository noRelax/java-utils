/*****************************************************************************

 Description: Used to display some text on the screen
 Download by http://www.codefans.net
 Created By: Oscar Vivall 2006-01-09
 @file        Alert.java

 COPYRIGHT All rights reserved Sony Ericsson Mobile Communications AB 2004.
 The software is the copyrighted work of Sony Ericsson Mobile Communications AB.
 The use of the software is subject to the terms of the end-user license 
 agreement which accompanies or is included with the software. The software is 
 provided "as is" and Sony Ericsson specifically disclaim any warranty or 
 condition whatsoever regarding merchantability or fitness for a specific 
 purpose, title or non-infringement. No warranty of any kind is made in 
 relation to the condition, suitability, availability, accuracy, reliability, 
 merchantability and/or non-infringement of the software provided herein.

 *****************************************************************************/

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Alert implements Runnable{
    private String info;
    private boolean started = false;
    
    public Alert(){
    }
    
    public void setText(String s){
        info = null;
        info = new String(s);
    }

    public boolean isStarted(){
        return started;
    }
    
    public void start(){
        new Thread(this).start();
    }
    
    public void draw(Graphics g) {
        g.setColor(0xFF0000);
        g.drawString(info, 0, 0, 0);
    }
    
    public void run() {
        started = true;
        try{
            Thread.sleep(1200);
        }catch(Exception e){}
        started = false;
    }
    
}
