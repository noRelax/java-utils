package com.huiton.cerp.pub.util;

/**
 * Title:        CERP测试框架
 * Description:  获取数据库操作对象，在各个处理页面请求使用数据库对象时使用
 * Copyright:    Copyright (c) 2000
 * Company:      BRITC
 * @author 吴剑
 * @version 1.0
 */
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import com.huiton.pub.dbx.JdbOp;
import com.huiton.pub.dbx.PageQuery;
import com.huiton.cerp.pub.util.WebKeys;
import com.huiton.cerp.pub.util.SubsystemKeys;

public class DBOperators {
    /**
     * 获得JdbOp对象，并把它存到session中备用
     * @param HttpServletRequest request
     * @param String sessionCode
     * @param String subsystemKey
     * @return JdbOp对象
     */
    public static JdbOp getJdbOp(HttpServletRequest request,
                                    String sessionCode,
                                    String subsystemKey)
        throws Exception
    {
        HttpSession session = request.getSession();
        JdbOp dbOperator = null;

        String sessionKey = "";
        if(subsystemKey.equals(SubsystemKeys.EPD))
            sessionKey = WebKeys.EPDDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.WFS))
            sessionKey = WebKeys.WFSDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.SAM))
            sessionKey = WebKeys.SAMDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.CUS))
            sessionKey = WebKeys.CUSDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.SPM))
            sessionKey = WebKeys.SPMDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.MFA))
            sessionKey = WebKeys.MFADBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.SAL))
            sessionKey = WebKeys.SALDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.CRM))
            sessionKey = WebKeys.CRMDBOperatorKey;
        //cameran
        else if(subsystemKey.equals(SubsystemKeys.SFC))
            sessionKey = WebKeys.SFCDBOperatorKey;
        //cameran
        else if(subsystemKey.equals(SubsystemKeys.MDM))
            sessionKey = WebKeys.MDMDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.PPC))
            sessionKey = WebKeys.PPCDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.PPP))
                 sessionKey = WebKeys.PPPDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.INV))
                 sessionKey = WebKeys.INVDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.PUR))
                 sessionKey = WebKeys.PURDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.COS))
                 sessionKey = WebKeys.COSDBOperatorKey;
       else if(subsystemKey.equals(SubsystemKeys.QMS))
                 sessionKey = WebKeys.QMSDBOperatorKey;
       else if(subsystemKey.equals(SubsystemKeys.EQP))
       	sessionKey = WebKeys.EQPDBOperatorKey;

        else
            throw new Exception(
                "DBOperators.getJdbOp(): 没有定义子系统使用的数据对象键值！");

        dbOperator = (JdbOp)session.getAttribute(sessionKey);

        if(dbOperator==null) {
            dbOperator = new JdbOp(sessionCode, subsystemKey);
            session.setAttribute(sessionKey, dbOperator);
        }

        return dbOperator;
    }

    /**
     * 获得PageQuery对象，并把它存到session中备用
     * @param HttpServletRequest request
     * @param String sessionCode
     * @param String subsystemKey
     * @param String funcCode
     * @return PageQuery对象
     */
    public static PageQuery getPageQuery(HttpServletRequest request,
                                         String sessionCode,
                                         String subsystemKey,
                                         String funcCode
                                         )
        throws Exception
    {
        HttpSession session = request.getSession();
        PageQuery pageQuery = null;

        String sessionKey = "";
        if(subsystemKey.equals(SubsystemKeys.EPD)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.EPDPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.EPDPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.WFS)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.WFSPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.WFSPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.SAM)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.SAMPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.SAMPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.CUS)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.CUSPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.CUSPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.SPM)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.SPMPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.SPMPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.MFA)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.MFAPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.MFAPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.SAL)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.SALPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.SALPageQueryKey;
            System.out.println("-----in DBOperator:"+subsystemKey);

        }
        else if(subsystemKey.equals(SubsystemKeys.CRM)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.CRMPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.CRMPageQueryKey;
        }
        //cameran
        else if(subsystemKey.equals(SubsystemKeys.SFC)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.SFCPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.SFCPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.MDM)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.MDMPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.MDMPageQueryKey;

        }
        else if(subsystemKey.equals(SubsystemKeys.PPC)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.PPCPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.PPCPageQueryKey;

        }
       else if(subsystemKey.equals(SubsystemKeys.PPP)) {
             pageQuery = (PageQuery)session.getAttribute(WebKeys.PPPPageQueryKey +
                                                       funcCode);
             sessionKey = WebKeys.PPPPageQueryKey;
         }
         else if(subsystemKey.equals(SubsystemKeys.INV)) {
             pageQuery = (PageQuery)session.getAttribute(WebKeys.INVPageQueryKey +
                                                       funcCode);
             sessionKey = WebKeys.INVPageQueryKey;
         }
        else if(subsystemKey.equals(SubsystemKeys.PUR)) {
             pageQuery = (PageQuery)session.getAttribute(WebKeys.PURPageQueryKey +
                                                       funcCode);
             sessionKey = WebKeys.PURPageQueryKey;
         }
        else if(subsystemKey.equals(SubsystemKeys.COS)) {
             pageQuery = (PageQuery)session.getAttribute(WebKeys.COSPageQueryKey +
                                                       funcCode);
             sessionKey = WebKeys.COSPageQueryKey;
         }
        else if(subsystemKey.equals(SubsystemKeys.QMS)) {
             pageQuery = (PageQuery)session.getAttribute(WebKeys.QMSPageQueryKey +
                                                       funcCode);
             sessionKey = WebKeys.QMSPageQueryKey;
         }
        else if(subsystemKey.equals(SubsystemKeys.EQP)) {
        	pageQuery = (PageQuery)session.getAttribute(WebKeys.EQPPageQueryKey +
        			funcCode);
        	sessionKey = WebKeys.EQPPageQueryKey;
        }
        
        
        
        
        else {
            System.out.println("What's wrong?:"+subsystemKey);
            throw new Exception(
                "DBOperators.getPageQuery(): 没有定义子系统使用的数据对象键值！");
       }

        sessionKey += funcCode;

        if(pageQuery==null) {
            pageQuery = new PageQuery(sessionCode, subsystemKey);
            session.setAttribute(sessionKey, pageQuery);
        }

        return pageQuery;
    }


    /**
     * 获得JdbOp对象，并把它存到session中备用
     * @param ServletRequest request
     * @param HttpSession session
     * @param String sessionCode
     * @param String subsystemKey
     * @return JdbOp对象
     */
    public static JdbOp getJdbOp(ServletRequest request,
                                    HttpSession session,
                                    String sessionCode,
                                    String subsystemKey)
        throws Exception
    {
        JdbOp dbOperator = null;

        String sessionKey = "";
        if(subsystemKey.equals(SubsystemKeys.EPD))
            sessionKey = WebKeys.EPDDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.WFS))
            sessionKey = WebKeys.WFSDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.SAM))
            sessionKey = WebKeys.SAMDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.CUS))
            sessionKey = WebKeys.CUSDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.SPM))
            sessionKey = WebKeys.SPMDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.MFA))
            sessionKey = WebKeys.MFADBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.SAL))
            sessionKey = WebKeys.SALDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.CRM))
            sessionKey = WebKeys.CRMDBOperatorKey;
        //cameran
        else if(subsystemKey.equals(SubsystemKeys.SFC))
            sessionKey = WebKeys.SFCDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.MDM))
            sessionKey = WebKeys.MDMDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.PPC))
            sessionKey = WebKeys.PPCDBOperatorKey;
        else if(subsystemKey.equals(SubsystemKeys.PPP))
                  sessionKey = WebKeys.PPPDBOperatorKey;
         else if(subsystemKey.equals(SubsystemKeys.INV))
                  sessionKey = WebKeys.INVDBOperatorKey;
         else if(subsystemKey.equals(SubsystemKeys.PUR))
                  sessionKey = WebKeys.PURDBOperatorKey;
         else if(subsystemKey.equals(SubsystemKeys.COS))
                  sessionKey = WebKeys.COSDBOperatorKey;
         else if(subsystemKey.equals(SubsystemKeys.QMS))
                  sessionKey = WebKeys.QMSDBOperatorKey;
         else if(subsystemKey.equals(SubsystemKeys.EQP))
         	sessionKey = WebKeys.EQPDBOperatorKey;
        

        else
            throw new Exception(
                "DBOperators.getJdbOp(): 没有定义子系统使用的数据对象键值！");

        dbOperator = (JdbOp)session.getAttribute(subsystemKey);

        if(dbOperator==null) {
            dbOperator = new JdbOp(sessionCode, subsystemKey);
            session.setAttribute(sessionKey, dbOperator);
        }

        return dbOperator;
    }

    /**
     * 获得PageQuery对象，并把它存到session中备用
     * @param ServletRequest request
     * @param ServletSession session
     * @param String subsystemKey
     * @param String funcCode
     * @return PageQuery对象
     */
    public static PageQuery getPageQuery(ServletRequest request,
                                         HttpSession session,
                                         String sessionCode,
                                         String subsystemKey,
                                         String funcCode
                                         )
        throws Exception
    {
        PageQuery pageQuery = null;

        String sessionKey = "";
        if(subsystemKey.equals(SubsystemKeys.EPD)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.EPDPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.EPDPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.WFS)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.WFSPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.WFSPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.SAM)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.SAMPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.SAMPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.CUS)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.CUSPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.CUSPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.SPM)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.SPMPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.SPMPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.MFA)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.MFAPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.MFAPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.SAL)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.SALPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.SALPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.SAL)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.CRMPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.CRMPageQueryKey;
        }
        //cameran
        else if(subsystemKey.equals(SubsystemKeys.SFC)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.SFCPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.SFCPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.MDM)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.MDMPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.MDMPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.PPC)) {
            pageQuery = (PageQuery)session.getAttribute(WebKeys.PPCPageQueryKey +
                                                      funcCode);
            sessionKey = WebKeys.PPCPageQueryKey;
        }
        else if(subsystemKey.equals(SubsystemKeys.PPP)) {
             pageQuery = (PageQuery)session.getAttribute(WebKeys.PPPPageQueryKey +
                                                       funcCode);
             sessionKey = WebKeys.PPPPageQueryKey;
         }
         else if(subsystemKey.equals(SubsystemKeys.INV)) {
             pageQuery = (PageQuery)session.getAttribute(WebKeys.INVPageQueryKey +
                                                       funcCode);
             sessionKey = WebKeys.INVPageQueryKey;
         }
        else if(subsystemKey.equals(SubsystemKeys.PUR)) {
             pageQuery = (PageQuery)session.getAttribute(WebKeys.PURPageQueryKey +
                                                       funcCode);
             sessionKey = WebKeys.PURPageQueryKey;
         }
        else if(subsystemKey.equals(SubsystemKeys.COS)) {
             pageQuery = (PageQuery)session.getAttribute(WebKeys.COSPageQueryKey +
                                                       funcCode);
             sessionKey = WebKeys.COSPageQueryKey;
         }
       else if(subsystemKey.equals(SubsystemKeys.QMS)) {
             pageQuery = (PageQuery)session.getAttribute(WebKeys.QMSPageQueryKey +
                                                       funcCode);
             sessionKey = WebKeys.QMSPageQueryKey;
         }
       else if(subsystemKey.equals(SubsystemKeys.EQP)) {
       	pageQuery = (PageQuery)session.getAttribute(WebKeys.EQPPageQueryKey +
       			funcCode);
       	sessionKey = WebKeys.EQPPageQueryKey;
       }
        
       
       
       
       else
            throw new Exception(
                "DBOperators.getPageQuery(): 没有定义子系统使用的数据对象键值！");

        sessionKey += funcCode;

        if(pageQuery==null) {
            pageQuery = new PageQuery(sessionCode, subsystemKey);
            session.setAttribute(sessionKey, pageQuery);
        }

        return pageQuery;
    }

}