import java.io.*;

public class Copy {
    public static void main(String[] args) throws IOException {
        FileReader in = new FileReader("The Little Match Girl.txt");
        FileWriter out = new FileWriter("outagain.txt");

        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }

        in.close();
        out.close();
    }
}