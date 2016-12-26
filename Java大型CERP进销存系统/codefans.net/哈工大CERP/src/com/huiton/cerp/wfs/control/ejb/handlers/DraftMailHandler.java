package com.huiton.cerp.wfs.control.ejb.handlers;

import com.huiton.cerp.pub.util.EJBUtil;
import com.huiton.cerp.pub.util.FileUtil;
import com.huiton.cerp.wfs.mail.ejb.DraftMail;
import com.huiton.cerp.wfs.mail.ejb.DraftMailHome;
import com.huiton.cerp.wfs.mail.events.MailEvent;
import com.huiton.mainframe.control.ejb.StateHandlerSupport;
import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.exceptions.*;
import com.huiton.mainframe.util.tracer.Debug;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import javax.ejb.CreateException;
import javax.naming.NamingException;

public class DraftMailHandler extends StateHandlerSupport
{

    private String tmpFilePath;

    public DraftMailHandler()
    {
        tmpFilePath = "";
    }

    public void perform(CERPEvent event)
        throws CERPEventException
    {
        Debug.println("DraftMailHandler: Entered DraftMailHandler...");

        {
            MailEvent me = (MailEvent)event;
            tmpFilePath = (String)me.getSendInfo().get("attach_addr");
            Debug.println("DraftMailHandler: Create DraftMailHome...");
//            DraftMailHome draftMailHome = EJBUtil.getDraftMailHome();
//            DraftMail draftMail = draftMailHome.create();
            switch(me.getActionType())
            {
            case 0: // '\0'
                Debug.println("DraftMailHandler: draftMail.send()...");
//                draftMail.send(me.getSendInfo(), me.getReceInfo(), me.getSendAttach());
                Debug.println("DraftMailHandler: Mail send succeeded...");
                return;

            case 1: // '\001'
                Debug.println("DraftMailHandler: draftMail.saveOriginal()...");
//                draftMail.send(me.getSendInfo(), me.getReceInfo(), me.getSendAttach());
                Debug.println("DraftMailHandler: saveOriginal succeeded...");
                return;

            case 2: // '\002'
                Debug.println("DraftMailHandler: draftMail.saveDraft()...");
//                draftMail.saveDraft(me.getSendInfo(), me.getSendAttach());
                Debug.println("DraftMailHandler: saveDraft succeeded...");
                return;

            case 3: // '\003'
                Debug.println("DraftMailHandler: draftMail.sendDraft()...");
//                draftMail.sendDraft(me.getSendInfo(), me.getReceInfo(), me.getSendAttach());
                Debug.println("DraftMailHandler: sendDraft succeeded...");
                return;

            case 4: // '\004'
                Debug.println("DraftMailHandler: draftMail.sendDraft()...");
//                draftMail.sendDraftAndSaveOriginal(me.getSendInfo(), me.getReceInfo(), me.getSendAttach());
                Debug.println("DraftMailHandler: sendDraft succeeded...");
                return;
            }
        }

    }

    public void doEnd()
    {
        try
        {
            if(tmpFilePath != null && !tmpFilePath.equals(""))
            {
                tmpFilePath = tmpFilePath.substring(0, tmpFilePath.lastIndexOf("\\"));
                Debug.println("DraftMailHandler.doEnd(): tmpFilePath = ".concat(String.valueOf(String.valueOf(tmpFilePath))));
                FileUtil.deleteFile(tmpFilePath);
            }
        }
        catch(IOException e)
        {
            Debug.println("DraftMailHandler.doEnd(): \u4E34\u65F6\u6587\u4EF6\u5220\u9664\u5931\u8D25\uFF01");
        }
    }
}
