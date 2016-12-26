package com.huiton.mainframe.control.ejb;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

// Referenced classes of package com.huiton.mainframe.control.ejb:
//            CERPClientController

public interface CERPClientControllerHome
    extends EJBHome
{

    public abstract CERPClientController create()
        throws CreateException, RemoteException;
}
