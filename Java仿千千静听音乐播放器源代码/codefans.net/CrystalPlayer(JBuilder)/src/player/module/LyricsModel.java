package player.module;
//download:http://www.codefans.net
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import java.awt.Component;
import java.awt.Color;

public class LyricsModel extends DefaultComboBoxModel implements ListCellRenderer{
  private LyricsItem listItem;

  public void addElement(LyricsItem item){
    super.addElement(item);
  }

  public LyricsItem getSelectedElement(){
    return (LyricsItem)super.getSelectedItem();
  }

  public void removeElementAt(int index){
    super.removeElementAt(index);
  }

  public void removeElement(LyricsItem item){
    super.removeElement(item);
  }

  public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected,boolean cellHasFocus) {
    listItem = (LyricsItem)value;
    if(listItem != null){
      if (isSelected) {
        listItem.setSelected(true);
        listItem.setForeground(Color.white);
      }
      else {
        listItem.setSelected(false);
        listItem.setForeground(Color.black);
      }
    }
    return listItem;
  }
}
