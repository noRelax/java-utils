//class Animation will define the Eater as well as the enemies...be careful with the 
//objects passed to the constructors..as unwary external modifination will cause problems..
//Finishing www.codefans.net
//setting size and location of an Animation object will be responsibility of the 
//class where this object will be created...


import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Animation extends JPanel implements GameConstants {
	
	private int direction;
	private int id;
	private int velocity;
	private int oldDirection;
	private boolean changeImage = true;
	private ImageIcon icons [];
		
	private int [] imageSequence;
	private int currentImage =0;//its value can be 0 to 1 less thatn imageSequence.length
	
	
	//constructor...
	
	public Animation (ImageIcon [] ic, int id, int d){
		super(null);
		setOpaque(false);
		icons = ic;
		this.id = id;
		direction = d;
		setSize(25, 25);
					
	}//end Constructor..
	
	public int getID (){
		return id;
	}
		
	public void setVelocity (int v){
		if (v>=0 && v<=MAX_VELOCITY)
			velocity = v;
	}
	public int getVelocity (){
		return velocity;
	}
			
	public void setSequence (int [] newSequence){
		imageSequence = newSequence;
	
	}
	
	public int [] getSequence (){
		return imageSequence;
	}
	

	public void setIcons (ImageIcon [] ic){
		icons = ic;
	}
	
	public ImageIcon [] getIcons (){
		return icons;
	}
	
	public void setDirection (int d){
		if (direction!=d){
		oldDirection = direction;
		direction =d;
		}		
		
	}//end method setDirection..
	
	public int getOldDirection (){
		return oldDirection;
	}
	
	public int getDirection() {
		return direction;
	}
					
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		
		if (currentImage>=imageSequence.length){
			currentImage=0;	
		}
		
		icons[imageSequence[currentImage]].paintIcon(this, g, 0, 0);
		if (changeImage)
		++currentImage;
		changeImage = !changeImage;
					
	}		
	
	}//end class Animation..
