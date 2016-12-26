package com.huiton.mainframe.control.web.handlers;

/**
 * Title:        CERP���Կ��
 * Description:  �������ճ�
 * Copyright:    Copyright (c) 2002
 * Company:      BRITC
 * @author �Ű���
 * @version 1.0
 */
import com.huiton.pub.dbx.*;
import java.sql.*;
import java.util.*;
import com.huiton.cerp.pub.util.functions.*;
import javax.servlet.http.*;
import com.huiton.mainframe.util.tracer.Debug;

public abstract class Schedule
{

    protected static final int times = 3; // times try to get pageQuery

    //��Ա
    protected PageQuery pageQuery = null;
    protected String companyCode = null ; //��˾
    protected String userUniqueNo = null ; //�û�Ψһ��
    protected String strFields = null ; //��ѯ�ֶ�
    protected String strTables = null ; //��ѯ�ı�
    protected ResultSet rs = null; //�����
    protected String opFlag = null; //�������
    protected String outFlag = "1"; //����������
    protected String sql = null; //��ѯ��
    protected int pageSize = 15; //ÿҳ��ʾ��
    protected String condition = "" ;  // ��ѯ����
    protected String strToday = null ; //����
    protected Vector vct = null; //

    //������
    public Schedule(String companyCode,String userUniqueNo)
    {
        created();
        this.companyCode = companyCode ;
        this.userUniqueNo = userUniqueNo ;
    }

    //���õĹ�����
    public Schedule() throws Exception
    {
        throw new Exception("The right usage is Schedule(companyCode,userUniqueNo)");
    }

    //pageQuery initialize
    private boolean created()
    {
        try
        {
            for (int i=0; i<times && (pageQuery==null) ;i++)
            {
                pageQuery = new PageQuery("","sam");
            }
            return true ;
        }
        catch (Exception e)
        {
            return false ;
        }
    }

    //��������

    //����
    public void addNew(HttpServletRequest request)
    {
        m_initParams(request);
        m_addNew(request);
        m_setAttr(request);
    }

    //����
    public void insert(HttpServletRequest request)
    {
        m_initParams(request);
        m_insert(request);
        m_query(request);
        m_setAttr(request);

    }

    //�������
    public void insMore(HttpServletRequest request)
    {
        m_initParams(request);
        m_insert(request);
        m_addNew(request);
        m_setAttr(request);
    }

    //�޸�
    public void modify(HttpServletRequest request)
    {
        m_initParams(request);
        m_modify(request);
        m_setAttr(request);
    }

     //����
    public void update(HttpServletRequest request)
    {
        m_initParams(request);
        m_update(request);
        m_query(request);
        m_setAttr(request);
    }

     //ɾ��
    public void delete(HttpServletRequest request)
    {
        m_initParams(request);
        m_delete(request);
        m_query(request);
        m_setAttr(request);
    }

     //ȫɾ
    public void delAll(HttpServletRequest request)
    {
        m_initParams(request);
        m_delAll(request);
        m_setAttr(request);
    }

     //�鿴һ����¼
    public void  review(HttpServletRequest request)
    {
        //�޸�
        modify(request);
    }

     //��ѯ
    public void  query(HttpServletRequest request)
    {
        m_initParams(request);
        m_query(request);
        m_setAttr(request);
    }

    //��������
    //ɾ��
    protected void m_delete(HttpServletRequest request)
    {
        try
        {
            // ɾ��ѡ�м�¼
            String [] id = request.getParameterValues("id");
            if (id !=null)
            {
                    for (int ii=0;ii<id.length;ii++)
                    {

                        String delSql = "delete from sam_user_event "
                            + " where company_code='" + companyCode
                            + "' and log_no=" + id[ii];

                        pageQuery.simpleUpdate(delSql);
                    }
            }

        }
        catch(Exception e)
        {
            Debug.println("delete failed");
            outFlag = "0" ;
        }

    }

    //����
    protected void m_addNew(HttpServletRequest request)
    {
        vct = null;

        try
        {
            pageQuery.getData("event_code,event_name",
                    "sam_event_type",
                    "company_code='" + companyCode + "'",100);

            vct = pageQuery.dividePage(1,100);
        }
        catch(Exception e)
        {}
    }

