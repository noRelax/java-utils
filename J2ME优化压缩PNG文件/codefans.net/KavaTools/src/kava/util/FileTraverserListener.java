package kava.util;

import java.io.File;

public interface FileTraverserListener {
	public void treatFile(File file) throws Exception;
}
