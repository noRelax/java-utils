package QQ_test1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class choose_head_pic  extends JFrame implements ActionListener,MouseListener
{
	private int n=4;
	private JLabel[][] pic_label=new JLabel[n][n];
	private JLabel preview_label1,preview_label2;
	private RecButton summit_button; 
	private Icon image;
	private boolean setted=false;
	private int w,h;
	int i=0,j=0,x=0,y=0,setted_x,setted_y;
	private JPanel main_panel;
	public choose_head_pic()
	{
		setTitle("HAHA-CHAT选择头像");
		setSize(330,260);
		//setBackground(Color.BLACK);
		setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
		try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}catch(Exception e){e.printStackTrace();}
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		int num=0;
		image=new ImageIcon("face\\1.jpg");
		w=image.getIconWidth();
		h=image.getIconHeight();
		main_panel=new JPanel();
		main_panel.setLayout(null);
		main_panel.setBackground(Color.DARK_GRAY);
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				num=i*4+j+1;
				System.out.println(num);
				pic_label[i][j]=new JLabel();
				image=new ImageIcon("face\\"+num+".jpg"); 
				pic_label[i][j].setIcon(image);
				pic_label[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				pic_label[i][j].addMouseListener(this);
				pic_label[i][j].setBounds(new Rectangle(5+(w+5)*i,5+(h+5)*j,w,h));
				main_panel.add(pic_label[i][j]);
				pic_label[i][j].setBorder(BorderFactory.createBevelBorder(0));
			}
		}
		preview_label1=new JLabel("预览");
		preview_label2=new JLabel();
		preview_label1.setForeground(Color.green);
		image=new ImageIcon("face\\2.jpg"); 
		preview_label2.setIcon(image);
		
		preview_label1.setBounds(new Rectangle(255,20,50,20));
		preview_label2.setBounds(new Rectangle(250,40,w,h));

		preview_label1.setFont(new Font("宋体",Font.BOLD,12));
		summit_button=new RecButton("确定");
		summit_button.setForeground(Color.green);
		summit_button.setBounds(new Rectangle(240,180,70,25));
		summit_button.addActionListener(this);
		main_panel.setBounds(new Rectangle(20,20,(w+5)*n+5,(h+5)*n+5));
		c.add(main_panel);
		c.add(preview_label1);
		c.add(preview_label2);
		c.add(summit_button);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("确定"))          
		{
			int num=setted_x*4+setted_y+1;
			System.out.println("x"+setted_x+" y"+setted_y);
			image=new ImageIcon("face\\"+num+".jpg"); 
			set_personal_inf.face_pic2.setIcon(image);
			set_personal_inf.head_pic_path=num+".jpg";
			dispose();
		}
	}
	public void mousePressed(MouseEvent e){	}
	public void mouseReleased(MouseEvent e)	{	}
	public void mouseEntered(MouseEvent e)
	{
		JLabel l=(JLabel)e.getSource();
		x=0;y=0;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				if(l==pic_label[i][j])
				{
					x=i;y=j;
					break;
				}
			}
		}
		pic_label[x][y].setBorder(BorderFactory.createBevelBorder(1));
	}
	public void mouseExited(MouseEvent e)
	{  
		JLabel l=(JLabel)e.getSource();
		x=0;y=0;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				if(l==pic_label[i][j])
				{
					x=i;y=j;
					break;
				}
			}
		}
		pic_label[x][y].setBorder(BorderFactory.createBevelBorder(0));
	}
	public void mouseClicked(MouseEvent e)
	{
		JLabel l=(JLabel)e.getSource();
		x=0;y=0;
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				if(l==pic_label[i][j])
				{
					setted_x=i;setted_y=j;
					break;
				}
			}
		}
		int num=setted_x*4+setted_y+1;
		image=new ImageIcon("face\\"+num+".jpg"); 
		preview_label2.setIcon(image);
	}
	public static void main(String args[])
	{
		choose_head_pic c=new choose_head_pic();
		c.setVisible(true);
	}
}
