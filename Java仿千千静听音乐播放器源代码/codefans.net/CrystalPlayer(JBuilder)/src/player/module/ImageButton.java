package player.module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import player.skin.SkinListener;
import player.skin.Skin;

public class ImageButton extends JButton implements MouseListener,SkinListener{
  private boolean entered = false;
  private boolean pressed = false;
  private ImageIcon icon = null;
  private String iconName = null;
  private float alpha = 0.7f;
  public ImageButton() {
    this(null);
  }

  public ImageButton(String iconName) {
    this(iconName, "");
  }

  public ImageButton(String iconName, String toolTip) {
    this.iconName = iconName;
    if(iconName != null)this.setIconImage(Skin.getImageIcon(iconName,this));
    this.addMouseListener(this);
    this.setContentAreaFilled(false);
    this.setToolTipText("<html><body><table height=18 border=0 cellpadding=2 cellspacing=1 bgcolor=#000000><tr><td bgcolor=#FFFFFF>" + toolTip + "</td></tr></table></body></html>");
  }

  public void setIconImage(ImageIcon icon){
    this.icon = icon;
    this.repaint();
  }

  public void setAlpha(float alpha) {
    this.alpha = alpha;
    this.repaint();
  }

  public float getAlpha() {
    return alpha;
  }


  public void paintBorder(Graphics g) {}

  public void paintComponent(Graphics g) {
    Graphics2D cs = (Graphics2D)g;
    if(icon != null){
      if(entered){
        if(pressed)cs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        else cs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f));
      }
      else cs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
      icon.paintIcon(this,cs,0,0);
    }
  }

  public void mouseClicked(MouseEvent e) {}

  public void mousePressed(MouseEvent e) {
    pressed = true;
    this.repaint();
  }

  public void mouseExited(MouseEvent e) {
    entered = false;
    this.repaint();
  }

  public void mouseEntered(MouseEvent e) {
    entered = true;
    this.repaint();
  }

  public void mouseReleased(MouseEvent e) {
    pressed = false;
    this.repaint();
  }

  public String getSkinName() {
    return iconName;
  }

  public void paintSkin(ImageIcon icon) {
    this.setIconImage(icon);
  }
}
