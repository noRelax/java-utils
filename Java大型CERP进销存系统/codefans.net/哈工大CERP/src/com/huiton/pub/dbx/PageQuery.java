// FrontEnd Plus GUI for JAD
// DeCompiled : PageQuery.class

package com.huiton.pub.dbx;
//import com.huiton.connectionpool.ConnectionPool;
import java.io.PrintStream;
import java.sql.*;
import java.util.Vector;
 
//import com.huiton.pub.util.*;

//import com.huiton.mainframe.util.tracer.Debug;

public class PageQuery extends JdbOp
{

    //protected Connection m_conn;
    //protected Statement m_statement;
    public int pageCount=0;
    //protected int m_scrollType;
   // protected int m_updatable;
    private  ResultSet rs=null;
    public int rowCount=0;
    public int locat=0;
    public int pageSize=0;

    public PageQuery() throws Exception
    {
    }

    /**
         * Description: 构造函数。使用sessionCode获取Cerp数据库连接
         * @param sessionCode：系统登陆时创建的会话唯一标识
         * @param sysCode: 要联接的系统代码。如果为空，返回"sam"库
         */
    public PageQuery(String sessionCode,String sysCode) throws Exception{
       if (!super.createConnect(sessionCode,sysCode))
            throw (new Exception(m_errMsg));
    }

    /**
     *
     * @param query_sql
     * @param table_name
     * @param rule_sql
     * @param pagesize
     * @return
     */
    public ResultSet getData(String query_sql, String table_name, String rule_sql, int pagesize)
    {
        try
        {
            this.pageSize=pagesize;
            if(rule_sql.length() != 0)
                query_sql = String.valueOf(String.valueOf((new StringBuffer("select ")).append(query_sql).append(" from ").append(table_name).append(" where ").append(rule_sql)));
            else
                query_sql = String.valueOf(String.valueOf((new StringBuffer("select ")).append(query_sql).append(" from ").append(table_name)));
            System.out.println("in querydata:the sql sentence is:"+query_sql);
            rs = m_statement.executeQuery(query_sql);
            //System.out.println(rs.getRow());
          //  if(!rs.isLast())
            if(rs!=null&&rs.next())
            {
                rs.last();
                rowCount = rs.getRow();
                pageCount = rowCount % pagesize != 0 ? rowCount / pagesize + 1 : rowCount / pagesize;
           // ResultSet resultset = rs;
                rs.beforeFirst();
            }
            else
            {
              rowCount=0;
              pageCount=0;
            }
            System.out.println("the size is:"+rowCount);
            return rs;
            }
        catch(Exception e)
        {
            System.out.println("com.huiton.pub.dbx.PageQuery类出错原因:"+e);
            return null;
        }
    }


    public String spellSql(String field_name[], String field_value[])
    {
        int len = field_name.length;
        String sql = "";
        for(int i = 0; i < len; i++)
        {
            if(field_value[i].length() == 0)
                continue ;
            if(sql.length() == 0)
                sql = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(field_name[i])))).append(" like '%").append(field_value[i]).append("%'")));
            else
                sql = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(sql)))).append(" and ").append(field_name[i]).append(" like '%").append(field_value[i]).append("%'")));
        }

        return sql;
    }

    public String fullSql(String fieldName[], String fieldValue[])
    {
        int len = fieldName.length;
        String sql = "";
        for(int i = 0; i < len; i++)
        {
            if(fieldValue[i].length() == 0)
                continue;
            if(sql.length() == 0)
                sql = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(fieldName[i])))).append("='").append(fieldValue[i]).append("'")));
            else
                sql = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(sql)))).append(" and ").append(fieldName[i]).append("='").append(fieldValue[i]).append("'")));
        }

        return sql;
    }
