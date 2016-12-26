package com.huiton.cerp.sam;

import java.util.*;


/**
 * Title:       公司属性的定义类
 * Description:  该类定义了公司在sam_company表中的定义项
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author 王涛
 * @version 1.0
 */

public class ComDef {

  public ComDef() {
  }

  /**
   * 公司代号
   */
  public String comCode;

  /**
   * 公司父代号
   */
  public String comParCode;

  /**
   * 公司名称
   */
  public String comName;

  /**
   * 公司英文名称
   */
  public String comNameEn;
}