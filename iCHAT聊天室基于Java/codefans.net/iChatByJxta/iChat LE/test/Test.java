import javax.media.*;
import java.io.*;

public class Test {
  public static void main(String[] args) throws Exception  {
    Player player = Manager.createPlayer(new File("msg.wav").toURL());
    player.start();

    Thread.sleep(5000);
    System.exit(0);
  }
}