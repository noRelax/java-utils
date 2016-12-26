package com.huiton.pub.dbx;

/**
 * Title:        epd
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      huiton
 * @author zhaoyin
 * @version 1.0
 */

public class SpellSql {

  public SpellSql() {
  }
 /*
  public static void main(String[] args) {
    SpellSql spellSql1 = new SpellSql();
     String c[]={"=","*%","%*","%%"};
     String fieldName[]={"item_code","item_name","m_p_flag","create_time"};
     String fieldValue[]={"zhyin20","zhyin20","m",""};
     String q=spellSql1.spellSql(c,fieldName,fieldValue,"zhao");
     System.out.println(q);

    }
*/
  public String spellSql(String condi[],String fieldName[],String fieldValue[],String companyCode)
  {
      String querySql="";
      String tempSql="";
      for(int i=0;i<condi.length;i++)
      {
        tempSql="";
        if(condi[i].length()>0&&fieldValue[i].length()>0)
	{
          if(condi[i].equals(">")||condi[i].equals("<")||condi[i].equals(">=")||condi[i].equals("<=")||condi[i].equals("="))
            tempSql=fieldName[i]+condi[i]+"'"+fieldValue[i]+"'";
          else if(condi[i].equals("%*"))
	    tempSql=fieldName[i]+" like '%"+fieldValue[i]+"'";
          else if(condi[i].equals("*%"))
	    tempSql=fieldName[i]+" like '"+fieldValue[i]+"%'";
	  else
	    tempSql=fieldName[i]+" like '%"+fieldValue[i]+"%'";
        }
       if(tempSql.length()>0)
       {
         if(querySql.length()>0)
           querySql+=" and "+tempSql;
         else
           querySql=tempSql;
       }
     }
      if(companyCode.length()!=0)
      {
       if(querySql.length()>0)
        querySql+=" and company_code='"+companyCode+"'";
        else
          querySql="company_code='"+companyCode+"'";
      }
      return querySql;
  }
}