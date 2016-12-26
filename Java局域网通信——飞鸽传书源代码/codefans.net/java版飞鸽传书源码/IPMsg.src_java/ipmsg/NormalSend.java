/*
 *	Normal Send Class
 *		1997/10/22 (C) Copyright T.Kazawa(Digitune)
 *  Download by http://www.codefans.net
 */

package ipmsg;

import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Hashtable;
import java.io.UnsupportedEncodingException;

public class NormalSend extends IPMSend {
	private Hashtable listeners = new Hashtable();
	private int wait = 3000, retry = 3;
	private boolean reply = false;
	
	public synchronized void addIPMComListener(IPMComListener listener) {
		listeners.put(listener, listener);
	}
	
	public synchronized void removeIPMComListener(IPMComListener listener) {
		listeners.remove(listener);
	}
	
	public void run() {
		for (int i = retry; i != 0; i--) {
			send(dsock, spack, saddr);
			try {
				sleep(wait);
			} catch (InterruptedException ex) {}
			if (reply)
				return;
			spack.setCommand(spack.getCommand() | IPMsg.IPMSG_RETRYOPT);
		}
		IPMComEvent ipmce = new IPMComEvent(this, dsock.getLocalPort()
			, spack, saddr);
		synchronized (this) {
			for (Enumeration enum = listeners.elements()
				; enum.hasMoreElements(); ) {
				IPMComListener listener
					= (IPMComListener) enum.nextElement();
				listener.receive(ipmce);
			}
		}
	}
	
	public void receiveReply() {
		reply = true;
	}
	
	public NormalSend(DatagramSocket argsock, IPMPack argpack
		, IPMAddress argaddr) {
		super(argsock, argpack, argaddr);
	}
}
