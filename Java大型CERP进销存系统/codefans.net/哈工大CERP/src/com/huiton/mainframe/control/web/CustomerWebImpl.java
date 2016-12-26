package com.huiton.mainframe.control.web;

/**
 * Title:        CERP测试框架
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author 吴剑
 * @version 1.0
 */

import com.huiton.mainframe.control.web.ModelManager;
import com.huiton.mainframe.control.web.ModelUpdateListener;

import com.huiton.mainframe.control.exceptions.GeneralFailureException;
import com.huiton.mainframe.control.exceptions.CERPAppException;

import com.huiton.mainframe.util.tracer.Debug;

/**
 * This class is the web-tier representation of the Account.
 */
public class CustomerWebImpl implements ModelUpdateListener, java.io.Serializable {

    // private ModelManager mm;
    private String m_userId = null; //userCode
    private String m_userName = null;
    private String m_companyCode = null;
    private String m_userDept = null;//added by cameran
    private String m_userDeptName = null;//added by cameran
    private String m_currentYear = null;
    private String m_sessionCode = null;
    private String m_userUniqueNo = null;
    private String m_language = "zh";
    private String m_defaultPage = null;
    private boolean m_loggedIn = false;
    private String m_sysCode = null ;
    private String m_progCode = null;
    private InnerCondition m_ic = new InnerCondition();

    public CustomerWebImpl() {
        m_sysCode = "sam";
        m_progCode = "";
    }

    public CustomerWebImpl(ModelManager mm) {
        // this.mm = mm;
        // mm.addListener(JNDINames.CUSTOMER_EJBHOME, this);
    }

    public void setUserId(String userId) {
        this.m_userId = userId;
    }

    public String getUserId() {
        return m_userId;
    }
    //以下关于部门的函数由cameran添加，用于提取用户所在部门代码2003-6-1
    public void setUserDept(String userDept) {
        this.m_userDept = userDept;
    }

    public String getUserDept() {
        return m_userDept;
    }

    public void setUserDeptName(String userDeptName) {
        this.m_userDeptName = userDeptName;
    }

    public String getUserDeptName() {
        return m_userDeptName;
    }
    //以上关于部门的函数由cameran添加，用于提取用户所在部门代码2003-6-1
    public void setUserName(String userName) {
        this.m_userName = userName;
    }

    public String getUserName() {
        return m_userName;
    }

    public void setCompanyCode(String companyCode) {
        this.m_companyCode = companyCode;
    }

    public String getCompanyCode() {
        return m_companyCode;
    }

    public void setCurrentYear(String currentYear) {
        this.m_currentYear = currentYear;
    }

    public String getCurrentYear() {
        return m_currentYear;
    }

    public void setSessionCode(String sessionCode) {
        this.m_sessionCode = sessionCode;
    }

    public String getSessionCode() {
        return m_sessionCode;
    }

    public void setUserUniqueNo(String userUniqueNo) {
        this.m_userUniqueNo = userUniqueNo;
    }

    public String getUserUniqueNo() {
        return m_userUniqueNo;
    }

    public void setLanguage(String language) {
        this.m_language = language;
    }

    public String getLanguage() {
        return m_language;
    }

    public void setDefaultPage(String defaultPage) {
        this.m_defaultPage = defaultPage;
    }

    public String getDefaultPage() {
        return m_defaultPage;
    }

    /**
     *  Set by the SigninHandler.doAfter() method
     */
    public void setLoggedIn(boolean loggedIn) {
        this.m_loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return m_loggedIn;
    }

    public void setSysCode(String sysCode) {
        this.m_sysCode = sysCode;
    }

    public void setProgCode(String progCode) {
        this.m_progCode = progCode;
    }

    public String getProgCode() {
        return m_progCode;
    }

    public String getSysCode() {
        return m_sysCode;
    }

    /**设置条件*/
    public void setInnerCondition(InnerCondition ic)
    {
        m_ic = ic;
    }

    /**获得传入参数的子系统代码*/
    public String getParamSysCode()
    {
        String mm_sysCode = getParam("sys_code");
        if (mm_sysCode==null)
            return m_sysCode ;

        return mm_sysCode ;
    }

    /**根据参数名获得参数值*/
    public String getParam(String name)
    {
        return m_ic.getParam(name);
    }

    public void performUpdate()  throws CERPAppException {
        ;
    }
}

