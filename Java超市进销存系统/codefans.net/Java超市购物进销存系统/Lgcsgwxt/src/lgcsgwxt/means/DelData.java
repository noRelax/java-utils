package lgcsgwxt.means;

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
import java.sql.*;
import lgcsgwxt.DBAccess;

public class DelData {
    public DelData() {
    }

    private static final String delData1 =
            "delete from UserManage where cardId=?";
    public static int delCard(String cardId) {
        int i = 0;
        Connection conn = null;
        PreparedStatement pmst = null;
        ResultSet rs = null;
        conn = DBAccess.getConnection();

        try {
            pmst = conn.prepareStatement(delData1);
            pmst.setString(1, cardId);
            i = pmst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pmst != null) {
                    pmst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }

        return i;
    }
}
