import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import static java.awt.RenderingHints.*;
import javax.swing.Timer;
public class m extends Applet implements ActionListener,MouseListener,MouseMotionListener{

	private int[][] xyz; //4�����깹�ɵ���

	private double[] x,y,z;  //ԭʼ��

	private double[] x1,y1,z1;  // ��ת��ĵ�

	private double[][] mxy={{1,0,0},{0,1,0},{0,0,1}}; //��ת����

	private int time=0;
	
	private int[] colors={0x70e33e,0x65f0e4,0xf20f2f,0xffff00,0x454545,0xaaaaaa};  //����ɫ

	private	int ����=4; //�ڴ������ǣ�4*4����ħ��

	private int �Ӿ�=800;  //Խ��ԽԶ

	private int ����=-1;  //װ���ǵ����õ��ķ�����xyz�������

	private int[] �����={-1,-1};  //���ʱ���������

	private int[] ����ƶ�={-1,-1};

	private double[][] nou;//��ת����

	private double �κ�;
	private int ȡ��;

	private BufferedImage bi;
	private Graphics2D big;

//��ʱ��
	public void actionPerformed(ActionEvent e){
		if(time>0){
			for(int i=0;i<����*����*6;i++){

				if(get��(i)){
					for(int u=0;u<4;u++){
						double[] hh={x[xyz[i][u]],y[xyz[i][u]],z[xyz[i][u]]};
						double[] hjh=setMxy(hh,nou);
						x[xyz[i][u]]=hjh[0];
						y[xyz[i][u]]=hjh[1];
						z[xyz[i][u]]=hjh[2];
					}
				}
			}


			time--;
		}
		repaint();
	}

//Download by http://www.codefans.net
//��ʼ������
	public void init(){

		double[] ��ʱ��=new double[3];

		int i1,i2,i3,i4;

		int ֱ��=100;
		int �߾�=8;
		
		int ���ĵ�=((ֱ��+�߾�)*����-�߾�)/2;

		x=new double[����*����*����<<3];
		y=new double[����*����*����<<3];
		z=new double[����*����*����<<3];
		x1=new double[����*����*����<<3];
		y1=new double[����*����*����<<3];
		z1=new double[����*����*����<<3];
		xyz=new int[����*����*����<<1][4];
		
		double[][] X�����={{1,0,0},{0,0,1},{0,-1,0}};
		double[][] Y�����={{0,0,1},{0,1,0},{-1,0,0}};

		for(i1=0;i1<����;i1++){
			i4=i1<<2;
			x[i4+3]=x[i4]=i1*(ֱ��+�߾�)-���ĵ�;
			x[i4+1]=x[i4+2]=x[i4]+ֱ��;

			y[i4+1]=y[i4]=���ĵ�;
			y[i4+2]=y[i4+3]=y[i4]-ֱ��;
			z[i4]=z[i4+1]=z[i4+2]=z[i4+3]=���ĵ�;
		}
		for(i2=1;i2<����;i2++){
			for(i1=0;i1<����*4;i1++){
				x[i2*����*4+i1]=x[i1];
				z[i2*����*4+i1]=z[i1];
				y[i2*����*4+i1]=y[i1]-i2*(ֱ��+�߾�);
			}
		}
		for(i2=0;i2<3;i2++){
			for(i1=0;i1<����*����*4;i1++){
				��ʱ��[0]=x[i2*����*����*4+i1];
				��ʱ��[1]=y[i2*����*����*4+i1];
				��ʱ��[2]=z[i2*����*����*4+i1];	
				��ʱ��=setMxy(��ʱ��,X�����);	
				x[(i2+1)*����*����*4+i1]=��ʱ��[0];
				y[(i2+1)*����*����*4+i1]=��ʱ��[1];
				z[(i2+1)*����*����*4+i1]=��ʱ��[2];	
			}
		}
		for(i1=0;i1<����*����*4;i1++){
				��ʱ��[0]=x[i1];
				��ʱ��[1]=y[i1];
				��ʱ��[2]=z[i1];	
				double[] sy=setMxy(��ʱ��,Y�����);
				x[����*����*16+i1]=sy[0];
				y[����*����*16+i1]=sy[1];
				z[����*����*16+i1]=sy[2];
				double[][] kj=getMxy(getMxy(Y�����,Y�����),Y�����);
				sy=setMxy(��ʱ��,kj);
				x[����*����*20+i1]=sy[0];
				y[����*����*20+i1]=sy[1];
				z[����*����*20+i1]=sy[2];	
		}

		for(i1=0;i1<����*����*6;i1++){
			for(i2=0;i2<4;i2++){
				xyz[i1][i2]=i1*4+i2;
			}	
		}



		addMouseMotionListener(this);
		addMouseListener(this);
		Timer t=new Timer(40,this);
		setBackground(new Color(0x00ff00));
		bi= new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		big = bi.createGraphics();
		t.start();


	}

//����ͼ��
	public void paint(Graphics g){
		int i,u,o;
		Graphics2D g2=(Graphics2D)g;	
		big.setColor(new Color(0x000000));
		big.fillRect(0,0,800,600);

		double[] xx1=new double[3];


		for(i=0;i<����*����*24;i++){
			xx1[0]=x[i];
			xx1[1]=y[i];
			xx1[2]=z[i];
			xx1=setMxy(xx1,mxy);
			x1[i]=xx1[0];
			y1[i]=xx1[1];
			z1[i]=xx1[2];
		}

		for(i=0;i<����*����*6;i++){
			int[] lx1=new int[4];
			int[] ly1=new int[4];
			int[] lz1=new int[4];
			for(u=0;u<4;u++){
				lz1[u]=(int)(z1[xyz[i][u]])-�Ӿ�;
				lx1[u]=(int)(x1[xyz[i][u]])*400/-lz1[u]+400;
				ly1[u]=(int)(y1[xyz[i][u]])*400/-lz1[u]+300;
			}
			big.setColor(new Color(colors[i/����/����]));
			if(getabc(lx1[0],ly1[0],lx1[1],ly1[1],lx1[3],ly1[3])){big.fillPolygon(lx1,ly1,4);}
		}
		g2.drawImage(bi,0,0,null);
	}	

