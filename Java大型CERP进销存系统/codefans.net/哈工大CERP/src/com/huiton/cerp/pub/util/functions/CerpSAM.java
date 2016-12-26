package com.huiton.cerp.pub.util.functions;

/*
    author : zaj
    date : 2001/12/30
    program : CerpSAM
    说明：Cerp系统管理类，主要用于处理用户登陆，认证
*/

import java.util.*;
import java.sql.*;
import javax.servlet.http.*;
import com.huiton.mainframe.util.*;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.mainframe.control.web.ModelManager;
import com.huiton.mainframe.control.web.CustomerWebImpl;
import com.huiton.pub.dbx.*;
import com.huiton.cerp.pub.util.SubsystemKeys;

public class CerpSAM
{
    private static final int times = 3; // times try to get pageQuery
    private static final int timeout = 30; // default timeout minutes

    private boolean  hasObject = false; // flag of initialized pageQuery
    private PageQuery pageQuery = null;
    private ResultSet rs = null;
    private String sql = null;

    public CerpSAM()
    {
       created();
    }

    // new instance
    public static CerpSAM newInstance()
    {
        CerpSAM cs = new CerpSAM() ;
        return cs ;
    }

    // create pageQuery if not initialized
    private boolean created()
    {
        try
        {
            for (int i=0; i<times && !hasObject ;i++)
            {
                pageQuery = new PageQuery("","sam");
                hasObject = true;
            }
            return hasObject;
        }
        catch (Exception e)
        {
            return hasObject;
        }
    }

    // get timeout of the company
    private int getTimeout(String companyCode)
    {
        if (!created())
            return timeout ;

        try
        {
            sql = "select time_out from scg_run_info "
                + " where company_code='" +companyCode+ "'";

            rs = pageQuery.getData(sql);

            if (rs != null && rs.next())
            {
                int time_out = rs.getInt(1);
                if (time_out>0)
                    return time_out;
            }
            return timeout;
        }
        catch (Exception e)
        {
            return timeout ;
        }

    }

    // max number of users of the Company
    private int getMaxCompanyUser(String companyCode)
    {
        return 100;
    }

