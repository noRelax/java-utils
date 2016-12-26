package player.module;
//download:http://www.codefans.net
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.*;

public class LyricsSelecter extends JLabel{
  private Color borderColor = new Color(0x00, 0x00, 0x00);
  private boolean selected = false;

  public LyricsSelecter(String name,String singer){
    String text = "";
    if(singer != null && singer.length() > 0)text = singer + " - ";
    if(name != null && name.length() > 0)name = name;
    else name = "";
    text += name;
    this.setText(text);
    this.setToolTipText("<html><body><table height=18 border=0 cellpadding=2 cellspacing=1 bgcolor=#000000><tr><td bgcolor=#FFFFFF>" + this.getText() + "</td></tr></table></body></html>");
    this.setPreferredSize(new Dimension(-1,20));
  }

  protected void setSelected(boolean selected){
    this.selected = selected;
    this.repaint();
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
}
