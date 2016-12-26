package haha_player;
import javax.media.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
	public class s_player extends JFrame implements ActionListener, MouseListener{
		private static Player player = null;
		private static File audioFile;
		private static song_list list;
		private JList fileList = new JList();
		private JScrollPane scrollPane; 
		private RecButton b1,b2,b3,b4,b21,b22,b23;
		private JTextField rate_txt;
		int rate=120; 
		private Container c;
		private DefaultListModel listModel=new DefaultListModel(); 
		private static DrawPanel draw_rate;
		private int from= 20,to;
		private int[] height=new int[20];
		private int color;
		private String currentDirectory, filename,filepath;
		public s_player()
		{
			list_ini();
			addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					if (player != null)
						player.stop();
					dispose();
				}});
		setTitle("LELE Player");
		setSize(275,535);
		setBackground(Color.black);
		setLocationRelativeTo(null);
		try
    	{
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    	}catch(Exception e){e.printStackTrace();}
    	   
		c= getContentPane();
		c.setLayout(new BorderLayout());
		
		JMenu menu1=new JMenu("文件");
		menu1.setForeground(Color.BLUE);
		menu1.setBackground(Color.gray);
		JMenuItem item=new JMenuItem("加载文件");
		item.addActionListener(this);
		menu1.add(item);
		menu1.addSeparator();
		item=new JMenuItem("退出");
		item.addActionListener(this);
		menu1.add(item);	
		
		JMenu menu2=new JMenu("控制");
		menu2.setForeground(Color.BLUE);
		menu2.setBackground(Color.gray);
		item=new JMenuItem("start");
		item.addActionListener(this);
		menu2.add(item);
		menu2.addSeparator();
		item=new JMenuItem("stop");
		item.addActionListener(this);
		menu2.add(item);
		
		JMenuBar mb=new JMenuBar();
		mb.setLayout(new FlowLayout(5));
		mb.add(menu1);
		mb.add(menu2);
		setJMenuBar(mb);
		
	  	JPanel panel1=new JPanel();
	  	panel1.setBackground(Color.black);
	  	panel1.setLayout(null);
	  	c.add(panel1);

		JPanel listPanel=new JPanel();
		listPanel.setLayout(new BorderLayout());

		b1=new RecButton("start");
		b2=new RecButton("stop");
		b3=new RecButton("<<");
		b4=new RecButton(">>");
		b21=new RecButton("set Rate");
		b22=new RecButton("<");
		b23=new RecButton(">");
		
		b1.addActionListener(this);
		b2.addActionListener(this);	
		b3.addActionListener(this);
		b4.addActionListener(this);
		b21.addActionListener(this);
		b22.addActionListener(this);
		b23.addActionListener(this);
		
		rate_txt=new JTextField(5);
		rate_txt.setText("120");
		
		b1.setForeground(Color.green);
		b2.setForeground(Color.green);
		b3.setForeground(Color.green);
		b4.setForeground(Color.green);	
		b21.setForeground(Color.green);	
		b22.setForeground(Color.green);
		b23.setForeground(Color.green);
		
		c.setBackground(Color.black);
		listModel.addElement("不想长大.mp3");
		listModel.addElement("后来.mp3");
		fileList=new JList(listModel);
		fileList.setForeground(Color.green);
		fileList.setBackground(Color.black);
		fileList.addMouseListener(this);
		listPanel.add(fileList,  BorderLayout.CENTER);
		fileList.setSelectedIndex(0);

        draw_rate=new DrawPanel();
		scrollPane=new JScrollPane(fileList);
		
		draw_rate.setBounds(new Rectangle(5,0,250,130));
		draw_rate.setBorder(BorderFactory.createBevelBorder(1));
		panel1.add(draw_rate);
		
		b1.setBounds(new Rectangle(25,140,60,20));
		b2.setBounds(new Rectangle(90,140,50,20));
		b3.setBounds(new Rectangle(145,140,45,20));
		b4.setBounds(new Rectangle(195,140,45,20));
		b21.setBounds(new Rectangle(25,165,70,20));
		b22.setBounds(new Rectangle(110,165,35,20));
		rate_txt.setBounds(new Rectangle(150,165,50,20));
		b23.setBounds(new Rectangle(205,165,35,20));
		
		panel1.add(b1);	panel1.add(b2);	panel1.add(b3);	panel1.add(b4);	
		panel1.add(b21);	panel1.add(b22);	panel1.add(rate_txt);panel1.add(b23);	
		scrollPane.setBounds(new Rectangle(5,190,250,250));
		panel1.add(scrollPane);
		setVisible(true);
	    draw_rate.ini();
		}		
		public static URL tourl(File file) throws IOException,NoPlayerException,CannotRealizeException
		{
		return file.toURL();
		}				
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand()=="start")
			{try{
				if(player!=null)
				{player.start();
				draw_rate.start_changing();
				b1.setLabel("pause");}
				else
				{String song_name=fileList.getSelectedValue()+"";
				String song_path=list.geturl(song_name);
				audioFile= new File(song_path);
				System.out.println(song_path);
				player=Manager.createRealizedPlayer(tourl(audioFile));
				player.start();
				draw_rate.start_changing();
				b1.setLabel("pause");
				}
			}catch(Exception e1){System.out.println("error1");}}
			
			if(e.getActionCommand()=="pause")
			{try{
				player.stop();b1.setLabel("start");draw_rate.stop_changing();
			}catch(Exception e1){System.out.println("error1");}}
			
			if(e.getActionCommand()=="stop")
			{try{
				player.close();		draw_rate.stop_changing();	b1.setLabel("start ");	
			}catch(Exception e1){System.out.println("error2");}
			}
			
			if(e.getActionCommand()=="set Rate")
			{rate=Integer.parseInt(rate_txt.getText());
			setrate(rate);
			}
			if(e.getActionCommand()=="<<")
			{try{
				player.close();draw_rate.stop_changing();
				int index=fileList.getSelectedIndex();
				if(index==0)
					index=listModel.getSize();
				fileList.setSelectedIndex(index-1);						
				String song_name=fileList.getSelectedValue()+"";
				String song_path=list.geturl(song_name);
				audioFile= new File(song_path);
				player=Manager.createRealizedPlayer(tourl(audioFile));
				player.start();b1.setLabel("pause");
				draw_rate.start_changing();
				}catch(Exception e1){System.out.println("error2");}
			}
			
			if(e.getActionCommand()==">>")
			{
				if (player != null)
					player.close();
				try {
					int index=fileList.getSelectedIndex();
					if(index==listModel.getSize()-1)
						index=-1;
						fileList.setSelectedIndex(index+1);
						String song_name=fileList.getSelectedValue()+"";
						String song_path=list.geturl(song_name);
					audioFile= new File(song_path);
					player=Manager.createRealizedPlayer(tourl(audioFile));
					player.start();b1.setLabel("pause");
					draw_rate.start_changing();
				} catch (Exception e2) {System.out.println(e2);	return;}
			}
			if(e.getActionCommand()=="<")
			{rate=Integer.parseInt(rate_txt.getText());
			rate-=1;
			rate_txt.setText(rate+"");
			setrate(rate);
			}
			if(e.getActionCommand()==">")
			{rate=Integer.parseInt(rate_txt.getText());
			rate+=1;
			rate_txt.setText(rate+"");
			setrate(rate);
			}
			
			if(e.getActionCommand()=="加载文件")
			{
				if(player!=null)
					{player.close();
					draw_rate.stop_changing();	
					b1.setLabel("start ");}
				FileDialog fd = new FileDialog(this, "打开媒体文件", FileDialog.LOAD);
				fd.setDirectory(currentDirectory);
				fd.show();
				if (fd.getFile() == null)
					return;
				filename = fd.getFile();
				currentDirectory = fd.getDirectory();
				filepath = currentDirectory + filename;
				System.out.println(filepath);
				if(!list.isfull()) 
					list.add(filename,filepath);
				listModel.addElement(filename);
				
				try{audioFile= new File(filepath);
				player=Manager.createRealizedPlayer(tourl(audioFile));
				draw_rate.start_changing();
				b1.setLabel("pause");fileList.setSelectedIndex(listModel.getSize()-1);
				}catch(Exception e1){System.out.println("error1");}
			}
			if(e.getActionCommand()=="退出")
			{
				//player.getStartLatency();
				System.out.println(player.getDuration().getSeconds());
				player.setMediaTime(new Time(23486627));
				/*dispose();
				return;*/
			}
		}		
		public void list_ini()
		{
			list=new song_list();
			String temp=new String();
			temp="music/不想长大.mp3";
			if(!list.isfull()) 
				list.add("不想长大.mp3",temp);
			temp="music/后来.mp3";
			if(!list.isfull()) 
				list.add("后来.mp3",temp);
		}
		
		public void mouseClicked(MouseEvent e) { 
			if (e.getClickCount() == 2) { 
			int index = fileList.locationToIndex(e.getPoint()); 
			String song_name=fileList.getSelectedValue()+"";
			String song_path=list.geturl(song_name);
			try{
				if (player != null)
					{player.close();draw_rate.stop_changing();}
				try {
					audioFile=new File(song_path);
					player = Manager.createPlayer(new MediaLocator(tourl(audioFile)));					
				} catch (java.io.IOException e2) {
					System.out.println(e2);
					return;
				} catch (NoPlayerException e2) {
					System.out.println("不能找到播放器.");
					return;
				}
				if (player == null) {
					System.out.println("无法创建播放器.");
					return;
				}
				player.start();b1.setLabel("pause");draw_rate.start_changing();
				}catch(Exception e1){System.out.println("error2");}
				
			} 
			} 
		public void setrate(int r)
		{player.setRate((float)(r/120.0));}
		public void mousePressed(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}		
			
			class DrawPanel extends JPanel implements Runnable,MouseListener{ 
				double c; 
				Thread thread; 
				boolean isStopped; 
				int inner_blank_height=9;
				public DrawPanel() {  
					c=0.0;
					this.addMouseListener(this); 
					(thread=new Thread(this)).start(); 
				} 
				public void paint(Graphics g) { 
					g.clearRect(0,0,getSize().width,getSize().height); //清空面板

		for(int x=0;x<to-20;x+=13)
		{			    	
			switch(color){
				case 0:g.setColor(Color.LIGHT_GRAY);
		    	        break;
		    	case 1:g.setColor(Color.cyan);
		                 break;        
		    	case 2:g.setColor(Color.yellow);
		                 break;        
		    	case 3:g.setColor(Color.blue);
		                 break;       
		    	case 4:g.setColor(Color.red);
		                 break; 
		    	case 5:g.setColor(Color.magenta);
	            	break; 
		    	case 6:g.setColor(Color.ORANGE);
		    		break; 
		    	case 7:g.setColor(Color.PINK);
	            	break; 
		    	case 8:g.setColor(Color.green);
	            	break; 
		    	}
				for (int y=100 ; y >100-height[x/13] ; y-=7)
				{g.fillRect(from+x, 30+y, 10, 5); }
				g.setColor(Color.white);
				g.fillRect(from+x, 100-height[x/13]+inner_blank_height, 10, 5); 
				g.setColor(Color.BLUE);
				g.fillRect(from+x+1, 100-height[x/13]+1+inner_blank_height, 8, 3);
						}
				} 
				public void run() { 
					while(true){
						repaint(); 
						if(!isStopped) 
						{
							to=this.getWidth()-20;
							for(int i=0;i<20;i++)
								height[i]=(int)(Math.random()*100);
								color=(int)(Math.random()*9);
								inner_blank_height=(int)(Math.random()*15+10);
						}
						try{

							thread.sleep((int)(500*120/rate)); 
						}catch (InterruptedException ex){ex.printStackTrace();} 
					} 
				} 
				public void mouseClicked(MouseEvent e){} 
				public void mousePressed(MouseEvent e) {} 
				public void mouseReleased(MouseEvent e) { 
					isStopped=!isStopped;
				}
				public void stop_changing()
				{
					isStopped=true;
				}
				public void start_changing()
				{
					isStopped=false;
				}
				public void ini()
				{
					to=this.getWidth()-20;
					for(int i=0;i<20;i++)
						height[i]=(int)(Math.random()*100);
					color=(int)(Math.random()*9);
					inner_blank_height=(int)(Math.random()*15+10);
					repaint(); 
					isStopped=true;
				}
				public void mouseEntered(MouseEvent e){} 
				public void mouseExited(MouseEvent e){}
				
			}
				
		public static void main(String args[])
		{ 
			new s_player();
		}
}
