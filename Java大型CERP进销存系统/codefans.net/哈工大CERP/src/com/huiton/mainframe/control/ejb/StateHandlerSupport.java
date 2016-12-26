package com.huiton.mainframe.control.ejb;

import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.exceptions.CERPEventException;
import java.io.Serializable;

// Referenced classes of package com.huiton.mainframe.control.ejb:
//            StateHandler, StateMachine

public class StateHandlerSupport
    implements Serializable, StateHandler
{

    protected StateMachine machine;

    public StateHandlerSupport()
    {
        machine = null;
    }

    public void init(StateMachine machine)
    {
        this.machine = machine;
    }

    public void doStart()
    {
    }

    public void perform(CERPEvent cerpevent)
        throws CERPEventException
    {
    }

    public void doEnd()
    {
    }
}
