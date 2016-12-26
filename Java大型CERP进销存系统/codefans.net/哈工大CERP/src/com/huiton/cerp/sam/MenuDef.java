package com.huiton.cerp.sam;

/**
 * Title: 菜单属性定义类
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class MenuDef {

  public int level =0;
  public String menuCode = "";
  public String parentCode = "";
  public String menuName_zh = "";
  public String menuName_en = "";
  public String menuName_tw = "";
  public boolean hasSubMenu = false;
  public String progCode = "";

  public MenuDef() {
  }

}