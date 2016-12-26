/*
 * DirectionButton.java
 *
 * Created on 2005年12月27日, 下午 2:15
 *
 */

package marsping.component.button;

import marsping.component.ElevatorController;
import marsping.component.type.Direction;
import marsping.component.type.Floor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

/**
 *
 * @author MarsPing
 */
public class DirectionButton extends Button{
    private Floor floor;
    private Direction dir;
    
    /** Creates a new instance of DirectionButton */
    public DirectionButton(Direction dir,Floor floor,ElevatorController controller) {
        super(controller);
        
        this.floor = floor;
        this.dir = dir;
        setText(dir.toString());
        setPreferredSize(new Dimension(30,30));
    }
    
    protected void buttonMouseClicked(MouseEvent evt){
        if(!controller.isPowerOn())
            return;
        
        super.buttonMouseClicked(evt);
        
        switch(dir){
            case UP:
                if(Direction.getDirection().ordinal()==Direction.NO_DIRECTION.ordinal())
                    Direction.setDirectionUp();
                controller.updateUpCallList(floor);
                break;
            case DOWN:
                if(Direction.getDirection().ordinal()==Direction.NO_DIRECTION.ordinal())
                    Direction.setDirectionDown();
                controller.updateDownCallList(floor);
                break;
        }
    }
    
}
