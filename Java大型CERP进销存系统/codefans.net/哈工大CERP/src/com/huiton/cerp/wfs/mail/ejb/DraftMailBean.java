package com.huiton.cerp.wfs.mail.ejb;

import com.huiton.cerp.pub.util.FileUtil;
import com.huiton.cerp.pub.util.functions.LookField;
import com.huiton.mainframe.util.tracer.Debug;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

// Referenced classes of package com.huiton.cerp.wfs.mail.ejb:
//            WFSSendInfoHome, WFSReceInfoHome, WFSSendAttachHome, WFSSendInfoPK,
//            WFSSendInfo

public class DraftMailBean
    implements SessionBean
{

    private SessionContext sessionContext;

    public DraftMailBean()
    {

    }

    public void ejbCreate()
    {

    }

    public void ejbRemove()
        throws RemoteException
    {
    }

    public void ejbActivate()
        throws RemoteException
    {

    }

    public void ejbPassivate()
        throws RemoteException
    {


    }

    public void setSessionContext(SessionContext sessionContext)
        throws RemoteException
    {
        this.sessionContext = sessionContext;
    }

}