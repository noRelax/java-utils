package com.huiton.cerp.pub.util;

/**
 * This interface contains all the keys that are used to
 * store data in the different scopes of web-tier. These
 * values are the same as those used in the JSP
 * pages (useBean tags).
 */
public interface WebKeys {
    /**
     * WebKeys used by CERP system.
     */
    public static final String MainFrameKey = "MAINFRAME";
    public static final String PageQueryKey = "PageQuery";
    public static final String DBOperatorKey = "DBOperator";
    public static final String UsercodeKey = "user_code";
    public static final String PasswordKey = "password";
    public static final String SessionCodeKey = "session_code";
    public static final String DataHandleKey = "flag";

    // 分页处理
    public static final String PageVectorKey = "pageVector";
    public static final String PageCountKey = "pageCount";
    public static final String PageSizeKey = "PageSize";
    public static final String CurrentPageKey = "currentPage";
    public static final String CurrentPageKey1 ="currentPage1";

    // 数据操作对象
    public static final String SALPageQueryKey = "SALPageQuery";
    public static final String SALDBOperatorKey = "SALDBOperator";
    public static final String CRMPageQueryKey = "CRMPageQuery";
    public static final String CRMDBOperatorKey = "CRMDBOperator";
    public static final String SFCPageQueryKey = "SFCPageQuery";
    public static final String SFCDBOperatorKey = "SFCDBOperator";
    public static final String MDMPageQueryKey = "MDMPageQuery";
    public static final String MDMDBOperatorKey = "MDMDBOperator";

    //上面两行add by 王忠杰
    public static final String EPDPageQueryKey = "EPDPageQuery";
    public static final String EPDDBOperatorKey = "EPDDBOperator";

    public static final String SAMPageQueryKey = "SAMPageQuery";
    public static final String SAMDBOperatorKey = "SAMDBOperator";

    public static final String CUSDBOperatorKey = "CUSDBOperator";
    public static final String CUSPageQueryKey = "CUSPageQuery";

    public static final String SPMDBOperatorKey = "SPMDBOperator";
    public static final String SPMPageQueryKey = "SPMPageQuery";

    public static final String MFADBOperatorKey = "MFADBOperator";
    public static final String MFAPageQueryKey = "MFAPageQuery";
    public static final String PPCDBOperatorKey = "PPCDBOperator";
    public static final String PPCPageQueryKey = "PPCPageQuery";
    public static final String PPPDBOperatorKey = "PPPDBOperator";
    public static final String PPPPageQueryKey = "PPPPageQuery";
    public static final String PURDBOperatorKey = "PURDBOperator";
    public static final String PURPageQueryKey = "PURPageQuery";
    public static final String INVDBOperatorKey = "INVDBOperator";
    public static final String INVPageQueryKey = "INVPageQuery";
    public static final String COSDBOperatorKey = "COSDBOperator";
    public static final String COSPageQueryKey = "COSPageQuery";
    public static final String QMSDBOperatorKey = "QMSDBOperator";
    public static final String QMSPageQueryKey = "QMSPageQuery";
    public static final String EQPDBOperatorKey = "EQPDBOperator";
    public static final String EQPPageQueryKey = "EQPPageQuery";
    public static final String EQPDBOperatorKey = "EDHFDBOperator";
    public static final String EQPPageQueryKey = "EDHFPageQuery";
    
    // WFS
    public static final String WFSPageQueryKey = "WFSPageQuery";
    public static final String WFSDBOperatorKey = "WFSDBOperator";

    public static final String WFSMailReceInfoKey = "WFSMailReceInfoKey";
    public static final String WFSMailAttachKey = "WFSMailAttachKey";
    public static final String WFSDocuAttachKey = "WFSDocuAttachKey";
    public static final String WFSDocuRangeKey = "WFSDocuRangeKey";
    public static final String WFS2002ClassCodeKey = "WFS2002ClassCodeKey";   // 每页显示公文类别
    public static final String WFS2002OpenerKey = "WFS2002OpenerKey";
    public static final String WFS33004OpenerKey = "WFS33004OpenerKey";
    public static final String NewMailCount = "newMailCount";
    public static final String RowCount = "rowCount";

    //侯曦洋在WFS0121Handler.java中使用的session名称
    public static final String WFSwfGroupCode = "WFSwfGroupCode";
    public static final String WFSwfGroupName = "WFSwfGroupName";
    //侯曦洋在WFS0121Handler.java中使用的session名称 结束

    // 高云鹏在WFS0121Handler.java中使用的session名称
    public static final String EPDSelectionVCT="EPDSLIT_Vct";
    public static final String EPDStepFlag="EPDStepFlag";
    public static final String EPDCurrentSQL="EPDCurrentSQL";
    public static final String EPDCurrentSQL1="EPDCurrentSQL1";
    public static final String ULTResultt="Ultimate_Result";
    // 高云鹏在WFS0121Handler.java中使用的session名称 结束

    // 高云鹏在WFS3311Handler.java中使用的session名称
    public static final String WFSBackArray="WFSBackArray";
    // 王涛定义
    public static final String WFSSelectedGroupKey = "WFSSelectedGroup";
    public static final String WFSCheckStateKey = "WFSCheckState";
    public static final String WFSDocCode = "WFSDocCode";
    public static final String WFSReadNote = "WFSReadNote";
    public static final String RowNo = "RowNo";
    public static final String DocContent = "DocContent";
    public static final String DocTopic = "DocTopic";

    // 审批
    public static final String WFSCheckbillAttachKey = "WFSCheckbillAttachKey";

    /**
      * WebKeys used by mainframe.
      */
    public static final String CatalogModelKey = "catalog";
    public static final String AccountModelKey = "account";
    public static final String ModelManagerKey = "mm";
    public static final String ScreenManagerKey = "screenManager";
    public static final String RequestProcessorKey = "rp";
    public static final String ProfileMgrModelKey = "profilemgr";
    public static final String InventoryModelKey = "inventory";
    public static final String ShoppingCartModelKey = "cart";
    public static final String WebControllerKey = "webController";
    public static final String CurrentScreen = "currentScreen";
    public static final String PreviousScreen = "previousScreen";
    public static final String LanguageKey = "language";
    public static final String URLMappingsKey = "urlMappings";
    public static final String CustomerWebImplKey = "customer";
    public static final String MissingFormDataKey = "missingFormData";
    public static final String SigninTargetURL = "signinTargetURL";
    public static final String ServerTypeKey = "serverType";
    public static final String ParamHashtable = "paramHashtable";

}
