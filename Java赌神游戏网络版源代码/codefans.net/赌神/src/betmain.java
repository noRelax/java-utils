// *******************************************************
//
// FILENAME:    	betmain.java
// PROJECT:		BetSprite
// DESCRIPTION:		���������
// Download by http://www.codefans.net
//
// *******************************************************
import java.io.*;
import java.util.Random;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
/*****************************************************/
//// ����
//
//
/*****************************************************/
public class betmain extends Canvas implements Runnable
{
  //��ʼ������
  Image imagejiemian = null;
  Image imagexuanzeanniu= null;
  Image imagedating =null;
  Image imageno1 =null;
  Image imageno2 =null;
  Image imageno3 =null;
  Image imageno4 =null;
  Image imageno5 =null;
  Image imageno6 =null;
  Image imageren =null;
  Image imagewin=null;
  Image imagelost=null;

  Image imagediban= null;
  Image imagehand=null;

  Image imagexiazhuqi =null;

  Image imagebei =null;
  Image imagehei10 =null;
  Image imageheiJ =null;
  Image imageheiQ =null;
  Image imageheiK =null;
  Image imageheiA =null;
  Image imagehong10 =null;
  Image imagehongJ =null;
  Image imagehongQ =null;
  Image imagehongK =null;
  Image imagehongA =null;
  Image imagepian10 =null;
  Image imagepianJ =null;
  Image imagepianQ =null;
  Image imagepianK =null;
  Image imagepianA =null;
  Image imagehua10 =null;
  Image imagehuaJ =null;
  Image imagehuaQ =null;
  Image imagehuaK =null;
  Image imagehuaA =null;
  Image imageall=null;

  MIDlet omidlet;

  private int width,height,isFirst=1,itemPress=112;	// ȡ��Ļ���м��
//  int handX=85,handY=20;
  int handX=30,handY=115;
  int cardself[]=new int[5*6];//id(1-5),cardid(0-19),x,y
  int cardenemy[]=new int[5*6];//id(1-5),cardid(0-19),x,y
  int indexcard=0;
  int show=0;
  int cash10=0,cash50=0,cash100=0,showall=0;
  int totalcash0=10000,totalcash1=10000;
  int tempcash0,tempcash1;//ÿ��Ѻ��Ķ��٣�tempcash0�Լ� tempcash1�Է�
  int checkself=0,checkenemy=0;
  int money=0;  //һ�����ܵ�Ѻ��

  String person="no";

  SocketThread socketthread = new SocketThread ();

  Thread thread;

  /*****************************************************/
  // ���๹�캯��
  //
  //
  /*****************************************************/
  public betmain(MIDlet midlet)  // �ҵĻ���
  {
    if(socketthread.B_Socketthread == true)
          socketthread.start();


    width=getWidth();
    height=getHeight();
    thread=new Thread(this);
    omidlet=midlet;

    try{
      imagejiemian 	= Image.createImage("/jiemian.png");
      imagexuanzeanniu	= Image.createImage("/xuanzeanniu.png");
      imagediban 	= Image.createImage("/diban.png");
      imagehand		= Image.createImage("/hand.png");

      imagedating 	= Image.createImage("/datingdiban.png");
      imageno1	        = Image.createImage("/no1.png");
      imageno2	        = Image.createImage("/no2.png");
      imageno3	        = Image.createImage("/no3.png");
      imageno4        	= Image.createImage("/no4.png");
      imageno5      	= Image.createImage("/no5.png");
      imageno6  	= Image.createImage("/no6.png");
      imageren  	= Image.createImage("/ren.png");

      imagewin  	= Image.createImage("/win.png");
      imagelost  	= Image.createImage("/lost.png");
      imagexiazhuqi    	= Image.createImage("/xiazhuqi.png");

      imagebei  	= Image.createImage("/bei.png");
      imagehei10	= Image.createImage("/hei10.png");
      imageheiJ 	= Image.createImage("/heiJ.png");
      imageheiQ 	= Image.createImage("/heiQ.png");
      imageheiK 	= Image.createImage("/heiK.png");
      imageheiA 	= Image.createImage("/heiA.png");
      imagehong10	= Image.createImage("/hong10.png");
      imagehongJ	= Image.createImage("/hongJ.png");
      imagehongQ	= Image.createImage("/hongQ.png");
      imagehongK	= Image.createImage("/hongK.png");
      imagehongA	= Image.createImage("/hongA.png");
      imagepian10	= Image.createImage("/pian10.png");
      imagepianJ	= Image.createImage("/pianJ.png");
      imagepianQ	= Image.createImage("/pianQ.png");
      imagepianK	= Image.createImage("/pianK.png");
      imagepianA	= Image.createImage("/pianA.png");
      imagehua10	= Image.createImage("/hua10.png");
      imagehuaJ 	= Image.createImage("/huaJ.png");
      imagehuaQ 	= Image.createImage("/huaQ.png");
      imagehuaK 	= Image.createImage("/huaK.png");
      imagehuaA 	= Image.createImage("/huaA.png");

      imageall		= Image.createImage(width,height);
    }
    catch(Exception e)
    {    }
  }
  /*****************************************************/
  // ����
  //
  //
  /*****************************************************/
  protected void paint(Graphics g)
  {
    g.drawImage(imageall,width/2,height/2,g.HCENTER|g.VCENTER);
  }

