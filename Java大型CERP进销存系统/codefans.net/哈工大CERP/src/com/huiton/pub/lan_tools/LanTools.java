package com.huiton.pub.lan_tools;

import java.lang.*;
import java.io.*;
import java.util.Vector;

/**
 * Title:        ���ÿ�������
 * Description:  ������Ϊ�������õ���һЩ���ô���
 * Copyright:    Copyright (c) 2001
 * Company:      ������Ϣ�������޹�˾
 * @author ����
 * @version 1.0
 * Date:2001.5.9
 */

public class LanTools {

 /**
  *�������ж������Ƿ���ȵ��ݲ�
  */
 public final static double NUM_TOL = 1.0e-5;

  public LanTools() {
  }

  /**
   * ���������ַ���str��ǰ��ո�ȥ��������ͷ��ӵ�����
   * @param: strҪת�����ַ���
   */
 static public String toSqlString(String str) {
  if (str == null)
    return "''";
  if (str == "''")
    return str;

  return "'"+str.trim()+"'";
 }

 /**
  * �������ݵ��ݲ�ֵ��NUM_TOL���ж��������������Ƿ����
  * @param num1��num2�����жϵ��������ݡ�
  * @return ���num1��num2�Ĳ�ֵ���ݲ�ķ�Χ֮�ڣ�����true�����򷵻�false
  */
 static public boolean isEql(double num1, double num2)
 {double subValue = num1 - num2;

  if (java.lang.Math.abs(subValue) < NUM_TOL)
    return true;     //��Ϊ���

  return false;
 }

  /**
   * ��Unicode��ת��ΪGB��
   * @param strIn:Ҫת�����ַ���
   * @return �ɹ�ʱ����ת������ַ���
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
     * ��GB��ת��ΪUnicode��
     * @param strIn:Ҫת�����ַ���
     * @return �ɹ�ʱ����ת������ַ���
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
   * ��������ֵ
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
   * ����һ���ַ�����������һ���ַ���λ��
   * @param srcString: ���������ַ���
   * @param subString: Ҫ���ҵ��ַ���
   * @rteturn  subString������ʱ���ظ�λ�ã���0Ϊ��ʼ�����������򷵻�-1;
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
   * �滻һ���ַ���
   * @param srcString: Դ�ַ���λ��
   * @param subString2Replace: ���滻�����ַ���
   * @param newString: �����滻�����ַ���
   * @return �ɹ�ʱ���ر��滻���ĵ��ַ����� ���򷵻�ԭ�ַ�����
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