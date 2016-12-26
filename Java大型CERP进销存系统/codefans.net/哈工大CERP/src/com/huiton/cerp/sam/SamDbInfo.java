package com.huiton.cerp.sam;

import java.util.Vector;
import java.sql.*;

import com.huiton.pub.dbx.*;
import com.huiton.pub.lan_tools.LanTools;
import com.huiton.mainframe.util.tracer.Debug;


/**
 * Title: 系统管理库信息
 * Description:  本类提供了读取sam库的信息接口。非sam模块的程序应从本类获取sam库的数据
 * Copyright:    Copyright (c) 2001
 * Company: 利玛信息技术有限公司
 * @author 王涛
 * @version 1.0
 */

public class SamDbInfo {

  JdbOp dbObj;
  String errMsg="";

  public SamDbInfo() {
  try {
//System.out.println("workdir:" + System.getProperty("user.dir"));
  dbObj = new JdbOp("","sam");

  }catch (Exception e)  {
    errMsg = this.getClass().getName() + ".SamDbInfo(): " + e;
  }

  }

  /**
   * Tile: 获取系统执行的错误信息
   */
  public String getErrMsg() {
    return errMsg;
  }

  /**
   * Title: 获取用户的名称
   * @param userUniCode: 用户代号
   * @return 成功时返回用户名称，否则返回null
   */
  public String getUserName(String userUniCode) {
    errMsg = "";
  try {
    ResultSet rst;
    String mySql = "select user_name from sam_employee where user_unique_no = " + LanTools.toSqlString(userUniCode);
    rst = dbObj.getData(mySql);
    if (rst == null)
      throw new Exception(dbObj.getErrMsg());
    if (rst.next())
      return rst.getString("user_name");
    return "无名者";
  }catch (Exception e)  {
    errMsg = this.getClass().getName() + ".getUserName(): " + e;
    return null;
  }
  }

  /**
   * Title: 获取程序文件名
   * @param progCode: 程序文件代号
   * @return 成功时返回程序文件名(可能为空)，否则返回null
   */
  public String getProgFileName(String progCode)  {
    errMsg = "";
  try {
    ResultSet rst;
    String mySql = "select prog_value from scg_program where prog_code = " + LanTools.toSqlString(progCode);
    rst = dbObj.getData(mySql);
    if (rst == null)
      throw new Exception(dbObj.getErrMsg());
    if (rst.next())
      return rst.getString("prog_value");
    return "";
  }catch (Exception e)  {
    errMsg = this.getClass().getName() + ".getProgFileName(): " + e;
    return null;
  }
  }

  /**
   * 获取父菜单索引
   * @param menuList: 菜单向量
   * @param pCode: 父菜单号
   * @param curItem: 当前项的索引号
   * @return 成功时返回索引，否则返回-1
   */
  int getParentIndex(Vector menuList, String pCode,int curItem) {
    for (int i=0; i<curItem; i++) {
      MenuDef md = (MenuDef)menuList.elementAt(i);
      if (md.menuCode.equalsIgnoreCase(pCode))	{
        md.hasSubMenu = true;
        return i;
      }
    }
    return -1;
  }

  /**
   * 形成菜单。本方法循环递归调用以完成菜单树的构造
   * @param userId: 用户代号
   * @param companyCode: 公司号
   * @return 成功时返回获得的菜单对象向量，否则返回null
   */
  public Vector getMenu(String userId,String companyCode) {
    errMsg = "";
  try {
    int count=0;    //才单项的计数
    Vector menuList = new Vector();
    ResultSet rst;
    String mySql;

    //按宽度优先取结果集
    mySql ="select * from sam_personal_menu where company_code = "+LanTools.toSqlString(companyCode);
    mySql += " and user_unique_no = " + LanTools.toSqlString(userId);
    mySql += " order by menu_level,menu_index";
    rst = dbObj.getData(mySql);
    while (rst.next())  {
      MenuDef md = new MenuDef();
      md.menuCode = rst.getString("menu_code");
      md.menuName_zh = rst.getString("menu_name_cn");
      md.menuName_en = rst.getString("menu_name_en");
      md.menuName_tw = rst.getString("menu_name_tw");
      md.level = rst.getInt("menu_level");
      int index = rst.getInt("menu_index");
      md.parentCode = rst.getString("menu_code_father");
      md.progCode = rst.getString("prog_code");
      menuList.add(md);
    }

    //处理有子标志
    for (int i=1; i<menuList.size(); i++ )  {
      MenuDef md = (MenuDef)menuList.elementAt(i);
      getParentIndex(menuList,md.parentCode,i);    //获取父菜单索引
    }
   return menuList;
}catch (Exception e) {
  errMsg = this.getClass().getName() + ".getMenu(): " + e;
  return null;
}
  }