  int randRange(int n)
  {
    Random rand;
    rand = new Random();
    int r = rand.nextInt() % n;
    if (r < 0)
      r += n;
    return r;
  }
  /*****************************************************/
  //  ������Ϣ
  //
  //
  /*****************************************************/
  public void keyPressed( int code )
  {
    switch(getGameAction(code))
    {
      case RIGHT:
      {
        if(isFirst==1)
        {
        }
        if(isFirst==2)
        {
          handX=85;
        }
        if(isFirst==3)
        {
          handY=115;
          if(handX<80)
            handX+=25;
        }
        break;
      }
      case LEFT:
      {
        if(isFirst==1)
        {
        }
        if(isFirst==2)
        {
          handX=28;
        }
        if(isFirst==3)
        {
          handY=115;
          if(handX>30)
            handX-=25;
        }
        break;
      }
      case UP:
      {
        //�������
        if(isFirst==1)
        {
          if(itemPress==94)
            itemPress=112;
          else
            itemPress=94;
        }
        if(isFirst==2)
        {
          if(handY>20)
            handY-=35;
        }
        if(isFirst==3)
        {
          handX=30;
          if(handY>47)
            handY-=17;
        }
        break;
      }
      case DOWN:
      {
        //�������
        if(isFirst==1)
        {
          if(itemPress==94)
            itemPress=112;
          else
            itemPress=94;
        }
        if(isFirst==2)
        {
          if(handY<125)
            handY+=35;
        }
        if(isFirst==3)
        {
          handX=30;
          if(handY<115)
            handY+=17;
        }
        break;
      }
      case FIRE:	// enter key
      {
        //�������
        if(isFirst==1)
        {
          if(itemPress==94)
          {
            isFirst++;
            // ��ʱ���δ�����ͨѶ����
            socketthread.sendmessage("1");
          }
        }
        if(isFirst==2)
        {
          if(handX==28&&handY==125)
          {
            handX=28;
            handY=20;

            if(socketthread.B_Socketthread == true)
            {
              if(person=="yes")
                isFirst++;
            }
            handX=30;
            handY=47;
            isFirst=3;
          }
          if(handX==85&&handY==125)
          {
            handX=28;
            handY=20;
            isFirst--;
          }
        }
        //��������
        if(isFirst==3)
        {
          //cash10
          if(handX==30&&handY==47&&show==1&&totalcash0>=tempcash0)
            cash10++;
          if(handX==30&&handY==64&&show==1&&totalcash0>=tempcash0)
            cash50++;
          if(handX==30&&handY==81&&show==1&&totalcash0>=tempcash0)
            cash100++;
          if(handX==30&&handY==98&&show==1&&totalcash0>=tempcash0)
          {
            if(showall==1)
              showall=0;
            else
              showall=1;
          }
          if(handX==30&&handY==115)
          {
//            handX=30;
//            handY=47;

            if(show==1)
            {
              show=0;

              totalcash1=totalcash1-tempcash1;
              tempcash1=0;

              if(showall==1)
              {
                tempcash0=totalcash0;
                totalcash0=0;
              }
              else
                totalcash0=totalcash0-tempcash0;
              if(tempcash0!=0)
              {
                money=money+tempcash0;
                socketthread.sendmessage(Integer.toString(tempcash0)+"3");
                checkself++;
              }
            }
            else
            {
              show=1;
              cash10=0;
              cash50=0;
              cash100=0;
              showall=0;
            }
          }
          if(handX==55&&handY==115&&show==1)
          {
            cash10=0;
            cash50=0;
            cash100=0;
            showall=0;
          }
          if(handX==80&&handY==115&&show==1)
          {
            cash10=0;
            cash50=0;
            cash100=0;
            showall=0;
            handX=30;
            handY=115;
            show=0;
            //��������ʼ��һ��
            indexcard=0;
            for(int i=0;i<30;i++)
            {
               cardself[i]=0;
               cardenemy[i]=0;
            }
            tempcash0=0;
            socketthread.sendmessage(Integer.toString(tempcash0)+"3");
            checkself=0;
            checkenemy=0;
            money=0;
          }
          tempcash0=cash10*10+cash50*50+cash100*100;
        }
        break;
      }
      case GAME_A:
      {
        ((bet)omidlet).exit();
      }
    }
  }
  ////////////////////////////////////////////////////////////
  // ������
  //
  ////////////////////////////////////////////////////////////
  public void run()
  {
    communication();

    Graphics g=imageall.getGraphics();
    g.setGrayScale(255);
    g.fillRect(0,0,width,height);
    g.setGrayScale(0);

    if(isFirst==1)
    {
      g.drawImage(imagejiemian,0,0,g.TOP|g.LEFT);
      g.drawImage(imagexuanzeanniu,14,itemPress,g.TOP|g.LEFT);
    }
    if(isFirst==2)
    {
      g.drawImage(imagedating,0,0,g.TOP|g.LEFT);
      if(handX==28&&handY==20)
      {
        g.drawImage(imageno1,17,5,g.TOP|g.LEFT);
        g.drawImage(imageren,4,15,g.TOP|g.LEFT);
      }
      if(handX==85&&handY==20)
      {
        g.drawImage(imageno2,74,5,g.TOP|g.LEFT);
      }
      if(handX==28&&handY==55)
      {
        g.drawImage(imageno3,17,41,g.TOP|g.LEFT);
      }
      if(handX==85&&handY==55)
      {
        g.drawImage(imageno4,74,41,g.TOP|g.LEFT);
      }
      if(handX==28&&handY==90)
      {
        g.drawImage(imageno5,17,77,g.TOP|g.LEFT);
      }
      if(handX==85&&handY==90)
      {
        g.drawImage(imageno6,74,77,g.TOP|g.LEFT);
      }
      g.drawImage(imageren,4,15,g.TOP|g.LEFT);
      g.drawImage(imagehand,handX,handY,g.TOP|g.LEFT);

      if(person=="yes")
        isFirst++;
    }
    if(isFirst==3)
    {
      if(indexcard<2)
      {
        sendcard(indexcard++);
      }
      else
      {
        if(indexcard==5)
        {
         if(compare(5)==1)
          {
            indexcard=0;
            for(int i=0;i<30;i++)
            {
              cardself[i]=0;
              cardenemy[i]=0;
            }
            totalcash0=totalcash0+money;

            money=0;
            checkself=0;
            checkenemy=0;
          }
          else
          {
            indexcard=0;
            for(int i=0;i<30;i++)
            {
              cardself[i]=0;
              cardenemy[i]=0;
            }
            money=0;
            checkself=0;
            checkenemy=0;
          }
        }
        if(checkself==checkenemy && indexcard-1==checkself && indexcard<5)
        {
          sendcard(indexcard++);
        }
      }

      if(cardenemy[indexcard-1]==cardself[indexcard-1]&&cardself[indexcard-1]!=0&&cardenemy[indexcard]==0
         &&cardself[indexcard]==0)
         {
           if(compare(indexcard)==1)
             show=1;
           else
             show=0;
         }
      showcard(g);
    }

    repaint();//ϵͳ��������ͣˢ����ʾ
    try{
      thread.sleep(100);
    }catch (InterruptedException ie){ }
    Display.getDisplay(omidlet).callSerially(this);
  }
  ////////////////////////////////////////////////////////////////////////////
  //���������������������壨��������������
  //��������������������������Ҫ�Ƚ��Ƶ�����������Ϊ��ࡣ��
  //���������������Ƚϵ���˫����ǰ�����ƵĴ�С�����أ�Ϊ�Լ���
  //����������������Ϊ�Է���
  //���㷨������������cardself[];cardenemy[]Ϊ������ݡ�
  //�������������������Ƚϵ����ݣ�
  //������������������ͬ��˳����֧����ͬ������«������+���ӣ���˳�ӣ����������ԣ�����
  //������������������˼�룺�����ṹ�����飬��������һ������ֵ��һ���ǻ�ɫ��
  //                        ������Ϊ1��2��3��4��  4�����ɫ����ʾ��������
  //                        ��СΪ5��Ȼ���ȿ���������Ԫ�ص�ֵ�Ƿ��������ģ�
  //                        Ȼ���ڿ���ɫ��Ȼ�������м�������ͬ�ģ���������ʲ
  //������������������������ô���͵��Ƶ��ظ����ڿ��Ƿ�������Ԫ�صĻ�ɫָ����
  //���������������������������Ϳ��Ը��ݲ�ͬ����õ���ͬ��ָ������
  //���������������������������ø���ԽСָ��Խ���ԭ��
  //���������������������������ø����е����ظ������ѡ�ļ��㹫ʽ��
  //              �����ֱ�Բ�ͬ�����ıȽϽ������֣�˫����������ƴ�С�ĸ���
  //������������������ָ����Ȼ����бȽϴ�С��
  //������������������ָ����Χ��
  //������������������ͬ��˳����֧����ͬ������«������+���ӣ���˳�ӣ����������ԣ�����
  //������������������71~74--61~65----------51~55-------------41~44--31~35--100~500+1~4--10~50+1~4
  //����ע��    cardself[i*6+4]��ֵ��cardself[i*6+5] ��ɫ
  ////////////////////////////////////////////////////////////////////////////
  public int compare(int number)
  {
    int coreself=0,coreenemy=0;//����Ϊ�Լ��󣬣���Ϊ�Լ�С
    int k=1;
    int a[]=new int[2];
    int j;
    int i;
    if(number==1)
    {
      if(cardself[1*6+4]==cardenemy[1*6+4] && cardself[1*6+5]>cardenemy[1*6+5])
        return 0;
      if(cardself[1*6+4]==cardenemy[1*6+4] && cardself[1*6+5]<cardenemy[1*6+5])
        return 1;
      if(cardself[1*6+4]>cardenemy[1*6+4])
        return 1;
      else
        return 0;
    }
    if(number==2)
    {
      if(cardself[1*6+4]==cardself[2*6+4])
      {
        if(cardself[1*6+5] > cardself[2*6+5])
          coreself=(cardself[1*6+4]-10+1)*100+cardself[1*6+5];
        if(cardself[1*6+5] < cardself[2*6+5])
          coreself=(cardself[1*6+4]-10+1)*100+cardself[2*6+5];
      }
      if(cardself[1*6+4]>cardself[2*6+4])
      {
        coreself=(cardself[1*6+4]-10+1)*10+cardself[1*6+5];
      }
      if(cardself[1*6+4]<cardself[2*6+4])
      {
        coreself=(cardself[1*6+4]-10+1)*10+cardself[2*6+5];
      }

      if(cardenemy[1*6+4]==cardenemy[2*6+4])
      {
        if(cardenemy[1*6+5] > cardenemy[2*6+5])
          coreenemy=(cardenemy[1*6+4]-10+1)*100+cardenemy[1*6+5];
        if(cardenemy[1*6+5] < cardenemy[2*6+5])
          coreenemy=(cardenemy[1*6+4]-10+1)*100+cardenemy[2*6+5];
      }
      if(cardenemy[1*6+4]>cardenemy[2*6+4])
      {
        coreenemy=(cardenemy[1*6+4]-10+1)*10+cardenemy[1*6+5];
      }
      if(cardenemy[1*6+4]<cardenemy[2*6+4])
      {
        coreenemy=(cardenemy[1*6+4]-10+1)*10+cardenemy[2*6+5];
      }
      if(coreself>coreenemy)
        return 1;
      if(coreself<coreenemy)
        return 0;
    }
    if(number==3)
    {
      a[0]=0;
      a[1]=0;
      k=1;
      for(i=2;i<4;i++)
      {
        for(j=1;j<i;j++)
          if(cardself[i*6+4]==cardself[j*6+4])
          {
            if(a[0]!=0)
            {
              if(cardself[i*6+5]>cardself[j*6+5])   //ѡ��ɫ�����
                a[1]=i;
              else
                a[1]=j;
            }
            else
            {
              if(cardself[i*6+5]>cardself[j*6+5])   //ѡ��ɫ�����
                a[0]=i;
              else
                a[0]=j;
            }
            j=0;
            break;
          }
        if(j!=0)
          k++;
      }
      if(k==1)
      {
        coreself=(cardself[a[0]*6+4]-10+1)*1000;
      }
      if(k==2)
      {
        coreself=(cardself[a[0]*6+4]-10+1)*100+cardself[a[0]*6+5];
      }
      if(k==3)
      {
        if(cardself[1*6+4]>cardself[2*6+4])
        {
          if(cardself[1*6+4]>cardself[3*6+4])
            coreself=(cardself[1*6+4]-10+1)*10+cardself[1*6+5];
          else
            coreself=(cardself[3*6+4]-10+1)*10+cardself[3*6+5];
        }
        else
        {
          if(cardself[2*6+4]>cardself[3*6+4])
            coreself=(cardself[2*6+4]-10+1)*10+cardself[2*6+5];
          else
            coreself=(cardself[3*6+4]-10+1)*10+cardself[3*6+5];
        }
      }
      k=1;
      a[0]=0;
      a[1]=0;
      for(i=2;i<4;i++)
      {
        for(j=1;j<i;j++)
          if(cardenemy[i*6+4]==cardenemy[j*6+4])
          {
            if(a[0]!=0)
            {
              if(cardenemy[i*6+5]>cardenemy[j*6+5])   //ѡ��ɫ�����
                a[1]=i;
              else
                a[1]=j;
            }
            else
            {
              if(cardenemy[i*6+5]>cardenemy[j*6+5])   //ѡ��ɫ�����
                a[0]=i;
              else
                a[0]=j;
            }
            j=0;
            break;
          }
        if(j!=0)
          k++;
      }
      if(k==1)
      {
        coreenemy=(cardenemy[a[0]*6+4]-10+1)*1000;
      }
      if(k==2)
      {
        coreenemy=(cardenemy[a[0]*6+4]-10+1)*100+cardenemy[a[0]*6+5];
      }
      if(k==3)
      {
        if(cardenemy[1*6+4]>cardenemy[2*6+4])
        {
          if(cardenemy[1*6+4]>cardenemy[3*6+4])
            coreenemy=(cardenemy[1*6+4]-10+1)*10+cardenemy[1*6+5];
          else
            coreenemy=(cardenemy[3*6+4]-10+1)*10+cardenemy[3*6+5];
        }
        else
        {
          if(cardenemy[2*6+4]>cardenemy[3*6+4])
            coreenemy=(cardenemy[2*6+4]-10+1)*10+cardenemy[2*6+5];
          else
            coreenemy=(cardenemy[3*6+4]-10+1)*10+cardenemy[3*6+5];
        }
      }
      if(coreself>coreenemy)
        return 1;
      if(coreself<coreenemy)
        return 0;
    }
    if(number==4)
    {
      //�Ƚ��ĸ���ͬ��
      if(cardself[1*6+4]==cardself[2*6+4]&&cardself[1*6+4]==cardself[3*6+4]
         &&cardself[1*6+4]==cardself[4*6+4])
        coreself=(cardself[1*6+4]-10+1)*1000;
      if(cardenemy[1*6+4]==cardenemy[2*6+4]&&cardenemy[1*6+4]==cardenemy[3*6+4]
         &&cardenemy[1*6+4]==cardenemy[4*6+4])
        coreenemy=(cardenemy[1*6+4]-10+1)*1000;
      if(coreself>coreenemy)
        return 1;
      if(coreself<coreenemy)
        return 0;
      //�Ƚ�������ͬ
      if((cardself[1*6+4]==cardself[2*6+4]&&cardself[1*6+4]==cardself[3*6+4])
         ||(cardself[1*6+4]==cardself[2*6+4]&&cardself[1*6+4]==cardself[4*6+4])
         ||(cardself[1*6+4]==cardself[3*6+4]&&cardself[1*6+4]==cardself[4*6+4])
         ||(cardself[2*6+4]==cardself[3*6+4]&&cardself[2*6+4]==cardself[4*6+4]))
      {
        if(cardself[1*6+4]==cardself[2*6+4])
          coreself=60+cardself[1*6+4]-10+1;
        if(cardself[3*6+4]==cardself[4*6+4])
          coreself=60+cardself[3*6+4]-10+1;
      }
      if((cardenemy[1*6+4]==cardenemy[2*6+4]&&cardenemy[1*6+4]==cardenemy[3*6+4])
         ||(cardenemy[1*6+4]==cardenemy[2*6+4]&&cardenemy[1*6+4]==cardenemy[4*6+4])
         ||(cardenemy[1*6+4]==cardenemy[3*6+4]&&cardenemy[1*6+4]==cardenemy[4*6+4])
         ||(cardenemy[2*6+4]==cardenemy[3*6+4]&&cardenemy[2*6+4]==cardenemy[4*6+4]))
      {
        if(cardenemy[1*6+4]==cardenemy[2*6+4])
          coreenemy=60+cardenemy[1*6+4]-10+1;
        if(cardenemy[3*6+4]==cardenemy[4*6+4])
          coreenemy=60+cardenemy[3*6+4]-10+1;
      }
      if(coreself>coreenemy)
        return 1;
      if(coreself<coreenemy)
        return 0;
      //
      k=1;
      a[0]=0;
      a[1]=0;
      for(i=2;i<5;i++)
      {
        for(j=1;j<i;j++)
          if(cardself[i*6+4]==cardself[j*6+4])
          {
            if(a[0]!=0)
            {
              if(cardself[i*6+5]>cardself[j*6+5])   //ѡ��ɫ�����
                a[1]=i;
              else
                a[1]=j;
            }
            else
            {
              if(cardself[i*6+5]>cardself[j*6+5])   //ѡ��ɫ�����
                a[0]=i;
              else
                a[0]=j;
            }
            j=0;
            break;
          }
        if(j!=0)
          k++;
      }
      if(k==2)
      {
        if(cardself[a[0]*6+4]>cardself[a[1]*6+4])
          coreself=(cardself[a[0]*6+4]-10+1)*1000+cardself[a[0]*6+5];
        if(cardself[a[0]*6+4]<cardself[a[1]*6+4])
          coreself=(cardself[a[1]*6+4]-10+1)*1000+cardself[a[1]*6+5];
      }
      if(k==3)
      {
        coreself=(cardself[a[0]*6+4]-10+1)*100+cardself[a[0]*6+5];
      }
      if(k==4)
      {
        if(cardself[1*6+4]>cardself[2*6+4]&&cardself[1*6+4]>cardself[3*6+4]
           &&cardself[1*6+4]>cardself[4*6+4])
        {
          coreself=(cardself[1*6+4]-10+1)*10+cardself[1*6+5];
        }
        if(cardself[2*6+4]>cardself[1*6+4]&&cardself[2*6+4]>cardself[3*6+4]
           &&cardself[2*6+4]>cardself[4*6+4])
        {
          coreself=(cardself[2*6+4]-10+1)*10+cardself[2*6+5];
        }
        if(cardself[3*6+4]>cardself[2*6+4]&&cardself[3*6+4]>cardself[1*6+4]
           &&cardself[3*6+4]>cardself[4*6+4])
        {
          coreself=(cardself[3*6+4]-10+1)*10+cardself[3*6+5];
        }
        if(cardself[4*6+4]>cardself[2*6+4]&&cardself[4*6+4]>cardself[3*6+4]
           &&cardself[4*6+4]>cardself[1*6+4])
        {
          coreself=(cardself[4*6+4]-10+1)*10+cardself[4*6+5];
        }
      }

      k=1;

      a[0]=0;
      a[1]=0;
      for(i=2;i<5;i++)
      {
        for(j=1;j<i;j++)
          if(cardenemy[i*6+4]==cardenemy[j*6+4])
          {
            if(a[0]!=0)
            {
              if(cardenemy[i*6+5]>cardenemy[j*6+5])   //ѡ��ɫ�����
                a[1]=i;
              else
                a[1]=j;
            }
            else
            {
              if(cardenemy[i*6+5]>cardenemy[j*6+5])   //ѡ��ɫ�����
                a[0]=i;
              else
                a[0]=j;
            }
            j=0;
            break;
          }
        if(j!=0)
          k++;
      }
      if(k==2)
      {
        if(cardenemy[a[0]*6+4]>cardenemy[a[1]*6+4])
          coreenemy=(cardenemy[a[0]*6+4]-10+1)*1000+cardenemy[a[0]*6+5];
        if(cardenemy[a[0]*6+4]<cardenemy[a[1]*6+4])
          coreenemy=(cardenemy[a[1]*6+4]-10+1)*1000+cardenemy[a[1]*6+5];
      }
      if(k==3)
      {
        coreenemy=(cardenemy[a[0]*6+4]-10+1)*100+cardenemy[a[0]*6+5];
      }
      if(k==4)
      {
        if(cardenemy[1*6+4]>cardenemy[2*6+4]&&cardenemy[1*6+4]>cardenemy[3*6+4]
           &&cardenemy[1*6+4]>cardenemy[4*6+4])
        {
          coreenemy=(cardenemy[1*6+4]-10+1)*10+cardenemy[1*6+5];
        }
        if(cardenemy[2*6+4]>cardenemy[1*6+4]&&cardenemy[2*6+4]>cardenemy[3*6+4]
           &&cardenemy[2*6+4]>cardenemy[4*6+4])
        {
          coreenemy=(cardenemy[2*6+4]-10+1)*10+cardenemy[2*6+5];
        }
        if(cardenemy[3*6+4]>cardenemy[2*6+4]&&cardenemy[3*6+4]>cardenemy[1*6+4]
           &&cardenemy[3*6+4]>cardenemy[4*6+4])
        {
          coreenemy=(cardenemy[3*6+4]-10+1)*10+cardenemy[3*6+5];
        }
        if(cardenemy[4*6+4]>cardenemy[2*6+4]&&cardenemy[4*6+4]>cardenemy[3*6+4]
           &&cardenemy[4*6+4]>cardenemy[1*6+4])
        {
          coreenemy=(cardenemy[4*6+4]-10+1)*10+cardenemy[4*6+5];
        }
      }
      if(coreself>coreenemy)
        return 1;
      if(coreself<coreenemy)
        return 0;
    }
    if(number==5)
    {
      //ͬ��˳
      if(cardself[0*6+5]==cardself[1*6+5]&&cardself[0*6+5]==cardself[2*6+5]
         &&cardself[0*6+5]==cardself[3*6+5]&&cardself[0*6+5]==cardself[4*6+5])
      {
        coreself=70+cardself[0*6+5];
      }
      if(cardenemy[0*6+5]==cardenemy[1*6+5]&&cardenemy[0*6+5]==cardenemy[2*6+5]
         &&cardenemy[0*6+5]==cardenemy[3*6+5]&&cardenemy[0*6+5]==cardenemy[4*6+5])
      {
        coreenemy=70+cardenemy[0*6+5];
      }
      if(coreself>coreenemy)
        return 1;
      if(coreself<coreenemy)
        return 0;
      //��֧
      if((cardself[0*6+4]==cardself[1*6+4]&&cardself[0*6+4]==cardself[2*6+4]&&cardself[0*6+4]==cardself[3*6+4])
         ||(cardself[0*6+4]==cardself[1*6+4]&&cardself[0*6+4]==cardself[2*6+4]&&cardself[0*6+4]==cardself[4*6+4])
         ||(cardself[0*6+4]==cardself[1*6+4]&&cardself[0*6+4]==cardself[3*6+4]&&cardself[0*6+4]==cardself[4*6+4])
         ||(cardself[0*6+4]==cardself[2*6+4]&&cardself[0*6+4]==cardself[3*6+4]&&cardself[0*6+4]==cardself[4*6+4])
         ||(cardself[1*6+4]==cardself[2*6+4]&&cardself[1*6+4]==cardself[3*6+4]&&cardself[1*6+4]==cardself[4*6+4]))
      {
        if(cardself[0*6+4]==cardself[1*6+4])
          coreself=60+cardself[0*6+4]-10+1;
        if(cardself[2*6+4]==cardself[3*6+4])
          coreself=60+cardself[2*6+4]-10+1;
      }
      if((cardenemy[0*6+4]==cardenemy[1*6+4]&&cardenemy[0*6+4]==cardenemy[2*6+4]&&cardenemy[0*6+4]==cardenemy[3*6+4])
         ||(cardenemy[0*6+4]==cardenemy[1*6+4]&&cardenemy[0*6+4]==cardenemy[2*6+4]&&cardenemy[0*6+4]==cardenemy[4*6+4])
         ||(cardenemy[0*6+4]==cardenemy[1*6+4]&&cardenemy[0*6+4]==cardenemy[3*6+4]&&cardenemy[0*6+4]==cardenemy[4*6+4])
         ||(cardenemy[0*6+4]==cardenemy[2*6+4]&&cardenemy[0*6+4]==cardenemy[3*6+4]&&cardenemy[0*6+4]==cardenemy[4*6+4])
         ||(cardenemy[1*6+4]==cardenemy[2*6+4]&&cardenemy[1*6+4]==cardenemy[3*6+4]&&cardenemy[1*6+4]==cardenemy[4*6+4]))
      {
        if(cardenemy[0*6+4]==cardenemy[1*6+4])
          coreenemy=60+cardenemy[0*6+4]-10+1;
        if(cardenemy[2*6+4]==cardenemy[3*6+4])
          coreenemy=60+cardenemy[2*6+4]-10+1;
      }
      if(coreself>coreenemy)
        return 1;
      if(coreself<coreenemy)
        return 0;
      //��«+����
      if(cardself[0*6+4]==cardself[1*6+4]&&cardself[0*6+4]==cardself[2*6+4])
      {
        if(cardself[3*6+4]==cardself[4*6+4])
          coreself=50+cardself[0*6+4]-10+1;
        else
          coreself=30+cardself[0*6+4]-10+1;
      }
      if(cardself[0*6+4]==cardself[1*6+4]&&cardself[0*6+4]==cardself[3*6+4])
      {
        if(cardself[2*6+4]==cardself[4*6+4])
          coreself=50+cardself[0*6+4]-10+1;
        else
          coreself=30+cardself[0*6+4]-10+1;
      }
      if(cardself[0*6+4]==cardself[1*6+4]&&cardself[0*6+4]==cardself[4*6+4])
      {
        if(cardself[2*6+4]==cardself[3*6+4])
          coreself=50+cardself[0*6+4]-10+1;
        else
          coreself=30+cardself[0*6+4]-10+1;
      }
      if(cardself[0*6+4]==cardself[2*6+4]&&cardself[0*6+4]==cardself[3*6+4])
      {
        if(cardself[1*6+4]==cardself[4*6+4])
          coreself=50+cardself[0*6+4]-10+1;
        else
          coreself=30+cardself[0*6+4]-10+1;
      }
      if(cardself[0*6+4]==cardself[2*6+4]&&cardself[0*6+4]==cardself[4*6+4])
      {
        if(cardself[1*6+4]==cardself[3*6+4])
          coreself=50+cardself[0*6+4]-10+1;
        else
          coreself=30+cardself[0*6+4]-10+1;
      }
      if(cardself[0*6+4]==cardself[3*6+4]&&cardself[0*6+4]==cardself[4*6+4])
      {
        if(cardself[1*6+4]==cardself[2*6+4])
          coreself=50+cardself[0*6+4]-10+1;
        else
          coreself=30+cardself[0*6+4]-10+1;
      }
      if(cardself[1*6+4]==cardself[2*6+4]&&cardself[1*6+4]==cardself[3*6+4])
      {
        if(cardself[0*6+4]==cardself[4*6+4])
          coreself=50+cardself[1*6+4]-10+1;
        else
          coreself=30+cardself[1*6+4]-10+1;
      }
      if(cardself[1*6+4]==cardself[2*6+4]&&cardself[1*6+4]==cardself[4*6+4])
      {
        if(cardself[0*6+4]==cardself[3*6+4])
          coreself=50+cardself[1*6+4]-10+1;
        else
          coreself=30+cardself[1*6+4]-10+1;
      }
      if(cardself[1*6+4]==cardself[3*6+4]&&cardself[1*6+4]==cardself[4*6+4])
      {
        if(cardself[0*6+4]==cardself[2*6+4])
          coreself=50+cardself[1*6+4]-10+1;
        else
          coreself=30+cardself[1*6+4]-10+1;
      }
      if(cardself[2*6+4]==cardself[3*6+4]&&cardself[2*6+4]==cardself[4*6+4])
      {
        if(cardself[0*6+4]==cardself[1*6+4])
          coreself=50+cardself[2*6+4]-10+1;
        else
          coreself=30+cardself[2*6+4]-10+1;
      }

      if(cardenemy[0*6+4]==cardenemy[1*6+4]&&cardenemy[0*6+4]==cardenemy[2*6+4])
      {
        if(cardenemy[3*6+4]==cardenemy[4*6+4])
          coreenemy=50+cardenemy[0*6+4]-10+1;
        else
          coreenemy=30+cardenemy[0*6+4]-10+1;
      }
      if(cardenemy[0*6+4]==cardenemy[1*6+4]&&cardenemy[0*6+4]==cardenemy[3*6+4])
      {
        if(cardenemy[2*6+4]==cardenemy[4*6+4])
          coreenemy=50+cardenemy[0*6+4]-10+1;
        else
          coreenemy=30+cardenemy[0*6+4]-10+1;
      }
      if(cardenemy[0*6+4]==cardenemy[1*6+4]&&cardenemy[0*6+4]==cardenemy[4*6+4])
      {
        if(cardenemy[2*6+4]==cardenemy[3*6+4])
          coreenemy=50+cardenemy[0*6+4]-10+1;
        else
          coreenemy=30+cardenemy[0*6+4]-10+1;
      }
      if(cardenemy[0*6+4]==cardenemy[2*6+4]&&cardenemy[0*6+4]==cardenemy[3*6+4])
      {
        if(cardenemy[1*6+4]==cardenemy[4*6+4])
          coreenemy=50+cardenemy[0*6+4]-10+1;
        else
          coreenemy=30+cardenemy[0*6+4]-10+1;
      }
      if(cardenemy[0*6+4]==cardenemy[2*6+4]&&cardenemy[0*6+4]==cardenemy[4*6+4])
      {
        if(cardenemy[1*6+4]==cardenemy[3*6+4])
          coreenemy=50+cardenemy[0*6+4]-10+1;
        else
          coreenemy=30+cardenemy[0*6+4]-10+1;
      }
      if(cardenemy[0*6+4]==cardenemy[3*6+4]&&cardenemy[0*6+4]==cardenemy[4*6+4])
      {
        if(cardenemy[1*6+4]==cardenemy[2*6+4])
          coreenemy=50+cardenemy[0*6+4]-10+1;
        else
          coreenemy=30+cardenemy[0*6+4]-10+1;
      }
      if(cardenemy[1*6+4]==cardenemy[2*6+4]&&cardenemy[1*6+4]==cardenemy[3*6+4])
      {
        if(cardenemy[0*6+4]==cardenemy[4*6+4])
          coreenemy=50+cardenemy[1*6+4]-10+1;
        else
          coreenemy=30+cardenemy[1*6+4]-10+1;
      }
      if(cardenemy[1*6+4]==cardenemy[2*6+4]&&cardenemy[1*6+4]==cardenemy[4*6+4])
      {
        if(cardenemy[0*6+4]==cardenemy[3*6+4])
          coreenemy=50+cardenemy[1*6+4]-10+1;
        else
          coreenemy=30+cardenemy[1*6+4]-10+1;
      }
      if(cardenemy[1*6+4]==cardenemy[3*6+4]&&cardenemy[1*6+4]==cardenemy[4*6+4])
      {
        if(cardenemy[0*6+4]==cardenemy[2*6+4])
          coreenemy=50+cardenemy[1*6+4]-10+1;
        else
          coreenemy=30+cardenemy[1*6+4]-10+1;
      }
      if(cardenemy[2*6+4]==cardenemy[3*6+4]&&cardenemy[2*6+4]==cardenemy[4*6+4])
      {
        if(cardenemy[0*6+4]==cardenemy[1*6+4])
          coreenemy=50+cardenemy[2*6+4]-10+1;
        else
          coreenemy=30+cardenemy[2*6+4]-10+1;
      }
      //˳��
      if(cardself[0*6+4]!=cardself[1*6+4]&&
         cardself[0*6+4]!=cardself[2*6+4]&&
         cardself[0*6+4]!=cardself[3*6+4]&&
         cardself[0*6+4]!=cardself[4*6+4]&&
         cardself[1*6+4]!=cardself[2*6+4]&&
         cardself[1*6+4]!=cardself[3*6+4]&&
         cardself[1*6+4]!=cardself[4*6+4]&&
         cardself[2*6+4]!=cardself[3*6+4]&&
         cardself[2*6+4]!=cardself[4*6+4]&&
         cardself[3*6+4]!=cardself[4*6+4] )
      {
         if(cardself[0*6+4]==14)
           coreself=40+cardself[0*6+5];
         if(cardself[1*6+4]==14)
           coreself=40+cardself[1*6+5];
         if(cardself[2*6+4]==14)
           coreself=40+cardself[2*6+5];
         if(cardself[3*6+4]==14)
           coreself=40+cardself[3*6+5];
         if(cardself[4*6+4]==14)
           coreself=40+cardself[4*6+5];
      }
      if(cardenemy[0*6+4]!=cardenemy[1*6+4]&&
          cardenemy[0*6+4]!=cardenemy[2*6+4]&&
          cardenemy[0*6+4]!=cardenemy[3*6+4]&&
          cardenemy[0*6+4]!=cardenemy[4*6+4]&&
          cardenemy[1*6+4]!=cardenemy[2*6+4]&&
          cardenemy[1*6+4]!=cardenemy[3*6+4]&&
          cardenemy[1*6+4]!=cardenemy[4*6+4]&&
          cardenemy[2*6+4]!=cardenemy[3*6+4]&&
          cardenemy[2*6+4]!=cardenemy[4*6+4]&&
          cardenemy[3*6+4]!=cardenemy[4*6+4] )
      {
          if(cardenemy[0*6+4]==14)
            coreenemy=40+cardenemy[0*6+5];
          if(cardenemy[1*6+4]==14)
            coreenemy=40+cardenemy[1*6+5];
          if(cardenemy[2*6+4]==14)
            coreenemy=40+cardenemy[2*6+5];
          if(cardenemy[3*6+4]==14)
            coreenemy=40+cardenemy[3*6+5];
          if(cardenemy[4*6+4]==14)
            coreenemy=40+cardenemy[4*6+5];
      }
      if(coreself>coreenemy)
        return 1;
      if(coreself<coreenemy)
        return 0;
      //���Ժ�һ��
      k=1;
      a[0]=0;
      a[1]=0;
      for(i=1;i<5;i++)
      {
        for(j=0;j<i;j++ )
          if(cardself[i*6+4]==cardself[j*6+4])
          {
            if(a[0]!=0)
            {
              if(cardself[i*6+5]>cardself[j*6+5])   //ѡ��ɫ�����
                a[1]=i;
              else
                a[1]=j;
            }
            else
            {
              if(cardself[i*6+5]>cardself[j*6+5])   //ѡ��ɫ�����
                a[0]=i;
              else
                a[0]=j;
            }
            j=0;
            break;
          }
        if(j!=0)
          k++;
      }
      if(k==3)
      {
        if(cardself[a[0]*6+4]>cardself[a[1]*6+4])
          coreself=(cardself[a[0]*6+4]-10+1)*100+cardself[a[0]*6+5];
        if(cardself[a[0]*6+4]<cardself[a[1]*6+4])
          coreself=(cardself[a[1]*6+4]-10+1)*100+cardself[a[1]*6+5];
      }
      if(k==4)
      {
        coreself=(cardself[a[0]*6+4]-10+1)*10+cardself[a[0]*6+5];
      }
      k=1;
      a[0]=0;
      a[1]=0;
      for(i=1;i<5;i++)
      {
        for(j=0;j<i;j++ )
          if(cardenemy[i*6+4]==cardenemy[j*6+4])
          {
            if(a[0]!=0)
            {
              if(cardenemy[i*6+5]>cardenemy[j*6+5])   //ѡ��ɫ�����
                a[1]=i;
              else
                a[1]=j;
            }
            else
            {
              if(cardenemy[i*6+5]>cardenemy[j*6+5])   //ѡ��ɫ�����
                a[0]=i;
              else
                a[0]=j;
            }
            j=0;
            break;
          }
        if(j!=0)
          k++;
      }
      if(k==3)
      {
        if(cardenemy[a[0]*6+4]>cardenemy[a[1]*6+4])
          coreenemy=(cardenemy[a[0]*6+4]-10+1)*100+cardenemy[a[0]*6+5];
        if(cardenemy[a[0]*6+4]<cardenemy[a[1]*6+4])
          coreenemy=(cardenemy[a[1]*6+4]-10+1)*100+cardenemy[a[1]*6+5];
      }
      if(k==4)
      {
        coreenemy=(cardenemy[a[0]*6+4]-10+1)*10+cardenemy[a[0]*6+5];
      }
      if(coreself>coreenemy)
        return 1;
      if(coreself<coreenemy)
        return 0;
    }
    return 1;
  }

