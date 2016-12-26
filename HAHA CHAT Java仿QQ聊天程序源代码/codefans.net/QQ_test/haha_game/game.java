package haha_game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	/*
	 * JFrame��ģ�黯����
	 * 
	 * */
public class game extends JFrame
{
	JLabel label[];
	public game()
	{
		setTitle("HAHA GAME");
		setSize(300,600);
		setLocationRelativeTo(null);            //���н�������ʾ����Ļ����
		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		JLabel top_label=get_label("HAHA GAME",Color.red,40,40,30,240,50);
		label=new JLabel[6];
		label[0]=get_pic_label("������","������.gif",20,120);
		label[1]=get_pic_label("�ڰ���","�ڰ���.gif",160,120);
		label[2]=get_pic_label("ɨ��","ɨ��.gif",20,260);
		label[3]=get_pic_label("������","������.gif",160,260);
		label[4]=get_pic_label("ƴͼ","ƴͼ.gif",20,400);
		label[5]=get_pic_label("̰����","̰����.gif",160,400);
		//Download by http://www.codefans.net
		c.add(top_label);
		c.add(label[0]);c.add(label[1]);c.add(label[2]);
		c.add(label[3]);c.add(label[4]);c.add(label[5]);
		
	}
	public JLabel get_label(String label_name,Color font_color,int font_size,int x,int y,int w,int h)
	{
		JLabel l=new JLabel("<html>"+label_name+"</html>");	//�����ǩ���ݳ���һ�У����Զ�����
		l.setForeground(font_color);		//����������ɫ
		l.setFont(new Font("����",Font.BOLD,font_size));
		l.setBounds(new Rectangle(x,y,w,h));		//���ñ�ǩ��������
		return l;
	}
	public JLabel get_pic_label(String name,String path,int x,int y)
	{
		final JLabel l=new JLabel();	//�����ǩ���ݳ���һ�У����Զ�����
		Icon image=new ImageIcon("pic\\"+path); 		//Ϊ��ǩ���ͼƬ
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
		         case 0:		//������
		        	 wuziqi.main wu=new wuziqi.main();
		        	 wu.setVisible(true);
		        	 break;
		         case 1:		//�ڰ���
		        	 black_white.main bw=new black_white.main();
		        	 bw.setVisible(true);
		        	 break;
		         case 2:		//ɨ��
		        	 SaoLei.saolei sao=new SaoLei.saolei();
		        	 sao.setVisible(true);
		        	 break;
		         case 3:		//������
		        	 boxman.main box=new boxman.main(1);
		        	 box.setVisible(true);
		        	 break;
		         case 4:		//ƴͼ
		        	 pintu2.choose pin=new pintu2.choose();
		        	 pin.setVisible(true);
		        	 break;
		         case 5:		//̰����
		        	 greed_snake.Snake s=new greed_snake.Snake("̰����");
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
		l.setToolTipText(name);		//�����ͣ���������ʱ���ᵯ����ʾ
		l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	//���������״
		l.setBorder(BorderFactory.createBevelBorder(1));	//Ϊ��ǩ�趨�߿�
		l.setBounds(new Rectangle(x,y,image.getIconWidth(),image.getIconHeight()));		//���ñ�ǩ��������
		return l;
	}
	public static void main(String args[])
	{
		game m=new game();
		m.setVisible(true);
	}
}

