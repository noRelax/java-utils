package player.user;

import java.awt.*;
import player.skin.Skin;
import com.borland.jbcl.layout.*;
import eb.cstop.swing.*;
import player.module.*;
import eb.cstop.swt.*;
import javax.swing.*;
import java.awt.event.*;
import player.core.*;
import java.util.ArrayList;
import java.io.File;

public class CrystalPlayer extends RectangleFrame implements ProgressBarListener,VolumeBarListener,ActionListener,TrayIconListener{
  private ImagePanel contentPanel = new ImagePanel("BlackPlayer.png");
  private ImageButton doPlay = new ImageButton("Play.png","Play");
  private ImageButton doPause = new ImageButton("Pause.png","Pause");
  private ImageButton doStop = new ImageButton("Stop.png","Stop");
  private ImageButton doFile = new ImageButton("File.png","Files");
  private ImageButton doFront = new ImageButton("Front.png","Front");
  private ImageButton doNext = new ImageButton("Next.png","Next");
  private ImageButton doClose = new ImageButton("Close.png","Close");
  private ImageButton doMini = new ImageButton("Mini.png","Mini");
  private ImageButton doList = new ImageButton("List.png","FileList");
  private ImageButton doVideo = new ImageButton("Video.png","Video");
  private ImageButton doLyrics = new ImageButton("Lyrics.png","Lyrics");
  private ImageButton doSkin = new ImageButton("Skin.png","Skin");
  private TrayIcon trayIcon = TrayIcon.createTrayIcon();
  private ProgressBar progress = new ProgressBar();
  private ShapeLabel muiscName = new ShapeLabel("Cold Eyes - CrystalPlayer");
  private ShapeLabel time = new ShapeLabel("00 : 00 - 00 : 00",false);
  private ShapeLabel sampleRate = new ShapeLabel("00KHz",false);
  private ShapeLabel sample = new ShapeLabel("100kbps",false);
  private VolumeBar volume = new VolumeBar();
  private CrystalList list = new CrystalList(this);
  private CrystalLyrics lyrics = new CrystalLyrics(this);
  private CrystalVideo video = new CrystalVideo(this);
  private Core core = new Core(this);
  private CrystalLyricsList lyricsList = new CrystalLyricsList(this);
  private CrystalSkin skin = new CrystalSkin(this);
  private boolean next = true;
  private boolean isShowLyrics = true;
  static{
    Font font = new Font("ËÎÌו",0,12);
    UIManager.put("Button.font",font);
    UIManager.put("Label.font",font);
    UIManager.put("Diaglog.font",font);
    UIManager.put("List.font",font);
    UIManager.put("ComboBox.font",font);
    UIManager.put("CheckBox.font",font);
    UIManager.put("MenuItem.font",font);
    UIManager.put("ScrollBar.width",new Integer(0));
  }

  public static void main(String[] args){
    new CrystalPlayer();
  }

  public CrystalPlayer() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    progress.addProgressBarListener(this);
    volume.addVolumeBarListener(this);
    volume.setMaxLimit(100);
    new StatusAdapter(this);
    doSkin.addActionListener(this);
    doPlay.addActionListener(this);
    doPause.addActionListener(this);
    doStop.addActionListener(this);
    doFile.addActionListener(this);
    doFront.addActionListener(this);
    doNext.addActionListener(this);
    doClose.addActionListener(this);
    doMini.addActionListener(this);
    doList.addActionListener(this);
    doVideo.addActionListener(this);
    doLyrics.addActionListener(this);
    time.setDrawFont(new Font("System",1,12));
    time.setDrawX(2);
    time.setAlpha(0.6f);

    sampleRate.setDrawFont(new Font("Tahoma",0,9));
    sampleRate.setDrawX(5);

