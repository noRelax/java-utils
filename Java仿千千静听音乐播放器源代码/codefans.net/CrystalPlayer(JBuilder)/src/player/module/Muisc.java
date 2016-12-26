package player.module;
//download:http://www.codefans.net
import java.io.File;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.farng.mp3.MP3File;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.id3.AbstractID3v2;
import player.lyrics.*;


public class Muisc {
  private String name = "";
  private String singer = "";
  private String edition = "";
  private String year = "";
  private String bitRate = "00kHz";
  private String sampleRate = "000kbps";
  private String channels = "";
  private LyricsContent lyrics = null;

  public Muisc(File file){
    if (file != null){
      try {
        String path = file.getAbsolutePath();
        path = path.substring(0,path.lastIndexOf("."));
        File lyricsFile = new File(path + ".lrc");
        if(lyricsFile.exists()){
          lyrics = Lyrics.read(lyricsFile);
        }
        MP3AudioHeader header = new MP3AudioHeader(file);
        this.setBitRate(header.getBitRate() + "Kbps");
        this.setChannels(header.getChannels());
        this.setSampleRate((Integer.parseInt(header.getSampleRate()) / 1000) + "kHz");
        MP3File media = new MP3File(file);
        ID3v1 id3v1 = media.getID3v1Tag();
        if(id3v1 != null){
          name = id3v1.getTitle();
          name = (name != null) ? new String(name.getBytes("ISO8859_1"), "gb2312") : "";
          this.setName(name);

          singer = id3v1.getArtist();
          singer = (singer != null) ? new String(singer.getBytes("ISO8859_1"), "gb2312") : "";
          this.setSinger(singer);

          edition = id3v1.getAlbum();
          edition = (edition != null) ? new String(edition.getBytes("ISO8859_1"), "gb2312") : "";
          this.setEdition(edition);

          year = id3v1.getYear();
          year = (year != null) ? new String(year.getBytes("ISO8859_1"), "gb2312") : "";
          this.setYear(year);
        }
        else{
          AbstractID3v2 id3v2 = media.getID3v2Tag();
          if(id3v2 != null){
            name = id3v2.getSongTitle();
            name = (name != null) ? new String(name.getBytes("ISO8859_1"), "gb2312") : "";

            singer = id3v2.getLeadArtist();
            singer = (singer != null) ? new String(singer.getBytes("ISO8859_1"), "gb2312") : "";
            this.setSinger(singer);

            edition = id3v2.getAlbumTitle();
            edition = (edition != null) ? new String(edition.getBytes("ISO8859_1"), "gb2312") : "";
            this.setEdition(edition);

            year = id3v2.getYearReleased();
            year = (year != null) ? new String(year.getBytes("ISO8859_1"), "gb2312") : "";
            this.setYear(year);
          }
          else{
            name = file.getName();
            singer = "";
            edition = "";
            year = "";
          }
        }
      }
      catch (Exception e) {
        e.printStackTrace();
        name = file.getName();
        singer = "";
        edition = "";
        year = "";
      }
    }
  }

  public void setLyrics(LyricsContent lyrics){
    this.lyrics = lyrics;
  }

  public LyricsContent getLyrics(){
    return lyrics;
  }

  public String getBitRate() {
    return bitRate;
  }

  public String getChannels() {
    return channels;
  }

  public String getEdition() {
    return edition;
  }

  public String getName() {
    return name;
  }

  public String getSampleRate() {
    return sampleRate;
  }

  public String getSinger() {
    return singer;
  }

  public String getYear() {
    return year;
  }

  private void setBitRate(String bitRate) {
    this.bitRate = bitRate;
  }

  private void setChannels(String channels) {
    this.channels = channels;
  }

  private void setEdition(String edition) {
    this.edition = edition;
  }

  private void setName(String name) {
    this.name = name;
  }

  private void setSampleRate(String sampleRate) {
    this.sampleRate = sampleRate;
  }

  private void setSinger(String singer) {
    this.singer = singer;
  }

  private void setYear(String year) {
    this.year = year;
  }

}
