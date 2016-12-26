package ChatClient;

import java.net.*;
import java.io.*;
public class ChatClient{
 private DatagramSocket s;
 private InetAddress hostAddress;
 private byte[] buf = new byte[1000];
 private DatagramPacket dp = new DatagramPacket(buf,buf.length);
//����˵����������������ʵ�ֽ����û�������������ͨѶ
  public ChatClient(){
   try{
       //ʹ�ù�����������ʹ�ñ����κο��ö˿ڵ����ݰ�Socket
       s = new DatagramSocket();
       //��ȡ����IP
       hostAddress = InetAddress.getByName("localhost");
       while(true){
         String outMessage ="";  
        //��ȡ����
         BufferedReader stdin  = new BufferedReader(new InputStreamReader(System.in));
         try{
           outMessage = stdin.readLine();
         }catch(IOException ie){  System.err.println("IO error!");    }
         //������롰bye�����ʾ�˳�����
         if(outMessage.equals("bye")) break;
         String outString = "Client say: "+ outMessage;
         byte[] buf = outString.getBytes();
         //������ݣ���������
         DatagramPacket out = new DatagramPacket(buf,buf.length,hostAddress,4000);
         s.send(out);
         //�ȴ�����������
         s.receive(dp);
         String rcvd = "rcvd from "+ dp.getAddress() + ", " + dp.getPort() + 
         ": "+ new String(dp.getData(),0,dp.getLength());
         System.out.println(rcvd);
        }
     }catch(UnknownHostException e){
       System.out.println("Can;t open socket");
       System.exit(1);
     }catch(SocketException e){
       System.out.println("Can;t open socket");
       e.printStackTrace();
       System.exit(1);
     }catch(IOException e){
       System.err.println("Communication error");
       e.printStackTrace();
       System.exit(1);
     }catch(Exception e){
       System.err.println("Communication error");
       e.printStackTrace();
       System.exit(1);
     }
     System.out.println("ChatClient over");
 }
public static void main(String[] args){    new ChatClient(); }
}