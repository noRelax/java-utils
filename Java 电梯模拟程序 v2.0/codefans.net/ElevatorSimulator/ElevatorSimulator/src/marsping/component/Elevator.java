/*
 * Elevator.java
 *
 * Created on 2006年1月4日, 上午 3:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package marsping.component;

import marsping.component.type.Floor;

/**
 *
 * @author MarsPing
 */
public class Elevator {
    private static Floor currentFloor;
    
    /** Creates a new instance of Elevator */
    public Elevator(Floor f) {
        currentFloor = f;
    }
    
    public static Floor getCurrentFloor(){
        return currentFloor;
    }    
    
    public void goUpward(){
        switch(currentFloor.ordinal()+1){
            case 0:
                currentFloor = Floor.B1;
                break;
            case 1:
                currentFloor = Floor.F1;
                break;
            case 2:
                currentFloor = Floor.F2;
                break;
            case 3:
                currentFloor = Floor.F3;
                break;
            case 4:
                currentFloor = Floor.F4;
                break;
            case 5:
                currentFloor = Floor.F5;
                break;
            case 6:
                currentFloor = Floor.F6;
                break;
            case 7:
                currentFloor = Floor.F7;
                break;
            default:
                currentFloor = null;
        }
    }
    
    public void goDownward(){
        switch(currentFloor.ordinal()-1){
            case 0:
                currentFloor = Floor.B1;
                break;
            case 1:
                currentFloor = Floor.F1;
                break;
            case 2:
                currentFloor = Floor.F2;
                break;
            case 3:
                currentFloor = Floor.F3;
                break;
            case 4:
                currentFloor = Floor.F4;
                break;
            case 5:
                currentFloor = Floor.F5;
                break;
            case 6:
                currentFloor = Floor.F6;
                break;
            case 7:
                currentFloor = Floor.F7;
                break;
            default:
                currentFloor = null;
        }
    }
    
}
