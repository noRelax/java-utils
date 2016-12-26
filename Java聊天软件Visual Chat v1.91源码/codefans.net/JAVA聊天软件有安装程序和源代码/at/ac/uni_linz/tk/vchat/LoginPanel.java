package at.ac.uni_linz.tk.vchat;

import java.awt.*;
import java.awt.event.*;


/**
 * This Panel is displayed in the original browser window. It is being used for User
 * authentification.
 *
 * @author      Arno Huetter
 * (C)opyright by the Institute for Computer Science, Telecooperation Department, University of Linz
 */

public class LoginPanel extends Panel implements Runnable, ActionListener, ItemListener {

  private Thread checkThread;

  private ChatApplet chatApplet;

  private FramedPanel connectionPanel, userPanel, loginAsPanel;
  private InsetsPanel buttonPanel;
  private TextField nameTextField, passwordTextField1, passwordTextField2, portTextField;
  private Label hostLabel1, hostLabel2, portLabel, nameLabel, passwordLabel1, passwordLabel2, statusLabel;
  private CheckboxGroup loginCheckboxGroup;
  private Checkbox newUserCheckbox, existingUserCheckbox;
  private Button connectButton, disconnectButton;

  public static final int MODE_DEFAULT = 0;
  public static final int MODE_NEW_USERS = 1;
  public static final int MODE_EXISTING_USERS = 2;

/**
 * Constructs the LoginPanel.
 *
 * @param chatParam      the ChatApplet which administrates the
 *                                users
 */

  public LoginPanel(ChatApplet chatParam, int mode) {

    GridBagConstraints constraints;
    chatApplet = chatParam;

    connectionPanel = new FramedPanel("Connection", ChatRepository.INSETS);
    connectionPanel.setLayout(new GridBagLayout());

    hostLabel1 = new Label("Host:", Label.RIGHT);
    portLabel = new Label("Port:", Label.RIGHT);
    hostLabel2 = new Label(chatApplet.getHost());
    portTextField = new TextField(new Integer(chatApplet.getDefaultPort()).toString(), 5);

    ChatUtil.addWithBeginningConstraints(connectionPanel, hostLabel1);
    ChatUtil.addWithRemainingConstraints(connectionPanel, hostLabel2);

    ChatUtil.addWithBeginningConstraints(connectionPanel, portLabel);
    constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.WEST;
    ChatUtil.addWithConstraints(connectionPanel, portTextField, constraints);

    userPanel = new FramedPanel("User", ChatRepository.INSETS);
    userPanel.setLayout(new GridBagLayout());
    loginAsPanel = new FramedPanel("Login as", ChatRepository.INSETS);
    loginAsPanel.setLayout(new GridBagLayout());

    loginCheckboxGroup = new CheckboxGroup();
    newUserCheckbox = new Checkbox("New User", loginCheckboxGroup, mode == MODE_NEW_USERS);
    existingUserCheckbox = new Checkbox("Existing User", loginCheckboxGroup, mode != MODE_NEW_USERS);

    newUserCheckbox.setEnabled(mode != MODE_EXISTING_USERS);
    existingUserCheckbox.setEnabled(mode != MODE_NEW_USERS);

    nameLabel = new Label("Name:", Label.RIGHT);
    passwordLabel1 = new Label("Passwort:", Label.RIGHT);
    passwordLabel2 = new Label("Passwort (Verify):", Label.RIGHT);
    nameTextField = new TextField(30);
    passwordTextField1 = new TextField(10);
    passwordTextField1.setEchoChar('*');
    passwordTextField2 = new TextField(10);
    passwordTextField2.setEchoChar('*');

    ChatUtil.addWithRemainingConstraints(loginAsPanel, newUserCheckbox);
    ChatUtil.addWithRemainingConstraints(loginAsPanel, existingUserCheckbox);

    ChatUtil.addWithBeginningConstraints(userPanel, nameLabel);
    ChatUtil.addWithRemainingConstraints(userPanel, nameTextField);
    ChatUtil.addWithBeginningConstraints(userPanel, passwordLabel1);
    ChatUtil.addWithRemainingConstraints(userPanel, passwordTextField1);
    ChatUtil.addWithBeginningConstraints(userPanel, passwordLabel2);
    ChatUtil.addWithRemainingConstraints(userPanel, passwordTextField2);

    buttonPanel = new InsetsPanel(ChatRepository.INSETS);
    buttonPanel.setLayout(new BorderLayout());

    connectButton = new Button("Connect");
    disconnectButton = new Button("Disconnect");
    statusLabel = new Label("Status:");

    constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.NORTHWEST;
    constraints.insets = ChatRepository.SMALL_INSETS;
    constraints.weightx = 1.0;
    constraints.gridwidth = 1;

    ChatUtil.addWithConstraints(buttonPanel, connectButton, constraints);
    constraints.anchor = GridBagConstraints.NORTHEAST;
    ChatUtil.addWithConstraints(buttonPanel, disconnectButton, constraints);
    constraints.gridy = 2;
    constraints.gridwidth = 2;
    constraints.anchor = GridBagConstraints.NORTHWEST;
    constraints.fill = GridBagConstraints.BOTH;
    ChatUtil.addWithConstraints(buttonPanel, statusLabel, constraints);
    constraints.gridy = 3;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.NORTHEAST;
    ChatUtil.addWithConstraints(buttonPanel, chatApplet.getLogo(), constraints);

    passwordLabel2.setEnabled(newUserCheckbox.getState());
    passwordTextField2.setEnabled(newUserCheckbox.getState());

    connectButton.setEnabled(true);
    disconnectButton.setEnabled(false);

    setLayout(new BorderLayout());
    setFont(ChatRepository.STANDARD_FONT);
    add(connectionPanel, "North");
    add(userPanel, "Center");
    add(buttonPanel, "South");
    add(loginAsPanel, "East");

    newUserCheckbox.addItemListener(this);
    existingUserCheckbox.addItemListener(this);

    connectButton.addActionListener(this);
    disconnectButton.addActionListener(this);

    checkThread = new Thread(this);
    checkThread.setPriority(Thread.MIN_PRIORITY);
    checkThread.start();

  }


/**
 * Runs a thread that checks the connection status of the ChatClient. The connect- and
 * the disconnect-Buttons will be enabled/disabled depending on this status.
 */

