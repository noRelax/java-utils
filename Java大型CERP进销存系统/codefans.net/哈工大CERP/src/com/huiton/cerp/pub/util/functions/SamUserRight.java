package com.huiton.cerp.pub.util.functions;

/**
 * Title:        �û�Ȩ��
 * Description:  �û�Ȩ��
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      BRITC
 * @author
 * @version 1.0
 */

import java.util.*;
import java.sql.*;
import javax.servlet.http.*;
import com.huiton.mainframe.util.*;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.mainframe.control.web.ModelManager;
import com.huiton.mainframe.control.web.CustomerWebImpl;
import com.huiton.pub.dbx.*;
import com.huiton.cerp.pub.util.SubsystemKeys;

public class SamUserRight
{
  private static int times = 3;
  private PageQuery pageQuery = null;
  private ResultSet rs = null;
  private String sql = null;

  private String companyCode = null;
  private String userUniqueNo = null;
  private String progFlag = null;
  private String menuFlag = null;

  /**������*/
  public SamUserRight(String company_code,String user_unique_no,
    String prog_flag,String menu_flag)
  {
    companyCode = Show.getString(company_code);
    userUniqueNo = Show.getString(user_unique_no);
    progFlag = Show.getString(prog_flag);
    menuFlag = Show.getString(menu_flag);
  }

  /**������*/
  public SamUserRight(String company_code,String user_unique_no)
  {
    companyCode = Show.getString(company_code);
    userUniqueNo = Show.getString(user_unique_no);

    try
    {
      if (created())
      {
        sql = "select prog_flag,menu_flag from sam_user_info "
          + " where company_code='" + companyCode
          + "' and user_unique_no='" + userUniqueNo + "'";

        rs = pageQuery.getData(sql);

        if (rs!=null&&rs.next())
        {
          progFlag = Show.getString(rs.getString(1));
          menuFlag = Show.getString(rs.getString(2));
        }
        else
        {
          progFlag = "Y";
          menuFlag = "Y";
        }
      }
      else
      {
        progFlag = "Y";
        menuFlag = "Y";
      }
    }
    catch(Exception e)
    {
      progFlag = "Y";
      menuFlag = "Y";
    }
  }

  // create pageQuery if not initialized
  private boolean created()
  {
    try
    {
      for (int i=0; i<times && pageQuery==null ;i++)
      {
        pageQuery = new PageQuery("","sam");
        return true ;
      }
      return true ;
    }
    catch (Exception e)
    {
      return false ;
    }
  }

  //�����û�Ȩ�޺Ͳ˵�
  public void update()
  {
    if (progFlag.equals("N"))
    {
      if (updateProg())
        updateProgFlag();
    }
    if (menuFlag.equals("N"))
    {
      if (updateMenu())
        updateMenuFlag();
    }
  }

  //ǿ�Ƹ����û�Ȩ�޺Ͳ˵�
  public void forceUpdate()
  {
    if (updateProg())
      updateProgFlag();

    if (updateMenu())
      updateMenuFlag();
  }

