 import javax.swing.*;
 import javax.swing.event.*;
 import javax.swing.text.html.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.io.*;
 import java.net.*;


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
public class Help extends JInternalFrame{
	
	JEditorPane html; 
	private JPanel p;
	private JButton btnCencel;

	public Help(){
		try { 
			URL url = null; 
				 	    // System.getProperty("user.dir") + 
				 	    // System.getProperty("file.separator"); 
			String path = null; 
			try { 
				path = "/help/index.html"; 
				url = getClass().getResource(path); 
			} catch (Exception e) { 
				System.err.println("Failed to open " + path); 
				url = null; 
			} 
			 	     
			if(url != null) { 
				html = new JEditorPane(url); 
				html.setEditable(false); 
				html.addHyperlinkListener(createHyperLinkListener());   
				JScrollPane scroller = new JScrollPane(); 
				JViewport vp = scroller.getViewport(); 
				vp.add(html); 
				add(scroller, BorderLayout.CENTER); 
			} 
			{
				p = new JPanel();
				getContentPane().add(p, BorderLayout.NORTH);
				p.setPreferredSize(new java.awt.Dimension(651, 28));
				p.setLayout(null);
				{
					btnCencel = new JButton();
					p.add(btnCencel);
					btnCencel.setText("\u79bb\u5f00");
					btnCencel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Image/leave.gif")));
					btnCencel.setBounds(574, 3, 77, 21);
					btnCencel.setFont(new java.awt.Font("新宋体",0,12));
					btnCencel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							PublicData.onChoice=true;
							exit();
						}
					});
				}
			}
		}catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e); 
		} catch (IOException e) { 
			System.out.println("IOException: " + e); 
		} 

		setLayer(4);
        setBounds(142,0,0,0); 
        this.setPreferredSize(new java.awt.Dimension(657, 549));//内部窗体大小
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        this.setVisible(true);
        this.pack();
	}
	public HyperlinkListener createHyperLinkListener() {
		return new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					if (e instanceof HTMLFrameHyperlinkEvent) {
						((HTMLDocument)html.getDocument()).processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent)e); 
					}
					else{
						try {
							html.setPage(e.getURL());
						} catch (IOException ioe) {
							System.out.println("IOE: " + ioe);
						}
					}
				}
			}
		};
	}
	
	private void exit(){
		PublicData.onChoice=true;
		System.gc();
		this.doDefaultCloseAction();
	}
}
