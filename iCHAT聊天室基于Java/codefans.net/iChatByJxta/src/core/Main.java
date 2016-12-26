/*
 * @(#)Main.java
 * Created on 2005-9-14
 * iChat LE. Copyright AllenStudio. All Rights Reserved
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package core;

import java.awt.Font;

import javax.swing.UIManager;

import ui.ConnectDialog;

/**
 * Entrance of iChat LE.
 * 
 * @author Allen Chue
 */
public class Main {

    /**
     * @param args
     */
public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel"); System.out.println("right"); 
            Font f = new Font("Dialog", Font.PLAIN, 12);
            UIManager.put("Label.font", f); 
            UIManager.put("Button.font", f);
            UIManager.put("TextField.font", f);
            UIManager.put("TextArea.font", f);
     
            ConnectDialog dialog = new ConnectDialog();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
        catch (Throwable t) {
            t.printStackTrace();
            System.out.println("Oops! Uncaught exception!");
            System.exit(-10);
        }
    }
}
