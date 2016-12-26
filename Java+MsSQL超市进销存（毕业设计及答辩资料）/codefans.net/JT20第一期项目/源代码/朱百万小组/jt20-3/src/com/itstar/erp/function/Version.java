package com.itstar.erp.function;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class Version extends JFrame {

	private JPanel full = null;
	private JLabel condition1 = null;
	private JLabel back=null;
	public Version() {
		super();
		initialize();
		this.setResizable(false);
		this.setVisible(true);
	}
	private void initialize() {
			this.setTitle("版本说明");
			this.setSize(new Dimension(369, 200));
			this.setLocation(385, 240);
			this.setContentPane(getJContentPane());
			full.setLayout(null);
			this.setResizable(false);
			addControls();
			}

	private void addControls() {
			condition1 = new JLabel();
			condition1.setText("本系统由杜智星，张力，李智强，李强华等人共同开发研制");
			condition1.setFont(new Font("Dialog", Font.BOLD, 12));
			condition1.setBounds(new Rectangle(8, 60, 350, 26));
			full.add(condition1, null);
			
			
			ImageIcon img = new ImageIcon("src/com/itstar/erp/ui/mainframe/pig.jpg");
			back=new JLabel(img);
			back.setBounds(0, 0, 369, 169);
			
			full.add(back,null);
		}

	private JPanel getJContentPane() {
		if (full == null) {
          full = new JPanel();
		}
		return full;
   }
   }  
