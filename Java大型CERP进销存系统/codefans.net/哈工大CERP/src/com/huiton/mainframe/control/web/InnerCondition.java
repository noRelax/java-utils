package com.huiton.mainframe.control.web;

/**
 * Title:        CERP����
 * Description:  ���ò�����ϣ��
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      BRITC
 * @author �Ű���
 * @version 1.0
 */

import java.util.*;

class InnerCondition
{
    private Hashtable ht = new Hashtable(); //��ϣ�����ü�ֵ��
    private static final String strAnd = "&" ; //��ֵ�Ե����ַ�
    private static final String strEqual = "=" ; //��ֵ�Եķָ��
    private static final String strNull = ""; //�մ�

    /**������
     * @param s ������
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

    /**@param ������
     * @return ����ֵ*/
    public String getParam(String name)
    {
        return (String) ht.get(name);
    }

    /**
     * @param s Ҫ�и���ִ�
     * @param sep �ָ
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