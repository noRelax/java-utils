package com.huiton.cerp.pub.util.functions;

/**
 * Title:        字串替换函数
 * Description:  字串替换函数
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      BRITC
 * @author 张爱军
 * @version 1.0
 */

public class StringFunction
{
  public static String replace(String srcStr,String findStr,String replStr)
  {
    return repl(srcStr,findStr,replStr,1);
  }

  public static String replace(String srcStr,String findStr,String replStr,
    int times)
  {
    return repl(srcStr,findStr,replStr,times);
  }

  public static String replaceAll(String srcStr,String findStr,String replStr)
  {
    return repl(srcStr,findStr,replStr,-1);
  }

  private static String repl(String srcStr,String findStr,String replStr,
    int times)
  {
    if (times==0)
      return srcStr ;

    if (srcStr==null||srcStr.length()<1||findStr==null||findStr.length()<1
      ||replStr==null)
      return srcStr;

    int curTimes = 0; //替换次数
    int startPos = 0; //开始位置
    int nextPos = 0; //下一位置；
    int len = srcStr.length(); //串长
    StringBuffer result = new StringBuffer(); //

    if (times>0) //替换指定次数
    {
      while(curTimes<times && startPos<len)
      {
        nextPos = srcStr.indexOf(findStr,startPos);
        if (nextPos>=0)
        {
          result.append(srcStr.substring(startPos,nextPos));
          result.append(replStr);
          startPos = nextPos+findStr.length();
          curTimes++ ;
        }
        else
        {
          result.append(srcStr.substring(startPos));
          break ;
        }
      }

      if (curTimes>=times && startPos<len) //次数到
      {
        result.append(srcStr.substring(startPos));
      }
    }
    else //全部替换
    {
      while(startPos<len && (nextPos = srcStr.indexOf(findStr,startPos))>=0)
      {
          result.append(srcStr.substring(startPos,nextPos));
          result.append(replStr);
          startPos = nextPos+findStr.length();
      }

      if (startPos<len)
      {
        result.append(srcStr.substring(startPos));
      }
    }
    return new String(result);
  }

  public static String replace(String path,char old_char,String new_str)
  {
    if (path==null||path.length()<1||new_str==null)
      return path;

    String old_str = String.valueOf(old_char);
    if (new_str.equals(old_str))
      return path;

    if (new_str.length()==1)
      return path.replace(old_char,new_str.charAt(0));

    StringBuffer buf = new StringBuffer(100);
    int len = path.length();
    for (int i=0;i<len;i++)
    {
      char c = path.charAt(i);
      if (c==old_char)
        buf.append(new_str);
      else
        buf.append(c);
    }
    return new String(buf);
  }
}