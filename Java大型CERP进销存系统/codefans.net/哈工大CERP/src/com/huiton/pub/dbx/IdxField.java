package com.huiton.pub.dbx;

/**
 * Title:  索引字段描述类
 * Description:  本类描述了索引字段的描述方法
 * Copyright:    Copyright (c) 2001
 * Company: 利玛信息技术有限公司
 * @author 王涛
 * @version 1.0
 */
public class IdxField {

  public IdxField() {
  }
  /**
   * @const: fieldName 索引字段名
   * @const: isAsc  索引是否为升序，如改变量为flase，则索引为降序DESC
   */
  public String fieldName = "";
  public boolean isAsc = true;
}