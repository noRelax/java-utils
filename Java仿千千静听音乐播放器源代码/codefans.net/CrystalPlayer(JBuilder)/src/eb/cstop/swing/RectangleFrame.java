package eb.cstop.swing;
//download:http://www.codefans.net
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class RectangleFrame extends JFrame implements MouseListener,MouseMotionListener{

	protected int beForX = 0;
	protected int beForY = 0;

	public RectangleFrame(){
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
