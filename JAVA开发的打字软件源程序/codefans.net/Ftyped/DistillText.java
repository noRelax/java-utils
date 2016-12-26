import java.io.*;
public class DistillText {
	public static String getData(String fileName,int segment){
		int i=0;
		if((fileName==null) || (fileName.length()==0))
			return "";
		File f;
		FileReader in=null;
		String s="";
		try{
			f=new File(fileName);
			in=new FileReader(f);
			char[] buffer=new char[312];
			int len;
			if(PublicData.Lang.equals("English")){
				if(segment>=(int)(f.length()/312))
					PublicData.textTag=0;
			}
			if(PublicData.Lang.equals("Chinese")){
				if(segment>=(int)(f.length()/312-1)+1)
					PublicData.textTag=0;
			}
			while((len=in.read(buffer))!=-1){
				s=new String(buffer,0,len);
				i++;
				if(i==segment){
					PublicData.textTag++;
					break;
				}
			}
		}
		catch(IOException e){
			System.out.println(e);
		}
		finally{
			try{
				if(in!=null)
					in.close();
			}
			catch(IOException e){}
		}
		return s;
	}
}
