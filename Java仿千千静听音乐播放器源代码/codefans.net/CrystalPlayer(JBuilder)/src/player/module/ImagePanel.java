package player.module;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import player.skin.SkinListener;
import player.skin.Skin;

public class ImagePanel extends JPanel implements SkinListener{
  private ImageIcon icon = null;
  private String iconName = null;
  public ImagePanel() {
    this(null);
  }

  public ImagePanel(String iconName) {
    this.iconName = iconName;
    if(iconName != null)this.setIconImage(Skin.getImageIcon(iconName,this));
    this.setOpaque(false);
  }

  public void setIconImage(ImageIcon icon){
    this.icon = icon;
    this.repaint();
  }

  public void paintComponent(Graphics g){
    Graphics2D cs = (Graphics2D)g;
    if(icon != null)icon.paintIcon(this,cs,0,0);
    super.paintComponent(g);
  }

  public void paintSkin(ImageIcon icon) {
    this.setIconImage(icon);
  }

  public String getSkinName() {
    return iconName;
  }
}
