package com.huiton.cerp.sam;

/**
 * Title:        用户认证
 * Description:  用户认证
 * Copyright:    Copyright  Reserved By BRITC
 * Company:      BRITC
 * @author
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

public class SAM3340Handler extends RequestHandlerSupport
{

    public SAM3340Handler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SAM3340Handler: processRequest()");
        HttpSession session = request.getSession();
        ModelManager mm = (ModelManager)session.getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String lang = customer.getLanguage();

        String opFlag = null; //operation flag
        String outFlag = "1"; // outcome flag, 1 means succeeds
        String sql = null;

        ResultSet rs = null;
        String strFields = null;
        String strTables = null;
        String strConditions = null;

        opFlag = request.getParameter("opFlag");
        opFlag = (opFlag==null ? "" : opFlag.trim());
        //opFlag = (opFlag.length()<1 ? "" : opFlag);

        //get user info
        String body_code = request.getParameter("body_code");
        String body_name = request.getParameter("body_name");
        String user_unique_no = request.getParameter("user_unique_no");

        String bak_bodyName = request.getParameter("bak_bodyName");
        String bak_bodyCode = request.getParameter("bak_bodyCode");
        String bak_currentPage = request.getParameter("bak_currentPage");
        String bak_pageCount = request.getParameter("bak_pageCount");

        body_code = Show.getString(body_code);
        body_name = Show.getString(body_name);
        user_unique_no = Show.getString(user_unique_no);
        bak_currentPage = Show.getString(bak_currentPage);
        bak_pageCount = Show.getString(bak_pageCount);
        bak_bodyName = Show.getString(bak_bodyName);
        bak_bodyCode = Show.getString(bak_bodyCode);


        strConditions = "company_code='" + companyCode
          + "' and user_unique_no='" + user_unique_no + "'" ;

        strTables = "sam_user_auth" ;
        strFields = "user_code,user_pass,user_status,ip_addr,permit_num,"
          + "pass_mendable" ;

        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode, SubsystemKeys.SAM, "SAM3340Handler");

            if (opFlag.equals("addNew")) //新增用户认证
            {

            }
            else if (opFlag.equals("insert")||opFlag.equals("insMore")) //插入用户认证
            {
              String user_code = request.getParameter("user_code");
              String user_pass = request.getParameter("user_pass");
              String user_role = request.getParameter("user_role");
              String user_status = request.getParameter("user_status");
              String ip_addr = request.getParameter("ip_addr");
              String permit_num = request.getParameter("permit_num");
              String pass_mendable = request.getParameter("pass_mendable");

              user_code = Show.getString(user_code);
              user_pass = Show.getString(user_pass);
              user_role = Show.getString(user_role);
              user_status = Show.getString(user_status);
              ip_addr = Show.getString(ip_addr);
              permit_num = Show.getString(permit_num);
              pass_mendable = Show.getString(pass_mendable);

              if (permit_num.length()<1)
                permit_num = "0" ;

              if (user_status.length()<1)
                user_status = "Y" ;

              if (pass_mendable.length()<1)
                pass_mendable = "Y" ;

              sql = "insert into sam_user_auth(company_code,user_unique_no,"
                + "user_code,user_pass,user_status,ip_addr,permit_num,"
                + "pass_mendable) values('" + companyCode
                + "','" + user_unique_no + "','" + user_code
                + "','" + user_pass + "','" + user_status
                + "','" + ip_addr + "'," + permit_num
                + ",'" + pass_mendable + "')";

              pageQuery.addBatchSql(sql);

              sql = "insert into sam_user_info(company_code,user_unique_no,"
                + "prog_flag,menu_flag) " + " values('"
                + companyCode + "','" + user_unique_no +"','N','N')";

              pageQuery.addBatchSql(sql);

              sql = "update epd_address_book set auth_flag='Y' "
                + " where company_code='" + companyCode
                + "' and user_unique_no='" + user_unique_no + "'" ;

              pageQuery.addBatchSql(sql);

              //插入到sam_user_role表中，更新用户的角色信息
              int dimma_pos;
              String role_code;
              while(user_role.length()>0)
              {
                dimma_pos = user_role.indexOf(',');
                if(dimma_pos==-1)
                  role_code = user_role;
                else
                  role_code = user_role.substring(0,dimma_pos);

                sql = "insert into sam_user_role(company_code,user_unique_no,role_code) values('"+
                      companyCode+"','"+user_unique_no+"','"+role_code+"')";
                Debug.println(sql);
                pageQuery.addBatchSql(sql);

                if(dimma_pos==-1)
                  break;
                user_role = user_role.substring(dimma_pos+1,user_role.length());
              }

              pageQuery.exeBatchSql();

            }
            else if (opFlag.equals("modify")) //修改用户认证
            {
              sql = "select " + strFields
                + " from " + strTables
                + " where " + strConditions ;

              rs = pageQuery.getData(sql);
              if (rs!=null&&rs.next())
              {
                String user_code = rs.getString(1);
                String user_pass = rs.getString(2);
                String user_status = rs.getString(3);
                String ip_addr = rs.getString(4);
                String permit_num = rs.getString(5);
                String pass_mendable = rs.getString(6);

                user_code = Show.getString(user_code);
                user_pass = Show.getString(user_pass);
                user_status = Show.getString(user_status);
                ip_addr = Show.getString(ip_addr);
                permit_num = Show.getString(permit_num);
                pass_mendable = Show.getString(pass_mendable);

                request.setAttribute("user_code",user_code);
                request.setAttribute("user_pass",user_pass);
                request.setAttribute("user_status",user_status);
                request.setAttribute("ip_addr",ip_addr);
                request.setAttribute("permit_num",permit_num);
                request.setAttribute("pass_mendable",pass_mendable);
              }

              String user_role="";
              sql = "select * from sam_user_role where " + strConditions;
              Debug.println(sql);
              rs = pageQuery.getDataNew(sql);
              while(rs!=null && rs.next())
              {
                if(user_role.length()>0)
                  user_role += ",";
                String role_code = rs.getString("role_code");
                role_code = Show.getString(role_code);
                user_role += role_code;
              }
              request.setAttribute("user_role",user_role);
            }
            else if (opFlag.equals("update") || opFlag.equals("updMore")) //更新用户认证
            {
              String user_code = request.getParameter("user_code");
              String user_pass = request.getParameter("user_pass");
              String user_role = request.getParameter("user_role");
              String user_status = request.getParameter("user_status");
              String ip_addr = request.getParameter("ip_addr");
              String permit_num = request.getParameter("permit_num");
              String pass_mendable = request.getParameter("pass_mendable");

              user_role = Show.getString(user_role);
              user_code = Show.getString(user_code);
              user_pass = Show.getString(user_pass);
              user_status = Show.getString(user_status);
              ip_addr = Show.getString(ip_addr);
              permit_num = Show.getString(permit_num);
              pass_mendable = Show.getString(pass_mendable);

              if (permit_num.length()<1)
                permit_num = "0" ;

              if (user_status.length()<1)
                user_status = "Y" ;

              if (pass_mendable.length()<1)
                pass_mendable = "Y" ;

              sql = "update sam_user_auth set "
                + "user_code='" + user_code
                + "',user_pass='" + user_pass
                + "',user_status='" + user_status
                + "',ip_addr='" + ip_addr
                + "',permit_num=" + permit_num
                + ",pass_mendable='" + pass_mendable + "'"
                + " where company_code='" + companyCode
                + "' and user_unique_no='" + user_unique_no + "'" ;
              Debug.println(sql);

              pageQuery.addBatchSql(sql);

              sql = "delete from sam_user_role where company_code='"+companyCode+
                    "' and user_unique_no='"+user_unique_no+"'";
              Debug.println(sql);
              pageQuery.addBatchSql(sql);

              //sam_user_role表中，更新用户的角色信息
              int dimma_pos;
              String role_code;
              while(user_role.length()>0)
              {
                dimma_pos = user_role.indexOf(',');
                if(dimma_pos==-1)
                  role_code = user_role;
                else
                  role_code = user_role.substring(0,dimma_pos);

                sql = "insert into sam_user_role(company_code,user_unique_no,role_code) values('"+
                      companyCode+"','"+user_unique_no+"','"+role_code+"')";
                Debug.println(sql);
                pageQuery.addBatchSql(sql);

                if(dimma_pos==-1)
                  break;
                user_role = user_role.substring(dimma_pos+1,user_role.length());
              }

              pageQuery.exeBatchSql();
            }
            else
            {

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            outFlag = "0";
        }

        request.setAttribute("opFlag",opFlag);
        request.setAttribute("body_name",body_name);
        request.setAttribute("body_code",body_code);
        request.setAttribute("user_unique_no",user_unique_no);
        request.setAttribute("bak_currentPage",bak_currentPage);
        request.setAttribute("bak_pageCount",bak_pageCount);
        request.setAttribute("bak_bodyName",bak_bodyName);
        request.setAttribute("bak_bodyCode",bak_bodyCode);

        Debug.println("process handler over,outFlag="+outFlag);
        return null;
    }
}