package com.huiton.cerp.sam;

/**
 * Title:        SAM一些有用的函数
 * Description:  SAM一些有用的函数
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      BRITC
 * @author 张爱军
 * @version 1.0
 */

import java.util.*;
import java.sql.*;
import com.huiton.mainframe.util.*;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.pub.dbx.*;
import com.huiton.cerp.pub.util.functions.*;

public class SamUtil
{
  private static int times = 3;
  private PageQuery pageQuery = null;
  private ResultSet rs = null;
  private String sql = null;

  /**构造器*/
  public SamUtil()
  {
    created();
  }

  //create pageQuery if not initialized
  //创建数据库操作对象
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

  //检查部门定义是否循环
  public boolean isDeptRecursive(String companyCode,String deptCode,
    String deptCodeFather)
  {

    if (companyCode==null || companyCode.length()<1)
      return true ;

    if (deptCode==null || deptCode.length()<1 ||
      deptCodeFather==null || deptCodeFather.length()<1)
      return false ;

    if (deptCode.equals(deptCodeFather))
      return true ;

    if (!created())
      return true ;

    try
    {
      sql = "select dept_code_father from sam_dept "
        + " where company_code='" + companyCode
        + "' and dept_code='" + deptCode + "'" ;

      rs = pageQuery.getData(sql);
      if (rs!=null&&rs.next())
      {
        String dept_code_father = Show.getString(rs.getString(1));
        if (dept_code_father.length()<1)
          return false ;

        if (dept_code_father.equals(deptCodeFather))
          return true ;

        return isDeptRecursive(companyCode,dept_code_father,deptCodeFather);
      }
      else
      {
        return true ;
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
      return true ;
    }
  }

}