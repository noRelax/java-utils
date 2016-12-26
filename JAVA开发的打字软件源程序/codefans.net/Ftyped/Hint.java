import javax.swing.*;

public class Hint extends JInternalFrame{
	Tool parent;
	public Hint(Tool parent){
		this.parent=parent;
		setLayer(4);
        setBounds(142,326,0,0); 
        this.setPreferredSize(new java.awt.Dimension(657, 223));
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        this.setVisible(true);
        this.pack();
        this.add(new Keyset());
        if(PublicData.choice!=2){
	        Editor edit=new Editor(this);
	        this.parent.parent.add(edit,2);
	        edit.requestFocus(); 
        }
        else{
        	Etymon etymon=new Etymon(this);
        	this.parent.parent.add(etymon,7);
        }
	}
}