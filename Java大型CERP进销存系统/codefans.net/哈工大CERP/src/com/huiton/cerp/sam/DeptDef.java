package com.huiton.cerp.sam;

/**
 * Title:   部门定义类
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author 王涛
 * @version 1.0
 */

public class DeptDef {

  public DeptDef() {
  }

  /**
   * 公司代号
   */
  public String companyCode;

  /**
   * 部门代号
   */
  public String Code;

  /**
   * 部门名称
   */
  public String Name;

  /**
   * 部门父代号
   */
  public String parentCode;

  /**
   * 部门主管的职位代号
   */
  public String masterPosCode;
}