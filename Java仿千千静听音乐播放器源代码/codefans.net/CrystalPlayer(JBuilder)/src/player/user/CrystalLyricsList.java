package player.user;

import javax.swing.*;
import java.awt.*;
import player.module.*;
import player.skin.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import eb.cstop.swing.*;

public class CrystalLyricsList extends RectangleDialog implements ActionListener,MouseListener{
  private ImagePanel contentPanel = new ImagePanel("CrystalLyricsList.png");
  private JScrollPane scrollPane = new JScrollPane();
  private LyricsSelecterModel model = new LyricsSelecterModel();
  private JList list = new JList(model);
  private JPanel actionPanel = new JPanel();
  private ImageButton okAction = new ImageButton("ok.png");
  private ImageButton closeAction = new ImageButton("exit.png");
  private int index = -1;
  private CrystalPlayer frame = null;
  private JLabel message = new JLabel("请选择你需要的歌词");
  public CrystalLyricsList(CrystalPlayer frame) {
    super(frame);
    this.frame = frame;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void addItem(LyricsSelecter item){
    model.addElement(item);
    list.setSelectedIndex(0);
  }

  public int showSelectList(){
    this.setLocation(frame.getLocation().x + 201,frame.getLocation().y);
    this.setVisible(true);
    this.toFront();
    return index;
  }

  public int getSelectedIndex(){
    return index;
  }

  public void submit(){
    index = list.getSelectedIndex();
    this.reset();
  }

  public void close(){
    index = -1;
    this.reset();
  }

  private void reset(){
    this.setVisible(false);
    model.removeAll();
  }

  private void jbInit() throws Exception {
    list.addMouseListener(this);
    this.setUndecorated(true);
    list.setCellRenderer(model);
    this.setModal(true);
    closeAction.addActionListener(this);
    okAction.addActionListener(this);
    contentPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    contentPanel.setLayout(new BorderLayout());
    this.getContentPane().setLayout(new BorderLayout());
    actionPanel.setOpaque(false);
    actionPanel.setRequestFocusEnabled(false);
    actionPanel.setLayout(new XYLayout());
    message.setHorizontalAlignment(SwingConstants.CENTER);
    this.getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.add(scrollPane, BorderLayout.CENTER);
    contentPanel.add(actionPanel,  BorderLayout.SOUTH);
    scrollPane.getViewport().add(list, null);
    actionPanel.add(closeAction, new XYConstraints(100, 5, 50, 15));
    actionPanel.add(okAction, new XYConstraints(40, 5, 50, 15));
    contentPanel.add(message, BorderLayout.NORTH);
    this.setSize(200, 150);
  }

  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == okAction)this.submit();
    else this.close();
  }

  public void mouseClicked(MouseEvent e){
    if(e.getSource() == list && e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2){
      if(model.getSize() > 0){
        this.submit();
      }
    }
  }
}
