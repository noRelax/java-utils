/*
 * Button.java
 *
 * Created on 2005年12月27日, 下午 2:15
 * Download:http://www.codefans.net
 */

package marsping.component.button;

import marsping.component.ElevatorController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author MarsPing
 */
public class Button extends JButton {
    protected ElevatorController controller;
    
    /** Creates a new instance of Button */
    public Button() {
        setBackground(new Color(204, 204, 204));
        setFont(new Font("Dialog", 1, 18));
        setPreferredSize(new Dimension(45,45));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        setMargin(new Insets(2,2,2,2));
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                buttonMouseClicked(evt);
            }
        });
    }
    
    public Button(ElevatorController controller) {
        this();
        this.controller = controller;
    }
    
    protected void buttonMouseClicked(MouseEvent evt) {
        setBackground(new Color(254, 180, 82));
    }
    
    public void resetButton() {
        setBackground(new Color(204, 204, 204));
    }
}
