import java.awt.*;
import javax.swing.*;

public class SplitMap extends JPanel{
	
	public void  paint(Graphics g){
		paintComponent(g);
		setBorder(BorderFactory.createLineBorder(Color.RED));
		g.drawString("���ڼ���ͼƬ......",65,40);
		g.drawImage(Toolkit.getDefaultToolkit().getImage(
				(PublicData.SplitMapName==null)?"splitmap/null.gif":("splitmap/"+PublicData.SplitMapName)),0,0,this);
	}
}
