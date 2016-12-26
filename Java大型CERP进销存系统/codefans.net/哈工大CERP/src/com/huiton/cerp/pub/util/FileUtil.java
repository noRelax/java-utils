package com.huiton.cerp.pub.util;

/**
 * Title:        CERP测试框架
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author 吴剑
 * @version 1.0
 */

import java.io.*;

public class FileUtil {

    /**
     * 复制文件
     */
    public static void copyFile(String srcFile, String destFile)
        throws IOException
    {

        RandomAccessFile rfRead=null;
        RandomAccessFile rfWrite=null;
        try {
            rfRead = new RandomAccessFile(srcFile, "r");
            rfWrite = new RandomAccessFile(destFile, "rw");

            byte[] b = new byte[1];
            int flag = rfRead.read(b);
            for(int i=0;flag!=-1;i++) {
                rfWrite.write(b);
                flag = rfRead.read(b);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            rfRead.close();
            rfWrite.close();
        }
    }

    /**
     * 删除文件
     */
    public static boolean deleteFile(String fileName) throws IOException {
        File fl = new File(fileName);
        return fl.delete();
    }
     /**
     * 删除文件路径
     */
    public static boolean deleteFileInSequence(String fileName,String rootDir) throws IOException {
        boolean bl=true;
        if(fileName.lastIndexOf("/")==fileName.length()){
            fileName.substring(0,fileName.lastIndexOf("/"));
        }
        if(rootDir.lastIndexOf("/")==rootDir.length()){
            rootDir.substring(0,rootDir.lastIndexOf("/"));
        }
         File fl = new File(fileName);
         String tempFileName=fileName;
         System.out.println("tempFileName="+tempFileName);
         while(bl&&!tempFileName.equals(rootDir)){
            bl=fl.delete();
            int pos=tempFileName.lastIndexOf("/");
            tempFileName=tempFileName.substring(0,pos);
            System.out.println("tempFileName="+tempFileName);
            fl=new File(tempFileName);
        }

        return bl;
    }

    /**
     * 创建子目录
     */
    public static boolean makeDir(String dirName) throws IOException {
        boolean bl=true;
        if(dirName.lastIndexOf("/")!=dirName.length()){
            dirName=dirName.concat("/");
            //System.out.println("dirName="+dirName);
        }
        int pos=0;
        String temp=new String();
        while(dirName.lastIndexOf("/")>pos){
            pos=dirName.indexOf("/",pos+1);
            temp=dirName.substring(0,pos);
            //System.out.println("tempDirName="+temp);
            File fl = new File(temp);
            if(!fl.isDirectory())
                bl=fl.mkdir();
        }
        return bl;
    }
}