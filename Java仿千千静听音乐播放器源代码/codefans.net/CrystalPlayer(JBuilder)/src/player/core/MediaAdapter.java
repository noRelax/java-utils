package player.core;
//download:http://www.codefans.net
import javax.media.*;
import java.util.ArrayList;
import player.module.LyricsItem;
import java.util.*;
import player.lyrics.*;

public class MediaAdapter extends ControllerAdapter{
  private Core core = null;
  private MediaListener listener = null;

  public MediaAdapter(Core core) {this.core = core;}

  public Core getCore() {return core;}

  public void realizeComplete(RealizeCompleteEvent e){
    core.getPlayer().getGainControl().setLevel(core.getVolume());
    core.getPlayer().start();
    core.setPlayed(true);
    core.getFrame().getVideo().addVideo(core.getPlayer().getVisualComponent());
    listener = new MediaListener(this);
  }

  public void start(StartEvent e) {core.setPaused(false);}
  public void stop(StopEvent e) {core.setPaused(true);}
  public void endOfMedia(EndOfMediaEvent e) {core.deallocate();}

  public void audioDeviceUnavailable(AudioDeviceUnavailableEvent e) {core.deallocate();}
  public void connectionError(ConnectionErrorEvent e) {core.deallocate();}
  public void internalError(InternalErrorEvent e) {core.deallocate();}
  public void dataLostError(DataLostErrorEvent e) {core.deallocate();}
  public void controllerError(ControllerErrorEvent e) {core.deallocate();}
}
