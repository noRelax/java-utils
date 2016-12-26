/*
 * FloorButton.java
 *
 * Created on 2005年12月27日, 下午 2:15
 *
 */

package marsping.component.button;

import marsping.component.ElevatorController;
import marsping.component.type.Floor;
import java.awt.event.MouseEvent;
import marsping.component.Elevator;
import marsping.component.type.Direction;

/**
 *
 * @author MarsPing
 */
public class FloorButton extends Button{
    private Floor floor;
    
    /** Creates a new instance of FloorButton */
    public FloorButton(Floor floor, ElevatorController controller) {
        super(controller);
        
        this.floor = floor;
        setText(floor.toString());
    }
    
    protected void buttonMouseClicked(MouseEvent evt){
        if(!controller.isPowerOn())
            return;
        
        super.buttonMouseClicked(evt);
        
        Floor cf;
        cf = Elevator.getCurrentFloor();        
        
        if(Direction.getDirection().ordinal()==Direction.NO_DIRECTION.ordinal()){
            if(cf.ordinal()<floor.ordinal())
                Direction.setDirectionUp();
            else if(cf.ordinal()>floor.ordinal())
                Direction.setDirectionDown();
        }
        
        controller.updateList(floor);
    }
}
