/*
 *	IP Messenger Packet Send Abstract Class
 *		1997/10/17 (C) Copyright T.Kazawa (Digitune)
 * Download by http://www.codefans.net
 */

package ipmsg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class IPMSend extends Thread {
	protected DatagramSocket dsock;
	protected IPMPack spack;
	protected IPMAddress saddr;

	public static void send(DatagramSocket argsock, IPMPack argpack
		, IPMAddress argaddr) {
/*		System.out.println("Send(" + argsock.getLocalPort()
			+ "):" + argaddr.toString() + ":"
			+ new String(argpack.getBytes()));*/
		DatagramPacket dpack = new DatagramPacket(argpack.getBytes()
			, argpack.getBytes().length, argaddr.getInetAddress()
			, argaddr.getPort());
		try {
			argsock.send(dpack);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public IPMSend(DatagramSocket argsock,IPMPack argpack,IPMAddress argaddr) {
		dsock = argsock;
		spack = argpack;
		saddr = argaddr;
//		start();
	}
}
