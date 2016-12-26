package com.huiton.cerp.pub.util;

/*
 * $Id: EJBUtil.java,v 1.10.4.3 2001/03/15 00:40:10 brydon Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits r�serv�s.
 */
import java.util.Properties;
import java.rmi.RemoteException;
import javax.rmi.PortableRemoteObject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ejb.CreateException;
import javax.naming.Context;
import com.huiton.mainframe.control.ejb.CERPClientControllerHome;
//import com.huiton.cerp.wfs.mail.ejb.*;
//import com.huiton.cerp.wfs.document.ejb.*;
//import com.huiton.cerp.spm.strategy.ejb.*;
import com.huiton.cerp.pub.util.JNDINames;
import com.huiton.mainframe.util.tracer.Debug;
//import com.huiton.cerp.pub.query.*;


/**
 * This is a utility class for obtaining EJB references.
 */
public final class EJBUtil {

    public static CERPClientControllerHome getCCCHome()
    throws javax.naming.NamingException {
        InitialContext initial = new InitialContext();
        // Context initial = getInitialContext();
        Object objref = initial.lookup(JNDINames.CCC_EJBHOME);

        return (CERPClientControllerHome)PortableRemoteObject.narrow(objref, CERPClientControllerHome.class);
    }
/*
    public static DraftMailHome getDraftMailHome()
        throws javax.naming.NamingException
    {
        InitialContext initial = new InitialContext();
        Debug.println("EJBUtil: draftMail..." + JNDINames.DRAFTMAIL_EJBHOME);
        Object objref = initial.lookup(JNDINames.DRAFTMAIL_EJBHOME);

        return (DraftMailHome)
                PortableRemoteObject.narrow(objref, DraftMailHome.class);
    }

    public static DraftDocuHome getDraftDocuHome()
        throws javax.naming.NamingException
    {
        InitialContext initial = new InitialContext();
        Debug.println("EJBUtil: draftMail..." + JNDINames.DRAFTDOCU_EJBHOME);
        Object objref = initial.lookup(JNDINames.DRAFTDOCU_EJBHOME);

        return (DraftDocuHome)
                PortableRemoteObject.narrow(objref, DraftDocuHome.class);
    }

    //������2002.2.28
    public static StrategyLogicHome getStrategyLogicHome()
        throws javax.naming.NamingException
    {
        Context initial = getInitialContext();
        Debug.println("EJBUtil: StrategyLogicHome..." + "com/huiton/cerp/spm/strategy/ejb/StrategyLogic");
        Object objref = initial.lookup("com/huiton/cerp/spm/strategy/ejb/StrategyLogic");

        return (StrategyLogicHome)
                PortableRemoteObject.narrow(objref, StrategyLogicHome.class);
    }
*/
  /**
   * Using a Properties object will work on JDK 1.1.x and Java2
   * clients
   */
  private static Context getInitialContext() throws NamingException {

    try {
      // Get an InitialContext
      Properties h = new Properties();
      h.put(Context.INITIAL_CONTEXT_FACTORY,
              "weblogic.jndi.WLInitialContextFactory");
      h.put(Context.PROVIDER_URL, "t3://localhost:7001");
      return new InitialContext(h);
    } catch (NamingException ne) {
      Debug.println("We were unable to get a connection to the WebLogic server at "+"t3://localhost:7001");
      Debug.println("Please make sure that the server is running.");
      throw ne;
    }
  }

  /**
   * ��ȡͨ�ò�ѯ����Ľ������ѯ���󡣱�������ͨ�ò�ѯ������á�
   * @param classUrl  ���Ƶ����ڷ��ؽ������ĵ�ַ������Ӧscg_program���е�rst_url
   * @return  �ɹ�ʱ����ProgRst��ʵ�������򷵻�null;
   */
   /*
  public static ProgRst getProgRstObj(String classUrl) throws Exception
  {
    //ȷ�ϲ�ѯ�������Ч��
    Class cls = Class.forName(classUrl);
    Debug.println("valid url");
    //������ѯ���򲢵õ����������
    Object obj = cls.newInstance();
    if (obj instanceof CustomizerProgRst)
      return (CustomizerProgRst)obj;

    if ( obj instanceof ProgRst)
      return (ProgRst)obj;
    throw new Exception(classUrl+"δʵ�ֽӿڣ�ProgRst��CustomizerProgRst");
  }
*/
}