    sample.setDrawFont(new Font("Tahoma",0,9));
    sample.setDrawX(1);
    if(System.getProperty("java.version").startsWith("1.4"))volume.setMaxLimit(100);
    else volume.setMaxLimit(75);
    progress.setAlpha(0.6f);
    progress.setBorderColor(Color.white);
    progress.setColor(Color.white);
    trayIcon.setImageIcon(Skin.getDefaultImageIcon("TrayIcon.png"));
    trayIcon.setTrayIconToolTip("CrystalPlayer");
    trayIcon.addTrayIconListener(this);
    this.setResizable(false);
    this.getContentPane().setLayout(new BorderLayout());
    contentPanel.setLayout(new XYLayout());
    volume.setValue(volume.getMaxLimit());
    volume.setColor(Color.white);
    volume.setAlpha(0.75f);
    this.getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.add(doClose, new XYConstraints(189, 6, 5, 5));
    contentPanel.add(doMini, new XYConstraints(180, 6, 5, 5));
    contentPanel.add(muiscName, new XYConstraints(10, 7, 100, 16));
    contentPanel.add(time, new XYConstraints(10, 21, 100, 16));
    contentPanel.add(sampleRate, new XYConstraints(110, 7, 36, 16));
    contentPanel.add(sample, new XYConstraints(110, 21, 36, 16));
    contentPanel.add(volume, new XYConstraints(153, 14, -1, -1));
    contentPanel.add(doPlay, new XYConstraints(6, 48, 28, 14));
    contentPanel.add(doPause, new XYConstraints(38, 48, 30, 14));
    contentPanel.add(doStop, new XYConstraints(70, 48, 30, 14));
    contentPanel.add(doFile, new XYConstraints(102, 48, 30, 14));
    contentPanel.add(doFront, new XYConstraints(134, 48, 30, 14));
    contentPanel.add(doNext, new XYConstraints(166, 48, 30, 14));
    contentPanel.add(progress, new XYConstraints(6, 41, 188, -1));
    contentPanel.add(doVideo, new XYConstraints(6, 63, 60, 14));
    contentPanel.add(doLyrics, new XYConstraints(134, 63, 30, 14));
    contentPanel.add(doList, new XYConstraints(70, 63, 60, 14));
    contentPanel.add(doSkin, new XYConstraints(166, 63, 30, 14));
    this.setTitle("CrystalPlayer");
    this.setIconImage(Skin.getDefaultImageIcon("TrayIcon.png").getImage());
    this.setSize(200, 80);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2,(screenSize.height - frameSize.height) / 2);
    ArrayList muiscList = CrystalList.getList();
    File[] files = new File[muiscList.size()];
    for(int i=0;i<files.length;i++){
      files[i] = (File)muiscList.get(i);
    }
    list.addItems(files,false);
    this.setVisible(true);
    list.setLocation(this.getLocation().x ,this.getLocation().y + 81);
    list.setVisible(true);
    lyrics.setLocation(this.getLocation().x + 201,this.getLocation().y);
    video.setLocation(this.getLocation().x - 301,this.getLocation().y);
    skin.setLocation(this.getLocation().x + 201,this.getLocation().y);
    trayIcon.show();
  }

  public CrystalVideo getVideo(){
    return video;
  }

  public CrystalLyrics getLyrics(){
    return lyrics;
  }

  public boolean isNext() {
    return next;
  }

  public CrystalLyricsList getLyricsList() {
    return lyricsList;
  }

  public void readMuisc(Muisc muisc){
    muiscName.setDrawX(2);
    if(muisc != null){
      if (muisc.getSinger().length() > 0) muiscName.setString(muisc.getSinger() +" - " + muisc.getName());
      else muiscName.setString(muisc.getName());
      sample.setString(muisc.getBitRate());
      sampleRate.setString(muisc.getSampleRate());
    }
    else{
      time.setString("00 : 00 - 00 : 00");
      muiscName.setString("Cold Eyes - CrystalPlayer");
      sampleRate.setString("00KHz");
      sample.setString("000kbps");
    }
  }

  public void setMediaTime(String info){
    time.setString(info);
  }

  public ProgressBar getProgressBar(){
    return progress;
  }

  public void quit(){
    trayIcon.close();
    System.exit(0);
  }

  public void playSelected(){
    next = true;
    this.play(list.getSelectedItem());
  }

  public void next(){
    if(next && list.getRowCount() > 0){
      int index = list.getSelectedIndex();
      if(index + 1 < list.getRowCount())list.setSelectedIndex(index + 1);
      else list.setSelectedIndex(0);
      this.playSelected();
    }
  }

  public void front(){
    if(list.getRowCount() > 0){
      int index = list.getSelectedIndex();
      if(index < 1)list.setSelectedIndex(list.getRowCount() - 1);
      else list.setSelectedIndex(index - 1);
      this.playSelected();
    }
  }

  public void play(ListItem item){
    core.play(item);
  }

  public void stop(){
    next = false;
    core.stop();
  }

  public void valueChanged(VolumeBarEvent e){
    core.setVolume((float)e.getValue()/100);
  }

  public void valueChanged(ProgressBarEvent e){
    core.setMediaSeconds(e.getValue());
  }

  public boolean isShowLyrics(){
    return isShowLyrics;
  }

  public void trayIconClicked(TrayIconEvent e) {}
  public void trayIconDBLClicked(TrayIconEvent e) {
    if(!this.isVisible()){
      this.setExtendedState(JFrame.NORMAL);
      this.setVisible(true);
    }
    this.toFront();
  }
  public void trayIconPressed(TrayIconEvent e) {}
  public void trayIconReleased(TrayIconEvent e) {}
  public void mouseDragged(MouseEvent e) {
    this.setLocation(this.getLocation().x + e.getX() - beForX,this.getLocation().y + e.getY() - beForY);
    list.setLocation(this.getLocation().x ,this.getLocation().y + 81);
    lyrics.setLocation(this.getLocation().x + 201,this.getLocation().y);
    video.setLocation(this.getLocation().x - 301,this.getLocation().y);
    skin.setLocation(this.getLocation().x + 201,this.getLocation().y);
  }

  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if(source == doPlay)this.playSelected();
    else if(source == doPause){
      if(core.isPlayed()){
        if (core.isPaused())core.start();
        else core.pause();
      }
    }
    else if(source == doStop)this.stop();
    else if(source == doFile)list.doAddItem();
    else if(source == doFront)this.front();
    else if(source == doNext)this.next();
    else if(source == doClose)this.quit();
    else if(source == doMini)this.setExtendedState(JFrame.ICONIFIED);
    else if(source == doList)list.setVisible(!list.isVisible());
    else if(source == doVideo)video.setVisible(!video.isVisible());
    else if(source == doSkin)skin.refresh();
    else if(source == doLyrics){
      lyrics.setVisible(!lyrics.isVisible());
      isShowLyrics = lyrics.isVisible();
    }
  }

  private class StatusAdapter extends WindowAdapter{
    private CrystalPlayer frame = null;
    public StatusAdapter(CrystalPlayer frame){
      this.frame = frame;
      frame.addWindowListener(this);
    }
    public void windowClosing(WindowEvent e){
      frame.quit();
    }
    public void windowIconified(WindowEvent e){
      frame.setVisible(false);
    }
  }
}
