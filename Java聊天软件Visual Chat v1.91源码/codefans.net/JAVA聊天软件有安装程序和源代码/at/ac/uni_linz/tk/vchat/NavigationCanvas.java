package at.ac.uni_linz.tk.vchat;

import java.awt.*;
import java.awt.event.*;


/**
 * Displays icons for navigating through a Room.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class NavigationCanvas extends Canvas implements MouseListener, MouseMotionListener, Runnable {

  private Image imgForward, imgBackward, imgLeft, imgRight;
  private Rectangle rectForward, rectBackward, rectLeft, rectRight;
  private ChatApplet chatApplet;
  private Thread mouseListenerThread;
  private MouseEvent prolongedMouseEvent;
  private static final int PROLONGATION_DELAY = 100;

/**
 * Constructs the NavigationCanvas.
 *
 * @param chatAdministratorParam      the ChatApplet which administrates the
 *                                    users
 */

  public NavigationCanvas(ChatApplet chatAdministratorParam) {
    chatApplet = chatAdministratorParam;

    imgForward = createImage(ChatRepository.NAVIGATION_ARROW_DIMENSION.width, ChatRepository.NAVIGATION_ARROW_DIMENSION.height);
    imgBackward = createImage(ChatRepository.NAVIGATION_ARROW_DIMENSION.width, ChatRepository.NAVIGATION_ARROW_DIMENSION.height);
    imgLeft = createImage(ChatRepository.NAVIGATION_ARROW_DIMENSION.width, ChatRepository.NAVIGATION_ARROW_DIMENSION.height);
    imgRight = createImage(ChatRepository.NAVIGATION_ARROW_DIMENSION.width, ChatRepository.NAVIGATION_ARROW_DIMENSION.height);

    updateRectangles();

    addMouseListener(this);
    addMouseMotionListener(this);
    mouseListenerThread = new Thread(this);
  }


/**
 * Sets the icon for moving forward. It will be displayed in the upper center of
 * the NavigationCanvas.
 *
 * @param imgParam      the Image of the icon
 */

  public void setForwardIcon(Image imgParam) {
    imgForward = imgParam;
    repaint();
  }


/**
 * Sets the icon for moving backward. It will be displayed in the lower center of
 * the NavigationCanvas.
 *
 * @param imgParam      the Image of the icon
 */

  public void setBackwardIcon(Image imgParam) {
    imgBackward = imgParam;
    repaint();
  }


/**
 * Sets the icon for turning left. It will be displayed in the left middle of
 * the NavigationCanvas.
 *
 * @param imgParam      the Image of the icon
 */

  public void setLeftIcon(Image imgParam) {
    imgLeft = imgParam;
    repaint();
  }


/**
 * Sets the icon for turning right. It will be displayed in the right middle of
 * the NavigationCanvas.
 *
 * @param imgParam      the Image of the icon
 */

  public void setRightIcon(Image imgParam) {
    imgRight = imgParam;
    repaint();
  }


/**
 * Returns the preferred size of the NavigationCanvas.
 */

  public Dimension getPreferredSize() {
    return getCalculatedSize();
  }


/**
 * Returns the minimum size of the NavigationCanvas.
 */

  public Dimension getMinimumSize() {
    return getCalculatedSize();
  }


/**
 * Calculates and returns the optimal size of the NavigationCanvas.
 */

  private Dimension getCalculatedSize() {
    return new Dimension(ChatRepository.MARGIN * 2 + ChatRepository.NAVIGATION_ARROW_DIMENSION.width * 3, ChatRepository.MARGIN * 2 + ChatRepository.NAVIGATION_ARROW_DIMENSION.height * 3);
  }


/**
 * Calculates the imagemap of the NavigationCanvas's icons regarding to its actual
 * size.
 */

  private synchronized void updateRectangles() {
    if (imgForward != null) {
      rectForward = new Rectangle((getSize().width - imgForward.getWidth(this)) / 2, getSize().height / 2 - ChatRepository.NAVIGATION_ARROW_DIMENSION.height * 3 / 2, imgForward.getWidth(this), imgForward.getHeight(this));
    }
    if (imgLeft != null) {
      rectLeft = new Rectangle(getSize().width / 2 - ChatRepository.NAVIGATION_ARROW_DIMENSION.width * 3 / 2, (getSize().height - imgLeft.getHeight(this)) / 2, imgLeft.getWidth(this), imgLeft.getHeight(this));
    }
    if (imgRight != null) {
      rectRight = new Rectangle(getSize().width / 2 + ChatRepository.NAVIGATION_ARROW_DIMENSION.width / 2, (getSize().height - imgRight.getHeight(this)) / 2, imgRight.getWidth(this), imgRight.getHeight(this));
    }
    if (imgBackward != null) {
      rectBackward = new Rectangle((getSize().width - imgBackward.getWidth(this)) / 2, getSize().height / 2 + ChatRepository.NAVIGATION_ARROW_DIMENSION.height / 2, imgBackward.getWidth(this), imgBackward.getHeight(this));
    }
  }


