package QQ_test1;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.*;
public class chat_history extends JFrame implements ActionListener
{
	String to="";
	String content="";
	private String num="";
	private JLabel mark_label;
	private JComboBox mark_option;
	private JTextArea con_area;
	private RecButton del_button;
	private int file_length=0;
	private byte[] buffer;
	private String file_name=""; 
	private Vector friend_num_vector;
	msg_sender send;
	public chat_history(String n)
	{
		num=n;
		setTitle("CHAT History");
		setSize(500,500);
		setLocationRelativeTo(null);            //���н�������ʾ����Ļ����
		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.black);
		
		friend_num_vector=new Vector();
		mark_label=new JLabel("���ѱ�ע:");	//�����ǩ���ݳ���һ�У����Զ�����
		mark_label.setForeground(Color.green);		//����������ɫ
		mark_label.setFont(new Font("����",Font.BOLD,15));

		mark_option=new JComboBox();
		mark_option.setMaximumRowCount(20);		//���������˵��е������ʾ����
		
		con_area=new JTextArea();
		con_area.setBackground(Color.gray);
		con_area.setBackground(Color.white);
		con_area.setFont(new Font("����",Font.BOLD,15));
		con_area.setLineWrap(true);		//�����ı����Զ�����
		con_area.setEditable(false);
		JScrollPane scroll=new JScrollPane(con_area);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    
		mark_name_ini(mark_option);
		mark_option.addActionListener(new ActionListener(){
	      	public void actionPerformed(ActionEvent evt)
	      	{ 
	      		int index=mark_option.getSelectedIndex();
	      		System.out.println(index);
	      		if(friend_num_vector.size()>index)
	      			file_name=(String)(friend_num_vector.elementAt(index))+".txt";
	      		read_file();
	        }
	        });
		
		del_button=new RecButton("ɾ�������¼");
		del_button.setForeground(Color.green);
		del_button.setFont(new Font("����",Font.BOLD,15));
		del_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	//���������״
		del_button.addActionListener(this);
		
		mark_label.setBounds(new Rectangle(20,10,120,20));
		mark_option.setBounds(new Rectangle(140,10,100,20));
		del_button.setBounds(new Rectangle(270,10,130,20));
		scroll.setBounds(new Rectangle(5,35,480,425));
				
		c.add(mark_label);
		c.add(mark_option);
		c.add(del_button);
		c.add(scroll);
	}
	public JComboBox mark_name_ini(JComboBox j_box)
	{
		String sql="select distinct remark,friend_id from friend where firmed='true' and id='"+num+"'";
		try{
			send=new msg_sender(2,sql);
			String msg=send.r_msg();
			StringTokenizer str=new StringTokenizer(msg,"~");
			while(str.hasMoreTokens())
			{
				j_box.addItem(str.nextToken());
				friend_num_vector.add((String)(str.nextToken()));
			}
			file_name=(String)(friend_num_vector.elementAt(0))+".txt";
			read_file();
		}catch(Exception e2){e2.printStackTrace();System.out.println("��ѯ��ע����");}
		return j_box;
	}
	public void read_file()
	{
		try{
			File file=new File("chat_history\\"+file_name);
			FileInputStream f=new FileInputStream(file);
	        if(!file.exists())
	        {
	        	System.out.println("sdafasdf");
	        }
	        file_length=f.available();		//��ǰ�ļ����ֽ���
	        buffer=new byte[file_length];
			f.read(buffer);				//����ǰ�ļ������ƴ����ֽ�����
			content=new String(buffer,0,file_length);
			con_area.setText(content);
			f.close();
		}catch(Exception e){con_area.setText("���������¼");}
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("ɾ�������¼"))
		{
			System.out.println("chat_history\\"+file_name);
			File ff=new File("chat_history\\"+file_name);		//ɾ���ļ�
			ff.delete();
			File ff2=new File("chat_history\\"+file_name);		//ɾ���ļ�
			if(!ff2.exists());
			{
				JOptionPane.showMessageDialog(null,"ɾ���ɹ�","��ʾ��Ϣ",0);
			}
		}
	}
	public static void main(String args[])
	{
		chat_history c=new chat_history("3676");
		c.setVisible(true);
	}
}
