package player.lyrics;
//download:http://www.codefans.net
import java.util.*;

public class LyricsContents {
	private ArrayList list = new ArrayList();

	public void add(LyricsContent content) {
		list.add(content);
	}

	public void remove(int index) {
		list.remove(index);
	}

	public void remove(LyricsContent content) {
		list.remove(content);
	}

	public void removeAll() {
		list.removeAll(list);
	}

	public LyricsContent get(int index) {
		return (LyricsContent) list.get(index);
	}

	public int indexOf(LyricsContent content) {
		return list.indexOf(content);
	}

	public int getSize() {
		return list.size();
	}
}