    //�޸�
    protected void m_modify(HttpServletRequest request)
    {
        try
        {
            String id = request.getParameter("m_id");
                id = (id==null ? "" : id.trim());
                if (id.length()<1) // error
                {
                    outFlag = "0";
                }
                else
                {
                    sql = "select cur_date,start_time,stop_time,event_subject,"
                        + "event_location,event_code,"
                        + "alert_days,event_desc,deal_desc,deal_flag,priv_flag "
                        + " from sam_user_event where company_code='" + companyCode
                        + "' and log_no=" + id ;

                    Debug.println("sql=" + sql);
                    rs = pageQuery.getData(sql);

                    if (rs != null && rs.next())
                    {

                        request.setAttribute("m_id",id);
                        request.setAttribute("cur_date",rs.getString(1));
                        request.setAttribute("start_time",rs.getString(2));
                        request.setAttribute("stop_time",rs.getString(3));
                        request.setAttribute("event_subject",rs.getString(4));
                        request.setAttribute("event_location",rs.getString(5));
                        request.setAttribute("event_code",rs.getString(6));
                        request.setAttribute("alert_days",rs.getInt(7)+"");
                        request.setAttribute("event_desc",rs.getString(8));
                        request.setAttribute("deal_desc",rs.getString(9));
                        request.setAttribute("deal_flag",rs.getString(10));
                        request.setAttribute("priv_flag",rs.getString(11));

                        outFlag = "1";

                    }else
                    {
                        outFlag = "0";
                    }
                }

                pageQuery.getData("event_code,event_name",
                    "sam_event_type",
                    "company_code='" + companyCode + "'",100);

                vct = pageQuery.dividePage(1,100);
        }
        catch(Exception e)
        {
            Debug.println("modify failed");
            outFlag = "0" ;
        }

    }

    //����
    protected void m_update(HttpServletRequest request)
    {
        try
        {
                //������Ĳ���
                String cur_date = request.getParameter("cur_date");
                String start_hour = request.getParameter("start_hour");
                String stop_hour = request.getParameter("stop_hour");
                String start_min = request.getParameter("start_min");
                String stop_min = request.getParameter("stop_min");
                String alert_days = request.getParameter("alert_days");
                String event_code = request.getParameter("event_code");
                String event_subject = request.getParameter("event_subject");
                String event_location = request.getParameter("event_location");
                String event_desc = request.getParameter("event_desc");
                String deal_desc = request.getParameter("deal_desc");
                String deal_flag = request.getParameter("deal_flag");
                String priv_flag = request.getParameter("priv_flag");

                cur_date = (cur_date==null ? "" : cur_date.trim());
                start_hour = (start_hour==null ? "" : start_hour.trim());
                stop_hour = (stop_hour==null ? "" : stop_hour.trim());
                start_min = (start_min==null ? "" : start_min.trim());
                stop_min = (stop_min==null ? "" : stop_min.trim());
                alert_days = (alert_days==null ? "" : alert_days.trim());
                event_code = (event_code==null ? "" : event_code.trim());
                event_subject = (event_subject==null ? "" : event_subject.trim());
                event_location = (event_location==null ? "" : event_location.trim());
                event_desc = (event_desc==null ? "" : event_desc.trim());
                deal_desc = (deal_desc==null ? "" : deal_desc.trim());
                deal_flag = (deal_flag==null ? "" : deal_flag.trim());
                priv_flag = (priv_flag==null ? "" : priv_flag.trim());

                cur_date = (cur_date.length()<1 ? strToday : cur_date);
                deal_flag = (deal_flag.length()<1 ? "N" : deal_flag);
                priv_flag = (priv_flag.length()<1 ? "N" : priv_flag);
                alert_days = (alert_days.length()<1 ? "0" : alert_days);

                String mColon = new CommonDate().getColon(); //Сʱ���ӷָ��� :
                String start_time = start_hour + mColon + start_min;
                String stop_time = stop_hour + mColon + stop_min;

                String log_no = request.getParameter("m_id");
                log_no = (log_no==null ? "" : log_no.trim());

                // get event_code,event_name from event_code
                int pos = event_code.indexOf("__");
                int len = event_code.length();
                String event_name = "" ;
                if (pos >= 0)
                {
                    event_name = event_code.substring(pos+2,len);
                    event_code = event_code.substring(0,pos);
                }
                // �õ�event_name

                if (log_no.length()<1 || event_code.length()<1 || event_subject.length()<1)
                {
                    outFlag = "0" ;

                }else
                {
                    sql = "update sam_user_event set cur_date='" + cur_date
                        + "',start_time='" + start_time + "',stop_time='" + stop_time
                        + "',alert_days=" + alert_days + ",event_code='" + event_code
                        + "',event_name='" + event_name + "',event_subject='" + event_subject
                        + "',event_location='" + event_location + "',event_desc='" + event_desc
                        + "',deal_desc='" + deal_desc + "',deal_flag='" + deal_flag
                        + "',priv_flag='" + priv_flag + "' where company_code='" + companyCode
                        + "' and log_no=" + log_no ;

                    Debug.println("sql="+sql);
                    boolean bFlag = pageQuery.simpleUpdate(sql);
                    outFlag = (bFlag ? "1" : "0");
                }
        }
        catch(Exception e)
        {
            Debug.println("update failed");
            outFlag = "0" ;
        }

    }

