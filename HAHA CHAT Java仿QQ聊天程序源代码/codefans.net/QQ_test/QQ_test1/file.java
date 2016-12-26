package QQ_test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class file 
{
	String content="";
	String path="chat_history\\";
	public boolean enter=true;
	public file()
	{
		
	}
	public void write_into_file(String file_name,String c)
	{
		File file = new File(path+file_name+".txt");
		content=c;
	    if(!file.exists())
	    {
			try {
				file.createNewFile();
			} catch (IOException e) {e.printStackTrace();}
	    }
	    try
	    {
			//File file1 = new File("D://2013.txt");
			FileInputStream fin=new FileInputStream(file);
			byte[] buffer2=new byte[fin.available()];
			
			fin.read(buffer2);
			String t2=new String(buffer2,0,buffer2.length);
			if(t2!="")
			{
				if(enter)
					content=t2+"\n"+content;
				else
					content=t2+content;
			}				
			byte[] buffer=content.getBytes();
			fin.close();
			
			FileOutputStream fout=new FileOutputStream(file);
			fout.write(buffer);
			fout.close();
			
	    }catch(Exception e){}
	}
	public static void main(String args[])
	{
		new file();
	}
}
