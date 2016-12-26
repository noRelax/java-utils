package at.ac.uni_linz.tk.vchat;

import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;


/**
 * Main class for the server side's functionality. Starts a listener on the
 * defined port, opens socket connections as needed and handles UserEvents.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class ChatServer implements Runnable {

  private static final String STORAGE_FOLDER = "users/";
  private static final String IMAGE_FOLDER = "images/";
  private static final String ROOM_FOLDER = "rooms/";
  private static final String ROOM_FILENAME = "rooms";
  private static final String KEY = "Admin";
  private static final String BANNED_IP_FILENAME = "banned_ip";

  public static final int LOGLEVEL0 = 0;
  public static final int LOGLEVEL1 = 1;
  public static final int LOGLEVEL2 = 2;
  public static final int LOGLEVEL3 = 3;
  public static final int DEFAULT_LOGLEVEL = LOGLEVEL1;
  public static final int STANDARD_LOGLEVEL = LOGLEVEL1;

  public static final int DELAY = 10000;

  private ServerSocket listener;
  private Vector connectionVector, bannedIPs;
  private Thread serverThread;
  private IntegerHashtable onlineTable, roomTable;

  private String key, storageFolder, imageFolder, roomFolder, roomFilename;
  private int logLevel;

  private Random rand = new Random();

/**
 * Starts the ChatServer.
 *
 * @param args[]      command line arguments - the first argument is the server
 *                    key
 */

  public static void main(String args[]) {
    int port;

    try {
      port = (args.length >= 1 && new Integer(args[0]).intValue() > 2048) ? new Integer(args[0]).intValue() : ChatRepository.DEFAULT_PORT;
    }
    catch (NumberFormatException excpt) {
      port = ChatRepository.DEFAULT_PORT;
    }
    if (args.length >= 1 && (args[0].indexOf("?") != -1 || args[0].toLowerCase().indexOf("help") != -1)) {
      System.out.println("Visual Chat Server");
      System.out.println("Usage: java chat.ChatServer [ port [ serverkey [ storagefolder [ imagefolder [ roomfolder [ roomfilename [ loglevel ] ] ] ] ] ] ]");
      System.out.println("Hint: Store banned ip-addresses in a file named \"banned_ip\"");
    }
    else {
      new ChatServer(port, (args.length >= 2) ? args[1] : KEY, (args.length >= 3) ? args[2] : STORAGE_FOLDER, (args.length >= 4) ? args[3] : IMAGE_FOLDER, (args.length >= 5) ? args[4] : ROOM_FOLDER, (args.length >= 6) ? args[5] : ROOM_FILENAME, (args.length >= 7) ? ChatUtil.getInt(args[6]) : DEFAULT_LOGLEVEL);
    }
  }


