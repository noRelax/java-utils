package kava.util;

public class Util {

	public static byte[] int2Bytes(int value, int len) {
		byte[] bytes = new byte[len];
		for (int i = len - 1; i >= 0; i--) {
			bytes[i] = (byte) ((value >> (8 * (len - 1 - i))) & 0xff);
		}
		return bytes;
	}

	public static void int2Bytes(int value, int len, byte[] target, int offset) {
		for (int i = len - 1; i >= 0; i--) {
			target[offset + i] = (byte) ((value >> (8 * (len - 1 - i))) & 0xff);
		}
	}

	public static int bytes2Int(byte[] bytes) {
		return bytes2Int(bytes, 0, bytes.length);
	}

	public static int bytes2Int(byte[] bytes, int offset, int len) {
		int value = 0;
		for (int i = len - 1; i >= 0; i--) {
			int b = bytes[offset + i] & 0xff;
			value = (b << (8 * (len - 1 - i))) | value;
		}
		return value;
	}

}
