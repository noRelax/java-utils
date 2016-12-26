package chat;

import javax.swing.UIManager;
import java.awt.*;

/**
 * <p>Title: 使用JAVA制作的局域网聊天程序</p>
 * <p>Description: 我们的JAVA作业</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author RoadAhead
 * @version 1.0
 */

public class chatmain
{
  private boolean packFrame = false;

  //Construct the application
  public chatmain()
  {
    mainform frame = new mainform();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame)
    {
      frame.pack();
    }
    else
    {
      frame.validate();
    }
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height)
    {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width)
    {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }
  //Main method
  public static void main(String[] args)
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    new chatmain();
  }
}