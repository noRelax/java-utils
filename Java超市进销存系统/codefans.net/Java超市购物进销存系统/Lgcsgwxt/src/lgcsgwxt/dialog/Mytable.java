package lgcsgwxt.dialog;

import java.util.Vector;
import javax.swing.*;

public class Mytable {
    public static JTable maketable(Vector obj, Vector title) {
        JTable table = new JTable();
        table = new JTable(obj, title);
        return table;
    }
}