/**
 * Paints the NavigationPanel.
 *
 * @param g      the graphics context to use for painting
 */

  public synchronized void paint(Graphics g) {

    updateRectangles();

    if (imgForward != null) {
      g.drawImage(imgForward, rectForward.x, rectForward.y, this);
    }
    if (imgLeft != null) {
      g.drawImage(imgLeft, rectLeft.x, rectLeft.y, this);
    }
    if (imgRight != null) {
      g.drawImage(imgRight, rectRight.x, rectRight.y, this);
    }
    if (imgBackward != null) {
      g.drawImage(imgBackward, rectBackward.x, rectBackward.y, this);
    }
  }


/**
 * Invoked when the mouse has been clicked on a component.
 *
 * @param event      the MouseEvent
 */

  public synchronized void mouseClicked(MouseEvent event) {
    handleMouseEvent(event);
  }


/**
 * Invoked when the mouse enters a component.
 *
 * @param event      the MouseEvent
 */

  public synchronized void mouseEntered(MouseEvent event) {
  }


/**
 * Invoked when the mouse exits a component.
 *
 * @param event      the MouseEvent
 */

  public synchronized void mouseExited(MouseEvent event) {
  }


/**
 * Invoked when a mouse button has been pressed on a component.
 *
 * @param event      the MouseEvent
 */

  public synchronized void mousePressed(MouseEvent event) {
    handleMouseEvent(event);
    prolongedMouseEvent = event;
    if (!mouseListenerThread.isAlive()) {
      mouseListenerThread.start();
    }
    else {
      mouseListenerThread.resume();
    }
  }


/**
 * Invoked when a mouse button has been released on a component.
 *
 * @param event      the MouseEvent
 */

  public synchronized void mouseReleased(MouseEvent event) {
    mouseListenerThread.suspend();
  }


/**
 * Invoked when a mouse button is pressed on a component and then dragged.
 *
 * @param event      the MouseEvent
 */

  public synchronized void mouseDragged(MouseEvent event) {
    handleMouseEvent(event);
  }


/**
 * Invoked when the mouse button has been moved on a component (with no buttons no down).
 *
 * @param event      the MouseEvent
 */

  public synchronized void mouseMoved(MouseEvent event) {
  }


/**
 * Contains functionality for all possible MouseEvents on the NavigationPanel.
 *
 * @param event      the MouseEvent
 */

  public synchronized void handleMouseEvent(MouseEvent event) {
    if (rectBackward != null && rectBackward.contains(event.getPoint())) {
      chatApplet.setUserPosition(chatApplet.getCurrentUser().getId(), new Point(chatApplet.getCurrentUser().getPosition().x - (int)(Math.cos((double)(chatApplet.getCurrentUser().getHeading()) * Math.PI / 180.0) * 4.0), chatApplet.getCurrentUser().getPosition().y + (int)(Math.sin((double)(chatApplet.getCurrentUser().getHeading()) * Math.PI / 180.0) * 4.0)), true);
    }
    else if (rectForward != null && rectForward.contains(event.getPoint())) {
      chatApplet.setUserPosition(chatApplet.getCurrentUser().getId(), new Point(chatApplet.getCurrentUser().getPosition().x + (int)(Math.cos((double)(chatApplet.getCurrentUser().getHeading()) * Math.PI / 180.0) * 4.0), chatApplet.getCurrentUser().getPosition().y - (int)(Math.sin((double)(chatApplet.getCurrentUser().getHeading()) * Math.PI / 180.0) * 4.0)), true);
    }
    else if (rectLeft != null && rectLeft.contains(event.getPoint())) {
      chatApplet.setUserHeading(chatApplet.getCurrentUser().getId(), chatApplet.getCurrentUser().getHeading() + 4, true);
    }
    else if (rectRight != null && rectRight.contains(event.getPoint())) {
      chatApplet.setUserHeading(chatApplet.getCurrentUser().getId(), chatApplet.getCurrentUser().getHeading() - 4, true);
    }
  }

  public void run() {
    while (true) {
      try {
        Thread.sleep(PROLONGATION_DELAY);
      }
      catch (InterruptedException excpt) {
      }
      if (prolongedMouseEvent != null) {
        handleMouseEvent(prolongedMouseEvent);
      }
    }
  }


}