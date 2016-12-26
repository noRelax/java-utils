package ftp2;
import java.io.*;
import java.net.*;
public class ClientFTP {
	private Socket s=null;
	private String ip="localhost";
	private int port=8082;
	public ClientFTP() {
		creationConnection();
	}
	
	private void creationConnection() {
		try {
			s=new Socket(ip,port);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void getFile() {
		String savePath="I:\\";
		int bufferSize=8193;
		byte[] buf=new byte[bufferSize];
		int passedlen=0;
		long length=0;
		try {
			DataInputStream dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
			savePath+=dis.readUTF();
			DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(savePath)));
			length=dis.readLong();
			System.out.println("文件长度："+length+"\n");
			while (true) {
				int read=0;
				if(dis!=null){
					read=dis.read(buf);
				}
				passedlen+=read;
				if (read==-1) {
					break;					
				}
				dos.write(buf,0,read);
			}
			System.out.println("接收文件另存为："+savePath+"\n");
			dos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		new ClientFTP().getFile();
	}

}
