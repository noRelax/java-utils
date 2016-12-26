package at.ac.uni_linz.tk.vchat;

import java.awt.*;
import java.awt.image.*;
import java.util.*;


/**
 * Used for displaying Users' messages in an cartoon-like balloon.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class Balloon extends Component {

  public static final int FACING_RIGHT = 0;
  public static final int FACING_LEFT = 1;
  public static final int DIAMETER = 5;
  public static final int MARGIN = 10;
  public static final double VERTICAL_SCALE = 0.8;
  public static final double HORICONTAL_SCALE = 0.2;
  public static final double LINE_SPACING = 1.5;

  private Rectangle area;
  private String text;
  private Color color;
  private Font font;
  private int facing;


/**
 * Constructs the Balloon.
 *
 * @param areaParam       rectangle defining the size and position of the
 *                        balloon
 * @param textParam       text to be displayed in the balloon
 * @param colorParam      color of the balloon
 */

  public Balloon(Rectangle areaParam, String textParam, Color colorParam) {
    this(areaParam, textParam, colorParam, FACING_RIGHT);
  }


/**
 * Constructs the Balloon.
 *
 * @param areaParam        rectangle defining the size and position of the
 *                         balloon
 * @param textParam        text to be displayed in the balloon
 * @param colorParam       color of the balloon
 * @param facingParam      the balloon's orientation - has to be FACING_RIGHT or
 *                         FACING_LEFT
 */

  public Balloon(Rectangle areaParam, String textParam, Color colorParam, int facingParam) {
    this(areaParam, textParam, ChatRepository.STANDARD_FONT, colorParam, facingParam);
  }


  public Balloon(Rectangle areaParam, String textParam, Font fontParam, Color colorParam, int facingParam) {
    area = new Rectangle(areaParam.x, areaParam.y, areaParam.width, areaParam.height);
    text = textParam;
    font = fontParam;
    color = colorParam;
    facing = facingParam;
  }


/**
 * Paints the Balloon.
 *
 * @param g      the graphics context
 */

  public void paint(Graphics g) {
    Font fontCopy;
    StringTokenizer stringTokenizer;
    String line, word;
    Rectangle textArea;
    int textX, textY;

    if (text != null && !text.equals("")) {

      /*
       * Calculate the points for the bubble's tail
       */
      int[] triX = {(facing == FACING_RIGHT) ? area.x + area.width : area.x, area.x + (int)(area.width * (1 - HORICONTAL_SCALE) / 2), area.x + (int)(area.width * (1 + HORICONTAL_SCALE) / 2)};
      int[] triY = {area.y + area.height, area.y + (int)(area.height * VERTICAL_SCALE), area.y + (int)(area.height * VERTICAL_SCALE)};

      ChatUtil.paintPattern(g, new Rectangle(area.x, area.y, area.width, (int)(area.height * VERTICAL_SCALE)), Color.white);

      g.setColor(color);
      g.drawLine(area.x, area.y, area.x + area.width, area.y);
      g.drawLine(area.x, area.y, area.x, area.y + (int)(area.height * VERTICAL_SCALE));
      g.drawLine(area.x + area.width, area.y, area.x + area.width, area.y + (int)(area.height * VERTICAL_SCALE));
      g.drawLine(area.x, area.y + (int)(area.height * VERTICAL_SCALE), triX[1], triY[1]);
      g.drawLine(area.x + area.width, area.y + (int)(area.height * VERTICAL_SCALE), triX[2], triY[2]);
      g.drawLine(triX[0], triY[0], triX[1], triY[1]);
      g.drawLine(triX[0], triY[0], triX[2], triY[2]);

      /*
       * Make the message text fit into the bubble
       *
       * This is quite a dirty implementation. We should include the methods of XList here to break the text...
       */
      fontCopy = g.getFont();
      g.setFont(font);

      stringTokenizer = new StringTokenizer(text + " ");
      textArea = new Rectangle(area.x + MARGIN, area.y + MARGIN, area.width - 2 * MARGIN, (int)(area.height * VERTICAL_SCALE) - 2 * MARGIN);
      textX = area.x + MARGIN;
      textY = textArea.y + MARGIN;
      word = "";

      while (stringTokenizer.hasMoreTokens() && textArea.contains(textX, textY)) {
        line = "";
        do {
          line += word;
          word = stringTokenizer.nextToken() + " ";
        }
        while (stringTokenizer.hasMoreTokens() && getFontMetrics(font).stringWidth(line + word) < textArea.width);

        if (!stringTokenizer.hasMoreTokens() && getFontMetrics(font).stringWidth(line + word) < textArea.width)
          line += word;
        else if (!stringTokenizer.hasMoreTokens() && getFontMetrics(font).stringWidth(line + word) >= textArea.width) {
          while (getFontMetrics(font).stringWidth(word) >= textArea.width && word.length() > 0)
            word = word.substring(0, word.length() - 1);
          g.drawString(word, textX, textY + (line.equals("") ? 0 : (int)(getFontMetrics(font).getMaxAscent() * LINE_SPACING)));
        }

        while (getFontMetrics(font).stringWidth(line) >= textArea.width && line.length() > 0)
          line = line.substring(0, word.length() - 1);

        g.drawString(line, textX, textY);
        textY += (int)(getFontMetrics(font).getMaxAscent() * LINE_SPACING);
      }
      g.setFont(fontCopy);
    }
  }

}