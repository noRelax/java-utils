
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LoginData {
	
	
	private JTextField t1;
	private JTextField t2;
	private JFrame jf1;
	
	
	
	public LoginData(String title){
		jf1=new JFrame(title);
	}
	
	
	public void init(){
	    //�õ��������
	    Container con=jf1.getContentPane();
	    
	    //�ӱ�ǩ���ı���
	    
	    JLabel jl1=new JLabel("�û���:");
	    t1=new JTextField(20);
	    
	    JLabel jl2=new JLabel("IP��ַ:");
	    t2=new JTextField("localhost",20);
	    
	    //����壬�貼��Ϊ����ʽ
	    
	    JPanel jp1=new JPanel();
	    jp1.setLayout(new GridLayout(2,2));
	    
	    //������ϼӱ�ǩ���ı���
	    
	    jp1.add(jl1);
	    jp1.add(t1);
	    jp1.add(jl2);
	    jp1.add(t2);
	    
	     //����壬�貼��Ϊ��ʽ
	     
	    JPanel jp2=new JPanel();
	    
	    jp2.setLayout(new FlowLayout());
	    
	    //�Ӱ�ť������
	    
	    JButton b1=new JButton("��½");
	    jp2.add(b1);
	    
	    b1.addActionListener(new ActionLoginListenerData(t1,t2,jf1));
	    
	    
	    //���������Ϊ�ϱ���
	    con.add(jp1,BorderLayout.NORTH);
	    con.add(jp2,BorderLayout.SOUTH);
	    
	    jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf1.setLocation(300,300);
	    jf1.setSize(200,100);
	    jf1.setVisible(true);
		
	}
	
	public static void main(String args[]){
		
		LoginData login=new LoginData("��½");
		login.init();	
	}
}



class ActionLoginListenerData implements ActionListener{
	
	private JTextField t1;
	private JTextField t2;
	private JFrame jf1;
	
	public ActionLoginListenerData(JTextField t1,JTextField t2,JFrame jf1){
		this.t1=t1;
		this.t2=t2;
		this.jf1=jf1;
	}
	
	public void actionPerformed(ActionEvent e){
			
			ClientData clientData=new ClientData(t1.getText(),t2.getText());
			clientData.init();
			jf1.setVisible(false);
		
	}
}