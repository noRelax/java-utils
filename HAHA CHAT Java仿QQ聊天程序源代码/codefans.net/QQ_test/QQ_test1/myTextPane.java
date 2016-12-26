package QQ_test1;
import java.awt.*; 
import java.awt.event.*; 
import java.io.File; 

import javax.swing.*; 
import javax.swing.text.*; 
public class myTextPane extends JTextPane
{
	
	private StyledDocument doc = null; // �ǳ���Ҫ����������ʽ�Ϳ����� 
	SimpleAttributeSet attrSet = new SimpleAttributeSet(); 
	public myTextPane()
	{
		doc = this.getStyledDocument(); // ���JTextPane��Document
		setFont("����",16,new Color(0,0,0),null,0);
	}
	public void insert_icon(String path) 
	{ 
		setCaretPosition(doc.getLength()); // ���ò���λ�� 
		insertIcon(new ImageIcon(path)); // ����ͼƬ 
	}
	public void insert(String text)
	{
			String temp1="",temp_image="";				//temp1��¼ǰ�벿�֣�temp2��¼��벿��
			int i=0,j=0;
			for(i=0;i<text.length();i++)
			{
				if(text.charAt(i)=='/')
				{
					j=i;
					for(;i<j+5&&i<text.length();i++)
					{
						temp_image+=text.charAt(i);
						if(text.charAt(i)=='\\')
						{
							break;
						}
					}
					if(i<text.length())
					{
						if(text.charAt(i)!='\\')
						{
							temp1+=temp_image;
							temp_image="";
						}
						else
						{
							if(!temp1.equals(""))
							{
								insert_text(temp1);
							}
							temp1="";
							if(!temp_image.equals(""))
							{
								String image_path=temp_image.substring(1);
								image_path=image_path.substring(0,image_path.length()-1);
								insert_icon("expression\\"+image_path+".gif");
							}
							temp_image="";
						}
					}
					else
					{
						temp1+=temp_image;
						temp_image="";
					}
				}
				else
				{
					temp1+=text.charAt(i);
				}
			}
			if(!temp1.equals(""))
			{
				insert_text(temp1);
			}
	}
	public void insert_text(String text)
	{
		try { //�����ı� 
			doc.insertString(doc.getLength(),text,attrSet);
		} catch (BadLocationException e) { 	e.printStackTrace(); 	}
	}
	public void insert_text2(String text)
	{
		try { //�����ı� 
			StyleConstants.setLeftIndent(attrSet, 20);
			doc.insertString(doc.getLength(),text,attrSet);
		} catch (BadLocationException e) { 	e.printStackTrace(); 	}
	}
	public void insertln(String text)
	{
		try { //�����ı� 
			insert(text);
			doc.insertString(doc.getLength(),"\n",attrSet); 
		} catch (BadLocationException e) { 	e.printStackTrace(); 	}
	}
	public void setFont(String font_name,int font_size,Color font_foreground_color,
			Color font_background_color,int font_style)
	{
		attrSet = new SimpleAttributeSet(); 
		if(font_name!= null) 
			StyleConstants.setFontFamily(attrSet, font_name); 
		StyleConstants.setFontSize(attrSet, font_size);
		
		if (font_style ==0)			//����
		{
			StyleConstants.setBold(attrSet, false); 
			StyleConstants.setItalic(attrSet, false); 
		}
		else if (font_style == 1)			//����
		{ 
			StyleConstants.setBold(attrSet, true); 
			StyleConstants.setItalic(attrSet, false); 
		} 
		else if (font_style ==2)			//б��
		{ 
			StyleConstants.setBold(attrSet, false); 
			StyleConstants.setItalic(attrSet, true); 
		} 
		else if (font_style ==3)			//	��б��
		{
			StyleConstants.setBold(attrSet, true); 
			StyleConstants.setItalic(attrSet, true); 
		}
		if (font_foreground_color!= null) 
			StyleConstants.setForeground(attrSet, font_foreground_color); 
		if (font_background_color!= null) 
			StyleConstants.setBackground(attrSet, font_background_color); 
	}
	public SimpleAttributeSet get_attrset()
	{
		return attrSet;
	}
	public void set_attrset(SimpleAttributeSet a)
	{
		attrSet=a;
	}
	public static void main(String args[])
	{
		JFrame f=new JFrame("�������");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		f.setSize(600, 400); 
		f.setLocationRelativeTo(null); 
		f.setVisible(true); 
		final myTextPane p=new myTextPane();
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(p,BorderLayout.CENTER);
		JButton b=new JButton("sd");
		b.addActionListener(new ActionListener()
		{public void actionPerformed(ActionEvent evt){ 
      		p.setFont(new Font("����",50,Font.PLAIN));
      		p.insert_text2("�Ҷ������");
         }});
		f.getContentPane().add(b,BorderLayout.SOUTH);
		p.setFont("����", 34, new Color(255,0,255),null,2);
		p.insert("�Ҷ������");
		p.setFont("����", 23, new Color(255,0,0),null,2);
		p.insert("�θ��㶥��");
		p.insert_icon("pic\\hide.jpg");
	}
}
