package com.huiton.cerp.wfs.mail.ejb;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

// Referenced classes of package com.huiton.cerp.wfs.mail.ejb:
//            DraftMail

public interface DraftMailHome
    extends EJBHome
{

    public abstract DraftMail create()
        throws CreateException, RemoteException;
}
