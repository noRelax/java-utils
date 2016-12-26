package player.module;
//download:http://www.codefans.net
import javax.swing.JLabel;
import java.io.File;
import player.skin.Skin;
import java.awt.*;
import javax.swing.*;
import player.skin.SkinListener;

public class ListItem extends JLabel implements SkinListener{
  private File file = null;
  private Color borderColor = new Color(0x00, 0x00, 0x00);
  private boolean selected = false;
  private Muisc muisc = null;

  public ListItem(String path){
    this(new File(path));
  }

  public ListItem(File file) {
    this.file = file;
    muisc = new Muisc(file);
    this.setFont(new Font("ËÎÌו",0,12));
    this.setPreferredSize(new Dimension(-1,20));
    this.setIcon(Skin.getImageIcon("Muisc.png",this));
    this.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
    if(muisc.getSinger().length() > 0)this.setText(muisc.getSinger() + " - " + muisc.getName());
    else this.setText(muisc.getName());
    this.setToolTipText("<html><body><table height=18 border=0 cellpadding=2 cellspacing=1 bgcolor=#000000><tr><td bgcolor=#FFFFFF>" + this.getText() + "</td></tr></table></body></html>");
  }

  protected void setSelected(boolean selected){
    this.selected = selected;
    this.repaint();
  }

  public File getFile(){
    return file;
  }

  public boolean isFile(){
    return file != null && file.isFile();
  }

  public Muisc getMuisc(){
    return muisc;
  }

  public void paintComponent(Graphics g) {
    Graphics2D cs = (Graphics2D) g;
    if (selected) {
      int width = this.getSize().width;
      int height = this.getSize().height;
      cs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
      int i = 1;
      int comparison = 0;
      cs.setColor(borderColor);
      cs.drawRect(0, 0, width - 1, height - 1);
      while (i < height - 1) {
        comparison = (i - 1) * 100 / (height - 3);
        if (comparison < 36) {
          cs.setColor(new Color(235 - (comparison + comparison * 2),235 - (comparison + comparison * 2),235 - (comparison + comparison * 2)));
          cs.drawLine(1, i, width - 2, i);
          if (i != 1) {
            cs.setColor(new Color(184 - comparison * 2,184 - comparison * 2,184 - comparison * 2));
            cs.drawLine(2, i, width - 3, i);
          }
        }
        else {
          cs.setColor(new Color(comparison - 36, comparison - 36,comparison - 36));
          cs.drawLine(1, i, width - 2, i);
        }
        i++;
      }
      cs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
    super.paintComponent(g);
  }

  public String getSkinName() {
    return "Muisc.png";
  }

  public void paintSkin(ImageIcon icon) {
    this.setIcon(icon);
  }
}
