package player.lyrics;
//download:http://www.codefans.net
import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class LyricsHunter extends Thread {
	private HttpClient httpClient = new HttpClient();
	private String muiscName = null;
	private ArrayList listeners = new ArrayList();

	public static void main(String[] args) {
		new LyricsHunter("The Lover After Me");
	}

	public void addLyricsDownLoadListener(LyricsDownLoedListener listener){
		listeners.add(listener);
	}

	public void removeLyricsDownLoadListener(LyricsDownLoedListener listener){
		listeners.remove(listener);
	}

	public LyricsHunter(String muiscName) {
		this.muiscName = muiscName;
		httpClient.getHostConfiguration().setHost("lrc.lrcmp4.com",80,"http");
	}

	public void run(){
		PostMethod postMethod = new PostMethod("/search.asp");
		InputStream in = null;
		BufferedReader reader = null;
		StringBuffer content = new StringBuffer("");
		try{
			postMethod.setRequestBody(new NameValuePair[] {new NameValuePair("keyword",new String(muiscName.getBytes(),"ISO8859_1"))});
			httpClient.executeMethod(postMethod);
			if(postMethod.getStatusCode() == HttpStatus.SC_OK){
				in = postMethod.getResponseBodyAsStream();
				reader = new BufferedReader(new InputStreamReader(in,"gb2312"));
				String line = null;
				while((line = reader.readLine()) != null){
					content.append(line + "\n");
				}
				this.findLink(content.toString());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{if(reader != null)reader.close();}catch(Exception e){}
			try{if(in != null)in.close();}catch(Exception e){}
	    	postMethod.releaseConnection();
	    }
	}

	public void findLink(String html){
		if(html.length() > 0){
			String link = null;
			Pattern pattern = Pattern.compile("href=\"?(.*?)(\"|>|\\s+)");
			Matcher matcher = pattern.matcher(html);
			LyricsContents contents = new LyricsContents();
			while(matcher.find()){
				link = matcher.group(1);
				if(link.startsWith("getfile.asp")){
					this.downLoad(link,contents);
				}
			}
			if(contents.getSize() > 0){
				LyricsDownLoedListener listener = null;
				for(int i=0;i<listeners.size();i++){
					listener = (LyricsDownLoedListener)listeners.get(i);
					listener.lyricsDowned(contents);
				}
			}
		}
	}

	private void downLoad(String src,LyricsContents contents){
		PostMethod postMethod = new PostMethod("/" + src);
		InputStream in = null;
		BufferedReader reader = null;
		StringBuffer content = new StringBuffer("");
		try{
			httpClient.executeMethod(postMethod);
			if(postMethod.getStatusCode() == HttpStatus.SC_OK){
				in = postMethod.getResponseBodyAsStream();
				reader = new BufferedReader(new InputStreamReader(in,"gb2312"));
				String line = null;
				while((line = reader.readLine()) != null){
					content.append(line + "TEMPenter");
				}
				LyricsContent lyrics = Lyrics.doFilter(content.toString());
				contents.add(lyrics);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{if(reader != null)reader.close();}catch(Exception e){}
			try{if(in != null)in.close();}catch(Exception e){}
	    	postMethod.releaseConnection();
	    }
	}
}
