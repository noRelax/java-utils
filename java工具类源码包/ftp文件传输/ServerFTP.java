package ftp2;
import java.io.*;
import java.net.*;
public class ServerFTP {
	int port=8083;
	String filepath="D:\\123.txt";
	void start(){
		Socket s=null;
		try {
			ServerSocket ss=new ServerSocket(port);
			while(true){
				File file=new File(filepath);
				if(!file.exists()){
					file.createNewFile();
				}
				System.out.println("文件长度："+(int)file.length());
				s=ss.accept();
				System.out.println("建立socket连接");
				DataInputStream dis=new DataInputStream(new BufferedInputStream(new FileInputStream(filepath)));
				DataOutputStream dos=new DataOutputStream(s.getOutputStream());
				dos.writeUTF(file.getName());
				dos.writeLong((long)file.length());
				int bufferSize=8192;
				byte[] buf=new byte[bufferSize];
				while(true){
					int read=0;
					if(dis!=null){
						read=dis.read(buf);
					}
					if(read==-1){
						break;
					}
					dos.write(buf,0,read);
				}
				dis.close();
				s.close();
				System.out.println("文件传输完毕！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ServerFTP().start();
	}

}
