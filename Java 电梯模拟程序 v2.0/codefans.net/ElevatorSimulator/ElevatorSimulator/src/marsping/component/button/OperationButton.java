/*
 * OperationButton.java
 *
 * Created on 2005年12月27日, 下午 2:16
 * Download:http://www.codefans.net
 */

package marsping.component.button;

import marsping.component.ElevatorController;
import marsping.component.type.Operation;
import java.awt.event.MouseEvent;

/**
 *
 * @author MarsPing
 */
public class OperationButton extends Button{
    private Operation operation;
    
    /** Creates a new instance of OperationButton */
    public OperationButton(Operation operation, ElevatorController controller) {
        super(controller);
        
        this.operation = operation;
        setText(operation.toString());
    }
    
    protected void buttonMouseClicked(MouseEvent evt){
        if(!controller.isPowerOn())
            return;
        
        switch(operation){
            case OPEN:
                controller.openDoor();
                break;
            case CLOSE:
                controller.closeDoor();
                break;
        }
    }
}
