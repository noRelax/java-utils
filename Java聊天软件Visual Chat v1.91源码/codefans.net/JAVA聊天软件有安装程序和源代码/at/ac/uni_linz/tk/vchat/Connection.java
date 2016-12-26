package at.ac.uni_linz.tk.vchat;

import java.io.*;
import java.net.*;
import java.util.zip.*;


/**
 * Works as server-side connection that sends and receives data to / from the
 * ChatClient.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class Connection extends Thread {

  private Socket clientSocket;
  private ChatServer server;
  private ObjectOutputStream output;
  private ObjectInputStream input;
  private boolean connected;

  private int userId;


/**
 * Constructs the Connection.
 *
 * @param serverParam      the ChatServer, which handles incoming UserEvents
 * @param socketParam      the Socket that has been openend by the ChatServer
 */

  public Connection(ChatServer serverParam, Socket socketParam) {
    super();

    server = serverParam;
    clientSocket = socketParam;
    userId = 0;

    try {
      output = new ObjectOutputStream(clientSocket.getOutputStream());
      input = new ObjectInputStream(clientSocket.getInputStream());

      connected = true;
    }
    catch (IOException excpt) {
      server.log("IOException while opening connection: " + excpt);
    }
  }


  public String getIPAddress() {
    if (connected) {
      return clientSocket.getInetAddress().getHostAddress();
    }
    else {
      return new String("");
    }
  }

/**
 * Runs a thread that is receiving data from the InpuStream openend to the
 * ChatClient.
 */

  public void run() {
    Object object;
    while (connected) {
      try {
        object = input.readObject();
        server.log("Received object: " + object, ChatServer.LOGLEVEL3);
        server.handleUserEvent(object, this);
      }
      catch (Exception excpt) {
        server.log("Exception while receiving data: " + excpt);
        server.closeConnection(this);
      }
    }
  }


/**
 * Sends data over the OutputStream openend to the ChatClient.
 *
 * @param sendObject      the object to be sent
 */

  public synchronized void send(Object sendObject) {
    try {
      output.writeObject(sendObject);
    }
    catch (Exception excpt) {
      server.log("Exception while sending: " + excpt);
      server.closeConnection(this);
    }
  }


/**
 * Closes the connection.
 */

  public synchronized void close() {
    try {
      if (isAlive())
        stop();
      if (output != null)
        output.close();
      if (input != null)
        input.close();
      if (clientSocket != null)
        clientSocket.close();
    }
    catch (IOException excpt) {
      server.log("IOException while closing connection: " + excpt);
    }
    finally {
      connected = false;
    }
  }


/**
 * Returns the id of the User who is using this Connection, resp. 0 if the User's
 * id has not been set yet or the Administrator holds the Connection with the
 * ServerAdministrationApplet.
 */

  public int getUserId() {
    return userId;
  }

/**
 * Sets the id of the User who is using this Connection.
 */

  public void setUserId(int userIdParam) {
    userId = userIdParam;
  }


/**
 * Rrtuens the Connection's Socket.
 */

  public Socket getSocket() {
    return clientSocket;
  }

}
