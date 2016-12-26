package at.ac.uni_linz.tk.vchat;

import java.io.*;


/**
 * Represents a changement of a User's data.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class UserUpdateEvent implements Serializable {

  public User user;
  public String statusString;


/**
 * Constructs the UserUpdateEvent.
 *
 * @param userParam      the User's new data
 */

  public UserUpdateEvent (User userParam) {
    user = (User)(userParam.clone());
    statusString = "";

    user.setBackAvatar(null);
    for (int i = 0; i < user.getNrOfMoods(); i++)
      user.setAvatar(i, null);
  }
}