/**
 * Constructs the ChatServer.
 *
 * @param portParam                the port the server is running on
 * @param keyParam                 the server key
 * @param storageFolderParam       the foldername for storing user-files
 * @param imageFolderParam         the foldername for retrieving images
 * @param roomFolderParam          the foldername for storing user-files
 * @param roomFileNameParam        the foldername for retrieving images
 * @param logLevelParam            the logLevel
 */

  public ChatServer(int portParam, String keyParam, String storageFolderParam, String imageFolderParam, String roomFolderParam, String roomFilenameParam, int logLevelParam) {
    Object loadedObject;
    Room room;

    key = keyParam;
    storageFolder = storageFolderParam + (storageFolderParam.endsWith("/") ? "" : "/");
    imageFolder = imageFolderParam + (imageFolderParam.endsWith("/") ? "" : "/");
    roomFolder = roomFolderParam + (roomFolderParam.endsWith("/") ? "" : "/");
    roomFilename = roomFilenameParam;
    logLevel = logLevelParam;

    try {
      new File(storageFolder).mkdirs();
      new File(imageFolder).mkdirs();
      new File(roomFolder).mkdirs();

      listener = new ServerSocket(portParam);
      connectionVector = new Vector();
      onlineTable = new IntegerHashtable();

      if (!exists(ChatRepository.ADMIN)) {
        saveUser(new User(ChatRepository.ADMIN, keyParam));
        log("Created admin user...");
      }
      if (new File(roomFolder + roomFilename).exists()) {
        try {
          roomTable = (IntegerHashtable)load(roomFolder + roomFilename);
        }
        catch (Exception excpt) {
          log(excpt.toString());
          createStandardRooms();
        }
      }
      else {
        createStandardRooms();
      }

      try {
        roomTable = (IntegerHashtable)load(roomFolder + roomFilename);
      }
      catch (Exception excpt) {
        log(excpt.toString());
        createStandardRooms();
      }

      bannedIPs = new Vector();
      try {
        String content = new String();
        StringTokenizer tokenizer;
        char ch;
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(new File(BANNED_IP_FILENAME)));
        while ((ch = (char)is.read()) != (char)-1) {
          content += ch;
        }
        is.close();
        tokenizer = new StringTokenizer(content, System.getProperty("line.separator"));
        while(tokenizer.hasMoreTokens()) {
          bannedIPs.addElement(tokenizer.nextToken().trim());
        }
      }
      catch (Exception excpt) {
        log(excpt.toString());
      }

      log("Listening on port " + portParam + "...");
      log("Storage folder is " + storageFolder + "...");
      log("Image folder is " + imageFolder + "...");
      log("Room folder is " + roomFolder + "...");
      log("Room filename is " + roomFilename + "...");
      log("Log level is " + logLevel + "...");
      log("Nr of banned ip-addresses read from " +  BANNED_IP_FILENAME + ": " + bannedIPs.size());

      serverThread = new Thread(this);
      serverThread.start();
      log("ChatServer up and running...");
    }
    catch (IOException excpt) {
      log("IOException while starting ChatServer: " + excpt);
    }
  }


/**
 * Runs a thread that listenes on the defined port to new socket connections.
 * Starts a new thread for each Connection.
 */

  public void run() {
    Connection connection;
    while (true) {
      try {
        connection = new Connection(this, listener.accept());
        connection.start();
        connectionVector.addElement(connection);
        log("Opened connection to " + connection.getSocket().getInetAddress());
      }
      catch (IOException excpt) {
        log("IOException while opening a new connection: " + excpt);
      }
    }
  }


