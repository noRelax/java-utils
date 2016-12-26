/*
 * Direction.java
 *
 * Created on 2005年12月27日, 下午 2:40
 * Download:http://www.codefans.net
 */

package marsping.component.type;

/**
 *
 * @author MarsPing
 */
public enum Direction {
    UP, DOWN, NO_DIRECTION;
    
    private static Direction dir = NO_DIRECTION;
    
    public static void setDirectionUp(){
        dir = UP;
    }
    
    public static void setDirectionDown(){
        dir = DOWN;
    }
    
    public static void resetDirection(){
        dir = NO_DIRECTION;
    }
    
    public static Direction getDirection(){
        return dir;
    }
    
    public String toString(){
        String temp;
        switch(this.ordinal()){
            case 0:
                return "▲";
            case 1:
                return "▼";
        }
        return "null";
    }
}
