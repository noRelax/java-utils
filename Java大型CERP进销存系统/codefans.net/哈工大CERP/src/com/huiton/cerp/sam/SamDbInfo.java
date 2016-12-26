package com.huiton.cerp.sam;

import java.util.Vector;
import java.sql.*;

import com.huiton.pub.dbx.*;
import com.huiton.pub.lan_tools.LanTools;
import com.huiton.mainframe.util.tracer.Debug;


/**
 * Title: ϵͳ�������Ϣ
 * Description:  �����ṩ�˶�ȡsam�����Ϣ�ӿڡ���samģ��ĳ���Ӧ�ӱ����ȡsam�������
 * Copyright:    Copyright (c) 2001
 * Company: ������Ϣ�������޹�˾
 * @author ����
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
   * Tile: ��ȡϵͳִ�еĴ�����Ϣ
   */
  public String getErrMsg() {
    return errMsg;
  }

  /**
   * Title: ��ȡ�û�������
   * @param userUniCode: �û�����
   * @return �ɹ�ʱ�����û����ƣ����򷵻�null
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
    return "������";
  }catch (Exception e)  {
    errMsg = this.getClass().getName() + ".getUserName(): " + e;
    return null;
  }
  }

  /**
   * Title: ��ȡ�����ļ���
   * @param progCode: �����ļ�����
   * @return �ɹ�ʱ���س����ļ���(����Ϊ��)�����򷵻�null
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
   * ��ȡ���˵�����
   * @param menuList: �˵�����
   * @param pCode: ���˵���
   * @param curItem: ��ǰ���������
   * @return �ɹ�ʱ�������������򷵻�-1
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
   * �γɲ˵���������ѭ���ݹ��������ɲ˵����Ĺ���
   * @param userId: �û�����
   * @param companyCode: ��˾��
   * @return �ɹ�ʱ���ػ�õĲ˵��������������򷵻�null
   */
  public Vector getMenu(String userId,String companyCode) {
    errMsg = "";
  try {
    int count=0;    //�ŵ���ļ���
    Vector menuList = new Vector();
    ResultSet rst;
    String mySql;

    //���������ȡ�����
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

    //�������ӱ�־
    for (int i=1; i<menuList.size(); i++ )  {
      MenuDef md = (MenuDef)menuList.elementAt(i);
      getParentIndex(menuList,md.parentCode,i);    //��ȡ���˵�����
    }
   return menuList;
}catch (Exception e) {
  errMsg = this.getClass().getName() + ".getMenu(): " + e;
  return null;
}
  }

  /**
   * ��ȡ�û����ղؼ�����
   * @param uerId���û�����
   * @param companyCode: �ղؼ������Ĺ�˾����
   * @return �ɹ�ʱ���ذ����ղؼж����������������ÿһ��Ԫ�ض���һ��FavorDef�ࡣʧ��ʱ����null��
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
      //����ղ���
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
   * Title: ��ȡ���Ŷ�����Ϣ��
   * Description: ��������ȡָ����˾�Ĳ��Ŷ�����Ϣ��
   * @param companyCode: ��˾����
   * @return ���óɹ�ʱ����һ��������������ÿһ���Ϊһ��DeptDefʵ��
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
   * ��ȡ�Ự����
   * @param sessionId: Ҫ��ȡ�ĻỰ����
   * @return �ɹ�ʱ����SessionDef���󣬷��򷵻�null
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
      errMsg = "û��" + sessionId + "�ĵ�¼��Ϣ��";
    return sd;
}catch (Exception e)  {
    errMsg = this.getClass().getName() + ".getSession(): " + e;
    return null;
}
  }

  /**
   * ��ȡ��˾����
   * @param companyCode: Ҫ��ȡ���ԵĹ�˾����
   * @return �ɹ�ʱ����ComDef���󣬷��򷵻�null
   */
  public ComDef getComAtt(String companyCode) {
    errMsg = "";
try {
    ResultSet rst = dbObj.getData("select * from sam_company where company_code=" + LanTools.toSqlString(companyCode));
    if (!rst.next())
      throw new Exception("��Ч�Ĺ�˾����: " + companyCode);
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
     * ���ݲ��Ŵ��Ż�ȡ��������
     * @param deptCode: Ҫ��ѯ�Ĳ��Ŵ���
     * @return �ɹ����ظò��ŵ����ƣ����򷵻�null��
     * @������
     * @2001��12��25��
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
                   errMsg = "δ�ҵ����Ŵ���: " + deptCode;
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
     * ������Ա���Ż�ȡ��������
     * @param userUniqueNo: Ҫ��ѯ����Ա����
     * @return �ɹ����ظò��ŵ����ƣ����򷵻�null��
     * @������
     * @2001��12��25��
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
                   errMsg = "δ�ҵ���Ա����: " + userUniqueNo;
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
   * ��ȡְλ����
   * @param companyCode: ��˾����
   * @param positionCode: ְλ����
   * @return �ɹ�ʶ������positionCode���Ӧ��ְλ���ƣ����򷵻�null
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
      errMsg = "��˾" + companyCode + "�����ڵ�ְλ����: " + positionCode;
      throw new Exception(errMsg);
    }
  }catch (Exception e)  {
    e.printStackTrace();
    errMsg = "SamDbInfo.getPositionName: " + e;
    return null;
  }
  }

  /**
   * ����ָ�����ŵĶ���
   * @param companyCode ���������Ĺ�˾����
   * @param deptCode  ���Ŵ���
   * @return  �ɹ�ʱ���ز��Ŷ�����󣬷��򷵻�null
   */
  public DeptDef getDeptDef(String companyCode, String deptCode)  {
    errMsg = "";
    Debug.println("��ȡ���Ŷ���...");
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
    errMsg = "SamDbInfo.getDept: δ���ҵ����Ŵ���--" + deptCode;
    System.out.println("errMsg");
    return null;
  }catch (Exception e)  {
    e.printStackTrace();
    errMsg = "" + e;
    return null;
  }
  }

  /**
   * ��ñ�������
   * @param currencyCode    ���ֶ������
   * @param companyCode     ��˾����
   * @return  �ɹ�ʱ�������ƣ����򷵻�null
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
      System.out.println("��com.huiton.cerp.sam.samDbInfo��getCurrency��������"+e);
      return null;
    }
  }

}

