package lgcsgwxt.means;

import java.util.Date;
import java.util.Calendar;

/**
 * <p>Title: 鲁广超市进销存系统</p>
 *
 * <p>Description: 北大青鸟鲁广校区S1毕业设计</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: ST-117班</p>
 *
 * @author ST-117班第二小组
 * @version 1.0
 */
public class GetTime {
    public GetTime() {
    }
    public static String getTime() { //得到当前时间
        Date date1 = new Date();
        Calendar objcalendar;
        String time = "";
        objcalendar = Calendar.getInstance();
        int year = objcalendar.get(Calendar.YEAR);
        int Month = objcalendar.get(Calendar.MONTH);
        Month += 1;
        Integer Month1 = new Integer(Month);
        String Monthtos;
        if (Month < 10) {
            Monthtos = "0" + Month1.toString();
        } else {
            Monthtos = Month1.toString();
        }
        int Date = objcalendar.get(Calendar.DATE);
        Integer Date1 = new Integer(Date);
        String Datetos;
        if (Date < 10) {
            Datetos = "0" + Date1.toString();
        } else {
            Datetos = Date1.toString();
        }

        String time1 = date1.toString().substring(11, 19);
        time =year + "-" + Monthtos + "-" + Datetos + " " + time1;

        return time;

    }

}
