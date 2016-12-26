package com.huiton.pub.lan_tools;

import java.lang.*;
import java.io.*;
import java.util.Vector;

/**
 * Title:        常用开发工具
 * Description:  本工具为开发中用到的一些常用处理
 * Copyright:    Copyright (c) 2001
 * Company:      利玛信息技术有限公司
 * @author 王涛
 * @version 1.0
 * Date:2001.5.9
 */

public class LanTools {

 /**
  *常量：判断数字是否相等的容差
  */
 public final static double NUM_TOL = 1.0e-5;

  public LanTools() {
  }

  /**
   * 本方法将字符串str的前后空格去掉后，在两头添加单引号
   * @param: str要转换的字符串
   */
 static public String toSqlString(String str) {
  if (str == null)
    return "''";
  if (str == "''")
    return str;

  return "'"+str.trim()+"'";
 }

 /**
  * 根据数据的容差值（NUM_TOL）判断两个浮点数据是否相等
  * @param num1、num2用于判断的两个数据。
  * @return 如果num1和num2的差值在容差的范围之内，返回true。否则返回false
  */
 static public boolean isEql(double num1, double num2)
 {double subValue = num1 - num2;

  if (java.lang.Math.abs(subValue) < NUM_TOL)
    return true;     //判为相等

  return false;
 }

  /**
   * 将Unicode码转换为GB码
   * @param strIn:要转换的字符串
   * @return 成功时返回转换后的字符串
   */
  public static String unicodeToGB(String strIn){
    byte[] b;
    String strOut = null;

    if(strIn == null || (strIn.trim()).equals(""))
      return strIn;
  try{
      b = strIn.getBytes("GBK");
      strOut = new String(b,"ISO8859_1");
   }catch(UnsupportedEncodingException e){}
    return strOut;
  }

    /**
     * 将GB码转换为Unicode码
     * @param strIn:要转换的字符串
     * @return 成功时返回转换后的字符串
    */
    public static String GBToUnicode(String strIn){
      String strOut = null;
      if(strIn == null || (strIn.trim()).equals(""))return strIn;
    try{
      byte[] b = strIn.getBytes("ISO8859_1");
      strOut = new String(b,"GBK");
    }catch(Exception e){}
      return strOut;
  }

  /**
   * 拷贝向量值
   */
  public static Vector copyVector(Vector sVect) {
    Object[] old = new Object[sVect.size()];
    Vector tVector = new Vector();

    sVect.copyInto(old);
    for (int i=0; i<old.length; i++)
      tVector.addElement(old[i]);

    return tVector;
  }

  /**
   * 搜索一个字符串包含的另一个字符串位置
   * @param srcString: 被搜索的字符串
   * @param subString: 要查找的字符串
   * @rteturn  subString被包含时返回该位置（以0为起始索引），否则返回-1;
   */
  public static int hasString(String srcString, String subString) {
    if (srcString==null || subString==null)
      return -1;
    int subLen = subString.length();
    int srcLen = srcString.length();
    int index;
    for (int i=0; i< subLen; i++) {
      index = srcString.indexOf(subString,i);
      if (index >=i)
        return index;
    }

    return -1;
  }

  /**
   * 替换一次字符串
   * @param srcString: 源字符串位置
   * @param subString2Replace: 被替换掉的字符串
   * @param newString: 用于替换的新字符串
   * @return 成功时返回被替换过的的字符串， 否则返回原字符串。
   */
  public static String substString(String srcString, String subString2Replace,
        String newString) {
    int index = LanTools.hasString(srcString,subString2Replace);
    if (index == -1)
      return srcString;
    int subLen = subString2Replace.length();
    String strHead = srcString.substring(0,index);
    String strEnd = srcString.substring(index + subLen);
    return strHead + newString + strEnd;
  }

}