package com.huiton.cerp.wfs.mail.events;

import com.huiton.mainframe.control.event.CERPEvent;
import java.util.Collection;
import java.util.HashMap;

public class MailEvent
    implements CERPEvent
{

    public static final int SEND_MAIL = 0;
    public static final int SAVE_ORIGINAL = 1;
    public static final int SAVE_DRAFT = 2;
    public static final int SEND_DRAFT = 3;
    public static final int SEND_DRAFT_ORIGINAL = 4;
    private int actionType;
    private HashMap sendInfo;
    private Collection receInfo;
    private Collection sendAttach;

    public MailEvent(int actionType, HashMap sendInfo, Collection receInfo, Collection sendAttach)
    {
        this.actionType = actionType;
        this.sendInfo = sendInfo;
        this.receInfo = receInfo;
        this.sendAttach = sendAttach;
    }

    public HashMap getSendInfo()
    {
        return sendInfo;
    }

    public Collection getSendAttach()
    {
        return sendAttach;
    }

    public Collection getReceInfo()
    {
        return receInfo;
    }

    public int getActionType()
    {
        return actionType;
    }

    public String getEventName()
    {
        return "java:comp/env/event/wfs/mail/MailEvent";
    }

    public String toString()
    {
        return "MailEvent: ";
    }

}
