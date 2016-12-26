package com.huiton.cerp.sam;

/**
 * Title:        用户密码修改
 * Description:  用户密码修改
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      HUITON
 * @author       cameran2003-7-31
 * @version 1.0
 */

import com.huiton.cerp.pub.util.*;
import com.huiton.cerp.pub.util.functions.*;
import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.exceptions.DuplicateRecordException;
import com.huiton.mainframe.control.web.CustomerWebImpl;
import com.huiton.mainframe.control.web.ModelManager;
import com.huiton.mainframe.control.web.handlers.RequestHandlerSupport;
import com.huiton.mainframe.control.web.handlers.*;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.pub.dbx.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SAM2700Handler extends RequestHandlerSupport
{

    public SAM2700Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM2700Handler: processRequest()");
        HttpSession session = request.getSession();
        ModelManager mm = (ModelManager)session.getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String userName = customer.getUserName();
        String lang = customer.getLanguage();

        String opFlag = null; //operation flag
        String outFlag = "1"; // outcome flag, 1 means succeeds
        String sql = null;

        ResultSet rs = null;
        String strFields = null;
        String strTables = null;
        String strConditions = null;

        opFlag = request.getParameter("opFlag");
        opFlag = (opFlag==null ? "query" : opFlag.trim());

        String user_code = request.getParameter("user_code");
        String user_pass="";

        //get user info
        String currentPage = request.getParameter("currentPage");
        String pageCount = request.getParameter("pageCount");

        currentPage = Show.getString(currentPage);
        pageCount = Show.getString(pageCount);


        strConditions = "company_code='" + companyCode
          + "' and user_unique_no='" + userUniqueNo + "'" ;

        strTables = "sam_user_auth" ;
        strFields = "user_code,user_pass" ;

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode, SubsystemKeys.SAM, "SAM2700Handler");


            if (opFlag.equals("query")) //修改用户认证
            {
              sql = "select " + strFields
                + " from " + strTables
                + " where " + strConditions ;

              Debug.println(sql) ;
              rs = pageQuery.getData(sql);
              if (rs!=null&&rs.next())
              {
                user_code = rs.getString(1);
                user_pass = rs.getString(2);

                user_code = Show.getString(user_code);
                user_pass = Show.getString(user_pass);

                request.setAttribute("user_code",user_code);
                request.setAttribute("user_pass",user_pass);

              }

            }
            else if (opFlag.equals("update")) //更新用户认证
            {
              user_pass = request.getParameter("user_pass");

              sql = "update sam_user_auth"
                  +" set user_pass = '"+user_pass+"'"
                  + " where " + strConditions ;
              if(!pageQuery.simpleUpdate(sql))
                {outFlag= "0";}
              else
              { outFlag="2";  //用于提示用户，密码修改成功

              }

              request.setAttribute("user_pass",user_pass);

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            outFlag = "0";
        }

        request.setAttribute("opFlag",opFlag);
        request.setAttribute("user_name",userName);
        request.setAttribute("user_code",user_code);
        request.setAttribute("user_unique_no",userUniqueNo);
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("outFlag",outFlag);

        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}