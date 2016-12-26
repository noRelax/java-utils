package eb.cstop.swing;
//download:http://www.codefans.net
import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CrystalButton extends JButton implements MouseListener{

	private float alpha = 1.0f;
	private Color borderColor = new Color(0x00,0x00,0x00);
	private Color exitedBorderColor = new Color(0x33,0x33,0x33);
	private boolean exited = false;
	private boolean pressed = false;
	public CrystalButton() {
		this("");
	}

	public CrystalButton(String text) {
		this(text, null);
	}

	public CrystalButton(String text, ImageIcon icon) {
		this(text,icon,"");
	}

	public CrystalButton(String text, ImageIcon icon, String toolTip) {
		this.addMouseListener(this);
		this.setText(text);
		if (icon != null)this.setIcon(icon);
		this.setContentAreaFilled(false);
		this.setFont(new Font("ËÎÌו", 0, 12));
		this.setForeground(Color.white);
		this.setToolTipText(toolTip);
	}

	public void setAlpha(float alpha){
                this.alpha = alpha;
                this.repaint();
        }

        public float getAlpha(){
                return alpha;
        }

	public void paintBorder(Graphics g) {}

	public void paintComponent(Graphics g) {
		Graphics2D cs = (Graphics2D) g;
		int width = this.getSize().width;
		int height = this.getSize().height;
		cs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
		int i = 1;
		int comparison = 0;
		if(exited && !pressed)cs.setColor(exitedBorderColor);
		else cs.setColor(borderColor);
		cs.drawRect(0, 0, width - 1, height - 1);
		while(i < height - 1){
			comparison = (i-1)*100/(height - 3);
			if(comparison < 36){
				if(exited && !pressed)cs.setColor(new Color(255 - (comparison + comparison*2),255 - (comparison + comparison*2),255 - (comparison + comparison*2)));
				else cs.setColor(new Color(235 - (comparison + comparison*2),235 - (comparison + comparison*2),235 - (comparison + comparison*2)));
				cs.drawLine(1, i, width - 2, i);
				if(i != 1){
					if(exited && !pressed)cs.setColor(new Color(204 - comparison*2,204 - comparison*2,204 - comparison*2));
					else cs.setColor(new Color(184 - comparison*2,184 - comparison*2,184 - comparison*2));
					cs.drawLine(2, i, width - 3, i);
				}
			}
			else{
				if(exited && !pressed)cs.setColor(new Color(comparison - 16,comparison - 16,comparison - 16));
				else cs.setColor(new Color(comparison - 36,comparison - 36,comparison - 36));
				cs.drawLine(1, i, width - 2, i);
			}
			i++;
		}
		cs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
		super.paintComponent(g);
		cs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		pressed = true;
		this.repaint();
	}
	public void mouseExited(MouseEvent e) {
		exited = false;
		this.repaint();
	}
	public void mouseEntered(MouseEvent e) {
		exited = true;
		this.repaint();
	}
	public void mouseReleased(MouseEvent e) {
		pressed = false;
		this.repaint();
	}
}