/**
 * Handles incoming UserEvents or UserLoginRequests.
 *
 * @param object          the UserEvent or UserLoginRequest
 * @param connection      the Connection that received the UserEvent
 */

  public synchronized void handleUserEvent(Object object, Connection connection) {
    ExistingUserLoginRequest existingUserLogin;
    NewUserLoginRequest newUserLogin;
    UserUpdateEvent userUpdate;
    User user;
    Room room;
    Connection suspendedConnection;
    String userName;

    room = null;

    if (object instanceof UserLoginRequest) {

      if (bannedIPs.contains(connection.getIPAddress())) {
        ((UserLoginRequest)object).status = UserLoginRequest.DENIED;
        ((UserLoginRequest)object).statusString = "IP-Address has been banned";
        log("Login for new user " + ((UserLoginRequest)object).user.getName() + " denied: Banned IP-Address");
        send(object, connection);
      }

      else {
        if (object instanceof ExistingUserLoginRequest) {
          existingUserLogin = (ExistingUserLoginRequest)object;
          log("Login for user " + existingUserLogin.user.getName() + " received");
          if (!exists(existingUserLogin.user.getName()) || !correctPassword(existingUserLogin.user.getName(), existingUserLogin.user.getPassword())) {
            existingUserLogin.status = UserLoginRequest.DENIED;
            if (!exists(existingUserLogin.user.getName())) {
              existingUserLogin.statusString = "User does not exist";
              log("Login for user " + existingUserLogin.user.getName() + " denied: User does not exist");
            }
            else if (!correctPassword(existingUserLogin.user.getName(), existingUserLogin.user.getPassword())) {
              existingUserLogin.statusString = "Wrong password";
              log("Login for user " + existingUserLogin.user.getName() + " denied: Wrong password");
            }
            send(existingUserLogin, connection);
          }
          else {
            log("Login for user " + existingUserLogin.user.getName() + " accepted");
            existingUserLogin.status = UserLoginRequest.ACCEPTED;
            existingUserLogin.statusString = "Everything ok";
            existingUserLogin.user = loadUser(existingUserLogin.user.getName());
            existingUserLogin.user.setPosition(getAvailablePosition(existingUserLogin.user.getRoom()));
            if (isOnline(existingUserLogin.user.getName())) {
              closeConnection(getConnection(getOnlineUserId(existingUserLogin.user.getName())));
            }
            handleAcceptedUserLoginRequest(existingUserLogin, connection);
          }
        }

        else if (object instanceof NewUserLoginRequest) {
          newUserLogin = (NewUserLoginRequest)object;
          log("Login for user " + newUserLogin.user.getName() + " received");
          if (exists(newUserLogin.user.getName())) {
            newUserLogin.status = UserLoginRequest.DENIED;
            newUserLogin.statusString = "User exists already";
            log("Login for new user " + newUserLogin.user.getName() + " denied: Already existing");
            send(newUserLogin, connection);
          }
          else {
            newUserLogin.status = UserLoginRequest.ACCEPTED;
            newUserLogin.statusString = "Everything ok";
            log("Login for new user " + newUserLogin.user.getName() + " accepted");
            newUserLogin.user.setPosition(getAvailablePosition(newUserLogin.user.getRoom()));

            saveUser(newUserLogin.user);
            handleAcceptedUserLoginRequest(newUserLogin, connection);
          }
        }
      }
    }
    else if (object instanceof UserUpdateEvent) {
      userUpdate = (UserUpdateEvent)object;
      log("Update for user " + userUpdate.user.getName() + " received");
      new UserUpdateThread(userUpdate, this, connection).start();
    }

    else if (object instanceof RoomEvent) {
      RoomEvent roomEvent;
      Enumeration userEnum;

      if (object instanceof RoomUpdateEvent || object instanceof RoomCreateEvent) {

        log("Handling room creation / update...");

        if (object instanceof RoomUpdateEvent) {
          roomEvent = (RoomUpdateEvent)object;
          room = ((RoomUpdateEvent)object).room;
        }
        else {
          roomEvent = (RoomCreateEvent)object;
          room = ((RoomCreateEvent)object).room;
          room.setId(getNextAvailableRoomId());
          room.removeAllUsers();
          if (roomExists(room.getName())) {
            int i;
            for (i = 1; roomExists(room.getName() + i); i++);
            room.setName(room.getName() + i);
          }
        }

        addRoom(room);
        broadcast(roomEvent);

        userEnum = onlineTable.elements();
        while(userEnum.hasMoreElements()) {
          user = (User)userEnum.nextElement();
          if (user.getRoom() == room.getId() && !room.hasAccess(user.getName())) {
            user.setRoom(0);
            user.setPosition(getAvailablePosition(0));
            addOnlineUser(user);
            broadcast(new UserRoomEvent(user.getId(), user.getRoom(), user.getPosition()));
          }
        }
      }
      else if (object instanceof RoomRemoveEvent) {
        room = getRoom(((RoomRemoveEvent)object).roomId);
        if (room != null) {
          removeRoom(room.getId());
          broadcast(object);
        }
      }
      saveRooms();
    }

    else if (object instanceof UserEvent) {
      boolean broadcastExcluding;
      broadcastExcluding = true;

      user = getOnlineUser(((UserEvent)object).userId);
      if (user != null) {
        if (object instanceof UserPositionEvent) {
          if (!collides(user.getRoom(), ((UserPositionEvent)object).userPosition, user.getId()))
            user.setPosition(((UserPositionEvent)object).userPosition);
          else {
            ((UserPositionEvent)object).userPosition = user.getPosition();
            broadcastExcluding = false;
          }
        }
        else if (object instanceof UserHeadingEvent)
          user.setHeading(((UserHeadingEvent)object).userHeading);
        else if (object instanceof UserMessageEvent) {
          user.setMessage(((UserMessageEvent)object).userMessage);
          log("User " + user.getName() + " says " + user.getMessage());
        }
        else if (object instanceof UserMoodEvent)
          user.setMood(((UserMoodEvent)object).userMood);
        else if (object instanceof UserRoomEvent) {
          room = getRoom(((UserRoomEvent)object).roomId);
          if (room != null) {
            getRoom(room.getId()).removeUser(user.getName());
            user.setRoom(room.getId());
            room.addUser(user.getName());
            user.setPosition(getAvailablePosition(room.getId()));
            ((UserRoomEvent)object).position = user.getPosition();
          }
          broadcastExcluding = false;
        }
        if (object instanceof UserLogoutEvent)
          closeConnection(connection);
        else {
          addOnlineUser(user);
          if (broadcastExcluding)
            broadcastExcluding(object, connection);
          else
            broadcast(object);
        }
      }
    }

    else if (object instanceof ServerAdministrationRequest) {
      if (((ServerAdministrationRequest)object).key.equals(key)) {
        ((ServerAdministrationRequest)object).status = ServerAdministrationRequest.ACCEPTED;

        if (object instanceof ServerStopRequest) {
          Enumeration connectionEnum;
          Connection closingConnection;
          String[] command;

          try {
            log("Stopping server...");

            listener.close();
            serverThread.stop();

            connectionEnum = connectionVector.elements();
            while (connectionEnum.hasMoreElements()) {
              closingConnection = (Connection)connectionEnum.nextElement();
              if (closingConnection.getUserId() != 0)
                closeConnection(closingConnection);
            }

            Runtime.getRuntime().exit(0);

          }
          catch (IOException excpt) {
            log("Exception while stopping server: " + excpt);
          }
        }
        else if (object instanceof ServerUserListRequest) {
          log("Sending user list...");
          ((ServerUserListRequest)object).userListVector = getUserListVector();
          send(object, connection);
        }
        else if (object instanceof ServerGetUserDataRequest) {
          log("Retrieving data for user " + ((ServerGetUserDataRequest)object).user.getName());
          user = loadUser(((ServerGetUserDataRequest)object).user.getName());
          if (user != null) {
            ((ServerGetUserDataRequest)object).user.setPassword(user.getPassword());
            ((ServerGetUserDataRequest)object).user.setEmail(user.getEmail());
          }
          else {
            log("Error: User " + ((ServerGetUserDataRequest)object).user.getName() + " doesn't exist");
            ((ServerGetUserDataRequest)object).status = ServerAdministrationRequest.ERROR_OCCURED;
          }
          send(object, connection);
        }
        else if (object instanceof ServerUpdateUserDataRequest) {
          log("Updating data for user " + ((ServerUpdateUserDataRequest)object).user.getName());
          user = loadUser(((ServerUpdateUserDataRequest)object).user.getName());
          if (user != null) {
            user.setPassword(((ServerUpdateUserDataRequest)object).user.getPassword());
            user.setEmail(((ServerUpdateUserDataRequest)object).user.getEmail());
            saveUser(user);
            if (isOnline(user.getName()))
              handleUserUpdateEvent(new UserUpdateEvent(user), getConnection(user.getId()));
          }
          else {
            log("Error: User " + ((ServerUpdateUserDataRequest)object).user.getName() + " doesn't exist");
            ((ServerGetUserDataRequest)object).status = ServerAdministrationRequest.ERROR_OCCURED;
          }
          send(object, connection);
        }
        else if (object instanceof ServerDeleteUserRequest) {
          log("Deleting user " + ((ServerDeleteUserRequest)object).userName);
          userName = ((ServerDeleteUserRequest)object).userName;
          if (exists(userName)) {
            if (isOnline(userName))
              closeConnection(getConnection(getOnlineUserId(userName)));
            deleteUser(userName);
          }
          else {
            log("Error: User " + ((ServerDeleteUserRequest)object).userName + " doesn't exist");
            ((ServerDeleteUserRequest)object).status = ServerAdministrationRequest.ERROR_OCCURED;
          }
          send(object, connection);
        }
      }
      else {
        log("Wrong server key");
        ((ServerAdministrationRequest)object).status = ServerAdministrationRequest.DENIED;
        send(object, connection);
      }
    }
  }


