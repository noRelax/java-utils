package com.huiton.cerp.pub.util.functions;

import java.util.*;

/**
 * Title:        通用日期
 * Description:  处理通常使用的日期对象
 * Copyright:    Copyright Reserved By BRITC
 * Company:      BRITC
 * @author 张爱军
 * @version 1.0
 */

public class CommonDate extends GregorianCalendar
{
	private String m_sep = "/"; //默认日期分隔符
    private String m_colon = ":"; //默认的小时分钟分隔符

	/**无参构造器* */
    public CommonDate()
	{
		super();
	}

	/**构造器* */
    public CommonDate(long t)
	{
		super();
        super.setTimeInMillis(t);
	}

    /**构造器* */
    public CommonDate(Date today)
	{
        super();
        super.setTime(today);
	}

    /**构造器*
     * @param s
     * '_'可为任意字符，可准确处理形如'2002_02_02' or '20020202' 两种形式的参数
     * 对其中的非法字符，不指出异常，但日期可能不是所求
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

    /**构造器*
     * @param strYear year的字符串
     * @param strMonth month的字符串
     * @param strDate date的字符串
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

    /**构造器* */
    public CommonDate(int year,int month,int date)
	{
		this(year,month,date,0,0,0);
	}

	/**构造器* */
    public CommonDate(int year,int month,int date,int hour,int min)
	{
		this(year,month,date,hour,min,0);
	}

    /**构造器* */
    public CommonDate(int year,int month,int date,int hour,int min,int sec)
	{
		super(year,month-1,date,hour,min,sec);
	}

    /** 返回传入日期和此日期之间的分钟数
    * @param cd 传入日期
    * @return int cd - this
    */
    public int getDiffMinutes(CommonDate cd )
    {
        int thisTime = (int)(this.getTimeInMillis()/60000);
        int cdTime = (int)(cd.getTimeInMillis()/60000);
        return cdTime-thisTime ;
    }

    /**返回几小时后/前的日期
     * @param hour,(hour>0)增加/(hour<0)减少的小时数*/
    public CommonDate addHours(int hour)
    {
        CommonDate other = (CommonDate) this.clone();
        if (hour==0)
            return other;

        other.add(HOUR,hour);
        return other ;
    }

    /**返回几分钟后/前的日期
     * @param min,(min>0)增加/(min<0)减少的分钟数*/
    public CommonDate addMinutes(int min)
    {
        CommonDate other = (CommonDate) this.clone();
        if (min==0)
            return other;

        other.add(MINUTE,min);
        return other ;
    }

    /**返回几秒种后/前的日期
     * @param sec,(sec>0)增加/(sec<0)减少的秒数*/
    public CommonDate addSeconds(int sec)
    {
        CommonDate other = (CommonDate) this.clone();
        if (sec==0)
            return other;

        other.add(SECOND,sec);
        return other ;
    }

    /**对象复制*/
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

    /**返回变迁后的日期
     * @param i 变迁的天数，大于0则后移，否则前移
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

	/**返回周变迁后的日期
     * @param i 变迁的周数，大于0则后移，否则前移
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

    /**返回月变迁后的日期
     * @param i 变迁的月数，大于0则后移，否则前移
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

    /**返回年变迁后的日期
     * @param i 变迁的年数，大于0则后移，否则前移
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

    /**返回星期几,sunday is 0,monday is 1...
     * */
    public int getDayOfWeek()
    {
        return this.get(DAY_OF_WEEK) - SUNDAY;
    }

    /**返回该月天数
     * */
    public int getDaysOfTheMonth()
    {
        return this.getActualMaximum(DAY_OF_MONTH);
    }

    /**移到月初
     * */
    public CommonDate firstDayOfTheMonth()
    {
        return new CommonDate(getYear(),getMonth(),1);
    }

    /**移到该周周末,sunday 0
     * */
    public CommonDate firstDayOfTheWeek()
    {
        return shiftDate(-getDayOfWeek());
    }

    /**返回字符串年月日，如：2002/01/20
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

	/**返回字符串时分，如13:02
     * */
    public String getHM()
    {
        int intHour = getHour();
        int intMinute = getMinute();

        return (intHour<10 ? "0"+intHour : ""+intHour) + ":" + (intMinute<10 ? "0"+intMinute : ""+intMinute);
    }

    /**设置日期分隔符
     * @param sep,新的分隔符
     * */
    public void setSep(String sep)
	{
		sep = (sep==null ? "" : sep.trim());
		if (sep.length()>0)
			m_sep = sep.substring(0,1);
	}

	/**返回日期分隔符
     * */
    public String getSep()
	{
		return m_sep;
	}

    /**设置时分分隔符
     * @param sep,新的分隔符
     * */
    public void setColon(String colon)
	{
		colon = (colon==null ? "" : colon.trim());
		if (colon.length()>0)
			m_colon = colon.substring(0,1);
	}

	/**返回时分分隔符
     * */
    public String getColon()
	{
		return m_colon;
	}

    /**将对象转变为字符串
     * */
    public String toString()
    {
        return getYMD();
    }
}