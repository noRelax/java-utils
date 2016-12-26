package com.huiton.mainframe.control.web;

/**
 * Title:        CERP程序
 * Description:  内置参数哈希表
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      BRITC
 * @author 张爱军
 * @version 1.0
 */

import java.util.*;

class InnerCondition
{
    private Hashtable ht = new Hashtable(); //哈希表，放置键值对
    private static final String strAnd = "&" ; //键值对的连字符
    private static final String strEqual = "=" ; //键值对的分割符
    private static final String strNull = ""; //空串

    /**构造器
     * @param s 条件串
     * */
    public InnerCondition(String s)
    {
         s = (s==null ? "" : s.trim());
         if (s.length()<1)
            return ;

         String[] keyAndValue = split(s,strAnd) ;
         for(int i=0;i<keyAndValue.length;i++)
         {
            String[] tmp = split(keyAndValue[i],strEqual);
            if (tmp.length>1)
            {
                ht.put(tmp[0],tmp[1]);
            }
            else
            {
                ht.put(tmp[0],strNull);
            }
         }
    }

    public InnerCondition()
    {

    }

    /**@param 参数名
     * @return 参数值*/
    public String getParam(String name)
    {
        return (String) ht.get(name);
    }

    /**
     * @param s 要切割的字串
     * @param sep 分割串
     * return String[]
     * */
    private String[] split(String s,String sep)
    {
        StringTokenizer st = new StringTokenizer(s,sep);
        int sum = st.countTokens();
        String [] sArray = new String[sum];
        int i = 0;
        while(st.hasMoreTokens())
        {
            String tmp = (String) st.nextToken();
            sArray[i++] = tmp;
        }
        return sArray ;
    }
}