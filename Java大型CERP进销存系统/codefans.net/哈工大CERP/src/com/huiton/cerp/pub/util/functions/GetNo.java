package com.huiton.cerp.pub.util.functions;

import java.sql.*;
import com.huiton.pub.dbx.*;
import com.huiton.mainframe.util.tracer.Debug;

abstract class GetNo
{
	abstract int getNo(String company_code,String no_type);
	int m_getNo(String company_code,String no_type)
	{
		try
		{
			String strSQL = null;
		    ResultSet rs = null;

            JdbOp op = new JdbOp("","sam");
            strSQL = "select no_value from sam_no "+
				" where company_code='"+company_code+"' and no_type='"+no_type+"'";

            Debug.println("strSQL=" + strSQL);

            rs = op.getData(strSQL);
			if (rs==null || !rs.next())
			{
				strSQL = "insert into sam_no(company_code,no_type,no_value) values("+
					"'"+company_code+"','"+no_type+"',1)";

                Debug.println("strSQL=" + strSQL);

                op.simpleUpdate(strSQL);
				return 1;

            }else // exists,update it
			{
				strSQL = "update sam_no set no_value=no_value+1 "+
					" where company_code='"+company_code+"' and no_type='"+no_type+"'";

                Debug.println("strSQL=" + strSQL);

                op.simpleUpdate(strSQL);
			}

			// get the new one

			strSQL = "select no_value from sam_no "+
				" where company_code='"+company_code+"' and no_type='"+no_type+"'";

            Debug.println("strSQL=" + strSQL);

            rs = op.getData(strSQL);
			if (rs != null && rs.next())
			{
				return  rs.getInt(1);
			}else
            {
                return  -1;
            }
		}catch(Exception e)
		{
			return -1;
		}
	}

}