  public void run() {
    while(true) {
      try {
        Thread.sleep(1000);
        connectButton.setEnabled(!chatApplet.getClient().connected());
        disconnectButton.setEnabled(chatApplet.getClient().connected());
      }
      catch (Exception excpt) {
      }
    }
  }


/**
 * Invoked when an action occurs.
 *
 * param @event      the ActionEvent that occured
 */

  public void actionPerformed(ActionEvent event) {
    int port;

    if (event.getSource() == connectButton) {

      try {
        port = new Integer(portTextField.getText()).intValue();
        if (port <= 2048)
          port = chatApplet.getDefaultPort();
      }
      catch (NumberFormatException excpt) {
        port = chatApplet.getDefaultPort();
      }

      portTextField.setText(new Integer(port).toString());

      if (!(nameTextField.getText().equals("") || passwordTextField1.getText().equals("") || (newUserCheckbox.getState() && ! passwordTextField1.getText().equals(passwordTextField2.getText())))) {
        if (newUserCheckbox.getState())
          chatApplet.getClient().connectAsNewUser(new User(nameTextField.getText(), passwordTextField1.getText()), port);
        else if (existingUserCheckbox.getState())
          chatApplet.getClient().connectAsExistingUser(nameTextField.getText(), passwordTextField1.getText(), port);
        }
      else
        chatApplet.setStatus("Can't connect: Invalid data", true);
    }
    else if (event.getSource() == disconnectButton) {
      chatApplet.getClient().disconnect();
      chatApplet.setFrameVisibility(false);
    }
  }

  public void setStatus(String statusString) {
    statusLabel.setText("Status: " + statusString);
  }


/**
 * Invoked when an item's state has been changed.
 *
 * param @event      the ItemEvent that occured
 */

  public void itemStateChanged(ItemEvent event) {
    passwordLabel2.setEnabled(newUserCheckbox.getState());
    passwordTextField2.setEnabled(newUserCheckbox.getState());
  }

}