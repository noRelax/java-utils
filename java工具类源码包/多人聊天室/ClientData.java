
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.Socket;
import java.io.*;


public class ClientData{
	
	private DataInputStream InputS;
	private DataOutputStream  outputS;
	
	private JTextField t1;
	private JFrame jf1;
	private Socket socket;
	private String username;
	private String ip;
    public JTextArea ta;
	
//------------------------------------------------------------------------------		
	public ClientData(String username,String ip){
		
		this.username=username;
		this.ip=ip;
		jf1=new JFrame("登陆用户是 :"+username);
		
	}
	
//-----------------------------------------------------------------------------		
	public void init(){
		
	    //得到内容面板
	    
	    Container con=jf1.getContentPane();
	    ta=new JTextArea();
	    
	    
	    JScrollPane js=new JScrollPane(ta);
	    t1=new JTextField(20);
	    ta.setEditable(false);
	    
	    
	    JPanel jp2=new JPanel();
	    jp2.setLayout(new FlowLayout());
	    JButton b1=new JButton("发送");
	    
	    jp2.add(t1);
	    jp2.add(b1);
	    
	    
	    b1.addActionListener(new ActionClientListenerData());
	    
	    con.add(js,BorderLayout.CENTER);
	    con.add(jp2,BorderLayout.SOUTH);
	    
	    jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf1.setLocation(300,300);
	    jf1.setSize(400,300);
	    jf1.setVisible(true);
	    connect();
	    createReadThread();
		
	}
	
	//-----------------------------------------------------------------------------	
    class ActionClientListenerData implements ActionListener{
    	
		public void actionPerformed(ActionEvent e){
			
			try{
				outputS.writeUTF(username +"说:"+t1.getText());
				t1.setText("");	
			    t1.requestFocus();
				
			}
			catch(Exception e1){
		    }
			
		
		
	   }
    }
//-----------------------------------------------------------------------------		
	public void connect(){
		try{
			 socket =new Socket(ip,6000);
			 
			 InputS = new DataInputStream(socket.getInputStream());
			 outputS = new DataOutputStream(socket.getOutputStream());
			 ta.append("连接成功！！！！");
			 ta.append("\n");
			 
		}
		catch(IOException e){
			 ta.append("连接失败！！！！");
			 ta.append("\n");
			 e.printStackTrace();
			 
		}
		
		

	}
//-----------------------------------------------------------------------------	
	public void createReadThread(){
		ClientReadThreadData rt = new ClientReadThreadData(ta,InputS);
		rt.start();
	}
	

	
	
//-----------------------------------------------------------------------------	
	
//	public static void main(String args[]){
//		
//		Client client=new Client("wei","localhost");
//		client.init();	
		
		
//	}
//-----------------------------------------------------------------------------	

}
//-----------------------------------------------------------------------------
/**从流中读取数据的线程************************************************************/
//-----------------------------------------------------------------------------
class ClientReadThreadData extends Thread
{  
    private  String data;
    private  StringBuffer buf=new StringBuffer();
	private  JTextArea ta;;
	private  DataInputStream InputS;
	
	public ClientReadThreadData(JTextArea t,DataInputStream d)//传入两个对象类型的参数
	{
		this.ta = t;
		this.InputS = d;
    }
    
    public void run()
    {
    	    
    		try
    		{
    		
    	       while(true)
    			{
    				    data = InputS.readUTF();
                        ta.append(data);
	    			    ta.append("\n");
	    			
	    			    ///////////////////////////////////////////////////////
	    			    ///////////////////////////////////////////////////////
	    			    //文本区滚动到最下方	
	    			    ta.setCaretPosition(ta.getText().length());				

	    		

	    		}  
    	    }
    	    catch(IOException e)
            {
            	System.out.println("连接中断！");
            }            	                	    
          		
    }    	  
}
	
	
	
	
	
	
