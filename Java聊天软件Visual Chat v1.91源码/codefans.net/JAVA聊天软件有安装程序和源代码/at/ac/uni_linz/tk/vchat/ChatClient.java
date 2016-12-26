package at.ac.uni_linz.tk.vchat;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.image.*;


/**
 * Implements the methods for the client side's networking. Opens a socket
 * connection and Input- and OutputStreams to the ChatServer, sends and receives
 * data. It also includes the functionality for User logins and logouts.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class ChatClient implements Runnable {

  private Socket clientSocket;
  private Thread clientThread;
  private String host;
  private boolean connected;
  private ChatApplet chatApplet;

  private ObjectOutputStream output;
  private ObjectInputStream input;

  private UserLoginRequest userLogin;


/**
 * Constructs the ChatClient.
 *
 * @param hostParam               the host where the ChatApplet descends from
 *                                (that is also where the ChatServer ought to be
 *                                running)
 * @param portParam               the standard port where the ChatServer is
 *                                listening
 * @param chatParam      the ChatApplet which administrates the
 *                                users
 */

  public ChatClient(String hostParam, ChatApplet chatParam) {
    host = hostParam;
    chatApplet = chatParam;
  }


/**
 * Connects to the ChatServer.
 *
 * @param portParam      the port where the ChatServer is listening
 */

  public void connect(int portParam) {
    try {
      chatApplet.setStatus("Connecting...", true);
      clientSocket = new Socket(host, portParam);

      /*
       * Open Input- and OutputStreams
       */
      input = new ObjectInputStream(clientSocket.getInputStream());
      output = new ObjectOutputStream(clientSocket.getOutputStream());

      try {
        Thread.sleep(1000);
      }
      catch (InterruptedException excpt) {
      }


      /*
       * Start the thread that is receiving data
       */
      clientThread = new Thread(this);
      clientThread.start();
      connected = true;
      chatApplet.setStatus("Connected", true);
    }
    catch (Exception excpt) {
      connected = false;
      System.out.println("Exception while connecting: " + excpt);
      chatApplet.setStatus("Exception while connecting. Server down, firewall config or file-URL applet.", true);
    }
  }


/**
 * Waits for the ChatServer's reply to a login request.
 */

  public void waitForLoginReply() {
    while (userLogin.status == UserLoginRequest.REQUESTED && connected()) {
      try {
        Thread.sleep(100);
      }
      catch (InterruptedException excpt) {
      }
    }
  }


/**
 * Connects to the ChatServer and sends a login request for an existing User.
 *
 * @param userNameParam          the name of the User to login
 * @param userPasswordParam      the password of the User to login
 * @param portParam              the port where the ChatServer is listening
 */

  public void connectAsExistingUser(String userNameParam, String userPasswordParam, int portParam) {
    int lastUserId;

    connect(portParam);
    if (connected) {
      chatApplet.setStatus("Connecting as existing user...", true);
      chatApplet.stopSimulator();
      chatApplet.removeAllExceptDefaultRoom();
      userLogin = new ExistingUserLoginRequest(userNameParam, userPasswordParam);
      send(userLogin);
      chatApplet.setStatus("Waiting for server reply...", true);
      waitForLoginReply();
      if (userLogin.status == UserLoginRequest.ACCEPTED) {
        lastUserId = chatApplet.getCurrentUserId();
        chatApplet.setCurrentUser(userLogin.user);

        if (userLogin.user.getId() != lastUserId)
          chatApplet.removeUser(lastUserId);

        chatApplet.restartHistory();
        chatApplet.setStatus("Login accepted: " + userLogin.statusString , true);
        chatApplet.setFrameVisibility(true);
      }
      else {
        disconnect();
        chatApplet.setStatus("Login denied: " + userLogin.statusString, true);
      }
    }
  }


/**
 * Connects to the ChatServer and sends a login request for a new User.
 *
 * @param userParam      the User to login
 * @param portParam      the port where the ChatServer is listening
 */

  public void connectAsNewUser(User userParam, int portParam) {
    int lastUserId;

    connect(portParam);
    if (connected) {
      chatApplet.setStatus("Connecting as new user...", true);
      chatApplet.stopSimulator();
      chatApplet.removeAllExceptDefaultRoom();

      userLogin = new NewUserLoginRequest(userParam);
      send(userLogin);
      chatApplet.setStatus("Waiting for server reply...", true);
      waitForLoginReply();
      if (userLogin.status == UserLoginRequest.ACCEPTED) {
        lastUserId = chatApplet.getCurrentUserId();
        chatApplet.setCurrentUser(userLogin.user);

        if (userLogin.user.getId() != lastUserId)
          chatApplet.removeUser(lastUserId);

        chatApplet.restartHistory();
        chatApplet.setStatus("Login accepted: " + userLogin.statusString , true);
        chatApplet.setFrameVisibility(true);
      }
      else {
        disconnect();
        chatApplet.setStatus("Login denied: " + userLogin.statusString, true);
      }
    }
  }


