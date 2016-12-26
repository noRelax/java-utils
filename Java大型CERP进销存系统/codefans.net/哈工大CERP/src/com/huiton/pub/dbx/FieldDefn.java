package com.huiton.pub.dbx;

import java.io.Serializable;

/**
 * Title: 表定义类
 * Description: 本类定义了字段表达的常量。当获取表的信息时使用本类
 * Copyright:    Copyright (c) 2001
 * Company: huiton
 * @author 王涛
 * @version 1.0
 */
public class FieldDefn implements Serializable  {
  /**
   * @const: colName 字段名
   * @const: colType  字段类型
   * @const: colDft 字段的缺省值
   * @const: nullable 是否允许为空
   * @const: isPriKey 是否为主键
   * @const: colLen 列的长度
   */
  public String colName = "";

  public String colName_cn = "";    //中文别名
  public String colName_en = "";    //英文别名
  public String colName_tw = "";    //繁体别名

  public int colType;
  public String colDft = "";
  public boolean nullable = false;
  public boolean isPriKey = false;
  public int colLen = 0;
  public String colWidth = "";
  public Boolean isFix;     //固定显示字段

  public FieldDefn () {
  }
}