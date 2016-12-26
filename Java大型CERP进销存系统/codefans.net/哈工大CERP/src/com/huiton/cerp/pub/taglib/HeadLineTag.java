package com.huiton.cerp.pub.taglib;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.huiton.mainframe.control.web.Parameter;
import com.huiton.mainframe.util.WebKeys;
import com.huiton.mainframe.control.web.*;
import com.huiton.mainframe.util.tracer.Debug;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import com.huiton.pub.dbx.*;
import java.sql.*;
import javax.servlet.ServletRequest;

/**
 * This class is an easy interface to the JSP template or other
 * text that needs to be inserted.
 * author : zaj
 * date :2001/12/20
 * parameters :
 * needed context: pSysCode,pProgCode by request.getParameter()
 *      mmSysCode,mmProgCode from request.getAttribute();
 *      pProgName_en,pProgName_zh from session
 */

public class HeadLineTag extends TagSupport
{

    private JdbOp op = null ; //数据库操作对象
    private static int times = 3; //尝试创建的次数

    private boolean  created()
    {
        if (op != null)
            return true ;

        try
        {
            for (int i=0;i<times;i++)
            {
                op = new JdbOp("","sam");
                return true ;
            }
        }
        catch(Exception e)
        {

        }
        return false ;
    }

    /**
     * default constructor
     */
    public HeadLineTag()
    {
        super();
        created();
    }

    public int doStartTag() throws JspTagException
    {
        try
	    {
            JspWriter out = pageContext.getOut();
            ServletRequest request = (ServletRequest) pageContext.getRequest();
            HttpSession session = pageContext.getSession();
            // 得到程序中文名 to get proper prog_name from db according to the lang

	        String lang = null;
	        String company_code = null;
            String sql = null;
            String prog_name = null;
            String user_unique_no = null;
            String menu_name = null;
            String menu_name_en = null;
            String prog_name_en = null;

	        //get lang,company_code from session
	        lang = (String) session.getAttribute("language");
	        company_code = (String) session.getAttribute("company_code");
            user_unique_no = (String)session.getAttribute("user_unique_no");

            Debug.println("lang="+lang+",company_code="+company_code);
	        // if get nothing,get it from customer

	        if (lang==null || lang.equals("") || company_code==null || company_code.equals("")|| user_unique_no==null || user_unique_no.equals(""))
	        {
		        ModelManager mm = (ModelManager)session.getAttribute("mm");
        	    CustomerWebImpl customer = mm.getCustomerWebImpl();
		        lang = customer.getLanguage();
		        company_code = customer.getCompanyCode();
                user_unique_no = customer.getUserUniqueNo();
	        }

            // set default or trim it
            lang = (lang==null ? "" : lang.trim());
            company_code = (company_code==null ? "" : company_code.trim());

            // if get nothing , return at once
            if (company_code.length()<1 )
            {
                return SKIP_BODY;
            }

            // get parameters from request now
	        String pSysCode = (String) request.getParameter("pSysCode");
	        String pProgCode = (String) request.getParameter("pProgCode");

            pSysCode = (pSysCode==null ? "" : pSysCode.trim());
            pProgCode = (pProgCode==null ? "" : pProgCode.trim());

            Debug.println("sys_code="+pSysCode+",prog_code="+pProgCode);

            if (pSysCode.length()<1 || pProgCode.length()<1)
            {
                if (lang.equalsIgnoreCase("en"))
                {
                    prog_name = (String) session.getAttribute("pProgName_en");
                }else
                {
                    prog_name = (String) session.getAttribute("pProgName_zh");
                }

                // decide if there's known name
                prog_name=(prog_name==null ? "" : prog_name.trim());

                //如果没有，直接取该程序的菜单/程序名称
                if (prog_name.length()<1)
                {
                    // get data from scg_program
                    String mSysCode = (String)session.getAttribute("mSysCode");
                    String mProgCode = (String)session.getAttribute("mProgCode");

                    sql = "select prog_name_cn,prog_name_en "
                            + " from scg_program "
                            + " where company_code='"+company_code
                            + "' and sys_code='"+mSysCode
                            + "' and prog_code='"+mProgCode+"'" ;

                    if (!created())
                        return  SKIP_BODY;

		            ResultSet rs = op.getData(sql);
		            if (rs != null && rs.next())
		            {
                        menu_name = rs.getString(1);
                        menu_name_en = rs.getString(2);
                        Debug.println("rs exists");
		            }else
		            {
			            Debug.println("no rs matched in the scg_program");
                        // set value to nothing
                        menu_name = "";
                        menu_name_en = "";
		            }

                    session.setAttribute("pProgName_zh",menu_name);
                    session.setAttribute("pProgName_en",menu_name_en);

                    if (lang.equalsIgnoreCase("en"))
                    {
                        prog_name = menu_name_en;
                    }else
                    {
                        prog_name = menu_name;
                    }
                }
            }else // get data from db
            {

                // get data from sam_user_menu first
                sql = "select menu_name_cn,menu_name_en "
                    + " from scg_main_menu "
                    + " where company_code='"+company_code
                    + "' and sys_code='"+pSysCode
                    + "' and menu_code='"+pProgCode+"'";

                Debug.println("sql="+sql);

                if (!created())
                        return  SKIP_BODY;

		        ResultSet rs = op.getData(sql);
		        if (rs != null && rs.next())
		        {
                    menu_name = rs.getString(1);
                    menu_name_en = rs.getString(2);
                    Debug.println("rs exists");
		        }else
		        {
			        Debug.println("rs doesn't exist");
                    // get data from scg_program

                    sql = "select prog_name_cn,prog_name_en "
                        + " from scg_program "
                        + " where company_code='"+company_code
                        + "' and sys_code='"+pSysCode
                        + "' and prog_code='"+pProgCode+"'" ;

                    rs = op.getData(sql);
		            if (rs != null && rs.next())
		            {
                        prog_name = rs.getString(1);
                        prog_name_en = rs.getString(2);

                        Debug.println("prog_name_cn="+prog_name
                          +",prog_name_en="+prog_name_en);

                        // set value to menu_name
                        menu_name = prog_name;
                        menu_name_en = prog_name_en;
                    }else
                    {
                        Debug.println("no rs matched in the scg_program");
                        // set value to nothing
                        menu_name = "";
                        menu_name_en = "";
                    }
		        }

                ModelManager mm1 = (ModelManager)session.getAttribute("mm");
        	    CustomerWebImpl customer1 = mm1.getCustomerWebImpl();

                session.setAttribute("pProgName_zh",menu_name);
                session.setAttribute("pProgName_en",menu_name_en);

                if (lang.equalsIgnoreCase("en"))
                {
                    prog_name = menu_name_en;
                }else
                {
                    prog_name = menu_name;
                }
            }
            out.println("<table width='98%' align='center' "
              + "cellspacing='1' cellpading='1'><tr><td height='10'></td>"
              + "</tr><tr><td><span class='contenttitle'>"+prog_name
              + "</span><br><img src='/com/huiton/cerp/pub/images/line2.gif' "
              + "width='250' height='5'></td></tr><tr><td height='15'></td>"
              + "</tr></table>");

        }catch(Exception e)
        {
		    // do nothing
	    }
	    return SKIP_BODY;
    }
}