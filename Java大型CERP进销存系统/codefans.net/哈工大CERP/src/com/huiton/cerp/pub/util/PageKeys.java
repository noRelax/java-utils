package com.huiton.cerp.pub.util;

/**
 * <p>Title: ҳ�泣��������</p>
 * <p>Description: ���ﶨ���ҳ�泣�������п����������ļ��ж��壬���Ϊ�����Ժ�ı仯��Щ��������get��ʽ���أ����ұ���ʵ��������</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: BRITC</p>
 * @author ����
 * @version 1.0
 */
public class PageKeys {
  static int rows2C = 14;    //�����������б�����
  static int rows1C = 16;    //һ���������б�����
  static int rows0C = 18;    //���������б�����

  static int rows2CW = 10; //���������ĵ��������б�����
  static int rows1CW = 12; //һ�������ĵ��������б�����
  static int rows0CW = 14;   //�������ĵ��������б�����

  public PageKeys() {
  }

  /**
   * ���������б�����
   */
  public static int  getRows0C() {
    return rows0C;
  }

  /**
   * һ���������б�����
   */
  public static int getRows1C() {
    return rows1C;
  }

  /**
   * �����������б�����
   */
  public static int getRows2C() {
    return rows2C;
  }

  /**
   * ���������ĵ��������б�����
   */
  public static int getRows2CW() {
    return rows2CW;
  }

  /**
   * �������ĵ��������б�����
   */
  public static int getRows0CW() {
    return rows0CW;
  }

  /**
   * һ�������ĵ��������б�����
   */
  public static int getRows1CW() {
    return rows1CW;
  }
}