  //����Ȩ��
  private boolean updateProg()
  {
    if (!created())
      return false ;

    try
    {
      //��վɼ�¼
      String[] delTables = {
        "sam_user_role1_tmp",
        "sam_user_role_tmp",
        "sam_user_prog_tmp"};

      for(int ii=0;ii<delTables.length;ii++)
      {
        sql = "delete from " + delTables[ii]
          + " where company_code='" + companyCode
          + "' and user_unique_no='" + userUniqueNo + "' " ;

        Debug.println("sql="+sql);
        pageQuery.simpleUpdate(sql);
      }

      //1 ��ȡ�û����н�ɫ���� sam_user_role_tmp
      sql ="insert into sam_user_role1_tmp (company_code,user_unique_no,"
        + " role_code) select '" + companyCode + "','" + userUniqueNo
        + "',role_code from sam_user_role "
        + " where company_code='" + companyCode + "' and user_unique_no='"
        + userUniqueNo + "' "
        + " union "
        + " select '" + companyCode + "','" + userUniqueNo + "',r.role_code "
        + " from  sam_user_position p,sam_position_role r "
        + " where p.company_code=r.company_code and "
        + " p.position_code=r.position_code and "
        + " p.company_code='" + companyCode
        + "' and p.user_unique_no='" + userUniqueNo + "' ";

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //ȥ�ظ���¼
      sql = "insert into sam_user_role_tmp (company_code,user_unique_no,"
        + "role_code) select distinct company_code,user_unique_no,role_code "
        + " from sam_user_role1_tmp "
        + " where company_code='" + companyCode + "' and user_unique_no='"
        + userUniqueNo + "' " ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //ɾ�� sam_user_role1_tmp ����ʱ��¼
      sql = "delete from sam_user_role1_tmp "
        + " where company_code='" + companyCode + "' and user_unique_no='"
        + userUniqueNo + "' " ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //2���û��������ɫ��������û�����
      sql = " insert into sam_user_prog_tmp(company_code,user_unique_no,"
        + "sys_code,prog_code) select '" + companyCode
        + "','" + userUniqueNo + "',p.sys_code,p.prog_code "
        + " from sam_user_role_tmp r, sam_role_prog p "
        + " where r.company_code=p.company_code and "
        + " r.role_code=p.role_code and "
        + " r.company_code='" + companyCode + "' and "
        + " r.user_unique_no='" + userUniqueNo + "' "
        + " union "
        + " select '" + companyCode
        + "','" + userUniqueNo + "',sys_code,prog_code "
        + " from sam_user_prog "
        + " where company_code='" + companyCode
        + "' and user_unique_no='" + userUniqueNo + "'" ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //ɾ�� sam_user_role_tmp ����ʱ��¼
      sql = "delete from sam_user_role_tmp "
        + " where company_code='" + companyCode + "' and user_unique_no='"
        + userUniqueNo + "' " ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //ɾ����Ȩ��
      sql = "delete from sam_user_prog_right "
        + " where company_code='" + companyCode
        + "' and user_unique_no='" + userUniqueNo + "'" ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //������Ȩ��
      sql = "insert into sam_user_prog_right(company_code,user_unique_no,"
        + "sys_code,prog_code) select distinct company_code,user_unique_no,"
        + "sys_code,prog_code from sam_user_prog_tmp "
        + " where company_code='" + companyCode + "' and user_unique_no='"
        + userUniqueNo + "' " ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //ɾ��sam_user_prog_tmp��ʱ��¼
      sql = "delete from sam_user_prog_tmp "
        + " where company_code='" + companyCode
        + "' and user_unique_no='" + userUniqueNo + "'" ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);
    }
    catch(Exception e)
    {
      e.printStackTrace();
      return false ;
    }
    return true ;
  }

  //���²˵�
  private boolean updateMenu()
  {
    if (!created())
      return false ;

    try
    {
      //��վɼ�¼
      String[] delTables = {
        "sam_user_menu_tmp",
        "sam_user_prog_right_tmp"};

      for(int ii=0;ii<delTables.length;ii++)
      {
        sql = "delete from " + delTables[ii]
          + " where company_code='" + companyCode
          + "' and user_unique_no='" + userUniqueNo + "' " ;

        Debug.println("sql="+sql);
        pageQuery.simpleUpdate(sql);
      }

      //���ɺ����ӵ���ʱȨ�ޱ� sam_user_prog_right_tmp
      sql = "insert into sam_user_prog_right_tmp(company_code,user_unique_no,"
        + "sys_code,prog_code) select company_code,user_unique_no,sys_code,"
        + "prog_code from sam_user_prog_right "
        + " where company_code='" + companyCode
        + "' and user_unique_no='" + userUniqueNo + "' "
        + " union " //�ӳ���
        + " select p.company_code,p.user_unique_no,s.sys_code,s.prog_code "
        + " from sam_user_prog_right p,scg_program s "
        + " where p.company_code=s.company_code and "
        + " p.company_code='" + companyCode
        + "' and p.user_unique_no='" + userUniqueNo
        + "' and p.sys_code=s.sys_code "
        + " and p.prog_code=s.prog_code_father "
        + " union " //������Ȩ�ĳ���
        + " select company_code,'" + userUniqueNo + "',sys_code,prog_code "
        + " from scg_program "
        + " where check_right='N' and company_code='" + companyCode + "'" ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //����Ҷ��
      sql = " insert into sam_user_menu_tmp(company_code,user_unique_no,"
        + "sys_code,menu_code,menu_level,menu_code_father)"
        + " select m.company_code,'" + userUniqueNo + "',m.sys_code,m.menu_code,"
        + " m.menu_level,m.menu_code_father "
        + " from scg_main_menu m,sam_user_prog_right_tmp p"
        + " where p.company_code='" + companyCode
        + "' and p.user_unique_no='" + userUniqueNo
        + "' and m.leaf_flag='Y' "
        + " and m.company_code=p.company_code "
        + " and m.prog_sys_code=p.sys_code "
        + " and m.prog_code=p.prog_code";

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //ɾ�������ӵ���ʱȨ�ޱ� sam_user_prog_right_tmp
      sql = "delete from sam_user_prog_right_tmp "
          + " where company_code='" + companyCode
          + "' and user_unique_no='" + userUniqueNo + "' " ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //�γ���
      sql = "select max(menu_level) from sam_user_menu_tmp "
        + " where company_code='" + companyCode
        + "' and user_unique_no='" + userUniqueNo + "'" ;

      Debug.println("sql="+sql);

      int menuLevel = 1;
      rs = pageQuery.getData(sql);
      if (rs!=null&&rs.next())
        menuLevel = rs.getInt(1);

      while(menuLevel>1)
      {
        sql = " insert into sam_user_menu_tmp(company_code,user_unique_no,"
          + "sys_code,menu_code,menu_level,menu_code_father)"
          + " select m.company_code,'" + userUniqueNo + "',m.sys_code,"
          + "m.menu_code,m.menu_level,m.menu_code_father "
          + " from scg_main_menu m,sam_user_menu_tmp t"
          + " where t.company_code='" + companyCode
          + "' and t.user_unique_no='" + userUniqueNo
          + "' and m.company_code=t.company_code"
          + " and t.menu_level=" + menuLevel
          + " and t.sys_code=m.sys_code"
          + " and t.menu_code_father=m.menu_code";

        Debug.println("sql="+sql);
        pageQuery.simpleUpdate(sql);
        menuLevel-- ;
      }

      //ɾ��sam_user_menu�еľɼ�¼
      sql = "delete from sam_user_menu "
        + " where company_code='" + companyCode
        + "' and user_unique_no='" + userUniqueNo + "'" ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //sam_user_menu�в����¼�¼
      sql = "insert into sam_user_menu(company_code,user_unique_no,sys_code,"
        + "menu_code) select distinct company_code,user_unique_no,sys_code,"
        + "menu_code from sam_user_menu_tmp"
        + " where company_code='" + companyCode
        + "' and user_unique_no='" + userUniqueNo + "'" ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

      //ɾ��sam_user_menu_tmp�е���ʱ��¼
      sql = "delete from sam_user_menu_tmp "
        + " where company_code='" + companyCode
        + "' and user_unique_no='" + userUniqueNo + "'" ;

      Debug.println("sql="+sql);
      pageQuery.simpleUpdate(sql);

    }
    catch(Exception e)
    {
      e.printStackTrace();
      return false ;
    }
    return true ;
  }

  //����Ȩ�ޱ��
  private void updateProgFlag()
  {
    if (!created())
      return ;

    try
    {
      sql = "update sam_user_info set prog_flag='Y' "
        + " where company_code='" + companyCode
        + "' and user_unique_no='" + userUniqueNo + "'" ;

      pageQuery.simpleUpdate(sql);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  //���²˵����
  private void updateMenuFlag()
  {
    if (!created())
      return ;

    try
    {
      sql = "update sam_user_info set menu_flag='Y' "
      + " where company_code='" + companyCode
      + "' and user_unique_no='" + userUniqueNo + "'" ;

      pageQuery.simpleUpdate(sql);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}