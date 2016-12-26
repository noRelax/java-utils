package com.huiton.cerp.sam;

/**
 * Title:  会话信息定义类
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author 王涛
 * @version 1.0
 */

public class SessionDef {

  public SessionDef() {
  }

  /**
   * 会话代号
   */
  public String sessionId;
  /**
   * 访问公司代号
   */
  public String companyCode;
  /**
   * 访问数据库年号
   */
  public String year;
  /**
   * 用户代号
   */
  public String userId;
  /**
   * 登录时间
   */
  public String loginDateTime;
}