import javax.swing.*;
import java.awt.*;
/*
 *------------Keyset.java-------------
 * ��java�ļ���Ҫ��ɱ༭�������ַ�����
 * ��java�ļ���������:2007��3��17��
 * ��Java�ļ��깤����:2007��3��20��
 * ��д��:��ռ��
 */
public class Keyset extends JPanel{
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		String str=new String(PublicData.key);
		int keyTop=0;//�ַ��ڼ����ϵ�������
		int keyLeft=0;//�ַ��ڼ����ϵĺ�����
		boolean leftShift=false;//�Ƿ�����ߵ�Shift
		boolean rightShift=false;//�Ƿ����ұߵ�Shift
		boolean yShift=false;//�Ƿ�Ҫ��Shift
		boolean ySpace=false;//�Ƿ��ǿո�
		String shift=new String("~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"ZXCVBNM<>?");
		String noShift=new String("`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./");
		int LEFT=52;
		int TOP=20;
		int lTemp=LEFT;
		int tTemp=TOP;
		if(shift.lastIndexOf(str)<=46){//�����ǲ���Ҫ����Shift
			if((shift.lastIndexOf(str)>=0 && shift.lastIndexOf(str)<=5) || 
				(shift.lastIndexOf(str)>=13 && shift.lastIndexOf(str)<=17) || 
				(shift.lastIndexOf(str)>=26 && shift.lastIndexOf(str)<=30) || 
				(shift.lastIndexOf(str)>=37 && shift.lastIndexOf(str)<=41)){//�����ַ��ڲ���������
				yShift=true;
				rightShift=true;
				if(shift.lastIndexOf(str)>=0 && shift.lastIndexOf(str)<=5){//�����ַ��ڸ�����λ��
					keyTop=1;
					for(int i=0;i<=5;i++){
						if(str.equals(shift.substring(i, i+1))){
							keyLeft=i;
							System.out.println(shift.substring(i, i+1));
						}
					}
				}
				if(shift.lastIndexOf(str)>=13 && shift.lastIndexOf(str)<=17){//�����ַ��ڸ�����λ��
					keyTop=2;
					for(int i=13;i<=17;i++){
						if(str.equals(shift.substring(i, i+1))){
							keyLeft=i;
						}
					}
				}
				if(shift.lastIndexOf(str)>=26 && shift.lastIndexOf(str)<=30){//�����ַ��ڸ�����λ��
					keyTop=3;
					for(int i=26;i<=30;i++){
						if(str.equals(shift.substring(i, i+1))){
							keyLeft=i;
						}
					}
				}
				if(shift.lastIndexOf(str)>=37 && shift.lastIndexOf(str)<=41){//�����ַ��ڸ�����λ��
					keyTop=4;
					for(int i=37;i<=41;i++){
						if(str.equals(shift.substring(i, i+1))){
							keyLeft=i;
						}
					}
				}
			}
			if((shift.lastIndexOf(str)>=6 && shift.lastIndexOf(str)<=12) || 
				(shift.lastIndexOf(str)>=18 && shift.lastIndexOf(str)<=25) || 
				(shift.lastIndexOf(str)>=31 && shift.lastIndexOf(str)<=36) || 
				(shift.lastIndexOf(str)>=42 && shift.lastIndexOf(str)<=46)){//�����ַ��ڲ���������
				yShift=true;
				leftShift=true;
				if(shift.lastIndexOf(str)>=6 && shift.lastIndexOf(str)<=12){
					keyTop=1;
					for(int i=6;i<=12;i++){
						if(str.equals(shift.substring(i, i+1))){
							keyLeft=i;
						}
					}
				}
				if(shift.lastIndexOf(str)>=18 && shift.lastIndexOf(str)<=25){
					keyTop=2;
					for(int i=18;i<=25;i++){
						if(str.equals(shift.substring(i, i+1))){
							keyLeft=i;
						}
					}
				}
				if(shift.lastIndexOf(str)>=31 && shift.lastIndexOf(str)<=36){
					keyTop=3;
					for(int i=31;i<=36;i++){
						if(str.equals(shift.substring(i, i+1))){
							keyLeft=i;
						}
					}
				}
				if(shift.lastIndexOf(str)>=42 && shift.lastIndexOf(str)<=46){
					keyTop=4;
					for(int i=42;i<=46;i++){
						if(str.equals(shift.substring(i, i+1))){
							keyLeft=i;
						}
					}
				}
			}
		}
		if(noShift.lastIndexOf(str)<=46){
			if(noShift.lastIndexOf(str)>=0 && noShift.lastIndexOf(str)<=12){
				keyTop=1;
				for(int i=0;i<=12;i++){
					if(str.equals(noShift.substring(i,i+1))){
						keyLeft=i;
					}
				}
			}
			if(noShift.lastIndexOf(str)>=13 && noShift.lastIndexOf(str)<=25){
				keyTop=2;
				for(int i=13;i<=25;i++){
					if(str.equals(noShift.substring(i,i+1))){
						keyLeft=i;
					}
				}
			}
			if(noShift.lastIndexOf(str)>=26 && noShift.lastIndexOf(str)<=36){
				keyTop=3;
				for(int i=26;i<=36;i++){
					if(str.equals(noShift.substring(i,i+1))){
						keyLeft=i;
					}
				}
			}
			if(noShift.lastIndexOf(str)>=37 && noShift.lastIndexOf(str)<=46){
				keyTop=4;
				for(int i=37;i<=46;i++){
					if(str.equals(noShift.substring(i,i+1))){
						keyLeft=i;
					}
				}
			}
		}
		if(str.equals(" ")){
			ySpace=true;
		}

		//���Ʊ༭����ͼ��//
		g.setColor(Color.black);
		for(int i=0;i<=12;i++){
			if(keyTop==1 && keyLeft==i){
				g.setColor(Color.gray);
				g.fillRect(LEFT,TOP,35,35);
			}
			g.setColor(Color.black);
			g.draw3DRect(LEFT,TOP,35,35,true);
			LEFT=LEFT+37;
		}
		//����
		g.draw3DRect(LEFT,TOP,70,35,true);
		
		int sum=12;
		LEFT=lTemp;
		TOP=TOP+37;
		//Tab��
		g.draw3DRect(LEFT,TOP,54,35,true);
		LEFT=LEFT+56;

		for(int i=1;i<=12;i++)
		{
			if(keyTop==2 && keyLeft==i+sum){
				g.setColor(Color.gray);
				g.fillRect(LEFT,TOP,35,35);
			}
			g.setColor(Color.black);
			g.draw3DRect(LEFT,TOP,35,35,true);
			LEFT=LEFT+37;
		}
		//|\��
		sum=sum+12;
		if(keyTop==2 && keyLeft==sum+1){
			g.setColor(Color.gray);
			g.fillRect(LEFT,TOP,51,35);
		}
		g.setColor(Color.black);
		g.draw3DRect(LEFT,TOP,51,35,true);
		
		sum=sum+1;
		LEFT=lTemp;
		TOP=TOP+37;
		//Caps Lock��
		g.draw3DRect(LEFT,TOP,65,35,true);
		LEFT=LEFT+67;

		for(int i=1;i<=11;i++){
			if(keyTop==3 && keyLeft==sum+i){
				g.setColor(Color.gray);
				g.fillRect(LEFT,TOP,35,35);
			}
			g.setColor(Color.black);
			g.draw3DRect(LEFT,TOP,35,35,true);
			LEFT=LEFT+37;
		}
		//Enter��
		g.draw3DRect(LEFT,TOP,77,35,true);
		sum=sum+11;
		LEFT=lTemp;
		TOP=TOP+37;
		//��Shift��
		if(leftShift==true){
			g.setColor(Color.gray);
			g.fillRect(LEFT,TOP,82,35);
		}
		g.setColor(Color.black);
		g.draw3DRect(LEFT,TOP,82,35,true);

		LEFT=LEFT+84;
		for(int i=1;i<=10;i++){
			if(keyTop==4 && keyLeft==sum+i){
				g.setColor(Color.gray);
				g.fillRect(LEFT,TOP,35,35);
			}
			g.setColor(Color.black);
			g.draw3DRect(LEFT,TOP,35,35,true);
			LEFT=LEFT+37;
		}
		//��Shift��
		if(rightShift==true){
			g.setColor(Color.gray);
			g.fillRect(LEFT,TOP,97,35);
		}
		g.setColor(Color.black);
		g.draw3DRect(LEFT,TOP,97,35,true);
		
		//��Ctrl��
		LEFT=lTemp;
		TOP=TOP+37;
		g.draw3DRect(LEFT,TOP,53,35,true);
		LEFT=LEFT+55;
		//win��
		g.draw3DRect(LEFT,TOP,44,35,true);
		LEFT=LEFT+46;
		//��Alt��
		g.draw3DRect(LEFT,TOP,44,35,true);
		LEFT=LEFT+46;
		//Space��
		if(ySpace==true){
			g.setColor(Color.gray);
			g.fillRect(LEFT,TOP,211,35);
		}
		g.setColor(Color.black);
		g.draw3DRect(LEFT,TOP,211,35,true);
		//��Alt��
		LEFT=LEFT+213;
		g.draw3DRect(LEFT,TOP,44,35,true);
		LEFT=LEFT+46;
		//win��
		g.draw3DRect(LEFT,TOP,46,35,true);
		LEFT=LEFT+48;
		//��ݼ�
		g.draw3DRect(LEFT,TOP,42,35,true);
		LEFT=LEFT+44;
		//��Ctrl��
		g.draw3DRect(LEFT,TOP,53,35,true);
		//�༭����ͼ�λ������
		//������ĸ������-------
		LEFT=lTemp;
		TOP=tTemp;
		g.setColor(Color.lightGray);
		Font f=new Font("x",Font.BOLD,13);
		g.setFont(f);
		LEFT=LEFT+7;
		TOP=TOP+14;
		for(int i=1;i<=13;i++){
			g.drawString(shift.substring(i-1,i),LEFT,TOP);
			g.drawString(noShift.substring(i-1,i),LEFT,TOP+18);
			LEFT=LEFT+37;
		}
		f=new Font("x",Font.BOLD,12);
		g.setFont(f);
		g.drawString("BackSpace",LEFT-4,TOP+8);
		LEFT=lTemp+7;
		TOP=TOP+37;
		g.drawString("Tab",LEFT,TOP+8);
		f=new Font("x",Font.BOLD,14);
		g.setFont(f);
		LEFT=lTemp+63;
		for(int i=13;i<=25;i++){
			g.drawString(shift.substring(i,i+1),LEFT,TOP);
			if(i>=23)
				g.drawString(noShift.substring(i,i+1),LEFT,TOP+16);
			LEFT=LEFT+37;
		}
		f=new Font("x",Font.BOLD,12);
		g.setFont(f);
		LEFT=lTemp;
		TOP=TOP+37;
		g.drawString("CapsLock",LEFT+7,TOP+8);
		f=new Font("x",Font.BOLD,14);
		g.setFont(f);
		LEFT=lTemp+74;
		for(int i=26;i<=36;i++){
			g.drawString(shift.substring(i,i+1),LEFT,TOP);
			if(i>=35)
				g.drawString(noShift.substring(i,i+1),LEFT,TOP+16);
			if("F".equals(shift.substring(i,i+1)) || "J".equals(shift.substring(i,i+1)))
				g.drawString("_",LEFT+5,TOP+15);
			LEFT=LEFT+37;
		}
		f=new Font("x",Font.BOLD,12);
		g.setFont(f);
		g.drawString("Enter����",LEFT+15,TOP+8);
		LEFT=lTemp+7;
		TOP=TOP+37;
		g.drawString("��Shift",LEFT,TOP+8);
		f=new Font("x",Font.BOLD,14);
		g.setFont(f);
		LEFT=lTemp+91;
		for(int i=37;i<=46;i++){
			g.drawString(shift.substring(i,i+1),LEFT,TOP);
			if(i>=44)
				g.drawString(noShift.substring(i,i+1),LEFT,TOP+16);
			LEFT=LEFT+37;
		}
		f=new Font("x",Font.BOLD,12);
		g.setFont(f);
		g.drawString("��Shift",LEFT,TOP+8);
		LEFT=lTemp+7;
		TOP=TOP+37;
		g.drawString("Ctrl",LEFT,TOP+8);
		LEFT=LEFT+55;
		g.drawString("Win",LEFT,TOP+8);
		LEFT=LEFT+46;
		g.drawString("Alt",LEFT,TOP+8);
		LEFT=LEFT+46;
		g.drawString("Space",LEFT+90,TOP+8);
		LEFT=LEFT+213;
		g.drawString("Alt",LEFT,TOP+8);
		LEFT=LEFT+46;
		g.drawString("Win",LEFT,TOP+8);
		LEFT=LEFT+50;
		g.drawString("SM",LEFT,TOP+8);
		LEFT=LEFT+44;
		g.drawString("Ctrl",LEFT,TOP+8);
	}
}