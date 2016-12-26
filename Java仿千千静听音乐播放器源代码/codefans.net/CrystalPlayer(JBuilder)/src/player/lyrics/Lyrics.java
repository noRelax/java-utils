package player.lyrics;
//download:http://www.codefans.net
import java.io.*;
import java.util.*;

public class Lyrics {
	public static LyricsContent read(File file) {
		if (file == null)return null;
		LyricsContent content = null;
		FileInputStream in = null;
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer("");
		try {
			in = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				if (i == 0)buffer.append(line);
				else buffer.append("TEMPenter" + line);
				i++;
			}
			content = Lyrics.doFilter(buffer.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {if (reader != null)reader.close();} catch (Exception e) {}
			try {if (in != null)in.close();} catch (Exception e) {}
		}
		return content;
	}

	protected static LyricsContent doFilter(String value) {
		value = value.replaceAll("\\[","TEMP¡º").replaceAll("\\]","TEMP¡»").replaceAll("\\:","TEMP¡Ã");
		value = value.replaceAll("\\p{Punct}","");
		value = value.replaceAll("TEMP¡º","[").replaceAll("TEMP¡»","]").replaceAll("TEMP¡Ã",":");
		String[] lines = value.split("TEMPenter");
		String line, key, nextKey, text = null;
		LyricsContent content = new LyricsContent();
		ArrayList list = new ArrayList();
		HashMap map = new HashMap();
		content.setContent(list);
		content.setMap(map);
		StringBuffer contentText = new StringBuffer("");
		int index = 0;
		for (int i = 0; i < lines.length; i++) {
			line = lines[i];
			if (line.startsWith("[")) {
				key = line.substring(1, line.indexOf("]", 0));
				if (key.matches("[0-9:.]*")) {
					text = line.substring(line.lastIndexOf("]") + 1, line.length());
					list.add(index, text);
					key = line.substring(0, line.lastIndexOf("]") + 1);
					while ((nextKey = key.substring(0, key.indexOf("]", 0) + 1)).length() > 0) {
						key = key.substring(key.indexOf("]", 0) + 1, key.length());
						nextKey = nextKey.substring(1, 6);
                                                contentText.append("[" + nextKey + "]");
						map.put(nextKey, new Integer(index));
					}
					contentText.append(text + "\n");
					index++;
				}
				else {
					if (key.toLowerCase().startsWith("ti:")) {
						content.setName(key.substring(3, key.length()));
						contentText.append("[ti:" + content.getName() + "]\n");
					}
					else if (key.toLowerCase().startsWith("ar:")) {
						content.setSinger(key.substring(3, key.length()));
						contentText.append("[ar:" + content.getSinger() + "]\n");
					}
				}
			}
		}
		content.setText(contentText.toString());
		return content;
	}
}