/*
  public static void main(String args[])
    {
      try{
         PageQuery pa=new PageQuery("10794090975888","epd");
      ResultSet rs=pa.getData("*","epd_tax","company_code='11'",11);
      Vector vct=pa.dividePage(1,30);
      String temp[]=new String[4];
      for(int i=0;i<vct.size();i++)
      {
        temp=(String[])vct.elementAt(i);
        System.out.println(temp[3]);
      }
      }

      catch(Exception e)
      {System.out.println(e);}
    }
*/
    public String date_sql(String field_name[], String field_value[])
    {
        int len = field_name.length;
        String sql = "";

        for(int i = 0; i < len; i++)
        {
            if(i < len - 1 && field_value[i].length() != 0)
                if(sql.length() == 0)
                    sql = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(field_name[i])))).append("like'%").append(field_value[i]).append("%'")));
                else
                    sql = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(sql)))).append(" and ").append(field_name[i]).append("like'%").append(field_value[i]).append("%'")));
            if(i != len - 1 || field_value[i].length() == 0)
                continue;
            if(sql.length() == 0)
            {
                if(field_value[i].length() == 4)
                    sql = String.valueOf(String.valueOf((new StringBuffer("convert(char(4),")).append(field_name[i]).append(",111)='").append(field_value[i]).append("'")));
                if(field_value[i].length() == 7)
                    sql = String.valueOf(String.valueOf((new StringBuffer("convert(char(7),")).append(field_name[i]).append(",111)='").append(field_value[i]).append("'")));
                if(field_value[i].length() == 10)
                    sql = String.valueOf(String.valueOf((new StringBuffer("convert(char(10),")).append(field_name[i]).append(",111)='").append(field_value[i]).append("'")));
            }
            if(field_value[i].length() == 4)
                sql = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(sql)))).append(" and convert(char(4),").append(field_name[i]).append(",111)='").append(field_value[i]).append("'")));
            if(field_value[i].length() == 7)
                sql = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(sql)))).append(" and convert(char(7),").append(field_name[i]).append(",111)='").append(field_value[i]).append("'")));
            if(field_value[i].length() == 10)
                sql = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(sql)))).append(" and convert(char(10),").append(field_name[i]).append(",111)='").append(field_value[i]).append("'")));
        }

        return sql;
    }


    /**
     * 获得分页记录
     * @param locat1  定位的页面值
     * @param pagesize  页面所包含的行数（现在没有作用）
     * @return  返回获的页面向量集
     * @deprecated  应该采用dividePage(int locat1)方法替代本方法
     */
    public Vector dividePage(int locat1, int pagesize)
    {
      return dividePage(locat1);
    }

    /**
     * 获得分页记录
     * @param locat1  定位的页面值
     * @return  返回获的页面向量集
     */
    public Vector dividePage(int locat1)
    {
         Vector vct = new Vector();
        try
        {
          if (pageSize <= 0)      //返回所有记录
            return this.rst2Vector(rs,false);

            if (rowCount<=0)
              return vct;
            this.locat=locat1;
            ResultSetMetaData rsmd = rs.getMetaData();
            //rsmd.getColumnType();

            //rsmd.getColumnTypeName();

            int colcount = rsmd.getColumnCount();
            //for(int i=1;i<=colcount;i++)
             // System.out.println("rsmd"+i+":"+rsmd.getColumnTypeName(i) );
             // rsmd.getColumnTypeName();
            rs.absolute((locat - 1) * pageSize + 1);
            int j = 0;
            do
            {
                if(j >= pageSize)
                    break ;
                String row[] = new String[colcount];
                for(int i = 1; i <= colcount; i++)
                {
                    if(rsmd.getColumnTypeName(i).equalsIgnoreCase("float")||rsmd.getColumnTypeName(i).equalsIgnoreCase("number"))
                    {
                      row[i - 1] = rs.getString(i);
                      row[i - 1] = row[i - 1] == null ? "" : row[i - 1];
                //      row[i-1]=StrFormat.strFormat(row[i-1]);
                    }

                    else
                    {
                      row[i - 1] = rs.getString(i);
                      row[i - 1] = row[i - 1] == null ? "" : row[i - 1];
                    }
                }

                vct.addElement(row);
                if(!rs.next())
                    break ;
                j++;
            } while(true);
            return vct;
        }
        catch(Exception e)
        {
           // disConnect();
            System.out.println("com.huiton.pub.dbx.PageQuery:dividePage出错原因:"+e);
            return vct;
}
    }
    public ResultSet returnRs()
    {
      return this.rs;
    }
  public void setRs(ResultSet rs,int pagesize) {
    this.pageSize = pagesize;
    this.rs = rs;
//    Debug.println("setRs:" + this.rs);
    try{
      rs.beforeFirst();
      if(rs!=null&&rs.next())
       {
           rs.last();
           rowCount = rs.getRow();
           if (rowCount == 0)
             pageCount = 0;
           else if (pagesize <=0 )
             pageCount = 1;
           else
             pageCount = rowCount % pagesize != 0 ? rowCount / pagesize + 1 : rowCount / pagesize;
//           Debug.println("pagequery rowCount:" + rowCount);
           rs.beforeFirst();
           // ResultSet resultset = rs;
           System.out.println("PageQuery rowCount:" + rowCount + " pageCount:" + pageCount);
       }
       else
       {
           rowCount=0;
           pageCount=0;
//           Debug.println("pagequery empty resultset");
        }
        //return rs;
       }
        catch(Exception e)
        {
            System.out.println("com.huiton.pub.dbx.PageQuery类出错原因:"+e);
           // return null;
        }
  }

  public String[] getRstHeader() throws Exception
  {
    if (rs == null)
      throw new Exception("先用getData获得数据！");
    Vector vfd = getFieldsDef(rs);
    FieldDefn fd;
    String[] header = new String[vfd.size()];
    for (int i=0; i<vfd.size(); i++)
    {
      fd = (FieldDefn)vfd.elementAt(i);
      header[i] = fd.colName;
    }
    return header;
  }

}