  public void sendcard(int index)
  {
    int nextcard;
    nextcard=randRange(20);

    cardself[index*6]  =index+1;
    cardself[index*6+1]=nextcard;
    cardself[index*6+2]=15*index+10;
    cardself[index*6+3]=74;

    socketthread.sendmessage(Integer.toString(nextcard)+Integer.toString(index)+"2");

    if(nextcard==0)
    {
      cardself[index*6+4]=10; //�Ƶ���ֵ
      cardself[index*6+5]=4;  //�ƵĻ�ɫ
    }
    if(nextcard==1)
    {
      cardself[index*6+4]=11;
      cardself[index*6+5]=4;
    }
    if(nextcard==2)
    {
      cardself[index*6+4]=12;
      cardself[index*6+5]=4;
    }
    if(nextcard==3)
    {
      cardself[index*6+4]=13;
      cardself[index*6+5]=4;
    }
    if(nextcard==4)
    {
      cardself[index*6+4]=14;
      cardself[index*6+5]=4;
    }
    if(nextcard==5)
    {
      cardself[index*6+4]=10;
      cardself[index*6+5]=3;
    }
    if(nextcard==6)
    {
      cardself[index*6+4]=11;
      cardself[index*6+5]=3;
    }
    if(nextcard==7)
    {
      cardself[index*6+4]=12;
      cardself[index*6+5]=3;
    }
    if(nextcard==8)
    {
      cardself[index*6+4]=13;
      cardself[index*6+5]=3;
    }
    if(nextcard==9)
    {
      cardself[index*6+4]=14;
      cardself[index*6+5]=3;
    }
    if(nextcard==10)
    {
      cardself[index*6+4]=10;
      cardself[index*6+5]=2;
    }
    if(nextcard==11)
    {
      cardself[index*6+4]=11;
      cardself[index*6+5]=2;
    }
    if(nextcard==12)
    {
      cardself[index*6+4]=12;
      cardself[index*6+5]=2;
    }
    if(nextcard==13)
    {
      cardself[index*6+4]=13;
      cardself[index*6+5]=2;
    }
    if(nextcard==14)
    {
      cardself[index*6+4]=14;
      cardself[index*6+5]=2;
    }
    if(nextcard==15)
    {
      cardself[index*6+4]=10;
      cardself[index*6+5]=1;
    }
    if(nextcard==16)
    {
      cardself[index*6+4]=11;
      cardself[index*6+5]=1;
    }
    if(nextcard==17)
    {
      cardself[index*6+4]=12;
      cardself[index*6+5]=1;
    }
    if(nextcard==18)
    {
      cardself[index*6+4]=13;
      cardself[index*6+5]=1;
    }
    if(nextcard==19)
    {
      cardself[index*6+4]=14;
      cardself[index*6+5]=1;
    }
  }

