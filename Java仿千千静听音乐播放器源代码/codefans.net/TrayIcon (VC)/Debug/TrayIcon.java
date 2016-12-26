import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
//download:http://www.codefans.net
public class TrayIcon{
    private JPopupMenu rightPopup;
    private JWindow popupWindow;
    private JFrame mainFrame;
    private static TrayIcon staticInstance;

	static{
        System.loadLibrary("TrayIcon");
    }

    private TrayIcon(){}
    public static TrayIcon getDefaultTrayIcon(){
        if(staticInstance == null){
            staticInstance = new TrayIcon();
            staticInstance.initTrayIcon(staticInstance,"JMPlayer",1000);
        }
        return staticInstance;
    }

    private void setJFrame(final JFrame frame){
        this.mainFrame=frame;
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeTrayIcon();
            }
            public void windowIconified(WindowEvent e) {
                frame.setVisible(false);
            }
            public void windowDeactivated(WindowEvent e) {
                if(rightPopup!=null)
                    rightPopup.setVisible(false);
            }
        });
    }

    public void setTrayIconJPopupMenu(JPopupMenu popup){
        this.rightPopup=popup;
        rightPopup.show(getPopupWindow(),0,0);
        rightPopup.setVisible(false);
    }

    private void processTrayIconMouseEvent(int button,int x,int y){
        if(button == -1)return;
        if(button == 0 ||button ==5 ||button == 7){
            if(rightPopup != null && button == 5){
                rightPopup.show(getPopupWindow(),x,y);
                rightPopup.requestFocus();
                rightPopup.repaint();
            }
        }
		else{
            if(button == 6){
                if(rightPopup!=null)rightPopup.setVisible(false);
                if(!mainFrame.isVisible()){
                    mainFrame.setVisible(true);
                    mainFrame.toFront();
                    mainFrame.requestFocus();
                    if(mainFrame.isVisible()){
                        if(mainFrame.getExtendedState()==JFrame.ICONIFIED){
                            mainFrame.setExtendedState(JFrame.NORMAL);
                        }
                    }
                }
				else{
                    mainFrame.setVisible(false);
                }
            }
        }
    }

    private Window getPopupWindow(){
        if(popupWindow == null){
            popupWindow = new JWindow();
            popupWindow.setBounds(0,0,1,1);
            popupWindow.show();
        }
        return popupWindow;
    }
    private boolean isFirst=true;

    public void initTrayIcon(String tip,File iconfile,JFrame frame,JPopupMenu popup){
        if(isFirst){
            if(iconfile!=null){
                ImageIcon icon=new ImageIcon(iconfile.getAbsolutePath());
                int w=icon.getIconWidth(),h=icon.getIconHeight();
                Image image=icon.getImage();
                try {
                    int[] pixels = new int[w * h];
                    PixelGrabber pg = new PixelGrabber(image, 0, 0, w, h, pixels, 0, w);
                    pg.grabPixels();
                    if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
                        throw new Exception("Error loading icon image");
                    }
                    setTrayIconData(w, h, pixels);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if(tip!=null){
                setTrayIconTip(tip);
            }
            setTrayIconJPopupMenu(popup);
            setJFrame(frame);
            isFirst=false;
        }
    }
    private native void initTrayIcon(TrayIcon instance,String appName,int id);
    private native void setTrayIconTip(String tip);
    private native void setTrayIconData(int w, int h, int pixels[]);
    public native void showTrayIcon();
    public native void closeTrayIcon();
}