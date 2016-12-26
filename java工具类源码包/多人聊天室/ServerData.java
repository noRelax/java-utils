import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;


/**
 *接收客户端
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
		
	    //得到内容面板
	    Container con=jf1.getContentPane();
	    ta=new JTextArea();
	    
	    JScrollPane js=new JScrollPane(ta);
        ta.setEditable(false);
	
	    
	    
	    con.add(js,BorderLayout.CENTER);
	    
	    jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jf1.setLocation(300,300);
	    jf1.setSize(500,500);
	    jf1.setVisible(true);
	    
	    //启动serverSocket与服务器端口号绑定
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
			 	System.out.println("连接服务器连接服务失败！");	
			 }
			
			
			 
		}
		catch(IOException e){
			 ta.append("连接失败！！！！");
			 e.printStackTrace();
		}		
}
	
	
//-----------------------------------------------------------------------------
	
	public static void main(String args[]){		
		ServerData serverData=new ServerData("服务区窗口");
		serverData.init();
	}
	
	

} 


//-----------------------------------------------------------------------------
/**从流中读取数据的线程********************/
//-----------------------------------------------------------------------------
	
	
class ServerReadWriteData extends Thread{
	
	private int index;//保存当前线程的编号
	private String data;
	private JTextArea ta;;
	private DataInputStream InputS;
	private DataOutputStream outputS[];
	static int bad[] = new int[100];//保存坏掉连接序号的数组
	static int badIndex = 0;//坏掉连接的下标
	
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
    			        //读入的任何一句话都必须写出到所有客户端
    			        
    				    data = InputS.readUTF();
                        ta.append(data);
	    			    ta.append("\n");
	    			    
	    			    //文本区滚动到最下方	
	    			    
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
            	//如果出现异常,则添加到错误连接的数组中
            	
            	bad[badIndex++] = index;
            	System.out.println("连接中断！");
            }            	                	    
          		
    }    	  
	
}	
	
	
	
