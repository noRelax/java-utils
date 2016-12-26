package eb.cstop.swt;
//download:http://www.codefans.net
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class TrayIcon{

	private static TrayIcon staticInstance = null;
	private String tooltip = "TrayIcon";
	private ImageIcon image = null;
	private int status = 0;
	private ArrayList mouseListeners = new ArrayList();
	private TrayIconEvent event = new TrayIconEvent();

	static{try{System.loadLibrary("TrayIcon");}catch(Exception e){e.printStackTrace();}}

	private TrayIcon(){}

	public void addTrayIconListener(TrayIconListener listener){
		mouseListeners.add(listener);
	}

	public void removeTrayIconListener(TrayIconListener listener){
		mouseListeners.remove(listener);
	}

	private void callDownListener(TrayIconEvent event){
		TrayIconListener listener = null;
		for(int i=0;i<mouseListeners.size();i++){
			listener = (TrayIconListener)mouseListeners.get(i);
			event.setSource(listener);
			listener.trayIconPressed(event);
		}
	}

	private void callUPListener(TrayIconEvent event){
		TrayIconListener listener = null;
		for(int i=0;i<mouseListeners.size();i++){
			listener = (TrayIconListener)mouseListeners.get(i);
			event.setSource(listener);
			listener.trayIconReleased(event);
		}
	}

	private void callClickListener(TrayIconEvent event){
		TrayIconListener listener = null;
		for(int i=0;i<mouseListeners.size();i++){
			listener = (TrayIconListener)mouseListeners.get(i);
			event.setSource(listener);
			listener.trayIconClicked(event);
		}
	}

	private void callDBLClickListener(TrayIconEvent event){
		TrayIconListener listener = null;
		for(int i=0;i<mouseListeners.size();i++){
			listener = (TrayIconListener)mouseListeners.get(i);
			event.setSource(listener);
			listener.trayIconDBLClicked(event);
		}
	}

	private void trayIconCallback(int button, int x, int y) {
		event.setX(x);
		event.setY(y);
		if(button == 7){
			event.setButton(1);
			this.callDBLClickListener(event);
		}
		else if(button == 2 || button == 4 || button == 6){
			if(button == 2)event.setButton(1);
			else if(button == 4)event.setButton(3);
			else if(button == 6)event.setButton(2);

			if(button == 2 && status == 1){
				this.callClickListener(event);
			}
			else if(button == 4 && status == 3){
				this.callClickListener(event);
			}
			else if(button == 6 && status == 5){
				this.callClickListener(event);
			}
			this.callUPListener(event);
		}
		else if(button == 1 || button == 3 || button == 5){
			if(button == 1)event.setButton(1);
			else if(button == 2)event.setButton(3);
			else if(button == 3)event.setButton(2);
			this.callDownListener(event);
		}
		status = button;
	}

	public static TrayIcon createTrayIcon() {
		if (staticInstance == null) {
			staticInstance = new TrayIcon();
			staticInstance.initTrayIcon(staticInstance, "TrayIcon", 1000);
		}
		return staticInstance;
	}

	public void setTrayIconToolTip(String tooltip){
		this.tooltip = tooltip;
		this.setTrayIconTip(tooltip);
	}

	public String getTrayIconToolTip(){
		return tooltip;
	}

	public void setImageIcon(ImageIcon image){
		if(image != null){
			int width = image.getIconWidth();
			int height = image.getIconHeight();
			int[] pixels = new int[width * height];
			try{
				PixelGrabber pixelGrabber = new PixelGrabber(image.getImage(), 0, 0, width, height, pixels, 0, width);
				pixelGrabber.grabPixels();
				if((pixelGrabber.getStatus() & ImageObserver.ABORT) != 0)throw new Exception("Load error image");
				this.setTrayIconData(width, height, pixels);
				this.image = image;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public ImageIcon getImageIcon(){
		return image;
	}

	private native void initTrayIcon(TrayIcon instance, String applicationName, int id);
	private native void setTrayIconTip(String tooltip);
	private native void setTrayIconData(int w, int h, int pixels[]);
	public native void show();
	public native void close();
}
