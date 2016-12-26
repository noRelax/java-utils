package player.lyrics;
//download:http://www.codefans.net
import java.util.ArrayList;
import java.util.HashMap;

public class LyricsContent {
	private ArrayList content = null;

	private HashMap map = null;

	private String name = "";

	private String singer = "";

	private String text = "";

	public ArrayList getContent() {
		return content;
	}

	public void setMap(HashMap map) {
		this.map = map;
	}

	public void setContent(ArrayList content) {
		this.content = content;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public void setText(String text) {
		this.text = text;
	}

	public HashMap getMap() {
		return map;
	}

	public String getName() {
		return name;
	}

	public String getSinger() {
		return singer;
	}

	public String getText() {
		return text;
	}

	public int rowCount() {
		return content.size();
	}

	public int getIndex(String key) {
		return ((Integer) map.get(key)).intValue();
	}

	public String getContent(int row) {
		return (String) content.get(row);
	}

	public String getContent(String key) {
		return (String) content.get(((Integer) map.get(key)).intValue());
	}
}