/**
 * Handles a UserLoginRequest that has been accepted.
 *
 * @param loginRequest      the UserLoginRequest
 * @param connection        the Connection that received the UserLoginRequest
 */

  public synchronized void handleAcceptedUserLoginRequest(UserLoginRequest loginRequest, Connection connection) {
    User user;
    Room room;
    UserLoginEvent loginEvent;
    Enumeration userEnum, roomEnum;

    loginRequest.user.setId(getNextAvailableUserId());
    loginRequest.user.setRoom(0);
    loginRequest.user.setLoginDate(new Date());
    addOnlineUser(loginRequest.user);
    connection.setUserId(loginRequest.user.getId());
    send(loginRequest, connection);
    /*
    roomEnum = roomTable.elements();

    while(roomEnum.hasMoreElements()) {
      room = (Room)((Room)roomEnum.nextElement()).clone();
      room.removeAllUsers();
      if (room.getId() != 0)
        send(new RoomCreateEvent(room), connection);
    }
    */

    send(new RoomListEvent(roomTable), connection);

    userEnum = onlineTable.elements();

    while(userEnum.hasMoreElements()) {
      user = (User)userEnum.nextElement();
      if (user.getId() != loginRequest.user.getId()) {
        send(new UserLoginEvent(user), connection);
      }
    }

    loginEvent = new UserLoginEvent(loginRequest.user);
    broadcastExcluding(loginEvent, connection);

  }


