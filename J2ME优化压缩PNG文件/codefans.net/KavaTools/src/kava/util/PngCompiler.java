package kava.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class PngCompiler implements FileTraverserListener {

	private static final byte[] PNG_HEAD = { (byte) 0x89, (byte) 0x50,
			(byte) 0x4E, (byte) 0x47, (byte) 0x0D, (byte) 0x0A, (byte) 0x1A,
			(byte) 0x0A };

	private File targetFolder;
	private long sizeBefore;
	private long sizeAfter;

	public PngCompiler(File targetFolder) {
		this.targetFolder = targetFolder;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (args == null || args.length == 0) {
			System.out
					.println("syntax:java -jar PngCompiler <target folder path>");
		} else {
			PngCompiler compiler = new PngCompiler(new File(args[0]));
			compiler.compile();
			System.out.println("before compress,total size = "
					+ compiler.sizeBefore + ",after compress total size = "
					+ compiler.sizeAfter);
		}

	}

	public void compile() throws Exception {
		FileTraverser ft = new FileTraverser(this, "png");
		System.out.println("================begin compress==================");
		ft.visitAllFiles(this.targetFolder);
		System.out.println("================end compress==================");
	}

	@Override
	public void treatFile(File file) throws Exception {
		long fileLen = file.length();
		byte[] data = compileFile(file);
		sizeBefore += fileLen;
		if (data != null) {
			// good png
			if (file.delete()) {
				if (file.createNewFile()) {
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(file));
					bos.write(data);
					bos.close();
					sizeAfter += data.length;
					System.out.println("[" + file.getName() + "] compressed,"
							+ fileLen + "=>" + data.length);
				} else {
					System.out.println("can't recreate file:" + file.getName()
							+ ",please try again");
				}
			} else {
				System.out.println("can't delete file:" + file.getName()
						+ " skiped,please try again");
			}

		} else {
			System.out.println("bad png file:" + file.getName() + ",skiped");
			sizeAfter += fileLen;
		}
	}

	private byte[] compileFile(File pngFile) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		RandomAccessFile raf = new RandomAccessFile(pngFile, "r");
		if (readAndCheckHead(raf)) {
			baos.write(PNG_HEAD);
			byte[] sizeData;
			byte[] typeData;
			while (true) {
				sizeData = new byte[4];
				if (raf.read(sizeData) != sizeData.length) {
					break;
				}

				typeData = new byte[4];
				if (raf.read(typeData) != typeData.length) {
					break;
				}
				String blockType = new String(typeData);
				int contentSize = Util.bytes2Int(sizeData);

				if (blockType.equals("IHDR") || blockType.equals("PLTE")
						|| blockType.equals("IDAT") || blockType.equals("IEND")
						|| blockType.equals("tRNS")) {

					// assert contentSize > 0;
					byte[] content = new byte[contentSize];
					if (raf.read(content) != contentSize) {
						break;
					}

					byte[] crcData = new byte[4];
					if (raf.read(crcData) != crcData.length) {
						break;
					}

					baos.write(sizeData);
					baos.write(typeData);
					baos.write(content);
					baos.write(crcData);
					if (blockType.equals("IEND")) {
						break;
					}
				} else {
					raf.skipBytes(contentSize + 4);
				}
			}

			raf.close();
			baos.close();
			return baos.toByteArray();
		} else {
			raf.close();
			baos.close();
			return null;
		}

	}

	private boolean readAndCheckHead(RandomAccessFile raf) throws Exception {
		byte[] head = new byte[8];
		if (raf.read(head) == head.length) {
			return byteArrayEquals(head, PNG_HEAD);
		} else {
			return false;
		}

	}

	public static boolean byteArrayEquals(byte[] ba1, byte[] ba2) {
		if (ba1 != null) {
			if (ba2 != null) {
				if (ba1.length == ba2.length) {
					for (int i = 0; i < ba1.length; i++) {
						if (ba1[i] != ba2[i]) {
							return false;
						}
					}
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}
		} else {
			return ba2 == null;
		}
	}

}
