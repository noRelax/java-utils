import java.io.*;

public class CountBytes {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("The Little Match Girl.txt");

        int total = 0;
        while (in.read() != -1)
            total++;
        
        in.close();

        System.out.println(total + " bytes");
    }
}
