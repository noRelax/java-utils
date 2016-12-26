package QQ_test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class msg_sender
{
	public static final int PORT1 = 8080;
	private String msg_send,msg_received;
	public msg_sender(int col_num,String sql) throws IOException
	  {
		msg_send=sql;
	    InetAddress addr = InetAddress.getByName(login.ip);
	    Socket socket = new Socket(addr,PORT1);
	    try 
	    {
	      BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
	      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	      if(sql.charAt(0)=='s'||sql.charAt(0)=='S')         //查询
	      {
	    	  out.println(col_num);
	    	  out.println(msg_send);
	    	  msg_received=in.readLine();
	    	  //System.out.println(msg_received);
	      }
	      else if(sql.charAt(0)=='u'||sql.charAt(0)=='U'||    //更新，插入
	    		  sql.charAt(0)=='i'||sql.charAt(0)=='I'||
	    		  sql.charAt(0)=='d'||sql.charAt(0)=='D')
	      {
	    	  out.println(0);
	    	  out.println(msg_send);
	    	  msg_received=in.readLine();
	      }
	      else if(sql.charAt(0)=='l')
	      {
	    	  out.println(0);
	    	  out.println(msg_send);
	      }
	    }catch(Exception q){}
	  finally{socket.close();}
	  }
	public String r_msg()
	{
		return msg_received;
	}
	public boolean updated()
	{
		if(msg_received.equalsIgnoreCase("s"))
			return true;
		else
			return false;
	}
}