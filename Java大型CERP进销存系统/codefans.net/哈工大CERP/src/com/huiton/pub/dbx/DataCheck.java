/**
 * Title:        通用组件开发
 * Description:  com.huiton.functions.DataCheck	检查数据完整性
 * Copyright:    Copyright (c) 2001
 * Company:      huiton
 * @author 吴剑
 * @version 1.0
 */
package com.huiton.pub.dbx;

import java.sql.*;
import com.huiton.pub.dbx.*;

public class DataCheck
{String m_sessionCode = "",m_sysCode = "";
 JdbObj store;
 String errMsg = "";

  public DataCheck(String sessionCode, String sysCode)  {
    m_sessionCode = sessionCode;
    m_sysCode = sysCode;
  try {
    store = new JdbObj(m_sessionCode, m_sysCode);
  }catch (Exception e)  {
     errMsg = this.getClass().getName() + ": " + e;
  }
  }
     public boolean checkPK(String table, String[] primaryKey) {
        try {
            DatabaseMetaData dbmd = store.conn.getMetaData();
            ResultSet pkRS = dbmd.getPrimaryKeys(null, null, table);

            //形成按关键字的条件与句
            String strCri = "";
            for(int i=0;pkRS.next();i++)
                strCri += " AND " + pkRS.getString(4) + " = '" + primaryKey[i] + "' ";

            strCri = strCri.substring(5, strCri.length());

            //获取该条件的记录个数
            com.huiton.pub.dbx.LookField lf = new LookField();
            int recNum = Integer.parseInt(lf.dCount("*", table, strCri));

            return recNum<=0;
        }
        catch(SQLException e) {
          errMsg = this.getClass().getName() + ".checkPK: " + e;
          return false;
        }
     }

  //获取错误信息
  public String getErrMsg() {
    return errMsg;
  }
}
