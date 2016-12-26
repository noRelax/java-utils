/*
 * @(#)ChatWindow.java
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
package ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.media.Manager;
import javax.media.Player;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import core.ConnectionModel;
import core.MessageListener;

/**
 * Actual implementation for a chat client.
 * 
 * @author Allen Chue
 */
public class ChatWindow extends JFrame implements MessageListener {
	
	public static void main(String[] args) throws Exception {
 //           ChatWindow win=new ChatWindow("127.0.0.1","127.0.0.1");
		Player player = Manager.createPlayer(new File("msg.wav").toURL());
		player.start();
		
	    Thread.sleep(5000);
	    System.exit(0);
	}
	
    private static final long serialVersionUID = 11111111;
    

    public static final int MAX_WAITING_TIME = 10000;
    
        
    private ConnectionModel manager;
    
    private JPanel jContentPane = null;
    private JScrollPane messageBoxWrapper = null;
    private JTextArea messageBox = null;
    private JScrollPane inputBoxWrapper = null;
    private JTextArea inputBox = null;
    private JPanel bottomPanel = null;
    private JButton sendButton = null;
    
    private String selfName, friendName;
    
    private Player remindPlayer = null;
    
    /**
     * This is the default constructor
     */
    public ChatWindow(String selfName, String friendName) {
        super();
        
        this.selfName = selfName;
        this.friendName = friendName;
            
        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(400, 278);
        this.setLocation(new java.awt.Point(300,400));
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getJContentPane());
        this.setTitle("iChat LE - "+ UIResources.getString("window.title") + friendName);
        
        manager = ConnectionModel.getInstance();
        manager.addMessageListener(this);
        
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ui/resources/icon.gif")));
		
		try {
			remindPlayer = Manager.createPlayer(
					new File(UIResources.getString("window.remindSound")).toURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
            jContentPane.add(getMessageBoxWrapper(), java.awt.BorderLayout.CENTER);
            jContentPane.add(getBottomPanel(), java.awt.BorderLayout.SOUTH);
        }
        return jContentPane;
    }

    /**
     * This method initializes messageBoxWrapper	
     * 	
     * @return javax.swing.JScrollPane	
     */
    private JScrollPane getMessageBoxWrapper() {
        if (messageBoxWrapper == null) {
            messageBoxWrapper = new JScrollPane();
            messageBoxWrapper.setViewportView(getMessageBox());
        }
        return messageBoxWrapper;
    }

    /**
     * This method initializes messageBox	
     * 	
     * @return javax.swing.JTextArea	
     */
    private JTextArea getMessageBox() {
        if (messageBox == null) {
            messageBox = new JTextArea();
            messageBox.setColumns(32);
            messageBox.setEditable(false);
            messageBox.setRows(8);
        }
        return messageBox;
    }

    /**
     * This method initializes inputBox	
     * 	
     * @return javax.swing.JScrollPane	
     */
    private JScrollPane getInputBoxWrapper() {
        if (inputBoxWrapper == null) {
            inputBoxWrapper = new JScrollPane();
            inputBoxWrapper.setViewportView(getInputBox());
        }
        return inputBoxWrapper;
    }

    /**
     * This method initializes inputBox	
     * 	
     * @return javax.swing.JTextArea	
     */
    private JTextArea getInputBox() {
        if (inputBox == null) {
            inputBox = new JTextArea();
            inputBox.setColumns(28);
            inputBox.setRows(4);

            inputBox.getDocument().addDocumentListener(
            		new DocumentListener() {

						public void insertUpdate(DocumentEvent e) {
							updateSendButton();							
						}

						public void removeUpdate(DocumentEvent e) {
							updateSendButton();
						}

						public void changedUpdate(DocumentEvent e) {
							updateSendButton();
						}
            			
            		});
            inputBox.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent e) {
                    if ((e.getKeyChar() == KeyEvent.VK_ENTER) &&
                            (e.getModifiers() == KeyEvent.CTRL_MASK)) {
                        sendMessage();
                    }
                }
            });
        }
        return inputBox;
    }

    /**
     * This method initializes bottomPanel	
     * 	
     * @return javax.swing.JPanel	
     */
    private JPanel getBottomPanel() {
        if (bottomPanel == null) {
            bottomPanel = new JPanel();
            bottomPanel.setLayout(new BorderLayout());
            bottomPanel.add(getInputBoxWrapper(), java.awt.BorderLayout.CENTER);
            bottomPanel.add(getSendButton(), java.awt.BorderLayout.EAST);
        }
        return bottomPanel;
    }

    /**
     * This method initializes sendButton	
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getSendButton() {
        if (sendButton == null) {
            sendButton = new JButton();
            sendButton.setEnabled(false);
            sendButton.setText(UIResources.getString("window.sendButton"));
            sendButton.setMnemonic(java.awt.event.KeyEvent.VK_S);
            sendButton.addActionListener(new java.awt.event.ActionListener() {   
            	public void actionPerformed(java.awt.event.ActionEvent e) { 
            	    sendMessage();
            	}   
            
            
            });
        }
        return sendButton;
    }
    
    public void sendMessage() {
        manager.send(inputBox.getText());
        updateMessageBox(selfName, inputBox.getText());
        inputBox.setText("");
        
    }
    
    /**
     * Updates the <code>messageBox</code> with <code>
     * String</code> s. This version of method is used
     * after the user sends or receives a message.
     * @param name the name of whom the message is from
     * @param message the message either sent or received
     */
    private void updateMessageBox(String name, String message) {
        messageBox.append("\n" + name + UIResources.getString("window.say") +
                "\n" + message);//$NON-NLS-1$
        messageBox.setCaretPosition(messageBox.getDocument().getLength());
    }
    
    /**
     * Updates the button state according to whether inputBox's content
     * is emtpy.
     *
     */
    private void updateSendButton() {
    	sendButton.setEnabled(
    			inputBox.getDocument().getLength() == 0 ? false : true);
    }
    
    public void messageReceived(String message) {
    	remindPlayer.start();
        updateMessageBox(friendName, message);
    }
    
}  //  @jve:decl-index=0:visual-constraint="10,10"
