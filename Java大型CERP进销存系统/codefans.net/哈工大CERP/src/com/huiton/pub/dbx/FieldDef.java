package com.huiton.pub.dbx;

import java.io.Serializable;

/**
 * Title: 表定义类，用于创建新表
 * Description: 本类定义了字段表达的常量。当用JdbOp.createTbl创建表时，将引用本类的数组定义表的字段类型
 * Copyright:    Copyright (c) 2001
 * Company: huiton
 * @author 王涛
 * @version 1.0
 */
public class FieldDef implements Serializable  {
  /**
   * @const: colName 字段名
   * @const: colType  字段类型
   * @const: colDft 字段的缺省值
   * @const: nullable 是否允许为空
   * @const: isPriKey 是否为主键
   */
  public String colName = "";

  public String colName_cn = "";    //中文别名
  public String colName_en = "";    //英文别名
  public String colName_tw = "";    //繁体别名

  public String colType="";
  public String colDft = "";
  public boolean nullable;
  public boolean isPriKey;

  public FieldDef () {
  }
}