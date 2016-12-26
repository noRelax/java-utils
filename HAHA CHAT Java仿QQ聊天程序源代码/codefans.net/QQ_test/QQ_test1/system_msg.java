package QQ_test1;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class system_msg extends JWindow
{
	int   width=0,image_wide=0;   
	int   height=0,image_height=0;   
	JPanel main_panel;
	JLabel exit_label,msg_label,top_label;
	Icon image;
	public system_msg(String msg)
	{
		width=Toolkit.getDefaultToolkit().getScreenSize().width;   
		height=Toolkit.getDefaultToolkit().getScreenSize().height;   
		image=new ImageIcon("pic\\system_msg_bcg.gif"); 
		image_wide=image.getIconWidth();
		image_height=image.getIconHeight();
		setSize(image_wide,image_height);
		
		this.setLocation(width-image_wide-1,height-31-image_height);
		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.getHSBColor((float)(130/256.0),(float) (210/256.0), (float)(238/256.0)));
		
		final Image image1 = Toolkit.getDefaultToolkit().createImage("pic\\system_msg_bcg.gif"); 
		main_panel= new JPanel(){ 
			protected void paintChildren(Graphics g) { 
				g.drawImage(image1,0,0,this); 
				super.paintChildren(g); 
			} 
		}; 
		main_panel.setLayout(null);
		
		
		exit_label=new JLabel();
		image=new ImageIcon("pic\\close_button2.jpg"); 
		exit_label.setIcon(image);
		exit_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit_label.addMouseListener(new MouseAdapter() {
		     public void mouseClicked(MouseEvent e) {
		         if (e.getClickCount()==1) 
		         {
		            dispose();
		         }
		     }});
		
		top_label=new JLabel("系统消息");
		top_label.setForeground(Color.red);
		top_label.setFont(new Font("楷体",Font.BOLD,25));
		
		msg_label=new JLabel("<html>"+msg+"</html>");
		msg_label.setForeground(Color.black);
		msg_label.setFont(new Font("宋体",Font.PLAIN,18));
		
		main_panel.setBounds(new Rectangle(0,0,image_wide,image_height));
		top_label.setBounds(new Rectangle(image_wide/2-55,10,150,30));
		
		JScrollPane scroll=new JScrollPane(msg_label);
		exit_label.setBounds(new Rectangle(image_wide-30, 0,30,20));
		scroll.setBounds(new Rectangle(60,60,170,130));
		
		main_panel.add(top_label);
		main_panel.add(exit_label);
		main_panel.add(scroll);
		c.add(main_panel);
	}
	
	public static void main (String args[])
	{
		system_msg s=new system_msg("FUCK你没商量无极恢复撒办法是对付你dfgdfgdfdgdfgdfgdfgdfgdfgdfgdfgdf的积分就打发你点击");
		s.setVisible(true);
	}
}
