package at.ac.uni_linz.tk.vchat;

import java.io.*;
import java.net.*;


/**
 * A Thread running in the ChatServer's environment handling a UserUpdateEvent.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class UserUpdateThread extends Thread {

  private ChatServer server;
  private Connection connection;
  private UserUpdateEvent updateEvent;
  private User oldUser;
  private int overallAvatarSize;


/**
 * Constructs the Connection.
 *
 * @param updateEventParam      the UserUpdateEvent produced by a User
 * @param serverParam           the ChatServer, which initiated the
 *                              UserUpdateThread
 */

  public UserUpdateThread(UserUpdateEvent updateEventParam, ChatServer serverParam, Connection connectionParam) {
    server = serverParam;
    connection = connectionParam;
    updateEvent = updateEventParam;
    oldUser = server.loadUser(updateEvent.user.getName());
  }


/**
 * Runs the UserUpdateThread, which load the User's new avatars over the network.
 */

  public void run() {

    server.log("Updating user avatars...");

    overallAvatarSize = 0;

    retrieveImage(-1, updateEvent.user.getBackAvatarURL());
    for (int i = 0; i < updateEvent.user.getNrOfMoods(); i++)
      retrieveImage(i, updateEvent.user.getAvatarURL(i));

    server.saveUser(updateEvent.user);
    server.handleUserUpdateEvent(updateEvent, connection);
  }


/**
 * Controls the retrieving process for an user avatar. The loading of the image itself is
 * done by getImage().
 *
 * @param portraitIndex      the Avatar's index
 * @param imageURL           the Image's URL or filename
 */

  private void retrieveImage(int portraitIndex, String imageName) {
    if (imageName != null && !imageName.equals("") && (portraitIndex >= oldUser.getNrOfMoods() || !imageName.equals(portraitIndex == -1 ? oldUser.getBackAvatarURL() : oldUser.getAvatarURL(portraitIndex)))) {
      try {
        getImage(portraitIndex, imageName, true);
      }
      catch (Exception excpt1) {
        server.log("Exception while updating avatar - URL: " + excpt1 + ". Trying to find user avatar on server.");
        try {
          getImage(portraitIndex, imageName, false);
        }
        catch (Exception excpt2) {
          server.log("Exception while updating avatar - File: " + excpt2);
          resetPortrait(portraitIndex);
        }
      }
    }
    else if (imageName == null || imageName.equals("")) {
      if (portraitIndex == -1) {
        updateEvent.user.setBackAvatarURL("");
        updateEvent.user.setBackAvatar(null);
      }
      else {
        updateEvent.user.setAvatarURL(portraitIndex, "");
        updateEvent.user.setAvatar(portraitIndex, null);
      }
    }
    else {
      resetPortrait(portraitIndex);
    }
    // server.log("Overall avatar size so far: " + overallAvatarSize + " bytes");
  }


/**
 * Resets a User's Image to the most recent one that was stored.
 *
 * @param portraitIndex      the Image's index (equals the mood it is related to)
 */

  private void resetPortrait(int portraitIndex) {
    if (portraitIndex == -1) {
      resetBackPortrait();
    }
    else {
      updateEvent.user.setAvatarURL(portraitIndex, oldUser.getNrOfMoods() > portraitIndex ? oldUser.getAvatarURL(portraitIndex) : "");
      updateEvent.user.setAvatar(portraitIndex, oldUser.getNrOfMoods() > portraitIndex ? oldUser.getAvatar(portraitIndex) : null);
    }
    resetoverallAvatarSize(portraitIndex);
  }


/**
 * Resets a User's Back-Image to the most recent one that was stored.
 */

  private void resetBackPortrait() {
    updateEvent.user.setBackAvatarURL(oldUser.getBackAvatarURL());
    updateEvent.user.setBackAvatar(oldUser.getBackAvatar());
  }


/**
 * Resets the overall user-file size depending on the size of the actual image.
 *
 * @param portraitIndex      the Image's index (equals the mood it is related to)
 */

  private void resetoverallAvatarSize(int portraitIndex) {
    String imageURL;
    imageURL = (portraitIndex == -1 ? updateEvent.user.getBackAvatarURL() : updateEvent.user.getAvatarURL(portraitIndex));
    try {
      overallAvatarSize += new URL(updateEvent.user.getAvatarURL(portraitIndex)).openConnection().getContentLength();
    }
    catch (Exception excpt1) {
      try {
        overallAvatarSize += new File(server.getImageFolder() + imageURL).length();
      }
      catch (Exception excpt2) {
      }
    }
  }


/**
 * Loads an Image from a WebServer or the local filesystem.
 *
 * @param portraitIndex      the Avatar's index
 * @param imageName          the Avatar's URL or filename
 * @param isURL              true if imageName is an URL, false if not
 */

  private void getImage(int portraitIndex, String imageName, boolean isURL) throws Exception {
    long size;
    size = isURL ? new URL(imageName).openConnection().getContentLength() : new File(server.getImageFolder() + imageName).length();
    if (size > 0) {
      if (size <= ChatRepository.MAX_PORTRAIT_SIZE) {
        if (overallAvatarSize + size < ChatRepository.MAX_USERFILE_SIZE) {
          if (portraitIndex == -1) {
            if (isURL)
              updateEvent.user.setBackAvatar(ChatUtil.getImage(new URL(imageName)));
            else
              updateEvent.user.setBackAvatar(ChatUtil.getImage(server.getImageFolder() + imageName));
          }
          else {
            if (isURL)
              updateEvent.user.setAvatar(portraitIndex, ChatUtil.getImage(new URL(imageName)));
            else
              updateEvent.user.setAvatar(portraitIndex, ChatUtil.getImage(server.getImageFolder() + imageName));
          }
          overallAvatarSize += size;
          server.log("Updated avatar to new " + (isURL ? "URL" : "file") + ": " + imageName);
        }
        else {
          server.log("With avatar " + imageName + " the overall user-file size exceeds maximum file length");
          updateEvent.statusString = "With avatar " + imageName + " the overall user-file size exceeds maximum file length";
          resetPortrait(portraitIndex);
        }
      }
      else {
        server.log("Avatar " + imageName + " exceeds maximum size");
        updateEvent.statusString = "Avatar " + imageName  + " exceeds maximum file length";
        resetPortrait(portraitIndex);
      }
    }
    else {
      server.log("Avatar " + imageName + " doesn't exist");
      updateEvent.statusString = "Avatar " + imageName  + " doesn't exist";
      resetPortrait(portraitIndex);
    }
  }
}
