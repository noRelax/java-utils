package com.huiton.cerp.wfs.document.ejb;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

// Referenced classes of package com.huiton.cerp.wfs.document.ejb:
//            DraftDocu

public interface DraftDocuHome
    extends EJBHome
{

    public abstract DraftDocu create()
        throws CreateException, RemoteException;
}