  public void getcard(int nextcard)
  {
    int index;
    index=nextcard%10;
    nextcard=nextcard/10;

    cardenemy[index*6]  =index+1;
    cardenemy[index*6+1]=nextcard;
    cardenemy[index*6+2]=15*index+10;
    cardenemy[index*6+3]=20;
    if(nextcard==0)
    {
      cardenemy[index*6+4]=10;
      cardenemy[index*6+5]=4;
    }
    if(nextcard==1)
    {
      cardenemy[index*6+4]=11;
      cardenemy[index*6+5]=4;
    }
    if(nextcard==2)
    {
      cardenemy[index*6+4]=12;
      cardenemy[index*6+5]=4;
    }
    if(nextcard==3)
    {
      cardenemy[index*6+4]=13;
      cardenemy[index*6+5]=4;
    }
    if(nextcard==4)
    {
      cardenemy[index*6+4]=14;
      cardenemy[index*6+5]=4;
    }
    if(nextcard==5)
    {
      cardenemy[index*6+4]=10;
      cardenemy[index*6+5]=3;
    }
    if(nextcard==6)
    {
      cardenemy[index*6+4]=11;
      cardenemy[index*6+5]=3;
    }
    if(nextcard==7)
    {
      cardenemy[index*6+4]=12;
      cardenemy[index*6+5]=3;
    }
    if(nextcard==8)
    {
      cardenemy[index*6+4]=13;
      cardenemy[index*6+5]=3;
    }
    if(nextcard==9)
    {
      cardenemy[index*6+4]=14;
      cardenemy[index*6+5]=3;
    }
    if(nextcard==10)
    {
      cardenemy[index*6+4]=10;
      cardenemy[index*6+5]=2;
    }
    if(nextcard==11)
    {
      cardenemy[index*6+4]=11;
      cardenemy[index*6+5]=2;
    }
    if(nextcard==12)
    {
      cardenemy[index*6+4]=12;
      cardenemy[index*6+5]=2;
    }
    if(nextcard==13)
    {
      cardenemy[index*6+4]=13;
      cardenemy[index*6+5]=2;
    }
    if(nextcard==14)
    {
      cardenemy[index*6+4]=14;
      cardenemy[index*6+5]=2;
    }
    if(nextcard==15)
    {
      cardenemy[index*6+4]=10;
      cardenemy[index*6+5]=1;
    }
    if(nextcard==16)
    {
      cardenemy[index*6+4]=11;
      cardenemy[index*6+5]=1;
    }
    if(nextcard==17)
    {
      cardenemy[index*6+4]=12;
      cardenemy[index*6+5]=1;
    }
    if(nextcard==18)
    {
      cardenemy[index*6+4]=13;
      cardenemy[index*6+5]=1;
    }
    if(nextcard==19)
    {
      cardenemy[index*6+4]=14;
      cardenemy[index*6+5]=1;
    }
  }

