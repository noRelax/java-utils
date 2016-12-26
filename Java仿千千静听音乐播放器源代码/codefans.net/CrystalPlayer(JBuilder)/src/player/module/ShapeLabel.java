package player.module;

import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.event.*;

public class ShapeLabel extends JLabel implements MouseListener{
  public String text = "";
  public Font font = new Font("ËÎÌו",0,12);
  public TextLayout layout = null;
  public Shape shape = null;
  private int drawX = 0;
  private int drawWidth = -1;
  private boolean exited = true;
  private float alpha = 0.75f;
  private BasicStroke stroke = new BasicStroke(2.4f,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL);
  public ShapeLabel(){
    this(null);
  }

  public ShapeLabel(String text){
    this(text,true);
  }

  public ShapeLabel(String text,boolean isScroll){
    this.setString(text);
    this.addMouseListener(this);
    if(isScroll)new ShapeThread(this);
  }

  public void setAlpha(float alpha) {
    this.alpha = alpha;
    this.repaint();
  }

  public float getAlpha() {
    return alpha;
  }

  public void setDrawFont(Font font) {
    this.font = font;
    this.repaint();
  }

  public Font getDrawFont() {
    return font;
  }

  public void paintBorder(Graphics g){
    Graphics2D cs = (Graphics2D)g;
    cs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
    cs.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    FontRenderContext context = cs.getFontRenderContext();
    layout = new TextLayout(text,font,context);
    shape = layout.getOutline(AffineTransform.getTranslateInstance(drawX,12));
    drawWidth = shape.getBounds().width;
    cs.setColor(Color.white);
    cs.setStroke(stroke);
    cs.draw(shape);
    cs.setColor(Color.black);
    cs.fill(shape);
  }

  public int getDrawWidth(){
    return drawWidth;
  }

  public boolean isExited(){
    return exited;
  }

  public void setDrawX(int drawX){
    this.drawX = drawX;
    this.repaint();
  }

  public int getDrawX(){
    return drawX;
  }

  public void setString(String text){
    this.text = (text != null)?text:"";
    this.setToolTipText("<html><body><table height=18 border=0 cellpadding=2 cellspacing=1 bgcolor=#000000><tr><td bgcolor=#FFFFFF>" + text + "</td></tr></table></body></html>");
    this.repaint();
  }

  public String getString(){
    return text;
  }

  public void mouseClicked(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {
    exited = true;
  }
  public void mouseEntered(MouseEvent e) {
    exited = false;
  }
  public void mouseReleased(MouseEvent e) {}
}
