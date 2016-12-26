package at.ac.uni_linz.tk.vchat;

/**
 * Represents a logout of a User.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class UserLogoutEvent extends UserEvent  {


/**
 * Constructs the UserLogoutEvent.
 *
 * @param userParam      the User who logged out
 */

  public UserLogoutEvent(int idParam) {
    super(idParam);
  }
}
