/*
 * @(#)ConnectDialog.java
 * Created on 2005-9-13
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
package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import core.ConnectionListener;
import core.ConnectionModel;

/**
 * A dialog that let the user to connect a friend.
 * 
 * @author Allen Chue
 */
public class ConnectDialog extends JDialog implements ConnectionListener {

    private static final long serialVersionUID = 2222;

    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
            Font f = new Font("Dialog", Font.PLAIN, 12);
            UIManager.put("Label.font", f);
            UIManager.put("Buttton.font", f);
            UIManager.put("TextArea.font", f);
            
            ConnectDialog dialog = new ConnectDialog();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } catch (Throwable t) {
            System.out.println("Oops! Uncaught exception!");
            System.exit(-10);
        }
    }

    private JPanel jContentPane = null;

    private JPanel centerPanel = null;

    private JPanel buttonPanel = null;

    private JButton connectButton = null;

    private JButton exitButton = null;

    private JPanel infoPane = null;

    private JPanel statusPane = null;

    private JLabel addressLabel = null;

    private JTextField nameField = null;

    private JLabel nameLabel = null;

    private JTextField addresssField = null;

    private JLabel conServerLabel = null;

    private JLabel conClientLabel = null;

    private ConnectionModel manager;// = ConnectionModel.getInstance();

    /**
     * This is the default constructor
     */
    public ConnectDialog() {

        initialize();
        manager = ConnectionModel.getInstance();
        manager.addConnectionListener(this);
    }

    public ConnectDialog(Frame owner) {
        super(owner);

        initialize();

        manager.addConnectionListener(this);

    }

    /**
     * Overriden method.<br>
     * Sets the dialog to be visible, meanwhile uses a thread to repeatedly
     * accept remote connections until a connection from a remote client is set
     * up.
     * 
     * @param b
     *            true to set the dialog visible, while false to hide it
     */
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        Thread acceptThread = new Thread() {
            public void run() {
                while (!manager.acceptConnection()) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
            }

        };
        acceptThread.setPriority(3);
        acceptThread.start();
    }

    /**
     * This method initializes this
     */
    private void initialize() {
        this.setSize(384, 204);
        this.setResizable(false);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(UIResources.getString("dialog.title"));
        this.setContentPane(getJContentPane());
        
        this.getRootPane().setDefaultButton(connectButton);
    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getCenterPanel(), java.awt.BorderLayout.CENTER);
            jContentPane.add(getButtonPanel(), java.awt.BorderLayout.SOUTH);
        }
        return jContentPane;
    }

    /**
     * This method initializes centerPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getCenterPanel() {
        if (centerPanel == null) {
            GridLayout gridLayout1 = new GridLayout();
            gridLayout1.setRows(2);
            gridLayout1.setColumns(1);
            centerPanel = new JPanel();
            centerPanel.setLayout(gridLayout1);
            centerPanel.add(getInfoPane(), null);
            centerPanel.add(getStatusPane(), null);
        }
        return centerPanel;
    }

    /**
     * This method initializes buttonPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setHgap(10);
            flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
            buttonPanel = new JPanel();
            buttonPanel.setLayout(flowLayout);
            buttonPanel.add(getConnectButton(), null);
            buttonPanel.add(getExitButton(), null);
        }
        return buttonPanel;
    }

    /**
     * This method initializes okButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getConnectButton() {
        if (connectButton == null) {
            connectButton = new JButton();
            connectButton.setText(UIResources.getString("dialog.connectButton"));
            connectButton.setMnemonic(java.awt.event.KeyEvent.VK_C);
            connectButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {

                            if ("".equals(nameField.getText())) {
                                System.out.println("Empty");

                                JOptionPane.showMessageDialog(
                                        ConnectDialog.this,
                                        UIResources.getString("dialog.emptyNameErrorMsg"),
                                        UIResources.getString("dialog.errorTitle"),
                                        JOptionPane.WARNING_MESSAGE);
                            }

                            else {
                                nameField.setEnabled(false);
                                addresssField.setEnabled(false);
                                connectButton.setEnabled(false);
                                exitButton.setEnabled(false);
                                if (!manager.connectToFriend(addresssField
                                        .getText(), nameField.getText())) {
                                    JOptionPane.showMessageDialog(
                                                    ConnectDialog.this,
                                                    UIResources.getString("dialog.conAsClientErrorMsg"),
                                                    UIResources.getString("dialog.errorTitle"),
                                                    JOptionPane.WARNING_MESSAGE);
                                    nameField.setEnabled(true);
                                    addresssField.setEnabled(true);
                                    connectButton.setEnabled(true);
                                }
                                exitButton.setEnabled(true);
                            }
                        }
                    });
        }
        return connectButton;
    }

    /**
     * This method initializes cancelButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getExitButton() {
        if (exitButton == null) {
            exitButton = new JButton();
            exitButton.setText(UIResources.getString("dialog.exitButton"));
            exitButton.setMnemonic(java.awt.event.KeyEvent.VK_X);
            exitButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    System.exit(0);
                }
            });
        }
        return exitButton;
    }

    public String getConnectAddress() {
        return nameField.getText();
    }

    /**
     * This method initializes infoPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getInfoPane() {
        if (infoPane == null) {
            nameLabel = new JLabel();
            nameLabel.setText(UIResources.getString("dialog.displayNameLabel") + ": ");
            addressLabel = new JLabel();
            addressLabel.setText(UIResources.getString("dialog.remoteAddressLabel") + ": ");
            GridLayout gridLayout = new GridLayout();
            gridLayout.setRows(2);
            gridLayout.setHgap(5);
            gridLayout.setVgap(6);
            gridLayout.setColumns(2);
            infoPane = new JPanel();
            infoPane.setLayout(gridLayout);
            infoPane.setBorder(javax.swing.BorderFactory.createTitledBorder(
                    null, UIResources.getString("dialog.infoTitle"),
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    new java.awt.Color(51, 51, 51)));
            infoPane.add(addressLabel, null);
            infoPane.add(getAddresssField(), null);
            infoPane.add(nameLabel, null);
            infoPane.add(getNameField(), null);
            nameLabel.setLabelFor(addresssField);
        }
        return infoPane;
    }

    /**
     * This method initializes statusPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getStatusPane() {
        if (statusPane == null) {
            conClientLabel = new JLabel();
            conClientLabel.setText(UIResources.getString("dialog.conAsClientLabel"));
            conClientLabel.setIcon(new ImageIcon(getClass().getResource(
                    "/ui/resources/not_ok.gif")));//$NON-NLS-1$
            conServerLabel = new JLabel();
            conServerLabel.setText(UIResources.getString("dialog.conAsServerLabel"));
            conServerLabel.setIcon(new ImageIcon(getClass().getResource(
                    "/ui/resources/not_ok.gif")));//$NON-NLS-1$
            conServerLabel.setCursor(new java.awt.Cursor(
                    java.awt.Cursor.DEFAULT_CURSOR));
            statusPane = new JPanel();
            statusPane.setLayout(new BoxLayout(getStatusPane(),
                    BoxLayout.Y_AXIS));
            statusPane.setBorder(javax.swing.BorderFactory.createTitledBorder(
                    null, UIResources.getString("dialog.statusTitle"),
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION, null,
                    null));
            statusPane.add(conServerLabel, null);
            statusPane.add(conClientLabel, null);
        }
        return statusPane;
    }

    /**
     * This method initializes addressTextField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getNameField() {
        if (nameField == null) {
            nameField = new JTextField();
            nameField.setColumns(15);
        }
        return nameField;
    }

    /**
     * This method initializes nameField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getAddresssField() {
        if (addresssField == null) {
            addresssField = new JTextField();
        }
        return addresssField;
    }

    /**
     * 
     * 
     */
    private void goChat() {
        String selfName = nameField.getText();
        String friendName = manager.receive();
        dispose();
        ChatWindow chatWindow = new ChatWindow(selfName, friendName);
        chatWindow.setLocation(this.getLocation());
        chatWindow.setVisible(true);
    }

    public void connectionAsServerChanged(boolean b) {
        if (b) {
            // Connection accepted
//            SwingUtilities.invokeLater(new Runnable() {
//                public void run() {
                    conServerLabel.setIcon(new ImageIcon(getClass()
                            .getResource("/ui/resources/ok.gif")));
//                }
//            });
            if (manager.isConnectedAsClient()) {
                // Both connection OK!
                goChat();
            }
        } else {
            System.out.println("Connection as a server failed.");//$NON-NLS-1$
        }
    }

    public void connectionAsClientChanged(boolean b) {
        if (b) {
            // Remote connection succeeds
//            SwingUtilities.invokeLater(new Runnable() {
//                public void run() {
                    conClientLabel.setIcon(new ImageIcon(getClass()
                            .getResource("/ui/resources/ok.gif")));
//                }
//            });
            if (manager.isConnectedAsServer()) {
                // Both connection OK!
                goChat();
            }

        } else {
            System.out.println("Connection as a client failed.");//$NON-NLS-1$
        }
    }
} // @jve:decl-index=0:visual-constraint="10,10"
