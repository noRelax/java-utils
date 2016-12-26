import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;


/**
 *���տͻ���
 */

public class ServerData {
	
	private DataInputStream InputS[]=new DataInputStream[100];
	private DataOutputStream  outputS[]=new DataOutputStream[100];
	
	private ServerSocket serverSocket;
	private Socket socket[]=new Socket[100];
	
	public static int num=0;
	
	private JFrame jf1;
	private JTextArea ta;
	
//-----------------------------------------------------------------------------	
	public ServerData(String title){
		
		jf1=new JFrame(title);
	}
	
//-----------------------------------------------------------------------------
	
	public void init(){
		
	    //�õ��������
	    Container con=jf1.getContentPane();
	    ta=new JTextArea();
	    
	    JScrollPane js=new JScrollPane(ta);
        ta.setEditable(false);
	
	    
	    
	    con.add(js,BorderLayout.CENTER);
	    
	    jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf1.setLocation(300,300);
	    jf1.setSize(500,500);
	    jf1.setVisible(true);
	    
	    //����serverSocket��������˿ںŰ�
	    connect();
		
	}
	
	
//-----------------------------------------------------------------------------

	public void connect(){
		
		try{
			
			 serverSocket=new ServerSocket(6000);
			 try{
			 	while(true){
			 	
				 	 socket[num] = serverSocket.accept();
				     InputS[num] =new DataInputStream(socket[num].getInputStream());
	                 outputS[num]= new DataOutputStream(socket[num].getOutputStream());
	                 
	                 ServerReadWriteData serverReadWriteData=new ServerReadWriteData(num,ta,InputS[num],outputS);
	                 serverReadWriteData.start();
	                 System.out.println("num  "+num);
	                 num++;
                 }
                 
			 }
			 catch(IOException e){
			 	System.out.println("���ӷ��������ӷ���ʧ�ܣ�");	
			 }
			
			
			 
		}
		catch(IOException e){
			 ta.append("����ʧ�ܣ�������");
			 e.printStackTrace();
		}		
}
	
	
//-----------------------------------------------------------------------------
	
	public static void main(String args[]){		
		ServerData serverData=new ServerData("����������");
		serverData.init();
	}
	
	

} 


//-----------------------------------------------------------------------------
/**�����ж�ȡ���ݵ��߳�********************/
//-----------------------------------------------------------------------------
	
	
class ServerReadWriteData extends Thread{
	
	private int index;//���浱ǰ�̵߳ı��
	private String data;
	private JTextArea ta;;
	private DataInputStream InputS;
	private DataOutputStream outputS[];
	static int bad[] = new int[100];//���滵��������ŵ�����
	static int badIndex = 0;//�������ӵ��±�
	
	public ServerReadWriteData(int index,JTextArea ta,DataInputStream InputS,DataOutputStream outputS[]){
		
		this.index=index;
		this.ta=ta;
		this.InputS=InputS;
		this.outputS=outputS;
	}
  
   public void run()
    {
    		try
    		{
    			while(true)
    			{       
    			        //������κ�һ�仰������д�������пͻ���
    			        
    				    data = InputS.readUTF();
                        ta.append(data);
	    			    ta.append("\n");
	    			    
	    			    //�ı������������·�	
	    			    
	    			    ta.setCaretPosition(ta.getText().length());
	    			    
	    			    outer:
	    			    for(int i=0;i<ServerData.num;i++){
	    			    
	    			    	for(int k=0;k<badIndex;k++){
	    			    		
	    			    		if(bad[k]==i){
	    			    			continue outer;
	    			    		}
	    			 
	    			    	}
	    			       	
	    			       outputS[i].writeUTF(data);	
	    			    	
	    			    }
	    		}  
    	    }
    	    catch(IOException e)
            {
            	//��������쳣,����ӵ��������ӵ�������
            	
            	bad[badIndex++] = index;
            	System.out.println("�����жϣ�");
            }            	                	    
          		
    }    	  
	
}	
	
	
	
