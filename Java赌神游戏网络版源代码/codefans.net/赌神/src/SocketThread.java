// *******************************************************
//
// FILENAME:    	betmain.java
// PROJECT:		BetSprite
// DESCRIPTION:		赌神网络版
// Download by http://www.codefans.net
//
// *******************************************************

import java.io.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class SocketThread extends Thread {

  //有些函数必需要写在外边，靠，什么烂东西，封装很差
  InputStream is = null;
  OutputStream os = null;
  StreamConnection socket = null;
  String GMessage;

  boolean B_Socketthread = true; // socket 线程开关 true 为启动
  boolean Intranet = true;// 局域网和广域网测试开关 TRUE 为局域网
  boolean Debug = false;//debug 开关 true is debug state
  //////////////////////////////////////////////////////////////////////
  //
  //      //这里实例化
  //
  //////////////////////////////////////////////////////////////////////

  public SocketThread()
  {
       super(); 
       if (Intranet)
         Debug = true;
  }
  public void run()
  {

    if(Debug == true)
      System.out.print("Inside SocketThread Constructor...\n");
  }

  /////////////////////////////////////////////////////////////
  // accept
  //
  //
  /////////////////////////////////////////////////////////////
  public void accept()
  {

    String name = "NULL";
    try{

      if(Intranet == true)
      {
        // 本地测试IP
        name =  "socket://192.168.0.58:54757";
      }
      else
      {
        name =  "socket://202.96.138.83:54757";
      }

      socket = (StreamConnection)Connector.open(name, Connector.READ_WRITE);

      if(Debug == true)
        System.out.print("getmessage() working:accept\n");

      GMessage=getmessage();
    }
    catch(Exception ex)
    {
      if(Debug == true)
        System.out.print("getmessage() failing:accept\n");
    }
    if(Debug == true)
      System.out.print("accept() is open");
  }

  /////////////////////////////////////////////////////////////
  // 获得消息
  //
  //
  /////////////////////////////////////////////////////////////

  public String getmessage()
  {
    String s="NULL";
    int i=0;
    if(Debug == true)
      System.out.print("getmessage() start\n");

    try {

      //socket =(StreamConnection)Connector.open(,Connector.READ, true);
      is = socket.openInputStream();
      if(Debug == true)
        System.out.print("socket.open sending 1\n");
    }
    catch (Exception e) {}

    try {
      int b;
      StringBuffer sb = new StringBuffer();
      if(Debug == true)
        System.out.print("socket.open 1\n");
      while ( (b = is.read()) != 97) // 42 是“*”97 "a"
      {
        sb.append((char)b);
        i++;
        if(Debug == true)
          System.out.print("recive Message.\n");
      }
      //socket.close();
      if(Debug == true)
        System.out.print("socket.close() working,get \n");
      if(i!=0)
        s = sb.toString();
      if(Debug == true)
        System.out.print("sb.toString() working"+s+"\n");

      //dt.setText(sb.toString());
      //display.setCurrent(outputForm);
    }
    catch (Exception e)
    {
      if(Debug == true)
        System.out.print("sb.toString() failed\n");
    }
    return s;
  }


  /////////////////////////////////////////////////////////////
  // 发送消息
  //
  //
  /////////////////////////////////////////////////////////////
  public void sendmessage(String request)
  {
    try
    {
      //send the message to the server
      os = socket.openOutputStream();
      os.write(request.getBytes());
      //os.close();
      socket.close();
    }
    catch(IOException ex)
    {
      return;
    }

    finally
    {
      try
      {
        if(socket != null)
        {
          socket.close();
          socket = null;
        }
      }
      catch (IOException ex1)
          {}
    }
  }
}