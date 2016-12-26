package fileIO;

import java.io.*;

/** Little Endian Data Output Stream is used mainly for compatability with software written in other languages such as c++.
 */
public class LEDataOutputStream extends OutputStream
{
 private OutputStream os;

    public LEDataOutputStream(File f) throws IOException
    {
        this.os = new FileOutputStream(f);
    }

    public LEDataOutputStream(OutputStream os)
    {
        this.os = os;
    }

    public void close() throws IOException
    {
        os.close();
    }

    public void flush() throws IOException 
    {
        os.flush();
    }

    public void write(int i) throws IOException 
    {
        os.write(i);
    }

    public void write(byte[] data) throws IOException
    {
        os.write(data);
    }

    public void write(byte[] data,int offset,int len) throws IOException
    {
        os.write(data,offset,len);
    }

    /** fills n bytes with the value specified by byteVal
     */
    public void fillNBytes(int n, int byteVal) throws IOException 
    {
        if (n <= 0) return;
        for (int i = 0; i < n; i++)
            os.write(byteVal);
    }

    public void writeInt(int i) throws IOException
    {
        os.write(i);
        os.write(i>>8);
        os.write(i >> 16);
        os.write(i >> 24);
    }

    public void writeIntBigEndian(int i) throws IOException
    {
        os.write(i >> 24);
        os.write(i >> 16);
        os.write(i >> 8);
        os.write(i);
    }

    public void writeByte(int v) throws IOException
    {
        os.write(v);
    }

    public void writeShort(int v) throws IOException
    {
        os.write(v);
        os.write(v >> 8);
    }

    public void writeShortBigEndian(int v) throws IOException
    {
        os.write(v>>8);
        os.write(v);
    }

    public void writeStringBytes(String s) throws IOException 
    {
        os.write(s.getBytes());
    }

    public void writeLong(long v) throws IOException
    {
        for (int i = 0; i < 8; i++)
            os.write((int)(v >> (i*8)));
    }

    public void writeLongBigEndian(long v) throws IOException
    {
        for (int i = 7; i >= 0; i--)
            os.write((int)(v >> (i * 8)));
    }

    public void writeFloat(double d) throws IOException
    {
        int bits = Float.floatToIntBits((float)d);
        writeInt(bits);
    }

    public void writeFloatBigEndian(double d) throws IOException
    {
        int bits = Float.floatToIntBits((float)d);
        writeIntBigEndian(bits);
    }

    public void writeDouble(double d) throws IOException
    {
        long l = Double.doubleToLongBits(d);
        writeLong(l);
    }

    public void writeDoubleBigEndian(double d) throws IOException
    {
        long l = Double.doubleToLongBits(d);
        writeLongBigEndian(l);
    }
}