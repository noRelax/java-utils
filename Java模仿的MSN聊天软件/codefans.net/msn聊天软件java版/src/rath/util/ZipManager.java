/*
 * @(#)ZipManager.java
 *
 * Copyright (c) 2001, JangHo Hwang
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 	1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 	2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 	3. Neither the name of the JangHo Hwang nor the names of its contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *    $Id: ZipManager.java,v 1.1.1.1 2002/03/08 21:20:46 xrath Exp $
 */
package rath.util;

import java.io.*;
import java.util.*;
import java.util.zip.*;
/**
 * Zip������ ������ ������ Ǯ��, ���� ���ϵ��� ���Ƽ�
 * Zip���� �����ִ� ���� �����ϰ� ����� �� �ִ�
 * �������̽��� �����ϴ� Ŭ�����̴�.
 *
 * @author JangHo Hwang, rath@linuxkorea.co.kr
 * @version $Id: ZipManager.java,v 1.1.1.1 2002/03/08 21:20:46 xrath Exp $
 */
public class ZipManager
{
	// CRC update�� �Ҷ� ����� buffer�� ũ��
	public static final int BUFFER_SIZE = 1024;
	// ���� ��� �������̸�, ���� ����� ���� static final�� ����
	public static final String FS = System.getProperty("file.separator");
	private String archiveRoot = System.getProperty("user.dir");

	/**
	 * �� ���� ������ ��Ʈ ���丮 ��ġ�� ���Ѵ�.
	 * ���ϵ��� ���϶���, �� ������ ���� ��ο���
	 * �� ��ī�̺� ��Ʈ ���丮�� ������ ��θ���
	 * ���� zip���뿡���� Entry name�� �ȴ�.
	 *
	 * @param    directory    ��ī�̺� ��Ʈ ���丮.
	 */
	public void setArchiveRoot( String directory )
	{
		this.archiveRoot = directory;
	}

	/**
	 * ���� ��ī�̺� ��Ʈ ���丮�� ��ȯ�Ѵ�.
	 * @return    ��ī�̺� ��Ʈ ���丮.
	 */
	public String getArchiveRoot()
	{
		return archiveRoot;
	}

	public void doCompress( File file, File toCreate )
		throws IOException
	{
		if( toCreate.exists() )
			throw new IOException( toCreate + " is already exist" );

		FileOutputStream fos = new FileOutputStream( toCreate );
		doCompress( file, fos );
	}

	public void doCompress( File file, OutputStream out )
		throws IOException
	{
		ZipOutputStream zos = new ZipOutputStream( out );

		compress( file, zos );

		zos.setMethod( ZipOutputStream.DEFLATED );
		zos.close();
	}

	private void compress( File file, ZipOutputStream zos )
		throws IOException
	{
		if( file.isDirectory() )
		{
			File[] files = file.listFiles();
			for(int i=0; i<files.length; i++)
			{
				if( files[i].isDirectory() )
					compress( files[i], zos );
				else
					addFile( files[i], zos );
			}
		}
		else
		if( file.isFile() )
		{
			addFile( file, zos );
		}
	}

	public void addFile( File file, ZipOutputStream zos )
		throws IOException
	{
		if( Thread.currentThread().isInterrupted() )
			return;

		compressStarted( file );

		String enname = file.getAbsolutePath().substring(archiveRoot.length()+1);
		ZipEntry en = new ZipEntry( enname );
		CRC32 crc32 = new CRC32();

		byte[] chs = new byte[1024];

		FileInputStream fis = new FileInputStream(file);
		int len = 0;
		while( (len=fis.read( chs )) > -1 )
			crc32.update( chs, 0, len );
		fis.close();

		en.setSize( file.length() );
		en.setTime( file.lastModified() );
		en.setCrc( crc32.getValue() );

		zos.putNextEntry( en );
		fis = new FileInputStream(file);
		while( (len=fis.read( chs )) > -1 )
			zos.write( chs, 0, len );
		fis.close();
		zos.closeEntry();

		compressComplete( file );
	}

	/**
	 * �ش� ������ �����ϱ� ������ �Ҹ����.
	 *
	 * @param file
	 */
	protected void compressStarted( File file )
	{

	}

	/**
	 * �ش� ������ ������ ���� �� �� �Ҹ����.
	 *
	 * @param file
	 */
	protected void compressComplete( File file )
	{

	}
}
