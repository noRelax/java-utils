package com.huiton.cerp.pub.util;

/**
 * <p>Title: CERP���Կ��</p>
 * <p>Description: ���Թ���</p>
 * <p>Copyright: Copyright (c) 2000</p>
 * <p>Company: BRITC</p>
 * @author �⽣
 * @version 1.0
 */
import java.util.*;
import javax.servlet.ServletRequest;

public class LanguageUtil
{
    // ������Դ�����б�
    public static final String PUBLIC_RES_CLASS = "com.huiton.pub.lan_tools.PublicRes";
    public static final String CERP_RES_CLASS = "com.huiton.cerp.CerpRes";
    public static final String WFS_RES_CLASS = "com.huiton.cerp.wfs.WfsRes";

    /**
     * ������԰󶨶���
     * @param resClass
     * @return ResourceBundle
     */
    public static ResourceBundle getResourceBundle(ServletRequest request,
                                                    String resClass)
    {
        String lang = (String)request.getAttribute("lang");
        lang = lang==null ? "zh":lang;
        return ResourceBundle.getBundle(resClass, new Locale(lang,""));
    }
}