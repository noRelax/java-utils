import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import static java.awt.RenderingHints.*;
import javax.swing.Timer;
public class m extends Applet implements ActionListener,MouseListener,MouseMotionListener{

	private int[][] xyz; //4个坐标构成的面

	private double[] x,y,z;  //原始点

	private double[] x1,y1,z1;  // 旋转后的点

	private double[][] mxy={{1,0,0},{0,1,0},{0,0,1}}; //旋转矩阵

	private int time=0;
	
	private int[] colors={0x70e33e,0x65f0e4,0xf20f2f,0xffff00,0x454545,0xaaaaaa};  //六面色

	private	int 数量=4; //在此设置是（4*4）的魔方

	private int 视距=800;  //越大越远

	private int 鼠标点=-1;  //装的是点击后得到的方块在xyz里的索引

	private int[] 鼠标点击={-1,-1};  //点击时的鼠标坐标

	private int[] 鼠标移动={-1,-1};

	private double[][] nou;//旋转矩阵

	private double 段号;
	private int 取轴;

	private BufferedImage bi;
	private Graphics2D big;

//定时器
	public void actionPerformed(ActionEvent e){
		if(time>0){
			for(int i=0;i<数量*数量*6;i++){

				if(get轴(i)){
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
//初始化数据
	public void init(){

		double[] 临时点=new double[3];

		int i1,i2,i3,i4;

		int 直径=100;
		int 边距=8;
		
		int 中心点=((直径+边距)*数量-边距)/2;

		x=new double[数量*数量*数量<<3];
		y=new double[数量*数量*数量<<3];
		z=new double[数量*数量*数量<<3];
		x1=new double[数量*数量*数量<<3];
		y1=new double[数量*数量*数量<<3];
		z1=new double[数量*数量*数量<<3];
		xyz=new int[数量*数量*数量<<1][4];
		
		double[][] X轴矩阵={{1,0,0},{0,0,1},{0,-1,0}};
		double[][] Y轴矩阵={{0,0,1},{0,1,0},{-1,0,0}};

		for(i1=0;i1<数量;i1++){
			i4=i1<<2;
			x[i4+3]=x[i4]=i1*(直径+边距)-中心点;
			x[i4+1]=x[i4+2]=x[i4]+直径;

			y[i4+1]=y[i4]=中心点;
			y[i4+2]=y[i4+3]=y[i4]-直径;
			z[i4]=z[i4+1]=z[i4+2]=z[i4+3]=中心点;
		}
		for(i2=1;i2<数量;i2++){
			for(i1=0;i1<数量*4;i1++){
				x[i2*数量*4+i1]=x[i1];
				z[i2*数量*4+i1]=z[i1];
				y[i2*数量*4+i1]=y[i1]-i2*(直径+边距);
			}
		}
		for(i2=0;i2<3;i2++){
			for(i1=0;i1<数量*数量*4;i1++){
				临时点[0]=x[i2*数量*数量*4+i1];
				临时点[1]=y[i2*数量*数量*4+i1];
				临时点[2]=z[i2*数量*数量*4+i1];	
				临时点=setMxy(临时点,X轴矩阵);	
				x[(i2+1)*数量*数量*4+i1]=临时点[0];
				y[(i2+1)*数量*数量*4+i1]=临时点[1];
				z[(i2+1)*数量*数量*4+i1]=临时点[2];	
			}
		}
		for(i1=0;i1<数量*数量*4;i1++){
				临时点[0]=x[i1];
				临时点[1]=y[i1];
				临时点[2]=z[i1];	
				double[] sy=setMxy(临时点,Y轴矩阵);
				x[数量*数量*16+i1]=sy[0];
				y[数量*数量*16+i1]=sy[1];
				z[数量*数量*16+i1]=sy[2];
				double[][] kj=getMxy(getMxy(Y轴矩阵,Y轴矩阵),Y轴矩阵);
				sy=setMxy(临时点,kj);
				x[数量*数量*20+i1]=sy[0];
				y[数量*数量*20+i1]=sy[1];
				z[数量*数量*20+i1]=sy[2];	
		}

		for(i1=0;i1<数量*数量*6;i1++){
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

//生成图像
	public void paint(Graphics g){
		int i,u,o;
		Graphics2D g2=(Graphics2D)g;	
		big.setColor(new Color(0x000000));
		big.fillRect(0,0,800,600);

		double[] xx1=new double[3];


		for(i=0;i<数量*数量*24;i++){
			xx1[0]=x[i];
			xx1[1]=y[i];
			xx1[2]=z[i];
			xx1=setMxy(xx1,mxy);
			x1[i]=xx1[0];
			y1[i]=xx1[1];
			z1[i]=xx1[2];
		}

		for(i=0;i<数量*数量*6;i++){
			int[] lx1=new int[4];
			int[] ly1=new int[4];
			int[] lz1=new int[4];
			for(u=0;u<4;u++){
				lz1[u]=(int)(z1[xyz[i][u]])-视距;
				lx1[u]=(int)(x1[xyz[i][u]])*400/-lz1[u]+400;
				ly1[u]=(int)(y1[xyz[i][u]])*400/-lz1[u]+300;
			}
			big.setColor(new Color(colors[i/数量/数量]));
			if(getabc(lx1[0],ly1[0],lx1[1],ly1[1],lx1[3],ly1[3])){big.fillPolygon(lx1,ly1,4);}
		}
		g2.drawImage(bi,0,0,null);
	}	

	public boolean get轴(int i){
		if(取轴==0){
			return ((x[xyz[i][0]]>段号-2 && x[xyz[i][0]]<段号+2)||(x[xyz[i][1]]>段号-2 && x[xyz[i][1]]<段号+2)  || (x[xyz[i][2]]>段号-2 && x[xyz[i][2]]<段号+2));
		}else if(取轴==1){
			return ((y[xyz[i][0]]>段号-2 && y[xyz[i][0]]<段号+2) || (y[xyz[i][1]]>段号-2 && y[xyz[i][1]]<段号+2)  || (y[xyz[i][2]]>段号-2 && y[xyz[i][2]]<段号+2));
		}else{
			return ((z[xyz[i][0]]>段号-2 && z[xyz[i][0]]<段号+2) || (z[xyz[i][1]]>段号-2 && z[xyz[i][1]]<段号+2)  || (z[xyz[i][2]]>段号-2 && z[xyz[i][2]]<段号+2));
		}
	}

//坐标旋转
	public double[] setMxy(double[] l,double[][] m){
		double xx2[]={0,0,0};
		for(int u=0;u<3;u++){
			for(int o=0;o<3;o++){
				xx2[u]+=l[o]*m[o][u];
			}
		}
		return xx2;
	}

//矩阵乘法
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


//判断是否是顺时针方向排列
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

//鼠标按下时的动作
	public void mousePressed(MouseEvent e){
		
		if(e.getButton()==1){
				鼠标点=getf(e.getX(),e.getY());
					鼠标点击[0]=e.getX();
					鼠标点击[1]=e.getY();
		}else if(e.getButton()==3){
			鼠标移动[0]=e.getX();
			鼠标移动[1]=e.getY();
		}
	}

	public double 取点(int n,double[] o){
		double li=o[xyz[n][0]];
		if(li>0){
			for(int i=1;i<4;i++){li=(li>o[xyz[n][i]])?li:o[xyz[n][i]];}
		}else{
			for(int i=1;i<4;i++){li=(li<o[xyz[n][i]])?li:o[xyz[n][i]];}
		}
		return li;
	}


//鼠标放开时的动作
	public void mouseReleased(MouseEvent e){



		if(e.getButton()==1 && time==0 && 鼠标点!=-1){

			double[] 点=new double[2];
			鼠标点击[0]=e.getX()-鼠标点击[0];
			鼠标点击[1]=e.getY()-鼠标点击[1];
if(Math.abs(鼠标点击[0])>Math.abs(鼠标点击[1])){鼠标点击[1]=0;}else{鼠标点击[0]=0;}
			double cos1=Math.cos(2*Math.PI/180);
			double sin1=Math.sin(2*Math.PI/180);


			if( (z[xyz[鼠标点][0]]>z[xyz[鼠标点][2]]-10 &&z[xyz[鼠标点][0]]<z[xyz[鼠标点][1]]+10) && (z[xyz[鼠标点][1]]>z[xyz[鼠标点][2]]-10 &&z[xyz[鼠标点][1]]<z[xyz[鼠标点][2]]+10) ){
				double hu=Math.atan2(mxy[0][1],mxy[0][0]);
				
				点[0]=鼠标点击[0]*Math.cos(hu)+鼠标点击[1]*Math.sin(hu);
				点[1]=鼠标点击[1]*Math.cos(hu)-鼠标点击[0]*Math.sin(hu);

				if(Math.abs(点[0])>Math.abs(点[1])){
					int l=(点[0]>0)?-1:1;
					if(z[xyz[鼠标点][0]]<0){l*=-1;}
					double[][] anou={{cos1,0,sin1*l},{0,1,0},{-sin1*l,0,cos1}};nou=anou;
					段号=取点(鼠标点,y);
					取轴=1;
				}else{
					int l=(点[1]>0)?-1:1;
					double[][] anou={{1,0,0},{0,cos1,sin1*l},{0,-sin1*l,cos1}};nou=anou;
					段号=取点(鼠标点,x);
					取轴=0;
				}

			}else if( (x[xyz[鼠标点][0]]>x[xyz[鼠标点][2]]-10 &&x[xyz[鼠标点][0]]<x[xyz[鼠标点][1]]+10) && (x[xyz[鼠标点][1]]>x[xyz[鼠标点][2]]-10 &&x[xyz[鼠标点][1]]<x[xyz[鼠标点][2]]+10) ){
				double hu=Math.atan2(mxy[2][1],mxy[2][0]);
				点[0]=鼠标点击[0]*Math.cos(hu)+鼠标点击[1]*Math.sin(hu);
				点[1]=鼠标点击[1]*Math.cos(hu)-鼠标点击[0]*Math.sin(hu);
				if(Math.abs(点[0])>Math.abs(点[1])){
					int l=(点[0]>0)?-1:1;
					if(x[xyz[鼠标点][0]]>0){l*=-1;}
					double[][] anou={{cos1,0,sin1*l},{0,1,0},{-sin1*l,0,cos1}};nou=anou;
					段号=取点(鼠标点,y);
					取轴=1;
				}else{
					int l=(点[1]>0)?-1:1;
					double[][] anou={{cos1,sin1*l,0},{-sin1*l,cos1,0},{0,0,1}};nou=anou;
					段号=取点(鼠标点,z);
					取轴=2;
				}
			}else{

				double hu=Math.atan2(mxy[0][1],mxy[0][0]);
				点[0]=鼠标点击[0]*Math.cos(hu)+鼠标点击[1]*Math.sin(hu);
				点[1]=鼠标点击[1]*Math.cos(hu)-鼠标点击[0]*Math.sin(hu);
				if(Math.abs(点[0])>Math.abs(点[1])){

					int l=(点[1]>0)?-1:1;
					System.out.println(y[xyz[鼠标点][0]]);
					if(y[xyz[鼠标点][0]]<0){l*=-1;}
					double[][] anou={{cos1,sin1*l,0},{-sin1*l,cos1,0},{0,0,1}};nou=anou;
					段号=取点(鼠标点,z);
					取轴=2;
				}else{

					int l=(点[1]>0)?-1:1;
					double[][] anou={{1,0,0},{0,cos1,sin1*l},{0,-sin1*l,cos1}};nou=anou;
					段号=取点(鼠标点,x);
					取轴=0;
				}	


			}



time=45;
//System.out.println(hu*180/Math.PI);


			鼠标点=-1;
		}
		鼠标移动[0]=鼠标移动[1]=-1;
		
		



	}

//鼠标按下移动时的动作
	public void mouseDragged(MouseEvent e){
		if(鼠标移动[0]!=-1&&鼠标移动[1]!=-1){
			double oix=(鼠标移动[0]-e.getX())*Math.PI/720;
			double oiy=(鼠标移动[1]-e.getY())*Math.PI/720;
		
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
			鼠标移动[0]=e.getX();
			鼠标移动[1]=e.getY();
		}
	}
	public void mouseMoved(MouseEvent e){}


//判断鼠标点是在哪个方块上	
	public int getf(int xx,int yy){
		boolean[] t1=new boolean[4];
		double x4,y4,z4,x5,y5,z5;

		for(int i=0;i<数量*数量*6;i++){
			for(int j=0;j<4;j++){
				int l=(j==3)?0:j+1;
				z4=z1[xyz[i][j]]-视距;
				x4=x1[xyz[i][j]]*400/-z4+400;
				y4=y1[xyz[i][j]]*400/-z4+300;
				z5=z1[xyz[i][l]]-视距;
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