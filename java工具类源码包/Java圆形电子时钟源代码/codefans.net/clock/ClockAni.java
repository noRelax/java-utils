import java.awt.event.*;
import javax.swing.*;
// Finishing Codefans.net
public class ClockAni extends StillClock
{
	public ClockAni()
	{
		Timer time = new Timer (1000,new TimerListener());
		time.start();
	}
	
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			setCurrentTime();
			repaint();
		}
	}
	
	public static void main(String [] args )
	{
		JFrame frame = new JFrame("clock_wak");
		ClockAni clock = new ClockAni();
		frame.add(clock);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200,200);
		frame.setVisible(true);
	}
}