/**
 * Handles an UserUpdateEvent.
 *
 * @param updateEvent      the UserUpdateEvent
 * @param connection       the Connection that received the UserUpdateEvent
 */

  public synchronized void handleUserUpdateEvent(UserUpdateEvent updateEvent, Connection connection) {
    log("Handling user update...");
    addOnlineUser(updateEvent.user);
    send(updateEvent, connection);
    updateEvent.user.setPassword("");
    updateEvent.statusString = "";
    broadcastExcluding(updateEvent, connection);
  }


/**
 * Handles an RoomUpdateEvent.
 *
 * @param updateEvent      the RoomUpdateEvent
 * @param connection       the Connection that received the UserUpdateEvent
 */

  public synchronized void handleRoomUpdateEvent(RoomUpdateEvent updateEvent, Connection connection) {
    User user;
    Room room;
    Enumeration userEnum;

    log("Handling room update...");
    room = updateEvent.room;

    addRoom(room);
    broadcast(updateEvent);
    userEnum = onlineTable.elements();

    while(userEnum.hasMoreElements()) {
      user = (User)userEnum.nextElement();
      if (user.getRoom() == room.getId() && ((room.isPrivate() && !room.getInvitedUsers().contains(user.getName())) || (!room.isPrivate() && room.getKickedUsers().contains(user.getName())))) {
        user.setRoom(0);
        user.setPosition(getAvailablePosition(0));
        addOnlineUser(user);
        broadcast(new UserRoomEvent(user.getId(), user.getRoom(), user.getPosition()));
      }
    }
  }


/**
 * Handles an RoomRemoveEvent.
 *
 * @param updateEvent      the RoomRemoveEvent
 * @param connection       the Connection that received the UserUpdateEvent
 */

  public synchronized void handleRoomRemoveEvent(RoomRemoveEvent removeEvent, Connection connection) {
    User user;
    Room room;
    Enumeration userEnum;

    log("Handling room removal...");

    if ((room = (Room)roomTable.get(removeEvent.roomId)) != null) {

      userEnum = onlineTable.elements();

      while(userEnum.hasMoreElements()) {
        user = (User)userEnum.nextElement();
        if (user.getRoom() == room.getId()) {
          user.setRoom(0);
          user.setPosition(getAvailablePosition(0));
          addOnlineUser(user);
          broadcast(new UserRoomEvent(user.getId(), user.getRoom(), user.getPosition()));
        }
      }

      broadcast(removeEvent);
    }
  }


