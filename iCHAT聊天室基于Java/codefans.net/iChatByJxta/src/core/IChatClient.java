/*
 * @(#)IChatClient.java
 * Created on 2005-9-12
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

import java.net.InetAddress;

/**
 * This class defines the basic behavior
 * that a chat client should has.<br><br>
 * For current assumption, the client acts
 * also as a server, that is, this chat program
 * is peer to peer. What's more, the communication
 * is exclusive - you cannot chat with two guys
 * using a single client.
 * 
 * @author Allen Chue
 */
public interface IChatClient {
    /**
     * Sends a message to another client
     * that is connected to this client.
     * @param message the message to send
     * @return whether or not this message is successfully sent
     */
    public boolean sendMessage(String message);
    
    /**
     * Displays the received message.
     */
    public void displayReceivedMessage();
    
    /**
     * Connects to a friend using the specified host name.
     * @param host the name of the friend computer (either
     * IP address of Computer name).
     * @param selfName the nickname you use
     * @return whether this connecting operation succeeds
     */
    public boolean connectToFriend(String host, String selfName);
    
    /**
     * Opens the port to receive messages.
     * @return whether this operaton succeeds
     */
    public boolean openPort();
    
    /**
     * Returns the address of the client that
     * is connected to this client in the
     * <code>InetAddress</code> form.
     * @return the friend's IP address
     */
    public InetAddress getFriendAddress();
}
