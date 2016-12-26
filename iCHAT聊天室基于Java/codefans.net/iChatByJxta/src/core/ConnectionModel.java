/*
 * @(#)ConnectionManager.java
 * Created on 2005-9-14
 * iChat LE. Copyright AllenStudio. All Rights Reserved
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This implementation of <code>ChatClient</code>
 * interface uses {@link java.net.Socket} to send
 * and receive messages.<br>
 * This class is a background model for setting up 
 * connection and data transmission.<br>
 * <em>Note: for special reasons, this class is a
 * singleton</em>
 * 
 * @version 1.0 Sep 14, 2005
 * @author Allen Chue
 */
public class ConnectionModel implements IChatClient {

    public static final int SERVER_PORT = 1129;
    
    private static ConnectionModel instance = null;
    
    private ConnectionListener connectionListener;
    private MessageListener messageListener;
    private Thread daemonMessageChecker;

    /** Socket to receive messages (as a server) */
    private ServerSocket serverSocket;
    
    /** Socket for other client to connect to it */
    private Socket connectionSocket;
    
    /** Socket to send messages (as a client) */
    private Socket clientSocket;
    
    /** Stream to receive input from a remote friend */
    private DataInputStream inputFromFriend;
    
    /** Stream to send output to a remote friend */
    private DataOutputStream outputToFriend;
    
    private boolean isConnectedAsServer = false;
    private boolean isConnectedAsClient = false;
    
    private ConnectionModel() throws RuntimeException {
        if (!openPort()) {
            throw new RuntimeException("Cannot open the server port!");
        }
    }
    
    
    /* (non-Javadoc)
     * @see core.IChatClient#sendMessage(java.lang.String)
     */
    public boolean sendMessage(String message) {
        return false;
    }

    /* (non-Javadoc)
     * @see core.IChatClient#displayReceivedMessage()
     */
    public void displayReceivedMessage() {
    }

    /**
     * Connects to a remote friend by the host name.
     * @param host the name or IP address of remote client
     * @return true if connected successfully
     * @see core.IChatClient#connectToFriend(java.lang.String)
     */
    public boolean connectToFriend(String host, String selfName) {
        try {
            clientSocket = new Socket(host, SERVER_PORT);
            //Init the output stream
            outputToFriend = new DataOutputStream(
                    clientSocket.getOutputStream());
            
            send(selfName);
            
            fireConnectionAsClientChanged(true);            
            return true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens the port to accept remote connection.
     * @see core.IChatClient#openPort()
     */
    public boolean openPort() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* (non-Javadoc)
     * @see core.IChatClient#getFriendAddress()
     */
    public InetAddress getFriendAddress() {
        if (connectionSocket != null) {
            return connectionSocket.getInetAddress();
        }
        return null;
    }
    
    /**
     * Returns if this client is connected as a client
     * @return true if connected
     */
    public boolean isConnectedAsClient() {
        return isConnectedAsClient;
    }

    /**
     * Returns if this client is connected as a server,
     * that is, it is connected by a remote client.
     * @return true if connected
     */
    public boolean isConnectedAsServer() {
        return isConnectedAsServer;
    }
        
    /**
     * Accepts remote connection. This method is synchronized
     * since it is not supposed more than one clients try to
     * connect a single port. (iChat LE is currently peer to
     * peer, and exclusive).
     * @return true if a remote client connects to this client
     * successfully, false otherwise
     */
    public synchronized boolean acceptConnection() {
        if (serverSocket == null) {
            return false;
        }
        else {
            try {
                connectionSocket = serverSocket.accept();
                
                //Init input stream
                inputFromFriend = new DataInputStream(
                        connectionSocket.getInputStream());                
                
                fireConnectionAsServerChanged(true);
                return true;
            }
            catch (IOException e) {
                return false;
            }
        }
    }
    
    /**
     * Adds a <code>ConnectionListener</code> to this model.
     * Addition to existing listener has no effect.
     * @param l the listener to be added
     */
    public void addConnectionListener(ConnectionListener l) {
        if (connectionListener == null) {
            connectionListener = l;
        }
    }
    
    /**
     * Removes the <code>ConnectionListener</code> attached
     * to this model, if any.
     */
    public void removeConnectionListener() {
        connectionListener = null;
    }
    
    /**
     * Adds a <code>MessageListener</code> to this model.
     * Addition to existing listener has no effect.
     * @param l the listener to be added
     */
    public void addMessageListener(MessageListener l) {
        if (messageListener == null) {
            daemonMessageChecker = new Thread() {
                public void run() {
                    try {
                        String s = null;
                        while (true) {
                            if ((s = ConnectionModel.this.receive()) != null) {
                                fireMessageReceived(s);
                            }
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            daemonMessageChecker.setPriority(3);
            daemonMessageChecker.start();
            messageListener = l;
        }
    }
    
    /**
     * Removes the <code>MessageListener</code> attached
     * to this model, if any.
     */
    public void removeMessageListener() {
        messageListener = null;
    }
    
    /**
     * Sends a <code>String</code> s.
     * @param s the String to send
     */
    public void send(String s) {
        if (outputToFriend != null) {
            try {
                outputToFriend.writeUTF(s);
                outputToFriend.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String receive() {
        if (inputFromFriend != null) {
            try {
                return inputFromFriend.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static ConnectionModel getInstance() throws RuntimeException {
        if (instance == null) {
            instance = new ConnectionModel();
            return instance;
        }
        else return instance;
    }

    /**
     * Subclasses may derive this method to change the
     * behavior when the connection as a server changes.
     * @param b true if the connection succeeds, false otherwise
     */
    protected void fireConnectionAsServerChanged(boolean b) {
        isConnectedAsServer = b;
        
        if (connectionListener != null) {
            connectionListener.connectionAsServerChanged(b);
        }
    }
    
    /**
     * Subclasses may derive this method to change the
     * behavior when the connection as a client changes.
     * @param b true if the connection succeeds, false otherwise
     */
    protected void fireConnectionAsClientChanged(boolean b) {
        isConnectedAsClient = b;
        
        if (connectionListener != null) {
            connectionListener.connectionAsClientChanged(b);
        }
    }
    
    /**
     * Subclasses may derive this method to change the
     * behavior when a message from remote friend is received.
     * @param s the message received
     */
    protected void fireMessageReceived(String s) {
        if (messageListener != null) {
            messageListener.messageReceived(s);
        }
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

}