/**
 * Broadcasts a UserEvent over all except one Connection.
 *
 * @param broadcastObject         the UserEvent to be broadcasted
 * @param excludeConnection       the Connection to be excluded from the broadcast
 */

  public synchronized void broadcastExcluding(Object broadcastObject, Connection excludeConnection) {
    Enumeration connectionEnum;
    Connection broadcastConnection;

    log("Broadcasting...", LOGLEVEL3);

    connectionEnum = connectionVector.elements();
    while (connectionEnum.hasMoreElements()) {
      broadcastConnection = (Connection)connectionEnum.nextElement();
      if (broadcastConnection.getUserId() != 0 && broadcastConnection != excludeConnection)
        broadcastConnection.send(broadcastObject);
    }
  }


/**
 * Broadcasts a UserEvent over all Connections.
 *
 * @param broadcastObject      the UserEvent to be broadcasted
 */

  public synchronized void broadcast(Object broadcastObject) {
    broadcastExcluding(broadcastObject, null);
  }


/**
 * Sends a UserEvent or UserLoginRequest over one connection.
 *
 * @param broadcastObject      the UserEvent to be broadcasted
 */

  public synchronized void send(Object broadcastObject, Connection connection) {
    log("Sending...", LOGLEVEL3);
    connection.send(broadcastObject);
  }


/**
 * Closes a Connection.
 *
 * @param connection      the Connection to be closed
 */

  public void closeConnection(Connection connection) {
    log("Closing connection...");

    try {
      if (connection.getUserId() != 0) {
        broadcastExcluding(new UserLogoutEvent(connection.getUserId()), connection);
        getRoom(getOnlineUser(connection.getUserId()).getRoom()).removeUser(getOnlineUser(connection.getUserId()).getName());
      }
    }
    catch (Exception excpt) {
      log("Exception while closing connection: " + excpt);
    }
    removeOnlineUser(connection.getUserId());
    connectionVector.removeElement(connection);
    connection.close();
  }


/**
 * Returns the connection of a certain User currently being logged in.
 *
 * @param userIdParam      the User's id
 */

  public synchronized Connection getConnection(int userIdParam) {
    Enumeration connectionEnum;
    Connection connection;

    connectionEnum = connectionVector.elements();

    while (connectionEnum.hasMoreElements()) {
      connection = (Connection)connectionEnum.nextElement();
      if (connection.getUserId() == userIdParam)
        return connection;
    }

    return null;
  }


/**
 * Returns true if a Room of a given name exists.
 *
 * @param roomNameParam      the Room's name
 */

  public synchronized boolean roomExists(String roomNameParam) {
    Enumeration roomEnum;

    roomEnum = roomTable.elements();

    while(roomEnum.hasMoreElements()) {
      if (((Room)roomEnum.nextElement()).getName().equals(roomNameParam))
        return true;
    }
    return false;
  }


/**
 * Returns the true if a certain User is curently logged in.
 *
 * @param userIdParam      the User's id
 */

  public boolean isOnline(int userIdParam) {
    return onlineTable.containsKey(userIdParam);
  }


/**
 * Returns the true if a certain User is curently logged in.
 *
 * @param userNameParam      the User's name
 */

  public boolean isOnline(String userNameParam) {
    return getOnlineUserId(userNameParam) != -1;
  }


/**
 * Returns dynamic id of a User currently online or -1 if he is not online.
 *
 * @param userNameParam      the User's name
 */

  public int getOnlineUserId(String userNameParam) {
    Enumeration userEnum;
    User user;

    userEnum = onlineTable.elements();

    while(userEnum.hasMoreElements()) {
      if (((user = (User)userEnum.nextElement())).getName().equals(userNameParam))
        return user.getId();
    }
    return -1;
  }


