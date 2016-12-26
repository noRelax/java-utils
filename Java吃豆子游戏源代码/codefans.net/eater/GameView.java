//Finishing www.codefans.net

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements GameConstants {
	
	private final String[] EATER_NAMES={"eater_0_1.gif", "eater_0_2.gif", "eater_0_3.gif",
										"eater_0_4.gif", "eater_0_5.gif", "eater_0_6.gif",
										"eater_1_1.gif", "eater_1_2.gif", "eater_1_3.gif",
										"eater_1_4.gif", "eater_1_5.gif", "eater_1_6.gif",
										"eater_2_1.gif", "eater_2_2.gif", "eater_2_3.gif",
										"eater_2_4.gif", "eater_2_5.gif", "eater_2_6.gif",
										"eater_3_1.gif", "eater_3_2.gif", "eater_3_3.gif",
									    "eater_3_4.gif", "eater_3_5.gif", "eater_3_6.gif",
									    "eater_dead.gif"};
	
	private final String[] ENEMY_NAMES={"enemy_0.gif", "enemy_1.gif", "enemy_2.gif",
										"enemy_3.gif"};
												
	private Animation eater;
	private Animation [] enemies;
	private Background bg;
	private GameController control;
	
	public GameView () {
		super (null);
		setOpaque(false);
		initilalizeEater();
		initializeEnemies();
		bg = new Background(eater, enemies);
		add(bg);
		setSize(new Dimension (bg.getWidth(), bg.getHeight()));
		setPreferredSize(getSize());
	
	}//end constructor..
	
	public void initilalizeEater() {
		ImageIcon [] eaterIcons = new ImageIcon[EATER_NAMES.length];
		for (int i=0; i<eaterIcons.length;i++){
			eaterIcons[i] = new ImageIcon("images/" + EATER_NAMES[i]);
		eater = new Animation (eaterIcons, EATER, EAST);
		
				
		}
	}//end method initializeEater..
	public void initializeEnemies(){
		enemies = new Animation [8];
		ImageIcon [] enemyIcons = new ImageIcon [ENEMY_NAMES.length];
				
		for (int i=0; i<enemyIcons.length; i++) {
				enemyIcons [i] = new ImageIcon ("images/" + ENEMY_NAMES[i]);
		}
		
		for (int i=0; i<enemies.length; i++) {
			enemies[i] = new Animation (enemyIcons, ENEMY, SOUTH);
			
		}
	} //end method intializeEnemies..
	
	public static void main (String args []){
		JFrame window = new JFrame ("Stage - 1");
		GameView view = new GameView();
		Container container = window.getContentPane();
		container.add(view);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		//window.setSize(view.getSize());
		window.setVisible(true);
		window.setLocation(2, 2);
	//	window.getContentPane().repaint();
	}
}