/*
 * CallList.java
 *
 * Created on 2005年12月29日, 上午 10:30
 * Download:http://www.codefans.net
 */

package marsping.component.data;

import marsping.component.type.Floor;
import java.util.ArrayList;

/**
 *
 * @author MarsPing
 */
public class CallList {
    private ArrayList<Floor> firstList;
    private ArrayList<Floor> bufferList;
    
    /** Creates a new instance of CallList */
    public CallList() {
        firstList = new ArrayList<Floor>();
        bufferList = new ArrayList<Floor>();
    }
    
    public String toString(){
        StringBuffer string = new StringBuffer();
        string.append("First:");
        for(Floor f:firstList){
            string.append(" "+f.toString()+";");
        }
        string.append("\n");
        string.append("Buffer:");        
        for(Floor f:bufferList){
            string.append(" "+f.toString()+";");
        }        
        
        return string.toString();
    }
    
    public synchronized boolean isEmpty(){
        return isFirstEmpty() & isBufferEmpty();
    }
    
    public synchronized boolean isFirstEmpty(){
        if(firstList.size()==0) {
            return true;
        }
        return false;
    }
    
    public synchronized boolean isBufferEmpty(){
        if(bufferList.size()==0) {
            return true;
        }
        return false;
    }
    
    public synchronized void swapList(){
        ArrayList<Floor> tempList;
        
        tempList = firstList;
        firstList = bufferList;
        bufferList = tempList;
    }
    
    public synchronized boolean removeFirstElement(Floor f) {
        return firstList.remove(f);
        //return firstList.remove(0);
    }
    
    /*
    public synchronized Floor removeFirstElement() {
        return firstList.remove(0);
    }*/
    
    public synchronized Floor getFirstElement() {
        if(firstList.size()==0)
            return null;
        return firstList.get(0);
    }
    
    public synchronized void addToFirstOrderAZ(Floor tf) {
        Floor f;
        for(int i=0; i<firstList.size(); i++){
            f = firstList.get(i);
            if(tf.ordinal()==f.ordinal())
                return;
            else if(tf.ordinal()<f.ordinal()){
                firstList.add(i,tf);
                return;
            }                
        }
        firstList.add(tf);
        //firstList.add(0, floor);
    }
    
    public synchronized void addToFirstOrderZA(Floor tf) {
        Floor f;
        for(int i=0; i<firstList.size(); i++){
            f = firstList.get(i);
            if(tf.ordinal()==f.ordinal())
                return;
            else if(tf.ordinal()>f.ordinal()){
                firstList.add(i,tf);
                return;
            }                
        }
        firstList.add(tf);
        //firstList.add(0, floor);
    }
    
    public synchronized void addToBufferOrderAZ(Floor tf) {
        Floor f;
        for(int i=0; i<bufferList.size(); i++){
            f = bufferList.get(i);
            if(tf.ordinal()==f.ordinal())
                return;
            else if(tf.ordinal()<f.ordinal()){
                bufferList.add(i,tf);
                return;
            }                
        }
        
        bufferList.add(tf);
    }
    
    public synchronized void addToBufferOrderZA(Floor tf) {
        Floor f;
        for(int i=0; i<bufferList.size(); i++){
            f = bufferList.get(i);
            if(tf.ordinal()==f.ordinal())
                return;
            else if(tf.ordinal()>f.ordinal()){
                bufferList.add(i,tf);
                return;
            }                
        }
        
        bufferList.add(tf);
    }
    
    /*
     * 只有確保==>如果每按一次就更新一次=>沒問題
     * 不同步==>會有問題
     */
    /*
    public synchronized void sortAZ() {
        Floor first, second;
        
        for(int i=0; i+1<firstList.size(); i++){
            first = firstList.get(i);
            second = firstList.get(i+1);
            
            if(first.ordinal()>second.ordinal()){
                firstList.add(i+1, firstList.remove(i));
            }
        }
        
        for(int i=0; i+1<bufferList.size(); i++){
            first = bufferList.get(i);
            second = bufferList.get(i+1);
            
            if(first.ordinal()>second.ordinal()){
                bufferList.add(i+1, bufferList.remove(i));
            }
        }
    }
     */
    
    /*
    public synchronized void sortZA() {
        Floor first, second;
        
        for(int i=0; i+1<firstList.size(); i++){
            first = firstList.get(i);
            second = firstList.get(i+1);
            
            if(first.ordinal()<second.ordinal()){
                firstList.add(i+1, firstList.remove(i));
            }
        }
        
        for(int i=0; i+1<bufferList.size(); i++){
            first = bufferList.get(i);
            second = bufferList.get(i+1);
            
            if(first.ordinal()<second.ordinal()){
                bufferList.add(i+1, bufferList.remove(i));
            }
        }
    }
     */
}