    // current online company  user
    private int getCurrentCompanyUser(String companyCode)
    {
        if (!created())
            return 0 ;

        try
        {
            sql = "select count(*) from sam_session "
                + " where company_code='" +companyCode + "'";

            rs = pageQuery.getData(sql);

            if (rs != null && rs.next())
            {
                return rs.getInt(1);
            }
            return 0;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    // current online users using this account
    private int getCurrentTheUser(String companyCode,String userCode)
    {
        if (!created())
            return 0 ;

        try
        {
            sql = "select count(*) from sam_session "
                + " where company_code='" +companyCode
                + "' and user_code='" +userCode+ "'";

            rs = pageQuery.getData(sql);

            if (rs != null && rs.next())
            {
                return rs.getInt(1);
            }
            return 0;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    private boolean acceptIP(String standardIP,String clientIP)
    {
        standardIP = (standardIP==null ? "" : standardIP.trim());
        clientIP = (clientIP==null ? "" : clientIP.trim());

        if (standardIP.length()<1) // no restriction
            return true ;

        if (standardIP.equals(clientIP))
            return true ;

        StringTokenizer standardST = new StringTokenizer(standardIP,".");
        StringTokenizer clientST = new StringTokenizer(clientIP,".");

        while (standardST.hasMoreElements())
        {
            String sPart = (String)standardST.nextElement();
            sPart = (sPart==null ? "" : sPart.trim());
            if (clientST.hasMoreElements())
            {
                String cPart = (String)clientST.nextElement();
                cPart = (cPart==null ? "" : cPart.trim());

                if (sPart.equals("*"))
                    continue ;

                if (!sPart.equalsIgnoreCase(cPart))
                    return false ;

            }else
            {
                return false ;
            }
        }
        return true ;

    }

    //check password
    private boolean m_equals(String standPass,String userPass)
    {
        standPass = (standPass==null ? "" : standPass.trim());
        userPass = (userPass==null ? "" : userPass.trim());
        return standPass.equals(userPass);
    }

    // login process
    public boolean login(HttpServletRequest request,String companyCode,String userCode,String password,String year)
    {
        if (!created())
            return false ;

        try
        {
            sql = "SELECT user_status,ip_addr,permit_num,user_pass, user_unique_no"
                + " FROM sam_user_auth "
                + " WHERE user_code = '" + userCode
                + "' and company_code = '" + companyCode + "'";

            rs = pageQuery.getData(sql);

            // check
            if(rs!=null&&rs.next())
            {

                // get user status
                String userStatus = rs.getString(1);
                userStatus = (userStatus==null ? "" : userStatus.trim());
                userStatus = (userStatus.equals("") ? "Y" : userStatus);
                Debug.println("user_status=" + userStatus);

                // get ip addr
                String ipAddr = rs.getString(2);

                // get permit num
                int permitNum = rs.getInt(3);

                // password
                String userPass = rs.getString(4);

                // userUniqueNo
                String userUniqueNo = rs.getString(5);

                // check company users' number
                if (getMaxCompanyUser(companyCode) <= getCurrentCompanyUser(companyCode))
                {
                    request.setAttribute("flag",LoginStatusKeys.COMPANY_USER_OVERFLOW);
                    return false ;
                }

                //check user status
                if (!userStatus.equalsIgnoreCase("Y"))
                {
                    request.setAttribute("flag",LoginStatusKeys.STATUS_FORBID);
                    return false ;
                }

                //check ip_addr
                Debug.println("standardIP=" + ipAddr + ",clientIP=" + request.getRemoteAddr());

                if (!acceptIP(ipAddr,request.getRemoteAddr()))
                {
                    request.setAttribute("flag",LoginStatusKeys.IP_FORBID);
                    return false ;
                }

                //check user permit_num;
                Debug.println("permitNum=" + permitNum);
                if (permitNum>0 &&
                  permitNum <= getCurrentTheUser(companyCode,userCode))
                {
                    request.setAttribute("flag",LoginStatusKeys.USER_OVERFLOW);
                    return false ;
                }

                // get pass to check
                if (m_equals(userPass,password))
                {
                    // 后续处理
                    CommonDate today = new CommonDate();
                    int visitTime = (int)(System.currentTimeMillis()/60000);
                    int loginTime = visitTime;

                    LookField lf = new LookField("",SubsystemKeys.SAM);

                    sql = "company_code = '" + companyCode +
                            "' AND user_unique_no = '" + userUniqueNo + "'";

                    // get userName
                    String userName = lf.dLookup("body_name",
                        "epd_address_book",sql);
                    Debug.println("XXXXXXXXXXXXXXXXXXXXXX"+userName);
                    //get user 部门
                    //以下关于部门的函数由cameran添加，用于提取用户所在部门代码2003-6-1
                    String userDept = lf.dLookup("dept_code",
                            "epd_address_book", sql);
                    userDept = (userDept==null ? "" : userDept.trim());

                    String userDeptName =lf.dLookup("dept_name",
                            "sam_dept", "dept_code = '"+userDept+"'");
                    userDeptName = (userDeptName==null ? "" : userDeptName.trim());
                    Debug.println("XXXXXXXXXXXXXXXXXXXXXX"+userDept);
                    //以上关于部门的函数由cameran添加，用于提取用户所在部门代码2003-6-1

                    // get user 语言
                    String language = lf.dLookup("language_type",
                            "sam_user_info", sql);

                    language = (language==null ? "" : language.trim());
                    language = (language.equals("") ? "zh" : language);

                    // get defaultpage
                    String defaultPage = lf.dLookup("default_page", "sam_user_info", sql);

                    // get log no and insert into sam_access_log
                    int logNo = 0;

                    do
                    {
                        logNo = CerpGetNo.getNo(companyCode,"sam_access_log");

                        sql = "insert into sam_access_log(company_code,"
                            + "log_no, user_unique_no, login_date, login_time,"
                            + "logout_datetime, online_minutes, ip_addr, user_name)"
                            + " values('" + companyCode + "',"
                            + logNo + ",'"
                            + userUniqueNo + "','"
                            + today.getYMD() + "','"
                            + today.getHM() + "','',0,'"
                            + request.getRemoteAddr() + "','"
                            + userName +"')";

                        Debug.println("sql="+sql);
                    }
                    while (!pageQuery.simpleUpdate(sql));

                    // get sessionCode and insert into sam_session
                    String sessionCode = null;

                    do
                    {
                        sessionCode = String.valueOf((long)(loginTime*java.lang.Math.random()*1000000));
                        sql = "insert sam_session(company_code,session_code,year, "
                            + " user_unique_no,user_code,user_ip_addr, "
                            + " visit_datetime, "
                            + " login_datetime, log_no) values('"
                            + companyCode + "','" + sessionCode + "','"
                            + year + "','" + userUniqueNo + "','" + userCode
                            + "','"+request.getRemoteAddr()+"', " + visitTime + ", "
                            + loginTime + ", " + logNo + ")";

                        Debug.println("sql="+sql);
                    }
                    while (!pageQuery.simpleUpdate(sql));

                    //更新用户权限和菜单
                    sql = "select prog_flag,menu_flag from sam_user_info "
                      + " where company_code='" + companyCode
                      + "' and user_unique_no='" + userUniqueNo + "'" ;

                    rs = pageQuery.getData(sql);
                    if (rs!=null && rs.next())
                    {
                      SamUserRight sur = new SamUserRight(companyCode,
                        userUniqueNo,rs.getString(1),rs.getString(2));

                      sur.update();
                    }

                    // set session
                    HttpSession session = request.getSession();
                    session.setAttribute("default_page", defaultPage);
                    session.setAttribute("session_code",sessionCode);
			        session.setAttribute("company_code",companyCode);
			        session.setAttribute("user_code",userCode);
                    session.setAttribute("user_name",userName);
                    session.setAttribute("user_unique_no",userUniqueNo);
			        session.setAttribute("language",language);
			        session.setAttribute("year",year);
                    session.setAttribute("mSysCode","sam");
                    session.setAttribute("mProgCode","");

                    // 设置当前用户状态 set customer
                    ModelManager mm = (ModelManager) session.getAttribute("mm");
                    CustomerWebImpl customer = mm.getCustomerWebImpl();
                    customer.setUserId(userCode);
                    customer.setCompanyCode(companyCode);
                    customer.setCurrentYear(year);
                    customer.setSessionCode(sessionCode);
                    customer.setUserUniqueNo(userUniqueNo);
                    customer.setUserName(userName);
                    //以下关于部门的函数由cameran添加，用于提取用户所在部门代码2003-6-1
                    customer.setUserDept(userDept);//added by cameran
                    customer.setUserDeptName(userDeptName);//added by cameran
                    //以上关于部门的函数由cameran添加，用于提取用户所在部门代码2003-6-1
                    customer.setLanguage(language);
                    customer.setLoggedIn(true);
                    customer.setDefaultPage(defaultPage);

                    //succeed
                    request.setAttribute("flag",LoginStatusKeys.SUCCEED);
                    return true  ;

                }else // password does not match
                {
                    request.setAttribute("flag",LoginStatusKeys.WRONG_PASSWORD);
                    return false ;
                }
            }else // no this user
            {
                request.setAttribute("flag",LoginStatusKeys.NO_USER);
                return false ;
            }
        }
        catch (Exception e)
        {
            request.setAttribute("flag",LoginStatusKeys.DB_ERROR);
            return false ;
        }

    }

    // log out
    public synchronized void logout(String companyCode,String sessionCode)
    {
        if (!created())
            return ;

        try
        {
            CommonDate today = new CommonDate();
            int curTime = (int)(System.currentTimeMillis()/60000);

            int pageSize = 1000;
            int time_out = getTimeout(companyCode);

            String strFields = "login_datetime,log_no";
            String strTables = "sam_session";
            String strConditions = "company_code='" + companyCode
                + "' and (session_code='" + sessionCode
                + "' or visit_datetime<="+(curTime-time_out) + ")";

            rs = pageQuery.getData(strFields,strTables,strConditions,pageSize);

            Vector vct = pageQuery.dividePage(1,pageSize);
            Debug.println("vct==null" + (vct==null) + ",vct.size()=" + vct.size());

            if (vct != null)
            {
                int login_datetime = 0;
                int visit_datetime = 0;
                int log_no = 0;
                int online_minutes = 0;
                String mySql = null;

                String logout_datetime = today.getYMD()+ " " + today.getHM();

                for(int i=0;i<vct.size();i++)
                {
                    String[] value = (String[]) vct.elementAt(i);
                    login_datetime = Integer.parseInt(value[0]);
                    log_no = Integer.parseInt(value[1]);

                    online_minutes = curTime - login_datetime;

                    mySql = "update sam_access_log "
                        + " set online_minutes=" + online_minutes
                        + ", logout_datetime='" + logout_datetime
                        + "' where company_code='" + companyCode
                        + "' and log_no=" + log_no;

                    Debug.println("mySql="+mySql);
                    pageQuery.simpleUpdate(mySql);
                }
            }

            // delete session
            sql = "delete from sam_session "
                + " where company_code='" + companyCode
                + "' and (session_code='" + sessionCode
                + "' or visit_datetime<="+(curTime-time_out) + ")";

            Debug.println(sql);
            pageQuery.simpleUpdate(sql);
        }
        catch (Exception e)
        {

        }
    }

    // to inhance efficiency, you can modify this method to get info from some class
    private int getLogProgLevel(String companyCode)
    {
        if (!created())
            return 0 ;

        try
        {
            sql = "select log_prog_level from scg_run_info "
                + " where company_code='" + companyCode + "'" ;

            rs = pageQuery.getData(sql);

            if (rs != null && rs.next())
            {
                return rs.getInt(1);
            }
            return 0;
        }
        catch (Exception e)
        {
            return 0 ;
        }
    }

    // get this prog's level
    private int getProgLevel(String companyCode,String sysCode,String progCode)
    {
        if (!created())
            return 0 ;

        try
        {
            sql = "select prog_level from scg_program "
                + " where company_code='" + companyCode
                + "' and sys_code='" + sysCode
                + "' and prog_code='" + progCode + "'" ;

            rs = pageQuery.getData(sql);

            if (rs != null && rs.next())
            {
                return rs.getInt(1);
            }
            return 0;
        }
        catch (Exception e)
        {
            return 0 ;
        }
    }

    // log prog visited
    private void  m_logProg(String companyCode,String userUniqueNo,String sysCode,String progCode)
    {
        if (!created())
            return ;

        try
        {
            CommonDate today = new CommonDate();

            sql = "insert into sam_prog_log(company_code,log_no,sys_code,prog_code,"
                    + " user_unique_no,run_date,run_time,sys_name,prog_name,prog_value,"
                    + " user_name) select '"
                    + companyCode + "'," + CerpGetNo.getNo(companyCode,"sam_prog_log")
                    + ", '" + sysCode + "','" + progCode + "','" + userUniqueNo
                    + "','" + today.getYMD() + "','" + today.getHM()
                    + "',sys.sys_name,prog.prog_name_cn,prog.prog_value,body.body_name "
                    + " from scg_system sys,scg_program prog,epd_address_book body "
                    + " where sys.sys_code='" + sysCode + "' and prog.sys_code='"
                    + sysCode + "' and prog.prog_code='" + progCode
                    + "' and body.user_unique_no='" + userUniqueNo + "'" ;

            Debug.println("sql=" + sql);
            pageQuery.simpleUpdate(sql);

            return ;
        }
        catch (Exception e)
        {
            return ;
        }
    }

    // decide log it or not
    public void logProg(String companyCode,String userUniqueNo,String sysCode,String progCode)
    {
        if (getProgLevel(companyCode,sysCode,progCode)>=getLogProgLevel(companyCode))
            m_logProg(companyCode,userUniqueNo,sysCode,progCode);

        return ;
    }

    // has session , refresh session
    public boolean hasSession(String sessionCode)
    {
        try
        {
            int curTime = (int)(System.currentTimeMillis()/60000);

            sql = "select 1 from sam_session "
                + " where session_code='" + sessionCode + "'" ;

            rs = pageQuery.getData(sql);
            if (rs != null && rs.next())
            {
                refreshSession(sessionCode);
                return true ;
            }

            return false ;
        }
        catch (Exception e)
        {
            return false ;
        }
    }

    // refresh session
    private void refreshSession(String sessionCode)
    {

        try
        {
            int curTime = (int)(System.currentTimeMillis()/60000);

            sql = "update sam_session set visit_datetime="
                + curTime + " where session_code='" + sessionCode + "'" ;

            pageQuery.simpleUpdate(sql);
        }
        catch (Exception e)
        {}
    }


    /**从库中取出子系统代码，程序代码，程序参数
     * @param progValue
     * @param companyCode
     * @return string[3]*/
    public String[] getSysAndProgCode(String progValue,String companyCode)
	{
		String [] value = new String[3];

        try
        {
		    sql = "select sys_code,prog_code,prog_params "
			   + " from scg_program "
			   + " where company_code='"+companyCode
			   + "' and prog_value='"+progValue+"'";

		    rs = pageQuery.getData(sql);
		    if (rs != null && rs.next())
		    {
			    value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
			}
        }catch(Exception e)
        {
        }
        return value;
	}

    // whether has right
    public boolean hasRight(String sysCode,String progCode,String companyCode,
      String userUniqueNo)
	{
        try
        {
            sql = "select check_right,prog_code_father from scg_program "
                + " where company_code='" + companyCode
                + "' and sys_code='" + sysCode
                + "' and prog_code='" + progCode + "'";

            rs = pageQuery.getData(sql);
		    if (rs != null && rs.next())
		    {
                String checkRight = rs.getString(1);
                String progCodeFather = rs.getString(2);

                checkRight = (checkRight==null?"":checkRight.trim());
                progCodeFather =
                  (progCodeFather==null?"":progCodeFather.trim());

                //无须授权
                if (checkRight.equals("N"))
                    return true ;

                //须授权
                if (progCodeFather.length()<1) //无父
                {
                    sql = "select 1 from sam_user_prog_right "
                        + " where company_code='" + companyCode
                        + "' and sys_code='" + sysCode
                        + "' and prog_code='" + progCode
                        + "' and user_unique_no='" + userUniqueNo + "'";

                    rs = pageQuery.getData(sql);
                    if (rs!=null && rs.next())
                        return  true  ;

                    return false ;
                }
                else //有父
                {
                    sql = "select 1 from sam_user_prog_right "
                        + " where company_code='" + companyCode
                        + "' and sys_code='" + sysCode
                        + "' and prog_code='" + progCodeFather
                        + "' and user_unique_no='" + userUniqueNo + "'";

                    rs = pageQuery.getData(sql);
                    if (rs!=null && rs.next())
                        return  true  ;

                    sql = "select 1 from scg_program "
                        + " where company_code='" + companyCode
                        + "' and sys_code='" + sysCode
                        + "' and prog_code='" + progCodeFather
                        + "' and check_right='N'";

                    rs = pageQuery.getData(sql);
                    if (rs!=null && rs.next())
                        return  true  ;

                    return  false  ;
                }
            }
            else
            {
                return true ;
            }
        }
        catch(Exception e)
        {
            return true ;
        }
	}

    // check right
    public boolean hasRight(String progValue,String companyCode,String userUniqueNo)
    {
        String [] value = getSysAndProgCode(progValue,companyCode);
        return hasRight(value[0],value[1],companyCode,userUniqueNo);
    }
}