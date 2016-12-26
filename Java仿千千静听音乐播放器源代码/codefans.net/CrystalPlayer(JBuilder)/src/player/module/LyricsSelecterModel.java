package player.module;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import java.awt.Component;
import java.awt.Color;

public class LyricsSelecterModel extends DefaultComboBoxModel implements ListCellRenderer{
  private LyricsSelecter listItem;

  public void addElement(LyricsSelecter item){
    super.addElement(item);
  }

  public LyricsSelecter getSelectedElement(){
    return (LyricsSelecter)super.getSelectedItem();
  }

  public LyricsSelecter getItemByIndex(int index){
    return (LyricsSelecter)super.getElementAt(index);
  }

  public void removeElementAt(int index){
    super.removeElementAt(index);
  }

  public void removeElement(LyricsSelecter item){
    super.removeElement(item);
  }

  public void removeAll(){
    super.removeAllElements();
  }

  public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected,boolean cellHasFocus) {
    listItem = (LyricsSelecter)value;
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
