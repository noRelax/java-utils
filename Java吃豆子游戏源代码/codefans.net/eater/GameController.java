import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;


public class GameController extends JPanel implements ActionListener {
	
	private JButton exitButton = new JButton("Exit");
	private JButton settingsButton = new JButton ("Settings");
	private JButton aboutButton = new JButton ("About");
	private JButton keysButton = new JButton ("Keys");
	private JLabel label = new JLabel ("     0     ");
	
	private Background bg;
		
	public GameController (Background bg){
		super(new FlowLayout(FlowLayout.CENTER));
		this.bg = bg;
		setOpaque(false);
		
		exitButton.addActionListener(this);
		settingsButton.addActionListener(this);
		aboutButton.addActionListener(this);
		keysButton.addActionListener(this);
		
		label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		add(label);				
		add(exitButton);
		add(aboutButton);
		add(settingsButton);
		add(keysButton);
		
			
			
	}
	
	public JLabel getLabel(){
		return label;
	}
	
	public void actionPerformed (ActionEvent e){
		
		if (e.getSource()==exitButton){
			System.exit(0); 
						
		}				
		
		else if (e.getSource()==settingsButton){
				
					
		}
	
		else if (e.getSource()==aboutButton){
			JOptionPane.showMessageDialog(getParent(),  "Eater Version 1.1" +
														"\nProgrammed by K.I.K");
														
		}
		
		else if (e.getSource()==keysButton){
			JOptionPane.showMessageDialog(getParent(),  "Use arrow keys to move the eater");
		}
		
		else return;
	}
}