/**
 * Disconnects from the ChatServer.
 */

  public void disconnect() {
    try {
      send(new UserLogoutEvent(chatApplet.getCurrentUserId()));
      if (clientThread != null && clientThread.isAlive())
        clientThread.stop();
      if (output != null)
        output.close();
      if (input != null)
        input.close();
      if (clientSocket != null)
        clientSocket.close();
      chatApplet.setStatus("Disconnected", true);
    }
    catch (IOException excpt) {
      System.out.println("Exception while disconnecting: " + excpt);
      chatApplet.setStatus("Exception while disconnecting", true);
    }
    finally {
      connected = false;
      chatApplet.removeAllExceptCurrentUser();
      chatApplet.removeAllExceptDefaultRoom();
    }
  }


/**
 * Runs a thread that is receiving data from the InpuStream openend to the
 * ChatServer.
 */

  public void run() {
    Object receivedObject;
    User user;
    while (connected) {
      try {
        receivedObject = input.readObject();
        chatApplet.setStatus("Receiving data");
        if (receivedObject instanceof UserPositionEvent)
          chatApplet.setUserPosition(((UserPositionEvent)receivedObject).userId, ((UserPositionEvent)receivedObject).userPosition, false);
        else if (receivedObject instanceof UserHeadingEvent)
          chatApplet.setUserHeading(((UserHeadingEvent)receivedObject).userId, ((UserHeadingEvent)receivedObject).userHeading, false);
        else if (receivedObject instanceof UserMessageEvent)
          chatApplet.setUserMessage(((UserMessageEvent)receivedObject).userId, ((UserMessageEvent)receivedObject).userMessage, false);
        else if (receivedObject instanceof UserMoodEvent)
          chatApplet.setUserMood(((UserMoodEvent)receivedObject).userId, ((UserMoodEvent)receivedObject).userMood, false);
        else if (receivedObject instanceof UserRoomEvent) {
          chatApplet.moveUserToRoom(((UserRoomEvent)receivedObject).userId, ((UserRoomEvent)receivedObject).roomId, false);
          chatApplet.setUserPosition(((UserRoomEvent)receivedObject).userId, ((UserRoomEvent)receivedObject).position, false);
        }
        else if (receivedObject instanceof UserUpdateEvent) {
          chatApplet.updateUser(((UserUpdateEvent)receivedObject).user, false);
          if (((UserUpdateEvent)receivedObject).user.getId() == chatApplet.getCurrentUserId()) {
            chatApplet.setStatus(((UserUpdateEvent)receivedObject).statusString, false);
          }
        }
        else if (receivedObject instanceof UserLoginEvent)
          chatApplet.addUser(((UserLoginEvent)receivedObject).user);
        else if (receivedObject instanceof UserLogoutEvent) {
          chatApplet.removeUser(((UserLogoutEvent)receivedObject).userId);
        }
        else if (receivedObject instanceof NewUserLoginRequest) {
          chatApplet.setStatus("Received login", true);
          userLogin = (NewUserLoginRequest)receivedObject;
        }
        else if (receivedObject instanceof ExistingUserLoginRequest) {
          chatApplet.setStatus("Received login", true);
          userLogin = (ExistingUserLoginRequest)receivedObject;
        }
        else if (receivedObject instanceof RoomUpdateEvent) {
          chatApplet.setStatus("Received room update", true);
          chatApplet.updateRoom(((RoomUpdateEvent)receivedObject).room, false);
        }
        else if (receivedObject instanceof RoomCreateEvent) {
          chatApplet.setStatus("Received room creation", true);
          chatApplet.updateRoom(((RoomCreateEvent)receivedObject).room, false);
        }
        else if (receivedObject instanceof RoomListEvent) {
          chatApplet.setStatus("Received room list", true);
          chatApplet.setRoomTable(((RoomListEvent)receivedObject).roomTable);
        }
        else if (receivedObject instanceof RoomRemoveEvent) {
          chatApplet.setStatus("Received room removal", true);
          chatApplet.forceRoomRemoval(((RoomRemoveEvent)receivedObject).roomId, false);
        }
      }
      catch (Exception excpt) {
        System.out.println("Exception while receiving data: " + excpt + ". Going offline...");
        chatApplet.setStatus("Exception while receiving data. Going offline...", true);
        disconnect();
      }
    }
  }


/**
 * Sends data over the OutputStream openend to the ChatServer.
 *
 * @param sendObject      the object to be sent
 */

  public void send(Object sendObject) {
    try {
      if (connected) {
        chatApplet.setStatus("Sending data");
        output.writeObject(sendObject);
      }
    }
    catch (IOException excpt) {
      System.out.println("Exception while sending data: " + excpt + ". Going offline...");
      chatApplet.setStatus("Exception while sending data. Going offline...", true);
      disconnect();
    }
  }


/**
 * Sends data over the OutputStream openend to the ChatServer.
 *
 * @param sendObject      the object to be sent
 */

  public boolean connected() {
    return connected;
  }


/**
 * Returns the host where the ChatClient is opening connections to.
 */

  public String getHost() {
    return host;
  }

}