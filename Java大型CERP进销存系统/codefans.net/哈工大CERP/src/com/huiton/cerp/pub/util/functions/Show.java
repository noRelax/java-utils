package com.huiton.cerp.pub.util.functions;

/**
 * Title:        CERP程序
 * Description:  功能描述
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      BRITC
 * @author 张爱军
 * @version 1.0
 */

public class Show
{
    public static String getSizedString(String s,int maxSize)
    {
      s = getString(s);
      if (s.length()<1 || maxSize<1)
        return s;

      try
      {
        byte[] b = s.getBytes("GBK");
        if (b.length<=maxSize)
            return s ;

        String ret = new String(b,0,maxSize,"GBK");
        if (s.startsWith(ret))
          return ret ;

        return s.substring(0,ret.length()-1);
      }
      catch(Exception e)
      {
        return s ;
      }
    }

    public static String getSizedString(String s)
    {
        return getString(s);
    }

    public static String getString(String s)
    {
        if (s==null)
            return "";

        return s.trim();
    }
   
}