  public void communication()
  {
    int getstr;
    if(socketthread.B_Socketthread ==true)
    {
      socketthread.accept();
      if(socketthread.GMessage!="NULL")
      {
        getstr=Integer.parseInt(socketthread.GMessage);
        if(getstr%10==1)
        {
          //���ܵ���ʼ��Ϸ�ı�ʾ��
          person="yes";
//          isFirst=3;
//          socketthread.sendmessage("1");
        }
        if(getstr%10==2)
        {
          //���ܵ���
          getcard(getstr/10);
        }
        if(getstr%10==3)
        {
          //ѺǮ��
          tempcash1=getstr/10;
          if(tempcash1==0)
          {
            cash10=0;
            cash50=0;
            cash100=0;
            showall=0;
            handX=30;
            handY=115;
            show=0;
            //��������ʼ��һ��
            indexcard=0;
            for(int i=0;i<30;i++)
            {
               cardself[i]=0;
               cardenemy[i]=0;
            }
            totalcash0=totalcash0+money;
            money=0;
            checkself=0;
            checkenemy=0;
          }
          checkenemy++;
          money=money+tempcash1;
        }
      }
    }
  }

  public void showcard(Graphics g)
  {
    int nextcard,x,y;
    g.drawImage(imagediban,0,0,g.TOP|g.LEFT);

    //g.drawImage(imagexiaoguo,0,0,g.TOP|g.LEFT);
    //g.drawString(String.valueOf(chessmatrixX[5]),1,145,g.TOP|g.HCENTER);
    //g.drawString("��Ȩ���У� ANYWALK TECH.COM.",width/2,height-15,g.TOP|g.HCENTER);

    //g.drawImage(imagebei,10,13,g.TOP|g.LEFT);

    for(int i=0;i<indexcard ;i++)
    {
      nextcard=cardself[i*6+1];
      x=cardself[i*6+2];
      y=cardself[i*6+3];
      if(cardself[i*6]>0)
      {
        if(nextcard==0)
          g.drawImage(imagehei10,x,y,g.TOP|g.LEFT);
        if(nextcard==1)
          g.drawImage(imageheiJ,x,y,g.TOP|g.LEFT);
        if(nextcard==2)
          g.drawImage(imageheiQ,x,y,g.TOP|g.LEFT);
        if(nextcard==3)
          g.drawImage(imageheiK,x,y,g.TOP|g.LEFT);
        if(nextcard==4)
          g.drawImage(imageheiA,x,y,g.TOP|g.LEFT);
        if(nextcard==5)
          g.drawImage(imagehong10,x,y,g.TOP|g.LEFT);
        if(nextcard==6)
          g.drawImage(imagehongJ,x,y,g.TOP|g.LEFT);
        if(nextcard==7)
          g.drawImage(imagehongQ,x,y,g.TOP|g.LEFT);
        if(nextcard==8)
          g.drawImage(imagehongK,x,y,g.TOP|g.LEFT);
        if(nextcard==9)
          g.drawImage(imagehongA,x,y,g.TOP|g.LEFT);
        if(nextcard==10)
          g.drawImage(imagehua10,x,y,g.TOP|g.LEFT);
        if(nextcard==11)
          g.drawImage(imagehuaJ,x,y,g.TOP|g.LEFT);
        if(nextcard==12)
          g.drawImage(imagehuaQ,x,y,g.TOP|g.LEFT);
        if(nextcard==13)
          g.drawImage(imagehuaK,x,y,g.TOP|g.LEFT);
        if(nextcard==14)
          g.drawImage(imagehuaA,x,y,g.TOP|g.LEFT);
        if(nextcard==15)
          g.drawImage(imagepian10,x,y,g.TOP|g.LEFT);
        if(nextcard==16)
          g.drawImage(imagepianJ,x,y,g.TOP|g.LEFT);
        if(nextcard==17)
          g.drawImage(imagepianQ,x,y,g.TOP|g.LEFT);
        if(nextcard==18)
          g.drawImage(imagepianK,x,y,g.TOP|g.LEFT);
        if(nextcard==19)
          g.drawImage(imagepianA,x,y,g.TOP|g.LEFT);
      }
    }

    int isShowFrist=1;//0:��ʾ��1������ʾ
    if(indexcard<5)
    {
      g.drawImage(imagebei,cardenemy[2],cardenemy[3],g.TOP|g.LEFT);
      isShowFrist=1;
    }
    else
      isShowFrist=0;
    for(int i=isShowFrist;i<indexcard ;i++)
    {
      if(cardenemy[i*6]>0)
      {
        nextcard=cardenemy[i*6+1];
        x=cardenemy[i*6+2];
        y=cardenemy[i*6+3];
        if(nextcard==0)
          g.drawImage(imagehei10,x,y,g.TOP|g.LEFT);
        if(nextcard==1)
          g.drawImage(imageheiJ,x,y,g.TOP|g.LEFT);
        if(nextcard==2)
          g.drawImage(imageheiQ,x,y,g.TOP|g.LEFT);
        if(nextcard==3)
          g.drawImage(imageheiK,x,y,g.TOP|g.LEFT);
        if(nextcard==4)
          g.drawImage(imageheiA,x,y,g.TOP|g.LEFT);
        if(nextcard==5)
          g.drawImage(imagehong10,x,y,g.TOP|g.LEFT);
        if(nextcard==6)
          g.drawImage(imagehongJ,x,y,g.TOP|g.LEFT);
        if(nextcard==7)
          g.drawImage(imagehongQ,x,y,g.TOP|g.LEFT);
        if(nextcard==8)
          g.drawImage(imagehongK,x,y,g.TOP|g.LEFT);
        if(nextcard==9)
          g.drawImage(imagehongA,x,y,g.TOP|g.LEFT);
        if(nextcard==10)
          g.drawImage(imagehua10,x,y,g.TOP|g.LEFT);
        if(nextcard==11)
          g.drawImage(imagehuaJ,x,y,g.TOP|g.LEFT);
        if(nextcard==12)
          g.drawImage(imagehuaQ,x,y,g.TOP|g.LEFT);
        if(nextcard==13)
          g.drawImage(imagehuaK,x,y,g.TOP|g.LEFT);
        if(nextcard==14)
          g.drawImage(imagehuaA,x,y,g.TOP|g.LEFT);
        if(nextcard==15)
          g.drawImage(imagepian10,x,y,g.TOP|g.LEFT);
        if(nextcard==16)
          g.drawImage(imagepianJ,x,y,g.TOP|g.LEFT);
        if(nextcard==17)
          g.drawImage(imagepianQ,x,y,g.TOP|g.LEFT);
        if(nextcard==18)
          g.drawImage(imagepianK,x,y,g.TOP|g.LEFT);
        if(nextcard==19)
          g.drawImage(imagepianA,x,y,g.TOP|g.LEFT);
      }
    }

    g.setGrayScale(255);
    g.drawString(Integer.toString(totalcash0),19,128,g.TOP|g.LEFT);
    g.drawString(Integer.toString(totalcash1),60,1,g.TOP|g.LEFT);
    if(show==1)
    {
      g.drawImage(imagexiazhuqi,22,21,g.TOP|g.LEFT);
      g.setGrayScale(255);
      //g.drawString("0123456789",10,13,g.TOP|g.LEFT);
      g.drawString(Integer.toString(cash10),65,42,g.TOP|g.LEFT);
      g.drawString(Integer.toString(cash50),65,59,g.TOP|g.LEFT);
      g.drawString(Integer.toString(cash100),65,76,g.TOP|g.LEFT);
      g.drawString(Integer.toString(tempcash0),35,24,g.TOP|g.LEFT);
      g.drawString(Integer.toString(tempcash1),72,24,g.TOP|g.LEFT);
      if(showall==1)
        g.drawString("ShowAll",47,93,g.TOP|g.LEFT);

      //g.setColor(255);
      //g.drawString("0123456789",10,23,g.TOP|g.LEFT);
      //g.setGrayScale(0);
      //g.drawString("0123456789",10,33,g.TOP|g.LEFT);
      //g.drawString(Integer.toString(randRange(5)),10,23,g.TOP|g.LEFT);
      g.drawImage(imagehand,handX,handY,g.TOP|g.LEFT);
    }
  }

  /*****************************************************/
  // ��Ϸ��ʼ��
  //
  //
  /*****************************************************/
  public void start()
  {
    //��ʱ����ͨѶ����
    //socketthread.accept();

    thread.start();
    for(int i=0;i<30;i++)
    {
      cardself[i]=0;
      cardenemy[i]=0;
    }
  }
}