package player.skin;
//download:http://www.codefans.net
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Skin {
  private static String catalogue = null;
  private static ArrayList listeners = new ArrayList();

  public static void setCatalogue(String catalogue){
    Skin.catalogue = catalogue;
    SkinListener listener = null;
    for(int i=0;i<listeners.size();i++){
      listener = (SkinListener)listeners.get(i);
      listener.paintSkin(Skin.getImageIcon(listener.getSkinName()));
    }
  }

  public static void removeListener(SkinListener listener){
    listeners.remove(listener);
  }

  public static ImageIcon getImageIcon(String name) {
    return Skin.getImageIcon(name,null);
  }

  public static ImageIcon getDefaultImageIcon(String name){
    try{
      return new ImageIcon(Skin.class.getResource(name));
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static ImageIcon getImageIcon(String name,SkinListener listener) {
    try{
      if(listener != null)listeners.add(listener);
      if(Skin.catalogue == null)return new ImageIcon(Skin.class.getResource(name));
      else return new ImageIcon("Skin//" + Skin.catalogue + "//" + name);
    }
    catch(Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
