import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
//Download by http://www.codefans.net
public class numen extends MIDlet implements Runnable{
  private static numencanva mscreen;

 /*****************************************************************************
  * 游戏主类构造函数
  *****************************************************************************/
  public numen() {
    mscreen = new numencanva();
    Display.getDisplay(this).setCurrent(mscreen);
  }

  public void run(){
    if(mscreen.process()) this.notifyDestroyed();
  }

  /*****************************************************************************
   * 处理激活游戏运行的事件
   *****************************************************************************/
  public void startApp() {
     (new Thread(this)).start();
  }
  /*****************************************************************************
   * 处理暂停游戏事件
   *****************************************************************************/
  public void pauseApp() {
    mscreen.stayapp();
    notifyPaused();
  }

  /*****************************************************************************
   * 处理游戏退出事件
   *****************************************************************************/
  public void destroyApp(boolean unconditional) {
    mscreen.exitapp();
  }
}
