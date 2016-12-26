/*
 * ElevatorController.java
 *
 * Created on 2005年12月28日, 下午 10:08
 *
 */

package marsping.component;

import marsping.component.data.CallList;
import marsping.component.type.Direction;
import marsping.component.type.Floor;
import java.util.ArrayList;
import marsping.CurrentFloorPanel;
import marsping.ListPanel;

/**
 *
 * @author MarsPing
 */
public class ElevatorController implements Runnable{
    private static boolean moving = false;
    private static boolean powerOn = false;
    private static boolean doorOpen = false;
    private static CallList upCallList = new CallList();
    private static CallList downCallList = new CallList();
    private OperationPanel opPanel;
    private CurrentFloorPanel cfPanel;
    private ListPanel lPanel;
    private ArrayList<CallPanel> cpList;
    private Elevator elevator = new Elevator(Floor.F1);
    private long time = System.currentTimeMillis();;
    
    /** Creates a new instance of ElevatorController */
    public ElevatorController() {
    }
    
    public void setListPanel(ListPanel lPanel){
        this.lPanel = lPanel;
    }
    public void setCurrentFloorPanel(CurrentFloorPanel cfPanel){
        this.cfPanel = cfPanel;
    }
    
    public void setCallPanelList(ArrayList<CallPanel> cpList){
        this.cpList = cpList;
    }
    
    public void setOperationPanel(OperationPanel opPanel){
        this.opPanel = opPanel;
    }
    
    public void setAllBorder(){
        for(CallPanel cp:cpList){
            cp.resetBorder();
        }
        cpList.get(Elevator.getCurrentFloor().ordinal()).setBorder();
    }
    
    public void run(){
        statusWaiting();
    }
    
    public void turnOn() {
        powerOn = true;
    }
    
    public void turnOff() {
        powerOn = false;
    }
    
    public synchronized void updateList(Floor tf){
        Floor cf = elevator.getCurrentFloor();
        
        if(tf.ordinal()>=cf.ordinal())
            updateUpCallList(tf);
        else
            updateDownCallList(tf);
    }
    
    public synchronized void updateUpCallList(Floor tf) {
        Direction dir = Direction.getDirection();
        Floor cf = elevator.getCurrentFloor();
        
        if(upCallList.isFirstEmpty() && !upCallList.isBufferEmpty())
            upCallList.swapList();
        
        if(dir.ordinal() == Direction.UP.ordinal() && tf.ordinal()>=cf.ordinal()){
            upCallList.addToFirstOrderAZ(tf);
        }else if(dir.ordinal()==Direction.UP.ordinal() && tf.ordinal()<cf.ordinal()){
            if(upCallList.isFirstEmpty())
                upCallList.addToFirstOrderAZ(tf);
            else
                upCallList.addToBufferOrderAZ(tf);
        }else{
            upCallList.addToFirstOrderAZ(tf);
        }
        
        lPanel.setList(upCallList, downCallList);
    }
    
    public synchronized void updateDownCallList(Floor tf) {
        Direction dir = Direction.getDirection();
        Floor cf = elevator.getCurrentFloor();
        
        if(downCallList.isFirstEmpty() && !downCallList.isBufferEmpty())
            downCallList.swapList();
        
        if(dir.ordinal()==Direction.DOWN.ordinal() && tf.ordinal()<=cf.ordinal()){
            downCallList.addToFirstOrderZA(tf);
        }else if(dir.ordinal()==Direction.DOWN.ordinal() && tf.ordinal()>cf.ordinal()){
            if(downCallList.isFirstEmpty())
                downCallList.addToFirstOrderZA(tf);
            else
                downCallList.addToBufferOrderZA(tf);
        }else{
            downCallList.addToFirstOrderZA(tf);
        }
        
        lPanel.setList(upCallList, downCallList);
    }
    
    public synchronized static Floor getTargetFloor(){
        Direction dir = Direction.getDirection();
        
        if(dir.ordinal()==Direction.UP.ordinal()){
            if(!upCallList.isFirstEmpty())
                return upCallList.getFirstElement();
            else{                
                upCallList.swapList();
                Direction.setDirectionDown();
                return downCallList.getFirstElement();
            }
        }else if(dir.ordinal()==Direction.DOWN.ordinal()){
            if(!downCallList.isFirstEmpty())
                return downCallList.getFirstElement();
            else{
                downCallList.swapList();
                Direction.setDirectionUp();
                return upCallList.getFirstElement();
            }
        }else{
            if(upCallList.isEmpty()&!downCallList.isEmpty())
                return downCallList.getFirstElement();
            else if(upCallList.isEmpty()&!downCallList.isEmpty())
                return upCallList.getFirstElement();
            else
                return Elevator.getCurrentFloor();
        }
    }
    
