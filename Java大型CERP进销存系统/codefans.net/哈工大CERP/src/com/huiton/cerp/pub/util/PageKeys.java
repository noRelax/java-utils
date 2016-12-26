package com.huiton.cerp.pub.util;

/**
 * <p>Title: 页面常数定义类</p>
 * <p>Description: 这里定义地页面常数将来有可能在配置文件中定义，因此为兼容以后的变化这些常数将以get形式返回，并且必须实例化本类</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: BRITC</p>
 * @author 王涛
 * @version 1.0
 */
public class PageKeys {
  static int rows2C = 14;    //两行条件的列表行数
  static int rows1C = 16;    //一行条件的列表行数
  static int rows0C = 18;    //无条件的列表行数

  static int rows2CW = 10; //两行条件的弹出窗口列表行数
  static int rows1CW = 12; //一行条件的弹出窗口列表行数
  static int rows0CW = 14;   //无条件的弹出窗口列表行数

  public PageKeys() {
  }

  /**
   * 无条件的列表行数
   */
  public static int  getRows0C() {
    return rows0C;
  }

  /**
   * 一行条件的列表行数
   */
  public static int getRows1C() {
    return rows1C;
  }

  /**
   * 两行条件的列表行数
   */
  public static int getRows2C() {
    return rows2C;
  }

  /**
   * 两行条件的弹出窗口列表行数
   */
  public static int getRows2CW() {
    return rows2CW;
  }

  /**
   * 无条件的弹出窗口列表行数
   */
  public static int getRows0CW() {
    return rows0CW;
  }

  /**
   * 一行条件的弹出窗口列表行数
   */
  public static int getRows1CW() {
    return rows1CW;
  }
}