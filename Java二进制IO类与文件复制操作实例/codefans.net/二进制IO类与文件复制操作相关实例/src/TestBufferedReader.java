import java.io.*;

public class TestBufferedReader {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("The Little Match Girl.txt");
        BufferedReader in = new BufferedReader(fr);
        
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        in.close();
    }
}
