package player.lyrics;
//download:http://www.codefans.net
import player.module.*;
import java.io.*;

public class LyricsWriter {
  public static void wirte(String path){

  }

  public static void wirte(ListItem item, LyricsContent content) {
    File file = item.getFile().getParentFile();
    String name = item.getFile().getName();
    name = name.substring(0, name.lastIndexOf("."));
    file = new File(file.getAbsolutePath() + "\\" + name + ".lrc");
    FileOutputStream out = null;
    PrintStream writer = null;
    try {
      if(!file.exists())file.createNewFile();
      out = new FileOutputStream(file);
      writer = new PrintStream(out);
      writer.println(content.getText());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try{if(writer != null)writer.close();}catch(Exception e){}
      try{if(out != null)out.close();}catch(Exception e){}
    }
  }
}
