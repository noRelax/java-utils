package player.user;
//download:http://www.codefans.net
import java.awt.*;
import player.module.ImagePanel;
import player.skin.Skin;
import javax.swing.*;
import player.module.MuiscListModel;
import player.module.FileChooser;
import java.io.File;
import player.module.ListItem;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CrystalList extends JDialog implements MouseListener,KeyListener{
  private ImagePanel contentPanel = new ImagePanel("CrystalList.png");
  private JScrollPane scrollPane = new JScrollPane();
  private MuiscListModel model = new MuiscListModel();
  private JList list = new JList(model);
  private CrystalPlayer frame = null;
  private FileChooser chooser = new FileChooser();
  public CrystalList(CrystalPlayer frame) {
    super(frame);
    this.frame = frame;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public FileChooser getFileChooser(){
    return chooser;
  }

  public void addItems(File[] files,boolean isSave){
    if(files != null ){
     for(int i=0;i<files.length;i++){
       model.addElement(new ListItem(files[i]));
     }
     this.saveList();
    }
  }

  public void removeSelectedItems(){
    int[] indexes = list.getSelectedIndices();
    if(indexes != null && indexes.length > 0){
      int i = indexes.length;
      int index = index = indexes[0];
      ListItem item = null;
      while(i > 0){
        item = model.getItemByIndex(indexes[i - 1]);
        Skin.removeListener(item);
        model.removeElement(item);
        i--;
      }
      this.saveList();
      if(model.getSize() > 0){
        if(index + 1 > model.getSize())list.setSelectedIndex(index - 1);
        else if(index == 0)list.setSelectedIndex(0);
        else list.setSelectedIndex(index - 1);
      }
    }
  }

  public ListItem getItemByIndex(int index){
    return model.getItemByIndex(index);
  }

  public ListItem getSelectedItem(){
    return this.getItemByIndex(list.getSelectedIndex());
  }

  public int getSelectedIndex(){
    return list.getSelectedIndex();
  }

  public void setSelectedIndex(int index){
    list.setSelectedIndex(index);
  }

  public int getRowCount(){
    return model.getSize();
  }

  public void doAddItem(){
    int chcek = chooser.showOpenDialog(this);
    if(chcek == 0){
      File[] files = chooser.getSelectedFiles();
      this.addItems(files,true);
    }
  }

  private void jbInit() throws Exception {
    list.addMouseListener(this);
    list.addKeyListener(this);
    list.setCellRenderer(model);
    this.setUndecorated(true);
    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(new BorderLayout());
    contentPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    contentPanel.add(scrollPane, BorderLayout.CENTER);
    scrollPane.getViewport().add(list, null);
    list.setBackground(new Color(238, 238, 238));
    this.setSize(200, 200);
  }

  public static ArrayList getList(){
    ArrayList list = new ArrayList();
    File file = new File("file.ini");
    if(file.exists()){
      FileInputStream in = null;
      BufferedReader reader = null;
      try{
        in = new FileInputStream(file);
        reader = new BufferedReader(new InputStreamReader(in));
        String path = null;
        File muiscFile = null;
        while((path = reader.readLine()) != null){
          if(path.length() > 0){
            muiscFile = new File(path);
            if(muiscFile.exists()){
              list.add(muiscFile);
            }
          }
        }
      }
      catch(Exception e){
        e.printStackTrace();
      }
      finally{
        try{if(in != null)in.close();}catch(Exception e){}
        try{if(reader != null)reader.close();}catch(Exception e){}
      }
    }
    return list;
  }

  public void saveList(){
    File file = new File("file.ini");
    FileOutputStream out = null;
    PrintStream writer = null;
    try{
      if(file.exists())file.delete();
      file.createNewFile();
      out = new FileOutputStream(file);
      writer = new PrintStream(out);
      ListItem item = null;
      if(model.getSize() < 1){
        writer.println("");
      }
      else{
        for (int i = 0; i < model.getSize(); i++) {
          item = model.getItemByIndex(i);
          writer.println(item.getFile().getAbsolutePath() + "\n");
        }
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    finally{
      try{if(out != null)out.close();}catch(Exception e){}
      try{if(writer != null)writer.close();}catch(Exception e){}
    }
  }

  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_DELETE)this.removeSelectedItems();
  }
  public void keyReleased(KeyEvent e) {}
  public void keyTyped(KeyEvent e) {}

  public void mouseClicked(MouseEvent e) {
    if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2 && list.getSelectedIndex() != -1){
      frame.playSelected();
    }
  }
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
}
