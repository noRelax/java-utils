package com.huiton.cerp;

import java.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author 王涛
 * @version 1.0
 */

public class CerpRes extends java.util.ListResourceBundle{
  static final Object[][] contents = new String[][]{
        { "NO.", "第" },
        { "PAGE","页"},
        { "TOTAL","共"},
        { "GET","选用"},
        { "BACK","返回"},
        { "QUERY","查询"},
        { "NO_PAGE","要查询的页面不存在："},
        { "SUBMIT","提交"},
        { "NO_ITEM_SELECTED","没有项目被选中!"},
        { "当页全选","当页全选"},
        { "全选","全选"},
      };
  public Object[][] getContents() {
    return contents;
  }

}