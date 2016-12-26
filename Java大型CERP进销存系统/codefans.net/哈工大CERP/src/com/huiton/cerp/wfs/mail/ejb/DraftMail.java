package com.huiton.cerp.wfs.mail.ejb;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.EJBObject;

public interface DraftMail
    extends EJBObject
{

    public abstract void saveDraft(HashMap hashmap, Collection collection)
        throws RemoteException;

    public abstract void send(HashMap hashmap, Collection collection, Collection collection1)
        throws RemoteException;

    public abstract void sendDraft(HashMap hashmap, Collection collection, Collection collection1)
        throws RemoteException;

    public abstract void sendDraftAndSaveOriginal(HashMap hashmap, Collection collection, Collection collection1)
        throws RemoteException;
}
