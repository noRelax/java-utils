package com.huiton.mainframe.control.web.handlers;

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
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddFavorHandler extends RequestHandlerSupport
{

    public AddFavorHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        try
        {
            Debug.println("----AddFavorHandler: processRequest()");
            ModelManager mm = (ModelManager)request.getSession().getAttribute("mm");
            CustomerWebImpl customer = mm.getCustomerWebImpl();

            String companyCode = customer.getCompanyCode();
            String userUniqueNo = customer.getUserUniqueNo();
            String sessionCode = customer.getSessionCode();
            String lang = customer.getLanguage();

            String opFlag = null; //operation flag
            String outFlag = "1"; // outcome flag, 1 means succeeds
            Vector vct = null; //保存记录
            String sql = null;

            int currentPage = 0; //当前页
            int pageCount = 0; //总页数

            opFlag = request.getParameter("opFlag");
            opFlag = (opFlag==null ? "" : opFlag.trim());
            opFlag = (opFlag.length()<1 ? "query" : opFlag);

            int pageSize = 15;
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode, SubsystemKeys.SAM, "AddFavorHandler");

            String condition = "";  // set query Condition to nothing
            //selectAll //全选标记
            String selectAll = request.getParameter("selectAll");
            selectAll = (selectAll==null ? "0" : selectAll.trim());

            // get currentPage and pageCount
            String m_currentPage = request.getParameter("currentPage");
            m_currentPage = (m_currentPage==null ? "1" : m_currentPage.trim());
            currentPage = Integer.parseInt(m_currentPage);
            currentPage = (currentPage < 1 ? 1 : currentPage);

            m_currentPage = request.getParameter("pageCount");
            m_currentPage = (m_currentPage==null ? "1" : m_currentPage.trim());
            pageCount = Integer.parseInt(m_currentPage);
            pageCount = (pageCount < 1 ? 1 : pageCount);

            Debug.println("----opFlag=" + opFlag);

            // to decide which field to get
            String favor_name = "favor_name_cn";
            String prog_name = "prog_name_cn";
            if (lang.equalsIgnoreCase("en"))
            {
                favor_name = "favor_name_en";
                prog_name = "prog_name_en";
            }

            if (opFlag.equalsIgnoreCase("query"))
            {
                // 查询
                String strFields = "sys_code,prog_code," + favor_name + ",favor_type" ;
                String strConditions = null;
                if (condition.length()<1)
                {
                    strConditions = " company_code='"+companyCode
                        +"' and user_unique_no='"+userUniqueNo
                        +"' order by favor_type,favor_index ";
                }else
                {
                    strConditions = condition + " and (company_code='"+companyCode
                        +"' and user_unique_no='"+userUniqueNo
                        +"') order by favor_type,favor_index ";
                }

                String strTables = "sam_user_favor_v";

                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount;
                pageCount = (pageCount < 1 ? 1 : pageCount);
                currentPage = (currentPage > pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);
            }
            else if (opFlag.equalsIgnoreCase("delete"))
            {
                // 删除选中记录
                String [] id = request.getParameterValues("id");
                if (id !=null)
                {

                for (int ii=0;ii<id.length;ii++)
                {
                    // split id into sys_code and prog_code
                    int sepPos = id[ii].indexOf("__");
                    sepPos = (sepPos<0 ? id[ii].length() : sepPos);

                    String mm_sys_code = id[ii].substring(0,sepPos);
                    String mm_prog_code = id[ii].substring((sepPos==id[ii].length() ? sepPos : sepPos+2));

                    Debug.println("id["+ii+"] = " +id[ii]);
                    String delSql = "delete from sam_user_favor "
                            + " where company_code='" + companyCode
                            + "' and user_unique_no='" + userUniqueNo
                            + "' and sys_code='" + mm_sys_code
                            + "' and prog_code='" + mm_prog_code + "'";

                    pageQuery.simpleUpdate(delSql);

                }

                }
                // show data
                String strFields =  "sys_code,prog_code," + favor_name + ",favor_type" ;
                String strConditions = null;
                if (condition.length()<1)
                {
                    strConditions = " company_code='"+companyCode
                        +"' and user_unique_no='"+userUniqueNo
                        +"' order by favor_type,favor_index ";
                }else
                {
                    strConditions = condition + " and (company_code='"+companyCode
                        +"' and user_unique_no='"+userUniqueNo
                        +"') order by favor_type,favor_index ";
                }

                String strTables = "sam_user_favor_v";

                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount;
                pageCount = (pageCount < 1 ? 1 : pageCount);
                currentPage = (currentPage > pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);
            }
            else if (opFlag.equalsIgnoreCase("delAll"))
            {
                // 删除所有符合条件的记录
                String strConditions = null;
                if (condition.length()<1)
                {
                    strConditions = " company_code='"+companyCode
                        +"' and user_unique_no='"+userUniqueNo+"' ";
                }else
                {
                    strConditions = condition + " and (company_code='"+companyCode
                        +"' and user_unique_no='" + userUniqueNo + "') ";
                }

                String strTables = "sam_user_favor";

                boolean bFlag = pageQuery.simpleUpdate("delete from "
                    + strTables + " where " + strConditions);

                outFlag = (bFlag ? "1" : "0");
                pageCount = 1 ;
                currentPage = 1 ;
                selectAll = "0" ;
                vct = new Vector();
            }
            else if (opFlag.equalsIgnoreCase("addNew"))
            {
                String m_url = request.getParameter("m_url");
                Debug.println("m_url="+m_url);

                m_url = (m_url==null ? "" : m_url.trim());
                if (m_url.length()<1)
                {
                    outFlag = "0";
                }else
                {
                    Debug.println("before trimed m_url="+m_url);
                    int startPos = m_url.indexOf("//");
                    startPos = (startPos>-1 ? startPos+2 : 0); // get start Position
                    int idxPos = m_url.indexOf("/",startPos);
                    idxPos = (idxPos>-1 ? idxPos : 0 );

                    m_url = m_url.substring(idxPos); // get substring
                    Debug.println("after trimed m_url="+m_url);

                    int sepPos = m_url.indexOf("::");
                    sepPos = (sepPos<0 ? m_url.length() : sepPos);
                    String progValue = m_url.substring(0,sepPos);
                    String[] value = (new CerpSAM()).getSysAndProgCode(progValue,companyCode);
                    String mm_sys_code = value[0];
                    String mm_prog_code = value[1];

                    // check if it exists already
                    sql = "select 1 from sam_user_favor_v "
                        + " where company_code='" + companyCode
                        + "' and user_unique_no='" + userUniqueNo
                        + "' and sys_code='" + mm_sys_code
                        + "' and prog_code='" + mm_prog_code+ "'";

                    Debug.println(sql);
                    ResultSet rs = pageQuery.getData(sql);
                    if (rs !=null && rs.next())
                    {
                        outFlag = "0";
                    }else
                    {
                        sql = "select " + prog_name
                            + " from scg_program "
                            + " where company_code='" + companyCode
                            + "' and sys_code='" + mm_sys_code
                            + "' and prog_code='" + mm_prog_code + "'" ;

                        Debug.println("mm_sys_code="+mm_sys_code+",mm_prog_code="+mm_prog_code);
                        Debug.println(sql);
                        rs = pageQuery.getData(sql);

                        if (rs != null && rs.next())
                        {
                            String m_favor_name = rs.getString(1);
                            request.setAttribute("m_favor_name",m_favor_name);
                            request.setAttribute("mm_sys_code",mm_sys_code);
                            request.setAttribute("mm_prog_code",mm_prog_code);
                        }else
                        {
                            outFlag = "0";
                        }
                    }
                }
                vct = new Vector();
            }
            else if (opFlag.equalsIgnoreCase("insert"))
            {
                // insert
                String mm_sys_code = request.getParameter("mm_sys_code");
                String mm_prog_code = request.getParameter("mm_prog_code");
                String m_favor_name = request.getParameter("m_favor_name");
                String favor_type = request.getParameter("favor_type");

                Debug.println("mm_sys_code="+mm_sys_code+",mm_prog_code="+mm_prog_code+",m_favor_name="+m_favor_name);

                String favor_index = "0";
                mm_sys_code = (mm_sys_code==null ? "" : mm_sys_code.trim());
                mm_prog_code = (mm_prog_code==null ? "" : mm_prog_code.trim());
                favor_type = (favor_type==null ? "" : favor_type.trim());
                m_favor_name = (m_favor_name==null ? "" : m_favor_name.trim());

                sql = "select 1 from sam_user_favor "
                    + " where company_code='" + companyCode
                    + "' and user_unique_no='" + userUniqueNo
                    + "' and sys_code='" + mm_sys_code
                    + "' and prog_code='" + mm_prog_code + "'";

                Debug.println("sql=" + sql);

                ResultSet rs = pageQuery.getData(sql);
                if (rs != null && rs.next())
                {
                    outFlag = "0";
                }else
                {
                    sql = "insert into sam_user_favor(company_code,user_unique_no,sys_code,prog_code,favor_name_cn,favor_name_en,favor_name_tw,favor_type,favor_index) "
                        + " select '" + companyCode + "','" + userUniqueNo + "','"
                        + mm_sys_code + "','" + mm_prog_code + "',prog_name_cn,prog_name_en,prog_name_tw,'" + favor_type + "'," + favor_index
                        + " from scg_program where company_code='" +companyCode+ "' and sys_code='" + mm_sys_code + "' and prog_code='" + mm_prog_code + "'";

                    Debug.println("sql=" + sql);
                    boolean bFlag = pageQuery.simpleUpdate(sql);

                    if  (bFlag)
                    {
                        sql = "update sam_user_favor set " + favor_name + "='" + m_favor_name
                            + "' where company_code='" + companyCode + "' and user_unique_no='" + userUniqueNo
                            + "' and sys_code='" + mm_sys_code + "' and prog_code='" + mm_prog_code + "'";

                        Debug.println("sql=" + sql);
                        bFlag = pageQuery.simpleUpdate(sql);
                        outFlag = (bFlag ? "1" : "0");
                    }else
                    {
                        outFlag = "0";
                    }
                }

                if (outFlag.equals("1"))
                {
                    // show data
                    String strFields =  "sys_code,prog_code," + favor_name + ",favor_type" ;
                    String strConditions = null;
                    if (condition.length()<1)
                    {
                        strConditions = " company_code='"+companyCode
                            + "' and user_unique_no='"+userUniqueNo
                            + "' order by favor_type,favor_index ";
                    }else
                    {
                        strConditions = condition + " and (company_code='"+companyCode
                            + "' and user_unique_no='"+userUniqueNo
                            + "') order by favor_type,favor_index ";
                    }

                    String strTables = "sam_user_favor_v";

                    pageQuery.getData(strFields,strTables,strConditions,pageSize);
                    pageCount = pageQuery.pageCount;
                    pageCount = (pageCount < 1 ? 1 : pageCount);
                    currentPage = (currentPage > pageCount ? pageCount : currentPage);
                    vct = pageQuery.dividePage(currentPage,pageSize);
                }else
                {
                    vct = new Vector();
                }
            }
            else if (opFlag.equalsIgnoreCase("update"))
            {
                // update
                String mm_sys_code = request.getParameter("mm_sys_code");
                String mm_prog_code = request.getParameter("mm_prog_code");
                String m_favor_name = request.getParameter("m_favor_name");
                String favor_type = request.getParameter("favor_type");

                mm_sys_code = (mm_sys_code==null ? "" : mm_sys_code.trim());
                mm_prog_code = (mm_prog_code==null ? "" : mm_prog_code.trim());
                favor_type = (favor_type==null ? "" : favor_type.trim());
                m_favor_name = (m_favor_name==null ? "" : m_favor_name.trim());

                sql = "update sam_user_favor "
                    + " set " + favor_name + "='" + m_favor_name
                    + "',favor_type='" + favor_type
                    + "' where company_code='" + companyCode
                    + "' and user_unique_no='" + userUniqueNo
                    + "' and sys_code='" + mm_sys_code
                    + "' and prog_code='" + mm_prog_code + "'";

                boolean bFlag = pageQuery.simpleUpdate(sql);
                outFlag = (bFlag ? "1" : "0");

                // show data
                String strFields =  "sys_code,prog_code," + favor_name + ",favor_type" ;
                String strConditions = null;
                if (condition.length()<1)
                {
                    strConditions = " company_code='"+companyCode
                        + "' and user_unique_no='"+userUniqueNo
                        + "' order by favor_type,favor_index ";
                }else
                {
                    strConditions = condition + " and (company_code='"+companyCode
                        + "' and user_unique_no='"+userUniqueNo
                        + "') order by favor_type,favor_index ";
                }

                String strTables = "sam_user_favor_v";

                pageQuery.getData(strFields,strTables,strConditions,pageSize);
                pageCount = pageQuery.pageCount;
                pageCount = (pageCount < 1 ? 1 : pageCount);
                currentPage = (currentPage > pageCount ? pageCount : currentPage);
                vct = pageQuery.dividePage(currentPage,pageSize);

            }
            else // unknown situation
            {
                vct = new Vector();
            }

            request.setAttribute("vct",vct);
            request.setAttribute("opFlag",opFlag);
            request.setAttribute("outFlag",outFlag);
            request.setAttribute("pageCount",pageCount+"");
            request.setAttribute("currentPage",currentPage+"");
            request.setAttribute("selectAll",selectAll);
        }
        catch(Exception e)
        {

        }
        return null;
    }
}
