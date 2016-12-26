import javax.swing.*;
import java.awt.*;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Chat  extends JInternalFrame{
	MainFrame parent;
	private JTextPane jtp;
	private JTextField txtData;

	public Chat(MainFrame parent){
		this.parent=parent;

		setLayer(4);
        setBounds(1,326,0,0); 
        this.setPreferredSize(new java.awt.Dimension(140, 223));
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE); 
		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.rowWeights = new double[] {0.1, 0.1, 0.0, 0.0};
		thisLayout.rowHeights = new int[] {7, 7, 49, 56};
		thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0};
		thisLayout.columnWidths = new int[] {0, 45, 45, 48};
		getContentPane().setLayout(thisLayout);
        setVisible(true);
        pack();
		{
			jtp = new JTextPane();
			getContentPane().add(jtp, new GridBagConstraints(1, 0, 4, 5, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			jtp.setPreferredSize(new Dimension(140, 160));
			jtp.setEditable(false);
		}
		{
			txtData = new JTextField();
			getContentPane().add(txtData, new GridBagConstraints(1, 7, 3, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			txtData.setColumns(10);
		}
	}
}