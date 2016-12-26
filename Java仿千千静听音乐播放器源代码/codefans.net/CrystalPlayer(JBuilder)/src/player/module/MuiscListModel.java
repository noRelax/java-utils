package player.module;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import java.awt.Component;
import java.awt.Color;

public class MuiscListModel extends DefaultComboBoxModel implements ListCellRenderer{
  private ListItem listItem;

  public void addElement(ListItem item){
    super.addElement(item);
  }

  public ListItem getSelectedElement(){
    return (ListItem)super.getSelectedItem();
  }

  public ListItem getItemByIndex(int index){
    return (ListItem)super.getElementAt(index);
  }

  public void removeElement(ListItem item){
    super.removeElement(item);
  }

  public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected,boolean cellHasFocus) {
    listItem = (ListItem)value;
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
