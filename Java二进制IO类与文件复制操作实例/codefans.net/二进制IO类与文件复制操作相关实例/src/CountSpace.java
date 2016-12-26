import java.io.*;

public class CountSpace {
    public static void main(String[] args) throws IOException {
        Reader in = new FileReader("The Little Match Girl.txt");
        int ch;
        int total = 0;
        int spaces = 0;
        while ((ch = in.read()) != -1) {
            if (Character.isWhitespace((char) ch)) {
                spaces++;
            }
            total++;
        }
        System.out.println(total + " chars, " + spaces + " spaces");
    }

}
