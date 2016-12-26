/*
 * Floor.java
 *
 * Created on 2005年12月27日, 下午 3:18
 *
 */

package marsping.component.type;

/**
 *
 * @author MarsPing
 */
public enum Floor {
    B1,F1,F2,F3,F4,F5,F6,F7;
        
    public String toString(){
        String temp;
        switch(this.ordinal()){
            case 0:
                return "-1";
            case 1:
                return "1";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "7";
        }
        return "null";
    }
}
