package com.huiton.cerp.sam;

/**
 * Title:        公司定义
 * Description:  公司定义
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      BRITC
 * @author 张爱军
 * @version 1.0
 */

import com.huiton.cerp.pub.util.*;
import com.huiton.cerp.pub.util.functions.*;
import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.exceptions.DuplicateRecordException;
import com.huiton.mainframe.control.web.CustomerWebImpl;
import com.huiton.mainframe.control.web.ModelManager;
import com.huiton.mainframe.control.web.handlers.RequestHandlerSupport;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.pub.dbx.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SAM2100Handler extends RequestHandlerSupport
{

    public SAM2100Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM2100Handler: processRequest()");
        HttpSession session = request.getSession();
        ModelManager mm = (ModelManager)session.getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();

        String opFlag = null; //operation flag
        String outFlag = "1"; // outcome flag, 1 means succeeds
        Vector vct = null; //保存记录
        Vector sysVct = null ;//保存币种
        String sql = null;

        ResultSet rs = null;
        String strFields = null;
        String strTables = null;
        String strConditions = null;

        opFlag = request.getParameter("opFlag");
        opFlag = (opFlag==null ? "" : opFlag.trim());

        strConditions = " company_code='" + companyCode + "'";
        strFields = "company_code_father,company_name,tel,fax,mail,addr,"
          + "company_name_brief,gm,cfo,bank_name,post_code,currency_code,"
          + "bank_account,dns,url,company_name_en,addr_en,company_name_brief_en";

        strTables = "sam_company";

        Debug.println("----opFlag=" + opFlag);

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode,SubsystemKeys.SAM, "SAM2100Handler");

            if (opFlag.equals("update"))
            {
              String company_code_father = request.getParameter("company_code_father");
              String company_name = request.getParameter("company_name");
              String tel = request.getParameter("tel");
              String fax = request.getParameter("fax");
              String mail = request.getParameter("mail");
              String addr = request.getParameter("addr");
              String company_name_brief = request.getParameter("company_name_brief");
              String gm = request.getParameter("gm");
              String cfo = request.getParameter("cfo");
              String bank_name = request.getParameter("bank_name");
              String post_code = request.getParameter("post_code");
              String currency_code = request.getParameter("currency_code");
              String bank_account = request.getParameter("bank_account");
              String dns = request.getParameter("dns");
              String url = request.getParameter("url");
              String company_name_en = request.getParameter("company_name_en");
              String addr_en = request.getParameter("addr_en");
              String company_name_brief_en = request.getParameter("company_name_brief_en");

              company_code_father = Show.getString(company_code_father);
              company_name = Show.getString(company_name);
              tel = Show.getString(tel);
              fax = Show.getString(fax);
              mail = Show.getString(mail);
              addr = Show.getString(addr);
              company_name_brief = Show.getString(company_name_brief);
              gm = Show.getString(gm);
              cfo = Show.getString(cfo);
              bank_name = Show.getString(bank_name);
              post_code = Show.getString(post_code);
              currency_code = Show.getString(currency_code);
              bank_account = Show.getString(bank_account);
              dns = Show.getString(dns);
              url = Show.getString(url);
              company_name_en = Show.getString(company_name_en);
              addr_en = Show.getString(addr_en);
              company_name_brief_en = Show.getString(company_name_brief_en);

              sql = "update " + strTables
                + " set company_code_father='" + company_code_father
                + "',company_name='" + company_name
                + "',tel='" + tel
                + "',fax='" + fax
                + "',mail='" + mail
                + "',addr='" + addr
                + "',company_name_brief='" + company_name_brief
                + "',gm='" + gm
                + "',cfo='" + cfo
                + "',bank_name='" + bank_name
                + "',post_code='" + post_code
                + "',currency_code='" + currency_code
                + "',bank_account='" + bank_account
                + "',dns='" + dns
                + "',url='" + url
                + "',company_name_en='" + company_name_en
                + "',addr_en='" + addr_en
                + "',company_name_brief_en='" + company_name_brief_en + "'"
                + " where company_code='" + companyCode + "'";

              if (!pageQuery.simpleUpdate(sql))
                outFlag = "0" ;

            }
            //else //modify
            {
              pageQuery.getData(strFields,strTables,strConditions,1);
              vct = pageQuery.dividePage(1);
              pageQuery.getData("currency_code,currency_name","sam_currency",
                "company_code='" + companyCode + "'",100);
              sysVct = pageQuery.dividePage(1,100);
            }
        }
        catch (Exception e)
        {
          e.printStackTrace();
          outFlag = "0";
        }

        vct = (vct==null ? new Vector() : vct);
        sysVct = (sysVct==null ? new Vector() : sysVct);
        request.setAttribute("vct",vct);
        request.setAttribute("sysVct",sysVct);
        request.setAttribute("opFlag",opFlag);
        request.setAttribute("outFlag",outFlag);
        request.setAttribute("companyCode",companyCode);

        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}