package com.huiton.mainframe.control.ejb;

import com.huiton.mainframe.control.event.*;
import com.huiton.mainframe.util.tracer.Debug;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ModelUpdateManager
    implements Serializable
{

    public ModelUpdateManager()
    {
    }

    public Collection getUpdatedModels(CERPEvent cse)
    {
        Debug.println("ModelUpdateManager: getUpdateModels");
        ArrayList modelList = new ArrayList();
        if(cse instanceof LanguageChangeEvent)
        {
            Debug.println("ModelUpdateManager: LanguageEvent");
            modelList.add("java:comp/env/ejb/cart/Cart");
        } else
        if(cse instanceof SigninEvent)
        {
            Debug.println("ModelUpdateManager: SigninEvent");
            modelList.add("java:comp/env/ejb/customer/Customer");
            modelList.add("java:comp/env/ejb/profilemgr/ProfileMgr");
            modelList.add("java:comp/env/ejb/cart/Cart");
        }
        return modelList;
    }
}
