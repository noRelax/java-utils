/**
 * Title:        ͨ���������
 * Description:  com.huiton.functions.DataCheck	�������������
 * Copyright:    Copyright (c) 2001
 * Company:      huiton
 * @author �⽣
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

            //�γɰ��ؼ��ֵ��������
            String strCri = "";
            for(int i=0;pkRS.next();i++)
                strCri += " AND " + pkRS.getString(4) + " = '" + primaryKey[i] + "' ";

            strCri = strCri.substring(5, strCri.length());

            //��ȡ�������ļ�¼����
            com.huiton.pub.dbx.LookField lf = new LookField();
            int recNum = Integer.parseInt(lf.dCount("*", table, strCri));

            return recNum<=0;
        }
        catch(SQLException e) {
          errMsg = this.getClass().getName() + ".checkPK: " + e;
          return false;
        }
     }

  //��ȡ������Ϣ
  public String getErrMsg() {
    return errMsg;
  }
}
