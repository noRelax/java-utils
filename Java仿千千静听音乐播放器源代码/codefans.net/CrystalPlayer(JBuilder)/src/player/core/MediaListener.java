package player.core;
//download:http://www.codefans.net
import javax.media.Player;
import java.util.GregorianCalendar;
import java.util.*;
import player.lyrics.*;
import player.module.*;
import java.io.*;
import player.user.*;

public class MediaListener extends Thread implements LyricsDownLoedListener{
  private Core core = null;
  private Player player = null;
  private MediaAdapter adapter = null;
  private int maxTime;
  private int currentSeconds;
  private String limitTime = "";
  private GregorianCalendar calendar = new GregorianCalendar();
  private LyricsContent lyrics = null;
  private HashMap map = null;
  private ListItem item = null;

  public MediaListener(MediaAdapter adapter) {
    this.adapter = adapter;
    core = adapter.getCore();
    player = core.getPlayer();
    lyrics = core.getLyrics();
    item = core.getItem();
    if(core.getItem() != null && lyrics == null){
      File file = core.getItem().getFile().getParentFile();
      String name = core.getItem().getFile().getName();
      name = name.substring(0,name.lastIndexOf("."));
      file = new File(file.getAbsolutePath() + "\\" + name + ".lrc");
      lyrics = Lyrics.read(file);
      if(lyrics == null){
        LyricsHunter hunter = new LyricsHunter(core.getItem().getMuisc().getName());
        hunter.addLyricsDownLoadListener(this);
        hunter.start();
      }
      else {
        if(player == core.getPlayer()){
          core.getItem().getMuisc().setLyrics(lyrics);
          core.getFrame().getLyrics().setVisible(lyrics != null && core.getFrame().isVisible() && core.getFrame().isShowLyrics());
        }
      }
    }
    maxTime = Math.round((float)player.getDuration().getSeconds());
    core.getFrame().getProgressBar().setMaxLimit(maxTime);
    calendar.setTimeInMillis(maxTime * 1000);
    limitTime = this.getTimeFormat(calendar);
    this.start();
  }

  public Player getPlayer() {
    return player;
  }

  private static String getTimeFormat(GregorianCalendar calendar){
    String text = "";
    if(calendar.get(calendar.MINUTE) < 10)text += "0" + calendar.get(calendar.MINUTE);
    else text += calendar.get(calendar.MINUTE);
    if(calendar.get(calendar.SECOND) < 10)text += " : 0" + calendar.get(calendar.SECOND);
    else text += " : " + calendar.get(calendar.SECOND);
    return text;
  }

  protected void setMap(HashMap map){
    this.map = map;
  }

  public void run(){
    try{
      if(lyrics != null){
        ArrayList list = lyrics.getContent();
        map = lyrics.getMap();
        for(int i=0;i<list.size();i++){
          core.getFrame().getLyrics().addLyrics(new LyricsItem((String)list.get(i)));
        }
      }
      String time = null;
      Integer index = null;
      while(core.isPlayed() && player == core.getPlayer()){
        currentSeconds = Math.round((float)player.getMediaTime().getSeconds());
        core.getFrame().getProgressBar().setValueForChange(currentSeconds);
        calendar.setTimeInMillis(currentSeconds * 1000);
        time = this.getTimeFormat(calendar).replaceAll(" ","");
        if(map != null){
          index = (Integer)map.get(time);
          if(index != null){
            core.getFrame().getLyrics().setSelectIndex(index.intValue());
          }
        }
        core.getFrame().setMediaTime(time + " - " + limitTime);
        Thread.sleep(500);
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    finally{
      this.deallocate();
    }
  }

  protected void deallocate(){
    player.removeControllerListener(adapter);
    player.close();
    if(adapter.getCore().getPlayer() == player)core.deallocate();
  }

  public void lyricsDowned(LyricsContents contents) {
    if(adapter.getCore().getPlayer() == player){
      LyricsContent content = null;
      if(contents.getSize() == 1){
        content = contents.get(0);
        adapter.getCore().getFrame().getLyricsList().addItem(new LyricsSelecter(content.getName(),content.getSinger()));
      }
      else{
        if(contents.getSize() > 0){
          CrystalLyricsList lyricsList = new CrystalLyricsList(adapter.getCore().getFrame());
          for (int i = 0; i < contents.getSize(); i++) {
            content = contents.get(i);
            lyricsList.addItem(new LyricsSelecter(content.getName(), content.getSinger()));
          }
          int index = lyricsList.showSelectList();
          if (index != -1) {
            content = contents.get(index);
          }
        }
      }
      if(adapter.getCore().getPlayer() == player) {
        adapter.getCore().getItem().getMuisc().setLyrics(content);
        ArrayList list = content.getContent();
        HashMap map = content.getMap();
        for (int i = 0; i < list.size(); i++) {
          core.getFrame().getLyrics().addLyrics(new LyricsItem((String)list.get(i)));
        }
        if (core.getFrame().isVisible() && core.getFrame().isShowLyrics())core.getFrame().getLyrics().setVisible(true);
        this.setMap(map);
      }
      LyricsWriter.wirte(item,content);
    }
  }
}