	public boolean get��(int i){
		if(ȡ��==0){
			return ((x[xyz[i][0]]>�κ�-2 && x[xyz[i][0]]<�κ�+2)||(x[xyz[i][1]]>�κ�-2 && x[xyz[i][1]]<�κ�+2)  || (x[xyz[i][2]]>�κ�-2 && x[xyz[i][2]]<�κ�+2));
		}else if(ȡ��==1){
			return ((y[xyz[i][0]]>�κ�-2 && y[xyz[i][0]]<�κ�+2) || (y[xyz[i][1]]>�κ�-2 && y[xyz[i][1]]<�κ�+2)  || (y[xyz[i][2]]>�κ�-2 && y[xyz[i][2]]<�κ�+2));
		}else{
			return ((z[xyz[i][0]]>�κ�-2 && z[xyz[i][0]]<�κ�+2) || (z[xyz[i][1]]>�κ�-2 && z[xyz[i][1]]<�κ�+2)  || (z[xyz[i][2]]>�κ�-2 && z[xyz[i][2]]<�κ�+2));
		}
	}

//������ת
	public double[] setMxy(double[] l,double[][] m){
		double xx2[]={0,0,0};
		for(int u=0;u<3;u++){
			for(int o=0;o<3;o++){
				xx2[u]+=l[o]*m[o][u];
			}
		}
		return xx2;
	}

//����˷�
	public double[][] getMxy(double[][] xx,double[][] yy){ 
		int i=0,u=0,o=0;
		double xx1[][]={{0,0,0},{0,0,0},{0,0,0}};
		for(i=0;i<3;i++){
			for(u=0;u<3;u++){
				for(o=0;o<3;o++){
					xx1[i][u]+=xx[i][o]*yy[o][u];
				}

			}
		}
		return xx1;
	}


//�ж��Ƿ���˳ʱ�뷽������
	public boolean  getabc(double ax,double ay,double bx,double by,double cx,double cy){  
		double cax=cx-ax;
		double cay=cy-ay;
		double bcx=bx-cx;
		double bcy=by-cy;
		return cax*bcy>cay*bcx;
	}

	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}

//��갴��ʱ�Ķ���
	public void mousePressed(MouseEvent e){
		
		if(e.getButton()==1){
				����=getf(e.getX(),e.getY());
					�����[0]=e.getX();
					�����[1]=e.getY();
		}else if(e.getButton()==3){
			����ƶ�[0]=e.getX();
			����ƶ�[1]=e.getY();
		}
	}

	public double ȡ��(int n,double[] o){
		double li=o[xyz[n][0]];
		if(li>0){
			for(int i=1;i<4;i++){li=(li>o[xyz[n][i]])?li:o[xyz[n][i]];}
		}else{
			for(int i=1;i<4;i++){li=(li<o[xyz[n][i]])?li:o[xyz[n][i]];}
		}
		return li;
	}


