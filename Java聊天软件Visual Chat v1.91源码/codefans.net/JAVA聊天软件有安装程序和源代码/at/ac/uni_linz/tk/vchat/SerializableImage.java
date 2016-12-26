package at.ac.uni_linz.tk.vchat;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.util.zip.*;
import java.util.*;


/**
 * Provides the possibility to serialize Images, so that they can be transmitted
 * over a network or stored to a physical device.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class SerializableImage implements Serializable, ImageObserver, Cloneable {

  static final long serialVersionUID = -2975749503113144495L;

  private transient Image image;
  private int buffer[];
  private int width, height;


/**
 * Constructs the SerializableImage
 *
 * @param imageParam       the image to be serialized
 */

  public SerializableImage(Image imageParam) {
    image = imageParam;
    do {
      try {
        Thread.sleep(100);
      }
      catch (InterruptedException excpt) {
      }
      width = image.getWidth(this);
      height = image.getHeight(this);
    } while((width == -1) || (height == -1));
    buffer = new int[width * height];
  }


/**
 * Writes the image to an ObjectOutputStream. Required to implement the
 * Serializable-Interface. The data is transmitted compressed using a
 * ZipOutputStream.
 *
 * @param out       the ObjectOutputStream to write to
 */

  private synchronized void writeObject(ObjectOutputStream out) throws IOException {
    PixelGrabber grabber;
    ZipOutputStream zipOutput;
    ObjectOutputStream objectOutput;

    grabber = new PixelGrabber(image, 0, 0, width, height, buffer, 0, width);
    try {
      grabber.grabPixels();
    }
    catch (InterruptedException excpt) {
    }

    out.writeInt(width);
    out.writeInt(height);

    zipOutput = new ZipOutputStream(out);
    zipOutput.putNextEntry(new ZipEntry(buffer.toString()));
    objectOutput = new ObjectOutputStream(zipOutput);
    objectOutput.writeObject(buffer);
    zipOutput.closeEntry();

    objectOutput.flush();
}


/**
 * Reads the image from an ObjectInputStream. Required to implement the
 * Serializable-Interface. The data is transmitted compressed using a
 * ZipInputStream.
 *
 * @param in       the ObjectInputStream to read from
 */

  private synchronized void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    ZipInputStream zipInput;

    byte[] byteBuffer;

    width = in.readInt();
    height = in.readInt();

    zipInput = new ZipInputStream(in);
    zipInput.getNextEntry();
    buffer = (int[])new ObjectInputStream(zipInput).readObject();
    zipInput.closeEntry();

	  image = new Label().createImage(new MemoryImageSource(width, height, ColorModel.getRGBdefault(), buffer, 0, width));
  }


/**
 * Returns the image as an java.awt.image.
 * @return      the image
 */

  public Image getImage() {
    return image;
  }


/**
 * Called when information about an image which was previously requested using
 * an asynchronous interface becomes available. Required to implement the
 * ImageObserver-Interface. Returns true, if further updates are needed, resp. false
 * if the required information has been acquired
 *
 * @param img         the Image to be tracked
 * @param x           the upper x-coordinate of the image
 * @param y           the upper y-coordinate of the image
 * @param width       the width of the image
 * @param height      the height of the image
 */

  public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
    return ((infoflags & ALLBITS) == 0);
  }

}

