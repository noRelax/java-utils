package eb.cstop.swing;
//download:http://www.codefans.net
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LButton extends JButton implements MouseListener{
	private final Font font = new Font("ËÎÌו",0,12);
	private boolean mouseOn = false;
	private boolean mouseDown = false;
	public LButton(){
		this.setButton();
	}
	public LButton(String text){
		super(text);
		this.setButton();
	}
	public LButton(ImageIcon icon){
		this.setIcon(icon);
		this.setButton();
	}
	public LButton(String text,ImageIcon icon){
		super(text);
		this.setIcon(icon);
		this.setButton();
	}
	private void setButton(){
		this.setFont(font);
		this.addMouseListener(this);
		this.setForeground(new Color(0,70,150));
	}
	public void paintBorder(Graphics g){
		this.setContentAreaFilled(false);
                g.setColor(new Color(255,255,255));
                g.fillRect(3,3,this.getSize().width - 5,this.getSize().height - 5);
		if(!this.mouseDown){
			if(!this.mouseOn){
				g.setColor(new Color(160,160,160));
			}
			else{
				g.setColor(new Color(180,180,180));
			}
			g.drawLine(3,2,this.getSize().width - 3,2);//TopLine
			g.drawLine(3,this.getSize().height - 3,this.getSize().width - 3,this.getSize().height - 3);//BottomLine
			g.drawLine(2,3,2,this.getSize().height - 4);//LeftLine
			g.drawLine(this.getSize().width - 2,3,this.getSize().width - 2,this.getSize().height - 4);//RightLine
			int height = this.getSize().height - 5;
			int row = 3;
			while(row <= height){
				int now = (row * 100)/height;
				if(now <= 35){
					if(!mouseOn){
						g.setColor(new Color(245 - now,245 - now,245 - now));
						}
					else{
						g.setColor(new Color(255 - now,255 - now,255 - now));
					}
					g.drawLine(4,row,this.getSize().width - 4,row);
				}
				else{
					if(!mouseOn){
						g.setColor(new Color(140 + now,140 + now,140 + now));
					}
					else{
						g.setColor(new Color(150 + now,150 + now,150 + now));
					}
					g.drawLine(4,row,this.getSize().width - 4,row);
				}
				row++;
			}
		}
		else{
			g.setColor(new Color(140,140,140));
			g.drawLine(3,2,this.getSize().width - 3,2);
			g.drawLine(3,this.getSize().height - 3,this.getSize().width - 3,this.getSize().height - 3);
			g.drawLine(2,3,2,this.getSize().height - 4);
			g.drawLine(this.getSize().width - 2,3,this.getSize().width - 2,this.getSize().height - 4);
			int height = this.getSize().height - 5;
			int row = 3;
			while(row <= height){
				int now = (row * 100)/height;
				if(now <= 35){
					g.setColor(new Color(210 - now,210 - now,210 - now));
					g.drawLine(4,row,this.getSize().width - 4,row);
				}
				else{
					g.setColor(new Color(110 + now,110 + now,110 + now));
					g.drawLine(4,row,this.getSize().width - 4,row);
				}
				row++;
			}
		}
		super.paintComponent(g);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){
		this.mouseOn = true;
		this.setForeground(new Color(50,130,210));
		this.repaint();
	}
	public void mouseExited(MouseEvent e){
		this.mouseOn = false;
		this.setForeground(new Color(0,70,150));
		this.repaint();
	}
	public void mousePressed(MouseEvent e){
		this.mouseDown = true;
		this.repaint();
	}
	public void mouseReleased(MouseEvent e){
		this.mouseDown = false;
		this.repaint();
	}
}
