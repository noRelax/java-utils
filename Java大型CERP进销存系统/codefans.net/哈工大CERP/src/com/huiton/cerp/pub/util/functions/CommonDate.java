package com.huiton.cerp.pub.util.functions;

import java.util.*;

/**
 * Title:        ͨ������
 * Description:  ����ͨ��ʹ�õ����ڶ���
 * Copyright:    Copyright Reserved By BRITC
 * Company:      BRITC
 * @author �Ű���
 * @version 1.0
 */

public class CommonDate extends GregorianCalendar
{
	private String m_sep = "/"; //Ĭ�����ڷָ���
    private String m_colon = ":"; //Ĭ�ϵ�Сʱ���ӷָ���

	/**�޲ι�����* */
    public CommonDate()
	{
		super();
	}

	/**������* */
    public CommonDate(long t)
	{
		super();
        super.setTimeInMillis(t);
	}

    /**������* */
    public CommonDate(Date today)
	{
        super();
        super.setTime(today);
	}

    /**������*
     * @param s
     * '_'��Ϊ�����ַ�����׼ȷ��������'2002_02_02' or '20020202' ������ʽ�Ĳ���
     * �����еķǷ��ַ�����ָ���쳣�������ڿ��ܲ�������
     * */
    public CommonDate(String s)
	{

        super();
        // get the length, if it is more than 8, then get separator,
        // and split it use separator

        s = (s==null ? "" : s.trim());
        int len = s.length();

        try
        {
            if (s.length()== 8)
            {
                this.setYear(Integer.parseInt(s.substring(0,4)));
                this.setMonth(Integer.parseInt(s.substring(4,6)));
                this.setDate(Integer.parseInt(s.substring(6,8)));

            }else if (s.length()==10)
            {
                this.setYear(Integer.parseInt(s.substring(0,4)));
                this.setMonth(Integer.parseInt(s.substring(5,7)));
                this.setDate(Integer.parseInt(s.substring(8,10)));
            }else
            {

            }
        }
        catch (Exception e)
        {
            //
        }
	}

    /**������*
     * @param strYear year���ַ���
     * @param strMonth month���ַ���
     * @param strDate date���ַ���
     * */
    public CommonDate(String strYear,String strMonth,String strDate)
	{
        super();
        try
        {
            this.setYear(Integer.parseInt(strYear));
            this.setMonth(Integer.parseInt(strMonth));
            this.setDate(Integer.parseInt(strDate));
        }
        catch(Exception e)
        {   }
    }

    /**������* */
    public CommonDate(int year,int month,int date)
	{
		this(year,month,date,0,0,0);
	}

	/**������* */
    public CommonDate(int year,int month,int date,int hour,int min)
	{
		this(year,month,date,hour,min,0);
	}

    /**������* */
    public CommonDate(int year,int month,int date,int hour,int min,int sec)
	{
		super(year,month-1,date,hour,min,sec);
	}

    /** ���ش������ںʹ�����֮��ķ�����
    * @param cd ��������
    * @return int cd - this
    */
    public int getDiffMinutes(CommonDate cd )
    {
        int thisTime = (int)(this.getTimeInMillis()/60000);
        int cdTime = (int)(cd.getTimeInMillis()/60000);
        return cdTime-thisTime ;
    }

    /**���ؼ�Сʱ��/ǰ������
     * @param hour,(hour>0)����/(hour<0)���ٵ�Сʱ��*/
    public CommonDate addHours(int hour)
    {
        CommonDate other = (CommonDate) this.clone();
        if (hour==0)
            return other;

        other.add(HOUR,hour);
        return other ;
    }

    /**���ؼ����Ӻ�/ǰ������
     * @param min,(min>0)����/(min<0)���ٵķ�����*/
    public CommonDate addMinutes(int min)
    {
        CommonDate other = (CommonDate) this.clone();
        if (min==0)
            return other;

        other.add(MINUTE,min);
        return other ;
    }

    /**���ؼ����ֺ�/ǰ������
     * @param sec,(sec>0)����/(sec<0)���ٵ�����*/
    public CommonDate addSeconds(int sec)
    {
        CommonDate other = (CommonDate) this.clone();
        if (sec==0)
            return other;

        other.add(SECOND,sec);
        return other ;
    }

    /**������*/
    public Object clone()
	{
		{
			CommonDate other = (CommonDate) super.clone();
			other.m_sep = this.m_sep;
			return other;
		}
	}

	public int getYear()
	{
		return this.get(YEAR);
	}

	public int getMonth()
	{
		return  this.get(MONTH)+1;
	}

	public int getDate()
	{
		return this.get(DATE);
	}

    public int getMinute()
    {
        return this.get(MINUTE);
    }

