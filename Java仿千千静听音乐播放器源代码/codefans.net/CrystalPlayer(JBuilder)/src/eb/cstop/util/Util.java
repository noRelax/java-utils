package eb.cstop.util;
//download:http://www.codefans.net
import java.awt.FlowLayout;
import java.security.MessageDigest;

import javax.swing.*;

import eb.cstop.swing.CrystalButton;

public class Util {
	
	private final static String[] hex = { "00", "01", "02", "03", "04", "05","06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10","11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B","1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26","27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31","32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C","3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47","48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52","53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D","5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68","69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73","74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E","7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89","8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94","95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F","A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA","AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5","B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0","C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB","CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6","D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1","E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC","ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7","F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };
	private final static byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01,0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };
	
	public static void main(String[] args){
		System.out.println(Util.Hex10To16(51));
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(320,240);
		frame.getContentPane().add(new CrystalButton("按钮"));
		frame.getContentPane().add(new CrystalButton("按钮"));
		frame.setVisible(true);
	}
	
	/**
	 * MD5算法加密
	 * @param password
	 * @return
	 */
	public static String doEncrypt(String password){
		try{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] bytes = digest.digest(password.getBytes());
			password = Util.encrypt(bytes);
		}
		catch(Exception e){
			e.printStackTrace();
			password = null;
		}
		return password;
	}
	
	/**
	 * 加密计算
	 * @param bytes
	 * @return
	 */
	private static String encrypt(byte[] bytes){
		String value = "";
		String stmp = "";
		for (int i = 0;i < bytes.length;i++){
			stmp = Integer.toHexString(bytes[i] & 0XFF);
			if (stmp.length() == 1)value = value + "0" + stmp;
			else value = value + stmp;
		}
		return value.toUpperCase();
    }
	
	/**
	 * JavaScript escape函数算法
	 * @param value
	 * @return
	 */
	public static String escape(String value){
		StringBuffer buffer = new StringBuffer();
		int length = value.length();
		int ch = 0;
		for (int i = 0; i < length; i++) {
			ch = value.charAt(i);
			if ('A' <= ch && ch <= 'Z')buffer.append((char) ch);
			else if ('a' <= ch && ch <= 'z')buffer.append((char) ch);
			else if ('0' <= ch && ch <= '9')buffer.append((char) ch);
			else if (ch == '-' || ch == '_'	|| ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')')buffer.append((char) ch);
			else if (ch <= 0x007F) {buffer.append('%');buffer.append(hex[ch]);}
			else{buffer.append('%');buffer.append('u');buffer.append(hex[(ch >>> 8)]);buffer.append(hex[(0x00FF & ch)]);}
		}
		return buffer.toString();
	}
	
	/**
	 * JavaScript unescape函数算法
	 * @param value
	 * @return
	 */
	public static String unescape(String value) {
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		int length = value.length();
		int ch = 0;
		while (i < length) {
			ch = value.charAt(i);
			if ('A' <= ch && ch <= 'Z')buffer.append((char) ch);
			else if ('a' <= ch && ch <= 'z')buffer.append((char) ch);
			else if ('0' <= ch && ch <= '9')buffer.append((char) ch);
			else if (ch == '-' || ch == '_' || ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\'' || ch == '(' || ch == ')')buffer.append((char) ch);
			else if (ch == '%') {
				int cint = 0;
				if('u' != value.charAt(i + 1)) {
					cint = (cint << 4) | val[value.charAt(i + 1)];
					cint = (cint << 4) | val[value.charAt(i + 2)];
					i += 2;
				}
				else {
					cint = (cint << 4) | val[value.charAt(i + 2)];
					cint = (cint << 4) | val[value.charAt(i + 3)];
					cint = (cint << 4) | val[value.charAt(i + 4)];
					cint = (cint << 4) | val[value.charAt(i + 5)];
					i += 5;
				}
				buffer.append((char) cint);
			}
			else buffer.append((char) ch);
			i++;
		}
		return buffer.toString();
	}
	
	/**
	 * 10进制转换为16进制
	 */
	public static String Hex10To16(int value){
		String hexValue = "0x";
		if(value < 256 && value > -1){
			int number = value/16;
			hexValue += Util.getHex10To16Value(number);
			number = value%16;
			hexValue += Util.getHex10To16Value(number);
			return hexValue;
		}
		return hexValue;
	}
	
	private static String getHex10To16Value(int value){
		if(value < 10)return value + "";
		else if(value == 10)return "a";
		else if(value == 11)return "b";
		else if(value == 12)return "c";
		else if(value == 13)return "d";
		else if(value == 14)return "e";
		else if(value == 15)return "f";
		else return "0";
	}
}