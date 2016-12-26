package com.huiton.mainframe.control.ejb;

import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.exceptions.CERPEventException;

// Referenced classes of package com.huiton.mainframe.control.ejb:
//            StateMachine

public interface StateHandler
{

    public abstract void init(StateMachine statemachine);

    public abstract void doStart();

    public abstract void perform(CERPEvent cerpevent)
        throws CERPEventException;

    public abstract void doEnd();
}
