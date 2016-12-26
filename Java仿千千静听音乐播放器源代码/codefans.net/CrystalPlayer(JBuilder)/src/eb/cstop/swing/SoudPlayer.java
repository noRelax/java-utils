package eb.cstop.swing;
//download:http://www.codefans.net
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;
public class SoudPlayer extends Thread{
	AudioFormat audioFormat;
	AudioInputStream streamInput;
	SourceDataLine line;
	DataLine.Info info;
	public SoudPlayer(String path){
		File file = new File(path);
		try{
			streamInput = AudioSystem.getAudioInputStream(file.toURL());
			newObject();
		}
		catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
	public SoudPlayer(File file){
		try{
			streamInput = AudioSystem.getAudioInputStream(file);
			newObject();
		}
		catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
	private void newObject(){
		audioFormat = streamInput.getFormat();
		info = new DataLine.Info(SourceDataLine.class,audioFormat,2048);
		start();
	}
	public void run(){
		try{
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(streamInput.getFormat());
			line.start();
			int i = 0;
			byte[] bytes = new byte[2048];
			while((i = streamInput.read(bytes)) != -1){
				line.write(bytes,0,i);
				sleep(5);
			}
			line.drain();
			line.stop();
		}
		catch(IOException e){
			e.printStackTrace();
			return;
		}
		catch(LineUnavailableException e){
			e.printStackTrace();
			return;
		}
		catch(InterruptedException e){
			e.printStackTrace();
			return;
		}
		finally{
			try{
				streamInput.close();
			}
			catch(IOException e){
				e.printStackTrace();
				return;
			}
		}
	}
}
