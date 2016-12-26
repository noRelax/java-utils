package com.huiton.cerp.sam;

/**
 * Title:   收藏夹项定义类
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author ：王涛
 * @version 1.0
 */

public class FavorDef {

  public FavorDef() {

  }
  /**
   * 收藏夹项名称
   */
  public String name = "";

  /**
   * 收藏夹项的排序索引
   */
  public String index = "";

  /**
   * 收藏家的类别：A、B、C三类
   */
  public String level = "";

  /**
   * 收藏夹对应的程序代号
   */
  public String progUrl = "#";

  /**
   * 收藏项对应的系统代号
   */
  public String sysCode = "";

  /**
   * 收藏项对应的公司代号
   */
  public String companyCode = "";

  /**
   * 收藏项对应的用户代号
   */
  public String userId = "";

}