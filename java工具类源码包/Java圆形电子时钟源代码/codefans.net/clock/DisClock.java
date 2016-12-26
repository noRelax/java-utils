import java.awt.*;
import javax.swing.*;
// Finishing Codefans.net
public class DisClock extends JFrame
{
	public DisClock()
	{
		StillClock clock = new StillClock();
		
		MessagePanel mp = new MessagePanel(clock.getHour()+":"+clock.getMinute()+":"+clock.getSecond());
		mp.setCentered(true);
		mp.setForeground(Color.blue);
		mp.setFont(new Font("Courier", Font.BOLD,16));
		
		add(clock);
		add(mp,BorderLayout.SOUTH);
	}
	
	public static void main(String [] args)
	{
		DisClock f = new DisClock();
		f.setTitle("MY CLOCK");
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300,300);
		f.setVisible(true);
	} 
}