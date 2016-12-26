package QQ_test1;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class CellRenderer extends JLabel implements ListCellRenderer
{
    public CellRenderer()
    {
        setOpaque(true);
    }
    
    public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus)
    {
        if (value != null)
        {
            setText(value.toString());
            setIcon(new ImageIcon("D:\\java\\Face\\"+(index+1)+".gif"));
        }
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }    
}

