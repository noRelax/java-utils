package com.huiton.cerp.pub.util;

/**
 * Title:        CERP≤‚ ‘øÚº‹
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author Œ‚Ω£
 * @version 1.0
 */
import java.util.ResourceBundle;
import java.util.Locale;
import javax.servlet.http.*;
import com.huiton.cerp.pub.util.SubsystemKeys;

public class ResourceContainer {
    public static final String DEFAULT_RESOURCE = "com.huiton.cerp.wfs.WfsRes";
    public static final String WFS_RESOURCE = "com.huiton.cerp.wfs.WfsRes";

    public static ResourceBundle getResource(HttpServletRequest request,
                                             String sysCode)
    {
        String lang = request.getParameter("lang");
        if (lang == null)
            lang = "zh";

        String resClass = DEFAULT_RESOURCE;
        if(sysCode.equals(SubsystemKeys.WFS)) {
            resClass = WFS_RESOURCE;
        }

        return ResourceBundle.getBundle(resClass, new Locale(lang,""));
    }
}