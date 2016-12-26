package player.module;
//download:http://www.codefans.net
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FileChooser extends JFileChooser{
  private String[] names = new String[]{".mp3",".wav",".avi",".mpeg",".mpg"};
  public FileChooser() {
    this.setMultiSelectionEnabled(true);
    this.removeChoosableFileFilter(this.getFileFilter());
    MediaFileFilter filter = null;
    for(int i=0;i<names.length;i++){
      if(i == 0){
        filter = new MediaFileFilter(names[i]);
        this.addChoosableFileFilter(filter);
      }
      else this.addChoosableFileFilter(new MediaFileFilter(names[i]));
    }
    this.setFileFilter(filter);
  }

  class MediaFileFilter extends FileFilter{
    private String name = ".*";

    MediaFileFilter(String name){
      this.name = name;
    }

    public boolean accept(File file) {
      return file.isDirectory() || file.getName().toLowerCase().endsWith(name);
    }

    public String getDescription() {
      return name;
    }

  }
}
