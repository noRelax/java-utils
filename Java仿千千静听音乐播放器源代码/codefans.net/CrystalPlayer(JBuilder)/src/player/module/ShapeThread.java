package player.module;

public class ShapeThread extends Thread{
  private ShapeLabel label = null;
  public ShapeThread(ShapeLabel label) {
    this.label = label;
    this.start();
  }

  public void run(){
    try{
      int width = 0;
      while (true) {
        width = label.getDrawWidth() - label.getSize().width;
        if(width > 0 && label.isExited()){
          if(-label.getDrawX() == label.getDrawWidth())label.setDrawX(label.getSize().width);
          else label.setDrawX(label.getDrawX() - 1);
        }
        Thread.sleep(100);
      }
    }
    catch(InterruptedException e){
      new ShapeThread(label);
    }
  }
}
