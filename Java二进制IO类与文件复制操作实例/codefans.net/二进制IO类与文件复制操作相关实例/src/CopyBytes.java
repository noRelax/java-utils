import java.io.*;
//download by http://www.codefans.net
public class CopyBytes {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("The Little Match Girl.txt");
        File outputFile = new File("outagain.txt");

        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);

        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }

        in.close();
        out.close();
    }
}