package com.huiton.cerp.pub.util;

/**
 * Title:        CERP���Կ��
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author �⽣
 * @version 1.0
 */
import java.util.*;
import com.huiton.mainframe.util.tracer.Debug;

public class DebugUtil {
    // ��ӡ HashMap �еļ�¼
    public static void showHashMapRecord(HashMap recordMap) {
        Iterator it = recordMap.keySet().iterator();
        String strKey, strValue;
        while(it.hasNext()) {
            strKey = (String)it.next();
            strValue = (String)recordMap.get(strKey);
            Debug.println("DraftDocuBean.releaseDocu: keys = " + strKey +
                                "     value = " + strValue);
        }
    }
}