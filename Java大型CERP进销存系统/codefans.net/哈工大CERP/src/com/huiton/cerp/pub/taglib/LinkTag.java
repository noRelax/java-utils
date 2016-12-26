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
import com.huiton.cerp.pub.util.functions.*;

/**
 * This class is an easy interface to the JSP template or other
 * text that needs to be inserted.
 */

public class LinkTag extends TagSupport
{

    private String m_href = null;
    private String m_sysCode = null;
    private String m_progCode = null;
    private String m_showName = null;
    private boolean hasQuest = false;
    private String m_target = null;
    private String m_progValue = null ;

    private CerpSAM cs = new CerpSAM();
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

    public LinkTag()
    {
        super();
    }

    public void setTarget(String target)
    {
        target = target.trim();
        if (target.length()>0)
        {
            m_target = target;
        }
    }

    public void setHref(String href)
    {
        int iLen = href.length();
        int iPosQuest =  href.indexOf("?");
        int iPosAnd = href.lastIndexOf("&");

        //trim last & first
        if (iPosAnd > -1) // &
        {
            if (iPosAnd == iLen)
            {
                href = href.substring(0,iLen-1);
                iLen--;
            }
        }

        // trim last ? then
        if (iPosQuest > -1) // ?
        {
            m_progValue = href.substring(0,iPosQuest-1);

            if (iPosQuest == iLen)
            {
                href = href.substring(0,iLen-1);
                iLen --;
            }else
            {
                hasQuest = true;
            }
        }
        m_href = href;

    }

    public void setSysCode(String sysCode)
    {
        sysCode = sysCode.trim();
        if (sysCode.length()>0)
        {
            m_sysCode = sysCode;
        }
    }

    public void setProgCode(String progCode)
    {
        progCode = progCode.trim();
        if (progCode.length()>0)
        {
            m_progCode = progCode;
        }
    }

    public void setShowName(String showName)
    {
        showName = showName.trim();
        if (showName.length()>0)
        {
            m_showName = showName;
        }
    }

    public int doStartTag() throws JspTagException
    {
    try
    {
            JspWriter out = pageContext.getOut();
            ServletRequest request = (ServletRequest) pageContext.getRequest();
            HttpSession session = pageContext.getSession();
            String strLink = null;
            ModelManager mm = (ModelManager) session.getAttribute("mm");
            CustomerWebImpl customer = mm.getCustomerWebImpl();
            String companyCode = customer.getCompanyCode();
            String userUniqueNo = customer.getUserUniqueNo();

            //判断权限
            if (!cs.hasRight(m_progValue,companyCode,userUniqueNo))
            {
              clearStatus();
              return  SKIP_BODY;
            }

            // 如有程序名，直接显示
        if (m_showName == null)
        {

            // 从菜单得到程序名
	        String lang = null;
	        String company_code = null;
            String sql = null;
            String prog_name = "prog_name_cn";
            String user_unique_no = null;
            String menu_name = "menu_name_cn";

	        lang = customer.getLanguage();
		    company_code = companyCode;
            user_unique_no = userUniqueNo;

            // if get nothing , return at once
            if (company_code.length()<1)
            {
              clearStatus();
              return  SKIP_BODY;
            }

            // check parameters now
            Debug.println("sys_code="+m_sysCode+",prog_code="+m_progCode);

            if (m_sysCode.length()<1 || m_progCode.length()<1)
            {
                m_showName = "";

            }else // get data from db
            {
                if (lang.equalsIgnoreCase("en"))
                {
                    menu_name = "menu_name_en";
                    prog_name = "prog_name_en";
                 }
                // get data from scg_main_menu first
                sql = "select " + menu_name
                    + " from scg_main_menu "
                    + " where company_code='"+company_code
                    + "' and sys_code='"+m_sysCode
                    + "' and menu_code='"+m_progCode+"'";

                Debug.println("sql="+sql);
                menu_name = "";

                //
                if (!created())
                {
                  clearStatus();
                  return  SKIP_BODY;
                }


		        ResultSet rs = op.getData(sql);
		        if (rs != null && rs.next())
		        {
                    menu_name = rs.getString(1);
                    Debug.println("menu_name="+menu_name);
		        }else
		        {
			        Debug.println("rs doesn't exist");
                    // get data from scg_program

                    sql = "select " + prog_name
                        + " from scg_program "
                        + " where company_code='"+company_code
                        + "' and sys_code='"+m_sysCode
                        + "' and prog_code='"+m_progCode+"'" ;

                    Debug.println("sql="+sql);
                    prog_name = "";

                    rs = op.getData(sql);
		            if (rs != null && rs.next())
		            {
                        prog_name = rs.getString(1);
                        Debug.println("prog_name="+prog_name);

                        // set value to menu_name
                        menu_name = prog_name;
                    }else
                    {
                        Debug.println("no rs matched in the scg_program");
                    }
		        }
                m_showName = menu_name;

                //     get showName now
            }
        }
        strLink = "<a href='"+m_href;
        if (hasQuest)
        {
            strLink += "&";
        }else
        {
            strLink += "?" ;
        }
        strLink += "pSysCode="+m_sysCode+"&pProgCode="+m_progCode+"' ";
        if (m_target != null)
        {
            strLink += "target="+m_target;
        }
        strLink += " >"+m_showName+"</a>";
        out.println(strLink);

    }catch(Exception e)
    {
		    // do nothing
	}

    clearStatus();
    return SKIP_BODY;
    }

    private void clearStatus()
    {
        m_href = null;
        m_sysCode = null;
        m_progCode = null;
        m_showName = null;
        hasQuest = false;
        m_target = null;
    }
}