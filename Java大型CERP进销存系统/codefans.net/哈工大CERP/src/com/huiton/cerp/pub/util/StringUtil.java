package com.huiton.cerp.pub.util;

/**
 * Title:        CERP���Կ��
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author �⽣
 * @version 1.0
 */

public class StringUtil {
    /**
     * ֻ�滻��һ���ҵ����ַ��������Ҫȫ���滻����дһ������
     */
    public static String replace(String mainString, String srcString, String rplString)
        throws Exception
    {
        String result = "";
        int index = mainString.indexOf(srcString);
        if(index==-1)
            throw new Exception("�ַ�û���ҵ���");
        result = mainString.substring(0, index);
        result += rplString;
        result += mainString.substring(mainString.indexOf(srcString) + srcString.length(), mainString.length());

        return result;
    }

    public static String str_replace(String s, char c, String s1)
    {
        if(s1.length() < 1)
            return s;
        if(s1.length() == 1)
            return s.replace(c, s1.charAt(0));
        String s2 = String.valueOf(c);
        if(s2.equals(s1))
            return s;
        StringBuffer stringbuffer = new StringBuffer(100);
        int i = s.length();
        for(int j = 0; j < i; j++)
        {
            char c1 = s.charAt(j);
            if(c1 == c)
                stringbuffer.append(s1);
            else
                stringbuffer.append(c1);
        }

        return new String(stringbuffer);
    }
}