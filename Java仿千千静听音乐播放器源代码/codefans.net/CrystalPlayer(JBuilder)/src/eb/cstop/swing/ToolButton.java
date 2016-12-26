package eb.cstop.swing;
//download:http://www.codefans.net
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class ToolButton extends JButton implements MouseListener {
	private boolean entered = false;
	private boolean pressed = false;
	private Color topBorder = new Color(0xff,0xff,0xff);
	private Color bottomBorder = new Color(0x87,0x87,0x87);

	private Color pressedTopBorder = new Color(0x87,0x87,0x87);
	private Color pressedBottomBorder = new Color(0xff,0xff,0xff);
	private Color pressedBG = new Color(0xa5,0xa5,0xa5);

	public ToolButton() {
		this("");
	}

	public ToolButton(String text) {
		this(text, null);
	}

	public ToolButton(String text, ImageIcon icon) {
		this.addMouseListener(this);
		this.setText(text);
		if (icon != null)this.setIcon(icon);
		this.setContentAreaFilled(false);
		this.setFont(new Font("ËÎÌו", 0, 12));
	}

	public void paintBorder(Graphics g) {}
	public void paintComponent(Graphics g) {
		Graphics2D cs = (Graphics2D) g;
		if(entered && this.isEnabled()){
			if(pressed)cs.setColor(pressedTopBorder);
			else cs.setColor(topBorder);
			cs.drawLine(1, 1, this.getWidth() - 2, 1);
			cs.drawLine(1, 1, 1, this.getHeight() - 2);

			if(pressed)cs.setColor(pressedBottomBorder);
			else cs.setColor(bottomBorder);
			cs.drawLine(this.getWidth() - 2, 1, this.getWidth() - 2, this.getHeight() - 2);
			cs.drawLine(1, this.getHeight() - 2, this.getWidth() - 2, this.getHeight() - 2);
			if(pressed){
				cs.setColor(pressedBG);
				cs.fillRect(2, 2, this.getWidth() - 4, this.getHeight() - 4);
			}
		}
		super.paintComponent(g);
	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		this.pressed = true;
		this.repaint();
	}

	public void mouseExited(MouseEvent e) {
		this.entered = false;
		this.repaint();
	}

	public void mouseEntered(MouseEvent e) {
		this.entered = true;
		this.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		this.pressed = false;
		this.repaint();
	}

	public Color getBottomBorderColor() {
		return bottomBorder;
	}

	public void setBottomBorderColor(Color bottomBorder) {
		this.bottomBorder = bottomBorder;
	}

	public Color getPressedBGColor() {
		return pressedBG;
	}

	public void setPressedBGColor(Color pressedBG) {
		this.pressedBG = pressedBG;
	}

	public Color getPressedBottomBorderColor() {
		return pressedBottomBorder;
	}

	public void setPressedBottomBorderColor(Color pressedBottomBorder) {
		this.pressedBottomBorder = pressedBottomBorder;
	}

	public Color getPressedTopBorderColor() {
		return pressedTopBorder;
	}

	public void setPressedTopBorderColor(Color pressedTopBorder) {
		this.pressedTopBorder = pressedTopBorder;
	}

	public Color getTopBorderColor() {
		return topBorder;
	}

	public void setTopBorderColor(Color topBorder) {
		this.topBorder = topBorder;
	}
}