/**
 * Returns the true if a User's name exists already.
 *
 * @param userNameParam      the User's name
 */

  public boolean exists(String userNameParam) {
    return new File(storageFolder + userNameParam).exists();
  }


/**
 * Stores a User to the ChatServer's local file system. The User's name will be
 * used as the filename.
 *
 * @param userParam      the User to be stored
 */

  public void saveUser(User userParam) {
    save(userParam, storageFolder + userParam.getName());
  }


/**
 * Loads a User from the ChatServer's local file system.
 *
 * @param userNameParam      the name of the User to be loaded
 */

  public User loadUser(String userNameParam) {
    Object loadedObject;
    loadedObject = load(storageFolder + userNameParam);
    if (loadedObject instanceof User)
      return (User)loadedObject;
    else
      return null;
  }


/**
 * Deletes a User from the ChatServer's local file system.
 *
 * @param userNameParam      the name of the User to be loaded
 */

  private synchronized void deleteUser(String userNameParam) {
    new File(storageFolder + userNameParam).delete();
  }


/**
 * Returns the next available User id. The id will be created dynamically.
 */

  public synchronized int getNextAvailableUserId() {
    int id;
    do {
      id = rand.nextInt();
    } while (onlineTable.containsKey(id));
    return id;
  }


/**
 * Returns the next available Room id. The id will be created dynamically.
 */

  public synchronized int getNextAvailableRoomId() {
    return roomTable.getMaxKey() + 1;
  }


/**
 * Returns whether a password is correct or not.
 */

  public boolean correctPassword(String userNameParam, String userPasswordParam) {
    Object loadedObject;
    loadedObject = loadUser(userNameParam);
    if (loadedObject instanceof User)
      return ((User)loadedObject).getPassword().equals(userPasswordParam);
    else
      return false;
  }


/**
 * Stores an object under a given filename to the ChatServer's local file system.
 *
 * @param objectParam      the object to be stored
 * @param fileName         the filename under which to store the object
 */

  public synchronized void save(Object objectParam, String fileName) {
    ObjectOutputStream output;
    try {
      output = new ObjectOutputStream(new FileOutputStream(fileName));
      output.writeObject(objectParam);
      output.close();
    }
    catch (IOException excpt) {
      log("IOException while writing file " + fileName);
    }
  }


/**
 * Loads an object from the ChatServer's local file system.
 *
 * @param fileName      the filename under which the object is stored
 */

  public synchronized Object load(String fileName) {
    ObjectInputStream input;
    Object object;
    object = null;

    try {
      input = new ObjectInputStream(new FileInputStream(fileName));
      object = input.readObject();
      input.close();
    }
    catch (ClassNotFoundException excpt) {
      log("ClassNotFoundException while reading file " + fileName);
    }
    catch (IOException excpt) {
      log("IOException while reading file " + fileName);
    }
    return object;
  }


/**
 * Adds a User to the list of currently logged in users.
 *
 * @param userParam      the User to be added
 */

  public void addOnlineUser(User userParam) {
    onlineTable.put(userParam.getId(), userParam);
  }


/**
 * Removes a User from the list of currently logged in users.
 *
 * @param idParam      the id of the User to be removed
 */

  public void removeOnlineUser(int idParam) {
    onlineTable.remove(idParam);
  }


/**
 * Adds a Room.
 *
 * @param roomParam      the Room to be added
 */

  public void addRoom(Room roomParam) {
    roomTable.put(roomParam.getId(), roomParam);
  }


/**
 * Removes a Room.
 *
 * @param idParam      the id of the Room to be removed
 */

  public void removeRoom(int idParam) {
    roomTable.remove(idParam);
  }


