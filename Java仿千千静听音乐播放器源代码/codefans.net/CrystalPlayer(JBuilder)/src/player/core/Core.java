package player.core;
//download:http://www.codefans.net
import player.user.CrystalPlayer;
import javax.media.*;
import player.module.ListItem;
import player.lyrics.*;

public class Core {
  private CrystalPlayer frame = null;
  private Player player = null;
  private boolean played = false;
  private boolean paused = false;
  private float volume = 1.0f;
  private MediaAdapter adapter = new MediaAdapter(this);
  private LyricsContent lyrics = null;
  private ListItem item = null;

  public Core(CrystalPlayer frame) {
    this.frame = frame;
  }

  protected void setPlayed(boolean played) {this.played = played;}
  protected void setPaused(boolean paused) {this.paused = paused;}
  public void setVolume(float volume) {this.volume = volume;if(player != null)player.getGainControl().setLevel(volume);}

  public boolean isPaused() {return paused;}
  public boolean isPlayed() {return played;}
  public float getVolume() {return volume;}
  public Player getPlayer() {return player;}
  public CrystalPlayer getFrame() {return frame;}
  public LyricsContent getLyrics() {return lyrics;}

  public ListItem getItem() {
    return item;
  }

  public void setMediaSeconds(int seconds){if(player != null)player.setMediaTime(new Time((double)seconds));}

  public void play(ListItem item){
    this.item = item;
    if(item != null){
      try{
        frame.getLyrics().removeAllLyrics();
        player = Manager.createPlayer(item.getFile().toURL());
        player.addControllerListener(adapter);
        lyrics = item.getMuisc().getLyrics();
        frame.getLyrics().setVisible(lyrics != null && frame.isVisible() && frame.isShowLyrics());
        frame.readMuisc(item.getMuisc());
        player.realize();
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  public void start(){if(player != null)player.start();}
  public void pause(){if(player != null)player.stop();}
  public void stop(){if(player != null)player.setMediaTime(player.getDuration());}

  protected void deallocate(){
    item = null;
    lyrics = null;
    player = null;
    frame.readMuisc(null);
    frame.getLyrics().setVisible(false);
    frame.getVideo().addVideo(null);
    this.setPaused(false);
    this.setPlayed(false);
    if (frame.isNext()) frame.next();
  }
}
