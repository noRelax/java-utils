/*
 * Door.java
 *
 * Created on 2006�~1��4��, �W�� 3:36
 */

package marsping.component;

/**
 *
 * @author  MarsPing
 */
public class Door extends javax.swing.JPanel {
    
    /** Creates new form Door */
    public Door() {
        initComponents();
    }
    
    public void openDoor(){        
        for(int i=1; i<=5; i++){
            leftDoor.setLocation(0-10*i,0);
            rightDoor.setLocation(50+10*i,0);
            
            try{
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void closeDoor(){
        for(int i=1; i<=5; i++){
            leftDoor.setLocation(-50+10*i,0);
            rightDoor.setLocation(100-10*i,0);
            
            try{
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        leftDoor = new javax.swing.JPanel();
        rightDoor = new javax.swing.JPanel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setPreferredSize(new java.awt.Dimension(100, 100));
        leftDoor.setBackground(new java.awt.Color(153, 153, 255));
        leftDoor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        org.jdesktop.layout.GroupLayout leftDoorLayout = new org.jdesktop.layout.GroupLayout(leftDoor);
        leftDoor.setLayout(leftDoorLayout);
        leftDoorLayout.setHorizontalGroup(
            leftDoorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 46, Short.MAX_VALUE)
        );
        leftDoorLayout.setVerticalGroup(
            leftDoorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 96, Short.MAX_VALUE)
        );
        add(leftDoor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        rightDoor.setBackground(new java.awt.Color(153, 153, 255));
        rightDoor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        org.jdesktop.layout.GroupLayout rightDoorLayout = new org.jdesktop.layout.GroupLayout(rightDoor);
        rightDoor.setLayout(rightDoorLayout);
        rightDoorLayout.setHorizontalGroup(
            rightDoorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 46, Short.MAX_VALUE)
        );
        rightDoorLayout.setVerticalGroup(
            rightDoorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 96, Short.MAX_VALUE)
        );
        add(rightDoor, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel leftDoor;
    private javax.swing.JPanel rightDoor;
    // End of variables declaration//GEN-END:variables
    
}
