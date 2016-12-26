package player.user;
//download:http://www.codefans.net
import java.awt.*;
import player.module.ImagePanel;
import javax.swing.*;
import player.module.LyricsModel;
import player.module.LyricsItem;

public class CrystalLyrics extends JDialog{
  private ImagePanel contentPanel = new ImagePanel("CrystalLyrics.png");
  private JScrollPane scrollPane = new JScrollPane();
  private CrystalPlayer frame = null;
  private LyricsModel model = new LyricsModel();
  private JList list = new JList(model);
  private int scrollY = 0;
  private int maxY = 0;
  public CrystalLyrics(CrystalPlayer frame) {
    super(frame);
    this.frame = frame;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    list.setEnabled(false);
    list.setCellRenderer(model);
    this.setUndecorated(true);
    this.getContentPane().setLayout(new BorderLayout());
    list.setBackground(new Color(238, 238, 238));
    this.getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(new BorderLayout());
    contentPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    contentPanel.add(scrollPane, BorderLayout.CENTER);
    scrollPane.getViewport().add(list, null);
    this.setSize(350, 281);
  }

  public void addLyrics(LyricsItem item){
    model.addElement(item);
  }

  public void setSelectIndex(int index){
    list.setSelectedIndex(index);
    scrollY = (index + 1) * 20 - scrollPane.getSize().height + 3;
    maxY = scrollPane.getViewport().getViewSize().height - scrollPane.getSize().height + 3;
    if(scrollY < 0)scrollY = 0;
    else if(scrollY > maxY)scrollY = maxY;
    scrollPane.getViewport().setViewPosition(new Point(0,scrollY));
    scrollPane.repaint();
  }

  public void removeAllLyrics(){
    model.removeAllElements();
  }
}