    private void statusWaiting() {
        lPanel.setList(upCallList, downCallList);
        while(isPowerOn()){
            statusOperating();
            try{
                Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
            }
            if(isDoorOpen()){
                statusDoorOpenning();
            }
        }
    }
    
    private void move(){
        moving = true;
    }
    
    private void stop(){
        moving = false;
    }
    
    private boolean isMoving(){
        return moving;
    }
    
    private void statusOperating() {
        Floor cf,tf;
        while(!(upCallList.isEmpty()&downCallList.isEmpty())){
            lPanel.setList(upCallList, downCallList);
            move();
            
            tf = getTargetFloor();
            cf = elevator.getCurrentFloor();
            
            if(tf.ordinal()==cf.ordinal()){
                statusDoorOpenning();
                if(!(upCallList.isEmpty()&downCallList.isEmpty()))
                    achieveTF();
            }else{
                statusMoving();
                statusDoorOpenning();
                if(!(upCallList.isEmpty()&downCallList.isEmpty()))
                    achieveTF();
            }
        }
        lPanel.setList(upCallList, downCallList);
    }
    
    private void statusDoorOpenning(){
        setAllStatusPanel();
        stop();
        cpList.get(elevator.getCurrentFloor().ordinal()).openDoor();
        openDoor();
        
        while(isDoorOpen()){
            
            try{
                Thread.sleep(10);
            }catch(Exception e){
                e.printStackTrace();
            }
            if((System.currentTimeMillis()-time)>2000){
                closeDoor();
            }
        }
        cpList.get(elevator.getCurrentFloor().ordinal()).closeDoor();
        setAllStatusPanel();
    }
    
    private boolean isDoorOpen(){
        return doorOpen;
    }
    
    private void statusMoving(){
        Direction dir = Direction.getDirection();
        
        //超重要方向判定
        if(dir.ordinal()==Direction.NO_DIRECTION.ordinal()){
            if(getTargetFloor().ordinal()>elevator.getCurrentFloor().ordinal())
                Direction.setDirectionUp();
            else if(getTargetFloor().ordinal()<elevator.getCurrentFloor().ordinal())
                Direction.setDirectionDown();
        }
        /*
        if((dir.ordinal()==Direction.NO_DIRECTION.ordinal() && !upCallList.isFirstEmpty())||
                (dir.ordinal()==Direction.NO_DIRECTION.ordinal() && downCallList.isFirstEmpty())){
            Direction.setDirectionUp();
        }else{
            Direction.setDirectionDown();
        }
         */
        
        setAllStatusPanel();
        
        if(elevator.getCurrentFloor().ordinal()<=getTargetFloor().ordinal())
            statusMovingUp();
        else
            statusMovingDown();
    }
    
    private void statusMovingUp(){
        while(getTargetFloor().ordinal()>elevator.getCurrentFloor().ordinal()){
            setAllStatusPanel();
            elevator.goUpward();
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
            setAllBorder();
            cfPanel.setAllPanel();
        }
    }
    
    private void statusMovingDown(){
        while(getTargetFloor().ordinal()<elevator.getCurrentFloor().ordinal()){
            setAllStatusPanel();
            elevator.goDownward();
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
            setAllBorder();
            cfPanel.setAllPanel();
        }
    }
    
    
    private synchronized void achieveTF(){
        Direction dir = Direction.getDirection();
        Floor cf = elevator.getCurrentFloor();
        opPanel.resetButton(cf);
        
        cpList.get(cf.ordinal()).resetButton(dir);
        
        switch(dir){
            case DOWN:
                if(!downCallList.removeFirstElement(cf))
                    upCallList.removeFirstElement(cf);
                break;
            case UP:
                upCallList.removeFirstElement(cf);
                break;
            case NO_DIRECTION:
                upCallList.removeFirstElement(cf);
                break;
        }
        
        if(upCallList.isEmpty()&&downCallList.isEmpty()){
            Direction.resetDirection();
        }
        setAllStatusPanel();
    }
    
    public void openDoor(){
        if(!isMoving()){
            doorOpen = true;
            System.out.println("開門");
            time = System.currentTimeMillis();
            
            //cpList.get(elevator.getCurrentFloor().ordinal()).openDoor();
        }
    }
    
    public void closeDoor(){
        if(isDoorOpen()){
            doorOpen = false;
            System.out.println("關門");
            time = System.currentTimeMillis();
            
            //cpList.get(elevator.getCurrentFloor().ordinal()).closeDoor();
        }
    }
    
    private void setAllStatusPanel(){
        for(CallPanel cp:cpList){
            cp.setStatus();
        }
        opPanel.setStatus();
    }
    
    public boolean isPowerOn() {
        return powerOn;
    }
}
