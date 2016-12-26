package com.huiton.cerp.pub.util;

/**
 * Title:        CERP≤‚ ‘øÚº‹
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author Œ‚Ω£
 * @version 1.0
 */

public interface JNDINames {

    //
    // JNDI names of EJB home objects
    //
    // mail
    public static final String DRAFTMAIL_EJBHOME =
        "com/huiton/cerp/wfs/mail/ejb/DraftMail";
    public static final String WFSSENDINFO_EJBHOME =
        "java:comp/env/com/huiton/cerp/wfs/mail/ejb/WFSSendInfo";
    public static final String WFSRECEINFO_EJBHOME =
        "java:comp/env/com/huiton/cerp/wfs/mail/ejb/WFSReceInfo";
    public static final String WFSSENDATTACH_EJBHOME =
        "java:comp/env/com/huiton/cerp/wfs/mail/ejb/WFSSendAttach";

    // document
    public static final String DRAFTDOCU_EJBHOME =
        "com/huiton/cerp/wfs/document/ejb/DraftDocu";
    public static final String WFSDOCU_EJBHOME =
        "java:comp/env/com/huiton/cerp/wfs/document/ejb/WFSDocu";
    public static final String WFSDOCUINIT_EJBHOME =
        "java:comp/env/com/huiton/cerp/wfs/document/ejb/WFSDocuInit";
    public static final String WFSDOCUREAD_EJBHOME =
        "java:comp/env/com/huiton/cerp/wfs/document/ejb/WFSDocuRead";
    public static final String WFSDOCURANGE_EJBHOME =
        "java:comp/env/com/huiton/cerp/wfs/document/ejb/WFSDocuRange";
    public static final String WFSDOCUATTACH_EJBHOME =
        "java:comp/env/com/huiton/cerp/wfs/document/ejb/WFSDocuAttach";

    //spm strategy
    public static final String SPM_STRATEGYLOGIC_EJBHOME =
        "com/huiton/cerp/spm/strategy/ejb/StrategyLogic";
    public static final String SPM_STRATEGY_DAO_CLASS =
        "java:comp/env/ejb/com/huiton/cerp/spm/strategy/dao/StrategyDAO";
    public static final String SPM_STRATEGY_DATASOURCE =
        "java:comp/env/jdbc/spm/DataSource";
    public static final String SPM_STRATEGY_EJBHOME =
        "java:comp/env/com/huiton/cerp/spm/strategy/ejb/Strategy";
    public static final String SPM_CONFIG_EJBHOME =
        "java:comp/env/com/huiton/cerp/spm/strategy/ejb/SpmConfig";


    /** JNDI name of the home interface of ShoppingClientController EJB */
    public static final String CCC_EJBHOME =
        "java:comp/env/ejb/ccc/Ccc";

    public static final String SERVER_TYPE =
        "java:comp/env/server/ServerType";
}