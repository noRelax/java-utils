package server;
import java.awt.*;

import javax.swing.*;


public class RecButton extends JButton {

    public RecButton(String label) {
        super(label); 
        setContentAreaFilled(false); 
    }
    public RecButton(String label,Icon i) {
        super(label,i); 
        setContentAreaFilled(false); 
    }
    
    protected void paintComponent(Graphics g) {
       if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(Color.yellow);
        }
        g.drawRect(0,0, getSize().width, getSize().height);
        super.paintComponent(g);
    }


   protected void paintBorder(Graphics g) {
       g.setColor(Color.orange);
       g.drawRect(0,0, getSize().width-1, getSize().height-1); 
       g.setColor(Color.blue);
       g.drawRect(1,1, getSize().width-3, getSize().height-3); 
       g.setColor(Color.RED);
       g.drawRect(2,2, getSize().width-5, getSize().height-5); 
   }

}

