//there is still a bug in the program..when the eater moves to the north it's movement
//Finishing www.codefans.net
//at row=7 col=0 is not prolery checked..fix it, when u will....
//

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Background extends JPanel implements GameConstants, 
											 KeyListener, ActionListener {
	
	private int [][] vState = { {0,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0},
								{0,0,1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,1,0,0},
								{0,1,1,1,1,1,1,1,1,1,0,1,1,1,0,0,1,0,0,0},
								{0,1,1,1,1,1,1,1,0,1,0,1,1,1,0,0,0,1,1,0},
								{0,0,1,1,1,1,1,1,0,0,0,1,0,1,0,0,0,0,0,0},
								{0,0,0,1,1,1,1,1,0,0,0,1,0,0,0,0,1,0,0,0},
								{0,0,1,0,1,1,1,1,1,1,1,1,1,0,0,1,0,1,0,0},
								{1,1,0,0,0,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1},
								{0,1,1,1,1,0,0,0,1,0,0,0,1,1,1,0,0,1,0,0},
								{0,1,1,0,1,0,1,0,0,0,0,0,0,1,1,1,0,0,0,0},
								{0,0,0,0,0,0,1,0,0,1,0,1,0,1,0,1,0,0,1,0},
								{0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,1,1,0,1,0},
								{0,0,0,1,0,0,0,1,0,1,0,0,1,1,1,1,1,1,1,0},
								{0,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0},
								{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0}
								
							};
	private int [][] hState = { {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								{1,1,1,0,0,1,0,0,0,1,1,1,0,0,0,1,0,1,1},
								{1,0,0,1,0,0,0,0,1,1,1,0,1,1,1,0,1,1,1},
								{0,0,0,0,0,0,0,1,0,1,0,0,0,1,1,1,0,1,1},
								{1,0,1,0,0,1,0,0,1,1,1,0,0,1,1,1,1,1,1},
								{1,1,1,0,0,0,0,1,1,1,0,1,1,1,1,1,1,1,1},
								{1,1,0,0,0,0,0,0,1,1,1,0,1,1,1,0,1,1,1},
								{1,0,1,1,0,0,0,0,0,0,0,1,0,1,0,1,0,1,1},
								{0,1,1,1,1,1,1,0,0,1,1,0,0,0,1,1,0,1,1},
								{1,0,0,0,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1},
								{1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,1},
								{1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,0,1,1,0},
								{1,1,1,1,1,1,1,1,0,1,1,1,1,0,0,0,0,0,1},
								{1,1,0,0,1,1,1,0,1,0,1,0,0,0,0,0,0,0,1},
								{1,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,1},
								{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
							};


	private int gateWidth = 4;
	private int pathWidth = 29;
	private int cellWidth = gateWidth+pathWidth;
	private int score = -1;
	private final int DELAY = 30;
		
	private Gate [][] vGates = new Gate [vState.length][vState[0].length];
	private Gate [][] hGates = new Gate [hState.length][hState[0].length];
	private Animation eater;
	private Animation [] enemies; 
	private GameController control;
	private Dot dots  [][] = new Dot [19][15];
	private int [] eaterEastUpSequence 		= {0, 1, 2};
	private int [] eaterEastDownSequence	= {3, 4, 5};
	private int [] eaterWestUpSequence		= {6, 7, 8};
	private int [] eaterWestDownSequence	= {9, 10, 11};
	private int [] eaterNorthUpSequence		= {12, 13, 14};
	private int [] eaterNorthDownSequence	= {15, 16, 17};
	private int [] eaterSouthUpSequence		= {18, 19, 20};
	private int [] eaterSouthDownSequence	= {21, 22, 23};
	private int [] eaterDeadSequence	= {24};
	private int [] enemyEastSequence	= {0};
	private int [] enemyWestSequence	= {1};
	private int [] enemyNorthSequence	= {2};
	private int [] enemySouthSequence	= {3};
	
	private javax.swing.Timer animationTimer;
	
	public Background (Animation eater, Animation [] e) {
		super(null);
		//setOpaque(false);
		this.eater = eater;
		enemies = e;
				
		eater.setLocation(gateWidth, (vState.length/2)*(pathWidth+gateWidth)+
									  gateWidth+((pathWidth-eater.getWidth())/2));
		
		eater.setSequence(eaterEastUpSequence);
		add(eater);
		placeEnemies();
		setuphGates();
		setupvGates();
		setupDots();
		setLocation(0, 0);
		control = new GameController(this);
		control.setSize(hState[0].length*cellWidth+gateWidth, 40);
		add(control);		
		setSize(control.getWidth(),vState.length*cellWidth+gateWidth+control.getHeight());
		control.setLocation(0, getHeight()-control.getHeight());
	
		animationTimer = new javax.swing.Timer (DELAY, this);
		animationTimer.start();
		addKeyListener(this);
		setFocusable(true);		
				
	}//endConstructor...
	
	public void setupvGates() {
		for (int i=0; i<vGates.length; i++){
			for (int j=0; j<vGates[0].length; j++){
				vGates[i][j] = new Gate (vState[i][j]);
				vGates[i][j].setSize(gateWidth, pathWidth+gateWidth*2);
				vGates[i][j].setLocation(j*(pathWidth+gateWidth), i*(pathWidth+gateWidth));
				add(vGates[i][j]);			
						
			}//end inner for..
		}//end outer for..
	}//end method setupvGates..
	
	public void setuphGates () {
		for (int i=0; i<hGates.length; i++){
			for (int j=0; j<hGates[0].length; j++){
				hGates[i][j] = new Gate (hState[i][j]);
				hGates[i][j].setSize(pathWidth+gateWidth*2, gateWidth);
				hGates[i][j].setLocation(j*(pathWidth+gateWidth), i*(pathWidth+gateWidth));
				add(hGates[i][j]);
			}//end inner for..
		}//end outer for..

	}//end method setuphGates..
	
	public void setupDots () {
		for (int i=0; i<dots.length; i++){
			for (int j=0;j<dots[0].length; j++){
				dots[i][j] = new Dot ();
				dots[i][j].setLocation (16+ i*(gateWidth+pathWidth), 16+j*(gateWidth+pathWidth));
				add(dots[i][j]);
			}//end inner for..
		}//end outer for..
	}//end method setupGates..
	
	public void reinstateDots(){
		for (int i=0; i<dots.length; i++){
			for (int j=0;j<dots[0].length; j++){
				dots[i][j].state = 0;
				
			}//end inner for..
		}//end outer for..
	}
	public void placeEnemies() {
		
		enemies[0].setDirection(EAST);
		enemies[0].setLocation(70, 72);
		enemies[0].setSequence(enemyEastSequence);
		enemies[0].setVelocity(3);
		add(enemies[0]);
		
		enemies[1].setDirection(SOUTH);
		enemies[1].setLocation(6, 104);
		enemies[1].setSequence(enemySouthSequence);
		enemies[1].setVelocity(2);
		add(enemies[1]);
		
		enemies[2].setDirection(NORTH);
		enemies[2].setLocation(302, 172);
		enemies[2].setSequence(enemyNorthSequence);
		enemies[2].setVelocity(4);
		add(enemies[2]);
		
		enemies[3].setDirection(SOUTH);
		enemies[3].setLocation(369, 336	);
		enemies[3].setSequence(enemySouthSequence);
		enemies[3].setVelocity(5);
		add(enemies[3]);
		
		enemies[4].setDirection(NORTH);
		enemies[4].setLocation(70, 336);
		enemies[4].setSequence(enemyNorthSequence);
		enemies[4].setVelocity(3);
		add(enemies[4]);
		
		enemies[5].setDirection(EAST);
		enemies[5].setLocation(170, 303);
		enemies[5].setSequence(enemyEastSequence);
		enemies[5].setVelocity(5);
		add(enemies[5]);
		
		enemies[6].setDirection(SOUTH);
		enemies[6].setLocation(402, 172);
		enemies[6].setSequence(enemySouthSequence);
		enemies[6].setVelocity(4);
		add(enemies[6]);
		
		enemies[7].setDirection(EAST);
		enemies[7].setLocation(369, 6);
		enemies[7].setSequence(enemyEastSequence);
		enemies[7].setVelocity(5);
		add(enemies[7]);
		
	}//end method placeEnemies..
	
	public void actionPerformed (ActionEvent e) {
	    
		moveAnimation(eater);
		checkNextMove(eater);
		
		for (int i=0; i<enemies.length; i++){
			moveAnimation(enemies[i]);
			checkNextMove(enemies[i]);
		}
		
		score();		
		decideGameState();
		repaint();
								
	}//end method actionPerformed...
	
	public void checkNextMove(Animation a) {
		switch (a.getDirection()){
			case EAST:
				if (a.getX()>hState[0].length*cellWidth){
					a.setLocation(gateWidth-a.getWidth(), a.getY());
				}
					
			break;
			case WEST:
				if (a.getX()<gateWidth-a.getWidth()){
					a.setLocation(hState[0].length*cellWidth, a.getY());
				}			
			break;
		}
	}	
	public void score(){
		Rectangle eaterRect = eater.getBounds();
		Rectangle rect;
		for (int i=0; i<dots.length; i++){
			for (int j=0; j<dots[0].length; j++){
				rect = dots[i][j].getBounds();
				if (rect.intersects(eaterRect))
					dots[i][j].eliminate();
			}
		}
	}	
	public void decideGameState(){
		Rectangle eaterRect = eater.getBounds();
		Rectangle rect;
		for (int i=0; i<enemies.length; i++){
			rect = enemies[i].getBounds();
			if (rect.intersects(eaterRect)){
				eater.setVelocity(0);
				eater.setSequence(eaterDeadSequence);
				animationTimer.stop();
				repaint();
			int selected	= JOptionPane.showConfirmDialog(this,"Sorry You Lose!" +
									"\nYour Score = " + score + "\nPlay again?",
                                    "Oooooooooooops",
                                    JOptionPane.YES_NO_OPTION);

			if (selected==JOptionPane.OK_OPTION){
				restart();
				}
			else return;
			}//end out if..
		}
		
		if (score==dots.length*dots[0].length-1){
			animationTimer.stop();
			repaint();
			int selected	= JOptionPane.showConfirmDialog(this,"Congratulations!!\n"+
								"You Win! \nYour Score = " + score +" \nPlay again?",
                                    "Woooooooooowww",
                                    JOptionPane.YES_NO_OPTION);

			if (selected==JOptionPane.OK_OPTION){
				restart();
				}
			else return;
						
		}
	}
	
	public void restart(){
		score = -1;
		eater.setLocation(gateWidth, (vState.length/2)*(pathWidth+gateWidth)+
									  gateWidth+((pathWidth-eater.getWidth())/2));
		
		eater.setSequence(eaterEastUpSequence);
		placeEnemies();
		reinstateDots();
		animationTimer.start();
		
	}
	
	public void moveAnimation (Animation a){
		Point p1;
		Point p2;
		int aWidth = a.getWidth();
		int aHeight= a.getHeight();
		
		int v = a.getVelocity();
		int cushion, row1, row2, col1, col2;
		
		switch (a.getDirection()){
			case EAST:
				p1 	= new Point (a.getX()+aWidth, a.getY());
				p2 	= new Point (a.getX()+aWidth, a.getY()+aHeight);
				row1 = p1.y/cellWidth;
				row2 = (p2.y-1)/cellWidth;
				col1 = (p1.x)/cellWidth;
				cushion = cellWidth-(p1.x%cellWidth);
				if (cushion<v && cushion>0){
					a.setLocation(a.getX()+cushion, a.getY());
					return;									
				}
			 if (p1.x%cellWidth==0){
				if(vState[row1][col1]==0||vState[row2][col1]==0){
					if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?NORTH:SOUTH);
						a.setSequence(rand<1?enemyNorthSequence: enemySouthSequence);
					}
				return;						
				}
			  }
				if ((p1.y-gateWidth)%cellWidth>cellWidth-aHeight||
					p1.y%(cellWidth)>cellWidth-aHeight){
					if (col1<hState[0].length){
						if (hState[row2][col1]==0){
						if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?NORTH:SOUTH);
						a.setSequence(rand<1?enemyNorthSequence: enemySouthSequence);
					}
						return;
					}	
					}
				}
				a.setLocation(a.getX()+v, a.getY());
			
			break;
			case WEST:
				p1 	= new Point (a.getX(), a.getY());
				p2 	= new Point (a.getX(), a.getY()+aHeight);
				row1 = p1.y/cellWidth;
				row2 = (p2.y-1)/cellWidth;
				col1 = p1.x/cellWidth;
				cushion = (p1.x%cellWidth)-gateWidth;
							
				if (cushion<v && cushion>0){
					a.setLocation(a.getX()-cushion, a.getY());
					return;									
				}
				
			  if ((p1.x-gateWidth)%cellWidth==0){
					
				if(vState[row1][col1]==0||vState[row2][col1]==0){
					if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?NORTH:SOUTH);
						a.setSequence(rand<1?enemyNorthSequence: enemySouthSequence);
					}
						return;
					}	
				}
				
				if ((p1.y-gateWidth)%cellWidth>cellWidth-aHeight||
					 p1.y%cellWidth>cellWidth-aHeight){
					col1=(p1.x-gateWidth*2)/cellWidth;
					if (hState[row2][col1]==0){
						if (a.getID()==ENEMY){
							double rand = 2*Math.random();
							a.setDirection(rand<1?NORTH:SOUTH);
							a.setSequence(rand<1?enemyNorthSequence: enemySouthSequence);
						}	
						return;
					}	
				}
				
				a.setLocation(a.getX()-v, a.getY());
				
			break;
			case NORTH:
				p1 	= new Point (a.getX(), a.getY());
				p2 	= new Point (a.getX()+aWidth, a.getY());
				row1 = p1.y/cellWidth;
				col1 = p1.x/cellWidth;
				col2 = (p2.x-1)/cellWidth;
				if (col2>=hState[0].length){
					col2= hState[0].length-1;
				}//end if
				
				cushion = p1.y%cellWidth-gateWidth;
							
				if (cushion<v && cushion>0){
					a.setLocation(a.getX(), a.getY()-cushion);
					return;									
				}//end if
				
			  if ((p1.y-gateWidth)%cellWidth==0){
					
				if(hState[row1][col1]==0||hState[row1][col2]==0){
						if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?EAST:WEST);
						a.setSequence(rand<1?enemyEastSequence:enemyWestSequence);
						}
						return;
					}//end inner if	
				}//end outer if
				
				if (!(p1.x%cellWidth>=gateWidth&&p1.x%cellWidth<=cellWidth-aWidth)){
					row1= (p1.y-gateWidth*2)/cellWidth;
					if (vState[row1][col2]==0){
						if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?EAST:WEST);
						a.setSequence(rand<1?enemyEastSequence:enemyWestSequence);
						}//end inner if
						return;
					}//end middle if	
				}//end outer if
				a.setLocation(a.getX(), a.getY()-v);
				
			break;
				
			case SOUTH:
				p1 	= new Point (a.getX(), a.getY()+a.getHeight());
				p2 	= new Point (a.getX()+aWidth, a.getY()+a.getHeight());
				row1 = p1.y/cellWidth;
				col1 = p1.x/cellWidth;
				col2 = (p2.x-1)/cellWidth;
				if (col2>=hState[0].length){
					col2= hState[0].length-1;
				}//end if
					
				
				cushion = cellWidth-(p1.y%cellWidth);
							
				if (cushion<v && cushion>0){
					a.setLocation(a.getX(), a.getY()+cushion);
					return;									
				}//end if
				
			  if (p1.y%cellWidth==0){
					
				if(hState[row1][col1]==0||hState[row1][col2]==0){
						if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?EAST:WEST);
						a.setSequence(rand<1?enemyEastSequence:enemyWestSequence);
						}
						return;
					}//end inner if	
				}//end outer if
				
				if (p1.x%cellWidth>cellWidth-aWidth||
					(p1.x-gateWidth)%cellWidth>cellWidth-aWidth){
					if (vState[row1][col2]==0){
						if (a.getID()==ENEMY){
						double rand = 2*Math.random();
						a.setDirection(rand<1?EAST:WEST);
						a.setSequence(rand<1?enemyEastSequence:enemyWestSequence);
						}//end inner if
						return;
					}//end middle if	
				}//end outer if
				a.setLocation(a.getX(), a.getY()+v);
				
			break;
		}//end switch..
				
	}
	
	public void keyPressed (KeyEvent e){
	  
	  int keyCode = e.getKeyCode();
   		if (keyCode==e.VK_LEFT){
   		   	eater.setDirection(WEST);
			eater.setSequence(eaterWestUpSequence);	   		   	
   			eater.setVelocity(6);	
   		  	
   		}//end if..
   		else if (keyCode==e.VK_RIGHT){
   		  	eater.setDirection(EAST);	
   			eater.setSequence(eaterEastUpSequence);
   			eater.setVelocity(5);
   		}  //end if..	
   		else if (keyCode==e.VK_UP){
   			eater.setDirection(NORTH);	
   			eater.setSequence(eaterNorthUpSequence);
   			eater.setVelocity(5);
   		     			
   		}  //end if..	
   		else if (keyCode==e.VK_DOWN){
   			eater.setDirection(SOUTH);	
   			eater.setSequence(eaterSouthUpSequence);
   			eater.setVelocity(4);
   			
   		}//end if..
   		else return;
	}
	
	public void keyReleased (KeyEvent e){
		int keyCode = e.getKeyCode();
    	
    	if (keyCode==e.VK_LEFT||keyCode==e.VK_RIGHT||keyCode==e.VK_UP||keyCode==e.VK_DOWN)
    	{	    	
    		eater.setVelocity(0);
    	}
    	else
    		return;
		
	}
	public void keyTyped (KeyEvent e){}
	
	class Dot extends JPanel {
		ImageIcon icon = new ImageIcon ("images/dot.gif");
		int state;
		public Dot () {
			super (null);
			setOpaque(false);
			setSize(5, 5);
			state=0;
		}
		
		public void paintComponent (Graphics g){
			super.paintComponent(g);
			if (state==0)
			icon.paintIcon(this, g, 0, 0);
		}
		
		public void eliminate(){
			if (state==0){
				control.getLabel().setText("Score  =  " + ++score);
				state=1;
				repaint();
			}
			
			
		}
	} //end class Dot..
	class Gate extends JPanel {
			
		public Gate(int state){
		setBackground(Color.black);
			if (state!=0)
				setOpaque(false);
		
		}
	}//end class Gate..
}//end class Background...