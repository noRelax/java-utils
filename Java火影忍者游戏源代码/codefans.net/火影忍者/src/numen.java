import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
//Download by http://www.codefans.net
public class numen extends MIDlet implements Runnable{
  private static numencanva mscreen;

 /*****************************************************************************
  * ��Ϸ���๹�캯��
  *****************************************************************************/
  public numen() {
    mscreen = new numencanva();
    Display.getDisplay(this).setCurrent(mscreen);
  }

  public void run(){
    if(mscreen.process()) this.notifyDestroyed();
  }

  /*****************************************************************************
   * ��������Ϸ���е��¼�
   *****************************************************************************/
  public void startApp() {
     (new Thread(this)).start();
  }
  /*****************************************************************************
   * ������ͣ��Ϸ�¼�
   *****************************************************************************/
  public void pauseApp() {
    mscreen.stayapp();
    notifyPaused();
  }

  /*****************************************************************************
   * ������Ϸ�˳��¼�
   *****************************************************************************/
  public void destroyApp(boolean unconditional) {
    mscreen.exitapp();
  }
}
