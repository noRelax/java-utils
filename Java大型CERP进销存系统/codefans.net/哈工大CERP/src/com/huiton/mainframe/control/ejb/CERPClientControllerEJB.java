package com.huiton.mainframe.control.ejb;

import com.huiton.cerp.pub.util.EJBUtil;
import com.huiton.cerp.wfs.document.ejb.DraftDocu;
import com.huiton.cerp.wfs.document.ejb.DraftDocuHome;
import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.exceptions.CERPAppException;
import com.huiton.mainframe.control.exceptions.CERPEventException;
import com.huiton.mainframe.util.tracer.Debug;
import java.rmi.RemoteException;
import java.util.Collection;
import javax.ejb.*;
import javax.naming.NamingException;

// Referenced classes of package com.huiton.mainframe.control.ejb:
//            StateMachine

public class CERPClientControllerEJB
    implements SessionBean
{

    private SessionContext sessionContext;
    private StateMachine sm;
    private SessionContext sc;
    private DraftDocu draftDocu;

    public CERPClientControllerEJB()
    {
    }

    public void ejbCreate()
    {
        sm = new StateMachine(this, sc);
    }

    public void ejbRemove()
        throws RemoteException
    {
        sm = null;
        draftDocu = null;
    }

    public void ejbActivate()
        throws RemoteException
    {
    }

    public void ejbPassivate()
        throws RemoteException
    {
    }

    public void setSessionContext(SessionContext sc)
        throws RemoteException
    {
        this.sc = sc;
    }

    public Collection handleEvent(CERPEvent cse)
        throws CERPEventException
    {
        return sm.handleEvent(cse);
    }

    public DraftDocu getDraftDocu()
        throws CERPAppException
    {
        if(draftDocu == null)
        {
            {
                Debug.println("CERPClientController.getDraftDocu(): entering...");
//                DraftDocuHome ddHome = EJBUtil.getDraftDocuHome();
                Debug.println("CERPClientController.getDraftDocu(): creating...");
//                draftDocu = ddHome.create();
            }
        }
        return draftDocu;
    }
}
