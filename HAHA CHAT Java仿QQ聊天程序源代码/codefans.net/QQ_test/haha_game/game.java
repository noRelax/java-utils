package haha_game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	/*
	 * JFrame的模块化程序
	 * 
	 * */
public class game extends JFrame
{
	JLabel label[];
	public game()
	{
		setTitle("HAHA GAME");
		setSize(300,600);
		setLocationRelativeTo(null);            //此行将窗口显示在屏幕中心
		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		JLabel top_label=get_label("HAHA GAME",Color.red,40,40,30,240,50);
		label=new JLabel[6];
		label[0]=get_pic_label("五子棋","五子棋.gif",20,120);
		label[1]=get_pic_label("黑白棋","黑白棋.gif",160,120);
		label[2]=get_pic_label("扫雷","扫雷.gif",20,260);
		label[3]=get_pic_label("推箱子","推箱子.gif",160,260);
		label[4]=get_pic_label("拼图","拼图.gif",20,400);
		label[5]=get_pic_label("贪吃蛇","贪吃蛇.gif",160,400);
		//Download by http://www.codefans.net
		c.add(top_label);
		c.add(label[0]);c.add(label[1]);c.add(label[2]);
		c.add(label[3]);c.add(label[4]);c.add(label[5]);
		
	}
	public JLabel get_label(String label_name,Color font_color,int font_size,int x,int y,int w,int h)
	{
		JLabel l=new JLabel("<html>"+label_name+"</html>");	//如果标签内容超过一行，会自动换行
		l.setForeground(font_color);		//设置字体颜色
		l.setFont(new Font("宋体",Font.BOLD,font_size));
		l.setBounds(new Rectangle(x,y,w,h));		//设置标签绝对坐标
		return l;
	}
	public JLabel get_pic_label(String name,String path,int x,int y)
	{
		final JLabel l=new JLabel();	//如果标签内容超过一行，会自动换行
		Icon image=new ImageIcon("pic\\"+path); 		//为标签添加图片
		l.setIcon(image);
		l.addMouseListener(new MouseAdapter() 
		{
		     public void mouseClicked(MouseEvent e) 
		     {
		    	 int x=0;
		         if (e.getClickCount()==1) 
		         {
		     		JLabel la=(JLabel)e.getSource();
		    		for(int i=0;i<6;i++)
		    		{
		    			if(la==label[i])
		    			{
		    				x=i;
		    				break;
		    			}
		    		}
		         }
		         switch(x)
		         {
		         case 0:		//五子棋
		        	 wuziqi.main wu=new wuziqi.main();
		        	 wu.setVisible(true);
		        	 break;
		         case 1:		//黑白棋
		        	 black_white.main bw=new black_white.main();
		        	 bw.setVisible(true);
		        	 break;
		         case 2:		//扫雷
		        	 SaoLei.saolei sao=new SaoLei.saolei();
		        	 sao.setVisible(true);
		        	 break;
		         case 3:		//推箱子
		        	 boxman.main box=new boxman.main(1);
		        	 box.setVisible(true);
		        	 break;
		         case 4:		//拼图
		        	 pintu2.choose pin=new pintu2.choose();
		        	 pin.setVisible(true);
		        	 break;
		         case 5:		//贪吃蛇
		        	 greed_snake.Snake s=new greed_snake.Snake("贪吃蛇");
		        	 break;
		         }
		     }
		 	public void mouseEntered(MouseEvent e)
		 	{
	        	 Point p=l.getLocation();
	        	 l.setLocation(p.x-2,p.y-2);
		 	}
			public void mouseExited(MouseEvent e) 
			{ 
	        	 Point p=l.getLocation();
	        	 l.setLocation(p.x+2,p.y+2);
			}

		}
		 );
		l.setToolTipText(name);		//当鼠标停留在面板上时，会弹出提示
		l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	//设置鼠标形状
		l.setBorder(BorderFactory.createBevelBorder(1));	//为标签设定边框
		l.setBounds(new Rectangle(x,y,image.getIconWidth(),image.getIconHeight()));		//设置标签绝对坐标
		return l;
	}
	public static void main(String args[])
	{
		game m=new game();
		m.setVisible(true);
	}
}

