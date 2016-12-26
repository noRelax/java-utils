package kava.util;

import java.io.File;

public class FileTraverser {
	private FileTraverserListener listener;
	private String postfix;
	public FileTraverser(FileTraverserListener _listener,String _postfix){
		this.listener = _listener;
		this.postfix = _postfix;
	}
		
	public void visitAllFiles(File dir) throws Exception{
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				visitAllFiles(new File(dir, children[i]));
			}
		} else {
			String name = dir.getName().toLowerCase();
			if(name.endsWith(postfix.toLowerCase())){
				listener.treatFile(dir);
			}
		}
	}
}
