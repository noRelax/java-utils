package com.huiton.cerp.wfs.document.ejb;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.EJBException;
import javax.ejb.EJBObject;

public interface DraftDocu
    extends EJBObject
{

    public abstract void saveDraft(HashMap hashmap, Collection collection)
        throws RemoteException, EJBException;

    public abstract void updateDraft(HashMap hashmap, Collection collection)
        throws RemoteException, EJBException;

    public abstract void releaseDocu(HashMap hashmap, HashMap hashmap1, Collection collection, Collection collection1)
        throws RemoteException, EJBException;
}
