// Javano.java
//Download:http://www.codefans.net
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Javano extends JApplet
{
   Keyboard keyboard;
   JComboBox instruments;

   public void init ()
   {
      JPanel panel = new JPanel ();
      panel.add (new JLabel ("Instruments:"));

      instruments = new JComboBox ();
      panel.add (instruments);

      getContentPane ().add (panel, BorderLayout.NORTH);

      keyboard = new Keyboard ();

      getContentPane ().add (keyboard, BorderLayout.SOUTH);
   }

   public void start ()
   {
      keyboard.turnOn ();

      DefaultComboBoxModel dcbm;
      dcbm = new DefaultComboBoxModel (keyboard.getInstruments ());
      instruments.setModel (dcbm);

      ActionListener al;
      al = new ActionListener ()
           {
               public void actionPerformed (ActionEvent e)
               {
                  JComboBox cb = (JComboBox) e.getSource ();

                  keyboard.chooseInstrument (cb.getSelectedIndex ());
               }
           };
      instruments.addActionListener (al);
   }

   public void stop ()
   {
      keyboard.turnOff ();
   }
}