  /**
   * 获取用户的收藏夹数据
   * @param uerId：用户代号
   * @param companyCode: 收藏夹所属的公司代号
   * @return 成功时返回包含收藏夹定义的向量，向量的每一个元素都是一个FavorDef类。失败时返回null。
   * @see FavorDef
   */
  public Vector getFavor(String userId, String companyCode)  {
    errMsg = "";
  try {
    String mySql = "",progId;
    Vector vRst = new Vector();
    ResultSet pRst;

    mySql = "select * from sam_personal_favor where user_unique_no = " + LanTools.toSqlString(userId);
    mySql += " and company_code = " + LanTools.toSqlString(companyCode);
    mySql += " order by favor_level,favor_index";
    ResultSet rst = dbObj.getData(mySql);
    if (rst == null)
      return vRst;
    while (rst.next()) {
      //添加收藏项
      FavorDef  fd = new FavorDef();
      fd.companyCode = companyCode;
      fd.userId = userId;
      fd.sysCode = rst.getString("sys_code");
      progId = rst.getString("prog_code");
      fd.name = rst.getString("favor_name");
      fd.index = rst.getString("favor_index");
      fd.level = rst.getString("favor_level");

      mySql = "select prog_value from scg_program where prog_code=" + LanTools.toSqlString(progId);
      mySql += " and sys_code=" + LanTools.toSqlString(fd.sysCode);
      pRst = dbObj.getData(mySql);
      if (pRst.next())
        fd.progUrl = pRst.getString("prog_value");
      vRst.addElement(fd);
    }
    return vRst;

  }catch (Exception e)  {
    errMsg = this.getClass().getName() + ".getFavor(): " + e;
    return null;
  }
  }

  /**
   * Title: 获取部门定义信息。
   * Description: 本方法获取指定公司的部门定义信息。
   * @param companyCode: 公司代号
   * @return 调用成功时返回一个向量，向量的每一项均为一个DeptDef实例
   * @see DeptDef
   */
  public Vector getDept(String companyCode) {

    errMsg = "";
//System.out.println("enter samdbinfo");
  try {
    Vector vRst = new Vector();
//System.out.println("select * from sam_dept where company_code =" + LanTools.toSqlString(companyCode));
    ResultSet rst = dbObj.getData("select * from sam_dept where company_code =" + LanTools.toSqlString(companyCode));
    while (rst.next())  {
      DeptDef dd = new DeptDef();
      dd.companyCode = companyCode;
      dd.Code = rst.getString("dept_code");
      dd.Name = rst.getString("dept_name");
      dd.parentCode = rst.getString("dept_code_father");
      dd.masterPosCode = rst.getString("master_position_code");
      vRst.add(dd);
//System.out.println(dd.companyCode);
    }

    return vRst;
}catch (Exception e)  {
    errMsg = this.getClass().getName() + ".getDept(): " + e;
    return null;
}
  }

  /**
   * 获取会话定义
   * @param sessionId: 要获取的会话代号
   * @return 成功时返回SessionDef对象，否则返回null
   */
  public SessionDef getSession(String sessionId)  {
    SessionDef sd = new SessionDef();

    errMsg = "";
try {
    ResultSet rst = dbObj.getData("select * from sam_session where session_code = " + LanTools.toSqlString(sessionId));
    if (rst.next()) {

      sd.companyCode = rst.getString("company_code");
      sd.sessionId = sessionId;
      sd.year = rst.getString("year");
      sd.userId = rst.getString("user_unique_no");
      sd.loginDateTime = rst.getString("login_dateTime");

    }
    else
      errMsg = "没有" + sessionId + "的登录信息。";
    return sd;
}catch (Exception e)  {
    errMsg = this.getClass().getName() + ".getSession(): " + e;
    return null;
}
  }

  /**
   * 获取公司属性
   * @param companyCode: 要提取属性的公司代号
   * @return 成功时返回ComDef对象，否则返回null
   */
  public ComDef getComAtt(String companyCode) {
    errMsg = "";
try {
    ResultSet rst = dbObj.getData("select * from sam_company where company_code=" + LanTools.toSqlString(companyCode));
    if (!rst.next())
      throw new Exception("无效的公司代号: " + companyCode);
    ComDef cd = new ComDef();
    cd.comCode = companyCode;
    cd.comName = rst.getString("company_name");
    cd.comParCode = rst.getString("company_code_father");
    cd.comNameEn = rst.getString("company_name_en");
    return cd;
}catch (Exception e)  {
    errMsg = this.getClass().getName() + ".getComAtt(): " + e;
    return null;
}
  }

