/*
 * Operation.java
 *
 * Created on 2005�~12��27��, �U�� 3:37
 * Download:http://www.codefans.net
 */

package marsping.component.type;

/**
 *
 * @author MarsPing
 */
public enum Operation {
    OPEN, CLOSE;
    
    public String toString(){
        String temp;
        switch(this.ordinal()){
            case 0:
                return "�ա�";
            case 1:
                return "�֡�";
        }
        return "null";
    }
}
