/*****************************************************************************

 Description: The  MIDlet class
 Download by http://www.codefans.net
 Created By: Oscar Vivall 2006-01-09
 @file        Java3DMenu.java

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

public class Java3DMenu extends javax.microedition.midlet.MIDlet {
    public void startApp() {
        Display.getDisplay(this).setCurrent(new MenuCanvas(this));
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