/**
 * Returns a User from the list of currently logged in users.
 *
 * @param idParam      the id of the User to be returned
 */

  public User getOnlineUser(int idParam) {
    Object object;
    object = onlineTable.get(idParam);
    if (object != null)
      return (User)object;
    else
      return null;
  }


/**
 * Returns a Room.
 *
 * @param idParam      the id of the Room to be returned
 */

  public Room getRoom(int idParam) {
    Object object;
    object = roomTable.get(idParam);
    if (object != null)
      return (Room)object;
    else
      return null;
  }


/**
 * Returns a free position in a certain Room to place the User after logging in.
 *
 * @param roomid      the number of the Room to place the User
 */

  public synchronized Point getAvailablePosition(int roomId) {
    Point position, userPosition;
    Enumeration userEnum;

    do {
      position = new Point((int)(rand.nextDouble() * (double)ChatRepository.ROOM_DIMENSION.width - 2.0) + 1, (int)(rand.nextDouble() * (double)ChatRepository.ROOM_DIMENSION.height - 2.0) + 1);
    }
    while (collides(roomId, position, -1));

    return position;
  }


/**
 * Checks whether a Point in a Room collides with a User being placed there.
 *
 * @param roomid             the number of the Room
 * @param position           the position to be checked
 * @param excludeUserId      exclude the position of this User
 */

  public boolean collides(int roomId, Point position, int excludeUserId) {
    Enumeration userEnum;
    User user;
    userEnum = onlineTable.elements();
    while (userEnum.hasMoreElements()) {
      if ((user = (User)userEnum.nextElement()).getRoom() == roomId && user.getId() != excludeUserId) {
        if (new Rectangle(user.getPosition().x - ChatRepository.USER_SIZE / 2 - ChatRepository.MINIMUM_DISTANCE, user.getPosition().y - ChatRepository.USER_SIZE / 2 - ChatRepository.MINIMUM_DISTANCE, ChatRepository.USER_SIZE + ChatRepository.MINIMUM_DISTANCE * 2, ChatRepository.USER_SIZE + ChatRepository.MINIMUM_DISTANCE * 2).contains(position))
          return true;
      }
    }
    return false;
  }

/**
 * Writes a timestamp and logging information to the standard output.
 *
 * @param logText      the text to be logged
 */

  public synchronized void log(String logText) {
    log(logText, STANDARD_LOGLEVEL);
  }


/**
 * Writes a timestamp and logging information to the standard output.
 *
 * @param logText      the text to be logged
 * @param level        the logLevel
 */

  public synchronized void log(String logText, int level) {
    if (level <= logLevel) {
      System.out.println(DateFormat.getDateInstance(DateFormat.SHORT).format(new Date()) + " " + DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date()) + ": " + logText);
    }
  }


/**
 * Returns the folder where images should be stored.
 */

  public String getImageFolder() {
    return imageFolder;
  }


/**
 * Returns a Vector which contains the names of all Users that are stored.
 */

  public Vector getUserListVector() {
    String[] userFileList;
    Vector userListVector;

    userListVector = new Vector();

    userFileList = new File(storageFolder).list();
    for (int i = 0; i < userFileList.length; i++)
      userListVector.addElement(userFileList[i]);

    return userListVector;

  }


/**
 * Creates the standard Rooms. Is being called after starting the ChatServer for
 * the first time.
 */

  private void createStandardRooms() {
    Room room;
    roomTable = new IntegerHashtable();
    for (int i = 0; i < ChatRepository.STANDARD_ROOM_NAME.length; i++) {
      room = new Room(i, ChatRepository.STANDARD_ROOM_NAME[i], ChatRepository.ROOM_DIMENSION);
      room.setPrivate(ChatRepository.STANDARD_ROOM_PRIVATE[i]);
      room.setAdministrator(ChatRepository.ADMIN);
      addRoom(room);
    }
    log("Created standard rooms...");
    saveRooms();
  }


/**
 * Persistently stores all the Rooms to the server's file system.
 */

  private void saveRooms() {
    save(roomTable, roomFolder + roomFilename);
    log("Saved rooms...");
  }

}