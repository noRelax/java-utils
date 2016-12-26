package player.module;
//download:http://www.codefans.net
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import player.user.*;

public class VolumeBar extends JLabel implements MouseListener,MouseMotionListener, MouseWheelListener {
	private int value = 0;
	private int minLimit = 0;
	private int maxLimit = 100;
	private float alpha = 0.7f;
	private Color color = new Color(250, 160, 0);
	private ArrayList changeListener = new ArrayList();
	private VolumeBarEvent event = new VolumeBarEvent();
	public Shape shape = null;

	public VolumeBar() {
		this.setPreferredSize(new Dimension(39, 23));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
		this.repaint();
	}

	public float getAlpha() {
		return alpha;
	}


	public void addVolumeBarListener(VolumeBarListener listener) {
		changeListener.add(listener);
	}

	public boolean removeVolumeBarListener(VolumeBarListener listener) {
		return changeListener.remove(listener);
	}

	public void setMinLimt(int value) {
		if (value > 0)minLimit = value;
		else minLimit = 0;
	}

	public int getMinLimt() {
		return minLimit;
	}

	public void setMaxLimit(int value) {
		if (value > 0)maxLimit = value;
		else maxLimit = 100;
	}

	public int getMaxLimit() {
		return maxLimit;
	}

	public void setValueForChange(int value) {
		if (value == this.value)return;
		if (value < minLimit)return;
		else if (value > maxLimit)return;
		if (value >= minLimit)this.value = value;
		else value = 0;
		this.repaint();
	}

	public void setValue(int value) {
		if (value == this.value)return;
		if (value < minLimit)return;
		else if (value > maxLimit)return;
		if (value >= minLimit)this.value = value;
		else value = 0;
		VolumeBarListener listener = null;
		event.setValue(value);
		event.setComparison((int)(((float) value / maxLimit) * 100.0f));
		for (int i = 0; i < changeListener.size(); i++) {
			listener = (VolumeBarListener) changeListener.get(i);
			event.setSource(listener);
			listener.valueChanged(event);
		}
		this.repaint();
	}

	public int getValue() {
		return value;
	}

	public void setColor(Color color) {
		this.color = color;
		this.repaint();
	}

	public Color getColor() {
		return color;
	}

	public void paintBorder(Graphics g) {
		Graphics2D cs = (Graphics2D) g;
		cs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
		cs.setColor(color);
		int comparison = event.getComparison()/10;
		boolean isFulled = false;
		for(int i = 0;i < 10;i++){
			isFulled = comparison > i;
			if(!isFulled)cs.drawRect(i * 4,(23 - (i + 3)),2,i + 2);
			else cs.fillRect(i * 4,(23 - (i + 3)),3,i + 3);
		}
		if(event.getComparison() < 10)cs.drawString("00" + event.getComparison() + "%",8,9);
		else if(event.getComparison() < 100)cs.drawString("0" + event.getComparison() + "%",8,9);
		else cs.drawString(event.getComparison() + "%",8,9);
	}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			int comparison = (int) (((float) e.getX() / (this.getSize().width - 2)) * (float) 100);
			this.setValue((int) (((float) comparison / 100) * (float) maxLimit));
		}
	}

	public void mouseReleased(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {
		int comparison = (int) (((float) e.getX() / (this.getSize().width - 2)) * (float) 100);
		this.setValue((int) (((float) comparison / 100) * (float) maxLimit));
	}

	public void mouseMoved(MouseEvent e) {}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getUnitsToScroll() > 0)this.setValue(value - 2);
		else this.setValue(value + 2);
	}
}
