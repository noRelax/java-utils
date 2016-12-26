package eb.cstop.swing;
//download:http://www.codefans.net
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JDialog;
import java.awt.Frame;

public class RectangleDialog extends JDialog implements MouseListener,MouseMotionListener{

	private int beForX = 0;
	private int beForY = 0;

	public RectangleDialog(){
		this(null);
	}

        public RectangleDialog(Frame frame){
                super(frame);
                this.setUndecorated(true);
                this.addMouseListener(this);
                this.addMouseMotionListener(this);
        }

	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){
	    beForX = e.getX();
	    beForY = e.getY();
	}
	public void mouseReleased(MouseEvent e){}
	public void mouseDragged(MouseEvent e) {
		this.setLocation(this.getLocation().x + e.getX() - beForX,this.getLocation().y + e.getY() - beForY);
	}
	public void mouseMoved(MouseEvent e) {}
}