    public int getHour()
    {
        return this.get(HOUR_OF_DAY);
    }

    public void setYear(int year)
    {
        this.set(YEAR,year);
    }

    public void setMonth(int month)
    {
        this.set(MONTH,month-1);
    }

    public void setDate(int date)
    {
        this.set(DAY_OF_MONTH,date);
    }

    /**���ر�Ǩ�������
     * @param i ��Ǩ������������0����ƣ�����ǰ��
     * */
    public CommonDate shiftDate(int i)
	{
        CommonDate cDate = (CommonDate) this.clone();
		if (i==0)
            return cDate;

        cDate.add(DATE, i);
		return cDate;
	}

    public CommonDate prevDate()
	{
		return shiftDate(-1);
	}

	public CommonDate nextDate()
	{
		return shiftDate(1);
	}

	/**�����ܱ�Ǩ�������
     * @param i ��Ǩ������������0����ƣ�����ǰ��
     * */
     public CommonDate shiftWeek(int i)
    {
        return shiftDate(7*i);
    }

    public CommonDate nextWeek()
    {
        return shiftWeek(1);
    }

	public CommonDate prevWeek()
    {
        return shiftWeek(-1);
    }

    /**�����±�Ǩ�������
     * @param i ��Ǩ������������0����ƣ�����ǰ��
     * */
     public CommonDate shiftMonth(int i)
    {
        CommonDate cDate = (CommonDate) this.clone();
		if (i==0)
            return cDate;

        cDate.add(MONTH, i);
		return cDate;
    }

    public CommonDate prevMonth()
    {
        return shiftMonth(-1);
    }

    public CommonDate nextMonth()
    {
        return shiftMonth(1);
    }

    /**�������Ǩ�������
     * @param i ��Ǩ������������0����ƣ�����ǰ��
     * */
    public CommonDate shiftYear(int i)
    {
        CommonDate cDate = (CommonDate) this.clone();
		if (i==0)
            return cDate;

        cDate.add(YEAR, i);
		return cDate;
    }

    public CommonDate prevYear()
    {
        return shiftYear(-1);
    }

    public CommonDate nextYear()
    {
        return shiftYear(1);
    }

    /**�������ڼ�,sunday is 0,monday is 1...
     * */
    public int getDayOfWeek()
    {
        return this.get(DAY_OF_WEEK) - SUNDAY;
    }

    /**���ظ�������
     * */
    public int getDaysOfTheMonth()
    {
        return this.getActualMaximum(DAY_OF_MONTH);
    }

    /**�Ƶ��³�
     * */
    public CommonDate firstDayOfTheMonth()
    {
        return new CommonDate(getYear(),getMonth(),1);
    }

    /**�Ƶ�������ĩ,sunday 0
     * */
    public CommonDate firstDayOfTheWeek()
    {
        return shiftDate(-getDayOfWeek());
    }

    /**�����ַ��������գ��磺2002/01/20
     * */
    public String getYMD()
	{
		int intYear = this.getYear();
		int intMonth = this.getMonth();
		int intDate = this.getDate();

		return intYear + m_sep + (intMonth<10 ? "0"+intMonth : ""+intMonth) + m_sep + (intDate<10 ? "0"+intDate : ""+intDate);
	}
	public String getMainKey()
	{
		int intYear = this.getYear();
		int intMonth = this.getMonth();
		int intDate = this.getDate();

		return intYear + (intMonth<10 ? "0"+intMonth : ""+intMonth) + (intDate<10 ? "0"+intDate : ""+intDate);
	}

	/**�����ַ���ʱ�֣���13:02
     * */
    public String getHM()
    {
        int intHour = getHour();
        int intMinute = getMinute();

        return (intHour<10 ? "0"+intHour : ""+intHour) + ":" + (intMinute<10 ? "0"+intMinute : ""+intMinute);
    }

    /**�������ڷָ���
     * @param sep,�µķָ���
     * */
    public void setSep(String sep)
	{
		sep = (sep==null ? "" : sep.trim());
		if (sep.length()>0)
			m_sep = sep.substring(0,1);
	}

	/**�������ڷָ���
     * */
    public String getSep()
	{
		return m_sep;
	}

    /**����ʱ�ַָ���
     * @param sep,�µķָ���
     * */
    public void setColon(String colon)
	{
		colon = (colon==null ? "" : colon.trim());
		if (colon.length()>0)
			m_colon = colon.substring(0,1);
	}

	/**����ʱ�ַָ���
     * */
    public String getColon()
	{
		return m_colon;
	}

    /**������ת��Ϊ�ַ���
     * */
    public String toString()
    {
        return getYMD();
    }
}