//���ſ�ʱ�Ķ���
	public void mouseReleased(MouseEvent e){



		if(e.getButton()==1 && time==0 && ����!=-1){

			double[] ��=new double[2];
			�����[0]=e.getX()-�����[0];
			�����[1]=e.getY()-�����[1];
if(Math.abs(�����[0])>Math.abs(�����[1])){�����[1]=0;}else{�����[0]=0;}
			double cos1=Math.cos(2*Math.PI/180);
			double sin1=Math.sin(2*Math.PI/180);


			if( (z[xyz[����][0]]>z[xyz[����][2]]-10 &&z[xyz[����][0]]<z[xyz[����][1]]+10) && (z[xyz[����][1]]>z[xyz[����][2]]-10 &&z[xyz[����][1]]<z[xyz[����][2]]+10) ){
				double hu=Math.atan2(mxy[0][1],mxy[0][0]);
				
				��[0]=�����[0]*Math.cos(hu)+�����[1]*Math.sin(hu);
				��[1]=�����[1]*Math.cos(hu)-�����[0]*Math.sin(hu);

				if(Math.abs(��[0])>Math.abs(��[1])){
					int l=(��[0]>0)?-1:1;
					if(z[xyz[����][0]]<0){l*=-1;}
					double[][] anou={{cos1,0,sin1*l},{0,1,0},{-sin1*l,0,cos1}};nou=anou;
					�κ�=ȡ��(����,y);
					ȡ��=1;
				}else{
					int l=(��[1]>0)?-1:1;
					double[][] anou={{1,0,0},{0,cos1,sin1*l},{0,-sin1*l,cos1}};nou=anou;
					�κ�=ȡ��(����,x);
					ȡ��=0;
				}

			}else if( (x[xyz[����][0]]>x[xyz[����][2]]-10 &&x[xyz[����][0]]<x[xyz[����][1]]+10) && (x[xyz[����][1]]>x[xyz[����][2]]-10 &&x[xyz[����][1]]<x[xyz[����][2]]+10) ){
				double hu=Math.atan2(mxy[2][1],mxy[2][0]);
				��[0]=�����[0]*Math.cos(hu)+�����[1]*Math.sin(hu);
				��[1]=�����[1]*Math.cos(hu)-�����[0]*Math.sin(hu);
				if(Math.abs(��[0])>Math.abs(��[1])){
					int l=(��[0]>0)?-1:1;
					if(x[xyz[����][0]]>0){l*=-1;}
					double[][] anou={{cos1,0,sin1*l},{0,1,0},{-sin1*l,0,cos1}};nou=anou;
					�κ�=ȡ��(����,y);
					ȡ��=1;
				}else{
					int l=(��[1]>0)?-1:1;
					double[][] anou={{cos1,sin1*l,0},{-sin1*l,cos1,0},{0,0,1}};nou=anou;
					�κ�=ȡ��(����,z);
					ȡ��=2;
				}
			}else{

				double hu=Math.atan2(mxy[0][1],mxy[0][0]);
				��[0]=�����[0]*Math.cos(hu)+�����[1]*Math.sin(hu);
				��[1]=�����[1]*Math.cos(hu)-�����[0]*Math.sin(hu);
				if(Math.abs(��[0])>Math.abs(��[1])){

					int l=(��[1]>0)?-1:1;
					System.out.println(y[xyz[����][0]]);
					if(y[xyz[����][0]]<0){l*=-1;}
					double[][] anou={{cos1,sin1*l,0},{-sin1*l,cos1,0},{0,0,1}};nou=anou;
					�κ�=ȡ��(����,z);
					ȡ��=2;
				}else{

					int l=(��[1]>0)?-1:1;
					double[][] anou={{1,0,0},{0,cos1,sin1*l},{0,-sin1*l,cos1}};nou=anou;
					�κ�=ȡ��(����,x);
					ȡ��=0;
				}	


			}



time=45;
//System.out.println(hu*180/Math.PI);


			����=-1;
		}
		����ƶ�[0]=����ƶ�[1]=-1;
		
		



	}

//��갴���ƶ�ʱ�Ķ���
	public void mouseDragged(MouseEvent e){
		if(����ƶ�[0]!=-1&&����ƶ�[1]!=-1){
			double oix=(����ƶ�[0]-e.getX())*Math.PI/720;
			double oiy=(����ƶ�[1]-e.getY())*Math.PI/720;
		
			double[][] fff={{1,0,0},{0,1,0},{0,0,1}};
			fff[0][0]=fff[2][2]=Math.cos(oix);
			fff[0][2]=Math.sin(oix);
			fff[2][0]=-fff[0][2];

			double[][] fff1={{1,0,0},{0,1,0},{0,0,1}};
			fff1[1][1]=fff1[2][2]=Math.cos(oiy);
			fff1[1][2]=Math.sin(oiy);
			fff1[2][1]=-fff1[1][2];

			mxy=getMxy(mxy,fff);
			mxy=getMxy(mxy,fff1);
			����ƶ�[0]=e.getX();
			����ƶ�[1]=e.getY();
		}
	}
	public void mouseMoved(MouseEvent e){}


//�ж����������ĸ�������	
	public int getf(int xx,int yy){
		boolean[] t1=new boolean[4];
		double x4,y4,z4,x5,y5,z5;

		for(int i=0;i<����*����*6;i++){
			for(int j=0;j<4;j++){
				int l=(j==3)?0:j+1;
				z4=z1[xyz[i][j]]-�Ӿ�;
				x4=x1[xyz[i][j]]*400/-z4+400;
				y4=y1[xyz[i][j]]*400/-z4+300;
				z5=z1[xyz[i][l]]-�Ӿ�;
				x5=x1[xyz[i][l]]*400/-z5+400;
				y5=y1[xyz[i][l]]*400/-z5+300;
				t1[j]=getabc(x4,y4,x5,y5,xx,yy);
			}
			if(t1[0]&&t1[1]&&t1[2]&&t1[3]){
				return i;
			}			
		}
		return -1;
	}
}