package flight.Main;

import flight.assist.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame
{
    private UpdateComboBox update;
    private Interface jiemian;
    	
	public Main()
	{
		update = new UpdateComboBox();
		
		jiemian = new Interface();
		
		this.getContentPane().add(jiemian);		
	}
	
	public static void main(String args[])
	{
		   //Get the System's look for the GUI
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
	   
		Main frame = new Main();
		frame.setSize(570,440);
		frame.setResizable(false);
		frame.setTitle("航班查询系统及定票系统");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}