    //����
    protected void m_insert(HttpServletRequest request)
    {
        try
        {
                //������Ĳ���
                String cur_date = request.getParameter("cur_date");
                String start_hour = request.getParameter("start_hour");
                String stop_hour = request.getParameter("stop_hour");
                String start_min = request.getParameter("start_min");
                String stop_min = request.getParameter("stop_min");
                String alert_days = request.getParameter("alert_days");
                String event_code = request.getParameter("event_code");
                String event_subject = request.getParameter("event_subject");
                String event_location = request.getParameter("event_location");
                String event_desc = request.getParameter("event_desc");
                String deal_desc = request.getParameter("deal_desc");
                String deal_flag = request.getParameter("deal_flag");
                String priv_flag = request.getParameter("priv_flag");

                cur_date = (cur_date==null ? "" : cur_date.trim());
                start_hour = (start_hour==null ? "" : start_hour.trim());
                stop_hour = (stop_hour==null ? "" : stop_hour.trim());
                start_min = (start_min==null ? "" : start_min.trim());
                stop_min = (stop_min==null ? "" : stop_min.trim());
                alert_days = (alert_days==null ? "" : alert_days.trim());
                event_code = (event_code==null ? "" : event_code.trim());
                event_subject = (event_subject==null ? "" : event_subject.trim());
                event_location = (event_location==null ? "" : event_location.trim());
                event_desc = (event_desc==null ? "" : event_desc.trim());
                deal_desc = (deal_desc==null ? "" : deal_desc.trim());
                deal_flag = (deal_flag==null ? "" : deal_flag.trim());
                priv_flag = (priv_flag==null ? "" : priv_flag.trim());

                cur_date = (cur_date.length()<1 ? strToday : cur_date);
                deal_flag = (deal_flag.length()<1 ? "N" : deal_flag);
                priv_flag = (priv_flag.length()<1 ? "N" : priv_flag);
                alert_days = (alert_days.length()<1 ? "0" : alert_days);

                String mColon = new CommonDate().getColon(); //Сʱ���ӷָ��� :
                String start_time = start_hour + mColon + start_min;
                String stop_time = stop_hour + mColon + stop_min;

                // get event_code,event_name from event_code
                int pos = event_code.indexOf("__");
                int len = event_code.length();
                String event_name = "" ;
                if (pos >= 0)
                {
                    event_name = event_code.substring(pos+2,len);
                    event_code = event_code.substring(0,pos);
                }
                // �õ�event_name

                Debug.println("insert now");
                if (event_code.length()<1 || event_subject.length()<1)
                {
                    Debug.println("event_code="+event_code+",event_subject="+event_subject);
                    outFlag = "0" ;
                }else
                {
                    sql = "insert into sam_user_event(company_code,log_no,user_unique_no,cur_date,"
                        + "start_time,stop_time,event_subject,event_location,event_code,event_name,"
                        + "alert_days,event_desc,deal_desc,deal_flag,priv_flag) "
                        + " values('" + companyCode + "'," + CerpGetNo.getNo(companyCode,"sam_user_event")
                        + ",'" + userUniqueNo + "','" + cur_date + "','" + start_time + "','" + stop_time
                        + "','" + event_subject + "','" + event_location + "','" + event_code
                        + "','" + event_name + "'," + alert_days + ",'" + event_desc + "','"
                        + deal_desc + "','" + deal_flag + "','" + priv_flag + "')" ;

                    Debug.println("sql=" + sql);
                    boolean bFlag = pageQuery.simpleUpdate(sql);
                    outFlag = (bFlag ? "1" : "0");

                }
            }
            catch(Exception e)
            {
                Debug.println("insert failed");
                outFlag = "0" ;
            }

    }

    protected abstract void  m_initParams(HttpServletRequest request);
    protected abstract void  m_setAttr(HttpServletRequest request);
    protected abstract void  m_query(HttpServletRequest request);
    protected abstract void  m_delAll(HttpServletRequest request);
}