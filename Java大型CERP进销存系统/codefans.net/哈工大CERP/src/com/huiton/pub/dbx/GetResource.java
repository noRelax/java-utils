// FrontEnd Plus GUI for JAD
// DeCompiled : GetResource.class

package com.huiton.pub.dbx;

import java.util.Locale;
import java.util.ResourceBundle;

public class GetResource
{

    Locale locales[];
    Locale currentLocale;
    private ResourceBundle res;
    String name;

    public GetResource()
    {
    }

    public String getObjectName(String objectID)
    {
        name = res.getString(objectID);
        return name;

    }

    public void setCurrentLocale(int localeIndex, String fileName)
    {
        locales = (new Locale[] {
            new Locale("en", "US"), new Locale("zh", "")
        });
        currentLocale = locales[localeIndex];
        res = ResourceBundle.getBundle(fileName.trim(), currentLocale);
    }
}
