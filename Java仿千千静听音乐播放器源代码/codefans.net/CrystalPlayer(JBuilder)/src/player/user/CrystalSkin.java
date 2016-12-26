package player.user;

import java.awt.*;
import player.module.ImagePanel;
import javax.swing.*;
import player.module.LyricsModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import player.module.LyricsItem;
import player.skin.Skin;
import java.io.File;

public class CrystalSkin extends JDialog implements ListSelectionListener{
  private ImagePanel contentPanel = new ImagePanel("CrystalList.png");
  private JScrollPane scrollPane = new JScrollPane();
  private CrystalPlayer frame = null;
  private LyricsModel model = new LyricsModel();
  private JList list = new JList(model);
  private LyricsItem defaultItem = new LyricsItem("Ä¬ÈÏÆ¤·ô",SwingConstants.LEFT);
  private boolean isActive = false;
  public CrystalSkin(CrystalPlayer frame) {
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
    model.addElement(defaultItem);
    list.addListSelectionListener(this);
    list.setCellRenderer(model);
    list.setSelectedIndex(0);
    this.setUndecorated(true);
    this.getContentPane().setLayout(new BorderLayout());
    list.setBackground(new Color(238, 238, 238));
    this.getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(new BorderLayout());
    contentPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    contentPanel.add(scrollPane, BorderLayout.CENTER);
    scrollPane.getViewport().add(list, null);
    this.setSize(200, 200);
    isActive = true;
  }

  public void refresh(){
    this.setVisible(!this.isVisible());
    if(this.isVisible()){
      int index = list.getSelectedIndex();
      isActive = false;
      model.removeAllElements();
      model.addElement(defaultItem);
      File file = new File("Skin");
      File[] files = file.listFiles();
      for(int i=0;i<files.length;i++){
        file = files[i];
        if(file.isDirectory())model.addElement(new LyricsItem(file.getName(),SwingConstants.LEFT));
      }
      list.setSelectedIndex(index);
      isActive = true;
    }
  }

  public void setSkinByIndex(int index){
    if(index + 1 > model.getSize())index = model.getSize() - 1;
    list.setSelectedIndex(index);
    LyricsItem item = (LyricsItem)model.getElementAt(index);
    Skin.setCatalogue(item.getText());
  }

  public void valueChanged(ListSelectionEvent e) {
    if(!e.getValueIsAdjusting() && isActive){
      if(list.getSelectedIndex() == 0)Skin.setCatalogue(null);
      else this.setSkinByIndex(list.getSelectedIndex());
    }
  }
}
