package eb.cstop.swing;
//download:http://www.codefans.net
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class ProgressBar extends JLabel implements MouseListener,MouseMotionListener, MouseWheelListener {
	private int value = 0;
	private int minLimit = 0;
	private int maxLimit = 100;
	private float alpha = 0.7f;
	private Color border = new Color(0, 0, 0);
	private Color color = new Color(250, 160, 0);
	private ArrayList changeListener = new ArrayList();
	private ProgressBarEvent event = new ProgressBarEvent();

	public ProgressBar() {
		this.setPreferredSize(new Dimension(-1, 5));
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


	public void addProgressBarListener(ProgressBarListener listener) {
		changeListener.add(listener);
	}

	public boolean removeProgressBarListener(ProgressBarListener listener) {
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
		this.repaint();
		ProgressBarListener listener = null;
		event.setValue(value);
		event.setComparison((int)(((float) value / maxLimit) * 100.0f));
		for (int i = 0; i < changeListener.size(); i++) {
			listener = (ProgressBarListener) changeListener.get(i);
			event.setSource(listener);
			listener.valueChanged(event);
		}
	}

	public int getValue() {
		return value;
	}

	public void setBorderColor(Color border) {
		this.border = border;
		this.repaint();
	}

	public Color getBorderColor() {
		return border;
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
		int width = this.getSize().width;
		int valueWidth = (int) (((float) value / maxLimit) * (float) width);
		cs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
		cs.setColor(color);
		for(int i = 1;i < valueWidth - 1;i++){
			if(i%2 == 0){
				cs.drawLine(i, 1, i, 1);
				cs.drawLine(i, 3, i, 3);
			}
		}
		cs.setColor(border);
		cs.drawRect(0, 0, width - 1, 4);
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
