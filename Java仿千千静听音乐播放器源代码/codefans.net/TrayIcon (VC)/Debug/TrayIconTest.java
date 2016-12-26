import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

/**
 * <p>Title: TrayIcon</p>
 * <p>Description: TrayIconTest</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author <a href="mailto:sunkingxie@hotmail.com">SunKing</a>
 * @version 1.0
 * @download:http://www.codefans.net
 */

public class TrayIconTest extends JFrame{
    TrayIcon util = TrayIcon.getDefaultTrayIcon();
    private JPopupMenu popup = new JPopupMenu("Test PopupMenu");
    private JMenuItem item_SH = new JMenuItem();
    private JMenuItem item_Exit = new JMenuItem(new ExitAction());
    private JMenuBar bar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem item_New = new JMenuItem();
    private JScrollPane sp1 = new JScrollPane();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JEditorPane editor = new JEditorPane();
    private JLabel lbStatus = new JLabel();

    public TrayIconTest() {
        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        TrayIconTest trayIconTest1 = new TrayIconTest();
        setFrameAtScreenCenter(trayIconTest1,400,300);
        trayIconTest1.show();
    }
    public static void setFrameAtScreenCenter(Window frame,int width,int height){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - width)/2,(
                screenSize.height - height) / 2,width,height);
    }
    private void jbInit() throws Exception {
        this.setTitle("JMPlayer");
        this.getContentPane().setLayout(borderLayout1);
        item_SH.setText("Hide");
        item_SH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                item_SH_actionPerformed(e);
            }
        });
        popup.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                item_SH.setText(isVisible()?"Hide":"Show");
                item_SH.setMnemonic(isVisible()?'H':'S');
            }
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        menuFile.setText("File");
        item_New.setText("New");
        item_New.setEnabled(false);
        lbStatus.setBorder(BorderFactory.createLoweredBevelBorder());
        lbStatus.setText(" ");
        this.setJMenuBar(bar);
        popup.add(item_SH);
        popup.addSeparator();
        popup.add(item_Exit);
        bar.add(menuFile);
        menuFile.add(item_New);
        menuFile.addSeparator();
        menuFile.add(new ExitAction());
        this.getContentPane().add(sp1, BorderLayout.CENTER);
        this.getContentPane().add(lbStatus,  BorderLayout.SOUTH);
        sp1.getViewport().add(editor, null);

        try {
            editor.setContentType("text/html; charset=gb2312");
            editor.setPage("file:"+new File("readme.html").getAbsolutePath());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        util.initTrayIcon("Test trayicon program have tip text!",new File("icon.gif"),this,popup);
        util.showTrayIcon();
    }
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.setExtendedState(ICONIFIED);
            return;
        }
        super.processWindowEvent(e);
    }

    void item_SH_actionPerformed(ActionEvent e) {
        this.setVisible(!this.isVisible());
        if(isVisible()){
            if(this.getExtendedState()==ICONIFIED){
                this.setExtendedState(NORMAL);
            }
        }
    }
    
}
class ExitAction extends AbstractAction{
    public ExitAction(){
        super("Exit");
        super.putValue(Action.MNEMONIC_KEY,new Integer('X'));
    }
    public void actionPerformed(ActionEvent e) {
        TrayIcon.getDefaultTrayIcon().closeTrayIcon();
        System.exit(0);
    }
}
