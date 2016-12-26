/*****************************************************************************

 Description: Used to create a star scrolling effect.
 Download by http://www.codefans.net
 Created By: Oscar Vivall 2006-01-09
 @file        StarHScroller.java

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
import java.util.*;

public class StarHScroller {

    public final int NUM_STARS = 30;
    public final int WIDTH, HEIGHT;
    
    public int []posX = new int[NUM_STARS];
    public int []posY = new int[NUM_STARS];
    public int []size = new int[NUM_STARS];

    Random r = new Random();

    public StarHScroller(int w, int h){
        WIDTH = w;
        HEIGHT = h;
        
        for(int i=0; i<NUM_STARS; i++){
            size[i] = r.nextInt(2)+1;
            posX[i] = r.nextInt(WIDTH);
            posY[i] = r.nextInt(HEIGHT) + 1;
        }
    }

    private void initStar(int index){
        size[index] = r.nextInt(2)+1;
        posX[index] = -10 + r.nextInt(10);;
        posY[index] = r.nextInt(HEIGHT) + 1;
    }

    public void draw(Graphics g){
        g.setColor(0xFFFFFF);
        for(int i=0; i<NUM_STARS; i++){
            g.fillRect(posX[i], posY[i], size[i], size[i]);
            switch(size[i]){
                case 1:
                    posX[i] +=1;
                    break;
                case 2:
                    posX[i] +=2;
                    break;
            }
            if(posX[i] > WIDTH){
                initStar(i);
            }
        }
    }
}
