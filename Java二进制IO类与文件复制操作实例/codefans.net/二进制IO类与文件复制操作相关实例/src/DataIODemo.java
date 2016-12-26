import java.io.*;

public class DataIODemo {
    public static void main(String[] args) throws IOException {

        // write the data out
        DataOutputStream out = new DataOutputStream(new
				   FileOutputStream("invoice.txt"));

        double[] prices = { 19.99, 9.99, 15.99, -1, 4.99 };
        int[] units = { 12, 8, 13, 29, 50 };
        String[] descs = { 
                "Java T-shirt",
			   "Java Mug",
			   "Duke Juggling Dolls",
			   "Java Pin",
			   "Java Key Chain" };
        
        for (int i = 0; i < prices.length; i ++) {
            out.writeDouble(prices[i]);
            out.writeChar('\t');
            out.writeInt(units[i]);
            out.writeChar('\t');
            out.writeUTF(descs[i]);
            out.writeChar('\n');
        }
        out.close();

        // read it in again
        DataInputStream in = new DataInputStream(new
				 FileInputStream("invoice.txt"));

        double price;
        int unit;
        String desc;
        double total = 0.0;

        try {
            while (true) {
                price = in.readDouble();
                in.readChar();       // throws out the tab
                unit = in.readInt();
                in.readChar();       // throws out the tab
                desc = in.readUTF();
                in.readChar();
                System.out.println("�㶩����" +
				    unit + "��" +
				    desc + "���۸���$" + price);
                total = total + unit * price;
            }
        } catch (EOFException e) {
        }
        System.out.println("�ܶ�: $" + total);
        in.close();
    }
}