    /**
     * 根据部门代号获取部门名称
     * @param deptCode: 要查询的部门代号
     * @return 成功返回该部门的名称，否则返回null。
     * @侯曦洋
     * @2001年12月25日
     */
    public String getDeptName(String deptCode, String companyCode)
    {
         try
         {
              errMsg = "";
              String mySql = "select dept_name from sam_dept where company_code = '" + companyCode.trim() +
                             "' and dept_code = '" + deptCode.trim() + "'" ;

              Debug.println("samDbInfo: " + mySql);
              ResultSet rst = dbObj.getData(mySql);
              if (rst == null || !rst.next())
              {
                   errMsg = "未找到部门代号: " + deptCode;
                   Debug.println("samDbInfo: " + errMsg);
                   return null;
              }

              return rst.getString("dept_name");
         }
         catch (Exception e)
         {
              errMsg = this.getClass().getName()+ ".getDeptName(): " + e;
              return null;
         }
    }

    /**
     * 根据人员代号获取部门名称
     * @param userUniqueNo: 要查询的人员代号
     * @return 成功返回该部门的名称，否则返回null。
     * @侯曦洋
     * @2001年12月25日
     */
    public String getDeptNameOfUserUniqueNo(String userUniqueNo, String companyCode)
    {
         try
         {
              errMsg = "";
              String mySql = " select a.dept_name from sam_dept a, epd_address_book b where " +
                             " a.company_code = '" + companyCode.trim() + "'" +
                             " and b.user_unique_no = '" + userUniqueNo.trim() + "'"  +
                             " and a.company_code = b.company_code " +
                             " and a.dept_code = b.dept_code";

              Debug.println("samDbInfo: " + mySql);
              ResultSet rst = dbObj.getData(mySql);
              if (rst == null || !rst.next())
              {
                   errMsg = "未找到人员代号: " + userUniqueNo;
                   Debug.println("samDbInfo: " + errMsg);
                   return null;
              }

              return rst.getString("dept_name");
         }
         catch (Exception e)
         {
              errMsg = this.getClass().getName()+ ".getDeptNameOfUserUniqueNo(): " + e;
              return null;
         }
    }

  /**
   * 获取职位名称
   * @param companyCode: 公司代号
   * @param positionCode: 职位代码
   * @return 成功识返回与positionCode相对应的职位名称，否则返回null
   */
  public String getPositionName(String companyCode,String positionCode)  {
    errMsg = "";
  try {
    ResultSet rst;
    String mySql = "select  position_name from sam_position where company_code='" + companyCode + "'";
    mySql += " and position_code='" + positionCode + "'";
    rst = dbObj.getData(mySql);
    if (rst.next())
      return rst.getString("position_name");
    else  {
      errMsg = "公司" + companyCode + "不存在的职位代号: " + positionCode;
      throw new Exception(errMsg);
    }
  }catch (Exception e)  {
    e.printStackTrace();
    errMsg = "SamDbInfo.getPositionName: " + e;
    return null;
  }
  }

  /**
   * 返回指定部门的定义
   * @param companyCode 部门所属的公司代号
   * @param deptCode  部门代号
   * @return  成功时返回部门定义对象，否则返回null
   */
  public DeptDef getDeptDef(String companyCode, String deptCode)  {
    errMsg = "";
    Debug.println("获取部门定义...");
  try {
    String mySql = "select * from sam_dept where company_code='" + companyCode + "'";
    mySql += " and dept_code='" + deptCode + "'";
    Debug.println("SamDbInfo: " + mySql);
    ResultSet rst = dbObj.getData(mySql);
    if (rst.next())  {
      DeptDef dd = new DeptDef();
      dd.companyCode = companyCode;
      dd.Code = rst.getString("dept_code");
      dd.Name = rst.getString("dept_name");
      dd.parentCode = rst.getString("dept_code_father");
      dd.masterPosCode = rst.getString("master_position_code");

      return dd;
//System.out.println(dd.companyCode);
    }
    errMsg = "SamDbInfo.getDept: 未能找到部门代号--" + deptCode;
    System.out.println("errMsg");
    return null;
  }catch (Exception e)  {
    e.printStackTrace();
    errMsg = "" + e;
    return null;
  }
  }

  /**
   * 获得币种名称
   * @param currencyCode    币种定义代码
   * @param companyCode     公司代码
   * @return  成功时返回名称，否则返回null
   */
  public String getCurrency(String currencyCode,String companyCode)
  {
    String sql="";
    sql="select currency_name from sam_currency where company_code='"+companyCode+"'";
    sql+=" and currency_code='"+currencyCode+"'";
    String currency="";
    try{
      ResultSet rs=dbObj.getData(sql);
      if(rs.next())
      {
        currency=rs.getString(1);
      }
      return currency;
    }
    catch(Exception e)
    {
      System.out.println("类com.huiton.cerp.sam.samDbInfo的getCurrency方法出错"+e);
      return null;
    }
  }

}

