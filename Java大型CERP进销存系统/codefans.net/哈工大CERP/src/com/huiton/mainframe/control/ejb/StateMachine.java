package com.huiton.mainframe.control.ejb;

import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.exceptions.CERPEventException;
import com.huiton.mainframe.util.tracer.Debug;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.SessionContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

// Referenced classes of package com.huiton.mainframe.control.ejb:
//            ModelUpdateManager, StateHandler, CERPClientControllerEJB

public class StateMachine
    implements Serializable
{

    private CERPClientControllerEJB cccejb;
    private ModelUpdateManager mum;
    private HashMap attributeMap;
    private HashMap handlerMap;
    private SessionContext sc;

    public StateMachine(CERPClientControllerEJB cccejb, SessionContext sc)
    {
        Debug.println("StateMachine: constructing...");
        this.cccejb = cccejb;
        this.sc = sc;
        mum = new ModelUpdateManager();
        attributeMap = new HashMap();
        handlerMap = new HashMap();
    }

    public Collection handleEvent(CERPEvent cse)
        throws CERPEventException
    {
        Debug.println("StateMachine: received event= ".concat(String.valueOf(String.valueOf(cse))));
        Debug.println("StateMachine: handle event= ".concat(String.valueOf(String.valueOf(cse))));
        String eventName = cse.getEventName();
        if(eventName != null)
        {
            Debug.println("StateMachine: processingEvent= ".concat(String.valueOf(String.valueOf(eventName))));
            String handlerName = getHandlerName(eventName);
            Debug.println("StateMachine: process handler class wj = ".concat(String.valueOf(String.valueOf(handlerName))));
            Debug.println("StateMachine: processing...");
            StateHandler handler = null;
            Debug.println("StateMachine: processing...");
            try
            {
                if(handlerMap.get(eventName) != null)
                {
                    handler = (StateHandler)handlerMap.get(eventName);
                } else
                {
                    handler = (StateHandler)Class.forName(handlerName).newInstance();
                    handlerMap.put(eventName, handler);
                }
            }
            catch(Exception ex)
            {
                Debug.println(String.valueOf(String.valueOf((new StringBuffer("StateMachine: error loading ")).append(handlerName).append(" :").append(ex))));
            }
            if(handler != null)
            {
                Debug.println("StateMachine: loaded handler ".concat(String.valueOf(String.valueOf(handlerName))));
                handler.init(this);
                Debug.println("StateMachine: initialzied ".concat(String.valueOf(String.valueOf(handlerName))));
                handler.doStart();
                handler.perform(cse);
                handler.doEnd();
                Debug.println("StateMachine: sucessfully processed :".concat(String.valueOf(String.valueOf(eventName))));
            }
        }
        return mum.getUpdatedModels(cse);
    }

    private String getHandlerName(String eventName)
    {
        Debug.println(String.valueOf(String.valueOf((new StringBuffer("StateMachine: looking up:")).append(eventName).append("<"))));
        try
        {
            InitialContext ic = new InitialContext();
            String s = (String)ic.lookup(eventName);
            return s;
        }
        catch(NamingException ex)
        {
            Debug.println("AccountHandler caught: ".concat(String.valueOf(String.valueOf(ex))));
        }
        return null;
    }

    public void setAttribute(String key, Object value)
    {
        attributeMap.put(key, value);
    }

    public Object getAttribute(String key)
    {
        return attributeMap.get(key);
    }

    public CERPClientControllerEJB getCERPClientControllerEJB()
    {
        return cccejb;
    }

    public SessionContext getSessionContext()
    {
        return sc;
    }
}