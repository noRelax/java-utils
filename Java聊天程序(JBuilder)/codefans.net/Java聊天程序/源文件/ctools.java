package chat;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.lang.Object;
import java.lang.Exception;


/**
 * <p>Title: ʹ��JAVA�����ľ������������</p>
 * <p>Description: ���ǵ�JAVA��ҵ</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author RoadAhead
 * @version 1.0
 */

public  class   ctools
{

  static void senddata(String dip,String sdata)
  {
    try
    {
      //�õ�Ŀ������ĵ�ַʵ��
      InetAddress target = InetAddress.getByName(dip);
      //������ת����byte����
      byte[] buf = sdata.getBytes();

       DatagramSocket sendSocket=new DatagramSocket();
       DatagramPacket op = new DatagramPacket(buf, buf.length, target, mainform.PORT);
      //��BUF�������е����ݴ��
       sendSocket.send(op);
      //��������
       sendSocket.close();
    }
    catch(Exception aa)
    {
      JOptionPane.showMessageDialog(null, aa.getMessage());
    }
  }//procedure end


  static void WriteToSet(String filename,String text)  //������д���ļ���
  {
    try
   {
     File file = new File (filename);
     FileWriter out = new FileWriter(file);
     out.write(text);
     out.close();
   }
   catch (IOException e) {
     JOptionPane.showMessageDialog(null, e.getMessage());
   }
    } //end peocedure


    static String readfromset(String fileName)  //�������ļ��ж�
    {
      String rv="";
      try
      {
        RandomAccessFile file = new RandomAccessFile(fileName,"r");
        long filepoint=0;
        long length=file.length();
        mainform.setmyname(file.readLine().trim());
        mainform.setmyicon(file.readLine(),1);
        rv=file.readLine();
        mainform.onstartset=rv;
        mainform.winstate=file.readLine();
        mainform.onofflinetime=file.readLine().trim();
        file.close();
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog(null, e.getMessage());
      }
      return(rv) ;//�����ÿ������ʱ�������򷵻�"1"

    }

    static String ssize(String s,int l)//���ַ���Ϊָ������
    {
      String r="";
      int i;
      if (r.length()>l)
        r=s.substring(1,l);
      else
      {
        for(i=1;i<=l-s.length();i++)
        {
          r=r+" ";
        }
        r=r+s;
      }
      return(r);
    }  //End Procedure


    static String topack(String type,String name,String icon) //������Ϊ"11" or "02"ʱ
    {
      String r="";
      r=type+ssize(name,20)+ssize(icon,5);
      return(r);
    }

    static String topack(String type,String msg) //������Ϊ"06" or "16" ʱ
    {
      String r="";
      if (msg.length()>mainform.maxStrCount)
        msg=msg.substring(1,mainform.maxStrCount);
      r=type+msg;
      return(r);
    }


    static int scanlist(String uip) //����IP�Ƿ������������,�����򷵻�
    {
      int isfind=-1;
      String t;
      int i;
      for(i=1;i<mainform.userlist.length;i++)
      {
        t=mainform.userlist[i][3];
        if (t.equals(uip))
        {
          isfind=i;
          break;
        }
      };
      return(isfind);
   }

/*���û�������,�������������е���� */
   static int addtolist(String uname,String uicon,String uip,String ustate)
   {
     int newsize=mainform.userlist.length;
     String[][] ts;
     int x,y;
     String temp;
     ts=new String [newsize+1][5];
     for(x=1;x<mainform.userlist.length;x++)//���� mainform ����
     {
       for(y=1;y<mainform.userlist[x].length;y++)
       {
         ts[x][y]=mainform.userlist[x][y];
       }
     }
     ts[newsize][1]=uname;
     ts[newsize][2]=uicon;
     ts[newsize][3]=uip;
     ts[newsize][4]=ustate;
     mainform.userlist=ts;

     //���ӵ��û�ͼ��ť����
     JButton[] ts2;
     ts2=new JButton[newsize+1];
     for(x=1;x<mainform.but_userlist.length;x++)
     {
       ts2[x]=mainform.but_userlist[x];
     };
     mainform.but_userlist=ts2;

     JButton tmpbut=new JButton();
     Icon myicon=new ImageIcon("face/"+uicon+"-"+ustate+".gif");
     tmpbut.setIcon(myicon);
     tmpbut.setText(uname);
     tmpbut.setHorizontalTextPosition(SwingConstants.CENTER );
     tmpbut.setVerticalTextPosition(SwingConstants.BOTTOM);
     temp=ctools.ssize(uip,15)+uicon;
     tmpbut.setName(temp);

     tmpbut.setBackground(mainform.ed_show.getBackground());
     tmpbut.addActionListener(new ActionListener(){
       public void mouseExited(MouseEvent e)
       {
         //setBorder(BorderFactory.createRaisedBevelBorder());
       }
       public void actionPerformed(ActionEvent ae)
       {
         String tmpstr;
         tmpstr=ae.toString();
         int temp;

         temp=tmpstr.lastIndexOf("cmd=")+4;
         mainform.setusername(tmpstr.substring(temp,tmpstr.lastIndexOf(",when=")).trim());

         temp=tmpstr.lastIndexOf("on");
         mainform.setuserip(tmpstr.substring(temp+3,temp+18).trim());

         tmpstr=tmpstr.substring(temp+18,tmpstr.length());
         mainform.setusericon(tmpstr.trim(),"1");
         mainform.ed_input.grabFocus();

       }
});
     mainform.but_userlist[newsize]=tmpbut;
     mainform.but_userlist[newsize].setVisible(true);
     mainform.pan_userlist.add(mainform.but_userlist[newsize]);

//     prarray(mainform.userlist); //��ӡ����,ֻ���ڲ���
     return(newsize);
   }


   static void prarray(String[][] sz)
   {
     int x=1,y=1;
     for(x=1;x<sz.length;x++)
     {
       for(y=1;y<sz[x].length;y++)
       {
         System.out.print(sz[x][y]);
       }
       System.out.print("\n");
     }

   }

static String bytetostr(byte[] yb,int len)
{
  String rs="";
  int i=0;
  char c;
  for(i=0;i<len;i++)
  {
    c=(char)yb[i];
    rs=rs+String.valueOf(c);
  }

  return(rs);

}


static void setuserstate(int bh,String state) //�����û�״̬
{
  String icon;
  mainform.userlist[bh][4]=state;
  icon=mainform.userlist[bh][2].trim();
  icon="face/"+icon+"-"+state.trim()+".gif";
  Icon myicon=new ImageIcon(icon);
  mainform.but_userlist[bh].setIcon(myicon);
}


}