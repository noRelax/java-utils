package player.user;
//download:http://www.codefans.net
import java.awt.*;
import player.module.ImagePanel;
import player.skin.Skin;
import javax.swing.*;

public class CrystalVideo extends JDialog{
  private ImagePanel contentPanel = new ImagePanel("CrystalVideo.png");
  private CrystalPlayer frame = null;
  private JPanel videoPanel = new JPanel();
  public CrystalVideo(CrystalPlayer frame) {
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
    this.setUndecorated(true);
    this.getContentPane().setLayout(new BorderLayout());
    videoPanel.setBackground(new Color(238, 238, 238));
    videoPanel.setEnabled(true);
    videoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    videoPanel.setLayout(new BorderLayout());
    this.getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(new BorderLayout());
    contentPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    contentPanel.add(videoPanel, BorderLayout.CENTER);
    this.setSize(300, 281);
  }

  public void addVideo(Component component){
    videoPanel.removeAll();
    this.setVisible(component != null);
    if(component != null)videoPanel.add(component, BorderLayout.CENTER);
    videoPanel.updateUI();
  }
}
