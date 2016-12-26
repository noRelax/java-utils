package lgcsgwxt.means;

import lgcsgwxt.DBAccess;
import java.sql.*;

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
public class Dialog_enroll_SQL {
     public static final String SQL_SELECT="select * from UserManage where CardId=?";
    public boolean IsEmployeeExits(Dialog_enroll_MySQL Dialog_enroll_MySQL)
   {
       DBAccess helper = new DBAccess();
       Connection conn = helper.getConnection();
       PreparedStatement ps = null;
       ResultSet rs = null;
       String sql = "select * from UserManage where CardId=?";
       try {
           ps = conn.prepareStatement(sql);
           ps.setInt(1,Dialog_enroll_MySQL.getCardId());
           rs = ps.executeQuery();
           if(rs.next())
           {
               return true;
           }
           return false;

       } catch (SQLException e) {
          e.printStackTrace();
       }
        return false;
    }


}
