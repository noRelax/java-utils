package at.ac.uni_linz.tk.vchat;

import java.util.*;

/**
 * Represents a request for obtaining a list of all User names.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class ServerUserListRequest extends ServerAdministrationRequest {

  public Vector userListVector;


/**
 * Constructs the ServerUserListRequest.
 *
 * @param keyParam       the server's key which was applied when starting the
 *                       ChatServer
 */

  public ServerUserListRequest(String keyParam) {
    super(keyParam);
    userListVector = new Vector();
  }

}