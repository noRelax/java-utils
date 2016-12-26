package com.huiton.mainframe.control.web.handlers;

import com.huiton.cerp.pub.util.DBOperators;
import com.huiton.mainframe.control.event.CERPEvent;
import com.huiton.mainframe.control.exceptions.DuplicateRecordException;
import com.huiton.mainframe.control.web.CustomerWebImpl;
import com.huiton.mainframe.control.web.ModelManager;
import com.huiton.mainframe.control.web.handlers.RequestHandlerSupport;
import com.huiton.mainframe.util.tracer.Debug;
import com.huiton.pub.dbx.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SelectFavorTypeHandler extends RequestHandlerSupport
{
    public SelectFavorTypeHandler()
    {
    }

    public CERPEvent processRequest(HttpServletRequest request)
        throws Exception, DuplicateRecordException, SQLException
    {
        Debug.println("----SelectFavorTypeHandler: processRequest()");
        ModelManager mm = (ModelManager)request.getSession().getAttribute("mm");
        CustomerWebImpl customer = mm.getCustomerWebImpl();

        String companyCode = customer.getCompanyCode();
        String sessionCode = customer.getSessionCode();
        String userUniqueNo = customer.getUserUniqueNo();
        String lang = customer.getLanguage();
        String loc = request.getParameter("loc"); // no
        loc = (loc==null ? "" : loc.trim());

        String sql = null;
        int currentPage = 0; //当前页
        int pageCount = 0; //总页数

        // get currentPage and pageCount
        String m_currentPage = request.getParameter("currentPage");
        m_currentPage = (m_currentPage==null ? "1" : m_currentPage.trim());
        currentPage = Integer.parseInt(m_currentPage);
        currentPage = (currentPage < 1 ? 1 : currentPage);

        m_currentPage = request.getParameter("pageCount");
        m_currentPage = (m_currentPage==null ? "1" : m_currentPage.trim());
        pageCount = Integer.parseInt(m_currentPage);
        pageCount = (pageCount < 1 ? 1 : pageCount);

        int pageSize = 20;
        try
        {
            PageQuery pageQuery = DBOperators.getPageQuery(request,
                sessionCode,"sam","SelectFavorTypeHandler");

            String strFields = "distinct favor_type";
            String strTables = "sam_user_favor";
            String strConditions = "company_code='" + companyCode
                + "' and user_unique_no='" + userUniqueNo + "'" ;

            pageQuery.getData(strFields,strTables,strConditions,pageSize);
            pageCount = pageQuery.pageCount;
            pageCount = (pageCount < 1 ? 1 : pageCount);
            currentPage = (currentPage > pageCount ? pageCount : currentPage);
            Vector vct = pageQuery.dividePage(currentPage,pageSize);

            request.setAttribute("vct",vct);
        }
        catch(Exception e)
        {
        }
        request.setAttribute("loc",loc);
        request.setAttribute("pageCount",pageCount+"");
        request.setAttribute("currentPage",currentPage+"");
        return null;
    }
}
