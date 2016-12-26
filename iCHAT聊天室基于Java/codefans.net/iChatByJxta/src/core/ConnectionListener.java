/*
 * @(#)ConnectionListener.java
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
/**
 * Listenes changes of connection between
 * two clients.
 * @author Allen Chue
 */
public interface ConnectionListener {
    

    /**
     * Invoked when a client is connected or disconnected
     * by a remote computer, that is, it acts as a server.
     * @param b true if connected, false otherwise
     */
    public void connectionAsServerChanged(boolean b);

    /**
     * Invoked when this client connects a remote client
     * successfully or disconnects from the remote target.
     * @param b true if connected, false otherwise
     */
    public void connectionAsClientChanged(boolean b);
}
