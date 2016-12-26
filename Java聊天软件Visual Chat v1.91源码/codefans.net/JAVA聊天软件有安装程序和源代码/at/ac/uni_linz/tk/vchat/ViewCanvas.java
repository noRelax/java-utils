package at.ac.uni_linz.tk.vchat;

import at.ac.uni_linz.tk.vchat.engine.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


/**
 * Displayes the User's three-dimensional view on the Room, other Users and their
 * messages.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class ViewCanvas extends Canvas implements Runnable {

  private static final int ROOM_HEIGHT = 30;
  private static final int SPACE_BUFFER = 5;
  private static final int GRID_SIZE = 10;

  private Image bufferImage;

  private ChatApplet chatApplet;

  private Object3D world;
  private Light light;
  private Plane3D frustum[];

/**
 * Constructs the ViewCanvas.
 *
 * @param chatParam      the ChatApplet which administrates the
 *                                Users
 * @param repositoryParam         the ChatRepository, where commonly used objects
 *                                are being stored
 */

  public ViewCanvas(ChatApplet chatAdministratorParam) {
    boolean toggle = true;
    chatApplet = chatAdministratorParam;

    world = new Object3D();
    light = new Light();
    frustum = new Plane3D[5];

    Vertex3D avertex[] = new Vertex3D[4];
    createFrustum();
    light = new Light(-2, 2, -2);
    for (int i = -SPACE_BUFFER; i < ChatRepository.ROOM_DIMENSION.width + SPACE_BUFFER; i+=GRID_SIZE) {
      for (int j = -SPACE_BUFFER; j < ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER; j+=GRID_SIZE) {
        if (toggle) {
          Polygon3D s = new Polygon3D();
          s.addPoint(new Vertex3D(i, 0, j));
          s.addPoint(new Vertex3D(i, 0, j + GRID_SIZE));
          s.addPoint(new Vertex3D(i + GRID_SIZE, 0, j + GRID_SIZE));
          s.addPoint(new Vertex3D(i + GRID_SIZE, 0, j));
          s.normVec = new Vertex3D(0, 1, 0);
          s.col = Color.white;
          world.add(s);
        }
        toggle=!toggle;
      }
      toggle=!toggle;
    }
    Polygon3D p = new Polygon3D();
    p.addPoint(new Vertex3D(-SPACE_BUFFER, 0, -SPACE_BUFFER));
    p.addPoint(new Vertex3D(ChatRepository.ROOM_DIMENSION.width + SPACE_BUFFER, 0, -SPACE_BUFFER));
    p.addPoint(new Vertex3D(ChatRepository.ROOM_DIMENSION.width + SPACE_BUFFER, 0, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER));
    p.addPoint(new Vertex3D(-SPACE_BUFFER, 0, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER));
    p.normVec = new Vertex3D(0, 1, 0);
    p.col = Color.lightGray;
    world.add(p);

    world.add(new Cube3D(new Vertex3D(-SPACE_BUFFER, 0, -SPACE_BUFFER), new Vertex3D(0, ROOM_HEIGHT, 0), Color.red));
    world.add(new Cube3D(new Vertex3D(ChatRepository.ROOM_DIMENSION.width + SPACE_BUFFER, 0, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER), new Vertex3D(ChatRepository.ROOM_DIMENSION.width, ROOM_HEIGHT, ChatRepository.ROOM_DIMENSION.height), Color.blue));
    world.add(new Cube3D(new Vertex3D(ChatRepository.ROOM_DIMENSION.width + SPACE_BUFFER, 0, -SPACE_BUFFER), new Vertex3D(ChatRepository.ROOM_DIMENSION.width, ROOM_HEIGHT, 0), Color.yellow));
    world.add(new Cube3D(new Vertex3D(-SPACE_BUFFER, 0, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER), new Vertex3D(0, ROOM_HEIGHT, ChatRepository.ROOM_DIMENSION.height), Color.green));

    Polygon3D w;
    Color wCol = new Color (239, 239, 239);
    w = new Polygon3D();
    w.addPoint(new Vertex3D(-SPACE_BUFFER, 0, -SPACE_BUFFER));
    w.addPoint(new Vertex3D(ChatRepository.ROOM_DIMENSION.width + SPACE_BUFFER, 0, -SPACE_BUFFER));
    w.addPoint(new Vertex3D(ChatRepository.ROOM_DIMENSION.width + SPACE_BUFFER, ROOM_HEIGHT, -SPACE_BUFFER));
    w.addPoint(new Vertex3D(-SPACE_BUFFER, ROOM_HEIGHT, -SPACE_BUFFER));
    w.normVec = new Vertex3D(0, 0, 1);
    w.col = wCol;
    world.add(w);
    w = new Polygon3D();
    w.addPoint(new Vertex3D(-SPACE_BUFFER, 0, -SPACE_BUFFER));
    w.addPoint(new Vertex3D(-SPACE_BUFFER, 0, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER));
    w.addPoint(new Vertex3D(-SPACE_BUFFER, ROOM_HEIGHT, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER));
    w.addPoint(new Vertex3D(-SPACE_BUFFER, ROOM_HEIGHT, -SPACE_BUFFER));
    w.normVec = new Vertex3D(1, 0, 0);
    w.col = wCol;
    world.add(w);
    w = new Polygon3D();
    w.addPoint(new Vertex3D(-SPACE_BUFFER, 0, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER));
    w.addPoint(new Vertex3D(ChatRepository.ROOM_DIMENSION.width, 0, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER));
    w.addPoint(new Vertex3D(ChatRepository.ROOM_DIMENSION.width, ROOM_HEIGHT, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER));
    w.addPoint(new Vertex3D(-SPACE_BUFFER, ROOM_HEIGHT, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER));
    w.normVec = new Vertex3D(0, 0, -1);
    w.col = wCol;
    world.add(w);
    w = new Polygon3D();
    w.addPoint(new Vertex3D(ChatRepository.ROOM_DIMENSION.width + SPACE_BUFFER, 0,  -SPACE_BUFFER));
    w.addPoint(new Vertex3D(ChatRepository.ROOM_DIMENSION.width + SPACE_BUFFER, 0, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER));
    w.addPoint(new Vertex3D(ChatRepository.ROOM_DIMENSION.width + SPACE_BUFFER, ROOM_HEIGHT, ChatRepository.ROOM_DIMENSION.height + SPACE_BUFFER));
    w.addPoint(new Vertex3D(ChatRepository.ROOM_DIMENSION.width + SPACE_BUFFER, ROOM_HEIGHT, -SPACE_BUFFER));
    w.normVec = new Vertex3D(-1, 0, 0);
    w.col = wCol;
    world.add(w);

  }

  public Dimension getPreferredSize() {
    if (getParent() == null) {
      return super.getPreferredSize();
    }
    else {
      return new Dimension(getParent().getSize().width - getParent().getInsets().left - getParent().getInsets().right, getParent().getSize().height - getParent().getInsets().top - getParent().getInsets().bottom);
    }
  }


