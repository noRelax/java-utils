package com.huiton.mainframe.control.ejb;

import com.huiton.cerp.wfs.document.ejb.DraftDocu;
import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.exceptions.CERPAppException;
import com.huiton.mainframe.control.exceptions.CERPEventException;
import java.rmi.RemoteException;
import java.util.Collection;
import javax.ejb.EJBObject;

public interface CERPClientController
    extends EJBObject
{

    public abstract Collection handleEvent(CERPEvent cerpevent)
        throws CERPEventException, RemoteException;

    public abstract DraftDocu getDraftDocu()
        throws RemoteException, CERPAppException;
}
