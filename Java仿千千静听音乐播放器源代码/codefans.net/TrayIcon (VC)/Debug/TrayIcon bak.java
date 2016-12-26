import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//download:http://www.codefans.net
public class TrayIcon implements WindowStateListener,Runnable {
	private JFrame frame;
	static{
		System.loadLibrary("TrayIcon");
	}
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize(320,240);
		frame.setVisible(true);
		TrayIcon trayIcon = new TrayIcon(frame);
	}
	public TrayIcon(JFrame frame){
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowStateListener(this);
		this.install();
		Thread thread = new Thread(this);
		thread.start();
	}

	public void showWindow(){
		frame.setExtendedState(JFrame.PROPERTIES);
		frame.requestFocus();
		frame.setVisible(true);
	}
	
	public native void install();
	public native void show();
	public native void hide();
	public native int getStatus();
	public native int setStatus(int status);

	public void windowStateChanged(WindowEvent e) {
		if(e.getNewState() == 1){
			this.setStatus(1);
			this.show();
			frame.setVisible(false);
		}
	}

	public void run(){
		try{
			while(true){
				if(this.getStatus() == 0 && !frame.isVisible())this.showWindow();
				Thread.sleep(50);
			}
		}
		catch(Exception e){}
	}
}