/**
 * Updates the ViewCanvas. Clearing the ViewCanvas is not necessary, as it uses
 * double-buffering.
 *
 * @param g       the graphics context
 */

  public void update(Graphics g) {
    paint(g);
  }

/**
 * Paints the ViewCanvas.
 *
 * @param g       the graphics context
 */

  public synchronized void paint(Graphics g) {
    Object3D clippedworld;
    Graphics bufferGraphics;
    Image image, userBackPortrait;

    Hashtable userTable;
    User user, currentUser;
    Vector vecCurrentSituation;
    int distance, angle;
    int canvasWidth, canvasHeight;

    Point pntPosition;

    HistoryEntry histEntry;

    currentUser = chatApplet.getCurrentUser();
    pntPosition = currentUser.getPosition();

    canvasWidth = getSize().width;
    canvasHeight = getSize().height;

    if (bufferImage == null || bufferImage.getWidth(this) != canvasWidth || bufferImage.getHeight(this) != canvasHeight)
      bufferImage = createImage(canvasWidth, canvasHeight);

    bufferGraphics = bufferImage.getGraphics();

    vecCurrentSituation = chatApplet.historyMode() ? chatApplet.getHistoryEntryVector(chatApplet.getHistoryDate()) : chatApplet.getCurrentSituationVector();

    for (int i = 0; i < vecCurrentSituation.size(); i++) {
      user = chatApplet.getUser(((HistoryEntry)vecCurrentSituation.elementAt(i)).userId);
      if (user == null || !chatApplet.inVisualRange(currentUser.getId(), ((HistoryEntry)vecCurrentSituation.elementAt(i)).position)) {
        vecCurrentSituation.removeElementAt(i);
        i--;
      }
    }
    Vector tmpVec = new Vector(vecCurrentSituation.size());
    /* Heap-Sort - should be enough for this purpose, not too much overhead... */
    for (int i = 0; i < vecCurrentSituation.size(); i++) {
      int dist = 0;
      int delta = tmpVec.size() / 2;
      int j = delta;
      boolean done = false;
      while (!done) {
        dist = chatApplet.getDistance(currentUser.getId(), ((HistoryEntry)vecCurrentSituation.elementAt(i)).position);
        delta = Math.max(1, delta / 2);
        if ((j - delta) >= 0 && dist > chatApplet.getDistance(currentUser.getId(), ((HistoryEntry)tmpVec.elementAt(j - 1)).position)) {
          j -= delta;
        }
        else if ((j + delta) <= tmpVec.size() && dist < chatApplet.getDistance(currentUser.getId(), ((HistoryEntry)tmpVec.elementAt(j)).position)) {
          j += delta;
        }
        else {
          done = true;
        }
      }
      tmpVec.insertElementAt(vecCurrentSituation.elementAt(i), j);
    }

    vecCurrentSituation = tmpVec;

    bufferGraphics.setColor(Color.white);
    bufferGraphics.fillRect(0, 0, canvasWidth, canvasHeight);
    ViewPoint viewPoint;
    Object3D viewedWorld = getTransformedWorld(viewPoint = new ViewPoint(new Vertex3D(pntPosition.x, ChatRepository.USER_HEIGHT - 1, pntPosition.y), new Orientation(0, currentUser.getHeading() + 90, 0)));

    clippedworld = viewedWorld.clip(frustum);
    clippedworld.bringToScreen(getSize().width, getSize().height, light, true);
    clippedworld.orderByDepth();
    clippedworld.paint(bufferGraphics);

    for (int i = 0; i < vecCurrentSituation.size(); i++) {
      histEntry = (HistoryEntry)vecCurrentSituation.elementAt(i);
      user = chatApplet.getUser(histEntry.userId);
      if (user != null && histEntry.userId != chatApplet.getCurrentUserId()) {
        image = chatApplet.getUserAvatar(user.getId(), ((HistoryEntry)vecCurrentSituation.elementAt(i)).mood);
        Object3D userObj = new Object3D();
        Polygon3D polyWorld = new Polygon3D();
        int width = image.getWidth(this) * ChatRepository.USER_HEIGHT / image.getWidth(this);
        polyWorld.addPoint(new Vertex3D(histEntry.position.x - width / 2, 0, histEntry.position.y));
        polyWorld.addPoint(new Vertex3D(histEntry.position.x - width / 2, ChatRepository.USER_HEIGHT, histEntry.position.y));
        polyWorld.addPoint(new Vertex3D(histEntry.position.x + width / 2, ChatRepository.USER_HEIGHT, histEntry.position.y));
        polyWorld.addPoint(new Vertex3D(histEntry.position.x + width / 2, 0, histEntry.position.y));

        Object3D littleWorld = new Object3D(getTransformedPoly(polyWorld, viewPoint)).clip(frustum);

        littleWorld = littleWorld.clip(frustum);
        littleWorld.bringToScreen(getSize().width, getSize().height, light, true);
        // littleWorld.paint(bufferGraphics);
        if (littleWorld.getNrOfPolygons() == 1) {
          Polygon3D poly = littleWorld.getPolygon(0);

          if (!chatApplet.inVisualRange(histEntry.position, histEntry.heading, currentUser.getPosition())) {
            image = chatApplet.getUserBackAvatar(user.getId());
          }

          new Image3D(poly, image, user.getName(), histEntry.text, user.getColor()).paint(bufferGraphics);
            if (chatApplet.historyMode() && histEntry.userId == chatApplet.getCurrentUserId()) {
              (new Balloon(new Rectangle(canvasWidth * 2 / 3, 10, canvasWidth * 1 / 3 - 10, canvasHeight - 20), histEntry.text, currentUser.getColor(), Balloon.FACING_LEFT)).paint(bufferGraphics);
            }
          }
        }
      }
      bufferGraphics.setColor(Color.black);
      bufferGraphics.setFont(ChatRepository.BOLD_FONT);
      bufferGraphics.drawString(chatApplet.getCurrentRoom().getName(), 10, getFontMetrics(getFont()).getMaxAscent() + 10);
      bufferGraphics.setFont(ChatRepository.STANDARD_FONT);

      g.drawImage(bufferImage, 0, 0, this);
    }

    private void createFrustum() {
      frustum[0] = new Plane3D();
      frustum[0].normVec = new Vertex3D(0.0D, -Math.sqrt(2D), Math.sqrt(2D));
      frustum[0].dist = 0.0D;
      frustum[1] = new Plane3D();
      frustum[1].normVec = new Vertex3D(-Math.sqrt(2D), 0.0D, Math.sqrt(2D));
      frustum[1].dist = 0.0D;
      frustum[2] = new Plane3D();
      frustum[2].normVec = new Vertex3D(Math.sqrt(2D), 0.0D, Math.sqrt(2D));
      frustum[2].dist = 0.0D;
      frustum[3] = new Plane3D();
      frustum[3].normVec = new Vertex3D(0.0D, Math.sqrt(2D), Math.sqrt(2D));
      frustum[3].dist = 0.0D;
      frustum[4] = new Plane3D();
      frustum[4].normVec = new Vertex3D(0.0D, 0.0D, 1.0D);
      frustum[4].dist = 2D;
    }

    private Polygon3D getTransformedPoly(Polygon3D poly, ViewPoint viewpoint) {
      Polygon3D myPoly = new Polygon3D(poly);
      myPoly.translate(new Vertex3D(-viewpoint.from.x, -viewpoint.from.y, -viewpoint.from.z));
      myPoly.rotate(viewpoint.orient);
      return myPoly;
    }

    private Object3D getTransformedWorld(ViewPoint viewpoint) {
      Object3D viewworld = new Object3D(world);
      viewworld.translate(new Vertex3D(-viewpoint.from.x, -viewpoint.from.y, -viewpoint.from.z));
      viewworld.rotate(viewpoint.orient);
      return viewworld;
    }

    public void run() {
    }



}
